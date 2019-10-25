package comp5216.sydney.edu.au.group5.lazygod.entities;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserInfo {
    String uuid;                          // used emile as uuid
    String nickName;
    String phone;
    //String password;

    public UserInfo(String uuid, String nickName, String phone) {
        this.nickName = nickName;
        this.phone = phone;
        this.uuid = uuid;
    }

    public UserInfo(String uuid){
        this.uuid = uuid;

    }

    public String getNickName() {
        return nickName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPhone() {
        return phone;
    }
/*
    public String getPassword() {
        return password;
    }

 */
}
