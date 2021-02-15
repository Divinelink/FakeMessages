package fakemessages.divinelink.fakemessages.name;


import android.content.Context;

import java.util.List;

import fakemessages.divinelink.fakemessages.details.AddressDomain;

public interface IChangeNameInteractor {

    void setNewName (OnGetNameChangeListener listener, Context ctx);

    interface OnGetNameChangeListener{

        void onSuccess();
        void onError();

    }
}
