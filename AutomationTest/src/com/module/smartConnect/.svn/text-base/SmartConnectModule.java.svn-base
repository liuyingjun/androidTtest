
package com.module.smartConnect;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.util.Log;
import android.view.KeyEvent;

public class SmartConnectModule implements ISmartConnect {

    AcceptanceTestCase testCase;

    CommonModule commonModule;

    String TAG = "Reliability";

    public SmartConnectModule(AcceptanceTestCase testCase) {
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
    }

    public void launchSmartConnect() throws UiObjectNotFoundException {
        testCase.launchApp("com.sonyericsson.extras.liveware",
                "com.sonyericsson.extras.liveware.ui.LaunchActivity");
        commonModule.wait(2);
        if (commonModule.isTextExists("OK")) {
            commonModule.clickText("OK");
        }

        AcceptanceTestCase.assertTrue(
                "Launch Smart Connect failed.",
                commonModule.waitForText("Devices", 2000)
                        && commonModule.waitForText("Events", 2000));
    }

    public void addNewEvent(String eventName) throws UiObjectNotFoundException {
        commonModule.clickText("Events");
        commonModule.wait(2);

        commonModule.clickDescription("Add event");
        if (commonModule.waitForText("Create new event", 2000)) {
            commonModule.clickText("OK");
        }
        commonModule.waitForText("Conditions", 2000);

        commonModule.clickText("Add time");
        commonModule.waitForText("Between:", 2000);

        commonModule
                .clickResourceId("com.sonyericsson.extras.liveware:id/time_trigger_button_left");
        commonModule.waitForText("Set time", 2000);

        // UiObject obj = new UiObject(new
        // UiSelector().className("android.widget.Button").instance(3));
        // obj.click();// add one minute.
        // obj.click();// add one minute.

        commonModule.clickText("Set");
        commonModule.waitForText("Done", 2000);

        commonModule.clickText("Done");
        commonModule.waitForDescription("Next", 2000);

        commonModule.clickDescription("Next");
        commonModule.waitForText("Actions", 2000);

        commonModule.clickText("Add start action");
        commonModule.waitForText("Go to Home screen", 2000);

        commonModule.clickText("Go to Home screen");
        commonModule.waitForText("Actions", 2000);

        commonModule.clickText("Add end action");
        commonModule.waitForText("Go to Home screen", 2000);

        commonModule.scrollFindText("Sound mode");
        commonModule.waitForText("Normal", 2000);

        commonModule.clickText("Normal");
        commonModule.waitForText("Actions", 2000);

        commonModule.clickDescription("Next");
        commonModule.waitForText("Event name:", 2000);

        commonModule.clickResourceId("com.sonyericsson.extras.liveware:id/eventName");
        testCase.enterText(eventName);
        testCase.enterText("\n");
        commonModule.wait(1);

        commonModule.clickText("Finish");
        AcceptanceTestCase.assertTrue(
                "Add a new event failed.",
                commonModule.waitForText(eventName, 2000)
                        && commonModule.waitForText("Smart Connect", 2000));
    }

    public void renameEvent(String eventName, String rename) throws UiObjectNotFoundException {
        testCase.longPressItemWithText(eventName);
        commonModule.waitForText("Rename event", 2000);

        commonModule.clickText("Rename event");
        commonModule.waitForText("Rename", 2000);

        testCase.enterText(rename);
        commonModule.clickText("Rename");
        AcceptanceTestCase.assertTrue(
                "Rename event failed.",
                !commonModule.waitForText(eventName, 2000)
                        && commonModule.waitForText(rename, 2000)
                        && commonModule.waitForText("Smart Connect", 2000));
    }

    public void editEvent(String eventName) throws UiObjectNotFoundException {
        commonModule.clickText(eventName);
        commonModule.waitForText("Edit", 2000);

        commonModule.clickText("Edit");
        commonModule.waitForText("Conditions", 2000);

        // Reset time.
        UiObject time = new UiObject(new UiSelector().resourceId(
                "com.sonyericsson.extras.liveware:id/list_item").instance(1));
        time.click();// Press time item.
        commonModule.waitForText("Between:", 2000);

        commonModule
                .clickResourceId("com.sonyericsson.extras.liveware:id/time_trigger_button_left");
        commonModule.waitForText("Set time", 2000);

        int hour = Integer.valueOf(testCase.getTextFromViewWithId("numberpicker_input"));
        Log.i("Current hour", "-----> " + hour);
        int minute = Integer.valueOf(testCase.getTextFromViewWithId("numberpicker_input", 1));
        Log.i("Current minute", "-----> " + minute);

        // Set time : Hour and minute
        if (minute == 59) {
            testCase.clickItemWithId("numberpicker_input");
            if (hour == 23) {
                Log.i("HOUR", "----------> " + 00);
                testCase.enterText("00");
            } else {
                Log.i("HOUR", "----------> " + hour);
                ;
            }
            Log.i("MINUTE", "----------> " + 02);
            testCase.clickItemWithId("numberpicker_input", 1);
            testCase.enterText("02");
        } else {
            Log.i("HOUR", "----------> " + hour);
            Log.i("MINUTE", "----------> " + (minute + 3));
            testCase.clickItemWithId("numberpicker_input", 1);
            testCase.enterText(String.valueOf(minute + 3));
        }
        testCase.click("Set");
        commonModule.wait(1);

        testCase.click("Done");
        commonModule.waitForDescription("Next", 2000);

        commonModule.clickDescription("Next");
        commonModule.waitForText("Actions", 2000);

        // Delete item.
        testCase.longPressItemWithText("Sound mode");
        commonModule.waitForText("Delete", 2000);

        commonModule.clickText("Delete");
        AcceptanceTestCase.assertTrue("Delete item failed",
                !commonModule.isTextExists("Sound mode"));

        commonModule.clickText("Finish");
        commonModule.waitForText("Edit", 2000);
    }

    public boolean checkEvent(int timeout) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (commonModule.isDescriptionExists("Swipe right to open application screen menu")
                    || commonModule.isDescriptionContains("Home screen")) {
                return true;
            } else {
                testCase.sleep(1000);
            }
        }
        return false;
    }

    public void deleteEvent(String eventName) throws UiObjectNotFoundException {
        launchSmartConnect();
        if (!commonModule.isTextExists(eventName)) {
            return;
        }
        commonModule.waitForText(eventName, 2000);

        testCase.longPressItemWithText(eventName);
        commonModule.waitForText("Delete event", 2000);

        commonModule.clickText("Delete event");
        if (commonModule.waitForText("Delete event New event?", 2000)) {
            commonModule.clickText("Delete");
        }
        AcceptanceTestCase.assertTrue("Delete event failed.", !commonModule.isTextExists(eventName)
                && commonModule.waitForText("Smart Connect", 2000));
    }
}
