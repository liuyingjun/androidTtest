package com.module.email;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IEmail {

    /**
     * Login Email.
     *
     * @param userName
     * @param password
     * @param timeMin: time for login minutes.
     * @return
     */
    public boolean loginEmailSyncAutoServer(String userName, String password) throws UiObjectNotFoundException;

    /**
     * Send email with attachment. Make sure email already login before invoke
     * this method.
     *
     * @param attachName: the attachment name you add.
     * @param subject: email subject.
     */
    public void sendEmailWithAttachment(String to, String subject, String attachName) throws UiObjectNotFoundException;

    /**
     * Check email send successfully.
     *
     * @param subject
     */
    public void checkEmailSendSuccessfully(String subject) throws UiObjectNotFoundException;

    /**
     * Launch email application
     */
    public void launchEmail();

    /**
     * Send email, back to home screen after send email
     *
     * @param emailSender
     * @param emailReceiver
     * @param subject
     * @param content
     * @param ccAccount
     * @throws UiObjectNotFoundException
     */
    public void sendEmail(String emailSender, String emailReceiver, String subject, String content,
            boolean ccAccount) throws UiObjectNotFoundException;

    /**
     * Sort email by types.
     * @param sortType
     */
    public void sortBy(String sortType) throws UiObjectNotFoundException;

    /**
     * Select one of mail and reply.Add a reception and send it out.
     * @throws UiObjectNotFoundException
     */
    public void replyEmail(boolean searchEmail) throws UiObjectNotFoundException;

    /**
     * Add a new account.
     *
     * @param test_EASaccount
     * @param test_EASpassword
     * @param i
     * @return
     * @throws UiObjectNotFoundException
     */
    public boolean addAccount(String test_EASaccount, String test_EASpassword, int i) throws UiObjectNotFoundException;

    /**
     * Set synchroniz frequency for account.
     *
     * @param emailaccount: the account you selected.
     * @param frenquency: synchroniz frequency you set.
     */
    public void setSynchronizFrequency(String emailaccount, String frenquency) throws UiObjectNotFoundException;

    public void addGoogleAccount(String account, String password) throws UiObjectNotFoundException;

    public void addEmailAccountFromSettings(String account, String password) throws UiObjectNotFoundException;

    public void switchAccount(String switchTo) throws UiObjectNotFoundException;

    public void openMailBySubject(String subject) throws UiObjectNotFoundException;

    public void replyEmail(String type, String withContent) throws UiObjectNotFoundException;

    public void openTheEmailWithMusicAsAttachment(String account, String subject) throws UiObjectNotFoundException;

	/**
     * Select one of mail and forward.
     * @throws UiObjectNotFoundException
     */
    public void fowardEmail() throws UiObjectNotFoundException;
    
    public void sendEmailToManyReceiver(int receivers) throws UiObjectNotFoundException;
	
    public void removeAccount(String account) throws UiObjectNotFoundException;

    public void openTheEmailWithPictureAsAttachment(String account, String subject) throws UiObjectNotFoundException;

    public void checkMailReceived(String account, boolean openMail) throws UiObjectNotFoundException;

    public void deleteLatestMailByLongPress(String subject) throws UiObjectNotFoundException;

    public void deleteMailWhenOpened(String subject) throws UiObjectNotFoundException;

    public void removeAttachmentDuringEditMail(String sendTo, String subject)
            throws UiObjectNotFoundException;

    public void checkReceivedMailContent(String subject, int attachment)
            throws UiObjectNotFoundException;

    public void sendEmailWithMultipleAttachments(String to, String subject, String[] attachName)
            throws UiObjectNotFoundException;

    public void addAttachment(String attachmentType, String attachmentName, String fileFolder)
            throws UiObjectNotFoundException;

    public void prepareEmailEditor() throws UiObjectNotFoundException;

    public void changeDisplayName(String account, String changedName)
            throws UiObjectNotFoundException;

    public void addEASAccountFromSettings(String account, String password)
            throws UiObjectNotFoundException;

    public void syncEASInSetting(String account) throws UiObjectNotFoundException;

    public void syncEmailInSetting(String account) throws UiObjectNotFoundException;

    public void changeCheckFrequency(String account, String selection)
            throws UiObjectNotFoundException;
}
