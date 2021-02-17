package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                Calendar cal1 = Calendar.getInstance();
                cal1.add(Calendar.DATE, -1);

                if (messages.size() == 0) {
                    GenerateRandomEntries(ctx, messagesDao);
                } else {
                    String lastone = messagesDao.getLastTwoItems().get(1).getMessageTime().split(",")[0];
                    String yesterday = cal1.getTime().toString().split(" ")[0];
                    if (!lastone.equals(yesterday)) {
                        messagesDao.deleteAll();
                        GenerateRandomEntries(ctx, messagesDao);
                    }
                    messagesDao.updateLastMessage(messages.get(messages.size() - 1),
                            new MessageDomain(
                                    messagesDao.getLatestMessage().getId(),
                                    timeFormatter.format(new Date(System.currentTimeMillis() - 1800 * 1000)),
                                    getNameSharedPref(ctx),
                                    getAddressSharedPref(ctx),
                                    getAreaSharedPref(ctx),
                                    Integer.toString(getCodeSharedPref(ctx))));
                }
                listener.onSuccess(messagesDao.getMessages());
            }
        });
    }

    public void GenerateRandomEntries(Context ctx, MessagesDao messageDao) {

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEEEE, d MMM");
        Random r = new Random();
        final int HHmin = 10;
        final int HHmax = 19;
        final int MMmin = 0;
        final int MMmax = 59;


        int i = 10;
        while (i >= 0) {
            Calendar cal = Calendar.getInstance();
            int HH = r.nextInt(HHmax - HHmin - 1) + HHmin;
            int MM = r.nextInt(MMmax - MMmin - 1) + MMmin;
            cal.add(Calendar.DATE, -i);
            String messageCode = (i % 3 == 0) ? "2" : "6";
            String date;
            if (i != 0)
                date = dateFormatter.format(cal.getTime()) + String.format(" %02d:%02d", HH, MM);
            else
                date = timeFormatter.format(new Date(System.currentTimeMillis() - 1800 * 1000));

            messageDao.insertMessage(new MessageDomain(
                    10-i,
                    date,
                    getNameSharedPref(ctx),
                    getAddressSharedPref(ctx),
                    getAreaSharedPref(ctx),
                    messageCode));
            i--;
        }
    }

    @Override
    public String getAddressSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("address", ctx.getString(R.string.default_address));
    }

    @Override
    public String getAreaSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("area", ctx.getString(R.string.default_area));
    }

    @Override
    public String getNameSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString("name", ctx.getString(R.string.default_name));
    }

    @Override
    public int getCodeSharedPref(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getInt("codeID", 6);
    }
}
