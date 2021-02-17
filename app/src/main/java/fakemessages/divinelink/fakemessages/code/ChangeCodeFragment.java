package fakemessages.divinelink.fakemessages.code;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.IHomeView;
import fakemessages.divinelink.fakemessages.details.ChangeDetailsFragment;
import fakemessages.divinelink.fakemessages.name.ChangeNamePresenterImpl;
import fakemessages.divinelink.fakemessages.name.IChangeNamePresenter;
import fakemessages.divinelink.fakemessages.name.IChangeNameView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeCodeFragment extends Fragment implements IChangeCodeView {


    private IChangeCodePresenter presenter;

    private RadioGroup mCodeGroup;


    public static ChangeCodeFragment newInstance(IHomeView homeView) {
        Bundle args = new Bundle();
        ChangeCodeFragment fragment = new ChangeCodeFragment();
        args.putSerializable("home_view", homeView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_change_code, container, false);

        mCodeGroup = (RadioGroup) v.findViewById(R.id.radioGroupCodes);
        presenter = new ChangeCodePresenterImpl(this);

        presenter.getCode(getContext());

        mCodeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                presenter.setCode(getContext(), mCodeGroup.indexOfChild(v.findViewById(mCodeGroup.getCheckedRadioButtonId())) + 1);
            }
        });
        return v;
    }

    @Override
    public void showSuccessCodeChanged(int code) {

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), String.format(getString(R.string.successCodeMessage), code), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void setCodeOnRadioGroup(int code) {
        switch (code) {
            case 1:
                mCodeGroup.check(R.id.code1);
                return;
            case 2:
                mCodeGroup.check(R.id.code2);
                return;
            case 3:
                mCodeGroup.check(R.id.code3);
                return;
            case 4:
                mCodeGroup.check(R.id.code4);
                return;
            case 5:
                mCodeGroup.check(R.id.code5);
                return;
            case 6:
                mCodeGroup.check(R.id.code6);
        }
    }
}