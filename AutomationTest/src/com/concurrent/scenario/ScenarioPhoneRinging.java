package com.concurrent.scenario;

import android.os.Environment;
import android.util.Log;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;
import com.module.common.CommonModule;
import com.module.media.*;
import com.module.messaging.AbstractMessagingFactory;
import com.module.messaging.IMessaging;
import com.module.messaging.MessagingFactory;
import com.module.telephony.AbstractTelephonyFactory;
import com.module.telephony.ITelephony;

import java.io.File;
import java.util.HashMap;

import junit.framework.Assert;

public class ScenarioPhoneRinging implements IScenario {

	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private AcceptanceTestCase testCase;
	private IMessaging IMessaging;
	private ITelephony telephonyModule;

	private String remotePhoneNum1;
	private String remotePhoneNum2;

	public ScenarioPhoneRinging(AcceptanceTestCase testCase)
			throws PropertyNotFoundException {

		this.testCase = testCase;
		this.commonModule = new CommonModule(testCase);
		this.telephonyModule = ((AbstractTelephonyFactory) GroupFactories
				.createFactory(testCase, "telephony")).create();
        this.IMessaging = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();
		getPartnerPhoneNumber();
	}

	protected void getPartnerPhoneNumber() {
		remotePhoneNum1 = TestDataExtract.callNumber;
		remotePhoneNum2 = TestDataExtract.callNumber2;
	}

	public void doAfter() throws UiObjectNotFoundException {

		commonModule.openNotificationBar();

		commonModule.wait(2);

		testCase.click(remotePhoneNum2);

		telephonyModule.endCall();

	}

	@Override
	public void doBegin() throws UiObjectNotFoundException {

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum2);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");}
         else{
        	 Assert.assertTrue("Cannot received incoming call.",
                     false);
         }
         commonModule.backOutToHomeScreen();
	}
}
