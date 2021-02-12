package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;

import java.util.List;

public interface IMessageInteractor {

    void getMessagesFromDB(IMessageInteractor.OnGetMessagesFinishListener listener, Context ctx);

    interface OnGetMessagesFinishListener{

        void onSuccess(List<MessageDomain> messages);
        void onError();
    }





}
