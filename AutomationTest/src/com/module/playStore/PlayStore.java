package com.module.playStore;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.util.Log;
import android.view.KeyEvent;

import java.util.HashMap;

public class PlayStore implements IPlayStore{

    private static final String TAG = "Reliability";
    public AcceptanceTestCase testCase;
    public CommonModule commonModule;

    public HashMap<String, String> moduleData;

    public PlayStore(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("playstore");
    }

    public void launchPlayStore(){
        testCase.launchApp("com.android.vending", "com.android.vending.AssetBrowserActivity");

        commonModule.wait(3);
    }

    public void searchAndInstallAppInPlayStore(String appName) throws UiObjectNotFoundException{
        launchPlayStore();

        commonModule.clickResourceId(moduleData.get("Search_button_id"));
        commonModule.wait(1);

        testCase.enterText(appName);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);

        commonModule.waitForText(appName, 10*1000);
        commonModule.clickText(appName);

        if(!commonModule.waitForText("INSTALL",10*1000)){
            commonModule.clickText(appName);
        }

        AcceptanceTestCase.assertTrue("Install button didn't display", commonModule.waitForText("INSTALL", 10*1000));
        commonModule.clickText("INSTALL");

        if(commonModule.waitForText("ACCEPT", 5*1000)){
            commonModule.clickText("ACCEPT");
        }

        commonModule.clearNotificationsBar();
        commonModule.wait(3);
        commonModule.openNotificationBar();

        AcceptanceTestCase.assertTrue("The app should be download completed and installed automaticly, but ont",
                commonModule.waitForText("Successfully installed." , 5*60*1000));


    }
    
    public void playGame(int minute){
        testCase
                .launchApp("org.cocos2dx.FishingJoy2", "org.cocos2dx.FishingJoy2.FishingJoy2");
        commonModule.acquireWakelock();
        if (testCase.isViewWithTextPresent("取消")){
        	testCase.click("取消");
        }
        else{
            Log.d(TAG, "no cancel button");
        }
        /** load the game */
        commonModule.wait(30);
        /** start game button pointer */
        testCase.clickPoint(commonModule.getX(700, 1794), commonModule.getY(700, 1080));
        commonModule.wait(10);
        /** select the map button pointer */
        testCase.clickPoint(commonModule.getX(260, 1794), commonModule.getY(800, 1080));
        commonModule.wait(30);
        /** select hao time to use commonModule game pointer */
        testCase.clickPoint(commonModule.getX(700, 1794), commonModule.getY(950, 1080));
        commonModule.wait(10);
        /** click sue button pointer */
        testCase.clickPoint(commonModule.getX(900, 1794), commonModule.getY(780, 1080));
        commonModule.wait(5);
        /** play the game */
        for (int k = 0; k < minute; k++) {
        	testCase.clickPoint(commonModule.getX(900, 1794), commonModule.getY(780, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(500, 1794), commonModule.getY(500, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(600, 1794), commonModule.getY(950, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(500, 1794), commonModule.getY(500, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1500, 1794), commonModule.getY(550, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1200, 1794), commonModule.getY(450, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1100, 1794), commonModule.getY(300, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1400, 1794), commonModule.getY(620, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1600, 1794), commonModule.getY(560, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1540, 1794), commonModule.getY(205, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1470, 1794), commonModule.getY(360, 1080));
            commonModule.wait(5);
            testCase.clickPoint(commonModule.getX(1350, 1794), commonModule.getY(280, 1080));
            commonModule.wait(5);
        }
    }
}
