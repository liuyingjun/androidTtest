package com.module.calendar;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;


public interface ICalendar {

    /**
     * Start calendar application from menu.
     */
    void launchCalendar();

    /**
     * Add five events on different days.
     * @param subject the event title
     * @param account add account to which account: Google, EAS, Facebook, Local, facebook is not ready
     * @throws UiObjectNotFoundException
     */
    void addCalendarNewEvent(String subject,String account) throws UiObjectNotFoundException;

    /**
     * Add five events on different days.
     *
     */
    void addNewCalendarEventAPI(long startTimeInMilli, String strTaskName,
			String strDescription, String value, boolean needReminder);
    /**
     * Delete all the event.
     */
    void deleteAllTheEvent();
    /**
     * Wait for calendar alarm appear.
     *
     * @title calendar event title.
     * @timeout timeout
     */
    boolean waitForCalendarAlarm(String title, int timeout);

    /**
     * Tap new event icon, then input text in subject, location and description.
     *
     * @param subject
     * @throws UiObjectNotFoundException
     */
    void prepareNewEvent(String subject) throws UiObjectNotFoundException;

    /**
     * Launch Calendar application, Create an all day event, but not save.
     *
     * @param subject
     * @throws UiObjectNotFoundException
     */
    void createAllDayEventButNotSave(String subject) throws UiObjectNotFoundException;

    /**
     * Launch Calendar application, Create an all day event, and save.
     *
     * @param subject
     * @throws UiObjectNotFoundException
     */
    void createAllDayEventAndSave(String subject) throws UiObjectNotFoundException;

    /**
     * Add attendees for event. Make sure there are some contacts with different
     * email in Phone book before invoke this method.
     *
     * @param subject
     * @throws UiObjectNotFoundException
     */
    void addAttendeesForEvent(String subject) throws UiObjectNotFoundException;

    /**
     * Switch Calendar view Day/week/Month/Year/Agenda/Tasks. Make sure Calendar
     * application is launched before invoke this method.
     *
     * @param period
     * @throws UiObjectNotFoundException
     */
    void switchCalendarView(String period) throws UiObjectNotFoundException;

    /**
     * Switch Calendar (Day/Week/Month) view to check all day Event. Make sure
     * there is a all day event.
     *
     * @param subject: All day event subject.
     * @throws UiObjectNotFoundException
     */
    void switchCalendarViewToCheckAllDayEvent(String subject) throws UiObjectNotFoundException;

    /**
     * Switch Day/Week view, then swipe left or right to change date.
     *
     * @throws UiObjectNotFoundException
     */
    void changeDateByView() throws UiObjectNotFoundException;

    public void checkCalendarAccounts(String... accounts) throws UiObjectNotFoundException;

    public void setBirthdayLink(String status) throws UiObjectNotFoundException;

    public void checkBirthdaySetting(String status);

    public void switchCalendarViewInLaundscapeMode(String subject) throws UiObjectNotFoundException;
     	
			public void deleteEvent(String subject) throws UiObjectNotFoundException;
			
			public void editEvent(String subject) throws UiObjectNotFoundException;
			
			public void viewEvent(String subject) throws UiObjectNotFoundException;
			
			/**
			 * Foward calendar to multiple contacts
			 * @param eventSubject
			 * @param contactList
			 * @throws UiObjectNotFoundException
			 */
			public void fowardCalendarEvent(String eventSubject, ArrayList<String> contactList) throws UiObjectNotFoundException;

}
