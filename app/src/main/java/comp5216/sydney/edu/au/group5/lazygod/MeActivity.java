package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp5216.sydney.edu.au.group5.lazygod.database.UserDB;
import comp5216.sydney.edu.au.group5.lazygod.database.UserDF;
import comp5216.sydney.edu.au.group5.lazygod.database.UserDao;
import comp5216.sydney.edu.au.group5.lazygod.entities.UserInfo;

public class MeActivity extends Activity {

    TextView name, phone, email;
    UserInfo user;

    UserDB userDB;                 // local database
    UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        name = findViewById(R.id.textViewStudentname);
        phone = findViewById(R.id.meemail);
        email = findViewById(R.id.meemail);

        // read local data
        userDB = userDB.getDatabase(this.getApplication().getApplicationContext());
        userDao = userDB.userDao();
        readUser();

        name.setText(user.getNickName());
        phone.setText(user.getPhone());
        email.setText(user.getUuid() + "@uni.sydney.edu.au");

    }

    public void onAddItemClick(View view){
        int id = view.getId(); // 获取button的id
        switch (id){
            case R.id.btnRunner2:
                startActivity(new Intent(MeActivity.this,MainActivity.class));
                finish();
                break;// 点击runneer button 直接启动MainActivity并跳转至主界面
            case R.id.btnTask2:
                Intent intent = new Intent(MeActivity.this,AppliedActivity.class);
                intent.putExtra("uuid", user.getUuid());
                startActivity(intent);
                finish();
                break;

        }
    }

    // Read Local database users information
    public void readUser() {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    //read items from database
                    List<UserDF> itemsFromDB = userDao.listAll();
                    if (itemsFromDB != null & itemsFromDB.size() > 0) {
                        for (UserDF item : itemsFromDB) {
                            user = new UserInfo(item.getUuid(), item.getNickName(), item.getPhone());
                            Log.i("SQLite read item", user.getUuid());
                        } }
                    return null; }
            }.execute().get();
        }
        catch(Exception ex) {
            Log.e("readItemsFromDatabase", ex.getStackTrace().toString());
        }
    }
}
