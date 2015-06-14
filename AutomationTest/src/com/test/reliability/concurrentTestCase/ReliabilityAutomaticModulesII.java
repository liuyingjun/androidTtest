
package com.test.reliability.concurrentTestCase;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.concurrent.PostInvocationHandler;
import com.concurrent.PrePostInvocationHandler;
import com.concurrent.events.ConcurrentEvents;
import com.concurrent.events.IConcurrentEvents;
import com.concurrent.scenario.ScenarioAddClock;
import com.concurrent.scenario.ScenarioAlarmExpired;
import com.concurrent.scenario.ScenarioBatteryCharging;
import com.concurrent.scenario.ScenarioCalculator;
import com.concurrent.scenario.ScenarioCameraRecordingVideo;
import com.concurrent.scenario.ScenarioChangeCalendarSetting;
import com.concurrent.scenario.ScenarioCopyMultipleFilesOfficeSuite;
import com.concurrent.scenario.ScenarioCutMultipleFilesOfficeSuite;
import com.concurrent.scenario.ScenarioDeleteContacts;
import com.concurrent.scenario.ScenarioDeleteMultipleFilesOfficeSuite;
import com.concurrent.scenario.ScenarioEASAccountSync;
import com.concurrent.scenario.ScenarioEditContacts;
import com.concurrent.scenario.ScenarioEditReadMMS;
import com.concurrent.scenario.ScenarioEditReadSMS;
import com.concurrent.scenario.ScenarioExportContacts;
import com.concurrent.scenario.ScenarioImportContacts;
import com.concurrent.scenario.ScenarioOngingCall;
import com.concurrent.scenario.ScenarioPhoneRinging;
import com.concurrent.scenario.ScenarioPlayMusicAndVerifyBasicFunction;
import com.concurrent.scenario.ScenarioPlayingFMRadio;
import com.concurrent.scenario.ScenarioRecordingSequentialVideo;
import com.concurrent.scenario.ScenarioRecordingVideo4KVideo;
import com.concurrent.scenario.ScenarioRecordingVideo4KVideoSuperipr;
import com.concurrent.scenario.ScenarioRecordingVideoAREffect;
import com.concurrent.scenario.ScenarioRecordingVideoARFun;
import com.concurrent.scenario.ScenarioRecordingVideoCreativeEffect;
import com.concurrent.scenario.ScenarioRecordingVideoTimeshiftVideo;
import com.concurrent.scenario.ScenarioSetStopwatch;
import com.concurrent.scenario.ScenarioSetTimer;
import com.concurrent.scenario.ScenarioSketchDrawingPictureAndShareToEmail;
import com.concurrent.scenario.ScenarioTakePictureBackgroundDefocus;
import com.concurrent.scenario.ScenarioTrackIDSearchingMusic;
import com.concurrent.scenario.ScenarioVideoPlaying;
import com.concurrent.scenario.ScenarioViewAndEditOfficesuite;
import com.concurrent.scenario.ScenarioViewPictureFromAlbum;
import com.module.alarm.*;
import com.module.calculator.AbstractCalculatorFactory;
import com.module.calculator.ICalculator;
import com.module.calendar.*;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.CameraFactory;
import com.module.camera.ICamera;
import com.module.album.AlbumFactory;
import com.module.album.IAlbum;
import com.module.common.CommonModule;
import com.module.email.AbstractEmailFactory;
import com.module.email.IEmail;
import com.module.email.EmailFactory;
import com.module.email.IEmail;
import com.module.facebook.AbstractFacebookFactory;
import com.module.facebook.IFacebook;
import com.module.media.IMedia;
import com.module.media.MediaFactory;
import com.module.messaging.*;
import com.module.movies.*;
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;
import com.module.settings.AbstractSettingsFactory;
import com.module.settings.ISetting;
import com.module.telephony.AbstractTelephonyFactory;
import com.module.telephony.ITelephony;
import com.module.telephony.TelephonyFactory;
import com.module.contacts.IContacts;
import com.module.contacts.ContactsFactory;
import com.module.walkman.*;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleData;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.test.reliability.BasicUtils;
import com.utils.CommandConstantsUtils;
import com.utils.ErrorInfoUtils;
import com.utils.TestUIWatcher;

import org.xmlpull.v1.XmlPullParserException;

import android.app.AlarmManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.view.KeyEvent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.reflect.Proxy;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class ReliabilityAutomaticModulesII {

    private AcceptanceTestCase testCase;

    private CommonModule commonModule;

    private BasicUtils basicUtils;

    ITelephony telephonyModule;

    ICamera camera;

    IMessaging IMessaging;

    IMedia IMedia;

    IAlarm IAlarm;

    ICalendar ICalendar;

    IWalkman IWalkman;

    IAlbum IAlbum;

    IContacts IContacts;

    IEmail IEmail;

	private IFacebook facebook;

    private IAlbum albumModule;

    private IMovies movieModule;

    private ICalculator calculator;

    private ISetting settingsModule;

    private IOfficeSuite officeSuite;

    ConcurrentEvents concurrentevets;
    public String cameraFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/100ANDRO/";

    public String xperiaFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/XPERIA/";

    public static String TAG = "Reliability";

    public String testloops;

    public int runFlag;

    public boolean isCatch;

    private long time1;

    private long time2;

    private long time4;

    private int realTimes = 0;

    private String callNumber;

    private static String callNumber2;

    private static String callNumber3;

    private static String selNumber;

    private static String test_emailaccount;

    private static String test_emailpassword;

    private static String test_email2account;

    private static String test_email2password;

    private static String test_facebookaccount;

    private static String test_facebookpassword;

    private static String test_facebookname;

    private static String test_wifi;

    private static String test_wifipassword;

    private static String test_partnerskypeaccount;

    private static String test_skypeaccount;

    private static String test_skypepassword;

    private static String test_sipaccount;

    private static String test_sippassword;

    private static String partner_sipaccount;

    private static String test_EASaccount;

    private static String test_EASpassword;

    private static String test_serveremail;

    private static String test_gmailaccount;

    private static String test_gmailpassword;

    public ReliabilityAutomaticModulesII(AcceptanceTestCase testCase)
            throws PropertyNotFoundException, UiObjectNotFoundException, IOException, XmlPullParserException {
        ModuleDataParser dataParser = new ModuleDataParser();
        dataParser.parse();

        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
        officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
        camera = ((AbstractCameraFactory)GroupFactories.createFactory(testCase, "camera")).create();
        IMessaging = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();
        telephonyModule = ((AbstractTelephonyFactory)GroupFactories.createFactory(testCase, "telephony")).create();
        this.IMedia = MediaFactory.create(testCase);
        this.IAlarm = AlarmFactory.getCameraModule(testCase);
        this.ICalendar = CalendarFactory.getCalendarModule(testCase);
        this.IWalkman = ((AbstractWalkmanFactory)GroupFactories.createFactory(testCase, "walkman")).create();
        this.IAlbum = AlbumFactory.getAlbumModule(testCase);
        this.IContacts = ContactsFactory.getContactsModule(testCase);
        this.IEmail = ((AbstractEmailFactory)GroupFactories.createFactory(testCase, "email")).create();
        albumModule = AlbumFactory.getAlbumModule(testCase);
        facebook = ((AbstractFacebookFactory)GroupFactories.createFactory(testCase, "facebook"))
                .create();
        calculator = ((AbstractCalculatorFactory)GroupFactories.createFactory(testCase,
                "calculator")).create();
        settingsModule = ((AbstractSettingsFactory)(GroupFactories.createFactory(testCase,
                "settings"))).getSettingsModule();


		this.concurrentevets = new ConcurrentEvents(testCase);
        getAccounts();
        TestUIWatcher UIWatcher = new TestUIWatcher();
        UIWatcher.watch();

        Log.d(TAG, "Send email account to server");
        IMessaging.remoteSendMessageToDUT("MUT_Email_Account:" + test_emailaccount, callNumber);
        IMessaging.remoteSendMessageToDUT("MUT_Facebook_Name:" + test_facebookname, callNumber);
    }

    public void getAccounts() {
        callNumber = TestDataExtract.callNumber;
        callNumber2 = TestDataExtract.callNumber2;
        callNumber3 = TestDataExtract.callNumber3;

        test_emailaccount = TestDataExtract.test_emailaccount;
        test_emailpassword = TestDataExtract.test_emailpassword;
        // test_googleaccount = TestDataExtract.test_googleaccount;
        // test_googlepassword = TestDataExtract.test_googlepassword;
        test_facebookaccount = TestDataExtract.test_facebookaccount;
        test_facebookpassword = TestDataExtract.test_facebookpassword;
        test_facebookname = TestDataExtract.test_facebookname;
        // test_serveremail = TestDataExtract.test_serveremail;
        test_partnerskypeaccount = TestDataExtract.test_partnerskypeaccount;
        test_skypeaccount = TestDataExtract.test_skypeaccount;
        test_skypepassword = TestDataExtract.test_skypepassword;
        test_sipaccount = TestDataExtract.test_sipaccount;
        test_sippassword = TestDataExtract.test_sippassword;
        partner_sipaccount = TestDataExtract.partner_sipaccount;
        test_email2account = TestDataExtract.test_email2account;
        test_email2password = TestDataExtract.test_email2password;
        test_EASaccount = TestDataExtract.test_EASaccount;
        test_EASpassword = TestDataExtract.test_EASpassword;
        test_serveremail = TestDataExtract.test_serveremail;
        test_gmailaccount = TestDataExtract.test_gmailaccount;
        test_gmailpassword = TestDataExtract.test_gmailpassword;

        test_wifi = TestDataExtract.test_wifi;
        test_wifipassword = TestDataExtract.test_wifipassword;
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-1
     * RELIA_Messaging_Interaction events when sending message-Parameter__incoming_call
     */

	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.answerIncomingCall();

			editReadMessagaConcurrent.ignoreIncomingCall();

			editReadMessagaConcurrent.hangUpIncomingCall();

//			editReadMessagaConcurrent.rejectWithSMSAntherCallIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSBusyIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSDrivingIncomingCall();

//			editReadMessagaConcurrent.rejectWithSMSEatingIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			telephonyModule.endCall();
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-2
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__incoming_messgae
	 */

	@SuppressWarnings("static-access")
	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__incoming_messgae(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.receiveSMS();

			editReadMessagaConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-3
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__incoming_email
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__incoming_email(
			String casename, HashMap<String, String> paras) throws Exception {
		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {
			editReadMessagaConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-4
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__background_media
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__background_media(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler editReadMessaga = new PostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(), editReadMessaga);

		try {
			editReadMessagaConcurrent.musicPlayInBackground();

			editReadMessagaConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IMedia.stopRadio();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-4
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__background_media
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__background_video(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler editReadMessaga = new PostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(), editReadMessaga);

		try {
			IMedia.setVideoPlayBackgroundSetting("on");

			editReadMessagaConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			IMedia.setVideoPlayBackgroundSetting("off");

		}
	}
	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-5
	 * RELIA_Messaging_Interaction events when sending message-Parameter__alarm
	 */

	@SuppressWarnings({ "static-access", "deprecation" })
	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			editReadMessagaConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			editReadMessagaConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-6
	 * RELIA_Messaging_Interaction events when sending message-Parameter__chat
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_SMS__chat(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadSMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.SNSStatusUpdateFacebook();

			editReadMessagaConcurrent.SNSStatusUpdateWeibo();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-1
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__incoming_call
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.answerIncomingCall();

			editReadMessagaConcurrent.ignoreIncomingCall();

			editReadMessagaConcurrent.hangUpIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSAntherCallIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSBusyIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSDrivingIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSEatingIncomingCall();

			editReadMessagaConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			telephonyModule.endCall();
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-2
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__incoming_messgae
	 */

	@SuppressWarnings("static-access")
	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__incoming_messgae(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.receiveSMS();

			editReadMessagaConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {

			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-3
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__incoming_email
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__incoming_email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {
			editReadMessagaConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-4
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__background_media
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__background_media(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler editReadMessaga = new PostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(), editReadMessaga);

		try {
			editReadMessagaConcurrent.musicPlayInBackground();

			editReadMessagaConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IMedia.stopRadio();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-4
	 * RELIA_Messaging_Interaction events when sending
	 * message-Parameter__background_media
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__background_video(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler editReadMessaga = new PostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(), editReadMessaga);

		try {
			IMedia.setVideoPlayBackgroundSetting("on");

			editReadMessagaConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IMedia.setVideoPlayBackgroundSetting("off");

		}
	}
	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-5
	 * RELIA_Messaging_Interaction events when sending message-Parameter__alarm
	 */

	@SuppressWarnings({ "static-access", "deprecation" })
	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			editReadMessagaConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			editReadMessagaConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	/**
	 * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344171-6
	 * RELIA_Messaging_Interaction events when sending message-Parameter__chat
	 */

	public void RELIA_Messaging_Interaction_Events_When_Sending_MMS__chat(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editReadMessaga = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditReadMMS(testCase));

		IConcurrentEvents editReadMessagaConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editReadMessaga);

		try {

			editReadMessagaConcurrent.SNSStatusUpdateFacebook();

			editReadMessagaConcurrent.SNSStatusUpdateWeibo();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_take_picture_when_calling(String casename, HashMap<String, String> paras) throws Exception {

		try {
					telephonyModule.makeMOCallAndAnswered(callNumber);
					commonModule.backOutToHomeScreen();

					camera.openCameraAndTakePicture("Superior auto");

					camera.openCameraAndTakePicture("AR fun");

					camera.openCameraAndTakePicture("Timeshift video");

					camera.openCameraAndTakePicture("AR effect");

					camera.openCameraAndTakePicture("Creative effect");

					camera.openCameraAndTakePicture("Background defocus");

					camera.openCameraAndTakePicture("Face in");

					camera.openCameraAndTakePicture("Multi camera");

					camera.openCameraAndTakePicture("Sweep Panorama");

					camera.openCameraAndTakePicture("Sound Photo");

					//camera.openCameraAndTakePicture("Portrait Retouch");

					camera.openCameraAndTakePicture("4K video");

					telephonyModule.endCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_superiorAuto_incoming_calling(String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideoSuperipr(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_superiorAuto_incoming_sms(String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideoSuperipr(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_superiorAuto_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideoSuperipr(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__Incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingMusic = new PrePostInvocationHandler(
				concurrentevets, new ScenarioPlayMusicAndVerifyBasicFunction(
						testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__Incoming_MMS(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingMusic = new PrePostInvocationHandler(
				concurrentevets, new ScenarioPlayMusicAndVerifyBasicFunction(
						testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__Incoming_email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayMusicAndVerifyBasicFunction(testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler playingMusic = new PostInvocationHandler(concurrentevets,
				new ScenarioPlayMusicAndVerifyBasicFunction(testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__Video_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler playingMusic = new PostInvocationHandler(concurrentevets,
				new ScenarioPlayMusicAndVerifyBasicFunction(testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Music_Player_Concurrent_events_while_playing_music__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayMusicAndVerifyBasicFunction(testCase));

		IConcurrentEvents playingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingMusic);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			IWalkman.setPlayMode("repeat all");

			playingMusicConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}


	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_superiorAuto_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideoSuperipr(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");

			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_ARfun_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoARFun(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

	} catch (Exception ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (AssertionFailedError ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (Error ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} finally {
		commonModule.unLockScreen();
		commonModule.backOutToHomeScreen();
		telephonyModule.endCall();
		commonModule.delete(cameraFilePath);
	}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_ARfun_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoARFun(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

	} catch (Exception ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (AssertionFailedError ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (Error ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} finally {
		commonModule.unLockScreen();
		commonModule.backOutToHomeScreen();
		commonModule.delete(cameraFilePath);
	}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_ARfun_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoARFun(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_ARfun_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoARFun(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_AReffect_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoAREffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

	} catch (Exception ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (AssertionFailedError ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (Error ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} finally {
		commonModule.unLockScreen();
		commonModule.backOutToHomeScreen();
		telephonyModule.endCall();
		commonModule.delete(cameraFilePath);
	}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_AReffect_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoAREffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

	} catch (Exception ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (AssertionFailedError ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} catch (Error ex) {
		commonModule.takeScreenShot(casename);
		throw ex;
	} finally {
		commonModule.unLockScreen();
		commonModule.backOutToHomeScreen();
		commonModule.delete(cameraFilePath);
	}
}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_AReffect_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoAREffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_AReffect_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoAREffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_creativeffect_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoCreativeEffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_creativeffect_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoCreativeEffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_creativeffect_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoCreativeEffect(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_creativeffect_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoCreativeEffect(
						testCase));
		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_4k2k_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			camera.launchCameraPhotoApplication();
			camera.selectOneEffectFromCameraSettings("4K video");
			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_4k2k_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_4k2k_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_4k2k_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideo4KVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_timeshift_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoTimeshiftVideo(
						testCase));
		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {
			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_timeshift_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoTimeshiftVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_timeshift_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoTimeshiftVideo(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_timeshift_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioRecordingVideoTimeshiftVideo(
						testCase));
		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_backgroundefocus_incoming_calling(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioTakePictureBackgroundDefocus(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.answerIncomingCall();

			recordingVideoConcurrent.ignoreIncomingCall();

			recordingVideoConcurrent.hangUpIncomingCall();

			recordingVideoConcurrent.rejectWithSMSBusyIncomingCall();

			recordingVideoConcurrent.rejectWithSMSDrivingIncomingCall();

			recordingVideoConcurrent.rejectWithSMSMeetingIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_backgroundefocus_incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioTakePictureBackgroundDefocus(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			recordingVideoConcurrent.receiveSMSInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_backgroundefocus_alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(
				concurrentevets, new ScenarioTakePictureBackgroundDefocus(
						testCase));
		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			recordingVideoConcurrent.alarmExpired();

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
					description, "UTC/GMT +2:00", true);

			recordingVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_Camera_Concurrent_Events_When_Using_Camera_backgroundefocus_video_playing(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cameraRecordingVideo = new PostInvocationHandler(
				concurrentevets, new ScenarioTakePictureBackgroundDefocus(
						testCase));

		IConcurrentEvents recordingVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cameraRecordingVideo);

		try {

			IMedia.setVideoPlayBackgroundSetting("on");
			recordingVideoConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.setVideoPlayBackgroundSetting("off");
			commonModule.delete(cameraFilePath);
		}
	}

	public void RELIA_MM_Concurrent_events_for_video_palying__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler videoPlaying = new PrePostInvocationHandler(concurrentevets,
				new ScenarioVideoPlaying(testCase));

		IConcurrentEvents videoPlayingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						videoPlaying);

		try {
			videoPlayingConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_MM_Concurrent_events_for_video_palying__incoming_MMS(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler videoPlaying = new PrePostInvocationHandler(concurrentevets,
				new ScenarioVideoPlaying(testCase));

		IConcurrentEvents videoPlayingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						videoPlaying);

		try {


			videoPlayingConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_MM_Concurrent_events_for_video_palying__incoming_email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler videoPlaying = new PrePostInvocationHandler(concurrentevets,
				new ScenarioVideoPlaying(testCase));

		IConcurrentEvents videoPlayingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						videoPlaying);

		try {
			videoPlayingConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_MM_Concurrent_events_for_video_palying__Music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler videoPlaying = new PostInvocationHandler(concurrentevets,
				new ScenarioVideoPlaying(testCase));

		IConcurrentEvents videoPlayingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						videoPlaying);

		try {

			videoPlayingConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_MM_Concurrent_events_for_video_palying__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler videoPlaying = new PostInvocationHandler(concurrentevets,
				new ScenarioVideoPlaying(testCase));

		IConcurrentEvents videoPlayingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						videoPlaying);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			videoPlayingConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}
	public void RELIA_Telephony_Concurrent_Events_While_Calling_Under_RAM__incoming_messgae(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler ongoingCall = new PrePostInvocationHandler(concurrentevets,
				new ScenarioOngingCall(testCase));

		IConcurrentEvents ongoingCallConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						ongoingCall);

		try {

			ongoingCallConcurrent.receiveSMS();

			commonModule.wait(3);

			ongoingCallConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			telephonyModule.endCall();
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Telephony_Concurrent_Events_While_Calling_Under_RAM__background_download(
			String casename, HashMap<String, String> paras) throws Exception {

	    PrePostInvocationHandler ongoingCall = new PrePostInvocationHandler(concurrentevets,
                new ScenarioOngingCall(testCase));

        IConcurrentEvents ongoingCallConcurrent = (IConcurrentEvents) Proxy
                .newProxyInstance(concurrentevets.getClass().getClassLoader(),
                        concurrentevets.getClass().getInterfaces(),
                        ongoingCall);


		try {

			ongoingCallConcurrent.downloadFileOnBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			telephonyModule.endCall();
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Telephony_Concurrent_Events_While_Calling_Under_RAM__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler ongoingCall = new PrePostInvocationHandler(concurrentevets,
				new ScenarioOngingCall(testCase));

		IConcurrentEvents ongoingCallConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						ongoingCall);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);
//		    IAlarm.setAlarm(testCase.getInstrumentation().getTargetContext());

			ongoingCallConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			telephonyModule.endCall();
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Teleohony_Concurrnent_events_when_ringing__incoming_SMS(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler phoneRinging = new PrePostInvocationHandler(
				concurrentevets, new ScenarioPhoneRinging(
						testCase));

		IConcurrentEvents phoneRingingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						phoneRinging);

		try {

			phoneRingingConcurrent.receiveSMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Teleohony_Concurrnent_events_when_ringing__incoming_MMS(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler phoneRinging = new PrePostInvocationHandler(
				concurrentevets, new ScenarioPhoneRinging(
						testCase));

		IConcurrentEvents phoneRingingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						phoneRinging);
		try {


			phoneRingingConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler importContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);

		try {

			importContactsConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();

		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__incoming_SMS(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler importContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);
		try {

			importContactsConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler importContacts = new PostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);

		try {

			importContactsConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler importContacts = new PostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);

		try {

			importContactsConcurrent.FMRadioPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler importContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			commonModule.wait(30);

			importContactsConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
			IAlarm.deleteAllAlarms();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_import_contacts__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler importContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioImportContacts(testCase));

		IConcurrentEvents importContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						importContacts);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  importContactsConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);

		try {
			IContacts.createMoreContacts(1000);

			exportContactsConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();

		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__incoming_SMS(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);
		try {
			IContacts.createMoreContacts(1000);

			exportContactsConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);

		try {
			IContacts.createMoreContacts(1000);

			exportContactsConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);

		try {
			IContacts.createMoreContacts(1000);

			exportContactsConcurrent.FMRadioPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);

		try {
			IContacts.createMoreContacts(1000);

			IAlarm.setNewAlarmFewMinuteslater(0,3);

			commonModule.wait(30);

			exportContactsConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
			IAlarm.deleteAllAlarms();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_when_export_contacts__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler exportContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioExportContacts(testCase));

		IConcurrentEvents exportContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						exportContacts);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {
			 IContacts.createMoreContacts(1000);

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  exportContactsConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IContacts.deleteContacts();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
            IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);
		try {

			playingFMRadioConcurrent.receiveSMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.stopRadio();
	}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__incoming_email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.receiveEmail();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__download_file(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.downloadFileOnBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.stopRadio();

		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  playingFMRadioConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__alarm(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			playingFMRadioConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
			IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__play_game(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.playGame();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
            IMedia.stopRadio();
		}
	}

	public void RELIA_FM_Concurrnent_While_playing_FM__edit_message(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler playingFMRadio = new PrePostInvocationHandler(concurrentevets,
				new ScenarioPlayingFMRadio(testCase));

		IConcurrentEvents playingFMRadioConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						playingFMRadio);

		try {

			playingFMRadioConcurrent.editMessage();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
            IMedia.stopRadio();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {

			viewPictureFromAlbumConcurrent.answerIncomingCall();

			viewPictureFromAlbumConcurrent.ignoreIncomingCall();

			viewPictureFromAlbumConcurrent.hangUpIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
            IMedia.stopRadio();
		}
	}
	public void RELIA_Album_Concurrnent_events_when_viewing_picture__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {

			viewPictureFromAlbumConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
            IMedia.stopRadio();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {

			viewPictureFromAlbumConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {

			viewPictureFromAlbumConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler viewPictureFromAlbum = new PostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {

			viewPictureFromAlbumConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			viewPictureFromAlbumConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
		}
	}

	public void RELIA_Album_Concurrnent_events_when_viewing_picture__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewPictureFromAlbum = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewPictureFromAlbum(testCase));

		IConcurrentEvents viewPictureFromAlbumConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewPictureFromAlbum);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";
		try {

			startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
		    ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
							description, "UTC/GMT +2:00", true);

			viewPictureFromAlbumConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);

		try {

			calculatorConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);
		try {

			calculatorConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);

		try {

			calculatorConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);

		try {

			calculatorConcurrent.receiveEmail();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler calculator = new PostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);

		try {

			calculatorConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  calculatorConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Calculator__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler calculator = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCalculator(testCase));

		IConcurrentEvents calculatorConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						calculator);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			calculatorConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Alarm___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler alarmExpired = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);

		try {

			alarmExpiredConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			IAlarm.deleteAllAlarms();

		}

	}

	public void RELIA_PIM_Concurrnent_for_Alarm__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler alarmExpired = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);
		try {

			alarmExpiredConcurrent.receiveMessageInBackground("SMS");

            Assert.assertTrue("Canot Receive sms or timeout.",
                    IMessaging.checkReceiveSMS("Are you going to the party tonight?", 10));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
	}
	}

	public void RELIA_PIM_Concurrnent_for_Alarm__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler alarmExpired = new PostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);

		try {

			alarmExpiredConcurrent.receiveMessageInBackground("MMS");

            Assert.assertTrue("Receive mms timeout.",
                    IMessaging.checkReceiveSMS(callNumber, 10 * 1000));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
		}
	}


	public void RELIA_PIM_Concurrnent_for_Alarm__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler alarmExpired = new PostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);

		try {

			alarmExpiredConcurrent.musicPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
			IWalkman.stopMusicFromStatusBar();

		}
	}


	public void RELIA_PIM_Concurrnent_for_Alarm__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler alarmExpired = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an reliability test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  alarmExpiredConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
		}
	}


	public void RELIA_PIM_Concurrnent_for_Alarm__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler alarmExpired = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAlarmExpired(testCase));

		IConcurrentEvents alarmExpiredConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						alarmExpired);
		try {

			alarmExpiredConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
		}
	}


	public void RELIA_PIM_Concurrnent_for_Clocks___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);

		try {

			addClockConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);
		try {

			addClockConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
	}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);

		try {

			addClockConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler addClock = new PostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);

		try {

			addClockConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
			IWalkman.stopMusicFromStatusBar();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			addClockConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  addClockConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Clocks__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler addClock = new PrePostInvocationHandler(concurrentevets,
				new ScenarioAddClock(testCase));

		IConcurrentEvents addClockConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						addClock);
		try {

			addClockConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.startClock();
			IAlarm.deleteClockOneByOne();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);

		try {

			SetStopwatchConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

			IAlarm.stopStopwatchOrTimer("Stopwatch");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);
		try {

			SetStopwatchConcurrent.receiveSMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Stopwatch");
	}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);

		try {

			SetStopwatchConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Stopwatch");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler setStopwatch = new PostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);
		try {

			SetStopwatchConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IAlarm.stopStopwatchOrTimer("Stopwatch");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			SetStopwatchConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
			IAlarm.stopStopwatchOrTimer("Stopwatch");

		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  SetStopwatchConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Stopwatch");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Stopwatch__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setStopwatch = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetStopwatch(testCase));

		IConcurrentEvents SetStopwatchConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setStopwatch);
		try {

			SetStopwatchConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Stopwatch");
		}
	}


	public void RELIA_PIM_Concurrnent_for_Timer___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);

		try {

			setTimerConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
			IAlarm.stopStopwatchOrTimer("Timer");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);
		try {

			setTimerConcurrent.receiveSMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Timer");
	}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);

		try {

			setTimerConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Timer");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler setTimer = new PostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);
		try {

			setTimerConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
			IAlarm.stopStopwatchOrTimer("Timer");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			setTimerConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();
			IAlarm.stopStopwatchOrTimer("Timer");

		}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  setTimerConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Timer");
		}
	}

	public void RELIA_PIM_Concurrnent_for_Timer__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler setTimer = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSetTimer(testCase));

		IConcurrentEvents setTimerConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						setTimer);
		try {
			setTimerConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.stopStopwatchOrTimer("Timer");
		}
	}


	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);

		try {

			viewAndEditOfficesuiteConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);
		try {

			viewAndEditOfficesuiteConcurrent.receiveSMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);

		try {

			viewAndEditOfficesuiteConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler viewAndEditOfficesuite = new PostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);
		try {

			viewAndEditOfficesuiteConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);

		try {
			IAlarm.setNewAlarmFewMinuteslater(1,20);

			viewAndEditOfficesuiteConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  viewAndEditOfficesuiteConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler viewAndEditOfficesuite = new PrePostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);
		try {
			viewAndEditOfficesuiteConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_view_edit_file__download_file(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler viewAndEditOfficesuite = new PostInvocationHandler(concurrentevets,
				new ScenarioViewAndEditOfficesuite(testCase));

		IConcurrentEvents viewAndEditOfficesuiteConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						viewAndEditOfficesuite);
		try {

			viewAndEditOfficesuiteConcurrent.downloadFileOnBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();

		}
	}


	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);

		try {

			batteryChargingConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {

			batteryChargingConcurrent.receiveSMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);

		try {

			batteryChargingConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler batteryCharging = new PostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {

			batteryChargingConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);

		try {
			IAlarm.setNewAlarmFewMinuteslater(1,20);

			batteryChargingConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  batteryChargingConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {
			batteryChargingConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__download_file(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {

			batteryChargingConcurrent.downloadFileOnBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.discardChanges();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__video_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);

		try {

			  batteryChargingConcurrent.videoPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler batteryCharging = new PostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {
			batteryChargingConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Battery_Concurrnent_events_when_charging_USBmode__playing_game(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler batteryCharging = new PrePostInvocationHandler(concurrentevets,
				new ScenarioBatteryCharging(testCase));

		IConcurrentEvents batteryChargingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						batteryCharging);
		try {

			batteryChargingConcurrent.playGame();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);

		try {

			useSketchSettingConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);
		try {

			useSketchSettingConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);

		try {

			useSketchSettingConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler useSketchSetting = new PostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);
		try {

			useSketchSettingConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email_setting__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			useSketchSettingConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  useSketchSettingConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_PIM_Concurrnent_for_Sketch_share_from_email_setting__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler useSketchSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioSketchDrawingPictureAndShareToEmail(testCase));

		IConcurrentEvents useSketchSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						useSketchSetting);
		try {

			useSketchSettingConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IAlbum.exitSketchDrawingStatus();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);

		try {

			changeCalendarSettingConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);
		try {

			changeCalendarSettingConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);

		try {

			changeCalendarSettingConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler changeCalendarSetting = new PostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);
		try {

			changeCalendarSettingConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IWalkman.stopMusicFromStatusBar();

		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			changeCalendarSettingConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  changeCalendarSettingConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Calendar_Concurrnent_when_set_calendar_setting__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler changeCalendarSetting = new PrePostInvocationHandler(concurrentevets,
				new ScenarioChangeCalendarSetting(testCase));

		IConcurrentEvents changeCalendarSettingConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						changeCalendarSetting);
		try {

			changeCalendarSettingConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);

		try {

			trackIDSearchingMusicConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);
		try {

			trackIDSearchingMusicConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);

		try {

			trackIDSearchingMusicConcurrent.receiveMMS();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__flie_download(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler trackIDSearchingMusic = new PostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);
		try {

			trackIDSearchingMusicConcurrent.downloadFileOnBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);

		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			trackIDSearchingMusicConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  trackIDSearchingMusicConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_TrackID_Concurrnent_events_when_searching_songs__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler trackIDSearchingMusic = new PrePostInvocationHandler(concurrentevets,
				new ScenarioTrackIDSearchingMusic(testCase));

		IConcurrentEvents trackIDSearchingMusicConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						trackIDSearchingMusic);
		try {

			trackIDSearchingMusicConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
		}
	}
	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);

		try {

			editContactsConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}
	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {

			editContactsConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);

		try {

			editContactsConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler editContacts = new PostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {

			editContactsConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,3);

			editContactsConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  editContactsConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {

			editContactsConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Phonebook_Concurrnent_events_while_edit_contacts__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {

			editContactsConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IContacts.deleteContacts();
			commonModule.backOutToHomeScreen();
		}
	}


	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);

		try {

			copyMultipleFilesConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			telephonyModule.endCall();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);
		try {


			copyMultipleFilesConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
	}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);

		try {

			copyMultipleFilesConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler copyMultipleFiles = new PostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);
		try {

			copyMultipleFilesConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,2);

			copyMultipleFilesConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  copyMultipleFilesConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler copyMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCopyMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents copyMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						copyMultipleFiles);
		try {

			copyMultipleFilesConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_copy__file_download(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler editContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEditContacts(testCase));

		IConcurrentEvents editContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						editContacts);
		try {

			editContactsConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);

		try {

			cutMultipleFilesConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			telephonyModule.endCall();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);
		try {


			cutMultipleFilesConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
	}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);

		try {

			cutMultipleFilesConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler cutMultipleFiles = new PostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);
		try {

			cutMultipleFilesConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,2);

			cutMultipleFilesConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  cutMultipleFilesConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);
		try {

			cutMultipleFilesConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_cut__file_download(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler cutMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioCutMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents cutMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						cutMultipleFiles);
		try {

			cutMultipleFilesConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}


	public void RELIA_OfficwSuite_Concurrnent_events_when_operate_multiple_files_delete___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);

		try {

			deleteMultipleFilesConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			telephonyModule.endCall();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);
		try {

			deleteMultipleFilesConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
	}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);

		try {

			deleteMultipleFilesConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler deleteMultipleFiles = new PostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);
		try {

			deleteMultipleFilesConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,2);

			deleteMultipleFilesConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  deleteMultipleFilesConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();

		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteMultipleFiles = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);
		try {

			deleteMultipleFilesConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_OfficeSuite_Concurrnent_events_when_operate_multiple_files_delete__file_download(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler deleteMultipleFiles = new PostInvocationHandler(concurrentevets,
				new ScenarioDeleteMultipleFilesOfficeSuite(testCase));

		IConcurrentEvents deleteMultipleFilesConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteMultipleFiles);
		try {

			deleteMultipleFilesConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			officeSuite.openPathFromStorage("Internal storage/Reliabilitytest/2", false);
			officeSuite.emptyFolder();
		}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler recordingSequentialVideo = new PrePostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);
		try {

			recordingSequentialVideoConcurrent.receiveMessageInBackground("SMS");

	        IMessaging.openReceiveMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
	        Assert.assertTrue("Cannot received sms.",
	                testCase.isViewWithTextPresent(CommandConstantsUtils.SMS_CONTENT_RECEIVE));


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler recordingSequentialVideo = new PrePostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);
		try {

			recordingSequentialVideoConcurrent.receiveMessageInBackground("MMS");

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler recordingSequentialVideo = new PostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);
		try {

			recordingSequentialVideoConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler recordingSequentialVideo = new PrePostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  recordingSequentialVideoConcurrent.calendarExpiredInBackground();

			  Assert.assertTrue("Canot Receive calendar.",
	                    IMessaging.checkReceiveSMS(calendarTitle1, 10));
		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler recordingSequentialVideo = new PrePostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);
		try {

			recordingSequentialVideoConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Camera_Concurrnent_events_during_recording_sequential_video__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler recordingSequentialVideo = new PostInvocationHandler(concurrentevets,
				new ScenarioRecordingSequentialVideo(testCase));

		IConcurrentEvents recordingSequentialVideoConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						recordingSequentialVideo);
		try {

			recordingSequentialVideoConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}


	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);

		try {

			syncEASConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);
		try {


			syncEASConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);

		try {

			syncEASConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler syncEAS = new PostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);
		try {

			syncEASConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();

		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,2);

			syncEASConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  syncEASConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler syncEAS = new PrePostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);
		try {

			syncEASConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Sync_Concurrnent_events_while_sync_EAS__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PostInvocationHandler syncEAS = new PostInvocationHandler(concurrentevets,
				new ScenarioEASAccountSync(testCase));

		IConcurrentEvents syncEASConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						syncEAS);
		try {

			syncEASConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}


	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact___incoming_call(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {

			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", "PIM"+contactNumbers+".vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.answerIncomingCall();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			telephonyModule.endCall();

		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__incoming_sms(
			String casename, HashMap<String, String> paras) throws Exception {


		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {

			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", "PIM"+contactNumbers+".vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.receiveSMSFormStatusBar();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
	}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__incoming_mms(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));

		try {
			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.receiveMMS();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();

		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__music_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {
			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.musicPlayInBackground();


		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			IWalkman.stopMusicFromStatusBar();
		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__alarm(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {
			IAlarm.setNewAlarmFewMinuteslater(0,2);

			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			commonModule.wait(15);

			deleteContactsConcurrent.alarmExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
			IAlarm.deleteAllAlarms();

		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__calendar(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));

		long startMillis = 0;
		String calendarTitle1 = "Relibility test";
		String description = "This is an aging test";

		try {

		      startMillis = commonModule.getCurrTime() + 1000 * 60 * 12;
			  ICalendar.addNewCalendarEventAPI(startMillis, calendarTitle1,
						description, "UTC/GMT +2:00", true);

			  IContacts.startPhonebook();

				IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			  deleteContactsConcurrent.calendarExpired();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();

		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__email(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {
			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.receiveEmail();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Phonebook_Concurrnent_Events_While_Delete_Contact__FM_background(
			String casename, HashMap<String, String> paras) throws Exception {

		PrePostInvocationHandler deleteContacts = new PrePostInvocationHandler(concurrentevets,
				new ScenarioDeleteContacts(testCase));

		IConcurrentEvents deleteContactsConcurrent = (IConcurrentEvents) Proxy
				.newProxyInstance(concurrentevets.getClass().getClassLoader(),
						concurrentevets.getClass().getInterfaces(),
						deleteContacts);
		int contactNumbers = Integer.parseInt(paras.get("contactNumbers"));
		try {
			IContacts.startPhonebook();

			IContacts.importContacts("Internal storage", contactNumbers+"vCard.vcf");//contact vcard with "Contact number(50)" contacts
			deleteContactsConcurrent.FMRadioPlayInBackground();

		} catch (Exception ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (AssertionFailedError ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} catch (Error ex) {
			commonModule.takeScreenShot(casename);
			throw ex;
		} finally {
			commonModule.unLockScreen();
			commonModule.backOutToHomeScreen();
		}
	}

}
