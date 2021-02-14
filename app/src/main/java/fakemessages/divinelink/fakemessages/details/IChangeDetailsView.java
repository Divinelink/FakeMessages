package fakemessages.divinelink.fakemessages.details;

import java.util.List;

public interface IChangeDetailsView {

    void changeCurrentAddress(List<AddressDomain> addressDomain);

    void showAddresses(List<AddressDomain> addresses);

    void showUpdatedAddresses(int position);

}
