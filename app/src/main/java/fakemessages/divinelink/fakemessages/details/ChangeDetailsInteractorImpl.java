package fakemessages.divinelink.fakemessages.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.List;

import fakemessages.divinelink.fakemessages.base.HomeDatabase;
import fakemessages.divinelink.fakemessages.base.ISetSharedPrefManager;
import fakemessages.divinelink.fakemessages.messages.MessagesDao;

public class ChangeDetailsInteractorImpl implements IChangeDetailsInteractor, ISetSharedPrefManager {

    @Override
    public void getAddressesFromDB(final OnGetAddressFinishListener listener, final Context ctx) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final AddressDao addressDao = HomeDatabase.getDatabase(ctx).addressDao();
                final List<AddressDomain> addresses = addressDao.getAddresses();
                listener.onSuccess(addresses);
            }
        });
    }

    @Override
    public void setNewAddress(final onSetNewAddressFinishListener listener, final Context ctx, final AddressDomain address) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final AddressDao addressDao = HomeDatabase.getDatabase(ctx).addressDao();
                final MessagesDao messageDao = HomeDatabase.getDatabase(ctx).messagesDao();

                messageDao.updateAddress(address.getAddress());
                messageDao.updateArea(address.getArea());

                setAddress(ctx, address);

                listener.onSuccessSet();
            }
        });

    }

    @Override
    public void removeAddressFromDB(final OnRemoveAddressFinishListener listener, final Context ctx, final AddressDomain address) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final AddressDao addressDao = HomeDatabase.getDatabase(ctx).addressDao();
                final List<AddressDomain> addresses = addressDao.getAddresses();

                addressDao.delete(address.getId());

                listener.onSuccessRemoval(addresses.indexOf(address));
            }
        });
    }

    @Override
    public void saveAddressOnDB(final onSaveAddressFinishListener listener, final Context ctx, final String address, final String area) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final AddressDao addressDao = HomeDatabase.getDatabase(ctx).addressDao();
                final List<AddressDomain> addresses = addressDao.getAddresses();
                addresses.add(new AddressDomain(address, area));
                addressDao.insertAddress(new AddressDomain(address, area));
                listener.onSuccessSave(addresses);
            }
        });
    }

    @Override
    public void setName(Context ctx) {

    }

    @Override
    public void setAddress(Context ctx, AddressDomain address) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString("address", address.getAddress());
        editor.putString("area", address.getArea());
        editor.apply();
    }
}
