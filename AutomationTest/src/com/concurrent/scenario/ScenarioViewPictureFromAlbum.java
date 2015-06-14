package com.concurrent.scenario;

import java.util.HashMap;

import junit.framework.Assert;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.album.AlbumFactory;
import com.module.album.IAlbum;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioViewPictureFromAlbum implements IScenario {
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private IAlbum IAlbum;
	private AcceptanceTestCase testCase;
	HashMap<String, String> moduleData;

	public ScenarioViewPictureFromAlbum(AcceptanceTestCase testCase) {

		this.testCase = testCase;
		moduleData = ModuleDataParser.getModuleData("camera");
		this.commonModule = new CommonModule(testCase);
		this.IAlbum = AlbumFactory.getAlbumModule(testCase);

	}

	@Override
	public void doBegin() throws Exception {
		int[] screen = testCase.getScreenSize();
		IAlbum.openOnePicture();
		testCase.clickPoint(screen[0] / 3, screen[1] / 3);
	}

	public void doAfter() throws UiObjectNotFoundException {
		
		if (!commonModule.waitForResourceId(
                "com.sonyericsson.album:id/action_layer_title", 2000)){
			int[] screen = testCase.getScreenSize();
			
	        testCase.clickPoint(screen[0] / 3, screen[1] / 3);
	        Assert.assertTrue("Open a picture failed.",
	                 commonModule.waitForResourceId(
                                "com.sonyericsson.album:id/action_layer_title", 2000));
		}
			
	}

}
