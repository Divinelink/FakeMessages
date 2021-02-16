package fakemessages.divinelink.fakemessages.name;

import java.util.List;

import fakemessages.divinelink.fakemessages.details.AddressDomain;

public interface IChangeNameView {

    void showErrorEmptyNameField();

    void showSuccessNameChanged();

    void showNameOnEditText(String name);

}
