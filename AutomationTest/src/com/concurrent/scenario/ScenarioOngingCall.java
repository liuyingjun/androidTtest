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

public class ScenarioOngingCall implements IScenario {

	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private AcceptanceTestCase testCase;
	private IMessaging IMessaging;
	private ITelephony telephonyModule;

	private String remotePhoneNum1;
	private String remotePhoneNum2;

	public ScenarioOngingCall(AcceptanceTestCase testCase)
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

		commonModule.backOutToHomeScreen();

		commonModule.openNotificationBar();

		commonModule.wait(2);

		commonModule.clickTextContains("Ongoing call");

		telephonyModule.endCall();

		commonModule.unLockScreen();

	}

	@Override
	public void doBegin() throws UiObjectNotFoundException {

		telephonyModule.makeMOCallAndAnswered(remotePhoneNum2);

	}
}
