
package com.test.rt.phase2;

import android.os.Environment;
import android.view.KeyEvent;

import com.module.alarm.AlarmFactory;
import com.module.alarm.IAlarm;
import com.module.album.AlbumFactory;
import com.module.album.IAlbum;
import com.module.browser.BrowserFactory;
import com.module.browser.IBrowser;
import com.module.calculator.AbstractCalculatorFactory;
import com.module.calculator.ICalculator;
import com.module.calendar.CalendarFactory;
import com.module.calendar.ICalendar;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.module.contacts.ContactsFactory;
import com.module.contacts.IContacts;
import com.module.email.IEmail;
import com.module.lockscreen.AbstractLockScreenFactory;
import com.module.lockscreen.ILockScreen;
import com.module.media.IMedia;
import com.module.media.MediaFactory;
import com.module.messaging.AbstractMessagingFactory;
import com.module.messaging.IMessaging;
import com.module.messaging.MessagingFactory;
import com.module.officesuite.IOfficeSuite;
import com.module.officesuite.OfficeSuiteFactory;
import com.module.playStore.AbstractPlayStoreFactory;
import com.module.playStore.IPlayStore;
import com.module.settings.AbstractSettingsFactory;
import com.module.settings.ISetting;
import com.module.smallApp.ISmallApp;
import com.module.smallApp.SmallAppFactory;
import com.module.smartConnect.ISmartConnect;
import com.module.smartConnect.SmartConnectFactory;
import com.module.telephony.AbstractTelephonyFactory;
import com.module.telephony.ITelephony;
import com.module.voiceSearch.AbstractVoiceSearchFactory;
import com.module.voiceSearch.IVoiceSearch;
import com.module.voiceSearch.VoiceSearchFactory;
import com.module.walkman.AbstractWalkmanFactory;
import com.module.walkman.IWalkman;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.module.email.AbstractEmailFactory;
import com.module.facebook.AbstractFacebookFactory;
import com.module.facebook.IFacebook;
import com.module.fileCommander.AbstractFileCommanderFactory;
import com.module.fileCommander.IFileCommander;
import com.module.gmail.AbstractGmailFactory;
import com.module.gmail.IGmail;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import junit.framework.AssertionFailedError;

public class RTAutomaticModulesPhaseII {

    private AcceptanceTestCase testCase;

    private CommonModule commonModule;

	private String callNumber;

    private static String callNumber2;

    private static String callNumber3;

    private static String emailaccount;

    private static String emailpassword;

    private static String email2account;

    private static String email2password;

    private static String EASaccount;

    private static String EASpassword;

    private static String facebookaccount;

    private static String facebookpassword;

    private static String test_vpnusername;

    private static String test_vpnpassword;

    private static String test_gmailpassword;

    private static String test_gmailaccount;

    private static String test_sipaccount;

    private static String test_sippassword;

    private static String test_partnerskypeaccount;

    private static String test_skypeaccount;

    private static String test_skypepassword;

    private static String partner_sipaccount;

    private static String test_wifi;

    private static String test_wifipassword;

    private IOfficeSuite officeSuite;

    private ITelephony telephonyModule;

    private ICamera camera;

    private IAlarm alarmModule;

    private ISetting settingsModule;

    private IMedia mediaModule;

    private IAlbum albumModule;

    private IMessaging messagingModule;

    private IContacts contactsModule;

    private ISmallApp smallApp;

    private ISmartConnect smartConnect;

    private IEmail emailModule;

    private IWalkman walkman;

    private ICalculator calculator;

    private IFacebook facebook;

    private ICalendar calender;

    private IPlayStore playStore;

    private IFileCommander fileCommander;

    private IGmail gmail;

    private IBrowser browser;

    private IVoiceSearch voiceSearch;

    private ILockScreen lockScreen;

    public String cameraFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/100ANDRO/";

    public String xperiaFilePath = Environment.getExternalStorageDirectory().getPath()
            + "/DCIM/XPERIA/";

    public String testloops;

    public RTAutomaticModulesPhaseII(AcceptanceTestCase testCase) throws PropertyNotFoundException,
            XmlPullParserException, IOException {
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);

        ModuleDataParser dataParser = new ModuleDataParser();
        dataParser.parse();

        getAccounts();

        officeSuite = OfficeSuiteFactory.getOfficeSuiteModule(testCase);
        telephonyModule = ((AbstractTelephonyFactory)GroupFactories.createFactory(testCase,
                "telephony")).create();
        alarmModule = AlarmFactory.getCameraModule(testCase);
        settingsModule = ((AbstractSettingsFactory)(GroupFactories.createFactory(testCase,
                "settings"))).getSettingsModule();
        messagingModule = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();
        camera = ((AbstractCameraFactory)GroupFactories.createFactory(testCase, "camera")).create();
        mediaModule = MediaFactory.getMediaModule(testCase);
        albumModule = AlbumFactory.getAlbumModule(testCase);
        contactsModule = ContactsFactory.getContactsModule(testCase);
        smallApp = SmallAppFactory.getSmallAppModule(testCase);
        smartConnect = SmartConnectFactory.getSmartConnectModule(testCase);
        emailModule = ((AbstractEmailFactory)GroupFactories.createFactory(testCase, "email"))
                .create();
        walkman = ((AbstractWalkmanFactory)GroupFactories.createFactory(testCase, "walkman"))
                .create();
        calculator = ((AbstractCalculatorFactory)GroupFactories.createFactory(testCase,
                "calculator")).create();
        facebook = ((AbstractFacebookFactory)GroupFactories.createFactory(testCase, "facebook"))
                .create();
        calender = CalendarFactory.getCalendarModule(testCase);
        playStore = ((AbstractPlayStoreFactory)GroupFactories.createFactory(testCase, "playstore"))
                .create();
        fileCommander = ((AbstractFileCommanderFactory)GroupFactories.createFactory(testCase,
                "filecommander")).create();
        gmail = ((AbstractGmailFactory)GroupFactories.createFactory(testCase, "gmail")).create();
        browser = BrowserFactory.create(testCase);
        voiceSearch = ((AbstractVoiceSearchFactory)GroupFactories.createFactory(testCase,
                "voicesearch")).create();
        lockScreen = ((AbstractLockScreenFactory)GroupFactories.createFactory(testCase,
                "lockscreen")).create();

    }

    public void getAccounts() {
        callNumber = TestDataExtract.callNumber;
        callNumber2 = TestDataExtract.callNumber2;
        callNumber3 = TestDataExtract.callNumber3;

        emailaccount = TestDataExtract.test_emailaccount;
        emailpassword = TestDataExtract.test_emailpassword;
        email2account = TestDataExtract.test_email2account;
        email2password = TestDataExtract.test_email2password;
        EASaccount = TestDataExtract.test_EASaccount;
        EASpassword = TestDataExtract.test_EASpassword;
        facebookaccount = TestDataExtract.test_facebookaccount;
        facebookpassword = TestDataExtract.test_facebookpassword;
        test_vpnusername = TestDataExtract.test_vpnusername;
        test_vpnpassword = TestDataExtract.test_vpnpassword;
        test_gmailaccount = TestDataExtract.test_gmailaccount;
        test_gmailpassword = TestDataExtract.test_gmailpassword;
        test_partnerskypeaccount = TestDataExtract.test_partnerskypeaccount;
        test_skypeaccount = TestDataExtract.test_skypeaccount;
        test_skypepassword = TestDataExtract.test_skypepassword;
        test_sipaccount = TestDataExtract.test_sipaccount;
        test_sippassword = TestDataExtract.test_sippassword;
        partner_sipaccount = TestDataExtract.partner_sipaccount;

        test_wifi = TestDataExtract.test_wifi;
        test_wifipassword = TestDataExtract.test_wifipassword;


    }

    public void RT_COMM_Screenlock_Action_in_notification_window(String casename,
            HashMap<String, String> paras) throws Exception {

        try {
            // preconditions
            commonModule.clearStatusBar();

            commonModule.lockScreen();

            // Remote will make a call to DUT
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_CALL);

            telephonyModule.waitingForCallComing();

            // remote end call
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_END_CALL);

            // Remote will send a SMS to DUT
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SMS);

            messagingModule.openReceiveMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE);

            commonModule.backOutToHomeScreen();

            commonModule.lockScreen();
            commonModule.wait(2);
            commonModule.litScreen();

            telephonyModule.clickMissedCallFromNotificationBar(callNumber);

        } catch (Exception ex) {
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
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Screenlock_Action_in_notification_window_music(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            // preconditions
            commonModule.clearStatusBar();

            camera.launchCameraPhotoApplication();

            camera.takePictures(1);

            walkman.playMusicOnBackground();

            commonModule.lockScreen();
            commonModule.wait(2);
            commonModule.litScreen();

            walkman.verifyMusicStateUnderLockScreen();

            walkman.switchToNextSongUnderLockScreenState();

            walkman.switchToPreSongUnderLockScreenState();

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173382
     * RT_COMM_PIM_Calendar_Appointment_Create_All day event
     *
     * @throws Exception
     */
    public void RT_COMM_PIM_Calendar_Appointment_Create_All_Day_Event(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        String subject = "All day event";
        try {
            contactsModule.deleteContacts();
            contactsModule.startPhonebook();
            contactsModule.importContacts("Internal storage", "PIM00002.vcf");
            calender.deleteAllTheEvent();
            commonModule.backOutToHomeScreen();

            calender.launchCalendar();
            calender.switchCalendarView("Month");

            calender.createAllDayEventButNotSave(subject);

            calender.createAllDayEventAndSave(subject);

            calender.addAttendeesForEvent(subject);

            calender.switchCalendarViewToCheckAllDayEvent(subject);

            calender.changeDateByView();

        } catch (Exception ex) {
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
            calender.deleteAllTheEvent();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_PIM_Contacts_Display_Group(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();
        String contactWithoutNumber = "withoutNumber";
        String contactWithGmailInfo = "gmailInfo";
        String groupName = "newGmailGroup";

        try {
            commonModule.clearData("Contacts");

            contactsModule.deleteContacts();

            emailModule.addGoogleAccount(test_gmailaccount, test_gmailpassword);

            contactsModule.addContactsWithGmailInfo(contactWithGmailInfo);
            contactsModule.createGmailGroupAndAddContact(groupName, 1);
            contactsModule.filterContactWithGmailGroup(groupName, contactWithGmailInfo);

            commonModule.clearData("Contacts");
            contactsModule.addContactWithoutNumber(contactWithoutNumber);

            contactsModule.filterContactWithNumber(contactWithoutNumber);

            contactsModule.filterShowAllContacts();

            AcceptanceTestCase.assertTrue("contacts without number should show",
                    commonModule.waitForText(contactWithoutNumber, 3000));
            AcceptanceTestCase.assertTrue("gamil contacts should show",
                    commonModule.waitForText(contactWithGmailInfo, 3000));

        } catch (Exception ex) {
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
            commonModule.unLockScreen();
            try {
                contactsModule.filterShowAllContacts();
                contactsModule.deleteGroup(groupName);
                contactsModule.deleteContacts();
                commonModule.backOutToHomeScreen();
            } catch (Exception e) {
                System.out.println("RT_COMM_PIM_Contacts_Display_Group failed in finally");
            }
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173375
     * RT_COMM_Int_Music Player_Settings+Receive call during playing music
     *
     * @throws Exception
     */
    public void RT_COMM_Int_Music_Player_Settings_Receive_Call_During_Playing_Music_settings(
            String casename, HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.closeAllApps();

            walkman.playMusicUI();

            settingsModule.changeWallpaper();

            settingsModule.changeTheme();

            walkman.checkMusicStatus("playing");

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Int_Music_Player_Settings_Receive_Call_During_Playing_Music_receive_call(
            String casename, HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.closeAllApps();

            walkman.playMusicUI();

            telephonyModule.answerMTCall(callNumber);

            walkman.checkMusicStatus("pause");

            walkman.checkSearchKey();

            telephonyModule.endCall();

            walkman.checkMusicStatus("playing");

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Int_Music_Player_Settings_Receive_Call_During_Playing_Music_background_receive_call(
            String casename, HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.closeAllApps();

            walkman.playMusicOnBackground();

            walkman.checkMusicStatus("playing");

            telephonyModule.answerMTCall(callNumber);

            walkman.checkMusicStatus("pause");

            telephonyModule.endCall();

            walkman.checkMusicStatus("playing");

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Int_Music_Player_Settings_Receive_Call_During_Playing_Music_background_reject_call(
            String casename, HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.closeAllApps();

            walkman.playMusicOnBackground();

            telephonyModule.receiveMTCallButNotAnswer(callNumber);

            walkman.checkMusicStatus("playing");

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Int_Music_Player_Settings_Receive_Call_During_Playing_Music_background_ignore_call(
            String casename, HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.closeAllApps();

            walkman.playMusicOnBackground();

            // tell remote phone make a call to main phone
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_CALL);

            telephonyModule.waitingForCallComing();

            walkman.checkMusicStatus("pause");

            telephonyModule.endCall();

            walkman.checkMusicStatus("playing");

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_PIM_Calendar_My_Calendars_Accounts(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            // add gmail and facebook account and check
            gmail.addGoogleAccount(test_gmailaccount, test_gmailpassword);
            facebook.addFacebookAccount(facebookaccount, facebookpassword);
            calender.checkCalendarAccounts("Google", "Facebook");

            // remove gmail account and check
            settingsModule.removeAccount("Google", test_gmailaccount);
            calender.checkCalendarAccounts("Facebook");

            // remove facebook account and check
            settingsModule.removeAccount("Facebook", facebookaccount);
            calender.checkCalendarAccounts();

            // add gamil account again and check
            emailModule.addGoogleAccount(test_gmailaccount, test_gmailpassword);
            calender.checkCalendarAccounts("Google");
        } catch (Exception ex) {
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
        }
    }

    public void RT_COMM_PIM_Calendar_My_Calendars_Birthday_Link(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.addContactWithBirthday("Sam");
            calender.setBirthdayLink("on");
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            calender.checkBirthdaySetting("on");

            calender.setBirthdayLink("off");
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            calender.checkBirthdaySetting("off");
        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            contactsModule.deleteContacts();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_PIM_Calendar_My_Calendars_Add_Appointment(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            long startTimeInMilli = commonModule.getCurrTime() + 1000 * 60 * 5;
            calender.addNewCalendarEventAPI(startTimeInMilli, "waitForExpire",
                    "expire 5 minutes later", "UTC/GMT +2:00", true);

            commonModule.openNotificationBar();
            AcceptanceTestCase.assertTrue("alarm didn't expire in 1 minute",
                    commonModule.waitForText("waitForExpire", 60 * 1000));

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            calender.deleteAllTheEvent();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173386
     * RT_COMM_PIM_Contacts_Edit number before call
     *
     * @throws Exception
     */
    public void RT_COMM_PIM_Contacts_Edit_Number_Before_Call(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            telephonyModule.clearCallLog();
            contactsModule.startPhonebook();
            contactsModule.importContacts("Internal storage", "PIM00002.vcf");
            contactsModule.callByContact("A0", "‪000-000-0000‬");
            commonModule.backOutToHomeScreen();

            telephonyModule.editNumberBeforeCallAndMakeCall("A0", callNumber);

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
            commonModule.backOutToHomeScreen();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173392
     * RT_COMM_PIM_Contacts_Share contact
     *
     * @throws Exception
     */
    public void RT_COMM_PIM_Contacts_Share_Contact(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();
            contactsModule.startPhonebook();
            contactsModule.importContacts("Internal storage", "PIM00002.vcf");
            emailModule.addGoogleAccount(test_gmailaccount, test_gmailpassword);
            emailModule.addAccount(emailaccount, emailpassword, 3);
            commonModule.backOutToHomeScreen();

//            contactsModule.shareContactBy("A0","Gmail", null);

            contactsModule.shareContactBy("A0", "Email", null);

            contactsModule.shareContactBy("A0", "Messaging", callNumber);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173414
     * RT_COMM_Dialer_Display call button
     *
     * @throws Exception
     */
    public void RT_COMM_Dialer_Display_Call_Button(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            telephonyModule.makeMOCallWithoutAnswered("112");
            telephonyModule.endCall();

            telephonyModule.clearCallLog();
            telephonyModule.callWithoutNumber();
            commonModule.backOutToHomeScreen();

            telephonyModule.makeMOCallWithoutAnswered(callNumber);
            telephonyModule.endCall();

            telephonyModule.makeMOCallAndAnswered(callNumber);
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
            commonModule.backOutToHomeScreen();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_PIM_Calendar_Landscape_Mode(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {

            testCase.getUiDevice().setOrientationRight();

            long startTimeInMilli = commonModule.getCurrTime() + 1000 * 60 * 5;
            String subject = "landscape";
            calender.addNewCalendarEventAPI(startTimeInMilli, subject, "expire 5 minutes later",
                    "UTC/GMT +2:00", true);

            calender.switchCalendarViewInLaundscapeMode(subject);

            testCase.getUiDevice().setOrientationNatural();
        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            testCase.getUiDevice().setOrientationNatural();
            calender.deleteAllTheEvent();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Playstore_Install_and_Uninstall_from_Playstore(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            // precondition: uninstall the app if exist
            commonModule.launchAppBySearch("TED");
            commonModule.wait(5);
            commonModule.uninstallApp("TED");

            emailModule.addGoogleAccount(test_gmailaccount, test_gmailpassword);

            playStore.searchAndInstallAppInPlayStore("TED");

            commonModule.launchAppBySearch("TED");

            AcceptanceTestCase.assertTrue("App TED should be launched.",
                    commonModule.waitForPackage("com.ted.android", 5000));

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.unLockScreen();
            commonModule.uninstallApp("TED");
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Playstore_Install_and_Uninstall_from_FileManager(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            fileCommander.installAppFromFileCommander("bbc.mobile.news.ww-1.apk");

            commonModule.launchAppBySearch("BBC News");

            commonModule.uninstallApp("BBC News");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.unLockScreen();
            commonModule.uninstallApp("BBC News");
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173438
     * RT_COMM_Browser_Edit bookmark
     *
     * @throws Exception
     */
    public void RT_COMM_Browser_Edit_Bookmark(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        String bookmark1 = "Baidu";
        String bookmark2 = "Google";

        try {
            commonModule.clearData("Chrome");
            browser.startChrom();
            browser.loadWebPage("www.baidu.com");
            browser.addBroswerBookmark(bookmark1);
            commonModule.backOutToHomeScreen();

            browser.editBookmarkUrl(bookmark1, "www.google.com\n");

            browser.clearBookmarkNameAndSave(bookmark1);

            browser.editBookmarkName(bookmark1, bookmark2);

            browser.loadBookmark(bookmark2);

            browser.loadWebPage("www.gte.nu/To_Browsing/index.html");

            browser.addBroswerBookmark("GTE");

            browser.prepareEditBookmark("GTE");
        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173431
     * RT_COMM_Browser_Forward
     *
     * @throws Exception
     */
    public void RT_COMM_Browser_Forward(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.clearData("Chrome");

            browser.startChrom();

            browser.loadWebPage("www.baidu.com");

            browser.loadWebPage("www.google.com");

            browser.backwardWebPage();

            browser.forwardWebPage();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173422
     * RT_COMM_Facebook_Photos
     *
     * @throws Exception
     */
    public void RT_COMM_Facebook_Photos(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            facebook.launchFacebook(facebookaccount, facebookpassword);

            facebook.checkAllPhotosInFacebookAlbum();

            facebook.createNewFacebookAlbum("Aaa", "Test For RT", "Beijing");

            facebook.uploadANewPhotoViaTakePhoto("Aaa");

            facebook.sharePhotoToContact("Aaa");

            facebook.uploadANewPhotoFromMemory("Aaa");

        } catch (Exception ex) {
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
            facebook.launchFacebook(facebookaccount, facebookpassword);
            facebook.deleteFacebookAlbum("Aaa");
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Album_Mark_Several(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(2);

            albumModule.startAlbum();
            albumModule.markAndUnMarkOnePictureFromMenu();

            albumModule.markSeveralItemsAndDelete();

            albumModule.markItemsByLongTap(true);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Forward_Message_SMS(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            messagingModule.removeAllMessages();

            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SMS);

            messagingModule.openConversationByNameOrNumber(callNumber);

            messagingModule.forwordSMSMessage("Are you going to the party tonight?", callNumber);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Forward_Message_MMS(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            messagingModule.removeAllMessages();

            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_MMS);

            messagingModule.openConversationByNameOrNumber(callNumber);

            messagingModule.forwordSMSMessage("Do you like the picture and sound?", callNumber);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173435
     * RT_COMM_Browser_Send and Receive page link
     *
     * @throws Exception
     */
    public void RT_COMM_Browser_Send_And_Receive_Page_Link(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        String longUrl = "http://box.zhangmen.baidu.com/m?gate=1&ct=134217728&tn=baidumt,lonely%2021st%20Century%20&word=mp3";
//        String longUrl = "http://192.16.134.130/users/S700i_WAP/Long%20names/Long%20names/Long%20names.html";
        try {
            browser.startChrom();

            browser.loadWebPage(longUrl);

            browser.shareLinkPageByMessaging(callNumber);

            messagingModule.waitMessageSendSuccessfully();

            browser.startChrom();

            browser.loadWebPage(longUrl);
        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Email_Reply_reply_all(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);
            emailModule.addEmailAccountFromSettings(email2account, email2password);

            String subject = "test reply and reply all function";
            String content = "mail send from " + email2account + "to " + emailaccount;
            String replyContent = "reply content";
            String replyAllContent = "reply all content";

            emailModule.switchAccount(email2account);

            // account2 send mail to account1
            emailModule.sendEmail(email2account, emailaccount, subject, content, false);

            emailModule.switchAccount(emailaccount);

            // reply
            emailModule.openMailBySubject(subject);
            emailModule.replyEmail("Reply", replyContent);

            // reply all
            emailModule.openMailBySubject(subject);
            emailModule.replyEmail("Reply all", replyAllContent);

            // switch to account2
            emailModule.switchAccount(email2account);

            // check reply all result
            emailModule.openMailBySubject("RE: " + subject);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Email_Open_Save_the_Attachment_Music(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);

            String subject = "attach music";

            emailModule.sendEmailWithAttachment(emailaccount, subject, "MusicAttachment.mp3");

            emailModule.switchAccount(emailaccount);
            emailModule.openTheEmailWithMusicAsAttachment(emailaccount, subject);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Email_Open_Save_the_Attachment_Picture(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);

            String subject = "attach picture";

            emailModule.sendEmailWithAttachment(emailaccount, subject, "pic.JPG");

            emailModule.switchAccount(emailaccount);
            emailModule.openTheEmailWithPictureAsAttachment(emailaccount, subject);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173440
     * RT_COMM_Browser_Go to URL
     *
     * @throws Exception
     */
    public void RT_COMM_Browser_Go_To_URL(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            commonModule.clearData("Chrome");
            browser.startChrom();
            browser.loadWebPage("www.baidu.com");
            browser.addBroswerBookmark("Baidu");
            commonModule.backOutToHomeScreen();

            browser.startChrom();
            commonModule.setOrientationPortrait();
            browser.checkBrowserToolbar();
            browser.loadWebPage("www.sina.com.cn");

            commonModule.setOrientationLandscape();
            browser.checkBrowserToolbar();
            browser.loadWebPage("www.sina.com.cn");

            commonModule.setOrientationPortrait();
            browser.loadBookmark("Baidu");

            browser.longTapUrlToEnableMagnifier();
//            mediaModule.copyUrl();
//            messagingModule.sendMessageByPasteContent(callNumber);
//            mediaModule.loadWebPageInMessage(callNumber);
            browser.loadWebPage("www.baidu.com");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.setOrientationPortrait();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Messa_Email_Email_Accounts_Customization(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);
            emailModule.removeAccount(emailaccount);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173445
     * RT_COMM_FM Radio_MMI
     *
     * @throws Exception
     */
    public void RT_COMM_FM_Radio_MMI(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            mediaModule.launchRadioPlayer();

            mediaModule.checkFmRadioMmi();

            mediaModule.checkRadioDial();

            mediaModule.scrollUpOrDownRadioDial();

            mediaModule.dragRadioDialSlowly();

            commonModule.setOrientationLandscape();

            mediaModule.checkFmRadioMmi();

            mediaModule.changeVisualizerSetting("Gate");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.setOrientationPortrait();
            commonModule.backOutToHomeScreen();
            mediaModule.stopRadio();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173466
     * RT_COMM_Album_Delete item
     *
     * @throws Exception
     */
    public void RT_COMM_Album_Delete_Item(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(1);
            camera.recordVideo(10);
            commonModule.backOutToHomeScreen();

            albumModule.startAlbum();
            albumModule.selectPicture(1);
            albumModule.playVideo();
            testCase.pressBackNTimes(1);
            albumModule.selectDeleteButCancel();
            albumModule.deletePictureOrVideo();
            commonModule.backOutToHomeScreen();

            albumModule.startAlbum();
            albumModule.selectPicture(1);
            albumModule.selectDeleteButCancel();
            albumModule.deletePictureOrVideo();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173467
     * RT_COMM_Album_Use the captured photo
     *
     * @throws Exception
     */
    public void RT_COMM_Album_Use_The_Captured_Photo(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        String contactName = "AAA";

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(2);
            commonModule.backOutToHomeScreen();
            contactsModule.createContactsURI(contactName, callNumber,"asdfasfad@163.com");

            albumModule.startAlbum();
            albumModule.selectPicture(1);
            albumModule.setPictureAsContactPhoto(contactName);
            commonModule.backOutToHomeScreen();

            albumModule.startAlbum();
            albumModule.selectPicture(3);
            albumModule.setPictureAsWallpaper();
            commonModule.backOutToHomeScreen();

            telephonyModule.makeMOCallWithoutAnswered(callNumber);
            telephonyModule.endCall();

            albumModule.startAlbum();
            albumModule.selectPicture(1);
            albumModule.deletePictureOrVideo();
            albumModule.deletePictureOrVideo();
            commonModule.backOutToHomeScreen();

            telephonyModule.answerMTCall(callNumber);
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
            commonModule.delete(cameraFilePath);
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Messa_Embedded_Information(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();

            // embed call number
            String embeddedNumber = callNumber;
            commonModule.sendSMSCommand(callNumber,
                    CommandConstantsUtils.SMS_With_Specified_Content + "," + embeddedNumber);
            commonModule.wait(4);
            messagingModule.makeACallFromMessageContent(callNumber, embeddedNumber);

            // embed mail address
            String embeddedMailAddress = emailaccount;
            commonModule.sendSMSCommand(callNumber,
                    CommandConstantsUtils.SMS_With_Specified_Content + "," + embeddedMailAddress);
            commonModule.wait(4);
            messagingModule.sendMailFromMessageContent(callNumber, embeddedMailAddress,
                    "Send from message");

            // embed mail url
            String embeddedUrlAddress = "http://www.baidu.com";
            commonModule.sendSMSCommand(callNumber,
                    CommandConstantsUtils.SMS_With_Specified_Content + "," + embeddedUrlAddress);
            commonModule.wait(4);
            messagingModule.openURLFromMessageContent(callNumber, embeddedUrlAddress);

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

    public void RT_COMM_Messa_Email_Delete_email_attachment_deleteMail(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);
            emailModule.addEmailAccountFromSettings(email2account, email2password);

            emailModule.switchAccount(emailaccount);

            String subject1 = "delete from mail list";
            emailModule.sendEmail(email2account, emailaccount, subject1, "delete from mail list",
                    false);
            emailModule.checkMailReceived(email2account, false);
            emailModule.deleteLatestMailByLongPress(subject1);

            String subject2 = "delete after mail is opened";
            emailModule.sendEmail(email2account, emailaccount, subject2,
                    "delete after mail is opened", false);
            emailModule.checkMailReceived(email2account, true);
            emailModule.deleteMailWhenOpened(subject2);

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

    public void RT_COMM_Messa_Email_Delete_email_attachment_deleteAttachment(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            emailModule.addEmailAccountFromSettings(emailaccount, emailpassword);
            emailModule.switchAccount(emailaccount);

            String subject = "check delete attachment";
            emailModule.removeAttachmentDuringEditMail(email2account, subject);


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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173468
     * RT_COMM_Album_View in Landscape mode
     *
     * @throws Exception
     */
    public void RT_COMM_Album_View_In_Landscape_Mode(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(1);
            camera.recordVideo(20);
            commonModule.backOutToHomeScreen();

            commonModule.setAutoRotate("on");

            albumModule.startAlbum();
            commonModule.setOrientationLandscape();
            commonModule.wait(1);

            albumModule.selectPictureByLandscape(3);
            commonModule.wait(3);
            albumModule.flickLeftAndRightToCheckPictures();

            albumModule.startAlbum();
            albumModule.selectPictureByLandscape(1);
            commonModule.wait(3);
            albumModule.playVideo();
            commonModule.wait(3);
            commonModule.setOrientationPortrait();
            commonModule.wait(3);
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.setOrientationPortrait();
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173471
     * RT_COMM_FM Radio_Change volume
     *
     * @throws Exception
     */
    public void RT_COMM_FM_Radio_Change_Volume(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            mediaModule.launchRadioPlayer();

            mediaModule.pressRadioVolumeKey();

            mediaModule.longPressRadioVolumeDownKey();

            mediaModule.longPressRadioVolumeUpKey();

            mediaModule.pressRadioVolumeMuteKey();

            mediaModule.turnOffFMRadio();

            mediaModule.turnOnFMRadio();

            mediaModule.TurnOnOrOffSpeaker("ON");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            mediaModule.TurnOnOrOffSpeaker("OFF");
            mediaModule.turnOffFMRadio();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173474
     * RT_COMM_FM Radio_Radio on&off
     *
     * @throws Exception
     */
    public void RT_COMM_FM_Radio_Radio_On_Off(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            mediaModule.launchRadioPlayer();

            mediaModule.turnOffFMRadio();

            mediaModule.turnOnFMRadio();

            mediaModule.turnOffFMRadio();

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
            commonModule.backOutToHomeScreen();
            mediaModule.stopRadio();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173475
     * RT_COMM_FM Radio_Search
     *
     * @throws Exception
     */
    public void RT_COMM_FM_Radio_Search(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            mediaModule.launchRadioPlayer();

            mediaModule.searchChannel();

            mediaModule.checkAllChannelsByTapNextButton();

        } catch (Exception ex) {
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
            mediaModule.stopRadio();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173625
     * RT_COMM_IntMessage Tone + Incoming call
     *
     * @throws Exception
     */
    public void RT_COMM_IntMessage_Tone_Incoming_Call(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            settingsModule.setLongNotificationTone("Far Away From Home", "Groove Coverage");

            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SMS);

            telephonyModule.answerMTCall(callNumber);

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
            commonModule.backOutToHomeScreen();
            commonModule.clearNotificationsBar();
            settingsModule.setNotificationTone("Notification");
            commonModule.backOutToHomeScreen();
            telephonyModule.endCall();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Messa_Message_on_SIM_Copy_Delete(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            contactsModule.deleteContacts();

            String messageContentString = "Are you going to the party tonight?";
            messagingModule.removeAllMessages();
            commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_SMS);

            messagingModule.openConversationByNameOrNumber(callNumber);
            messagingModule.copyMessageToSimCard(messageContentString);

            commonModule.backOutToHomeScreen();

            messagingModule.simMessageCopyToPhoneOrDelete(messageContentString, "Copy to phone");

            messagingModule.simMessageCopyToPhoneOrDelete(messageContentString, "Delete message");


        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173507
     * RT_COMM_Messa_MMS SMS Convert
     *
     * @throws Exception
     */
    public void RT_COMM_Messa_MMS_SMS_Convert(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(1);

            messagingModule.prepareSMSMessage(CommandConstantsUtils.SMS_CONTENT_RECEIVE, callNumber);

            messagingModule.smsConvertToMmsByAddAttachment();

            messagingModule.mmsConvertToSmsByRemoveAttachment();

            messagingModule.smsConvertToMmsByAddSubject("RT test");

            messagingModule.mmsConvertToSmsByRemoveSubject();

            messagingModule.smsConvertToMmsByAddSubject("RT test");

            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173604
     * RT_COMM_PERF_Launch camera
     *
     * @throws Exception
     */
    public void RT_COMM_PERF_Launch_Camera(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.startCameraBySleepMode();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

	public void RT_COMM_Music_Basic_function(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            walkman.launchWALKMANPlayer();
            walkman.playMusicOnBackground();

            walkman.pausePlayMusicAndVerify();

            walkman.playNextMusic();

            commonModule.pressKey(KeyEvent.KEYCODE_MEDIA_FAST_FORWARD);

        } catch (Exception ex) {
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
        }
    }

    public void RT_COMM_Music_Playlist(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            walkman.gotoPlaylist();
            walkman.checkDefaultPlaylist();

            walkman.createPlaylist("MyNewPlaylist", true, 3);

            walkman.deletePlaylist("MyNewPlaylist");

        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173613
     * RT_COMM_Int_FM Radio+Camera or Music or Voice search
     *
     * @throws Exception
     */
    public void RT_COMM_Int_FM_Radio_Camera_Or_Music_Or_Voice_Search(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            mediaModule.launchRadioPlayer();

            mediaModule.turnOffFMRadio();

            camera.startCameraByPressCameraKey();

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);// Back to FM Radio.

            commonModule.setOrientationPortrait();

            mediaModule.turnOnFMRadio();

            walkman.playMusic();

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);// Back to FM Radio.

            mediaModule.TurnOnOrOffSpeaker("ON");

            voiceSearch.launchVoiceSearch();

        } catch (Exception ex) {
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
            walkman.stopMusicFromStatusBar();
            mediaModule.stopRadio();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173597
     * RT_COMM_View_Browser recent shots
     *
     * @throws Exception
     */
    public void RT_COMM_View_Browser_Recent_Shots(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraByMode("Superior auto");
            camera.takePictures(1);
            commonModule.backOutToHomeScreen();

            camera.startCameraByPressCameraKey();

            camera.openLatestPictureInCamera();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_PIM_Contacts_Join_and_Separate(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();

            contactsModule.createContactAPI("Ab", "123", 0, "");
            contactsModule.createContactAPI("Cd", "123", 0, "");

            contactsModule.linkAndUnlinkContacts("Ab", "Cd", "Cd");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173588
     * RT_COMM_CAM_Preview mode_Play video
     *
     * @throws Exception
     */
    public void RT_COMM_CAM_Preview_Mode_Play_Video_rear_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraByMode("Superior auto");

            camera.recordVideo(10);

            camera.playRecentVideoUntilEnd(10);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_CAM_Preview_Mode_Play_Video_front_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraByMode("Superior auto");

            camera.switchToFrontCamera();

            camera.recordVideo(10);

            camera.playRecentVideoUntilEnd(10);

        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173595
     * RT_COMM_CAM_Still_Photo Resolution
     *
     * @throws Exception
     */
    public void RT_COMM_CAM_Still_Photo_Resolution(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.createContactsURI("PhotoResolution", callNumber,"asefd@163.com");

            camera.launchCameraByMode("Manual");

            camera.selectAllPhotoResolutionAndTakePictures();

            camera.clickRecentShot();

            camera.setLastPictureAsContactPhoto("PhotoResolution");

            camera.setLastPictureAsWallpaper();

        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173572
     * RT_COMM_CAM_F_Video_Video size
     *
     * @throws Exception
     */
    public void RT_COMM_CAM_F_Video_Video_Size(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();

            camera.switchToFrontCamera();

            camera.selectAllFrontVideoResolutionAndRecordVideos(5 * 60);// 5 minutes.

        } catch (Exception ex) {
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
        }
    }

    public void RT_COMM_Messa_Edit_Message_using_shortcut_picture_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();

            messagingModule.prepareSMSMessage("insert shortcut",callNumber);

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);

            messagingModule.insertImageFromShortcut();
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

            messagingModule.insertCameraPicture("shortcut");
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Edit_Message_using_shortcut_sketch_location(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();

            messagingModule.prepareSMSMessage("insert sketch",callNumber);
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);

            messagingModule.insertSketchNote("shortcut");
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

            messagingModule.insertLocation("shortcut");
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173583
     * RT_COMM_CAM_Video_White balance
     * @throws Exception
     */
    public void RT_COMM_CAM_Video_White_Balance(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraByMode("Manual");

            camera.switchToVideoWithManualMode();

            camera.selectAllWhiteBalanceAndRecordVideos(5);

        } catch (Exception ex) {
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
        }
    }

	public void RT_COMM_Messa_Write_new_MMS_picture_sound_video_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();

            messagingModule.sendMMSWithVideo("with video record", callNumber, 3);

            //auto tool can't recognize 'Take photo' currently, so did't do insert picture from camera currently
            //messagingModule.sendMMSWithCameraPicture("insert picture from camera", callNumber, "plusIcon");

            messagingModule.sendMMSWithPictureAndSound("with picture and sound", callNumber);

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Messa_Write_new_MMS_sketch_location_contacts(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            messagingModule.removeAllMessages();
            contactsModule.deleteContacts();
            String contactName = "sendViaMessage";
            contactsModule.addContactsWithGmailInfo(contactName);

            messagingModule.createMessage();
            messagingModule.addRecipient(callNumber);

            messagingModule.insertSketchNote("plusIcon");
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

            messagingModule.insertLocation("plusIcon");
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

            messagingModule.insertContacts(contactName);
            messagingModule.sendMessageAndWaitMessageSendSuccessfully();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1178600
     * RT_COMM_ICS_PIM_Contacts_Myself and Group contact
     * @throws Exception
     */
    public void RT_COMM_ICS_PIM_Contacts_Myself_And_Group_Contact(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            // Precondition:
            emailModule.loginEmailSyncAutoServer(emailaccount, emailpassword);
            contactsModule.deleteContacts();
            contactsModule.createContactAPI("GroupContact1", callNumber, 1, emailaccount);
            contactsModule.createContactAPI("GroupContact2", callNumber2, 1, email2account);
            contactsModule.createContactWithTwoGroups("GroupContact0", callNumber, null, "Family",
                    "Friends");
            messagingModule.removeAllMessages();

            contactsModule.checkGroupMember("Colleagues", "GroupContact1", "GroupContact2");

            contactsModule.sendGroupMessage("Colleagues");

            contactsModule.sendGroupEmail("Colleagues");

            contactsModule.checkContactGroupInfo("GroupContact0", "Family", "Friends");

            contactsModule.editMyself(null, callNumber, null, true);

            contactsModule.shareContactBy("Myself", "Messaging", callNumber);

            contactsModule.shareContactBy("Myself", "Email", email2account);

            settingsModule.changeTheme();

            contactsModule.startPhonebook();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

	public void RT_COMM_Picture_view_and_slideshow_share_one_picture(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            facebook.addFacebookAccount(facebookaccount, facebookpassword);

            camera.launchCameraPhotoApplication();
            camera.takePictures(2);
            albumModule.startAlbum();

            albumModule.shareOnePictureToFacebook("share picture");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

    public void RT_COMM_Picture_view_and_slideshow_play_slideshow(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            facebook.addFacebookAccount(facebookaccount, facebookpassword);

            albumModule.startAlbum();

            albumModule.openTheFirstAlbumFromFacebook();

            albumModule.playSenseMeSlideshow("Zoom & Grid", "Default music");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

	public void RT_COMM_Multitasking(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            walkman.playMusicOnBackground();
            commonModule.pressKey(KeyEvent.KEYCODE_HOME);

            albumModule.startAlbum();
            commonModule.pressKey(KeyEvent.KEYCODE_HOME);

            commonModule.startAppFromRecentApp("Walkman", "com.sonyericsson.music");
            commonModule.pressKey(KeyEvent.KEYCODE_HOME);

            commonModule.startAppFromRecentApp("Album", "com.sonyericsson.album");

            commonModule.closeAllApps();
        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1178652
     * RT_COMM_Camera 7.0
     * @throws Exception
     */
    public void RT_COMM_Camera_7(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.takePhotoBySleepModeAndCheck();

            camera.startCameraBySleepMode();
            camera.takePhotoWhileRecordingVideoAndCheck();

            camera.takePhotosBeforeOrWhileRecordingVideoAndCheck();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    public void RT_COMM_Album_Send_photo(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();
        try {
            camera.takePictures(3);

            albumModule.shareOnePictureToEmail(emailaccount, "share picture to email");

            albumModule.shareOnePictureToMMS(callNumber, "share album picture to MMS");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173586
     * RT_COMM_CAM_Still_Selftimer
     * @throws Exception
     */
    public void RT_COMM_CAM_Still_Selftimer(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.changeSelftimerSetting("On (10 sec.)");

            camera.cancelTakeSelftimerPicture();

            camera.takeSelftimerPicture("On (10 sec.)");

            settingsModule.changeSoundMode("Ringer vibrate");
            camera.takeSelftimerPicture("On (10 sec.)");

            settingsModule.changeSoundMode("Ringer off");
            camera.takeSelftimerPicture("On (10 sec.)");

            settingsModule.changeSoundMode("Ringer on");
            camera.takeSelftimerPicture("On (10 sec.)");

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (AssertionFailedError ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } catch (Error ex) {
            commonModule.takeScreenShot(casename);
            throw ex;
        } finally {
            camera.changeSelftimerSetting("Off");
            commonModule.delete(cameraFilePath);
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173596
     * RT_COMM_CAM_Simple mode capturing
     * @throws Exception
     */
    public void RT_COMM_CAM_Simple_Mode_Capturing(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.startCameraByPressCameraKey();

            camera.selectOneEffectFromCameraSettings("Superior auto");

            camera.activeGeoTagFromCameraSettings();

            camera.pressCameraButton();

            camera.checkPictures(1);

        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173587
     * RT_COMM_CAM_Preview mode_View photo
     *
     * @throws Exception
     */
    public void RT_COMM_CAM_Preview_Mode_View_Photo_rear_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(2);
            commonModule.backOutToHomeScreen();

            camera.launchCameraByMode("Superior auto");
            camera.checkPictures(2);

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);// Back to viewfinder.
            camera.clickRecentShot();

            camera.startCameraByPressCameraKey();
            camera.clickRecentShot();
            albumModule.checkAllMoreMenuInAlbumMenu();

        } catch (Exception ex) {
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
        }
    }

    public void RT_COMM_CAM_Preview_Mode_View_Photo_front_camera(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraPhotoApplication();
            camera.takePictures(2);
            commonModule.backOutToHomeScreen();

            camera.launchCameraByMode("Superior auto");
            camera.switchToFrontCamera();
            camera.checkPictures(2);

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);// Back to viewfinder.
            camera.clickRecentShot();

            camera.startCameraByPressCameraKey();
            camera.switchToFrontCamera();
            camera.clickRecentShot();
            albumModule.checkAllMoreMenuInAlbumMenu();

        } catch (Exception ex) {
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
        }
    }

    /**
     * Case in Sony ALM: Domain: PSV Project: PSV ID: 1173593
     * RT_COMM_CAM_F_Video_Touch capture
     *
     * @throws Exception
     */
    public void RT_COMM_CAM_F_Video_Touch_Capture(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            camera.launchCameraByMode("Superior auto");
            camera.switchToFrontCamera();
            camera.launchCameraPhotoApplication();

            camera.setTouchCaptureSettingOn();

            camera.touchScreenToCapture();// Start record video.
            commonModule.wait(3);

            camera.touchScreenToCaptureWhileRecordVideo();

        } catch (Exception ex) {
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
        }
    }

	 public void RT_COMM_Messa_Email_Settings_changeDisplayName(String casename,
            HashMap<String, String> paras) throws Exception {

        commonModule.backOutToHomeScreen();
        try {
           emailModule.addEmailAccountFromSettings(email2account, email2password);

           String changedName = "nameChanged";
           emailModule.changeDisplayName(email2account, changedName);
           commonModule.backOutToHomeScreen();

           emailModule.sendEmail(email2account, email2account, "change name", "rt test: change name", false);

           emailModule.checkMailReceived(changedName, false);
        } catch (Exception ex) {
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
            emailModule.changeDisplayName(email2account, email2account);
            commonModule.backOutToHomeScreen();
        }
    }

	public void RT_COMM_PIN_Contacts_Favourites_Add_And_Remove(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
            contactsModule.deleteContacts();
            contactsModule.startPhonebook();
            contactsModule.importContacts("Internal storage", "PIM00001.vcf");
            contactsModule.addContactWithNumberWithoutName("123456789");

            contactsModule.addAllContactsToFavorite("Common","Image","123456789","No Number But Address","No Image","Qwertyuioplkjhgfdsazxcvbnmqwertyuiopasdfghjklzxcvbnm");

            contactsModule.deleteFavoriteContact("Common");
        } catch (Exception ex) {
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
        }
    }

	public void RT_COMM_Dialer_Save_the_number_of_dialer_text_box_into_contact(String casename, HashMap<String, String> paras)
            throws Exception {

        commonModule.backOutToHomeScreen();

        try {
        	telephonyModule.launchPhone();

        	contactsModule.saveContactFromDialer("","Empty Phone Number");

        	telephonyModule.launchPhone();

        	contactsModule.saveContactFromDialer("*#1234567890","Symbols Phone Number");

        	telephonyModule.launchPhone();

        	contactsModule.saveContactFromDialer(callNumber,"Normal Phone Number");

        	commonModule.sendSMSCommand(callNumber, CommandConstantsUtils.COMMAND_CALL);

        	telephonyModule.answerIncomingCall();

        	telephonyModule.checkOngingCallName("Normal Phone Number");

        	telephonyModule.endCall();

        	telephonyModule.makeMOCallAndAnswered(callNumber);

        	telephonyModule.checkOngingCallName("Normal Phone Number");


        } catch (Exception ex) {
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
            telephonyModule.endCall();
            contactsModule.deleteContacts();
            commonModule.unLockScreen();
            commonModule.backOutToHomeScreen();
        }
    }

	public void RT_COMM_Settings_Date_Time_Change_Date_Format(String casename, HashMap<String, String> paras)
            throws Exception {
    			long date = System.currentTimeMillis()+86400000;
        commonModule.backOutToHomeScreen();

        try {
            settingsModule.checkDateTimeSetting();
            settingsModule.setDateFormat();
            settingsModule.setDateManual(date);
            settingsModule.setTimeZoneManual();
            settingsModule.change24HourFormat();
            commonModule.unLockScreen();

        } catch (Exception ex) {
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
        }
    }

	public void RT_COMM_Messa_Email_Settings_checkFrequency(String casename, HashMap<String, String> paras)
            throws Exception {
        commonModule.backOutToHomeScreen();

        try {
            emailModule.addEmailAccountFromSettings(email2account, email2password);

            emailModule.changeCheckFrequency(email2account, "Every 10 minutes");
            commonModule.backOutToHomeScreen();

            emailModule.sendEmail(email2account, email2account, "change check frequency", "test check frequency", false);

            //wait 5 minutes
            commonModule.wait(5*60);
            commonModule.openNotificationBar();
            AcceptanceTestCase.assertTrue("Mail shouldn't been received within 10 minutes", !commonModule.waitForText(email2account, 2000));
            commonModule.backOutToHomeScreen();

            //wait another 5 minutes
            commonModule.wait(5*60);
            emailModule.checkMailReceived(email2account, false);

        } catch (Exception ex) {
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
            emailModule.changeCheckFrequency(email2account, "Automatic (Push)");
            commonModule.backOutToHomeScreen();
        }
    }

	public void RT_COMM_SECU_Unlock_Screen_lock(String casename, HashMap<String, String> paras)
            throws Exception {
        commonModule.backOutToHomeScreen();

        try {

            lockScreen.lockScreen();
            commonModule.wait(3);

            lockScreen.tryToUnlockScreenWithHWKey();
            lockScreen.tryToUnlockScreenWithVirtualKey();
            lockScreen.tryToUnlockScreenBySwipeButNotReachTheGoalPosition();

            //unlock screen by swipe
            lockScreen.unlockScreen();
            lockScreen.longPressPowerKeyAndCheck();

        } catch (Exception ex) {
            commonModule.takeScreenShot(casename);
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

