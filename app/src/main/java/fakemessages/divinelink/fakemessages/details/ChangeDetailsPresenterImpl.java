package fakemessages.divinelink.fakemessages.details;

import android.content.Context;

import java.util.List;


public class ChangeDetailsPresenterImpl implements IChangeDetailsPresenter,
        IChangeDetailsInteractor.OnRemoveAddressFinishListener,
        IChangeDetailsInteractor.OnGetAddressFinishListener,
        IChangeDetailsInteractor.onSaveAddressFinishListener,
        IChangeDetailsInteractor.onSetNewAddressFinishListener {

    private final IChangeDetailsView detailsView;
    private final IChangeDetailsInteractor interactor;

    public ChangeDetailsPresenterImpl(IChangeDetailsView detailsView) {
        this.detailsView = detailsView;
        interactor = new ChangeDetailsInteractorImpl();
    }

    @Override
    public void getAddresses(Context ctx) {
        interactor.getAddressesFromDB(this, ctx);
    }

    @Override
    public void onSuccess(List<AddressDomain> addresses) {
        detailsView.showAddresses(addresses);
    }

    // -- SAVE ADDRESS -- \\
    @Override
    public void saveAddress(Context ctx, String address, String area) {
        interactor.saveAddressOnDB(this, ctx, address, area);
    }

    @Override
    public void onSuccessSave(List<AddressDomain> addresses) {
        detailsView.showInsertedAddress(addresses);
    }

    // -- END SAVE ADDRESS -- \\

    // -- SET NEW ADDRESS -- \\
    @Override
    public void changeCurrentAddress(Context ctx, AddressDomain address) {
        interactor.setNewAddress(this, ctx, address);
    }

    @Override
    public void onSuccessSet(AddressDomain address) {

        detailsView.showSuccessMessage(address);

    }
    // -- END NEW ADDRESS -- \\

    // -- REMOVE ADDRESS -- \\
    @Override
    public void removeAddress(Context ctx, AddressDomain address, int position) {
        interactor.removeAddressFromDB(this, ctx, address, position);
    }

    @Override
    public void onSuccessRemoval(int position) {
        detailsView.showRemovedAddress(position);
    }

    // -- END REMOVE ADDRESS -- \\


    @Override
    public void onFieldsEmpty(AddressDomain address) {
        if (address.getAddress().equals("") && address.getArea().equals(""))
            detailsView.showEmptyFieldsWarning();
        else if (address.getArea().equals(""))
            detailsView.showEmptyFieldWarning("Area");
        else if (address.getAddress().equals(""))
            detailsView.showEmptyFieldWarning("Address");
    }

    @Override
    public void onError() {

    }

}
