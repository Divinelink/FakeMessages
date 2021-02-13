package fakemessages.divinelink.fakemessages.details;


import android.content.Context;

public interface IChangeDetailsInteractor {

    void getAddressesFromDB (IChangeDetailsInteractor.OnGetAddressFinishListener listener, Context ctx);

    void saveAddressOnDB (onSaveAddressFinishListener listener, Context ctx, String address, String area);


    interface onSaveAddressFinishListener{

        void onSuccessSave(AddressDomain addressDomain);
        void onError();

    }

    interface OnGetAddressFinishListener{

        void onSuccess();
        void onError();

    }
}
