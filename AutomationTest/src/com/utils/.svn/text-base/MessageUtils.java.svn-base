package com.utils;

import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.test.reliability.BasicUtils;

import android.util.Log;
import android.view.KeyEvent;

import java.io.IOException;

public class MessageUtils {

    private AcceptanceTestCase testCase;
    private CommonModule commonModule;


    public MessageUtils(AcceptanceTestCase testCase){

        this.testCase = testCase;
        commonModule = new CommonModule(testCase);
    }
    public void resetMailAccount() throws IOException {

        int i;
        commonModule.backOutToHomeScreen();
        commonModule.launchAppByManual("Settings");
        commonModule.wait(3);

        for (i = 0; i < 5; i++) {
            if (testCase.isViewWithTextPresent("Email")) {
                testCase.click("Email");
                commonModule.wait(3);
                break;
            }
            testCase.scrollDown();
            commonModule.wait(5);

        }
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.wait(3);
        if (testCase.isViewWithTextPresent("Remove account")) {
            testCase.click("Remove account");
        }
        commonModule.wait(3);
        if (testCase.isViewWithTextPresent("Remove account")) {
            testCase.click("Remove account");
        }
        commonModule.wait(3);
        commonModule.backOutToHomeScreen();

    }

    public void deleteMMS() throws IOException {

        int i;
        commonModule.backOutToHomeScreen();
        commonModule.launchAppByManual("Messaging");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        for (i = 0; i < 5; i++) {
            if (testCase.isViewWithTextPresent("Delete conversations")) {
                testCase.click("Delete conversations");
                commonModule.wait(3);
                break;
            }
        }
        if (testCase.isViewWithTextPresent("0 selected")) {
            testCase.click("0 selected");
            commonModule.wait(2);
        }
        if (testCase.isViewWithTextPresent("Mark all")) {
            testCase.click("Mark all");
            commonModule.wait(2);
            }
        if (testCase.isViewWithIdPresent("menu_delete")) {
            testCase.clickItemWithId("menu_delete");
            commonModule.wait(2);
            }
        if (testCase.isViewWithTextPresent("Delete")) {
            testCase.click("Delete");
            commonModule.wait(2);
            }

        commonModule.backOutToHomeScreen();

    }

    public boolean checkserver(String num) throws IOException {
        int i;
        int iMax = 10;
        boolean bResult = false;
        boolean bResultMissCall = false;
        boolean bResultSentMMS = false;
        boolean bResultSentSMS = false;
        boolean bResultSentMail = false;
        Log.e("Aging_TEST", "2");
        commonModule.wait(20);
        for (i = 0; i < iMax; i++) {

            if ((!bResultSentMMS) ) {
                Log.e("Aging_TEST", "mms");
                testCase.openStatusBar();
                    if(testCase.isViewWithTextPresent("New messages: 2")){
                        Log.e("Aging_TEST", "2");
                    }
                    if(testCase.isViewWithTextPresent("+852"+num)||testCase.isViewWithTextPresent(num)){
                        Log.e("Aging_TEST", "no");
                    }
                if (testCase.isViewWithTextPresent("New messages: 2")&&(testCase.isViewWithTextPresent("+852"+num)||testCase.isViewWithTextPresent(num))) {

                    bResultSentMMS = true;
                }

            }
            if (!bResultSentMail) {
                commonModule.backOutToHomeScreen();
                commonModule.launchAppByManual("Settings");
                testCase.scrollDown();
                testCase.scrollDown();
                testCase.scrollDown();
                if (testCase.isViewWithTextPresent("Email")) {
                    testCase.click("Email");
                }
                commonModule.wait(3);

                if (testCase.isViewWithTextPresent("Sync Email")) {
                    testCase. click("Sync Email");

                }
                commonModule.backOutToHomeScreen();
                commonModule.wait(15);
                testCase.openStatusBar();
                if (testCase.isViewWithTextPresent("email subject")) {
                    bResultSentMail = true;
                    Log.e("Aging_TEST", "get email");
                }

                if (!bResultMissCall) {
                    Log.e("Aging_TEST", "call");
                    testCase.openStatusBar();
                    if (testCase.isViewWithTextPresent("Missed call")) {
                        bResultMissCall = true;
                    }
                    testCase.pressKey(KeyEvent.KEYCODE_BACK);
                    commonModule.wait(10);
                }

            }

            if (bResultMissCall & bResultSentSMS & bResultSentMMS & bResultSentMail) {
                Log.e("Aging_TEST", "all event ok!!1");
                bResult = true;
                break;
            }
            commonModule.backOutToHomeScreen();
            commonModule.wait(20);
        }

        return bResult;

    }

    public void setCalendar() throws IOException {
        commonModule.backOutToHomeScreen();
        commonModule.launchAppByManual("Calendar");
        commonModule.wait(3);
        testCase.clickId("action_create_event");
        commonModule.wait(3);
        testCase.enterText("test");
        testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        testCase.enterText("beijing");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        testCase.enterText("Basic event test");
        commonModule.wait(3);
        testCase.click("Done");
        commonModule.backOutToHomeScreen();

    }

    public void resetCalendar() throws IOException {
        commonModule.launchAppByManual("Calendar");
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        testCase.click("Search");
        commonModule.wait(3);
        testCase.enterText("test");
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.wait(3);
        testCase.click("test");
        testCase.clickId("delete");
        testCase.click("Delete");
        commonModule.backOutToHomeScreen();

    }

    public void clearStatusbar() throws IOException {
        commonModule.backOutToHomeScreen();
        testCase.openStatusBar();
        commonModule.wait(2);
        if (testCase.isViewWithTextPresent("Clear")) {
            testCase.click("Clear");

        }

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.backOutToHomeScreen();

    }

    public void zzztest_setVPN() throws IOException {

        int i;

        commonModule.backOutToHomeScreen();

        commonModule.launchAppByManual("Settings");
        for (i = 2; i < 9; i++) {
            testCase.clickItemWithId("title", i);
            if (testCase.isViewWithTextPresent("VPN")) {
                break;
            }
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(3);
        }
        testCase.scrollDown();
        testCase.click("Mobile networks");
        commonModule.wait(3);
        testCase.click("Access Point Names");
        commonModule.wait(3);

        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.wait(3);
        testCase.click("Reset to default");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.wait(3);
        testCase.click("New APN");
        commonModule.wait(3);
        testCase.click("Name");
        commonModule.wait(3);
        testCase.enterText("GTE Beijing");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);
        testCase.click("APN");
        commonModule.wait(3);
        testCase.enterText("gte-internet");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(10);

        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.wait(3);
        testCase.click("New APN");
        commonModule.wait(3);
        testCase.click("Name");
        commonModule.wait(3);
        testCase.enterText("GTE Beijing MMS");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);
        testCase.click("APN");
        commonModule.wait(3);
        testCase.enterText("SEMC");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);

        testCase.scrollDown();

        testCase.clickItemWithText("MMSC");
        commonModule.wait(3);
        testCase.enterText("http://mms.gte.nu/servlets/mms");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);

        testCase.clickItemWithText("MMS proxy");
        commonModule.wait(3);
        testCase.enterText("mmsproxy.gte.org");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);

        testCase.clickItemWithText("MMS port");
        commonModule.wait(3);
        testCase.enterText("8080");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);

        testCase.scrollDown();

        testCase.clickItemWithText("APN type");
        commonModule.wait(3);
        testCase.enterText("mms");
        commonModule.wait(3);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
        testCase.click("OK");
        commonModule.wait(3);

        commonModule.backOutToHomeScreen();

    }

    public void setMailAccount(String test_gmailaccount, String test_gmailpassword)
            throws IOException {
        int i;
        int iMax = 20;
        commonModule.backOutToHomeScreen();
        commonModule.launchAppByManual("Email");

        if (testCase.isViewWithIdPresent("account_email")) {
            Log.e("Aging_TEST", "a");
            testCase.enterText(test_gmailaccount);
            testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
            commonModule.wait(3);
            testCase.enterText(test_gmailpassword);
            commonModule.wait(3);
            testCase.click("Next");
            commonModule.wait(10);
            for (i = 0; i < iMax; i++) {
                if (testCase.isViewWithTextPresent("Next")) {
                    if (testCase.isViewWithIdPresent("account_check_frequency")) {
                        testCase.clickId("account_check_frequency");
                        commonModule.wait(3);
                        testCase.click("Every 5 minutes");
                        commonModule.wait(3);
                        testCase.click("OK");
                        commonModule.wait(1);

                    }

                    testCase.click("Next");
                    commonModule.wait(5);
                    if (testCase.isViewWithTextPresent("Note") || testCase.isViewWithTextPresent("No")) {
                        testCase.click("No");
                        commonModule.wait(1);
                    }
                    break;
                }
                commonModule.wait(10);
            }
            commonModule.wait(3);
            testCase.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
            testCase.enterText("test");
            commonModule.wait(3);
            testCase.click("Next");

            for (i = 0; i < iMax; i++) {
                testCase.pressKey(KeyEvent.KEYCODE_MENU);
                if (testCase.isViewWithTextPresent("Sort")) {
                    testCase.pressKey(KeyEvent.KEYCODE_BACK);
                    break;
                }
                commonModule.wait(10);
            }
        }
        commonModule.backOutToHomeScreen();

    }



}
