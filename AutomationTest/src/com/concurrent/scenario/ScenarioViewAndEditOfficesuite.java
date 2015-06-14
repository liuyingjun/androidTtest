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
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;

import java.io.File;
import java.util.HashMap;

public class ScenarioViewAndEditOfficesuite implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IOfficeSuite officeSuite;
    
    public ScenarioViewAndEditOfficesuite(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
    	
    	 moduleData = ModuleDataParser.getModuleData("officesuite");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	testCase.enterText("edit doc file after.");
    	commonModule.clickResourceId(moduleData.get("Wordeditor_save_icon"));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		
		officeSuite.openFileFromExternalStorage("relDoc.doc");
		
		commonModule.wait(4);
		
		testCase.enterText("edit doc file before.");
		
	}  
}
