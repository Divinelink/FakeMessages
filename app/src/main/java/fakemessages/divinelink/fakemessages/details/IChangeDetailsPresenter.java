package fakemessages.divinelink.fakemessages.details;

import android.content.Context;

public interface IChangeDetailsPresenter {

    void getAddresses(Context ctx);

    void saveAddress(Context ctx, String address, String area);

    void changeCurrentAddress(Context ctx, AddressDomain address);

    void removeAddress(Context ctx, AddressDomain address, int position);

}
