package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import comp5216.sydney.edu.au.group5.lazygod.entities.TaskInfo;


public class PosttaskActivity extends Activity {

    EditText title, contents, money, phone;
    String uuid;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);

        uuid = getIntent().getStringExtra("uuid");

        mFirestore = FirebaseFirestore.getInstance();
    }

    public void onAddItemClick(View view) {
        if (view.getId() == R.id.btnPost_post) {
            title = findViewById(R.id.editTextPost_Title);
            contents = findViewById(R.id.editTextPost_Requirement);
            money = findViewById(R.id.textViewPost_Reward);
            phone = findViewById(R.id.editTextPost_phone);

            TaskInfo taskInfo = new TaskInfo(title.getText().toString());

            CollectionReference tasks = mFirestore.collection("tasks");
            tasks.add(taskInfo);

        }
        if (view.getId() == R.id.btnApply_Cancle) {
            finish();
        }
    }
}