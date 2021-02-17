package fakemessages.divinelink.fakemessages.code;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.Date;

import fakemessages.divinelink.fakemessages.base.HomeDatabase;
import fakemessages.divinelink.fakemessages.base.IGetSharedPrefManager;
import fakemessages.divinelink.fakemessages.messages.MessageDomain;
import fakemessages.divinelink.fakemessages.messages.MessagesDao;
import fakemessages.divinelink.fakemessages.name.IChangeNameInteractor;

public class ChangeCodeInteractorImpl implements IChangeCodeInteractor {

    @Override
    public void setNewCode(final OnSetNewCodeChangeListener listener, final Context ctx, final int newMessageCode) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final MessagesDao messagesDao = HomeDatabase.getDatabase(ctx).messagesDao();

                messagesDao.updateLastMessage(messagesDao.getLatestMessage(),
                        new MessageDomain(
                                messagesDao.getLatestMessage().getId(),
                                messagesDao.getLatestMessage().getMessageTime(),
                                messagesDao.getLatestMessage().getUserName(),
                                messagesDao.getLatestMessage().getUserAddress(),
                                messagesDao.getLatestMessage().getUserCity(),
                                Integer.toString(newMessageCode)));

                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
                final SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("codeID", newMessageCode);
                editor.apply();

                listener.onSuccessNewCode(newMessageCode);

            }
        });
    }

    @Override
    public void getCode(OnGetNewCodeListener listener, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        listener.onSuccessGetCode(preferences.getInt("codeID", 6));
    }
}



