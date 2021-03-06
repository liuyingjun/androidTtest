package com.concurrent.scenario;

import android.util.Log;


import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;
import com.module.common.CommonModule;
import com.module.messaging.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ScenarioEditReadMMS implements IScenario{

	public static String TAG = "Reliability";
    private CommonModule commonModule;

    private IMessaging IMessaging;
    private AcceptanceTestCase testCase;
    private String remotePhoneNum;

    public ScenarioEditReadMMS(AcceptanceTestCase testCase){

    	this.testCase=testCase;
        IMessaging = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();
    	this.commonModule = new CommonModule(testCase);
    	getPartnerPhoneNumber();
    }

    protected void getPartnerPhoneNumber(){
    	remotePhoneNum = TestDataExtract.callNumber;
    }

    public void doAfter(){

         commonModule.wait(2);
         testCase.clickId("conversation_send_button");
         IMessaging.waitMessageSendSuccessfully();
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException {
        IMessaging.removeAllMessages();

        IMessaging.prepareSMSMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE,
        		 remotePhoneNum);
        IMessaging.InsertImageForMMS();
	}
}
