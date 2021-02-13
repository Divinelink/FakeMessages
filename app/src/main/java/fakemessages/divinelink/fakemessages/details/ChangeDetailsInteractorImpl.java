package fakemessages.divinelink.fakemessages.details;

import android.content.Context;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fakemessages.divinelink.fakemessages.base.HomeDatabase;
import fakemessages.divinelink.fakemessages.messages.MessageDomain;
import fakemessages.divinelink.fakemessages.messages.MessagesDao;

public class ChangeDetailsInteractorImpl implements IChangeDetailsInteractor {

    @Override
    public void getAddressesFromDB(OnGetAddressFinishListener listener, Context ctx) {

    }

    @Override
    public void saveAddressOnDB(final onSaveAddressFinishListener listener, final Context ctx, final String address, final String area) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final AddressDao addressDao = HomeDatabase.getDatabase(ctx).addressDao();
                final AddressDomain newAddress = new AddressDomain(address, area);
                addressDao.insertAddress(newAddress);



                listener.onSuccessSave(newAddress);

            }
        });
    }
}
