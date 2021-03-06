package com.module.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.ScreenCapture;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.PowerManager.WakeLock;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

public class CommonModule {

    AcceptanceTestCase testCase;

    String TAG = "Reliability";

    static WakeLock wakeLock = null;

    public String lockScreenPassword = "test";

    public CommonModule(AcceptanceTestCase testCase) {
        this.testCase = testCase;
    }

    public boolean waitForText(String text, int timeout) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!testCase.isViewWithTextPresent(text)) {
                testCase.sleep(300);
            } else {
                return true;
            }
        }
        return false;

    }

    public boolean waitForId(String id, int timeout) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!testCase.isViewWithIdPresent(id)) {
                testCase.sleep(300);
            } else {
                return true;
        }
            }

        return false;

    }

    public boolean waitForTextGone(String text, long timeout) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (testCase.isViewWithTextPresent(text)) {
                testCase.sleep(300);
            } else {
                return true;
        }
            }
        return false;
    }

    public boolean waitForIdGone(String id, long timeout) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (testCase.isViewWithIdPresent(id)) {
                testCase.sleep(300);
            } else {
                return true;
        }
            }
        return false;
    }

    public void wait(int secs) {

        testCase.sleep(secs * 1000);
    }

    public void unLockScreen() {
        if (!testCase.isScreenOn()) {
            testCase.pressKey(KeyEvent.KEYCODE_POWER);
        }

        if (testCase.isViewWithTextPresent("Swipe to unlock")
                || testCase.isViewWithTextPresent("Swipe up or down to unlock")) {
            testCase.disableLockScreen();
        } else {
            testCase.scrollDown();
        }
    }


    /**
     * this is the method for going back home scream
     *
     * @author
     * @update this is update method
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     */
    public void backOutToHomeScreen() {

        for (int i = 0; i < 5; i++) {

            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            testCase.sleep(300);
        }
        testCase.pressKey(KeyEvent.KEYCODE_HOME);
        this.wait(1);

    }

    public void clickItemByContentDescription(String contentDescription)
            throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().description(contentDescription));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }
    }

    public void pressKeyForMultipleTimes(int keyCode, int pressTime) {

        for (int i = 0; i < pressTime; i++) {

            testCase.pressKey(keyCode);
        }
    }

    public int getX(int _width) {

        int[] screamSize = testCase.getScreenSize();
        int width = screamSize[0];
        return (int)((width * _width) / 720);
    }

    public int getY(int _heigh) {
        int[] screamSize = testCase.getScreenSize();
        int heigh = screamSize[1];
        return (int)((heigh * _heigh) / 1184);
    }

    public int getX(int x, int currentWidth) {

        int[] screamSize = testCase.getScreenSize();
        int width = screamSize[0];
        return (int)((width * x) / currentWidth);
    }

    public int getY(int y, int currentHeigh) {
        int[] screamSize = testCase.getScreenSize();
        int heigh = screamSize[1];
        return (int)((heigh * y) / currentHeigh);
    }

    public void launchAppByManual(String appDisplayName) {
        testCase.pressKey(KeyEvent.KEYCODE_HOME);
        // clickItemWithId("apptray_button");
        testCase.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        int flag = 0, i = 0;

        while (flag == 0 && i < 8) {

            if (testCase.isViewWithTextPresent(appDisplayName)) {

                testCase.click(appDisplayName);
                flag = 1;
            } else {

                // scrollAppPanelRight();
                //this.scrollAppPanelRightUPDATE();

                i++;
            }
        }

        AcceptanceTestCase.assertTrue("------------------Fail to launch " + appDisplayName,
                flag == 1);
    }

    public void scrollAppPanelRightUPDATE() {

        int[] screenSize = testCase.getScreenSize();
        int x = screenSize[0];
        int y = screenSize[1];
        // Log.i(POWER_TAG, "x == " + x + " ,y== " + y);
        float[][] path0 = scrollAppPanelRightFormula(x, y);
        testCase.setTimeout(6000);
        testCase.dragGesture(path0);
        testCase.resetTimeout();

    }

    public float[][] scrollAppPanelRightFormula(int width, int height) {

        int x1 = (int)(width * (316.00 / 320));
        int x2 = (int)(width * (216.00 / 320));
        int x3 = (int)(width * (116.00 / 320));
        int x4 = (int)(width * (3.00 / 320));
        int y = (int)(height * (212.00 / 480));

        Log.i(TAG, "x1的单个值为： == " + x1);
        Log.i(TAG, "x2的单个值为： == " + x2);
        Log.i(TAG, "x3的单个值为： == " + x3);
        Log.i(TAG, "x4的单个值为： == " + x4);
        Log.i(TAG, "y的单个值为： == " + y);

        float path[][] = {
                {
                        x1, y
                }, {
                        x2, y
                }, {
                        x3, y
                }, {
                        x4, y
                }
        };

        Log.i(TAG, "x1 == " + x1 + ", x2 == " + x2 + ", x3== " + x3 + ", y== " + y);
        return path;

    }

    public void setScreenTimeOutserver(String listItem) {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        if (testCase.isViewWithTextPresent("Display")) {
            testCase.clickItemWithText("Display");
        } else {
            this.backOutToHomeScreen();
            testCase.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(4);
            testCase.clickItemWithText("Display");
        }
        this.wait(2);
        if (testCase.isViewWithTextPresent("Sleep")) {
            testCase.clickItemWithText("Sleep");
            if (testCase.isViewWithTextPresent(listItem)) {
                testCase.clickItemWithText(listItem);
            }
        } else {
            this.backOutToHomeScreen();
            testCase.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(4);
            testCase.clickItemWithText("Display");
            this.wait(2);
            testCase.clickItemWithText("Sleep");
            this.wait(2);
            testCase.clickItemWithText(listItem);
        }
    }

    public void scrollHomeLeft() {

        float path[][] = {
                {
                        1, 599
                }, {
                        100, 599
                }, {
                        200, 599
                }, {
                        300, 599
                }, {
                        400, 599
                }, {
                        550, 599
                }, {
                        719, 599
                }
        };

        testCase.setTimeout(6000);
        testCase.dragGesture(path, 30);
        testCase.resetTimeout();

    }

    public void launchAppBySearch(String appDisplayName) {

        this.backOutToHomeScreen();
        this.wait(2);
        testCase.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        this.wait(1);
        int flag = 0;
        for (int i = 0; i < 5; i++) {
            this.scrollHomeLeft();
            this.wait(2);
            if (testCase.isViewWithTextPresent("Search apps")) {
                testCase.click("Search apps");
                testCase.enterText(appDisplayName);
                this.wait(3);
                int[] menuCoor = testCase.getCoordinatesForViewWithId("apptray_search_menu_item");

                testCase.clickPoint(menuCoor[0], menuCoor[1] + 100);

                this.wait(2);
                flag = 1;
                Log.d(TAG, appDisplayName + " is launched successfully");
                break;
            } else {
                this.backOutToHomeScreen();
                this.wait(2);
                testCase.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
                this.wait(1);

            }
        }
        AcceptanceTestCase.assertTrue("------------------Fail to launch " + appDisplayName,
                flag == 1);
    }

    public void launchAppByManual2(String appDisplayName, String appDisplayName2) {

        testCase.pressKey(KeyEvent.KEYCODE_HOME);
        testCase.clickItemWithId("apptray_button");
        int flag = 0, i = 0;

        while (flag == 0 && i < 5) {

            if (testCase.isViewWithTextPresent(appDisplayName)) {
                testCase.click(appDisplayName);
                flag = 1;

            } else if (testCase.isViewWithTextPresent(appDisplayName2)) {
                testCase.click(appDisplayName2);
                flag = 1;

            } else {

                scrollAppPanelRightUPDATE();
                i++;
            }
        }

        AcceptanceTestCase.assertTrue("------------------Fail to launch " + appDisplayName,
                flag == 1);
    }

    public void clickListItemwithText(String itemname) {

        for (int i = 0; i <= 30; i++) {
            if (testCase.isViewWithTextPresent(itemname)) {
                testCase.click(itemname);
                break;
            }

            testCase.scrollDown();
            testCase.sleep(300);
        }
    }

    public long getCurrTime() {

        return System.currentTimeMillis();

    }

    public boolean scrollFindText(String text) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);

            if (isTextExists(text)) {
                testCase.click(text);
                return true;
            } else if (!isTextExists(text)) {
                testCase.scrollDown();
            }
            this.wait(1);
        }

        return false;
    }

    public boolean scrollFindTextInstanceNotClick(String text,int instance) {

        for (int i = 0; i < 100; i++) {
            this.wait(1);

            if (isTextExistsInstance(text,instance)) {
                return true;
            } else{
                testCase.scrollDown();
            }
            this.wait(1);
        }

        return false;
    }

    public void scrollFindTextContains(String text) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);

            if (isTextContains(text)) {
                break;
            } else if (!isTextContains(text)) {
                testCase.scrollDown();
            }
            this.wait(1);
        }
        this.wait(2);
        testCase.click(text);
    }

    public void scrollUpFindTextNotClick(String text) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);

            if (isTextExists(text)) {
                break;
            } else if (!isTextExists(text)) {
                testCase.scrollUp();
            }
            this.wait(1);
        }

    }


    public void scrollFindId(String id) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);

            if (isResourceId(id)) {
                break;
            } else if (!isResourceId(id)) {
                testCase.scrollDown();
            }
            this.wait(1);
        }
        testCase.clickId(id);
    }


    /**
     * scroll to find current view if or not exits "text view"
     *
     * @param text
     */
    public void scrollFindTextNotClick(String text) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);
            if (isTextExists(text)) {
                break;
            } else if (!isTextExists(text)) {
            	testCase.scrollDown();
            }
        }
    }

    public boolean scrollCheckText(String text) {

        for (int i = 0; i < 30; i++) {
            this.wait(1);
            if (isTextExists(text)) {
                return true;
            } else if (!isTextExists(text)) {
            	testCase.scrollDown();
            }

        }return false;
    }
    /**
     * scroll to find current view if or not exits "resource id view"
     *
     * @param id
     */
    public void scrollFindResourceIdNotClick(String id) {

        for (int i = 0; i < 10; i++) {
            this.wait(1);
            if (isResourceId(id)) {
                break;
            } else if (!isResourceId(id)) {
                testCase.scrollDown();
            }
        }
    }

    public void takeScreenShot(String caseName) {
        try {
            Log.v(TAG, "Take Screen begins");
            ScreenCapture screenCapture = new ScreenCapture();
            boolean isSaved = screenCapture.saveScreenshotToSdCard(testCase.getScreenshot(),
                    caseName);
            if (isSaved) {
                Log.v(TAG, "Take Screen Successed");
            } else {
                Log.v(TAG, "Take Screen Failed");
            }
            Log.v(TAG, "Take Screen ends");
        } catch (Exception e) {
            Log.v(TAG, "Fail to take screen shot");
        }
    }

    public void clickIdWithInstance(String id, int instance) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().resourceId(id).instance(instance));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void pressKey(int keyCode) {
        testCase.pressKey(keyCode);
    }

    public void disappearAlertNote() {
        if (testCase.isViewWithIdPresent("button1") && testCase.isViewWithIdPresent("alertTitle")) {
            if (testCase.isViewWithIdPresent("check_box")) {
                testCase.clickId("check_box");
            }
            testCase.clickId("button1");
        }
    }

    /**
     * this is the method for enterTextSlowly
     *
     * @param text : enter this text
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2.12.04.20
     *
     *
     */
    public void enterTextSlowly(String text) {

        int length = text.length();
        char c;
        String s;

        for (int i = 0; i < length; i++) {
            c = text.charAt(i);
            s = String.valueOf(c);
            testCase.enterText(s);
        }
    }

	public void sendSMSCommand(final String sendto, final String command) {

        SmsManager iSMSManager = null;
        iSMSManager = SmsManager.getDefault();

        iSMSManager.sendTextMessage(sendto, null, command, null, null);
        wait(3);
    }

    public void clickResourceId(String id) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().resourceId(id));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void longClickResourceId(String id) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().resourceId(id));
        Rect rect = obj.getVisibleBounds();

        testCase.longPressCoordinates(rect.centerX(), rect.centerY(), 3000);

    }

    public void activeGPSServiceFromSettings() throws UiObjectNotFoundException {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        waitForText("WIRELESS & NETWORKS", 2000);

        scrollFindText("Location");
        wait(2);
        if (testCase.isViewWithTextPresent("OFF")) {
            testCase.click("OFF");
            if (isTextContains("Your device can share")) {
                clickText("Agree");
            }
            waitForId("alertTitle", 3000);
            testCase.click("Agree");
        }
        Assert.assertTrue("Set GPS service failed.", waitForText("ON", 3000));
        backOutToHomeScreen();
    }

    public static void scanPhoto(Context ctx, String imgFileName) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(imgFileName);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        ctx.sendBroadcast(mediaScanIntent);
    }

    public void delete(String file) {
        Context ctx = testCase.getInstrumentation().getContext();
        File dirFile = new File(file);
        if (dirFile.isFile()) {
            dirFile.delete();
            return;
        }

        if (dirFile.isDirectory()) {
            File[] childFiles = dirFile.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                dirFile.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i].getAbsolutePath());
                scanPhoto(ctx, childFiles[i].getAbsolutePath());
            }
            dirFile.delete();
        }
    }

    public void clickText(String text) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().text(text));

        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void clickTextByIntance(String text, int instance) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().text(text).instance(instance));

        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void clickDescription(String desc) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().description(desc));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }
    }

    public boolean waitForResourceId(String id, int timeout) {
        long startTime = System.currentTimeMillis();

        UiObject object = new UiObject(new UiSelector().resourceId(id));

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!object.exists()) {
                testCase.sleep(300);
            } else {
                return true;
            }
        }

        return false;
    }

    public void bluetoothOnOrOff(String flag) throws UiObjectNotFoundException {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        scrollToBegin();
        if (isResourceId("com.android.settings:id/switchWidget")) {
            UiObject bt = new UiObject(new UiSelector().resourceId(
                    "com.android.settings:id/switchWidget").instance(1));
            if (flag == "ON") {
                if (!bt.isChecked()) {
                    bt.click();
                    wait(3);
                }
                Assert.assertTrue("Open Bluetooth failed.", bt.isChecked());
            } else if (flag == "OFF") {
                if (bt.isChecked()) {
                    bt.click();
                    wait(3);
                }
                Assert.assertTrue("Close Bluetooth failed.", !bt.isChecked());
            }
        } else {
            clickText("Bluetooth");
            if (flag == "ON") {
                if (isTextExists("Off")) {
                    clickResourceId("com.android.settings:id/switch_widget");
                    wait(3);
                }
                Assert.assertTrue("Open Bluetooth failed.", isTextExists("On"));
            } else if (flag == "OFF") {
                if (isTextExists("On")) {
                    clickResourceId("com.android.settings:id/switch_widget");
                    wait(3);
                }
                Assert.assertTrue("Close Bluetooth failed.", isTextExists("Off"));
            }
        }
        backOutToHomeScreen();
    }

    public boolean isResourceId(String id) {
        UiObject obj = new UiObject(new UiSelector().resourceId(id));
        if (obj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTextExistsInstance(String text,int instance) {
        UiObject obj = new UiObject(new UiSelector().text(text).instance(instance));
        if (obj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTextExists(String text) {
        UiObject obj = new UiObject(new UiSelector().text(text));
        if (obj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDescriptionExists(String desc) {
        UiObject obj = new UiObject(new UiSelector().description(desc));
        if (obj.waitForExists(5000)) {
            return true;
        } else {
            return false;
        }
    }

	  @SuppressWarnings("static-access")
	public void connect2Wifi(String apName) {
        WifiManager mWifiManager = null;
        mWifiManager = (WifiManager)testCase.getInstrumentation().getContext()
                .getSystemService(Context.WIFI_SERVICE);
        // Turn on Wifi
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
        wait(5);
        testCase.assertTrue("Turn on Wi-Fi failed!!", mWifiManager.isWifiEnabled());
        backOutToHomeScreen();

        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        wait(4);
        if (testCase.isViewWithTextPresent("Wi-Fi")) {
            testCase.click("Wi-Fi");
        } else {
            testCase.click("WLAN");
        }
        wait(4);
        if (testCase.isViewWithTextPresent(apName)) {
            Log.d(TAG, "AP finded");
        } else {
            for (int i = 0; i < 3; i++) {
                testCase.pressKey(KeyEvent.KEYCODE_MENU);
                waitForText("Scan", 4000);
                testCase.click("Scan");
                wait(10);
                waitForText(apName, 4000);
                if (testCase.isViewWithTextPresent(apName)) {
                    Log.d(TAG, "AP finded");
                    break;
                }
            }
        }
        testCase.click(apName);
        wait(2);
        if (testCase.isViewWithTextPresent("Forget")) {
            testCase.click("Forget");
            wait(2);
            testCase.click(apName);
        }
        if (testCase.isViewWithTextPresent("Connect")) {

            testCase.scrollUp();
            testCase.scrollUp();
            for (int i = 0; i < 5; i++) {
                if (testCase.isViewWithIdPresent("method_with_somc_additions")) {
                    testCase.clickId("method_with_somc_additions");
                    wait(1);
                    testCase.click("SIM");
                    break;
                } else if (testCase.isViewWithIdPresent("method_with_somc_additions_ds")) {
                    testCase.clickId("method_with_somc_additions_ds");
                    wait(1);
                    testCase.click("SIM1");
                    break;
                } else {
                    testCase.scrollUp();
                }
            }
            wait(1);
            // this.pressKey(KeyEvent.KEYCODE_BACK);
            waitForText("Connect", 1000);
            testCase.click("Connect");
            // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_RIGHT);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_CENTER);
            wait(2);
        }
        waitForText("Connected", 6000);
        Log.d(TAG, "AP connected");
        wait(3);
        backOutToHomeScreen();

    }

    public void pressMoreOption() {
        testCase.getUiDevice().pressMenu();
    }

    public boolean waitForDescription(String desc, int timeout) {
        long startTime = System.currentTimeMillis();

        UiObject object = new UiObject(new UiSelector().description(desc));

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!object.exists()) {
                testCase.sleep(300);
            } else
                return true;
        }
        return false;
    }

    public void setOrientationLandscape() {
        testCase.setOrientationLandscape();
    }

    public void setOrientationPortrait() {
        testCase.setOrientationPortrait();
    }

    /**
     * Pressing recentApp item, long pressing appName to into App info, and
     * clear data.
     * @throws UiObjectNotFoundException
     *
     * @appName App name.
     */
    public void clearData(String appName) throws UiObjectNotFoundException {
        pressKey(KeyEvent.KEYCODE_APP_SWITCH);
        wait(2);
        if (!waitForText("No recent apps", 2000)) {
            for (int i = 0; i < 5; i++) {
                if (!waitForText(appName, 2000)) {
                    testCase.scrollUp();
                }
            }
            if (waitForText(appName, 2000)) {
                testCase.longPressItemWithDescription(appName);
                waitForText("App info", 2000);
                testCase.click("App info");
                wait(5);
                testCase.click("Clear data");
                waitForId("button1", 2000);
                testCase.clickId("button1");
                wait(2);
            } else if (appName.equals("Creative effect")
                    && testCase.isViewWithDescriptionPresent("Picture effect")) {
                testCase.longPressItemWithDescription("Picture effect");
                waitForText("App info", 2000);
                testCase.click("App info");
                wait(5);
                testCase.click("Clear data");
                if (waitForId("button1", 2000)) {
                    testCase.clickId("button1");
                }
                wait(2);
            }
            pressKey(KeyEvent.KEYCODE_APP_SWITCH);
            waitForText("Close all", 2000);

            clickText("Close all");
        }
        backOutToHomeScreen();
    }

    public void clickTextContains(String text) throws UiObjectNotFoundException {

        UiObject obj = new UiObject(new UiSelector().textContains(text));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void clickDescriptionContains(String desc) throws UiObjectNotFoundException {

        UiObject obj = new UiObject(new UiSelector().descriptionContains(desc));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public boolean waitForTextContains(String text, int timeout) {
        long startTime = System.currentTimeMillis();

        UiObject object = new UiObject(new UiSelector().textContains(text));

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!object.exists()) {
                testCase.sleep(300);
                System.out.println(object);
            } else {
                System.out.println(text);
                return true;
            }
        }

        return false;
    }

    public boolean waitForDescriptionContains(String desc, int timeout) {
        long startTime = System.currentTimeMillis();

        UiObject object = new UiObject(new UiSelector().descriptionContains(desc));

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!object.exists()) {
                testCase.sleep(300);
                System.out.println(object);
            } else {
                System.out.println(desc);
                return true;
            }
        }

        return false;
    }

    public boolean isDescriptionContains(String desc) {
        UiObject obj = new UiObject(new UiSelector().descriptionContains(desc));
        if (obj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTextContains(String textContains) {
        UiObject obj = new UiObject(new UiSelector().textContains(textContains));
        if (obj.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void scrollToBegin() {
        for (int i = 0; i < 10; i++) {
            testCase.scrollUp();
        }
    }

    public void scrollToEnd() {
        for (int i = 0; i < 10; i++) {
            testCase.scrollDown();
        }
    }

    public void clickResourceIdByInstance(String id, int instance) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().resourceId(id).instance(instance));
        for (int i = 0; i < 5; i++) {
            if (obj.exists()) {
                obj.click();
                break;
            } else {
                wait(1);
            }
        }

    }

    public void emptyEditorByInstance(int instance) throws UiObjectNotFoundException {
        UiObject object = new UiObject(new UiSelector().className("android.widget.EditText")
                .instance(instance));

        Rect mRect = object.getVisibleBounds();
        for (int i = 0; i < 5; i++) {
            testCase.clickPoint(mRect.right - 10, mRect.centerY());

            for (int j = 0; j < 20; j++) {
                testCase.pressKey(KeyEvent.KEYCODE_DEL);
                if (object.getText().equals("")) {
                    break;
                }
            }
        }
    }

    public void openRecentApp() {
        testCase.pressKey(KeyEvent.KEYCODE_APP_SWITCH);
        testCase.setOrientationPortrait();
        waitForResourceId("com.sony.smallapp.launcher:id/expand_button", 2000);
    }

    public Rect getCoordinateByText(String text) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().text(text));
        Rect mRect = obj.getVisibleBounds();
        return mRect;
    }

    public Rect getCoordinateByDesc(String desc) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().description(desc));
        Rect mRect = obj.getVisibleBounds();
        return mRect;
    }

    public void clearStatusBar() {
        testCase.openStatusBar();
        testCase.clickItemWithText("Notifications");
        if (testCase.isViewWithIdPresent("clear_all_button")) {
            testCase.clickId("clear_all_button");
            wait(2);
        } else {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
    }

    public void longClickTextContains(String textContains) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().textContains(textContains));
        Rect mRect = obj.getVisibleBounds();
        testCase.longPressCoordinates(mRect.centerX(), mRect.centerY(), 3000);
    }

    public void enableLockScreenPassword() throws UiObjectNotFoundException {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        waitForText("Settings", 2000);
        clickListItemwithText("Security");
        clickText("Screen lock");
        clickText("Password");
        testCase.enterText(lockScreenPassword);
        clickText("Continue");
        testCase.enterText(lockScreenPassword);
        clickText("OK");
        backOutToHomeScreen();

    }

    public void unlockPasswordLock() throws UiObjectNotFoundException {
        KeyguardManager mKeyguardManager = (KeyguardManager)testCase.getInstrumentation()
                .getTargetContext().getSystemService(Context.KEYGUARD_SERVICE);

        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
            if (!testCase.isScreenOn()) {
                testCase.pressKey(KeyEvent.KEYCODE_POWER);
            }
            clickResourceId("com.android.keyguard:id/passwordEntry");
            testCase.enterText(lockScreenPassword);
            testCase.pressKey(KeyEvent.KEYCODE_ENTER);
            wait(2);
        }

    }

    public void disableLockScreenPassword() throws UiObjectNotFoundException {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        waitForText("Settings", 2000);
        clickListItemwithText("Security");
        clickText("Screen lock");
        if (isTextExists("Confirm your password")) {
            testCase.enterText(lockScreenPassword);
            clickText("Continue");
        }
        if (isTextExists("Choose screen lock")) {
            clickText("Swipe");
        }

    }

    public void lockScreen() {
        testCase.pressKey(KeyEvent.KEYCODE_POWER);
        wait(2);
    }

    public List<String> getSystemAllAppList() throws IOException, NameNotFoundException {
        ApplicationInfo applicationInfo = null;
        PackageManager packageManager = testCase.getInstrumentation().getContext()
                .getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> localApp = packageManager.queryIntentActivities(mainIntent, 0);

        List<String> systemAppList = new ArrayList<String>();
        for (int a = 0; a < localApp.size(); a++) {
            String appNameLong = String.valueOf(localApp.get(a));
            String[] str = appNameLong.split("/");
            String appName2 = str[0];
            String appName = appName2.substring(21);

            applicationInfo = packageManager.getApplicationInfo(appName, 0);

            String applicationName = (String)packageManager.getApplicationLabel(applicationInfo);

            systemAppList.add(applicationName);

        }
        return systemAppList;
    }

    public void lanchAllAppAndVerify() throws IOException, NameNotFoundException {
        List<String> appNames = getSystemAllAppList();
        String checkId = "action_bar_title";

        List<String> canNotVerify = new ArrayList<String>();
        List<String> timeOutError = new ArrayList<String>();
        List<String> errorAppList = new ArrayList<String>();
        for (int a = 0; a < appNames.size(); a++) {

            if (appNames.get(a).equals("PlayStation®App")
                    || appNames.get(a).equals("Super Nima Brothers")
                    || appNames.get(a).equals("Support")
                    || appNames.get(a).equals("Google Play services")
                    || appNames.get(a).equals("Google Play Movies & TV")
                    || appNames.get(a).equals("TrackID™ TV")) {

                if (appNames.get(a).equals("PlayStation®App")) {
                    launchAppBySearch("PlayStation");
                    wait(3);
                    if (!testCase.isViewWithTextPresent("User Agreement")) {
                        errorAppList.add(appNames.get(a));
                    }
                } else if (appNames.get(a).equals("TrackID™ TV")) {
                    launchAppBySearch("TV");
                    wait(3);

                    if (!waitForIdGone("setup_progressbar", 20000)) {
                        timeOutError.add(appNames.get(a));
                    }
                } else if (appNames.get(a).equals("Google Play services")) {
                    backOutToHomeScreen();
                }

                else if (appNames.get(a).equals("Support")) {
                    if (!waitForIdGone("setup_progressbar", 20000)) {
                        timeOutError.add(appNames.get(a));
                    }
                    canNotVerify.add(appNames.get(a));
                } else if (appNames.get(a).equals("Google Play Movies & TV")) {
                    launchAppBySearch("Play Movies & TV");
                    wait(3);
                }

                else {
                    launchAppBySearch(appNames.get(a));
                }
            } else if (appNames.get(a).startsWith("Google")) {

                if (appNames.get(a).equals("Google Play Music")) {
                    launchAppBySearch("Play Music");
                    checkId = "heading_text";
                }

                else if (appNames.get(a).equals("Google Play Store")) {
                    launchAppBySearch("Play Store");
                    checkId = "title";
                } else if (appNames.get(a).equals("Google Play services")) {
                    backOutToHomeScreen();
                } else if (appNames.get(a).equals("Google Search")) {
                    launchAppBySearch("Google");
                    wait(3);
                    if (testCase.isViewWithIdPresent("title")) {
                        checkId = "title";
                    } else {
                        checkId = "search_box";
                    }
                } else if (appNames.get(a).equals("Google Play Newsstand")) {
                    launchAppBySearch("Play Newsstand");
                    checkId = "message";
                } else if (appNames.get(a).equals("Google Play Games")) {
                    launchAppBySearch("Play Games");
                    checkId = "title";
                } else if (appNames.get(a).equals("Google Play Books")) {
                    launchAppBySearch("Play Books");
                    checkId = "title";
                } else if (appNames.get(a).equals("Google+")) {
                    launchAppBySearch("Google+");
                    checkId = "title";
                }

                wait(3);
                Log.d(TAG, " is launched successfully" + checkId);
                if (!testCase.isViewWithIdPresent(checkId)) {
                    errorAppList.add(appNames.get(a));
                }

            }

            else if (appNames.get(a).equals("Xperia™ Lounge") || appNames.get(a).equals("TrackID™")
                    || appNames.get(a).equals("TrackID™ TV")) {
                if (appNames.get(a).equals("Xperia™ Lounge")) {
                    checkId = "alertTitle";
                    launchAppBySearch("Xperia");
                } else if (appNames.get(a).equals("TrackID™")) {
                    checkId = "alertTitle";
                    launchAppBySearch("TrackID");
                }

                wait(3);
                Log.d(TAG, " is launched successfully" + checkId);
                if (!testCase.isViewWithIdPresent(checkId)) {
                    errorAppList.add(appNames.get(a));

                }
                if (appNames.get(a).equals("Xperia™ Lounge") || appNames.get(a).equals("TrackID™")) {

                    testCase.clickId("button2");
                }

            } else {
                launchAppBySearch(appNames.get(a));

                if (appNames.get(a).equals("Messaging")) {
                    checkId = "conversationlist_title";
                } else if (appNames.get(a).equals("Contacts")) {
                    checkId = "menu_add_contact";
                } else if (appNames.get(a).equals("Album")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Camera")) {
                    if (testCase.isViewWithTextPresent("Remember photo locations?")) {
                        testCase.click("Yes");
                    }
                    checkId = "main_button";
                } else if (appNames.get(a).equals("Email")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Walkman")) {
                    wait(1);
                    if (isTextExists("Home")) {
                        checkId = "drawer_list";
                    } else {
                        checkId = "action_bar_title";
                    }
                } else if (appNames.get(a).equals("Settings")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Browser")) {
                    checkId = "url";
                } else if (appNames.get(a).equals("Calculator")) {
                    checkId = "overflow_menu";
                } else if (appNames.get(a).equals("Chrome")) {
                    checkId = "google_icon";
                    wait(3);
                } else if (appNames.get(a).equals("Gmail")) {
                    checkId = "title";
                } else if (appNames.get(a).equals("Hangouts")) {
                    checkId = "sms_oob_accept";
                }

                else if (appNames.get(a).equals("Google+")) {
                    checkId = "title";
                } else if (appNames.get(a).equals("Calendar")) {
                    checkId = "home";
                } else if (appNames.get(a).equals("Clock")) {
                    checkId = "analog_clock";
                } else if (appNames.get(a).equals("Sketch")) {
                    checkId = "new_drawing_button";
                }

                else if (appNames.get(a).equals("YouTube")) {
                    checkId = "title";
                    wait(5);
                }

                else if (appNames.get(a).equals("Socialife")) {
                    checkId = "splash_logo";
                } else if (appNames.get(a).equals("Movies")) {
                    if (testCase.isViewWithTextPresent("Get video details")) {
                        testCase.click("Decline");
                    }
                    if (isTextExists("Home")) {
                        checkId = "drawer_list";
                    } else {
                        checkId = "action_bar_title";
                    }
                } else if (appNames.get(a).equals("Video Unlimited")) {
                    checkId = "welcome_base_content_vu_logo";
                } else if (appNames.get(a).equals("Update Center")) {
                    checkId = "action_bar_title";
                }

                else if (appNames.get(a).equals("File Commander")) {
                    checkId = "title";
                }

                else if (appNames.get(a).equals("Smart Connect")) {
                    checkId = "action_bar_title";
                }

                else if (appNames.get(a).equals("Debug Menu")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Downloads")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Drive")) {
                    checkId = "title";
                } else if (appNames.get(a).equals("News & Weather")) {
                    checkId = "action_bar_title";
                }

                else if (appNames.get(a).equals("Maps")) {
                    checkId = "terms_splash_image";
                } else if (appNames.get(a).equals("Modem log enabler")) {
                    checkId = "action_bar_title";
                }

                else if (appNames.get(a).equals("FM radio")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("Lifelog")) {
                    checkId = "start_title";
                } else if (appNames.get(a).equals("Movie Creator")) {
                    checkId = "highlight_list_welcome_title";
                } else if (appNames.get(a).equals("What's New")) {
                    checkId = "legal_info_text_view";
                } else if (appNames.get(a).equals("Sony Select")) {
                    checkId = "info_welcome_text";
                } else if (appNames.get(a).equals("Backup & restore")) {
                    checkId = "action_bar_title";
                } else if (appNames.get(a).equals("AVG AntiVirus")) {
                    checkId = "get_started_button";
                } else if (appNames.get(a).equals("OfficeSuite")) {
                    checkId = "title";
                } else if (appNames.get(a).equals("Facebook")) {
                    checkId = "login_fb_logo";
                } else if (appNames.get(a).equals("-Navigation-")) {
                    checkId = "privacy_statement";
                    wait(5);
                }

                wait(3);
                Log.d(TAG, " is launched successfully" + checkId);
                if (!testCase.isViewWithIdPresent(checkId)) {
                    errorAppList.add(appNames.get(a));

                }
                backOutToHomeScreen();
            }

        }
        if (errorAppList != null) {

            if (timeOutError != null) {
                Assert.assertTrue("Lanuch " + errorAppList + "app failed." + "----Lanuch "
                        + timeOutError + " timeout in 20 seconds." + canNotVerify
                        + "Canot check on the page, please check manually.", false);
            } else {
                Assert.assertTrue("Lanuch " + errorAppList + "app failed." + canNotVerify
                        + "Canot check on the page, please check manually.", false);
            }

        }

    }

    public void clearNotificationsBar() throws UiObjectNotFoundException {
        openNotificationBar();
        wait(2);
        if (isDescriptionExists("Clear all notifications.")) {
            clickDescription("Clear all notifications.");
        }
    }

    public void acquireWakelock() {

        if (wakeLock == null) {
            PowerManager pw = (PowerManager)testCase.getInstrumentation().getContext()
                    .getSystemService(Context.POWER_SERVICE);

            wakeLock = pw.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP, "PowerTest");
            wakeLock.acquire();

        }
    }

    public int getResourceIdCount(String id) throws UiObjectNotFoundException {

        int i = 0;
        UiCollection obj = new UiCollection(new UiSelector().resourceId(id));

        i = obj.getChildCount();

        return i;
    }

    public String getTextFromResourceId(String id) throws UiObjectNotFoundException{
        UiObject object = new UiObject(new UiSelector().resourceId(id));

        return object.getText();
    }

    public void litScreen(){
        if (!testCase.isScreenOn()) {
            testCase.pressKey(KeyEvent.KEYCODE_POWER);
        }
    }

    public void closeAllApps() throws UiObjectNotFoundException {
        pressKey(KeyEvent.KEYCODE_APP_SWITCH);
        waitForText("Close all", 2000);

        clickText("Close all");
        backOutToHomeScreen();
    }

    public void uninstallApp(String appName) throws UiObjectNotFoundException, RemoteException{
        testCase.getUiDevice().pressRecentApps();
        wait(2);
        if (!waitForText("No recent apps", 2000)) {
            for (int i = 0; i < 5; i++) {
                if (!waitForText(appName, 2000)) {
                    testCase.scrollUp();
                }
            }
            if (waitForText(appName, 2000)) {
                testCase.longPressItemWithDescription(appName);
                waitForText("App info", 2000);
                testCase.click("App info");
                wait(5);
                testCase.click("Uninstall");
                waitForText("OK", 2000);
                testCase.click("OK");
                wait(5);
            }
            pressKey(KeyEvent.KEYCODE_APP_SWITCH);
            waitForText("Close all", 2000);

            clickText("Close all");
        }
        backOutToHomeScreen();
    }

    public void openNotificationBar() throws UiObjectNotFoundException{

        testCase.openNotification();

        clickText("Notifications");
    }

    public boolean waitForPackage(String packageName, int timeout) {
        long startTime = System.currentTimeMillis();

        UiObject object = new UiObject(new UiSelector().packageName(packageName));

        while (System.currentTimeMillis() - startTime < timeout) {
            if (!object.exists()) {
                testCase.sleep(300);
            } else
                return true;
        }
        return false;
    }

    public void openQuickSettings() throws UiObjectNotFoundException {

        testCase.openStatusBar();

        clickText("Quick settings");
    }

    public void setAutoRotate(String value) throws UiObjectNotFoundException {
        openQuickSettings();
        wait(2);

        if (value.equals("on") && isDescriptionExists("Auto rotate Off.")) {
            clickDescription("Auto rotate Off.");
            AcceptanceTestCase.assertTrue("Set auto raotate on failed.",
                    waitForDescription("Auto rotate On.", 2000));
        } else if (value.equals("off") && isDescriptionExists("Auto rotate On.")) {
            clickDescription("Auto rotate On.");
            AcceptanceTestCase.assertTrue("Set auto raotate off failed.",
                    waitForDescription("Auto rotate Off.", 2000));
        }
        backOutToHomeScreen();
    }

    public void verifyAppOpened(String appName) {
        wait(2);
        if (appName == "Alarm") {
            AcceptanceTestCase.assertTrue("Alarm is opened", isDescriptionExists("Alarm"));
        } else if (appName == "Calculator") {
            AcceptanceTestCase.assertTrue("Calculator cannot be opened",
                    isResourceId("com.android.calculator2:id/clear")||isResourceId("com.android.calculator2:id/del"));

        } else if (appName == "Camera") {
            AcceptanceTestCase.assertTrue("Camera cannot be opened",
                    isResourceId("com.sonyericsson.android.camera:id/main_button")
                            || isTextExists("Yes"));

        } else if (appName == "Contacts") {
            AcceptanceTestCase.assertTrue("Contact cannot be opened",
                    isResourceId("com.sonyericsson.android.socialphonebook:id/menu_add_contact")
                            || isTextExists("Done"));

        } else if (appName == "Album") {
            AcceptanceTestCase.assertTrue("Album cannot be opened", isResourceId("android:id/up"));

        } else if (appName == "Calendar") {
            AcceptanceTestCase.assertTrue("Calendar cannot be opened",
                    isResourceId("com.android.calendar:id/action_today"));

        }
    }

    public void startAppFromRecentApp(String appName, String packageName){
        pressKey(KeyEvent.KEYCODE_APP_SWITCH);
        wait(2);
        if (!waitForText(appName, 2000)) {
            testCase.scrollUp();
        }

        testCase.clickItemWithDescription(appName);

        AcceptanceTestCase.assertTrue(appName + " didn't launched from recent app", waitForPackage(packageName, 8000));

    }

    public void fillInternalStorageFull(){

			    FileOutputStream fos = null;
			    SimpleDateFormat timeStampFormat = new SimpleDateFormat("HHmmssSSSS");
			    int FILL_BLOCK_COUNT = 50 * 1024;
			    double decimalToFill = 1.0;
			    int loopsPerSleep = 10000;
			    int loopCounter = 0;
			    Log.i(TAG, "------------------- Filling internal storage ----------------------");
			    StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
			    int blockSize = statFs.getBlockSize();
			    BigDecimal blockSizeBigDecimal = BigDecimal.valueOf(blockSize);
			    BigDecimal availableSize = BigDecimal.valueOf(statFs.getAvailableBlocks()).multiply(
			            blockSizeBigDecimal);
			    BigDecimal freeSize = BigDecimal.valueOf(statFs.getFreeBlocks()).multiply(
			            blockSizeBigDecimal);
			    BigDecimal freeStorageAfterFill = freeSize.multiply(BigDecimal
			            .valueOf(1.0 - decimalToFill));
			    Log.i(TAG, "blockSize: " + blockSize);
			    Log.i(TAG, "availableSize: " + availableSize);
			    Log.i(TAG, "freeSize: " + freeSize);
			    Log.i(TAG, "freeStorageAfterFill: " + freeStorageAfterFill);

			    byte[] buffer = new byte[blockSize];
			    (new Random()).nextBytes(buffer);
			    String fileName = timeStampFormat.format(Calendar.getInstance().getTime());
			    try {
			        int count = (freeSize.divide(
			                BigDecimal.valueOf(FILL_BLOCK_COUNT * blockSize), BigDecimal.ROUND_DOWN))
			                .intValue();
			        for (int k = 0; k < count; k++) {
			            // Fill data directory with 200M file
			            execFill(k, blockSize);
			            wait(5);
			        }
			        wait(5);
			        availableSize = BigDecimal.valueOf(statFs.getAvailableBlocks()).multiply(
			                blockSizeBigDecimal);
			        freeSize = BigDecimal.valueOf(statFs.getFreeBlocks()).multiply(blockSizeBigDecimal);
			        fos = testCase.getInstrumentation().getContext().openFileOutput(fileName, 1);
			        while (freeSize.compareTo(freeStorageAfterFill) >= 0) {
			            loopCounter++;
			            if ((loopCounter % loopsPerSleep) == 0) {
			                Log.i(TAG, "io scheduling relaxing");
			                wait(1); /*
			                                     * give io scheduling a chance to
			                                     * relax
			                                     */
			            }
			            fos.write(buffer);
			            fos.flush();

			            statFs.restat(Environment.getDataDirectory().getAbsolutePath());
			            availableSize = BigDecimal.valueOf(statFs.getAvailableBlocks()).multiply(
			                    blockSizeBigDecimal);
			            freeSize = BigDecimal.valueOf(statFs.getFreeBlocks()).multiply(
			                    blockSizeBigDecimal);
			        }
			        Log.i(TAG, "availableSize: " + availableSize);
			        Log.i(TAG, "freeSize after fill is done: " + freeSize);
			        Log.i(TAG, "freeStorageAfterFill after fill is done: " + freeStorageAfterFill);
			        Log.i(TAG, "------------------------- Done filling ----------------------------");
			        AcceptanceTestCase.assertTrue(true);
    } catch (FileNotFoundException e) {
        Log.e(TAG, "FileNotFoundException, message " + e.getMessage());
        AcceptanceTestCase.assertTrue("FileNotFoundException, message " + e.getMessage(),false);
    } catch (IOException e) {
        Log.e(TAG, "IOException, message " + e.getMessage());
        if(!e.getMessage().contains("ENOSPC")){
        	AcceptanceTestCase.assertTrue(false);
        }

    } finally {
        if (fos != null) {
            try {
            	fos.close();
            } catch (IOException e) {
                Log.e(TAG, e + " when closing Stream " + e.getMessage());
            }
        }
    }
    }

    public void dragText(String text,String toId) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().text(text));
        UiObject destObj = new UiObject(new UiSelector().resourceId(toId));
        obj.dragTo(destObj, 20);
    }

    public void dragResourceId(String id,String toId) throws UiObjectNotFoundException {
        UiObject obj = new UiObject(new UiSelector().resourceId(id));
        UiObject destObj = new UiObject(new UiSelector().resourceId(toId));
        obj.dragTo(destObj, 20);
    }

/**
 * Fill data directory with 200M file
 *
 * @param times   the number of times
 * @param blockSize    the block size
 * @throws IOException
 */
public void execFill(int times, int blockSize) throws IOException {
    int FILL_BLOCK_COUNT = 50 * 1024;
    String PACKAGE = "AutomationTest";

    File destDir = new File(Environment.getExternalStorageDirectory().getPath()+"/"+PACKAGE);
    if (!destDir.exists()) {
     destDir.mkdirs();
    }
    Runtime rt = Runtime.getRuntime();
    rt.exec("dd if=/dev/zero of=/storage/emulated/0/" + PACKAGE + "/" + times + "_" + FILL_BLOCK_COUNT
            + "Block.file bs=" + blockSize + " count=" + FILL_BLOCK_COUNT);
    Log.e(TAG, "dd if=/dev/zero of=/storage/emulated/0/" + PACKAGE + "/" + times + "_" + FILL_BLOCK_COUNT
            + "Block.file bs=" + blockSize + " count=" + FILL_BLOCK_COUNT);
    Log.e(TAG, "Filling " + FILL_BLOCK_COUNT + " block for " + (times + 1) + " times");
}

public void hidKeyBoard(){
    if(testCase.isInputMethodWindowOpened()){
    	pressKey(KeyEvent.KEYCODE_BACK);
    }
}
}


