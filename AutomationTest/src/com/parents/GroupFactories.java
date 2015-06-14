package com.parents;

import com.module.calculator.CalculatorFactory;
import com.module.camera.CameraFactory;
import com.module.facebook.FacebookFactory;
import com.module.fileCommander.FileCommanderFactory;
import com.module.gmail.GmailFactory;
import com.module.lockscreen.LockScreenFactory;
import com.module.messaging.MessagingFactory;
import com.module.playStore.PlayStoreFactory;
import com.module.settings.SettingsFactory;
import com.module.telephony.TelephonyFactory;
import com.module.voiceSearch.VoiceSearchFactory;
import com.module.walkman.WalkmanFactory;
import com.module.email.EmailFactory;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class GroupFactories {

    public static Factory createFactory(AcceptanceTestCase testCase, String moduleName) {
        if (moduleName == "telephony") {
            return new TelephonyFactory(testCase);
        } else if (moduleName == "walkman") {
            return new WalkmanFactory(testCase);
        } else if (moduleName == "settings") {
            return new SettingsFactory(testCase);
        }else if(moduleName == "email"){
            return new EmailFactory(testCase);
        }else if(moduleName == "camera"){
            return new CameraFactory(testCase);
        } else if (moduleName == "calculator") {
            return new CalculatorFactory(testCase);
        } else if (moduleName == "facebook") {
            return new FacebookFactory(testCase);
        } else if(moduleName == "playstore"){
            return new PlayStoreFactory(testCase);
        } else if(moduleName == "filecommander"){
            return new FileCommanderFactory(testCase);
        } else if(moduleName == "gmail"){
            return new GmailFactory(testCase);
        } else if(moduleName == "voicesearch"){
            return new VoiceSearchFactory(testCase);
        } else if(moduleName == "messaging"){
            return new MessagingFactory(testCase);
        } else if(moduleName == "lockscreen"){
            return new LockScreenFactory(testCase);
        }
        return null;
    }
}
