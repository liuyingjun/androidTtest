
package com.utils;

import java.io.IOException;

import android.util.Log;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestUIWatcher extends UiAutomatorTestCase {

    public void watch() throws UiObjectNotFoundException, IOException {

        this.exitCameraWhenCameraHot();// Camera hot

        this.exitCameraWhenCameraTemperature();

        this.dismissCrashFileWarning();// Crash file warning appear

        this.dismissDownloadMicrosoftFontPackWarning();

        this.dismissMissingVoiceMailNumberWarning();

        this.dismissMissingKeyboardWarning();

        this.disablePersonalizeYourKeyboard();

        // cancel part of listener
        // UiDevice.getInstance().removeWatcher("cameraHot");

        // UiDevice.getInstance().runWatchers();

        // UiDevice.getInstance().resetWatcherTriggers();

        boolean cameraHot = UiDevice.getInstance().hasWatcherTriggered("cameraHot");
        boolean cameraTemperature = UiDevice.getInstance().hasWatcherTriggered("cameraTemperature");
        boolean crashFileWarning = UiDevice.getInstance().hasWatcherTriggered("crashFileWarning");
        boolean personalizeKeyboard = UiDevice.getInstance().hasWatcherTriggered(
                "personalizeKeyboard");

        if (cameraHot == true) {
            Log.d("UIWather", "Camera listener has been triggered.");
        }

        if (cameraTemperature == true) {
            Log.d("UIWather", "Camera listener has been triggered.");
        }

        if (crashFileWarning == true) {
            Log.d("UIWather", "Crash file warning listener has been triggered.");
        }

        if (personalizeKeyboard == true) {
            Log.d("UIWather", "personalizeKeyboard listener has been triggered.");
        }

    }

    public void removeWatchers() {
        getUiDevice().removeWatcher("cameraHot");
        getUiDevice().removeWatcher("cameraTemperature");
        getUiDevice().removeWatcher("crashFileWarning");
        getUiDevice().removeWatcher("downloadFontPack");
        getUiDevice().removeWatcher("noVoicemailNumberSaved");
        getUiDevice().removeWatcher("keyboard");
    }

    /**
     * The camera is hot , cannot use normally.
     */
    public void exitCameraWhenCameraHot() {
        UiDevice.getInstance().registerWatcher("cameraHot", new UiWatcher() {

            UiObject cameraHotObject = new UiObject(new UiSelector().text("Attention"));

            @Override
            public boolean checkForCondition() {
                System.out.println("The listener starts running-EXIT CAMERA WHEN CAMNERA HOT");
                // sleep(3000);
                if (cameraHotObject.exists()) {
                    System.out.println("listener successful--exit camera");
                    UiObject ok_button = new UiObject(new UiSelector().text("OK"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                System.out.println("listener unsuccessful--exit camera");
                return false;
            }
        });
    }

    /**
     * The camera is hot , cannot use normally.
     */
    public void exitCameraWhenCameraTemperature() {
        UiDevice.getInstance().registerWatcher("cameraTemperature", new UiWatcher() {

            UiObject cameraHotObject = new UiObject(new UiSelector()
                    .textContains("The device temperature is rising."));

            @Override
            public boolean checkForCondition() {
                System.out.println("The listener starts running-EXIT CAMERA WHEN CAMNERA HOT");
                // sleep(3000);
                if (cameraHotObject.exists()) {
                    System.out.println("listener successful--exit camera");
                    UiObject ok_button = new UiObject(new UiSelector().text("OK"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                System.out.println("listener unsuccessful--exit camera");
                return false;
            }
        });
    }

    /**
     * Crash file warning pop-up, dismiss this warning
     */
    public void dismissCrashFileWarning() {
        UiDevice.getInstance().registerWatcher("crashFileWarning", new UiWatcher() {

            UiObject crashFileObject = new UiObject(new UiSelector().text("Reminder"));

            @Override
            public boolean checkForCondition() {
                System.out.println("The listener starts running-DISMISS CRASH FILES WARNING");
                // sleep(3000);
                if (crashFileObject.exists()) {
                    System.out.println("listener successful--dismiss crash file");
                    UiObject ok_button = new UiObject(new UiSelector().text("OK"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                System.out.println("listener unsuccessful--dismiss crash file warning");
                return false;
            }
        });
    }

    public void dismissDownloadMicrosoftFontPackWarning() {
        UiDevice.getInstance().registerWatcher("downloadFontPack", new UiWatcher() {

            UiObject warningObject = new UiObject(
                    new UiSelector()
                            .text("For genuine viewing of your documents download Microsoft Campatibility Font Pack."));

            @Override
            public boolean checkForCondition() {
                // sleep(3000);
                if (warningObject.exists()) {
                    UiObject ok_button = new UiObject(new UiSelector().text("Later"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            }
        });
    }

    public void dismissMissingVoiceMailNumberWarning() {
        UiDevice.getInstance().registerWatcher("noVoicemailNumberSaved", new UiWatcher() {

            UiObject warningObject = new UiObject(new UiSelector()
                    .text("No voicemail number is saved on the SIM card."));

            @Override
            public boolean checkForCondition() {
                // sleep(3000);
                if (warningObject.exists()) {
                    UiObject ok_button = new UiObject(new UiSelector().text("OK"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            }
        });
    }

    public void dismissMissingKeyboardWarning() {
        UiDevice.getInstance().registerWatcher("keyboard", new UiWatcher() {

            UiObject warningObject = new UiObject(new UiSelector()
                    .textContains("To personalize the keyboard later,"));

            @Override
            public boolean checkForCondition() {
                // sleep(3000);
                if (warningObject.exists()) {
                    UiObject ok_button = new UiObject(new UiSelector().text("OK"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            }
        });
    }

    /**
     * The camera is hot , cannot use normally.
     */
    public void disablePersonalizeYourKeyboard() {
        UiDevice.getInstance().registerWatcher("personalizeKeyboard", new UiWatcher() {

            UiObject warningObject = new UiObject(new UiSelector()
                    .text("Personalize your keyboard?"));

            @Override
            public boolean checkForCondition() {
                // sleep(3000);
                if (warningObject.exists()) {
                    System.out.println("listener successful--exit camera");
                    UiObject ok_button = new UiObject(new UiSelector().text("Cancel"));
                    try {
                        ok_button.click();
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                System.out.println("listener unsuccessful--disable personalize your keyboard.");
                return false;
            }
        });
    }

}
