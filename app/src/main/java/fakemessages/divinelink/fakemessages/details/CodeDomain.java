package fakemessages.divinelink.fakemessages.details;

import androidx.room.Entity;

@Entity(tableName = "Codes")
public class CodeDomain {

    int id;

    public CodeDomain(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
