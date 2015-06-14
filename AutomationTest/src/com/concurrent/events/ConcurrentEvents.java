package com.concurrent.events;

import java.io.File;

import junit.framework.Assert;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.browser.BrowserFactory;
import com.module.browser.IBrowser;
import com.module.common.CommonModule;
import com.module.telephony.AbstractTelephonyFactory;
import com.module.telephony.ITelephony;
import com.module.media.*;
import com.module.messaging.*;
import com.module.walkman.*;
import com.module.alarm.*;
import com.module.calendar.*;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;

public class ConcurrentEvents implements IConcurrentEvents{

	public static String TAG = "Reliability";
    private ITelephony telephonyModule;
    private IMessaging IMessaging;
    private IWalkman IWalkman;
    private IMedia IMedia;
    private IAlarm IAlarm;
    private ICalendar ICalendar;
	private IBrowser ibrowser;

    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private String remotePhoneNum;
    private String test_emailaccount;
    private BroadcastReceiver mInSmsReceiver;
    public static String strMsg = "msg";

    public ConcurrentEvents(AcceptanceTestCase testCase) throws PropertyNotFoundException{
    	this.testCase = testCase;

    	this.telephonyModule=((AbstractTelephonyFactory)GroupFactories.createFactory(testCase, "telephony")).create();

        this.IMessaging = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();

    	this.IWalkman=((AbstractWalkmanFactory)GroupFactories.createFactory(testCase, "walkman")).create();

    	this.IMedia= MediaFactory.create(testCase);

    	this.IAlarm=AlarmFactory.getCameraModule(testCase);

    	this.ICalendar= CalendarFactory.getCalendarModule(testCase);

    	this.commonModule = new CommonModule(testCase);

		this.ibrowser = BrowserFactory.create(testCase);
    	getPartnerPhoneNumber();
    }

    protected void getPartnerPhoneNumber(){
    	remotePhoneNum = TestDataExtract.callNumber;
    	test_emailaccount = TestDataExtract.test_emailaccount;
    }

    private void registerInSMSReceiver() {

        mInSmsReceiver = new BroadcastReceiver() {

    public void onReceive(Context ctx, Intent intent) {
        Log.d(TAG, "==========A message received==========");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }

            for (SmsMessage message : messages) {
                String strFrom = message.getDisplayOriginatingAddress();
                strMsg = message.getDisplayMessageBody();
                Log.d(TAG,"From:"+strFrom);
                Log.d(TAG,"Msg:"+strMsg);
            }
        }
    }     };

    Context context = testCase.getInstrumentation().getContext();
    context.registerReceiver(mInSmsReceiver, new IntentFilter(
            "android.provider.Telephony.SMS_RECEIVED"));

}


	@Override
	public void answerIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

        telephonyModule.answerIncomingCall();
        commonModule.wait(10);// Wait for incoming call
        telephonyModule.endCall();
	}

	@Override
	public void hangUpIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }

		telephonyModule.hangUpIncomingCall();
	}

	@Override
	public void ignoreIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

        if (commonModule.waitForText("Incoming call", 30 * 1000)) {
            Log.i(TAG, "call coming");

            Log.d(TAG, "Receive a incoming call during edit sms");
            commonModule.waitForTextGone("Incoming call", 100*1000);
        } else if (testCase
                .isViewInAllInteractiveWindows(null, null, "Incoming call", null, -1, -1)) {
            for (int i = 0; i < 20; i++) {
                if (testCase.isViewInAllInteractiveWindows(null, null, "Incoming call", null, -1,
                        -1)) {
                    commonModule.wait(5);
                } else {
                    break;
                }
            }
        }
	}

	@Override
	public void rejectWithSMSBusyIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

		telephonyModule.rejectIncomingCallWithMessage("I'm busy. I'll call you back later.");

	}

	@Override
	public void rejectWithSMSMeetingIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

		telephonyModule.rejectIncomingCallWithMessage("I'm in a meeting. I'll call you back later.");

	}

	@Override
	public void rejectWithSMSDrivingIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

		telephonyModule.rejectIncomingCallWithMessage("I'm driving. I'll call you back later.");

	}

	@Override
	public void rejectWithSMSEatingIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

		telephonyModule.rejectIncomingCallWithMessage("I'm eating. I'll call you back later.");

	}

	@Override
	public void rejectWithSMSAntherCallIncomingCall() throws PropertyNotFoundException{

		IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, remotePhoneNum);
		commonModule.wait(8);

         if(commonModule.waitForText("Incoming call", 30*1000)){
       	   Log.i(TAG, "call coming");
          }
          Log.d(TAG, "Receive a incoming call during edit sms");

		 telephonyModule.rejectIncomingCallWithMessage("I'm on another phone call. I'll call you back later.");

	}

	@Override
	public void receiveSMS() throws PropertyNotFoundException {

        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, remotePhoneNum);
        commonModule.wait(5);
        IMessaging.openReceiveMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
        Assert.assertTrue("Cannot received sms.",
                testCase.isViewWithTextPresent(CommandConstantsUtils.SMS_CONTENT_RECEIVE)
                        || testCase.isViewInAllInteractiveWindows(null, null,
                                CommandConstantsUtils.SMS_CONTENT_RECEIVE, null, -1, -1));
	}

	@Override
	public void receiveSMSFormStatusBar() throws PropertyNotFoundException, UiObjectNotFoundException {
		commonModule.wait(5);
        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, remotePhoneNum);
        commonModule.wait(5);
        commonModule.openNotificationBar();
        testCase.assertTrue("Cannot received message",
                testCase.isViewWithTextPresent(CommandConstantsUtils.SMS_CONTENT_RECEIVE));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
	}

	@Override
	public void receiveMMS() throws PropertyNotFoundException {

        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, remotePhoneNum);
        commonModule.wait(80);// Wait for partner phone send mms to
                              // mian phone.
        IMessaging.openReceiveMessage(remotePhoneNum);
        testCase.assertTrue("Cannot received mms",
                testCase.isViewWithTextPresent(CommandConstantsUtils.MMS_CONTENT_RECEIVE));


	}

	@Override
	public void receiveEmail() {

        commonModule.clearStatusBar();
        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_EMAIL, remotePhoneNum);

        Assert.assertTrue("Cannot receive email.",
                IMessaging.checkReceiveSMS(test_emailaccount, 10 * 60 * 1000));

	}

	@Override
	public void musicPlayInBackground() throws UiObjectNotFoundException {
		IWalkman.playMusicOnBackground();
	}

	@Override
	public void FMRadioPlayInBackground() {
		IMedia.playRadioOnBackground();
	}

	@Override
	public void videoPlayInBackground() {
		File dir = new File(Environment.getExternalStorageDirectory().getPath());
		IMedia.playVideoURL(dir,"oceans_x264_1280_534.mp4");
		testCase.pressKey(KeyEvent.KEYCODE_HOME);

	}

	@Override
	public void alarmExpired() throws PropertyNotFoundException {

        Assert.assertTrue("Can not receive the alarm in 2 minutes.",
                IAlarm.snoozeAlarm(3 * 60 * 1000));
        commonModule.wait(2);

	}

	@Override
	public void calendarExpired() throws PropertyNotFoundException {
		String calendarTitle1="Relibility test";
        commonModule.wait(2);
        testCase.assertTrue("Can not receive the reminder in 2 minutes.",
                ICalendar.waitForCalendarAlarm(calendarTitle1, 3 * 60 * 1000));

	}

	@Override
	public void SNSStatusUpdateTwitter() throws PropertyNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void SNSStatusUpdateFacebook() throws PropertyNotFoundException {
        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_FACEBOOK, remotePhoneNum);
        Assert.assertTrue("There is no input text message.", IMessaging
                .checkReceiveSMS("This is for Reliability facebook test", 300*1000));

	}

	@Override
	public void SNSStatusUpdateTwitterWechat() throws PropertyNotFoundException {
        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_FACEBOOK, remotePhoneNum);
        Assert.assertTrue("There is no input text message.", IMessaging
                .checkReceiveSMS("This is for Reliability wechat test", 10 * 60));

	}

	@Override
	public void SNSStatusUpdateWeibo() throws PropertyNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveFileViaBT() throws PropertyNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveSMSInBackground() throws PropertyNotFoundException {
		// TODO Auto-generated method stub
        this.registerInSMSReceiver();

        IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, remotePhoneNum);
        commonModule.wait(5);
        Log.d(TAG,"Msg:"+strMsg);
        AcceptanceTestCase.assertTrue("Cannot received sms.",
        		strMsg.equalsIgnoreCase(CommandConstantsUtils.SMS_CONTENT_RECEIVE));

        this.unregisterAllReceiver();
	}
    private void unregisterAllReceiver() {

        Context context = testCase.getInstrumentation().getContext();
        if (mInSmsReceiver != null) {
            context.unregisterReceiver(mInSmsReceiver);
            mInSmsReceiver = null;
        }

    }

	@Override
	public void calendarExpiredInBackground() throws PropertyNotFoundException {
					String calendarTitle1="Relibility test";
        commonModule.wait(3 * 60);
//        testCase.assertTrue("Can not receive the reminder in 2 minutes.",
//                ICalendar.waitForCalendarAlarm(calendarTitle1, 3 * 60 * 1000));

	}

	@Override
	public void downloadFileOnBackground() throws UiObjectNotFoundException{
		ibrowser.downloadFileWithChrome("http://spe.mobilephone.net/wit/objects/vid_3gp_bin_fl.dm");
	}

	@Override
	public void playGame() throws UiObjectNotFoundException {
		testCase.launchApp("org.cocos2dx.FishingJoy2",
				"org.cocos2dx.FishingJoy2.FishingJoy2");
		commonModule.acquireWakelock();
		/** load the game */
		commonModule.wait(30);

		if (testCase.isViewWithTextPresent("È¡Ïû")) {
			testCase.click("È¡Ïû");
		}
		/** start game button pointer */
		testCase.clickPoint(commonModule.getX(700, 1794),
				commonModule.getY(700, 1080));
		commonModule.wait(10);
		/** select the map button pointer */
		testCase.clickPoint(commonModule.getX(260, 1794),
				commonModule.getY(800, 1080));
		commonModule.wait(30);
		/** select hao time to use this game pointer */
		testCase.clickPoint(commonModule.getX(700, 1794),
				commonModule.getY(950, 1080));
		commonModule.wait(10);
		/** click sue button pointer */
		testCase.clickPoint(commonModule.getX(900, 1794),
				commonModule.getY(780, 1080));
		commonModule.wait(5);
		/** play the game */
		testCase.clickPoint(commonModule.getX(900, 1794),
				commonModule.getY(780, 1080));
		commonModule.wait(5);
		testCase.clickPoint(commonModule.getX(500, 1794),
				commonModule.getY(500, 1080));
		commonModule.wait(5);
		testCase.clickPoint(commonModule.getX(600, 1794),
				commonModule.getY(950, 1080));
		commonModule.wait(5);
		testCase.clickPoint(commonModule.getX(500, 1794),
				commonModule.getY(500, 1080));
		commonModule.wait(5);
		testCase.clickPoint(commonModule.getX(1500, 1794),
				commonModule.getY(550, 1080));
		commonModule.wait(5);
		testCase.clickPoint(commonModule.getX(1200, 1794),
				commonModule.getY(450, 1080));
		commonModule.wait(5);

		commonModule.backOutToHomeScreen();
	}

	@Override
	public void editMessage() throws UiObjectNotFoundException {
        IMessaging.prepareSMSMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE,
       		 remotePhoneNum);
       IMessaging.InsertImageForMMS();
	}

	@Override
	public void receiveMessageInBackground(String type) throws PropertyNotFoundException,
			UiObjectNotFoundException {
		if (type=="SMS"){
			IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, remotePhoneNum);
            }

		else if(type=="MMS"){
			IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, remotePhoneNum);
		}

		commonModule.wait(5);

	}

}
