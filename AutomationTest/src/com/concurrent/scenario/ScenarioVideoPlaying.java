package com.concurrent.scenario;

import android.os.Environment;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.common.CommonModule;
import com.module.media.*;

import java.io.File;
import java.util.HashMap;

public class ScenarioVideoPlaying implements IScenario{
	
	HashMap<String, String> moduleData;
	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private AcceptanceTestCase testCase;
    private IMedia IMedia;
    
    public ScenarioVideoPlaying(AcceptanceTestCase testCase){

    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IMedia=MediaFactory.create(testCase);
    	moduleData = ModuleDataParser.getModuleData("movies");
    }
    

    public void doAfter() throws UiObjectNotFoundException{ 

    	commonModule.wait(2);
//		AcceptanceTestCase.assertTrue("Video not in pause status. ",
//				commonModule.waitForResourceId(
//						moduleData.get("Video_Play_Button_Id"), 3 * 1000));
//		
    	AcceptanceTestCase.assertTrue("Video not in pause status. ",(commonModule.waitForResourceId
    			("com.sonyericsson.video:id/video_play_button", 3 * 1000)));
    	
    	
		commonModule.clickResourceId("com.sonyericsson.video:id/video_play_button");
    	commonModule.wait(3);
    	
    	commonModule.backOutToHomeScreen();
    }

	@Override
	public void doBegin(){
		
        String dir = Environment.getExternalStorageDirectory().getPath()+"/"+"reliability"+ "/";
        File testFolder = new File(dir);
		IMedia.playVideoURL(testFolder,"oceans_x264_1280_534.mp4");
		
	}  
}
