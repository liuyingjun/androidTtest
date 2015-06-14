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
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;

import java.io.File;
import java.util.HashMap;

public class ScenarioExportContacts implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IContacts IContacts;
    private IOfficeSuite officeSuite;
    
    public ScenarioExportContacts(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IContacts = ContactsFactory.getContactsModule(testCase);
    	officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
    	moduleData = ModuleDataParser.getModuleData("movies");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    	
        AcceptanceTestCase.assertTrue("Export contacts failed",
                commonModule.waitForTextGone("Exporting contact data", 300 * 1000));
        
        officeSuite.launchOfficeSuite();
        
        if(!testCase.isViewWithTextMatches("Internal storage")){
            commonModule.clickResourceId("com.mobisystems.office:id/app_icon");

            commonModule.clickText("Internal storage");
            //commonModule.clickText("Internal storage");
        }
        
        commonModule.scrollFindText("System");
        testCase.click("PIM");
        
        AcceptanceTestCase.assertTrue("Export contacts failed",
                testCase.isViewWithTextPresent("PIM00001.vcf"));
        
        testCase.clickId("list_item_check");
        testCase.clickId("edit_delete");
        testCase.click("OK");
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IContacts.startPhonebook();
		IContacts.openPhonebooksetting("Export contacts");
        testCase.click("Internal storage");
        
        testCase.click("OK");
    
	}  
}
