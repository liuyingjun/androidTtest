package com.module.gmail;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.view.KeyEvent;

import java.util.HashMap;

public class GmailModule implements IGmail{

    public AcceptanceTestCase testCase;
    public CommonModule commonModule;
    public HashMap<String, String> moduleData;

    public GmailModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("gmail");
    }

    public void launchGmail(){
        testCase.launchApp("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail");
        commonModule.wait(3);
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

    public void sendMailTo(String account, String subject, String content) throws UiObjectNotFoundException{
        launchGmail();

        commonModule.clickResourceId(moduleData.get("Create_mail_icon"));

        commonModule.clickResourceId(moduleData.get("Editor_to_id"));
        testCase.enterText(account);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickResourceId(moduleData.get("Editor_subject_id"));
        testCase.enterText(subject);

        commonModule.clickResourceId(moduleData.get("Editor_mail_content_id"));
        testCase.enterText(content);

        commonModule.wait(3);
        commonModule.clickResourceId(moduleData.get("Send_mail_icon"));
        commonModule.wait(5);
    }

    public void checkMailReceived(String account, boolean openMail) throws UiObjectNotFoundException{
        commonModule.openNotificationBar();

        commonModule.waitForText(account, 5*60*1000);
        AcceptanceTestCase.assertTrue("Mail from account:"+account+"didn't received", testCase.isViewWithTextPresent(account));

        if(openMail){
            testCase.clickItemWithText(account);
        }
    }

}
