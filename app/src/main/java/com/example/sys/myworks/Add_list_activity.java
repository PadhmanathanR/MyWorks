package com.example.sys.myworks;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Add_list_activity extends AppCompatActivity {
String date_disp;
String time_disp;
String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_activity);

        final dbHelperClass dbclass=new dbHelperClass(this);

        final Calendar calendar=Calendar.getInstance();
        Button pick_date=findViewById(R.id.pick_date_a);
        Button pick_time=findViewById(R.id.pick_time_a);
        final TextView show_date=findViewById(R.id.shoe_date);
        final TextView show_time=findViewById(R.id.shoe_time);
       final EditText msg=findViewById(R.id.msg);



        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_list_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month+=1;


                                calendar.set(Calendar.DAY_OF_MONTH,day);
                                calendar.set(Calendar.MONTH,month);
                                calendar.set(Calendar.YEAR,year);
                                String monn,dayy;
                                if(month<10)
                                {
                                    monn="0"+month;
                                }
                                else {
                                    monn= String.valueOf(month);
                                }
                                if(day<10)
                                {
                                    dayy="0"+day;
                                }
                                else {
                                    dayy=String.valueOf(day);
                                }
                                date_disp=dayy+"-"+monn+"-"+year;
                                show_date.setText(date_disp);

                            }
                        }, year,month,dayOfMonth);


                datePickerDialog.show();
            }
        });

        pick_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar myCalender = Calendar.getInstance();
                final int hour_a = myCalender.get(Calendar.HOUR_OF_DAY);
                int minute_a = myCalender.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(Add_list_activity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        String minn,hrr;

                        if(hourOfDay<10)
                        {
                            minn="0"+minute;
                        }
                        else {
                            minn=String.valueOf(minute);
                        }
                        if(hourOfDay<10)
                        {
                            hrr="0"+hourOfDay;
                        }
                        else {
                            hrr=String.valueOf(hourOfDay);
                        }

                        time_disp=hrr +  " : " + minn;
                        show_time.setText(time_disp);
                    }
                },hour_a,minute_a,true);

                timePickerDialog.show();

            }
        });

      Button add=findViewById(R.id.add);
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              long millis=0;

              millis=calendar.getTimeInMillis();

              Toast.makeText(Add_list_activity.this,String.valueOf(millis),Toast.LENGTH_LONG).show();


              message=msg.getText().toString();
              long mili=System.currentTimeMillis();
              int mil= (int) mili;
              String alarmId= String.valueOf(mil);
             final Data_Class data_class=new Data_Class(date_disp,time_disp,message,alarmId);
              Data_Class.addArrayList(data_class);
              boolean res=dbclass.inset_data(date_disp,time_disp,message,alarmId);
              if(res) {




                  AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager

                  Intent alarmIntent = new Intent(Add_list_activity.this, AlarmReceiver.class);
                  alarmIntent.putExtra("MSG",message);
                  PendingIntent pendingIntent = PendingIntent.getBroadcast(Add_list_activity.this,  mil, alarmIntent, 0);
                  manager.set(AlarmManager.RTC_WAKEUP,millis, pendingIntent);



                  Toast.makeText(Add_list_activity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(Add_list_activity.this, MainActivity.class));
                  finish();





              }
          }
      });




    }
}
