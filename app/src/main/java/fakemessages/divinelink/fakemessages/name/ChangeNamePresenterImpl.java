package fakemessages.divinelink.fakemessages.name;

import android.content.Context;

import java.util.List;


public class ChangeNamePresenterImpl implements IChangeNamePresenter, IChangeNameInteractor.OnGetNameChangeListener {

    private final IChangeNameView nameView;
    private final IChangeNameInteractor interactor;

    public ChangeNamePresenterImpl(IChangeNameView nameView) {
        this.nameView = nameView;
        interactor = new ChangeNameInteractorImpl();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void getName(Context ctx) {

    }

    @Override
    public void setName(Context ctx) {

    }
}
