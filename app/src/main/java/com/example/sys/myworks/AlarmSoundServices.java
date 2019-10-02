package com.example.sys.myworks;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class AlarmSoundServices extends Service {
    private MediaPlayer mediaPlayer;

    String message;

    @Override
    public IBinder onBind(Intent intent)
    {
        message=intent.getStringExtra("MSG");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Reminder");
        builder.setContentText(message);


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());

        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancel(1);

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }
}