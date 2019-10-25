package comp5216.sydney.edu.au.group5.lazygod.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

import comp5216.sydney.edu.au.group5.lazygod.MainActivity;
import comp5216.sydney.edu.au.group5.lazygod.R;
import comp5216.sydney.edu.au.group5.lazygod.entities.TaskInfo;

public class GridViewAdapter extends BaseAdapter {

    // A adapter for grid view
    private Context context;
    private ArrayList<TaskInfo> taskList;

    public GridViewAdapter(Context context, ArrayList<TaskInfo> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.task_grid, null);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.gridtitle);
            holder.name = convertView.findViewById(R.id.gridname);
            holder.money = convertView.findViewById(R.id.gridmoney);
            holder.time = convertView.findViewById(R.id.gridtime);
            holder.contents = convertView.findViewById(R.id.gridcontents);
            //holder.mapView = convertView.findViewById(R.id.gridmap);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(taskList.get(position).getSender());
        holder.time.setText(taskList.get(position).getTime());
        holder.money.setText(taskList.get(position).getMoney());
        holder.title.setText(taskList.get(position).getTitle());
        holder.contents.setText(taskList.get(position).getContents());

        return convertView;
    }

    static class ViewHolder{
        TextView name, time, money, title, contents;
        //MapView mapView;
    }

}