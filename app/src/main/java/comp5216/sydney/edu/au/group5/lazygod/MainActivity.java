package comp5216.sydney.edu.au.group5.lazygod;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//所有button的响应方法
    public void onAddItemClick(View view){
        int id = view.getId(); // 获取button的id
        switch (id){
            case R.id.btnRunner:
                Toast.makeText(MainActivity.this,"you are already in runner", Toast.LENGTH_SHORT).show();
                break;//已经在主界面只需提示
            case R.id.btnMe:
                startActivity(new Intent(MainActivity.this,MeActivity.class));
                break;// 点击me button 直接启动MeActivity并跳转至用户界面
            case R.id.btnaddtask:
                Toast.makeText(MainActivity.this,"not work", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnTask:
                Toast.makeText(MainActivity.this,"not work 1", Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
