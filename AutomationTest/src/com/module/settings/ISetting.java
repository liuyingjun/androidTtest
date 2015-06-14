package com.module.settings;

import java.text.ParseException;
import java.util.Date;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.Module;

public interface ISetting extends Module{

    public void launchSettings();

    public void setDataTraffic(String value) throws UiObjectNotFoundException;

    public void turnOnFlightMode() throws UiObjectNotFoundException;

    public void switchSoundSettings() throws UiObjectNotFoundException;

    void changeSoundMode(String value) throws UiObjectNotFoundException;

    public void takeScreenshotViaPowerMenu() throws UiObjectNotFoundException;

    public void wifiOnOrOff(String flag) throws UiObjectNotFoundException;

    public void turnOffFlightMode() throws UiObjectNotFoundException;

    public void changeCallipersRange() throws UiObjectNotFoundException;

    /**
     * Connect GTE wifi.
     *
     * @throws UiObjectNotFoundException
     */
    public void connectGTEWifi() throws UiObjectNotFoundException;

    /**
     * Set SIM PIN lock on or off.
     *
     * @param value
     * @throws UiObjectNotFoundException
     */
    public void setSimPinLock(String value) throws UiObjectNotFoundException;

    /**
     * Change SIM PIN.
     */
    public void changeSimPin() throws UiObjectNotFoundException;

    /**
     * Check change SIM PIN.
     */
    public void checkChangeSimPin() throws UiObjectNotFoundException;

    public void setStaminaMode(String mode)  throws UiObjectNotFoundException;

    public void openVPNSetting() throws UiObjectNotFoundException;

    public String connectVPN(String userID, String password) throws UiObjectNotFoundException;

    public void createPptpVPN() throws UiObjectNotFoundException;

    public void createL2tpPskVPN() throws UiObjectNotFoundException;

    public void createL2tpRsaVPN() throws UiObjectNotFoundException;

    public void deleteVPN() throws UiObjectNotFoundException;

    public void disconnectVPN() throws UiObjectNotFoundException;

    public void verifyVPN() throws UiObjectNotFoundException;

    public void connectVPNImpropriety(String vpnUsername,String vpnPassword) throws UiObjectNotFoundException;

    public void browerInternet(String url,String checkPoint) throws UiObjectNotFoundException;

    public void installCA() throws UiObjectNotFoundException;

    public void removeCA() throws UiObjectNotFoundException;

    public void checkDateTimeSetting() throws UiObjectNotFoundException;

    public void setDateManual(long date) throws UiObjectNotFoundException;

    public void setDateFormat() throws UiObjectNotFoundException;

    public void change24HourFormat() throws UiObjectNotFoundException;

    public void setTimeZoneManual() throws UiObjectNotFoundException,ParseException;

    public Date printDisplayTime(String time) throws ParseException;

    public void createAndSetAPN(String apnName, String apnSetting, String apnProtocol) throws UiObjectNotFoundException;

    public void deleteAPN(String apnName) throws UiObjectNotFoundException;

    public void changeWallpaper() throws UiObjectNotFoundException;

    public void changeTheme() throws UiObjectNotFoundException;

    public void removeAccount(String accountType, String accountName) throws UiObjectNotFoundException;

    void setLongNotificationTone(String musicName, String singer) throws UiObjectNotFoundException;

    void setNotificationTone(String tone) throws UiObjectNotFoundException;
    
    public void addItemToQuickSetting(String setting) throws UiObjectNotFoundException;
    
    public void deleteItemToQuickSetting(String setting) throws UiObjectNotFoundException;
    
    public void openSettingFromNotification(String setting)	throws UiObjectNotFoundException;
    
    public void removeAllItemFromQuickSetting() throws UiObjectNotFoundException;
    
    public void addMostItemToQuickSetting() throws UiObjectNotFoundException;
}
