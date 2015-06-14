package com.concurrent.scenario;

import android.os.Environment;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.common.CommonModule;
import com.module.contacts.ContactsFactory;
import com.module.contacts.IContacts;
import com.module.media.*;

import java.io.File;
import java.util.HashMap;

public class ScenarioImportContacts implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IContacts IContacts;
    
    public ScenarioImportContacts(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IContacts = ContactsFactory.getContactsModule(testCase);
    	moduleData = ModuleDataParser.getModuleData("movies");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	
        AcceptanceTestCase.assertTrue("Import contacts disappeared or import finished.",
                testCase.isViewWithTextPresent("Import all"));
    	
        AcceptanceTestCase.assertTrue("Import contacts failed",
                commonModule.waitForTextGone("Import all", 300 * 1000));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IContacts.startPhonebook();
		IContacts.openPhonebooksetting("Import contacts");
        testCase.click("Internal storage");
        
        testCase.click("Local contact");
        
        if (commonModule.waitForText("Select vCard file", 120 * 1000)) {
            testCase.clickItemWithId("text1", 5);
            testCase.click("OK");
        }
	}  
}
