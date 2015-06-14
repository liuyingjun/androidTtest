package com.module.album;


import java.util.HashMap;

import android.graphics.Point;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import junit.framework.Assert;

public class AlbumModule implements IAlbum {

    AcceptanceTestCase testCase;
    
    HashMap<String, String> moduleData;
    
    CommonModule commonModule;

    String TAG = "Reliability";

    public AlbumModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
        moduleData = ModuleDataParser.getModuleData("sketch");
    }

    public void startAlbum() throws UiObjectNotFoundException{
        testCase.launchApp("com.sonyericsson.album", "com.sonyericsson.album.MainActivity");
        commonModule.wait(2);
        if (commonModule.waitForText("Home", 3000)) {
            commonModule.clickText("Home");
        } else if (!commonModule.isTextExists("Album")) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Home", 3000);

            commonModule.clickText("Home");
        }
    }

    public void openOnePicture() throws UiObjectNotFoundException{
        startAlbum();
        if (!commonModule.isTextExists("Camera")) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Camera", 2000);
        }
        commonModule.clickText("Camera");
        commonModule.wait(2);

        int[] screen = testCase.getScreenSize();
        testCase.clickPoint(screen[0] / 10, screen[1] / 6);
        commonModule.waitForText("Photos", 2000);

        testCase.clickPoint(screen[0] / 10, screen[1] / 4);
        commonModule.waitForTextGone("Photos", 2000);

        for(int j=0;j<4;j++){
            commonModule.wait(2);

            if(commonModule.isResourceId("com.sonyericsson.album:id/action_layer_title")){
                break;
            }else{
                testCase.clickPoint(commonModule.getX(800,1080), commonModule.getY(480,1776));
            }
        }
//        if (screen[0] > screen[1]) {
//            testCase.clickPoint(commonModule.getX(1260, 1794), commonModule.getY(420, 1080));
//        } else {
//            testCase.clickPoint(commonModule.getX(805, 1080), commonModule.getY(475, 1794));
//        }

        testCase.clickPoint(screen[0] / 3, screen[1] / 3);
        Assert.assertTrue("Open a picture failed.",
                 commonModule.waitForResourceId(
                                "com.sonyericsson.album:id/action_layer_title", 2000));
    }

    public void viewPictureInAlbum(int viewCount) {
        // Album Home page
        testCase.clickId("up");
        testCase.click("Home");
        commonModule.wait(1);
        // Open picture
        testCase.clickPoint(commonModule.getX(200), commonModule.getY(660));
        commonModule.wait(1);
        scanPicture(viewCount);
    }

    public void deletePictureInAlbum() {
        viewPictureInAlbum(0);
        if (!testCase.isViewWithIdPresent("action_layer_action_delete")) {
            testCase.clickPoint(commonModule.getX(200), commonModule.getY(660));
        }
        testCase.clickId("action_layer_action_delete");
        if (commonModule.isTextExists("Delete photo?")) {
            testCase.click("Delete");
            testCase.assertViewWithIdNotPresent("alertTitle");
        }
        testCase.assertViewWithIdPresent("action_layer_action_delete");
    }

    public void openScreenshot() throws UiObjectNotFoundException {
        startAlbum();
        if (!commonModule.isTextExists("Home")) {
            commonModule.clickResourceId("android:id/home");
            commonModule.waitForText("Home", 2000);
        }
        commonModule.clickText("Home");
        commonModule.wait(2);

        int[] screen = testCase.getScreenSize();
        testCase.clickPoint(screen[0] / 10, screen[1] / 2);
        commonModule.waitForResourceId("com.sonyericsson.album:id/action_layer", 2000);

        testCase.clickPoint(screen[0] / 2, screen[1] / 2);
        Assert.assertTrue("Open a screenshot failed.",
                commonModule.waitForResourceId("com.sonyericsson.album:id/action_layer_title", 2000)
                        && commonModule.waitForDescription("More options", 2000));
    }

    public void selectSlideshow() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.waitForText("Slideshow", 2000);

        commonModule.clickText("Slideshow");
        AcceptanceTestCase.assertTrue(
                "Select slideshow failed.",
                !commonModule.isTextExists("Slideshow")
                        && !commonModule.isResourceId("android:id/home"));

        commonModule.wait(5);
//        int[] screen = testCase.getScreenSize();
//        testCase.clickPoint(screen[0] / 2, screen[1] / 2);
//        commonModule.waitForDescription("More options", 2000);
    }

    public void rotatePicture() throws UiObjectNotFoundException {
        // Rotating picture to 4 directions.
        if (!commonModule.isResourceId("com.sonyericsson.album:id/action_layer_action_delete")) {
            openScreenshot();
        }
        for (int i = 0; i < 4; i++) {
        	if(!commonModule.isTextExists("Rotate")){
            commonModule.pressMoreOption();
            }
            commonModule.waitForText("Rotate", 2000);

            commonModule.clickText("Rotate");
            commonModule.waitForDescription("More options", 2000);
        }
    }

    public void copyToSdcard() throws UiObjectNotFoundException {
        if (!commonModule.isResourceId("com.sonyericsson.album:id/action_layer_action_delete")) {
            openScreenshot();
        }
    	if(!commonModule.isTextExists("Print")){
        commonModule.pressMoreOption();
        }
        commonModule.waitForText("Print", 2000);

        commonModule.clickText("Print");
        commonModule.waitForText("Save", 2000);

        commonModule.clickText("Save");
        if (!commonModule.waitForText("Save to", 2000)) {
            commonModule.clickResourceId("android:id/home");
        }
        commonModule.waitForText("Save to", 2000);
        if (!commonModule.isTextExists("SD Card")) {
            testCase.failTest("Not insert SD card.");
        } else {
            commonModule.clickText("SD Card");
            commonModule.waitForText("Save", 2000);

            commonModule.clickText("Save");
            AcceptanceTestCase.assertTrue("Copy screenshot to SD card failed.",
                    commonModule.waitForDescription("Delete", 5 * 1000));
        }
    }

    public void tagPictureOnMap() throws UiObjectNotFoundException {

        commonModule.pressMoreOption();

        if(commonModule.isTextExists("Add geotag")){
            commonModule.clickText("Add geotag");
        }else if(commonModule.isTextExists("Edit geotag")){
            commonModule.clickText("Edit geotag");
        }

        commonModule.clickDescription("Search on map");
        commonModule.waitForResourceId("android:id/search_src_text", 2000);// Search editor.

        testCase.enterText("Beijing");
        testCase.enterText("\n");
        commonModule.wait(5);

        int[] screen = testCase.getScreenSize();
        for (int i = 0; i < 10; i++) {
            UiObject okButton = new UiObject(new UiSelector().text("OK"));
            if(okButton.isEnabled()){
                break;
            }
            testCase.clickPoint(screen[0] / 3, screen[1] / 3);
            commonModule.wait(2);
        }

        commonModule.clickText("OK");
        commonModule.wait(2);

//        testCase.clickPoint(screen[0] / 3, screen[1] / 3);
//        commonModule.wait(2);

        AcceptanceTestCase.assertTrue("Search the map failed, maybe network is not good enough.",
                commonModule.waitForTextContains("China", 10 * 1000));
    }

    public void checkPictureView() throws UiObjectNotFoundException {
        commonModule.wait(2);
        AcceptanceTestCase.assertTrue("Album picture view display failed",
                commonModule.isResourceId("com.sonyericsson.album:id/currentscenicview"));

    }

    public void viewSlideshow(int duration) throws UiObjectNotFoundException {
        selectSlideshow();
        commonModule.wait(duration);
        checkPictureView();
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }

    public void startOnlineAlbum() throws UiObjectNotFoundException {
        testCase.launchApp("com.sonyericsson.album", "com.sonyericsson.album.MainActivity");
        commonModule.wait(2);
        // Album Home page
        testCase.clickId("up");
        commonModule.wait(2);
        testCase.click("Facebook");
        if (commonModule.isTextExists("OK")) {
            commonModule.clickText("OK");
        }
        if (commonModule.isTextExists("Done")) {
            commonModule.clickText("Done");
        }
        commonModule.wait(30);
        AcceptanceTestCase.assertTrue("Facebook is not opened",
                commonModule.waitForDescription("Facebook, Open navigation drawer", 5000));

        testCase.clickPoint(commonModule.getX(500), commonModule.getY(300));

        commonModule.wait(2);
        AcceptanceTestCase.assertFalse(
                "Online Album loading failed, or no photo exist in this facebook account",
                commonModule.isDescriptionExists("Facebook, Open navigation drawer"));
        testCase.clickPoint(commonModule.getX(250), commonModule.getY(550));
    }

    public void scanPicture(int viewCount) {
        // Scroll right and view picture
        commonModule.wait(2);
        for (int i = 0; i < viewCount; i++) {
            testCase.scrollRight();
            testCase.assertViewWithIdPresent("action_layer");
            commonModule.wait(1);
        }
    }

    /**
     * from left to right is the first, second, third, forth,fifth.... there are
     * 4 pictures/line by default, and the first picture is 4times larger than
     * other picture
     *
     * @param location
     */
    public Point getCoordinateOfPicture(int location) {

        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();
        System.out.println("height="+height);
        int pictureStartPointY = 0;
        if (height == 1184) {
            pictureStartPointY = commonModule.getY(344, 1184);
        } else if (height == 1776) {
            pictureStartPointY = commonModule.getY(692, 1776);
        }

        Point p = new Point();
        int x = 0;
        int y = 0;
        if (location == 1 || location == 2 || location == 5 || location == 6) {
            x = commonModule.getX(175, 720);
            y = commonModule.getY(511, 1184);
        } else if (location % 4 == 0) {
            x = (4 * 2 - 1) * width / 8;
            y = pictureStartPointY + ((location / 4) * 2 - 1) * (height - pictureStartPointY) / 8;

        } else {
            x = (location % 4 * 2 - 1) * width / 8;
            y = pictureStartPointY + ((location / 4) * 2 + 1) * (height - pictureStartPointY) / 8;
        }
        System.out.println("location:" + location + "   ;x=" + x + "   ;y=" + y);
        p.set(x, y);

        return p;
    }

    /**
     * from left to right is the first, second, third, forth,fifth.... there are
     * 7 pictures/line by default, and the first picture is 4times larger than
     * other picture
     *
     * @param location
     */
    public Point getCoordinateOfPictureByLandscape(int location) {

        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();
        int pictureStartPointY = commonModule.getY(548, 1080);
        System.out.println("height="+height+";    width="+width+";    starty="+pictureStartPointY);

        Point p = new Point();
        int x = 0;
        int y = 0;
        if (location == 1 || location == 2 || location == 8 || location == 9) {
            x = commonModule.getX(254, 1794);
            y = commonModule.getY(946, 1080);
        } else if (location % 7 == 0) {
            x = (7 * 2 - 1) * width / 14;
            y = pictureStartPointY + ((location / 7) * 2 - 1) * (height - pictureStartPointY) / 3;

        } else {
            x = (location % 7 * 2 - 1) * width / 14;
            y = pictureStartPointY + ((location / 7) * 2 + 1) * (height - pictureStartPointY) / 3;
        }
        System.out.println("location:" + location + "   ;x=" + x + "   ;y=" + y);
        p.set(x, y);

        return p;
    }

    public void selectItemFromMenu() throws UiObjectNotFoundException {
        commonModule.pressMoreOption();

        commonModule.clickText("Select items");
    }

    public void selectPicture(int location) {
        Point point = getCoordinateOfPicture(location);
        testCase.clickPoint(point.x, point.y);
        commonModule.wait(1);
    }

    public void selectPictureByLandscape(int location) {
        Point point = getCoordinateOfPictureByLandscape(location);
        testCase.clickPoint(point.x, point.y);
        commonModule.wait(5);
    }

    public void longTapPicture(int location) {
        Point point = getCoordinateOfPicture(location);

        testCase.longPressCoordinates(point.x, point.y, 3000);
    }

    public void markAndUnMarkOnePictureFromMenu() throws UiObjectNotFoundException {
        selectItemFromMenu();

        // mark 1
        selectPicture(1);
        testCase.assertTextPresent("1");

        // unmark
        selectPicture(1);
        testCase.assertTextPresent("0");

        testCase.pressBackNTimes(1);
    }

    public void markSeveralItemsAndDelete() throws UiObjectNotFoundException {
        selectItemFromMenu();

        selectPicture(1);
        selectPicture(3);
        selectPicture(4);
        selectPicture(7);

        commonModule.clickResourceId("com.sonyericsson.album:id/selectmode_option_delete");

        commonModule.waitForText("Delete items?", 3000);

        commonModule.clickText("Delete");

    }

    public void markItemsByLongTap(boolean back) {
        longTapPicture(1);

        selectPicture(7);
        selectPicture(8);
        selectPicture(9);

        AcceptanceTestCase.assertTrue("No picture marked", commonModule.waitForResourceId(
                "com.sonyericsson.album:id/selectmode_option_delete", 3000));

        if (back) {
            commonModule.pressKey(KeyEvent.KEYCODE_BACK);
            AcceptanceTestCase.assertTrue("Should exit mark mode and delete icon disappear",
                    !commonModule.waitForResourceId(
                            "com.sonyericsson.album:id/selectmode_option_delete", 3000));
        }
    }

    public void playVideo() {
        // Make sure entered Album, and select one video.
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
    }

    public void selectDeleteButCancel() throws UiObjectNotFoundException {
        int height = testCase.getUiDevice().getDisplayHeight();
        int width = testCase.getUiDevice().getDisplayWidth();

        testCase.clickPoint(width / 4, height / 4);
        commonModule.waitForDescription("Delete", 2000);

        commonModule.clickDescription("Delete");
        commonModule.waitForText("Cancel", 2000);

        commonModule.clickText("Cancel");
        commonModule.wait(1);
    }

    public void deletePictureOrVideo() throws UiObjectNotFoundException {
        if(!commonModule.isDescriptionExists("Delete")) {
            int height = testCase.getUiDevice().getDisplayHeight();
            int width = testCase.getUiDevice().getDisplayWidth();

            testCase.clickPoint(width / 4, height / 4);
            commonModule.waitForDescription("Delete", 2000);
        }
        commonModule.clickDescription("Delete");
        commonModule.waitForText("Delete", 2000);

        commonModule.clickText("Delete");
        commonModule.wait(1);
    }

    public void setPictureAsContactPhoto(String contactName) throws UiObjectNotFoundException {
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

    public void setPictureAsWallpaper() throws UiObjectNotFoundException {
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

    public void flickLeftAndRightToCheckPictures() {
        testCase.scrollRight(2);
        testCase.scrollLeft(2);
    }

    public void editPictureWithPhotoEditor() throws UiObjectNotFoundException {
        swipeToPictureView();

        commonModule.clickDescription("Edit photo");
        commonModule.clickText("Photo editor");
        if (commonModule.isTextExists("Just once")) {
            commonModule.clickText("Just once");
        }

        commonModule.clickResourceId("com.sonyericsson.photoeditor:id/fxButton");
        commonModule.wait(2);
        UiObject list1 = new UiObject(
                new UiSelector().resourceId("com.sonyericsson.photoeditor:id/listFilters"));
        UiObject filItem = list1.getChild(new UiSelector().className("android.view.View").index(1));
        commonModule.wait(2);
        filItem.click();

        commonModule.wait(2);
        commonModule.clickResourceId("com.sonyericsson.photoeditor:id/borderButton");
        commonModule.wait(2);
        UiObject list2 = new UiObject(
                new UiSelector().resourceId("com.sonyericsson.photoeditor:id/listBorders"));
        UiObject borItem = list2.getChild(new UiSelector().className("android.view.View").index(1));
        commonModule.wait(2);
        borItem.click();

        commonModule.clickText("SAVE");

        commonModule.wait(2);
    }

    public void editPictureWithPhotos() throws UiObjectNotFoundException {
    			swipeToPictureView();

        commonModule.clickDescription("Edit photo");
        commonModule.clickText("Photos");
        	if(commonModule.isTextExists("Just once")){
        commonModule.clickText("Just once");
						}
        commonModule.clickDescription("AUTO");
        commonModule.clickText("High");
        commonModule.clickResourceId("com.google.android.apps.plus:id/apply_button");

        commonModule.wait(2);

        commonModule.clickDescription("CROP");
        commonModule.clickText("Square");
        commonModule.clickResourceId("com.google.android.apps.plus:id/apply_button");

        commonModule.wait(2);

        commonModule.clickDescription("ROTATE");
        commonModule.clickText("Rotate Right");
        commonModule.clickResourceId("com.google.android.apps.plus:id/apply_button");

        commonModule.wait(2);

        commonModule.clickDescription("LOOKS");
        UiObject list = new UiObject(
                new UiSelector().resourceId("com.google.android.apps.plus:id/filter_list"));
        UiObject filItem = list.getChild(new UiSelector().className("android.view.View").index(2));
        commonModule.wait(2);
        filItem.click();
        commonModule.wait(2);
        commonModule.clickResourceId("com.google.android.apps.plus:id/apply_button");

        commonModule.wait(2);
        commonModule.clickText("Done");

        commonModule.wait(2);
    }

    public void editPictureWithSketch() throws UiObjectNotFoundException {
        swipeToPictureView();

        commonModule.clickDescription("Edit photo");
        commonModule.clickText("Sketch");
        if (commonModule.isTextExists("Just once")) {
            commonModule.clickText("Just once");
        }
        if (!commonModule.waitForDescription("Edit photo", 2000)
                && commonModule.isResourceId("com.sonyericsson.album:id/action_layer")) {
            int height = testCase.getUiDevice().getDisplayHeight();
            int width = testCase.getUiDevice().getDisplayWidth();
            testCase.clickPoint(width / 4, height / 4);
        }
        commonModule.clickDescription("Edit photo");
        commonModule.clickText("Sketch");
        if (commonModule.isTextExists("Just once")) {
            commonModule.clickText("Just once");
        }

        commonModule.clickDescription("Flip");

        commonModule.wait(2);

        commonModule.clickDescription("Crop");
        commonModule.clickDescription("Ellipse");
        commonModule.clickResourceId("android:id/action_mode_close_button");

        commonModule.wait(2);

        commonModule.clickResourceId("com.sonymobile.sketch:id/select_object_mode_btn");
        if (commonModule.isTextExists("Got it!")) {
            commonModule.clickText("Got it!");
        }
        commonModule.clickDescription("Brush picker");
        commonModule.wait(2);

        commonModule.clickResourceIdByInstance("com.sonymobile.sketch:id/background", 3);
        commonModule.wait(2);

        commonModule.clickDescription("Brush color selected");
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        testCase.scrollUp();

        commonModule.wait(2);
        commonModule.clickDescription("Text tool selected");
        commonModule.clickText("Enter text");
        testCase.enterText("This is for reliability test");
        commonModule.clickText("Done");

        commonModule.clickResourceId("android:id/action_mode_close_button");

        commonModule.clickText("Done");

        commonModule.wait(2);
    }

    public void shareOnePictureToFacebook(String comments) throws UiObjectNotFoundException{
        openOnePicture();

        testCase.clickPoint(testCase.getScreenSize()[0] / 3, testCase.getScreenSize()[1] / 3);

        //click share icon
        commonModule.clickResourceId("android:id/expand_activities_button");

        if(commonModule.waitForText("Facebook",3000)){
            commonModule.clickText("Facebook");
        }else if (commonModule.waitForText("See all",3000)){
            commonModule.clickText("See all");
            commonModule.wait(2);

            if(!commonModule.scrollFindText("Facebook")){
                commonModule.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(2);

                commonModule.clickResourceId("android:id/default_activity_button");
            }
        }

        AcceptanceTestCase.assertTrue("Facebook share screen display", commonModule.waitForText("Write Post", 8000));

        commonModule.wait(10);
        testCase.enterText(comments);
        commonModule.wait(2);
        commonModule.clickResourceId("com.facebook.katana:id/primary_named_button");
        commonModule.wait(5);
        AcceptanceTestCase.assertTrue("After share completed, share icon should display again",
                commonModule.waitForResourceId("android:id/expand_activities_button", 5000));

    }

    public void openTheFirstAlbumFromFacebook() throws UiObjectNotFoundException{
        commonModule.clickResourceId("android:id/up");

        commonModule.scrollFindText("Facebook");

        if(commonModule.waitForText("OK", 5000)){
            commonModule.clickText("OK");
        }else if(commonModule.waitForText("Done", 5000)){
            commonModule.clickText("Done");
        }

        //wait for refresh icon
        commonModule.waitForResourceId("com.sonyericsson.album:id/social_refresh_action_image", 10*1000);
        commonModule.clickResourceId("com.sonyericsson.album:id/social_refresh_action_image");

        commonModule.wait(10);

        //click the first album
        testCase.clickPoint(commonModule.getX(367, 720), commonModule.getY(208, 1184));

        //'New from friends' is the first album name of facebook
        if(!commonModule.waitForText("New from friends", 5000)){
            commonModule.wait(5);

            testCase.clickPoint(commonModule.getX(367, 720), commonModule.getY(208, 1184));
        }
    }

    public void playSenseMeSlideshow(String theme, String music) throws UiObjectNotFoundException{
        commonModule.pressMoreOption();

        commonModule.clickText("SensMe™ slideshow");

        //select theme
        commonModule.clickResourceId("com.sonymobile.musicslideshow:id/slideshow_controller_theme_spinner");
        AcceptanceTestCase.assertTrue("Theme selection list should opened with the them to be selected", commonModule.waitForText(theme, 5000));

        commonModule.clickText(theme);

        //select music
        commonModule.clickResourceId("com.sonymobile.musicslideshow:id/slideshow_controller_music_spinner");
        AcceptanceTestCase.assertTrue("Music selection list should opened with the music to be selected", commonModule.waitForText(music, 5000));

        commonModule.clickText(music);

        AcceptanceTestCase.assertTrue("Play buton should display",
                commonModule.waitForResourceId("com.sonymobile.musicslideshow:id/slideshow_controller_playpause_button_play", 10000));

        commonModule.clickResourceId("com.sonymobile.musicslideshow:id/slideshow_controller_playpause_button_play");

        //wait a while for the playing
        commonModule.wait(5);
        
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(2);
    }

    public void shareOnePictureToEmail(String account, String subject) throws UiObjectNotFoundException{
        openOnePicture();

        testCase.clickPoint(testCase.getScreenSize()[0]/3, testCase.getScreenSize()[1]/3);

        //click share icon
        commonModule.clickResourceId("android:id/expand_activities_button");
        commonModule.wait(2);

        if(commonModule.waitForText("Email",3000)){
            commonModule.clickText("Email");
        }else if (commonModule.waitForText("See all",3000)){
            commonModule.clickText("See all");
            commonModule.wait(2);

            if(!commonModule.scrollFindText("Email")){
                commonModule.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(2);

                commonModule.clickResourceId("android:id/default_activity_button");
            }
        }

        AcceptanceTestCase.assertTrue("email view should be opened", commonModule.waitForPackage("com.android.email", 5000));

        testCase.enterText(account);
        commonModule.wait(2);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.wait(2);

        commonModule.clickResourceId("com.android.email:id/subject");
        commonModule.wait(2);
        testCase.enterText(subject);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.wait(2);

        commonModule.clickText("Send");

    }

    public void shareOnePictureToMMS(String recipient, String content) throws UiObjectNotFoundException{
        openOnePicture();

        testCase.clickPoint(testCase.getScreenSize()[0]/3, testCase.getScreenSize()[1]/3);

        //click share icon
        commonModule.clickResourceId("android:id/expand_activities_button");
        commonModule.wait(2);

        if(commonModule.waitForText("Messaging",3000)){
            commonModule.clickText("Messaging");
        }else if (commonModule.waitForText("See all",3000)){
            commonModule.clickText("See all");
            commonModule.wait(2);

            if(!commonModule.scrollFindText("Messaging")){
                commonModule.pressKey(KeyEvent.KEYCODE_BACK);
                commonModule.wait(2);

                commonModule.clickResourceId("android:id/default_activity_button");
            }
        }

        AcceptanceTestCase.assertTrue("Message view should be opened", commonModule.waitForPackage("com.sonyericsson.conversations", 5000));

        //click recipient id
        commonModule.clickResourceId("com.sonyericsson.conversations:id/recipients_editor");
        testCase.enterText(recipient);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.wait(2);

        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
        testCase.enterText(content);
        commonModule.pressKey(KeyEvent.KEYCODE_ENTER);
        commonModule.wait(2);

        commonModule.clickText("Send");

    }

    public void swipeToPictureView() throws UiObjectNotFoundException {
        commonModule.wait(2);
        if (!commonModule.isDescriptionExists("Delete")) {
            testCase.clickPoint(commonModule.getX(500, 720), commonModule.getY(500, 1184));
        }
        while (!commonModule.isDescriptionExists("Edit photo")) {
            testCase.scrollRight();
            commonModule.wait(2);
        }
        AcceptanceTestCase.assertTrue("Album picture view display failed",
                commonModule.isResourceId("com.sonyericsson.album:id/currentscenicview"));
    }

	public void checkPictureDetails() throws UiObjectNotFoundException {
	    commonModule.pressMoreOption();
	    commonModule.clickText("Details");
        AcceptanceTestCase.assertTrue("Check picture details failed.",
                commonModule.waitForTextContains("Date:", 2000));
	    commonModule.clickText("OK");
	}

    public void editNameTags(String name) throws UiObjectNotFoundException {
        commonModule.pressMoreOption();
        commonModule.clickText("Edit name tags");
        if (commonModule.waitForText("Edit tags", 2000)) {
            commonModule.clickText("OK");
        }
        if (!commonModule.waitForText("Add a name", 2000)) {
            int height = testCase.getUiDevice().getDisplayHeight();
            int width = testCase.getUiDevice().getDisplayWidth();
            testCase.clickPoint(width / 3, height / 3);
        }
        commonModule.clickText("Add a name");
        if(commonModule.waitForText(name, 2000)) {
            commonModule.clickText(name);
        } else {
            commonModule.clickResourceId("com.sonyericsson.album:id/edit_name_actionbar");
            testCase.enterText(name);
            testCase.enterText("\n");
            commonModule.clickText("Add as new person");
        }
        AcceptanceTestCase.assertTrue("Edit name tags failed.",
                commonModule.waitForText(name, 2000)
                        && commonModule.waitForDescription("Discard", 2000));
    }

    public void checkAllMoreMenuInAlbumMenu() throws UiObjectNotFoundException {
        selectSlideshow();
        setPictureAsWallpaper();
        rotatePicture();
        checkPictureDetails();
        tagPictureOnMap();
        editNameTags("Lucy");
        copyToSdcard();// Select print.
    }

	@Override
	public void startSketch() throws UiObjectNotFoundException {
        commonModule.launchAppBySearch("sketch");
	}

	@Override
	public void drawingAPictureBySketch() throws UiObjectNotFoundException {
		
		commonModule.clickResourceId(moduleData.get("Drawing_Icon"));
		commonModule.clickResourceId(moduleData.get("New_sketch_potrait_mode"));
		
		testCase.dragViewBetweenTwoPosition(commonModule.getX(169, 720), commonModule.getY(206, 1184),
				commonModule.getX(157, 720), commonModule.getY(1009, 1184));
		
		testCase.dragViewBetweenTwoPosition(commonModule.getX(250, 720), commonModule.getY(206, 1184),
				commonModule.getX(250, 720), commonModule.getY(1009, 1184));
		
		testCase.dragViewBetweenTwoPosition(commonModule.getX(400, 720), commonModule.getY(206, 1184),
				commonModule.getX(400, 720), commonModule.getY(1009, 1184));
		
		
	}

	@Override
	public void exitSketchDrawingStatus() throws UiObjectNotFoundException {
        startSketch();
        if (!commonModule.isResourceId(moduleData.get("Drawing_Icon"))){
        	commonModule.wait(2);
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
    		testCase.clickId("button2");
        }

	}
	
	public void openThrowInAlbum() throws UiObjectNotFoundException {
		commonModule.pressMoreOption();
		commonModule.clickText("Throw");
		AcceptanceTestCase.assertTrue("Throw cannot be opened", commonModule.waitForResourceId("com.sonymobile.playanywhere:id/devicelist_dialog_fragment",5000));
		commonModule.waitForTextGone("Searching...", 60*1000);
		if(commonModule.isTextExists("No device found")){
			commonModule.pressKey(KeyEvent.KEYCODE_BACK);
			//AcceptanceTestCase.assertTrue("Does not have devices exist to connect", false);
		}else{
			commonModule.pressKey(KeyEvent.KEYCODE_BACK);
			//TODO: connect the correct device
		}
	}

}
