package com.example.sys.myworks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    public  static  int no=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        dbHelperClass dbclass=new dbHelperClass(this);


        FloatingActionButton floatingActionButton=findViewById(R.id.myFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Add_list_activity.class);
                startActivity(intent);
            }
        });


        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);

        mRecyclerView.setHasFixedSize(true);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Cursor res=dbclass.getData();
        if(no==1) {

            convertToAdapter(res);
            no++;
        }



        RecyclerView.Adapter mAdapter = new MyAdapter(this,Data_Class.getArrayList());

        mRecyclerView.setAdapter(mAdapter);






    }
    public void convertToAdapter(Cursor cursor)
    {
        if (cursor.getCount() != 0)
        {
            String date,time,work,id,millis;
            while(cursor.moveToNext())
            {

                date=cursor.getString(0);
                time=cursor.getString(1);
                work=cursor.getString(2);
                id=cursor.getString(3);

                Data_Class dc=new Data_Class(date,time,work,id);

                    Data_Class.addArrayList(dc);

            }
        }
    }

}
