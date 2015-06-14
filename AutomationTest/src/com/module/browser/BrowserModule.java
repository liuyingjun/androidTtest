
package com.module.browser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import junit.framework.TestCase;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;


public class BrowserModule implements IBrowser {
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    String TAG = "Reliability";

    public BrowserModule(AcceptanceTestCase testCase)  throws PropertyNotFoundException {
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
    }

	@Override
	public void launchChrome() {
		commonModule.launchAppBySearch("Chrome");
	}

	@Override
	public void downloadFileWithChrome(String url) throws UiObjectNotFoundException{
	    startChrom();

		commonModule.waitForId("url_bar", 60);
		testCase.clickId("url_bar");
		testCase.enterText(url);
		commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
		commonModule.wait(5);
		commonModule.waitForId("url_action_button", 100);
	}

    public void startChrom() throws UiObjectNotFoundException {
        testCase.launchApp("com.android.chrome", "com.google.android.apps.chrome.Main");
        if(commonModule.waitForText("You're invited", 2000)) {
            commonModule.clickDescription("Close");
        }

        if (commonModule.waitForResourceId("com.android.chrome:id/terms_accept", 5000)) {
            commonModule.clickResourceId("com.android.chrome:id/terms_accept");
            commonModule.waitForText("No thanks", 2000);

            commonModule.clickText("No thanks");
        }
        AcceptanceTestCase.assertTrue("Launch Chrom failed.",
                commonModule.waitForResourceId("com.android.chrome:id/location_bar", 3000));
    }

    public void waitForWebPageLoadSuccessfully() throws UiObjectNotFoundException {
        for (int i = 0; i < 10; i++) {

            if (commonModule.isDescriptionExists("Stop page loading")) {
                commonModule.wait(3);
            } else if (commonModule.isDescriptionExists("Refresh page")) {
                break;
            } else if (commonModule.isDescriptionExists("You are offline. Heading")) {
                commonModule.clickDescription("Refresh page");
                commonModule.wait(10);
                if (commonModule.isDescriptionExists("You are offline. Heading")) {
                    testCase.failTest("Offline--Check your internet settings");
                }
            }
        }
        AcceptanceTestCase.assertTrue("Load web page failed.",
                commonModule.isDescriptionExists("Refresh page"));
    }

    public void loadWebPage(String url) throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.chrome:id/url_bar");
        testCase.enterText(url);
        testCase.enterText("\n");

        waitForWebPageLoadSuccessfully();
    }

    public void addBookmarkButNotSave() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForDescription("Bookmark page", 2000);

        commonModule.clickDescription("Bookmark page");
        AcceptanceTestCase.assertTrue(
                "Add bookmark page not open.",
                commonModule.waitForText("Add bookmark", 2000)
                        && commonModule.waitForText("Cancel", 2000));

        commonModule.clickText("Cancel");
        commonModule.wait(1);
    }

    public void addBroswerBookmark(String bookmark) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForDescription("Bookmark page", 2000);

        commonModule.clickDescription("Bookmark page");
        AcceptanceTestCase.assertTrue("Add bookmark page not open.",
                commonModule.waitForText("Cancel", 2000)
                        && commonModule.waitForText("Save", 2000));

        commonModule.clickResourceId("com.android.chrome:id/bookmark_title_input");
        commonModule.emptyEditorByInstance(0);
        testCase.enterText(bookmark);
        testCase.assertTextPresent(bookmark);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForText("Save", 2000);

        commonModule.scrollFindText("Save");
        commonModule.wait(1);

        commonModule.pressMoreOption();
        commonModule.waitForText("Bookmarks", 2000);

        commonModule.clickText("Bookmarks");
        AcceptanceTestCase.assertTrue("Bookmark add failed.",
                commonModule.waitForText(bookmark, 2000)
                        && commonModule.waitForResourceId(
                                "com.android.chrome:id/bookmark_folder_structure", 2000));
    }

    public void reopenFromHistory(String url) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForText("History", 2000);

        commonModule.clickText("History");
        commonModule.waitForText(url, 2000);

        commonModule.clickText(url);
        for (int i = 0; i < 10; i++) {
            if (commonModule.isDescriptionExists("Stop page loading")) {
                commonModule.wait(3);
            } else if (commonModule.isDescriptionExists("Refresh page")) {
                break;
            } else if (commonModule.isDescriptionExists("You are offline. Heading")) {
                commonModule.clickDescription("Refresh page");
                commonModule.wait(10);
                if (commonModule.isDescriptionExists("You are offline. Heading")) {
                    testCase.failTest("Offline--Check your internet settings");
                }
            }
        }
        AcceptanceTestCase.assertTrue("Reopen web page failed.",
                commonModule.isDescriptionExists("Refresh page"));
    }

    public void deleteBookmark(String bookmark) throws UiObjectNotFoundException {
        startChrom();

        commonModule.pressMoreOption();
        commonModule.waitForText("Bookmarks", 2000);

        commonModule.clickText("Bookmarks");
        if (!commonModule.waitForText(bookmark, 2000)) {
            return;
        }
        commonModule.waitForText(bookmark, 2000);

        testCase.longPressItemWithText(bookmark);
        commonModule.waitForText("Delete bookmark", 2000);

        commonModule.clickText("Delete bookmark");
        AcceptanceTestCase.assertTrue("Delete bookmark failed.",
                !commonModule.isTextExists(bookmark));
    }

    public void openBookmarks() throws UiObjectNotFoundException {
        startChrom();

        commonModule.pressMoreOption();
        commonModule.waitForText("Bookmarks", 2000);

        commonModule.clickText("Bookmarks");
        commonModule.waitForResourceId("com.android.chrome:id/bookmark_folder_structure", 2000);
    }

    public void prepareEditBookmark(String bookmark) throws UiObjectNotFoundException {
        openBookmarks();

        testCase.longPressItemWithText(bookmark);
        commonModule.waitForText("Edit bookmark", 2000);

        commonModule.clickText("Edit bookmark");
        commonModule.waitForText("Save", 2000);
    }

    public void editBookmarkUrl(String bookmark, String newUrl) throws UiObjectNotFoundException{
        if (!commonModule.isTextExists("Save")) {
            prepareEditBookmark(bookmark);
        }

        commonModule.emptyEditorByInstance(1);
        testCase.enterText(newUrl);

        commonModule.clickResourceId("com.android.chrome:id/ok");// Press save button.
        commonModule.waitForResourceId("com.android.chrome:id/bookmark_folder_structure", 2000);
    }

    public void clearBookmarkNameAndSave(String bookmark) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Save")) {
            prepareEditBookmark(bookmark);
        }
        commonModule.emptyEditorByInstance(0);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Make keyboard disappeared.

        commonModule.clickResourceId("com.android.chrome:id/ok");// Press save button.
        AcceptanceTestCase.assertTrue("Bookmark saved without name !",
                !commonModule.waitForResourceId("com.android.chrome:id/bookmark_folder_structure",
                        2000) && commonModule.isTextExists("Edit bookmark"));
    }

    public void editBookmarkName(String nameBefore, String nameAfter) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Save")) {
            prepareEditBookmark(nameBefore);
        }

        commonModule.emptyEditorByInstance(0);
        testCase.enterText(nameAfter);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);// Make keyboard disappeared.

        commonModule.clickResourceId("com.android.chrome:id/ok");// Press save button.
        AcceptanceTestCase.assertTrue("Edit bookmark name failed.",
                commonModule.waitForResourceId("com.android.chrome:id/bookmark_folder_structure",
                        2000) && commonModule.waitForText(nameAfter, 2000));
    }

    @Override
    public void loadBookmark(String bookmark) throws UiObjectNotFoundException {
        openBookmarks();

        commonModule.clickText(bookmark);

        waitForWebPageLoadSuccessfully();
    }

    public void backwardWebPage() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForDescription("Go back", 2000);

        commonModule.clickDescription("Go back");
        waitForWebPageLoadSuccessfully();
    }

    public void forwardWebPage() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForDescription("Go forward", 2000);

        commonModule.clickDescription("Go forward");
        waitForWebPageLoadSuccessfully();
    }

    public void shareLinkPageByMessaging(String number) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForTextContains("Share", 2000);

        commonModule.clickTextContains("Share");
        commonModule.waitForText("Share via", 2000);

        commonModule.scrollFindText("Messaging");
        commonModule.waitForText("Write new", 2000);

        commonModule.clickResourceId("com.sonyericsson.conversations:id/recipients_editor");
        testCase.enterText(number);
        testCase.enterText("\n");

        commonModule.clickText("Send");
    }

    public void shareLinkPageByGmail(String gmail) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForTextContains("Share", 2000);

        commonModule.clickTextContains("Share");
        commonModule.waitForText("Share via", 2000);

        commonModule.scrollFindText("Gmail");
        commonModule.waitForText("Compose", 2000);

        commonModule.clickResourceId("com.google.android.gm:id/to");
        testCase.enterText(gmail);
        testCase.enterText("\n");

        commonModule.clickResourceId("com.google.android.gm:id/send");
    }

    public void checkBrowserToolbar(){
        commonModule.pressMoreOption();// Enter toolbar.
        commonModule.wait(1);

        testCase.pressBackNTimes(1);// Close toolbar.
        commonModule.wait(1);
    }

    public void longTapUrlToEnableMagnifier() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.chrome:id/url_bar");// focus url bar.
        commonModule.waitForResourceId("com.android.chrome:id/navigation_button", 2000);

        commonModule.longClickResourceId("com.android.chrome:id/url_bar");// make magnifier displayed.
    }

    public void copyUrl() throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.chrome:id/url_bar");// focus url bar.
        commonModule.waitForResourceId("com.android.chrome:id/navigation_button", 2000);

        // Tap url bar to make copy/cut/select text buttons display.
//        commonModule.clickResourceId("com.android.chrome:id/url_bar");
//        commonModule.clickResourceId("com.android.chrome:id/url_bar");
        testCase.clickId("url_bar");
        testCase.clickId("url_bar");
        commonModule.waitForResourceId("android:id/copy", 2000);

        commonModule.clickResourceId("android:id/copy");
        commonModule.waitForIdGone("android:id/copy", 2000);
    }

    public void loadWebPageInMessage(String number) throws UiObjectNotFoundException {
        testCase.launchApp("com.sonyericsson.conversations",
                "com.sonyericsson.conversations.ui.ConversationListActivity");
        testCase.sleep(2000);
        testCase.setTimeout(1000);
        if (testCase.isViewWithTextPresent("Change SMS app?")) {
            testCase.clickItemWithText("Yes");
        }
        testCase.resetTimeout();
        testCase.sleep(2000);

        commonModule.clickText(number);
        commonModule.waitForTextContains("http://", 2000);

        commonModule.clickTextContains("http://");
        if (commonModule.isTextExists("Complete action using")
                && commonModule.isTextExists("Chrome")) {
            commonModule.clickText("Chrome");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }

        waitForWebPageLoadSuccessfully();
    }

    public void loadWebPageWithoutNetwork(String url) throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.chrome:id/url_bar");
        testCase.enterText(url);
        testCase.enterText("\n");

        AcceptanceTestCase.assertTrue("Network still connecting.",
                commonModule.waitForDescription("You are offline. Heading", 10 * 1000));
    }

    public void startLoadWebPage(String url) throws UiObjectNotFoundException {
        commonModule.clickResourceId("com.android.chrome:id/url_bar");
        testCase.enterText(url);
        testCase.enterText("\n");
    }


}
