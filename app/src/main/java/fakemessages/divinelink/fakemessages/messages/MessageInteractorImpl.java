package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fakemessages.divinelink.fakemessages.R;
import fakemessages.divinelink.fakemessages.base.HomeDatabase;

public class MessageInteractorImpl implements IMessageInteractor {

    @Override
    public void getMessagesFromDB(final OnGetMessagesFinishListener listener, final Context ctx) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final MessagesDao messagesDao = HomeDatabase.getDatabase(ctx).messagesDao();

                messagesDao.deleteAll();

                final List<MessageDomain> messages = messagesDao.getMessages();

                if (messages.size() == 0) {
                    // Time, senderMessageText, receiverMessageText
                    messagesDao.insertMessage(new MessageDomain("Sun, 14:21",
                            "ΑΝΔΡΕΟΛΑΣ ΧΑΡΑΛΑΜΠΟΣ",
                            "ΣΠΕΤΣΩΝ 24",
                            "ΠΕΡΙΣΤΕΡΙ",
                            "6"));
                    messagesDao.insertMessage(new MessageDomain("Mon, 12:44",
                            "ΑΝΔΡΕΟΛΑΣ ΧΑΡΑΛΑΜΠΟΣ",
                            "ΣΠΕΤΣΩΝ 24",
                            "ΠΕΡΙΣΤΕΡΙ",
                            "2"));

                    messagesDao.insertMessage(new MessageDomain("Yesterday, 09:15",
                            "ΑΝΔΡΕΟΛΑΣ ΧΑΡΑΛΑΜΠΟΣ",
                            "ΣΠΕΤΣΩΝ 24",
                            "ΠΕΡΙΣΤΕΡΙ",
                            "6"));
                    messagesDao.insertMessage(new MessageDomain("Yesterday, 16:56",
                            "ΑΝΔΡΕΟΛΑΣ ΧΑΡΑΛΑΜΠΟΣ",
                            "ΣΠΕΤΣΩΝ 24",
                            "ΠΕΡΙΣΤΕΡΙ",
                            "6"));
                }




                SimpleDateFormat formatter = new SimpleDateFormat("HH:MM");


                messagesDao.insertMessage(new MessageDomain(formatter.format(new Date(System.currentTimeMillis() - 1800 * 1000)),
                        "ΑΝΔΡΕΟΛΑΣ ΧΑΡΑΛΑΜΠΟΣ",
                        "ΣΠΕΤΣΩΝ 24",
                        "ΠΕΡΙΣΤΕΡΙ",
                        "6"));

                listener.onSuccess(messagesDao.getMessages());

            }

        });
    }

    private void addMessagesToDB() {

    }
}
