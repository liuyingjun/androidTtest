
package com.module.smallApp;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.graphics.Rect;
import android.view.KeyEvent;

public class SmallAppModule implements ISmallApp {
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    public SmallAppModule(AcceptanceTestCase testCase) {
        // TODO Auto-generated constructor stub
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);
    }

    public void openSmallAppList() throws UiObjectNotFoundException {
        commonModule.openRecentApp();

        if (commonModule.waitForResourceId("com.sony.smallapp.launcher:id/expand_button", 2000)) {
            commonModule.clickResourceId("com.sony.smallapp.launcher:id/expand_button");
        } else {
            commonModule.clickDescription("Open the list of small apps");
        }
        commonModule.waitForText("Small apps", 2000);

        if (commonModule.waitForText("Favorites bar", 5000)) {
            commonModule.wait(2);
            commonModule.clickDescription("Close hints");// Close "Favorites bar".
            commonModule.waitForTextGone("Favorites bar", 2000);
        }
        commonModule.wait(3);
    }

    public void selectOneSmallApp(String appName) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Small apps") && !commonModule.isTextExists(appName)) {
            openSmallAppList();
        }
        AcceptanceTestCase.assertTrue(appName + "not display!",
                commonModule.waitForResourceId("com.sony.smallapp.launcher:id/icon_view", 5000));

        for (int i = 0; i < 5; i++) {
            if (!commonModule.isResourceId("com.sony.smallapp.launcher:id/launcher_icon")) {
                break;
            }
            commonModule.clickTextContains(appName);
            if (i == 4 && !commonModule.isResourceId("com.sony.smallapp.launcher:id/launcher_icon")) {
                testCase.failTest("Tool can't capture small app.");
            }
        }
        commonModule.wait(1);
    }

    public void moveSmallApp(String appName) throws UiObjectNotFoundException {
        Rect rec = commonModule.getCoordinateByText(appName);
        int x = rec.centerX();
        int y = rec.centerY();
        testCase.dragViewBetweenTwoPosition(x, y, x, y + 200, 30);
        testCase.dragViewBetweenTwoPosition(x, y + 200, x, y, 30);
    }

    public void focusUnderlayingApp() {
        int[] screen = testCase.getScreenSize();
        testCase.clickPoint(screen[0] - 100, screen[1] - 200);
    }

    public void focusSmallApp() {
        testCase.clickPoint(100, 100);
    }

    public void closeSmallApps() throws UiObjectNotFoundException {
        for (int i = 0; i < 10; i++) {
            if (!commonModule.isDescriptionContains("will be closed")) {
                focusSmallApp();
            }
            if (commonModule.isDescriptionContains("will be closed")) {
                commonModule.clickDescriptionContains("will be closed");
            } else {
                break;
            }
        }
    }

    public void enterBrowserAndEnterWebPage() throws UiObjectNotFoundException {
        selectOneSmallApp("Browser");

        commonModule.clickResourceId("com.sonymobile.smallbrowser:id/urlbar_focused");
        testCase.enterText("www.baidu.com");
        testCase.enterText("\n");
        commonModule.wait(3);

        closeSmallApps();
    }

    public void addSmallApp(String appName) throws UiObjectNotFoundException{
        openSmallAppList();

        for (int i = 0; i < 5; i++) {
            if (commonModule.isTextExists("Widgets")) {
                break;
            }
            if (commonModule.waitForResourceId("com.sony.smallapp.launcher:id/addButton", 2000)) {
                commonModule.clickResourceId("com.sony.smallapp.launcher:id/addButton");
            } else if (commonModule.isDescriptionExists("Add small apps")) {
                commonModule.clickDescription("Add small apps");
            } else {
                testCase.clickPoint(commonModule.getX(830, 1080), commonModule.getY(150, 1920));
            }

            if (i == 4 && !commonModule.isTextExists("Widgets")) {
                testCase.failTest("Tool can't verify Add small apps icon.");
            }
        }
        AcceptanceTestCase.assertTrue("Tap add widget icon failed.",
                commonModule.waitForText("Widgets", 2000));

        commonModule.clickText("Widgets");
        commonModule.waitForText("Select widget", 5000);

        if (!commonModule.isTextExists(appName)) {
            commonModule.scrollFindTextNotClick(appName);
        }
        commonModule.clickText(appName);

        if (commonModule.isTextExists("Rename")) {
            commonModule.clickText("OK");
        }

        AcceptanceTestCase.assertTrue(
                "Add " + appName + " small app failed.",
                commonModule.waitForText(appName, 2000)
                        && commonModule.waitForText("Small apps", 2000));
    }

    public void deleteWidget(String... apps) throws UiObjectNotFoundException{
        if (!commonModule.isTextExists("Small apps")) {
            openSmallAppList();
        }

        for (int i = 0; i < 5; i++) {
            if (commonModule.isTextExists("Select a widget you wish to delete")) {
                break;
            }
            if (commonModule.isResourceId("com.sony.smallapp.launcher:id/menuButton")) {
                commonModule.clickResourceId("com.sony.smallapp.launcher:id/menuButton");
            } else if (commonModule.isDescriptionExists("Options menu")) {
                commonModule.clickDescription("Options menu");
            } else {
                testCase.clickPoint(commonModule.getX(1000, 1080), commonModule.getY(150, 1920));
            }
            commonModule.waitForText("Delete widget", 2000);

            commonModule.clickText("Delete widget");

            if (i == 4 && !commonModule.isTextExists("Select a widget you wish to delete")) {
                testCase.failTest("Tool can't capture more option icon.");
            }
        }

        // Delete widget.
        for (String app : apps) {

            if (commonModule.isTextExists(app)) {
                commonModule.clickText(app);
                commonModule.waitForText("Delete widget", 2000);

                commonModule.clickText("OK");
            } else {
                break;
            }
        }
    }
}
