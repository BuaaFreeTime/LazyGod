package comp5216.sydney.edu.au.group5.lazygod;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;



public class AppliedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task_applied);
    }

    public void onAddItemClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnApplied_Runner:
                startActivity(new Intent(AppliedActivity.this, MainActivity.class));
                break;
            case R.id.btnApplied_Me:
                startActivity(new Intent(AppliedActivity.this, MeActivity.class));
                break;
            case R.id.btnApplied_Task:
                Toast.makeText(AppliedActivity.this, "you are already in Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnApplied_Applied:
                Toast.makeText(AppliedActivity.this, "you are already in Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnApplied_Sent:
                startActivity(new Intent(AppliedActivity.this, SentActivity.class));
                break;
            case R.id.btnApplied_back:
                startActivity(new Intent(AppliedActivity.this, MainActivity.class));
                break;
            case R.id.btnApplied_addtask:
                startActivity(new Intent(AppliedActivity.this, PosttaskActivity.class));
                break;
        }
    }
}