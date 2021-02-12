package fakemessages.divinelink.fakemessages.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.messages.MessageFragment;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addMessageFragment();
    }

    @Override
    public void addMessageFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.homeRoot, MessageFragment.newInstance(this))
                .commit();
    }
}