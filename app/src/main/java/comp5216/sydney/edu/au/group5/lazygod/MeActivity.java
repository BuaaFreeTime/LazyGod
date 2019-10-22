package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

public class MeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
    }

    public void onAddItemClick(View view){
        int id = view.getId(); // 获取button的id
        switch (id){
            case R.id.btnMe2:
                Toast.makeText(MeActivity.this,"you are already in Personal interface", Toast.LENGTH_SHORT).show();
                break;//已经在个人界面只需提示
            case R.id.btnRunner2:
                startActivity(new Intent(MeActivity.this,MainActivity.class));
                break;// 点击runneer button 直接启动MainActivity并跳转至主界面
            case R.id.btnTask2:
                Toast.makeText(MeActivity.this,"not work", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogout:
                Toast.makeText(MeActivity.this,"not work 1", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
