package com.concurrent.scenario;


import com.android.uiautomator.core.UiObjectNotFoundException;

import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import com.module.common.CommonModule;
import com.module.contacts.ContactsFactory;
import com.module.contacts.IContacts;

public class ScenarioDeleteContacts implements IScenario{

	  public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    IContacts IContacts;
    
    public ScenarioDeleteContacts(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IContacts = ContactsFactory.getContactsModule(testCase);

    }
    

    public void doAfter() throws UiObjectNotFoundException{ 
    			AcceptanceTestCase.assertTrue("Delete contacts failed", commonModule.waitForText("No contacts", 60*100));
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		IContacts.deleteContactsWithoutChecked();
	    }  
}
