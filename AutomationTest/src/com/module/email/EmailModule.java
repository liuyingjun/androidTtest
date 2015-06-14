package com.module.email;

import java.util.HashMap;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.util.Log;
import android.view.KeyEvent;

public class EmailModule implements IEmail{
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    String TAG = "Reliability";

    HashMap<String, String> moduleData;

    public EmailModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("email");
    }

    public boolean loginEmailSyncAutoServer(String userName, String password) throws UiObjectNotFoundException {
        boolean isSuccess = false;
        commonModule.backOutToHomeScreen();
        testCase.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
        commonModule.wait(4);

        if (commonModule.isResourceId(moduleData.get("Account_Email_EditText_Id"))) {
            commonModule.clickResourceId(moduleData.get("Account_Email_EditText_Id"));
            testCase.enterText(userName);
            commonModule.wait(1);
            commonModule.clickResourceId(moduleData.get("Account_Password_EditText_Id"));
            testCase.enterText(password);
            commonModule.wait(1);
            commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
            commonModule.wait(30);

            if(testCase.isViewWithTextPresent("Check frequency")){
                testCase.click("Next");
                commonModule.wait(2);
            }

            if (commonModule.isTextExists("POP3")) {
                testCase.failTest("Poor network!");
            }

            if(testCase.isViewWithTextPresent("Account setup")){
                commonModule.clickResourceId(moduleData.get("Account_Name_EditText_Id"));
                commonModule.wait(1);
                testCase.enterText("rt");

                testCase.click("Next");
                commonModule.wait(1);

            }
            commonModule.waitForDescription("Compose", 30*1000);
            isSuccess = true;

        } else if (commonModule.isResourceId(moduleData.get("Compose_Button_Id"))
                || testCase.isViewWithTextPresent("No mail")) {
            isSuccess = !isSuccess;
            Log.e(TAG, "Email Account is already configured.");
        }
        AcceptanceTestCase.assertTrue(isSuccess);
        return isSuccess;
    }

    public void sendEmailWithAttachment(String to, String subject, String attachName) throws UiObjectNotFoundException {
        launchEmail();
        commonModule.clickDescription("Compose");// Tap new email icon.
        commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 2000);

        // add attachment
        commonModule.clickDescription("Attach file");
        if (!commonModule.waitForText("Internal storage", 2000)) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Internal storage", 2000);

            commonModule.clickText("Internal storage");
            commonModule.waitForDescription("Search", 2000);
        } else if (commonModule.waitForText("Internal storage", 2000)
                && commonModule.waitForText("Open from", 2000)) {
            commonModule.clickText("Internal storage");
            commonModule.waitForDescription("Search", 2000);
        }
        commonModule.scrollFindText("testresource");
        commonModule.wait(1);
        commonModule.scrollFindText(attachName);
        if (commonModule.isTextExists("Resize image")) {
            commonModule.clickText("Small");
            commonModule.wait(1);

            commonModule.clickText("Always");
        }
        AcceptanceTestCase.assertTrue(
                "Add attachment failed.",
                commonModule.waitForResourceId(moduleData.get("Attachment_Icon_Id"), 3000)
                        && commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 3000));

        // input recipient
        commonModule.clickResourceId(moduleData.get("Email_To_EditText_Id"));
        testCase.enterText(to);
        testCase.getUiDevice().pressEnter();
        AcceptanceTestCase.assertTrue("", commonModule.isTextContains(to));

        commonModule.clickResourceId(moduleData.get("Email_Subject_EditText_Id"));
        testCase.enterText(subject);
        testCase.assertTextPresent(subject);

        commonModule.clickResourceId(moduleData.get("Email_Content_EditText_Id"));
        testCase.enterText("Test for RT email.");
        commonModule.waitForTextContains("Test for RT email.", 2000);

        commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
        AcceptanceTestCase.assertTrue("Send email failed.",
                commonModule.waitForDescription("Compose", 2000));
    }

    public void sendEmailDeleteAttachment(String attachName, String subject) throws UiObjectNotFoundException {
        commonModule.clickDescription("Compose");// Tap new email icon.
        commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 2000);

        // add attachment
        commonModule.clickDescription("Attach file");
        if (!commonModule.waitForText("Internal storage", 2000)) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Internal storage", 2000);

            commonModule.clickText("Internal storage");
            commonModule.waitForText("Internal storage", 2000);
            commonModule.waitForDescription("Search", 2000);
        }
        commonModule.scrollFindText(attachName);
        if (commonModule.isTextExists("Resize image")) {
            commonModule.clickText("Small");
            commonModule.wait(1);

            commonModule.clickText("Always");
        }
        AcceptanceTestCase.assertTrue(
                "Add attachment failed.",
                commonModule.waitForText(attachName, 2000)
                        && commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 2000));

        commonModule.clickResourceId(moduleData.get("Attachment_Delete_Button_Id"));
        AcceptanceTestCase.assertTrue("", commonModule.waitForTextGone(attachName, 2000));

        // input recipient
        commonModule.clickResourceId(moduleData.get("Email_To_EditText_Id"));
        testCase.enterText("agingtest005@gmail.com");
        testCase.assertTextPresent("agingtest005@gmail.com");

        commonModule.clickResourceId(moduleData.get("Email_Subject_EditText_Id"));
        testCase.enterText(subject);
        testCase.assertTextPresent(subject);

        commonModule.clickResourceId(moduleData.get("Email_Content_EditText_Id"));
        testCase.enterText("Test for RT email.");
        commonModule.waitForTextContains("Test for RT email.", 2000);

        commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
        AcceptanceTestCase.assertTrue("Send email failed.",
                commonModule.waitForDescription("Compose", 2000));
    }

    public void checkEmailSendSuccessfully(String subject) throws UiObjectNotFoundException {
        commonModule.clickResourceId(moduleData.get("Home_Button_Id"));
        commonModule.waitForText("Sent", 2000);

        commonModule.clickText("Sent");
        for (int i = 0; i < 20; i++) {

            if (!commonModule.isTextExists("agingtest005@gmail.com")
                    || !commonModule.isTextExists(subject)) {

                int[] screen = testCase.getScreenSize();
                testCase.dragViewBetweenTwoPosition(screen[0] / 2, screen[1] / 5, screen[0] / 2,
                        (screen[1] / 5) * 4, 30);// Drag to refresh.
                commonModule.wait(3);
            } else if (commonModule.isTextExists("agingtest005@gmail.com")
                    && commonModule.isTextExists(subject)) {
                break;
            } else if (i == 19 && !commonModule.isTextExists("agingtest005@gmail.com")
                    || !commonModule.isTextExists(subject)) {
                testCase.failTest("Poor network, the email not send successfully.");
            }
        }
        // AcceptanceTestCase.assertTrue(
        // "Send email failed.",
        // commonModule.waitForText("agingtest005@gmail.com", 2000)
        // && commonModule.waitForText(subject, 2000));
    }

	public void launchEmail(){
    	testCase.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
    	commonModule.wait(5);
    	testCase.waitForActivitySwitch();
    }

    public void sendEmail(String emailSender, String emailReceiver, String subject, String content,
            boolean ccAccount) throws UiObjectNotFoundException {

        launchEmail();
        commonModule.wait(4);
        if (commonModule.isResourceId(moduleData.get("Compose_Button_Id"))
                || testCase.isViewWithTextPresent("No mail")) {
            commonModule.clickResourceId(moduleData.get("Compose_Button_Id"));
            if(emailSender!=null){
            commonModule.clickResourceId(moduleData.get("Accounts_Selector_Button_Id"));
            testCase.click(emailSender);
            }
            commonModule.wait(2);
            commonModule.clickResourceId(moduleData.get("Email_To_EditText_Id"));
            commonModule.wait(2);
            testCase.enterText(emailReceiver);
            commonModule.wait(2);
            if (ccAccount) {
                testCase.pressKey(KeyEvent.KEYCODE_MENU);
                testCase.click("Show Cc/Bcc");
                // Add cc account
                commonModule.clickResourceId(moduleData.get("Email_CC_Button_Id"));
                if (commonModule.isResourceId(moduleData.get("Check_Box_Id"))) {
                    testCase.clickItemWithId("checklist_check", 0);
                    if (commonModule.isResourceId(moduleData.get("Email_Selector_Title_Id"))) {
                        testCase.clickItemWithId("dialog_checklist_check", 0);
                    }
                } else {
                    commonModule.clickResourceId(moduleData.get("Search_Text_EditText_Id"));
                    testCase.enterText("agingtestserver@gmail.com");
                    commonModule.clickResourceId(moduleData.get("Phonebook_Add_Account_Button_Id"));
                }
                testCase.click("Done");

                // Add bcc account
                commonModule.clickResourceId(moduleData.get("Email_Bcc_Pluc_Button_Id"));
                if (commonModule.isResourceId(moduleData.get("Check_Box_Id"))) {
                    testCase.clickItemWithId("checklist_check", 1);
                    if (commonModule.isResourceId(moduleData.get("Email_Selector_Title_Id"))) {
                        testCase.clickItemWithId("dialog_checklist_check", 0);
                    }
                } else {
                    commonModule.clickResourceId(moduleData.get("Search_Text_EditText_Id"));
                    testCase.enterText("agingtestserver@gmail.com");
                    commonModule.clickResourceId(moduleData.get("Phonebook_Add_Account_Button_Id"));
                }
                testCase.click("Done");
            }

            testCase.pressKey(KeyEvent.KEYCODE_ENTER);
            commonModule.wait(2);
            testCase.clickItemWithId("subject");
            commonModule.wait(2);
            testCase.enterText(subject);
            commonModule.wait(2);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.clickResourceId(moduleData.get("Email_Content_EditText_Id"));

            testCase.enterText(content);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
            commonModule.wait(10);

        }
        commonModule.backOutToHomeScreen();
    }

    public void sortBy(String sortType) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForText("Sort", 2000);

        commonModule.clickText("Sort");
        commonModule.waitForText("Sort by", 2000);

        commonModule.clickText(sortType);
        commonModule.waitForTextGone("Sort by", 2000);

        commonModule.clickResourceId("com.android.email:id/message_list_header_text");
        commonModule.wait(2);

        commonModule.clickResourceId("com.android.email:id/message_list_header_text");
        commonModule.wait(2);
    }

    public void replyEmail(boolean searchEmail) throws UiObjectNotFoundException {
        // select one of mail.
			    	if(searchEmail){
			        commonModule.clickDescription("Search");
			        commonModule.waitForResourceId("android:id/search_plate", 2000);
			
			        commonModule.clickResourceId("android:id/search_plate");
			        testCase.enterText("agingtest");
			        testCase.enterText("\n");
			        commonModule.waitForResourceId("com.android.email:id/search_filter", 5000);
			
			        commonModule.clickResourceId("com.android.email:id/search_filter");
			        commonModule.waitForText("From", 2000);
			
			        commonModule.clickText("From");
			        commonModule.waitForText("Older email", 5000);
			    	}
        UiObject list = new UiObject(new UiSelector().resourceId("android:id/list"));
        // UiObject emailItem = new UiObject(new UiSelector().className(
        // "android.widget.RelativeLayout").index(1));
        UiObject emailItem = list.getChild(new UiSelector().className(
                "android.widget.RelativeLayout").index(1));
        emailItem.click();
        commonModule.waitForText("Reply", 2000);

        // reply email
        commonModule.clickText("Reply");
        commonModule.waitForResourceId("com.android.email:id/send", 2000);

        commonModule.clickResourceId("com.android.email:id/to");
        testCase.enterText("agingtest005@gmail.com");

        commonModule.clickResourceId("com.android.email:id/message_content");
        testCase.enterText("Test for RT.");
        commonModule.waitForTextContains("Test for RT.", 2000);

        commonModule.clickResourceId("com.android.email:id/send");
        AcceptanceTestCase.assertTrue(
                "Reply email failed.",
                !commonModule.waitForResourceId("com.android.email:id/send", 3000)
                        && commonModule.waitForText("Reply", 3000));
    }

    public boolean addAccount(String account, String password, int timeMin) throws UiObjectNotFoundException {
        boolean isSuccess = false;
        launchEmail();
        if (testCase.isViewWithTextPresent("Account setup")){
        	loginEmailSyncAutoServer(account, password);
        }
        else{
	        commonModule.pressMoreOption();
	        commonModule.waitForText("Settings", 2000);

	        commonModule.clickText("Settings");
	        if (!commonModule.isTextExists(account)) {
	            commonModule.waitForText("Add account", 2000);

	            commonModule.clickText("Add account");
	            commonModule.wait(4);

	            if (commonModule.isResourceId(moduleData.get("Account_Email_EditText_Id"))) {
	                commonModule.clickResourceId(moduleData.get("Account_Email_EditText_Id"));
	                testCase.enterText(account);
	                commonModule.wait(1);
	                commonModule.clickResourceId(moduleData.get("Account_Password_EditText_Id"));
	                testCase.enterText(password);
	                commonModule.wait(1);
	                commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
	                commonModule.wait(30);
	                for (int j = 0; j < 5; j++) {
	                    if (testCase.isViewWithTextPresent("IMAP")) {
	                        testCase.click("IMAP");
	                        commonModule.wait(10);
	                        if (commonModule.isResourceId(moduleData.get("Account_Port_EditText_Id"))) {
	                            commonModule
	                                    .clickResourceId(moduleData.get("Account_Port_EditText_Id"));
	                            String serverText = new UiObject(new UiSelector().resourceId(moduleData
	                                    .get("Account_Port_EditText_Id"))).getText();

	                            for (int i = 0; i < serverText.length(); i++) {
	                                testCase.pressKey(KeyEvent.KEYCODE_DEL);
	                            }
	                            testCase.enterText("993");
	                            commonModule.wait(1);
	                            testCase.pressKey(KeyEvent.KEYCODE_BACK);
	                            commonModule.wait(2);
	                            commonModule.clickResourceId(moduleData
	                                    .get("Account_Security_Type_Spinner_Id"));
	                            commonModule.wait(2);
	                            testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
	                            testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
	                            testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
	                            testCase.pressKey(KeyEvent.KEYCODE_ENTER);
	                            commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
	                            commonModule.wait(20);
	                        }
	                        break;
	                    } else if (commonModule.isTextExists("Check frequency")) {
	                        commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
	                        commonModule.waitForText("Account setup", 2000);

	                        commonModule.emptyEditorByInstance(1);
	                        testCase.enterText(account);

	                        commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
	                        commonModule.wait(20);
	                        break;
	                    } else {
	                        commonModule.wait(10);
	                    }
	                }


	                // login 3min
	                long currTime = commonModule.getCurrTime();
	                long afterTime = commonModule.getCurrTime() + timeMin * 60 * 1000L;

	                while (commonModule.getCurrTime() < afterTime) {
	                    commonModule.wait(1);
	                    if (testCase.isViewWithIdPresent("compose")
	                            || testCase.isViewWithTextPresent("No mail")) {
	                        // login seccuess
	                        commonModule.wait(5);
	                        break;
	                    } else if (testCase
	                            .isViewWithTextPresent("Could not create the account.Try again later.")) {
	                        if (testCase.isViewWithTextPresent("Cancel")) {
	                            testCase.click("Cancel");
	                            Log.d(TAG, "The email account cannot be created.");
	                            return isSuccess;
	                        }
	                    }
	                }
	                commonModule.wait(5);
	                if (commonModule.isResourceId(moduleData.get("Compose_Button_Id"))
	                        || testCase.isViewWithTextPresent("No mail")) {
	                    // login success
	                    Log.d(TAG, "Email Account login sucessully");
	                    isSuccess = !isSuccess;
	                }
	            } else if (commonModule.isResourceId(moduleData.get("Compose_Button_Id"))
	                    || testCase.isViewWithTextPresent("No mail")) {
	                isSuccess = !isSuccess;
	                Log.e(TAG, "Email Account is already configured.");
	            }
	            testCase.assertTrue(isSuccess);
	            return isSuccess;
	        }
	        else{
	        	commonModule.backOutToHomeScreen();
	        }
        }
        return isSuccess;
    }

    public void setSynchronizFrequency(String account, String frequency) throws UiObjectNotFoundException {
        launchEmail();
        commonModule.wait(8);

        commonModule.pressMoreOption();
        commonModule.waitForText("Settings", 2000);

        commonModule.clickText("Settings");
        commonModule.waitForText(account, 2000);

        commonModule.clickText(account);
        commonModule.waitForText("Check frequency", 2000);

        commonModule.clickText("Check frequency");
        commonModule.waitForText("Account check frequency", 2000);

        commonModule.clickText("Check frequency");
        commonModule.waitForText(frequency, 2000);

        commonModule.clickText(frequency);

        if (testCase.isViewWithTextPresent("OK")){
        	commonModule.clickText("OK");
        }

        AcceptanceTestCase.assertTrue(
                "Set synchroniz frequency failed.",
                commonModule.waitForText("Check frequency", 5000)
                        && commonModule.waitForText(frequency, 5000));
    }

	 public void addGoogleAccount(String account, String password) throws UiObjectNotFoundException {
    	 testCase.launchApp("com.android.settings", "com.android.settings.Settings");
         commonModule.waitForText("Settings", 2000);

         commonModule.scrollFindTextNotClick("Add account");

         if(commonModule.isTextExists("Google")){
		        	 commonModule.clickText("Google");
		        	 if(commonModule.waitForText(account, 3000)){
		             return;
             }else{
            	 commonModule.pressKey(KeyEvent.KEYCODE_BACK);
             }
		        	 }
             commonModule.clickText("Add account");

             commonModule.clickText("Google");

             commonModule.waitForText("Existing", 2000);

             commonModule.clickText("Existing");

             commonModule.emptyEditorByInstance(0);
             testCase.enterText(account);

             commonModule.emptyEditorByInstance(1);
             testCase.enterText(password);

             commonModule.clickResourceId(moduleData.get("Google_Next_Button"));
             if(commonModule.isTextExists("OK")){
                 commonModule.clickText("OK");
             }
             AcceptanceTestCase.assertTrue("Google service cannot opened", commonModule.waitForText("Google services", 300*1000));
             commonModule.clickResourceId(moduleData.get("Google_Next_Button"));
             commonModule.clickText("SKIP");
             if(commonModule.isTextExists("Account sign‑in successful")){
                 commonModule.clickResourceId(moduleData.get("Google_Next_Button"));
             }
             commonModule.waitForText("Google", 10*1000);
             commonModule.clickText("Google");
             AcceptanceTestCase.assertTrue("Google account login failed", commonModule.isTextExists(account));


     }
	 
	 public void addEASAccountFromSettings(String account, String password) throws UiObjectNotFoundException{
	     testCase.launchApp("com.android.settings", "com.android.settings.Settings");
	     commonModule.waitForText("Settings", 2000);

	     commonModule.scrollFindTextNotClick("Add account");

	     if(commonModule.waitForText("Exchange ActiveSync", 3000)){
	         commonModule.clickText("Exchange ActiveSync");

	         if(commonModule.waitForText(account, 3000)){
	             return;
	         }else{
	             commonModule.pressKey(KeyEvent.KEYCODE_BACK);
	         }
	     }

	     //account didn't exist, add
	     commonModule.clickText("Add account");
	     commonModule.wait(1);
	     commonModule.clickText("Exchange ActiveSync");
	     commonModule.wait(1);

	     commonModule.clickResourceId(moduleData.get("Account_Email_EditText_Id"));
	     testCase.enterText(account);
	     commonModule.clickResourceId(moduleData.get("Account_Password_EditText_Id"));
	     testCase.enterText(password);

	     commonModule.clickResourceId(moduleData.get("Next_Button_Id"));

	     AcceptanceTestCase.assertTrue("waiting for 'Account settings' failed , maybe poor network", commonModule.waitForText("Account settings", 2*60*1000));
	     commonModule.clickResourceId(moduleData.get("Next_Button_Id"));

	     commonModule.waitForText("Account setup", 2000);

         commonModule.emptyEditorByInstance(1);
         testCase.enterText(account);

         commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
         commonModule.wait(20);

	 }

	 public void addEmailAccountFromSettings(String account, String password) throws UiObjectNotFoundException{
	     testCase.launchApp("com.android.settings", "com.android.settings.Settings");
	     commonModule.waitForText("Settings", 2000);

	     commonModule.scrollFindTextNotClick("Add account");

	     if(commonModule.waitForText("Email", 3000)){
	         commonModule.clickText("Email");

	         if(commonModule.waitForText(account, 3000)){
	             return;
	         }else{
	             commonModule.pressKey(KeyEvent.KEYCODE_BACK);
	         }
	     }

	     //account didn't exist, add
	     commonModule.clickText("Add account");
	     commonModule.wait(1);
	     commonModule.clickText("Email");
	     commonModule.wait(1);

	     commonModule.clickResourceId(moduleData.get("Account_Email_EditText_Id"));
	     testCase.enterText(account);
	     commonModule.clickResourceId(moduleData.get("Account_Password_EditText_Id"));
	     testCase.enterText(password);

	     commonModule.clickResourceId(moduleData.get("Next_Button_Id"));

	     AcceptanceTestCase.assertTrue("waiting for 'Account settings' failed , maybe poor network", commonModule.waitForText("Account settings", 2*60*1000));
	     commonModule.clickResourceId(moduleData.get("Next_Button_Id"));

	     commonModule.waitForText("Account setup", 2000);

         commonModule.emptyEditorByInstance(1);
         testCase.enterText(account);

         commonModule.clickResourceId(moduleData.get("Next_Button_Id"));
         commonModule.wait(20);

	 }

	 public void switchAccount(String switchTo) throws UiObjectNotFoundException{
	     launchEmail();

	     commonModule.clickResourceId(moduleData.get("Home_Button_Id"));

	     commonModule.clickText(switchTo);

	     AcceptanceTestCase.assertTrue("After switch to "+switchTo+",compose icon should diplay , but not",
	             commonModule.waitForResourceId(moduleData.get("Compose_Button_Id"), 5000));
	 }

	 public void openMailBySubject(String subject) throws UiObjectNotFoundException{

	     //scroll down screen to sync new mail
	     if(commonModule.waitForResourceId(moduleData.get("Search_close_button"), 3000)){
	         testCase.pressBackNTimes(1);
	     }

	     testCase.scrollUp();
         commonModule.waitForResourceId(moduleData.get("Compose_Button_Id"), 20*1000);

         testCase.pressKey(KeyEvent.KEYCODE_SEARCH);

         testCase.enterText(subject);
         testCase.enterText("\n");

         commonModule.wait(3);
         testCase.clickPoint(commonModule.getX(290,720), commonModule.getY(390, 1184));

	     AcceptanceTestCase.assertTrue("Mail with subject should be opened, but not",
	             commonModule.waitForTextContains(subject, 5*1000));
	 }

	 public void replyEmail(String type, String withContent) throws UiObjectNotFoundException{
	     commonModule.clickText(type);
	     commonModule.wait(3);

	     testCase.enterText(withContent);
	     testCase.pressKey(KeyEvent.KEYCODE_ENTER);
	     commonModule.wait(2);

	     commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
	     commonModule.wait(5);

	     commonModule.backOutToHomeScreen();
	 }

	 public void openTheEmailWithMusicAsAttachment(String account, String subject) throws UiObjectNotFoundException{
	     openMailBySubject(subject);
	     if(commonModule.waitForText("Load", 15000)){
	         commonModule.clickText("Load");
	     }

	     commonModule.waitForText("Play", 10*1000);
	     commonModule.clickText("Play");

	     if(commonModule.waitForText("Complete action using", 3000)){
	         commonModule.clickText("Walkman");
	         commonModule.wait(2);

	         commonModule.clickText("Just once");
	     }

	     AcceptanceTestCase.assertTrue("Walkman playing state should be opened",
	             commonModule.waitForPackage("com.sonyericsson.music", 10*1000));
	 }

	 public void openTheEmailWithPictureAsAttachment(String account, String subject) throws UiObjectNotFoundException{
	     //checkMailReceived(account, true);
	     openMailBySubject(subject);

         if(commonModule.waitForText("Load", 15000)){
             commonModule.clickText("Load");
         }

         commonModule.waitForText("View", 10*1000);
         commonModule.clickText("View");

         if(commonModule.waitForText("Complete action using", 3000)){
             commonModule.clickText("Album");
             commonModule.wait(2);

             commonModule.clickText("Just once");
         }
         commonModule.wait(5);
         UiObject packageObject = new UiObject(new UiSelector().packageName("com.sonyericsson.album"));
         AcceptanceTestCase.assertTrue("Album state should be opened",
                 packageObject.exists());
     }
	 
	public void fowardEmail() throws UiObjectNotFoundException{
		UiObject list = new UiObject(new UiSelector().resourceId("android:id/list"));
		// UiObject emailItem = new UiObject(new UiSelector().className(
		// "android.widget.RelativeLayout").index(1));
		UiObject emailItem = list.getChild(new UiSelector().className(
				"android.widget.RelativeLayout").index(1));
		emailItem.click();
		commonModule.waitForText("Forward", 2000);

		// forward email
		commonModule.clickText("Forward");
		commonModule.waitForResourceId("com.android.email:id/send", 2000);

		commonModule.clickResourceId("com.android.email:id/to");
		testCase.enterText("agingtest005@gmail.com");

		commonModule.clickResourceId("com.android.email:id/message_content");
		testCase.enterText("Test for Reliability.");
		commonModule.waitForTextContains("Test for Reliability.", 2000);

		commonModule.clickResourceId("com.android.email:id/send");
		AcceptanceTestCase.assertTrue(
				"Forward email failed.",
				!commonModule.waitForResourceId("com.android.email:id/send", 3000)
						&& commonModule.waitForText("Forward", 3000));
	  }
	  
	public void sendEmailToManyReceiver(int receivers) throws UiObjectNotFoundException{
	        commonModule.wait(4);
		if (commonModule.isResourceId(moduleData.get("Compose_Button_Id"))
				|| testCase.isViewWithTextPresent("No mail")) {
			commonModule.clickResourceId(moduleData.get("Compose_Button_Id"));
			commonModule.wait(2);
			commonModule.clickResourceId(moduleData.get("Email_To_EditText_Id"));
			for(int i=0;i<receivers;i++){
				
				commonModule.wait(2);
				testCase.enterText("agingtestnew00"+i+"@gmail.com");
				commonModule.wait(2);
				testCase.pressKey(KeyEvent.KEYCODE_ENTER);
				commonModule.wait(2);
			}
			
			commonModule.wait(2);
			testCase.clickItemWithId("subject");
			commonModule.wait(2);
			testCase.enterText("Reliability email test");
			commonModule.wait(2);
			testCase.pressKey(KeyEvent.KEYCODE_BACK);
			commonModule.clickResourceId(moduleData.get("Email_Content_EditText_Id"));

			commonModule.enterTextSlowly("This email is for reliability test!");
			testCase.pressKey(KeyEvent.KEYCODE_BACK);
			commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
			commonModule.wait(10);

		}
    }
	
	public void removeAccount(String account) throws UiObjectNotFoundException{
	     launchEmail();

	     commonModule.pressMoreOption();

	     commonModule.clickText("Settings");
	     commonModule.wait(2);

	     commonModule.clickText(account);
	     commonModule.wait(2);

	     commonModule.scrollFindText("Delete account");
	     commonModule.wait(2);

	     AcceptanceTestCase.assertTrue("account still exist", commonModule.waitForText("Delete account", 3000));

	     commonModule.clickText("OK");

	     AcceptanceTestCase.assertTrue("account still exist", commonModule.waitForTextGone(account, 10000));
	 }

	public void checkMailReceived(String account, boolean openMail) throws UiObjectNotFoundException{
	   
	    commonModule.openNotificationBar();

	    commonModule.waitForText(account, 10*60*1000);
	    AcceptanceTestCase.assertTrue("Mail from account:"+account+" didn't received", testCase.isViewWithTextPresent(account));

	    if(openMail){
	        testCase.clickItemWithText(account);
	    }

	}

	public void deleteLatestMailByLongPress(String subject) throws UiObjectNotFoundException{

	    commonModule.backOutToHomeScreen();

	    launchEmail();

	    int x = commonModule.getX(200,720);
	    int y = commonModule.getY(290, 1184);
	    testCase.longPressCoordinates(x, y, 3000);

	    commonModule.clickResourceId(moduleData.get("Delete_Mail_icon"));

	    commonModule.waitForText("Delete this mail?", 3000);
	    commonModule.clickText("Delete");
	    commonModule.wait(3);

	    //check latest mail is another mail
	    testCase.clickPoint(x, y);
	    AcceptanceTestCase.assertTrue("Mail should be delete and subjet shouldn't exist anymore", !commonModule.waitForText(subject, 3000));

	}

	public void deleteMailWhenOpened(String subject) throws UiObjectNotFoundException{

        int x = commonModule.getX(200,720);
        int y = commonModule.getY(290, 1184);

        commonModule.clickResourceId(moduleData.get("Delete_Mail_icon"));

        commonModule.waitForText("Delete this mail?", 3000);
        commonModule.clickText("Delete");
        commonModule.wait(3);

        //check latest mail is another mail
        testCase.clickPoint(x, y);
        AcceptanceTestCase.assertTrue("Mail should be delete and subjet shouldn't exist anymore", !commonModule.waitForText(subject, 3000));

    }

	public void removeAttachmentDuringEditMail(String sendTo, String subject) throws UiObjectNotFoundException{

	    launchEmail();
        commonModule.clickDescription("Compose");// Tap new email icon.
        commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 2000);

        // add attachment
        commonModule.clickDescription("Attach file");
        if (!commonModule.waitForText("Internal storage", 2000)) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Internal storage", 2000);

            commonModule.clickText("Internal storage");
            commonModule.waitForDescription("Search", 2000);
        } else if (commonModule.waitForText("Internal storage", 2000)
                && commonModule.waitForText("Open from", 2000)) {
            commonModule.clickText("Internal storage");
            commonModule.waitForDescription("Search", 2000);
        }

        commonModule.scrollFindText("testresource");
        commonModule.wait(1);
        commonModule.scrollFindText("pic.JPG");

        if (commonModule.isTextExists("Resize image")) {
            commonModule.clickText("Small");
            commonModule.wait(1);

            commonModule.clickText("Always");
        }

        AcceptanceTestCase.assertTrue("Delete attachment icon should display",
                commonModule.waitForResourceId(moduleData.get("Delete_Attachment_icon"), 5000));

        commonModule.clickResourceId(moduleData.get("Delete_Attachment_icon"));

        AcceptanceTestCase.assertTrue("Delete attachment icon shouldn't display anymore",
                !commonModule.waitForResourceId(moduleData.get("Delete_Attachment_icon"), 5000));
	}

    public void sendEmailWithMultipleAttachments(String to, String subject, String[] attachName) throws UiObjectNotFoundException {
        launchEmail();
        commonModule.clickDescription("Compose");// Tap new email icon.
        commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 2000);

        int i=0;
		      while(i<attachName.length){
		    	  if(attachName[i]=="Sound Recorder"){
		    	  	addAttachment("Sound Recorder", null, null);
		    	  	i++;
		    	  }else{
			    	  addAttachment("Internal storage", attachName[i],"testresource");
			    	  if(i+1<attachName.length){
			    		  		addAttachment("SD Card", attachName[i+1],"testresource");
			    	  }
			    	  i=i+2;
			    	  }
			      }

        AcceptanceTestCase.assertTrue(
                "Add attachment failed.",
                commonModule.waitForResourceId(moduleData.get("Attachment_Icon_Id"), 3000)
                        && commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 3000));

        // input recipient
        commonModule.clickResourceId(moduleData.get("Email_To_EditText_Id"));
        testCase.enterText(to);
        testCase.getUiDevice().pressEnter();
        AcceptanceTestCase.assertTrue("", commonModule.isTextContains(to));

        commonModule.clickResourceId(moduleData.get("Email_Subject_EditText_Id"));
        testCase.enterText(subject);
        testCase.assertTextPresent(subject);
        if(testCase.isInputMethodWindowOpened()){
        	testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        commonModule.clickResourceId(moduleData.get("Email_Content_EditText_Id"));
        testCase.enterText("Test for RT email.");
        commonModule.waitForTextContains("Test for RT email.", 2000);

        commonModule.clickResourceId(moduleData.get("Send_Button_Id"));
        AcceptanceTestCase.assertTrue("Send email failed.",
                commonModule.waitForDescription("Compose", 2000));
    }

	public boolean refreshEmail() throws UiObjectNotFoundException{
			AcceptanceTestCase.assertFalse("Email connection error", commonModule.waitForText("Could not open connection to server", 5000));
	        if (testCase.isViewWithIdPresent("refresh")) {
	        		testCase.clickItemWithId("refresh");
	        } else {
		        	testCase.pressKey(KeyEvent.KEYCODE_MENU);
		        	testCase.clickItemWithText("Refresh");
	        }
	        boolean loadSuccessfully = false;
	        int nbrOfAttempts = 0;
	        if(commonModule.isTextExists("No mail")){
						   if (testCase.isViewWithIdPresent("refresh")) {
					        		testCase.clickItemWithId("refresh");
					     } else {
						        	testCase.pressKey(KeyEvent.KEYCODE_MENU);
						        	testCase.clickItemWithText("Refresh");
					             }
	        	  loadSuccessfully = commonModule.waitForText("Today", 120*1000);
	        }else{
			        	while (!testCase.isViewWithIdPresent("main_text") && nbrOfAttempts < 30) {
		        			testCase.scrollDownOnView("list");
		            nbrOfAttempts++;
							        }
									 testCase.scrollDownOnView("list");
									 
									 loadSuccessfully = commonModule.waitForText("Load more mail", 120*1000);
			        }
	        
	        
	        return loadSuccessfully;
	}
	
	public void checkReceivedMailContent(String subject, int attachment) throws UiObjectNotFoundException{
				if(refreshEmail()){
								int nbrOfAttempts = 0;
			        while (!commonModule.isTextExists("Today") && nbrOfAttempts < 30) {
			            testCase.scrollUpOnView("list");
			            nbrOfAttempts++;
			        }
			        UiObject list = new UiObject(new UiSelector().resourceId("android:id/list"));
			        UiObject emailItem = list.getChild(new UiSelector().className(
			                "android.widget.RelativeLayout").index(1));
			        emailItem.click();
			        commonModule.wait(2);
			        AcceptanceTestCase.assertTrue("Receive mail failed", commonModule.isTextExists(subject));
			        if(attachment==0){
			        		AcceptanceTestCase.assertFalse("Mail content display is wrong without attachment", commonModule.isResourceId("com.android.email:id/attachment_icon"));
			        }else if(attachment==1){
			        		AcceptanceTestCase.assertTrue("Mail content display is wrong when email with one attachment", commonModule.isResourceId("com.android.email:id/attachment_icon"));
			        }else{
			        		AcceptanceTestCase.assertTrue("Mail content display is wrong when email with multiple attachments", commonModule.isTextExists("Attachments: "+attachment));
			        }
			        
				}else{
					AcceptanceTestCase.assertTrue("Load email messages failed in 2 minutes", false);
				}
}
	
	public void addAttachment(String attachmentType, String attachmentName, String fileFolder) throws UiObjectNotFoundException{
				commonModule.clickDescription("Attach file");
				if(!commonModule.isTextExists("Open from")){
						commonModule.clickResourceId("android:id/home");
				}
				
				commonModule.scrollFindText(attachmentType);
				if(attachmentType=="Sound Recorder"){
								commonModule.waitForResourceId("com.android.soundrecorder:id/recordButton", 2000);
		
			        commonModule.clickResourceId("com.android.soundrecorder:id/recordButton");
			        commonModule.wait(3);
		
			        commonModule.clickResourceId("com.android.soundrecorder:id/stopButton");
			        commonModule.waitForText("Done", 2000);
		
			        commonModule.clickText("Done");
			        AcceptanceTestCase.assertTrue("Insert sound for Email failed.",
			                commonModule.waitForText("Send", 5000));
				}else{
								commonModule.scrollFindText(fileFolder);
								commonModule.wait(2);
								commonModule.scrollFindText(attachmentName);
								if (commonModule.isTextExists("Resize image")) {
								    commonModule.clickText("Small");
								    commonModule.wait(1);
								
								    commonModule.clickText("Always");
						        }
								AcceptanceTestCase.assertTrue("Insert attachment for Email failed.",
						                commonModule.waitForText("Send", 5000));
								
							}
				
			}
	
    public void prepareEmailEditor() throws UiObjectNotFoundException {
        launchEmail();
        commonModule.clickDescription("Compose");// Tap new email icon.
        AcceptanceTestCase.assertTrue("Email editor state cannot opened", commonModule.waitForResourceId(moduleData.get("Send_Button_Id"), 3000));

    }

    public void changeDisplayName(String account, String changedName) throws UiObjectNotFoundException{
        launchEmail();

        commonModule.pressMoreOption();
        commonModule.clickText("Settings");
        commonModule.wait(2);

        AcceptanceTestCase.assertTrue(account + "didn't exist", commonModule.waitForText(account, 3000));
        commonModule.clickText(account);
        commonModule.wait(2);

        commonModule.scrollFindText("Your name");

        commonModule.emptyEditorByInstance(0);

        testCase.enterText(changedName);
        commonModule.clickText("OK");
        commonModule.wait(2);

    }
    
    public void syncEmailInSetting(String account) throws UiObjectNotFoundException{
	     testCase.launchApp("com.android.settings", "com.android.settings.Settings");
	     commonModule.waitForText("Settings", 2000);

	     commonModule.scrollFindText("Email");
	     commonModule.clickText(account);
	     commonModule.wait(2);
	     AcceptanceTestCase.assertTrue("Cannot open sync setting", commonModule.isTextExists("Sync Email"));
	     commonModule.pressKey(KeyEvent.KEYCODE_MENU);
	     commonModule.clickText("Sync now");
					AcceptanceTestCase.assertTrue(
							"Sync Email account failed!",
							commonModule.waitForTextGone("Syncing now…", 120 * 1000)
									&& commonModule
											.waitForIdGone("sync_active", 120 * 1000));
	     

    }
    
    public void syncEASInSetting(String account) throws UiObjectNotFoundException{
	     testCase.launchApp("com.android.settings", "com.android.settings.Settings");
	     commonModule.waitForText("Settings", 2000);

	     commonModule.scrollFindText("Exchange ActiveSync");
	     commonModule.clickText(account);
	     commonModule.wait(2);
	     AcceptanceTestCase.assertTrue("Cannot open sync setting", commonModule.isTextExists("Sync Email")&&commonModule.isTextExists("Sync Calendar"));
	     commonModule.pressKey(KeyEvent.KEYCODE_MENU);
	     commonModule.clickText("Sync now");
					AcceptanceTestCase.assertTrue(
							"Sync EAS account failed!",
							commonModule.waitForTextGone("Syncing now…", 120 * 1000)
									&& commonModule
											.waitForIdGone("sync_active", 120 * 1000));
	     

   }

    public void changeCheckFrequency(String account, String selection) throws UiObjectNotFoundException{
        launchEmail();

        commonModule.pressMoreOption();
        commonModule.clickText("Settings");
        commonModule.wait(2);

        AcceptanceTestCase.assertTrue(account + "didn't exist", commonModule.waitForText(account, 3000));
        commonModule.clickText(account);
        commonModule.wait(2);

        commonModule.scrollFindText("Check frequency");
        AcceptanceTestCase.assertTrue("Account check frequency"+" sould be opened", commonModule.waitForText("Account check frequency", 3000));

        commonModule.scrollFindText("Check frequency");
        commonModule.clickText(selection);

        if(commonModule.waitForText("OK", 3000)){
            commonModule.clickText("OK");
        }
        AcceptanceTestCase.assertTrue("frequency should be changed to "+selection, commonModule.waitForText(selection, 3000));

    }
}
