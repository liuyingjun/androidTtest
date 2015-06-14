
package com.module.smallApp;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface ISmallApp {

    /**
     * Open small app list, than select one small app via app name.
     *
     * @appName the app's name be selected
     */
    void selectOneSmallApp(String appName) throws UiObjectNotFoundException;

    /**
     * Move small app.
     * @throws UiObjectNotFoundException
     *
     * @appName The app's name be moved.
     */
    void moveSmallApp(String appName) throws UiObjectNotFoundException;

    /**
     * Tap underlaying application.
     */
    void focusUnderlayingApp();

    /**
     * Close small apps.
     */
    void closeSmallApps() throws UiObjectNotFoundException;

    /**
     * Open Browser app, enter some web page.
     */
    void enterBrowserAndEnterWebPage() throws UiObjectNotFoundException;

    /**
     * Open small app list, than add a small app.
     *
     * @appName the app's name be added
     */
    void addSmallApp(String appName) throws UiObjectNotFoundException;
    /**
     * Open small app list, than delete some widgets.
     *
     * @app1 the first app's name be deleted
     * @app2 the second app's name be deleted
     * @app3 the third app's name be deleted
     */
    void deleteWidget(String ... apps) throws UiObjectNotFoundException;

}
