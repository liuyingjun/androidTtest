package com.module.camera;

import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.R.integer;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Rect;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera;
import android.util.Log;
import android.view.KeyEvent;

import java.util.HashMap;

public class CameraModule implements ICamera {
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    public static final String CAMERA_PACKAGE = "com.sonyericsson.android.camera";

    public static final String CAMERA_CLASS_NAME = "com.sonyericsson.android.camera.CameraActivity";

    String TAG = "Reliability";
    CameraInfo info = new Camera.CameraInfo();

    public CameraModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("camera");
    }

    public void launchCameraPhotoApplication() {
        testCase.launchApp(CAMERA_PACKAGE, CAMERA_CLASS_NAME);
        testCase.setTimeout(6000);
        removeAlertTitle();
        AcceptanceTestCase.assertTrue("launch Camera failed", commonModule.isResourceId(moduleData.get("Take_Photo_Main_Button_Id")));
        testCase.resetTimeout();

    }

    protected void removeAlertTitle() {
        if (commonModule.waitForText("Remember photo locations?", 5000)) {
            testCase.clickItemWithText("No");
        }
    }

    protected boolean isCameraApplicationOpened() {
        return testCase.isViewWithIdPresent("capture_button");
    }

    public void recordVideo(int recordingTime) throws UiObjectNotFoundException {
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
        commonModule.wait(2);
        // testCase.assertViewWithIdPresent("recording_time");
        commonModule.wait(recordingTime);
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
    }

    public void takePictures(int picNum) {
        // launchCameraPhotoApplication();
        for (int i = 0; i < picNum; i++) {
            pressCameraButton();
            commonModule.wait(5);
        }
    }

    public void pressCameraButton() {
        testCase.pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);
    }

    public void switchCamera() {
        testCase.clickPoint(commonModule.getX(100, 1794), commonModule.getY(750, 1080));
    }

    public void selectOneCreativeEffect(int y) throws UiObjectNotFoundException {
        if (commonModule.isResourceId(moduleData.get("Creative_Effect_Selector_Icon_Id"))) {
            commonModule.clickResourceId(moduleData.get("Creative_Effect_Selector_Icon_Id"));
        }else{
        	testCase.clickPoint(commonModule.getX(1100, 1196), commonModule.getY(220, 720));
        }
        testCase.clickPoint(commonModule.getX(1500, 1794), commonModule.getY(y, 1080));
        commonModule.wait(1);
        testCase.clickPoint(commonModule.getX(500, 1794), commonModule.getY(500, 1080));
        commonModule.wait(2);
    }

    public void selectAllCreativeEffectAndTakePictures() throws UiObjectNotFoundException {
        for (int i = 0; i < 19; i++) {
            Log.i(TAG, "Select Creative Effect " + (i + 1));
            if (i == 0) {
                // The first one.
                selectOneCreativeEffect(100);
            } else if (i == 1) {
                // The second one.
                selectOneCreativeEffect(400);
            } else if (i == 2) {
                // The third one.
                selectOneCreativeEffect(700);
            } else if (i > 2 && i < 18) {
                selectOneCreativeEffect(800);
            } else {
                // The last one.
                selectOneCreativeEffect(900);
            }
            takePictures(1);
        }
	}

    public void changePreviewMode(String preview,boolean videoSelection) throws UiObjectNotFoundException {
        // Press more menu button.
        commonModule.waitForId("effect_selector_icon", 5000);
        testCase.clickPoint(commonModule.getX(100, 1794), commonModule.getY(1000, 1080));
        commonModule.wait(3);
        if(videoSelection){
            if(commonModule.isResourceId("com.sonymobile.android.addoncamera.dual:id/tab_middle")){
            	commonModule.clickResourceId("com.sonymobile.android.addoncamera.dual:id/tab_middle");
            } else{
            	testCase.clickPoint(commonModule.getX(270, 1794), commonModule.getY(540, 1080));
            }
        }
        if (commonModule.isResourceId(moduleData.get("Preview_Text_Id"))) {
            commonModule.clickResourceId(moduleData.get("Preview_Text_Id"));
        } else if (commonModule.isTextExists("Preview")) {
            commonModule.clickText("Preview");
        } else if (commonModule.isDescriptionExists("Preview")) {
        			commonModule.clickResourceId(moduleData.get("Preview_Description_Id"));
        } else{
        	testCase.clickPoint(commonModule.getX(275, 1196), commonModule.getY(370, 720));
        }
        commonModule.waitForText(preview, 3000);

        commonModule.clickText(preview);

        Assert.assertTrue("Change Preview Mode failed.", commonModule.waitForText("Preview", 3000)
                && commonModule.waitForText(preview, 3000));
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        Assert.assertTrue("Back to camera view failed.",
                commonModule.waitForTextGone("Preview", 3000));
    }

    public void checkPictures(int picNum) throws UiObjectNotFoundException {
        if (!commonModule.waitForResourceId(moduleData.get("Delete_Picture_Icon"), 2000)
                && !commonModule.waitForResourceId(moduleData.get("Content_Thumbnail_Id"), 2000)) {
            launchCameraPhotoApplication();
        }
        if (commonModule.isResourceId(moduleData.get("Content_Thumbnail_Id"))) {
            commonModule.clickResourceId(moduleData.get("Content_Thumbnail_Id"));
            commonModule.wait(2);
        }
        if (commonModule.waitForId("alertTitle", 3000) && commonModule.waitForText("Album", 3000)) {
            testCase.click("Album");
            if (commonModule.waitForText("Just once", 3000)) {
                testCase.click("Just once");
            }
        }
        // Make sure entered Album, and select one video.
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        // Make video information displayed.
        for (int i = 0; i < 5; i++) {
            testCase.clickPoint(width / 4, height / 4);
            if (commonModule.waitForResourceId(moduleData.get("Delete_Picture_Icon"), 2000)) {
                break;
            }
        }
        if (picNum == 1) {
            return;
        }
        for (int i = 0; i < picNum - 1; i++) {
            testCase.scrollRight();
            commonModule.wait(1);
        }
    }

    public void launchCameraByMode(String cameraMode) {

        launchCameraPhotoApplication();
        commonModule.wait(1);
        testCase.setOrientationPortrait();
        testCase.clickItemWithId("mode_selector_button");
        testCase.clickItemWithText(cameraMode);
        commonModule.wait(3);

    }

    @SuppressWarnings("static-access")
    public void switchMicrophoneOnTimeshiftMode() {
        // Press more menu button.
        testCase.clickPoint(commonModule.getX(100, 1794), commonModule.getY(1000, 1080));
        commonModule.wait(3);
        // Press switch microphone button
        testCase.clickPoint(commonModule.getX(400, 1794), commonModule.getY(500, 1080));
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(1);
        testCase.assertFalse("Camera Mode: Could not exit switch microphone.",
                commonModule.waitForId("switch_category", 8000));

    }

    public void openLatestPictureInCamera() throws UiObjectNotFoundException {
        commonModule.wait(3);
        testCase.assertViewWithIdPresent("main_button");
        commonModule.wait(1);
        clickRecentShot();
        // Press to show picture information
        testCase.clickPoint(commonModule.getX(500, 1794), commonModule.getY(900, 1080));
        commonModule.wait(1);
        testCase.assertViewWithIdNotPresent("main_button");
    }

	public void clickRecentShot() throws UiObjectNotFoundException {
	    commonModule.waitForResourceId(moduleData.get("Take_Photo_Main_Button_Id"), 5000);
        commonModule.wait(3);
        if (commonModule.isResourceId(moduleData.get("Recent_Shot_Icon_Id"))) {
            commonModule.clickResourceId(moduleData.get("Recent_Shot_Icon_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(126, 1080));
        }
		commonModule.wait(3);
        if (testCase.isViewWithTextPresent("Complete action using")) {
            testCase.click("Album");
            if (testCase.isViewWithTextPresent("Always")) {
                testCase.click("Always");
            }
            commonModule.wait(5);
        }

        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        // Make photo/video information displayed.
        for (int i = 0; i < 5; i++) {
            testCase.clickPoint(width / 4, height / 4);
            if (commonModule.waitForResourceId(moduleData.get("Delete_Picture_Icon"), 2000)) {
                break;
            }
        }
        AcceptanceTestCase.assertTrue("Click Recent Shot failed.",
                commonModule.waitForResourceId(moduleData.get("Delete_Picture_Icon"), 2000));
    }

	@Override
    public void openLatestVideoInCamera() throws UiObjectNotFoundException {
        commonModule.wait(3);
        testCase.assertViewWithIdPresent("main_button");
        commonModule.wait(1);
        clickRecentShot();
        // Click to play video
        if (!testCase.isViewWithIdPresent("video_buttons")) {
            testCase.clickPoint(commonModule.getX(540, 1080), commonModule.getY(950, 1794));
            commonModule.wait(2);
        }
        if (testCase.isViewWithTextPresent("Complete action using")) {
            testCase.click("Movies");
            if (testCase.isViewWithTextPresent("Always")) {
                testCase.click("Always");
            }
            commonModule.wait(3);
        }
        commonModule.wait(1);
        testCase.assertViewWithIdNotPresent("main_button");
    }

	public void playRecentVideoUntilEnd(long videoSeconds) throws UiObjectNotFoundException {
        clickRecentShot();

        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        testCase.clickPoint(width / 2, height / 2);
        commonModule.wait(1);
        if (testCase.isViewWithTextPresent("Complete action using")) {
            testCase.click("Movies");
            if (testCase.isViewWithTextPresent("Just once")) {
                testCase.click("Just once");
            }
        }

        // Wait for video play end.
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < videoSeconds * 1000) {
            commonModule.wait(3);
        }
        AcceptanceTestCase.assertTrue("Video not play end.",
                commonModule.waitForResourceId("com.sonyericsson.album:id/action_layer_action_delete", 3000));
    }

    @SuppressWarnings("static-access")
    public void recordVideoOnTimeshiftMode(int recordingTime) throws UiObjectNotFoundException {
        commonModule.wait(2);
        commonModule.clickResourceId(moduleData.get("Record_Video_Main_Button_Id"));
        testCase.assertViewWithIdPresent("recording_time");
        commonModule.wait(recordingTime);
        commonModule.clickResourceId(moduleData.get("Record_Video_Main_Button_Id"));
        testCase.assertTrue("Timeshift Mode: Could not save timeshift video!",
                commonModule.waitForId("skip_button", 8000));
        commonModule.wait(2);
    }

    public void selectOneEffectFromCameraSettings(String effectName)
            throws UiObjectNotFoundException {
        commonModule.clickResourceId(moduleData.get("Mode_Selector_Button_Id"));
        commonModule.waitForText(effectName, 2000);

        if (effectName.equals("Creative effect")
                && testCase.isViewWithTextPresent("Picture effect")) {
            commonModule.clickText("Picture effect");
            commonModule.wait(3);
        } else {
            for (int i = 0; i < 3; i++) {
                if (commonModule.waitForText(effectName, 3000)) {
                    break;
                }
                testCase.scrollRight();
                testCase.scrollDown();
                commonModule.wait(2);
            }

            commonModule.clickText(effectName);
            commonModule.wait(2);

        }
        // Assert.assertTrue("Select effect from Camera settings failed.",
        // commonModule.waitForTextGone(effectName, 3000));
    }

	public void selectOneAREffect(String areffect) {

        // if (testCase.isViewWithDescriptionPresent("Theme menu")) {
        // testCase.clickItemWithDescription("Theme menu");
        // commonModule.waitForText(areffect, 3000);
        // }
        testCase.clickItemWithDescription(areffect);
        if (testCase.isViewWithTextPresent("AR effect") && testCase.isViewWithTextPresent("Next")) {
            testCase.click("Next");
            commonModule.waitForText("OK", 3000);
            testCase.click("OK");
        }
        Assert.assertTrue("Select AR effect failed.", commonModule.waitForText("Reset", 3000));
    }

    public int selectAllAREffectAndTakePictures() {
        int i = 0;
        if (testCase.isViewWithDescriptionPresent("Fairytale")) {
            selectOneAREffect("Fairytale");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Fairytale", 3000);
        } else if (testCase.isViewWithDescriptionPresent("Fairy tale")) {
            selectOneAREffect("Fairy tale");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Fairy tale", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Dive")) {
            selectOneAREffect("Dive");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Dive", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Dinosaur")) {
            selectOneAREffect("Dinosaur");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Dinosaur", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Celebration")) {
            selectOneAREffect("Celebration");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Celebration", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Masquerade")) {
            selectOneAREffect("Masquerade");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Masquerade", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Funky disco")) {
            selectOneAREffect("Funky disco");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Funky disco", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Butterfly")) {
            selectOneAREffect("Butterfly");
            takePictures(1);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Butterfly", 3000);
        }
        return i;
    }

    public void takePicturesWithBackgroundDefocusMode() {
        if (testCase.isViewWithTextPresent("OK")) {
            testCase.click("OK");
            commonModule.wait(3);
        }
        testCase.pressKey(KeyEvent.KEYCODE_FOCUS);
        commonModule.wait(3);
        takePictures(1);
        if (testCase.isViewWithIdPresent("image_button_check")) {
            testCase.clickId("image_button_check");
        } else if (testCase.isViewWithTextPresent("Could not blur background.")) {
            testCase.failTest("Could not blur background.");
        }
        Assert.assertTrue("Take pictures with Background defocus mode failed.",
                commonModule.waitForId("touch_focus_guide_icon_16_9", 10 * 1000));
    }

    public void activeGeoTagFromCameraSettings() throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        if (!commonModule.waitForId("geo_tag_indicator", 3000)) {
            testCase.clickItemWithDescription("Menu");
            commonModule.wait(2);

            // Tap other settings icon.
            if (commonModule.waitForDescription("Other settings", 2000)) {
                commonModule.clickDescription("Other settings");
            } else if (commonModule.isResourceId(moduleData.get("Other_Settings_Icon_Id"))) {
                commonModule.clickResourceId(moduleData.get("Other_Settings_Icon_Id"));
            } else {
                testCase.clickPoint(commonModule.getX(295, 1920), commonModule.getY(267, 1080));
            }
            commonModule.wait(2);

            if (commonModule.isResourceId(moduleData.get("Switch_Category"))) {
                commonModule.clickResourceId(moduleData.get("Switch_Category"));
            } else if (commonModule.isTextExists("Geotagging")) {
                commonModule.clickText("Geotagging");
            } else {
                testCase.clickPoint(commonModule.getX(585, 1794), commonModule.getY(183, 1080));
            }

			if (commonModule.waitForText("How to get location", 3000)) {
				testCase.click("OK");
			}

            if (testCase.isViewWithIdPresent("switch_category")
                    || commonModule.isTextExists("Geotagging")) {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
            }
            commonModule.wait(2);
        }
//        Assert.assertTrue("Set Geo tag failed.", commonModule.waitForResourceId(
//                moduleData.get("Geo_Tag_Indicator"), 5000));
    }

    @SuppressWarnings("static-access")
    public void openHDR() {
        openCameraSetting();
        commonModule.wait(3);
        // Press HDR switch button
        testCase.clickPoint(commonModule.getX(1100, 1794), commonModule.getY(500, 1080));
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(1);
        testCase.assertFalse("Camera Mode: Could not exit switch microphone.",
                commonModule.waitForId("switch_category", 8000));

	}

	@SuppressWarnings("static-access")
	public void open4K2KPictureMode() {
		openCameraSetting();
		commonModule.wait(3);
		// Press resolution button
		testCase.clickPoint(commonModule.getX(450, 1794),
				commonModule.getY(500, 1080));
		commonModule.wait(3);
		// Press 4k2k button
		testCase.clickPoint(commonModule.getX(1000, 1794),
				commonModule.getY(500, 1080));
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		commonModule.wait(1);
		testCase.assertFalse("Camera Mode: Could not exit setting.",
				commonModule.waitForId("switch_category", 8000));

	}

	@SuppressWarnings("static-access")
	public void change4KVideoSetting(int steadyShot, int preview) {
		int gap = 170;

		openCameraSetting();
		commonModule.wait(2);
		// Change SteadyShot setting
		testCase.clickPoint(commonModule.getX(400, 1794),
				commonModule.getY(500, 1080));
		commonModule.wait(1);
		switch (steadyShot) {
		case 0:
			testCase.clickPoint(commonModule.getX(550, 1794),
					commonModule.getY(500, 1080));
		case 1:
			testCase.clickPoint(commonModule.getX(550 + gap, 1794),
					commonModule.getY(500, 1080));
		}
		commonModule.wait(1);
		// Change preview setting
		testCase.clickPoint(commonModule.getX(400 + gap, 1794),
				commonModule.getY(500, 1080));
		commonModule.wait(2);
		switch (preview) {
		case 0:
			testCase.clickPoint(commonModule.getX(730, 1794),
					commonModule.getY(500, 1080));
		case 1:
			testCase.clickPoint(commonModule.getX(730 + gap, 1794),
					commonModule.getY(500, 1080));
		case 2:
			testCase.clickPoint(commonModule.getX(730 + gap * 2, 1794),
					commonModule.getY(500, 1080));
		}
		commonModule.wait(1);
		// Change microphone setting
		testCase.clickPoint(commonModule.getX(400 + gap * 2, 1794),
				commonModule.getY(500, 1080));
		testCase.pressKey(KeyEvent.KEYCODE_BACK);
		commonModule.wait(3);
		boolean id = commonModule.waitForId("switch_category", 5000);
		testCase.assertFalse("Camera Mode: Could not exit setting page.",id
				);

	}

    public void recordVideoOn4KMode(int recordingTime) throws UiObjectNotFoundException {
        commonModule.wait(2);
        commonModule.clickResourceId(moduleData.get("Record_Video_Main_Button_Id"));
        commonModule.wait(recordingTime);
        commonModule.clickResourceId(moduleData.get("Record_Video_Main_Button_Id"));
        commonModule.wait(3);
        if (testCase.isViewWithIdPresent("auto_review_delete")) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
    }

	private void openCameraSetting() {
		// Press more menu button.
		testCase.clickPoint(commonModule.getX(100, 1794),
				commonModule.getY(1000, 1080));
	}

    public void changeScenes(String scenes) throws UiObjectNotFoundException {
        testCase.setOrientationPortrait();
        if (!commonModule.isTextExists("Scene selection")) {
            if (commonModule.isDescriptionExists("Scenes")) {
                commonModule.clickDescription("Scenes");
            } else {
                // Tap the coordinate to click Scenes icon.
                testCase.clickPoint(commonModule.getX(60, 1280), commonModule.getY(70, 720));
            }
            commonModule.waitForText("Scene selection", 2000);
        }

        for (int i = 0; i < 5; i++) {

            if (!commonModule.isTextExists("Scene selection")) {
                break;
            }

            if (commonModule.isTextExists(scenes)) {
                commonModule.clickText(scenes);
            } else if (commonModule.isDescriptionExists(scenes)) {
                commonModule.clickDescription(scenes);
            }

            if (i == 4) {
                testCase.failTest("Tool can't change scenes.");
            }
        }
        Assert.assertTrue("Change '" + scenes + "' scenes failed.",
                commonModule.waitForTextGone("Scene selection", 3000));
    }

    public void changeScenesByCoordinate(int pointX) {
        testCase.setOrientationPortrait();
        testCase.clickItemWithDescription("Scenes");
        commonModule.waitForText("Scene selection", 2000);
        testCase.clickPoint(commonModule.getX(pointX, 1794), commonModule.getY(540, 1080));
        Assert.assertTrue("Change scenes failed.", commonModule.waitForId("main_button", 5000));
    }

    public void recordVideoByAREffect(int recordingTime) throws UiObjectNotFoundException {
        commonModule.clickResourceId(moduleData.get("AR_Effect_Record_Button_Id"));
        commonModule.wait(2);
        commonModule.wait(recordingTime);
        commonModule.clickResourceId(moduleData.get("AR_Effect_Record_Stop_Button"));
        Assert.assertTrue("Record Video by AR effect failed.",
                commonModule.waitForResourceId(moduleData.get("AR_Effect_Record_Button_Id"), 3000));
    }

    public void checkVideos(int videoNum) throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        if (commonModule.isResourceId(moduleData.get("Content_Thumbnail_Id"))) {
            commonModule.clickResourceId(moduleData.get("Content_Thumbnail_Id"));
        } else if (commonModule
                .isResourceId("com.sonymobile.androidapp.cameraaddon.areffect:id/video_indicator")) {
            commonModule
                    .clickResourceId("com.sonymobile.androidapp.cameraaddon.areffect:id/video_indicator");
        } else if (commonModule.isDescriptionExists("Thumbnail")) {
            commonModule.clickDescription("Thumbnail");
        }
        commonModule.wait(2);
        if (!commonModule.waitForId("home", 3000)) {
            testCase.clickPoint(commonModule.getX(1300, 1794), commonModule.getY(540, 1080));
        }
        commonModule.waitForId("home", 3000);
        for (int i = 0; i < videoNum; i++) {
            testCase.scrollRight();
            commonModule.wait(1);
        }
    }

    public void selectAllPreviewModeAndTakePictures() throws UiObjectNotFoundException {
        changePreviewMode("Unlimited",false);
        takePictures(1);
        commonModule.waitForId("auto_review_delete", 3000);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForId("mode_selector_button", 5000);

        changePreviewMode("5 seconds",false);
        takePictures(1);
        commonModule.waitForId("auto_review_delete", 3000);
        commonModule.waitForId("mode_selector_button", 6000);

        changePreviewMode("3 seconds",false);
        takePictures(1);
        commonModule.waitForId("auto_review_delete", 3000);
        commonModule.waitForId("mode_selector_button", 4000);

        changePreviewMode("Edit",false);
        takePictures(1);
        commonModule.waitForId("Complete action using", 3000);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        if (!commonModule.waitForId("main_button", 3000)) {
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.waitForId("main_button", 3000);
        }

        changePreviewMode("Off",false);
        takePictures(1);
        commonModule.waitForId("mode_selector_button", 3000);
	}


	public void selectAllScenesAndTakePictures() throws UiObjectNotFoundException{
		changeScenes("Off");
        takePictures(1);

        changeScenes("Soft skin");
        takePictures(1);

        changeScenes("Soft snap");
        takePictures(1);

        changeScenes("Anti motion blur");
        takePictures(1);

        changeScenes("Landscape");
        takePictures(1);

        changeScenes("Backlight correction HDR");
        takePictures(1);

        changeScenes("Night portrait");
        takePictures(1);

        changeScenes("Night scene");
        takePictures(1);

        changeScenes("Hand-held twilight");
        takePictures(1);

        changeScenes("High sensitivity");
        takePictures(1);

        changeScenes("Gourmet");
        takePictures(1);

        changeScenes("Pet");
        takePictures(1);

        changeScenes("Beach");
        takePictures(1);

        changeScenes("Snow");
        takePictures(1);

        changeScenes("Party");
        takePictures(1);

        changeScenes("Sports");
        takePictures(1);

        changeScenes("Document");
        takePictures(1);

        changeScenes("Fireworks");
        takePictures(1);
    }

    public int selectAllAREffectAndRecordVideos(int recordingTime) throws UiObjectNotFoundException {
        int i = 0;
        if (testCase.isViewWithDescriptionPresent("Fairytale")) {
            selectOneAREffect("Fairytale");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Fairytale", 3000);
        } else if (testCase.isViewWithDescriptionPresent("Fairy tale")) {
            selectOneAREffect("Fairy tale");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Fairy tale", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Dive")) {
            selectOneAREffect("Dive");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Dive", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Dinosaur")) {
            selectOneAREffect("Dinosaur");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Dinosaur", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Celebration")) {
            selectOneAREffect("Celebration");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Celebration", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Masquerade")) {
            selectOneAREffect("Masquerade");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Masquerade", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Funky disco")) {
            selectOneAREffect("Funky disco");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Funky disco", 3000);
        }
        if (testCase.isViewWithDescriptionPresent("Butterfly")) {
            selectOneAREffect("Butterfly");
            recordVideoByAREffect(recordingTime);
            i = i + 1;
            testCase.clickItemWithDescription("Theme menu");
            commonModule.waitForText("Butterfly", 3000);
        }
        return i;
    }

    public void switchToVideoWithManualMode() throws UiObjectNotFoundException {
        commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        Assert.assertTrue("Switch to video failed.",
                commonModule.waitForDescription("Record", 3000));
    }

    public void recordVideoByManualMode(int recordingTime) throws UiObjectNotFoundException {
        switchToVideoWithManualMode();
        commonModule.clickDescription("Record");
        commonModule.wait(recordingTime);

        commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));// Tap stop button.
        Assert.assertTrue("Record video failed.",
                commonModule.waitForResourceId(moduleData.get("Content_Thumbnail_Id"), 5000));
    }

    public void selectScenesAndRecordVideos(String ... scenes) throws UiObjectNotFoundException{
        for (String scene : scenes) {
            if (commonModule.isDescriptionExists("Scenes")) {
                commonModule.clickDescription("Scenes");
            } else {
                // Tap the coordinate to click Scenes icon.
                testCase.clickPoint(commonModule.getX(60, 1280), commonModule.getY(70, 720));
            }
            commonModule.waitForText("Scene selection", 2000);

            if (commonModule.isTextContains(scene)
                    || commonModule.isDescriptionExists(scene)) {
                changeScenes(scene);
                recordVideoByManualMode(5);
            }
            else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);// Make scenes selector disappeared.
            }
        }
    }

    public void selectAllAppsAndRecordVideos(int recordingTime) throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        testCase.clickItemWithId("mode_selector_button");
        commonModule.wait(2);
        if (testCase.isViewWithTextPresent("Manual")) {
            Log.i(TAG, "Take camera video with manual");
            testCase.click("Manual");
            recordVideoByManualMode(recordingTime);
            commonModule.backOutToHomeScreen();
        }
        launchCameraPhotoApplication();
        testCase.clickItemWithId("mode_selector_button");
        commonModule.wait(2);
        if (testCase.isViewWithTextPresent("AR effect")) {
            Log.i(TAG, "Take camera video with ar effect");
            testCase.click("AR effect");
            commonModule.wait(2);
            selectOneAREffect("Dive");
            recordVideoByAREffect(recordingTime);
            commonModule.backOutToHomeScreen();
        }
    }

    public void recordVideoWithZoom() throws UiObjectNotFoundException {
        commonModule.backOutToHomeScreen();
        launchCameraPhotoApplication();
        commonModule.clickDescription("Record");
        commonModule.waitForId(moduleData.get("Record_Time_Container_Id"), 2000);
        for (int i = 0; i < 3; i++) {
            testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        }
        for (int j = 0; j < 3; j++) {
            testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        }
        commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));// Tap stop button.
//        Assert.assertTrue("Record video with zoom failed.",
//                commonModule.waitForResourceId(moduleData.get("Content_Thumbnail_Id"), 5000));
    }

	public void setGeoTaggingOff() throws UiObjectNotFoundException{
        launchCameraPhotoApplication();
        testCase.clickItemWithDescription("Menu");
        commonModule.wait(2);

        // Tap other settings icon.
        for (int i = 0; i < 5; i++) {
            if (commonModule.isResourceId(moduleData.get("Switch_Category"))
                    || commonModule.isTextExists("Geotagging")) {
                break;
            }

            if (commonModule.isResourceId(moduleData.get("Other_Settings_Icon_Id"))) {
                commonModule.clickResourceId(moduleData.get("Other_Settings_Icon_Id"));
            } else if (commonModule.isDescriptionExists("Other settings")) {
                commonModule.clickDescription("Other settings");
            } else {
                commonModule.clickResourceIdByInstance(moduleData.get("Camera_Setting_Tab_Icon"), 1);
            }
            commonModule.wait(1);

            if (i == 4) {
                testCase.failTest("Tap other setting icon failed.");
            }
        }

        if (commonModule.isResourceId(moduleData.get("Switch_Category"))) {
            commonModule.clickResourceId(moduleData.get("Switch_Category"));
        } else if (commonModule.isTextExists("Geotagging")) {
            commonModule.clickText("Geotagging");
        }

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        AcceptanceTestCase.assertTrue("Set Geo Tagging off failed.",
                !commonModule.waitForId("geo_tag_indicator", 3000));
	}

    public void changeWhiteBalance(String wb) throws UiObjectNotFoundException {
        launchCameraPhotoApplication();

        commonModule.clickDescription("Exposure value White balance");
        if (commonModule.waitForDescription(wb, 2000)) {
            commonModule.clickDescription(wb);
            verifyWhiteBalance(wb);
        } else if (wb.equals("Auto")) {
            testCase.clickPoint(commonModule.getX(579, 1794), commonModule.getY(209, 1080));
        } else if (wb.equals("Incandescent")) {
            testCase.clickPoint(commonModule.getX(579, 1794), commonModule.getY(374, 1080));
        } else if (wb.equals("Fluorescent")) {
            testCase.clickPoint(commonModule.getX(579, 1794), commonModule.getY(539, 1080));
        } else if (wb.equals("Daylight")) {
            testCase.clickPoint(commonModule.getX(579, 1794), commonModule.getY(704, 1080));
        } else if (wb.equals("Cloudy")) {
            testCase.clickPoint(commonModule.getX(579, 1794), commonModule.getY(869, 1080));
        }
    }

	public void verifyWhiteBalance(String wb) throws UiObjectNotFoundException{
	    if(!commonModule.isDescriptionExists(wb)){
            commonModule.clickDescription("Exposure value White balance");
        }
	    //commonModule.waitForDescription(wb, 5000);
	    commonModule.wait(5);
	    UiObject wbo = new UiObject(new UiSelector().description(wb));

	    AcceptanceTestCase.assertTrue("Change " + wb + " White balance failed.", wbo.isSelected());

	}

	public void selectAllWhiteBalanceAndTakePhotos() throws UiObjectNotFoundException{
	    changeWhiteBalance("Auto");
	    takePictures(1);

	    changeWhiteBalance("Incandescent");
        takePictures(1);

        changeWhiteBalance("Fluorescent");
        takePictures(1);

        changeWhiteBalance("Daylight");
        takePictures(1);

        changeWhiteBalance("Cloudy");
        takePictures(1);
	}

    public void selectAllWhiteBalanceAndRecordVideos(int recordSeconds)
            throws UiObjectNotFoundException {
        changeWhiteBalance("Auto");
        recordVideoByManualMode(recordSeconds);

        changeWhiteBalance("Incandescent");
        recordVideoByManualMode(recordSeconds);

        changeWhiteBalance("Fluorescent");
        recordVideoByManualMode(recordSeconds);

        changeWhiteBalance("Daylight");
        recordVideoByManualMode(recordSeconds);

        changeWhiteBalance("Cloudy");
        recordVideoByManualMode(recordSeconds);
    }

    public void setHDROnOrOff(String flag) throws UiObjectNotFoundException {

            launchCameraByMode("Manual");
            commonModule.clickDescription("Menu");
            commonModule.waitForText("HDR", 2000);

            UiObject hdr = new UiObject(new UiSelector().resourceId(
                    "com.sonyericsson.android.camera:id/switch_track"));
            if (flag == "ON") {
                if (!hdr.isChecked()) {
                    hdr.click();
                    commonModule.wait(3);
                }
//                Assert.assertTrue("Open HDR failed.", hdr.isChecked());
            } else if (flag == "OFF") {
                if (hdr.isChecked()) {
                    hdr.click();
                    commonModule.wait(3);
                }
//                Assert.assertTrue("Close HDR failed.", !hdr.isChecked());
            }

            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.waitForTextGone("HDR", 2000);

    }

    public void changeResolutionAndRecordVideo(String resolution, int recordingTime) throws UiObjectNotFoundException{
        if(!commonModule.isTextExists(resolution)){
            commonModule.clickDescription("Menu");
            commonModule.wait(2);

            commonModule.clickDescriptionContains("Resolution");
            commonModule.waitForText(resolution, 2000);
        }

        commonModule.clickText(resolution);

        if(commonModule.isTextExists("Video resolution")){
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }

        recordVideoByManualMode(recordingTime);

        commonModule.clickDescription("Menu");
        commonModule.wait(2);

        commonModule.clickDescriptionContains("Resolution");
        commonModule.waitForText(resolution, 2000);
    }

    public void selectAllResolutionAndRecordVideos(int recordingTime) throws UiObjectNotFoundException{
        commonModule.clickDescription("Menu");
        commonModule.wait(2);

        try{
            commonModule.clickResourceId("com.sonyericsson.android.camera:id/category");
        }catch(UiObjectNotFoundException e){
            testCase.clickPoint(commonModule.getX(280, 1196), commonModule.getY(540, 720));
        }

        if(commonModule.isTextExists("Full HD (30 fps)")){
            changeResolutionAndRecordVideo("Full HD (30 fps)", recordingTime);
        } else if(commonModule.isTextExists("Full HD")){
            changeResolutionAndRecordVideo("Full HD", recordingTime);
        }

        if(commonModule.isTextExists("Full HD (60 fps)")){
            changeResolutionAndRecordVideo("Full HD (60 fps)", recordingTime);
        }

        if(commonModule.isTextExists("HD")){
            changeResolutionAndRecordVideo("HD", recordingTime);
        } else if(commonModule.isTextExists("HD720p")){
            changeResolutionAndRecordVideo("HD720p", recordingTime);
        }

        if(commonModule.isTextExists("VGA")){
            changeResolutionAndRecordVideo("VGA", recordingTime);
        }

        if(commonModule.isTextExists("MMS")){
            changeResolutionAndRecordVideo("MMS", recordingTime);
        } else if(commonModule.isTextExists("Multimedia message")){
            changeResolutionAndRecordVideo("Multimedia message", recordingTime);
        }

        // Only Honami.
        if(commonModule.isTextExists("4K2K")){
            changeResolutionAndRecordVideo("4K2K", recordingTime);
        }
    }

    /*
    public void checkVideoSize(){
        commonModule.clickResourceId("com.sonyericsson.android.camera:id/content_thumbnail");
        if(!commonModule.isResourceId("android:id/home")){
            int[] screen = testCase.getScreenSize();
            testCase.clickPoint(screen[0]/4, screen[1]/4);
        }
        commonModule.waitForDescription("More options", 2000);

        commonModule.clickDescription("More options");
        commonModule.waitForText("Details", 2000);

        commonModule.clickText("Details");

    }
    */

    public void shortPressZoomKey(){
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        commonModule.wait(2);
        // AcceptanceTestCase.assertTrue("Zoom bar not display.--down",
        // commonModule.isResourceId(moduleData.get("Zoombar_Id")));
        // AcceptanceTestCase.assertTrue("Zoom bar not disappear.--down",
        // !commonModule.isResourceId(moduleData.get("Zoombar_Id")));

        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        commonModule.wait(2);
        // AcceptanceTestCase.assertTrue("Zoom bar not display.--up",
        // commonModule.isResourceId(moduleData.get("Zoombar_Id")));
        // AcceptanceTestCase.assertTrue("Zoom bar not disappear.--up",
        // !commonModule.isResourceId(moduleData.get("Zoombar_Id")));
    }

    public void longPressZoomKey() {
        commonModule.wait(2);
        testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_UP, 3000);
        for(int i=0; i<5; i++){
            if(commonModule.isResourceId(moduleData.get("Zoombar_Id"))){
                break;
            }else if(i==4){
                AcceptanceTestCase.fail("Zoom bar not display.--up");
            }else{
                testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_UP, 3000);
            }
        }

        commonModule.wait(2);

        testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_DOWN, 3000);
        for(int i=0; i<5; i++){
            if(commonModule.isResourceId(moduleData.get("Zoombar_Id"))){
                break;
            }else if(i==4){
                AcceptanceTestCase.fail("Zoom bar not display.--down");
            }else{
                testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_DOWN, 3000);
            }
        }
        commonModule.wait(2);
    }

    public void changeUseVolumeKeyAs(String volumeKey) throws UiObjectNotFoundException{
        commonModule.clickDescription("Menu");
        for (int i = 0; i < 3; i++) {
            if (commonModule.waitForText("Use Volume key as", 5000)) {
                break;
            }
            if (commonModule.waitForDescription("Other settings", 5000)) {
                commonModule.clickDescription("Other settings");
            } else if (commonModule.isResourceId(moduleData.get("Other_Settings_Icon_Id"))) {
                commonModule.clickResourceId(moduleData.get("Other_Settings_Icon_Id"));
            } else {
                testCase.clickPoint(commonModule.getX(295, 1920), commonModule.getY(267, 1080));
            }
        }

        commonModule.clickText("Use Volume key as");
        commonModule.waitForText(volumeKey, 2000);

        commonModule.clickText(volumeKey);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForTextGone("Use Volume key as", 2000);
    }

    public void verifyVolumeKeyAsVolume(){
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        commonModule.wait(2);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        commonModule.wait(2);

        testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_UP, 5000);
        commonModule.wait(3);
        testCase.longPressKey(KeyEvent.KEYCODE_VOLUME_DOWN, 5000);
        commonModule.wait(3);
    }

    public void verifyVolumeKeyAsShutter(){
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        commonModule.wait(5);
        AcceptanceTestCase.assertTrue("Use volume key as Shutter not work well",
                commonModule.waitForResourceId(moduleData.get("Record_Time_Container_Id"), 3000));
        commonModule.wait(2);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_DOWN);
        commonModule.wait(5);

        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        commonModule.wait(5);
        AcceptanceTestCase.assertTrue("Use volume key as Shutter not work well",
                commonModule.waitForResourceId(moduleData.get("Record_Time_Container_Id"), 3000));
        commonModule.wait(2);
        testCase.pressKey(KeyEvent.KEYCODE_VOLUME_UP);
        commonModule.wait(2);
    }

    public void holdCameraKey() {
        testCase.pressKey(KeyEvent.KEYCODE_CAMERA);
        commonModule.wait(2);
        removeAlertTitle();
        AcceptanceTestCase.assertTrue("Camera launch failed",
                commonModule.waitForResourceId(moduleData.get("Take_Photo_Main_Button_Id"), 2000));
    }

    public void verifyManualMode() {
    	removeAlertTitle();
        commonModule.wait(2);
        AcceptanceTestCase.assertTrue(
                "Manual Mode open failed",
                commonModule.isDescriptionExists("Flash")
                        && commonModule.isDescriptionExists("Exposure value White balance")
                        && commonModule.isResourceId(moduleData.get("Take_Photo_Main_Button_Id")));

    }

    public void launchCameraWithGeoActive() throws UiObjectNotFoundException{
        testCase.launchApp(CAMERA_PACKAGE, CAMERA_CLASS_NAME);
        testCase.setTimeout(6000);
        if (testCase.isViewWithIdPresent("alertTitle")) {
            testCase.clickItemWithText("Yes");
        }
        if(commonModule.isTextExists("Enable geotagging")){
            commonModule.clickText("OK");
            commonModule.waitForText("Location", 2000);

            commonModule.clickText("OFF");
            if(commonModule.isTextExists("Location consent")){
                commonModule.clickText("Agree");
            }
        }
        testCase.launchApp(CAMERA_PACKAGE, CAMERA_CLASS_NAME);

        testCase.assertViewWithIdPresent("main_button");
        testCase.resetTimeout();
    }

    public void takeCreateEffectVideo() throws UiObjectNotFoundException {
        commonModule.wait(2);
        if (commonModule.waitForText("OK", 5000)) {
            commonModule.clickText("OK");
        }
        if (!commonModule.isResourceId("com.sonyericsson.android.addoncamera.artfilter:id/effect_selector_icon")) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        commonModule.clickResourceId("com.sonyericsson.android.addoncamera.artfilter:id/sub_button");
        commonModule.wait(2);
        // AcceptanceTestCase.assertTrue("Video recording failed!",
        // commonModule.isResourceId("com.sonyericsson.android.camera:id/recording_time"));
    }

    public void stopCreateEffectVideoRecording() throws UiObjectNotFoundException {

        testCase.pressKey(KeyEvent.KEYCODE_BACK);

    }

    public void repeatChangeEffectAndTakePicture(int timeout) throws UiObjectNotFoundException {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            selectAllCreativeEffectAndTakePictures();
            commonModule.wait(60);
        }
    }

    public void switchMicrophone() throws UiObjectNotFoundException{
    	// Press more menu button.
        testCase.clickPoint(commonModule.getX(100, 1794), commonModule.getY(1000, 1080));
        commonModule.wait(3);
        if(commonModule.isResourceId("com.sonymobile.android.addoncamera.dual:id/tab_middle")){
        	commonModule.clickResourceId("com.sonymobile.android.addoncamera.dual:id/tab_middle");
        } else{
        	testCase.clickPoint(commonModule.getX(270, 1794), commonModule.getY(540, 1080));
        }
        commonModule.wait(2);
        if (commonModule.isTextExists("Microphone")) {
        			commonModule.clickText("Microphone");
        } else{
        			testCase.clickPoint(commonModule.getX(590, 1794), commonModule.getY(560, 1080));
        }
        commonModule.wait(2);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        AcceptanceTestCase.assertTrue("Back to camera view failed.",
                commonModule.waitForTextGone("Microphone", 3000));
        commonModule.wait(2);
        }

    public void recordVideoFaceIn(int recordingTime) throws UiObjectNotFoundException {
    	if(commonModule.waitForResourceId(moduleData.get("Face_In_Record_Button_Id"), 5000)){
        commonModule.clickResourceId(moduleData.get("Face_In_Record_Button_Id"));
        }else{
        	testCase.clickPoint(commonModule.getX(1650, 1794), commonModule.getY(700, 1080));
        }
        commonModule.wait(2);
        // testCase.assertViewWithIdPresent("recording_time");
        commonModule.wait(recordingTime);
        commonModule.clickResourceId(moduleData.get("Face_In_Record_Button_Id"));
    }

    public void selectAllCreativeEffectAndRecordVideo() throws UiObjectNotFoundException {
        for (int i = 0; i < 19; i++) {
            Log.i(TAG, "Select Creative Effect " + (i + 1));
            if (i == 0) {
                // The first one.
                selectOneCreativeEffect(100);
            } else if (i == 1) {
                // The second one.
                selectOneCreativeEffect(400);
            } else if (i == 2) {
                // The third one.
                selectOneCreativeEffect(700);
            } else if (i > 2 && i < 18) {
                selectOneCreativeEffect(800);
            } else {
                // The last one.
                selectOneCreativeEffect(900);
            }
            takeCreateEffectVideo();
            for(int j=0;j<5;j++){
				takePictures(1);
				commonModule.wait(2);
			}
            commonModule.wait(10);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(2);
        }
	}

    public void openCameraAndTakePicture(String cameraMode) throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        selectOneEffectFromCameraSettings(cameraMode);

        if (commonModule.waitForText("Camera unavailable during call", 3000)) {
            Log.i("TAG", cameraMode + " Camera unavailable during call");
            commonModule.clickText("OK");
        } else {
            commonModule.wait(2);
            if (commonModule.isTextExists("OK")) {
                commonModule.clickText("OK");
            }
            takePictures(1);
            commonModule.backOutToHomeScreen();

        }
    }

    public void openCameraAndRecordVideo(String cameraMode, int recordingTime)
            throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        selectOneEffectFromCameraSettings(cameraMode);

        if (commonModule.waitForText("Camera unavailable during call", 3000)) {
            Log.i("TAG", cameraMode + " Camera unavailable during call");
            commonModule.clickText("OK");
        } else {
            commonModule.wait(2);
            if (commonModule.isTextExists("OK")) {
                commonModule.clickText("OK");
            }
            recordVideo(recordingTime);
            commonModule.backOutToHomeScreen();

        }
    }

    public void pauseWhenRecordVideo(int recordingTime, int time) throws UiObjectNotFoundException {
        if (commonModule.isDescriptionExists("Record")) {
            commonModule.clickDescription("Record");
        } else {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        }
        commonModule.wait(2);
        // testCase.assertViewWithIdPresent("recording_time");

        int consumptionTime = 0;

        for (int i = 0; i < time; i++) {
            commonModule.clickResourceId(moduleData.get("Take_Photo_Main_Button_Id"));
            commonModule.wait(3);
            consumptionTime = consumptionTime + 3;
            UiObject chapter = new UiObject(
                    new UiSelector()
                            .resourceId("com.sonyericsson.android.camera:id/thumbnail_container"));
            UiObject emailItem = chapter.getChild(new UiSelector().className(
                    "android.widget.ImageView").index(0));
            AcceptanceTestCase.assertTrue("Chapter cannot display", emailItem.exists());
        }
        if (consumptionTime < recordingTime) {
            commonModule.wait(recordingTime - consumptionTime);
        }
        commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
    }

    public void takeAndPostPictureViaSocialLive() throws UiObjectNotFoundException {
        if (commonModule.waitForText("Share moments live on Facebook", 5000)) {
            UiObject obj = new UiObject(new UiSelector().text("Yes, I agree"));
            if (!obj.isChecked()) {
                obj.click();
            }
            commonModule.wait(2);
            commonModule.clickText("Log in");
            commonModule.waitForIdGone("LoginProgress", 30 * 1000);
            if (commonModule.isTextExists("Facebook")) {
                commonModule.clickText("OK");
            }
            commonModule.waitForIdGone("LoginProgress", 30 * 1000);
            testCase.scrollLeft();
        }

        commonModule.clickResourceId("com.bambuser.sociallive:id/BroadcastButton");
        commonModule.wait(2);
        commonModule.clickResourceId("com.bambuser.sociallive:id/TitleEditText");
        commonModule.emptyEditorByInstance(0);
        testCase.enterText("Test");
        if (testCase.isInputMethodWindowOpened()) {
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        if (commonModule.isTextExists("All friends")) {
            commonModule.clickText("All friends");
        }
        commonModule.wait(2);
        commonModule.clickText("OK");

        commonModule.clickResourceId("com.bambuser.sociallive:id/TakePictureButton");
        if (commonModule.waitForText("Connection failed", 120 * 1000)) {
            commonModule.clickText("OK");
            AcceptanceTestCase.assertTrue("Social live cannot connect the facebook", false);
        } else {
            // TODO add the operations when upload picture successfully
        }
    }

    public void startCameraByPressCameraKey () {
        testCase.longPressKey(KeyEvent.KEYCODE_CAMERA, 500);
        AcceptanceTestCase.assertTrue("Launch Camera by press Camera key failed.",
                commonModule.waitForResourceId(moduleData.get("Take_Photo_Main_Button_Id"), 2000));
    }

    public void switchToFrontCamera() throws UiObjectNotFoundException {
        if (commonModule.isDescriptionExists("Double tap to switch to rear camera")) {
            return;
        }
        commonModule.clickDescription("Double tap to switch to front camera");
//        AcceptanceTestCase.assertTrue("Switch to Front Camera failed.",
//                commonModule.waitForDescription("Double tap to switch to rear camera", 3000));
    }

    protected void changePhotoResolutionBylocation(int itemNum) throws UiObjectNotFoundException {
        commonModule.clickDescription("Menu");
        commonModule.waitForDescription("Camera settings", 5000);

        // Tap Resolution text.
        testCase.clickPoint(commonModule.getX(410, 1794), commonModule.getY(540, 1080));
        commonModule.wait(1);
        // Select resolution.
        testCase.clickPoint(commonModule.getX(475 + (171 * itemNum), 1794),
                commonModule.getY(450, 1080));
        commonModule.wait(1);
        testCase.pressBackNTimes(1);
    }

    public void selectAllPhotoResolutionAndTakePictures() throws UiObjectNotFoundException {
        for (int i = 0; i < 6; i++) {
            changePhotoResolutionBylocation(i);
            takePictures(1);
        }
    }

    protected void changeFrontVideoResolution(int itemNum) throws UiObjectNotFoundException {
        commonModule.clickDescription("Menu");
        commonModule.wait(1);
        // Tap video setting icon.
        testCase.clickPoint(commonModule.getX(267, 1794), commonModule.getY(540, 1080));
        commonModule.wait(1);
        // Tap Video resolution text.
        testCase.clickPoint(commonModule.getX(414, 1794), commonModule.getY(540, 1080));
        commonModule.wait(1);
        // Select Video resolution.
        testCase.clickPoint(commonModule.getX(547 + (171 * itemNum), 1794),
                commonModule.getY(450, 1080));
        commonModule.wait(1);
        testCase.pressBackNTimes(1);
    }

    public void selectAllFrontVideoResolutionAndRecordVideos(int recordingMin) throws UiObjectNotFoundException {
        for (int i = 0; i < 4; i++) {
            changeFrontVideoResolution(i);
            recordVideo(recordingMin);
        }
    }

    public void setLastPictureAsContactPhoto(String contactName) throws UiObjectNotFoundException {
        if(commonModule.isResourceId(moduleData.get("Recent_Shot_Icon_Id"))){
            clickRecentShot();
        }

        // Make video information displayed.
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        for (int i = 0; i < 5; i++) {
            if (commonModule.waitForResourceId(
                    "com.sonyericsson.album:id/action_layer_action_delete", 2000)) {
                break;
            }
            testCase.clickPoint(width / 4, height / 4);
        }

        commonModule.pressMoreOption();
        commonModule.waitForText("Use as", 2000);

        commonModule.clickText("Use as");
        commonModule.waitForText("Contact picture", 2000);

        commonModule.clickText("Contact picture");
        commonModule.waitForText("Contacts", 2000);

        commonModule.scrollFindText(contactName);
        if (testCase.isViewWithTextPresent("Complete action using")) {
            testCase.click("Crop picture");
            if (testCase.isViewWithTextPresent("Just once")) {
                testCase.click("Just once");
            }
        }
        commonModule.waitForText("Crop", 2000);

        commonModule.clickText("Crop");
        commonModule.waitForTextGone("Crop", 3000);
    }

    public void setLastPictureAsWallpaper() throws UiObjectNotFoundException {
        if(commonModule.isResourceId(moduleData.get("Recent_Shot_Icon_Id"))){
            clickRecentShot();
        }

        // Make video information displayed.
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        for (int i = 0; i < 5; i++) {
            if (commonModule.waitForResourceId(
                    "com.sonyericsson.album:id/action_layer_action_delete", 2000)) {
                break;
            }
            testCase.clickPoint(width / 4, height / 4);
        }
        commonModule.pressMoreOption();
        commonModule.waitForText("Use as", 2000);

        commonModule.clickText("Use as");
        commonModule.waitForText("Wallpaper", 2000);

        commonModule.clickText("Wallpaper");
        if (testCase.isViewWithTextPresent("Complete action using")) {
            testCase.click("Crop picture");
            if (testCase.isViewWithTextPresent("Just once")) {
                testCase.click("Just once");
            }
        }
        commonModule.waitForText("Crop", 2000);

        commonModule.clickText("Crop");
        commonModule.waitForTextGone("Crop", 5 * 1000);
    }

    public void startCameraBySleepMode() {
        commonModule.lockScreen();
        startCameraByPressCameraKey();
    }

    public void takePhotoBySleepModeAndCheck() throws UiObjectNotFoundException {
        startCameraBySleepMode();
        takePictures(1);
        checkPictures(1);
    }

    public void takePhotoWhileRecordingVideoAndCheck() throws UiObjectNotFoundException {
        // Start record video.
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
        commonModule.wait(5);

        // Press camera soft key to capture photo while recording.
        if (commonModule.isDescriptionExists("Camera key")) {
            commonModule.clickDescription("Camera key");
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(960, 1080));
        }
        commonModule.wait(5);

        // End recording.
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
        commonModule.wait(3);

        playRecentVideoUntilEnd(10);
    }

    public void takePhotosBeforeOrWhileRecordingVideoAndCheck() throws UiObjectNotFoundException {
        launchCameraPhotoApplication();
        takePictures(1);
        takePhotoWhileRecordingVideoAndCheck();
        checkPictures(3);
    }

    public void changeSelftimerSetting(String value) throws UiObjectNotFoundException {
        if (!commonModule.isDescriptionExists("Camera key")) {
            launchCameraByMode("Superior auto");
        }
        commonModule.clickDescription("Menu");
        if (commonModule.waitForText("Self-timer", 2000)) {
            commonModule.clickText("Self-timer");
        } else {
            testCase.clickPoint(commonModule.getX(585, 1794), commonModule.getY(860, 1080));
        }

        if(commonModule.waitForText(value, 2000)) {
            commonModule.clickText(value);
        } else if (value.equals("On (10 sec.)")){
            testCase.clickPoint(commonModule.getX(728, 1794), commonModule.getY(450, 1080));
        } else if (value.equals("On (2 sec.)")) {
            testCase.clickPoint(commonModule.getX(900, 1794), commonModule.getY(450, 1080));
        } else if (value.equals("Off")) {
            testCase.clickPoint(commonModule.getX(1070, 1794), commonModule.getY(450, 1080));
        }
        commonModule.wait(2);
    }

    public void cancelTakeSelftimerPicture() {
        pressCameraButton();
        commonModule.wait(2);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(3);
    }

    public void takeSelftimerPicture(String value) {
        pressCameraButton();
        AcceptanceTestCase.assertTrue(!commonModule.waitForDescription("Menu", 3000));
        if (value.equals("On (10 sec.)")) {
            commonModule.wait(10);
        } else if (value.equals("On (2 sec.)")) {
            commonModule.wait(2);
        }
        AcceptanceTestCase.assertTrue("Take selftimer picture failed.",
                commonModule.waitForDescription("Menu", 3000)
                        || commonModule.waitForDescription("Camera key", 3000));
    }

    public void setTouchCaptureSettingOn() throws UiObjectNotFoundException {
        commonModule.clickDescription("Menu");

        if (commonModule.waitForDescription("Other settings", 2000)) {
            commonModule.clickDescription("Other settings");
        } else {
            testCase.clickPoint(commonModule.getX(270, 1794), commonModule.getY(290, 1080));
        }

        if (commonModule.waitForText("Touch capture", 2000)) {
            commonModule.clickText("Touch capture");
        } else {
            testCase.clickPoint(commonModule.getX(750, 1794), commonModule.getY(540, 1080));
        }

        testCase.pressBackNTimes(1);
    }

    public void touchScreenToCapture() {
        int width = testCase.getUiDevice().getDisplayWidth();
        int height = testCase.getUiDevice().getDisplayHeight();

        testCase.clickPoint(width / 3, height / 3);
    }

    public void touchScreenToCaptureWhileRecordVideo() throws UiObjectNotFoundException {
        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
        commonModule.wait(5);

        touchScreenToCapture();
        commonModule.wait(5);

        if (commonModule.isResourceId(moduleData.get("Common_Record_Button_Id"))) {
            commonModule.clickResourceId(moduleData.get("Common_Record_Button_Id"));
        } else {
            testCase.clickPoint(commonModule.getX(1644, 1794), commonModule.getY(696, 1080));
        }
    }
    public void changeCameraStorage(String storage) throws UiObjectNotFoundException {
    				// Press more menu button.
        commonModule.waitForId("effect_selector_icon", 5000);
        testCase.clickPoint(commonModule.getX(100, 1794), commonModule.getY(1000, 1080));
        commonModule.wait(3);

        commonModule.clickDescription("Other settings");
        commonModule.wait(2);
        commonModule.clickText("Data storage");
        commonModule.wait(2);
        commonModule.clickText(storage);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
    }
}
