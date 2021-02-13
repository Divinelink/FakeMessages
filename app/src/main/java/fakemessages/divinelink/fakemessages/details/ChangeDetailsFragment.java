package fakemessages.divinelink.fakemessages.details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.IHomeView;
import fakemessages.divinelink.fakemessages.messages.IMessagePresenter;
import fakemessages.divinelink.fakemessages.messages.MessageFragment;
import fakemessages.divinelink.fakemessages.messages.MessageRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeDetailsFragment extends Fragment implements IChangeDetailsView {


    private static final String DETAILS = "details";
    private String details;


    IHomeView homeView;
    TextView mDetailsTextView;
    EditText mAddressEditText, mAreaEditText;

    Button mAddAddressBtn;


    private IChangeDetailsPresenter presenter;


    public static ChangeDetailsFragment newInstance(IHomeView homeView, String details) {
        Bundle args = new Bundle();
        ChangeDetailsFragment fragment = new ChangeDetailsFragment();
        args.putSerializable("home_view", homeView);
        args.putString("details", details);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            details = getArguments().getString(DETAILS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_details, container, false);

        mDetailsTextView = (TextView) v.findViewById(R.id.changeDetailsTextView);
        mAddressEditText = (EditText) v.findViewById(R.id.addressEditText);
        mAreaEditText = (EditText) v.findViewById(R.id.areaEditText);

        mAddAddressBtn = (Button) v.findViewById(R.id.addAddressBtn);

        mDetailsTextView.setText(details);

        //TODO ADD GOOGLE SEARCH ON ADDRESS INPUT
        presenter = new ChangeDetailsPresenterImpl(this);

        mAddAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Add warning when fields are empty.
                presenter.saveAddress(getContext(), mAddressEditText.getText().toString(), mAreaEditText.getText().toString());
            }
        });



        return v;
    }

    @Override
    public void changeCurrentAddress(final AddressDomain addressDomain) {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "New Address is: " + addressDomain.getAddress() + " " + addressDomain.getArea(), Toast.LENGTH_SHORT).show();
                    mAddressEditText.getText().clear();
                    mAreaEditText.getText().clear();
                }
            });
        }
    }
}