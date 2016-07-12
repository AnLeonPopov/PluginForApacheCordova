package com.example.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
 
 
public class MainActivity extends Activity {

  public Context pContext;
  public final static String BROADCAST_ACTION = "UpdateDataInActivity";

  BroadcastReceiver br;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	Intent intent = getIntent();
	String[] names = intent.getStringArrayExtra("Names");
	/*{ "Ivan", "Maria", "Petr", "Anton", "Daria", "Boris",
      "Kostya", "Igor", "Anna", "Denis", "Andrey" };*/

	setTitle("Activity"); 
    ListView lv = new ListView(this);
    setContentView(lv);

	// Nahodim spisok
    //ListView lv = (ListView) findViewById(R.id.main);
 
    // create adapter
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, names);
 
    // prisvaivaem adaptery spisok
    lv.setAdapter(adapter);

	lv.setOnScrollListener(new OnScrollListener() {
		public void onScrollStateChanged(AbsListView view, int scrollState) {
        	if (scrollState == 0 ){
				setTitle("END!");


				Intent intent = new Intent(MyPlugin.BROADCAST_ACTION);
				// sms update
				intent.putExtra(MyPlugin.PARAM_TASK, MyPlugin.TASK_Update_CODE);
				sendBroadcast(intent);

			}
		}

		public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		}
	});


	/*
	// create BroadcastReceiver
	br = new BroadcastReceiver() {
		//action when receiving messages
		public void onReceive(Context context, Intent intent) {
			int task = intent.getIntExtra(MyPlygin.PARAM_TASK, 0);
         
			if (task  == MyPlugin.TASK_Update_CODE) {
						
			}
         
					
		}
	};
	//Create Filtr for BroadcastReceiver
	IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
	// registration (on) BroadcastReceiver
	registerReceiver(br, intFilt);
	*/
  }

	
   
}