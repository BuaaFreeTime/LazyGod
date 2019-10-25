package comp5216.sydney.edu.au.group5.lazygod.entities;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class TaskInfo {
    String name;
    String time;
    Date date;
    String title;
    String contents;
    String money;
    String sender;
    String phone;
    String applyer = null;

    public TaskInfo(String name) {
        this.name = name;
        this.contents = name;
        this.title = name;
        this.time = name;
        this.money = "A$" + name;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getMoney() {
        return money;
    }

    public String sender() {
        return sender;
    }

    public String getPhone() {
        return phone;
    }

    public String getApplyer() {
        return applyer;
    }
}
