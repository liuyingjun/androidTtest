package com.concurrent.scenario;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Environment;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.alarm.AlarmFactory;
import com.module.alarm.IAlarm;
import com.module.calculator.AbstractCalculatorFactory;
import com.module.calculator.ICalculator;
import com.module.calendar.CalendarFactory;
import com.module.calendar.ICalendar;
import com.module.common.CommonModule;
import com.module.media.*;

import java.io.File;
import java.util.HashMap;

public class ScenarioBatteryCharging implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
	
    public ScenarioBatteryCharging(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);

    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    	Intent batteryStatus = testCase.getInstrumentation()
    			.getContext().registerReceiver(null, ifilter);
    	
    	int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		AcceptanceTestCase.assertTrue(
				"Canot charging after concurrent.",(status == BatteryManager.BATTERY_STATUS_CHARGING));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
    	IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    	Intent batteryStatus = testCase.getInstrumentation()
    			.getContext().registerReceiver(null, ifilter);
    	
    	int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		if(status == BatteryManager.BATTERY_STATUS_FULL){
			
			
		AcceptanceTestCase
			.assertTrue(
					"The battery is full can not charging.",false);
		}
		
		AcceptanceTestCase.assertTrue(
				"Connect usb but not charging.",(status == BatteryManager.BATTERY_STATUS_CHARGING));

	}  
}
