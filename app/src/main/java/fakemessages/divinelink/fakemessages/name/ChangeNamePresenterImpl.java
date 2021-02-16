package fakemessages.divinelink.fakemessages.name;

import android.content.Context;


public class ChangeNamePresenterImpl implements IChangeNamePresenter, IChangeNameInteractor.OnSetNameChangeListener, IChangeNameInteractor.OnGetNameChangeListener {

    private final IChangeNameView nameView;
    private final IChangeNameInteractor interactor;

    public ChangeNamePresenterImpl(IChangeNameView nameView) {
        this.nameView = nameView;
        interactor = new ChangeNameInteractorImpl();
    }


    @Override
    public void onSuccess() {
        nameView.showSuccessNameChanged();
    }

    @Override
    public void onEmptyName() {
        nameView.showErrorEmptyNameField();
    }

    @Override
    public void getName(Context ctx) {
        interactor.getNameFromSharedPref(this, ctx);
    }

    @Override
    public void onSuccessGetName(String name) {
        nameView.showNameOnEditText(name);
    }

    @Override
    public void setName(Context ctx, String name) {
        interactor.setNewName(this, ctx, name);
    }
}
