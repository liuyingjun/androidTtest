package com.concurrent.scenario;

import android.os.Environment;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.calculator.AbstractCalculatorFactory;
import com.module.calculator.ICalculator;
import com.module.calendar.CalendarFactory;
import com.module.calendar.ICalendar;
import com.module.common.CommonModule;
import com.module.media.*;

import java.io.File;
import java.util.HashMap;

public class ScenarioCalculator implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private ICalculator calculator;
    
    public ScenarioCalculator(AcceptanceTestCase testCase){

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	 calculator = ((AbstractCalculatorFactory)GroupFactories.createFactory(testCase,
                 "calculator")).create();
    	 moduleData = ModuleDataParser.getModuleData("calculator");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	testCase.click("=");
    	AcceptanceTestCase.assertTrue(
				"Calulator wrong.",
				commonModule.waitForText(
						"17", 2 * 1000));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{

		calculator.clearHistory();

		testCase.click("9");
		testCase.click("+");
		testCase.click("8");
	}  
}
