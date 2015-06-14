package com.concurrent.scenario;

import java.util.HashMap;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioTakePictureBackgroundDefocus implements IScenario{
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private ICamera camera;
    private AcceptanceTestCase testCase;
    HashMap<String, String> moduleData;
    
    public ScenarioTakePictureBackgroundDefocus(AcceptanceTestCase testCase){
    	this.testCase=testCase;
    	moduleData = ModuleDataParser.getModuleData("camera");
    	this.commonModule = new CommonModule(testCase);
    	camera = ((AbstractCameraFactory)GroupFactories.createFactory(testCase, "camera")).create();

    }

	@Override
	public void doBegin() throws UiObjectNotFoundException {
		camera.launchCameraPhotoApplication();
		camera.selectOneEffectFromCameraSettings("Background defocus");
		
		for(int i=0;i<10;i++){
		camera.takePicturesWithBackgroundDefocusMode();
		}
	   commonModule.wait(2);
			AcceptanceTestCase.assertTrue("Take pictures failed", commonModule.waitForResourceId("com.sonymobile.backgrounddefocus:id/image_button_capture", 3000));
	}

	@Override
	public void doAfter() throws UiObjectNotFoundException {
		// TODO Auto-generated method stub
		commonModule.wait(5);
		camera.takePicturesWithBackgroundDefocusMode();
		commonModule.backOutToHomeScreen();
	}
}
