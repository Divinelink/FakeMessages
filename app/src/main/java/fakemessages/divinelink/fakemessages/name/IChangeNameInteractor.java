package fakemessages.divinelink.fakemessages.name;


import android.content.Context;

public interface IChangeNameInteractor {

    void setNewName(OnSetNameChangeListener listener, Context ctx, String name);

    void getNameFromSharedPref(OnGetNameChangeListener listener, Context ctx);

    interface OnGetNameChangeListener {
        void onSuccessGetName(String name);
    }

    interface OnSetNameChangeListener {
        void onSuccess();

        void onEmptyName();

    }
}
