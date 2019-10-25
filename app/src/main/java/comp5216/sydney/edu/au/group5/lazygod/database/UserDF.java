package comp5216.sydney.edu.au.group5.lazygod.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import comp5216.sydney.edu.au.group5.lazygod.entities.UserInfo;


@Entity(tableName = "user", indices = {@Index("uuid")})
public class UserDF {
    // A database form class

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @ColumnInfo(name = "nickName")
    private String nickName;

    @ColumnInfo(name = "phone")
    private String phone;

    public UserDF(String uuid, String nickName, String phone) {
        this.nickName = nickName;
        this.phone = phone;
        this.uuid = uuid;
    }

    public UserDF (UserInfo userInfo) {
        this.uuid = userInfo.getUuid();
        this.nickName = userInfo.getNickName();
        this.phone = userInfo.getPhone();
    }

    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public String getNickName() {
        return nickName;
    }
}