package fakemessages.divinelink.fakemessages.details;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.IHomeView;
import fakemessages.divinelink.fakemessages.messages.IMessagePresenter;
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
    private TextView mDetailsTextView;
    private EditText mAddressEditText, mAreaEditText;
    private RecyclerView detailsRV;
    private Button mAddAddressBtn;


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
        detailsRV = (RecyclerView) v.findViewById(R.id.DetailsRV);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        detailsRV.setLayoutManager(layoutManager);

        mDetailsTextView.setText(details);


        presenter = new ChangeDetailsPresenterImpl(this);

        mAddAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Add warning when fields are empty.
                presenter.saveAddress(getContext(), mAddressEditText.getText().toString(), mAreaEditText.getText().toString());
            }
        });
        if (details.equals("address")) {
            presenter.getAddresses(getContext());
        }


        return v;
    }

    @Override
    public void showUpdatedAddresses(int position) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    detailsRV.getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void changeCurrentAddress(final List<AddressDomain> addresses) {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(),
                            "New Address is: " + addresses.get(addresses.size() - 1).getAddress()
                                    + " " + addresses.get(addresses.size() - 1).getArea(),
                            Toast.LENGTH_SHORT).show();

                    mAddressEditText.getText().clear();
                    mAreaEditText.getText().clear();

                    detailsRV.getAdapter().notifyDataSetChanged();

                }
            });
        }
    }

    @Override
    public void showAddresses(List<AddressDomain> addresses) {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                final ChangeDetailsRvAdapter detailRvAdapter = new ChangeDetailsRvAdapter(addresses, new OnAddressClickListener() {
                    @Override
                    public void onAddressClick(AddressDomain address, int position) {
                        new MaterialAlertDialogBuilder(getContext())
                                .setTitle(R.string.set_address)
                                .setMessage(String.format("Set %s, %s as your current address?",
                                        addresses.get(position).getAddress(),
                                        addresses.get(position).getArea()))
                                .setPositiveButton(R.string.set_address, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        presenter.changeCurrentAddress(getContext(), address);

                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                    @Override
                    public void onAddressLongClick(AddressDomain address, int position) {
                        new MaterialAlertDialogBuilder(getContext())
                                .setTitle(R.string.remove_address)
                                .setMessage(String.format("Are you sure you want to delete %s, $s?",
                                        addresses.get(position).getAddress(),
                                        addresses.get(position).getArea()))
                                .setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        presenter.removeAddress(getContext(), address);
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                }, getActivity());
                @Override
                public void run() {
                    detailsRV.setAdapter(detailRvAdapter);
                }
            });
        }
    }


}