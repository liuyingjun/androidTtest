package com.concurrent.scenario;

import android.os.Environment;
import android.view.KeyEvent;

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

public class ScenarioChangeCalendarSetting implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    ICalendar ICalendar;
    
    public ScenarioChangeCalendarSetting(AcceptanceTestCase testCase){

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.ICalendar = CalendarFactory.getCalendarModule(testCase);

    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	
    	commonModule.backOutToHomeScreen();
		ICalendar.launchCalendar();
		testCase.pressKey(KeyEvent.KEYCODE_MENU);
		testCase.click("Settings");
		
		//notification  -- enable
		
		AcceptanceTestCase
		.assertTrue(
				"Set calendar notification setting failed after concurrent"
				,!testCase.isCheckboxChecked("checkbox", 0));

	    testCase.click("Notifications");

		//select ringtone  -- change to Default ringtone
		
		testCase.click("Select ringtone");
		testCase.click("Sound picker");
		if(testCase.isViewWithTextPresent("Just once")){
			testCase.click("Just once");
		}
		AcceptanceTestCase
		.assertTrue(
				"Set calendar select ringtone setting failed after concurrent"
				,testCase.isViewWithTextPresent("Acoustic"));
		testCase.scrollUp();
		testCase.click("Default ringtone");
		testCase.click("Done");
		
		//default reminder time --change to 10 minutes
		AcceptanceTestCase
		.assertTrue(
				"Set calendar default reminder time setting failed after concurrent"
				,testCase.isViewWithTextPresent("20 minutes"));
		
		testCase.click("Default reminder time");
		testCase.scrollUp();
		testCase.click("10 minutes");
		
		//Quick responses  -- change to "Be there in about 10 minutes."
		
		testCase.click("Quick responses");
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Quick responses setting failed after concurrent"
				,testCase.isViewWithTextPresent("I will Be there in about 10 minutes."));
		
		testCase.click("I will Be there in about 10 minutes.");
		commonModule.emptyEditorByInstance(0);
		testCase.enterText("Be there in about 10 minutes.");
		testCase.click("OK");
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		
		//National holidays
		AcceptanceTestCase
		.assertTrue(
				"Set calendar holidays setting failed after concurrent"
				,testCase.isViewWithTextPresent("Australia"));
		
		testCase.click("National holidays");
		testCase.click("Australia");
		testCase.click("OK");
		
		//weekend -- cancel monday
		commonModule.scrollFindTextNotClick("Weekend");
		AcceptanceTestCase
		.assertTrue(
				"Set calendar weekend setting failed after concurrent"
				,testCase.isViewWithTextPresent("Monday, Saturday, Sunday"));
		testCase.click("Weekend");
		testCase.click("Monday");
		testCase.click("OK");
		
		//Days in week view  -- change to 7 days
		commonModule.scrollFindTextNotClick("Days in week view");
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Days in week view setting failed after concurrent"
				,testCase.isViewWithTextPresent("Work days only"));

		testCase.click("Days in week view");
		testCase.click("7 days");
		
		//Week starts on -- change to Sunday
		commonModule.scrollFindTextNotClick("Week starts on");
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Week starts on view setting failed after concurrent"
				,testCase.isViewWithTextPresent("Monday"));
		
		testCase.click("Week starts on");
		testCase.click("Sunday");
		
		//Show week number -- disable
		testCase.scrollDownNTimes(4);
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Show week number setting failed after concurrent"
				,testCase.isCheckboxChecked("checkbox",0));

		testCase.click("Show week numbers");

		//Use home time zone -- disable
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Use home time zone setting failed after concurrent"
				,testCase.isCheckboxChecked("checkbox",1));

		testCase.click("Use home time zone");
		
		//Weather forecast
		
		testCase.click("Weather forecast");
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Weather forecast setting failed after concurrent"
				,testCase.isViewWithTextPresent("OFF"));
		
		testCase.click("OFF");
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		//Birthday
		testCase.click("Birthdays");	
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Birthday setting failed after concurrent"
				,testCase.isViewWithTextPresent("OFF"));
		
		testCase.click("OFF");
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		//Hide declined events
		AcceptanceTestCase
		.assertTrue(
				"Set calendar Hide declined events setting failed after concurrent"
				,testCase.isCheckboxChecked("checkbox",2));

		testCase.click("Hide declined events");
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{

		ICalendar.launchCalendar();
		testCase.pressKey(KeyEvent.KEYCODE_MENU);
		testCase.click("Settings");

		
		//select ringtone  -- change to Acoustic
		
		testCase.click("Select ringtone");
		testCase.click("Sound picker");
		if(testCase.isViewWithTextPresent("Just once")){
			testCase.click("Just once");
		}

		testCase.click("Acoustic");
		testCase.click("Done");
		
		
		//notification  -- disable
		if(testCase.isCheckboxChecked("checkbox", 0)){
			testCase.click("Notifications");
		}
		//default reminder time --change to 20 minutes
		
		testCase.click("Default reminder time");
		testCase.click("20 minutes");
		
		//Quick responses  -- change to "Be there in about 10 minutes."
		
		testCase.click("Quick responses");
		testCase.click("Be there in about 10 minutes.");
		testCase.enterText("I will ");
		testCase.click("OK");
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		
		//National holidays -- change to "Australia"

		testCase.click("National holidays");
		testCase.click("Australia");
		testCase.click("OK");
		
		//weekend -- add monday
		
		commonModule.scrollFindText("Weekend");
		testCase.click("Monday");
		testCase.click("OK");
		
		//Days in week view  -- change to Work days only
		commonModule.scrollFindText("Days in week view");
		testCase.click("Work days only");
		
		//Week starts on -- change to Monday
		commonModule.scrollFindText("Week starts on");
		testCase.click("Monday");
		
		//Show week number -- enable
		testCase.scrollDownNTimes(4);
		if (!testCase.isCheckboxChecked("checkbox",0)){
			testCase.click("Show week numbers");
		}
		
		//Use home time zone -- enable
		if (!testCase.isCheckboxChecked("checkbox",1)){
			testCase.click("Use home time zone");
		}
		
		//Weather forecast
		testCase.click("Weather forecast");
		if (testCase.isViewWithTextPresent("ON")){
			testCase.click("ON");
		}
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		
		//Birthday
		testCase.click("Birthdays");
		if (testCase.isViewWithTextPresent("ON")){
			testCase.click("ON");
		}
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		//Hide declined events
		if (testCase.isCheckboxChecked("checkbox",2)){
			testCase.click("Hide declined events");
		}
	}  
}
