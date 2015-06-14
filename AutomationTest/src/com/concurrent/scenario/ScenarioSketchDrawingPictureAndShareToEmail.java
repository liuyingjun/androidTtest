package com.concurrent.scenario;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;
import com.parser.cases.TestDataExtract;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.alarm.AlarmFactory;
import com.module.alarm.IAlarm;
import com.module.album.AlbumFactory;
import com.module.album.IAlbum;
import com.module.calculator.AbstractCalculatorFactory;
import com.module.calculator.ICalculator;
import com.module.calendar.CalendarFactory;
import com.module.calendar.ICalendar;
import com.module.common.CommonModule;
import com.module.media.*;
import com.module.messaging.AbstractMessagingFactory;
import com.module.messaging.IMessaging;
import com.module.messaging.MessagingFactory;

import java.io.File;
import java.util.HashMap;

public class ScenarioSketchDrawingPictureAndShareToEmail implements IScenario{

	HashMap<String, String> moduleData;
	HashMap<String, String> moduleDataEmail;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private static String test_emailaccount;
    IAlbum IAlbum;
    IMessaging IMessaging;

    public ScenarioSketchDrawingPictureAndShareToEmail(AcceptanceTestCase testCase) throws PropertyNotFoundException{

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IAlbum = AlbumFactory.getAlbumModule(testCase);
    	this.IMessaging = ((AbstractMessagingFactory)GroupFactories.createFactory(testCase, "messaging")).create();
    	moduleData = ModuleDataParser.getModuleData("sketch");
    	moduleDataEmail = ModuleDataParser.getModuleData("email");
    	getEmailAccout();
    }

    protected void getEmailAccout(){
    	test_emailaccount = TestDataExtract.test_emailaccount;
    }

    public void doAfter() throws UiObjectNotFoundException{

    	boolean received = false;
    	long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 2*60*1000) {
            if (testCase.isViewWithTextPresent(test_emailaccount)) {
                Log.d(TAG, "Email receive successfully");
                testCase.click(test_emailaccount);
                received=true;
                break;
            } else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(5);
                testCase.openNotification();
            }
        }
		AcceptanceTestCase
		.assertTrue("Received email failed", received);


        IAlbum.startSketch();
        commonModule.wait(2);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);

		testCase.click("Discard");
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{

		IAlbum.startSketch();
		IAlbum.drawingAPictureBySketch();

		testCase.pressKey(KeyEvent.KEYCODE_MENU);

		testCase.click("Share");
		testCase.click("Email");

        commonModule.wait(2);
        commonModule.clickResourceId(moduleDataEmail.get("Email_To_EditText_Id"));
        commonModule.wait(2);
        testCase.enterText(test_emailaccount);
        testCase.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.clickResourceId(moduleDataEmail.get("Send_Button_Id"));

        testCase.pressKey(KeyEvent.KEYCODE_HOME);

	}
}
