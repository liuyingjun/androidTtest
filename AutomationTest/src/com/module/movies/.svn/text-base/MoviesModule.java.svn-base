package com.module.movies;

import com.module.common.CommonModule;
import com.module.settings.SettingsFactory;
import com.parser.data.ModuleDataParser;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.HashMap;

public class MoviesModule implements IMovies {

    private AcceptanceTestCase testCase;
    private CommonModule commonModule;
    private HashMap<String, String> moduleData;

    public MoviesModule(){

    }

    public MoviesModule(AcceptanceTestCase testCase) throws PropertyNotFoundException {
        this.testCase = testCase;
        commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("movies");
    }
    @Override
    public void launchMovies() {
        testCase.launchApp("com.sonyericsson.video", "com.sonyericsson.video.browser.BrowserActivity");
        commonModule.wait(3);
    }

    @Override
    public void playOneMovie(String videoName) {
        this.launchMovies();

        commonModule.scrollFindText(videoName);

    }

}
