package com.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * this is the class of AlarmService
 * 
 * @method onBind : the main method
 * @method onStart  : the start method
 * @method onDestroy : the destroy method
 * 
 * @author 
 * @update this is update data
 * @version ICS,ANDROID 4.0
 * @since 2012.04.20
 * 
 * @Phone NoZhongMi
 *
 */
public class AlarmService extends Service{
	
	private static final String POWER_TAG = "AlarmService";
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}
	
    @Override
    public void onStart(Intent intent, int startId) {
    	
    	Log.v(POWER_TAG, "------------------Start AlarmService"); 
    }
    
    @Override
    public void onDestroy() {
    	
    	Log.v(POWER_TAG, "------------------Destroy AlarmService");
    	AlarmReceiver.releaseLock();
    } 
}
