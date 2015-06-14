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

public class ScenarioAddClock implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    IAlarm IAlarm;
    
    public ScenarioAddClock(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IAlarm = AlarmFactory.getCameraModule(testCase);
    	
    	 moduleData = ModuleDataParser.getModuleData("calculator");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	

		commonModule.clickListItemwithText("Accra");
		AcceptanceTestCase.assertFalse("Add world clock failed",
				commonModule.isTextExists("Add city"));
		commonModule.waitForResourceId(
				"com.sonyericsson.organizer:id/world_clock_list", 3000);
		boolean isTextExist = commonModule.scrollCheckText("Accra");
		AcceptanceTestCase
				.assertTrue(
						"Clock added failed",
						isTextExist
								&& commonModule
										.isResourceId("com.sonyericsson.organizer:id/weather_icon"));

		
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IAlarm.startAlarmFromMenu();
		if (!commonModule
				.isResourceId("com.sonyericsson.organizer:id/worldclock_list")) {
			IAlarm.switchAlarmTabByDescription("World clock");
		}
		testCase.clickItemWithId("menu_item_world_clock_add");
		commonModule.wait(2);
	}  
}
