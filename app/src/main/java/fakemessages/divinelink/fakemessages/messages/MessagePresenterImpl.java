package fakemessages.divinelink.fakemessages.messages;

import android.content.Context;

import java.util.List;

public class MessagePresenterImpl implements IMessagePresenter, IMessageInteractor.OnGetMessagesFinishListener {

    private final IMessageView messageView;
    private final IMessageInteractor interactor;

    public MessagePresenterImpl(IMessageView messageView) {
        this.messageView = messageView;
        interactor = new MessageInteractorImpl();
    }

    @Override
    public void getMessages(Context ctx) {

        interactor.getMessagesFromDB(this, ctx);

    }

    @Override
    public void onSuccess(List<MessageDomain> messages) {
        messageView.showMessages(messages);
    }

    @Override
    public void onError() {

    }
}
