package com.module.fileCommander;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.view.KeyEvent;

import java.util.HashMap;

public class FileCommander implements IFileCommander{

    public AcceptanceTestCase testCase;
    public CommonModule commonModule;

    HashMap<String, String> moduleData;

    public FileCommander(AcceptanceTestCase testCase){
        this.testCase = testCase;
        commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("filecommander");
    }

    public void launchFileCommander(){
        testCase.launchApp("com.mobisystems.fileman", "com.mobisystems.office.FileBrowser");

        commonModule.wait(2);
    }

    public void installAppFromFileCommander(String appName) throws UiObjectNotFoundException{

        launchFileCommander();

        if(commonModule.waitForText("Internal storage", 3000)){
            //device have been 'Internal storage' folder or 'Home' folder with text 'Internal storage'
            commonModule.clickText("Internal storage");
        }else{
            commonModule.clickResourceId(moduleData.get("File_commander_app_icon"));

            commonModule.clickText("Internal storage");
        }

        commonModule.wait(2);

        commonModule.scrollFindText("testresource");

        commonModule.scrollFindText(appName);

        if(commonModule.waitForText("Install blocked", 5000)){
            commonModule.clickText("Settings");

            //check allow install unknown source
            commonModule.scrollFindText("Unknown sources");

            //Accept the alert message
            commonModule.clickText("OK");

            commonModule.pressKey(KeyEvent.KEYCODE_BACK);

            commonModule.scrollFindText(appName);
        }

        commonModule.waitForText("Install", 5000);

        commonModule.clickText("Install");

        //install completed ,click 'Done'
        commonModule.waitForText("Done", 20*1000);

        commonModule.clickText("Done");
    }
}
