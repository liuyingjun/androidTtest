package com.concurrent.scenario;

import android.os.Environment;
import android.view.KeyEvent;

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
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;

import java.io.File;
import java.util.HashMap;

public class ScenarioCutMultipleFilesOfficeSuite implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IOfficeSuite officeSuite;
    
    public ScenarioCutMultipleFilesOfficeSuite(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
    	
    	 moduleData = ModuleDataParser.getModuleData("officesuite");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	AcceptanceTestCase.assertTrue("Delete all file failed.",
				commonModule.waitForText("This folder is empty", 6*10*1000));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		
		officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/1", false);

		officeSuite.operationAllFiles("copy");
		
		commonModule.pressKey(KeyEvent.KEYCODE_BACK);
	    commonModule.scrollFindText("2");
	    
	    testCase.clickId("edit_menu");
	    
		testCase.click("Paste");
	
		commonModule.waitForText("12 items selected", 3*60*1000);
		
		officeSuite.operationAllFiles("cut");
		
		commonModule.pressKey(KeyEvent.KEYCODE_BACK);
	    commonModule.scrollFindText("3");
	    
	    testCase.clickId("edit_menu");
	    
		testCase.click("Paste");
	    }  
}
