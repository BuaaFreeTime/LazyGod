package comp5216.sydney.edu.au.group5.lazygod.entities;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    String docid;
    String applyer = null;

    public TaskInfo(String title, String contents, String money, String phone, String name, Date date) {
        this.title = title;
        this.contents = contents;
        this.phone = phone;
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        this.time = ft.format(date);
        this.money = "A$" + money;
        this.name = name;
    }

    public TaskInfo(Map<String, Object> map) {
        this.title = map.get("title").toString();
        this.contents = map.get("contents").toString();
        this.phone = map.get("phone").toString();
        this.time = map.get("time").toString();
        this.money = map.get("money").toString();
        this.sender = map.get("sender").toString();
        this.name = map.get("name").toString();
        if (map.get("applyer")!= null) this.applyer = map.get("applyer").toString();
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
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
