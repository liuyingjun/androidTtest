package com.concurrent.scenario;


import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.GroupFactories;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.common.CommonModule;
import com.module.walkman.*;



public class ScenarioPlayMusicAndVerifyBasicFunction implements IScenario{

	public static String TAG = "Reliability";
    private CommonModule commonModule;
    private IWalkman IWalkman;
    private AcceptanceTestCase testCase;
    
    public ScenarioPlayMusicAndVerifyBasicFunction(AcceptanceTestCase testCase) throws PropertyNotFoundException{
    	this.testCase=testCase;
    	this.commonModule = new CommonModule(testCase);
    	this.IWalkman= ((AbstractWalkmanFactory)GroupFactories.createFactory(testCase, "walkman")).create();
    }

    public void doAfter() throws UiObjectNotFoundException{  
       commonModule.wait(2);
       IWalkman.verifyMusicPlaying();
     
       IWalkman.stopPlayingMusic();
       commonModule.wait(2);
       IWalkman.playNextMusic();
       commonModule.wait(2);
//       IWalkman.playPreviousMusic();
//       commonModule.wait(2);
//       IWalkman.fastForwardCurrentSong();
//       commonModule.wait(2);
//       IWalkman.fastRewindCurrentSong();
//       commonModule.wait(2);
       IWalkman.startPlayingMusic();
       
       IWalkman.verifyMusicPlaying();
    }

	@Override
	public void doBegin() throws UiObjectNotFoundException{
		
		IWalkman.playMusic();
		
	}  
}
