package com.example.a15017573.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTask;
    ArrayAdapter aa;
    ArrayList<Task> task;
    Button btnInsert, btnGetTasks;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = (ListView) this.findViewById(R.id.lv);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGetTasks = (Button) findViewById(R.id.btnGetTasks);
        tvResults = (TextView) findViewById(R.id.tvResults);

        task = new ArrayList<Task>();

        aa = new TaskAdapter(this, R.layout.row, task);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the DBHelper object, passing in the
                //activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                //Insert a task
                ArrayList<String> data = db.getTaskContent();
                task = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, task);

                lvTask.setAdapter(aa);
                aa.notifyDataSetChanged();


            }
        });
    }
}