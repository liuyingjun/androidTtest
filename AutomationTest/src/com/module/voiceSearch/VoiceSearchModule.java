package com.module.voiceSearch;

import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import java.util.HashMap;

public class VoiceSearchModule implements IVoiceSearch {

    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    String netWorkModeString = "Network Mode";

    public static String TAG = "Reliability";

    public void setNetWorkModeString(String netWorkModeString) {
        this.netWorkModeString = netWorkModeString;
    }

    public VoiceSearchModule(AcceptanceTestCase testCase) throws PropertyNotFoundException {
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);

        this.moduleData = ModuleDataParser.getModuleData("voicesearch");
    }

    public VoiceSearchModule() {

    }

    public void launchVoiceSearch() {

        testCase.launchApp("com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.VoiceSearchActivity");
        AcceptanceTestCase.assertTrue("Launch Voice Search failed.",
                commonModule.waitForResourceId(moduleData.get("Speech_Logo"), 5000));
    }


}
