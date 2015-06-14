
package com.module.contacts;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface IContacts {

    /**
     * Launch phonebook.
     */
    void startPhonebook() throws UiObjectNotFoundException;

    /**
     * Add some contacts.Make sure Phonebook is launched before invoke this
     * method.
     *
     * @count add contacts count
     */
	void addContacts(int count) throws UiObjectNotFoundException;

    /**
     * Add new contact from dialer input number
     *
     * @contactNumber contact number
     */
    void saveContactFromDialer(String contactNumber,String contactName) throws UiObjectNotFoundException;

    /**
     * Select call icon from Quick Contact.Make sure Phonebook is launched
     * before invoke this method ,and there are contacts in phone book.
     */
    void selectCallFromQuickContact() throws UiObjectNotFoundException;

    /**
     * Select message icon from Quick Contact.Make sure Phonebook is launched
     * before invoke this method ,and there are contacts in phone book.
     */
    void selectMessageFromQuickContact() throws UiObjectNotFoundException;

    /**
     * Select view contact from Quick Contact.Make sure Phonebook is launched
     * before invoke this method ,and there are contacts in phone book.
     */
    void selectViewContactFormQuickContact() throws UiObjectNotFoundException;

    /**
     * Select Email icon from Quick Contact.Make sure Phonebook is launched
     * before invoke this method ,and there are contacts in phone book.
     */
    void tapEmailIconFormQuickContact() throws UiObjectNotFoundException;

    /**
     * Select Maps icon from Quick Contact.Make sure Phonebook is launched
     * before invoke this method ,and there are contacts in phone book.
     */
    void tapMapsIconFormQuickContact() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, input several rows of characters in each fields, edit
     * contact picture via camera.
     * @param firstName
     * @param callNumber
     * @param mail
     * @throws UiObjectNotFoundException
     */
    void createContact(String firstName,String callNumber, String mail) throws UiObjectNotFoundException;


    public void createContactAPI(String name, String number, int groupid, String email);

    public void createMoreContacts(int num);
    /**
     * Launch Phonebook, change the number type.
     *
     * @callNumber phone number of the number type be changed.
     */
    void checkAllNumberType(String name, String callNumber) throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, remove all fields' info excepte name.
     */
    void removeAllFieldsInfoExceptName(String name) throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, check call log, and save the list of unsaved number to
     * a saved contact.
     *
     * @callNumber phone number be saved.
     */
    void saveNumberFormCallLog(String name, String callNumber) throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, delete all contacts.
     */
    void deleteContacts() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, search contact by contact name.
     */
    void searchByContactName() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, search contact by phone number.
     */
    void searchByPhoneNumber() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, search contact by other characters except contact's
     * name.
     */
    void searchByOtherCharacters() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, set a contact picture for a contact without picture.
     */
    void setContactPicture() throws UiObjectNotFoundException;

    /**
     * Launch Phonebook, change a contact picture for a contact with picture.
     */
    void changeContactPicture() throws UiObjectNotFoundException;

    /**
     * Export contacts from phoneboot
     *
     * @param storage the export storage
     */
    void exportContacts(String storage);

    /**
     * Create contact via ContentProviderOperation
     *
     * @param name, number the contact name and phone number
     */
    void createContactsURI(String name, String number, String email) throws Exception;

    /**
     * Import contacts from storage
     *
     * @param storage
     */
    void importContacts(String storage, String fileName) throws UiObjectNotFoundException;

    /**
     * open contact setting
     *
     * @param setting
     */
    void openPhonebooksetting(String setting);

    /**
     * add google email account
     *
     * @param String googleAccount, String password
     */
    void addAccount(String googleAccount, String password);

    /**
     * mark several contacts and make some operations
     *
     * @param partnerNumber
     */
    void markSeveral(String partnerNumber);

    /**
     * filter contacts
     */
    void filterContact();

    /**
     * find link candidates
     */
    void findLinkCandidates();

    /**
     * set speed dial number, support by several platform, will update script
     * later
     */
    void speedDialNumber();

    /**
     * Add contact with number without contact name.
     *
     * @param number: phone number
     * @throws UiObjectNotFoundException
     */
    void addContactWithNumberWithoutName(String number) throws UiObjectNotFoundException;

    /**
     * Add contact to favorite.
     *
     * @param name: Contact name.
     * @throws UiObjectNotFoundException
     */
    void addContactToFavorite(String name) throws UiObjectNotFoundException;

    /**
     * Delete favorite contact.
     *
     * @param contact: the contact you will delete.
     */
    void deleteFavoriteContact(String contact) throws UiObjectNotFoundException;

    /**
     * Send SMS to all valid number.
     *
     * @param string
     */
	void sendSMSToAllValidNumber(String string)
			throws UiObjectNotFoundException;

    /**
     * Send email to contact.
     *
     * @param contact: contact name.
     */
    void sendEmailToContact(String contact) throws UiObjectNotFoundException;

    /**
     * Send email to contact.
     *
     * @param contact: contact name.
     * @param phonenumber: the number to call.
     */
    void callContactInContactDetail(String contact, String phoneNumber) throws UiObjectNotFoundException;

    /**
     * Send email to contact.
     *
     * @param contact: contact name.
     * @param oldnumber: the number before.
     * @param newnumber: the number after.
     */
    void editContactNumber(String contact, String oldNumber, String newNumber) throws UiObjectNotFoundException;

    void addAllContactsToFavorite(String ...names) throws UiObjectNotFoundException;

    public void addContactsWithGmailInfo(String contactName) throws UiObjectNotFoundException;

    public void addContactWithoutNumber(String contactName) throws UiObjectNotFoundException;

    public void createGmailGroupAndAddContact(String groupName, int contactCount) throws UiObjectNotFoundException;

    public void filterContactWithNumber(String contactsString) throws UiObjectNotFoundException;

    public void filterContactWithGmailGroup(String groupName, String contactsString) throws UiObjectNotFoundException;

    public void deleteGroup(String groupName) throws UiObjectNotFoundException;

    public void filterShowAllContacts() throws UiObjectNotFoundException;

    public void addContactWithBirthday(String contactName) throws UiObjectNotFoundException;

    /**
     * Make a call via exist contact.
     *
     * @param contact
     * @param number
     * @throws UiObjectNotFoundException
     */
    void callByContact(String contact, String number) throws UiObjectNotFoundException;

    void shareContactBy(String contact, String shareAs, String number) throws UiObjectNotFoundException;

    public void linkAndUnlinkContacts(String contactA, String contactB, String linkResult) throws UiObjectNotFoundException;

    void createContactWithTwoGroups(String name, String number, String email, String group1,
            String group2) throws UiObjectNotFoundException;

    void checkGroupMember(String group, String ...members) throws UiObjectNotFoundException;

    void sendGroupMessage(String group) throws UiObjectNotFoundException;

    void sendGroupEmail(String group) throws UiObjectNotFoundException;

    void checkContactGroupInfo(String contactName, String... groups)
            throws UiObjectNotFoundException;

    void editMyself(String newName, String newNumber, String newEmail, boolean isChangePic)
            throws UiObjectNotFoundException;
    
    public void handlSameContactName(boolean linkContact,String contactName)
            throws UiObjectNotFoundException;
    
    public void editContactName(String oldContactName,String newcontactName)
            throws UiObjectNotFoundException ;
    
    public void checkContactExist(String contactName, int instance) throws UiObjectNotFoundException;
    
    public void deleteOneContact(String contactName) throws UiObjectNotFoundException;

    public void inputContactFirstName(String firstName) throws UiObjectNotFoundException;
    
    public void inputContactLastName(String lastName) throws UiObjectNotFoundException;
    
    public void inputPhoneNumber(String number) throws UiObjectNotFoundException;
    
    public void inputEmail(String mail) throws UiObjectNotFoundException;
	
	 public void createContactFullField(String contactName) throws UiObjectNotFoundException;
	 	 
	 public void cancelAutoSyncData() throws UiObjectNotFoundException;
	 
	 public void displayContacts(String account) throws UiObjectNotFoundException;
	 
	 public void assertSyncContactNumberCorrectly(int count) throws UiObjectNotFoundException;
	 
	 public void dismissContacts(String account) throws UiObjectNotFoundException;
	 
	 public void syncContact(String account) throws UiObjectNotFoundException;
	 
	 public void deleteContactsWithoutChecked() throws UiObjectNotFoundException;
}
