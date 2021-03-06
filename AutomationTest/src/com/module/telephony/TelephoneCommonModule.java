
package com.module.telephony;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;

import java.util.HashMap;

public class TelephoneCommonModule implements ITelephony {

    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    String netWorkModeString = "Network Mode";

    public static String TAG = "Reliability";

    public void setNetWorkModeString(String netWorkModeString) {
        this.netWorkModeString = netWorkModeString;
    }

    public TelephoneCommonModule(AcceptanceTestCase testCase) throws PropertyNotFoundException {
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);

        this.moduleData = ModuleDataParser.getModuleData("telephony");
    }

    public TelephoneCommonModule() {

    }

    public void launchPhone() {

        testCase.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");

        commonModule.wait(2);
    }

    public void launchSettings() {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        commonModule.waitForText("Settings", 2000);
    }

    @SuppressWarnings("static-access")
    public void makeMOCallAndAnswered(String number) throws UiObjectNotFoundException {
        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        launchPhone();

        if (!commonModule.isResourceId(moduleData.get("Number_Editor"))) {
            commonModule.clickResourceId(moduleData.get("Number_Keyboard_Icon"));
            commonModule.wait(1);
            commonModule.clickResourceId(moduleData.get("Number_Editor"));
        }

        testCase.enterText(number);

        // press Call button
        commonModule.pressKey(KeyEvent.KEYCODE_CALL);
        commonModule.wait(3);

        // SIP call alert
        if (commonModule.waitForText("Make call", 2000)) {
            commonModule.clickText("Internet call");
        }

        if (commonModule.isTextExists("Select Account")) {
            String test_sipaccount = TestDataExtract.test_sipaccount;;
            commonModule.clickText(test_sipaccount);
        }

        for (int i = 0; i < 10; i++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is sent out");
                break;
            } else {
                commonModule.wait(2);
                if (i == 9) {
                    testCase.assertTrue("Call cannot be sent out!", false);
                }
            }
        }
        verifyCallAnswered();
    }

    public void makeConferenceCall(String number1, String number2) throws UiObjectNotFoundException {
        // call partner phone
        makeMOCallAndAnswered(number1);

        // call third phone
        if (commonModule.isTextExists("Add call")) {
            commonModule.clickText("Add call");
        } else {
            commonModule.clickResourceId(moduleData.get("Dialer_Icon_Id"));// click dialer button.
        }

        testCase.enterText(number2);
        testCase.assertTextPresent(number2);
//        commonModule.clickIdWithInstance("com.android.phone:id/icbp_btn_icon", 0);// click call
        commonModule.clickResourceId(moduleData.get("Second_Call_Button"));

        verifyCallAnswered();

        // click merge call icon
        commonModule.clickResourceId(moduleData.get("Merge_Calls_Icon"));

        AcceptanceTestCase.assertTrue("Conference should make up with text 'Conference'",
                commonModule.waitForText("Conference", 10 * 1000)
                        || commonModule.waitForText("Conference call", 10 * 1000));
    }

    public void endCall() {
        // press end call button
        // waiting for partner phone verify call have been connected, so wait 2s
        testCase.sleep(2 * 1000);
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_ENDCALL);
        testCase.sleep(5 * 1000);

        commonModule.unLockScreen();
    }

    public void verifyCallAnswered() throws UiObjectNotFoundException {
        commonModule.waitForResourceId(moduleData.get("Call_Timer_Id"), 10*1000);
        for (int i = 0; i < 10; i++) {
            String startTime = new UiObject(new UiSelector().resourceId(moduleData
                    .get("Call_Timer_Id"))).getText();

            if (!startTime.matches("00:00")) {
//                    && commonModule.isResourceId(moduleData.get("End_Call_Button_Id"))) {
                Log.i(TAG, "Call is answered.............");
                commonModule.wait(5);
                break;
            } else {
                Log.i(TAG, "Waiting for call");
                commonModule.wait(2);
                if (i == 9) {
                    testCase.failTest("Call is not answered");
                }
            }
        }

    }

    public void verifyCallOngoingWithinSpecifiedTime(int during) throws UiObjectNotFoundException {
        // get the call time
        String startTime = new UiObject(
                new UiSelector().resourceId(moduleData.get("Call_Timer_Id"))).getText();
        int minutes1 = Integer.valueOf(startTime.split(":")[0]) * 60;
        System.out.println("minutes1:" + minutes1);
        int seconds1 = Integer.valueOf(startTime.split(":")[1]);
        System.out.println("seconds1:" + seconds1);

        testCase.sleep((during + 5) * 1000);

        String endTime = new UiObject(new UiSelector().resourceId(moduleData.get("Call_Timer_Id")))
                .getText();
        int minutes2 = Integer.valueOf(endTime.split(":")[0]) * 60;
        int seconds2 = Integer.valueOf(endTime.split(":")[1]);
        System.out.println("seconds2:" + seconds2);
        AcceptanceTestCase.assertTrue("Call ongoing unnormal during " + during, (minutes2
                + seconds2 - minutes1 - seconds1) >= during);

    }

    @Override
    public void hangUpIncomingCall() {

        waitingForCallComing();
        testCase.pressKey(KeyEvent.KEYCODE_ENDCALL);

    }

    @Override
    public void answerIncomingCall() {
        waitingForCallComing();
        testCase.pressKey(KeyEvent.KEYCODE_CALL);

        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        for (int j = 0; j < 10; j++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is answerd");
                break;
            }else {
                commonModule.wait(2);
                if (j == 9) {
                    testCase.assertTrue("Call cannot be answered!", false);
                }
            }
        }
    }

    @Override
    public void rejectIncomingCallWithMessage(String textMessage) {
        waitingForCallComing();

        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();
//        testCase.getUiDevice().swipe(width / 2, height, width / 2, height / 2, 80);
        commonModule.wait(2);
        testCase.getUiDevice().swipe(width / 2, height, width / 2, height / 2, 80);
        if (commonModule.isTextExists("Response options")) {
            testCase.click("Reject with message");
            commonModule.wait(2);
        }

//        testCase.click(textMessage);
        commonModule.scrollFindText(textMessage);
    }

    public void changeNetworkMode(String mode) {

        // open network mode setting
        testCase.launchApp("com.android.phone", "com.android.phone.MobileNetworkSettings");

        testCase.clickItemWithText(netWorkModeString);

        String modeToBeSelected = null;
        if (mode.equalsIgnoreCase("2g")) {
            modeToBeSelected = "GSM only";
        } else if (mode.equalsIgnoreCase("3g")) {
            modeToBeSelected = "WCDMA only";
        } else if (mode.equalsIgnoreCase("3g prefer")) {
            modeToBeSelected = "WCDMA (preferred)/GSM";
        } else if (mode.equalsIgnoreCase("4g prefer")) {
            modeToBeSelected = "LTE (preferred)/WCDMA/GSM";
        }

        testCase.clickItemWithText(modeToBeSelected);

        commonModule.backOutToHomeScreen();

    }

    public void anwserCall() {

        testCase.sleep(2000);
        commonModule.pressKey(KeyEvent.KEYCODE_CALL);
    }

    public void answerMTCall(String remoteNumber) {

        // tell remote phone make a call to main phone
        commonModule.sendSMSCommand(remoteNumber, CommandConstantsUtils.COMMAND_CALL);

        answerIncomingCall();
    }

    @Override
    public void tapLouderSpeakerIcon() throws UiObjectNotFoundException {
        // press loud speaker button
        if (commonModule.isResourceId(moduleData.get("Loud_Speaker_Button"))) {
            commonModule.clickResourceId(moduleData.get("Loud_Speaker_Button"));
        } else {
            commonModule.clickIdWithInstance("com.android.phone:id/icbp_btn_icon", 1);
        }
    }

    public void verifyCallThroughBluetoothDevice(int period) throws UiObjectNotFoundException {
        // Select BTH call mode button.
        commonModule.clickIdWithInstance("com.android.phone:id/icbp_btn_icon", 1);
        commonModule.waitForText("Bluetooth device", 3000);

        commonModule.clickText("Bluetooth device");
        commonModule.wait(2);
        testCase.assertViewWithIdPresent("elapsedTime");
        commonModule.wait(period);
    }

    @SuppressWarnings("static-access")
    public void verifySkypeLaunched(int waitTime) {

        testCase.assertTrue(
                "Skype cannot launched in time",
                commonModule.waitForResourceId("com.skype.raider:id/tab_title", waitTime * 1000)
                        || commonModule.waitForResourceId("com.skype.raider:id/sign_in_skype_btn",
                                waitTime * 1000));
    }

    @SuppressWarnings("static-access")
    public void makeSkypeCallAndAnswered(String callAccount) throws UiObjectNotFoundException {
        commonModule.clickText("People");
        commonModule.clickText(callAccount);
        commonModule.clickResourceId("com.skype.raider:id/chat_menu_item_call_options");
        testCase.assertTrue("Skype call connect failed",
                commonModule.waitForTextContains("00:", 30 * 1000));
//                commonModule.waitForId("avatar_container", 30000));
    }

    public void launchSkype() {
        testCase.launchApp("com.skype.raider", "com.skype.raider.Main");
    }

    @SuppressWarnings("static-access")
    public void loginSkype(String skypeAccount, String skypePassword) {
        if (testCase.isViewWithIdPresent("sign_in_skype_btn")) {
            testCase.clickId("sign_in_skype_btn");

            testCase.clickId("signin_skypename");
            testCase.enterText(skypeAccount);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(2);

            testCase.clickId("signin_password");
            testCase.enterText(skypePassword);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            testCase.clickId("sign_in_btn");
            testCase.assertTrue("Skype cannot login in 2 minutes",
                    commonModule.waitForResourceId("com.skype.raider:id/tab_title", 120 * 1000));

        } else if (testCase.isViewWithIdPresent("tab_title")) {
            Log.i(TAG, "Skype is already login");
        }

    }

    public void endSkypeCall() {
        if (testCase.isViewWithIdPresent("call_end_button")) {
            testCase.clickId("call_end_button");
        }
    }

    public void verifySkypeInCallStatus() {
        AcceptanceTestCase.assertTrue("Skype call break!",
                commonModule.waitForResourceId("com.skype.raider:id/avatar_container", 10000));
    }

    public void configSipSetting(String value) {
        testCase.launchApp("com.android.phone", "com.android.phone.CallFeaturesSetting");
        commonModule.scrollFindTextNotClick("Other call settings");
        if (value.equals("on")) {
            if(testCase.isViewWithTextPresent("Phone account settings")){
                testCase.click("Phone account settings");
                testCase.click("Use SIP calling");
                testCase.click("For all calls");
            } else {
                commonModule.clickListItemwithText("Use Internet calling");
                testCase.click("Use SIP with network access");
            }
        } else if (value.equals("off")) {
            if(testCase.isViewWithTextPresent("Phone account settings")){
                testCase.click("Phone account settings");
                testCase.click("Use SIP calling");
                testCase.click("Only for SIP calls");
            } else {
                commonModule.clickListItemwithText("Use Internet calling");
                testCase.click("Only to SIP address");
            }
        } else if (value.equals("ask")) {
            commonModule.clickListItemwithText("Use Internet calling");
            testCase.click("Always ask");
        }
        commonModule.backOutToHomeScreen();
    }

    @SuppressWarnings("static-access")
    public void addSipAccount(String account, String password, String server)
            throws UiObjectNotFoundException {
        testCase.launchApp("com.android.phone", "com.android.phone.CallFeaturesSetting");
        commonModule.scrollFindTextNotClick("Other call settings");

        if (testCase.isViewWithTextPresent("Phone account settings")) {// For L.
            testCase.click("Phone account settings");
        } else {// For Shinano.
            commonModule.clickListItemwithText("Accounts");
        }
        if (!testCase.isCheckboxChecked("checkbox")) {
            commonModule.clickResourceId("android:id/checkbox");
        }
        // For L.
        if (testCase.isViewWithTextPresent("SIP accounts")) {
            testCase.click("SIP accounts");
        }
        testCase.click("Add account");
        testCase.click("Username");
        testCase.enterText(account);
        testCase.click("OK");

        testCase.click("Password");
        testCase.enterText(password);
        testCase.click("OK");

        testCase.click("Server");
        testCase.enterText(server);
        testCase.click("OK");

        commonModule.clickListItemwithText("Optional settings");
        if (testCase.isViewWithTextPresent("Outbound proxy address")) {
            commonModule.clickText("Outbound proxy address");
        } else {
            commonModule.clickListItemwithText("Outgoing proxy address");
        }
        testCase.enterText(server);
        testCase.click("OK");

        testCase.click("Save");
        if (commonModule.isTextExists("SIP accounts")) {
            commonModule.clickDescription("Navigate up");
            commonModule.wait(1);
            testCase.click("SIP accounts");
        }

        boolean sip1 = commonModule.waitForText("Primary account. Can receive calls now", 20000);
        boolean sip2 = commonModule.waitForText("Receiving calls.", 20000);
        testCase.assertTrue("SIP call account is not ready, cannot make sip call", sip1 || sip2);
        commonModule.backOutToHomeScreen();

    }

    public void simInsertContactSIM1FullAPI(String contact) {
        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String a = tm.getSimSerialNumber();
        Log.i("sssssssssss", ">>>>>>" + "new sim contact uri, " + a);

        ContentResolver resolver = testCase.getInstrumentation().getContext().getContentResolver();
        ContentValues values = new ContentValues();
        Uri uri = Uri.parse("content://icc/adn");
        values.put("tag", contact);
        values.put("number", "10086");
        Uri newSimContactUri = resolver.insert(uri, values);
        Log.d("1023", ">>>>>>" + "new sim contact uri, " + newSimContactUri.toString());

    }

    public void clearCallLog() throws UiObjectNotFoundException {
        launchPhone();

        if (commonModule.waitForText("Call log empty", 3000)) {
            return;
        }

        // press more options
        commonModule.pressMoreOption();
        if (commonModule.waitForText("Clear call log", 3000)) {
            commonModule.clickText("Clear call log");

            commonModule.waitForText("The entire call log will be cleared", 3000);
            commonModule.clickText("Clear");
        }

        if(commonModule.isTextExists("Missing Voicenumber")){
            commonModule.clickText("OK");
        }

        commonModule.backOutToHomeScreen();

    }

    public void receiveMTCallButNotAnswer(String remoteNumber) {
        // tell remote phone make a call to main phone
        commonModule.sendSMSCommand(remoteNumber, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        endCall();
    }

    public void checkCallLog(String... numbers) {
        launchPhone();
        for (String number : numbers) {
            if (commonModule.waitForText(number, 5000)) {
                AcceptanceTestCase.assertTrue(number + " not display on call log",
                        commonModule.waitForText(number, 5000));
                break;
            }
        }
    }

    public void makeMOCallWithoutAnswered(String number) throws UiObjectNotFoundException {
        launchPhone();

        testCase.enterText(number);

        // press Call button
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/call_button");

        AcceptanceTestCase.assertTrue("Main call should display text 'calling'",
                commonModule.waitForText("Calling", 5 * 1000));

    }

    public void makeMOCallWithFlightMode(String number) throws UiObjectNotFoundException {
        launchPhone();

        testCase.enterText(number);

        // press Call button
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/call_button");

        AcceptanceTestCase.assertTrue("Main call should display text 'calling'", commonModule
                .waitForText("To place a call, first turn off Airplane mode.", 5 * 1000));

        commonModule.clickText("OK");
        commonModule.backOutToHomeScreen();
    }

    public void replaceCurrentCall(String remoteNumber) throws UiObjectNotFoundException {

        // tell remote phone make a call to main phone
        commonModule.sendSMSCommand(remoteNumber, CommandConstantsUtils.COMMAND_CALL);

        endCurrentCallAndAnswer();
    }

    public void endCurrentCallAndAnswer() throws UiObjectNotFoundException {
        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        int i = 0;
        waitingForCallComing();

        commonModule.clickText("End current call and answer");
        commonModule.wait(5);

        for (int j = 0; j < 10; j++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is answerd");
                break;
            } else {
                commonModule.wait(2);
                if (j == 9) {
                    testCase.assertTrue("Call cannot be answered!", false);
                }
            }
        }
    }

    public void checkIsIncomingCall() {
        waitingForCallComing();

        endCall();
    }

    public void checkAnswerMachineNotAcceptIncomingCall(String number, int pickUpTime,
            String greetingName, int greetingTime) {

        commonModule.sendSMSCommand(number, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        commonModule.wait(pickUpTime);// Wait for 'greeting' exist.

        AcceptanceTestCase.assertTrue("Pick up time set failed.",
                commonModule.waitForText("Playing \"" + greetingName + "\"", 10 * 1000));

        commonModule.wait(greetingTime+5);

        AcceptanceTestCase.assertTrue(
                "Pick up time set failed.",
                commonModule.waitForText("Recording", 10 * 1000)
                        && !commonModule.isTextExists("Playing \"" + greetingName + "\""));

        commonModule.wait(5);// Wait for record voice message of call
                             // originator.

        endCall();
    }

    public void checkAnswerMachineAnswerIncomingCall(String number, int pickUpTime,
            String greetingName) {
        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        commonModule.sendSMSCommand(number, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        commonModule.wait(pickUpTime);// Wait for 'greeting' exist.
        AcceptanceTestCase.assertTrue("Pick up time set failed.",
                commonModule.waitForText("Playing \"" + greetingName + "\"", 10 * 1000));

        testCase.pressKey(KeyEvent.KEYCODE_CALL);

        for (int j = 0; j < 10; j++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is answerd");
                break;
            } else {
                commonModule.wait(2);
                if (j == 9) {
                    testCase.assertTrue("Call cannot be answered!", false);
                }
            }
        }

        commonModule.wait(3);

        endCall();
    }

    public void checkIncomingCallWithAnswerMachineOff(String number, int pickUpTime,
            String greetingName) {

        commonModule.sendSMSCommand(number, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        commonModule.wait(pickUpTime);// Wait for 'greeting' exist.
        AcceptanceTestCase.assertTrue("Answering machine still work.",
                !commonModule.isTextExists("Playing \"" + greetingName + "\""));

        commonModule.wait(3);

        endCall();
    }

    public void rejectIncomingCallWithAnswerMachine(String number, String greetingName,
            int greetingTime) throws UiObjectNotFoundException {

        commonModule.sendSMSCommand(number, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        commonModule.wait(2);
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();
        testCase.getUiDevice().swipe(width/2, height, width/2, height/2, 100);

        commonModule.wait(2);

        commonModule.clickText("Reject with Answering Machine");
        AcceptanceTestCase.assertTrue("Reject incoming call with answering machine failed.",
                commonModule.waitForText("Playing \"" + greetingName + "\"", 3000));

        commonModule.wait(greetingTime);
        commonModule.wait(6);

        AcceptanceTestCase.assertTrue("Playing greeting failed or Recording message failed.",
                commonModule.isTextExists("Recording"));

        commonModule.wait(5);// Wait for record voice message of call
                             // originator.

        endCall();
    }

    public void playVoiceMessageInCallLog() throws UiObjectNotFoundException {
        launchPhone();
        commonModule.waitForDescription("Play voice message", 2000);

        commonModule.clickDescription("Play voice message");
        commonModule.wait(5);
    }

    public void enableAnswerMachineFromCallLog(String greetingName, int recordTime)
            throws UiObjectNotFoundException {
        launchPhone();

        // press more options
        commonModule.pressMoreOption();
        if (commonModule.waitForText("Enable answering machine", 3000)) {
            commonModule.clickText("Enable answering machine");
            commonModule.waitForText("Answering Machine", 2000);
        } else if (commonModule.waitForText("Answering Machine settings", 3000)) { // For
                                                                                   // Leo.
            commonModule.clickText("Answering Machine settings");
            commonModule.waitForText("Answering Machine", 2000);
        }
        UiObject checkbox = new UiObject(new UiSelector().resourceId("android:id/checkbox"));
        if (checkbox.isChecked()) {
            return;
        } else if (!checkbox.isChecked()) {
            checkbox.click();
            if (commonModule
                    .isTextExists("You must record a greeting before you can use Answering Machine.")) {
                commonModule.clickText("OK");
                commonModule.waitForDescription("Record", 2000);

                commonModule.clickDescription("Record");// Record start.
                commonModule.waitForDescription("Stop", 2000);

                commonModule.wait(recordTime);// Wait for record greeting.

                commonModule.clickDescription("Stop");// Stop record.
                commonModule.waitForDescription("Play", 2000);

                commonModule.clickDescription("Play");
                commonModule.wait(5);

                commonModule.clickDescription("Stop");
                commonModule.waitForText("Save", 2000);

                commonModule.emptyEditorByInstance(0);
                testCase.enterText(greetingName);
                commonModule.clickText("Save");
            }
            commonModule.wait(2);
            AcceptanceTestCase
                    .assertTrue("Turn on Answering Machine failed.", checkbox.isChecked());
        }
    }

    public void disableAnswerMachineFromCallLog() throws UiObjectNotFoundException {
        launchPhone();

        // press more options
        commonModule.pressMoreOption();
        if (commonModule.waitForText("Enable answering machine", 3000)) {
            return;
        }
        if (commonModule.waitForText("Disable answering machine", 3000)) {
            commonModule.clickText("Disable answering machine");
            commonModule.waitForText("Answering Machine", 2000);
        } else if (commonModule.waitForText("Answering Machine settings", 3000)) { // For
                                                                                   // Leo.
            commonModule.clickText("Answering Machine settings");
            commonModule.waitForText("Answering Machine", 2000);
        }
        UiObject checkbox = new UiObject(new UiSelector().resourceId("android:id/checkbox"));

        if (!checkbox.isChecked()) {
            return;
        } else if (checkbox.isChecked()) {
            checkbox.click();
            commonModule.wait(2);
            AcceptanceTestCase.assertTrue("Turn off Answering Machine failed.",
                    !checkbox.isChecked());
        }
    }

    public void acceptIncomingCallDuringRecordMessage(String number, int pickUpTime,
            String greetingName, int greetingTime) {

        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        commonModule.sendSMSCommand(number, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        commonModule.wait(pickUpTime);// Wait for 'greeting' exist.
        commonModule.wait(2);
        AcceptanceTestCase.assertTrue("Pick up time set failed.",
                commonModule.isTextExists("Playing \"" + greetingName + "\""));

        commonModule.wait(greetingTime);
        commonModule.wait(8);
        AcceptanceTestCase.assertTrue("Playing greeting failed or Recording message failed.",
                commonModule.isTextExists("Recording"));

        commonModule.wait(5);// Wait for record voice message of call
                             // originator.
        testCase.pressKey(KeyEvent.KEYCODE_CALL);

        for (int j = 0; j < 10; j++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is answerd");
                break;
            } else {
                commonModule.wait(2);
                if (j == 9) {
                    testCase.assertTrue("Call cannot be answered!", false);
                }
            }
        }

        commonModule.wait(3);

        endCall();
    }

    public void setCallWaiting(String value) throws UiObjectNotFoundException {
        launchPhone();
        if (!commonModule.isResourceId(moduleData.get("Number_Editor"))) {
            commonModule.clickResourceId(moduleData.get("Number_Keyboard_Icon"));
            commonModule.wait(1);
            commonModule.clickResourceId(moduleData.get("Number_Editor"));
        }

        commonModule.wait(2);
        if (value.equals("on")) {
            testCase.enterText("*43#");
        } else if (value.equals("off")) {
            testCase.enterText("#43#");
        }
        testCase.pressKey(KeyEvent.KEYCODE_CALL);
        commonModule.wait(5);
        testCase.click("OK");
    }

    public void editMessageToRejectCall(String newMsg) throws UiObjectNotFoundException {
        enterCallSettings();
        commonModule.waitForText("Reject call with message", 2000);

        commonModule.clickText("Reject call with message");
        commonModule.waitForText("Edit messages", 2000);

        UiObject msg = new UiObject(new UiSelector().resourceId("android:id/title").instance(2));
        msg.click();
        commonModule.waitForResourceId("android:id/edit", 2000);

        commonModule.emptyEditorByInstance(0);
        testCase.enterText(newMsg);

        commonModule.clickText("OK");
        AcceptanceTestCase.assertTrue(
                "Edit messages to reject call failed.",
                commonModule.waitForText(newMsg, 2000)
                        && commonModule.waitForText("Edit messages", 2000));
    }

    public void enterAnsweringMachineFromSettings() throws UiObjectNotFoundException {
        enterCallSettings();
        commonModule.waitForText("Answering Machine", 2000);

        commonModule.clickText("Answering Machine");

        if (commonModule.waitForText("OK", 2000)) {
            commonModule.clickText("OK");
        }

        AcceptanceTestCase.assertTrue(
                "Enter Answering Machine from Settings failed.",
                commonModule.waitForText("Answering Machine", 2000)
                        && commonModule.waitForText("Greetings", 2000));
    }

    public void turnOnAnsweringMachine(String greetingName, int recordTime)
            throws UiObjectNotFoundException {
        enterAnsweringMachineFromSettings();
        UiObject checkbox = new UiObject(new UiSelector().resourceId("android:id/checkbox"));
        if (checkbox.isChecked()) {
            return;
        } else if (!checkbox.isChecked()) {
            checkbox.click();
            if (commonModule
                    .isTextExists("You must record a greeting before you can use Answering Machine.")) {
                commonModule.clickText("OK");
                commonModule.waitForDescription("Record", 2000);

                commonModule.clickDescription("Record");// Record start.
                commonModule.waitForDescription("Stop", 2000);

                commonModule.wait(recordTime);// Wait for record greeting.

                commonModule.clickDescription("Stop");// Stop record.
                commonModule.waitForDescription("Play", 2000);

                commonModule.clickDescription("Play");
                commonModule.wait(5);

                commonModule.clickDescription("Stop");
                commonModule.waitForText("Save", 2000);

                commonModule.emptyEditorByInstance(0);
                testCase.enterText(greetingName);
                commonModule.clickText("Save");
            }
            commonModule.wait(2);
            AcceptanceTestCase
                    .assertTrue("Turn on Answering Machine failed.", checkbox.isChecked());
        }
    }

    public void turnOffAnsweringMachine() throws UiObjectNotFoundException {
        enterAnsweringMachineFromSettings();
        UiObject checkbox = new UiObject(new UiSelector().resourceId("android:id/checkbox"));

        if (!checkbox.isChecked()) {
            return;
        } else if (checkbox.isChecked()) {
            checkbox.click();
            commonModule.wait(2);
            AcceptanceTestCase.assertTrue("Turn off Answering Machine failed.",
                    !checkbox.isChecked());
        }
    }

    public void setPickUpAfter(int seconds) throws UiObjectNotFoundException {
        commonModule.clickText("Pick up after");
        if (commonModule.waitForText("OK", 2000)) {
            commonModule.clickText("OK");
        }
        commonModule.waitForText("Done", 2000);

        commonModule.clickResourceId("android:id/numberpicker_input");
        testCase.enterText(String.valueOf(seconds));

        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue("Set pick up after '" + seconds + " seconds' failed.",
                commonModule.waitForText(seconds + " seconds", 3000));
    }

    public void checkGreetings(String greetingName) throws UiObjectNotFoundException {
        commonModule.clickText("Greetings");
        commonModule.waitForText(greetingName, 2000);

        UiObject greeting = new UiObject(new UiSelector().text(greetingName));
        Rect gRect = greeting.getVisibleBounds();

        UiObject playIcon = new UiObject(new UiSelector().resourceId("com.android.phone:id/icon"));
        Rect pRect = playIcon.getVisibleBounds();

        testCase.clickPoint(pRect.centerX(), gRect.bottom);// Play greeting.
        commonModule.waitForDescription("Stop", 2000);
        commonModule.wait(2);

        testCase.clickPoint(pRect.centerX(), gRect.bottom);// Stop greeting.
        commonModule.waitForDescription("Play", 2000);
        commonModule.wait(2);

        testCase.clickPoint(pRect.centerX(), gRect.bottom);// Play greeting
                                                           // again.
        commonModule.waitForDescription("Stop", 2000);

        commonModule.clickResourceId("com.android.phone:id/speaker_icon");// mute
                                                                          // sound.
        commonModule.wait(3);

        commonModule.clickResourceId("com.android.phone:id/speaker_icon");// unmute
                                                                          // sound.
        commonModule.wait(3);

        testCase.clickPoint(pRect.centerX(), gRect.bottom);// Stop greeting.
        commonModule.waitForDescription("Play", 2000);
    }

    public void recordGreeting(int seconds, String greetingName) throws UiObjectNotFoundException {
        if (commonModule.isTextExists("Answering Machine")) {
            commonModule.clickText("Greetings");
            commonModule.waitForText("Record new greeting", 2000);
        }

        commonModule.clickText("Record new greeting");
        commonModule.waitForDescription("Record", 2000);

        commonModule.clickDescription("Record");// Record start.
        commonModule.waitForDescription("Stop", 2000);

        commonModule.wait(seconds);// Wait for record greeting.

        if (commonModule.isDescriptionExists("Stop")) {
            commonModule.clickDescription("Stop");// Stop record.
        }
        commonModule.waitForText("Save", 2000);

        commonModule.emptyEditorByInstance(0);
        testCase.enterText(greetingName);

        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue(
                "Record Greeting " + greetingName + " failed.",
                commonModule.waitForText(greetingName, 3000)
                        && commonModule.waitForText("Record new greeting", 3000));
    }

    public void selectOneGreeting(String greetingName) throws UiObjectNotFoundException {
        if (commonModule.isTextExists("Answering Machine")) {
            commonModule.clickText("Greetings");
            commonModule.waitForText("Record new greeting", 2000);
        }

        commonModule.clickText(greetingName);
        commonModule.wait(2);
        UiObject redioButton = new UiObject(
                new UiSelector().resourceId("com.android.phone:id/radio_button"));
        AcceptanceTestCase.assertTrue("Select greeting failed.", redioButton.isChecked());

        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Back to Answer Machine view.
        commonModule.waitForText("Answering Machine", 2000);
    }

    public void checkAndPlayVoiceMessageInAnswerMachine(String number)
            throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Answering Machine")) {
            enterAnsweringMachineFromSettings();
        }

        commonModule.clickTextContains("Messages");
        int numLength = number.length();
        AcceptanceTestCase.assertTrue("There is no correct voice message.",
                commonModule.isTextExists("Withheld") || commonModule.isTextContains(number.substring(3, numLength-1)));

        commonModule.clickDescription("Play an unplayed message");
        commonModule.waitForDescription("Stop", 2000);
        AcceptanceTestCase.assertTrue("Play voice message failed.",
                commonModule.waitForDescription("Play", 20 * 1000));
    }

    public void deleteAllVoiceMessageInAnswerMachine() throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Answering Machine")) {
            enterAnsweringMachineFromSettings();
        }

        commonModule.clickTextContains("Messages");
        commonModule.waitForResourceId("com.android.phone:id/speaker_icon", 2000);

        if (commonModule.isTextExists("No messages")
                || !commonModule.isResourceId("android:id/title")) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);// Back to Answer Machine
                                                     // view.
            commonModule.waitForText("Answering Machine", 2000);

            return;
        }

        testCase.longPressItemWithId("title");
        commonModule.waitForText("Delete all", 2000);

        commonModule.clickText("Delete all");
        commonModule.waitForText("Delete all messages?", 2000);

        commonModule.clickText("OK");
        AcceptanceTestCase.assertTrue("Delete all voice message failed.",
                commonModule.waitForText("No messages.", 2000));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Back to Answer Machine view.
        commonModule.waitForText("Answering Machine", 2000);
    }

    public void deleteAllGreetings() throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Answering Machine")) {
            enterAnsweringMachineFromSettings();
        }

        commonModule.clickText("Greetings");
        commonModule.waitForText("Record new greeting", 2000);

        for (int i = 0; i < 10; i++) {

            if (!commonModule.isResourceId("com.android.phone:id/icon")) {
                break;
            }

            testCase.longPressItemWithId("timeAndDate");
            commonModule.waitForText("Delete", 2000);

            commonModule.clickText("Delete");
            if (commonModule.waitForTextContains("If you delete this greeting,", 2000)) {
                commonModule.clickText("OK");
            }
            commonModule.waitForText("Delete message?", 2000);

            commonModule.clickText("OK");
            commonModule.waitForText("Record new greeting", 2000);
        }
        AcceptanceTestCase.assertTrue(
                "Delete all greetings failed.",
                commonModule.isTextExists("Record new greeting")
                        && !commonModule.isResourceId("com.android.phone:id/icon"));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Back to Answer Machine view.
        commonModule.waitForText("Answering Machine", 2000);
    }

    public void mergeCall() throws UiObjectNotFoundException {
        // commonModule.clickResourceId(moduleData.get("Merge_Icon"));

        commonModule.clickResourceId("com.android.incallui:id/mergeCallsIcon");

        AcceptanceTestCase.assertTrue("Conference call didn't make up",
                commonModule.waitForText("Conference call", 5 * 1000));
    }

    @Override
    public void checkOngingCallName(String name) {

        testCase.assertTrue("Call cannot display the contact name!",
                testCase.isViewWithTextPresent(name));
    }

    public void enterCallSettings() throws UiObjectNotFoundException {
        launchSettings();

        if(commonModule.waitForText("Call settings", 2000)){
            commonModule.clickText("Call settings");
        }else{
            commonModule.clickText("Call");
        }

        commonModule.waitForText("Phone ringtone", 2000);
    }

    public void checkMicrophoneNoiseSuppression(String number) throws UiObjectNotFoundException {
        makeMOCallAndAnswered(number);
        endCall();

        enterCallSettings();
        commonModule.scrollFindText("Microphone noise suppression");

        if (commonModule.waitForText("OK", 2000)) {
            commonModule.clickText("OK");
        }
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

        makeMOCallAndAnswered(number);
        endCall();
    }

    public void setEqualizer(String mode) throws UiObjectNotFoundException {
        enterCallSettings();
        commonModule.scrollFindTextNotClick("Equalizer");

        if (commonModule.isTextExists(mode)) {

            commonModule.backOutToHomeScreen();
            return;
        } else {
            commonModule.clickText("Equalizer");
            commonModule.waitForText(mode, 2000);

            commonModule.clickText(mode);
            AcceptanceTestCase.assertTrue(
                    "Set Equalizer as " + mode + "mode failed.",
                    commonModule.waitForText("Equalizer", 2000)
                            && commonModule.waitForText(mode, 2000)
                            && commonModule.waitForTextGone("Cancel", 2000));
        }
    }

    public void checkEqualizer(String number) throws UiObjectNotFoundException {
        setEqualizer("Bright");
        makeMOCallAndAnswered(number);
        endCall();

        setEqualizer("Smooth");
        makeMOCallAndAnswered(number);
        endCall();

        setEqualizer("Normal");
        makeMOCallAndAnswered(number);
        endCall();
    }

    public void checkSpeakerVoiceEnhancement(String number) throws UiObjectNotFoundException {
        makeMOCallAndAnswered(number);
        endCall();

        enterCallSettings();
        commonModule.scrollFindText("Speaker voice enhancement");

        commonModule.clickText("Speaker voice enhancement");
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

        makeMOCallAndAnswered(number);
        endCall();
    }

    public void checkSlowTalk(String number) throws UiObjectNotFoundException {
        makeMOCallAndAnswered(number);
        endCall();

        enterCallSettings();
        commonModule.scrollFindText("Slow talk");

        commonModule.clickText("Slow talk");
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

        makeMOCallAndAnswered(number);
        endCall();
    }

    public void changeVolumeLevel() {
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        commonModule.wait(2);

        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        commonModule.wait(2);
    }

    public void muteOrUnmuteMicrophone(String value) throws UiObjectNotFoundException {
        UiObject muteButton = new UiObject(new UiSelector().resourceId(moduleData
                .get("Mute_Button")));

        if (value.equals("mute") && !muteButton.isSelected()) {
            commonModule.clickResourceId(moduleData.get("Mute_Button"));
            AcceptanceTestCase.assertTrue("Mute the microphone failed.", muteButton.isSelected());
        } else if (value.equals("unmute") && muteButton.isSelected()) {
            commonModule.clickResourceId(moduleData.get("Mute_Button"));
            AcceptanceTestCase
                    .assertTrue("Unmute the microphone failed.", !muteButton.isSelected());
        }
    }

    public void checkCallTimer() throws UiObjectNotFoundException {
        String startTime = new UiObject(
                new UiSelector().resourceId(moduleData.get("Call_Timer_Id"))).getText();
        int minutes1 = Integer.valueOf(startTime.split(":")[0]);
        System.out.println("minutes1:" + minutes1);
        int seconds1 = Integer.valueOf(startTime.split(":")[1]);
        System.out.println("seconds1:" + seconds1);
        int time1 = minutes1 * 60 + seconds1;

        commonModule.wait(2);
        String endTime = new UiObject(new UiSelector().resourceId(moduleData.get("Call_Timer_Id")))
                .getText();
        int minutes2 = Integer.valueOf(endTime.split(":")[0]);
        System.out.println("minutes2:" + minutes2);
        int seconds2 = Integer.valueOf(endTime.split(":")[1]);
        System.out.println("seconds2:" + seconds2);
        int time2 = minutes2 * 60 + seconds2;

        AcceptanceTestCase.assertTrue("Call timer is not running accordingly.", time2 > time1);
    }

    public void enterCallDetails(String numberOrName) throws UiObjectNotFoundException {
        launchPhone();
        commonModule.waitForTextContains(numberOrName, 2000);

        UiObject num = new UiObject(new UiSelector().textContains(numberOrName));
        Rect numRect = num.getVisibleBounds();
        UiObject detailIcon = new UiObject(new UiSelector().resourceId(
                moduleData.get("Detail_Icon")).instance(0));
        Rect detailRect = detailIcon.getVisibleBounds();

        testCase.clickPoint(detailRect.centerX(), numRect.bottom);
        commonModule.waitForText("Call details", 2000);
    }

    public void saveContactFromCallLog(String number, String name) throws UiObjectNotFoundException {
        enterCallDetails(number);

        commonModule.waitForText("Add to Contacts", 2000);

        commonModule.clickText("Add to Contacts");

        commonModule.waitForText("Create new contact", 2000);

        commonModule.clickText("Create new contact");
        if (commonModule.waitForText("Keep local", 3000)) {
            testCase.click("Keep local");
        }
        commonModule.waitForText("First name", 2000);

        commonModule.clickText("First name");
        testCase.enterText(name);

        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue(
                "Save contact from call log failed.",
                commonModule.waitForText(name, 2000)
                        && commonModule.waitForDescription("Edit", 2000));
    }

    public void sendMessageFromCallLog(String numberOrName) throws UiObjectNotFoundException {
        enterCallDetails(numberOrName);
        commonModule.waitForText("Send text message", 2000);

        commonModule.clickText("Send text message");
        commonModule.waitForText("Write message", 3000);

        commonModule.clickText("Write message");
        testCase.enterText("Send message from call log");
        commonModule.waitForResourceId(moduleData.get("Send_Button"), 2000);

        commonModule.clickResourceId(moduleData.get("Send_Button"));
    }

    public void callbackFromCallLog(String numberOrName) throws UiObjectNotFoundException {
        TelephonyManager tm = (TelephonyManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        enterCallDetails(numberOrName);

        if (commonModule.isTextExists("Call back")) {// phone number was not
                                                     // saved
            commonModule.clickText("Call back");
        } else {// phone number has been saved
            commonModule.clickText("Call " + numberOrName);
        }

        // Verify call establish successfully.
        for (int i = 0; i < 10; i++) {
            if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                Log.i(TAG, "Call is sent out");
                break;
            } else {
                commonModule.wait(2);
                if (i == 9) {
                    testCase.assertTrue("Call cannot be sent out!", false);
                }
            }
        }

        // Verify call answered.
        for (int i = 0; i < 10; i++) {
            if (commonModule.isResourceId(moduleData.get("Call_Timer_Id"))
                    && commonModule.isResourceId(moduleData.get("End_Call_Button_Id"))) {
                Log.i(TAG, "Call is answered");
                break;
            } else {
                Log.i(TAG, "Waiting for call");
                commonModule.wait(2);
                if (i == 9) {
                    testCase.failTest("Call is not answered");
                }
            }
        }
        endCall();
    }

    @Override
    public void makeMOCallWithSymbolNumber(String number) throws UiObjectNotFoundException {
        launchPhone();

        commonModule.enterTextSlowly(number);
        commonModule.wait(2);
        testCase.clickId("call_button");
        // press Call button
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/call_button");

    }

    public void verifyCallAnsweredWithCallNumber(String callNumber)
            throws UiObjectNotFoundException {
        commonModule.wait(3);
        verifyCallAnswered();
        String subCallNumber = callNumber.substring(3, callNumber.length());

        AcceptanceTestCase.assertTrue("Call number cannot display in calling screen",
                commonModule.isTextContains(subCallNumber));
    }

    public void receiveMTCallAndVerifyCallerInfo(String remoteNumber, String callerName) {
        // tell remote phone make a call to main phone
        commonModule.sendSMSCommand(remoteNumber, CommandConstantsUtils.COMMAND_CALL);

        waitingForCallComing();

        checkOngingCallName(callerName);
    }

    public void clickMissedCallFromNotificationBar(String callerInfo) {
        testCase.openStatusBar();
        commonModule.waitForTextContains(callerInfo, 5000);

        testCase.click("Missed call");
        commonModule.wait(2);

        AcceptanceTestCase.assertTrue("Dailer id should display on call log state",
                commonModule.waitForResourceId(moduleData.get("Call_Button_Id"), 8000));
    }

    public void waitingForCallComing() {

        for (int i = 0; i < 30; i++) {
            if (i == 29) {
                AcceptanceTestCase.fail("not receive call in 60s");
            }

            if (testCase.isViewWithTextPresent("Incoming call")) {
                break;
            } else if (testCase.isViewInAllInteractiveWindows(null, null, "Incoming call", null,
                    -1, -1)) {
                Log.i(TAG, "==============Incoming call==============");
                testCase.clickViewInAllInteractiveWindows(null, null, "Incoming call", null, -1, -1);
                commonModule.wait(2);
                break;
            } else {
                commonModule.wait(2);
            }
        }
    }

    public void editNumberBeforeCallAndMakeCall(String contact, String newNumber) throws UiObjectNotFoundException {
        launchPhone();
        commonModule.waitForText(contact, 2000);

        testCase.longPressItemWithText(contact);
        commonModule.waitForText("Edit number before call", 2000);

        commonModule.clickText("Edit number before call");
        commonModule.waitForResourceId(moduleData.get("Call_Button_Id"), 2000);

        commonModule.emptyEditorByInstance(0);
        testCase.enterText(newNumber);

        commonModule.clickResourceId(moduleData.get("Call_Button_Id"));
        verifyCallAnswered();
    }

    public void callWithoutNumber() throws UiObjectNotFoundException {
        launchPhone();

        commonModule.clickResourceId(moduleData.get("Call_Button_Id"));
        AcceptanceTestCase.assertTrue("Call can be established without number.",
                !commonModule.waitForText("Calling", 5000));
    }
}
