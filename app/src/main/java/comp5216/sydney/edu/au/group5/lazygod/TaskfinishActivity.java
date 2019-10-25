package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class TaskfinishActivity extends Activity {

    TextView title, nameTime, money, contents, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_finish);
        Intent intent = getIntent();
        title = findViewById(R.id.finishtitle);
        nameTime = findViewById(R.id.finishnametime);
        money = findViewById(R.id.finishmoney);
        contents = findViewById(R.id.finishcontents);
        phone = findViewById(R.id.finishphone);
        title.setText(intent.getStringExtra("title"));
        money.setText(intent.getStringExtra("money"));
        nameTime.setText(intent.getStringExtra("name") + "      " +
                intent.getStringExtra("time"));
        phone.setText(intent.getStringExtra("phone"));
        contents.setText(intent.getStringExtra("contents"));
    }

    public void onAddItemClick(View view){
        if (view.getId() == R.id.btnFinish_Finish) {

            setResult(RESULT_OK, getIntent());
            finish();
        }
        if (view.getId() == R.id.btnFinish_Cancle) {
            finish();
        }

    }

}