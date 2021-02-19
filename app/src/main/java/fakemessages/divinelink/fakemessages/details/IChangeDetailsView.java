package fakemessages.divinelink.fakemessages.details;

import android.graphics.drawable.AnimationDrawable;

import java.util.List;

public interface IChangeDetailsView {

    void showInsertedAddress(List<AddressDomain> addressDomain);

    void showRemovedAddress(int position);

    void showAddresses(List<AddressDomain> addresses);

    void showEmptyFieldsWarning();

    void showEmptyFieldWarning(String area);

    void showSuccessMessage(AddressDomain address);

}
