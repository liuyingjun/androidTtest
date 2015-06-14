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

public class ScenarioAlarmExpired implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    IAlarm IAlarm;
    
    public ScenarioAlarmExpired(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IAlarm = AlarmFactory.getCameraModule(testCase);

    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	commonModule.wait(3);
		AcceptanceTestCase
				.assertTrue(
						"Clock added failed",testCase.isViewWithIdPresent("dismissSlider"));

        testCase.clickId("snooze");
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IAlarm.setNewAlarmFewMinuteslater(0, 1);
		commonModule.wait(2);
		long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < 60){
            if(testCase.isViewWithIdPresent("dismissSlider")){
               break;
            }else
                testCase.sleep(1000);
        }
	}  
}
