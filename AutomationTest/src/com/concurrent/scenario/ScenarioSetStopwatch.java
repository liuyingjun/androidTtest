package com.concurrent.scenario;

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

import junit.framework.Assert;

public class ScenarioSetStopwatch implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    IAlarm IAlarm;
    
    public ScenarioSetStopwatch(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IAlarm = AlarmFactory.getCameraModule(testCase);
    	
    	 moduleData = ModuleDataParser.getModuleData("calculator");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	
    	commonModule.backOutToHomeScreen();
    	commonModule.openNotificationBar();
		Assert.assertTrue("There is no Stopwatch.",
				commonModule.waitForText("Stopwatch", 5000));
    	
		commonModule.clickText("Stopwatch");
		
		Assert.assertTrue("Stopwatch stopped.",
				commonModule.waitForText("Stop", 2000));
		
		commonModule.clickText("Stop");
		commonModule.clickText("Reset");
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IAlarm.startAlarmFromMenu();
		if (!commonModule
				.isResourceId("com.sonyericsson.organizer:id/stopwatch_header")) {
			IAlarm.switchAlarmTabByDescription("Stopwatch");
		}
          commonModule.clickText("Start");
	}  
}
