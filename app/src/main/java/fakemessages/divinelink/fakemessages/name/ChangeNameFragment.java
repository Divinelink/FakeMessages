package fakemessages.divinelink.fakemessages.name;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.IHomeView;
import fakemessages.divinelink.fakemessages.details.AddressDomain;
import fakemessages.divinelink.fakemessages.details.ChangeDetailsFragment;
import fakemessages.divinelink.fakemessages.details.ChangeDetailsPresenterImpl;
import fakemessages.divinelink.fakemessages.details.IChangeDetailsPresenter;
import fakemessages.divinelink.fakemessages.messages.IMessagePresenter;
import fakemessages.divinelink.fakemessages.messages.MessageRvAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeNameFragment extends Fragment implements IChangeNameView {


    private static final String NAME = "name";
    private String name;

    private Button mChangeNameBtn;
    private EditText mNameEditText;

    IHomeView homeView;

    private IChangeNamePresenter presenter;


    public static ChangeNameFragment newInstance(IHomeView homeView, String name) {
        Bundle args = new Bundle();
        ChangeNameFragment fragment = new ChangeNameFragment();
        args.putSerializable("home_view", homeView);
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_change_name, container, false);

        mChangeNameBtn = (Button) v.findViewById(R.id.setNameBtn);
        mNameEditText = (EditText) v.findViewById(R.id.editTextTextPersonName);

        presenter = new ChangeNamePresenterImpl(this);

        presenter.getName(getContext());

        mChangeNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setName(getContext(), mNameEditText.getText().toString().toUpperCase());
            }
        });

        return v;
    }

    @Override
    public void showErrorEmptyNameField() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Field name should not be empty.", Toast.LENGTH_SHORT).show();
                    mNameEditText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mNameEditText, InputMethodManager.SHOW_IMPLICIT);
                }
            });
        }
    }

    @Override
    public void showNameOnEditText(String name) {
        mNameEditText.setText(name);
    }

    @Override
    public void showSuccessNameChanged() {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Successfully changed name.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}