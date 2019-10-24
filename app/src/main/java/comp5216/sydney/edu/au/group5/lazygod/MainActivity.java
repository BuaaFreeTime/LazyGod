package comp5216.sydney.edu.au.group5.lazygod;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private GridView gvTask; // gridview 对象
    private List<Map<String,Object>>list; //模拟数据源
    private int[] images = {R.drawable.aboutbutton,R.drawable.addtask,R.drawable.addaphotobutton,R.drawable.applybutton};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvTask = findViewById(R.id.gvTask);// 获取gridview的id
        list = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < images.length ; i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("img", images[i]);
            map.put("text","Task"+i);
            list.add(map);
        }
        MybaseAdapter adapter = new MybaseAdapter();
        gvTask.setAdapter(adapter);
    }
    class MybaseAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return list.size();
        }

        @Override
        public Object getItem(int position){
            return list.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder = null;
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_main,null);
                holder = new ViewHolder();
                holder.iv = (ImageView)convertView.findViewById(R.id.接口);
                holder.tv = (TextView)convertView.findViewById(R.id.接口);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tv.setText((CharSequence)list.get(position).get("text"));
            holder.iv.setImageResource((Integer)list.get(position).get("img"));
            return convertView;
        }


    }

    static class ViewHolder{
        ImageView iv;
        TextView tv;

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
