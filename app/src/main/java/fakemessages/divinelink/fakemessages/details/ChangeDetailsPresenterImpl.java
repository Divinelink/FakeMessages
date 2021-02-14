package fakemessages.divinelink.fakemessages.details;

import android.content.Context;

import java.util.List;


public class ChangeDetailsPresenterImpl implements IChangeDetailsPresenter,
        IChangeDetailsInteractor.OnRemoveAddressFinishListener,
        IChangeDetailsInteractor.OnGetAddressFinishListener,
        IChangeDetailsInteractor.onSaveAddressFinishListener,
        IChangeDetailsInteractor.onSetNewAddressFinishListener{

    private final IChangeDetailsView detailsView;
    private final IChangeDetailsInteractor interactor;

    public ChangeDetailsPresenterImpl(IChangeDetailsView detailsView) {
        this.detailsView = detailsView;
        interactor = new ChangeDetailsInteractorImpl();
    }


    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(List<AddressDomain> addresses) {
        detailsView.showAddresses(addresses);
    }

    @Override
    public void getAddresses(Context ctx) {
        interactor.getAddressesFromDB(this, ctx);
    }

    @Override
    public void saveAddress(Context ctx, String address, String area) {
        interactor.saveAddressOnDB(this, ctx, address, area);
    }

    @Override
    public void onSuccessSave(List<AddressDomain> addresses) {
        detailsView.changeCurrentAddress(addresses);
    }

    @Override
    public void changeCurrentAddress(Context ctx, AddressDomain address) {
        interactor.setNewAddress(this, ctx, address);
    }

    @Override
    public void onSuccessSet() {

    }

    @Override
    public void onSuccessRemoval(int position) {
        detailsView.showUpdatedAddresses(position);
    }

    @Override
    public void removeAddress(Context ctx, AddressDomain address) {
        interactor.removeAddressFromDB(this, ctx, address);
    }
}
