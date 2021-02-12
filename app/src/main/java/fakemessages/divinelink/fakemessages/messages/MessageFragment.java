package fakemessages.divinelink.fakemessages.messages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.IHomeView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment implements IMessageView {

    IHomeView homeView;
    private RecyclerView messagesRV;

    private Toolbar mToolBar;

    private IMessagePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_message, container, false);

        homeView = (IHomeView) getArguments().getSerializable("home_view");

        messagesRV = (RecyclerView) v.findViewById(R.id.messagesRV);

        mToolBar = (Toolbar) v.findViewById(R.id.toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        messagesRV.setLayoutManager(layoutManager);



        presenter = new MessagePresenterImpl(this);

        presenter.getMessages(getActivity());

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MessageFragment newInstance(IHomeView homeView) {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        args.putSerializable("home_view", homeView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showMessages(final List<MessageDomain> messages) {


        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                final MessageRvAdapter messageRvAdapter = new MessageRvAdapter(messages, getActivity());

                @Override
                public void run() {
                    messagesRV.setAdapter(messageRvAdapter);
                }
            });

        }
    }
}