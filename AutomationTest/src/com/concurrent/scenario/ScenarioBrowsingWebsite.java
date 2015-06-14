package com.concurrent.scenario;

import java.util.HashMap;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.browser.BrowserFactory;
import com.module.browser.IBrowser;
import com.module.common.CommonModule;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ScenarioBrowsingWebsite implements IScenario {
	public static String TAG = "Reliability";
	private CommonModule commonModule;
	private IBrowser browser;
	private AcceptanceTestCase testCase;
	HashMap<String, String> moduleData;

	public ScenarioBrowsingWebsite(AcceptanceTestCase testCase) throws PropertyNotFoundException {

		this.testCase = testCase;
		this.commonModule = new CommonModule(testCase);
		this.browser = BrowserFactory.create(testCase);

	}

	@Override
	public void doBegin() throws Exception {
		browser.startChrom();
		browser.startLoadWebPage("www.baidu.com");
	}

	public void doAfter() throws UiObjectNotFoundException {
		
		browser.waitForWebPageLoadSuccessfully();
	}

}
