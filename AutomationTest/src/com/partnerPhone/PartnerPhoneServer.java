
package com.partnerPhone;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.module.email.EmailFactory;
import com.module.email.EmailModule;
import com.module.email.IEmail;
import com.module.walkman.AbstractWalkmanFactory;
import com.module.walkman.IWalkman;
import com.module.walkman.WalkmanFactory;
import com.module.walkman.WalkmanModule;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.test.reliability.BasicUtils;
import com.utils.CommandConstantsUtils;
import com.module.email.AbstractEmailFactory;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class PartnerPhoneServer extends AcceptanceTestCase {

    public static final String SERVER_TAG = "AnswerBatterylifeServer";

    public static final int SERVER_HANDLE_TIME = 10 * 24 * 60 * 60;
    
    public static boolean SIP;

    private BroadcastReceiver mInSmsReceiver;

    private BroadcastReceiver mInCallReceiver;

    public static final String INCOME_SMS_NUMBER = "income_sms_number";

    public static final String INCOME_SMS_CONTENT = "income_sms_content";

    public static final String SMS_CONTENT_OUT = "Are you going to the party tonight?";

    public static final String SMS_CONTENT_WiFi_OUT = "WLANtethering is ready";

    private static String mIncomingNumber;

    private static String test_EASaccount = "cnl848947@eas.teslan.org";

    private static String test_emailaccount = "agingtestnew005@gmail.com";

    // private static String test_serveremail = "agingtestnew005@gmail.com";
    private static String test_serveremail = "agingtestserver@gmail.com";

    // private static String test_serverfacebook = "agingtestserver@gmail.com";
    private static String test_serverfacebook = "systct20@gmail.com";

    private static String test_facebookname= "11";
    public String GMAIL_SENDTO;

    public static int GMAIL_COUNT = 0;

    public final int CALL_WAIT_TIME = 2 * 60;

    public int SERVER_LIFE_TIME = 1000 * 60 * 60;

    public final int TOTAL_WAIT_TIME = 8 * 60 * 60 * 60;

    public final String GMAIL_SUBJECT = "Reliability Gmail test ";

    public final String MMS_CONTENT = "Do you like the picture and sound?";

    public final String GMAIL_CONTENT = "Have you received my testing Gmail for Aging";
    
    public final String MUSIC_NAME = "Mambo Italiano.mp3";

    public CommonModule commonModule;

    public IWalkman walkmanModule;

    public IEmail emailModule;

    public String TAG = "Reliability";


    private static final String PHONE_PACKAGE = "com.sonyericsson.android.socialphonebook";
    private static final String PHONE_ACTIVITY = "com.sonyericsson.android.socialphonebook.DialerEntryActivity";
    private static final String MESSAGE_PACKAGE = "com.sonyericsson.conversations";
    private static final String MESSAGE_ACTIVITY = "com.sonyericsson.conversations.ui.ConversationListActivity";
    private static final String EMAIL_PACKAGE = "com.android.email";
    private static final String EMAIL_ACTIVITY = "com.android.email.activity.EmailActivity";
    public static final String CAMERA_PACKAGE = "com.sonyericsson.android.camera";
    public static final String CAMERA_ACTIVITY = "com.sonyericsson.android.camera.CameraActivity";


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ModuleDataParser dataParser = new ModuleDataParser();
        dataParser.parse();

        this.disableLockScreen();

        commonModule = new CommonModule(this);
        walkmanModule = ((AbstractWalkmanFactory)GroupFactories.createFactory(this, "walkman")).create();
        emailModule = ((AbstractEmailFactory)GroupFactories.createFactory(this, "email")).create();
    }

    @Override
    protected void tearDown() throws Exception {

        super.tearDown();

    }

    public void test_MainThread() {

        // this.acquireWakelock();

        commonModule.unLockScreen();
        try {

            this.initServerPre();

            this.registerInSMSReceiver();
            this.registerInCallReceiver();

            commonModule.wait(SERVER_HANDLE_TIME);

            this.unregisterAllReceiver();

        } catch (Exception e) {

            Log.i(SERVER_TAG, e.getStackTrace().toString());

        } finally {

            this.disableLockScreen();
            this.descroyPre();
        }

    }

    private void descroyPre() {

        commonModule.backOutToHomeScreen();
        commonModule.setScreenTimeOutserver("30 seconds");
        commonModule.backOutToHomeScreen();

    }

    private void initServerPre() throws IOException {

        commonModule.backOutToHomeScreen();
        // commonModule.setScreenTimeOutserver("30 minutes");
        // // super.loginEmailSyc("agingtest001@gmail.com", "Beijing00", 3);
        // // super.loginEmailSyncAuto(test_serveremail, "Beijing00", 3);
        // emailModule.loginEmailSyncAutoServer(test_serveremail, "Beijing00",
        // 4);
        commonModule.backOutToHomeScreen();
        // super.loginFacebookSycserver(test_serverfacebook, "Beijing123", 4);
        commonModule.backOutToHomeScreen();
    }

    private void unregisterAllReceiver() {

        Context context = getInstrumentation().getContext();
        if (mInSmsReceiver != null) {
            context.unregisterReceiver(mInSmsReceiver);
            mInSmsReceiver = null;
        }

        if (mInCallReceiver != null) {
            context.unregisterReceiver(mInCallReceiver);
            mInCallReceiver = null;
        }
    }

    private void registerInSMSReceiver() {

        mInSmsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle _data = intent.getExtras();
                if (_data != null) {

                    Object _pdus[] = (Object[])_data.get("pdus");
                    String _message = "New message:\n";
                    String _sender = null;

                    for (Object _pdu : _pdus) {

                        SmsMessage _part = SmsMessage.createFromPdu((byte[])_pdu);
                        String _messageContent = _part.getDisplayMessageBody();

                        _sender = _part.getDisplayOriginatingAddress();
                        Message _msg = myHandler.obtainMessage();
                        Bundle _bContent = new Bundle();

                        _bContent.putString(INCOME_SMS_NUMBER, _sender);
                        _bContent.putString(INCOME_SMS_CONTENT, _messageContent);
                        _msg.setData(_bContent);

                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                        myHandler.sendMessage(_msg);
                    }

                    Log.i(SERVER_TAG, ">>>>>>>>>>>>>>>>>>>>>>" + _message + "\nFrom: " + _sender);

                }
            }
        };

        Context context = getInstrumentation().getContext();
        context.registerReceiver(mInSmsReceiver, new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED"));

    }

    private void registerInCallReceiver() {

        mInCallReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                String _state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

                String _msg = "Phone state changed to " + _state;
                if (TelephonyManager.EXTRA_STATE_RINGING.equals(_state)) {

                    mIncomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    _msg += ". Incoming number is " + mIncomingNumber;

                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    anwserCall();
                }
                Log.i(SERVER_TAG, "------------------Incoming call: " + _msg);
            }
        };

        Context context = getInstrumentation().getContext();
        context.registerReceiver(mInCallReceiver, new IntentFilter(
                "android.intent.action.PHONE_STATE"));

    }

    private void sendOutSMS(final String sender, final String content) {

        SmsManager _iSMSManager = null;
        _iSMSManager = SmsManager.getDefault();

        /**
         * Intent msgSent = new Intent("ACTION_MSG_SENT"); Intent msgReceipt =
         * new Intent("ACTION_MSG_RECEIPT"); final PendingIntent pendingMsgSent
         * = PendingIntent.getBroadcast(context, 0, msgSent, 0); final
         * PendingIntent pendingMsgReceipt = PendingIntent.getBroadcast(context,
         * 0, msgReceipt, 0); iSMSManager.sendTextMessage(sender, null,
         * SMS_CONTENT, pendingMsgSent, pendingMsgReceipt);
         **/
        _iSMSManager.sendTextMessage(sender, null, content, null, null);
        Log.e(TAG, sender);
    }

    private void SetupWiFitethering(final String APname, final String APpwd) {
        commonModule.backOutToHomeScreen();
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        commonModule.wait(4);
        this.click("More…");
        commonModule.wait(1);
        this.click("Tethering & portable hotspot");
        commonModule.wait(1);
        if (!this.isCheckboxChecked("checkbox", 1)) {
            this.clickItemWithId("checkbox", 1);
        }
        commonModule.wait(5);
        this.click("Portable Wi-Fi hotspot settings");
        commonModule.wait(1);
        this.click("Configure Wi-Fi hotspot");
        commonModule.wait(1);
        String ApnameText = this.getTextFromViewWithId("ssid");

        for (int i = 0; i < ApnameText.length(); i++) {
            pressKey(KeyEvent.KEYCODE_DEL);
        }
        this.enterText(APname);
        commonModule.wait(1);
        this.clickId("password");
        String APpwdText = this.getTextFromViewWithId("account_port");

        for (int i = 0; i < APpwdText.length(); i++) {
            pressKey(KeyEvent.KEYCODE_DEL);
        }
        this.enterText(APpwd);
        commonModule.wait(1);
        this.click("Save");
        commonModule.wait(2);
        this.click("Power save");
        commonModule.wait(1);
        this.click("Never");
        commonModule.backOutToHomeScreen();

    }

    private void sendGmail() {

        for (int i = 0; i < 5; i++) {

            commonModule.backOutToHomeScreen();
            commonModule.launchAppByManual2("Google Mail", "Gmail");
            commonModule.wait(5);

            pressKey(KeyEvent.KEYCODE_MENU);
            clickItemWithText("Compose");
            clickItemWithId("to_content");
            enterText(GMAIL_SENDTO);
            pressKey(KeyEvent.KEYCODE_BACK);

            commonModule.wait(1);

            clickItemWithId("subject_text");
            enterText(GMAIL_SUBJECT + GMAIL_COUNT);
            commonModule.wait(1);

            clickItemWithId("body_text");
            enterText(GMAIL_CONTENT);
            commonModule.wait(1);

            Log.d(TAG, "------------------Message will be sent");
            pressKey(KeyEvent.KEYCODE_MENU);
            clickItemWithText("Send");
            commonModule.wait(10);

        }

    }

    private void sendFACEBOOKMessage() {

        commonModule.backOutToHomeScreen();
        commonModule.launchAppBySearch("Facebook");
        commonModule.wait(5);
        if (this.isViewWithIdPresent("alertTitle") && this.isViewWithTextPresent("Not Now")) {
            this.click("Not Now");
        }
        commonModule.wait(2);
        if (this.isViewWithIdPresent("publisher_status_icon")) {
            this.clickId("publisher_status_icon");
            commonModule.wait(2);
        }else if (this.isViewWithIdPresent("publisher_button0")) {
            this.clickId("publisher_button0");
            commonModule.wait(2);
        }

        this.enterText("This is for Reliability facebook test");
        commonModule.wait(2);
        this.clickId("audience_picker_right_arrow");
        commonModule.wait(2);
        if(this.isViewWithTextPresent("Friends")){
            this.click("Friends");
            }else if(this.isViewWithDescriptionPresent("Friends")){
            	this.clickItemWithDescription("Friends");
            }
        commonModule.wait(2);
        if(this.isInputMethodWindowOpened()){
				        	this.pressKey(KeyEvent.KEYCODE_BACK);
				        }
        this.pressKey(KeyEvent.KEYCODE_BACK);
        if(this.isViewWithTextPresent("Post")){
        this.click("Post");
        }else if(this.isViewWithDescriptionPresent("Post")){
        	this.clickItemWithDescription("Post");
        }
        commonModule.backOutToHomeScreen();
        commonModule.wait(10);
    }

    private void sendFACEBOOKMessage1() {

        commonModule.backOutToHomeScreen();
        commonModule.launchAppBySearch("Facebook");
        commonModule.wait(5);
        if (this.isViewWithTextPresent("Find Friends") || this.isViewWithTextPresent("Not Now")) {
            this.click("Not Now");
        }
        commonModule.wait(2);
        if (this.isViewWithIdPresent("primary_action_button")) {
            clickId("primary_action_button");
            commonModule.wait(2);
        }

        if (this.isViewWithIdPresent("friends_list_search")) {
            this.clickId("friends_list_search");
            commonModule.wait(2);
            this.enterText("Sevr Zha");
            this.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(2);
            if(this.isViewWithTextPresent(test_facebookname)){
                this.click(test_facebookname);
                }else if(this.isViewWithDescriptionPresent(test_facebookname)){
                	this.clickItemWithDescription(test_facebookname);
                }
            commonModule.wait(1);
        }

        for (int i = 0; i < 5; i++) {
            if (this.isViewWithIdPresent("compose_edit")) {
                this.clickId("compose_edit");
                break;
            } else {
                commonModule.wait(5);
            }
        }
        // this.clickId("compose_edit");
        this.enterText("This is for Reliability facebook test");
        commonModule.wait(2);
        this.clickId("compose_button_send");
        commonModule.backOutToHomeScreen();
        commonModule.wait(10);
    }

    private void sendEmail(String Mailreceiver, int times) throws InterruptedException {

        for (int i = 0; i < times; i++) {

            commonModule.backOutToHomeScreen();
            //commonModule.launchAppBySearch("Email");
            launchApp(EMAIL_PACKAGE,EMAIL_ACTIVITY);
            if (!this.isViewWithIdPresent("compose")) {
                this.pressKey(KeyEvent.KEYCODE_BACK);
                launchApp(EMAIL_PACKAGE,EMAIL_ACTIVITY);
            }

            this.clickId("compose");
            commonModule.wait(2);
            this.clickItemWithId("to");
            // this.enterText("agingtest009@gmail.com");
            // this.enterText(test_gmailaccount);
            this.enterText(Mailreceiver);
            this.pressKey(KeyEvent.KEYCODE_ENTER);
            commonModule.wait(1);
            this.clickId("subject");
            this.enterText("email subject" + i);
            this.clickItemWithId("message_content");
            this.enterText("email body" + i);

            if (this.isViewWithTextPresent("Send")) {
                click("Send");
            } else {
                this.clickId("send");
            }
            commonModule.wait(5);

        }
        commonModule.backOutToHomeScreen();

    }

    private void sendWechatMessage(){
    	//commonModule.launchAppBySearch("微信");

    }

    private void sendMMS(final String sender) throws InterruptedException {

        commonModule.backOutToHomeScreen();
        launchApp(CAMERA_PACKAGE, CAMERA_ACTIVITY);
        commonModule.wait(5);
        pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);
        commonModule.wait(5);
        commonModule.backOutToHomeScreen();
        launchApp(MESSAGE_PACKAGE, MESSAGE_ACTIVITY);
        // click("New message");
        clickId("menu_new_conversation");
        commonModule.wait(1);

        clickItemWithId("recipients_editor");
        enterText(sender);

        clickItemWithId("conversation_edit_text");

        enterText(MMS_CONTENT);

        clickItemWithId("conversation_tool_button");
        click("Add picture");

        commonModule.wait(1);
        if (isViewWithIdPresent("alertTitle")) {
            click("Album");
            commonModule.wait(1);
            click("Just once");
        }

        clickItemWithId("icon_thumb", 0);
        // commonModule.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 1);
        // pressKey(KeyEvent.KEYCODE_DPAD_CENTER);
        commonModule.wait(2);
        click("Send");
        Log.d(TAG, "------------------Message will be sent");

        commonModule.wait(10);
        commonModule.backOutToHomeScreen();
    }

    private void getemailaccout(final String content) {
        test_emailaccount = content.substring(CommandConstantsUtils.COMMAND_MUT_EMAIL_ACCOUNT
                .length());
    }

    private void geteasaccout(final String content) {
        test_EASaccount = content.substring(CommandConstantsUtils.COMMAND_MUT_EAS_ACCOUNT.length());
    }
    
    private void getfacebookname(final String content) {
        test_facebookname = content.substring(CommandConstantsUtils.COMMAND_MUT_FACEBOOK_NAME.length());
    }

    private void makeCall(final String sender) throws InterruptedException {
    	TelephonyManager tm = (TelephonyManager) getInstrumentation()
   		     .getTargetContext().getSystemService(Context.TELEPHONY_SERVICE);

        commonModule.backOutToHomeScreen();

        launchApp(PHONE_PACKAGE, PHONE_ACTIVITY);
        commonModule.wait(2);
        enterText(sender);
        pressKey(KeyEvent.KEYCODE_CALL);
        if (isViewWithTextPresent("Missing voicemail number")) {
            click("OK");
            commonModule.wait(2);
            enterText(sender);
            pressKey(KeyEvent.KEYCODE_CALL);
        }
//        for(int i=0; i<10;i++){
//            if (tm.getCallState()!=TelephonyManager.CALL_STATE_OFFHOOK && i==9) {
//
//                commonModule.backOutToHomeScreen();
//                launchApp(PHONE_PACKAGE, PHONE_ACTIVITY);
//                commonModule.wait(2);
//                enterText(sender);
//                pressKey(KeyEvent.KEYCODE_CALL);
//            }else{
//                this.sleep(500);
//            }
//        }

    }

    private void anwserCall() {

        commonModule.wait(2);
        if(SIP){
        	this.dragViewBetweenTwoPosition(commonModule.getX(70,540),commonModule.getY(780,888),commonModule.getX(460,540),commonModule.getY(780,888));
        	SIP=false;
        	//this.dragViewBetweenTwoPosition(70,780,460,780);
        }else{
        pressKey(KeyEvent.KEYCODE_CALL);
        }
    }

    private Handler myHandler = new Handler() {

        public void handleMessage(final Message msg) {

            Bundle _bContent = msg.getData();

            String _content = _bContent.getString(INCOME_SMS_CONTENT);
            String _incomeSmsNumber = _bContent.getString(INCOME_SMS_NUMBER);
            Log.e(TAG, "++++++++++++" + _incomeSmsNumber);
            // if (_incomeSmsNumber.startsWith("1") ||
            // _incomeSmsNumber.startsWith("8")) {
            // _incomeSmsNumber =
            // }

            if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_SMS)) {

                sendOutSMS(_incomeSmsNumber, SMS_CONTENT_OUT);// //no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_SMS_LONG)) {

                sendOutSMS(_incomeSmsNumber, CommandConstantsUtils.SMS_CONTENT_LONG_OUT);// no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_MMS)) {

                try {
                    sendMMS(_incomeSmsNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }// no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_CALL)) {

                try {
                    makeCall(_incomeSmsNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }// no

            } else if (_content.startsWith(CommandConstantsUtils.COMMAND_MUT_EMAIL_ACCOUNT)) {

                getemailaccout(_content);// no

            } else if (_content.startsWith(CommandConstantsUtils.COMMAND_MUT_EAS_ACCOUNT)) {

                geteasaccout(_content);// no

            } else if (_content.startsWith(CommandConstantsUtils.COMMAND_MUT_FACEBOOK_NAME)) {

                getfacebookname(_content);// no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_EMAIL_EAS)) {

                try {
                    sendEmail(test_EASaccount, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_EMAIL)) {

                try {
                    sendEmail(test_emailaccount, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_WIFI_TETHERING)) {

                SetupWiFitethering("test", "12345678");
                sendOutSMS(_incomeSmsNumber, SMS_CONTENT_WiFi_OUT);// no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_GMAIL)) {

                sendGmail(); // no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_FACEBOOK)) {

                sendFACEBOOKMessage1(); // no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_ANSWER_CALL)) {
            			SIP = true;
                //anwserCall();// -------YES--------

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_MULTIGMAILS_20)) {

                Thread t = new Thread(new EmailThread()); // no
                t.start();

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_CALL_CLIENT_SMS)) {

                Thread t = new Thread(new CallClientAndSMSThread());
                t.start(); // no

            } else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_ANSWER_CALL_MUSIC)) {

                Thread t = new Thread(new CallPlayMusicThread());
                t.start(); // -------YES--------
            }
            else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_WECHAT)) {

            	sendWechatMessage();
            }else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_PLAY_MUSIC)) {

            	playMusicURL(MUSIC_NAME); // -------YES--------
            }else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_SIP_ADDRESS)) {

            			setSipAddressSetting(); // -------YES--------
            }else if (_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_SIP_INTERNET_ADDRESS)) {

    			setSipInternetAddressSetting(); // -------YES--------
            }else if(_content.equalsIgnoreCase(CommandConstantsUtils.COMMAND_END_CALL)){
                pressKey(KeyEvent.KEYCODE_ENDCALL);
            }else if(_content.contains(CommandConstantsUtils.SMS_With_Specified_Content)){
                String messageContent = _content.split(",")[1];
                sendOutSMS(_incomeSmsNumber, messageContent);
            }
        }
    };

    @Override
    public boolean beforeFailing() {

        if (mInCallReceiver != null) {
            Context context = getInstrumentation().getContext();
            context.unregisterReceiver(mInCallReceiver);
            mInCallReceiver = null;
        }

        if (mInSmsReceiver != null) {
            Context context = getInstrumentation().getContext();
            context.unregisterReceiver(mInSmsReceiver);
            mInSmsReceiver = null;
        }

        return super.beforeFailing();

    }

    private class EmailThread implements Runnable {

        public void run() {
            try {
                sendEmailCount();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private class CallPlayMusicThread implements Runnable {

        public void run() {

            try {
                walkmanModule.playMusic("001_Working Life");
                walkmanModule.stopMusicWithWALKMAN();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            sleep(12 * 60 * 1000);

        }

    }

    private class CallClientAndSMSThread implements Runnable {

        public void run() {
            try {
                callClientEvent();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void callClientEvent() throws InterruptedException {

        for (int i = 0; i < 2; i++) {
            commonModule.wait(2);
            commonModule.backOutToHomeScreen();
            launchApp(PHONE_PACKAGE, PHONE_ACTIVITY);
            commonModule.wait(1);
            this.clickId("digits");
            commonModule.wait(1);
            this.enterText(mIncomingNumber);
            this.pressKey(KeyEvent.KEYCODE_CALL);
            commonModule.wait(10);
            pressKey(KeyEvent.KEYCODE_ENDCALL);
            commonModule.wait(4);
            commonModule.backOutToHomeScreen();
            commonModule.wait(1);
            // CallClientThread
            this.sendOutSMS(mIncomingNumber, "Base event for : sms " + i);
            commonModule.wait(2);
        }

    }

    private void sendEmailCount() throws InterruptedException {

        commonModule.backOutToHomeScreen();
        launchApp(EMAIL_PACKAGE, EMAIL_ACTIVITY);

        if (!this.isViewWithTextPresent("New mail")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
        }

        for (int i = 1; i <= 10; i++) {

            click("New mail");
            this.clickItemWithId("to");
            this.enterText("guozt2011@gmail.com");
            commonModule.wait(1);
            this.clickItemWithId("message_content");

            // pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 2);
            this.enterText("are you go to party tonight ?" + i);
            // this.clickItemWithId("message_content");
            commonModule.wait(1);
            this.clickItemWithId("subject");
            this.enterText("test_subject");

            commonModule.wait(1);
            click("Send");
            // wait(5*55);
            commonModule.wait(3);

        }

        commonModule.wait(8);
        commonModule.backOutToHomeScreen();
    }

    private void setHotSpotWifi() {

        commonModule.backOutToHomeScreen();
        launchApp("com.android.settings", "com.android.settings.Settings");
        this.clickItemWithId("title", 3);

        commonModule.wait(32);
        this.click("Tethering & portable hotspot");
        commonModule.wait(2);
        this.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(1);
        this.click("Tethering & portable hotspot");
        commonModule.wait(2);
        this.click("Configure Wi-Fi hotspot");
        commonModule.wait(2);

        if (isViewWithTextPresent("Configure Wi-Fi hotspot")) {
            this.clickPoint(600, 312);
            commonModule.wait(2);
            String text = this.getTextFromViewWithId("ssid");
            for (int i = 0; i < text.length(); i++) {
                this.pressKey(KeyEvent.KEYCODE_CLEAR);

            }
            this.enterText("test_hotspot_wifi");

            this.clickPoint(566, 560);
            commonModule.wait(2);
            String passText = this.getTextFromViewWithId("password");
            for (int i = 0; i < passText.length(); i++) {
                this.pressKey(KeyEvent.KEYCODE_CLEAR);

            }
            this.enterText("12345678");
            commonModule.wait(1);
            this.clickId("button1");
        }

        this.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(2);
        if (!isCheckboxChecked("checkbox", 1)) {
            this.clickItemWithId("checkbox", 1);
            commonModule.wait(3);
            this.click("OK");
            commonModule.wait(4);
        }
        commonModule.wait(2);
        commonModule.backOutToHomeScreen();

    }

    private void setSipAddressSetting() {

        commonModule.backOutToHomeScreen();
        launchApp("com.android.phone", "com.android.phone.CallFeaturesSetting");
        commonModule.clickListItemwithText("Use Internet calling");
					 click("Only to SIP address");
					 commonModule.backOutToHomeScreen();

    }
    private void setSipInternetAddressSetting() {

        commonModule.backOutToHomeScreen();
        launchApp("com.android.phone", "com.android.phone.CallFeaturesSetting");
        commonModule.clickListItemwithText("Use Internet calling");
					 click("Use SIP with network access");
					 commonModule.backOutToHomeScreen();

    }
    private void playMusicURL(String musicName) {
		String dir = Environment.getExternalStorageDirectory().getPath() + "/Music/";
		File testFolder = new File(Environment.getExternalStorageDirectory().getPath() + "/Music/");
	    File audioFile = new File(testFolder, musicName);
	    Intent launchIntent = new Intent(Intent.ACTION_VIEW);
	    launchIntent.setComponent(new ComponentName("com.sonyericsson.music", "com.sonyericsson.music.PlayerActivity"));
	    launchIntent.setDataAndType(Uri.fromFile(audioFile), "audio/*");
	    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    getInstrumentation().getContext().startActivity(launchIntent);
    }

}
