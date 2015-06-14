
package com.test.reliability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import com.services.AlarmReceiver;
import com.services.AlarmService;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.utils.CommandConstantsUtils;
import com.utils.ErroLogger;
import com.utils.ErrorInfoUtils;
import com.utils.Logger;
import com.utils.NetWorkStatusUtils;
import com.utils.ScreenResolutionUtils;

/**
 *
 * 1. http://www.gte.nu/homes/cnl850490
 2. Select the file "173.mov"--"wlan download-50mb
 *
 */

/**
 *
 *
 *
 *
 * this is the class of BasicUtils and this class is Basic class,other test case
 * class extends this class
 *
 *
 * @author
 * @update this is update data
 * @since 2012.04.20
 *
 */
//public class BasicUtils extends AcceptanceTestCase {
public class BasicUtils {
    public final String POWER_TAG = "Reliability_TEST";

    public String phoneType = "1";

    public String varient = "General";

    public static Logger logger;

    public static ErroLogger waitlogger;

    public static final String FINISH_SHOW1_1 = "finish___FlightMode1___cases";

    public static final String FINISH_SHOW1_2 = "finish___FlightMode2___cases";

    public static final String FINISH_SHOW2 = "finish___BatteryLife2G___cases";

    public static final String FINISH_SHOW3 = "finish___BatteryLife3G___cases";

    public static final String FINISH_SHOW4 = "finish___BatteryLife4G___cases";

    public static String _2G_Mode;

    public static String _3G_Mode;

    public static String _4G_Mode;

    public static String _CDMA_Mode;

    public static String _EVDO_Mode;

    private static String callNumber;

    private static String callNumber2;

    private static String callNumber3;

    private static String selNumber;

    private static String parterPhoneDeviceId;

    private static String testPhoneDeviceId;

    private static String test_EASaccount;

    private static String test_EASpassword;

    private static String test_emailaccount;

    private static String test_emailpassword;

    private static String test_facebookaccount;

    private static String test_facebookpassword;

    private static String test_googleaccount;

    private static String test_googlepassword;

    private static String test_wifi;

    private static String test_wifipassword;

    private static String test_emailserver;

    private static int video_clip_number;

    public static int waitLogID = 1;

    public static boolean tittleSwitch = true;

    public static int sCurrLoop = 1;

    public static int sRunLoops = 1;

	private static boolean hasSetBT = false;

	private static String callNumberPrefix = "+86";

    public static HashMap<String, String> sCurrParameter;

    List<String> timesList;

    /**
     * some mode xxx.csv file
     */
    public static final String LOG_FILE = "//sdcard//Agingtestlog_"
            + formatDate(System.currentTimeMillis()) + ".csv";

    // final String LOG_FILE = Environment.getExternalStorageDirectory() +
    // "/BatteryLifelog_"
    // + formatDate(System.currentTimeMillis()) + ".csv";

    public static final String WAITOG_FILE = "//sdcard//Agingtestwaitlog_"
            + formatDate(System.currentTimeMillis()) + ".csv";

    // final String WAITOG_FILE = Environment.getExternalStorageDirectory() +
    // "/BatteryLifewaitlog_"
    // + formatDate(System.currentTimeMillis()) + ".csv";
    /**
     * before log file
     */
    // public static final String LOG_FILE = "//sdcard//powerlog.csv";

    public static final String PASS_INFO = "pass";

    public static final String FAIL_INFO = "fail";

    public static int width;

    public static int heigh;

    public static int path00[][];

    public static int mustSleepAnySec = 10;

    public static int WAIT_FOR_TIME_SEC = 6;

    public BasicUtils() {
        //super(8000, 1000, "AcceptanceTest", true, false);

            if (this.tittleSwitch) {
                initLog();
                //this.setTitle();
                this.tittleSwitch = false;
            }

    }

    /*public void initScreanOnSpee() {
        PowerManager power = (PowerManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.POWER_SERVICE);

        if (!power.isScreenOn()) {

            this.waitTillSleepModePressPower(30);
            this.wait(1);
        }

    }*/

    /*public boolean ishasExcel() {

        boolean isFind = false;

        for (int i = 0; i < 5; i++) {
            this.waitForMill(1000);
            for (int k = 0; k < 4; k++) {
                this.scrollDown();
                this.waitForMill(200);
            }
            if (!isViewWithTextPresent("ZTest excel.xlsx")) {
                continue;
            }

            else {
                isFind = true;
                break;
            }

        }
        return isFind;
    }

    public void openExcel() {
        this.clickItemWithText("ZTest excel.xlsx");

        this.wait(3);
        if (isViewWithIdPresent("button1")) {
            this.clickId("button1");
            this.waitForMill(200);
        }
        this.wait(2);
        if (isViewWithTextPresent("CSV Settings")) {
            this.clickId("button1");
            this.wait(2);

        }
    }*/

    /*public void waitForTextViewCompoment(String viewTextName, int sec) {
        boolean isFind = false;
        long endTime = this.getCurrTime() + sec * 1000L;
        while (this.getCurrTime() < endTime) {
            this.wait(1);
            if (this.isViewWithTextPresent(viewTextName)) {
                isFind = !isFind;
                break;
            }
            this.wait(1);
        }
        if (!isFind) {
            assertTrue(false);
        }

    }*/

    /*public void waitForIdViewCompoment(String viewIdName, int sec, boolean allowFail) {
        boolean isFind = false;
        long endTime = this.getCurrTime() + sec * 1000L;
        while (this.getCurrTime() < endTime) {
            this.wait(1);
            if (this.isViewWithIdPresent(viewIdName)) {
                isFind = !isFind;
                break;
            }
            this.wait(1);
        }

        if (!allowFail && !isFind) {
            assertTrue(false);
        }

    }*/

    /*public void waitForIdViewCompoment(String viewIdName, int sec) {
        boolean isFind = false;
        long endTime = this.getCurrTime() + sec * 1000L;
        while (this.getCurrTime() < endTime) {
            this.wait(1);
            if (this.isViewWithIdPresent(viewIdName)) {
                isFind = !isFind;
                break;
            }
            this.wait(1);
        }

        if (!isFind) {
            assertTrue(false);
        }

    }*/

    /*public void waitForIdDisappearViewCompoment(String viewIdName, int sec) {

        boolean isDisappear = false;
        long endTime = this.getCurrTime() + sec * 1000L;
        while (this.getCurrTime() < endTime) {
            this.wait(1);
            if (!this.isViewWithIdPresent(viewIdName)) {
                isDisappear = !isDisappear;
                break;
            }
            this.wait(1);
        }
        if (!isDisappear) {
            assertTrue(false);
        }

    }*/

    /*public void waitForTextDisappearViewCompoment(String text, int sec) {

        boolean isDisappear = false;
        long endTime = this.getCurrTime() + sec * 1000L;
        while (this.getCurrTime() < endTime) {
            this.wait(1);
            if (!this.isViewWithTextPresent(text)) {
                isDisappear = !isDisappear;
                break;
            }
            this.wait(1);
        }
        if (!isDisappear) {
            assertTrue(false);
        }

    }
*/
    // public void initScreanOnSpee(){
    // Log.i("mylog", "1.into: initScreanOnSpee():");agingtest001@gmail.com
    // PowerManager power = (PowerManager) this.getInstrumentation().
    // getTargetContext().getSystemService(Context.POWER_SERVICE);
    //
    // if(!power.isScreenOn()){
    // Log.i("mylog", "2.into :if(!power.isScreenOn())");
    // this.waitTillSpeed_UPDATE(1);
    // Log.i("mylog", "3.get out :if(!power.isScreenOn())");
    //
    // }
    // this.wait(2);
    // if(power.isScreenOn() && isViewWithIdPresent("lock_screen")){
    // Log.i("mylog",
    // "4.into :if(power.isScreenOn() && isViewWithIdPresent(\"lock_screen\"))");
    // this.unlockScreenUPDATE();
    // Log.i("mylog",
    // "5.get out :if(power.isScreenOn() && isViewWithIdPresent(\"lock_screen\"))");
    // }else{
    // Log.i("mylog", "6.into :else");
    // this.waitTillSpeed_UPDATE(1);
    // Log.i("mylog", "7.get out :else");
    // }
    // Log.i("mylog", "8.get out :  initScreanOnSpee():");
    // }
    //
    private int runFlag = 0;

    private int realTimes = 0;

    private long time1;

    private long time4;

    @SuppressWarnings("static-access")
    public void destroyResource() throws IOException {

        if (runFlag == 1) {
            this.logger.logTime(time1);
            // basicUtils.logger.logTime(time2);
            // basicUtils.logger.logTime(time3);
            this.logger.logTime(time4);
            this.logger.logColumnInfo(this.PASS_INFO);

        } else if (runFlag == 2) {
            // basicUtils.logger.logColumnInfo(", , , ," +
            // basicUtils.FAIL_INFO);
            this.logger.logColumnInfo(", ," + this.FAIL_INFO);
            this.logger.logColumnInfo("" + ErrorInfoUtils.ERROR_UNKNOWN);

        } else if (runFlag == 3) {

            // basicUtils.logger.logColumnInfo(", , , ," +
            // basicUtils.FAIL_INFO);
            this.logger.logColumnInfo(", ," + this.FAIL_INFO);
            this.logger.logColumnInfo("" + ErrorInfoUtils.ERROR_3G);
            Log.d(POWER_TAG, "this case is failed !!");
        } else if (runFlag == 4) {

            // basicUtils.logger.logColumnInfo(", , , ," +
            // basicUtils.FAIL_INFO);
            this.logger.logColumnInfo(", ," + this.FAIL_INFO);
            this.logger.logColumnInfo("" + "skip");
            Log.d(POWER_TAG, "this case is not run !!");
        } else if (runFlag == 5) {

            // basicUtils.logger.logColumnInfo(", , , ," +
            // basicUtils.FAIL_INFO);
            this.logger.logColumnInfo(", ," + this.FAIL_INFO);
            this.logger.logColumnInfo("" + "get Messages:" + realTimes);
            Log.d(POWER_TAG, "this case is not run !!");
        } else if (runFlag == 6) {

            this.logger.logColumnInfo(", , , ," + this.FAIL_INFO);
            this.logger.logColumnInfo("" + "Action failed");
            Log.d(POWER_TAG, "this case is not run !!");
        }
        runFlag = 0;
        this.releaseWakelock();
    }

	/*public String getPhoneDeviceId() {
		getPhoneInfo();
		return deviceId;
	}

	public String getParterPhoneDeviceId() {
		return parterPhoneDeviceId;
	}*/

    /*public void setVGAModle2() {

        this.launchAppByManual("Camera");
        this.wait(5);
        this.clickPoint(this.getX(1750, 1794), this.getY(50, 1080));
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("VGA");
    }*/

    /*public void cancelVGAModle2() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.wait(5);
        this.clickPoint(this.getX(1750, 1794), this.getY(50, 1080));
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("Full HD");
    }*/

    /*public void setHDModle2() {

        this.launchAppByManual("Camera");
        this.wait(5);
        this.clickPoint(this.getX(1750, 1794), this.getY(50, 1080));
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("HD 720p");
    }*/

    /*public void cancelHDModle2() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.wait(5);
        this.clickPoint(this.getX(1750, 1794), this.getY(50, 1080));
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("Full HD");
    }*/

    /*public void setVGAModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        // this.clickPoint(this.getX(160, 1794), this.getY(1000, 1080));
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("640x480(4:3)");
    }*/

    /*public void cancelVGAModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        // this.clickPoint(this.getX(160, 1794), this.getY(1000, 1080));
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("1920x1080(16:9)");
    }*/

    /*public void setHDModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        // this.clickPoint(this.getX(160, 1794), this.getY(1000, 1080));
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("1280x720(16:9)");
    }*/

    /*public void cancelHDModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        // this.clickPoint(this.getX(160, 1794), this.getY(1000, 1080));
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Video resolution");
        this.wait(2);
        this.click("1920x1080(16:9)");
    }*/

    /*public void setHDRModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        this.clickPoint(this.getX(160, 1794), this.getY(500, 1080));
        this.wait(2);
        this.click("On");

    }*/

    /*public void cancelHDRModle() {

        this.launchAppByManual("Camera");
        this.wait(5);

        this.clickPoint(this.getX(160, 1794), this.getY(100, 1080));
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(700, 1794), this.getY(100, 1080)
                }, {
                        this.getX(300, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.clickItemWithText("Video camera");
        this.wait(2);
        this.clickPoint(this.getX(160, 1794), this.getY(500, 1080));
        this.wait(2);
        this.click("Off");
    }*/

    /*public void deleteVideo() {
        this.backOutToHomeScreen();
        this.launchAppByManual("Album");
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        // this.wait(10);
        this.waitForTextViewCompoment("Select items", 10);
        this.click("Select items");
        this.wait(2);
        // this.clickPoint(this.getX(350, 1794), this.getY(350, 1080));
        this.longPressCoordinates(this.getX(350, 1794), this.getY(350, 1080), 5);
        this.wait(2);

        if (!this.isViewWithIdPresent("selectmode_option_delete")) {
            this.wait(2);
            this.longPressCoordinates(this.getX(350, 1794), this.getY(350, 1080), 5);
            if (!this.isViewWithIdPresent("selectmode_option_delete")) {
                assertTrue(false);
            }
        }
        this.clickItemWithId("selectmode_option_delete");
        this.wait(2);
        if (this.isViewWithIdPresent("button1")) {
            this.clickId("button1");
        }
        this.wait(1);
        if (isViewWithTextPresent("OK")) {
            this.click("OK");
        }
        this.wait(1);
        if (isViewWithTextPresent("Delete")) {
            this.click("Delete");
        }
        this.wait(5);
        this.backOutToHomeScreen();
    }*/

    /*public void deletecapturedpic() {
        this.backOutToHomeScreen();
        this.launchAppBySearch("OfficeSuite");
        // this.launchApp("com.mobisystems.office",
        // "com.mobisystems.office.FileBrowser");
        this.wait(4);
        if (this.isViewWithTextPresent("Continue")) {
            this.click("Continue");
        }
        this.wait(1);
        if (this.isViewWithTextPresent("Register later")) {
            this.clickItemWithText("Register later");
            this.wait(2);
        }
        this.wait(1);
        if (this.isViewWithTextPresent("Later")) {
            this.clickItemWithText("Later");
            this.wait(2);
        }
        if (!this.isViewWithTextPresent("Internal storage")) {
            this.scrollHomeLeft();
            this.wait(3);
            this.clickItemWithText("Internal storage");
            this.wait(2);
        }
        // this.clickItemWithText("DCIM");
        this.scrollFindText("DCIM");
        this.wait(2);
        if (this.isViewWithTextPresent("100ANDRO")) {
            if (!this.isCheckboxChecked("list_item_check", 0)) {
                this.clickItemWithId("list_item_check", 0);
            }
            this.wait(1);
            this.clickId("edit_delete");
            if (this.isViewWithIdPresent("alertTitle")) {
                this.clickId("button1");
            }
            Log.d(POWER_TAG, "The DCIM folder is deleted");
        } else {
            Log.e(POWER_TAG, "No picture captured");
        }
        this.backOutToHomeScreen();
    }*/

    /*public void deleteIteminAlbum() {
        this.backOutToHomeScreen();
        // this.launchAppByManual("Album");
        this.launchApp("com.sonyericsson.album", "com.sonyericsson.album.MainActivity");
        this.wait(8);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        // this.wait(10);
        this.waitForTextViewCompoment("Select items", 10);
        this.click("Select items");
        this.wait(2);
        // this.clickPoint(this.getX(350, 1794), this.getY(350, 1080));
        this.longPressCoordinates(this.getX(350, 1794), this.getY(350, 1080), 5);
        this.wait(2);

        if (!this.isViewWithIdPresent("selectmode_option_delete")) {
            this.wait(2);
            this.longPressCoordinates(this.getX(350, 1794), this.getY(350, 1080), 5);
            if (!this.isViewWithIdPresent("selectmode_option_delete")) {
                assertTrue(false);
            }
        }
        this.clickItemWithId("selectmode_option_delete");
        this.wait(2);
        if (this.isViewWithIdPresent("button1")) {
            this.clickId("button1");
        }
        this.wait(1);
        if (isViewWithTextPresent("OK")) {
            this.click("OK");
        }
        this.wait(1);
        // if (isViewWithTextPresent("Delete")) {
        // this.click("Delete");
        // }
        // this.wait(5);
        Log.d(POWER_TAG, "The first Itme in Album is deleted");
        this.backOutToHomeScreen();
    }*/

    // public void initScreanOnSpee() {
    //
    // PowerManager power =
    // (PowerManager)this.getInstrumentation().getTargetContext()
    // .getSystemService(Context.POWER_SERVICE);
    //
    // if (!power.isScreenOn()) {
    //
    // this.waitTillSpeedIntervalCCC(2);
    // } else {
    // this.wait(2);
    // }
    //
    // if (isViewWithTextPresent("Swipe up or down to unlock")) {
    // this.unlockScreenForUGA();
    // Log.d(POWER_TAG, "Phone is unlocked3");
    //
    // } else {
    // // this.unlockScreenForUGA();
    // Log.d(POWER_TAG, "Phone already unlocked");
    // }
    // // pressKey(KeyEvent.KEYCODE_HOME);
    // // this.backOutToHomeScreen();
    // }
    /*public void initScreanOn() {

        PowerManager power = (PowerManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.POWER_SERVICE);

        if (!power.isScreenOn()) {

            this.waitTillSpeedIntervalCCC(2);
        } else {
            this.wait(2);
        }

        if (isViewWithTextPresent("Swipe to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked1");
            // } else if (isViewWithIdPresent("lock_screen")){
        } else if (isViewWithTextPresent("Swipe up or down to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked2");
        } else {
            Log.d(POWER_TAG, "Phone already unlocked");
        }
        // pressKey(KeyEvent.KEYCODE_HOME);
        this.backOutToHomeScreen();
    }*/

    /*public void unlockScreenForUGA2() {

        Log.d(POWER_TAG, "------------------Unlock screen");
        // Yuga
        // float path0[][] = {{300,300}, {300,500},{300, 900}};
        // TuBaSa
        float path0[][] = {
                {
                        270, 800
                }, {
                        455, 800
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.backOutToHomeScreen();

    }*/

    /*public void unlockScreenForUGA() {

        Log.d(POWER_TAG, "------------------Unlocking screen");
        // Yuga
        // float path0[][] = {{300,300}, {300,500},{300, 900}};
        // TuBaSa
        float path0[][] = {
                {
                        // this.getX(358, 1794), this.getY(75, 1080)
                        // }, {
                        // this.getX(358, 1794), this.getY(404, 1080)
                        // }, {
                        // this.getX(358, 1794), this.getY(776, 1080)
                        this.getX(358, 1794), this.getY(775, 1080)
                }, {
                        this.getX(358, 1794), this.getY(404, 1080)
                }, {
                        this.getX(358, 1794), this.getY(76, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        this.wait(3);
        // pressKey(KeyEvent.KEYCODE_HOME);
        this.backOutToHomeScreen();

    }*/

    /*public void scrollscreenright() {

        Log.d(POWER_TAG, "------------------Unlock screen");
        // Yuga
        // float path0[][] = {{300,300}, {300,500},{300, 900}};
        // TuBaSa
        float path0[][] = {
                {
                        // this.getX(358, 1794), this.getY(75, 1080)
                        // }, {
                        // this.getX(358, 1794), this.getY(404, 1080)
                        // }, {
                        // this.getX(358, 1794), this.getY(776, 1080)
                        this.getX(1400, 1794), this.getY(540, 1080)
                }, {
                        this.getX(1300, 1794), this.getY(540, 1080)
                }, {
                        this.getX(1200, 1794), this.getY(540, 1080)
                }, {
                        this.getX(1100, 1794), this.getY(540, 1080)
                }, {
                        this.getX(1000, 1794), this.getY(540, 1080)
                }, {
                        this.getX(900, 1794), this.getY(540, 1080)
                }, {
                        this.getX(800, 1794), this.getY(540, 1080)
                }, {
                        this.getX(700, 1794), this.getY(540, 1080)
                }, {
                        this.getX(600, 1794), this.getY(540, 1080)
                }, {
                        this.getX(500, 1794), this.getY(540, 1080)
                }, {
                        this.getX(400, 1794), this.getY(540, 1080)
                }, {
                        this.getX(300, 1794), this.getY(540, 1080)
                }

        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0, 200);
        resetTimeout();
        this.wait(3);
        // pressKey(KeyEvent.KEYCODE_HOME);
        // this.backOutToHomeScreen();

    }*/

	/*public void scrollScreenLeft() {
		Log.d(POWER_TAG, "------------------scrollScreenLeft");
		float path0[][] = { { this.getX(300, 1794), this.getY(540, 1080) },
				{ this.getX(400, 1794), this.getY(540, 1080) },
				{ this.getX(500, 1794), this.getY(540, 1080) },
				{ this.getX(600, 1794), this.getY(540, 1080) },
				{ this.getX(700, 1794), this.getY(540, 1080) },
				{ this.getX(800, 1794), this.getY(540, 1080) },
				{ this.getX(900, 1794), this.getY(540, 1080) },
				{ this.getX(1000, 1794), this.getY(540, 1080) },
				{ this.getX(1100, 1794), this.getY(540, 1080) },
				{ this.getX(1200, 1794), this.getY(540, 1080) },
				{ this.getX(1300, 1794), this.getY(540, 1080) },
				{ this.getX(1400, 1794), this.getY(540, 1080) } };
		setTimeout(12000); // "after Guoq update "8000
		dragGesture(path0, 200);
		resetTimeout();
		this.wait(3);
	}*/
    /*public void waitTillSpeed2(int secs) {

        PowerManager power = (PowerManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.POWER_SERVICE);
        boolean flag = false;

        this.pressKey(KeyEvent.KEYCODE_POWER);
        Log.i("test", "1.start set");
        setAlarm(secs);
        this.wait(secs);
        this.wait(10);
        for (int i = 0; i < 10; i++) {
            if (power.isScreenOn()) {
                this.wait(1);
                break;
            }
            this.wait(2);

        }
        wait(1);
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenUPDATE();
        }
        cancelAlarm();
    }*/

    /*public void waitScreamOnAndDisLockScreen() {
        PowerManager power = (PowerManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.POWER_SERVICE);
        this.wait(5);
        for (int i = 0; i < 10; i++) {
            this.wait(5);
            if (power.isScreenOn()) {
                this.wait(2);
                break;
            }

        }
        wait(1);
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenUPDATE();
        }
    }*/

    /**
     * this is the method for initLog
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     */
    public void initLog() {
        waitlogger = new ErroLogger();
        waitlogger.initLog(WAITOG_FILE);
        logger = new Logger();
        logger.initLog(LOG_FILE);

        Log.i("what", LOG_FILE);

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
    /*public void backOutToHomeScreen() {

        for (int i = 0; i < 5; i++) {

            pressKey(KeyEvent.KEYCODE_BACK);
        }
        pressKey(KeyEvent.KEYCODE_HOME);
        this.wait(1);

    }*/

    /*public void cancelMSGDeliveryreport() {
        this.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        this.wait(5);
        if (this.isViewWithIdPresent("alertTitle") && this.isViewWithTextPresent("Change SMS app?")) {
            this.clickId("button1");
        }
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.click("Settings");
        if (isCheckboxChecked("checkbox", 3)) {
            this.clickItemWithId("checkbox", 3);
            this.wait(3);

        }
        this.backOutToHomeScreen();

    }*/

    /*public void OpenLocationServices() {
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        int i = 0;
        while (i < 5) {
            if (this.isViewWithTextPresent("Location services")) {
                this.click("Location services");
                this.wait(2);
                if (!this.isCheckboxChecked("switchWidget", 0)) {
                    this.clickItemWithId("switchWidget", 0);
                    Log.d(POWER_TAG, "Location services will be opened.");
                }
                if (this.isViewWithTextPresent("Location consent")
                        || this.isViewWithTextPresent("Note")) {
                    this.click("Agree");
                }
                if (this.isViewWithTextPresent("Note")
                        || this.isViewWithTextPresent("Location consent")) {
                    this.click("Agree");
                }
                this.wait(2);
                this.click("Let Google apps access your location");
                if (!this.isCheckboxChecked("allow_access")) {
                    this.clickId("allow_access");
                }
                // if (!this.isCheckboxChecked("checkbox", 0)){
                // this.clickItemWithId("checkbox", 0);
                // }
                // if (!this.isCheckboxChecked("checkbox", 1)){
                // this.clickItemWithId("checkbox", 0);
                // }
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();

    }*/

    /*public void turnoffLocationServices() {
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        int i = 0;
        while (i < 5) {
            if (this.isViewWithTextPresent("Location services")) {
                this.click("Location services");
                this.wait(2);
                if (this.isCheckboxChecked("switchWidget", 0)) {
                    this.clickItemWithId("switchWidget", 0);
                    Log.d(POWER_TAG, "Location services is turned off.");
                }
                this.wait(2);
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }*/

    /*public void DeleteConversation(String text) {
        this.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        this.wait(5);
        if (this.isViewWithTextPresent(text)) {
            this.pressKey(KeyEvent.KEYCODE_MENU);
            this.wait(1);
            this.click("Delete conversations");
            this.wait(1);
            this.click(text);
            this.wait(1);
            this.clickItemWithId("menu_delete");
            this.wait(1);
            this.clickId("button1");
            // this.backOutToHomeScreen();
        }
        // else{
        // this.backOutToHomeScreen();
        // }
    }*/

    /*@SuppressWarnings("static-access")
    public void PlayVideo(String videoname) {

        this.launchApp("com.sonyericsson.video", "com.sonyericsson.video.browser.BrowserActivity");
        this.wait(4);
        if (this.isViewWithIdPresent("button2")) {
            this.clickId("button2");
        }
        this.wait(2);
        this.clickId("more_text");
        this.wait(2);
        boolean ishas = false;
        for (int i = 0; i < 10; i++) {
            if (this.isViewWithTextPresent(videoname)) {
                this.click(videoname);
                ishas = true;
                break;
            }
            this.wait(2);
            this.scrollDown();
            this.wait(3);
        }
        if (!ishas) {
            this.backOutToHomeScreen();
            this.assertTrue(false);
        }
        this.wait(2);
        if (this.isViewWithIdPresent("details_play")) {
            this.clickId("details_play");// /
        }
        this.wait(3);
        if (this.isViewWithTextPresent("OK") || this.isViewWithTextPresent("Cannot play video.")) {
            this.click("OK");
            this.wait(2);
            this.backOutToHomeScreen();
            this.assertTrue(false);
        }
        do {
            this.clickPoint(this.getX(850, 1794), this.getY(540, 1080));
        } while (!this.isViewWithIdPresent("video_play_button"));
        this.clickId("video_play_button");
        this.pressKey(KeyEvent.KEYCODE_MENU);
        if (this.isViewWithTextPresent("Play options")) {
            this.click("Play options");
            this.wait(1);
            this.click("Repeat one");
        } else {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            while (!this.isViewWithTextPresent("Repeat one")) {
                this.clickId("playmode_button");
            }
        }
        this.clickId("video_play_button");
    }*/

    /*public void setAlarmRingwithin(int min) {

        // this.launchAppByManual("Alarm & clock");
        this.launchApp("com.sonyericsson.organizer", "com.sonyericsson.organizer.Organizer");
        this.wait(4);
        this.waitForIdViewCompoment("menu_launcher_addalarm", 4);
        this.clickId("menu_launcher_addalarm");
        this.wait(2);
        this.clickId("editText");
        this.wait(1);
        this.openStatusBar();
        if (this.isViewWithTextPresent("Choose input method")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(1);
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(1);
        } else {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(1);
        }
        this.scrollDown();
        this.scrollDown();
        this.click("Side keys behavior");
        this.wait(2);
        this.click("Dismiss");
        this.wait(2);
        this.scrollUp();
        this.scrollUp();
        Log.e(POWER_TAG, "Set Alarm 3");
        this.waitForTextViewCompoment("Time", 4);
        this.click("Time");
        // get current hour and min
        this.waitForIdViewCompoment("numberpicker_input", 4);
        int hour = Integer.valueOf(this.getTextFromViewWithId("numberpicker_input"));
        Log.i("min", "-----> " + hour);
        int minute = Integer.valueOf(this.getTextFromViewWithId("numberpicker_input", 1));
        Log.i("min", "-----> " + minute);

        int alarmtime = minute + min - 60;
        // if (minute <= 58) {
        if (alarmtime < 0) {

            this.clickItemWithId("numberpicker_input", 1);
            Log.i("min", "-----> " + (String.valueOf(minute) + 2));
            this.enterText(String.valueOf(minute + min));// add 2 min
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(1);
            if (this.isViewWithTextPresent("Done")) {
                this.click("Done");
            } else {
                this.click("Set");
            }
            this.waitForTextViewCompoment("Done", 4);
            this.click("Done");// agait done, right up "done"
        } else { // min >58
            this.clickItemWithId("numberpicker_input", 1); // set minute
            this.enterText(String.valueOf(minute + min - 60));
            // switch (minute) {
            // case 58:
            // this.enterText(String.valueOf(minute + min - 60));
            // break;
            //
            // case 59:
            // this.enterText(String.valueOf(minute + min - 60));
            // break;
            //
            // default:
            // break;
            // }
            this.clickItemWithId("numberpicker_input", 0);// set hour
            if ((hour + 1) == 13) {
                this.enterText("01");// /////cccc
            } else {
                this.enterText(String.valueOf(hour + 1));// add 1 hour
            }
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(2);
            if (this.isViewWithTextPresent("Done")) {
                Log.e(POWER_TAG, "Set Alarm 1");
                this.click("Done");
            } else {
                this.click("Set");
            }
            Log.e(POWER_TAG, "Set Alarm 2");
            this.waitForTextViewCompoment("Done", 4);
            this.click("Done"); // done againt
            Log.e(POWER_TAG, "Set Alarm 4");
            this.wait(3);
            // this.backOutToHomeScreen();
        }

        this.wait(2);

    }*/

    /**
     * this is the method for formatDate
     *
     * @param long date: the time is long type
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    private static String formatDate(long date) {

        String timeScape = new Timestamp(date).toString() + "";
        String buff = "";

        for (int i = 0; i < timeScape.length(); i++) {

            char oneWord = timeScape.charAt(i);
            String str = String.valueOf(oneWord);

            if (str.equals(" ")) {
                buff += "_";
                continue;
            }
            if (str.equals(":")) {
                continue;
            }
            if (str.equals("-")) {
                continue;
            }
            if (str.equals(".")) {

                continue;
            }
            buff += str;
        }
        return buff;
    }

    /**
     * this is the method of launchAppByManual and this method can launch the
     * application by manual
     *
     * @param appDisplayName : the name of the application
     *
     * @author
     * @update this is the update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void launchAppByManual(String appDisplayName) {
        pressKey(KeyEvent.KEYCODE_HOME);
        // clickItemWithId("apptray_button");
        this.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        int flag = 0, i = 0;

        while (flag == 0 && i < 8) {

            if (isViewWithTextPresent(appDisplayName)) {

                click(appDisplayName);
                flag = 1;
            } else {

                // scrollAppPanelRight();
                this.scrollAppPanelRightUPDATE();
                i++;
            }
        }

        assertTrue("------------------Fail to launch " + appDisplayName, flag == 1);
    }*/

    /*public void launchAppBySearch(String appDisplayName) {

        // this.backOutToHomeScreen();
        // this.wait(2);
        // this.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        // this.wait(1);
        // int flag = 0;
        // for (int i = 0; i < 5; i++){
        // this.scrollHomeLeft();
        // this.wait(2);
        // if (this.isViewWithTextPresent("Search apps")){
        // this.click("Search apps");
        // this.enterText(appDisplayName);
        // this.wait(1);
        // this.clickPoint(this.getX(540, 1080), this.getY(270, 1794));
        // // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
        // // this.pressKey(KeyEvent.KEYCODE_ENTER);
        // // this.wait(1);
        // // this.pressKey(KeyEvent.KEYCODE_ENTER);
        // this.wait(2);
        // flag = 1;
        // Log.d(POWER_TAG, appDisplayName + " is launched successfully");
        // break;
        // }
        // else{
        // this.backOutToHomeScreen();
        // this.wait(2);
        // this.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        // this.wait(1);
        // }
        // }
        // assertTrue("------------------Fail to launch " + appDisplayName, flag
        // == 1);

        this.backOutToHomeScreen();
        this.wait(2);
        this.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
        this.wait(1);
        int flag = 0;
        for (int i = 0; i < 5; i++) {
            this.scrollHomeLeft();
            this.wait(2);
            if (this.isViewWithTextPresent("Search apps")) {
                this.click("Search apps");
                this.enterText(appDisplayName);
                this.wait(1);
                this.clickPoint(this.getX(540, 1080), this.getY(245, 1794));
                // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                // this.pressKey(KeyEvent.KEYCODE_ENTER);
                // this.wait(1);
                // this.pressKey(KeyEvent.KEYCODE_ENTER);
                this.wait(2);
                flag = 1;
                Log.d(POWER_TAG, appDisplayName + " is launched successfully");
                break;
            } else {
                this.backOutToHomeScreen();
                this.wait(2);
                this.clickPoint(this.getX(540, 1080), this.getY(1720, 1794));
                this.wait(1);

            }
        }
        assertTrue("------------------Fail to launch " + appDisplayName, flag == 1);
    }*/

    /**
     * this is the method of launchAppByManual2
     *
     * @param String appDisplayName, String appDisplayName2 this method could
     *            input two application name
     *
     * @author
     * @update this is the update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void launchAppByManual2(String appDisplayName, String appDisplayName2) {

        pressKey(KeyEvent.KEYCODE_HOME);
        clickItemWithId("apptray_button");
        int flag = 0, i = 0;

        while (flag == 0 && i < 5) {

            if (isViewWithTextPresent(appDisplayName)) {
                click(appDisplayName);
                flag = 1;

            } else if (isViewWithTextPresent(appDisplayName2)) {
                click(appDisplayName2);
                flag = 1;

            } else {

                scrollAppPanelRightUPDATE();
                i++;
            }
        }

        assertTrue("------------------Fail to launch " + appDisplayName, flag == 1);
    }*/

    /**
     *
     * this is the method of scrollAppPanelRight and this method can drag
     * gesture to right
     *
     * @author
     * @update this is update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void scrollAppPanelRight() {

        int[] screenSize = this.getScreenSize();
        int x = screenSize[0];
        int y = screenSize[1];

        Log.i(POWER_TAG, "x == " + x + " ,y== " + y);

        float[][] path0 = null;
        if (x == ScreenResolutionUtils.PHONE_MINT_WIDTH
                && y == ScreenResolutionUtils.PHONE_MINT_HEIGHT) {

            // MINT
            float path1[][] = {
                    {
                            706, 636
                    }, {
                            300, 636
                    }, {
                            500, 636
                    }, {
                            120, 636
                    }
            };
            path0 = path1;
        } else if (x == ScreenResolutionUtils.PHONE_NP_WIDTH
                && y == ScreenResolutionUtils.PHONE_NP_HEIGHT) {

            // NP
            float path2[][] = {
                    {
                            536, 457
                    }, {
                            300, 457
                    }, {
                            200, 457
                    }, {
                            120, 457
                    }
            };
            path0 = path2;
        } else if (x == ScreenResolutionUtils.PHONE_KQ_PP_WIDTH
                && y == ScreenResolutionUtils.PHONE_KQ_PP_HEIGHT) {

            // KQ ## PP
            float path3[][] = {
                    {
                            474, 462
                    }, {
                            300, 462
                    }, {
                            200, 462
                    }, {
                            80, 462
                    }
            };
            path0 = path3;
        } else if (x == ScreenResolutionUtils.PHONE_LT_WIDTH
                && y == ScreenResolutionUtils.PHONE_LT_HEIGHT) {

            // LT
            float path4[][] = {
                    {
                            316, 212
                    }, {
                            216, 212
                    }, {
                            116, 212
                    }, {
                            3, 212
                    }
            };
            path0 = path4;
        }

        setTimeout(6000);
        dragGesture(path0);
        resetTimeout();

    }*/

    /*public void scrollAppPanelRightUPDATE() {

        int[] screenSize = this.getScreenSize();
        int x = screenSize[0];
        int y = screenSize[1];
        Log.i(POWER_TAG, "x == " + x + " ,y== " + y);
        float[][] path0 = scrollAppPanelRightFormula(x, y);
        setTimeout(6000);
        dragGesture(path0);
        resetTimeout();

    }*/

    /*public float[][] scrollAppPanelRightFormula(int width, int height) {

        int x1 = (int)(width * (316.00 / 320));
        int x2 = (int)(width * (216.00 / 320));
        int x3 = (int)(width * (116.00 / 320));
        int x4 = (int)(width * (3.00 / 320));
        int y = (int)(height * (212.00 / 480));

        Log.i(POWER_TAG, "x1的单个值为： == " + x1);
        Log.i(POWER_TAG, "x2的单个值为： == " + x2);
        Log.i(POWER_TAG, "x3的单个值为： == " + x3);
        Log.i(POWER_TAG, "x4的单个值为： == " + x4);
        Log.i(POWER_TAG, "y的单个值为： == " + y);

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

        Log.i(POWER_TAG, "x1 == " + x1 + ", x2 == " + x2 + ", x3== " + x3 + ", y== " + y);
        return path;

    }*/

    /*public void scrollHomeLeft() {

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

        setTimeout(6000);
        dragGesture(path, 30);
        resetTimeout();

    }*/

    static WakeLock wakeLock = null;

    /**
     * this is the method of acquireWakelock and this method can wake lock the
     * info and only handle
     *
     * @author
     * @update this is the update method
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void acquireWakelock() {

        if (wakeLock == null) {
            PowerManager pw = (PowerManager)getInstrumentation().getContext().getSystemService(
                    Context.POWER_SERVICE);

            wakeLock = pw.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP, "PowerTest");
            wakeLock.acquire();

        }
    }*/

    /**
     * this is the method of releaseWakelock and this method release lock
     *
     * @author
     * @update this is the update method
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    public void releaseWakelock() {

        if (wakeLock != null && wakeLock.isHeld()) {

            wakeLock.release();
            wakeLock = null;

        }
    }

    /**
     * this is the method of waitForText and this method waiting for text appear
     *
     *
     * @param text the text of waiting for
     * @param secs waiting for time ,and this time is seconds
     * @param allowFail if TRUE,it is appear the text , if false, it is not
     *            appear the text !
     *
     * @author
     * @update this is the update method
     * @since 2012.04.20
     * @Version ICS,ANDROID 4.0
     *
     * @Phone NoZhongMi
     */
    /*public void waitForText(String text, int secs, boolean allowFail) {

        setTimeout(secs * 1000);

        if (allowFail) {

            isViewWithTextPresent(text);

        } else {

            assertTextPresent(text);
        }
        resetTimeout();
    }*/

    /**
     * this method is about waitForID and waiting for vies's id if has this
     * id,pass,else fail
     *
     * @param id :this is view's id
     * @param secs : waiting for N seconds
     * @param allowFail : if not has this id,fail
     *
     * @author hide
     * @update this is update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitForID(String id, int secs, boolean allowFail) {

        setTimeout(secs * 1000);

        if (allowFail) {

            isViewWithIdPresent(id);

        } else {

            assertViewWithIdPresent(id);
        }
        resetTimeout();
    }*/

    /*public void waitForMyID(String id, int secs) {

        long afterTime = this.getCurrTime() + secs * 1000L;
        boolean isFindViewId = false;
        while (this.getCurrTime() < afterTime) {

            this.wait(1);
            if (isViewWithIdPresent(id)) {
                isFindViewId = !isFindViewId;
                break;
            }
            this.wait(1);
        }

        if (!isFindViewId) {
            assertTrue(false);
        }

    }*/

    /**
     * this method is selectListItem
     *
     * @param text : which text will be selected
     * @param directionKeycode :the keycode for direction
     * @param scrollTime : scroll time
     * @param listLength : the list's length
     *
     * @author hide
     * @update this is update method
     * @Version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void selectListItem(String text, int directionKeycode, int scrollTime, int listLength) {

        int flag = 0, i = 0;
        while (flag == 0 && i < scrollTime) {

            if (isViewWithTextPresent(text)) {
                click(text);
                flag = 1;

            } else {
                i++;
                for (int j = 0; j < listLength; j++) {
                    sendKeys(directionKeycode);
                }
            }
        }
        assertTrue(flag == 1);
    }*/

    /*public void selectListItemOnlyWidgets(String text, int directionKeycode, int scrollTime,
            int listLength) {

        int flag = 0, i = 0;
        while (flag == 0 && i < scrollTime) {

            if (isViewWithTextPresent(text)) {
                // this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN,
                // 15);
                this.pressKey(KeyEvent.KEYCODE_ENTER);
                flag = 1;

            } else {
                i++;
                for (int j = 0; j < listLength; j++) {
                    sendKeys(directionKeycode);
                }
            }
        }
        assertTrue(flag == 1);
    }*/

    /**
     * this method is findListItem
     *
     *
     * @param text :this is will be found text
     * @param directionKeycode :KeyCode for direction
     * @param scrollTime :scroll time
     * @param listLength : the list length
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void findListItem(String text, int directionKeycode, int scrollTime, int listLength) {

        int flag = 0, i = 0;

        while (flag == 0 && i < scrollTime) {

            if (isViewWithTextPresent(text)) {
                flag = 1;

            } else {
                i++;
                for (int j = 0; j < listLength; j++) {
                    sendKeys(directionKeycode);
                }
            }
        }
        assertTrue(flag == 1);
    }*/

    /**
     * this method is getNetworkType
     *
     *
     * @return Integer : return the Integer type
     * @author
     * @since 2012.04.20
     *
     * @version ICS,ANDROID 4.0
     * @Phone NoZhongMi
     *
     */
    /*public int getNetworkType() {

        TelephonyManager manager = (TelephonyManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        return manager.getNetworkType();

    }*/

    /**
     * this method is getCurrentTime
     *
     * @return long : return long type for currentTime
     *
     * @author
     * @update this is update method
     * @Version ICS,ANDROID
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    public long getCurrTime() {

        return System.currentTimeMillis();

    }

    /**
     * this is the method of and it can switch to 2G,3G,4G....
     *
     * @param networkType "GSM only"/"WCDMA only"/"LTE only"/"WCDMA preferred",
     *            etc
     * @author
     * @update this is update method
     * @Version ICS,ANDROID
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */

    /*public void switchNetworkType(String networkType) {

        int i;

        Log.d(POWER_TAG, "----Try to switch network to: " + networkType);

        backOutToHomeScreen();
        // launchAppByManual("Phone");
        this.wait(3);
        if (isViewWithTextPresent("Phone")) {

            this.click("Phone");
        } else {
            this.launchAppByManual("Phone");
        }
        wait(1);
        this.clickInNumber("*#*#4636#*#*");
        this.wait(1);

        click("Phone information");
        this.wait(1);
        for (int j = 0; j < 4; j++) {
            if (!this.isViewWithIdPresent("preferredNetworkType")) {
                scrollDown();
                this.wait(2);
            } else {
                break;
            }

        }

        this.wait(1);
        clickId("preferredNetworkType");
        wait(1);

        this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_UP, 15);
        this.wait(3);
        for (i = 0; i <= 4; i++) {
            if (isViewWithTextPresent(networkType)) {
                Log.e("POWER_TEST", networkType);
                click(networkType);
                break;
            } else {
                this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 6);
            }
        }
        wait(10);
        this.assertTextPresent(networkType);

    }*/

    /**
     * this is the method for clickInNumber, and it is format the number
     *
     * @param number : the waiting format number
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void clickInNumber(String number) {

        int length = number.length();
        char c;
        String s;

        for (int i = 0; i < length; i++) {

            c = number.charAt(i);
            s = String.valueOf(c);
            this.clickItemWithText(s);

        }
    }*/

    /**
     * this is the method of is3gStatus and this method can judge if or not 3G
     * status if TRUE: this is the 3g status if false: this is not the 3g status
     *
     *
     * @author
     * @update this is the update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public boolean is3gStatus() {

        boolean flag3g = false;

        TelephonyManager manager = (TelephonyManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        int networkType = manager.getNetworkType();
        Log.d(POWER_TAG, "----hqx: " + networkType);
        if (networkType == NetWorkStatusUtils.NETWORK_STATUS_3G
                || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_2
                || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_3
                || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_4
                || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_5) {
            flag3g = true;
        }

        return flag3g;
    }*/

    /*public boolean checkStatus(int netstatus) {
        boolean result = false;
        TelephonyManager manager = (TelephonyManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = manager.getNetworkType();
        Log.e("network", "networkType is" + networkType);
        switch (netstatus) {
            case 0:
                if (networkType == NetWorkStatusUtils.NETWORK_STATUS_2G
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_2G_2) {
                    result = true;
                }
                break;

            case 1:
                if (networkType == NetWorkStatusUtils.NETWORK_STATUS_3G
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_2
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_3
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_4
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_5) {
                    result = true;
                }
                break;

            case 2:
                if (networkType == NetWorkStatusUtils.NETWORK_STATUS_4G) {
                    result = true;
                }
                break;
            case 3:
                if (networkType == NetWorkStatusUtils.NETWORK_STATUS_2G
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_2G_2) {
                    result = true;
                }
                break;
            case 4:
                if (networkType == NetWorkStatusUtils.NETWORK_STATUS_3G
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_2
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_3
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_4
                        || networkType == NetWorkStatusUtils.NETWORK_STATUS_3G_5) {
                    result = true;
                }
                break;

        }

        return result;

    }*/

    /**
     * this is the method of is2gStatus and this method can judge if or not 2G
     * status if TRUE: this is the 2g status if false: this is not the 2g status
     *
     *
     * @author
     * @update this is the update method
     * @Version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public boolean is2gStatus() {

        boolean flag3g = false;
        TelephonyManager manager = (TelephonyManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        int networkType = manager.getNetworkType();
        Log.d(POWER_TAG, "----hqx 2G: " + networkType);
        if (networkType == NetWorkStatusUtils.NETWORK_STATUS_2G
                || networkType == NetWorkStatusUtils.NETWORK_STATUS_2G_2) {
            flag3g = true;
        }

        return flag3g;
    }*/

    /**
     *
     * this is the method of is4gStatus and this method can judge if or not 4G
     * status if TRUE: this is the 4g status if false: this is not the 4g status
     *
     *
     * @author
     * @VERSION ICS,ANDROID 4.0
     * @update this is the update method
     * @since 2012.04.20
     *
     * @Phone NoZhongMI
     *
     */
    /*public boolean is4gStatus() {

        boolean flag3g = false;

        TelephonyManager manager = (TelephonyManager)this.getInstrumentation().getTargetContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        int networkType = manager.getNetworkType();
        if (networkType == NetWorkStatusUtils.NETWORK_STATUS_4G) {

            flag3g = true;
        }

        return flag3g;
    }*/

    /**
     * this is the method of WifiStatus and this method can judge if or not
     * WifiStatus if TRUE: this is the WifiStatus if false: this is not the
     * WifiStatus
     *
     *
     * @author
     * @update this is the update method
     * @VERSION ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    // public boolean isWifiStatus() {
    //
    // // ConnectivityManager conn =
    // // (ConnectivityManager)this.getInstrumentation()
    // // .getTargetContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    // // NetworkInfo[] info;
    // //
    // // if (conn != null) {
    // // info = conn.getAllNetworkInfo();
    // // if (info != null) {
    // // for (int i = 0; i < info.length; i++) {
    // // if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
    // //
    // // return true;
    // // }
    // // }
    // // }
    // // }
    // // return false;
    // this.backOutToHomeScreen();
    // this.wait(2);
    // this.launchApp("com.android.settings", "com.android.settings.Settings");
    // // this.launchAppByManual("Settings");
    // this.wait(4);
    // if (this.isViewWithTextPresent("Wi-Fi")) {
    // this.click("Wi-Fi");
    // } else {
    // this.click("WLAN");
    // }
    // if (isViewWithTextPresent("To see available networks, turn Wi-Fi on.")
    // || isViewWithTextPresent("To see available networks, turn on WLAN.")) {
    // // this.backOutToHomeScreen();
    // return false;
    // }
    // // this.backOutToHomeScreen();
    // return true;
    // }

    /*public boolean isWifiStatus() {

        // ConnectivityManager conn =

        // (ConnectivityManager)this.getInstrumentation()

        // .getTargetContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // NetworkInfo[] info;

        //

        // if (conn != null) {

        // info = conn.getAllNetworkInfo();

        // if (info != null) {

        // for (int i = 0; i < info.length; i++) {

        // if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {

        //

        // return true;

        // }

        // }

        // }

        // }

        // return false;

        this.backOutToHomeScreen();

        this.wait(2);

        this.launchApp("com.android.settings", "com.android.settings.Settings");

        // this.launchAppByManual("Settings");

        this.wait(4);

        if (this.isViewWithTextPresent("Wi-Fi")) {

            this.click("Wi-Fi");

        } else {

            this.click("WLAN");

        }

        if (isViewWithTextPresent("To see available networks, turn Wi-Fi on.")

        || isViewWithTextPresent("To see available networks, turn on WLAN.")
                || isViewWithIdPresent("empty")) {

            // if (isViewWithIdPresent("empty")) {

            // this.backOutToHomeScreen();

            return false;

        }

        // this.backOutToHomeScreen();

        return true;

    }*/

    /**
     * this is the method for addWidgets2Desktop
     *
     * @param widgetsName : the widgets name
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void addWidgets2Desktop(String widgetsName) {

        backOutToHomeScreen();
        Log.d(POWER_TAG, "------------------Add widgets " + widgetsName);
        pressKey(KeyEvent.KEYCODE_MENU);
        clickItemWithText("Add");
        clickItemWithText("Widgets");
        findListItem(widgetsName, KeyEvent.KEYCODE_DPAD_DOWN, 5, 7);
    }*/

    /**
     * this is wait method
     *
     * @param secs : the time of seconds
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void wait(int secs) {

        sleep(secs * 1000);
    }*/

    /**
     * this method is wait for mill
     *
     * @param secs
     *
     * @author
     * @update this is update method
     * @Version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     *
     */
    /*public void waitForMill(int secs) {

        sleep(secs);
    }*/

    /**
     * this method is setAlarm
     *
     * @param alarmTime : this is alarm time
     *
     * @author
     * @update this is update method
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void setAlarm(int alarmTime) {

        Context context = getInstrumentation().getContext();
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());

        Log.d(POWER_TAG, "------------------Set an Alarm after: " + alarmTime);
        am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis() + alarmTime * 1000, pi);

    }*/

    /**
     * this method is cancelAlarm
     *
     * @author
     * @update this is update method
     * @version ICS,ANDROID 4.0
     * @Phone NoZhongMi
     * @since 2012.03.26
     *
     */
    /*public void cancelAlarm() {

        Log.d(POWER_TAG, "------------------Cancel the Alarm");

        Context context = getInstrumentation().getContext();
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);

        Intent alarmService = new Intent(context, AlarmService.class);
        context.stopService(alarmService);

    }*/

    /**
     * this method is pressKeyForMultipleTimes
     *
     * @param keyCode : the code of the key
     * @param pressTime : the time of the press
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void pressKeyForMultipleTimes(int keyCode, int pressTime) {

        for (int i = 0; i < pressTime; i++) {

            pressKey(keyCode);
        }
    }*/

    /**
     * this is the method for longPressKey
     *
     * @param keyCode
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void longPressKey(int keyCode) {
        KeyEvent keyDwn = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
        getInstrumentation().sendKeySync(keyDwn);
        wait(1);
    }*/

    /**
     *
     * this is the method for waitForWebpageBeingLoaded
     *
     * @param secs : waiting for this seconds
     * @param allowFail : the parameter of the fail values
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @sine 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitForWebpageBeingLoaded(int secs, boolean allowFail) {

        this.wait(10);
        pressKey(KeyEvent.KEYCODE_MENU);
        waitForText("Refresh", secs, allowFail);
        pressKey(KeyEvent.KEYCODE_BACK);
    }*/

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
    /*public void enterTextSlowly(String text) {

        int length = text.length();
        char c;
        String s;

        for (int i = 0; i < length; i++) {
            c = text.charAt(i);
            s = String.valueOf(c);
            this.enterText(s);
        }
    }*/

    /**
     * this is the method for unlockScreenACA
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @sine 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void unlockScreen() {

        Log.d(POWER_TAG, "------------------Unlock screen");
        int[] screenSize = this.getScreenSize();
        int x = screenSize[0];
        int y = screenSize[1];
        Log.i(POWER_TAG, "xxx == " + x + " ,yyyy== " + y);

        float[][] path0 = null;
        if (x == ScreenResolutionUtils.PHONE_MINT_WIDTH
                && y == ScreenResolutionUtils.PHONE_MINT_HEIGHT) {

            // MINT ## OB ## MI ## HYBS
            float path1[][] = {
                    {
                            93, 1110
                    }, {
                            250, 1110
                    }, {
                            628, 1110
                    }
            };
            path0 = path1;
        } else if (x == ScreenResolutionUtils.PHONE_NP_WIDTH
                && y == ScreenResolutionUtils.PHONE_NP_HEIGHT) {

            // NP
            float path2[][] = {
                    {
                            70, 834
                    }, {
                            180, 834
                    }, {
                            470, 834
                    }
            };
            path0 = path2;
        } else if (x == ScreenResolutionUtils.PHONE_KQ_PP_WIDTH
                && y == ScreenResolutionUtils.PHONE_KQ_PP_HEIGHT) {

            // KQ ## PP
            float path3[][] = {
                    {
                            64, 719
                    }, {
                            180, 719
                    }, {
                            470, 719
                    }
            };
            path0 = path3;
        } else if (x == ScreenResolutionUtils.PHONE_LT_WIDTH
                && y == ScreenResolutionUtils.PHONE_LT_HEIGHT) {

            // LT
            float path4[][] = {
                    {
                            42, 399
                    }, {
                            180, 399
                    }, {
                            280, 399
                    }
            };
            path0 = path4;
        }

        setTimeout(6000);
        dragGesture(path0);
        resetTimeout();
    }*/

    /*public void unlockScreenUPDATE() {

        Log.d(POWER_TAG, "------------------Unlock screen");
        int[] screenSize = this.getScreenSize();
        int x = screenSize[0];
        int y = screenSize[1];
        Log.i(POWER_TAG, "xxx == " + x + " ,yyyy== " + y);

        // float[][] path0 = null;
        // if( x == ScreenResolutionUtils.PHONE_MINT_WIDTH && y==
        // ScreenResolutionUtils.PHONE_MINT_HEIGHT ){
        //
        // //MINT ## OB ## MI ## HYBS
        // float path1[][] = {{93,1110}, {250,1110},{628, 1110}};
        // path0 = path1;
        // }else if(x == ScreenResolutionUtils.PHONE_NP_WIDTH && y==
        // ScreenResolutionUtils.PHONE_NP_HEIGHT){
        //
        // //NP
        // float path2[][] = {{70,834}, {180,834},{470, 834}};
        // path0 = path2;
        // }else if(x == ScreenResolutionUtils.PHONE_KQ_PP_WIDTH && y==
        // ScreenResolutionUtils.PHONE_KQ_PP_HEIGHT){
        //
        // // KQ ## PP
        // float path3[][] = {{64,719}, {180,719},{470, 719}};
        // path0 = path3;
        // }else if(x == ScreenResolutionUtils.PHONE_LT_WIDTH && y==
        // ScreenResolutionUtils.PHONE_LT_HEIGHT){
        //
        // LT
        // path0 = path4;
        // }

        float path0[][] = unlockScreenFormula(x, y);
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
    }*/

    public float[][] unlockScreenFormula(int width, int height) {

        int x1 = (int)(width * (42.00 / 320));
        int x2 = (int)(width * (180.00 / 320));
        int x3 = (int)(width * (280.00 / 320));
        int y = (int)(height * (399.00 / 480));

        Log.i(POWER_TAG, "x1的单个值为： == " + x1);
        Log.i(POWER_TAG, "x2的单个值为： == " + x2);
        Log.i(POWER_TAG, "x3的单个值为： == " + x3);
        Log.i(POWER_TAG, "y的单个值为： == " + y);

        float path[][] = {
                {
                        x1, y
                }, {
                        x2, y
                }, {
                        x3, y
                }
        };

        Log.i(POWER_TAG, "x1 == " + x1 + ", x2 == " + x2 + ", x3== " + x3 + ", y== " + y);
        return path;
    }

    /**
     *
     * this is the method for wakeScreen
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void wakeScreen() {

        acquireWakelock();

        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        releaseWakelock();
        wait(1);
    }*/

    /**
     * this is the method for backToHome and so ...
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     * @Phone NoZhongMi
     *
     */
    /*public void backToHome() {

        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.pressKey(KeyEvent.KEYCODE_HOME);
    }*/

    /**
     * this is the method for measurePowerInSleepMode
     *
     * @param secs
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     * @Phone NoZhongMi
     *
     */
    /*public void measurePowerInSleepMode(int secs) {

        Log.d(POWER_TAG, "------------------Wait till screen enter lock state");
        wait(30);
        setAlarm(secs);
        wait(5);

        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        cancelAlarm();
    }*/

    /**
     * this is the method for setDisplayTimeout
     *
     * @param timeoutItem : the time out time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     * @Phone NoZhongMi
     *
     */
    // public void setDisplayTimeout(String timeoutItem) {
    //
    // launchApp("com.android.settings", "com.android.settings.Settings");
    // wait(5);
    // click("Display");
    // wait(2);
    // click("Screen timeout");
    // click(timeoutItem);
    // }

    /**
     * this is the method for waitTillSleepMode2
     *
     * @param secs : the sleep time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitTillSleepMode2(int secs) {

        if (secs <= 30) {

            secs = 40;
        }

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);

        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs - 3);
        wait(3);

        while (true) {
            wait(1);
            flag = pm.isScreenOn();
            if (flag) {
                break;
            }
        }
        wait(1);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        wait(1);

        cancelAlarm();
    }*/

    /**
     * this is the method for waitTillSleepMode3
     *
     * @param secs : the sleep time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitTillSleepMode3(int secs) {

        this.wait(5);
        this.wait(5);

        if (secs <= 30) {
            secs = 40;
        }
        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs - 3);
        wait(3);

        while (true) {
            wait(1);
            flag = pm.isScreenOn();
            if (flag) {
                break;
            }
        }
        wait(1);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        wait(1);

        cancelAlarm();
    }*/

    /**
     * this is the method for waitTillStandBy
     *
     * @param secs
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitTillStandBy(int secs) {

        if (secs < 40) {
            secs = 40;
        }
        setAlarm(secs);
        this.pressKey(KeyEvent.KEYCODE_POWER);
        wait(40);
        this.wakeScreen();
        cancelAlarm();

    }*/

    /**
     * this is the method for waitTillSleepMode
     *
     * @param secs
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitTillSleepMode(int secs) {

        if (secs < 30) {
            secs = 40;
        }
        wait(30);
        setAlarm(secs - 30);
        wait(10);

        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        cancelAlarm();
    }

    public void playVideo(int leftDown, int rightDown) {

        this.launchAppByManual("Movies");
        this.wait(3);
        if (isViewWithTextPresent("Accept") || isViewWithTextPresent("Decline")) {
            this.click("Decline");
        }
        // 1,0
        // 1,1
        // 1,2
        //
        // 2,o
        pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, leftDown);
        this.wait(1);

        // if((leftDown >1 && rightDown ==0) ||(leftDown==1 && rightDown>=1)){

        this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_RIGHT, rightDown);
        this.wait(1);
        // }
        pressKey(KeyEvent.KEYCODE_DPAD_CENTER);

        this.wait(2);
        this.clickId("details_play");
        this.wait(2);
        if (isViewWithTextPresent("OK") || isViewWithTextPresent("Cannot play video.")) {
            this.click("OK");
            this.backOutToHomeScreen();
            assertTrue(false);
        }
    }*/

    /*public void startPlayVideo(String videoName) {

        int i;
        this.backToHome();
        this.launchAppByManual("Movies");
        this.wait(3);
        if (isViewWithTextPresent("Accept") || isViewWithTextPresent("Decline")) {
            this.click("Decline");
            this.wait(3);
        }
        if (this.isViewWithIdPresent("button2")) {
            this.clickId("button2");
        }

        if (isViewWithTextPresent("Movies")) {
            this.clickPoint(this.getX(100, 540), this.getY(480, 960));

        }
        this.wait(1);
        for (i = 0; i < 3; i++) {
            if (videoName.equals("0008 video")) {
                if (isViewWithTextPresent("0008 video For high decoder")) {
                    this.click("0008 video For high decoder");
                    break;
                } else if (isViewWithTextPresent("0008 video For low decoder")) {
                    this.click("0008 video For low decoder");
                    break;
                }
            } else if (videoName.equals("0001 video")) {
                if (isViewWithTextPresent("0001 video For high decoder")) {
                    this.click("0001 video For high decoder");
                    break;
                } else if (isViewWithTextPresent("0001 video For low decoder")) {
                    this.click("0001 video For low decoder");
                    break;
                }
            } else if (videoName.equals("0009 video")) {
                if (isViewWithTextPresent("0009 video For high decoder")) {
                    this.click("0009 video For high decoder");
                    break;
                } else if (isViewWithTextPresent("0009 video For low decoder")) {
                    this.click("0009 video For low decoder");
                    break;
                }
            }
            if (isViewWithTextPresent(videoName)) {
                this.click(videoName);
                break;
            }
            this.scrollDown();
            this.wait(3);

        }

        this.wait(2);
        this.clickId("details_play");
        this.wait(3);
        if (isViewWithTextPresent("OK") || isViewWithTextPresent("Cannot play video.")) {
            this.click("OK");
            this.wait(2);
            this.backOutToHomeScreen();
            assertTrue(false);
        }
    }

    public void deleteVideo(String videoName) {

        int i;
        this.backToHome();
        this.launchAppByManual("Movies");
        this.wait(3);
        if (isViewWithTextPresent("Accept") || isViewWithTextPresent("Decline")) {
            this.click("Decline");
            this.wait(3);
        }

        if (isViewWithTextPresent("Movies")) {
            this.clickPoint(getX(130), getY(740));

        }
        this.wait(1);
        for (i = 0; i < 3; i++) {
            if (videoName.equals("0001 video")) {
                if (isViewWithTextPresent("0001 video For high decoder")) {
                    // this.click("0001 video For high decoder");
                    this.longPressItemWithText("0001 video For high decoder");
                    this.wait(3);
                    if (isViewWithTextPresent("Delete")) {
                        this.click("Delete");
                        this.wait(3);
                        if (isViewWithTextPresent("Delete")) {
                            this.click("Delete");
                            this.wait(3);
                        }
                    }
                    break;
                } else if (isViewWithTextPresent("0001 video For low decoder")) {
                    // this.click("0001 video For low decoder");
                    this.longPressItemWithText("0001 video For high decoder");
                    this.wait(3);
                    if (isViewWithTextPresent("Delete")) {
                        this.click("Delete");
                        this.wait(3);
                        if (isViewWithTextPresent("Delete")) {
                            this.click("Delete");
                            this.wait(3);
                        }
                    }
                    break;
                }
            }
            if (isViewWithTextPresent(videoName)) {
                // this.click(videoName);
                this.longPressItemWithText(videoName);
                this.wait(3);
                if (isViewWithTextPresent("Delete")) {
                    this.click("Delete");
                    this.wait(3);
                    if (isViewWithTextPresent("Delete")) {
                        this.click("Delete");
                        this.wait(3);
                    }
                }
                break;
            }
            this.scrollDown();
            this.wait(3);

        }

        this.wait(3);

        this.backOutToHomeScreen();
        // this.clickId("details_play");
        // this.wait(2);
        // if(isViewWithTextPresent("OK") ||
        // isViewWithTextPresent("Cannot play video.")){
        // this.click("OK");
        // this.backOutToHomeScreen();
        // assertTrue(false);
        // }
    }*/

    /**
     * update this method,and set quickly
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @Phone NoZhongMi
     * @since 2012.04.20
     *
     */
    /*public void setScreenTimeOut(String listItem) {

        // this.launchAppByManual("Settings");
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        // this.launchAppBySearch("Settings");
        this.wait(5);
        if (this.isViewWithTextPresent("Display")) {
            this.clickItemWithText("Display");
        } else {
            this.backOutToHomeScreen();
            this.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(5);
            this.clickItemWithText("Display");
        }
        this.wait(2);
        if (isViewWithTextPresent("Sleep")) {
            this.clickItemWithText("Sleep");
            if (this.isViewWithTextPresent(listItem)) {
                this.clickItemWithText(listItem);
                Log.d(POWER_TAG, "Set screen time out is " + listItem);
            }
        } else {
            this.backOutToHomeScreen();
            this.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(4);
            this.clickItemWithText("Display");
            this.wait(2);
            this.clickItemWithText("Sleep");
            this.wait(2);
            this.clickItemWithText(listItem);
            Log.d(POWER_TAG, "Set screen time out is " + listItem);
        }
    }

    public void setScreenTimeOutserver(String listItem) {

        this.launchAppBySearch("Settings");
        // this.launchApp("com.android.settings",
        // "com.android.settings.Settings");
        this.wait(4);
        if (this.isViewWithTextPresent("Display")) {
            this.clickItemWithText("Display");
        } else {
            this.backOutToHomeScreen();
            this.launchAppBySearch("Settings");
            this.wait(4);
            this.clickItemWithText("Display");
        }
        this.wait(2);
        if (isViewWithTextPresent("Sleep")) {
            this.clickItemWithText("Sleep");
            if (this.isViewWithTextPresent(listItem)) {
                this.clickItemWithText(listItem);
            }
        } else {
            this.backOutToHomeScreen();
            this.launchAppBySearch("Settings");
            this.wait(4);
            this.clickItemWithText("Display");
            this.wait(2);
            this.clickItemWithText("Sleep");
            this.wait(2);
            this.clickItemWithText(listItem);
        }
    }*/


    /**
     * call phone
     *
     * @since 2012.11.28
     *
     * @param phoneNumber : the phone number
     */
    /*public void callOut(String callNumber) {
        this.acquireWakelock();
        this.backOutToHomeScreen();
        // this.launchAppByManual("Phone");
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.waitForIdViewCompoment("digits", 10);
        this.wait(2);
        this.clickId("digits");
        this.enterTextSlowly(callNumber);
        this.wait(1);
        this.pressKey(KeyEvent.KEYCODE_CALL);
        this.wait(20);
        *//** init scream on *//*
        this.initScreanOnSpee();
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.wait(3);
        if (this.isViewWithIdPresent("digits")) {

            this.clickId("digits");
            this.enterTextSlowly(callNumber);
            this.wait(1);
            this.pressKey(KeyEvent.KEYCODE_CALL);
            this.wait(20);
            *//** init scream on *//*
            this.initScreanOnSpee();
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            this.wait(3);
            if (this.isViewWithIdPresent("digits")) {
                this.backOutToHomeScreen();
                assertTrue(false);
            }
        }

        this.releaseWakelock();
    }
    public void callOutforDS(String callNumber, int SIM) {
        this.acquireWakelock();
        this.backOutToHomeScreen();
        // this.launchAppByManual("Phone");
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.waitForIdViewCompoment("digits", 10);
        this.wait(2);
        this.clickId("digits");
        this.enterTextSlowly(callNumber);
        this.wait(1);
        if (SIM == 1) {
            this.clickId("call_button1");
        } else if (SIM == 2) {
            this.clickId("call_button2");
        }
        this.wait(20);
        /*public void callOut(String callNumber) {
        this.acquireWakelock();
        this.backOutToHomeScreen();
        // this.launchAppByManual("Phone");
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.waitForIdViewCompoment("digits", 10);
        this.wait(2);
        this.clickId("digits");
        this.enterTextSlowly(callNumber);
        this.wait(1);
        this.pressKey(KeyEvent.KEYCODE_CALL);
        this.wait(20);
        *//** init scream on *//*
        this.initScreanOnSpee();
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.wait(3);
        if (this.isViewWithIdPresent("digits")) {
            /** call server phone again *//*
            // this.backOutToHomeScreen();
            // this.launchApp("com.sonyericsson.android.socialphonebook",
            // "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            // // this.launchAppByManual("Phone");
            // this.waitForIdViewCompoment("digits", 10);
            // this.wait(2);
            this.clickId("digits");
            this.enterTextSlowly(callNumber);
            this.wait(1);
            this.pressKey(KeyEvent.KEYCODE_CALL);
            this.wait(20);
            *//** init scream on *//*
            this.initScreanOnSpee();
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            this.wait(3);
            if (this.isViewWithIdPresent("digits")) {
                this.backOutToHomeScreen();
                assertTrue(false);
            }
        }

        this.releaseWakelock();
    }
    /*public void callOutforDS(String callNumber, int SIM) {
        this.acquireWakelock();
        this.backOutToHomeScreen();
        // this.launchAppByManual("Phone");
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.waitForIdViewCompoment("digits", 10);
        this.wait(2);
        this.clickId("digits");
        this.enterTextSlowly(callNumber);
        this.wait(1);
        if (SIM == 1) {
            this.clickId("call_button1");
        } else if (SIM == 2) {
            this.clickId("call_button2");
        }
        this.wait(20);
        *//** init scream on *//*
        this.initScreanOnSpee();
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.wait(3);
        if (this.isViewWithIdPresent("digits")) {
            *//** call server phone again *//*
            this.clickId("digits");
            this.enterTextSlowly(callNumber);
            this.wait(1);
            if (SIM == 1) {
                this.clickId("call_button1");
            } else if (SIM == 2) {
                this.clickId("call_button2");
            }
            this.wait(20);
            *//** init scream on *//*
            this.initScreanOnSpee();
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            this.wait(3);
            if (this.isViewWithIdPresent("digits")) {
                this.backOutToHomeScreen();
                assertTrue(false);
            }
        }
        this.releaseWakelock();
        this.wait(60);
        if (this.isStayCalling1()) {
            this.pressKey(KeyEvent.KEYCODE_ENDCALL);
        } else {
            Log.e(POWER_TAG, "The call from SIM" + SIM + " is termainated unexpectly!");
            this.assertTrue("The call from SIM" + SIM + " is termainated unexpectly!", false);
        }
    }*/

    /*public void sendSMSforDS(String callNumber, int SIM) {
        this.backOutToHomeScreen();
        this.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        this.wait(4);
        this.clickId("menu_new_conversation");
        this.wait(2);
        this.clickId("recipients_editor");
        this.enterText(callNumber);
        this.wait(1);
        this.clickId("conversation_edit_text");
        this.wait(1);
        this.enterText(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
        if (SIM == 1) {
            this.clickId("conversation_send_button1");
        } else if (SIM == 2) {
            this.clickId("conversation_send_button2");
        }
        for (int j = 0; j < 20; j++) {
            if (!this.isViewWithTextPresent("Sending")
                    || !this.isViewWithTextPresent("Queued for sending")) {
                if (this.isViewWithTextPresent("Could not send")) {
                    Log.d(POWER_TAG, "SMS sent unsuccessfully");
                    this.assertTrue("SMS sent from SIM" + SIM + " is unsuccessfully", false);

                } else {
                    this.wait(2);
                    this.assertTextNotPresent("Sending");
                    Log.d(POWER_TAG, "SMS sent from SIM" + SIM + " is successfully");
                    break;
                }
            } else if (j == 19 && this.isViewWithTextPresent("Queued for sending")) {
                Log.e(POWER_TAG, "Waiting for MMS send is time out!!!");
                this.assertTrue("Waiting for MMS send from SIM" + SIM + " is time out!!!", false);
            } else {
                this.wait(6);
            }
        }
    }* init scream on *//*
        this.initScreanOnSpee();
        this.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
        this.wait(3);
        if (this.isViewWithIdPresent("digits")) {
            *//** call server phone again *//*
            this.clickId("digits");
            this.enterTextSlowly(callNumber);
            this.wait(1);
            if (SIM == 1) {
                this.clickId("call_button1");
            } else if (SIM == 2) {
                this.clickId("call_button2");
            }
            this.wait(20);
            *//** init scream on *//*
            this.initScreanOnSpee();
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            this.wait(3);
            if (this.isViewWithIdPresent("digits")) {
                this.backOutToHomeScreen();
                assertTrue(false);
            }
        }
        this.releaseWakelock();
        this.wait(60);
        if (this.isStayCalling1()) {
            this.pressKey(KeyEvent.KEYCODE_ENDCALL);
        } else {
            Log.e(POWER_TAG, "The call from SIM" + SIM + " is termainated unexpectly!");
            this.assertTrue("The call from SIM" + SIM + " is termainated unexpectly!", false);
        }
    }*/

    /*public void sendSMSforDS(String callNumber, int SIM) {
        this.backOutToHomeScreen();
        this.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        this.wait(4);
        this.clickId("menu_new_conversation");
        this.wait(2);
        this.clickId("recipients_editor");
        this.enterText(callNumber);
        this.wait(1);
        this.clickId("conversation_edit_text");
        this.wait(1);
        this.enterText(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
        if (SIM == 1) {
            this.clickId("conversation_send_button1");
        } else if (SIM == 2) {
            this.clickId("conversation_send_button2");
        }
        for (int j = 0; j < 20; j++) {
            if (!this.isViewWithTextPresent("Sending")
                    || !this.isViewWithTextPresent("Queued for sending")) {
                if (this.isViewWithTextPresent("Could not send")) {
                    Log.d(POWER_TAG, "SMS sent unsuccessfully");
                    this.assertTrue("SMS sent from SIM" + SIM + " is unsuccessfully", false);

                } else {
                    this.wait(2);
                    this.assertTextNotPresent("Sending");
                    Log.d(POWER_TAG, "SMS sent from SIM" + SIM + " is successfully");
                    break;
                }
            } else if (j == 19 && this.isViewWithTextPresent("Queued for sending")) {
                Log.e(POWER_TAG, "Waiting for MMS send is time out!!!");
                this.assertTrue("Waiting for MMS send from SIM" + SIM + " is time out!!!", false);
            } else {
                this.wait(6);
            }
        }
    }*/

    /*public void sendMMSforDS(String callNumber, int SIM) {
        this.backOutToHomeScreen();
        this.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        this.wait(4);
        this.clickId("menu_new_conversation");
        this.wait(2);
        this.clickId("recipients_editor");
        this.enterText(callNumber);
        this.wait(1);
        this.clickId("conversation_edit_text");
        this.wait(1);
        this.enterText(CommandConstantsUtils.SMS_CONTENT_RECEIVE);
        this.wait(1);
        this.clickId("conversation_tool_button");
        this.wait(1);
        this.click("Add picture");
        this.wait(1);
        if (this.isViewWithIdPresent("alertTitle")) {
            this.click("Album");
            this.wait(1);
            if (this.isViewWithTextPresent("Just once")) {
                this.click("Just once");
            }

        } else if (this.isViewWithTextPresent("Album")) {
            this.click("Album");
            this.wait(1);
        } else if (!this.isViewWithTextPresent("Album") && this.isViewWithTextPresent("Recent")) {
            this.clickId("up");
            this.wait(1);
            this.click("Album");
            this.wait(1);
        }
        this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 1);
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_DPAD_CENTER);
        this.wait(2);
        if (this.isViewWithIdPresent("conversation_tool_button")) {
            Log.d(POWER_TAG, "Insert Picture successfully!");
        } else {
            this.clickPoint(this.getX(140, 720), this.getY(299, 1208));
            this.wait(2);
            Log.d(POWER_TAG, "Insert Picture successfully!");
        }
        if (SIM == 1) {
            this.clickId("conversation_send_button1");
        } else if (SIM == 2) {
            this.clickId("conversation_send_button2");
        }
        for (int j = 0; j < 20; j++) {
            if (!this.isViewWithTextPresent("Sending")
                    || !this.isViewWithTextPresent("Queued for sending")) {
                if (this.isViewWithTextPresent("Could not send")) {
                    Log.d(POWER_TAG, "MMS sent unsuccessfully");
                    this.assertTrue("MMS sent from SIM" + SIM + " is unsuccessfully", false);

                } else {
                    this.wait(2);
                    this.assertTextNotPresent("Sending");
                    Log.d(POWER_TAG, "MMS sent from SIM" + SIM + " is successfully");
                    break;
                }
            } else if (j == 19 && this.isViewWithTextPresent("Queued for sending")) {
                Log.e(POWER_TAG, "Waiting for MMS send is time out!!!");
                this.assertTrue("Waiting for MMS send from SIM" + SIM + " is time out!!!", false);
            } else {
                this.wait(6);
            }
        }
    }
    public void switchdatatraffic(int SIM) {
        this.backOutToHomeScreen();
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        this.scrollFindText("Dual SIM settings");
        this.wait(2);
        this.click("SIM card for data traffic");
        this.wait(1);
        if (SIM == 1) {
            this.click("SIM1");
            Log.d(POWER_TAG, "Change data traffic to SIM1");
            if (this.isViewWithIdPresent("button1")) {
                this.clickId("button1");
            }
        } else if (SIM == 2) {
            this.click("SIM2");
            Log.d(POWER_TAG, "Change data traffic to SIM2");
            if (this.isViewWithIdPresent("button1")) {
                this.clickId("button1");
            }
        }
        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.wait(1);
        this.scrollFindText("About phone");
        this.wait(1);
        this.click("Status");
        this.wait(2);
        this.click("General");
        this.waitForTextViewCompoment("Connected", 200);
        Log.d(POWER_TAG, "Change data traffic Successfully");
    }*/

    /*public void browsingWeb() throws IOException {
        this.launchApp("com.android.browser", "com.android.browser.BrowserActivity");

        this.waitForIdViewCompoment("url", 60);
        if (this.isViewWithIdPresent("url")) {
            this.clickId("url");
            this.enterTextSlowly("www.yahoo.com");
            this.pressKey(KeyEvent.KEYCODE_ENTER);
        }
        this.waitForWebpageBeingLoaded2(120, false);
        Log.d(POWER_TAG, "www.yahoo.com" + "is opened!");
        this.getCurrTime();
        long intervalTime = this.getCurrTime() + 1 * 60 * 1000L;
        while (this.getCurrTime() < intervalTime) {
            this.wait(3);
            this.scrollDown();
            this.wait(3);
            this.scrollDown();
            this.wait(3);
            this.scrollUp();
            this.wait(3);
            this.scrollUp();
        }

    }

    public void isStayCalling() {
        this.wait(2);
        // if (this.isViewWithTextPresent("digits") ||
        // this.isViewWithIdPresent("apptray_pane_view")
        // || this.isViewWithIdPresent("apptray_button")) {
        if (this.isViewWithIdPresent("call_button")) {
            this.backOutToHomeScreen();
            this.wait(2);
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            if (!isViewWithTextPresent("End call")) {
                Log.e(POWER_TAG, "The call is termainal unexcepted");
                assertTrue(false);
            }

        }

    }*/

    /*public boolean isStayCalling1() {
        boolean iscalling = true;
        this.wait(2);

        if (this.isViewWithIdPresent("call_button")) {
            this.backOutToHomeScreen();
            this.wait(2);
            this.launchApp("com.sonyericsson.android.socialphonebook",
                    "com.sonyericsson.android.socialphonebook.DialerEntryActivity");
            if (!isViewWithTextPresent("End call")) {
                Log.e(POWER_TAG, "The call is termainal unexcepted");
                iscalling = false;
            }
        }
        return iscalling;

    }*/

    /**
     *
     * Using url downLoad file
     *
     * @String : http://www.gte.nu/homes/cnl850490/30M
     * @return if or not download success
     * @throws IOException
     */
    /*public void downloadFileWithUrl(String downloadUrl) throws IOException {
        boolean isSuccess = false;
        ChromeModule chromeAppService = new ChromeModule(this);
        // chromeAppService.launchChromeByMual();
        this.launchApp("com.android.chrome", "com.google.android.apps.chrome.Main");
        this.wait(5);
        if (this.isViewWithTextPresent("Accept and Continue")) {
            this.click("Accept and Continue");
            this.wait(2);
            this.click("No thanks");
        }
        // chromeAppService.enterUrl("http://spe.mobilephone.net/wit/commonv1/linkvideo.xhtml");
        chromeAppService.waitForWebpage("http://spe.mobilephone.net/wit/commonv1/linkvideo.xhtml",
                60);
        this.wait(4);
        chromeAppService.enterUrl(downloadUrl);
        if (this.isViewWithIdPresent("parentPanel")) {
            this.click("Chrome");
            this.wait(1);
            this.clickId("button_always");
        }
        this.wait(5);

        if (!this.isViewWithIdPresent("url_action_button") && this.isViewWithTextPresent("New tab")) {
            this.click("New tab");
        }
        this.waitForIdViewCompoment("url_action_button", 100);
        this.wait(8);
        this.openStatusBar();
        for (int i = 0; i < 30; i++) {
            this.wait(5);
            // 30M.txt
            // 150MB.zip
            // this.isViewWithTextPresent("Download complete");
            if (this.isViewWithTextPresent("Download complete.")) {
                this.click("Download complete.");
                this.wait(3);
                isSuccess = !isSuccess;
                break;
            }
        }
        this.backOutToHomeScreen();
        if (!isSuccess) {
            this.destroyResource();
            assertTrue(false);
        }
    }

    public void downloadFileWithUrl2(String downloadUrl) throws IOException {
        boolean isSuccess = false;
        this.launchApp("com.android.browser", "com.android.browser.BrowserActivity");
        this.wait(5);
        this.waitForIdViewCompoment("url", 10);
        this.clickId("url");
        // if (this.isViewWithTextPresent("Accept and Continue")) {
        // this.click("Accept and Continue");
        // this.wait(2);
        // this.click("No thanks");
        // }
        this.enterText("http://spe.mobilephone.net/wit/commonv1/linkvideo.xhtml");
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.waitForWebpageBeingLoaded2(100, true);
        this.clickId("url");
        this.enterText(downloadUrl);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(5);
        // if (!this.isViewWithIdPresent("url_action_button") &&
        // this.isViewWithTextPresent("New tab")){
        // this.click("New tab");
        // }
        // this.waitForIdViewCompoment("url_action_button", 100);
        this.waitForWebpageBeingLoaded2(100, false);
        this.wait(8);
        this.openStatusBar();
        for (int i = 0; i < 30; i++) {
            this.wait(10);
            // 30M.txt
            // 150MB.zip
            // this.isViewWithTextPresent("Download complete");
            if (this.isViewWithTextPresent("Download complete.")) {
                this.click("Download complete.");
                this.wait(2);
                isSuccess = !isSuccess;
                break;
            }
        }
        this.backOutToHomeScreen();
        if (!isSuccess) {
            destroyResource();
            assertTrue(false);
        }
    }*/

    /*public void OpenUrlviaChrome(String Url) {

        ChromeModule chromeAppService = new ChromeModule(this);

        this.launchApp("com.android.chrome", "com.google.android.apps.chrome.Main");
        this.wait(5);
        if (this.isViewWithTextPresent("Accept and Continue")) {
            this.click("Accept and Continue");
            this.wait(2);
            this.click("No thanks");
        }
        chromeAppService.enterUrl(Url);
        this.wait(5);
        this.waitForIdViewCompoment("url_action_button", 100);
        this.wait(2);

    }

    *//**
     *
     * Using url downLoad file
     *
     * @String : http://www.gte.nu/homes/cnl850490/30M
     * @return if or not download success
     *//*
    public void downloadFileWithUrlCDMA(String downloadUrl) {
        boolean isSuccess = false;
        ChromeModule chromeAppService = new ChromeModule(this);
        chromeAppService.launchChromeByMual("Internet");
        this.waitForIdViewCompoment("search_src_text", 60);
        this.clickId("search_src_text");
        this.enterText(downloadUrl);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(5);
        this.waitForIdViewCompoment("url_action_button", 100);
        this.wait(8);
        this.openStatusBar();
        for (int i = 0; i < 30; i++) {
            this.wait(3);
            // 30M.txt
            // 150MB.zip
            if (this.isViewWithTextPresent("778MB.txt")) {
                isSuccess = !isSuccess;
                break;
            }
        }
        this.backOutToHomeScreen();
        if (!isSuccess) {
            assertTrue(false);
        }
    }*/

    /**
     *
     * Using url downLoad file
     *
     * @String : http://www.gte.nu/homes/cnl850490/30M
     * @return if or not download success
     */
    /*public void downloadFileWithChrome(String downloadUrl) {
        boolean isSuccess = false;
        ChromeModule chromeAppService = new ChromeModule(this);
        this.launchAppBySearch("Chrome");
        this.waitForIdViewCompoment("url_bar", 60);
        this.clickId("url_bar");
        this.enterText(downloadUrl);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(5);
        this.waitForIdViewCompoment("url_action_button", 100);
        this.wait(8);
        this.openStatusBar();
        for (int i = 0; i < 30; i++) {
            this.wait(3);
            // 30M.txt
            // 150MB.zip
            if (this.isViewWithTextPresent("Download complete.")) {
                isSuccess = !isSuccess;
                break;
            }
        }
        this.click("Clear");
        this.wait(2);
        this.backOutToHomeScreen();
        if (!isSuccess) {
            assertTrue(false);
        }
    }

    public void deleteDownloadFiles() {
        this.backOutToHomeScreen();
        // this.launchAppByManual("Downloads");
        this.launchApp("com.android.providers.downloads.ui",
                "com.android.providers.downloads.ui.DownloadList");
        this.wait(4);
        if (this.isViewWithIdPresent("icon_mime")) {
            boolean isFind = true;
            int count = 0;
            Log.e(POWER_TAG, "check download files icon");
            while (isFind || count < 2) {
                this.wait(2);
                if (isViewWithIdPresent("icon_mime")) {
                    Log.e(POWER_TAG, "Start to delete download files");
                    this.longPressItemWithId("icon_mime");
                    this.wait(1);
                    this.clickItemWithId("menu_delete");
                }
                this.wait(2);
                if (!isViewWithIdPresent("icon_mime")) {
                    isFind = !isFind;
                }
                count++;
            }
        }

        this.assertViewWithIdNotPresent("title");

    }

    private int getXXX(int _pointX) {
        int x = (int)(this.getScreenSize()[0] * (_pointX / 720.0));

        return x;
    }

    private int getYYY(int _pointY) {
        int y = (int)(this.getScreenSize()[0] * (_pointY / 1184.0));
        return y;
    }

    *//**
     *
     * delete account, if it exist .
     *
     * @param accountName
     *//*
    public void cancelemailAccount(String accountName) {
        this.backToHome();
        // this.launchAppByManual("Settings");
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        int i = 0;
        while (i < 5) {
            // if (this.isViewWithTextPresent("Corporate")) {
            // this.click("Corporate");
            if (this.isViewWithTextPresent("Email")) {
                this.click("Email");
                this.wait(2);
                this.click(accountName);
                this.wait(2);
                this.pressKey(KeyEvent.KEYCODE_MENU);
                this.waitForTextViewCompoment("Remove account", 4);
                this.click("Remove account");
                this.waitForTextViewCompoment("Remove account", 4);
                this.click("Remove account");
                this.wait(5);
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }*/

    /*public void cancelEASAccount(String accountName) {
        this.backToHome();
        // this.launchAppByManual("Settings");
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        int i = 0;
        while (i < 5) {
            // if (this.isViewWithTextPresent("Corporate")) {
            // this.click("Corporate");
            if (this.isViewWithTextPresent("Exchange ActiveSync")
                    || this.isViewWithTextPresent("Corporate")) {
                if (this.isViewWithTextPresent("Exchange ActiveSync")) {
                    this.click("Exchange ActiveSync");
                } else if (this.isViewWithTextPresent("Corporate")) {
                    this.click("Corporate");
                }
                this.wait(2);
                this.click(accountName);
                this.wait(2);
                this.pressKey(KeyEvent.KEYCODE_MENU);
                this.waitForTextViewCompoment("Remove account", 4);
                this.click("Remove account");
                this.waitForTextViewCompoment("Remove account", 4);
                this.click("Remove account");
                this.wait(5);
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }

    *//**
     *
     * delete FaceBook account
     *
     * @sine 2012.11.26
     *//*
    public void deleteFacebookAccount() {

        // this.launchAppByManual("Settings");
        this.launchAppBySearch("Settings");
        this.waitForTextViewCompoment("Bluetooth", 8);
        *//** to scroll "about phone" view, but not click this view *//*
        this.scrollFindTextNotClick("About phone");

        this.wait(1);
        if (this.isViewWithTextPresent("Facebook")) {
            this.click("Facebook");
            this.waitForTextViewCompoment("Sync", 4);
            this.pressKey(KeyEvent.KEYCODE_MENU);
            this.waitForTextViewCompoment("Remove account", 4);
            this.click("Remove account");
            this.waitForTextViewCompoment("Remove account", 4);
            this.click("Remove account");
            this.wait(5);
        }
        this.backOutToHomeScreen();
        Log.d(POWER_TAG, "Facebook account is deleted");
    }*/

    /**
     *
     * delete google talk account
     *
     * @sine 2012.11.26
     */
    /*public void deleteGoogleAccount(String gtalkName) {
        this.backOutToHomeScreen();
        // this.launchAppByManual("Settings");
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.waitForTextViewCompoment("Bluetooth", 8);
        *//** to scroll "about phone" view, but not click this view *//*
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.scrollFindTextNotClick("About phone");

        this.wait(1);
        if (this.isViewWithTextPresent("Google")) {
            this.click("Google");
            this.waitForTextViewCompoment(gtalkName, 8);
            this.click(gtalkName);
            this.waitForTextViewCompoment("Sync", 4);
            this.pressKey(KeyEvent.KEYCODE_MENU);
            this.waitForTextViewCompoment("Remove account", 4);
            this.click("Remove account");
            this.waitForTextViewCompoment("Remove account", 4);
            this.click("Remove account");
            this.wait(5);
        }
        this.backOutToHomeScreen();
    }

    public void scrollFindText(String text) {

        for (int i = 0; i < 5; i++) {
            this.wait(1);

            if (isViewWithTextPresent(text)) {
                break;
            } else if (!isViewWithTextPresent(text)) {
                this.scrollDown();
            }
            this.wait(1);
        }
        this.click(text);
    }*/

    /**
     * scrool to find current view if or not exits "text view"
     *
     * @param text
     */
    /*public void scrollFindTextNotClick(String text) {

        for (int i = 0; i < 5; i++) {
            this.wait(1);
            if (isViewWithTextPresent(text)) {
                break;
            } else if (!isViewWithTextPresent(text)) {
                this.scrollDown();
            }
        }
    }

    public boolean scroolFindTextWithMusicItem(String text) {

        boolean isFind = false;
        for (int i = 0; i < 4; i++) {
            this.wait(1);

            if (isViewWithTextPresent(text)) {
                isFind = !isFind;
                break;
            } else if (!isViewWithTextPresent(text)) {
                this.scrollDown();
            }
        }

        return isFind;
    }

    *//**
     * this is the method for waitShortTillSleepMode
     *
     * @param secs : the sleep time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     *//*
    public void waitShortTillSleepMode(int secs) {

        if (secs < 35) {
            secs = 35;
        }
        setAlarm(secs);
        wait(5);
        wait(5);
        wait(10);
        wait(5);
        wait(5);
        wait(5);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        cancelAlarm();
    }*/

    /**
     *
     * this is the method for waitTillSleepModePressPower, and this is sleep by
     * press power
     *
     * @param secs : the sleep time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitTillSleepModePressPower(int secs) {

        if (secs <= 30) {
            secs = 40;
        }

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs - 3);
        wait(3);

        while (true) {
            wait(1);
            flag = pm.isScreenOn();
            if (flag) {
                break;
            }
        }
        wait(1);
        if (isViewWithTextPresent("Swipe to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked1");
        } else if (isViewWithTextPresent("Swipe up or down to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked2");
        } else {
            Log.d(POWER_TAG, "Phone already unlocked");
        }
        wait(1);

        cancelAlarm();
    }

    // calling
    public void waitTillCallSpeed(int secs) {
        // if(secs<35){
        // secs=35;
        // }
        // setAlarm(secs);
        // wait(35);
        // if(isViewWithIdPresent("lock_screen")){
        // unlockScreenUPDATE();
        // }
        // cancelAlarm();

        if (secs < 30) {
            secs = 30;
        }
        setAlarm(secs);
        wait(secs);
        this.wait(10);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreenUPDATE();
        }
        cancelAlarm();
    }*/

    // calling 2
    /*public void waitTillCallSpeedService(int secs) {
        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);

        // this.pressKey(KeyEvent.KEYCODE_POWER);

        if (secs < 30) {
            secs = 30;
        }
        setAlarm(secs + 3);
        Log.i("test", "2.after: setAlarm(secs +10);");
        Log.i("test", "3.before: this.wait(secs);");
        // this.wait(secs);
        pm.goToSleep(secs * 1000L);
        this.wait(10);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreenUPDATE();
        }
        cancelAlarm();
    }

    // music/FM
    public void waitTillMusFmSpeed(int secs) {

        // ////
        this.wait(5);
        // this.gotoSleep();

        // setAlarm(secs);
        setAlarm(secs - 4);
        wait(secs);
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenForUGA();
        } else {
            this.unlockScreenForUGA();
        }
        cancelAlarm();
    }

    public void waitTillSpeed_UPDATE(int secs) {

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs);

        for (int i = 0; i < 10; i++) {
            this.wait(2);
            flag = pm.isScreenOn();
            if (flag) {
                break;
            }
        }
        this.wait(2);
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenUPDATE();
            this.wait(1);
        }
        cancelAlarm();
    }*/

    /**
     * services
     *
     * @param secs
     */
    /*public void waitTillSpeedServices(int secs) {

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);

        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        Log.i("test", "1.before: setAlarm(secs +10);");
        // setAlarm(secs +20);
        // Log.i("test", "2.after: setAlarm(secs +10);");
        // Log.i("test", "3.before: this.wait(secs);");
        // this.wait(secs);
        // Log.i("test", "4.after: this.wait(secs);");
        //
        // int count = 0;
        // while(true){
        // this.wait(5);
        // count ++;
        // flag = pm.isScreenOn();
        // if(flag){
        // Log.i("test", "5.  count =="+ count +"  , flag == " +flag);
        // break;
        // }
        //
        // }
        // Log.i("test", "6.  count =="+ count);
        // this.wait(2);
        // Log.i("test", "7.before: if(isViewWithIdPresent(\"lock_screen\")){");
        // if(isViewWithIdPresent("lock_screen")){
        // Log.i("test", "8.into: this.unlockScreenUPDATE();");
        // this.unlockScreenUPDATE();
        // Log.i("test", "9.get out: this.unlockScreenUPDATE();");
        // }
        // Log.i("test", "10.after: if(isViewWithIdPresent(\"lock_screen\")){");
        // Log.i("test", "11. before:  cancelAlarm();");
        // cancelAlarm();
        // Log.i("test", "12. atter:  cancelAlarm();");

        Log.i("test", "1.before: setAlarm(secs +10);");
        setAlarm(secs);// before 20
        Log.i("test", "2.after: setAlarm(secs +10);");
        Log.i("test", "3.before: this.wait(secs);");
        // this.wait(10);
        pm.goToSleep(secs * 1000L);
        Log.i("test", "4.after: this.wait(secs);");
        int count = 0;
        while (true) {
            Log.i("test", "444444.xxxxxxxxxxxxxxxxx");
            count++;
            flag = pm.isScreenOn();
            if (flag) {
                Log.i("test", "5.  count ==" + count + "  , flag == " + flag);
                break;
            }

        }
        Log.i("test", "6.  count ==" + count);
        this.wait(2);
        Log.i("test", "7.before: if(isViewWithIdPresent(\"lock_screen\")){");
        if (isViewWithIdPresent("lock_screen")) {
            Log.i("test", "8.into: this.unlockScreenUPDATE();");
            this.unlockScreenForUGA();
            Log.i("test", "9.get out: this.unlockScreenUPDATE();");
        } else {
            this.unlockScreenForUGA();
        }
        Log.i("test", "10.after: if(isViewWithIdPresent(\"lock_screen\")){");
        Log.i("test", "11. before:  cancelAlarm();");
        cancelAlarm();
        Log.i("test", "12. atter:  cancelAlarm();");

    }

    *//**
     *
     * using wait, it will 2 wait time.
     *
     * @param secs
     * @throws IOException
     *//*
    public void waitTill(int secs) throws IOException {
        this.wait(5);
        // this.gotoSleep();
        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);

        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs);
        long time2 = getCurrTime();
        waitlogger.logRowInfo("waitcase" + waitLogID);
        waitLogID++;
        waitlogger.logTime(getCurrTime());
        this.wait(25);
        waitlogger.logTime(getCurrTime());
        long time3 = getCurrTime();
        long sleepTime = (time3 - time2) / 1000;
        waitlogger.logColumnInfo("sleepTime:" + sleepTime);
        if (sleepTime < secs - 2) {
            waitlogger.logTime(getCurrTime());
            this.wait(secs - (int)sleepTime);
            waitlogger.logTime(getCurrTime());
            while (true) {
                flag = pm.isScreenOn();
                if (flag) {
                    break;
                }
            }
            waitlogger.logTime(getCurrTime());
        }
        // Log.e("123", "now!2");
        // // this.wait(secs);
        // Log.e("123", "now!3");
        // while(true){
        // flag = pm.isScreenOn();
        // if(flag){
        // break;
        // }
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenForUGA();
        } else {
            this.unlockScreenForUGA();
        }
        cancelAlarm();
    }*/

    /**
     *
     * using wait, it will 2 wait time.
     *
     * @param secs
     * @throws IOException
     */
    /*public void waitTillForNetwork(int secs) throws IOException {
        this.wait(5);
        // this.gotoSleep();
        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);

        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs);
        // waitlogger.logRowInfo("waitcase" + waitLogID);
        // waitlogger.logTime(getCurrTime());
        long time2 = getCurrTime();
        this.wait(25);
        long time3 = getCurrTime();
        // waitlogger.logTime(getCurrTime());
        long sleepTime = (time3 - time2) / 1000;
        // waitlogger.logColumnInfo("sleepTime:" + sleepTime);
        // waitlogger.logTime(getCurrTime());
        while (true) {
            if (sleepTime >= (secs - 2)) {
                break;
            } else {
                time2 = getCurrTime();
                this.wait(16);
                time3 = getCurrTime();
                sleepTime += (time3 - time2) / 1000;
                // waitlogger.logColumnInfo("sleepTime:" + sleepTime);
            }
        }
        // if (isViewWithIdPresent("lock_screen")) {
        if (isViewWithTextPresent("Swipe to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked");
        } else if (isViewWithTextPresent("Swipe up or down to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked");
        } else {
            // this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is already unlocked");
        }
        cancelAlarm();
    }*/

    public boolean is2time2(long currentTime2, long currentTime3, long comparTimeSec) {
        boolean flag = false;

        if ((currentTime2 + comparTimeSec * 1000L + 2 * 60 * 1000L) < currentTime3) {
            flag = !flag;
        }

        return flag;
    }

    /**
     * (Interval == JianGe): waitTillSpeedInterval
     *
     * @param secs
     */

    /*public void waitTillSpeedInterval2(int secs) {

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        // this is "after Guoq" update
        try {
            setAlarm(secs);
            pm.goToSleep(secs);
            // Log.i("test", "5555: into try ");
            while (true) {
                flag = pm.isScreenOn();
                if (flag) {
                    break;
                }
            }
            // Log.i("test", "6666: get out while try ");
            // waitForMill(500);
            // if(isViewWithIdPresent("lock_screen")){
            // this.unlockScreenUPDATE();
            // }

            // this.unlockScreenForUGA();
            // waitForMill(500);

        } catch (Exception e) {

        } finally {

            cancelAlarm();
            this.stayAwake();
            this.unlockScreenForUGA();

            Log.i("test", "7777: getout finally  ");
            this.backOutToHomeScreen();
        }
    }

    *//**
     * interval time is 60 sec .
     *
     * @param secs
     * @time
     *//*
    public void waitTillSpeedInterval(int secs) {

        this.releaseWakelock();
        this.wait(5);
        // this.gotoSleep();
        setAlarm(secs);
        wait(secs);
        // if (isViewWithIdPresent("lock_screen")) {
        if (isViewWithIdPresent("Swipe up or down to unlock")) {
            this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is unlocked");
        } else {
            // this.unlockScreenForUGA();
            Log.d(POWER_TAG, "Phone is already unlocked");
        }
        cancelAlarm();
    }*/

    /*public void waitTillSpeedIntervalDD(int secs) {

        if (secs < 30) {
            secs = 40;
        }
        this.wait(30);
        setAlarm(secs - 30);
        wait(10);
        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenForUGA();
        } else {
            this.unlockScreenForUGA();
        }
        cancelAlarm();
    }

    public void waitTillSpeedIntervalCCC(int secs) {

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);

        try {
            setAlarm(secs);
            while (true) {
                flag = pm.isScreenOn();
                if (flag) {
                    break;
                }
            }
            this.waitForMill(1000);
            if (isViewWithTextPresent("Swipe to unlock")) {
                this.unlockScreenForUGA();
                Log.d(POWER_TAG, "Phone is unlocked1");
            } else if (isViewWithTextPresent("Swipe up or down to unlock")) {
                this.unlockScreenForUGA();
                Log.d(POWER_TAG, "Phone is unlocked2");
            } else {
                Log.d(POWER_TAG, "Phone already unlocked");
            }
            this.waitForMill(500);

        } catch (Exception e) {

        } finally {

            this.cancelAlarm();
            this.backOutToHomeScreen();
        }
    }*/

    /*public void reset_Data_Connection() {
        this.launchAppByManual("Settings");
        this.clickListItem("Data usage");
        this.wait(1);
        if (this.isViewWithIdPresent("widget_frame") && (!isCheckboxChecked("widget_frame", 0))) {
            clickItemWithId("widget_frame");
        }
        this.wait(1);

        this.backOutToHomeScreen();

    }

    public void set_Data_Connection() {
        this.launchAppByManual("Settings");
        this.clickListItem("Data usage");
        this.wait(1);
        if (this.isViewWithIdPresent("widget_frame") && (isCheckboxChecked("widget_frame", 0))) {
            clickItemWithId("widget_frame");
        }
        this.wait(1);
        if (isViewWithTextPresent("Attention") || isViewWithTextPresent("Yes")
                || isViewWithTextPresent("No")) {
            this.click("Yes");
            this.wait(2);
        }
        this.backOutToHomeScreen();

    }*/

    /*public void waitTillSpeed(int secs) {

        PowerManager pm = (PowerManager)getInstrumentation().getContext().getSystemService(
                Context.POWER_SERVICE);
        boolean flag;
        this.pressKey(KeyEvent.KEYCODE_POWER);
        setAlarm(secs);
        while (true) {
            flag = pm.isScreenOn();
            if (flag) {
                break;
            }
        }
        waitForMill(500);
        if (isViewWithIdPresent("lock_screen")) {
            // unlockScreen();
            this.unlockScreenUPDATE();
        }

        cancelAlarm();
    }

    // sleep model 2g/3g/flight model
    public void waitTillOnlySleepSpeed(int secs) {

        if (secs < 30) {
            secs = 40;
        }
        wait(30);
        setAlarm(secs - 30);
        this.wait(secs - 30);
        wait(3);

        if (isViewWithIdPresent("lock_screen")) {
            this.unlockScreenUPDATE();
        } else {
            this.wait(4);
            if (isViewWithIdPresent("lock_screen")) {
                this.unlockScreenUPDATE();
            } else {

                this.waitTillSpeed_UPDATE(1);
            }
        }
        cancelAlarm();
    }*/

    // public void waitTillSpeed(int secs){
    //
    // PowerManager pm =
    // (PowerManager)getInstrumentation().getContext().getSystemService(
    // Context.POWER_SERVICE);
    // boolean flag;
    // this.pressKey(KeyEvent.KEYCODE_POWER);
    // setAlarm(secs);
    // while(true){
    // flag = pm.isScreenOn();
    // if(flag){
    // break;
    // }
    // }
    // waitForMill(500);
    // if(isViewWithIdPresent("lock_screen")){
    // this.unlockScreenUPDATE();
    // }
    // cancelAlarm();
    // }

    /**
     * this is the method for waitInHalfSleepMode, and this method for music
     * sleep
     *
     * @param secs : the sleep time
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void waitInHalfSleepMode(int secs) {

        setAlarm(secs - 3);
        wait(secs);
        if (isViewWithIdPresent("lock_screen")) {
            unlockScreen();
        }
        cancelAlarm();
    }*/

    /**
     * this is the method for setCameraFlashmode
     *
     * @param flashMode : the flash mode
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void setCameraFlashmode(String flashMode) {

        Log.d(POWER_TAG, "------------------Set Camera flash mode to " + flashMode);
        launchApp("com.sonyericsson.android.camera",
                "com.sonyericsson.android.camera.CameraActivity");
        wait(3);
        pressKey(KeyEvent.KEYCODE_MENU);
        click("Flash");
        click(flashMode);
        pressKey(KeyEvent.KEYCODE_BACK);
    }*/

    /**
     * this is the method for sendSMS
     *
     * @param sendto : send to the number
     * @param content : send to the content
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void sendSMS(final String sendto, final String content) {

        SmsManager iSMSManager = null;
        iSMSManager = SmsManager.getDefault();
        *//**
         *
         // prepare listening intents Intent msgSent = new
         * Intent("ACTION_MSG_SENT"); Intent msgReceipt = new
         * Intent("ACTION_MSG_RECEIPT");
         *
         * final PendingIntent pendingMsgSent =
         * PendingIntent.getBroadcast(context, 0, msgSent, 0); final
         * PendingIntent pendingMsgReceipt = PendingIntent.getBroadcast(context,
         * 0, msgReceipt, 0);
         **//*
        iSMSManager.sendTextMessage(sendto, null, content, null, null);

    }*/

    /**
     * this is the method for connectToWlan
     *
     * @param apName
     * @param apPwd
     *
     */
    /*public void connectToWlan(String apName, String apPwd) {

        launchAppByManual("Settings");
        this.clickItemWithId("title", 0);

        this.wait(3);
        this.clickPoint(660, 100);

        this.wait(7);

        if (isWifiStatus()) {
            return;
        }
        if (isViewWithTextPresent("pbgwpa")) {
            this.clickItemWithText("pbgwpa");
            this.wait(2);
        } else {
            scrollDown();
            this.clickItemWithText("pbgwpa");
        }

        if (this.isViewWithTextPresent("Forget")) {
            click("Cancel");
            wait(10);
        } else {
            if (isViewWithTextPresent("Password")) {
                enterText(apPwd);
            }
            clickItemWithText("Connect");

            wait(10);
            acquireWakelock();
            if (!isWifiStatus()) {
                this.wait(3);
                pressKey(KeyEvent.KEYCODE_VOLUME_UP);
                pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
                this.wait(6);
                if (isWifiStatus()) {
                } else {
                    assertTrue(false);
                }
            }
            releaseWakelock();
        }

    }*/

    /**
     * this is the method for connect2Wlan2
     *
     * @param apName
     * @param apPwd
     *
     */
    /*public void connect2Wlan2(String apName, String apPwd) {

        backOutToHomeScreen();
        launchAppByManual("Settings");
        clickItemWithText("Wireless & networks");

        if (this.isViewWithTextPresent("WLAN settings")) {
            clickItemWithText("WLAN settings");
        } else {
            clickItemWithText("Wi-Fi settings");
        }
        wait(1);

        if (!this.isViewWithTextPresent("Connected to " + apName)) {
            Log.d(POWER_TAG, "------------------Turn on Wifi, then connect to " + apName);
            if (!isCheckboxChecked("checkbox", 0)) {
                clickItemWithId("checkbox", 0);
                wait(10);
            }

            selectListItem(apName, KeyEvent.KEYCODE_DPAD_DOWN, 2, 7);
            if (this.isViewWithTextPresent("Forget")) {
                click("Cancel");
                wait(10);
            } else {
                if (isViewWithTextPresent("Password")) {
                    enterText(apPwd);
                }
                clickItemWithText("Connect");

                acquireWakelock();
                waitForText("Connected to " + apName, 60, false);
                wait(10);
                pressKey(KeyEvent.KEYCODE_BACK);
                releaseWakelock();
            }
        }
    }*/

    /**
     *
     * this is the method for connect2WifiEnable, and it is not want to set the
     * userName and password
     *
     *
     * @author
     * @since 2012.04.20
     * @version ICS,ANDROID 4.0
     *
     * @Phone NoZhongMi
     *
     */
    /*public void ForgetconnectedWifiAP() {

        backOutToHomeScreen();
        launchApp("com.android.settings", "com.android.settings.Settings");

        this.wait(1);
        if (!isCheckboxChecked("switchWidget", 0)) {
            clickItemWithId("switchWidget", 0);
            wait(5);
        }

        this.wait(1);
        clickItemWithText("Wi-Fi");

        wait(20);
        if (!this.isViewWithTextPresent("Connected")) {
            this.wait(1);
        } else if (this.isViewWithTextPresent("Connected")) {

            this.clickItemWithText("Connected");
            this.wait(2);
            if (this.isViewWithTextPresent("Forget")) {
                click("Forget");
                wait(10);
            }

        }
        this.wait(2);
        backOutToHomeScreen();

    }

    *//**
     * this method for connect2Wifi
     *
     * @param apName userName
     * @param apPwd password
     *
     * @Verson ICS
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *//*
    public void connect2Wifi(String apName, String apPwd) {
        WifiManager mWifiManager = null;
        mWifiManager = (WifiManager)getInstrumentation().getContext().getSystemService(
                Context.WIFI_SERVICE);
        // Turn on Wifi
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
        wait(5);
        assertTrue("Turn on Wi-Fi failed!!",mWifiManager.isWifiEnabled());
        this.backOutToHomeScreen();

        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        if (this.isViewWithTextPresent("Wi-Fi")) {
            this.click("Wi-Fi");
        } else {
            this.click("WLAN");
        }
        wait(4);
        if (this.isViewWithTextPresent(apName)) {
            Log.d(POWER_TAG, "AP finded");
        } else {
            for (int i = 0; i < 3; i++) {
                this.pressKey(KeyEvent.KEYCODE_MENU);
                this.waitForTextViewCompoment("Scan", 4);
                this.click("Scan");
                this.wait(10);
                this.scrollFindTextNotClick(apName);
                if (this.isViewWithTextPresent(apName)) {
                    Log.d(POWER_TAG, "AP finded");
                    break;
                }
            }
        }
        this.click(apName);
        this.wait(2);
        if (this.isViewWithTextPresent("Forget")) {
            this.click("Cancel");
        } else if (this.isViewWithTextPresent("Password")) {
            this.enterText(apPwd);
            this.wait(1);
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.waitForTextViewCompoment("Connect", 10);
            this.click("Connect");
            // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_RIGHT);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_CENTER);
            this.wait(2);
        }
        this.waitForTextViewCompoment("Connected", 30);
        Log.d(POWER_TAG, "AP connected");
        this.wait(3);
        this.backOutToHomeScreen();

    }*/

    /*public void connect2Wifi(String apName) {
        WifiManager mWifiManager = null;
        mWifiManager = (WifiManager)getInstrumentation().getContext().getSystemService(
                Context.WIFI_SERVICE);
        // Turn on Wifi
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
        wait(5);
        assertTrue("Turn on Wi-Fi failed!!",mWifiManager.isWifiEnabled());
        this.backOutToHomeScreen();

        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        if (this.isViewWithTextPresent("Wi-Fi")) {
            this.click("Wi-Fi");
        } else {
            this.click("WLAN");
        }
        wait(4);
        if (this.isViewWithTextPresent(apName)) {
            Log.d(POWER_TAG, "AP finded");
        } else {
            for (int i = 0; i < 3; i++) {
                this.pressKey(KeyEvent.KEYCODE_MENU);
                this.waitForTextViewCompoment("Scan", 4);
                this.click("Scan");
                this.wait(10);
                this.scrollFindTextNotClick(apName);
                if (this.isViewWithTextPresent(apName)) {
                    Log.d(POWER_TAG, "AP finded");
                    break;
                }
            }
        }
        this.click(apName);
        this.wait(2);
        if (this.isViewWithTextPresent("Forget")) {
            this.click("Forget");
            this.wait(2);
            this.click(apName);
        }
        if (this.isViewWithTextPresent("Connect")) {

            this.scrollUp();
            this.scrollUp();
            for (int i = 0; i < 5; i++) {
                if (this.isViewWithIdPresent("method_with_somc_additions")) {
                    this.clickId("method_with_somc_additions");
                    this.wait(1);
                    this.click("SIM");
                    break;
                } else {
                    this.scrollUp();
                }
            }
            this.wait(1);
            // this.pressKey(KeyEvent.KEYCODE_BACK);
            this.waitForTextViewCompoment("Connect", 10);
            this.click("Connect");
            // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_RIGHT);
            // this.pressKey(KeyEvent.KEYCODE_DPAD_CENTER);
            this.wait(2);
        }
        this.waitForTextViewCompoment("Connected", 60);
        Log.d(POWER_TAG, "AP connected");
        this.wait(3);
        this.backOutToHomeScreen();

    }

    public void setemailsynctomanual(String accountName) {
        this.backOutToHomeScreen();
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        int i = 0;
        while (i < 5) {
            if (this.isViewWithTextPresent("Email")) {
                this.click("Email");
                this.wait(2);
                this.click("Account settings");
                this.wait(1);
                this.click(accountName);
                this.wait(2);
                this.scrollFindText("Inbox check frequency");
                this.wait(1);
                this.click("Check frequency");
                this.wait(1);
                this.click("Manual");
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }*/

    /*public void setemailsyncto5min(String accountName) {
        this.backOutToHomeScreen();
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        int i = 0;
        while (i < 5) {
            if (this.isViewWithTextPresent("Email")) {
                this.click("Email");
                this.wait(2);
                this.click("Account settings");
                this.wait(1);
                this.click(accountName);
                this.wait(2);
                this.scrollFindText("Inbox check frequency");
                this.wait(1);
                this.click("Check frequency");
                this.wait(1);
                this.click("Every 5 minutes");
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }

    public void manualsyncemail(String accountName) {
        this.backOutToHomeScreen();
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        this.wait(1);
        this.scrollDown();
        int i = 0;
        while (i < 5) {
            if (this.isViewWithTextPresent("Email")) {
                this.click("Email");
                this.wait(2);
                this.click(accountName);
                this.wait(2);
                this.pressKey(KeyEvent.KEYCODE_MENU);
                this.wait(4);
                if (this.isViewWithTextPresent("Cancel sync")) {
                    this.click("Cancel sync");
                    this.wait(2);
                    this.pressKey(KeyEvent.KEYCODE_MENU);
                    this.wait(4);
                }
                this.click("Sync now");
                break;
            } else {
                this.scrollDown();
            }
            i++;
        }
        this.backOutToHomeScreen();
    }

    public void changeEmailCheckFrequency(String emailName, String Frequency) {
        this.backOutToHomeScreen();
        this.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
        this.wait(5);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        this.wait(2);
        this.click("Settings");
        this.wait(2);
        this.clickItemWithText(emailName);
        this.wait(2);
        this.click("Inbox check frequency");
        this.wait(2);
        this.click("Check frequency");
        this.wait(2);
        this.click(Frequency);
        this.wait(2);
        this.assertTextPresent(Frequency);
        this.backOutToHomeScreen();

    }*/

    /*public void connet2WifiSetEnable() {

        // launchAppByManual("Settings");
        //
        // if(!isCheckboxChecked("switchWidget", 0)){
        // clickItemWithId("switchWidget", 0);
        // wait(3);
        // }
        //
        // if(isViewWithTextPresent("Wi-Fi")){
        // clickItemWithText("Wi-Fi");
        // }else if(isViewWithTextPresent("WLAN")){
        // clickItemWithText("WLAN");
        // }
        //
        // wait(5);
        // if(!this.isViewWithTextPresent("Scan")){
        //
        // this.pressKey(KeyEvent.KEYCODE_MENU);
        // this.wait(1);
        // this.click("Scan");
        // }else{
        // this.click("Scan");
        // }
        // this.wait(2);
        // if(!this.isViewWithTextPresent("Scan")){
        //
        // this.pressKey(KeyEvent.KEYCODE_MENU);
        // this.wait(1);
        // this.click("Scan");
        // }else{
        // this.click("Scan");
        // }
        // wait(3);
        //
        // if(this.isViewWithTextPresent("Connected")){
        // selectListItem("Connected", KeyEvent.KEYCODE_DPAD_DOWN,2, 7);
        // this.wait(1);
        // if(this.isViewWithTextPresent("Forget")){
        //
        // click("Forget");
        // wait(5);
        // }
        // }else {
        // this.wait(8);
        //
        // if(this.isViewWithTextPresent("Connected")){
        //
        // selectListItem("Connected", KeyEvent.KEYCODE_DPAD_DOWN,2, 7);
        // this.wait(1);
        // if(this.isViewWithTextPresent("Forget")){
        //
        // click("Forget");
        // wait(5);
        // }
        // }
        // // this.initScreanOnSpee();
        // }

        launchAppByManual("Settings");
        // this.click("Wi-Fi");
        this.waitForIdViewCompoment("switchWidget", 10);
        if (!isCheckboxChecked("switchWidget", 0)) {
            clickItemWithId("switchWidget", 0);
        }
        this.wait(8);
        if (isViewWithTextPresent("Wi-Fi")) {
            clickItemWithText("Wi-Fi");
        } else if (isViewWithTextPresent("WLAN")) {
            clickItemWithText("WLAN");
        }
        this.wait(8);
        if (this.isViewWithTextPresent("Connected")) {
            this.click("Connected");
            this.waitForTextViewCompoment("Forget", 4);
            this.click("Forget");
            this.wait(3);
        }

    }

    public void startMusicStreaming(String streaminglink) {

        this.launchApp("com.android.browser", "com.android.browser.BrowserActivity");
        this.wait(5);
        this.waitForIdViewCompoment("url", 10);
        this.clickId("url");
        this.enterText("http://www.gte.nu/homes/cnl850490/");
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(10);
        this.clickId("url");
        this.enterText(streaminglink);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(2);
        this.waitForTextViewCompoment("WALKMAN", 100);
        this.click("WALKMAN");
        this.wait(2);
        if (this.isViewWithTextPresent("Just once")) {
            this.click("Just once");
        }
        this.wait(5);
    }*/

    /*public void startMusicStreamingviaChmore(String streaminglink) {
        Log.e(POWER_TAG, "startMusicStreamingviaChmore");
        this.launchAppBySearch("Chrome");
        this.wait(5);
        this.wait(3);
        if (this.isViewWithTextPresent("Accept and Continue")) {
            this.click("Accept and Continue");
            this.wait(2);
            this.click("No thanks");
        }
        this.waitForIdViewCompoment("url_bar", 10);
        this.clickId("url_bar");
        this.enterText("http://www.gte.nu/homes/cnl850490/");
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(10);
        this.clickId("url_bar");
        this.enterText(streaminglink);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(2);
        this.waitForTextViewCompoment("WALKMAN", 100);
        this.click("WALKMAN");
        this.wait(2);
        if (this.isViewWithTextPresent("Just once")) {
            this.click("Just once");
        }
        this.wait(5);
    }

    public void stopMusicStreaming() {
        // this.basicUtils.launchAppByManual("WALKMAN");
        // this.launchApp("com.sonyericsson.music",
        // "com.sonyericsson.music.MusicActivity");
        // this.wait(4);
        // for (int i = 0; i < 2;i++) {
        // if (this.isViewWithTextPresent("Songs")){
        // this.click("Songs");
        // this.wait(3);
        // this.click("Abcessnessmess");
        // this.wait(3);
        // this.click("Abcessnessmess");
        // this.wait(2);
        // break;
        // } else if (this.isViewWithIdPresent("miniplayer")) {
        // this.clickId("miniplayer");
        // this.wait(2);
        // break;
        // }
        // }
        // this.clickId("player_next_button");
        // this.wait(2);
        // this.clickId("player_play_pause_button");
        // this.wait(3);
        this.openStatusBar();
        if (this.isViewWithIdPresent("pause")) {
            this.clickId("pause");
        }
        this.wait(2);

    }*/

    /**
     * this is the method for destoryFlag
     *
     */
    /*public void destoryFlag() {

        Context context = this.getInstrumentation().getTargetContext();
        SharedPreferences sp = context.getSharedPreferences("run_flag_file", Context.MODE_PRIVATE);
        Editor editor = sp.edit();

        editor.putInt("test1_makeVoiceCall", 0);
        editor.putInt("test2_receiveVoiceCall", 0);
        editor.putInt("test3_receiveAndReplySMS", 0);
        editor.putInt("test4_receiveAndReplyMMS", 0);
        editor.putInt("test5_receiveAndReplyGmail", 0);
        editor.putInt("test6_takePicture", 0);
        editor.putInt("test7_videoRecording", 0);
        editor.putInt("test8_smallAppAndTools", 0);
        editor.putInt("test9_browseInternet3G", 0);
        editor.putInt("test10_downloadFileOn3G", 0);
        editor.putInt("test11_playbackMusic", 0);
        editor.putInt("test12_playbackVideo", 0);
        editor.putInt("test13_browseInterentWlan", 0);
        editor.putInt("test14_downloadFileOnWlan", 0);
        editor.putInt("test15_FMRadio", 0);
        editor.putInt("test16_googleMap", 0);
        editor.putInt("test17_installAppFromMarket", 0);

        editor.commit();
    }

    *//**
     *
     * this method for turnOffWifi
     *
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.13
     *
     * @Phone NoZhongMi
     *
     *//*
    public void turnOffWifiwithAPname(String APname) {

        // this.backOutToHomeScreen();
        // launchAppByManual("Settings");
        // wait(4);
        //
        // if(isCheckboxChecked("switchWidget", 0)){
        // clickItemWithId("switchWidget", 0);
        // wait(5);
        // }
        // this.backOutToHomeScreen();
        if (!isWifiStatus()) {

        } else {
            // this.backOutToHomeScreen();
            Log.e(POWER_TAG, "turnOffWifi");
            // this.launchApp("com.android.settings",
            // "com.android.settings.Settings");
            // this.wait(4);
            // if (this.isViewWithTextPresent("Wi-Fi")) {
            // this.click("Wi-Fi");
            // } else {
            // this.click("WLAN");
            // }
            if (this.isViewWithTextPresent(APname)) {
                this.click(APname);
                if (this.isViewWithTextPresent("Forget")) {
                    this.click("Forget");
                    this.wait(1);
                    Log.d(POWER_TAG, "Delete connected AP:" + APname);
                    this.pressKey(KeyEvent.KEYCODE_BACK);
                } else {
                    this.click("Cancel");
                    this.wait(1);
                    this.pressKey(KeyEvent.KEYCODE_BACK);
                }
            }
            // launchAppByManual("Settings");
            this.waitForIdViewCompoment("switchWidget", 5);

            clickItemWithId("switchWidget", 0);
            wait(4);

            this.wait(1);
            this.backOutToHomeScreen();
        }
    }*/

    /*public void turnOffWifi() {

        // this.backOutToHomeScreen();
        // launchAppByManual("Settings");
        // wait(4);
        //
        // if(isCheckboxChecked("switchWidget", 0)){
        // clickItemWithId("switchWidget", 0);
        // wait(5);
        // }
        // this.backOutToHomeScreen();
        if (!isWifiStatus()) {
            this.backOutToHomeScreen();
        } else {
            // this.backOutToHomeScreen();
            Log.e(POWER_TAG, "turnOffWifi");
            this.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(4);

            // launchAppByManual("Settings");
            this.waitForIdViewCompoment("switchWidget", 5);

            clickItemWithId("switchWidget", 0);
            wait(4);

            this.wait(1);
            this.backOutToHomeScreen();
        }
    }

    public void resetCapturingMode() {
        this.backOutToHomeScreen();
        this.launchApp("com.sonyericsson.android.camera",
                "com.sonyericsson.android.camera.CameraActivity");
        // this.launchAppByManual("Camera");
        this.wait(5);
        this.clickId("capturing_mode_selector_button");
        this.wait(2);
        float path0[][] = {
                {
                        this.getX(1100, 1794), this.getY(100, 1080)
                }, {
                        this.getX(1500, 1794), this.getY(100, 1080)
                }, {
                        this.getX(1700, 1794), this.getY(100, 1080)
                }
        };
        setTimeout(12000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
        if (this.isViewWithTextPresent("Superior auto")) {

            this.clickItemWithText("Superior auto");
        } else if (this.isViewWithTextPresent("Scene recognition")) {
            this.clickItemWithText("Scene recognition");
        } else if (this.isViewWithTextPresent("Normal")) {
            this.clickItemWithText("Normal");
        } else {
            assertTrue(false);
        }
        this.wait(2);
        this.backOutToHomeScreen();
    }*/

    /**
     *
     * this is the method for searchAndInstallAppFromMarket
     *
     */

    /**
     *
     * public void searchAndInstallAppFromMarket(String appName){
     * Log.d(POWER_TAG, "------------------Search for " + appName +
     * " in Market"); clickId("search_button"); wait(1);
     * enterText(appName.toLowerCase()); clickId("search_go_btn");
     * waitForText(appName, 120, false); click(appName);
     * waitForText("DESCRIPTION", 60, false);
     *
     *
     * if(isViewWithTextPresent("Uninstall")){ Log.d(POWER_TAG,
     * "------------------The app is already installed. Uninstall it");
     * click("Uninstall"); waitForText("Uninstall app", 5, false);
     * clickId("button1"); wait(5); }
     *
     * Log.d(POWER_TAG,
     * "------------------Download and install the application");
     * if(isViewWithTextPresent("Install")){ click("Install"); } else
     * if(isViewWithTextPresent("Download")){ click("Download"); }
     * waitForText("Accept & download", 15, false); click("Accept & download");
     * waitForText("%", 60, true); waitForText("Uninstall", 300, false);
     * wait(5); }
     */

    /**
     * this is the method for uninstallApp
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void uninstallApp(String appName) {

        Log.d(POWER_TAG, "------------------Search for " + appName + " in Market");

        backOutToHomeScreen();
        launchAppByManual("Settings");
        click("Applications");
        click("Manage applications");

        selectListItem(appName, KeyEvent.KEYCODE_DPAD_DOWN, 3, 7);
        click("Uninstall");
        waitForText("Uninstall application?", 5, false);
        clickId("ok_button");
        waitForText("Uninstall finished", 10, false);
        clickId("ok_button");

        backOutToHomeScreen();
    }*/

    /**
     * this is the method for maxOf
     *
     * @param number1 : the first number
     * @param number2 : the second number
     * @return : the max number
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    public int maxOf(int number1, int number2) {

        return ((number1 > number2) ? number1 : number2);
    }

    /**
     * this is the method for clickIdInString
     *
     * @param ids : the id
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void clickIdInString(String... ids) {

        for (String id : ids) {

            clickId(id);
        }
    }*/

    /**
     * this is the method for initTitle
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @throws ParseException
     *
     * @Phone NoZhongMi
     *
     */
    /*public void initTitle() throws ParseException {

        Context context = this.getInstrumentation().getTargetContext();
        SharedPreferences spf = context.getSharedPreferences("power_title_file",
                Context.MODE_PRIVATE);
        int titleKey = spf.getInt("titleKey", Context.MODE_PRIVATE);

        if (titleKey == 0) {

            setTitle();
            Editor editor = spf.edit().putInt("titleKey", 0);
            editor.commit();
        }
    }*/

    /**
     * this is the method for initTitleForScenario
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @throws ParseException : the parse exception
     *
     * @Phone NoZhongMi
     *
     */
    /*public void initTitleForScenario() throws ParseException {

        Context context = this.getInstrumentation().getTargetContext();
        SharedPreferences spf = context.getSharedPreferences("scenario_title_file",
                Context.MODE_PRIVATE);

        int titleKey = spf.getInt("titleKey", Context.MODE_PRIVATE);
        if (titleKey == 1) {

            setTitleForScenario();
            Editor editor = spf.edit().putInt("titleKey", 0);
            editor.commit();
        }
    }
*/
    /**
     * the tile for the powerlog.xml
     *
     * @author
     * @since 2012.04.20
     * @version ICS,ANDROID 4.0
     *
     * @Phone NoZhongMi
     *
     */
    /*public void setTitle() throws ParseException {

        try {

            this.getPhoneInfo();

            logger.logTime2();

            logger.logColumnInfo("" + "\n");
            logger.logInfo(deviceModel);
            logger.logInfo("" + "\r\n");
            logger.logInfo(softwareVersion);
            logger.logInfo("" + "\r\n");
            if (deviceId != null) {
                logger.logInfo(deviceId);
            } else {
                logger.logInfo("none");
            }
            logger.logInfo("" + "\r\n");
//            logger.logInfo(deviceIMEI);
            logger.logInfo("" + "\r\n");
            logger.logInfo("ID");
            logger.logColumnInfo("CaseName");
            logger.logColumnInfo("StartCaseTime");
            // logger.logColumnInfo("MeasureStartTime");
            // logger.logColumnInfo("MeasureEndTime");
            logger.logColumnInfo("EndCaseTime");
            logger.logColumnInfo("Status");

            logger.logColumnInfo("ErrorInfo");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void setwaitTitle() throws ParseException {

        try {

            this.getPhoneInfo();

            logger.logTime2();

            waitlogger.logColumnInfo("" + "\n");
            waitlogger.logInfo(deviceModel);
            waitlogger.logInfo("" + "\r\n");
            waitlogger.logInfo(softwareVersion);
            waitlogger.logInfo("" + "\r\n");
            if (deviceId != null) {
                waitlogger.logInfo(deviceId);
            } else {
                waitlogger.logInfo("none");
            }
            waitlogger.logInfo("" + "\r\n");
            waitlogger.logColumnInfo("CaseName");
            waitlogger.logColumnInfo("casebegin1");
            waitlogger.logColumnInfo("caseend1");
            waitlogger.logColumnInfo("casebegin2");
            waitlogger.logColumnInfo("caseend2");
            waitlogger.logColumnInfo("casebegin3");
            waitlogger.logColumnInfo("caseend3");
            waitlogger.logColumnInfo("casebegin4");
            waitlogger.logColumnInfo("caseend4");
            waitlogger.logColumnInfo("casebegin5");
            waitlogger.logColumnInfo("caseend5");
            waitlogger.logColumnInfo("casebegin6");
            waitlogger.logColumnInfo("caseend6");
            waitlogger.logColumnInfo("casebegin7");
            waitlogger.logColumnInfo("caseend7");
            waitlogger.logColumnInfo("casebegin8");
            waitlogger.logColumnInfo("caseend8");
            waitlogger.logColumnInfo("casebegin9");
            waitlogger.logColumnInfo("caseend9");
            waitlogger.logColumnInfo("casebegin10");
            waitlogger.logColumnInfo("caseend10");
            waitlogger.logColumnInfo("casebegin11");
            waitlogger.logColumnInfo("caseend11");
            waitlogger.logColumnInfo("casebegin12");
            waitlogger.logColumnInfo("caseend12");
            waitlogger.logColumnInfo("casebegin13");
            waitlogger.logColumnInfo("caseend13");
            waitlogger.logColumnInfo("casebegin14");
            waitlogger.logColumnInfo("caseend14");
            waitlogger.logColumnInfo("casebegin15");
            waitlogger.logColumnInfo("caseend15");
            waitlogger.logColumnInfo("casebegin16");
            waitlogger.logColumnInfo("caseend16");
            waitlogger.logColumnInfo("casebegin17");
            waitlogger.logColumnInfo("caseend17");
            waitlogger.logColumnInfo("casebegin18");
            waitlogger.logColumnInfo("caseend18");
            waitlogger.logColumnInfo("casebegin19");
            waitlogger.logColumnInfo("caseend19");
            waitlogger.logColumnInfo("casebegin20");
            waitlogger.logColumnInfo("caseend20");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }*/

    /**
     *
     * this is the method for setTitleForScenario
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @throws ParseException
     *
     * @Phone NoZhongMi
     *
     */
    /*public void setTitleForScenario() throws ParseException {

        this.getPhoneInfo();

        try {

            logger.logInfo("CaseName");

            logger.logColumnInfo("StartCaseTime");
            logger.logColumnInfo("MeasureStartTime");
            logger.logColumnInfo("MeasureEndTime");
            logger.logColumnInfo("EndCaseTime");
            logger.logColumnInfo("Stutus");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }*/

    private String softwareVersion = null;

    public String deviceModel = null;

    private String deviceId = null;

    private String deviceIMEI = null;

    /**
     * this is the method for getPhoneInfo
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     *
     * @Phone NoZhongMi
     *
     */
    /*public void getPhoneInfo() {
//
//        TelephonyManager tm = (TelephonyManager)this.getInstrumentation().getTargetContext()
//                .getSystemService(Context.TELEPHONY_SERVICE);

        deviceModel = Build.DEVICE;
        softwareVersion = Build.DISPLAY;
        // deviceId = tm.getDeviceId();
        deviceId = Build.SERIAL;
//        deviceIMEI = tm.getDeviceId();
    }

    public boolean isInRoaming() {
        this.openStatusBarUpdate();
        Log.e(POWER_TAG, "is in roaming 1");
        this.wait(3);
        if (this.isViewWithTextPresent("Data roaming")) {
            Log.e(POWER_TAG, "is in roaming 2");
            this.pressKey(KeyEvent.KEYCODE_BACK);
            return false;
        }
        Log.e(POWER_TAG, "is in roaming 3");
        this.pressKey(KeyEvent.KEYCODE_BACK);
        Log.e(POWER_TAG, "is in roaming 4");
        return true;
    }

    public boolean isInDatatraffic() {
        this.openStatusBarUpdate();
        this.wait(3);
        if (this.isViewWithTextPresent("Data traffic")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            return false;
        } else if (this.isViewWithTextPresent("Mobile data")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            return false;
        }
        this.pressKey(KeyEvent.KEYCODE_BACK);
        return true;
    }*/

    /*
     * switchs means on off .on is true off is false
     */
    /*public void DatatrafficSwitch(boolean switchs) {

        if (switchs) {
            this.unlockScreenForUGA();
            Log.e("qq", "qq");
            wait(5);
            this.backOutToHomeScreen();
            this.turnOnTraffic2();

        } else {
            this.openStatusBarUpdate();
            if (this.isViewWithTextPresent("Data traffic")) {
                this.backOutToHomeScreen();
            } else if (this.isViewWithTextPresent("Mobile data")) {
                this.backOutToHomeScreen();
            } else {
                this.backOutToHomeScreen();
                this.launchAppByManual("Settings");
                this.click("More…");
                this.wait(1);
                this.clickListItem("Mobile networks");
                this.wait(2);
                if (isCheckboxChecked("checkbox", 0)) {
                    this.clickItemWithId("checkbox", 0);
                    this.wait(1);
                }
                this.backOutToHomeScreen();
            }
        }
        this.backOutToHomeScreen();
        this.wait(3);
    }

    public void DataRoamingSwitch(boolean switchs) {
        this.wait(3);
        this.openStatusBarUpdate();
        this.wait(3);
        if (this.isViewWithTextPresent("Data roaming")) {
            this.click("Data roaming");
            if (switchs) {
                if (!isCheckboxChecked("checkbox", 1)) {
                    this.clickItemWithId("checkbox", 1);
                    this.wait(1);
                    if (isViewWithTextPresent("Attention") || isViewWithTextPresent("Yes")
                            || isViewWithTextPresent("No")) {
                        this.click("Yes");
                        this.wait(2);
                    }
                }

            }
        }
        this.backOutToHomeScreen();
        this.wait(3);
    }

    public void openStatusBarUpdate() {
        float path0[][] = {
                {
                        358, 010
                }, {
                        358, 500
                }, {
                        358, 850
                }
        };
        setTimeout(1000); // "after Guoq update "8000
        dragGesture(path0);
        resetTimeout();
    }*/

    /**
     * this is the method for setAirplaneMode
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMI
     *
     */
    /*public void setAirplaneMode() {

        int i;

        this.launchAppByManual("Settings");
        for (i = 2; i < 9; i++) {
            this.clickItemWithId("title", i);
            if (this.isViewWithTextPresent("VPN")) {
                break;
            }
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(2);
        }

        this.wait(1);
        if (isCheckboxChecked("checkbox", 0)) {

        } else if (!isCheckboxChecked("checkbox", 0)) {
            this.clickItemWithId("checkbox", 0);
            this.wait(5);
        }
        this.wait(1);

    }

    *//**
     * this is the method for cancelAirplaneMode
     *
     * @author
     * @version ICS,ANDROID
     * @since 2012.04.20
     *
     * @Phone NozhongMi
     *
     *//*
    public void cancelAirplaneMode() {

        int i;

        this.launchAppByManual("Settings");
        for (i = 2; i < 9; i++) {
            this.clickItemWithId("title", i);
            if (this.isViewWithTextPresent("VPN")) {
                break;
            }
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(3);
        }

        if (!isCheckboxChecked("checkbox", 0)) {

        } else if (isCheckboxChecked("checkbox", 0)) {
            this.clickItemWithId("checkbox", 0);
            this.wait(10);
        }
        this.wait(1);
    }*/

    /**
     * this is the method for stopPlayMusic
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public void stopPlayMusic() {

        backOutToHomeScreen();
        launchAppByManual("Music player");
        this.wait(1);

        if (isViewWithIdPresent("alertTitle")) {
            clickId("button1");
        }
        this.wait(1);
        clickId("text_right");
        click("Tracks");
        click("Shuffle all");
        this.waitForMill(2000);

        if (this.isViewWithIdPresent("player_play_pause_button")) {
            clickId("player_play_pause_button");
            this.waitForMill(1500);
        }
        backOutToHomeScreen();
    }*/

    /**
     * this is the method for waitForWebpageBeingLoaded2, and it is Refresh 20
     * seconds every times
     *
     * @param times : the refresh times
     * @return: run times
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    /*public int waitForWebpageBeingLoaded2(int times) {

        int tim = 0;

        for (int i = 1; i <= times; i++) {
            tim++;
            this.wait(8);
            pressKey(KeyEvent.KEYCODE_MENU);
            this.wait(1);
            if (isViewWithTextPresent("Refresh")) {
                this.wait(1);
                pressKey(KeyEvent.KEYCODE_BACK);
                break;
            } else {
                this.wait(1);
                pressKey(KeyEvent.KEYCODE_BACK);
                this.wait(1);
            }
        }
        return tim;
    }*/

    /**
     *
     * @param secs
     * @param allowFail
     * @throws IOException
     *
     */
    /*public void waitForWebpageBeingLoaded2(int secs, boolean allowFail) throws IOException {
        // wait(5);
        boolean right = false;
        long endTime = System.currentTimeMillis() + secs * 1000;
        this.wait(10);
        while (System.currentTimeMillis() < endTime) {
            this.pressKey(KeyEvent.KEYCODE_MENU);

            if (this.isViewWithTextPresent("Refresh")) {
                this.pressKey(KeyEvent.KEYCODE_BACK);
                Log.d(POWER_TAG, "The page is opened successful.");
                right = true;
                break;
            }
            this.pressKey(KeyEvent.KEYCODE_BACK);
        }
        if (!allowFail) {
            if (!right) {
                Log.e(POWER_TAG, "The page is not opened successful.");
            }
            destroyResource();
            assertEquals(true, right);
            // Log.e(POWER_TAG, "The page is not opened successful.");
            // Log.d(POWER_TAG, "The page is opened successful.");
        }

    }*/

    /**
     *
     * this is the method for setMinuteTime
     *
     *
     * @return long : the time for mm
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.26
     *
     * @Phone NoZhongMi
     *
     */

    public long setMinuteTime(int minute) {

        return minute * 60 * 1000L;
    }

    /*public void turnOffDataTraffic() {

        this.launchAppByManual("Settings");
        this.clickItemWithId("title", 3);
        this.click("Mobile networks");
        this.wait(1);
        if (isCheckboxChecked("checkbox", 0)) {
            this.clickItemWithId("checkbox", 0);
            this.wait(2);
        }
    }

    public void turnOnDataTraffic() {

        this.launchAppByManual("Settings");
        this.clickListItem("Data usage");
        this.wait(1);
        if (this.isViewWithIdPresent("widget_frame") && (!isCheckboxChecked("widget_frame"))) {
            clickItemWithId("widget_frame");
        }
        this.wait(1);
        if (isViewWithTextPresent("Attention") || isViewWithTextPresent("Yes")
                || isViewWithTextPresent("No")) {
            this.click("Yes");
            this.wait(2);
        }
        this.backOutToHomeScreen();
    }

    public void turnOnTraffic2() {

        this.launchAppByManual("Settings");
        this.click("More…");
        this.wait(1);
        this.clickListItem("Mobile networks");
        this.wait(2);
        if (!isCheckboxChecked("checkbox", 0)) {
            this.clickItemWithId("checkbox", 0);
            this.wait(1);
            if (isViewWithTextPresent("Attention") || isViewWithTextPresent("Yes")
                    || isViewWithTextPresent("No")) {
                this.click("Yes");
                this.wait(2);
            }
        }
        this.backOutToHomeScreen();
    }

    public void turnOnDataRoaming() {

        // this.launchAppByManual("Settings");
        this.launchApp("com.android.settings", "com.android.settings.Settings");
        this.wait(4);
        this.click("More…");
        this.wait(1);
        this.clickListItem("Mobile networks");
        this.wait(2);
        if (!isCheckboxChecked("checkbox", 1)) {
            this.clickItemWithId("checkbox", 1);
            this.wait(1);
            if (isViewWithTextPresent("Yes") || isViewWithTextPresent("No")) {
                this.click("Yes");
                this.wait(2);
            } else if (isViewWithTextPresent("OK") || isViewWithTextPresent("Cancel")) {
                this.click("OK");
                this.wait(2);
            }
        }
        this.backOutToHomeScreen();
    }*/

    /*public boolean checkDataRoaming() {
        this.backToHome();
        this.launchAppByManual("Settings");
        this.scrollDown();
        this.scrollDown();
        this.scrollDown();
        this.wait(1);
        this.click("About phone");
        this.wait(1);
        this.click("Status");
        this.scrollDown();
        this.wait(2);
        if (isViewWithTextPresent("Disconnected")) {
            this.backOutToHomeScreen();
            return false;
        }
        this.wait(3);
        this.backOutToHomeScreen();
        return true;
    }

    public void setStaticWallpaper() {

        this.launchAppByManual("Settings");
        this.click("Display");
        this.click("Wallpaper");
        this.click("Xperia™ wallpapers");
        this.wait(1);
        this.click("Set wallpaper");
        this.wait(2);

    }*/

    /*public void SendBroadcastToPET(String intentcontent, String loops) {

        Context mContext = getInstrumentation().getContext();

        // String intentcomd =
        // "com.sonyericsson.pet.jpmonitord.intent.ACTION_PET_SET_MARKER";
        String intentcomd = "com.sonyericsson.pet.jpmonitord.intent.ACTION_PET_EXTRA_INFO";

        Intent intent = new Intent(intentcomd);

        intent.putExtra("usecase", intentcontent);

        intent.putExtra("loop", loops);

        mContext.sendBroadcast(intent);

    }*/

    /*public void resetWallpaperTheme() {
        //
        // this.launchAppByManual("Settings");
        // this.waitForTextViewCompoment("Display", 4);
        // this.click("Display");
        // this.waitForTextViewCompoment("Theme", 4);
        // this.click("Theme");
        // this.waitForTextViewCompoment("Themes", 4);
        // this.scrollUp();
        // this.scrollUp();
        // this.wait(1);
        // // this.click("XPERIA");
        // this.clickItemWithId("text1", 4);
        // // back
        // this.waitForTextViewCompoment("Display", 8);
        // this.wait(2);
        // this.click("Theme");
        // this.waitForTextViewCompoment("Themes", 4);
        // this.scrollUp();
        // this.scrollUp();
        // this.scrollUp();
        // this.wait(1);
        // this.clickItemWithId("text1", 5);
        // this.waitForTextViewCompoment("Display", 8);
        // this.wait(2);
        // this.backOutToHomeScreen();

        this.launchAppByManual("Settings");
        this.clickListItem("Display");
        this.clickListItem("Theme");
        this.waitForTextViewCompoment("Themes", 4);
        this.wait(1);
        this.scrollUp();
        this.scrollUp();
        this.scrollUp();
        this.wait(1);
        for (int i = 0; i < 5; i++) {
            this.wait(1);

            if (isViewWithTextPresent("Silk")) {
                break;
            } else if (!isViewWithTextPresent("Silk")) {
                this.scrollDown();
            }
            this.wait(1);
        }
        this.click("Silk");
        if (this.isViewWithTextPresent("Apply")) {
            this.click("Apply");
        }
        // back
        this.clickListItem("Theme");
        this.wait(1);
        this.scrollUp();
        this.scrollUp();
        this.scrollUp();
        this.wait(1);
        for (int i = 0; i < 5; i++) {
            this.wait(1);

            if (isViewWithTextPresent("XPERIA")) {
                break;
            } else if (!isViewWithTextPresent("XPERIA")) {
                this.scrollDown();
            }
            this.wait(1);
        }
        this.click("XPERIA");
        if (this.isViewWithTextPresent("Apply")) {
            this.click("Apply");
        }
        this.waitForTextViewCompoment("Display", 8);
        this.wait(2);
        this.backOutToHomeScreen();
    }*/

    /*public int[] getScreamCenter(int width, int height) {

        int[] size = {
                (int)(width / 2.00), (int)(height / 2.00)
        };

        return size;
    }

    public void setDynamicWallpaper() {

        this.launchAppByManual("Settings");
        this.waitForTextViewCompoment("Display", 10);
        this.click("Display");
        this.waitForTextViewCompoment("Wallpaper", 10);
        this.click("Wallpaper");
        this.waitForTextViewCompoment("Live Wallpapers", 10);
        this.click("Live Wallpapers");
        this.waitForTextViewCompoment("Bubbles", 10);
        this.click("Bubbles");
        this.waitForTextViewCompoment("Set wallpaper", 10);
        this.click("Set wallpaper");
        this.wait(3);

    }*/

    /*public void cancleStaticWallpaper() {

        this.launchAppByManual("Settings");
        this.click("Display");
        this.click("Wallpaper");
        this.click("Live Wallpapers");
        this.click("Cosmic Flow");
        this.wait(1);
        this.click("Set wallpaper");
        this.wait(2);

    }

    public void setmusicrepeatone() {
        this.launchApp("com.sonyericsson.music", "com.sonyericsson.music.MusicActivity");
        this.waitForActivitySwitch();
        if (isViewWithTextPresent("Get more album art") || isViewWithTextPresent("OK")) {
            this.click("OK");
            this.wait(1);
        }
        this.clickId("miniplayer");
        this.wait(3);
        if (isViewWithTextPresent("Get more album art") || isViewWithTextPresent("OK")) {
            this.click("OK");
            this.wait(1);
        }
        for (int i = 0; i < 3; i++) {
            this.clickId("player_repeat_button");
            this.wait(1);
            if (this.isViewWithTextPresent("Repeat one")) {
                break;
            }
        }

        this.wait(2);
        this.backOutToHomeScreen();
    }

    public void startMusicWithWALKMAN(String musicName) {
        this.backOutToHomeScreen();
        // this.launchAppByManual(musicName);
        // this.launchAppByManual("WALKMAN");
        this.launchApp("com.sonyericsson.music", "com.sonyericsson.music.MusicActivity");
        this.wait(3);
        Log.i(POWER_TAG, "1");
        // if (isViewWithTextPresent("Get more album art") ||
        // isViewWithTextPresent("OK")) {
        // this.click("OK");
        // this.wait(1);
        // }
        if (this.isViewWithIdPresent("alertTitle")) {
            if (this.isViewWithIdPresent("never_show_dialog_checkbox")) {
                if (!this.isCheckboxChecked("never_show_dialog_checkbox")) {
                    this.clickId("never_show_dialog_checkbox");
                }
            }
            this.clickId("button1");
        }
        Log.i(POWER_TAG, "2");
        // this.click("My music");

        if (isViewWithTextPresent("Songs")) {
            this.click("Songs");
        } else {
            this.click("Tracks");
        }
        this.wait(1);
        // add
        if (isViewWithTextPresent(musicName)) {

            this.clickItemWithText(musicName);
        } else {
            boolean isFind = scroolFindTextWithMusicItem(musicName);
            if (isFind) {
                this.clickItemWithText(musicName);
            } else {
                assertTrue(false);
            }
        }

        this.wait(3);

        if (isViewWithTextPresent("Error") || isViewWithTextPresent("OK")) {
            assertTrue(false);
        }
        this.click(musicName);
        this.wait(3);
        // this.clickId("player_repeat_button");
        // this.wait(2);
         Set volume to mid level
        this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_VOLUME_DOWN, 4);
        this.wait(2);

    }

    /*public int getX(int _width) {

        int[] screamSize = this.getScreenSize();
        width = screamSize[0];
        return (int)((width * _width) / 720);
    }

    public int getY(int _heigh) {
        int[] screamSize = this.getScreenSize();
        heigh = screamSize[1];
        return (int)((heigh * _heigh) / 1184);
    }

    public int getX(int x, int currentWidth) {

        int[] screamSize = this.getScreenSize();
        width = screamSize[0];
        Log.e(POWER_TAG, "width == " + width);
        Log.e(POWER_TAG, "x pointer == " + ((int)((width * x) / currentWidth)));
        return (int)((width * x) / currentWidth);
    }

    public int getY(int y, int currentHeigh) {
        int[] screamSize = this.getScreenSize();
        heigh = screamSize[1];
        Log.e(POWER_TAG, "heigh == " + heigh);
        Log.e(POWER_TAG, "yyy pointer == " + ((int)((heigh * y) / currentHeigh)));
        return (int)((heigh * y) / currentHeigh);
    }*/

    /*public int getMusicX(int _width) {

        return (int)(_width * (663.00 / 720));
    }

    public int getMusicY(int _heigh) {

        return (int)(_heigh * (100.00 / 1184));
    }

    public void stopMusicWithWALKMAN() {

        this.backToHome();
        this.launchAppByManual("WALKMAN");
        this.wait(2);
        if (isViewWithTextPresent("Get more album art") || isViewWithTextPresent("OK")) {
            this.click("OK");
            this.wait(1);
        }
        this.click("My music");
        this.wait(1);
        this.click("Tracks");
        this.wait(1);
        this.clickItemWithText("Working Life");
        this.wait(5);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        if (!isViewWithTextPresent("Repeat mode")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(3);
            this.clickPoint(getX(210), getY(650));
            this.wait(3);
            this.clickPoint(getX(630), getY(810));
            this.wait(3);
        } else {
            this.pressKey(KeyEvent.KEYCODE_BACK);
        }
        this.clickId("player_play_pause_button");
        this.wait(3);
        this.backOutToHomeScreen();

    }*/

    /*public void stopMusicWithWALKMAN(String musicName) {
        // this.backOutToHomeScreen();
        // this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_VOLUME_UP, 4);
        // this.openStatusBar();
        // if (this.isViewWithTextPresent("Clear")) {
        // this.click("Clear");
        // this.wait(1);
        // this.openStatusBar();
        // }
        // for (int i = 0; i < 2; i++) {
        // if (this.isViewWithTextPresent(musicName)) {
        // this.clickId("next");
        // this.wait(2);
        // this.clickId("pause");
        // }
        // if (!this.isViewWithTextPresent(musicName)) {
        // break;
        // }
        // }
        // Log.d(POWER_TAG, "Music is stopped!");

        this.backOutToHomeScreen();
        this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_VOLUME_UP, 4);
        this.openStatusBar();
        this.wait(2);
        if (this.isViewWithTextPresent("Clear")) {
            this.click("Clear");
            this.wait(2);
            this.openStatusBar();
        }
        for (int i = 0; i < 2; i++) {
            if (this.isViewWithTextPresent("Notifications")) {
                this.click("Notifications");
            }
            if (this.isViewWithTextPresent(musicName)) {
                this.clickId("next");
                this.wait(2);
                this.clickId("pause");
                break;
            }
            if (i == 1) {
                break;
            }
        }
        Log.d(POWER_TAG, "Music is stopped!");

     }

    /*public void setMusicPlayRepeatOne() {

        this.backToHome();
        this.launchAppByManual("WALKMAN");
        this.wait(2);
        if (isViewWithTextPresent("Get more album art") || isViewWithTextPresent("OK")) {
            this.click("OK");
            this.wait(1);
        }
        this.click("My music");
        this.click("Tracks");
        this.wait(1);
        this.clickItemWithText("001_Working Life");
        this.wait(2);
        // this.clickPoint(663, 100);
        this.pressKey(KeyEvent.KEYCODE_MENU);
        if (!isViewWithTextPresent("Repeat mode")) {
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(3);
            this.clickPoint(getX(210), getY(650));
            this.wait(3);
            this.clickPoint(getX(630), getY(810));
            this.wait(3);
        } else {
            this.wait(1);
            this.click("Repeat mode");
            this.wait(1);
            this.click("Repeat one");
            this.wait(3);
        }
        this.clickId("player_play_pause_button");
        this.wait(5);
        this.backOutToHomeScreen();

    }*/

    /*public void finishSms(String callNumber, String callNumber2, String showContent) {

        this.wait(2);
        this.sendSMS(callNumber2, showContent);
        this.wait(2);
        this.sendSMS(callNumber, showContent);
    }

    public void cancelBrightness() {

        this.backOutToHomeScreen();
        this.wait(3);
        this.launchAppByManual("Settings");
        this.wait(3);
        this.clickListItem("Display");
        this.wait(3);
        this.click("Brightness");
        this.wait(3);
        if (isCheckboxChecked("automatic_mode")) {
            if (isCheckboxChecked("automatic_mode", 0)) {
                this.clickItemWithId("automatic_mode", 0);
                this.wait(3);
                this.click("OK");
            }
        }
        this.backOutToHomeScreen();
    }

    public void SetGoogleAccount(String accountname, String password) throws IOException {

        for (int i = 0; i < 3; i++) {
            this.backOutToHomeScreen();
            this.launchApp("com.android.vending", "com.android.vending.AssetBrowserActivity");
            this.wait(4);
            if ((this.isViewWithTextPresent("Google Play") || this
                    .isViewWithTextPresent("Play Store"))
                    && this.isViewWithIdPresent("accessibility_overlay")) {
                Log.d(POWER_TAG, "Already login");
                this.wait(10);
                break;
            } else if (this.isViewWithIdPresent("next_button")
                    && this.isViewWithIdPresent("create_button")) {
                this.clickId("next_button");
                this.wait(1);
                this.clickId("username_edit");
                this.enterText(accountname);
                this.wait(1);
                this.clickId("password_edit");
                this.enterText(password);
                this.wait(1);
                this.clickId("next_button");
                this.wait(5);
                if (this.isViewWithIdPresent("sign_in_agreement")) {
                    if (this.isViewWithIdPresent("google_play_opt_in")) {
                        if (isCheckboxChecked("google_play_opt_in")) {
                            if (isCheckboxChecked("google_play_opt_in", 0)) {
                                this.clickItemWithId("google_play_opt_in", 0);
                                this.wait(3);
                                this.click("OK");
                            }
                        }
                    } else {
                        this.click("OK");
                    }
                }
                this.wait(5);
                while (this.isViewWithTextPresent("Cancel")) {
                    this.wait(5);
                }
                if (this.isViewWithTextPresent("Couldn't sign in")) {
                    Log.d(POWER_TAG, "Login failed, retry again");
                    if (i == 3) {
                        this.destroyResource();
                        assertTrue(false);
                    }
                    continue;
                }
                if (this.isViewWithTextPresent("Not now")) {
                    this.click("Not now");
                }
                this.wait(5);
                if (this.isViewWithTextPresent("Not now")) {
                    this.click("Not now");
                }
                this.wait(5);
                if (this.isViewWithIdPresent("agree_backup")) {
                    if (isCheckboxChecked("agree_backup", 0)) {
                        this.clickItemWithId("agree_backup", 0);
                        this.wait(3);
                        this.clickId("next_button");
                    }
                }
                if (this.isViewWithIdPresent("agree_backup_restore")) {
                    if (isCheckboxChecked("agree_backup_restore", 0)) {
                        this.clickItemWithId("agree_backup_restore", 0);
                        this.wait(3);
                        this.clickId("next_button");
                    }
                }
                this.wait(5);
                if (this.isViewWithTextPresent("Not now")) {
                    this.click("Not now");
                }
                this.wait(5);
                if (this.isViewWithIdPresent("email_opt_in")) {
                    if (isCheckboxChecked("email_opt_in", 0)) {
                        this.clickItemWithId("email_opt_in", 0);
                        this.wait(2);
                        if (this.isViewWithIdPresent("positive_button")) {
                            this.clickId("positive_button");
                            Log.d(POWER_TAG, "click positive button");
                            this.wait(2);
                        }
                        if (this.isViewWithTextPresent("Accept")) {
                            this.click("Accept");
                            Log.d(POWER_TAG, "click accept");
                        }
                    } else {

                        if (this.isViewWithIdPresent("positive_button")) {
                            this.clickId("positive_button");
                            Log.d(POWER_TAG, "click positive button2");
                        }
                        if (this.isViewWithTextPresent("Accept")) {
                            this.click("Accept");
                            Log.d(POWER_TAG, "click accept1");
                        }
                    }
                }
                this.wait(10);
                if ((this.isViewWithTextPresent("Google Play") || this
                        .isViewWithTextPresent("Play Store"))
                        && this.isViewWithIdPresent("accessibility_overlay")) {
                    Log.d(POWER_TAG, "Login successful");
                    this.wait(10);
                    break;
                } else if ((this.isViewWithTextPresent("Google Play") || this
                        .isViewWithTextPresent("Play Store"))
                        && this.isViewWithTextPresent("Retry")) {
                    this.click("Retry");
                    this.wait(10);
                    if ((this.isViewWithTextPresent("Google Play") || this
                            .isViewWithTextPresent("Play Store"))
                            && this.isViewWithIdPresent("accessibility_overlay")) {
                        Log.d(POWER_TAG, "Login successful");
                        this.wait(10);
                        break;
                    }
                } else {
                    Log.d(POWER_TAG, "Login " + i + " is failed");
                    this.backOutToHomeScreen();
                    if (i == 3) {
                        this.destroyResource();
                        assertTrue(false);
                    }
                }
            }
        }
        this.backOutToHomeScreen();

    }*/

    /*public void InstallAPPfromPlayStore(String APPname) {
        this.launchApp("com.android.vending", "com.android.vending.AssetBrowserActivity");
        this.wait(4);
        this.clickId("search_button");
        this.enterText(APPname);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        this.wait(10);

        if (this.isViewWithIdPresent("bucket_items", 0)) {
            this.clickItemWithId("bucket_items", 0);
        }
        this.wait(3);
        this.clickId("buy_button");
        if (this.isViewWithTextPresent("CONTINUE")) {
            this.click("CONTINUE");
        } else if (this.isViewWithTextPresent("ACCEPT")) {
            this.click("ACCEPT");
        }
        this.wait(4);
        if (this.isViewWithIdPresent("alertTitle")) {
            this.click("OK");
            this.wait(1);
            this.clickId("buy_button");
            this.wait(1);
            if (this.isViewWithTextPresent("CONTINUE")) {
                this.click("CONTINUE");
            } else if (this.isViewWithTextPresent("ACCEPT")) {
                this.click("ACCEPT");
            }
        }
        while (!this.isViewWithIdPresent("launch_button")) {
            this.wait(5);
        }
        Log.e("ee", "Successfully install " + APPname);
        this.backOutToHomeScreen();

    }

    *//**
     *
     * login syc email
     *
     * @param userName
     * @param password
     * @return
     *//*
    public boolean loginEmailSyncAuto(String userName, String password, int timeMin) {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        // this.launchAppByManual("Email");
        // this.launchApp("com.android.email",
        // "com.android.email.activity.setup.AccountSetupBasics");
        this.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
        // this.launchAppBySearch("Email");
        this.wait(4);
        // if (this.isViewWithTextPresent("Exchange Active Sync")) {
        // this.click("Exchange Active Sync");
        // }
        if (isViewWithIdPresent("account_email")) {
            this.clickId("account_email");
            this.enterText(userName);
            this.wait(1);
            this.clickId("account_password");
            this.enterText(password);
            this.wait(1);
            // this.clickId("manual_setup");
            this.clickId("next");
            this.wait(30);
            // if (this.isViewWithTextPresent("Exchange ActiveSync")) {
            // this.click("Exchange ActiveSync");
            // this.wait(10);
            for (int j = 0; j < 5; j++) {
                if (this.isViewWithTextPresent("IMAP")) {
                    this.click("IMAP");
                    this.wait(10);
                    if (isViewWithIdPresent("account_port")) {
                        this.clickId("account_port");
                        String serverText = this.getTextFromViewWithId("account_port");

                        for (int i = 0; i < serverText.length(); i++) {
                            pressKey(KeyEvent.KEYCODE_DEL);
                        }
                        this.enterText("993");
                        this.wait(1);
                        this.pressKey(KeyEvent.KEYCODE_BACK);
                        this.wait(2);
                        this.clickId("account_security_type");
                        this.wait(2);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_ENTER);
                        this.clickId("next");
                        this.wait(20);
                    }
                    break;
                } else {
                    this.wait(10);
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithTextPresent("Inbox check frequency")
                        || isViewWithTextPresent("Inbox checking frequency")
                        || this.isViewWithTextPresent("Check frequency")) {
                    this.clickId("account_check_frequency");
                    // The default frequency is manual
                    this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // //The default frequency is 15 minutes
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    this.pressKey(KeyEvent.KEYCODE_ENTER);
                    Log.d(POWER_TAG, "Set the check frequency to 5Mins");
                    if (isViewWithTextPresent("OK")) {
                        this.click("OK");
                    }
                    this.click("Next");
                    this.wait(2);
                    break;
                }

                if (isViewWithTextPresent("Could not finish")
                        || isViewWithTextPresent("Edit details")) {
                    this.click("Edit details");
                    this.wait(2);
                    this.click("Next");
                    if (i == 9) {
                        return isSuccess;
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithIdPresent("account_name")) {
                    this.clickId("account_name");
                    String serverText = this.getTextFromViewWithId("account_name");
                    for (int j = 0; j < serverText.length(); j++) {
                        this.pressKey(KeyEvent.KEYCODE_DEL);
                    }
                    this.enterText(userName);
                    this.wait(2);
                    this.clickId("next");
                    break;
                }
                this.wait(1);
                if (this.isViewWithTextPresent("Note") || this.isViewWithTextPresent("No")) {
                    this.click("No");
                }
            }
            // wait long time to login last view

            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                    // login seccuess
                    this.wait(5);
                    break;
                } else if (isViewWithTextPresent("Could not create the account.Try again later.")) {
                    if (this.isViewWithTextPresent("Cancel")) {
                        this.click("Cancel");
                        Log.d(POWER_TAG, "The email account cannot be created.");
                        return isSuccess;
                    }
                }
            }
            this.wait(5);
            if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                // login success
                Log.d(POWER_TAG, "Email Account login sucessully");
                isSuccess = !isSuccess;
            }
        } else if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
            isSuccess = !isSuccess;
            Log.e(POWER_TAG, "Already configured.");
        }
        return isSuccess;
    }*/

    /*public boolean loginEmailSyncAutoServer(String userName, String password, int timeMin) {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        this.launchAppBySearch("Email");
        // this.launchApp("com.android.email",
        // "com.android.email.activity.setup.AccountSetupBasics");
        // this.launchApp("com.android.email",
        // "com.android.email.activity.EmailActivity");
        // this.launchAppBySearch("Email");
        this.wait(4);

        if (isViewWithIdPresent("account_email")) {
            this.clickId("account_email");
            this.enterText(userName);
            this.wait(1);
            this.clickId("account_password");
            this.enterText(password);
            this.wait(1);
            this.clickId("next");
            this.wait(30);
            for (int j = 0; j < 5; j++) {
                if (this.isViewWithTextPresent("IMAP")) {
                    this.click("IMAP");
                    this.wait(10);
                    if (isViewWithIdPresent("account_port")) {
                        this.clickId("account_port");
                        String serverText = this.getTextFromViewWithId("account_port");

                        for (int i = 0; i < serverText.length(); i++) {
                            pressKey(KeyEvent.KEYCODE_DEL);
                        }
                        this.enterText("993");
                        this.wait(1);
                        this.pressKey(KeyEvent.KEYCODE_BACK);
                        this.wait(2);
                        this.clickId("account_security_type");
                        this.wait(2);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_ENTER);
                        this.clickId("next");
                        this.wait(20);
                    }
                    break;
                } else {
                    this.wait(10);
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithTextPresent("Inbox check frequency")
                        || isViewWithTextPresent("Inbox checking frequency")
                        || this.isViewWithTextPresent("Check frequency")) {
                    this.clickId("account_check_frequency");
                    // The default frequency is manual
                    this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // //The default frequency is 15 minutes
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_UP);
                    this.pressKey(KeyEvent.KEYCODE_ENTER);
                    Log.d(POWER_TAG, "Set the check frequency to 5Mins");
                    if (isViewWithTextPresent("OK")) {
                        this.click("OK");
                    }
                    this.click("Next");
                    this.wait(2);
                    break;
                }

                if (isViewWithTextPresent("Could not finish")
                        || isViewWithTextPresent("Edit details")) {
                    this.click("Edit details");
                    this.wait(2);
                    this.click("Next");
                    if (i == 9) {
                        return isSuccess;
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithIdPresent("account_name")) {
                    this.clickId("account_name");
                    String serverText = this.getTextFromViewWithId("account_name");
                    for (int j = 0; j < serverText.length(); j++) {
                        this.pressKey(KeyEvent.KEYCODE_DEL);
                    }
                    this.enterText(userName);
                    this.wait(2);
                    this.clickId("next");
                    break;
                }
                this.wait(1);
                if (this.isViewWithTextPresent("Note") || this.isViewWithTextPresent("No")) {
                    this.click("No");
                }
            }
            // wait long time to login last view

            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                    // login seccuess
                    this.wait(5);
                    break;
                } else if (isViewWithTextPresent("Could not create the account.Try again later.")) {
                    if (this.isViewWithTextPresent("Cancel")) {
                        this.click("Cancel");
                        Log.d(POWER_TAG, "The email account cannot be created.");
                        return isSuccess;
                    }
                }
            }
            this.wait(5);
            if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                // login success
                Log.d(POWER_TAG, "Email Account login sucessully");
                isSuccess = !isSuccess;
            }
        } else if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
            isSuccess = !isSuccess;
            Log.e(POWER_TAG, "Email Account is already configured.");
        }
        assertTrue(isSuccess);
        return isSuccess;
    }

    public boolean loginEmailSyncManual(String userName, String password, int timeMin) {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        // this.launchAppByManual("Email");
        // this.launchApp("com.android.email",
        // "com.android.email.activity.setup.AccountSetupBasics");
        this.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
        // this.launchAppBySearch("Email");
        this.wait(4);
        // if (this.isViewWithTextPresent("Exchange Active Sync")) {
        // this.click("Exchange Active Sync");
        // }
        if (isViewWithIdPresent("account_email")) {
            this.clickId("account_email");
            this.enterText(userName);
            this.wait(1);
            this.clickId("account_password");
            this.enterText(password);
            this.wait(1);
            // this.clickId("manual_setup");
            this.clickId("next");
            this.wait(30);
            // if (this.isViewWithTextPresent("Exchange ActiveSync")) {
            // this.click("Exchange ActiveSync");
            // this.wait(10);
            for (int j = 0; j < 5; j++) {
                if (this.isViewWithTextPresent("IMAP")) {
                    this.click("IMAP");
                    this.wait(10);
                    // if (isViewWithIdPresent("account_port")) {
                    // this.clickId("account_port");
                    // String serverText =
                    // this.getTextFromViewWithId("account_port");
                    //
                    // for (int i = 0; i < serverText.length(); i++) {
                    // pressKey(KeyEvent.KEYCODE_DEL);
                    // }
                    // this.enterText("993");
                    // this.wait(1);
                    // this.pressKey(KeyEvent.KEYCODE_BACK);
                    // this.wait(2);
                    // this.clickId("account_security_type");
                    // this.wait(2);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // this.pressKey(KeyEvent.KEYCODE_ENTER);
                    // this.clickId("next");
                    // this.wait(20);
                    // }
                    if (this.isViewWithIdPresent("account_port")) {
                        this.clickId("account_port");
                        String serverText = this.getTextFromViewWithId("account_port");

                        for (int i = 0; i < serverText.length(); i++) {
                            pressKey(KeyEvent.KEYCODE_DEL);
                        }
                        this.enterText("465");
                        this.wait(1);
                        this.pressKey(KeyEvent.KEYCODE_BACK);
                        this.wait(2);
                        this.clickId("account_security_type");
                        this.wait(2);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                        this.pressKey(KeyEvent.KEYCODE_ENTER);
                        this.clickId("next");
                        this.wait(20);
                        Log.e(POWER_TAG, "Email account login.");

                    }
                    break;
                } else {
                    this.wait(10);
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithTextPresent("Inbox check frequency")
                        || isViewWithTextPresent("Inbox checking frequency")
                        || this.isViewWithTextPresent("Check frequency")) {
                    this.clickId("account_check_frequency");
                    this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    // this.pressKey(KeyEvent.KEYCODE_DPAD_DOWN);
                    this.pressKey(KeyEvent.KEYCODE_ENTER);
                    Log.d(POWER_TAG, "Set the check frequency to Manually");
                    if (isViewWithTextPresent("OK")) {
                        this.click("OK");
                    }
                    this.click("Next");
                    this.wait(2);
                    break;
                }

                if (isViewWithTextPresent("Could not finish")
                        || isViewWithTextPresent("Edit details")) {
                    this.click("Edit details");
                    this.wait(2);
                    this.click("Next");
                    if (i == 9) {
                        return isSuccess;
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                this.wait(2);
                // check if or not this view for "Next"
                if (isViewWithIdPresent("account_name")) {
                    this.clickId("account_name");
                    String serverText = this.getTextFromViewWithId("account_name");
                    for (int j = 0; j < serverText.length(); j++) {
                        this.pressKey(KeyEvent.KEYCODE_DEL);
                    }
                    this.enterText(userName);
                    this.wait(2);
                    this.clickId("next");
                    break;
                }
                this.wait(1);
                if (this.isViewWithTextPresent("Note") || this.isViewWithTextPresent("No")) {
                    this.click("No");
                }
            }
            // wait long time to login last view

            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                // if (isViewWithIdPresent("title_left_text") ||
                // isViewWithTextPresent("No mail")) {
                if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                    // login seccuess
                    this.wait(5);
                    break;
                } else if (isViewWithTextPresent("Could not create the account.Try again later.")) {
                    if (this.isViewWithTextPresent("Cancel")) {
                        this.click("Cancel");
                        Log.d(POWER_TAG, "The email account cannot be created.");
                        return isSuccess;
                    }
                }
            }
            this.wait(5);
            // if (isViewWithIdPresent("title_left_text")||
            // isViewWithTextPresent("No mail")) {
            if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                // login success
                Log.d(POWER_TAG, "Email Account login sucessully");
                isSuccess = !isSuccess;
            }
        }
        // else if (isViewWithIdPresent("title_left_text")||
        // isViewWithTextPresent("No mail")) {
        else if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
            isSuccess = !isSuccess;
            Log.e(POWER_TAG, "Already configured.");
        }
        return isSuccess;
    }*/

    /*public boolean loginEmailActiveSync(String userName, String password, int timeMin) {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        // this.launchAppByManual("Email");
        // this.launchApp("com.android.email",
        // "com.android.email.activity.setup.AccountSetupBasics");
        this.launchApp("com.android.email", "com.android.email.activity.EmailActivity");
        // this.launchAppBySearch("Email");
        this.wait(4);
        // if (this.isViewWithTextPresent("Exchange Active Sync")) {
        // this.click("Exchange Active Sync");
        // }
        if (isViewWithIdPresent("account_email")) {
            this.clickId("account_email");
            this.enterText(userName);
            this.wait(1);
            this.clickId("account_password");
            this.enterText(password);
            this.wait(1);
            // this.clickId("manual_setup");
            this.clickId("next");
            this.wait(30);
            for (int j = 0; j < 5; j++) {
                if (this.isViewWithTextPresent("Exchange ActiveSync")
                        || this.isViewWithTextPresent("Exchange")) {
                    if (this.isViewWithTextPresent("Exchange")) {
                        this.click("Exchange");
                    } else if (this.isViewWithTextPresent("Exchange ActiveSync")) {
                        this.click("Exchange ActiveSync");
                    }
                    this.wait(10);
                    // if (this.isViewWithTextPresent("IMAP")) {
                    // this.click("IMAP");
                    // this.wait(10);
                    this.waitForIdViewCompoment("account_username", 300);
                    if (isViewWithIdPresent("account_username")) {
                        // this.clickId("account_username");
                        // String serverText1 =
                        // this.getTextFromViewWithId("account_username");
                        //
                        // for (int i = 0; i < serverText1.length(); i++) {
                        // pressKey(KeyEvent.KEYCODE_DEL);
                        // }
                        this.enterText("Teslan1");
                        this.pressKey(KeyEvent.KEYCODE_ENTER);
                        if (this.isViewWithIdPresent("account_server")) {
                            this.clickId("account_server");
                            String serverText = this.getTextFromViewWithId("account_server");
                            for (int i = 0; i < serverText.length(); i++) {
                                this.pressKey(KeyEvent.KEYCODE_DEL);
                            }
                        }
                        this.enterText("eas.gte.org");
                        this.wait(1);
                        this.pressKey(KeyEvent.KEYCODE_BACK);
                        this.wait(2);
                        if (!this.isCheckboxChecked("account_trust_certificates")) {
                            this.clickId("account_trust_certificates");
                        }
                        this.wait(2);
                        this.clickId("next");
                    }
                    this.wait(20);
                    // this.waitForTextViewCompoment("Remote security administration",
                    // 300);

                    for (int i = 0; i < 10; i++) {
                        if (this.isViewWithTextPresent("Remote security administration")) {
                            Log.d(POWER_TAG, "Incoming setting is done.");
                            this.click("OK");
                            this.wait(5);
                        } else if (isViewWithTextPresent("Could not finish")
                                || isViewWithTextPresent("Edit details")) {
                            this.click("Edit details");
                            this.wait(1);
                            this.click("Next");
                        }
                        if ((this.isViewWithTextPresent("Inbox check frequency")
                                || this.isViewWithTextPresent("Inbox checking frequency") || this
                                    .isViewWithTextPresent("Check frequency"))
                                && this.isViewWithIdPresent("next")) {
                            this.clickId("account_sync_window");
                            this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 2);
                            this.pressKey(KeyEvent.KEYCODE_ENTER);
                            this.clickId("next");
                            this.wait(5);
                        }
                        if ((this.isViewWithTextPresent("Set account name (optional)") || this
                                .isViewWithTextPresent("Give this account a name (optional)"))
                                && this.isViewWithIdPresent("next")) {
                            this.clickId("next");
                            this.wait(5);
                        }
                        if (this.isViewWithIdPresent("cancel_button")
                                && this.isViewWithIdPresent("action_button")) {
                            this.clickId("action_button");
                            Log.d(POWER_TAG, "The EAS account is set up.");
                            break;
                        }
                        if (isViewWithTextPresent("Could not finish")
                                || isViewWithTextPresent("Edit details")) {
                            this.click("Edit details");
                            this.wait(2);
                            this.click("Next");
                            if (i == 9) {
                                Log.e(POWER_TAG, "The EAS account set up is time out.");
                                return isSuccess;
                            }
                        }
                    }
                    break;
                } else {
                    this.wait(10);
                }
            }

            // wait long time to login last view

            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                // if (isViewWithIdPresent("title_left_text") ||
                // isViewWithTextPresent("No mail")) {
                if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                    // login seccuess
                    this.wait(5);
                    break;
                } else if (isViewWithTextPresent("Could not create the account.Try again later.")) {
                    if (this.isViewWithTextPresent("Cancel")) {
                        this.click("Cancel");
                        Log.d(POWER_TAG, "The email account cannot be created.");
                        return isSuccess;
                    }
                }
            }
            this.wait(5);
            // if (isViewWithIdPresent("title_left_text")||
            // isViewWithTextPresent("No mail")) {
            if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
                // login success
                Log.d(POWER_TAG, "Email Account login sucessully");
                isSuccess = !isSuccess;
            }
        }
        // else if (isViewWithIdPresent("title_left_text")||
        // isViewWithTextPresent("No mail")) {
        else if (isViewWithIdPresent("compose") || isViewWithTextPresent("No mail")) {
            isSuccess = !isSuccess;
            Log.e("agingserver", "Already configured.");
        }

        return isSuccess;
    }*/

    /**
     *
     * login Talk
     *
     * @param userName
     * @param password
     * @return
     */
    /*public boolean loginTalk(String userName, String password, int timeMin) {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        this.launchAppByManual("Talk");
        this.wait(3);
        // if(isViewWithIdPresent("login_username") ||
        // isViewWithIdPresent("login_password") ){
        if (isViewWithTextPresent("Existing") || isViewWithTextPresent("New")) {
            this.wait(1);
            this.click("Existing");

            this.wait(1);
            String text = this.getTextFromViewWithId("username_edit");
            if (text != null) {
                this.clickId("username_edit");
                for (int i = 0; i < text.length(); i++) {
                    this.pressKey(KeyEvent.KEYCODE_DEL);
                }
                this.pressKey(KeyEvent.KEYCODE_BACK);
                this.wait(1);
            }

            this.clickId("username_edit");
            this.enterText(userName);

            this.wait(1);
            this.clickId("password_edit");
            this.enterText(password);
            this.wait(3);

            this.clickId("next_button");
            // login
            this.wait(5);
            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithIdPresent("done_button")) {
                    this.wait(2);
                    break;
                }
                if (isViewWithIdPresent("submission_title")) {

                    this.clickId("next_button");
                }
                if (this.isViewWithTextPresent("OK")) {

                    this.click("OK");

                }
                if (this.isViewWithTextPresent("Not now")) {
                    this.click("Not now");
                    break;
                }
                Log.e(POWER_TAG, "9");
            }

            this.wait(2);

            if (this.isViewWithIdPresent("agree_backup")) {

                if (isCheckboxChecked("agree_backup")) {
                    this.clickId("agree_backup");
                    Log.e(POWER_TAG, "6");
                }
            }
            this.wait(1);
            this.clickId("done_button");
            Log.e(POWER_TAG, "7");
            this.wait(2);
            this.wait(1);
            if (isViewWithTextPresent("Accept") || isViewWithTextPresent("Decline")) {
                this.click("Accept");
                Log.e(POWER_TAG, "8");
                this.wait(2);
            }

            currTime = this.getCurrTime();
            afterTime = this.getCurrTime() + timeMin * 60 * 1000L;
            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithIdPresent("avatar_qcb") || isViewWithIdPresent("line1")) {
                    this.wait(5);
                    break;
                }
            }
            //
            this.wait(2);
            if (isViewWithIdPresent("avatar_qcb") || isViewWithIdPresent("line1")) {
                this.wait(10);
                isSuccess = !isSuccess;
            }
            Log.i(POWER_TAG, currTime + "");
            //
        }

        return isSuccess;
    }

    public void searchdirections(String city1, String city2) {
        this.clickId("directions");
        this.wait(5);
        this.enterText(city1);
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.clickId("toField");
        this.enterText(city2);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        while (this.isViewWithIdPresent("message")) {
            Log.e("a", "loading");
        }
        this.pressKey(KeyEvent.KEYCODE_BACK);
        this.pressKey(KeyEvent.KEYCODE_BACK);
    }

    public void searchcity(String city) {
        this.clickId("search");
        this.wait(1);
        String text = this.getTextFromViewWithId("search_src_text");
        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                this.pressKey(KeyEvent.KEYCODE_DEL);
            }
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.wait(1);
        }
        this.clickId("search");
        this.enterText(city);
        this.pressKey(KeyEvent.KEYCODE_ENTER);
        while (this.isViewWithIdPresent("message")) {
            Log.e("a", "loading");
        }
        this.wait(2);
        this.pressKey(KeyEvent.KEYCODE_BACK);
    }*/

    /**
     *
     * login facebook
     *
     * @param userName
     * @param password
     * @return
     * @throws IOException
     */
    /*public boolean loginFacebookSyc(String userName, String password, int timeMin)
            throws IOException {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        this.launchAppBySearch("Facebook");
        // this.launchApp("com.facebook.katana",
        // "com.facebook.katana.activity.FbFragmentChromeActivity");
        this.wait(2);
        // if(isViewWithIdPresent("login_username") ||
        // isViewWithIdPresent("login_password") ){
        if (isViewWithIdPresent("login_username")) {
            this.wait(1);
            String text = this.getTextFromViewWithId("login_username");
            if (text != null) {
                this.clickId("login_username");
                for (int i = 0; i < text.length(); i++) {
                    this.pressKey(KeyEvent.KEYCODE_DEL);
                }
                this.pressKey(KeyEvent.KEYCODE_BACK);
                this.wait(1);
            }

            this.clickId("login_username");
            this.enterText(userName);

            this.wait(1);
            this.clickId("login_password");
            this.enterText(password);
            this.wait(2);

            // login facebook
            if (isViewWithTextPresent("Log In")) {
                this.click("Log In");
            } else {

                this.click("Login");
            }
            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithTextPresent("Don't sync")) {
                    this.click("Don't sync");
                    this.clickId("primary_named_button");
                }
                this.wait(5);
                if (isViewWithTextPresent("Find Friends on Facebook")
                        && isViewWithTextPresent("Not Now")) {
                    this.click("Not Now");
                }
                if (isViewWithIdPresent("publisher_status_icon")
                        || isViewWithIdPresent("publisher_checkin_button")) {
                    for (int i = 0; i < 10; i++) {
                        if (this.isViewWithIdPresent("list_empty_progress")) {
                            this.wait(3);
                        } else if (this.isViewWithIdPresent("list_empty_progress") && i == 9) {
                            destroyResource();
                            assertTrue(false);
                        } else {
                            Log.d(POWER_TAG, "Content is displayed");
                            break;
                        }
                    }
                    Log.d(POWER_TAG, "Facebook log in successfully");
                    isSuccess = !isSuccess;
                    break;
                } else {
                    this.wait(5);
                }

                // if (isViewWithTextPresent("Sync")) {
                // this.wait(2);
                // break;
                // }
                // if (isViewWithTextPresent("OK")) {
                // this.click("OK");
                // this.wait(2);
                // if (isViewWithTextPresent("Log In")) {
                // this.click("Log In");
                // } else {
                // break;
                // }
                // }
                // // /
                // if (this.isViewWithTextPresent("Existing")) {
                // this.pressKey(KeyEvent.KEYCODE_BACK);
                // this.wait(5);
                // if (isViewWithIdPresent("publisher_status_icon")
                // || isViewWithIdPresent("publisher_checkin_button")) {
                // this.wait(5);
                // break;
                // }
                // }
            }
            // this.wait(2);
            // if (this.isViewWithTextPresent("Sync")) {
            // this.click("Sync");
            // }
            // this.wait(2);
            // if (this.isViewWithTextPresent("Not Now")) {
            // this.click("Not Now");
            // }
            // currTime = this.getCurrTime();
            // afterTime = this.getCurrTime() + timeMin * 60 * 1000L;
            // while (this.getCurrTime() < afterTime) {
            // this.wait(1);
            // if (isViewWithIdPresent("publisher_status_icon")
            // || isViewWithIdPresent("publisher_checkin_button")) {
            // this.wait(10);
            // break;
            // }
            // }
            //
            // // login facebook success
            // this.wait(2);
            // if (isViewWithIdPresent("publisher_status_icon")
            // || isViewWithIdPresent("publisher_checkin_button")) {
            // this.wait(10);
            // isSuccess = !isSuccess;
            // }
            // Log.i(POWER_TAG, currTime + "");

        } else if (this.isViewWithIdPresent("alertTitle") && this.isViewWithTextPresent("Not Now")) {
            this.click("Not Now");
            if (isViewWithIdPresent("publisher_status_icon")
                    || isViewWithIdPresent("publisher_checkin_button")) {
                isSuccess = !isSuccess;
                Log.d(POWER_TAG, "Facebook account already loging");
            }
            Log.d(POWER_TAG, "Facebook account already loging");
        } else if (isViewWithIdPresent("publisher_status_icon")
                || isViewWithIdPresent("publisher_checkin_button")) {
            isSuccess = !isSuccess;
            Log.d(POWER_TAG, "Facebook account already loging");
        }
        this.backOutToHomeScreen();
        return isSuccess;
    }

    public boolean loginFacebookSycserver(String userName, String password, int timeMin)
            throws IOException {
        boolean isSuccess = false;
        this.backOutToHomeScreen();
        this.launchAppBySearch("Facebook");
        // this.launchApp("com.facebook.katana",
        // "com.facebook.katana.activity.FbFragmentChromeActivity");
        this.wait(2);
        if (isViewWithIdPresent("login_username")) {
            this.wait(1);
            String text = this.getTextFromViewWithId("login_username");
            if (text != null) {
                this.clickId("login_username");
                for (int i = 0; i < text.length(); i++) {
                    this.pressKey(KeyEvent.KEYCODE_DEL);
                }
                this.pressKey(KeyEvent.KEYCODE_BACK);
                this.wait(1);
            }

            this.clickId("login_username");
            this.enterText(userName);

            this.wait(1);
            this.clickId("login_password");
            this.enterText(password);
            this.wait(2);

            // login facebook
            if (isViewWithTextPresent("Log In")) {
                this.click("Log In");
            } else {

                this.click("Login");
            }
            // login 3min
            long currTime = this.getCurrTime();
            long afterTime = this.getCurrTime() + timeMin * 60 * 1000L;

            while (this.getCurrTime() < afterTime) {
                this.wait(1);
                if (isViewWithTextPresent("Don't sync")) {
                    this.click("Don't sync");
                    this.clickId("primary_named_button");
                }
                this.wait(5);
                if (isViewWithIdPresent("publisher_status_icon")
                        || isViewWithIdPresent("publisher_checkin_button")) {
                    for (int i = 0; i < 10; i++) {
                        if (this.isViewWithIdPresent("list_empty_progress")) {
                            this.wait(3);
                        } else if (this.isViewWithIdPresent("list_empty_progress") && i == 9) {
                            destroyResource();
                            assertTrue(false);
                        } else {
                            Log.d(POWER_TAG, "Content is displayed");
                            break;
                        }
                    }
                    Log.d(POWER_TAG, "Facebook log in successfully");
                    isSuccess = !isSuccess;
                    break;
                } else {
                    this.wait(5);
                }
            }

        } else if (isViewWithIdPresent("publisher_status_icon")
                || isViewWithIdPresent("publisher_checkin_button")) {
            Log.d(POWER_TAG, "Facebook account already loging");
        }
        return isSuccess;
    }*/

    /*public void setfacebookrefreshinterval() {
        // this.launchApp("com.facebook.katana",
        // "com.facebook.katana.activity.FbFragmentChromeActivity");
        this.launchAppBySearch("Facebook");
        this.wait(4);
        if (this.isViewWithTextPresent("Find Friends") || this.isViewWithTextPresent("Not Now")) {
            this.click("Not Now");
        }
        this.wait(2);
        if (this.isViewWithIdPresent("launcher_button_wrapper")) {
            this.clickId("launcher_button_wrapper");
            this.wait(1);
        }

        this.clickListItemwithText("App Settings");
        this.wait(1);
        if (this.isViewWithTextPresent("Refresh interval")) {
            this.click("Refresh interval");
            this.wait(1);
            this.click("30 minutes");
            this.wait(1);
            this.pressKey(KeyEvent.KEYCODE_BACK);
        }

    }

    public void widgetsIndex(String leftDownIndex, String rightDownIndex) {

        int leftIndex = 0;
        int rightIndex = 0;

        if ((!"".equals(leftDownIndex)) && (leftDownIndex != null)) {

            leftIndex = Integer.parseInt(leftDownIndex);
        }

        if ((!"".equals(rightDownIndex)) && (rightDownIndex != null)) {

            rightIndex = Integer.parseInt(rightDownIndex);

        }

        if (leftIndex >= 1 && rightIndex == 0) {
            this.wait(2);
            pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, leftIndex);
            this.pressKey(KeyEvent.KEYCODE_ENTER);

        }
        if (rightIndex >= 1 && leftIndex == 0) {

            this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, 1);
            this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_RIGHT, 1);
            this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_UP, 1);

            this.wait(2);
            pressKeyForMultipleTimes(KeyEvent.KEYCODE_DPAD_DOWN, rightIndex - 1);
            this.pressKey(KeyEvent.KEYCODE_ENTER);

        }

    }

    public void clearHomeWidgetsUPDATE() {

        int[] screenSize = this.getScreenSize();
        int x;
        int x1;
        int xmiddle;
        int i;
        int y1;
        int y2;
        int y3;
        int y4;
        int y5;
        int iwidth;
        int iheight;
        iwidth = screenSize[0];
        iheight = screenSize[1];
        x1 = (int)(iwidth * (90.00 / 720));
        xmiddle = screenSize[0] / 2;

        this.wait(5);
        for (i = 0; i < 17; i++) {
            x = (int)(iwidth * ((10.00 + 40.00 * i) / 720));
            y3 = (int)(iheight * (691.00 / 1184));
            y4 = (int)(iheight * (891.00 / 1184));
            y5 = (int)(iheight * (1084.00 / 1184));
            float afpathfouri[][] = {
                    {
                            x, y4
                    }, {
                            x1, y3
                    }, {
                            x1, y4
                    }, {
                            xmiddle, y5
                    }
            };
            dragGesture(afpathfouri);
            this.pressKey(KeyEvent.KEYCODE_BACK);

        }
        for (i = 0; i < 17; i++) {
            x = (int)(iwidth * ((10.00 + 40.00 * i) / 720));
            ;
            y3 = (int)(iheight * (691.00 / 1184));
            y2 = (int)(iheight * (800.00 / 1184));
            y4 = (int)(iheight * (891.00 / 1184));
            y5 = (int)(iheight * (1084.00 / 1184));
            float pathTreei[][] = {
                    {
                            x, y3
                    }, {
                            x1, y2
                    }, {
                            x1, y4
                    }, {
                            xmiddle, y5
                    }
            };
            dragGesture(pathTreei);
            this.pressKey(KeyEvent.KEYCODE_BACK);

        }

        for (i = 0; i < 17; i++) {
            x = (int)(iwidth * ((10.00 + 40.00 * i) / 720));
            ;
            y2 = (int)(iheight * (391.00 / 1184));
            y3 = (int)(iheight * (691.00 / 1184));
            y4 = (int)(iheight * (891.00 / 1184));
            y5 = (int)(iheight * (1084.00 / 1184));
            float pathTwoi[][] = {
                    {
                            x, y2
                    }, {
                            xmiddle, y3
                    }, {
                            xmiddle, y4
                    }, {
                            xmiddle, y5
                    }
            };
            dragGesture(pathTwoi);
            this.pressKey(KeyEvent.KEYCODE_BACK);
            this.waitForMill(100);
        }

        for (i = 0; i < 17; i++) {
            x = (int)(iwidth * ((10.00 + 40.00 * i) / 720));
            ;
            y1 = (int)(iheight * (191.00 / 1184));
            y2 = (int)(iheight * (391.00 / 1184));
            y3 = (int)(iheight * (691.00 / 1184));
            y4 = (int)(iheight * (891.00 / 1184));
            y5 = (int)(iheight * (1084.00 / 1184));
            float pathOnei[][] = {
                    {
                            x, y1
                    }, {
                            xmiddle, y2
                    }, {
                            xmiddle, y3
                    }, {
                            xmiddle, y4
                    }, {
                            xmiddle, y5
                    }
            };
            dragGesture(pathOnei);
            this.pressKey(KeyEvent.KEYCODE_BACK);

        }

    }

    public void clickListItem(String itemname) {

        for (int i = 0; i <= 8; i++) {
            if (this.isViewWithTextPresent(itemname)) {
                this.click(itemname);
                break;
            }
            if (this.isViewWithIdPresent(itemname)) {
                this.clickId(itemname);
                break;
            }
            this.scrollDown();
            this.wait(2);
        }
    }*/

	/*public void clickListItem(String itemname, int index) {

		for (int i = 0; i <= 8; i++) {
			if (this.isViewWithTextPresent(itemname)) {
				this.clickItemWithText(itemname);
				break;
			}
			if (this.isViewWithIdPresent(itemname)) {
				this.clickItemWithId(itemname, index);
				break;
			}
			this.scrollDown();
			this.wait(2);
		}
	}

    public void clickListItemwithText(String itemname) {

        for (int i = 0; i <= 8; i++) {
            if (this.isViewWithTextPresent(itemname)) {
                this.click(itemname);
                break;
            }

            this.scrollDown();
            this.wait(2);
        }
    }

    public void clickListItemwithID(String itemname) {

        for (int i = 0; i <= 8; i++) {

            if (this.isViewWithIdPresent(itemname)) {
                this.clickId(itemname);
                break;
            }
            this.scrollDown();
            this.wait(2);
        }
    }

    public void deleteAPPs(int APPnumber) {

        String[] APPName = {
                "SE0001", "SE0002", "SE0003", "SE0004", "SE0005"
        };
        for (int i = 0; i < APPnumber; i++) {
            this.launchApp("com.android.settings", "com.android.settings.Settings");
            this.wait(4);
            this.scrollDown();
            // this.wait(1);
            // this.scrollDown();
            this.scrollFindText("Apps");
            this.wait(2);
            this.scrollFindTextNotClick(APPName[i]);
            if (this.isViewWithTextPresent(APPName[i])) {
                this.click(APPName[i]);
                this.wait(2);
                this.click("Uninstall");
                this.wait(2);
                this.click("OK");
            }

            this.wait(3);
            this.backOutToHomeScreen();
        }
    }*/

    // int looptimes = (++APPnumber);
    // for (int i = 1; i < looptimes; i++){
    // this.launchApp("com.android.vending",
    // "com.android.vending.AssetBrowserActivity");
    // this.wait(4);
    // if (!this.isViewWithIdPresent("search_button")){
    // Log.e(POWER_TAG, "The account is not login");
    // this.backOutToHomeScreen();
    // return;
    // }
    // this.clickId("search_button");
    // this.enterText("SE000"+i);
    // this.pressKey(KeyEvent.KEYCODE_ENTER);
    // this.wait(10);
    // if (this.isViewWithTextPresent("1. SE000"+i)){
    // this.click("1. SE000"+i);
    // this.wait(3);
    // // if (this.isViewWithIdPresent("bucket_items",0)){
    // // this.clickItemWithId("bucket_items", 0);
    // // this.wait(3);
    // if (this.isViewWithIdPresent("uninstall_button")){
    // this.clickId("uninstall_button");
    // this.wait(4);
    // if (this.isViewWithIdPresent("parentPanel")){
    // this.click("OK");
    // this.wait(1);
    // }
    // int j = 0;
    // while (!this.isViewWithIdPresent("buy_button") && j < 10){
    // this.wait(5);
    // j++;
    // }
    // if (!this.isViewWithIdPresent("buy_button")){
    // assertTrue(false);
    // }
    // else{
    // Log.e(POWER_TAG, "Successfully uninstall SE000"+i);
    // }
    // }
    // else{
    // Log.e(POWER_TAG, "SE000"+i+" is not installed" );
    // return;
    // }
    // }
    // else{
    // Log.e(POWER_TAG, "The page is not loaded" );
    // return;
    // }
    // // this.backOutToHomeScreen();

    /*public void clearAllWidgets() {
        this.backOutToHomeScreen();
        this.clearHomeWidgetsUPDATE();
        this.scrollRight();
        this.clearHomeWidgetsUPDATE();
        this.scrollRight();
        this.clearHomeWidgetsUPDATE();
        this.backOutToHomeScreen();
        this.wait(1);
        this.scrollLeft();
        this.clearHomeWidgetsUPDATE();
        this.scrollLeft();
        this.clearHomeWidgetsUPDATE();
        this.backOutToHomeScreen();
    }

	public void enterCameraVideoManualMode() {
		this.launchApp("com.sonyericsson.android.camera",
				"com.sonyericsson.android.camera.CameraActivity");
		this.wait(3);
		if (this.isViewWithTextPresent("Yes")
				&& this.isViewWithTextPresent("No")) {
			this.click("No");
		}
		this.assertViewWithIdPresent("mode_selector_button");
		this.clickItemWithId("mode_selector_button");
		this.wait(2);
		this.assertTextPresent("Manual");
		this.clickItemWithText("Manual");
		this.wait(2);
		this.assertViewWithIdPresent("sub_button");
		this.clickItemWithId("sub_button");
		this.wait(3);
	}

	public void enterCameraPictureManualMode() {
		this.launchApp("com.sonyericsson.android.camera",
				"com.sonyericsson.android.camera.CameraActivity");
		this.wait(3);
		if (this.isViewWithTextPresent("Remember photo locations?")
				&& this.isViewWithTextPresent("No")) {
			this.click("No");
		}
		this.assertViewWithIdPresent("mode_selector_button");
		this.clickItemWithId("mode_selector_button");
		this.wait(2);
		this.assertTextPresent("Manual");
		this.clickItemWithText("Manual");
		this.wait(3);
	}

	public String playMusicInSongsList(int songIndex) {
		this.launchApp("com.sonyericsson.music",
				"com.sonyericsson.music.MusicActivity");
		this.wait(2);

		if (this.isViewWithTextPresent("Songs")) {
			this.clickItemWithText("Songs");
		}
		this.waitForText("Shuffle all", 30, false);

		this.clickItemWithId("text1", songIndex);
		String songName = this.getTextFromViewWithId("text1", songIndex);
		this.wait(2);
		this.backOutToHomeScreen();
		return songName;
	}*/

	/*public void unPairBluetooth() {
		this.launchApp("com.android.settings", "com.android.settings.Settings");
		this.wait(3);
		if (!this.isCheckboxChecked("switchWidget", 1)) {
			this.clickItemWithId("switchWidget", 1);
			this.wait(2);
		}
		this.clickItemWithText("Bluetooth");
		this.wait(2);
		if (this.isViewWithIdPresent("deviceDetails")) {
			this.clickItemWithId("deviceDetails");
			this.wait(2);
			this.click("Unpair");
			this.wait(2);
		}
		this.pressKey(KeyEvent.KEYCODE_BACK);
		this.wait(2);
		if (!this.isCheckboxChecked("switchWidget", 1)) {
			this.clickItemWithId("switchWidget", 1);
			this.wait(2);
		}
		this.backOutToHomeScreen();
	}

	public void openNewStatusBar() {
		this.openStatusBar();
		this.wait(2);
		if (this.isViewWithTextPresent("Notifications")) {
			this.clickItemWithText("Notifications");
			this.wait(2);
		}
	}

	public void setBluetooth(String timeout) {
		if (hasSetBT)
			return;
		testPhoneDeviceId = this.getPhoneDeviceId();
		long endTime = this.getCurrTime() + 3 * 60 * 1000;
		this.sendSMS(callNumber, CommandConstantsUtils.COMMAND_PAIR_BT);
		this.openNewStatusBar();
		this.wait(2);
		while (this.getCurrTime() < endTime) {
			if (this.isViewWithTextPresent(callNumber)) {
				this.click(callNumber);
				this.wait(2);
				break;
			} else if (this
					.isViewWithTextPresent(callNumberPrefix + callNumber)) {
				this.click(callNumberPrefix + callNumber);
				this.wait(2);
				break;
			} else {
				this.wait(1);
			}
		}

		parterPhoneDeviceId = this.getTextFromViewWithId(
				"message_item_body_text", 1);
		this.wait(2);
		this.backOutToHomeScreen();

		this.launchApp("com.android.settings", "com.android.settings.Settings");
		this.wait(3);
		if (!this.isCheckboxChecked("switchWidget", 1)) {
			this.clickItemWithId("switchWidget", 1);
		}
		this.clickItemWithText("Bluetooth");
		this.wait(2);
		if (this.isViewWithIdPresent("Scan for devices")) {
			this.clickItemWithText("Scan for devices");
			this.wait(2);
		}

		this.pressKey(KeyEvent.KEYCODE_MENU);
		this.wait(2);
		this.clickItemWithText("Rename tablet");
		int len = this.getTextFromViewWithId("edittext").length();
		this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_FORWARD_DEL, len);
		this.wait(2);
		this.enterText(testPhoneDeviceId);
		this.wait(2);
		this.pressKey(KeyEvent.KEYCODE_BACK);
		this.wait(2);
		this.clickItemWithId("button1");
		this.wait(2);

		this.pressKey(KeyEvent.KEYCODE_MENU);
		this.wait(2);
		this.clickItemWithText("Visibility timeout");
		this.wait(2);
		this.clickItemWithText(timeout);
		this.wait(2);

		if (!this.getTextFromViewWithId("summary", 0).equalsIgnoreCase(
				"Visible to all nearby Bluetooth devices")) {
			this.clickItemWithId("summary", 0);
			this.wait(1);
		}
		if (this.isViewWithTextPresent(parterPhoneDeviceId)) {
			this.click(parterPhoneDeviceId);
			this.waitForIdViewCompoment("alertTitle", 20, true);
			if (this.isViewWithIdPresent("alertTitle")) {
				this.click("Pair");
				this.wait(5);
			}
		}

		hasSetBT = true;
		this.backOutToHomeScreen();
	}*/

	/*public void closeBluetooth() {
		this.launchApp("com.android.settings", "com.android.settings.Settings");
		this.wait(3);
		if (this.isCheckboxChecked("switchWidget", 1)) {
			this.clickItemWithId("switchWidget", 1);
			this.wait(2);
		}

		this.backOutToHomeScreen();
	}

	public void viewFileAndDelete(int type) {
		this.launchApp("com.mobisystems.office",
				"com.mobisystems.office.FileBrowser");
		this.wait(2);
		if(this.isViewWithTextPresent("Continue")&&this.isViewWithTextPresent("Upgrade")){
        	this.clickItemWithText("Continue");
        	this.wait(2);
		}
		if (this.isViewWithTextPresent("Register later")) {
			this.clickItemWithText("Register later");
			this.wait(2);
		}
		if (this.isViewWithTextPresent("Later")) {
			this.clickItemWithText("Later");
			this.wait(2);
		}
		this.scrollScreenLeft();
		this.wait(5);
		this.clickItemWithId("list_item_icon", 3);
		this.wait(2);
		this.clickItemWithText("bluetooth");
		this.wait(2);
		if (type == 0) {
			for (int i = 1; i < 51; i++) {
				this.clickItemWithId("list_item_icon", 0);
				this.wait(5);
				this.pressKey(KeyEvent.KEYCODE_BACK);
				this.wait(2);
				this.clickItemWithId("list_item_check", 0);
				this.wait(2);
				this.clickItemWithId("edit_delete");
				this.wait(2);
				this.clickItemWithText("OK");
				this.wait(2);
			}

		} else if (type == 1) {
			for (int i = 1; i < 51; i++) {
				this.clickItemWithId("list_item_icon", 0);
				this.wait(2);
				if (this.isViewWithTextPresent("Album")) {
					this.clickItemWithText("Album");
					this.wait(2);
					this.clickItemWithText("Just once");
					this.wait(2);
				}
				this.pressKey(KeyEvent.KEYCODE_BACK);
				this.wait(2);
				this.clickItemWithId("list_item_check", 0);
				this.wait(2);
				this.clickItemWithId("edit_delete");
				this.wait(2);
				this.clickItemWithText("OK");
				this.wait(2);
			}

		} else if (type == 2) {
			for (int i = 1; i < 51; i++) {
				this.clickItemWithId("list_item_icon", 0);
				this.wait(2);
				if (this.isViewWithTextPresent("WALKMAN")) {
					this.clickItemWithText("WALKMAN");
					this.wait(2);
					this.clickItemWithText("Just once");
					this.wait(2);
					this.clickItemWithId("player_play_pause_button");
					this.wait(2);
				}

				this.pressKeyForMultipleTimes(KeyEvent.KEYCODE_BACK, 2);
				this.wait(2);
				this.clickItemWithId("list_item_check", 0);
				this.wait(2);
				this.clickItemWithId("edit_delete");
				this.wait(2);
				this.clickItemWithText("OK");
				this.wait(2);
			}

		} else if (type == 3) {
			this.clickItemWithId("list_item_icon", 0);
			this.wait(2);
			boolean opennormally = false;
			while (this.isViewWithTextPresent("Import all")) {
				if (this.isViewWithTextPresent("50 of 50 contacts")) {
					opennormally = true;
					break;
				}
				this.wait(2);

			}
			this.assertTrue(opennormally);
			this.wait(2);
			this.clickItemWithId("list_item_check", 0);
			this.wait(2);
			this.clickItemWithId("edit_delete");
			this.wait(2);
			this.clickItemWithText("OK");
			this.wait(2);

		}
		if (!this.isViewWithTextPresent("This folder is empty")) {
			this.assertTrue(false);
		}
	}
	public void isloadingsuccessful(int secs, boolean allowFail) {
		boolean right = false;
		long endTime = System.currentTimeMillis() + secs * 1000;
		this.wait(15);
		while (System.currentTimeMillis() < endTime) {

			if (!this.isViewWithTextPresent("Retry")) {
				right = true;
				break;
			} else {
				this.click("Retry");
				this.wait(15);
			}
		}
		if (!allowFail) {
			assertEquals(true, right);
		}

	}*/

	/*public void deleteMessage() {
		this.backOutToHomeScreen();
		this.launchApp("com.sonyericsson.conversations",
				"com.sonyericsson.conversations.ui.ConversationListActivity");
		this.wait(4);
		if (this.isViewWithIdPresent("conversationlist_item_name", 0)) {
			this.pressKey(KeyEvent.KEYCODE_MENU);
			this.wait(2);
			this.clickItemWithText("Delete conversations");
			this.clickItemWithId("select_item_num");
			this.wait(2);
			this.clickItemWithText("Mark all");
			this.wait(2);
			this.clickItemWithId("menu_delete");
			this.wait(2);
			this.clickItemWithId("button1");
			this.wait(2);
		}
		this.backOutToHomeScreen();
	}

	public void enterCameraVideoManualModeByCamButton() {
		this.longPressKey(KeyEvent.KEYCODE_CAMERA, 3);
		this.wait(3);
		if (this.isViewWithTextPresent("Remember photo locations?")
				&& this.isViewWithTextPresent("No")) {
			this.click("No");
		}
		this.assertViewWithIdPresent("mode_selector_button");
		this.clickItemWithId("mode_selector_button");
		this.wait(2);
		this.assertTextPresent("Manual");
		this.clickItemWithText("Manual");
		this.wait(2);
		this.assertViewWithIdPresent("sub_button");
		this.clickItemWithId("sub_button");
		this.wait(3);
	}

	public void enterCameraPictureAutoMode() {
		this.launchApp("com.sonyericsson.android.camera",
				"com.sonyericsson.android.camera.CameraActivity");
		this.wait(3);
		if (this.isViewWithTextPresent("Remember photo locations?")
				&& this.isViewWithTextPresent("No")) {
			this.click("No");
		}
		this.assertViewWithIdPresent("mode_selector_button");
		this.clickItemWithId("mode_selector_button");
		Log.d(POWER_TAG, "Click mode_selector_button");
		this.wait(2);
		this.assertTextPresent("Superior auto");
		this.clickItemWithText("Superior auto");
		Log.d(POWER_TAG, "Click Superior auto");
		this.wait(3);
	}

	public void deleteByOffice(String FileName){
    	this.launchApp("com.mobisystems.office", "com.mobisystems.office.FileBrowser");
        this.wait(2);
        if(this.isViewWithTextPresent("Continue")&&this.isViewWithTextPresent("Upgrade")){
        	this.clickItemWithText("Continue");
        	this.wait(2);
		}
        if (this.isViewWithTextPresent("Register later")) {
            this.clickItemWithText("Register later");
            this.wait(2);
        }
        if (this.isViewWithTextPresent("Later")) {
            this.clickItemWithText("Later");
            this.wait(2);
        }
        if(!this.isViewWithTextPresent("Internal storage")){
        	this.scrollScreenLeft();
        	this.wait(5);
        	this.clickItemWithText("Internal storage");
        	this.wait(2);
        }

        this.clickItemWithText(FileName);
        this.wait(2);
        this.clickItemWithId("edit_menu");
        this.clickItemWithText("Select All");
        this.wait(2);
        this.clickItemWithId("edit_delete");
        this.wait(5);
        this.clickItemWithText("OK");
        this.backOutToHomeScreen();
    }

	public void waitForNetworkConnect(int sec, int mode) {
		this.backOutToHomeScreen();
		long endTime = this.getCurrTime() + sec * 1000;
		this.launchApp("com.android.settings", "com.android.settings.Settings");
		this.waitForActivitySwitch();
		for (int k = 0; k < 4; k++) {
			this.scrollDown();
			this.wait(1);
		}
		this.clickItemWithText("About tablet");
		this.wait(1);
		this.clickItemWithText("Status");

		while (this.getCurrTime() < endTime) {
			if (!this.isViewWithTextPresent("In service")) {
				this.wait(10);
			} else {
				break;
			}
		}
		this.backOutToHomeScreen();

		switch (mode) {
		case 2:
			assertTrue(this.is2gStatus());
			break;
		case 3:
			assertTrue(this.is3gStatus());
			break;
		case 4:
			assertTrue(this.is4gStatus());
			break;
		}
	}*/

	public long getTotalRAMMemory() {
		 String str1 = "/proc/meminfo";
	     String str2;
	     String[] arrayOfString;
	     long initialRAMmemory = 0;

	        try
	        {
	            FileReader localFileReader = new FileReader(str1);
	            BufferedReader localBufferedReader = new BufferedReader(
	            localFileReader, 8192);
	            str2 = localBufferedReader.readLine();

	            arrayOfString = str2.split("\\s+");
	            for (String num : arrayOfString) {
	                Log.i(str2, num + "\t");
	            }

	            initialRAMmemory = Integer.valueOf(arrayOfString[1]).intValue();
	            localBufferedReader.close();

	        } catch (IOException e) {
	        }

	        return initialRAMmemory/(1024*1024);
	}

	/*public long getAvailRAMMemory() {
		Context mContext = getInstrumentation().getContext();

        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);

        return mi.availMem/(1024*1024);
	}

	public void insetSMS(String number, String text, int smsBoxType, int read) {
		ContentValues values = new ContentValues();
		values.put("date", System.currentTimeMillis());
		values.put("read", read); // 0:unread; 1:read
		values.put("type", smsBoxType);// inbox:1; sent:2; outbox:4;
		values.put("address", number);
		values.put("body", text);
		getInstrumentation().getContext().getContentResolver()
				.insert(Uri.parse("content://sms"), values);
	}

	public int getSmsCount(int smsBoxType, int read) {
		Cursor csr = null;
		int size = 0;
		try {
			Context context = getInstrumentation().getContext();
			String key = "type = " + smsBoxType + " and read = " + read;
			csr = context.getApplicationContext().getContentResolver()
					.query(Uri.parse("content://sms"), null, key, null, null);
			size = csr.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			csr.close();
		}
		return size;
	}*/
}
