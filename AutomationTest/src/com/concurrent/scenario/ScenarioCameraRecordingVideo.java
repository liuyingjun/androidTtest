package com.concurrent.scenario;

import java.util.HashMap;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioCameraRecordingVideo implements IScenario {
	
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private ICamera camera;
	private AcceptanceTestCase testCase;
	HashMap<String, String> moduleData;

	public ScenarioCameraRecordingVideo(AcceptanceTestCase testCase) {
		this.testCase = testCase;
		moduleData = ModuleDataParser.getModuleData("camera");
		this.commonModule = new CommonModule(testCase);
		camera = ((AbstractCameraFactory) GroupFactories.createFactory(
				testCase, "camera")).create();

	}

	@Override
	public void doBegin() throws Exception {
		// TODO Auto-generated method stub
		camera.launchCameraPhotoApplication();
		commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
		commonModule.wait(2);
		AcceptanceTestCase.assertTrue("Video recording failed", commonModule
				.waitForResourceId(
						"com.sonyericsson.android.camera:id/recording_time",
						3000));
	}

	@Override
	public void doAfter() throws UiObjectNotFoundException {
		// TODO Auto-generated method stub
		commonModule.wait(5);
		AcceptanceTestCase.assertTrue(
				"Cannot return to camera after calling",
				commonModule.waitForResourceId(
						moduleData.get("Common_Record_Button_Id"), 10 * 1000));
		commonModule.backOutToHomeScreen();
	}
}
