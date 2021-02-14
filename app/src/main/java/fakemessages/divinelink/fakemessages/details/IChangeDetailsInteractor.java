package fakemessages.divinelink.fakemessages.details;


import android.content.Context;

import java.util.List;

public interface IChangeDetailsInteractor {

    void getAddressesFromDB (IChangeDetailsInteractor.OnGetAddressFinishListener listener, Context ctx);

    void removeAddressFromDB(IChangeDetailsInteractor.OnRemoveAddressFinishListener listener, Context ctx, AddressDomain address);

    void saveAddressOnDB (onSaveAddressFinishListener listener, Context ctx, String address, String area);

    void setNewAddress (onSetNewAddressFinishListener listener, Context ctx, AddressDomain address);

    interface onSetNewAddressFinishListener{
        void onSuccessSet();
        void onError();
    }

    interface OnRemoveAddressFinishListener{
        void onSuccessRemoval(int position);
        void onError();
    }

    interface onSaveAddressFinishListener{

        void onSuccessSave(List<AddressDomain> addresses);
        void onError();

    }

    interface OnGetAddressFinishListener{

        void onSuccess(List<AddressDomain> addresses);
        void onError();

    }
}
