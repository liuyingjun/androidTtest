package com.concurrent.scenario;

import java.util.HashMap;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.module.media.IMedia;
import com.module.media.MediaFactory;
import com.module.walkman.AbstractWalkmanFactory;
import com.module.walkman.IWalkman;
import com.parents.GroupFactories;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioTrackIDSearchingMusic implements IScenario {
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private IMedia IMedia;
	private AcceptanceTestCase testCase;
	private IWalkman IWalkman;
	HashMap<String, String> moduleData;

	public ScenarioTrackIDSearchingMusic(AcceptanceTestCase testCase) throws PropertyNotFoundException {

		this.testCase = testCase;
		moduleData = ModuleDataParser.getModuleData("camera");
		this.commonModule = new CommonModule(testCase);
		this.IMedia = MediaFactory.create(testCase);
		this.IWalkman= ((AbstractWalkmanFactory)GroupFactories.createFactory(testCase, "walkman")).create();
	}

	@Override
	public void doBegin() throws Exception {
		
		IWalkman.playMusic("Because Of You");
		
		IWalkman.stopPlayingMusic();

        //verfiy repeat one function
		IWalkman.setRepeatOne();

		IWalkman.startPlayingMusic();
		
		commonModule.backOutToHomeScreen();
		
		IMedia.launchTrackID();
		testCase.clickId("tracker_button_recording");
		
	}

	public void doAfter() throws UiObjectNotFoundException {
        
		IMedia.searchSongs("Because Of You");
	}

}
