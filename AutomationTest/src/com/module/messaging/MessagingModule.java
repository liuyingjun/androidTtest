package com.module.messaging;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parents.Module;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleData;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import java.util.HashMap;

public class MessagingModule implements IMessaging{
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    String TAG = "Reliability";

    public MessagingModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("messaging");
    }

	@Override
	public void startMessagingFromMenu() {
		commonModule.backOutToHomeScreen();
        testCase.launchApp("com.sonyericsson.conversations",
	            "com.sonyericsson.conversations.ui.ConversationListActivity");
		testCase.sleep(2000);
		testCase.setTimeout(1000);
        if (testCase.isViewWithTextPresent("Change SMS app?")) {
        	testCase.clickItemWithText("Yes");
        }
        testCase.resetTimeout();
        testCase.sleep(2000);

	}
    @Override
    public void removeAllMessages() {
        commonModule.backOutToHomeScreen();
        startMessagingFromMenu();
        if (commonModule.isTextContains("No conversations.")) {
            return;
        }
//        testCase.clickItemWithDescription("More options");
        commonModule.pressMoreOption();
        commonModule.wait(2);
        testCase.click("Delete conversations");
        testCase.clickId("select_item_num");
        testCase.click("Mark all");
        commonModule.wait(2);
        testCase.clickId("menu_delete");

        if (testCase.isViewWithIdPresent("content_description")){
        	testCase.clickId("button1"); // Delete button
        }

//        Assert.assertTrue("Remove all messages failed.",
//        		commonModule.waitForId("conversationlist_emptytext_view", 5*1000));

    }

    @Override
    public void remoteSendMessageToDUT(String textMessage, String number){
        SmsManager iSMSManager = null;
        iSMSManager = SmsManager.getDefault();

        iSMSManager.sendTextMessage(number, null, textMessage, null, null);
        commonModule.wait(3);
    }

    @Override
    public void sendSMS(String textMessage, String number) throws UiObjectNotFoundException{
    	prepareSMSMessage(textMessage,number);
    	sendMessageAndWaitMessageSendSuccessfully();
    }

    @Override
    public void addRecipient(String number){
    	testCase.clickId("recipients_editor");
    	testCase.enterText(number);
    	commonModule.wait(1);
    	//testCase.pressKey("");
    }

    @Override
    public boolean isMessageListEmpty() {

        if (testCase.isViewWithIdPresent("conversationlist_emptytext_view")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void replySMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException{

        commonModule.scrollFindText(phoneNumber);
        inputTextMessage(textMessage);
        sendMessageAndWaitMessageSendSuccessfully();
    }

    @SuppressWarnings("static-access")
	@Override
    public void waitMessageSendSuccessfully() {

        for (int j = 0; j < 20; j++) {
            if (!testCase.isViewWithTextPresent("Sending")) {
                if (testCase.isViewWithTextPresent("Could not send")) {
                    Log.d(TAG, "Message sent unsuccessfully");
                    testCase.assertTrue("Message sent is unsuccessfully", false);

                } else if (testCase.isViewWithTextPresent("Queued for sending")) {
                    Log.e(TAG, "Waiting for message sending!!!");
                    if (j == 19) {
                        Log.e(TAG, "Waiting for message sending time out");
                        testCase.assertTrue("Waiting for message sending time out", false);
                    } else {
                        commonModule.wait(5);
                    }

                } else {
                    commonModule.wait(3);
                    // testCase.assertTextNotPresent("Sending");
                    Log.d(TAG, "Message sent is successfully");
                    break;
                }
            } else {
                commonModule.wait(5);
            }
        }
    }

    @Override
    public void forwordSMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException{

        commonModule.scrollFindText(textMessage);
        commonModule.wait(2);
        testCase.longPressItemWithText(textMessage);
        testCase.click("Forward message");
        commonModule.wait(2);
        addRecipient(phoneNumber);
        testCase.clickId("conversation_edit_text");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(1);
        sendMessageAndWaitMessageSendSuccessfully();
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }

	@Override
	public void deleteMessageWithNumber(String phoneNumber) {
        if (testCase.isViewWithTextPresent(phoneNumber)) {
        	testCase.longPressItemWithText(phoneNumber);
        	testCase.sleep(1000);
        	testCase.clickItemWithId("menu_delete");
        	testCase.clickItemWithText("Delete");
        	testCase.sleep(1000);
        	testCase.setTimeout(2000);
            if (testCase.isViewWithTextPresent("Delete")) {
                testCase.clickItemWithId("button1");
            }
        } else {
            AcceptanceTestCase.assertTrue("There is no such number message.", false);
        }
        AcceptanceTestCase.assertTrue("Delete message with number failed.",
                !commonModule.isTextExists(phoneNumber));
    }

    public void insetSMSAPI(String number, String text, int smsBoxType, int read) {
        ContentValues values = new ContentValues();
        values.put("date", System.currentTimeMillis());
        values.put("read", read); // 0:unread; 1:read
        values.put("type", smsBoxType);// inbox:1; sent:2; outbox:4;
        values.put("address", number);
        values.put("body", text);
        testCase.getInstrumentation().getContext().getContentResolver()
                .insert(Uri.parse("content://sms"), values);
    }

	@Override
	public void deleteMessageWithText(String textMessage) {

	    startMessagingFromMenu();
        commonModule.scrollFindText(textMessage);
        commonModule.wait(2);
        testCase.longPressItemWithText(textMessage);
        testCase.click("Delete message");
        commonModule.wait(2);
        testCase.clickId("button1");//Delete button
        commonModule.wait(2);
	}

    @Override
    public boolean checkReceiveSMS(String textMessage, int timeout) {
        long startTime = System.currentTimeMillis();
        commonModule.backOutToHomeScreen();
        testCase.openNotification();
        commonModule.wait(1);

        while (System.currentTimeMillis() - startTime < timeout) {
            if (testCase.isViewWithTextPresent(textMessage)) {
                Log.d(TAG, "SMS receive successfully");
                if (testCase.isViewWithTextPresent("Clear")) {
                    testCase.click("Clear");
                } else {
                    testCase.clickItemWithDescription("Clear all notifications.");
                }
                return true;
            } else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(5);
                testCase.openNotification();
            }
        }
		return false;
	}

	@Override
	public void sendMMSWithImage(String textMessage, String phoneNumber, boolean verifySendSuccessfully) throws UiObjectNotFoundException {
		prepareSMSMessage(textMessage,phoneNumber);
		InsertImageForMMS();
		commonModule.wait(3);
        if (!testCase.isViewWithIdPresent("conversation_send_button")) {
            testCase.pressBackNTimes(1);
            commonModule.wait(1);
        }
        testCase.clickId("conversation_send_button");
        if(verifySendSuccessfully){
            waitMessageSendSuccessfully();
        }

    }

    public void sendMMSWithImageWithoutCheck(String textMessage, String phoneNumber) throws UiObjectNotFoundException {
        prepareSMSMessage(textMessage, phoneNumber);
        InsertImageForMMS();
        commonModule.wait(3);
        testCase.clickId("conversation_send_button");
    }

	@Override
	public void forwordMMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException {
        commonModule.scrollFindText("MMS");
        forwordSMSMessage(textMessage,phoneNumber);
	}

	@Override
	public void inputTextMessage(String textMessage) {
        testCase.clickId("conversation_edit_text");
        commonModule.wait(1);
        testCase.enterText(textMessage);

	}

	@Override
	public void prepareSMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException {
		commonModule.backOutToHomeScreen();
        testCase.launchApp("com.sonyericsson.conversations",
	            "com.sonyericsson.conversations.ui.ConversationListActivity");

        commonModule.clickResourceId(moduleData.get("New_Conversation_Icon"));
        addRecipient(phoneNumber);
        inputTextMessage(textMessage);

	}

    @Override
    public void InsertImageForMMS() throws UiObjectNotFoundException {
        testCase.clickId("conversation_tool_button");
        testCase.click("Add picture");
        if(commonModule.isTextExists("Open from")){
            commonModule.clickText("Recent");
        }

        if(!commonModule.isTextExists("Recent")){
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Recent", 2000);

            commonModule.clickText("Recent");
        }
        testCase.clickItemWithId("icon_thumb", 0);
    }

	@Override
	public void deleteMMSWithText(String textMessage) {
		commonModule.scrollFindText("MMS");
		deleteMessageWithText(textMessage);

	}

	@Override
	public void openReceiveMessage(String textMessage) {

        for (int i = 0; i < 20; i++) {
            testCase.openStatusBar();
            commonModule.wait(1);
            if (testCase.isViewWithTextPresent(textMessage)) {
                Log.d(TAG, "SMS receive successfully");
                testCase.click(textMessage);
                break;
            } else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(5);
            }
        }
    }

    @Override
    public void sendMultiSMSToOneNumber(String textMessage, String number, int count)
            throws UiObjectNotFoundException {

        for (int i = 0; i < count; i++) {
            if (i == 0) {
                prepareSMSMessage(textMessage + i, number);

            } else {
                inputTextMessage(textMessage + i);
            }
            sendMessageAndWaitMessageSendSuccessfully();
        }
    }

	public void sendSMSWithFlightMode(String textMessage, String number) throws UiObjectNotFoundException{
        prepareSMSMessage(textMessage,number);
        testCase.clickId("conversation_send_button");
        commonModule.wait(5);
        Assert.assertTrue("Send SMS not failed.",
                commonModule.waitForText("Queued for sending", 3000));
    }

	public void sendMMSWithImageWithFlightMode(String textMessage, String phoneNumber) throws UiObjectNotFoundException {
        prepareSMSMessage(textMessage,phoneNumber);
        InsertImageForMMS();
        commonModule.wait(3);
        testCase.clickId("conversation_send_button");
        commonModule.wait(10);
        Assert.assertTrue("Send MMS not failed.",
                commonModule.waitForText("Queued for sending", 3000)
                        && !commonModule.waitForText("Sending", 3000));
    }

	public void openMessageSettings() throws UiObjectNotFoundException{
	    commonModule.pressMoreOption();
	    commonModule.waitForText("Settings", 2000);

	    commonModule.clickText("Settings");
	    commonModule.waitForText("Notifications", 2000);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForDescription("More options", 2000);
    }

    public void deleteConversationByMark(String phoneNumber) {
        testCase.clickItemWithDescription("More options");
        testCase.click("Delete conversations");
        testCase.click(phoneNumber);
        commonModule.wait(2);
        testCase.clickId("menu_delete");

        if (testCase.isViewWithIdPresent("content_description")) {
            testCase.clickId("button1"); // Delete button
        }

        Assert.assertTrue("Delete message failed.", !commonModule.isTextExists(phoneNumber));
    }

    public void insertOneDraftMessage(String message) throws UiObjectNotFoundException{
        commonModule.clickDescription("New conversation");
        commonModule.waitForText("Write new", 2000);

        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
        testCase.enterText(message);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        Assert.assertTrue("Insert one draft message failed.",
                commonModule.waitForText("Draft", 2000));
    }

    public void deleteConversationsWithName(String text) {
        for (int i = 0; i < 10; i++) {
            if (testCase.isViewWithTextPresent(text)) {
                testCase.longPressItemWithText(text);
                testCase.sleep(1000);
                testCase.clickItemWithId("menu_delete");
                testCase.clickItemWithText("Delete");
                testCase.sleep(1000);
                testCase.setTimeout(2000);
                if (testCase.isViewWithTextPresent("Delete")) {
                    testCase.clickItemWithId("button1");
                }
            } else {
                break;
            }
        }
        AcceptanceTestCase.assertTrue("Delete draft message failed.",
                !commonModule.isTextExists(text));
    }

    public void insertVideoForMMS(int length) throws UiObjectNotFoundException{
        testCase.clickId("conversation_tool_button");
        testCase.click("Record video");
        commonModule.waitForResourceId("com.sonyericsson.android.camera:id/main_button", 2000);

        testCase.pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);
        commonModule.wait(length);

        //commonModule.clickResourceId("com.sonyericsson.android.camera:id/main_button");
        testCase.pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);
        AcceptanceTestCase.assertTrue("Insert video for MMS failed.",
                commonModule.waitForDescription("MMS Preview", 5000)
                     && commonModule.waitForResourceId(
                             "com.sonyericsson.conversations:id/conversation_send_button", 5000));
    }

    public void insertSoundForMMS() throws UiObjectNotFoundException {
        testCase.clickId("conversation_tool_button");
        commonModule.waitForText("Add picture", 2000);

        if(!commonModule.isTextExists("Add sound")){
            commonModule.scrollFindTextNotClick("Add sound");
        }
        commonModule.clickText("Add sound");

        if(commonModule.isTextExists("Sound Recorder")){
            commonModule.clickText("Sound Recorder");
        }else if(commonModule.isTextExists("Sound recorder")){
            commonModule.clickText("Sound recorder");
        }

        commonModule.waitForResourceId("com.android.soundrecorder:id/recordButton", 2000);

        commonModule.clickResourceId("com.android.soundrecorder:id/recordButton");
        commonModule.wait(3);

        commonModule.clickResourceId("com.android.soundrecorder:id/stopButton");
        commonModule.waitForText("Done", 2000);

        commonModule.clickText("Done");

        verifyAttachmentAttached();
    }

    public void sendMMSWithVideo(String textMessage, String phoneNumber, int videoLength) throws UiObjectNotFoundException {
        prepareSMSMessage(textMessage, phoneNumber);
        insertVideoForMMS(videoLength);
        testCase.setOrientationPortrait();
        commonModule.wait(3);
        sendMessageAndWaitMessageSendSuccessfully();
    }

    public void sendMMSWithPictureAndSound(String textMessage, String phoneNumber) throws UiObjectNotFoundException {
        prepareSMSMessage(textMessage, phoneNumber);
        InsertImageForMMS();
        insertSoundForMMS();
        commonModule.wait(3);
        sendMessageAndWaitMessageSendSuccessfully();
    }

    public void openConversationByNameOrNumber(String text) throws UiObjectNotFoundException{
        startMessagingFromMenu();
        commonModule.clickText(text);
        AcceptanceTestCase.assertTrue("Open a conversation failed.",
                commonModule.waitForText(text, 5000)
                        && commonModule.waitForText("Write message", 2000));
    }

    public void savePictureInMessage() throws UiObjectNotFoundException{
        testCase.longPressItemWithId("mms_slide_image");
        commonModule.waitForText("Save picture", 2000);

        commonModule.clickText("Save picture");
        commonModule.wait(5);
    }

    public void setMessagePictureAsWallpaper() throws UiObjectNotFoundException {
        for(int i=0;i<3;i++){
            if(commonModule.isResourceId("com.sonyericsson.conversations:id/mms_slide_image")){
                commonModule.clickResourceId("com.sonyericsson.conversations:id/mms_slide_image");
            }
        }
        commonModule.wait(2);

        if (commonModule.isTextExists("Complete action using")
                && commonModule.isTextExists("Album")) {
            commonModule.clickText("Album");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        commonModule.wait(2);

        int screen[] = testCase.getScreenSize();
        testCase.clickPoint(screen[0] / 2, screen[1] / 2);
        commonModule.waitForResourceId("android:id/home", 2000);

        commonModule.pressMoreOption();
        commonModule.waitForText("Use as", 2000);

        commonModule.clickText("Use as");
        if (commonModule.waitForText("Wallpaper", 2000)) {
            commonModule.clickText("Wallpaper");
        }
        if(commonModule.waitForText("Crop picture", 2000)){
            commonModule.clickText("Crop picture");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        commonModule.waitForText("Crop", 2000);

        commonModule.clickText("Crop");
        commonModule.waitForTextContains("Setting wallpaper", 2000);
        AcceptanceTestCase.assertTrue("Set message picture as wallpaper failed.",
                commonModule.waitForTextGone("Setting wallpaper…", 3000));
    }

    public void playSoundInMessage() throws UiObjectNotFoundException {
        commonModule.clickTextContains("recording");
        if (commonModule.isTextExists("Complete action using")
                && commonModule.isTextExists("Walkman")) {
            commonModule.clickText("Walkman");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        AcceptanceTestCase.assertTrue("Play sound in message failed.", commonModule
                .waitForResourceId("com.sonyericsson.music:id/player_play_pause_button", 3000)||commonModule
                .waitForText("Home", 3000));
				if(commonModule.isTextExists("Home")){
					testCase.pressKey(KeyEvent.KEYCODE_BACK);
				}
        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Back to message view.
    }

    public void saveSoundInMessage() throws UiObjectNotFoundException{
        commonModule.longClickTextContains("recording");
        commonModule.waitForText("Save audio", 2000);

        commonModule.clickText("Save audio");
        commonModule.wait(3);
    }

    public void setSoundAsRingtone() throws UiObjectNotFoundException{
        commonModule.longClickTextContains("recording");
        commonModule.waitForText("Set audio file as ringtone", 2000);

        commonModule.clickText("Set audio file as ringtone");
        AcceptanceTestCase.assertTrue("Set sound as ringtone failed.",
                commonModule.waitForTextContains("recording", 5000));
    }

    public void playVideoInMessage() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.sonyericsson.conversations:id/mms_slide_video_indicator");
        if (commonModule.isTextExists("Complete action using")
                && commonModule.isTextExists("Movies")) {
            commonModule.clickText("Movies");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }

        int screen[] = testCase.getScreenSize();
        testCase.clickPoint(screen[0] / 2, screen[1] / 2);
        commonModule.waitForResourceId("android:id/home", 2000);

        AcceptanceTestCase.assertTrue("Play video in message failed.", commonModule.waitForPackage("com.sonyericsson.video", 5000));

        commonModule.waitForResourceId(
                "com.sonyericsson.conversations:id/mms_slide_video_indicator", 10 * 1000);
    }

    public void saveVideoInMessage() throws UiObjectNotFoundException{
        testCase.longPressItemWithId("mms_slide_video_indicator");
        commonModule.waitForText("Save video", 3000);

        commonModule.clickText("Save video");
        commonModule.wait(3);
    }

    public void waitMoreMessagesReceive(int smsCount) throws UiObjectNotFoundException {
        for (int i = 0; i < 20; i++) {
            testCase.openNotification();
            commonModule.wait(1);

            if (commonModule.isTextContains("New messages: ")) {

                String msginfo = new UiObject(new UiSelector().textContains("New messages: "))
                        .getText();
                int msgcount = Integer.valueOf(msginfo.split(": ")[1]);

                if (msgcount == smsCount) {
                    Log.d(TAG, "SMS receive successfully");
                    break;
                }
            } else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(5);
            }
        }
    }

    public void readReceiveMessageFromNotification(String smsContent) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Notifications")) {
            testCase.openNotification();
            commonModule.wait(1);
        }
        if (commonModule.isTextExists(smsContent)) {
            commonModule.clickText(smsContent);
        } else if (commonModule.isTextContains("New messages: ")) {
            commonModule.clickTextContains("New messages: ");
        }

        if (commonModule.waitForText("Messaging", 2000)) {
            commonModule.clickTextContains(smsContent);
        }
        AcceptanceTestCase.assertTrue(
                "Read one of receive messages from nuotification failed.",
                commonModule.waitForText(smsContent, 2000)
                        && commonModule.waitForText("Write message", 2000));
    }

    public void checkStatusBarForMoreSMS(int beforeCount, String smsContent)
            throws UiObjectNotFoundException {
        waitMoreMessagesReceive(beforeCount);
        readReceiveMessageFromNotification(smsContent);

        testCase.openNotification();
        commonModule.wait(1);

        if (beforeCount > 2) {
            String msginfo = new UiObject(new UiSelector().textContains("New messages: "))
                    .getText();
            int msgcount = Integer.valueOf(msginfo.split(": ")[1]);
            int afterCount = beforeCount - 1;

            AcceptanceTestCase.assertTrue("SMS count changed wrong.", afterCount <= msgcount);
        } else {
            AcceptanceTestCase.assertTrue("SMS count changed wrong.",
                    !commonModule.isTextExists("New messages: "));
        }

        commonModule.clearNotificationsBar();

    }

    public void deleteMMSAPN() throws UiObjectNotFoundException {
        commonModule.backOutToHomeScreen();
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        commonModule.waitForText("Settings", 2000);

        commonModule.clickText("More…");
        commonModule.waitForText("Mobile networks", 2000);

        commonModule.clickText("Mobile networks");
        commonModule.waitForText("Access Point Names", 2000);

        commonModule.clickText("Access Point Names");
        if (commonModule.waitForText("MMS", 2000)) {

            commonModule.clickText("MMS");
            commonModule.waitForText("Edit access point", 2000);

            commonModule.pressMoreOption();
            commonModule.waitForText("Delete APN", 2000);

            commonModule.clickText("Delete APN");
            AcceptanceTestCase.assertTrue("Delete MMS APN failed.",
                    commonModule.waitForTextGone("MMS", 3000)
                            && commonModule.waitForText("APNs", 3000));
        }
        commonModule.backOutToHomeScreen();
    }

    public void setupMMSAPN() throws UiObjectNotFoundException {
        commonModule.backOutToHomeScreen();
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        commonModule.waitForText("Settings", 2000);

        commonModule.clickText("More…");
        commonModule.waitForText("Mobile networks", 2000);

        commonModule.clickText("Mobile networks");
        commonModule.waitForText("Access Point Names", 2000);

        commonModule.clickText("Access Point Names");
        commonModule.waitForText("APNs", 2000);

        if (commonModule.isTextExists("MMS")) {
            commonModule.backOutToHomeScreen();
            return;
        }
        commonModule.clickDescription("New APN");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.clickText("Name");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("MMS");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.clickText("APN");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("tmcwap");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.scrollFindText("MMSC");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("http://192.168.23.180:8080");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.scrollFindText("MMS proxy");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("192.168.23.5");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.scrollFindText("MMS port");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("8000");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.scrollFindText("APN type");
        commonModule.waitForText("OK", 2000);
        testCase.enterText("mms");
        commonModule.clickText("OK");
        commonModule.waitForText("Edit access point", 2000);

        commonModule.pressMoreOption();
        commonModule.waitForText("Save", 2000);
        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue("Setup MMS APN failed.",
                commonModule.waitForText("MMS", 3000) && commonModule.waitForText("APNs", 3000));
        commonModule.backOutToHomeScreen();
    }

    public void receiveMMSWithoutAPN(String number, String content) throws UiObjectNotFoundException {
        deleteMMSAPN();
        remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, number);
        openReceiveMessage("(MMS) ");

        AcceptanceTestCase.assertTrue("Can't receive MMS or download MMS without APN setting.",
                commonModule.waitForText("Message not downloaded", 5 * 1000)
                        && !commonModule.waitForText(content, 5 * 1000));
    }

    public void reciveMMSWithAPN(String number, String content) throws UiObjectNotFoundException {
        setupMMSAPN();
        remoteSendMessageToDUT(CommandConstantsUtils.COMMAND_MMS, number);
        openReceiveMessage("(MMS) Do you like the picture and sound?");

        AcceptanceTestCase.assertTrue("MMS receive failed with MMS APN.",
                commonModule.waitForText(content, 5 * 1000)
                        && !commonModule.waitForText("Message not downloaded", 5 * 1000));
    }

    public void checkReceivedMessageInfo(String senderName){
        startMessagingFromMenu();

        for(int i=0; i<20; i++){
            if(commonModule.isTextExists(senderName)){
                return;
            }else if(i== 19){
                testCase.failTest("message didn't received");
            }else{
                commonModule.wait(2);
            }
        }
    }

    public void sendMessageByPasteContent(String number) throws UiObjectNotFoundException {
        startMessagingFromMenu();

        commonModule.clickResourceId(moduleData.get("New_Conversation_Icon"));
        addRecipient(number);

        // Focus message content area.
        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
        commonModule.wait(1);

        UiObject conEdit = new UiObject(new UiSelector()
                        .resourceId("com.sonyericsson.conversations:id/conversation_edit_text"));
        Rect conArea = conEdit.getVisibleBounds();
        commonModule.longClickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
//        commonModule.clickText("PASTE");
        testCase.clickPoint(conArea.left + 100, conArea.top - 50);// Press PASTE button.

        sendMessageAndWaitMessageSendSuccessfully();
    }

    public void makeACallFromMessageContent(String conversation, String callTo) throws UiObjectNotFoundException{
        openConversationByNameOrNumber(conversation);

        commonModule.clickText(callTo);

        AcceptanceTestCase.assertTrue("Call didn't setup", commonModule.waitForPackage("com.sonyericsson.android.socialphonebook", 5*1000));

        //call
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/call_button");
        AcceptanceTestCase.assertTrue("Call didn't setup", commonModule.waitForText("Calling", 5*1000));

        commonModule.pressKey(KeyEvent.KEYCODE_ENDCALL);
    }

    public void sendMailFromMessageContent(String conversation, String sendTo, String subject) throws UiObjectNotFoundException{

        openConversationByNameOrNumber(conversation);

        commonModule.clickText(sendTo);

        if(commonModule.waitForText("Complete action using", 3000)){
            commonModule.clickText("Email");
            commonModule.wait(1);

            commonModule.clickText("Just once");
        }

        AcceptanceTestCase.assertTrue("Send email state not opened", commonModule.waitForText("Send", 5000));

        testCase.enterText(subject);
        commonModule.wait(1);

        commonModule.clickText("Send");
    }

    public void openURLFromMessageContent(String conversation, String address) throws UiObjectNotFoundException{

        openConversationByNameOrNumber(conversation);

        commonModule.clickText(address);

        if(commonModule.waitForText("Complete action using", 3000)){
            commonModule.clickText("Chrome");
            commonModule.wait(1);

            commonModule.clickText("Just once");
        }

        AcceptanceTestCase.assertTrue("Send email state not opened", commonModule.waitForPackage("com.android.chrome", 5000));
    }

    public void copyMessageToSimCard(String message) throws UiObjectNotFoundException{

        testCase.longPressItemWithText(message);

        commonModule.waitForText("Copy message to SIM card", 3000);
        commonModule.clickText("Copy message to SIM card");

    }

    public void simMessageCopyToPhoneOrDelete(String message, String operate) throws UiObjectNotFoundException{

        startMessagingFromMenu();

        commonModule.pressMoreOption();
        commonModule.clickText("Settings");

        AcceptanceTestCase.assertTrue("Settings should be opened with text 'Notifications'", commonModule.waitForText("Notifications", 3000));

        commonModule.scrollFindText("SIM messages");

        AcceptanceTestCase.assertTrue("Sim message should display here", commonModule.waitForText(message, 10*1000));

        testCase.longPressItemWithText(message);

        commonModule.clickText(operate);

        if(operate == "Copy to phone"){
            commonModule.waitForTextGone("Copying message", 10*1000);
        }else if(operate == "Delete message"){
            AcceptanceTestCase.assertTrue("Delete message confirmation note should popup",
                    commonModule.waitForText("Delete message?", 10*1000));

            commonModule.clickText("Delete");
            AcceptanceTestCase.assertTrue("sim message should be deleted",
                    !commonModule.waitForText(message, 3*1000));
        }

    }

    public void smsConvertToMmsByAddAttachment() throws UiObjectNotFoundException {
        InsertImageForMMS();

        AcceptanceTestCase.assertTrue("SMS convert to MMS by add attachment failed.",
                commonModule.waitForText("MMS", 2000));
    }

    public void mmsConvertToSmsByRemoveAttachment() throws UiObjectNotFoundException {
        if (!commonModule.isDescriptionExists("Add picture")) {
            testCase.pressBackNTimes(1);
            commonModule.waitForDescription("Add picture", 2000);
            commonModule.wait(2);
        }
        UiObject attach = new UiObject(new UiSelector()
                .resourceId("com.sonyericsson.conversations:id/conversation_attachment_masterpile"));
        Rect att = attach.getVisibleBounds();

        // Click point to remove the attachment.
        int x = att.centerX() + 20;
        int y = att.top + 50;
        testCase.clickPoint(x, y);
        System.out.println("x=" + x + ",    y=" + y);

        AcceptanceTestCase.assertTrue("MMS convert to SMS by remove attachment failed.",
                commonModule.waitForTextGone("MMS", 2000));
    }

    public void smsConvertToMmsByAddSubject(String subject) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForText("Add subject", 2000);

        commonModule.clickText("Add subject");
        commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_edit_subject", 2000);

        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_subject");
        testCase.enterText(subject);

        AcceptanceTestCase.assertTrue("SMS convert to MMS by add subject failed.",
                commonModule.waitForText("MMS", 2000) && commonModule.waitForText(subject, 2000));
    }

    public void mmsConvertToSmsByRemoveSubject() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForText("Delete subject", 2000);

        commonModule.clickText("Delete subject");
        AcceptanceTestCase.assertTrue("SMS convert to MMS by delete subject failed.",
                commonModule.waitForTextGone("MMS", 2000));
    }

    public void sendMessageAndWaitMessageSendSuccessfully() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_send_button");
        waitMessageSendSuccessfully();
    }

    public void verifyAttachmentAttached(){

        AcceptanceTestCase.assertTrue("The preview icon should display when something inserted",
                commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_preview", 8000));
    }

    public void insertImageFromShortcut() throws UiObjectNotFoundException {
        if(!commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_add_media_btn", 3000)){
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(3);
        }

        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_add_media_btn");

        if(commonModule.isTextExists("Open from")){
            commonModule.clickText("Recent");
        }

        if(!commonModule.isTextExists("Recent")){
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Recent", 2000);

            commonModule.clickText("Recent");
        }

        commonModule.clickResourceIdByInstance("com.android.documentsui:id/icon_thumb", 0);

        verifyAttachmentAttached();
    }

    public void insertCameraPicture(String from) throws UiObjectNotFoundException {
        if(!commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_take_photo", 3000)){
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(3);
        }

        if(from.equalsIgnoreCase("shortcut")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_take_photo");
        }else if(from.equalsIgnoreCase("plusIcon")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_tool_button");

            commonModule.scrollFindText("Take photo");
        }

        if(commonModule.isTextExists("Complete action using")){
            commonModule.clickText("Camera");
            commonModule.wait(2);

            commonModule.clickText("Just once");
        }

        testCase.pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);

        verifyAttachmentAttached();
    }

    public void insertSketchNote(String from) throws UiObjectNotFoundException {
        if(!commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_sketch_button", 3000)){
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(3);
        }

        if(from.equalsIgnoreCase("shortcut")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_sketch_button");
        }else if(from.equalsIgnoreCase("plusIcon")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_tool_button");

            commonModule.scrollFindText("Add sketch");
        }

        AcceptanceTestCase.assertTrue("Sketch should be launched", commonModule.waitForPackage("com.sonymobile.sketch", 5000));

        int width = testCase.getScreenSize()[0];
        int height = testCase.getScreenSize()[1];
        testCase.getUiDevice().drag(width/4, height/4, width/4*3, height/4*3, 80);

        commonModule.clickText("Done");

        verifyAttachmentAttached();
    }

    public void insertLocation(String from) throws UiObjectNotFoundException {
        if(!commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_share_location_button", 3000)){
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(3);
        }
        if(from.equalsIgnoreCase("shortcut")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_share_location_button");
        }else if(from.equalsIgnoreCase("plusIcon")){
            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_tool_button");

            commonModule.scrollFindText("Add my location");
        }

        if(commonModule.waitForResourceId("android:id/button1", 5000)){
            commonModule.clickText("No");
        }

        AcceptanceTestCase.assertTrue("Share location icon should display",
                commonModule.waitForResourceId("com.sonyericsson.conversations:id/addressfragment_share_button", 8000));

        commonModule.clickResourceId("com.sonyericsson.conversations:id/addressfragment_share_button");

        verifyAttachmentAttached();
    }

    public void createMessage() throws UiObjectNotFoundException{
        startMessagingFromMenu();

        commonModule.clickResourceId("com.sonyericsson.conversations:id/menu_new_conversation");

        AcceptanceTestCase.assertTrue("A new message state should be opened",
                commonModule.waitForResourceId("com.sonyericsson.conversations:id/conversation_edit_text", 5000));
    }

    public void insertContacts(String contactName) throws UiObjectNotFoundException{
        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_tool_button");

        commonModule.clickText("Add contacts");
        AcceptanceTestCase.assertTrue("Contacts should be launched", commonModule.waitForPackage("com.sonyericsson.android.socialphonebook", 5000));

        commonModule.scrollFindText(contactName);
        commonModule.clickText("Done");

        AcceptanceTestCase.assertTrue("Select send contact via 'plain text' or 'vCard' display", commonModule.waitForText("Send contact via", 5000));

        commonModule.clickText("vCard");

        verifyAttachmentAttached();
    }

    public void sendMMSWithCameraPicture(String textMessage, String phoneNumber, String insertFrom) throws UiObjectNotFoundException {
        prepareSMSMessage(textMessage, phoneNumber);
        insertCameraPicture(insertFrom);
        testCase.setOrientationPortrait();
        commonModule.wait(3);
        sendMessageAndWaitMessageSendSuccessfully();
    }

    public void scanAndInsertFileForMMS(String folderName) throws UiObjectNotFoundException {
        testCase.clickId("conversation_tool_button");
        testCase.click("Add picture");
        if(commonModule.isTextExists("Open from")){
            commonModule.clickText("Internal storage");
        }

        if(!commonModule.isTextExists("Internal storage")){
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Internal storage", 2000);

            commonModule.clickText("Internal storage");
        }

        commonModule.scrollFindText(folderName);
        //Scan files
        for(int i=0;i<10;i++){
			        	testCase.scrollDown();
			        	AcceptanceTestCase.assertTrue("File display wrong", commonModule.waitForResourceId("com.android.documentsui:id/icon_thumb", 2000));
        }


        testCase.clickItemWithId("icon_thumb", 0);

        commonModule.wait(2);
        AcceptanceTestCase.assertTrue("Message change to MMS", commonModule.waitForText("MMS",5000));
    }


    public void sendSMSToMultipleRecipient(int recipients, String content, String receiver) throws UiObjectNotFoundException{
        testCase.launchApp("com.sonyericsson.conversations",
	            "com.sonyericsson.conversations.ui.ConversationListActivity");
        commonModule.wait(2);
        commonModule.clickResourceId(moduleData.get("New_Conversation_Icon"));
        addRecipient(receiver);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
        if(recipients>0){
		        for(int i=0;i<recipients;i++){
		        	addRecipient("1569900"+i);
		        	commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
		        }
        }

        inputTextMessage(content);
        sendMessageAndWaitMessageSendSuccessfully();
    }

    public void checkReceiveMessage(boolean selSender,String content) throws UiObjectNotFoundException{
			    	if(selSender){
					        UiObject object = new UiObject(new UiSelector().text(content).instance(1));
					        AcceptanceTestCase.assertTrue("Cannot receive message",object.waitForExists(120*1000));
					   }else{
						   AcceptanceTestCase.assertTrue("Cannot receive message",commonModule.waitForText(content, 120*1000));
					   }
    }
}
