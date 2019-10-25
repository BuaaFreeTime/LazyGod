package comp5216.sydney.edu.au.group5.lazygod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import comp5216.sydney.edu.au.group5.lazygod.entities.TaskInfo;
import comp5216.sydney.edu.au.group5.lazygod.entities.UserInfo;
import comp5216.sydney.edu.au.group5.lazygod.utils.GridViewAdapter;


public class AppliedActivity extends Activity {

    private static final int LIMIT = 50;

    private GridView gridView;                     // gridview 对象
    private ArrayList<TaskInfo> taskList;        //模拟数据源
    private GridViewAdapter taskAdapter;

    private FirebaseFirestore mFirestore;
    private Query mQuery;

    private String uuid;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task_applied);

        uuid = getIntent().getStringExtra("uuid");
        name = getIntent().getStringExtra("name");

        taskList = new ArrayList<TaskInfo>();

        try {
            initFirestore();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        gridView = findViewById(R.id.applygridview);// 获取gridview的id
        taskAdapter = new GridViewAdapter(this, taskList);

        gridView.setAdapter(taskAdapter);

        setupGridViewListener();

    }

    private void initFirestore() throws ExecutionException, InterruptedException {
        mFirestore = FirebaseFirestore.getInstance();

        // Get the 50 highest rated restaurants
        mQuery = mFirestore.collection("tasks").whereEqualTo("applyer", uuid)
                .orderBy("title", Query.Direction.DESCENDING)
                .limit(LIMIT);


        mQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> map = document.getData();
                        TaskInfo taskInfo = new TaskInfo(map);
                        taskInfo.setDocid(document.getId());
                        taskList.add(taskInfo);
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
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                int p = data.getIntExtra("position", 0);

                String docid = data.getStringExtra("docid");
                Log.w("xxxx", docid);

                taskList.remove(p);
                taskAdapter.notifyDataSetChanged();

                CollectionReference apply = mFirestore.collection("tasks");

                apply.document(docid).delete();

            }
        }
    }


    // set up a listener
    private void setupGridViewListener() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(AppliedActivity.this,
                        TaskfinishActivity.class);
                TaskInfo taskInfo = (TaskInfo) taskAdapter.getItem(position);
                if (intent != null) {
                    intent.putExtra("title", taskInfo.getTitle());
                    intent.putExtra("name", taskInfo.getName());
                    intent.putExtra("money", taskInfo.getMoney());
                    intent.putExtra("time", taskInfo.getTime());
                    intent.putExtra("contents", taskInfo.getContents());
                    intent.putExtra("phone", taskInfo.getPhone());
                    intent.putExtra("docid", taskInfo.getDocid());
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

    public void onAddItemClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnApplied_Runner:
                startActivity(new Intent(AppliedActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.btnApplied_Me:
                startActivity(new Intent(AppliedActivity.this, MeActivity.class));
                finish();
                break;
            case R.id.btnApplied_Sent:
                Intent intent = new Intent(AppliedActivity.this, SentActivity.class);
                intent.putExtra("uuid", uuid);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
                break;
        }
    }
}