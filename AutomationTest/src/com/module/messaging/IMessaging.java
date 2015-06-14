package com.module.messaging;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.parents.Module;

public interface IMessaging extends Module{

    /**
     * Starts messaging application
     *
     */
    void startMessagingFromMenu();

    /**
     * To wait and check if the message has been received in the given time.
     * Then clear it.
     *
     * @textMessage text message for receive
     * @timeout timeout
     */
    boolean checkReceiveSMS(String textMessage, int timeout);

    /**
     * Removes all messages for conversations view.
     *
     */
    void removeAllMessages();

    /**
     * Starts messaging application and sends SMS with the given text to the
     * given phone number.
     *
     * @textMessage text message
     * @number the number that the message will be sent to
     * @throws UiObjectNotFoundException
     */

    void sendSMS(String textMessage, String number) throws UiObjectNotFoundException;

    /**
     * Send multi-messages to one number
     *
     * @textMessage text message
     * @number the number that the message will be sent to
     * @count the count about messgae.
     * @throws UiObjectNotFoundException
     */

    void sendMultiSMSToOneNumber(String textMessage, String number, int count) throws UiObjectNotFoundException;

    /**
     * Send message by API mode.
     *
     * @textMessage text message
     * @number the number that the message will be sent to
     */
    void remoteSendMessageToDUT(String textMessage, String number);

    /**
     * Insert message by write data to database.
     *
     * @number the number that the message will be sent to or received
     * @text text message
     * @smsBoxType inbox :1; sent:2; outbox :4;
     * @read 0:unread; 1:read
     */
    void insetSMSAPI(String number, String text, int smsBoxType, int read);

    /**
     * Add recipient by typing number form the send message view.
     *
     * @number the number that the message will be sent to
     */
    void addRecipient(String number);

    /**
     * Checks if the conversations list is empty. Returns true if list is empty.
     * Make sure to start messaging application before calling this method,
     *
     */
    boolean isMessageListEmpty();

    /**
     * Reply a SMS message with given message and phone number. Should open a
     * conversation first.and find the conversation by textMessage.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     * @throws UiObjectNotFoundException
     */
    void replySMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Wait Check if the message send successfully, if not assert false.
     */
    void waitMessageSendSuccessfully();

    /**
     * Forword a SMS message with given message and phone number. Should open a
     * conversation first. and find the conversation by textMessage.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     * @throws UiObjectNotFoundException
     */
    void forwordSMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Delete the message that contains given text.If there are multiple views
     * of the same text, the first one will be deleted.
     *
     * @textMessage text to search in message list
     *
     */
    void deleteMessageWithText(String textMessage);

    /**
     * Delete the conversation that contains given number.
     *
     * @number the conversation want to delete.
     *
     */
    void deleteMessageWithNumber(String number);

    /**
     * Send MMS and with the image as attachment.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     *
     */
    void sendMMSWithImage(String textMessage, String phoneNumber, boolean verifySendSuccessfully) throws UiObjectNotFoundException;

    /**
     * Add picture for MMS content.
     *
     */
    void InsertImageForMMS()  throws UiObjectNotFoundException;

    /**
     * Forword a MMS message with given message and phone number. Should open a
     * conversation first. and find the conversation by textMessage.
     *
     * @textMessage text message that is added to MMS
     * @number the number that the message will be sent to
     * @throws UiObjectNotFoundException
     */
    void forwordMMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Open conversations and start a new conversation then input recipient
     * number and message text
     *
     * @textMessage text message that is added to MMS
     * @number the number that the message will be sent to
     * @throws UiObjectNotFoundException
     */
    void prepareSMSMessage(String textMessage, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Input message content with given text. Need goto a conversation first
     *
     * @textMessage text message that is added to MMS
     */
    void inputTextMessage(String textMessage);

    /**
     * Delete the message that contains given text.If there are multiple views
     * of the same text, the first one will be deleted.
     *
     * @textMessage text to search in message list
     *
     */
    void deleteMMSWithText(String textMessage);

    /**
     * Check the receive from status bar and open it.
     *
     * @textMessage text to search in notification.
     *
     */
    void openReceiveMessage(String textMessage);

    /**
     * Starts messaging application and sends SMS with the given text to the
     * given phone number.
     *
     * @textMessage text message
     * @number the number that the message will be sent to
     */
    void sendSMSWithFlightMode(String textMessage, String number)  throws UiObjectNotFoundException;

    /**
     * Send MMS and with the image as attachment.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     */
    void sendMMSWithImageWithFlightMode(String textMessage, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Open Message Settings. Make sure Message is launched before invoke this
     * method.
     */
    void openMessageSettings() throws UiObjectNotFoundException;

    /**
     * Delete the conversation by mark one conversation from message list view.
     *
     * @phoneNumber the conversation want to delete.
     */
    void deleteConversationByMark(String phoneNumber);

    /**
     * Starts messaging application and insert SMS without phone number, and not
     * send.
     *
     * @message text message
     */
    void insertOneDraftMessage(String message) throws UiObjectNotFoundException;

    /**
     * Delete draft conversations.
     *
     * @text text draft
     */
    void deleteConversationsWithName(String text);

    /**
     * Send MMS and with the video as attachment.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     */
    void sendMMSWithVideo(String textMessage, String phoneNumber, int videoLength)  throws UiObjectNotFoundException;

    /**
     * Send MMS and with the picture and sound as attachment.
     *
     * @textMessage text message that is added to SMS
     * @number the number that the message will be sent to
     */
    void sendMMSWithPictureAndSound(String smsContentLongOut, String callNumber)  throws UiObjectNotFoundException;

    /**
     * Open a conversation by contact name or phone number.
     *
     * @text Conversation's name or phone number.
     */
    void openConversationByNameOrNumber(String text) throws UiObjectNotFoundException;

    /**
     * Save a picture in message. Make sure opened a conversation with picture
     * already.
     */
    void savePictureInMessage() throws UiObjectNotFoundException;

    /**
     * Set a message picture as wallpaper. Make sure opened a conversation with
     * picture already.
     */
    void setMessagePictureAsWallpaper()  throws UiObjectNotFoundException;

    /**
     * Play sound in message. Make sure opened a conversation with sound
     * already.
     */
    void playSoundInMessage() throws UiObjectNotFoundException;

    /**
     * Save a sound in message. Make sure opened a conversation with sound
     * already.
     *
     * @throws UiObjectNotFoundException
     */
    void saveSoundInMessage() throws UiObjectNotFoundException;

    /**
     * Set a audio in message as ringtone. Make sure opened a conversation with
     * sound already.
     */
    void setSoundAsRingtone() throws UiObjectNotFoundException;

    /**
     * Play video in message. Make sure opened a conversation with sound
     * already.
     */
    void playVideoInMessage()  throws UiObjectNotFoundException;

    /**
     * Save a video in message. Make sure opened a conversation with video
     * already.
     */
    void saveVideoInMessage() throws UiObjectNotFoundException;

    /**
     * Starts messaging application and sends SMS with the given text to the
     * given phone number, but not wait for MMS send successfully.
     *
     * @textMessage text message
     * @number the number that the message will be sent to
     */
    void sendMMSWithImageWithoutCheck(String string, String callNumber)  throws UiObjectNotFoundException;

    /**
     * Wait receive more than one messages.
     *
     * @param smsCount: the count of receive messages.
     * @throws UiObjectNotFoundException
     */
    void waitMoreMessagesReceive(int smsCount) throws UiObjectNotFoundException;

    /**
     * Receive more than one messages from different phone number, check message
     * count from notification, read one message, check message count changed
     * from notification.
     *
     * @param beforeCount
     * @param smsContent
     * @throws UiObjectNotFoundException
     */
    void checkStatusBarForMoreSMS(int beforeCount, String smsContent)
            throws UiObjectNotFoundException;

    /**
     * Setup MMS APN.
     */
    void setupMMSAPN() throws UiObjectNotFoundException;

    /**
     * Delete MMS APN.
     */
    void deleteMMSAPN() throws UiObjectNotFoundException;

    /**
     * Receive MMS without MMS APN.
     *
     * @param callNumber
     */
    void receiveMMSWithoutAPN(String callNumber, String content) throws UiObjectNotFoundException;

    /**
     * Receive MMS with MMS APN.
     *
     * @param callNumber
     * @param commandMms
     */
    void reciveMMSWithAPN(String callNumber, String commandMms) throws UiObjectNotFoundException;

    public void checkReceivedMessageInfo(String senderName);

    /**
     * Launch message, create a new conversation, add recipient, long press
     * content area, press paste to add content. Then send message. Make sure
     * there are copy/cut text before invoke this method.
     *
     * @param number
     * @throws UiObjectNotFoundException
     */
    void sendMessageByPasteContent(String number) throws UiObjectNotFoundException;

    public void makeACallFromMessageContent(String conversation, String callTo) throws UiObjectNotFoundException;

    public void sendMailFromMessageContent(String conversation, String sendTo, String subject) throws UiObjectNotFoundException;

    public void openURLFromMessageContent(String conversation, String address) throws UiObjectNotFoundException;

    public void copyMessageToSimCard(String message) throws UiObjectNotFoundException;

    public void simMessageCopyToPhoneOrDelete(String message, String operate) throws UiObjectNotFoundException;

	/**
     * There is a SMS message prepared, then add attachment for the SMS, check
     * the SMS convert to MMS.
     *
     * @throws UiObjectNotFoundException
     */
    void smsConvertToMmsByAddAttachment() throws UiObjectNotFoundException;

    /**
     * There is a MMS message prepared, then delete the attachment, check the
     * MMS convert to SMS.
     *
     * @throws UiObjectNotFoundException
     */
    void mmsConvertToSmsByRemoveAttachment() throws UiObjectNotFoundException;

    /**
     * There is a SMS message prepared, tap more option add subject, then edit
     * subject, check the SMS convert to MMS.
     *
     * @param subject
     * @throws UiObjectNotFoundException
     */
    void smsConvertToMmsByAddSubject(String subject) throws UiObjectNotFoundException;

    /**
     * There is a MMS message prepared, tap more option, select delete subject,
     * check the MMS convert to SMS.
     *
     * @throws UiObjectNotFoundException
     */
    void mmsConvertToSmsByRemoveSubject() throws UiObjectNotFoundException;

    /**
     * Tap send button, wait for message send successfully.
     *
     * @throws UiObjectNotFoundException
     */
    void sendMessageAndWaitMessageSendSuccessfully() throws UiObjectNotFoundException;

    public void verifyAttachmentAttached();

    public void insertImageFromShortcut() throws UiObjectNotFoundException;

    public void insertCameraPicture(String from) throws UiObjectNotFoundException;

    public void insertSketchNote(String from) throws UiObjectNotFoundException ;

    public void insertLocation(String from) throws UiObjectNotFoundException ;

    public void createMessage() throws UiObjectNotFoundException;

    public void insertContacts(String contactName) throws UiObjectNotFoundException;

    public void sendMMSWithCameraPicture(String textMessage, String phoneNumber, String insertFrom) throws UiObjectNotFoundException ;

    public void scanAndInsertFileForMMS(String folderName) throws UiObjectNotFoundException;

    public void sendSMSToMultipleRecipient(int recipients, String content, String receiver) throws UiObjectNotFoundException;

    /**
     * Check msg received in Message receive state
     * @param selSender whether the message send by self
     * @param content
     * @throws UiObjectNotFoundException
     */
    public void checkReceiveMessage(boolean selSender,String content) throws UiObjectNotFoundException;
    }

