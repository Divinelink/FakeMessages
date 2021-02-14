package fakemessages.divinelink.fakemessages.details;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertAddress(AddressDomain address);

    @Query("SELECT * FROM Addresses")
    public abstract List<AddressDomain> getAddresses();

    @Query("DELETE FROM Addresses")
    abstract void deleteAll();

    @Query("DELETE FROM Addresses WHERE id=:id")
    abstract void delete(int id);


}
