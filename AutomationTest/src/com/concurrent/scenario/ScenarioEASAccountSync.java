package com.concurrent.scenario;

import android.os.Environment;
import android.util.Log;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.common.CommonModule;
import com.module.email.AbstractEmailFactory;
import com.module.email.IEmail;
import com.module.media.*;

import java.io.File;
import java.util.HashMap;

public class ScenarioEASAccountSync implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    IEmail IEmail;
    private String EASAccount;
    String syncTime1;
    String syncTime2;
    public ScenarioEASAccountSync(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IEmail = ((AbstractEmailFactory)GroupFactories.createFactory(testCase, "email")).create();
    	moduleData = ModuleDataParser.getModuleData("movies");
    	getPartnerPhoneNumber();
    }
    protected void getPartnerPhoneNumber(){
    	EASAccount = TestDataExtract.test_EASaccount;
    }

    public void doAfter() throws UiObjectNotFoundException{ 

		
		for (int j = 0; j < 10; j++) {
			commonModule.wait(3);
			if (!testCase.isViewWithTextPresent("Syncing now…")) {
				Log.d(TAG, "Sync is succeed");
				break;
			} else {
				Log.d(TAG, "Continue sync...");
			}
		}
		
		String syncTime2 = testCase.getTextFromViewWithId("summary");

		if (syncTime1 == syncTime2) {

			AcceptanceTestCase.assertTrue("Sync EAS account failed.",false);
		}
    }

	@Override
	public void doBegin(){
		testCase.launchApp("com.android.settings",
				"com.android.settings.Settings");

		commonModule.scrollFindText("Exchange ActiveSync");
	
		syncTime1 = testCase.getTextFromViewWithId("summary");
		commonModule.wait(60);
		testCase.clickItemWithDescription("More options");
		if (testCase.isViewWithTextPresent("Cancel sync")) {
			testCase.click("Cancel sync");
			commonModule.wait(2);
			testCase.clickItemWithDescription("More options");
			commonModule.wait(4);
		}
		testCase.click("Sync now");

	}  
}
