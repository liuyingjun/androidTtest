package com.module.alarm;

import com.android.uiautomator.core.UiObjectNotFoundException;

import android.content.Context;


public interface IAlarm{

    /**
     * Start alarm application from menu.
     */
    void startAlarmFromMenu();

    /**
     * Add a new alarm.
	 *
     * @throws UiObjectNotFoundException
     */
    void setNewAlarmFewMinuteslater(int hour, int minute) throws UiObjectNotFoundException;

    void setAlarm(Context context);

    /**
     * accept Alarm
     */
    boolean snoozeAlarm(int timeout);


    /**
     * delete all alarms
     */
    void deleteAllAlarms();

    /**
     * Switch 4 alarms Tabs.
     */
	void switchAlarmTabs() throws UiObjectNotFoundException;

	/**
     * Switch alarms Tab By Description.
     *
     * @desc description of alarm tab.
     */
	void switchAlarmTabByDescription(String desc) throws UiObjectNotFoundException;

	/**
     * Set Home Town by World clock mode.
     */
	void setHomeTown(String homeCity) throws UiObjectNotFoundException;

	/**
     * Change temperature units by World clock mode.
     */
	void changeTemperatureUnits() throws UiObjectNotFoundException;

	/**
     * Rearrange by World clock mode.
     */
	void rearrangeWithWordClock() throws UiObjectNotFoundException;

	/**
     * Delete city by World clock mode.
     *
     * @city city name be deleted.
     */
	void deleteCity(String city) throws UiObjectNotFoundException;

	/**
     * Start stopwatch, stop, resume and reset.
     *
	 * @throws UiObjectNotFoundException
     */
	void stopwatchOperation() throws UiObjectNotFoundException;

	/**
     * Set time with Timer mode and start it, than clear Timer.
     */
	void TimerOperation() throws UiObjectNotFoundException;

	/**
	 * Verify city time UI display with weather indicate by World clock mode.
	 */
	void compareWorldClock() throws UiObjectNotFoundException;

	/**
	 * Delete alarm one by one
	 * */
	void deleteAlarmOneByOne() throws UiObjectNotFoundException;

	/**
	 * Add one new alarm
	 * */
	void addNewAlarm(String hour, String minute) throws UiObjectNotFoundException;


	/**
	 * Add one new alarm when alarm count more than 20
	 * */
	public void addNewAlarmAfterMaximum() throws UiObjectNotFoundException;

	/**
	 * Delete clock
	 * */
	public void deleteClock() throws UiObjectNotFoundException;

	/**
	 * Add multiple clock
	 * */
	public void addClockMultipleTime(int times, boolean flightMode)	throws UiObjectNotFoundException;

	public void startClock() throws UiObjectNotFoundException;

	public void deleteClockOneByOne() throws UiObjectNotFoundException;

	public void setStopWatch(int lapTime) throws UiObjectNotFoundException;

	public void setTimer(String hour,String minute, String second) throws UiObjectNotFoundException;

	public void stopStopwatchOrTimer(String type) throws UiObjectNotFoundException;

	public void setNewAlarmFewMinuteslaterByTap(int seconds) throws UiObjectNotFoundException;
}
