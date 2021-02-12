package fakemessages.divinelink.fakemessages.messages;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Messages")
public class MessageDomain {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String messageTime;
    //User
    private String userName;
    private String userAddress;
    private String userCity;
    private String messageCode;


    public MessageDomain(int id, String messageTime, String userName, String userAddress, String userCity, String messageCode) {
        this.id = id;
        this.messageTime = messageTime;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.messageCode = messageCode;
    }

    @Ignore
    public MessageDomain(String messageTime, String userName, String userAddress, String userCity, String messageCode) {

        this.messageTime = messageTime;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.messageCode = messageCode;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
