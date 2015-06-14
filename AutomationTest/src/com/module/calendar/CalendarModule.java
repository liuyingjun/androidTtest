package com.module.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;

public class CalendarModule implements ICalendar{
    AcceptanceTestCase testCase;

    CommonModule commonModule;

    String TAG = "Reliability";
    CameraInfo info = new Camera.CameraInfo();

    public CalendarModule(AcceptanceTestCase testCase){
        this.testCase = testCase;
        this.commonModule = new CommonModule(testCase);
    }

    public void launchCalendar() {
    	commonModule.backOutToHomeScreen();
        testCase.launchApp("com.android.calendar", "com.android.calendar.LaunchActivity");
        testCase.waitForActivitySwitch();
        commonModule.wait(4);

    }

    protected void setTitleValue(String value) {
    	testCase.clickItemWithId("title");
    	testCase.enterText(value);
    	commonModule.wait(2);
    }

    protected void openNewEventView() {
        if (testCase.isViewWithIdPresent("action_create_event")) {
        	testCase.clickItemWithId("action_create_event");
        } else {
        	testCase.clickId("addEventButton");
        }
    }

    public void addCalendarNewEvent(String subject, String account) throws UiObjectNotFoundException{
        openNewEventView();
        
        if(account!=null){
        	commonModule.clickResourceId("com.android.calendar:id/calendar_group");
        	commonModule.wait(2);
        if(account=="Google"){
        	commonModule.clickText("Google");
        }else if(account=="EAS"){
        	commonModule.clickText("Exchange ActiveSync");
        }else if(account == "Local"){
        	commonModule.clickText("Default account");
        }else if(account=="Facebook"){
        	commonModule.clickText("Xperia™ with Facebook");
        }else{
        	AcceptanceTestCase.assertTrue("The given account is wrong", false);
        }
        }
        
		commonModule.clickResourceId("com.android.calendar:id/title");
		testCase.enterText(subject);// Input subject.
        if (testCase.isInputMethodWindowOpened()) {
        	testCase.pressKey(KeyEvent.KEYCODE_BACK);
        	commonModule.wait(2);
        }
        long startTimeInMilli = commonModule.getCurrTime() + 1000 * 60 * 12;
        String startTime[] = refFormatTime(startTimeInMilli);//start time
        
        testCase.clickItemWithId("start_time");
        commonModule.wait(2);
        testCase.clickItemWithId("numberpicker_input");
        testCase.enterText(startTime[0]);
        testCase.clickItemWithId("numberpicker_input",1);
        testCase.enterText(startTime[1]);
        if(commonModule.isTextExists("Set")){
        			testCase.click("Set");
        }

        testCase.scrollDown();
					 commonModule.clickResourceId("com.android.calendar:id/description");
				   testCase.enterText("This event is for testing");// Input subject.
						if (testCase.isInputMethodWindowOpened()) {
									testCase.pressKey(KeyEvent.KEYCODE_BACK);
									commonModule.wait(2);
						}

        testCase.click("Done");
        commonModule.wait(2);

        if (testCase.isViewWithTextPresent(subject)
                || testCase.isViewWithIdPresent("action_today")) {
        	testCase.resetTimeout();
            Log.v(TAG, "Create event successfully!");
        } else {
        	testCase.resetTimeout();
        	}
    }

    public void deleteAllTheEvent() {
        launchCalendar();

    	testCase.pressKey(KeyEvent.KEYCODE_MENU);
    	commonModule.wait(2);
    	testCase.clickItemWithText("Settings");
//    	testCase.click("General settings");
    	commonModule.scrollFindText("Delete all events");
    	testCase.setTimeout(2000);
        if (testCase.isViewWithTextPresent("OK")) {
        	testCase.clickItemWithText("OK");
        } else if (testCase.isViewWithIdPresent("button1")) {
        	testCase.clickItemWithId("button1");
        } else {
            Log.v(TAG, "there is no event any more");
        }
        testCase.resetTimeout();
//        testCase.assertTextPresent("All events delete ");
    }

	@Override
	public void addNewCalendarEventAPI(long startTimeInMilli, String strTaskName,
			String strDescription, String value, boolean needReminder) {

		ContentResolver cr = testCase.getInstrumentation().getContext()
				.getContentResolver();
		long endTimeInMilli = startTimeInMilli + 1000 * 60 * 60; // For next 1hr
		ContentValues values = new ContentValues();
		values.put("calendar_id", 1);
		values.put("dtstart", startTimeInMilli);
		values.put("dtend", endTimeInMilli);
		values.put("title", strTaskName);
		values.put("description", strDescription);
		values.put("eventTimezone", value);
		Uri EVENTS_URI = Uri.parse("content://com.android.calendar/events");
		Uri uri = cr.insert(EVENTS_URI, values);
		long eventID = Long.parseLong(uri.getLastPathSegment());
		if (needReminder) {
			Log.d(TAG, "Add reminder for event");
			String reminderUriString = "content://com.android.calendar/reminders";

			ContentValues reminderValues = new ContentValues();

			reminderValues.put("event_id", eventID);
			reminderValues.put("minutes", 10);
			reminderValues.put("method", 1); // Alert Methods:
												// Default(0),Alert(1),
												// Email(2),SMS(3)

			Uri reminderUri = cr.insert(Uri.parse(reminderUriString),
					reminderValues);
		}

	}
	@Override
	public boolean waitForCalendarAlarm(String title, int timeout) {
        long startTime = System.currentTimeMillis();

        while(System.currentTimeMillis() - startTime < timeout){
        	testCase.openStatusBar();
            if(testCase.isViewWithTextPresent(title)){
            	testCase.click("Clear");
            	 return true;

            }else
            	commonModule.wait(5);
        }
		return false;
	}

	public void prepareNewEvent(String subject) throws UiObjectNotFoundException{
	    openNewEventView();

	    commonModule.clickResourceId("com.android.calendar:id/title");
	    testCase.enterText(subject);// Input subject.

	    commonModule.clickResourceId("com.android.calendar:id/location");
	    testCase.enterText("Beijing");// Input location.
	    testCase.pressKey(KeyEvent.KEYCODE_BACK);// Disappear keyboard.
	    commonModule.wait(1);

	    commonModule.clickResourceId("com.android.calendar:id/description");
	    testCase.enterText("Test For RT! Test For RT!");
	    testCase.pressKey(KeyEvent.KEYCODE_BACK);// Disappear keyboard.
	    commonModule.wait(1);
	}

	public void createAllDayEventButNotSave(String subject) throws UiObjectNotFoundException{
	    launchCalendar();
	    prepareNewEvent(subject);

	    commonModule.clickResourceId("com.android.calendar:id/is_all_day");
	    commonModule.waitForIdGone("start_time", 2000);

	    commonModule.clickDescription("Cancel");
	}

	public void createAllDayEventAndSave(String subject) throws UiObjectNotFoundException{
	    launchCalendar();
        prepareNewEvent(subject);

        commonModule.clickResourceId("com.android.calendar:id/is_all_day");
        commonModule.waitForIdGone("start_time", 2000);

        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue("Create all day event failed.",
                commonModule.waitForResourceId("com.android.calendar:id/action_create_event", 5000)
                        && commonModule.waitForText("All day event", 5000));
	}

    public void addAttendeesForEvent(String subject) throws UiObjectNotFoundException {
	    commonModule.clickText(subject);
	    commonModule.waitForResourceId("com.android.calendar:id/edit", 2000);

	    commonModule.clickResourceId("com.android.calendar:id/edit");
	    commonModule.waitForText("Attendees", 2000);

	    commonModule.clickResourceId("com.android.calendar:id/to_plus_required");
	    commonModule.waitForText("Contacts", 2000);

        commonModule.clickIdWithInstance(
                "com.sonyericsson.android.socialphonebook:id/checklist_check", 0);// Select A0.
        commonModule.clickIdWithInstance(
                "com.sonyericsson.android.socialphonebook:id/checklist_check", 1);// Select A1.

        commonModule.clickText("Done");
        commonModule.waitForText("Attendees", 2000);

        commonModule.clickResourceId("com.android.calendar:id/to_plus_optional");
        commonModule.waitForText("Contacts", 2000);

        commonModule.clickIdWithInstance(
                "com.sonyericsson.android.socialphonebook:id/checklist_check", 2);// Select A2.
        commonModule.clickIdWithInstance(
                "com.sonyericsson.android.socialphonebook:id/checklist_check", 3);// Select A3.

        commonModule.clickText("Done");
        commonModule.waitForText("Attendees", 2000);

        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue("Add attendees for event failed.",
                commonModule.waitForResourceId("com.android.calendar:id/action_create_event", 5000)
                        && commonModule.waitForText("All day event", 5000));
	}

	public void switchCalendarView(String period) throws UiObjectNotFoundException{
	    commonModule.clickResourceId("android:id/up");
	    commonModule.waitForText(period, 2000);

	    commonModule.clickText(period);
	}

    public void switchCalendarViewToCheckAllDayEvent(String subject)
            throws UiObjectNotFoundException {
        launchCalendar();

        switchCalendarView("Day");
        AcceptanceTestCase.assertTrue("Event not shown on Day view.",
                commonModule.waitForText(subject, 2000));

        switchCalendarView("Week");
        AcceptanceTestCase.assertTrue("Event not shown on Week view.",
                commonModule.waitForDescriptionContains("All dayevent", 2000)||commonModule.waitForText(subject, 2000));

        switchCalendarView("Month");
        AcceptanceTestCase.assertTrue("Event not shown on Month view.",
                commonModule.waitForText(subject, 2000));
    }

	public void changeDateByView() throws UiObjectNotFoundException{
	    launchCalendar();

	    switchCalendarView("Day");
	    testCase.scrollRight();// Change to next day.
//	    testCase.scrollLeft();// Change to last day.

	    switchCalendarView("Week");
	    testCase.scrollRight();// Change to next week.
//        testCase.scrollLeft();// Change to last week.
	}

	public void checkCalendarAccounts(String... accountsType) throws UiObjectNotFoundException{
	    launchCalendar();

	    commonModule.clickResourceId("android:id/up");
	    commonModule.wait(1);
	    testCase.assertTextPresent("Calendars");

	    if (accountsType.length ==0){
	        AcceptanceTestCase.assertTrue("Google account shouldn't display on account list",
	                !commonModule.isTextExists("Google"));

	        AcceptanceTestCase.assertTrue("Facebook account shouldn't display on account list",
                    !commonModule.isTextExists("Xperia™ with Facebook"));

	        return;
	    }

	    for(int i=0; i<accountsType.length; i++){
	        if(accountsType[i] == "Facebook"){
	            testCase.assertTextPresent("Xperia™ with Facebook");
	        }else{
	            testCase.assertTextPresent(accountsType[i]);
	        }

	        commonModule.wait(2);
	    }
	}

	public void setBirthdayLink(String status) throws UiObjectNotFoundException{
	    launchCalendar();

	    commonModule.pressMoreOption();
	    commonModule.clickText("Settings");
	    commonModule.scrollFindTextNotClick("Birthdays");

	    UiObject obj = new UiObject(new UiSelector().resourceId("com.android.calendar:id/switcher").instance(1));
	    String currentStatus = obj.getText();

	    if(currentStatus.equalsIgnoreCase(status)){

	    }else{
	        obj.click();
	    }
	}

	public void checkBirthdaySetting(String status){
	    if(status.equalsIgnoreCase("on")){
	        AcceptanceTestCase.assertTrue("Birthday reminder should exist", commonModule.waitForResourceId("com.android.calendar:id/birthdayItemContainer", 5000));
	    }else{
	        AcceptanceTestCase.assertTrue("Birthday reminder shouldn't exist", !commonModule.waitForResourceId("com.android.calendar:id/birthdayItemContainer", 5000));
	    }
	}

	public void switchCalendarViewInLaundscapeMode(String subject)
            throws UiObjectNotFoundException {
        launchCalendar();

        switchCalendarView("Day");
        AcceptanceTestCase.assertTrue("Event not shown on Day view.",
                commonModule.waitForText(subject, 2000));

        switchCalendarView("Week");
        AcceptanceTestCase.assertTrue("Event not shown on Week view.",
                commonModule.waitForDescriptionContains("All dayevent", 2000)||commonModule.waitForText(subject, 2000));

        //in the landscape mode , mouth view can't capture the subject of the event
        switchCalendarView("Month");
        commonModule.wait(2);
    }

	public void deleteEvent(String subject)
            throws UiObjectNotFoundException {
					if(!commonModule.isTextExists("No event")){
											testCase.longPressItemWithId("itemContainer");
			        			commonModule.clickText("Delete event");
			        			commonModule.clickText("Delete");
					}

    }

	public void editEvent(String subject)
            throws UiObjectNotFoundException {
								commonModule.scrollFindTextNotClick(subject);
								testCase.longPressItemWithText(subject);
        			commonModule.clickText("Edit event");
        			testCase.scrollDown();
        			commonModule.clickResourceId("com.android.calendar:id/description");
        		 testCase.enterText("newDescription");// Input description.
        		 commonModule.clickText("Done");

    }

	public void viewEvent(String subject)
            throws UiObjectNotFoundException {
        			commonModule.scrollFindText(subject);
        			commonModule.wait(2);
        			AcceptanceTestCase.assertTrue("View event failed", commonModule.isResourceId("com.android.calendar:id/event_info_description"));
        			testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }
	
	public String[] refFormatTime(long currentTime) {
		  Date time = new Date(currentTime);
		  SimpleDateFormat sdFormatter = new SimpleDateFormat("HH:mm:ss");
		  String retStrFormatTime = sdFormatter.format(time);
		  String[] strs = retStrFormatTime.split(":"); 

		  return strs;
		}
	
	public void fowardCalendarEvent(String eventSubject, ArrayList<String> contactList) throws UiObjectNotFoundException {
		  
			commonModule.clickResourceId("com.android.calendar:id/dayText");
			commonModule.wait(2);
		  commonModule.scrollFindText(eventSubject);
		  commonModule.clickResourceId("com.android.calendar:id/forward");
		  if(commonModule.waitForResourceId("com.android.calendar:id/action_send", 5000)){
			  commonModule.clickResourceId("com.android.calendar:id/to_add_attendees");
			  
			  for(String tmp:contactList){
				  commonModule.scrollFindText(tmp);
				  commonModule.wait(1);
			  }
			  commonModule.wait(2);
			  commonModule.clickText("Done");
			  
			  commonModule.wait(2);
			  
			  commonModule.clickResourceId("com.android.calendar:id/action_send");
		  }else{
			  AcceptanceTestCase.assertTrue("Foward calenar event state cannot be opened",false);
		  }
		  commonModule.pressKey(KeyEvent.KEYCODE_BACK);
		  commonModule.clickResourceId("com.android.calendar:id/dayText");
		  
		}

}
