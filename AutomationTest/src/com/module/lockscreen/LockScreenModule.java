package com.module.lockscreen;

import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.view.KeyEvent;

public class LockScreenModule implements ILockScreen{

    public AcceptanceTestCase testCase;
    public CommonModule commonModule;

    public  LockScreenModule (AcceptanceTestCase testCase){
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);
    }

    public void lockScreen(){
        if(testCase.isScreenOn()){
            //set screen off first
            testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_POWER);
            commonModule.wait(2);
        }

        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_POWER);
    }

    public void unlockScreen(){
        if(commonModule.waitForText("Swipe to unlock", 3000)){
            testCase.scrollDown();
        }
    }

    public void litScreen(){
        if(!testCase.isScreenOn()){
            testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_POWER);
        }
    }

    public void verifyScreenLocked(){
        AcceptanceTestCase.assertTrue("text 'Swipe to unlock' should display on the locked screen",
                commonModule.waitForText("Swipe to unlock", 3000));
    }

    public void tryToUnlockScreenWithVirtualKey(){
        //press menu key to unlock but failed
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_MENU);
        verifyScreenLocked();

        //press back key to unlock but failed
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_BACK);
        verifyScreenLocked();

        //press home key to unlock but failed
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_HOME);
        verifyScreenLocked();
    }

    public void tryToUnlockScreenWithHWKey(){
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_VOLUME_UP);
        verifyScreenLocked();

        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_VOLUME_DOWN);
        verifyScreenLocked();

        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_CAMERA);
        verifyScreenLocked();
    }

    public void tryToUnlockScreenBySwipeButNotReachTheGoalPosition(){
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();
        testCase.getUiDevice().swipe(width/2, 50, width/2, height/5, 100);
        commonModule.wait(3);

        verifyScreenLocked();
    }

    public void longPressPowerKeyAndCheck(){
        testCase.longPressKey(KeyEvent.KEYCODE_POWER, 4000);

        AcceptanceTestCase.assertTrue("item 'Power off' should display when long press power key", commonModule.waitForText("Power off", 5000));
    }
}
