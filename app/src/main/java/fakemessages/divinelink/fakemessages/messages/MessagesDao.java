package fakemessages.divinelink.fakemessages.messages;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMessage(MessageDomain message);

    @Query("SELECT * FROM Messages")
    public abstract List<MessageDomain> getMessages();

    @Query("DELETE FROM Messages")
    abstract void deleteAll();

}
