 package com.example.studentportal.NotificationFcm;


import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.FragmentUtama;
import com.example.studentportal.PengumumanFragment;
import com.example.studentportal.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

 public class MyFirebaseMessagingService extends FirebaseMessagingService   {
     private static final String TAG = "FirebaseMessaging";

     @Override
     public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
         String title ="0";
         String body="0";
         String click_action="0";
         String extra ="0";
         //check if message contain a data payload
         if(remoteMessage.getData().size()>0){
             Log.d(TAG, "Message data payload:"+ remoteMessage.getData());
             try {
                 JSONObject data = new JSONObject(remoteMessage.getData());
                 extra=data.getString("extra");
                 Log.d(TAG, "onMessageReceived: \n"+
                         "Extra Information:"+extra);
 
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
         //check if message contains a notification payload
         if (remoteMessage.getNotification() != null){
             title= remoteMessage.getNotification().getTitle();
             body=remoteMessage.getNotification().getBody();
             click_action= remoteMessage.getNotification().getClickAction();
 
             Log.d(TAG, "Message Notification Title: "+title);
             Log.d(TAG, "Message Notification Body: "+body);
             Log.d(TAG, "Message Notification click_action: "+click_action);
         }
         sendNotification(extra,title,body,click_action);
     }

 
     private void sendNotification(String extra, String title,String message,String click_action){
         Intent intent;
         if (click_action.equals("INFOPEL")){
             intent=new Intent(click_action);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             intent.putExtra("extra",extra);
         }else if(click_action.equals("SOMEACTIVITY")){
             intent = new Intent(this, SomeActivity.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         }
         else if(click_action.equals("MAINACTIVITY")){
             intent = new Intent(this, FragmentUtama.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         }else{
             intent = new Intent(this,HomeFragment.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         }
         PendingIntent pendingIntent = PendingIntent.getActivities(this, 0 /* Request code */, new Intent[]{intent},
                 PendingIntent.FLAG_ONE_SHOT);
         Uri defaultSounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
         NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                 .setSmallIcon(R.drawable.student_blue)
                 .setContentTitle(title)
                 .setContentText(message)
                 .setAutoCancel(true)
                 .setSound(defaultSounduri)
                 .setContentIntent(pendingIntent);
         NotificationManager notificationManager=
                 (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
             NotificationChannel channel = new NotificationChannel(
               "channel_id","channel_name", NotificationManager.IMPORTANCE_DEFAULT
             );
             channel.setDescription("description");
             channel.setShowBadge(true);
             channel.canShowBadge();
             channel.enableLights(true);
             channel.setLightColor(Color.RED);
             channel.enableVibration(true);
             channel.setVibrationPattern(new long[]{100,200,300,400,500});
             notificationManager.createNotificationChannel(channel);
         }
         notificationManager.notify(0,notificationBuilder.build());
     }
 }
