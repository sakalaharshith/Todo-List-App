package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.todolist.table1.contractclass;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.table1.helper;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
   Context context;
   ArrayList<information> info;

   helper help1;
   public AdapterClass(Context c, ArrayList<information> i)
   {
       context=c;
       info=i;

   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.task_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       information i=info.get(position);

       holder.task.setText("Task:"+i.getTask());
       holder.time.setText("Time:"+i.getTime());

       holder.imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SQLiteDatabase db = help1.getWritableDatabase();

               int index=info.indexOf(position);
               Log.d("index",""+index);
               db.delete(contractclass.ListEntry.TABLE_NAME, contractclass.ListEntry.COLUMN_TASK + " = ?", new String[]{info.get(position).getTask()});
               info.remove(position);
               notifyItemRemoved(position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView task,time;
       LinearLayout linearLayout;
       ImageView imageView;
       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task=itemView.findViewById(R.id.task_text);
            time=itemView.findViewById(R.id.task_timings);
            linearLayout=itemView.findViewById(R.id.linear_layout);
            imageView=itemView.findViewById(R.id.del);
        }
    }

    View delete(helper help)
    {
        help1=help;
       return null;
    }
}
