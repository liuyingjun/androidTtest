package com.module.browser;

import java.io.File;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IBrowser {

    /**
     * launch chrome browser
     */
	public void launchChrome();

    /**
     * Play music file on background.
     *
     */
	public void downloadFileWithChrome(String url)  throws UiObjectNotFoundException;

    /**
     * launch Chrom.
     */
    void startChrom() throws UiObjectNotFoundException;

    /**
     * Load web page. Make sure Chrom is launched before invoking this method.
     *
     * @url Web page url.
     */
    void loadWebPage(String url)  throws UiObjectNotFoundException;

    /**
     * Tap more option button, press mark favorite icon, than click cancel. Make
     * sure Chrom is launched before invoking this method, and load a web page.
     */
    void addBookmarkButNotSave() throws UiObjectNotFoundException;

    /**
     * Tap more option button, press mark favorite icon, than save the bookmark.
     * Make sure Chrom is launched before invoking this method, and load a web
     * page.
     *
     * @bookmark the bookmark name.
     */
    void addBroswerBookmark(String bookmark) throws UiObjectNotFoundException;

    /**
     * Reopen a web page from history. Make sure Chrom is launched before
     * invoking this method.and load a web page.
     *
     * @url Web page url.
     */
    void reopenFromHistory(String url) throws UiObjectNotFoundException;

    /**
     * Delete the bookmark.
     *
     * @bookmark the bookmark name.
     */
    void deleteBookmark(String bookmark) throws UiObjectNotFoundException;

    /**
     * Load web page without network. Make sure Chrom is launched before
     * invoking this method.
     *
     * @url Web page url.
     */
    void loadWebPageWithoutNetwork(String string) throws UiObjectNotFoundException;

    /**
     * Launch Chrome, open bookmarks, select one bookmark, then edit url.
     *
     * @param bookmark
     */
    void editBookmarkUrl(String bookmark, String newUrl) throws UiObjectNotFoundException;

    /**
     * Launch Chrome, open bookmarks, select one bookmark, clear bookmark name and save.
     *
     * @param bookmark
     */
    void clearBookmarkNameAndSave(String bookmark) throws UiObjectNotFoundException;

    /**
     * Launch Chrome, open bookmarks, select one bookmark, edit bookmark name and save.
     *
     * @param nameBefore
     * @param nameAfter
     * @throws UiObjectNotFoundException
     */
    void editBookmarkName(String nameBefore, String nameAfter) throws UiObjectNotFoundException;

    /**
     * Load bookmark web page.
     *
     * @param bookmark
     * @throws UiObjectNotFoundException
     */
    void loadBookmark(String bookmark) throws UiObjectNotFoundException;

    /**
     * Prepare for edit bookmark.
     *
     * @param bookmark
     * @throws UiObjectNotFoundException
     */
    void prepareEditBookmark(String bookmark) throws UiObjectNotFoundException;

    /**
     * Backward web page.
     *
     * @throws UiObjectNotFoundException
     */
    void backwardWebPage() throws UiObjectNotFoundException;

    /**
     * forward web page.
     *
     * @throws UiObjectNotFoundException
     */
    void forwardWebPage() throws UiObjectNotFoundException;

    /**
     * Share Link page by message.
     *
     * @param number
     * @throws UiObjectNotFoundException
     */
    void shareLinkPageByMessaging(String number) throws UiObjectNotFoundException;

    /**
     * Share link page by Gmail.
     *
     * @param gmail
     * @throws UiObjectNotFoundException
     */
    void shareLinkPageByGmail(String gmail) throws UiObjectNotFoundException;

    /**
     * Press more option button to make toolbar display.
     */
    void checkBrowserToolbar();

    /**
     * Long tap url to enable magnifier.
     *
     * @throws UiObjectNotFoundException
     */
    void longTapUrlToEnableMagnifier() throws UiObjectNotFoundException;

    /**
     * Focus url bar, then tap url bar twice, press copy button.
     *
     * @throws UiObjectNotFoundException
     */
    void copyUrl() throws UiObjectNotFoundException;

    /**
     * Enter messaging, open the conversation which has link, press the link to
     * load the web pate.
     *
     * @param number: phone number.
     * @throws UiObjectNotFoundException
     */
    void loadWebPageInMessage(String number) throws UiObjectNotFoundException;
    
    /**
     * Input webpage and press Enter key
     * @param url
     * @throws UiObjectNotFoundException
     */
    void startLoadWebPage(String url) throws UiObjectNotFoundException;
    
    /**
     * Waiting for webpage load successfully
     * @throws UiObjectNotFoundException
     */
    void waitForWebPageLoadSuccessfully() throws UiObjectNotFoundException;


}
