package com.concurrent.scenario;

import android.os.Environment;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.common.CommonModule;
import com.module.contacts.ContactsFactory;
import com.module.contacts.IContacts;
import com.module.media.*;
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;

import java.io.File;
import java.util.HashMap;

public class ScenarioEditContacts implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IContacts IContacts;
    private IOfficeSuite officeSuite;
    
    public ScenarioEditContacts(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IContacts = ContactsFactory.getContactsModule(testCase);
    	officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
    	moduleData = ModuleDataParser.getModuleData("movies");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	
    	IContacts.inputEmail("reliabilitytest003@gmail.com");
    	
    	commonModule.clickText("Done");
    	commonModule.wait(5);
    	
    	AcceptanceTestCase.assertTrue(
				"Lose some words failed after concurrent."
				,testCase.isViewWithTextPresent("‪156 99001150‬")&&
				testCase.isViewWithTextPresent("reliabilitytest003@gmail.com"));
		
    	testCase.pressKey(KeyEvent.KEYCODE_BACK);
    	
    	AcceptanceTestCase.assertTrue(
				"Contact name wrong failed after concurrent."
				,testCase.isViewWithTextPresent("AA BB"));
		
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		
		IContacts.startPhonebook();
        commonModule.clickDescription("New contact");
        if(testCase.isViewWithTextPresent("Select backup account")){
            testCase.click("No backup");
        }
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }

        commonModule.wait(2);
		IContacts.inputContactFirstName("AA");
		IContacts.inputContactLastName("BB");
		IContacts.inputPhoneNumber("15699001150");
    
		
	}  
}
