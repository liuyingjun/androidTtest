package com.concurrent.scenario;

import java.util.HashMap;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.module.media.IMedia;
import com.module.media.MediaFactory;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioPlayingFMRadio implements IScenario {
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private IMedia IMedia;
	private AcceptanceTestCase testCase;
	HashMap<String, String> moduleData;

	public ScenarioPlayingFMRadio(AcceptanceTestCase testCase) {

		this.testCase = testCase;
		moduleData = ModuleDataParser.getModuleData("camera");
		this.commonModule = new CommonModule(testCase);
		this.IMedia = MediaFactory.create(testCase);

	}

	@Override
	public void doBegin() throws Exception {
		
		IMedia.playRadioOnBackground();
	}

	public void doAfter() throws UiObjectNotFoundException {
        
		commonModule.backOutToHomeScreen();
		commonModule.openNotificationBar();
		
		AcceptanceTestCase.assertTrue(
				"FMradio display incorrectly",
				commonModule.waitForResourceId(
						"com.sonyericsson.fmradio:id/shutDown", 3 * 1000));
		
		commonModule.clickResourceId("com.sonyericsson.fmradio:id/shutDown");
		
	}

}
