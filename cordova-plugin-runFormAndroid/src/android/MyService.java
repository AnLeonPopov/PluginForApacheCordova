package com.example.plugin;

import org.apache.cordova.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;


public class MyService extends Service {

    Handler mHandler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Context context = getApplicationContext();
    	Intent myIntent = new Intent(context, MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		//Intent serviceIntent = getIntent();
		String[] names = intent.getStringArrayExtra("Names");
		myIntent.putExtra("Names",names);
    	context.startActivity(myIntent);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	public void update(JSONArray data){

	}


}