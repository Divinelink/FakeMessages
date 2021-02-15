package fakemessages.divinelink.fakemessages.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.details.ChangeDetailsFragment;
import fakemessages.divinelink.fakemessages.messages.MessageFragment;
import fakemessages.divinelink.fakemessages.name.ChangeNameFragment;

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

    @Override
    public void addChangeDetailsFragment(String details) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.homeRoot, ChangeDetailsFragment.newInstance(this, details))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void addChangeNameFragment(String name) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.homeRoot, ChangeNameFragment.newInstance(this, name))
                .addToBackStack(null)
                .commit();
    }
}