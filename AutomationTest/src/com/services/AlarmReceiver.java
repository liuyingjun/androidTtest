package com.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

/**
 * this is a class for AlarmReceiver
 * 
 * @method acquireLock
 * @method releaseLock
 * 
 * @author 
 * @update this is update data
 * @version ICS,ANDROID 4.0
 * @since 2012.04.20
 * 
 * @Phone NoZhongMi
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	
	public static WakeLock wakeLock;
	private static final String POWER_TAG = "POWER_TEST";

	/**
	 * this is the method of acquireLock
	 * 
	 * @param ctx  : the context object
	 * 
	 * @author 
	 * @update this is update data
	 * @version ICS,ANDROID 4.0
	 * @since 2012.04.20
	 * 
	 */
	public static synchronized void acquireLock(Context ctx) {
		
		if (wakeLock == null) {
			
			PowerManager mgr = (PowerManager) ctx
					.getSystemService(Context.POWER_SERVICE);
			wakeLock = mgr.newWakeLock(PowerManager.FULL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, POWER_TAG);

			wakeLock.setReferenceCounted(true);
		}
		wakeLock.acquire();
	}

	/**
	 * this is the method of releaseLock
	 * 
	 * @author 
	 * @update this is update data
	 * @version ICS,ANDROID 4.0
	 * @since 2012.04.20
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	public static synchronized void releaseLock() {
		
		Log.d(POWER_TAG, "------------------releaseLock1");
		
		if (wakeLock != null) {
			
			Log.d(POWER_TAG, "------------------releaseLock2");
			wakeLock.release();
		}
	}

	/**
	 * this is the main method of the AlarmReceiver
	 * 
	 * @author 
	 * @update this is update data
	 * @version ICS,ANDROID 4.0
	 * @since 2012.04.20
	 * 
	 * @Phone NoZhongMi
	 * 
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.d(POWER_TAG, "------------------Alarm received");
		acquireLock(context);
		
		Intent alarmService = new Intent(context, AlarmService.class);
		context.startService(alarmService);
		
	}
}








