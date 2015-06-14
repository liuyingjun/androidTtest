
package com.module.settings;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.app.AlarmManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.VpnService.Builder;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import junit.framework.Assert;

public class SettingsModule implements ISetting {

    AcceptanceTestCase testCase;

    CommonModule commonModule;
    String TAG="RT";
    private HashMap< String, String> moduleData = new HashMap<String, String>();
    private HashMap< String, String> month = new HashMap<String, String>();

    public static String vpnName="Test";

    public SettingsModule(AcceptanceTestCase testCase) {
        this.testCase = testCase;
        commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("settings");

    }

    public void launchSettings() {
        testCase.launchApp("com.android.settings", "com.android.settings.Settings");
        commonModule.waitForText("Settings", 2000);
    }

    public void setDataTraffic(String value) throws UiObjectNotFoundException {
        launchSettings();

        testCase.clickItemWithText("Data usage");
        testCase.assertTextPresent("Mobile data traffic");

        UiObject obj = new UiObject(new UiSelector().className("android.widget.Switch"));

        if (obj.getText().toLowerCase().equals(value.toLowerCase())) {
            return;
        } else {
            obj.click();

            commonModule.wait(3);

            commonModule.clickResourceId("android:id/button1");
        }
    }

    public void longTapPowerKey() {
        testCase.longPressKey(KeyEvent.KEYCODE_POWER, 3000);
        commonModule.waitForText("Power off", 2000);
    }

    public void turnOnFlightMode() throws UiObjectNotFoundException {
        // longTapPowerKey();
        launchSettings();
        commonModule.clickText("More…");
        commonModule.wait(1);
        if (!testCase.isCheckboxChecked("checkbox", 0)) {
            commonModule.clickText("Airplane mode");
        }
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

        launchSettings();
        UiObject wifi = new UiObject(new UiSelector().resourceId(
                "com.android.settings:id/switchWidget").instance(0));
        Assert.assertTrue("Wifi is not disconnect.", !wifi.isChecked());

        UiObject bluetooth = new UiObject(new UiSelector().resourceId(
                "com.android.settings:id/switchWidget").instance(1));
        Assert.assertTrue("Bluetooth is not disconnect.", !bluetooth.isChecked());

        commonModule.backOutToHomeScreen();
    }

    public void switchSoundSettings() throws UiObjectNotFoundException {
        longTapPowerKey();
        commonModule.clickDescription("Ringer off");
        commonModule.wait(2);
        commonModule.backOutToHomeScreen();

        longTapPowerKey();
        UiObject off = new UiObject(new UiSelector().description("Ringer off"));
        Assert.assertTrue("Ringer off is not disconnect.", off.isSelected());

        commonModule.clickDescription("Ringer vibrate");
        commonModule.wait(2);
        commonModule.backOutToHomeScreen();

        longTapPowerKey();
        UiObject vibrate = new UiObject(new UiSelector().description("Ringer vibrate"));
        Assert.assertTrue("Ringer vibrate is not disconnect.", vibrate.isSelected());

        commonModule.clickDescription("Ringer on");
        commonModule.wait(2);
        commonModule.backOutToHomeScreen();

        longTapPowerKey();
        UiObject on = new UiObject(new UiSelector().description("Ringer on"));
        Assert.assertTrue("Ringer on is not disconnect.", on.isSelected());

        commonModule.backOutToHomeScreen();
    }

    public void changeSoundMode(String value) throws UiObjectNotFoundException {
        longTapPowerKey();
        commonModule.waitForDescription(value, 2000);

        commonModule.clickDescription(value);
        commonModule.wait(2);

        testCase.pressBackNTimes(1);
    }

    public void takeScreenshotViaPowerMenu() throws UiObjectNotFoundException {
        longTapPowerKey();
        commonModule.clickText("Take screenshot");
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

        commonModule.wait(2);
        testCase.openStatusBar();
        commonModule.clickText("Notifications");

        Assert.assertTrue("Take screenshot failed.",
                commonModule.waitForText("Screenshot captured.", 3000));

        commonModule.clickText("Screenshot captured.");
        commonModule.wait(2);

        if (commonModule.isTextExists("Album")
                && commonModule.isTextExists("Complete action using")) {
            commonModule.clickText("Album");
            if(commonModule.isTextExists("Just once")){
                commonModule.clickText("Just once");
            }
        }
        int[] screen = testCase.getScreenSize();
        testCase.clickPoint(screen[0] / 2, screen[1] / 2);
        Assert.assertTrue("Screenshot display failed on Album.",
                commonModule.waitForResourceId("com.sonyericsson.album:id/action_layer", 3000)
                        || commonModule.waitForResourceId("android:id/home", 3000));

        commonModule.backOutToHomeScreen();
    }

    public void wifiOnOrOff(String flag) throws UiObjectNotFoundException {
//        launchSettings();
//        UiObject wifi = new UiObject(new UiSelector().resourceId(
//                "com.android.settings:id/switchWidget").instance(0));
//        if (flag == "ON") {
//            if (!wifi.isChecked()) {
//                wifi.click();
//                commonModule.wait(3);
//            }
//            Assert.assertTrue("Open wifi failed.", wifi.isChecked());
////            commonModule.clickText("Wi-Fi");
////            Assert.assertTrue("Connected wifi failed.",
////                    commonModule.waitForText("Connected", 10 * 1000));
//        } else if (flag == "OFF") {
//            if (wifi.isChecked()) {
//                wifi.click();
//                commonModule.wait(3);
//            }
//            Assert.assertTrue("Close wifi failed.", !wifi.isChecked());
//        }
    	
					WifiManager mWifiManager = null;
			
					mWifiManager = (WifiManager) testCase.getInstrumentation()
							.getContext().getSystemService(Context.WIFI_SERVICE);
					// Turn on Wifi
					if (!mWifiManager.isWifiEnabled()&&flag == "ON") {
						mWifiManager.setWifiEnabled(true);
					}else if(mWifiManager.isWifiEnabled()&&flag == "OFF"){
						mWifiManager.setWifiEnabled(false);
					}
					commonModule.wait(3);
    }

    public void turnOffFlightMode() throws UiObjectNotFoundException {
        // longTapPowerKey();
        launchSettings();
        commonModule.clickText("More…");
        commonModule.wait(1);
        if (testCase.isCheckboxChecked("checkbox", 0)) {
            commonModule.clickText("Airplane mode");
        }
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();
    }

    public void changeCallipersRange() throws UiObjectNotFoundException {
        UiObject calliper = new UiObject(
                new UiSelector().resourceId("com.android.settings:id/sweep_right"));
        UiObject series = new UiObject(
                new UiSelector().resourceId("com.android.settings:id/series"));
        Rect cRect = calliper.getVisibleBounds();
        Rect sRect = series.getVisibleBounds();

        testCase.dragViewBetweenTwoPosition(cRect.centerX(), sRect.bottom, cRect.centerX() + 50,
                sRect.bottom, 30);
    }

    public void connectGTEWifi() throws UiObjectNotFoundException {
        wifiOnOrOff("ON");
        launchSettings();

        commonModule.clickText("Wi-Fi");
        commonModule.waitForResourceId("android:id/widget_frame", 5 * 1000);

        commonModule.scrollFindText("GTE");
        if (!commonModule.waitForText("Connected", 2000)
                && commonModule.waitForText("Forget", 2000)) {
            commonModule.clickText("Forget");
            commonModule.wait(2);

            commonModule.scrollFindText("GTE");
        }else if (commonModule.waitForText("Connected", 2000)) {
            commonModule.clickText("Cancel");
            return;
        }
        commonModule.waitForText("EAP method", 2000);

        commonModule.clickText("PEAP");
        commonModule.waitForText("SIM", 2000);

        commonModule.clickText("SIM");
        commonModule.waitForText("Connect", 2000);
        commonModule.wait(2);

        commonModule.clickText("Connect");
        for (int i = 0; i < 20; i++) {
            if (commonModule.isTextContains("Connecting")) {
                commonModule.wait(3);
            } else if (commonModule.isTextContains("Obtaining IP address")) {
                commonModule.wait(3);
            } else if (i == 19 && commonModule.isTextContains("Connecting")) {
                testCase.failTest("Poor network!");
            } else if (commonModule.isTextExists("Authentication problem")) {
                testCase.failTest("Authentication problem.");
            } else if (commonModule.isTextExists("Connected")) {
                break;
            }
        }
        AcceptanceTestCase.assertTrue("Connect GTE wifi failed.",
                commonModule.waitForText("Connected", 20 * 1000));
    }

    public void setSimPinLock(String value) throws UiObjectNotFoundException {
        launchSettings();

        commonModule.scrollFindText("Security");
        commonModule.waitForText("Set up SIM card lock", 2000);

        commonModule.clickText("Set up SIM card lock");
        commonModule.waitForText("Lock SIM card", 2000);

        UiObject checkbox = new UiObject(new UiSelector().resourceId("android:id/checkbox"));
        if (value.equals("on")) {
            if (checkbox.isChecked()) {
                return;
            } else {
                checkbox.click();
                commonModule.waitForText("SIM PIN", 2000);

                testCase.enterText("1234");
                commonModule.clickText("OK");
                commonModule.waitForText("Lock SIM card", 2000);
                AcceptanceTestCase.assertTrue("Active SIM PIN lock failed.", checkbox.isChecked());
            }
        } else if (value.equals("off")) {
            if (!checkbox.isChecked()) {
                return;
            } else {
                checkbox.click();
                commonModule.waitForText("SIM PIN", 2000);

                testCase.enterText("1234");
                commonModule.clickText("OK");
                commonModule.waitForText("Lock SIM card", 2000);
                AcceptanceTestCase.assertTrue("Block SIM PIN lock failed.", checkbox.isChecked());
            }
        }
    }

    public void changeSimPin() throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Change SIM PIN")) {
            launchSettings();

            commonModule.scrollFindText("Security");
            commonModule.waitForText("Set up SIM card lock", 2000);

            commonModule.clickText("Set up SIM card lock");
            commonModule.waitForText("Change SIM PIN", 2000);
        }

        commonModule.clickText("Change SIM PIN");
        commonModule.waitForText("Old SIM PIN", 2000);

        testCase.enterText("1234");
        commonModule.clickText("OK");
        commonModule.waitForText("New SIM PIN", 2000);

        testCase.enterText("1234");
        commonModule.clickText("OK");
        commonModule.waitForText("Re-type new PIN", 2000);

        testCase.enterText("1234");
        commonModule.clickText("OK");

        AcceptanceTestCase.assertTrue("SIM PIN changed sucessfully.",
                commonModule.waitForText("Change SIM PIN", 2000));
    }

    public void checkChangeSimPin() throws UiObjectNotFoundException {
        launchSettings();

        commonModule.scrollFindText("Security");
        commonModule.waitForText("Set up SIM card lock", 2000);

        commonModule.clickText("Set up SIM card lock");
        commonModule.waitForText("Change SIM PIN", 2000);

        commonModule.clickText("Change SIM PIN");
        commonModule.waitForText("Old SIM PIN", 2000);

        // Enter short wrong SIM PIN(less than 4 digits)
        testCase.enterText("123");
        commonModule.clickText("OK");
        commonModule.waitForTextContains("Type a PIN that is 4 to 8 numbers", 2000);

        // Enter long wrong SIM PIN(more than 8 digits)
        commonModule.emptyEditorByInstance(0);
        testCase.enterText("123456789");
        commonModule.clickText("OK");
        commonModule.waitForTextContains("Type a PIN that is 4 to 8 numbers", 2000);

        // Enter a wrong SIM PIN(between 4 to 8)
        commonModule.emptyEditorByInstance(0);
        testCase.enterText("5678");
        commonModule.clickText("OK");
        commonModule.waitForText("New SIM PIN", 2000);

        testCase.enterText("1234");// Enter new SIM PIN.
        commonModule.clickText("OK");
        commonModule.waitForText("Re-type new PIN", 2000);

        testCase.enterText("1111");// RE-type the new PIN with wrong one.
        commonModule.clickText("OK");
        commonModule.waitForTextContains("PINs don't match", 2000);

        testCase.enterText("1234");
        commonModule.clickText("OK");
        commonModule.waitForText("Re-type new PIN", 2000);

        testCase.enterText("1234");
        commonModule.clickText("OK");
        AcceptanceTestCase.assertTrue(
                "Check change SIM PIN failed.",
                commonModule.waitForTextContains("Can't change PIN", 3000)
                        && commonModule.waitForText("Change SIM PIN", 2000));
    }

    public void setStaminaMode(String mode) throws UiObjectNotFoundException{
        launchSettings();

        commonModule.scrollFindText("Power management");

        UiObject staminaSwitch= new UiObject(new UiSelector().resourceId(moduleData.get("Stamina_Switch_Button")).instance(0));
        if(mode.toUpperCase().equals("ON")){
            if(staminaSwitch.getText().equals("OFF")){
                staminaSwitch.click();
                if(commonModule.waitForText("Activate", 2000)){
                    commonModule.clickText("Activate");
                }
            }else{
                return;
            }
        }else if(mode.toUpperCase().equals("OFF")){
            if(staminaSwitch.getText().equals("ON")){
                staminaSwitch.click();
            }else{
                return;
            }
        }
    }

    public void openVPNSetting() throws UiObjectNotFoundException {
        launchSettings();
        commonModule.clickText("More…");
        commonModule.clickText("VPN");
        if (commonModule.isTextExists("Attention")) {
            commonModule.clickText("OK");
            commonModule.clickText("Password");
            testCase.enterText(commonModule.lockScreenPassword);
            commonModule.clickText("Continue");
            testCase.enterText(commonModule.lockScreenPassword);
            commonModule.clickText("OK");

        }
    }

    public String connectVPN(String userID, String password) throws UiObjectNotFoundException {
        commonModule.wait(1);
        for (int i = 0; i < 3; i++) {
            commonModule.clickText(vpnName);
            if (!commonModule.isTextExists(vpnName)) {
                break;
            } else {
                commonModule.wait(2);
            }
        }

        commonModule.wait(1);
        if (commonModule.isTextExists("VPN is connected")) {
            commonModule.clickText("Disconnect");
            commonModule.clickText(vpnName);
        }
        if (userID != null && password != null) {
            testCase.enterText(userID);
            testCase.pressKey(KeyEvent.KEYCODE_ENTER);
            testCase.enterText(password);
            commonModule.clickText("Connect");
            if (commonModule.waitForTextGone("Connecting…", 300 * 1000)) {
                if (commonModule.isTextExists("Connected")) {
                    return "Connected";
                } else if (commonModule.isTextExists("Disconnected")
                        || commonModule.isTextExists("Unsuccessful")) {
                    return "Disconnected";
                }
            } else {
                AcceptanceTestCase.assertTrue("VPN cannot connect in time", false);
            }
        } else {
            UiObject obj = new UiObject(new UiSelector().text("Connect"));
            if (!obj.isEnabled()) {
                return "EmptyAccount";
            }
        }
        return "Connect failed";

    }

    public void createPptpVPN() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.settings:id/vpn_create");
        testCase.enterText(vpnName);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickResourceId("com.android.settings:id/type");
        commonModule.clickText("PPTP");
        commonModule.clickResourceId("com.android.settings:id/server");
        testCase.enterText("124.215.201.21");
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        UiObject obj = new UiObject(new UiSelector().resourceId("com.android.settings:id/mppe"));
        if (obj.isChecked()) {
            commonModule.clickResourceId("com.android.settings:id/mppe");
        }
        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue("VPN connect failed", commonModule.isTextExists("PPTP VPN"));
    }

    public void createL2tpPskVPN() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.settings:id/vpn_create");
        testCase.enterText(vpnName);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickResourceId("com.android.settings:id/type");
        commonModule.clickText("L2TP/IPSec PSK");
        commonModule.clickResourceId("com.android.settings:id/server");
        testCase.enterText("124.215.201.21");
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        if (testCase.isInputMethodWindowOpened()) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        commonModule.clickResourceId("com.android.settings:id/ipsec_secret");
        testCase.enterText("pre");
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue("VPN connect failed",
                commonModule.isTextExists("L2TP/IPSec VPN with pre-shared keys"));
    }

    public void createL2tpRsaVPN() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.settings:id/vpn_create");
        testCase.enterText(vpnName);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickResourceId("com.android.settings:id/type");
        commonModule.clickText("L2TP/IPSec RSA");
        commonModule.clickResourceId("com.android.settings:id/server");
        testCase.enterText("124.215.201.21");
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        if (testCase.isInputMethodWindowOpened()) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        commonModule.clickResourceId("com.android.settings:id/ipsec_user_cert");
        commonModule.clickResourceIdByInstance("android:id/text1", 1);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickResourceId("com.android.settings:id/ipsec_ca_cert");
        commonModule.clickResourceIdByInstance("android:id/text1", 1);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue("VPN connect failed",
                commonModule.isTextExists("L2TP/IPSec VPN with certificates"));
    }

    public void deleteVPN() throws UiObjectNotFoundException {
        if (commonModule.isTextExists(vpnName)) {
            commonModule.longClickTextContains(vpnName);
            commonModule.clickText("Delete profile");
        }
    }

    public void disconnectVPN() throws UiObjectNotFoundException {
        if (commonModule.isTextExists(vpnName) && commonModule.isTextExists("Connected")) {
            commonModule.wait(1);
            for (int i = 0; i < 3; i++) {
                commonModule.clickText(vpnName);
                if (!commonModule.isTextExists(vpnName)) {
                    break;
                } else {
                    commonModule.wait(2);
                }
            }

            commonModule.wait(1);
            commonModule.clickText("Disconnect");
            AcceptanceTestCase.assertTrue("VPN cannot disconnect",
                    commonModule.waitForTextGone("Connected", 2000));
        }
    }

    public void verifyVPN() throws UiObjectNotFoundException {
        commonModule.wait(1);
        for (int i = 0; i < 3; i++) {
            commonModule.clickText(vpnName);
            if (!commonModule.isTextExists(vpnName)) {
                break;
            } else {
                commonModule.wait(2);
            }
        }

        commonModule.wait(1);
        boolean session = commonModule.isResourceId("com.android.vpndialogs:id/session")
                && commonModule.isTextExists("Session:");
        boolean duration = commonModule.isResourceId("com.android.vpndialogs:id/duration")
                && commonModule.isTextExists("Duration:");
        boolean sent = commonModule.isResourceId("com.android.vpndialogs:id/data_transmitted")
                && commonModule.isTextExists("Sent:");
        boolean received = commonModule.isResourceId("com.android.vpndialogs:id/data_received")
                && commonModule.isTextExists("Received:");
        boolean vpnConnected = commonModule.isTextExists("VPN is connected");

        AcceptanceTestCase.assertTrue(session);
        AcceptanceTestCase.assertTrue(duration);
        AcceptanceTestCase.assertTrue(received);
        AcceptanceTestCase.assertTrue(sent);
        AcceptanceTestCase.assertTrue(vpnConnected);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }

    public void connectVPNImpropriety(String vpnUsername, String vpnPassword)
            throws UiObjectNotFoundException {
        String connectResult;
        connectResult = connectVPN("abc", "def");
        AcceptanceTestCase.assertTrue("VPN should not connected", connectResult == "Disconnected");
        connectResult = connectVPN(null, null);
        AcceptanceTestCase.assertTrue("VPN should not connected", connectResult == "EmptyAccount");
        if (testCase.isInputMethodWindowOpened()) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        if (!commonModule.isTextExists("VPN")) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        connectResult = connectVPN("abc", vpnPassword);
        AcceptanceTestCase.assertTrue("VPN should not connected", connectResult == "Disconnected");
        connectResult = connectVPN(vpnUsername, "incorrect");
        AcceptanceTestCase.assertTrue("VPN should not connected", connectResult == "Disconnected");
        connectResult = connectVPN(vpnUsername, vpnPassword);
        AcceptanceTestCase.assertTrue("VPN should connected", connectResult == "Connected");
    }

    public void browerInternet(String url, String checkPoint) throws UiObjectNotFoundException {
        commonModule.launchAppBySearch("Chrome");
        if (commonModule.waitForText("Accept and continue", 5000)) {
            commonModule.clickText("Accept and continue");
            commonModule.clickText("No thanks");
        }

        commonModule.waitForResourceId("com.android.chrome:id/url_bar", 30000);
        if (commonModule.isResourceId("com.android.chrome:id/url_bar")) {
            commonModule.clickResourceId("com.android.chrome:id/url_bar");
            testCase.enterText(url);
            commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
            AcceptanceTestCase.assertTrue("Page cannot load in time",
                    commonModule.waitForDescription("Refresh page", 300 * 1000));
            testCase.pressKey(KeyEvent.KEYCODE_MENU);
            commonModule.scrollFindText("Find in page");
            testCase.enterText(checkPoint);
            AcceptanceTestCase.assertTrue("Cannot open webpage under the network",
                    commonModule.isTextExists("1/1"));
        }
    }

    public void installCA() throws UiObjectNotFoundException{
        launchSettings();
        commonModule.wait(2);
        commonModule.scrollFindText("Security");
        commonModule.clickListItemwithText("Install from device memory/SD card");
        if(testCase.isViewWithTextPresent("Open from")){
            testCase.click("Internal storage");
            commonModule.scrollFindText("testresource");
        }

        commonModule.scrollFindText("xperia.p12");
        commonModule.clickResourceId("com.android.certinstaller:id/credential_password");
        testCase.enterText("password");
        commonModule.clickText("OK");
        commonModule.clickText("OK");
        if(commonModule.isTextExists("Attention")){
	        	commonModule.clickText("OK");
	        	commonModule.clickText("Password");
	        	testCase.enterText(commonModule.lockScreenPassword);
	        	commonModule.clickText("Continue");
	     	   testCase.enterText(commonModule.lockScreenPassword);
	     	   commonModule.clickText("OK");

        }else if(commonModule.isTextExists("Confirm your password")){
	        	commonModule.clickResourceId("com.android.settings:id/password_entry");
	        	testCase.enterText(commonModule.lockScreenPassword);
	        	commonModule.clickText("Continue");
        }
    }

    public void removeCA() throws UiObjectNotFoundException {
        launchSettings();
        commonModule.wait(2);
        commonModule.scrollFindText("Security");
        commonModule.scrollFindText("Clear credentials");
        if(commonModule.isTextExists("OK")){
        commonModule.clickText("OK");
        }
    }

    public void checkDateTimeSetting() throws UiObjectNotFoundException {
        launchSettings();

        commonModule.scrollFindText("Date & time");

        UiObject autoDateTime = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(0));
        UiObject autoTimeZone = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(1));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));
        UiObject setTime = new UiObject(new UiSelector().text("Set time"));
        UiObject setTimeZone = new UiObject(new UiSelector().text("Select time zone"));
        if (autoDateTime.isChecked()) {
            AcceptanceTestCase.assertFalse(
                    "Date seting is enable when automatic date & time is checked",
                    setDate.isEnabled());
            AcceptanceTestCase.assertFalse(
                    "Time seting is enable when automatic date & time is checked",
                    setTime.isEnabled());
        } else {
            AcceptanceTestCase.assertTrue(
                    "Date seting is disable when automatic date & time is not checked",
                    setDate.isEnabled());
            AcceptanceTestCase.assertTrue(
                    "Time seting is disable when automatic date & time is not checked",
                    setTime.isEnabled());
        }
        if (autoTimeZone.isChecked()) {
            AcceptanceTestCase.assertFalse(
                    "Date seting is enable when automatic date & time is checked",
                    setTimeZone.isEnabled());
        } else {
            AcceptanceTestCase.assertTrue(
                    "Date seting is disable when automatic date & time is not checked",
                    setTimeZone.isEnabled());
        }
    }

    public void setDateManual(long date) throws UiObjectNotFoundException {
        addMonthData();

        UiObject autoDateTime = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(0));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));
        if (autoDateTime.isChecked()) {
            autoDateTime.click();
        }
        String monthKey = formatTimeStamp(date, "MM");
        String year = formatTimeStamp(date, "yyyy");
        String day = formatTimeStamp(date, "dd");
        setDate.click();
        commonModule.clickIdWithInstance("android:id/numberpicker_input", 0);
        testCase.enterText(month.get(formatTimeStamp(date, "MM")));
        commonModule.clickIdWithInstance("android:id/numberpicker_input", 1);
        testCase.enterText(day);
        commonModule.clickIdWithInstance("android:id/numberpicker_input", 2);
        testCase.enterText(year);
        commonModule.clickText("Set");
        AcceptanceTestCase.assertTrue("Date & Time is set failed",
                commonModule.waitForText(day + "/" + monthKey + "/" + year, 8000));

    }

    //
    // public void setTimeManual() throws UiObjectNotFoundException{
    // UiObject autoDateTime= new UiObject(new
    // UiSelector().resourceId("android:id/checkbox").instance(1));
    // UiObject setTime= new UiObject(new UiSelector().text("Set time"));
    // if(autoDateTime.isChecked()){
    // autoDateTime.click();
    // }
    // setTime.click();
    // commonModule.clickIdWithInstance("android:id/numberpicker_input", 0);
    // testCase.enterText("12");
    // commonModule.clickIdWithInstance("android:id/numberpicker_input", 1);
    // testCase.enterText("00");
    // commonModule.clickIdWithInstance("android:id/numberpicker_input", 2);
    // testCase.enterText("AM");
    // commonModule.clickText("Set");
    // AcceptanceTestCase.assertTrue("Date & Time set failed",
    // commonModule.isTextExists("12:00 AM"));
    //
    //
    // }

    public void setTimeZoneManual() throws UiObjectNotFoundException, ParseException {
        AlarmManager mAlarmManager = (AlarmManager)testCase.getInstrumentation().getContext()
                .getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.setTimeZone("GMT+00:00");
        commonModule.wait(3);
        UiObject autoTimeZone = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(1));
        UiObject setTimeZone = new UiObject(new UiSelector().text("Select time zone"));
        if (autoTimeZone.isChecked()) {
            autoTimeZone.click();
        }
        String startDate = getDisplayDate();
        Log.i(TAG, "Start time is: " + printDisplayTime(startDate));
        setTimeZone.click();
        commonModule.scrollFindTextNotClick("Beijing");
        commonModule.clickText("Beijing");
        // commonModule.clickText("Set");
        String endDate = getDisplayDate();

        if (startDate.equals(endDate)) {
            AcceptanceTestCase.assertTrue(false);
        }
        AcceptanceTestCase.assertTrue("Date & Time set failed",
                commonModule.isTextExists("GMT+08:00, China Standard Time"));

    }

    public void setDateFormat() throws UiObjectNotFoundException {
        Date d = new Date();;

        String date = new SimpleDateFormat("dd/MM/yyyy").format(d);

        commonModule.wait(5);
        commonModule.clickText("Select date format");
        AcceptanceTestCase.assertTrue(
                "Date format list cannot display",
                commonModule.isTextExists("Select date format")
                        && commonModule.isTextExists("Cancel"));
        commonModule.clickResourceIdByInstance("android:id/text1", 2);
        AcceptanceTestCase.assertTrue("Date format not changed as selection in setting",
                commonModule.isTextContains(date));
    }

    public static String formatTimeStamp(long milliSeconds, String formatPattern) {
        SimpleDateFormat timeFormat;
        if (formatPattern == null) {
            timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            timeFormat = new SimpleDateFormat(formatPattern);
        }

        String timeStamp = timeFormat.format(new Date(milliSeconds));
        return timeStamp;
    }

    public void change24HourFormat() throws UiObjectNotFoundException {
        UiObject hourFormat = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(2));
        hourFormat.click();
        if (hourFormat.isChecked()) {
            AcceptanceTestCase.assertTrue("AMPM should not display",
                    commonModule.isTextExists("13:00"));
            commonModule.wait(5);
            testCase.pressKey(KeyEvent.KEYCODE_POWER);
            commonModule.acquireWakelock();
            commonModule.wait(2);
            AcceptanceTestCase.assertFalse("AMPM should not display",
                    commonModule.isResourceId("com.sonyericsson.advancedwidget.clock:id/ampm"));

        } else {
            AcceptanceTestCase.assertTrue("AMPM should display",
                    commonModule.isTextExists("1:00 PM"));
            commonModule.wait(5);
            testCase.pressKey(KeyEvent.KEYCODE_POWER);
            commonModule.acquireWakelock();
            commonModule.wait(2);
            AcceptanceTestCase.assertTrue("AMPM should display",
                    commonModule.isResourceId("com.sonyericsson.advancedwidget.clock:id/ampm"));

        }
    }

    private void addMonthData() {
        month.put("1", "Jan");
        month.put("2", "Feb");
        month.put("3", "Mar");
        month.put("4", "Apr");
        month.put("5", "May");
        month.put("6", "Jun");
        month.put("7", "Jul");
        month.put("8", "Aug");
        month.put("9", "Sep");
        month.put("10", "Oct");
        month.put("11", "Nov");
        month.put("12", "Dec");
    }

    public String getDisplayDate() throws UiObjectNotFoundException {
        UiObject hourFormat = new UiObject(new UiSelector().resourceId("android:id/checkbox")
                .instance(2));
        if (!hourFormat.isChecked()) {
            hourFormat.click();
        }
        String displayDate = new UiObject(new UiSelector().resourceId("android:id/summary")
                .instance(2)).getText();
        String displayTime = new UiObject(new UiSelector().resourceId("android:id/summary")
                .instance(3)).getText();
        String dateAndTime = displayDate + " " + displayTime;
        return dateAndTime;

    }

    public long caculatorTimeInterval(Date startDate, Date endDate) {
        long beginTime = startDate.getTime();
        long endTime = endDate.getTime();
        long betweenHours = (long)((endTime - beginTime) / (1000 * 60 * 60));
        return betweenHours;
    }

    public Date printDisplayTime(String time) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        date = format.parse(time);

        return date;

    }

    public void createAndSetAPN(String apnName, String apnSetting, String apnProtocol)
            throws UiObjectNotFoundException {
        launchSettings();
        commonModule.clickText("More…");
        commonModule.clickText("Mobile networks");
        commonModule.clickText("Access Point Names");
        commonModule.clickDescription("New APN");
        commonModule.clickText("Name");
        testCase.enterText(apnName);
        commonModule.clickText("OK");
        commonModule.clickText("APN");
        testCase.enterText(apnSetting);
        commonModule.clickText("OK");
        commonModule.clickListItemwithText("APN protocol");
        AcceptanceTestCase.assertTrue("protocol list display error",
                commonModule.isTextExists("IPv4") && commonModule.isTextExists("IPv6")
                        && commonModule.isTextExists("IPv4/IPv6"));
        commonModule.clickText(apnProtocol);
        commonModule.clickListItemwithText("APN roaming protocol");
        AcceptanceTestCase.assertTrue("protocol list display error",
                commonModule.isTextExists("IPv4") && commonModule.isTextExists("IPv6")
                        && commonModule.isTextExists("IPv4/IPv6"));
        commonModule.clickText(apnProtocol);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.clickText("Save");
        AcceptanceTestCase.assertTrue("APN create failed", commonModule.isTextExists(apnName));
        int[] apnCoor = testCase.getCoordinatesForViewWithText(apnName);
        // int gap = Integer.getInteger(moduleData.get("APN_Selected_Gap"));
        testCase.clickPoint(apnCoor[0] + commonModule.getX(560, 720), apnCoor[1]);

        commonModule.backOutToHomeScreen();
        commonModule.wait(10);
    }

    public void deleteAPN(String apnName) throws UiObjectNotFoundException {
        launchSettings();
        commonModule.clickText("More…");
        commonModule.clickText("Mobile networks");
        commonModule.clickText("Access Point Names");
        commonModule.clickText(apnName);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.clickText("Delete APN");

    }

    public void changeWallpaper() throws UiObjectNotFoundException {
        launchSettings();
        commonModule.clickText("Personalization");
        commonModule.clickText("Wallpaper");
        commonModule.clickText("Album");
        commonModule.waitForText("Select a picture", 2000);
        testCase.clickPoint(commonModule.getX(200, 1080), commonModule.getY(450, 1794));
        if(commonModule.waitForText("Crop picture", 2000)){
            commonModule.clickText("Crop picture");
            if(commonModule.isTextExists("Just once")){
                commonModule.clickText("Just once");
            }
        }
        commonModule.waitForText("Crop", 2000);
        commonModule.clickText("Crop");
        AcceptanceTestCase.assertTrue("Change Wallpaper failed.",
                commonModule.waitForText("Choose wallpaper from", 10 * 1000));
    }

    public void changeTheme() throws UiObjectNotFoundException {
        launchSettings();
        commonModule.clickText("Personalization");
        commonModule.clickText("Themes");
        // Select the first theme in themes list.
        testCase.clickChildItem("list", 0, 0);
        commonModule.clickText("Apply theme");
        if (commonModule.waitForText("Apply theme?", 1000)) {
            commonModule.clickText("OK");
        }
        AcceptanceTestCase.assertTrue("Change Theme failed.",
                commonModule.waitForText("Personalization", 5 * 1000));
    }

    /**
     * This method is unsuitable for remove "Xperia with Facebook", but remove "Facebook" have the same effect
     * @param accountType
     * @param accountName
     * @throws UiObjectNotFoundException
     */
    public void removeAccount(String accountType, String accountName) throws UiObjectNotFoundException{
        launchSettings();

        commonModule.scrollFindTextNotClick("Add account");

        commonModule.clickText(accountType);
        commonModule.wait(2);

        if(accountType != "Facebook"){
            commonModule.clickText(accountName);
            commonModule.wait(2);
        }

        commonModule.pressMoreOption();

        commonModule.clickText("Remove account");
        commonModule.wait(2);

        //confirm remove
        commonModule.clickText("Remove account");
        commonModule.wait(2);
    }

    public void setLongNotificationTone(String musicName, String singer)
            throws UiObjectNotFoundException {
        launchSettings();

        commonModule.scrollFindText("Sound");

        if (!commonModule.waitForText(musicName, 2000)) {
            commonModule.scrollFindText("Notification sound");
            commonModule.waitForDescription("Music library", 2000);

            commonModule.clickDescription("Music library");
            commonModule.waitForText("Sound", 2000);

            commonModule.clickText(singer);

            commonModule.clickText(singer);

            commonModule.clickText(musicName);
            commonModule.waitForText("Notification sound", 2000);

            commonModule.clickText(musicName);
            commonModule.clickText("Done");
        }
        AcceptanceTestCase.assertTrue("Set a long notification tone failed.",
                commonModule.waitForText("Notification sound", 2000)
                        && commonModule.waitForText(musicName, 2000));

        commonModule.backOutToHomeScreen();
    }

    public void setNotificationTone(String tone) throws UiObjectNotFoundException {
        launchSettings();

        commonModule.scrollFindText("Sound");

        if (!commonModule.waitForText(tone, 2000)) {
            commonModule.scrollFindText("Notification sound");

            commonModule.scrollToBegin();

            commonModule.scrollFindText(tone);
            commonModule.clickText("Done");
        }
        AcceptanceTestCase.assertTrue("Set notification tone failed.",
                commonModule.waitForText("Notification sound", 2000)
                        && commonModule.waitForText(tone, 2000));

        commonModule.backOutToHomeScreen();
    }
    
    public void addItemToQuickSetting(String setting) throws UiObjectNotFoundException {
        commonModule.openQuickSettings();
        UiObject toolObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/tools_rows"));
        int count = toolObj.getChildCount();
        if(count==16){
		        	commonModule.clickText("Edit");
		        	for(int i=0;i<4;i++){
										commonModule.dragResourceId("com.android.systemui:id/tools_button_img", "com.android.systemui:id/unused_tools_scroll_view");
		        	}
		        	commonModule.clickText("Done");
        }
        if(!commonModule.isTextExists(setting)){
        	commonModule.clickText("Edit");
        	UiObject scrollObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/unused_tools_scroll_view"));
        	for(int i=0;i<10;i++){
        		if(commonModule.isTextExists(setting)){
        			commonModule.dragText(setting, "com.android.systemui:id/tools_rows");
        			commonModule.clickText("Done");
        			break;
        		}else{
        			scrollObj.swipeLeft(3);
        		}
        	}
        }

    }
    
    public void deleteItemToQuickSetting(String setting) throws UiObjectNotFoundException {
        commonModule.openQuickSettings();
        if(!commonModule.isTextExists(setting)){
        			commonModule.clickText("Edit");
        			commonModule.dragText(setting, "com.android.systemui:id/unused_tools_scroll_view");
        			commonModule.clickText("Done");

        	}
        	
        }
    
		public void openSettingFromNotification(String setting)
				throws UiObjectNotFoundException {
					boolean btSts = isBluetoothOn();
					boolean wfSts = isWifiOn();
					boolean amSts = isAirModeOn();
					int lteSts=0;
					if(setting=="‏LTE"){
						lteSts = getLteStatus();
					}
					boolean arSts = isAutoRotateOn();
					String soundSts = null;
					if(setting=="Sound"){
					   soundSts = getSoundStatus();
					}
					boolean nfcSts = isNfcOn();
			
					commonModule.openQuickSettings();
					if (!testCase.isViewWithTextPresent(setting)) {
						addItemToQuickSetting(setting);
					}
					commonModule.clickText(setting);
			
					if(setting=="Settings") {
						AcceptanceTestCase.assertTrue(
								"Setting cannot open from quick setting",
								commonModule.waitForText("WIRELESS & NETWORKS", 3000));
					} else if(setting == "Location") {
						if (commonModule.isTextExists("Quick settings")) {
							commonModule.clickText(setting);
						}
						AcceptanceTestCase.assertTrue(
								"Location cannot open from quick setting",
								commonModule.waitForText("Use Google's location service?",
										3000)||commonModule.waitForText("Location consent",
												3000));
						commonModule.clickText("Disagree");
					} else if(setting=="Throw") {
						AcceptanceTestCase
								.assertTrue(
										"Throw cannot open from quick setting",
										commonModule
												.waitForResourceId(
														"com.sonymobile.playanywhere:id/device_list",
														3000)||commonModule.waitForText("OK",
																3000));
			
					} else if(setting=="Mobile data") {
						AcceptanceTestCase.assertTrue(
								"Mobile data cannot open from quick setting",
								commonModule.waitForText("Attention", 3000));
						commonModule.clickText("Cancel");
					} else if(setting=="Auto-sync data") {
						AcceptanceTestCase.assertTrue(
								"Auto-sync data cannot open from quick setting",
								commonModule.waitForText("Turn on Auto-sync?", 3000)||commonModule.waitForText("Turn off Auto-sync?", 3000));
						commonModule.clickText("Cancel");
					} else if(setting=="Flash light") {
						commonModule.clickText(setting);
					} else if(setting=="Bluetooth") {
						commonModule.wait(5);
						boolean updateSts = isBluetoothOn();
						
						AcceptanceTestCase.assertTrue(
								"Bluetooth cannot open from quick setting", updateSts!=btSts);
					} else if(setting == "Wi-Fi") {
						commonModule.wait(2);
						boolean updateWF = isWifiOn();
						AcceptanceTestCase.assertTrue(
								"Wi-Fi cannot open from quick setting", wfSts!=updateWF);
					} else if(setting == "Wi-Fi hotspot") {
						if (commonModule.isTextExists("Quick settings")) {
							commonModule.clickText(setting);
						}
						AcceptanceTestCase.assertTrue(
								"Wi-Fi hotspot cannot open from quick setting",
								commonModule.waitForText("Tethering & portable hotspot",
										3000));
						commonModule.clickText("Cancel");
					} else if (setting=="Airplane mode") {
						commonModule.wait(2);
						boolean updateAM = isAirModeOn();
						AcceptanceTestCase.assertTrue(
								"Airplane mode cannot open from quick setting", amSts!=updateAM);
						if (updateAM) {
							commonModule.openQuickSettings();
							commonModule.clickText(setting);
						}
					} else if(setting=="Auto rotate") {
						commonModule.wait(2);
						boolean updateAR = isAutoRotateOn();
						AcceptanceTestCase.assertTrue(
								"Auto rotate cannot open from quick setting", arSts!=updateAR);
						commonModule.clickText("Auto rotate");
					} else if(setting=="Brightness") {
						AcceptanceTestCase.assertTrue(
								"Brightness cannot open from quick setting", commonModule
										.waitForText("Adapt to lighting conditions", 3000));
					} else if(setting=="Screen mirroring") {
						AcceptanceTestCase.assertTrue(
								"Brightness cannot open from quick setting",
								commonModule.waitForText("Start", 3000));
					} else if(setting=="‏LTE"){
						commonModule.wait(5);
						commonModule.backOutToHomeScreen();
						int updateLTE = getLteStatus();
						AcceptanceTestCase.assertTrue(
								"LTE cannot open from quick setting", lteSts!=updateLTE);
						commonModule.openQuickSettings();
						commonModule.wait(2);
					 commonModule.clickText(setting);

					} else if(setting=="Sound") {
						commonModule.wait(2);
						String updateSts = getSoundStatus();
						AcceptanceTestCase.assertTrue(
								"Sound cannot open from quick setting",
								updateSts != soundSts);
						for(int i=0;i<3;i++){
						   commonModule.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
						}
					} else if(setting=="Roaming") {
						AcceptanceTestCase.assertTrue(
								"Roaming cannot open from quick setting",
								commonModule.waitForText("Mobile network settings", 3000));
					} else if(setting=="STAMINA") {
						if (commonModule.isTextExists("Quick settings")) {
							commonModule.clickText(setting);
						}
						AcceptanceTestCase.assertTrue(
								"STAMINA cannot open from quick setting",
								commonModule.waitForText("STAMINA mode",
										3000));
						commonModule.clickText("Cancel");
					} else if(setting=="NFC") {
						commonModule.wait(2);
						boolean updateNFC = isNfcOn();
						AcceptanceTestCase.assertTrue(
								"NFC cannot open from quick setting", nfcSts!=updateNFC);
					}

        }
		
			public boolean isAirModeOn() {
				return (Settings.System.getInt(testCase.getInstrumentation()
						.getContext().getContentResolver(),
						Settings.System.AIRPLANE_MODE_ON, 0) == 1 ? true : false);
			}
		
			public boolean isBluetoothOn() {
				BluetoothAdapter mBth = null;
				mBth = BluetoothAdapter.getDefaultAdapter();
		
				return mBth.isEnabled();
		
			}
		
			public boolean isWifiOn() {
				WifiManager wm=(WifiManager)testCase.getInstrumentation()
						.getContext().getSystemService(Context.WIFI_SERVICE);
				if(wm.getWifiState()==WifiManager.WIFI_STATE_ENABLED||wm.getWifiState()==WifiManager.WIFI_STATE_ENABLING){
					return true;
				}else if(wm.getWifiState()==WifiManager.WIFI_STATE_DISABLED||wm.getWifiState()==WifiManager.WIFI_STATE_DISABLING){
					return false;
				}
				return false;
		    }
		
			public int getLteStatus() throws UiObjectNotFoundException {
				if(isWifiOn()){
					wifiOnOrOff("OFF");
				}

				    ConnectivityManager connectMgr = (ConnectivityManager) testCase.getInstrumentation().getContext()
				   .getSystemService(Context.CONNECTIVITY_SERVICE);
				    NetworkInfo info = connectMgr.getActiveNetworkInfo();
				    if(info !=null && info.getType() ==  ConnectivityManager.TYPE_MOBILE){
							   return info.getSubtype();
							}
				    	return 0;
		}
			
		    public String getSoundStatus() throws UiObjectNotFoundException {

		        longTapPowerKey();
		        UiObject off = new UiObject(new UiSelector().description("Ringer off"));
		        if(off.isSelected()){
		        	commonModule.backOutToHomeScreen();
		        	return "OFF";
		        }

		        UiObject vibrate = new UiObject(new UiSelector().description("Ringer vibrate"));
		        if(vibrate.isSelected()){
		        	commonModule.backOutToHomeScreen();
		        	return "Vibrate";
		        }

		        UiObject on = new UiObject(new UiSelector().description("Ringer on"));
		        if(on.isSelected()){
		        	commonModule.backOutToHomeScreen();
		        	return "ON";
		        }
		        commonModule.backOutToHomeScreen();
		        return "";
		    }
		  public boolean isNfcOn(){
		    NfcManager manager = (NfcManager) testCase.getInstrumentation().getContext().getSystemService(Context.NFC_SERVICE);
		    NfcAdapter adapter = manager.getDefaultAdapter();
		    if (adapter != null && adapter.isEnabled()) {
		        return true;
		    }
		    			return false;

		    }
		  
		  public boolean isAutoRotateOn(){
					  return (Settings.System.getInt(testCase.getInstrumentation()
								.getContext().getContentResolver(),
								Settings.System.ACCELEROMETER_ROTATION, 0) == 1 ? true : false);
		  }
		  
		  public void addMostItemToQuickSetting() throws UiObjectNotFoundException{
			      commonModule.openQuickSettings();
		       commonModule.clickText("Edit");
		       //unused setting item Object
		       UiObject unusedObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/unused_tools"));
		       //Used setting item Object
		       UiObject toolObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/tools_rows"));
		       //The used setting items count after drag
		       int toolCount2 = toolObj.getChildCount();
		       //Init the unused items count
		       int count;
		       do{
		    	     //the used items count before drag
		    	   	int toolCount1 = toolObj.getChildCount();
		    	   	//Setting item
				       UiObject childObj = unusedObj.getChild(new UiSelector().className(
				                "android.widget.FrameLayout").index(0));
				       //drag
		    	   		childObj.dragTo(toolObj, 20);
		    	   		commonModule.wait(2);
		    	   		//update count after drag
		    	   		toolCount2= toolObj.getChildCount();
		    	   		//if the count before drag and after drag is same, tool list is full, break
		    	   		if(toolCount1==toolCount2){
			    	   			Log.i(TAG,"Quick setting is full");
			    	   			commonModule.clickText("Done");
		    	 	        AcceptanceTestCase.assertTrue("Cannot add all item to quick setting", toolObj.getChildCount()==toolCount2);
			    	   			commonModule.backOutToHomeScreen();
			    	   			return;
		    	   			
		    	   		}
		    	   		//update
		    	   		count = unusedObj.getChildCount();
		    	   		
		    	   		}while(count!=0);
		    	   		
		       	//after drag, save the change, and get item count
		       commonModule.clickText("Done");
	 	       AcceptanceTestCase.assertTrue("Cannot add all item to quick setting", toolObj.getChildCount()==toolCount2);
		       commonModule.backOutToHomeScreen();
		       
		  }
		  
		  public void removeAllItemFromQuickSetting() throws UiObjectNotFoundException{
		      commonModule.openQuickSettings();
	       commonModule.clickText("Edit");
	       //tool list obj
	       UiObject toolObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/tools_rows"));
	       //the item count in tool list
	       int count = toolObj.getChildCount();
	       UiObject unusedObj = new UiObject(new UiSelector().resourceId("com.android.systemui:id/unused_tools"));
	       while(count!=0){
			       UiObject childObj = toolObj.getChild(new UiSelector().className(
			                "android.widget.FrameLayout").index(0));
	    	   		childObj.dragTo(unusedObj, 20);
	    	   		commonModule.wait(2);
	    	   		count = toolObj.getChildCount();
	    	   		
	       }
	       
	       commonModule.clickText("Done");
	       AcceptanceTestCase.assertFalse("Cannot remove all item from quick setting", toolObj.exists());
	       commonModule.backOutToHomeScreen();
	  }

}
