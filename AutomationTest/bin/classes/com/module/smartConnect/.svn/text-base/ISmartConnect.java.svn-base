
package com.module.smartConnect;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface ISmartConnect {

    /**
     * Launch Smart Connect.
     */
    void launchSmartConnect() throws UiObjectNotFoundException;

    /**
     * Add a new Event. Make sure Smart Connect application is launched before
     * invoke this method.
     *
     * @param eventName
     * @throws UiObjectNotFoundException
     */
    void addNewEvent(String eventName) throws UiObjectNotFoundException;

    /**
     * Rename event.Make sure Smart Connect application is launched before
     * invoke this method.
     *
     * @param eventName
     * @param rename
     */
    void renameEvent(String eventName, String rename) throws UiObjectNotFoundException;

    /**
     * Edite event.Make sure Smart Connect application is launched before invoke
     * this method.
     *
     * @param eventName
     * @throws UiObjectNotFoundException
     */
    void editEvent(String eventName) throws UiObjectNotFoundException;

    /**
     * Check event.Make sure Smart Connect application is launched before invoke
     * this method.
     *
     * @param timeout
     * @return
     */
    boolean checkEvent(int timeout);

    /**
     * Delete event.Make sure Smart Connect application is launched before
     * invoke this method.
     *
     * @param eventName
     */
    void deleteEvent(String eventName) throws UiObjectNotFoundException;

}
