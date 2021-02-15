package fakemessages.divinelink.fakemessages.name;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Telephony;
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


    IHomeView homeView;

    private IChangeNamePresenter presenter;


    public static ChangeDetailsFragment newInstance(IHomeView homeView, String name) {
        Bundle args = new Bundle();
        ChangeDetailsFragment fragment = new ChangeDetailsFragment();
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


        presenter = new ChangeNamePresenterImpl(this);


        return v;
    }

    @Override
    public void showNameOptions(List<AddressDomain> addressDomain) {

    }
}