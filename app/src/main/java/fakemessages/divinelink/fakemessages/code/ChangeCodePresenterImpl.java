package fakemessages.divinelink.fakemessages.code;

import android.content.Context;

import fakemessages.divinelink.fakemessages.name.ChangeNameInteractorImpl;
import fakemessages.divinelink.fakemessages.name.IChangeNameInteractor;
import fakemessages.divinelink.fakemessages.name.IChangeNamePresenter;
import fakemessages.divinelink.fakemessages.name.IChangeNameView;


public class ChangeCodePresenterImpl implements IChangeCodePresenter, IChangeCodeInteractor.OnSetNewCodeChangeListener, IChangeCodeInteractor.OnGetNewCodeListener {

    private final IChangeCodeView codeView;
    private final IChangeCodeInteractor interactor;

    public ChangeCodePresenterImpl(IChangeCodeView codeView) {
        this.codeView = codeView;
        interactor = new ChangeCodeInteractorImpl();
    }

    @Override
    public void getCode(Context ctx) {

        interactor.getCode(this, ctx);

    }

    @Override
    public void setCode(Context ctx, int id) {
        interactor.setNewCode(this, ctx, id);
    }

    @Override
    public void onSuccessNewCode(int code) {
        codeView.showSuccessCodeChanged(code);
    }

    @Override
    public void onSuccessGetCode(int code) {
        codeView.setCodeOnRadioGroup(code);
    }
}
