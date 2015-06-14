package com.concurrent.scenario;

import java.util.HashMap;

import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioLockScreen implements IScenario {
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private AcceptanceTestCase testCase;
	HashMap<String, String> moduleData;

	public ScenarioLockScreen(AcceptanceTestCase testCase) {

		this.testCase = testCase;
		this.commonModule = new CommonModule(testCase);

	}

	@Override
	public void doBegin() throws Exception {
		commonModule.lockScreen();
		if(!testCase.isScreenOn()){
			testCase.pressKey(KeyEvent.KEYCODE_POWER);
		}
	}

	public void doAfter() throws UiObjectNotFoundException {
		commonModule.unLockScreen();
	}

}
