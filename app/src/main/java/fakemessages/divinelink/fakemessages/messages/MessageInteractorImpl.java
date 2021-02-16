package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.HomeDatabase;
import fakemessages.divinelink.fakemessages.base.IGetSharedPrefManager;

public class MessageInteractorImpl implements IMessageInteractor, IGetSharedPrefManager {

    @Override
    public void getMessagesFromDB(final OnGetMessagesFinishListener listener, final Context ctx) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final MessagesDao messagesDao = HomeDatabase.getDatabase(ctx).messagesDao();
                final List<MessageDomain> messages = messagesDao.getMessages();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

                if (messages.size() == 0) {
                    // Time, senderMessageText, receiverMessageText
                    messagesDao.insertMessage(new MessageDomain("Sun, 14:21",
                            getNameSharedPref(ctx),
                            getAddressSharedPref(ctx),
                            getAreaSharedPref(ctx),
                            "6"));
                    messagesDao.insertMessage(new MessageDomain("Mon, 12:44",
                            getNameSharedPref(ctx),
                            getAddressSharedPref(ctx),
                            getAreaSharedPref(ctx),
                            "2"));
                    messagesDao.insertMessage(new MessageDomain("Yesterday, 09:15",
                            getNameSharedPref(ctx),
                            getAddressSharedPref(ctx),
                            getAreaSharedPref(ctx),
                            "6"));
                    messagesDao.insertMessage(new MessageDomain("Yesterday, 16:56",
                            getNameSharedPref(ctx),
                            getAddressSharedPref(ctx),
                            getAreaSharedPref(ctx),
                            "6"));
                    messagesDao.insertMessage(new MessageDomain(formatter.format(new Date(System.currentTimeMillis() - 1800 * 1000)),
                            getNameSharedPref(ctx),
                            getAddressSharedPref(ctx),
                            getAreaSharedPref(ctx),
                            "6"));
                } else {
                    messagesDao.updateLastMessage(messages.get(messages.size() - 1),
                            new MessageDomain(formatter.format(new Date(System.currentTimeMillis() - 1800 * 1000)),
                                    getNameSharedPref(ctx),
                                    getAddressSharedPref(ctx),
                                    getAreaSharedPref(ctx),
                                    "6"));
                }


                listener.onSuccess(messagesDao.getMessages());
            }
        });
    }

    @Override
    public String getAddressSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("address", "ΣΠΕΤΣΩΝ 25");
    }

    @Override
    public String getAreaSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("area", "ΑΙΓΑΛΕΩ");
    }

    @Override
    public String getNameSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("name", "ΛΕΩΝΙΔΑΣ ΤΡΙΑΝΤΑΦΥΛΛΟΣ");
    }
}
