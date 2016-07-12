package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.widget.Toast;
import android.content.Context;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

public class MyPlugin extends CordovaPlugin {

	public final static int TASK_Update_CODE = 1;
	
	public final static String PARAM_TASK = "task";
	public final static String BROADCAST_ACTION = "UpdateForPlugin";
	BroadcastReceiver br;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("runFormAndroid")) {

			String n = data.toString();
			n = removeChar(n,'"');
			n = removeChar(n,'[');
			n = removeChar(n,']');
			String[] names = n.split(",");

            Context context = cordova.getActivity().getApplicationContext();


            Intent service = new Intent(context, MyService.class);
			service.putExtra("Names",names);
        	context.startService(service);


			// create BroadcastReceiver
			br = new BroadcastReceiver() {
				//action when receiving messages
				public void onReceive(Context context, Intent intent) {
					int task = intent.getIntExtra(PARAM_TASK, 0);
         
					if (task  == TASK_Update_CODE) {
						NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

						NotificationCompat.Builder mBuilder =
							new NotificationCompat.Builder(context)
								.setSmallIcon(context.getApplicationInfo().icon)
								.setWhen(System.currentTimeMillis())
								.setContentTitle("It works!")
								.setTicker("Ticker")
								.setContentText("Text")
								.setNumber(1)
								.setAutoCancel(true);

						mNotificationManager.notify("App Name", 228, mBuilder.build());

					}
         
					
				}
			};
			//Create Filtr for BroadcastReceiver
			IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
			// registration (on) BroadcastReceiver
			context.registerReceiver(br, intFilt);


            return true;

        } else if (action.equals("UpdateData")){

			/*Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
			// sms update
			intent.putExtra(MyPlugin.PARAM_TASK, MyPlugin.TASK_Update_CODE);
			//intent.putExtra("Data", data);
			sendBroadcast(intent);*/
			return true;

		}else{
            
            return false;

        }
    }

	
	public static String removeChar(String s, char c) {
		String r = "";

		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) != c) r += s.charAt(i);
		}

		return r;
	}
}
