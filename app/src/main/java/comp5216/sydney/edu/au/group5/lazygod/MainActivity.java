package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import comp5216.sydney.edu.au.group5.lazygod.database.UserDB;
import comp5216.sydney.edu.au.group5.lazygod.database.UserDF;
import comp5216.sydney.edu.au.group5.lazygod.database.UserDao;
import comp5216.sydney.edu.au.group5.lazygod.entities.TaskInfo;
import comp5216.sydney.edu.au.group5.lazygod.entities.UserInfo;
import comp5216.sydney.edu.au.group5.lazygod.utils.GridViewAdapter;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.core.ApiFuture;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends Activity {

    // Define request code
    public final int SIGN_IN_CODE = 647;
    public final int ADD_ITEM_REQUEST_CODE = 648;

    private static final int LIMIT = 50;

    private GridView gridView;                     // gridview 对象
    private ArrayList<TaskInfo> taskList;        //模拟数据源
    private GridViewAdapter taskAdapter;

    UserInfo user;

    UserDB userDB;                 // local database
    UserDao userDao;

    private FirebaseFirestore mFirestore;
    private Query mQuery;


    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check permission
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // read local data
        userDB = userDB.getDatabase(this.getApplication().getApplicationContext());
        userDao = userDB.userDao();
        user = new UserInfo("initial");
        readUser();

        // check signin
        if (user.getUuid() == "initial") {
            Intent intent = new Intent(MainActivity.this, SigninActivity.class);
            flag = false;
            startActivityForResult(intent, SIGN_IN_CODE);
        }

        FirebaseFirestore.setLoggingEnabled(true);

        taskList = new ArrayList<TaskInfo>();

        try {
            initFirestore();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gridView = findViewById(R.id.gvTask);                    // 获取gridview的id
        taskAdapter = new GridViewAdapter(this, taskList);

        gridView.setAdapter(taskAdapter);

        setupGridViewListener();

    }

    private void initFirestore() throws ExecutionException, InterruptedException {
        mFirestore = FirebaseFirestore.getInstance();

        // Get the 50 highest rated restaurants
        mQuery = mFirestore.collection("tasks").whereEqualTo("applyer", null)
                .orderBy("title", Query.Direction.DESCENDING)
                .limit(LIMIT);


        mQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> map = document.getData();
                        Log.w("xxxx", map.get("name").toString());
                        taskList.add(new TaskInfo(map.get("name").toString()));
                        taskAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.w("xxxx", "Error getting documents.", task.getException());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIGN_IN_CODE) {
            if (resultCode == RESULT_OK) {
                flag = true;
            }
            if (resultCode == RESULT_FIRST_USER) {
                user = new UserInfo(data.getStringExtra("uuid"),
                                    data.getStringExtra("name"),
                                    data.getStringExtra("phone"));

                saveUser();
            }
        }
    }

    // set up a listener
    private void setupGridViewListener() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this,
                        TaskapplyActivity.class);
                TaskInfo taskInfo = (TaskInfo) taskAdapter.getItem(position);
                if (intent != null) {
                    intent.putExtra("title", taskInfo.getTitle());
                    intent.putExtra("name", taskInfo.getName());
                    intent.putExtra("money", taskInfo.getMoney());
                    intent.putExtra("time", taskInfo.getTime());
                    intent.putExtra("contents", taskInfo.getContents());
                    intent.putExtra("phone", taskInfo.getPhone());
                    startActivity(intent);
                }
            }
        });
    }


//所有button的响应方法
    public void onAddItemClick(View view){
        int id = view.getId(); // 获取button的id
        switch (id){
            case R.id.btnMe:
                Intent intent = new Intent(MainActivity.this,MeActivity.class);
                startActivity(intent);
                finish();
                break;// 点击me button 直接启动MeActivity并跳转至用户界面
            case R.id.btnaddtask:
                Intent intentAdd = new Intent(MainActivity.this, PosttaskActivity.class);
                intentAdd.putExtra("uuid", user.getUuid());
                startActivity(intentAdd);
                break;
            case R.id.btnTask:
                Intent intentTask = new Intent(MainActivity.this, AppliedActivity.class);
                intentTask.putExtra("uuid", user.getUuid());
                startActivity(intentTask);
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

    private void saveUser() {
        // A method to add item to database
        final UserInfo item;
        item = user;
        //Use asynchronous task to run query on the background to avoid locking UI
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserDF toDoItemDF = new UserDF(item);
                // Insert a new item to database
                userDao.insert(toDoItemDF);
                Log.i("SQLite saved item", item.getUuid());
                return null;
            }
        }.execute();

    }



}
