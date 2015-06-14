package com.module.fileCommander;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IFileCommander {

    public void launchFileCommander();

    public void installAppFromFileCommander(String appName) throws UiObjectNotFoundException;
}
