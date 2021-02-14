package fakemessages.divinelink.fakemessages.messages;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMessage(MessageDomain message);

    @Query("SELECT * FROM Messages")
    public abstract List<MessageDomain> getMessages();

    @Query("DELETE FROM Messages")
    abstract void deleteAll();

    @Query("DELETE FROM Messages WHERE id=:id")
    abstract void delete(int id);

    @Query("UPDATE Messages SET userAddress = :newAddress")
    public abstract void updateAddress(String newAddress);

    @Query("UPDATE Messages SET userCity = :newArea")
    public abstract void updateArea(String newArea);

    @Query("UPDATE Messages SET userName = :newName")
    public abstract void updateName(String newName);

    @Transaction
    public void updateLastMessage(MessageDomain oldMessage, MessageDomain newMessage) {
        delete(oldMessage.getId());
        insertMessage(newMessage);
    }

}
