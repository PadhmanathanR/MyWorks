package com.example.sys.myworks;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Data_Class> arrayList;


    public MyAdapter(Context context, ArrayList<Data_Class> arrayList) {

        this.arrayList=arrayList;
        this.context=context;

    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout,parent,false);
       return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Data_Class data_class=arrayList.get(position);
        String ex=data_class.getMessage();
        holder.MessageText.setText(ex);
        holder.TimeText.setText(String.valueOf(data_class.getTime()));
        holder.dateText.setText(String.valueOf(data_class.getDate()));
        final int id= Integer.parseInt(data_class.getAlarmID());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                Intent alarmIntent = new Intent(context, AlarmReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, alarmIntent, 0);




                if (manager != null) {
                    manager.cancel(pendingIntent);

                    context.stopService(new Intent(context, AlarmSoundServices.class));
                }

                dbHelperClass db=new dbHelperClass(context);

                boolean res=db.delete_data(String.valueOf(id));
                if(res)
                {
                    Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
                }

                arrayList.remove(data_class);

                notifyDataSetChanged();



            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dateText;
        TextView TimeText;
        TextView MessageText;
        Button delete;

        MyViewHolder(View itemView) {
            super(itemView);
            dateText=itemView.findViewById(R.id.dateText);
            TimeText=itemView.findViewById(R.id.TimeText);
            MessageText=itemView.findViewById(R.id.MessageText);

            delete=itemView.findViewById(R.id.deleteAlarm);
        }
    }
}
