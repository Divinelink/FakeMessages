package fakemessages.divinelink.fakemessages.details;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Addresses")
public class AddressDomain {

    @PrimaryKey
    private int id;

    private String address;
    private String area;

    public AddressDomain(int id, String address, String area) {
        this.id = id;
        this.address = address;
        this.area = area;
    }
    @Ignore
    public AddressDomain(String address, String area) {
        this.address = address;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
