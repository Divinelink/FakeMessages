package fakemessages.divinelink.fakemessages.details;


import android.content.Context;

import java.util.List;

public interface IChangeDetailsInteractor {

    void getAddressesFromDB (IChangeDetailsInteractor.OnGetAddressFinishListener listener, Context ctx);

    void removeAddressFromDB(IChangeDetailsInteractor.OnRemoveAddressFinishListener listener, Context ctx, AddressDomain address, int position);

    void saveAddressOnDB (onSaveAddressFinishListener listener, Context ctx, String address, String area);

    void setNewAddress (onSetNewAddressFinishListener listener, Context ctx, AddressDomain address);

    interface onSetNewAddressFinishListener{
        void onSuccessSet(AddressDomain address);
        void onError();
    }

    interface OnRemoveAddressFinishListener{
        void onSuccessRemoval(int position);
        void onError();
    }

    interface onSaveAddressFinishListener{

        void onSuccessSave(List<AddressDomain> addresses);
        void onFieldsEmpty(AddressDomain address);

    }

    interface OnGetAddressFinishListener{

        void onSuccess(List<AddressDomain> addresses);
        void onError();

    }
}
