package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.app.TaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class TaskapplyActivity extends Activity {

    TextView title, nameTime, money, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_apply);
        Intent intent = getIntent();
        title = findViewById(R.id.applytitle);
        nameTime = findViewById(R.id.applynametime);
        money = findViewById(R.id.applymoney);
        contents = findViewById(R.id.applycontents);
        title.setText(intent.getStringExtra("title"));
        money.setText(intent.getStringExtra("money"));
        nameTime.setText(intent.getStringExtra("name") + "      " +
                intent.getStringExtra("time"));
        contents.setText(intent.getStringExtra("contents"));
    }

    public void onClick(View view){
        if (view.getId() == R.id.btnApply_Apply) {

            setResult(RESULT_OK, getIntent());
            finish();
        }
        if (view.getId() == R.id.btnApply_Cancle) {
            finish();
        }

    }


}