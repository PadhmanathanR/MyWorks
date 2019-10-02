package com.example.sys.myworks;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm Alarm", Toast.LENGTH_SHORT).show();

        String message= intent.getStringExtra("MSG");
        Intent intent1=new Intent(context, AlarmSoundServices.class);
        intent.putExtra("MSG",message);
        context.startService(intent1);
    }
}