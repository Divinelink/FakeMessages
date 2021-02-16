package fakemessages.divinelink.fakemessages.base;

import android.content.Context;

public interface IGetSharedPrefManager {

    String getAddressSharedPref(Context ctx);

    String getAreaSharedPref(Context ctx);

    String getNameSharedPref(Context ctx);
}

