package com.example.mtci.azadmedicinecompany;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by AttaUllah on 5/24/2018.
 */

public class CheckNetConnection extends BroadcastReceiver {
    ArrayList<AllRecordDataHolderClass> list;
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {

            if ((activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    && activeNetwork.isConnected()) {


                SqliteDataBase sqliteDataBase = new SqliteDataBase(context);

                list = sqliteDataBase.getOrderMainTble(0);
                if (list.size() != 0) {

                    Intent intent2 = new Intent(context, AllCartRecord.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder b = new NotificationCompat.Builder(context);

                    b.setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Pending orders")
                            .setContentText("Network is connected send the pending orders.")
                            .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                            .setContentIntent(contentIntent)
                            .setContentInfo("Info");


                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(101, b.build());
                }
            }
        }
    }

}
