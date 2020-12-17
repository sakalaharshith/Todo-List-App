package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.todolist.table1.contractclass;

import com.example.todolist.table1.helper;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    ArrayList<information>list;
    AdapterClass adapterClass;
    EditText add_task,add_time;
    Button button;
    helper help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
       /* list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12"));
        list.add(new information("takdadawdwa","12")); */
          readDatabase();

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
         adapterClass=new AdapterClass(this,list);
        recyclerView.setAdapter(adapterClass);
     //adapterClass.notifyDataSetChanged();
       // adapterClass.notifyItemInserted();
        //adapterClass.notifyItemRemoved();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClass.delete(help);


    }

     void readDatabase() {
        help=new helper(this);
         SQLiteDatabase db=help.getReadableDatabase();
            Cursor c=db.query(contractclass.ListEntry.TABLE_NAME,null,null,null,null,null,null);
            int task_index=c.getColumnIndex(contractclass.ListEntry.COLUMN_TASK);
            int time_index=c.getColumnIndex(contractclass.ListEntry.COLUMN_TIME);
            while(c.moveToNext())
            {
                list.add(new information(c.getString(task_index),c.getString(time_index)));
            }
            c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menubar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add)
        {
            final Dialog dialog=new Dialog(this);
            dialog.setContentView(R.layout.addingdata);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(true);
            add_task=dialog.findViewById(R.id.task_entry);
            add_time=dialog.findViewById(R.id.time_entry);
            button=dialog.findViewById(R.id.button);
            dialog.setTitle("ADD YOUR TASK AND TIME HERE");
            dialog.show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.add(new information(add_task.getText().toString(),add_time.getText().toString()));
                    adapterClass.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "item is added", Toast.LENGTH_SHORT).show();
                    ContentValues contentValues=new ContentValues();
                     contentValues.put(contractclass.ListEntry.COLUMN_TASK,add_task.getText().toString());
                     contentValues.put(contractclass.ListEntry.COLUMN_TIME,add_time.getText().toString());
                     SQLiteDatabase wrt=help.getWritableDatabase();
                    long id= wrt.insert(contractclass.ListEntry.TABLE_NAME,null,contentValues);
                    Toast.makeText(MainActivity.this, "id is"+id, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }

            });


        }

        return true;
    }

}