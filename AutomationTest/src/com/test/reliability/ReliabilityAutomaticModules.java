
package com.test.reliability;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.concurrent.PostInvocationHandler;
import com.concurrent.PrePostInvocationHandler;
import com.concurrent.events.ConcurrentEvents;
import com.concurrent.events.IConcurrentEvents;
import com.concurrent.scenario.ScenarioBrowsingWebsite;
import com.concurrent.scenario.ScenarioCalculator;
import com.concurrent.scenario.ScenarioCameraRecordingVideo;
import com.concurrent.scenario.ScenarioEditReadMMS;
import com.concurrent.scenario.ScenarioEditReadSMS;
import com.concurrent.scenario.ScenarioExportContacts;
import com.concurrent.scenario.ScenarioImportContacts;
import com.concurrent.scenario.ScenarioLockScreen;
import com.concurrent.scenario.ScenarioOngingCall;
import com.concurrent.scenario.ScenarioPhoneRinging;
import com.concurrent.scenario.ScenarioPlayMusicAndVerifyBasicFunction;
import com.concurrent.scenario.ScenarioPlayingFMRadio;
import com.concurrent.scenario.ScenarioRecordingVideo4KVideo;
import com.concurrent.scenario.ScenarioRecordingVideo4KVideoSuperipr;
import com.concurrent.scenario.ScenarioRecordingVideoAREffect;
import com.concurrent.scenario.ScenarioRecordingVideoARFun;
import com.concurrent.scenario.ScenarioRecordingVideoCreativeEffect;
import com.concurrent.scenario.ScenarioRecordingVideoTimeshiftVideo;
import com.concurrent.scenario.ScenarioTakePictureBackgroundDefocus;
import com.concurrent.scenario.ScenarioVideoPlaying;
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
import com.module.browser.BrowserFactory;
import com.module.browser.IBrowser;
import com.module.common.CommonModule;
import com.module.email.AbstractEmailFactory;
import com.module.email.EmailModule;
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
import com.module.playStore.AbstractPlayStoreFactory;
import com.module.playStore.IPlayStore;
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

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.view.KeyEvent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract.CommonDataKinds.Email;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.reflect.Proxy;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class ReliabilityAutomaticModules {

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

			private IBrowser browser;

			private IPlayStore playStore;

    ConcurrentEvents concurrentevets;
    public String cameraFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/100ANDRO/";

    public String xperiaFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/XPERIA/";

    public static String TAG = "Reliability";

    public String testloops;

    public int runFlag;

    public boolean isCatch;

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

    public ReliabilityAutomaticModules(AcceptanceTestCase testCase)
            throws PropertyNotFoundException, UiObjectNotFoundException, IOException, XmlPullParserException {
        ModuleDataParser dataParser = new ModuleDataParser();
        dataParser.parse();

        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
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
        officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
        browser = BrowserFactory.create(testCase);
        playStore = ((AbstractPlayStoreFactory)GroupFactories.createFactory(testCase, "playstore"))
                .create();


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
        selNumber = TestDataExtract.selNumber;

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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 735922 RELA_Camera_Take
     * many videos
     */
    @SuppressWarnings("static-access")
    public void Make_Long_Call_With_Loudspeaker(String casename, HashMap<String, String> paras)
            throws Exception {
        try {

            telephonyModule.makeMOCallAndAnswered(callNumber);

            telephonyModule.tapLouderSpeakerIcon();

            // call during and verify
            int callDuring = Integer.valueOf(paras.get("durations"));

            telephonyModule.verifyCallOngoingWithinSpecifiedTime(callDuring);

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
            System.out.println("in finally");
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }

    }

    public void Make_Long_Time_Conference_Call(String casename, HashMap<String, String> paras)
            throws UiObjectNotFoundException {

        try {
            telephonyModule.setCallWaiting("on");

            int callDuring = Integer.valueOf(paras.get("durations"));

            telephonyModule.makeConferenceCall(callNumber, callNumber2);

            telephonyModule.verifyCallOngoingWithinSpecifiedTime(callDuring);

            telephonyModule.endCall();

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            System.out.println("in finally");
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            telephonyModule.setCallWaiting("off");
            commonModule.backOutToHomeScreen();
        }
    }


    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1348858 RELA_Camera_Take
     * long videos
     */
    @SuppressWarnings("static-access")
    public void Camera_Take_Long_Videos(String casename, HashMap<String, String> paras)
            throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        String durations = paras.get("durations");
        int duration = Integer.parseInt(durations);

        try {

            Log.i(TAG, "Take camera video with superior auto");
            camera.launchCameraPhotoApplication();
            camera.takePictures(1);
            commonModule.wait(3);
            camera.recordVideo(duration);
            commonModule.backOutToHomeScreen();

            camera.selectAllAppsAndRecordVideos(duration);

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            commonModule.clearData("Camera");

        }

    }

    public void RELIA_Messaging_SMS_Send_Receive_Forword_Delete_Multiple_SMS(String casename,
            HashMap<String, String> paras) throws Exception {

        try {
            String RAMStatus = paras.get("RAMStatus");
            int sendSMSNum = Integer.parseInt(paras.get("sendSMSNum"));
            int receiveSMSNum = Integer.parseInt(paras.get("receiveSMSNum"));
            int forwordSMSNum = Integer.parseInt(paras.get("forwordSMSNum"));
            int deleteSMSNum = Integer.parseInt(paras.get("deleteSMSNum"));

            IMessaging.startMessagingFromMenu();
            if (!IMessaging.isMessageListEmpty()) {
                IMessaging.removeAllMessages();
            } else {
                Log.v(TAG, "There is no message in message folder");
            }

            IMessaging.sendMultiSMSToOneNumber(CommandConstantsUtils.SMS_CONTENT_LONG_OUT,
                    callNumber, sendSMSNum);
            commonModule.backOutToHomeScreen();

            for (int i = 0; i < receiveSMSNum; i++) {
                // Receive sms, and the phone number need set to partner phone
                // number.
                IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_SMS, callNumber);
                IMessaging.openReceiveMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
                commonModule.backOutToHomeScreen();
            }

            IMessaging.startMessagingFromMenu();

            for (int i = 0; i < forwordSMSNum; i++) {
                IMessaging
                        .forwordSMSMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE, callNumber2);
            }

            for (int i = 0; i < deleteSMSNum; i++) {
                IMessaging.deleteMessageWithText(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
                commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            }

            IMessaging.removeAllMessages();

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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344169
     * RELIA_Messaging_MMS_Send and receive multiple MMS-Parameter
     */
    public void RELIA_Messaging_MMS_Send_Receive_Forword_Delete_Multiple_MMS(String casename,
            HashMap<String, String> paras) throws Exception {

        try {
            String RAMStatus = paras.get("RAMStatus");
            int sendMMSNum = Integer.parseInt(paras.get("sendMMSNum"));
            int receiveMMSNum = Integer.parseInt(paras.get("receiveMMSNum"));
            int forwordMMSNum = Integer.parseInt(paras.get("forwordMMSNum"));
            int deleteMMSNum = Integer.parseInt(paras.get("deleteMMSNum"));

            camera.launchCameraPhotoApplication();
            camera.takePictures(1);

            IMessaging.startMessagingFromMenu();
            if (!IMessaging.isMessageListEmpty()) {
                IMessaging.removeAllMessages();
            } else {
                Log.v(TAG, "There is no message in message folder");
            }

            for (int i = 0; i < sendMMSNum; i++) {
                IMessaging.sendMMSWithImage(CommandConstantsUtils.SMS_CONTENT_LONG_OUT, callNumber, true);
            }

            commonModule.backOutToHomeScreen();

            for (int i = 0; i < receiveMMSNum; i++) {
                // Receive sms, and the phone number need set to partner phone
                // number.
                IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, callNumber);
                commonModule.wait(120);
                Assert.assertTrue("Receive mms timeout.",
                        IMessaging.checkReceiveSMS(callNumber, 280 * 1000));
            }

            IMessaging.startMessagingFromMenu();

            for (int i = 0; i < forwordMMSNum; i++) {
                IMessaging
                        .forwordMMSMessage(CommandConstantsUtils.MMS_CONTENT_RECEIVE, callNumber2);
            }

            for (int i = 0; i < deleteMMSNum; i++) {
                IMessaging.deleteMMSWithText(CommandConstantsUtils.MMS_CONTENT_RECEIVE);
                commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            }

            IMessaging.removeAllMessages();

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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344170
     * RELIA_Messageing_Send and receive messages under heavy load-Parameter
     */
    public void RELIA_Messaging_Send_Receive_Message_Under_Heavy_Load(String casename,
            HashMap<String, String> paras) throws Exception {

        try {
            String RAMStatus = paras.get("RAMStatus");

            IMessaging.startMessagingFromMenu();
            if (!IMessaging.isMessageListEmpty()) {
                IMessaging.removeAllMessages();
            } else {
                Log.v(TAG, "There is no message in message folder");
            }
            // Send sms under this <<RAM Status>>
            IMessaging.sendSMS(CommandConstantsUtils.SMS_CONTENT_LONG_OUT, callNumber);

            // Send sms under this <<RAM Status>>
            IMessaging.sendSMS(CommandConstantsUtils.SMS_CONTENT_LONG_OUT, callNumber);

            commonModule.backOutToHomeScreen();

            // Receive MMS under this <<RAM Status>>
            IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, callNumber);
            commonModule.wait(120);
            Assert.assertTrue("Receive mms timeout .",
                    IMessaging.checkReceiveSMS(callNumber, 280 * 1000));

            // Receive MMS under this <<RAM Status>>
            IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, callNumber);
            commonModule.wait(120);
            Assert.assertTrue("Receive mms timeout.",
                    IMessaging.checkReceiveSMS(callNumber, 280 * 1000));

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

    /* Case in Sony ALM: Domain: PSV Project: PSV ID: 1325563 RELIA_Camera_Take
     * multiple pictures with creative effort-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Picture_With_Creative_Effort(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int times = Integer.parseInt(paras.get("times"));
        try {
            commonModule.backOutToHomeScreen();

            Log.i(TAG, "Launch camera");
            camera.launchCameraPhotoApplication();
            commonModule.wait(3);

            for (int j = 0; j < times; j++) {
                camera.selectOneEffectFromCameraSettings("Creative effect");

                camera.selectAllCreativeEffectAndTakePictures();

                Log.i(TAG, "Change Preview mode");
                camera.selectAllPreviewModeAndTakePictures();
            }

            Log.i(TAG, "Check the pictures");
            camera.checkPictures(times * 24);

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
            commonModule.backOutToHomeScreen();
            commonModule.clearData("Creative effect");
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1335160 RELA_Camera_Take
     * many videos
     */
    @SuppressWarnings("static-access")
    public void Camera_Take_Multiple_Video_Clips_With_Timeshift_Video_Mode(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int cameraCount = Camera.getNumberOfCameras();
        int recordingTime = 10;
        boolean switchFlag = false;
        try {
            camera.launchCameraByMode("Timeshift video");
            for (int i = 0; i < Integer.parseInt(paras.get("times")); i++) {
                Log.d(TAG, "Recording Timeshift video mode for " + (i + 1) + " times");
                camera.switchMicrophoneOnTimeshiftMode();
                camera.recordVideoOnTimeshiftMode(recordingTime);
                commonModule.wait(1);
                commonModule.pressKeyForMultipleTimes(KeyEvent.KEYCODE_BACK, 1);
                commonModule.wait(3);

            }
            commonModule.backOutToHomeScreen();

            Log.d(TAG, "Take and view a picture via camera");
            camera.launchCameraPhotoApplication();
            camera.takePictures(1);
            camera.openLatestPictureInCamera();

            commonModule.wait(1);
            commonModule.pressKeyForMultipleTimes(KeyEvent.KEYCODE_BACK, 1);
            commonModule.wait(3);

            Log.d(TAG, "Take and view a video via camera");
            camera.recordVideo(recordingTime);
            camera.openLatestVideoInCamera();
            commonModule.wait(recordingTime);

            if (cameraCount == 2) {
                commonModule.backOutToHomeScreen();
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                commonModule.wait(3);
                camera.switchCamera();
                switchFlag = true;
                Log.i(TAG, "Take camera video with front camera");
                commonModule.wait(3);
                camera.takePictures(1);
            }

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            if (switchFlag) {
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                camera.switchCamera();
            }
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            commonModule.delete(xperiaFilePath);

        }

    }

    @SuppressWarnings("static-access")
    public void Camera_Take_Multiple_Video_Clips_With_Superior_Auto_Mode(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int cameraCount = Camera.getNumberOfCameras();
        boolean switchFlag = false;

        int recordingTime = 10;
        try {
            camera.launchCameraByMode("Superior auto");
            for (int i = 0; i < Integer.parseInt(paras.get("times")); i++) {
                Log.d(TAG, "Recording superior auto mode for " + (i + 1) + " times");
                commonModule.wait(3);
                camera.recordVideo(recordingTime);

            }
            Log.d(TAG, "Take and view a picture via camera");
            camera.takePictures(1);
            camera.openLatestPictureInCamera();

            commonModule.wait(1);
            commonModule.pressKeyForMultipleTimes(KeyEvent.KEYCODE_BACK, 1);
            commonModule.wait(3);

            Log.d(TAG, "Take and view a video via camera");
            camera.recordVideo(recordingTime);
            camera.openLatestVideoInCamera();
            commonModule.wait(recordingTime);

            if (cameraCount == 2) {
                commonModule.backOutToHomeScreen();
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                commonModule.wait(3);
                camera.switchCamera();
                switchFlag = true;
                Log.i(TAG, "Take camera video with front camera");
                commonModule.wait(3);
                camera.takePictures(1);
            }

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            if (switchFlag) {
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                camera.switchCamera();
            }
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            commonModule.delete(cameraFilePath);

        }

    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1325564 RELIA_Camera_Take
     * multiple pictures with AR effort-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Picture_With_AR_Effort(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int times = Integer.parseInt(paras.get("times"));
        commonModule.backOutToHomeScreen();

        try {

            for (int i = 0; i < times; i++) {
                Log.i(TAG, "Launch camera");
                camera.launchCameraPhotoApplication();
                commonModule.wait(3);

                camera.selectOneEffectFromCameraSettings("AR effect");
                int count = camera.selectAllAREffectAndTakePictures();

                Log.i(TAG, "Check the pictures");
                camera.checkPictures(count);
                commonModule.backOutToHomeScreen();
            }

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
            commonModule.backOutToHomeScreen();
            commonModule.delete(xperiaFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1325568 RELIA_Camera_Take
     * multiple pictures with Background defocus-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Pictures_With_Background_Defocus(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int times = Integer.parseInt(paras.get("times"));
        commonModule.backOutToHomeScreen();

        try {
            Log.i(TAG, "Launch camera");
            camera.launchCameraPhotoApplication();
            commonModule.wait(3);

            camera.selectOneEffectFromCameraSettings("Background defocus");
            for (int i = 0; i < times; i++) {
                camera.takePicturesWithBackgroundDefocusMode();
            }

            Log.i(TAG, "Check the pictures");
            camera.checkPictures(times);

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
            commonModule.backOutToHomeScreen();
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1335262 RELIA_Camera_Take
     * multiple pictures with superior auto mode-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Pictures_With_Supserior_Auto_Mode(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        try {
            int times = Integer.parseInt(paras.get("times"));
            commonModule.backOutToHomeScreen();

            Log.i(TAG, "Launch camera");
            camera.launchCameraPhotoApplication();
            commonModule.wait(3);

            camera.selectOneEffectFromCameraSettings("Superior auto");

            for (int i = 0; i < times; i++) {
                camera.takePictures(1);
            }

            Log.i(TAG, "Check the pictures");
            camera.checkPictures(times);

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
            commonModule.backOutToHomeScreen();
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1336665 RELIA_Camera_Take
     * multiple pictures with Geo tag-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Pictures_With_Geo_Tag(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        int times = Integer.parseInt(paras.get("times"));
        commonModule.backOutToHomeScreen();

        try {
            Log.i(TAG, "Set location service.");
            commonModule.activeGPSServiceFromSettings();

            Log.i(TAG, "Set Geo tag.");
            camera.activeGeoTagFromCameraSettings();

            for (int i = 0; i < times; i++) {
                camera.takePictures(1);
                commonModule.wait(2);
            }

            Log.i(TAG, "Check the pictures");
            camera.checkPictures(times);

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
            commonModule.backOutToHomeScreen();
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    @SuppressWarnings("static-access")
    public void Camera_HDR_Take_Pictures_Under_Very_Light_And_Very_Dark_Areas(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        try {
            camera.launchCameraByMode("Manual");
            camera.openHDR();
            Log.w(TAG,
                    "---------------------Put DUT in a very light areas! Wait 1 mins----------------------------");
            commonModule.wait(5);
            for (int i = 0; i < Integer.parseInt(paras.get("photos")); i++) {
                Log.d(TAG, "Take pictures in light area for " + (i + 1) + " times");
                camera.takePictures(1);
                commonModule.wait(3);

            }
            Log.w(TAG,
                    "---------------------Put DUT in a very dark areas! Wait 1 mins----------------------------");
            commonModule.wait(5);
            for (int i = 0; i < Integer.parseInt(paras.get("photos")); i++) {
                Log.d(TAG, "Take pictures in dark area for " + (i + 1) + " times");
                camera.takePictures(1);
                commonModule.wait(3);

            }
        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            camera.launchCameraByMode("Superior auto");
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();

        }

    }

    @SuppressWarnings("static-access")
    public void Camera_Take_Multiple_4K2K_Pictures(String casename, HashMap<String, String> paras)
            throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        boolean switchFlag = false;
        int cameraCount = Camera.getNumberOfCameras();
        try {
            camera.launchCameraByMode("Manual");
            camera.open4K2KPictureMode();
            commonModule.wait(5);
            for (int i = 0; i < Integer.parseInt(paras.get("photos")); i++) {
                Log.d(TAG, "Take pictures in light area for " + (i + 1) + " times");
                camera.takePictures(1);
                commonModule.wait(3);

            }
            if (cameraCount == 2) {
                commonModule.backOutToHomeScreen();
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                commonModule.wait(3);
                camera.switchCamera();
                switchFlag = true;
                Log.i(TAG, "Take camera video with front camera");
                commonModule.wait(3);
                camera.takePictures(1);
            }
        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            if (switchFlag) {
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                camera.switchCamera();
            }
            commonModule.backOutToHomeScreen();
            camera.launchCameraByMode("Superior auto");
            commonModule.wait(3);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
//            commonModule.delete(cameraFilePath);

        }

    }

    @SuppressWarnings("static-access")
    public void Camera_Take_Multiple_4K2K_Video_Clips(String casename, HashMap<String, String> paras)
            throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        boolean switchFlag = false;
        int cameraCount = Camera.getNumberOfCameras();
        Random random = new Random();
        try {
            camera.launchCameraByMode("4K video");
            commonModule.wait(3);
            for (int i = 0; i < Integer.parseInt(paras.get("times")); i++) {
                int j = random.nextInt(2);
                int k = random.nextInt(3);
                commonModule.disappearAlertNote();
                Log.d(TAG, "Take 4k video for " + (i + 1) + " times");
                camera.change4KVideoSetting(j, k);
                commonModule.wait(3);
                camera.recordVideoOn4KMode(5);
                commonModule.wait(2);

            }
            if (cameraCount == 2) {
                commonModule.backOutToHomeScreen();
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                commonModule.wait(3);
                camera.switchCamera();
                switchFlag = true;
                Log.i(TAG, "Take camera video with front camera");
                commonModule.wait(3);
                camera.takePictures(1);
            }
        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (AssertionFailedError e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } catch (Error e) {
            commonModule.takeScreenShot(casename);
            throw e;

        } finally {
            if (switchFlag) {
                camera.launchCameraPhotoApplication();
                Log.i(TAG, "Switch camera");
                camera.switchCamera();
            }
            commonModule.wait(3);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
//            commonModule.delete(cameraFilePath);

        }

    }

    public void Make_Long_Time_Calls_in_Various_Network_Mode(String casename,
            HashMap<String, String> paras) {
        try {

            String networkmode = paras.get("networkmode");

            int during = Integer.valueOf(paras.get("durations"));

            if (networkmode.contains(",")) {
                // test multi network mode
                String[] modes = networkmode.split(",");

                for (int i = 0; i < modes.length; i++) {
                    telephonyModule.changeNetworkMode(modes[i]);

                    // MO call
                    telephonyModule.makeMOCallAndAnswered(callNumber);

                    telephonyModule.verifyCallOngoingWithinSpecifiedTime(during);

                    telephonyModule.endCall();

                    // MT call

                    telephonyModule.answerMTCall(callNumber);

                    telephonyModule.verifyCallOngoingWithinSpecifiedTime(during);

                    telephonyModule.endCall();

                }
            } else {
                telephonyModule.changeNetworkMode(networkmode);

                // MO call
                telephonyModule.makeMOCallAndAnswered(callNumber);

                telephonyModule.verifyCallOngoingWithinSpecifiedTime(during);

                telephonyModule.endCall();

                // MT call

                telephonyModule.answerMTCall(callNumber);

                telephonyModule.verifyCallOngoingWithinSpecifiedTime(during);

                telephonyModule.endCall();
            }
        } catch (Exception e) {
            commonModule.takeScreenShot(casename);

        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            System.out.println("in finally");
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();

            telephonyModule.changeNetworkMode("4g prefer");
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1344149
     * RELIA_Telephony_Long time call with PHF-Parameter
     *
     * @throws Exception
     */
    public void Make_Long_Time_Call_With_PHF_Parameter(String casename,
            HashMap<String, String> paras) {
        try {
            int duration = Integer.valueOf(paras.get("durations"));

            telephonyModule.makeMOCallAndAnswered(callNumber);

            telephonyModule.verifyCallOngoingWithinSpecifiedTime(duration);

            telephonyModule.endCall();

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            System.out.println("in finally");
            commonModule.backOutToHomeScreen();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1348081 RELIA_Camera_Take
     * multiple pictures with different scenes-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Pictures_With_Different_Scenes(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        int times = Integer.parseInt(paras.get("times"));
        commonModule.backOutToHomeScreen();

        try {
            for (int i = 0; i < times; i++) {
                commonModule.clearData("Camera");

                Log.i(TAG, "Launch camera");
                camera.launchCameraPhotoApplication();
                commonModule.wait(3);

                camera.selectOneEffectFromCameraSettings("Manual");

                camera.selectAllScenesAndTakePictures();
                // for (int j = 0; j < 18; j++) {
                // if (j == 0) {
                // camera.changeScenesByCoordinate(390);
                // } else if (j == 1) {
                // camera.changeScenesByCoordinate(560);
                // } else if (j == 15) {
                // camera.changeScenesByCoordinate(900);
                // } else if (j == 16) {
                // camera.changeScenesByCoordinate(1070);
                // } else if (j == 17) {
                // camera.changeScenesByCoordinate(1240);
                // } else {
                // camera.changeScenesByCoordinate(730);
                // }
                // camera.takePictures(1);
                // }
            }

            Log.i(TAG, "Check the pictures");
            camera.checkPictures(times * 18);

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
            commonModule.backOutToHomeScreen();
            commonModule.clearData("Camera");
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1325564 RELIA_Camera_Take
     * multiple video clips with AR effort-Parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Camera_Take_Multiple_Video_Clips_With_AR_Effort(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        int times = Integer.parseInt(paras.get("times"));
        int recordingTime = 5;
        commonModule.backOutToHomeScreen();

        try {

            for (int i = 0; i < times; i++) {
                commonModule.backOutToHomeScreen();

                Log.i(TAG, "Launch camera");
                camera.launchCameraPhotoApplication();
                commonModule.wait(3);

                camera.selectOneEffectFromCameraSettings("AR effect");
                int count = camera.selectAllAREffectAndRecordVideos(recordingTime);

                Log.i(TAG, "Check the videos");
//                camera.checkVideos(count);
            }

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
            commonModule.backOutToHomeScreen();
            commonModule.delete(xperiaFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    public void Make_MO_and_MT_Call_Uner_Various_RAM_Status(String casename,
            HashMap<String, String> paras) throws UiObjectNotFoundException {
        try {
            // MO Call
            telephonyModule.makeMOCallAndAnswered(callNumber);
            commonModule.wait(10);
            telephonyModule.endCall();

            // MT Call
            telephonyModule.answerMTCall(callNumber);
            commonModule.wait(10);
            telephonyModule.endCall();

        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.backOutToHomeScreen();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1325326
     * RELIA_Telephony_Repeat to multiple many calls in various network
     * mode-Parameters
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Telephony_Repeat_To_Multiple_Many_Calls_In_Various_Network_Mode(
            String casename, HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        try {

            int times = Integer.parseInt(paras.get("times"));
            String networkmode = paras.get("networkmode");
            commonModule.backOutToHomeScreen();

            if (networkmode.contains(",")) {
                // test multi network mode
                String[] modes = networkmode.split(",");

                for (int i = 0; i < modes.length; i++) {
                    telephonyModule.changeNetworkMode(modes[i]);

                    // MO call
                    for (int j = 0; j < times; j++) {
                        telephonyModule.makeMOCallAndAnswered(callNumber);

                        telephonyModule.tapLouderSpeakerIcon();

                        telephonyModule.endCall();
                    }

                    // MT call
                    for (int k = 0; k < times; k++) {
                        telephonyModule.answerMTCall(callNumber);

                        telephonyModule.tapLouderSpeakerIcon();

                        telephonyModule.endCall();
                    }

                }
            } else {
                telephonyModule.changeNetworkMode(networkmode);

                // MO call
                for (int j = 0; j < times; j++) {
                    telephonyModule.makeMOCallAndAnswered(callNumber);

                    telephonyModule.tapLouderSpeakerIcon();

                    telephonyModule.endCall();
                }

                // MT call
                for (int k = 0; k < times; k++) {
                    telephonyModule.answerMTCall(callNumber);

                    telephonyModule.tapLouderSpeakerIcon();

                    telephonyModule.endCall();
                }
            }
        } catch (Exception e) {
            commonModule.takeScreenShot(casename);

        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            System.out.println("in finally");
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();

            telephonyModule.changeNetworkMode("4g prefer");
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1335048
     * RELIA_Telephony_Long time call with BTH-Parameters
     *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public void RELIA_Telephony_Long_Time_Call_With_BTH(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        int period = Integer.parseInt(paras.get("durations"));
        commonModule.backOutToHomeScreen();

        try {
            commonModule.bluetoothOnOrOff("ON");

            // MO call

            telephonyModule.makeMOCallAndAnswered(callNumber);

            telephonyModule.verifyCallThroughBluetoothDevice(period);

            telephonyModule.endCall();

        } catch (Exception e) {
            commonModule.takeScreenShot(casename);

        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            System.out.println("in finally");
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            commonModule.bluetoothOnOrOff("OFF");
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

	    @SuppressWarnings("static-access")
    public void RELIA_Telephony_Long_Time_VOIP_Call(String casename,
            HashMap<String, String> paras) throws Exception {

        Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);

        commonModule.backOutToHomeScreen();
        int duration = Integer.valueOf(paras.get("durations"));
        try {
            commonModule.connect2Wifi(test_wifi);
            telephonyModule.launchSkype();
            telephonyModule.verifySkypeLaunched(20);
            telephonyModule.loginSkype(test_skypeaccount, test_skypepassword);
            telephonyModule.makeSkypeCallAndAnswered(test_partnerskypeaccount);
            commonModule.wait(duration);
//        			long currenttime = System.currentTimeMillis();
//            long browsingtime = currenttime +duration*1000L;
//            while(System.currentTimeMillis()< browsingtime){
//        					telephonyModule.verifySkypeInCallStatus();
//        					commonModule.wait(10);
//        		}
            telephonyModule.endSkypeCall();


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
            commonModule.backOutToHomeScreen();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
            Log.d(TAG, "<<<<<<<<<<<<<<<<<<" + casename + "|" + basicUtils.sCurrLoop);
        }
    }

    public void RELIA_Telephony_Long_Time_SIP_Call(String casename,
            HashMap<String, String> paras) throws Exception {

        int durations = Integer.valueOf(paras.get("durations"));
        String callSetting[] = {"Use SIP with network access", "Only to SIP address"};

        try {
        	commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SIP_INTERNET_ADDRESS);
            commonModule.connect2Wifi(test_wifi);
        	telephonyModule.configSipSetting("on");
        	telephonyModule.addSipAccount(test_sipaccount, test_sippassword,"192.168.65.6");

        	telephonyModule.makeMOCallAndAnswered(partner_sipaccount);
        	telephonyModule.verifyCallOngoingWithinSpecifiedTime(durations);
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
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SIP_ADDRESS);
            telephonyModule.configSipSetting("off");
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }
    public void RELIA_Video_Player_Play_Large_Size_Video_File(String casename,
            HashMap<String, String> paras) throws Exception {
    	String videoName = String.valueOf(paras.get("videoname"));

        try {

        	IMedia.launchVideoPlayer();
        	IMedia.playVideo(videoName);
        	int i = 0;
        	while(i<50){
        	IMedia.fastForwardAndRewind(videoName);
        	i++;
        	}

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

    public void RELIA_MM_System_Capability_For_Video_Playing(String casename,
            HashMap<String, String> paras) throws Exception {
    	String videoName = String.valueOf(paras.get("videoname"));
    	int repeat = Integer.valueOf(paras.get("repeattime"));

        try {

        	IMedia.launchVideoPlayer();

        	int i = 0;
        	while(i<repeat){
        	IMedia.playVideo(videoName);
        	IMedia.waitVideoPlaying();
        	IMedia.verifyVideoPlayerState();
        	i++;
        	}

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

    public void RELIA_Album_View_Pictures_Under_Various_RAM_Status(String casename,
            HashMap<String, String> paras) throws Exception {
    			 String RAMStatus = paras.get("RAMStatus");

        try {

        	IAlbum.startAlbum();

        	IAlbum.viewPictureInAlbum(20);

        	commonModule.backOutToHomeScreen();

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

    public void RELIA_Album_Repeat_To_Play_delete_Images_For_Many_Times(String casename,
            HashMap<String, String> paras) throws Exception {
    			 int times = Integer.valueOf(paras.get("times"));

        try {

        	for(int i=0;i<times;i++){
		        	IAlbum.startAlbum();

		        	IAlbum.deletePictureInAlbum();

		        	commonModule.backOutToHomeScreen();
        	}

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

    public void RELIA_MM_System_Capability_For_Playing_Streaming_Videos(String casename,
            HashMap<String, String> paras) throws Exception {

    			 String videoFolder = paras.get("streamingvideo");
        try {
        			commonModule.backOutToHomeScreen();
        			IMedia.launchVideoPlayerOneByOne(videoFolder);


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

    public void RELIA_MM_System_Capability_For_Playing_DRM_Videos(String casename,
            HashMap<String, String> paras) throws Exception {

    			 String videoFolder = paras.get("DRMvideo");
        try {
        			commonModule.backOutToHomeScreen();
        			IMedia.launchVideoPlayerOneByOne(videoFolder);


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

    public void RELIA_Music_Player_Playback_Large_Music_File(String casename,
            HashMap<String, String> paras) throws Exception {
    			String musicName = paras.get("musicName");
        try {
        			commonModule.backOutToHomeScreen();
        			IWalkman.playMusic(musicName);
        			commonModule.wait(3600);
        			IWalkman.playNextMusic();
        			IWalkman.verifyMusicPlaying();
        			IWalkman.stopMusicFromStatusBar();

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

    public void RELIA_Music_Player_Long_Time_Music_Play_Under_Different_Mode(String casename,
            HashMap<String, String> paras) throws Exception {
    			String playMode = paras.get("play_mode");


        try {
								commonModule.backOutToHomeScreen();
								IWalkman.playMusic();
								if (playMode.contains(",")) {
									// test multi play mode
									String[] modes = playMode.split(",");

									for (int i = 0; i < modes.length; i++) {
										Log.i(TAG,"Switch mode to "+modes[i]);
										IWalkman.setPlayMode(modes[i]);
										Log.i(TAG,"Switch mode to "+modes[i]+" done");
										commonModule.wait(10);
									}
								} else {
									IWalkman.setPlayMode(playMode);
									commonModule.wait(10);
								}
								commonModule.backOutToHomeScreen();

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

    public void RELIA_Music_Player_Long_Time_Play_Different_Format_Files(String casename,
            HashMap<String, String> paras) throws Exception {
    			int duration = Integer.valueOf(paras.get("duration"));
    			String music = paras.get("music_list");


        try {
								commonModule.backOutToHomeScreen();
								long currenttime = System.currentTimeMillis();
								long browsingtime = currenttime +duration*1000L;

								for(int i=0;i<1000;i++){
            		IWalkman.launchMusicUrl(music);
		            if(System.currentTimeMillis()> browsingtime){
		            	break;
		    			            	}
                     }
								commonModule.backOutToHomeScreen();

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

    public void RELIA_TrackID_Search_Songs_Under_Different_RAM_Status(String casename,
            HashMap<String, String> paras) throws Exception {
        try {
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_PLAY_MUSIC);
            commonModule.wait(30);
            IMedia.launchTrackID();
            IMedia.searchSongs("Mambo Italiano");

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

    public void RELIA_MM_Play_Different_Format_Video_For_Multiple_Times(String casename,
            HashMap<String, String> paras) throws Exception {

							 String videoFolder = paras.get("videoFolder");
							 int repeat = Integer.valueOf(paras.get("times"));
		    		        try {
		    		        	for(int i=0;i<repeat;i++){
		    		        			commonModule.backOutToHomeScreen();
		    		        			IMedia.launchVideoPlayerOneByOne(videoFolder);
			        	}

			        	commonModule.backOutToHomeScreen();
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

    public void RELIA_MM_System_Capability_For_Operate_Video_Playing(String casename,
            HashMap<String, String> paras) throws Exception {
		    	String videoName = paras.get("videoName");
				 int repeat = Integer.valueOf(paras.get("repeatTime"));
					try {
			        	IMedia.launchVideoPlayer();
			        	IMedia.playVideo(videoName);
									for(int i=0;i<repeat;i++){
						        	IMedia.fastForwardAndRewind(videoName);

						        	}
									commonModule.backOutToHomeScreen();

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

    public void RELIA_Music_Player_Long_Time_Play_DRM_Files(String casename,
            HashMap<String, String> paras) throws Exception {

			   int times = Integer.valueOf(paras.get("times"));
					String music = paras.get("music_list");
					try {
						for(int i=0;i<times;i++){
								commonModule.backOutToHomeScreen();

								String dir = Environment.getExternalStorageDirectory().getPath() + "/"
										+ music + "/";
								Log.i(TAG, "Play music: "+dir);
								File testFolder = new File(dir);
								String[] testMusic = testFolder.list();
								Log.i(TAG, "Play music: " + testMusic.length);
								if(testMusic.length>0){
									for (int j = 0; j < testMusic.length; j++) {
										IWalkman.playMusicURL(dir,testMusic[j]);
										commonModule.wait(3);
										IWalkman.verifyMusicPlaying();
										IWalkman.waitPlayingMusic();
								}
								}else{
									AcceptanceTestCase.assertTrue("The given music folder is empty, please help to check", false);
								}

								commonModule.backOutToHomeScreen();
						}

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

    public void RELIA_Phonebook_Add_New_Contact_When_Call(String casename,
            HashMap<String, String> paras) throws Exception {
    			int times = Integer.valueOf(paras.get("times"));
					try {
								for(int i=0;i<times;i++){
											commonModule.backOutToHomeScreen();
											IMessaging.remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_CALL, callNumber);
											telephonyModule.answerIncomingCall();
											telephonyModule.verifyCallAnswered();
											commonModule.backOutToHomeScreen();

											IContacts.addContacts(1);
											telephonyModule.launchPhone();
											telephonyModule.verifyCallAnswered();
											telephonyModule.endCall();
								}
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

    public void RELIA_Phonebook_Export_Contacts_To_Different_Storage(String casename,
            HashMap<String, String> paras) throws Exception {
		    	int contactNumber = Integer.valueOf(paras.get("contactNumber"));
		    	String storage = paras.get("storage");
					try {
						for(int i=0;i<contactNumber;i++){
							IContacts.createContactsURI("Lee"+i, "1569900"+i,"asdfasfad@163.com");
						}
						if (storage.contains(",")) {
									// export storage
									String[] storages = storage.split(",");
									for(int j=0;j<storages.length;j++){

											IContacts.startPhonebook();
											IContacts.exportContacts(storages[j]);
											commonModule.backOutToHomeScreen();
									}

						}else{
										IContacts.startPhonebook();
										IContacts.exportContacts(storage);
										commonModule.backOutToHomeScreen();
						}

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

    public void RELIA_Phonebook_Import_Contacts_From_Different_Storage_Under_Various_RAM_Status(
            String casename, HashMap<String, String> paras) throws Exception {
        String storage = paras.get("storage");
        try {

            if (storage.contains(",")) {
                // import storage
                String[] storages = storage.split(",");
                for (int i = 0; i < storages.length; i++) {
                    IContacts.startPhonebook();
                    IContacts.importContacts(storages[i], "PIM00001.vcf");
                    commonModule.backOutToHomeScreen();
                }

            } else {
                IContacts.startPhonebook();
                IContacts.importContacts(storage, "PIM00001.vcf");
                commonModule.backOutToHomeScreen();
            }

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

    public void RELIA_Phonebook_Multiple_Times_Settings(String casename, HashMap<String, String> paras) throws Exception {

						try {
							IContacts.startPhonebook();
							IContacts.addAccount(test_email2account, test_email2password);
							IContacts.importContacts("Internal storage", "PIM00001.vcf");
							IContacts.exportContacts("Internal storage");
							IContacts.filterContact();
							IContacts.findLinkCandidates();
							IContacts.markSeveral(callNumber);
							commonModule.backOutToHomeScreen();


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

    public void RELIA_Email_Send_Receive_Different_Protocol_Emails(String casename,
            HashMap<String, String> paras) throws Exception {

        String subject = "Reliability email test";
        String content = "This email is for reliability test!";

        try {
            // EAS
            IEmail.launchEmail();
            IEmail.sendEmail(test_EASaccount, test_serveremail, subject, content, true);

            // IMAP
            IEmail.launchEmail();
            IEmail.sendEmail(test_gmailaccount, test_serveremail, subject, content, true);

            // POP3
            IEmail.launchEmail();
            IEmail.sendEmail(test_emailaccount, test_serveremail, subject, content, true);

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

    public void RELIA_Camera_Record_Long_Time_Video_Under_Creative_Effect_Mode(String casename, HashMap<String, String> paras) throws Exception {
    			int duration = Integer.valueOf(paras.get("duration"));
					try {
            commonModule.backOutToHomeScreen();

            Log.i(TAG, "Launch camera");
            camera.launchCameraPhotoApplication();
            commonModule.wait(3);
            camera.selectOneEffectFromCameraSettings("Creative effect");
            camera.takeCreateEffectVideo();

            camera.repeatChangeEffectAndTakePicture(duration *1000);

            camera.stopCreateEffectVideoRecording();

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

    public void RELIA_Camera_Take_Multiple_Pictures_With_Face_In_Camera_Mode(String casename, HashMap<String, String> paras) throws Exception {
					int times = Integer.valueOf(paras.get("times"));

						try {
	            commonModule.backOutToHomeScreen();

	            Log.i(TAG, "Launch camera");
	            camera.launchCameraPhotoApplication();
	            commonModule.wait(3);
              camera.selectOneEffectFromCameraSettings("Face in");


	            for (int i = 0; i < times/5; i++) {
	                Log.i(TAG, "Change Preview mode");
	                camera.selectAllPreviewModeAndTakePictures();
					            }

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

    public void RELIA_Camera_Take_Multiple_Videos_With_Face_In_Camera_Mode(String casename, HashMap<String, String> paras) throws Exception {
					int times = Integer.valueOf(paras.get("times"));
						String[] previewMode = {"Edit","On","Off"};
						int number = new Random().nextInt(3);
						try {

						    for (int i = 0; i < times; i++) {
									    commonModule.backOutToHomeScreen();

									    Log.i(TAG, "Launch camera");
									    camera.launchCameraPhotoApplication();
									    commonModule.wait(3);
									    camera.selectOneEffectFromCameraSettings("Face in");
									    Log.i(TAG, "Change Preview mode");
									    camera.changePreviewMode(previewMode[number],true);
									    camera.switchMicrophone();

						        camera.recordVideoFaceIn(120);

								            }
						    		commonModule.backOutToHomeScreen();
					        camera.checkVideos(times);


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


    public void RELIA_Album_View_Pictures_Of_Different_Format_Durability_Test(String casename, HashMap<String, String> paras) throws Exception {
						int duration = Integer.valueOf(paras.get("duration"));
							try {
							commonModule.backOutToHomeScreen();
								albumModule.startAlbum();
								albumModule.viewPictureInAlbum(0);
								long startTime = System.currentTimeMillis();
				    while (System.currentTimeMillis() - startTime < duration * 1000) {
											albumModule.scanPicture(1);

				    }
				    commonModule.backOutToHomeScreen();
								albumModule.startAlbum();
								albumModule.viewPictureInAlbum(0);
								albumModule.viewSlideshow(duration);

								facebook.addXperiaFacebookAccount(test_facebookaccount, test_facebookpassword);

				    commonModule.backOutToHomeScreen();
				    albumModule.startOnlineAlbum();
				    startTime = System.currentTimeMillis();
				    while (System.currentTimeMillis() - startTime < duration * 1000) {
											albumModule.scanPicture(1);
				    }

				    commonModule.backOutToHomeScreen();
				    albumModule.startOnlineAlbum();
				    albumModule.viewSlideshow(duration);

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

    public void RELIA_Camera_Take_Multiple_Video_Clips_With_Creative_Effect(String casename, HashMap<String, String> paras) throws Exception {

    	int times = Integer.parseInt(paras.get("times"));
					try {
            commonModule.backOutToHomeScreen();

            Log.i(TAG, "Launch camera");
            camera.launchCameraPhotoApplication();
            commonModule.wait(3);

            for (int j = 0; j < times; j++) {
                camera.selectOneEffectFromCameraSettings("Creative effect");
                camera.takeCreateEffectVideo();
                commonModule.wait(1);


                camera.selectAllCreativeEffectAndRecordVideo();

                Log.i(TAG, "Change Preview mode");
                camera.selectAllPreviewModeAndTakePictures();

					            }
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


	public void RELIA_MM_Long_Time_Play_Video_Under_RAM_Status(String casename, HashMap<String, String> paras) throws Exception {
				 String videoList = paras.get("videoList");
				 int duration = Integer.valueOf(paras.get("duration"));
			        try {
			        			commonModule.backOutToHomeScreen();
			        			IMedia.playingVideoOneByOne(videoList, duration);

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

	public void RELIA_Email_Send_Receive_Emails_With_Different_Times(
			String casename, HashMap<String, String> paras) throws Exception {
		String subject = "Reliability";
		String content = "This is for reliability test";
		int times = Integer.parseInt(paras.get("times"));
		try {
					IEmail.loginEmailSyncAutoServer(test_emailaccount, test_emailpassword);
					for (int i=0;i<times;i++){
						IEmail.launchEmail();
						IEmail.sendEmail(null, test_email2account, subject, content, false);

						IEmail.launchEmail();
						IEmail.replyEmail(false);
						commonModule.backOutToHomeScreen();

						IEmail.launchEmail();
						IEmail.fowardEmail();
						commonModule.backOutToHomeScreen();
					}


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

	public void RELIA_Email_Send_Email_To_Many_Receivers(
			String casename, HashMap<String, String> paras) throws Exception {

		int times = Integer.parseInt(paras.get("times"));
		try {
			IEmail.loginEmailSyncAutoServer(test_emailaccount, test_emailpassword);
			IEmail.launchEmail();
			IEmail.sendEmailToManyReceiver(times);
			commonModule.backOutToHomeScreen();


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

	public void RELIA_PIM_System_Capability_For_Alarms(
			String casename, HashMap<String, String> paras) throws Exception {

		int times = Integer.parseInt(paras.get("times"));
		try {
			for(int i=0;i<times;i++){
					if(i<3){
							IAlarm.addNewAlarm("10", "10");
					}else if(i>19){
						IAlarm.addNewAlarmAfterMaximum();
					}else{
						IAlarm.addNewAlarm("11", String.valueOf(i));
					}
					}
					IAlarm.startAlarmFromMenu();
					IAlarm.deleteAlarmOneByOne();
					commonModule.backOutToHomeScreen();
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
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_PIM_System_Capability_For_Calculator(
			String casename, HashMap<String, String> paras) throws Exception {

		int times = Integer.parseInt(paras.get("times"));
		try {
			for(int i=0;i<times;i++){
				calculator.launchCalculator();

				commonModule.emptyEditorByInstance(0);

				calculator.checkInput();

				calculator.checkMathOperator();

				calculator.checkSpecialOperator();

				commonModule.backOutToHomeScreen();
							}

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

	public void RELIA_PIM_System_Capability_For_Clocks(
			String casename, HashMap<String, String> paras) throws Exception {

			int times = Integer.parseInt(paras.get("times"));
			try {
				IAlarm.addClockMultipleTime(times, true);
				commonModule.backOutToHomeScreen();

				IAlarm.startClock();
				IAlarm.changeTemperatureUnits();
				commonModule.backOutToHomeScreen();

				IAlarm.startClock();
				IAlarm.setHomeTown("Beijing");

				IAlarm.startClock();
				IAlarm.deleteClockOneByOne();

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
				settingsModule.turnOffFlightMode();

			}
		}

	public void RELIA_PIM_System_Capability_For_Stopwatch(
			String casename, HashMap<String, String> paras) throws Exception {

			int times = Integer.parseInt(paras.get("times"));
			try {
				for(int i=0;i<times;i++){
					IAlarm.startAlarmFromMenu();
					IAlarm.setStopWatch(3);
					commonModule.backOutToHomeScreen();
				}

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
				settingsModule.turnOffFlightMode();

			}
		}

	public void RELIA_PIM_System_Capability_For_Timer(
			String casename, HashMap<String, String> paras) throws Exception {

			int times = Integer.parseInt(paras.get("times"));
			try {
				for(int i=0;i<times;i++){
					IAlarm.startAlarmFromMenu();
					IAlarm.setTimer("00", "00", "05");
					commonModule.backOutToHomeScreen();
				}

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

	public void RELIA_PIM_Use_Calculator_With_Invalid_Input(
			String casename, HashMap<String, String> paras) throws Exception {

			try {
									calculator.launchCalculator();

									commonModule.emptyEditorByInstance(0);

	            calculator.checkSpecialOperator();

	            commonModule.backOutToHomeScreen();

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


	public void RELIA_Calendar_View_Edit_Delete_Calendar_Events_Under_Various_RAM_Status(
			String casename, HashMap<String, String> paras) throws Exception {

		try {
				ICalendar.launchCalendar();
				int i=0;
				while(i<3){
						ICalendar.addCalendarNewEvent("Test"+i, null);
						i++;
				}
				commonModule.backOutToHomeScreen();

				ICalendar.launchCalendar();
				i=0;
				while(i<3){
						ICalendar.viewEvent("Test"+i);
						ICalendar.editEvent("Test"+i);
						i++;
					}
				commonModule.backOutToHomeScreen();

				ICalendar.launchCalendar();
				i=0;
				while(i<3){
						ICalendar.deleteEvent("Test"+i);
						i++;
					}
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
			ICalendar.deleteAllTheEvent();
			commonModule.backOutToHomeScreen();
		}
	}


	public void RELIA_OfficeSuite_Name_A_Folder_File_With_Special_Characters(
			String casename, HashMap<String, String> paras) throws Exception {

		try {
			officeSuite.launchOfficeSuite();
			officeSuite.renameOfficeFile(true);
			officeSuite.renameOfficeFile(false);

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




	public void RELIA_Home_Switch_Different_Apps_Many_Times_By_Using_Menu_Button(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		String[] List = {"Calendar","Calculator","Camera","Contacts","Album"};
		try {
					commonModule.closeAllApps();
					Log.i(TAG,"Launch many Apps");
					ICalendar.launchCalendar();
					commonModule.backOutToHomeScreen();
					calculator.launchCalculator();
					commonModule.backOutToHomeScreen();
					camera.launchCameraPhotoApplication();
					commonModule.backOutToHomeScreen();
					IContacts.startPhonebook();
					commonModule.backOutToHomeScreen();
				 IAlbum.startAlbum();
				 commonModule.backOutToHomeScreen();

				 	int i=0;
					while(i<times){
								commonModule.openRecentApp();
								commonModule.scrollUpFindTextNotClick(List[i%5]);
								commonModule.clickDescription(List[i%5]);
								commonModule.verifyAppOpened(List[i%5]);
								i++;

				    }

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

	public void RELIA_Email_Send_Receive_Email_With_Different_Attachment_Size_without_attachment(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		String subject = "Reliability";
		String content = "This is for reliability test";

		try {
				IEmail.loginEmailSyncAutoServer(test_emailaccount, test_emailpassword);
				for (int i=0;i<times;i++){
					IEmail.launchEmail();
					IEmail.sendEmail(null, test_emailaccount, subject, content, false);
					IEmail.launchEmail();
					IEmail.checkReceivedMailContent(subject, 0);
					commonModule.backOutToHomeScreen();
				}


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

	public void RELIA_Email_Send_Receive_Email_With_Different_Attachment_Size_with_one_attachment(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		String subject = "Reliability";

		try {
				IEmail.loginEmailSyncAutoServer(test_emailaccount, test_emailpassword);
				for (int i=0;i<times;i++){
					IEmail.launchEmail();
					IEmail.sendEmailWithAttachment(test_emailaccount, subject, "Picture.JPG");
					IEmail.checkReceivedMailContent(subject, 1);
					commonModule.backOutToHomeScreen();
				}

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

	public void RELIA_Email_Send_Receive_Email_With_Different_Attachment_Size_with_many_attachments(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		String subject = "Reliability";
		String[] attachName = {"Picture.JPG","testDoc.doc","Video.3gp","MusicAttachment.mp3"};
		try {
				IEmail.loginEmailSyncAutoServer(test_emailaccount, test_emailpassword);
				for (int i=0;i<times;i++){
					IEmail.launchEmail();
					IEmail.sendEmailWithMultipleAttachments(test_emailaccount, subject, attachName);
					IEmail.checkReceivedMailContent(subject, attachName.length);
					commonModule.backOutToHomeScreen();
				}


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

	public void RELIA_Camera_Take_Multiple_Sequential_Videos_Under_Different_Mode(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		try {
					for (int i=0;i<times;i++){
									camera.launchCameraPhotoApplication();
									camera.selectOneEffectFromCameraSettings("Superior auto");
									camera.pauseWhenRecordVideo(120, 10);
									commonModule.backOutToHomeScreen();

									camera.checkVideos(1);
									commonModule.backOutToHomeScreen();

									camera.launchCameraPhotoApplication();
									camera.selectOneEffectFromCameraSettings("Manual");
									camera.switchToVideoWithManualMode();
									camera.pauseWhenRecordVideo(120, 10);
									commonModule.backOutToHomeScreen();

									camera.checkVideos(1);
									commonModule.backOutToHomeScreen();
					}

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
			commonModule.clearData("Camera");
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Camera_Take_And_Post_Multiple_Picture_With_Different_Camera_Mode(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		try {
					facebook.launchFacebook(test_facebookaccount, test_facebookpassword);
					commonModule.backOutToHomeScreen();
					for (int i=0;i<times;i++){
									camera.launchCameraPhotoApplication();
									camera.selectOneEffectFromCameraSettings("Social live");
									camera.takeAndPostPictureViaSocialLive();
									commonModule.backOutToHomeScreen();
					}

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


	public void RELIA_OfficeSuite_View_Edit_Files_In_OfficeSuite_Under_Different_RAM_Status(
			String casename, HashMap<String, String> paras) throws Exception {

		try {
				officeSuite.openAndEditFile("relDoc.doc", "DOC");
				officeSuite.openAndEditFile("relExcel.xls", "EXCEL");
				officeSuite.openAndEditFile("relTxt.txt", "TXT");
				officeSuite.openAndEditFile("relPpt.ppt", "PPT");
				officeSuite.openAndEditFile("relPdf.pdf", "PDF");

				officeSuite.openFileFromExternalStorage("Picture.JPG");
				officeSuite.verifyPictureOpened();
				commonModule.backOutToHomeScreen();

				officeSuite.openFileFromExternalStorage("Video.3gp");
				officeSuite.verifyVideoOpened();
				commonModule.backOutToHomeScreen();

				officeSuite.openFileFromExternalStorage("MusicAttachment.mp3");
				IWalkman.verifyMusicPlaying();
				commonModule.backOutToHomeScreen();
				IWalkman.stopMusicFromStatusBar();

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
			commonModule.backOutToHomeScreen();
		}
	}

	public void RELIA_Email_Select_Attachment_From_Large_Amount_Of_Data(
			String casename, HashMap<String, String> paras) throws Exception {

		try {
					IEmail.prepareEmailEditor();
					//PictureSet
					IEmail.addAttachment("Internal storage", "Picture.JPG", "testresource");
					commonModule.backOutToHomeScreen();

					//VideoSet
					IEmail.prepareEmailEditor();
					IEmail.addAttachment("Internal storage", "Video.3gp", "testresource");
					commonModule.backOutToHomeScreen();

					//AudioSet
					IEmail.prepareEmailEditor();
					IEmail.addAttachment("Internal storage", "MusicAttachment.mp3", "testresource");
					commonModule.backOutToHomeScreen();

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

	public void RELIA_Email_Setup_More_Accounts(
			String casename, HashMap<String, String> paras) throws Exception {

		int emailAccounts = Integer.parseInt(paras.get("emailAccounts"));
		int times = Integer.parseInt(paras.get("times"));
		String[] emailList = {"agingtestnew001@gmail.com","agingtestnew002@gmail.com"};
		String[] emailPwd = {"Beijing00","Beijing00"};
		try {
			//Setup Email Account
			for(int i=0;i<emailAccounts;i++){
						IEmail.addEmailAccountFromSettings(emailList[i],emailPwd[i]);
			}
			commonModule.backOutToHomeScreen();
			//Send mails for each
			for(int i=0;i<emailAccounts;i++){
				int j=0;
				while(j<times){
						IEmail.sendEmail(emailList[i], "test@gmail.com", "Reliability", "Reliability testing", false);
						j++;
				}
	}

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
 * 1366167	RELIA_Web_Repeat to add Bookmark(s) under phone heavy load-Parameter

 * @param casename
 * @param paras
 * @throws Exception
 */
	public void RELIA_Web_Repeat_To_Add_Bookmarks_Under_Phone_Heavy_Load(
			String casename, HashMap<String, String> paras) throws Exception {

				String bookmarkName = "Reliability";
				int times = Integer.parseInt(paras.get("times"));
				try {
					for(int i=0;i<times;i++){
							browser.startChrom();
							browser.loadWebPage("www.google.com.");
							browser.addBroswerBookmark(bookmarkName+i);
							commonModule.backOutToHomeScreen();
							browser.deleteBookmark(bookmarkName+i);
							commonModule.backOutToHomeScreen();
					}
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
	 	* 1349403
	 * RELIA_Album_System capability for edit Images-Parameter

	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Album_System_Capability_For_Edit_Images(
			String casename, HashMap<String, String> paras) throws Exception {

				int times = Integer.parseInt(paras.get("times"));
				try {
					albumModule.startAlbum();
					albumModule.viewPictureInAlbum(0);
					for(int i=0;i<times;i++){

						if(i%3==0){
										albumModule.editPictureWithPhotoEditor();
										albumModule.scanPicture(1);
						}else if(i%3==1){
										albumModule.editPictureWithPhotos();
										albumModule.scanPicture(1);
						}else if(i%3==2){
										albumModule.editPictureWithSketch();
										albumModule.scanPicture(1);
						}
						}
						commonModule.backOutToHomeScreen();

						albumModule.startAlbum();
						albumModule.selectPicture(1);
						for(int j=0;j<times;j++){
							albumModule.editPictureWithPhotoEditor();
							albumModule.editPictureWithPhotos();
							albumModule.editPictureWithSketch();
						}
							commonModule.backOutToHomeScreen();


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

    public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_add_events(
            String casename, HashMap<String, String> paras) throws Exception {

        int times = Integer.parseInt(paras.get("times"));
        try {
            IEmail.addGoogleAccount(test_gmailaccount, test_gmailpassword);
            commonModule.backOutToHomeScreen();
            IEmail.loginEmailSyncAutoServer(test_EASaccount, test_EASpassword);
            commonModule.backOutToHomeScreen();
            ICalendar.launchCalendar();
            for (int i = 0; i < times; i++) {
                ICalendar.addCalendarNewEvent("RelibilityGoogle" + i, "Google");
                ICalendar.addCalendarNewEvent("ReliabilityEAS" + i, "EAS");
                ICalendar.addCalendarNewEvent("ReliabilityLocal" + i, "Local");
            }
            commonModule.backOutToHomeScreen();
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
            ICalendar.deleteAllTheEvent();
        }
    }

    public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_expired_in_calling(
            String casename, HashMap<String, String> paras) throws Exception {

        PrePostInvocationHandler ongoingCall = new PrePostInvocationHandler(concurrentevets,
                new ScenarioOngingCall(testCase));

        IConcurrentEvents ongoingCallConcurrent = (IConcurrentEvents)Proxy.newProxyInstance(
                concurrentevets.getClass().getClassLoader(), concurrentevets.getClass()
                        .getInterfaces(), ongoingCall);
        try {
            ICalendar.launchCalendar();
            ICalendar.addCalendarNewEvent("Relibility test", null);
            commonModule.backOutToHomeScreen();
            ongoingCallConcurrent.calendarExpired();

            commonModule.backOutToHomeScreen();
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
            ICalendar.deleteAllTheEvent();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }


    public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_expired_lock_screen(
            String casename, HashMap<String, String> paras) throws Exception {

        PrePostInvocationHandler lockScreen = new PrePostInvocationHandler(concurrentevets,
                new ScenarioLockScreen(testCase));

        IConcurrentEvents lockScreenConcurrent = (IConcurrentEvents)Proxy.newProxyInstance(
                concurrentevets.getClass().getClassLoader(), concurrentevets.getClass()
                        .getInterfaces(), lockScreen);
        try {
            ICalendar.launchCalendar();
            ICalendar.addCalendarNewEvent("Relibility test", null);
            commonModule.backOutToHomeScreen();
            lockScreenConcurrent.calendarExpired();
//            commonModule.wait(2);
//            AcceptanceTestCase.assertTrue("Can not receive the reminder in 2 minutes.",
//                    ICalendar.waitForCalendarAlarm("Relibility test", 5000));

            commonModule.backOutToHomeScreen();
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
            ICalendar.deleteAllTheEvent();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

	public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_expired_camera_viewfinder(
			String casename, HashMap<String, String> paras) throws Exception {

				PrePostInvocationHandler cameraRecordingVideo = new PrePostInvocationHandler(concurrentevets,
						new ScenarioCameraRecordingVideo(testCase));

				IConcurrentEvents cameraRecordingVideoConcurrent = (IConcurrentEvents) Proxy
						.newProxyInstance(concurrentevets.getClass().getClassLoader(),
								concurrentevets.getClass().getInterfaces(),
								cameraRecordingVideo);
				try {
							ICalendar.launchCalendar();
							ICalendar.addCalendarNewEvent("Relibility test", null);
							commonModule.backOutToHomeScreen();
							cameraRecordingVideoConcurrent.calendarExpiredInBackground();
		        commonModule.wait(2);
		        AcceptanceTestCase.assertTrue("Can not receive the reminder in 2 minutes.",
		                ICalendar.waitForCalendarAlarm("Relibility test", 3 * 60 * 1000));

							commonModule.backOutToHomeScreen();
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
					ICalendar.deleteAllTheEvent();
				}
			}

	public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_expired_play_video(
			String casename, HashMap<String, String> paras) throws Exception {

				PrePostInvocationHandler playVideo = new PrePostInvocationHandler(concurrentevets,
						new ScenarioVideoPlaying(testCase));

				IConcurrentEvents playVideoConcurrent = (IConcurrentEvents) Proxy
						.newProxyInstance(concurrentevets.getClass().getClassLoader(),
								concurrentevets.getClass().getInterfaces(),
								playVideo);
				try {
							ICalendar.launchCalendar();
							ICalendar.addCalendarNewEvent("Relibility test", null);
							commonModule.backOutToHomeScreen();
							playVideoConcurrent.calendarExpiredInBackground();
		        commonModule.wait(2);
		        AcceptanceTestCase.assertTrue("Can not receive the reminder in 2 minutes.",
		                ICalendar.waitForCalendarAlarm("Relibility test", 3 * 60 * 1000));

							commonModule.backOutToHomeScreen();
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
					ICalendar.deleteAllTheEvent();
				}
			}

	public void RELIA_Calendar_Add_Multiple_Events_For_Different_Account_expired_browsing_website(
			String casename, HashMap<String, String> paras) throws Exception {

				PrePostInvocationHandler browsingWebsite = new PrePostInvocationHandler(concurrentevets,
						new ScenarioBrowsingWebsite(testCase));

				IConcurrentEvents browsingWebsiteConcurrent = (IConcurrentEvents) Proxy
						.newProxyInstance(concurrentevets.getClass().getClassLoader(),
								concurrentevets.getClass().getInterfaces(),
								browsingWebsite);
				try {
							ICalendar.launchCalendar();
							ICalendar.addCalendarNewEvent("Relibility test", null);
							commonModule.backOutToHomeScreen();
							browsingWebsiteConcurrent.calendarExpired();

							commonModule.backOutToHomeScreen();
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
					ICalendar.deleteAllTheEvent();
				}
			}

	public void RELIA_Calendar_Receive_Forward_Calendar_Events_To_Multiple_Contacts(
			String casename, HashMap<String, String> paras) throws Exception {
		int contactNumber = Integer.valueOf(paras.get("contactNumber"));
		ArrayList<String>  contactList = new ArrayList<String> ();
		String contactName = "AA_Reliability";
		try {
        	for(int i=0;i<contactNumber;i++){
        		IContacts.createContactsURI(contactName + i, "1569900"+i,"asdfasfad"+i+"@163.com");
        		contactList.add(contactName + i);
            	}
        	IEmail.addEASAccountFromSettings(test_EASaccount, test_EASpassword);
        	commonModule.backOutToHomeScreen();
        	ICalendar.launchCalendar();
        	for(int j=0;j<5;j++){

        		ICalendar.addCalendarNewEvent("Reliability test"+j, "EAS");
        	}
        	commonModule.backOutToHomeScreen();

        	for(int k=0;k<5;k++){
        		ICalendar.launchCalendar();
        		ICalendar.fowardCalendarEvent("Reliability test"+k, contactList);
        		commonModule.backOutToHomeScreen();
        	}


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
	 * RELIA_Camera_Use_Camera_When_Internal_Or_SD_Memory_Is_Full_internal_storage
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Camera_Use_Camera_When_Internal_Or_SD_Memory_Is_Full_internal_storage(
			String casename, HashMap<String, String> paras) throws Exception {
		String filePath = Environment.getExternalStorageDirectory().getPath()
	            + "/AutomationTest/";
		try {
			//Change camera storage
/*				camera.launchCameraPhotoApplication();
				camera.changeCameraStorage("SD card");
				commonModule.backOutToHomeScreen();*/

				commonModule.fillInternalStorageFull();

				//Superior auto
				camera.launchCameraPhotoApplication();
				camera.selectOneEffectFromCameraSettings("Superior auto");
				camera.takePictures(1);
				camera.recordVideo(5);

				//Manual
				camera.selectOneEffectFromCameraSettings("Manual");
				camera.takePictures(1);
				camera.recordVideoByManualMode(5);

				//Face in
				camera.selectOneEffectFromCameraSettings("Face in");
				camera.takePictures(1);
				camera.recordVideoFaceIn(5);

				//4K video
				camera.selectOneEffectFromCameraSettings("4K video");
				camera.recordVideoOn4KMode(5);

				//Timeshift video
				camera.selectOneEffectFromCameraSettings("Timeshift video");
				camera.recordVideoOnTimeshiftMode(5);

				//AR effect
				camera.selectOneEffectFromCameraSettings("AR effect");
				camera.recordVideoByAREffect(5);
				camera.takePictures(1);


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
			commonModule.delete(filePath);
		}
	}

	/**
	 * RELIA_Games_Play_Games_Under_Different_RAM_Status
	   *   1364710
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Games_Play_Games_Under_Different_RAM_Status(
			String casename, HashMap<String, String> paras) throws Exception {

		try {

			playStore.playGame(1);
			commonModule.backOutToHomeScreen();

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
	 * RELIA_Phonebook_Contact_With_Same_Name
	   *   1334982
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Contact_With_Same_Name(
			String casename, HashMap<String, String> paras) throws Exception {
			String contactName = "Reliability1334982";
			String preName = "TEST";

		try {
				IContacts.createContactsURI(preName, "13300000000", "abcd@gmail.com");
				IContacts.createContact(contactName,callNumber, test_emailaccount);
				IContacts.createContact(contactName,callNumber, test_emailaccount);
				IContacts.handlSameContactName(false, contactName);

				IContacts.editContactName(preName, contactName);
				IContacts.checkContactExist(contactName, 2);
				commonModule.backOutToHomeScreen();


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
	 * RELIA_Phonebook_Continuously_Delete_Multiple_Contacts_Save_In_Different_Storage
	   *   1365687
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Continuously_Delete_Multiple_Contacts_Save_In_Different_Storage(
			String casename, HashMap<String, String> paras) throws Exception {

		String storage = paras.get("storage");
		int times = Integer.parseInt(paras.get("times"));
		String contactName = "DS_Contact";
		try {
					if(storage=="SIM card"){
							IContacts.startPhonebook();
							IContacts.importContacts("SIM card", null);
							commonModule.backOutToHomeScreen();
					}else{
							for(int i=0;i<1500;i++){
								IContacts.createContactsURI("DS_Contact"+i, "1330000"+i, "abcd"+i+"@gmail.com");
							}
					}

					IContacts.startPhonebook();
					IContacts.checkContactExist("DS_Contact0", 0);
					IContacts.checkContactExist("DS_Contact1499", 0);
					commonModule.backOutToHomeScreen();

					IContacts.startPhonebook();
					for(int i=0;i<times;i++){
						IContacts.deleteOneContact(contactName+i);
					}

					IContacts.deleteContacts();
					commonModule.backOutToHomeScreen();


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
	 * 1365694
	 * RELIA_Messaging_Send_Long_SMS_To_Multiple_Recipients
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Messaging_Send_Long_SMS_To_Multiple_Recipients(
			String casename, HashMap<String, String> paras) throws Exception {

		int times = Integer.parseInt(paras.get("times"));
		try {
						IMessaging.startMessagingFromMenu();
						IMessaging.deleteMessageWithNumber(selNumber);
						StringBuffer sb = new StringBuffer();
						for(int i=0;i<16;i++){
							sb.append("aaaaaaaaaa");
						}
						sb.append("b");
						String msgContent = sb.toString();

						IMessaging.startMessagingFromMenu();
						IMessaging.sendSMSToMultipleRecipient(times, msgContent,selNumber);
						commonModule.backOutToHomeScreen();
						IMessaging.openConversationByNameOrNumber(selNumber);
						commonModule.hidKeyBoard();
						IMessaging.checkReceiveMessage(true, msgContent);

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
	 * 1365697
	 * RELIA_Phonebook_Add_Different_Contact_For_Multiple_Times
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Add_Different_Contact_For_Multiple_Times(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		try {
				IContacts.deleteContacts();
					for(int i=1;i<times;i++){
								IContacts.createContactFullField("Automation"+i);
								commonModule.backOutToHomeScreen();
					}

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
	 * 1365429
	 * RELIA_Messaging_MMS_Select_Attachment_From_Large_Amount_Of_Data
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Messaging_MMS_Select_Attachment_From_Large_Amount_Of_Data(
			String casename, HashMap<String, String> paras) throws Exception {
		try {
					//PictureSet
					IMessaging.prepareSMSMessage("Picture", callNumber);
					IMessaging.scanAndInsertFileForMMS("testresource");
					commonModule.backOutToHomeScreen();

					//VideoSet
					IMessaging.prepareSMSMessage("Video", callNumber);
					IMessaging.scanAndInsertFileForMMS("testresource");
					commonModule.backOutToHomeScreen();


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
	 *    1349440
	 * RELIA_Sync_Sync_Under_Various_RAM_Status
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Sync_Sync_Under_Various_RAM_Status(
			String casename, HashMap<String, String> paras) throws Exception {
		try {
			// Sync Email
			IEmail.addEmailAccountFromSettings(test_emailaccount, test_emailpassword);
			IEmail.syncEmailInSetting(test_emailaccount);
			commonModule.backOutToHomeScreen();

			//Sync EAS
			IEmail.addEASAccountFromSettings(test_EASaccount, test_EASpassword);
			IEmail.syncEASInSetting(test_EASaccount);
			commonModule.backOutToHomeScreen();

			//Sync Facebook
			facebook.addFacebookAccount(test_facebookaccount, test_facebookpassword);
			facebook.syncFacebookInSetting(test_facebookaccount);
			commonModule.backOutToHomeScreen();

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
	 *    1364703-1
	 * RELIA_TrackID_Search_Songs_For_Multiple_Times_songs
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_TrackID_Search_Songs_For_Multiple_Times_songs(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
		try {
					IWalkman.setPlayMode("repeat all");
					commonModule.backOutToHomeScreen();
					IWalkman.playMusic();
					commonModule.backOutToHomeScreen();

					// Switch and search songs one by one
					for(int i=0;i<times;i++){
								commonModule.wait(2);
								String musicName=IWalkman.getCurrentSongNameFromStatusBar();
								IMedia.launchTrackID();
								IMedia.searchSongs(musicName);
								IWalkman.playNextMusicFromStatusBar();
					}
					commonModule.backOutToHomeScreen();

					IWalkman.stopMusicFromStatusBar();

					commonModule.backOutToHomeScreen();

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
	 *    1364703-2
	 * RELIA_TrackID_Search_Songs_For_Multiple_Times_fm_radio
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_TrackID_Search_Songs_For_Multiple_Times_fm_radio(
			String casename, HashMap<String, String> paras) throws Exception {
		try {

					//Search FM radio
					IMedia.playRadioOnBackground();
					IMedia.launchTrackID();
					IMedia.repeatSearchSongs(10);
					IWalkman.verifyMusicPlaying();
					commonModule.wait(360);
					IWalkman.verifyMusicPlaying();
					commonModule.backOutToHomeScreen();



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
			commonModule.backOutToHomeScreen();

		}
	}


	/**
	 *    1365684-1
	 * RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_google_account
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_google_account(
			String casename, HashMap<String, String> paras) throws Exception {
				int contactNumber=Integer.parseInt(paras.get("contactNumber"));
				String networkmode = paras.get("networkmode");
				try {
							//Pre-condition
/*	   				IEmail.addGoogleAccount(test_gmailaccount, test_gmailpassword);
							IContacts.deleteContacts();
							IContacts.cancelAutoSyncData();
							commonModule.backOutToHomeScreen();*/

							 if (networkmode.contains(",")) {
								 String[] modes = networkmode.split(",");

			        for (int i = 0; i < modes.length; i++) {
			            telephonyModule.changeNetworkMode(modes[i]);

											//Sync google account contacts
						        IContacts.displayContacts(test_gmailaccount);
						        IContacts.syncContact(test_gmailaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_gmailaccount);
						        commonModule.backOutToHomeScreen();
						                }
			        }else{
			            telephonyModule.changeNetworkMode(networkmode);

											//Sync google account contacts
						        IContacts.displayContacts(test_gmailaccount);
						        IContacts.syncContact(test_gmailaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_gmailaccount);
			        }


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
					IContacts.dismissContacts(test_gmailaccount);
					commonModule.backOutToHomeScreen();

				}
			}

	/**
	 *    1365684-2
	 * RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_eas_account
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_eas_account(
			String casename, HashMap<String, String> paras) throws Exception {
				int contactNumber=Integer.parseInt(paras.get("contactNumber"));
				String networkmode = paras.get("networkmode");
				try {
							//Pre-condition
					/*	   				IEmail.addEASAccountFromSettings(test_EASaccount, test_EASaccount);
							IContacts.deleteContacts();
							IContacts.cancelAutoSyncData();
							commonModule.backOutToHomeScreen();*/

							 if (networkmode.contains(",")) {
								 String[] modes = networkmode.split(",");

			        for (int i = 0; i < modes.length; i++) {
			            telephonyModule.changeNetworkMode(modes[i]);

											//Sync google account contacts
						        IContacts.displayContacts(test_EASaccount);
						        IContacts.syncContact(test_EASaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_EASaccount);
						        commonModule.backOutToHomeScreen();
						                }
			        }else{
			            telephonyModule.changeNetworkMode(networkmode);

											//Sync google account contacts
						        IContacts.displayContacts(test_EASaccount);
						        IContacts.syncContact(test_EASaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_EASaccount);
			        }


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
					IContacts.dismissContacts(test_gmailaccount);
					commonModule.backOutToHomeScreen();

				}
			}


	/**
	 *    1365684-3
	 * RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_facebook_account
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Phonebook_Sync_With_Online_Account_Under_Various_Network_Mode_facebook_account(
			String casename, HashMap<String, String> paras) throws Exception {
				int contactNumber=Integer.parseInt(paras.get("contactNumber"));
				String networkmode = paras.get("networkmode");
				try {
							//Pre-condition
					/*	facebook.addFacebookAccount(test_facebookaccount, test_facebookpassword);

							IContacts.deleteContacts();
							IContacts.cancelAutoSyncData();
							commonModule.backOutToHomeScreen();*/

							 if (networkmode.contains(",")) {
								 String[] modes = networkmode.split(",");

			        for (int i = 0; i < modes.length; i++) {
			            telephonyModule.changeNetworkMode(modes[i]);

											//Sync google account contacts
						        IContacts.displayContacts(test_facebookaccount);
						        IContacts.syncContact(test_facebookaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_facebookaccount);
						        commonModule.backOutToHomeScreen();
						                }
			        }else{
			            telephonyModule.changeNetworkMode(networkmode);

											//Sync google account contacts
						        IContacts.displayContacts(test_facebookaccount);
						        IContacts.syncContact(test_facebookaccount);
						        IContacts.assertSyncContactNumberCorrectly(contactNumber);
						        IContacts.dismissContacts(test_facebookaccount);
			        }


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
					IContacts.dismissContacts(test_gmailaccount);
					commonModule.backOutToHomeScreen();

				}
			}


	/**
	 *    1363464
	 * RELIA_Quick_Settings_Set_Settings_For_Multiple_Times
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Quick_Settings_Set_Settings_For_Multiple_Times(
			String casename, HashMap<String, String> paras) throws Exception {
				String[] settingList = {"Settings","‏LTE","Wi-Fi","Bluetooth","Location","Wi-Fi hotspot","Throw",
						"Auto rotate","Brightness","Mobile data","Sound","Airplane mode","Roaming","Auto-sync data",
						"Flash light","STAMINA","NFC","Screen mirroring"};
				try {
					for(int i=0;i<settingList.length;i++){
								settingsModule.openSettingFromNotification(settingList[i]);
								}
					commonModule.backOutToHomeScreen();

					settingsModule.removeAllItemFromQuickSetting();

					commonModule.backOutToHomeScreen();

					settingsModule.addMostItemToQuickSetting();

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
	 *    1364666
	 * RELIA_FM_Playing_FM_Under_Different_RAM_Status
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_FM_Playing_FM_Under_Different_RAM_Status(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
				try {
					for(int i=0;i<times;i++){
						IMedia.launchRadioPlayer();

						IMedia.searchChannel();

						commonModule.wait(60);

						IWalkman.verifyMusicPlaying();

						IMedia.stopRadio();

						commonModule.backOutToHomeScreen();
					}

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
	 * 1349397
	 * RELIA_Album_Image_Player_Settings_For_Multiple_Times
	 * @param casename
	 * @param paras
	 * @throws Exception
	 */
	public void RELIA_Album_Image_Player_Settings_For_Multiple_Times(
			String casename, HashMap<String, String> paras) throws Exception {
		int times = Integer.parseInt(paras.get("times"));
				try {
					for(int i=0;i<times;i++){
						IAlbum.openOnePicture();
						IAlbum.checkAllMoreMenuInAlbumMenu();

						IAlbum.playSenseMeSlideshow("Zoom & Grid", "Default music");
						IAlbum.openThrowInAlbum();
						commonModule.backOutToHomeScreen();
						}


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
