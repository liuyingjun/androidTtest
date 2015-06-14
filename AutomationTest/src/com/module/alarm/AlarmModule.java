package com.module.alarm;

import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.module.settings.AbstractSettingsFactory;
import com.module.settings.ISetting;
import com.parents.GroupFactories;
import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.R.integer;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera;
import android.text.NoCopySpan.Concrete;
import android.util.Log;
import android.view.KeyEvent;

public class AlarmModule implements IAlarm{
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    String TAG = "Reliability";

    private ISetting settingsModule;

    public String[] capitalList = {"Algiers","Buenos Aires","Canberra","Baku","Dhaka","Bridgetown","Brussels","Belmopan","Gaborone","Brasilia",
    		"Bujumbura","Brazzaville","Havana","Copenhagen","Djibouti","Cairo","Asmara","Addis Ababa","Helsinki","Banjul","Berlin","Accra",
    		"Athens","Guatemala City","Conakry","Bissau","Georgetown","Budapest","Jakarta","Dublin","Jerusalem","Amman","Astana",
    		"Beirut","Bamako","Chisinav","Amsterdam","Abuja","Islamabad","Jerusalem","Doha","Bucharest","Dakar","Freetown","Bratislava","Honiara","Colombo","Castries",
    		"Bern","Damascus","Dushanbe","Dar es Salaam","Bangkok","Ankara","Ashkhabad","Funafuti","Caracas","Hanoi","Belgrade","Harare"};

    CameraInfo info = new Camera.CameraInfo();

    public AlarmModule(AcceptanceTestCase testCase) throws PropertyNotFoundException{
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
        settingsModule = ((AbstractSettingsFactory)(GroupFactories.createFactory(testCase,
                "settings"))).getSettingsModule();
    }
    public void startAlarmFromMenu() {
    	commonModule.backOutToHomeScreen();
    	testCase.launchApp("com.sonyericsson.organizer", "com.sonyericsson.organizer.Organizer");
    	commonModule.wait(2);
    }


    public void deleteAllAlarms() {
    	startAlarmFromMenu();
    	commonModule.wait(2);
    	testCase.pressKey(KeyEvent.KEYCODE_MENU);
    	commonModule.wait(1);
        if (testCase.isViewWithTextPresent("Delete alarms")) {
        	testCase.clickItemWithText("Delete alarms");
        	commonModule.wait(1);
        	testCase.setTimeout(1000);
            String deleteBoxId = null;
            boolean alarmExist = false;
            if (testCase.isViewWithIdPresent("indicator_delete")) {
                deleteBoxId = "indicator_delete";
                alarmExist = true;
            } else if (testCase.isViewWithIdPresent("clock_onoff")) {
                deleteBoxId = "clock_onoff";
                alarmExist = true;
            } else {
                alarmExist = false;
            }
            if (alarmExist) {
                for (int i = 0; i < 5; i++) {
                    if (testCase.isViewWithIdPresent(deleteBoxId, i)) {
                    	testCase.clickItemWithId(deleteBoxId, i);
                    	commonModule.wait(1);
                    }
                }
                commonModule.wait(1);
                testCase.clickItemWithId("delete_button");
                commonModule.wait(1);
                testCase.clickItemWithText("Yes");
                commonModule.wait(1);
            } else {
            	testCase.resetTimeout();
                Log.v(TAG, "There is no alarm to delete");
            }
            testCase.resetTimeout();
        } else {
            Log.v(TAG, "Could not find delete button");
            Assert.fail("Could not find delete button");
        }
    }
	@Override
	public boolean snoozeAlarm(int timeout) {
        long startTime = System.currentTimeMillis();

        while(System.currentTimeMillis() - startTime < timeout){
            if(testCase.isViewWithIdPresent("dismissSlider")){
            	testCase.clickId("snooze");
                return true;
            }else

                testCase.sleep(1000);
        }

        return false;

	}

	public void switchAlarmTabs() throws UiObjectNotFoundException{
		commonModule.clickDescription("Alarm");
		commonModule.waitForId("com.sonyericsson.organizer:id/alarms_list", 2000);

		commonModule.clickDescription("World clock");
		commonModule.waitForResourceId("com.sonyericsson.organizer:id/worldclock_list", 2000);

		commonModule.clickDescription("Stopwatch");
		commonModule.waitForResourceId("com.sonyericsson.organizer:id/stopwatch_header", 2000);

		commonModule.clickDescription("Timer");
		commonModule.waitForResourceId("com.sonyericsson.organizer:id/timer_countdown", 2000);
	}

	public void switchAlarmTabByDescription(String desc) throws UiObjectNotFoundException{
		commonModule.clickDescription(desc);
		commonModule.wait(2);
	}

	public void setHomeTown(String homeCity) throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/worldclock_list")){
			switchAlarmTabByDescription("World clock");
		}
		commonModule.clickDescription("More options");
		commonModule.waitForText("Set home city", 2000);

		commonModule.clickText("Set home city");
		commonModule.waitForResourceId("com.sonyericsson.organizer:id/city_search", 3000);

		commonModule.clickResourceId("com.sonyericsson.organizer:id/city_search");
		testCase.enterText(homeCity);
		commonModule.clickResourceId("com.sonyericsson.organizer:id/search_city_name");
		Assert.assertTrue("Set home town failed.",
				commonModule.waitForResourceId("com.sonyericsson.organizer:id/clock_home", 3000));
	}

	public void changeTemperatureUnits() throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/worldclock_list")){
			switchAlarmTabByDescription("World clock");
		}
		commonModule.clickDescription("More options");
		if (commonModule.waitForText("Fahrenheit", 2000)) {
			commonModule.clickText("Fahrenheit");
			commonModule.waitForTextGone("Fahrenheit", 2000);
		} else if (commonModule.waitForText("Celsius", 2000)) {
			commonModule.clickText("Celsius");
			commonModule.waitForTextGone("Celsius", 2000);
		}
	}

	public void rearrangeWithWordClock() throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/worldclock_list")){
			switchAlarmTabByDescription("World clock");
		}
		commonModule.clickDescription("More options");
		commonModule.waitForText("Rearrange", 2000);

		commonModule.clickText("Rearrange");
		commonModule.waitForResourceId(
				"com.sonyericsson.organizer:id/rearrange_handle", 2000);

		testCase.dragViewBetweenTwoPosition(commonModule.getX(670, 720),
				commonModule.getY(210, 1280), commonModule.getX(670, 720),
				commonModule.getY(350, 1280));

		commonModule.clickText("Done");
	}

	public void deleteCity(String city) throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/worldclock_list")){
			switchAlarmTabByDescription("World clock");
		}
		commonModule.clickDescription("More options");
		commonModule.waitForText("Delete", 2000);

		commonModule.clickText("Delete");
		commonModule.waitForText(city, 3000);

		commonModule.clickText(city);
		commonModule.wait(1);

		commonModule.clickResourceId("com.sonyericsson.organizer:id/delete_button");
		commonModule.waitForText("Delete cities?", 3000);

		commonModule.clickText("Yes");
		Assert.assertTrue("Delete city failed.",
				commonModule.waitForTextGone(city, 3000));
	}

	public void stopwatchOperation() throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/stopwatch_header")){
			switchAlarmTabByDescription("Stopwatch");
		}
		commonModule.waitForText("Start", 2000);

		// Start stopwatch.
		commonModule.clickText("Start");
		Assert.assertTrue("Start stopwatch failed.",
				commonModule.waitForText("Stop", 2000));

		// Stop stopwatch.
		commonModule.clickText("Stop");
		Assert.assertTrue("Stop stopwatch failed.",
				commonModule.waitForText("Start", 2000));

		// Resume stopwatch.
		commonModule.clickText("Start");
		Assert.assertTrue("Resume stopwatch failed.",
				commonModule.waitForText("Stop", 2000));

		commonModule.clickText("Stop");
		commonModule.waitForText("Start", 2000);

		// Reset stopwatch.
		commonModule.clickText("Reset");
		UiObject reset = new UiObject(new UiSelector().text("Reset"));
		Assert.assertTrue("Reset stopwatch failed.", !reset.isCheckable());
	}

	public void TimerOperation() throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/timer_countdown")){
			switchAlarmTabByDescription("Timer");
		}
		commonModule.waitForText("Set time", 2000);

		commonModule.clickText("Set time");
		commonModule.wait(2);

		testCase.clickItemWithText("00", 2);
		testCase.enterText("10");
		testCase.enterText("\n");
		commonModule.clickText("OK");
		commonModule.waitForText("Start", 2000);

		commonModule.clickText("Start");
		commonModule.waitForResourceId("com.sonyericsson.organizer:id/timer_history_text", 3000);

		commonModule.clickText("Stop");
		commonModule.waitForText("Reset", 5000);

		commonModule.clickText("Reset");
		commonModule.clickText("Clear");
	}

	public void compareWorldClock() throws UiObjectNotFoundException{
		if(!commonModule.isResourceId("com.sonyericsson.organizer:id/worldclock_list")){
			switchAlarmTabByDescription("World clock");
		}
		Assert.assertTrue(
				"World clock Time UI display with weather.",
				commonModule.waitForResourceId("com.sonyericsson.organizer:id/clock_time", 3000)
						&& commonModule.waitForResourceId(
								"com.sonyericsson.organizer:id/weather_icon", 10 * 1000));
	}

	public void addNewAlarm(String hour, String minute) throws UiObjectNotFoundException{
					startAlarmFromMenu();
		    	switchAlarmTabByDescription("Alarm");
		    	testCase.clickItemWithId("menu_launcher_addalarm");
		    	commonModule.wait(2);
		    	testCase.clickId("alarm_set_alarm_time_layout");// Set alarm time.
		    	commonModule.wait(2);

					testCase.clickItemWithId("numberpicker_input",0);
					testCase.enterText(hour);

					testCase.clickItemWithId("numberpicker_input",1);
					testCase.enterText(minute);

					testCase.pressKey(KeyEvent.KEYCODE_BACK);
					commonModule.wait(1);
					if (testCase.isViewWithTextPresent("Done")) {
						Log.e(TAG, "Set Alarm Time");
						testCase.click("Done");
					} else {
						testCase.click("Set");
					}

					testCase.click("Done");

					commonModule.wait(2);
					commonModule.backOutToHomeScreen();
	}

	public void deleteAlarm() throws UiObjectNotFoundException{
		commonModule.wait(2);
		AcceptanceTestCase.assertTrue("Delete option not shown", commonModule.isResourceId("com.sonyericsson.organizer:id/digitalClock"));
		testCase.longPressItemWithId("digitalClock");
		commonModule.wait(2);
		AcceptanceTestCase.assertTrue("Delete option not shown", commonModule.isTextExists("Delete alarm"));
		commonModule.clickText("Delete alarm");
		commonModule.clickText("Yes");
		commonModule.wait(2);
}

	public void deleteAlarmOneByOne() throws UiObjectNotFoundException{
		while(commonModule.isResourceId("com.sonyericsson.organizer:id/alarms_list")){
			deleteAlarm();
		}
	}

	public void addNewAlarmAfterMaximum() throws UiObjectNotFoundException{
			startAlarmFromMenu();
			switchAlarmTabByDescription("Alarm");
			testCase.clickItemWithId("menu_launcher_addalarm");
			commonModule.wait(2);
			if (commonModule
					.isResourceId("com.sonyericsson.organizer:id/alarm_set_alarm_scrollview")) {
				testCase.clickId("alarm_set_alarm_time_layout");// Set alarm time.
				commonModule.wait(2);

				testCase.clickItemWithId("numberpicker_input", 0);
				testCase.enterText("12");

				testCase.clickItemWithId("numberpicker_input", 1);
				testCase.enterText("12");

				testCase.pressKey(KeyEvent.KEYCODE_BACK);
				commonModule.wait(1);
				if (testCase.isViewWithTextPresent("Done")) {
					Log.e(TAG, "Set Alarm Time");
					testCase.click("Done");
				} else {
					testCase.click("Set");
				}

				testCase.click("Done");
			} else {
				AcceptanceTestCase
						.assertTrue(
								"Cannot add more than 20 alarms",
								commonModule
										.isResourceId("com.sonyericsson.organizer:id/menu_launcher_addalarm"));
			}
			commonModule.wait(2);
			commonModule.backOutToHomeScreen();
		}

		public void addClock(String itemname) throws UiObjectNotFoundException {
			startAlarmFromMenu();
			if (!commonModule
					.isResourceId("com.sonyericsson.organizer:id/worldclock_list")) {
				switchAlarmTabByDescription("World clock");
			}
			testCase.clickItemWithId("menu_item_world_clock_add");
			commonModule.wait(2);
			commonModule.clickListItemwithText(itemname);
			AcceptanceTestCase.assertFalse("Add world clock failed",
					commonModule.isTextExists("Add city"));
			commonModule.waitForResourceId(
					"com.sonyericsson.organizer:id/world_clock_list", 3000);
			boolean isTextExist = commonModule.scrollCheckText(itemname);
			AcceptanceTestCase
					.assertTrue(
							"Clock added failed",
							isTextExist
									&& commonModule
											.isResourceId("com.sonyericsson.organizer:id/weather_icon"));
			commonModule.backOutToHomeScreen();
		}

		public void addClockMultipleTime(int times, boolean flightMode)
				throws UiObjectNotFoundException {
			for (int i = 0; i < times; i++) {
				if (flightMode) {
					if (i == 0) {
						settingsModule.turnOnFlightMode();
					}
					if (i == 2) {
						settingsModule.turnOffFlightMode();
					}
				}
				addClock(capitalList[i]);
			}
		}

		public void deleteClock() throws UiObjectNotFoundException {
			if (commonModule
					.isResourceId("com.sonyericsson.organizer:id/world_clock_list")) {
				testCase.longPressItemWithId("world_clock_list");
			}
			AcceptanceTestCase.assertTrue(
					"Delete confirmation note doesn't display",
					commonModule.isTextExists("Delete"));
			commonModule.clickText("Yes");
		}

		public void startClock() throws UiObjectNotFoundException {
				startAlarmFromMenu();
	    	switchAlarmTabByDescription("World clock");
		}

		public void deleteClockOneByOne() throws UiObjectNotFoundException{
			while(commonModule.isResourceId("com.sonyericsson.organizer:id/world_clock_list")){
				commonModule.wait(1);
				deleteClock();
			}
		}

		public void setStopWatch(int lapTime) throws UiObjectNotFoundException{
					if(!commonModule.isResourceId("com.sonyericsson.organizer:id/stopwatch_header")){
						switchAlarmTabByDescription("Stopwatch");
					}
					commonModule.waitForText("Start", 2000);

					// Start stopwatch.
					commonModule.clickText("Start");
					AcceptanceTestCase.assertTrue("Start stopwatch failed.",
							commonModule.waitForText("Stop", 2000));

					//Lap
					for(int i=0;i<lapTime;i++){
						commonModule.wait(2);
						commonModule.clickText("Lap");
						AcceptanceTestCase.assertTrue("Lap time failed", commonModule.isTextExists((String.valueOf(i+2)+"."))&&commonModule.isResourceId("com.sonyericsson.organizer:id/lap_time"));
					}

					// Stop stopwatch.
					commonModule.clickText("Stop");
					AcceptanceTestCase.assertTrue("Stop stopwatch failed.",
							commonModule.waitForText("Start", 2000));

					// Stop stopwatch.
					commonModule.clickText("Reset");
					AcceptanceTestCase.assertTrue("Reset stopwatch failed.",
							commonModule.waitForText("Start", 2000));
				}

		public void setTimer(String hour,String minute, String second) throws UiObjectNotFoundException{
			if(!commonModule.isResourceId("com.sonyericsson.organizer:id/timer_countdown")){
				switchAlarmTabByDescription("Timer");
			}
			commonModule.waitForText("Set time", 2000);

			// Start stopwatch.
			if(commonModule.isTextExists("Reset")){
				commonModule.clickText("Reset");
			}
			commonModule.clickText("Set time");
			AcceptanceTestCase.assertTrue("Open set time failed.",
					commonModule.waitForResourceId("android:id/numberpicker_input", 2000));

			//Set timer
				testCase.clickItemWithId("numberpicker_input");
				testCase.enterText(hour);
				testCase.pressKey(KeyEvent.KEYCODE_ENTER);

				testCase.clickItemWithId("numberpicker_input",1);
				testCase.enterText(minute);
				testCase.pressKey(KeyEvent.KEYCODE_ENTER);

				testCase.clickItemWithId("numberpicker_input",2);
				testCase.enterText(second);
				testCase.pressKey(KeyEvent.KEYCODE_ENTER);

				commonModule.clickText("OK");
				AcceptanceTestCase.assertTrue("Stop stopwatch failed.",
						commonModule.waitForText("Start", 2000));

			// Start timer
			commonModule.clickText("Start");
			AcceptanceTestCase.assertTrue("Start timer failed.",
					commonModule.waitForText("Stop", 2000));

			// Stop timer
			commonModule.wait(3);
			commonModule.clickText("Stop");
			AcceptanceTestCase.assertTrue("Stop timer failed.",
					commonModule.waitForText("Start", 2000));

			//Clear timer
			commonModule.clickText("Clear");
			AcceptanceTestCase.assertFalse("Clear timer failed.",
					commonModule.isResourceId("com.sonyericsson.organizer:id/timer_history_text"));

			//Reset timer
			commonModule.clickText("Reset");
			AcceptanceTestCase.assertTrue("Reset timer failed.",
					commonModule.waitForText("Set time", 2000));

		}
		@Override
		public void stopStopwatchOrTimer(String type) throws UiObjectNotFoundException {
	    	commonModule.openNotificationBar();
	    	if(commonModule.waitForText(type, 5000)){
	    		commonModule.clickText(type);

				commonModule.clickText("Stop");

				commonModule.clickText("Reset");
	    	}

		}

    public void setAlarm(Context context) {
        AlarmManager alarms = (AlarmManager)testCase.getInstrumentation().getTargetContext()
                .getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmManager.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3 * 1000, pi);
    }

		@Override
		public void setNewAlarmFewMinuteslater(int hour, int minute)
				throws UiObjectNotFoundException {

			//setNewAlarmOneMinuteFromNow
	    	startAlarmFromMenu();
	    	switchAlarmTabByDescription("Alarm");
	    	commonModule.clickResourceId("com.sonyericsson.organizer:id/menu_launcher_addalarm");
	    	commonModule.wait(2);
	    	commonModule.clickResourceId("com.sonyericsson.organizer:id/alarm_set_alarm_time_layout");// Set alarm time.
	    	commonModule.wait(2);

			int nowHour = Integer.valueOf(testCase
					.getTextFromViewWithId("numberpicker_input"));

			int nowMinute = Integer.valueOf(testCase.getTextFromViewWithId(
					"numberpicker_input", 1));


		    // set minute
			if (((nowMinute+minute)/60)<1){
				testCase.clickItemWithId("numberpicker_input",1);
				testCase.enterText(String.valueOf(nowMinute+minute));

			}
			else{
				int i=(nowMinute+minute)%60;
				testCase.clickItemWithId("numberpicker_input",1);
				testCase.enterText(String.valueOf(i));

				if (nowHour==23){
					testCase.clickItemWithId("numberpicker_input");
					testCase.enterText("00");
					nowHour=0;
				}
				else{
					testCase.clickItemWithId("numberpicker_input");
					testCase.enterText(String.valueOf(nowHour+1));

					nowHour=nowHour+1;
				}
			}


			//set hour

			if (hour!=0){
				commonModule.wait(3);
				testCase.pressKey(KeyEvent.KEYCODE_BACK);

				commonModule.wait(2);

				if (((nowHour+hour)/24)<1){
					testCase.clickItemWithId("numberpicker_input");
					testCase.enterText(String.valueOf(nowHour+hour));

				}
				else{
					int i=(nowMinute+minute)%60;
					testCase.clickItemWithId("numberpicker_input");
					testCase.enterText(String.valueOf(i));
				}
			}

			testCase.click("Set");

			testCase.click("Done");
		}

	public void setNewAlarmFewMinuteslaterByTap(int seconds) throws UiObjectNotFoundException{
	    startAlarmFromMenu();
        switchAlarmTabByDescription("Alarm");
        commonModule.clickResourceId("com.sonyericsson.organizer:id/menu_launcher_addalarm");
        commonModule.wait(2);

        commonModule.clickResourceId("com.sonyericsson.organizer:id/alarm_set_alarm_time_layout");// Set alarm time.
        commonModule.wait(2);

        //set minutes
        for (int i=0; i<seconds; i++){
            commonModule.clickIdWithInstance("android.widget.Button", 3);
            testCase.sleep(500);
        }

        testCase.click("Set");

        testCase.click("Done");
	}
}

