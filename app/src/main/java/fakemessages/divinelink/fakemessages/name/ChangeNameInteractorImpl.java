package fakemessages.divinelink.fakemessages.name;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.HomeDatabase;
import fakemessages.divinelink.fakemessages.base.IGetSharedPrefManager;
import fakemessages.divinelink.fakemessages.messages.MessagesDao;

public class ChangeNameInteractorImpl implements IChangeNameInteractor {

    @Override
    public void setNewName(final OnSetNameChangeListener listener, final Context ctx, final String name) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final MessagesDao messagesDao = HomeDatabase.getDatabase(ctx).messagesDao();

                if (name.equals(""))
                    listener.onEmptyName();
                else {
                    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", name); // We set name to be on Uppercase when we pass it to presenter on the fragment.
                    messagesDao.updateName(name);
                    editor.apply();
                    listener.onSuccess();
                }
            }
        });
    }

    @Override
    public void getNameFromSharedPref(OnGetNameChangeListener listener, Context ctx) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        listener.onSuccessGetName(preferences.getString("name", ctx.getString(R.string.default_name)));

    }
}



