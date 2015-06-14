package com.concurrent.scenario;

import java.util.HashMap;

import android.media.MediaPlayer;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioRecordingSequentialVideo implements IScenario{
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private ICamera camera;
    private AcceptanceTestCase testCase;
    HashMap<String, String> moduleData;
    long startTime;

    public ScenarioRecordingSequentialVideo(AcceptanceTestCase testCase){
    	
    	this.testCase=testCase;
    	moduleData = ModuleDataParser.getModuleData("camera");
    	this.commonModule = new CommonModule(testCase);
    	camera = ((AbstractCameraFactory)GroupFactories.createFactory(testCase, "camera")).create();

    }


	@Override
	public void doBegin() throws Exception {
		
		camera.launchCameraPhotoApplication();
		camera.selectOneEffectFromCameraSettings("Superior auto");
		
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
        commonModule.wait(5);
        testCase.clickPoint(commonModule.getX(1095, 1196), commonModule.getY(328, 720));
       // testCase.clickId("main_button");
      //  commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));
        
        commonModule.wait(2);
        testCase.clickPoint(commonModule.getX(1095, 1196), commonModule.getY(328, 720));
       // testCase.clickId("main_button");
       // commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));
        commonModule.wait(5);
       // commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));
        testCase.clickPoint(commonModule.getX(1095, 1196), commonModule.getY(328, 720));
        //testCase.clickId("main_button");
        
        commonModule.wait(2);
        testCase.clickPoint(commonModule.getX(1095, 1196), commonModule.getY(328, 720));
       // testCase.clickId("main_button");
      //  commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));

	}
	
	public void doAfter() throws UiObjectNotFoundException {
		testCase.clickPoint(commonModule.getX(1095, 1196), commonModule.getY(328, 720));
		//commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));
        UiObject container = new UiObject(new UiSelector().resourceId("com.sonyericsson.android.camera:id/thumbnail_container"));
        int count = container.getChildCount();
        
		AcceptanceTestCase.assertTrue("Camera display incorrectly",
				                      count==3);
		
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
	}
	
}
