package fakemessages.divinelink.fakemessages.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import fakemessages.divinelink.fakemessages.details.AddressDomain;
import fakemessages.divinelink.fakemessages.details.AddressDao;
import fakemessages.divinelink.fakemessages.messages.MessageDomain;
import fakemessages.divinelink.fakemessages.messages.MessagesDao;

@Database(entities = {MessageDomain.class, AddressDomain.class}, version = 4, exportSchema = false)
abstract public class HomeDatabase extends RoomDatabase {

    public abstract MessagesDao messagesDao();
    public abstract AddressDao addressDao();


    static private HomeDatabase INSTANCE;

    public static HomeDatabase getDatabase(Context ctx) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                    HomeDatabase.class, "Home_Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
