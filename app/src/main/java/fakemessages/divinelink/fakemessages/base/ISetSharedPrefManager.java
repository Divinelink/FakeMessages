package fakemessages.divinelink.fakemessages.base;

import android.content.Context;

import fakemessages.divinelink.fakemessages.details.AddressDomain;

public interface ISetSharedPrefManager {

    void setName(Context ctx);

    void setAddress(Context ctx, AddressDomain address);

}
