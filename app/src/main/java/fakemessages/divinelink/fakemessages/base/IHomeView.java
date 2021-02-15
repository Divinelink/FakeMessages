package fakemessages.divinelink.fakemessages.base;

import java.io.Serializable;

public interface IHomeView extends Serializable {

    void addMessageFragment();

    void addChangeDetailsFragment(String details);

    void addChangeNameFragment(String name);
}
