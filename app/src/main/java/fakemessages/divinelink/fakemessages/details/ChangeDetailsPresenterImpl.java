package fakemessages.divinelink.fakemessages.details;

import android.content.Context;


public class ChangeDetailsPresenterImpl implements IChangeDetailsPresenter, IChangeDetailsInteractor.OnGetAddressFinishListener, IChangeDetailsInteractor.onSaveAddressFinishListener{

    private final IChangeDetailsView detailsView;
    private final IChangeDetailsInteractor interactor;

    public ChangeDetailsPresenterImpl(IChangeDetailsView detailsView) {
        this.detailsView = detailsView;
        interactor = new ChangeDetailsInteractorImpl();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void getAddresses(Context ctx) {

    }

    @Override
    public void saveAddress(Context ctx, String address, String area) {
        interactor.saveAddressOnDB(this, ctx, address, area);
    }

    @Override
    public void onSuccessSave(AddressDomain newAddress) {
        detailsView.changeCurrentAddress(newAddress);
    }
}
