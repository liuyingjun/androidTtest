
package com.module.contacts;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.parser.data.ModuleDataParser;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;

public class ContactsModule implements IContacts {

    AcceptanceTestCase testCase;

    CommonModule commonModule;

    HashMap<String, String> moduleData;

    String TAG = "Reliability";

    public ContactsModule(AcceptanceTestCase testCase) {
        // TODO Auto-generated constructor stub
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);

        moduleData = ModuleDataParser.getModuleData("contact");
    }

    public void startPhonebook() throws UiObjectNotFoundException {
        testCase.launchApp("com.sonyericsson.android.socialphonebook",
                "com.sonyericsson.android.socialphonebook.LaunchActivity");
        if (commonModule.waitForText("Contacts Setup", 2000)) {
            commonModule.clickText("Done");
        }
        AcceptanceTestCase.assertTrue("Launch phonebook failed.",
                commonModule.waitForDescription("Contacts", 5000)
                        || commonModule.waitForDescription("New contact", 5000));
    }

    public void addContacts(int count) throws UiObjectNotFoundException {

        for (int i = 0; i < count; i++) {
            startPhonebook();
            commonModule.clickDescription("New contact");
            if (testCase.isViewWithTextPresent("Select backup account")) {
                testCase.click("No backup");
            }
            if (commonModule.waitForText("Keep local", 2000)) {
                commonModule.clickText("Keep local");
            }
            commonModule.waitForResourceId("com.sonyericsson.android.socialphonebook:id/photo",
                    2000);

            commonModule.clickText("First name");
            testCase.enterText("Contact " + i);

            // UiObject phone = new UiObject(new UiSelector().resourceId(
            // "com.sonyericsson.android.socialphonebook:id/editors").instance(2));
            UiObject phone = new UiObject(new UiSelector().text("Phone").instance(1));
            phone.click();
            testCase.enterText("0123456789");

            commonModule.clickText("Add new");
            testCase.enterText("9876543210");

            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            // UiObject email = new UiObject(new UiSelector().resourceId(
            // "com.sonyericsson.android.socialphonebook:id/editors").instance(4));
            inputEmail("aaa@gmail.com");
            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            commonModule.clickText("Street");
            testCase.enterText("street");
            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            commonModule.clickText("City");
            testCase.enterText("city");

            commonModule.clickText("Done");
            commonModule.waitForResourceId(
                    "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

            commonModule.backOutToHomeScreen();
        }

    }

    public void saveContactFromDialer(String contactNumber,String contactName) throws UiObjectNotFoundException {

        if (contactNumber.equals("")) {
            testCase.clickItemWithDescription("More options");
            if (testCase.isViewWithTextPresent("Add to Contacts")) {
                AcceptanceTestCase.assertTrue(
                        "Don't have add to contacts function when there is no number in duaialer.", true);
            }
        }
        else{
        	commonModule.wait(2);

        	testCase.enterText(contactNumber);
        	testCase.clickItemWithDescription("More options");
        	testCase.click("Add to Contacts");

        	commonModule.wait(2);
        	if(commonModule.waitForResourceId("com.sonyericsson.android.socialphonebook:id/addicon", 2000)){
        	    commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/addicon");
        	}else if(commonModule.waitForResourceId("com.sonyericsson.android.socialphonebook:id/icon", 2000)){
        	    commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/icon");
        	}

        	if(commonModule.waitForText("Keep local", 3)){
        	    commonModule.clickText("Keep local");
        	}
            commonModule.waitForResourceId("com.sonyericsson.android.socialphonebook:id/photo", 2000);

            commonModule.clickText("First name");
            testCase.enterText(contactName);

            commonModule.clickText("Done");
            commonModule.waitForResourceId(
                    "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);
        }
        commonModule.backOutToHomeScreen();

    }

    public void enterQuickContact() throws UiObjectNotFoundException {
        UiObject pic = new UiObject(new UiSelector().resourceId(
                "com.sonyericsson.android.socialphonebook:id/list_item_photo").instance(2));
        pic.click();

        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/quickcontact_header", 2000);
    }

    public void selectCallFromQuickContact() throws UiObjectNotFoundException{
        enterQuickContact();

        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/actions_view_container");
        AcceptanceTestCase.assertTrue("Select call Icon failed.",
                commonModule.waitForResourceId("com.android.incallui:id/endCallButton", 2000));

        commonModule.clickResourceId("com.android.incallui:id/endCallButton");
        commonModule.waitForDescription("New contact", 2000);
    }

    public void selectMessageFromQuickContact() throws UiObjectNotFoundException {
        enterQuickContact();

        commonModule
                .clickResourceId("com.sonyericsson.android.socialphonebook:id/secondary_action_button");
        AcceptanceTestCase.assertTrue("Select message failed.", commonModule.waitForResourceId(
                "com.sonyericsson.conversations:id/conversation_title_text", 2000));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForDescription("New contact", 2000);
    }

    public void selectViewContactFormQuickContact() throws UiObjectNotFoundException{
        enterQuickContact();

        commonModule.clickDescription("Contact photo");
        AcceptanceTestCase.assertTrue(
                "Select view contact failed.",
                commonModule.waitForResourceId("android:id/home", 2000)
                        && commonModule.waitForDescription("Edit", 2000));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForDescription("New contact", 2000);
    }

    public void tapEmailIconFormQuickContact() throws UiObjectNotFoundException{
        enterQuickContact();

        commonModule.clickDescription("Email");
        AcceptanceTestCase.assertTrue("Tap Email icon failed.",
                commonModule.waitForTextContains(".com", 2000));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForDescription("New contact", 2000);
    }

    public void tapMapsIconFormQuickContact() throws UiObjectNotFoundException{
        enterQuickContact();
        commonModule.clickDescription("Maps");
        AcceptanceTestCase.assertTrue("Tap Maps icon failed.",
                commonModule.waitForText("Home", 2000));

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.waitForDescription("New contact", 2000);
    }

    public void createContact(String firstName, String number, String mail) throws UiObjectNotFoundException {
        enterAddContactView();

        commonModule.clickText("First name");
        testCase.enterText(firstName);

/*        commonModule.clickText("Last name");
        testCase.enterText("Bbb");*/

        if (number != null) {
            inputPhoneNumber(number);
        }

        if (mail != null) {
            inputEmail(mail);
        }

        commonModule.clickText("Street");
        testCase.enterText("Chaoyang");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("City");
        testCase.enterText("Beijing");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("State");
        testCase.enterText("China");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("Zip code");
        testCase.enterText("100000");

        commonModule.scrollToBegin();
        takePictureForContactPicture();

        commonModule.wait(2);
        commonModule.clickText("Done");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

    }

	public void createContactAPI(String name, String number, int groupid, String email) {
		ContentResolver resolver = testCase.getInstrumentation().getContext()
				.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();


		Resources res = testCase.getInstrumentation()
				.getContext().getResources();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] byteArray = baos.toByteArray();

		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
				.withValue("account_name", null).build();
		operations.add(op1);

		uri = Uri.parse("content://com.android.contacts/data");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/name")
				.withValue("data2", name).build();
		operations.add(op2);

		ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2")
				.withValue("data1", number).withValue("data2", "2").build();
		operations.add(op3);

		ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/email_v2")
				.withValue("data1", email)
				.withValue("data2", "2").build();
		operations.add(op4);

		ContentProviderOperation op6 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/photo")
				.withValue("data15", byteArray)
				.withValue("data2", "2").build();
		operations.add(op6);

		ContentProviderOperation op7 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/im")
				.withValue("data1", "5781496")
				.withValue("data2", "2").build();
		operations.add(op7);

		ContentProviderOperation op8 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/postal-address_v2")
				.withValue("data4", "Sepulveda Blvd")
				.withValue("data8", "Culver City")
				.withValue("data7", "CA")
				.withValue("data9", "90230")
				.withValue("data2", "2").build();
		operations.add(op8);

		ContentProviderOperation op9 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/note")
				.withValue("data1", "SUTest")
				.withValue("data2", "2").build();
		operations.add(op9);

		ContentProviderOperation op5 = ContentProviderOperation
				.newInsert(uri)
				.withValueBackReference(
						ContactsContract.CommonDataKinds.GroupMembership.RAW_CONTACT_ID,
						0)
				.withValue(
						ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID,
						groupid)
				.withValue(
						ContactsContract.CommonDataKinds.GroupMembership.MIMETYPE,
						ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
				.build();
		operations.add(op5);

		try {
			resolver.applyBatch("com.android.contacts", operations);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createMoreContacts(int num){
		String name="Tom";
		String number="134098654";
		String email="reliabilitytest@gmail.com";
		for (int i=0; i<num; i++){
			createContactAPI(name+i, number+i, 1,  email);
		}
	}

    public void changeNumberTypeAndCheck(String name ,String callNumber, String numberType) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText(name);
        commonModule.waitForDescription("Edit", 2000);

        commonModule.clickDescription("Edit");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/add_contact_spinner", 2000);

        commonModule
                .clickResourceId("com.sonyericsson.android.socialphonebook:id/add_contact_spinner");
        commonModule.waitForText(numberType, 2000);

        commonModule.clickText(numberType);
        if (commonModule.isTextExists("Custom field name")) {
            testCase.enterText("Custom");
            commonModule.clickText("OK");
        }
        commonModule.waitForText("Done", 2000);

        commonModule.clickText("Done");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

        commonModule
                .clickResourceId("com.sonyericsson.android.socialphonebook:id/primary_action_left_icon");
        AcceptanceTestCase.assertTrue(
                "Name or number not show on outgoing call screen.",
                commonModule.waitForText(name, 3000));
        //end call
        testCase.getUiDevice().pressKeyCode(KeyEvent.KEYCODE_ENDCALL);
        commonModule.wait(5);
        //commonModule.clickResourceId("com.android.incallui:id/endCallButton");
        commonModule.waitForResourceId("android:id/home", 3000);

        commonModule.clickResourceId("android:id/home");
        commonModule.wait(2);
        commonModule.waitForDescription("Call", 2000);
        commonModule.clickDescription("Call");

        AcceptanceTestCase.assertTrue(
                "Name/number/number type not record in call list.",
                commonModule.waitForTextContains(numberType, 3000)
                        && commonModule.waitForText(name, 3000)
                        && commonModule.waitForTextContains(callNumber.substring(0, 2), 3000));

        commonModule.backOutToHomeScreen();
    }

    public void checkAllNumberType(String name, String callNumber) throws UiObjectNotFoundException {
        changeNumberTypeAndCheck(name, callNumber, "Mobile");
        changeNumberTypeAndCheck(name, callNumber, "Home");
        changeNumberTypeAndCheck(name, callNumber, "Work");
        changeNumberTypeAndCheck(name, callNumber, "Main");
        changeNumberTypeAndCheck(name, callNumber, "Work Fax");
        changeNumberTypeAndCheck(name, callNumber, "Home Fax");
        changeNumberTypeAndCheck(name, callNumber, "Pager");
        changeNumberTypeAndCheck(name, callNumber, "Other");
        changeNumberTypeAndCheck(name, callNumber, "Custom");
    }

    public void removeAllFieldsInfoExceptName(String name) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText(name);
        commonModule.waitForDescription("Edit", 2000);

        commonModule.clickDescription("Edit");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/add_contact_spinner", 2000);

        commonModule.clickDescription("Contact photo");
        commonModule.waitForText("Remove photo", 2000);

        commonModule.clickText("Remove photo");
        commonModule.wait(1);

        commonModule.clickResourceIdByInstance(
                "com.sonyericsson.android.socialphonebook:id/delete_button", 0);

        commonModule.clickResourceIdByInstance(
                "com.sonyericsson.android.socialphonebook:id/delete_button", 1);

        commonModule.scrollToEnd();

        commonModule.emptyEditorByInstance(0);
        commonModule.emptyEditorByInstance(1);
        
        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue("Contact not only name display.", commonModule.waitForText(
                "No contact info. Tap edit icon on action bar to add info.", 2000));

        commonModule.backOutToHomeScreen();
    }

    public void saveNumberFormCallLog(String name, String callNumber) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickDescription("Call");
        commonModule.wait(2);
        AcceptanceTestCase.assertTrue("The unsaved number not show.",
                commonModule.waitForTextGone(name, 5000));

        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/detail_icon");
        commonModule.waitForText("Add to Contacts", 2000);

        commonModule.clickText("Add to Contacts");
        commonModule.waitForText(name, 2000);

        commonModule.clickText(name);
        commonModule.waitForText("Done", 2000);

        commonModule.clickText("Done");
        commonModule.waitForText("Call details", 2000);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        int numLength = callNumber.length();
        AcceptanceTestCase.assertTrue(
                "The unsaved number not show.",
                commonModule.waitForText(callNumber.substring(4, numLength-1), 2000)
                        && commonModule.waitForText(name, 2000));

        commonModule.backOutToHomeScreen();
    }

    public void deleteContacts() throws UiObjectNotFoundException {
        startPhonebook();

        if (!commonModule.isTextExists("No contacts")) {
            commonModule.pressMoreOption();
            commonModule.waitForText("Mark several", 2000);

            commonModule.clickText("Mark several");
            commonModule.waitForText("0 selected", 2000);

            commonModule.clickText("0 selected");
            commonModule.waitForText("Mark all", 2000);

            commonModule.clickText("Mark all");
            commonModule.waitForDescription("Delete", 2000);

            commonModule.clickDescription("Delete");
            commonModule.waitForText("Delete", 2000);

            commonModule.clickText("Delete");
            commonModule.waitForText("Cannot delete following contacts:", 2000);

            commonModule.clickText("OK");
        }
        AcceptanceTestCase.assertTrue("Delete contacts failed.",
                commonModule.waitForText("No contacts", 10*1000));

        commonModule.backOutToHomeScreen();
    }

    public void sendSMSToAllValidNumber(String contactName) throws UiObjectNotFoundException{
        startPhonebook();
        commonModule.waitForText(contactName, 2000);

        commonModule.clickText(contactName);
        commonModule.waitForDescription("Text message", 2000);

        commonModule.clickDescription("Text message");
        commonModule.waitForText("Write message", 2000);

        commonModule.clickText("Write message");
        testCase.enterText("This is for RT case.");

        commonModule.clickText("Send");// send message.
    }

    public void searchByContactName() throws UiObjectNotFoundException{
        startPhonebook();

        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/menu_search");
        commonModule.waitForResourceId("android:id/search_plate", 2000);

        testCase.enterText("Contact");
        AcceptanceTestCase.assertTrue("Search by contact name failed.", commonModule
                .waitForResourceId("com.sonyericsson.android.socialphonebook:id/list_item", 2000));

        commonModule.backOutToHomeScreen();
    }

    public void searchByPhoneNumber() throws UiObjectNotFoundException{
        startPhonebook();

        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/menu_search");
        commonModule.waitForResourceId("android:id/search_plate", 2000);

        testCase.enterText("0123456789");
        AcceptanceTestCase.assertTrue("Search by phone number failed.", commonModule
                .waitForResourceId("com.sonyericsson.android.socialphonebook:id/list_item", 2000));

        commonModule.backOutToHomeScreen();
    }

    public void searchByOtherCharacters() throws UiObjectNotFoundException{
        startPhonebook();

        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/menu_search");
        commonModule.waitForResourceId("android:id/search_plate", 2000);

        testCase.enterText("Jack");
        AcceptanceTestCase.assertTrue("Search by other characters failed.",
                commonModule.waitForText("No matching contacts found", 2000));

        commonModule.backOutToHomeScreen();
    }

    public void takePictureForContactPicture() throws UiObjectNotFoundException {
        commonModule.clickDescription("Contact photo");

        if (commonModule.waitForText("Take photo", 2000)) {
            commonModule.clickText("Take photo");
        } else if (commonModule.waitForText("Take new photo", 2000)) {
            commonModule.clickText("Take new photo");
        }

        if (commonModule.waitForText("Camera", 2000)) {
            commonModule.clickText("Camera");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        commonModule.wait(2);

        if (commonModule.isDescriptionExists("Double tap to switch to front camera")) {
            commonModule.clickDescription("Double tap to switch to front camera");
            commonModule.wait(2);
        }

        commonModule.clickDescription("Camera key");

        if (commonModule.waitForText("Crop picture", 2000)) {
            commonModule.clickText("Crop picture");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
            commonModule.wait(5);

            commonModule.clickText("Crop");
        }
        commonModule.setOrientationPortrait();
    }

    public void setContactPicture() throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText("Contact 0");
        commonModule.waitForDescription("Edit", 2000);

        commonModule.clickDescription("Edit");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/add_contact_spinner", 2000);

        takePictureForContactPicture();

        commonModule.clickText("Done");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

        commonModule.backOutToHomeScreen();
    }

    public void changeContactPicture() throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText("Aaa Bbb");
        commonModule.waitForDescription("Edit", 2000);

        commonModule.clickDescription("Edit");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/add_contact_spinner", 2000);

        takePictureForContactPicture();

        commonModule.clickText("Done");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

        commonModule.backOutToHomeScreen();
    }

    public void exportContacts(String storage) {
        testCase.clickItemWithDescription("More options");
        testCase.click("Export contacts");
        testCase.click(storage);
        if (storage.equalsIgnoreCase("SIM card")) {
            commonModule.wait(2);
            testCase.click("Mark all");
            testCase.click("Export");
            if (testCase.isViewWithIdPresent("select_dialog_listview")) {
                testCase.click("Add contacts");
            }
        } else if (storage.equalsIgnoreCase("Internal storage")) {
            commonModule.wait(2);
            testCase.clickId("button1");
        } else if (storage == "SD card") {
            commonModule.wait(2);
            testCase.clickId("button1");
        } else if (storage.equalsIgnoreCase("USB storage")) {

        }
        AcceptanceTestCase.assertTrue("Export contacts failed",
                commonModule.waitForTextGone("Exporting contacts", 300 * 1000));

    }

    public void createContactsURI(String name, String number, String email) throws Exception {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = testCase.getInstrumentation().getContext().getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null).build();
        operations.add(op1);

        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/name")
            .withValue("data2", name)
            .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
            .withValue("data1", number)
            .withValue("data2", "2")
            .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
        .withValueBackReference("raw_contact_id", 0)
        .withValue("mimetype", "vnd.android.cursor.item/email_v2")
        .withValue("data1", email)
        .withValue("data2", "2")
        .build();
        operations.add(op4);

        resolver.applyBatch("com.android.contacts", operations);
    }

    public void importContacts(String storage, String fileName) throws UiObjectNotFoundException {
        openPhonebooksetting("Import contacts");
        testCase.click(storage);
        if (testCase.isViewWithTextPresent("Local contact")) {
            testCase.click("Local contact");
        }

        commonModule.wait(2);
        if (storage.equalsIgnoreCase("SIM card")) {
            if (testCase.isViewWithTextPresent("Import all")) {
                testCase.click("Import all");
            }
        } else if (storage.equalsIgnoreCase("Internal storage")) {
            if (commonModule.waitForText("Select vCard file", 120 * 1000)) {
                commonModule.clickTextContains(fileName);
                testCase.click("OK");
            } else {
                AcceptanceTestCase
                        .assertTrue("There are no vcard exist on Internal storage", false);
            }

        } else if (storage.equalsIgnoreCase("SD card")) {
            if (commonModule.waitForText("Select vCard file", 120 * 1000)) {
                testCase.clickItemWithId("text1", 0);
                testCase.click("OK");
            } else {
                AcceptanceTestCase
                        .assertTrue("There are no vcard exist on Internal storage", false);
            }

        } else if (storage.equalsIgnoreCase("USB storage")) {

        }
        AcceptanceTestCase.assertTrue("Import contacts failed",
                commonModule.waitForTextGone("Import all", 300 * 1000));

    }

    public void openPhonebooksetting(String setting) {
        commonModule.wait(2);
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        testCase.click(setting);
    }

    public void addAccount(String googleAccount, String password){
    	openPhonebooksetting("Add account");
    	commonModule.wait(2);
    	testCase.click("Google");
    	testCase.click("Existing");
    	if(testCase.isViewWithTextPresent("Sign in")){
    		testCase.clickId("username_edit");
    		testCase.enterText(googleAccount);
    		testCase.clickId("password_edit");
    		testCase.enterText(password);
    		testCase.clickId("next_button");
    		if(testCase.isViewWithTextPresent("OK")){
    			testCase.click("OK");
    		}
    		if(testCase.isViewWithTextPresent("Couldn't sign in")){
    			AcceptanceTestCase.assertTrue(false);
    		}else{
    			boolean skipButton =	commonModule.waitForId("skip_button", 30*1000);
				  if(skipButton){
					  testCase.clickId("skip_button");
				  }
    		  boolean nextButton =	commonModule.waitForId("next_button", 10*1000);
				  if(nextButton){
					  testCase.clickId("next_button");
					  testCase.clickId("next_button");
				  }
				  AcceptanceTestCase.assertTrue("Add google account failed", commonModule.waitForDescription("More options", 5000));
    		}
    	}
	}

    public void markSeveral(String partnerNumber) {
        // Delete contact
        openPhonebooksetting("Mark several");
        commonModule.wait(2);
        if (testCase.isViewWithIdPresent("list_item")) {
            testCase.clickItemWithId("list_item", 2);
            testCase.clickId("markmode_option_delete");
            testCase.clickId("button1");
        } else {
            Log.w(TAG, "The are no contacts exist in phonebook");
        }

        // Favorite contact
        openPhonebooksetting("Mark several");
        commonModule.wait(2);
        if (testCase.isViewWithIdPresent("list_item")) {
            testCase.clickItemWithId("list_item", 2);
            testCase.clickId("markmode_option_favorites");
        } else {
            Log.w(TAG, "The are no contacts exist in phonebook");
        }

        // Share contact
        openPhonebooksetting("Mark several");
        commonModule.wait(2);
        if (testCase.isViewWithIdPresent("list_item")) {
            testCase.clickItemWithId("list_item", 2);
            testCase.clickItemWithDescription("Share with");
            testCase.click("See all");
            if (testCase.isViewWithTextPresent("Messaging")) {
                testCase.click("Messaging");
            } else {
                testCase.pressKey(KeyEvent.KEYCODE_BACK);
                testCase.clickId("default_activity_button");
            }
            commonModule.waitForId("recipients_editor", 10 * 1000);
            testCase.clickId("recipients_editor");
            testCase.enterText(partnerNumber);
            testCase.click("Send");
        } else {
            Log.w(TAG, "The are no contacts exist in phonebook");
        }
    }

    public void filterContact() {
        openPhonebooksetting("Filter");
        commonModule.wait(2);
        testCase.click("Contacts with phone numbers");

        testCase.click("Sort list by");
        testCase.click("Last name");

        testCase.click("View contacts as");
        testCase.click("Last name first");

        testCase.click("Local contact");
        testCase.click("Colleagues");
        testCase.click("Family");
        testCase.click("Friends");
        testCase.click("All other contacts");

        testCase.click("Done");

    }

    public void findLinkCandidates(){
		    	openPhonebooksetting("Find link candidates");
		    	commonModule.wait(2);
		    	boolean linkContact = commonModule.waitForText("Link contacts suggestion", 30*1000);
		    	if(linkContact){
		    		commonModule.scrollFindId("link_icon");
		    		testCase.click("Done");
		    	}
		    	if(testCase.isViewWithIdPresent("button1")){
		    		testCase.clickId("button1");
		    	}
		}
    public void speedDialNumber(){
		    	openPhonebooksetting("Settings");
		    	commonModule.wait(2);
	}

    public void addContactWithNumberWithoutName(String number) throws UiObjectNotFoundException {
        startPhonebook();
        commonModule.clickDescription("New contact");
        if (testCase.isViewWithTextPresent("Select backup account")) {
            testCase.click("No backup");
        }
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }

        inputPhoneNumber(number);
        commonModule.clickText("Done");
        if (commonModule.waitForText("Link contacts suggestion", 3000)) {
            commonModule.clickText("Cancel");
        }
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

        commonModule.backOutToHomeScreen();
    }

    public void addAllContactsToFavorite(String ...names) throws UiObjectNotFoundException {
        int i = 0;
        for (String name : names) {
            startPhonebook();
            commonModule.scrollFindTextNotClick(name);

            UiObject actBar = new UiObject(new UiSelector().resourceId("android:id/split_action_bar"));
            Rect aRect = actBar.getVisibleBounds();
            UiObject contact = new UiObject(new UiSelector().text(name));
            Rect cRect = contact.getVisibleBounds();
            if (cRect.bottom >= aRect.top) {
                testCase.scrollDown();
            }

            testCase.longPressItemWithText(name);
            commonModule.waitForDescription("Add favorite", 2000);
//            AcceptanceTestCase.assertTrue("cRect.bottom=" + cRect.bottom + ", aRect.top="
//                    + aRect.top, commonModule.waitForDescription("Add favorite", 2000));

            commonModule.clickDescription("Add favorite");
            commonModule.wait(3);

            commonModule.clickDescription("Favorites");
            i = i + 1;
            int j = commonModule.getResourceIdCount(
                    "com.sonyericsson.android.socialphonebook:id/favorites_pane_view");
            AcceptanceTestCase.assertTrue(
                    "Add " + name + " to favorite contacts" + j, i == j);
//            AcceptanceTestCase.assertTrue(
//                    "Add " + i + "favorite contacts, but only success " + j, i == j);

            commonModule.backOutToHomeScreen();
        }
    }

    public void addContactToFavorite(String name) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.scrollFindTextNotClick(name);

        UiObject actBar = new UiObject(new UiSelector().resourceId("android:id/split_action_bar"));
        Rect aRect = actBar.getVisibleBounds();
        UiObject contact = new UiObject(new UiSelector().text(name));
        Rect cRect = contact.getVisibleBounds();
        if (cRect.bottom >= aRect.top) {
            testCase.scrollDown();
        }

        testCase.longPressItemWithText(name);
        commonModule.waitForDescription("Add favorite", 2000);

        commonModule.clickDescription("Add favorite");
        commonModule.wait(3);

        commonModule.clickDescription("Favorites");
    }

    public void deleteFavoriteContact(String contact) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickDescription("Favorites");
        commonModule.waitForText(contact, 2000);

        commonModule.clickText(contact);
        commonModule.waitForDescription("Add to Favorites", 2000);

        commonModule.clickDescription("Add to Favorites");
        commonModule.wait(2);

        for (int i = 0; i < 3; i++) {
            if (!commonModule.isDescriptionContains("Add to Favorites")) {
                break;
            }
            testCase.pressKey(KeyEvent.KEYCODE_BACK);
            commonModule.wait(2);
        }

        AcceptanceTestCase.assertTrue("Delete favorite contact failed.",
                !commonModule.waitForText(contact, 3000));
    }

    public void sendEmailToContact(String contact) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText(contact);
        commonModule.waitForTextContains(".com", 2000);

        commonModule.clickTextContains(".com");
        if (commonModule.waitForText("Complete action using", 2000)) {
            commonModule.clickText("Email");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        AcceptanceTestCase.assertTrue("Select email failed.",
                commonModule.waitForResourceId("com.android.email:id/send", 3000));

        commonModule.clickText("Subject");
        testCase.enterText("RT test");

        commonModule.clickResourceId("com.android.email:id/message_content");
        testCase.enterText("This is for RT test !");

        commonModule.clickResourceId("com.android.email:id/send");
        AcceptanceTestCase.assertTrue("Send email to contact failed.",
                commonModule.waitForDescription("Edit", 5 * 1000));
    }

    public void editContactNumber(String contact, String oldNumber, String newNumber)
            throws UiObjectNotFoundException {
        commonModule.scrollFindText(contact);
        commonModule.clickDescription("Edit");
        commonModule.wait(2);
        commonModule.clickText(oldNumber);
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/delete_button");
        testCase.enterText(newNumber);
        commonModule.wait(2);
        commonModule.clickText("Done");
    }

    public void callContactInContactDetail(String contact, String phoneNumber)
            throws UiObjectNotFoundException {
        commonModule.wait(2);
        AcceptanceTestCase.assertTrue("Contact detail should be opened",
                commonModule.isTextExists(contact));
        String formatNumber = phoneNumber.substring(0, 3) + "‪ "
                + phoneNumber.substring(3, phoneNumber.length());
        Log.i(TAG, formatNumber);
        commonModule.wait(2);
        // commonModule.clickText(formatNumber);
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/data");
        AcceptanceTestCase.assertTrue("Calling state is shown",
                commonModule.waitForResourceId("com.android.incallui:id/full_incallscreen", 10000));
    }

    public void addContactWithoutNumber(String contactName) throws UiObjectNotFoundException{

        startPhonebook();
        commonModule.clickDescription("New contact");
        if(testCase.isViewWithTextPresent("Select backup account")){
            testCase.click("No backup");
        }
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }

        commonModule.wait(2);

        inputContactFirstName(contactName);
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("Done");

        commonModule.backOutToHomeScreen();

    }

    public void addContactsWithGmailInfo(String contactName) throws UiObjectNotFoundException{

        startPhonebook();
        commonModule.clickDescription("New contact");
        if(testCase.isViewWithTextPresent("Select backup account")){
            testCase.click("Google");
        }
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }
        commonModule.wait(2);

        inputContactFirstName(contactName);

        inputPhoneNumber("12345");

        commonModule.clickText("Done");
        commonModule.wait(2);

        commonModule.backOutToHomeScreen();

    }

    public void createGmailGroupAndAddContact(String groupName, int contactCount) throws UiObjectNotFoundException{
        startPhonebook();

        commonModule.clickDescription("Groups");

        commonModule.clickResourceId(moduleData.get("Create_Group_Icon"));

        if(testCase.isViewWithTextPresent("Create group under account")){
            testCase.click("Google");
        }

        testCase.enterText(groupName);
        commonModule.wait(2);

        testCase.click("Done");
        commonModule.wait(2);

        commonModule.clickResourceId(moduleData.get("Add_contact_for_group_icon"));

        for (int i=0; i<contactCount ;i++){
            commonModule.clickIdWithInstance(moduleData.get("Checkbox_after_each_contact"), i);
            commonModule.wait(2);
        }

        testCase.click("Done");
        commonModule.wait(2);
    }

    public void filterContactWithNumber(String contactsString) throws UiObjectNotFoundException{
        startPhonebook();

        openPhonebooksetting("Filter");

        testCase.click("Contacts with phone numbers");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        AcceptanceTestCase.assertTrue("Contacts which without number shouldn't show",
                !commonModule.waitForText(contactsString, 3000));

        //remove filter
        openPhonebooksetting("Filter");
        testCase.click("Contacts with phone numbers");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }

    public void filterContactWithGmailGroup(String groupName, String contactsString) throws UiObjectNotFoundException{
        startPhonebook();

        openPhonebooksetting("Filter");

        testCase.click("Google");

        //uncheck "My Contacts"
        UiObject myContactsCheckbox = new UiObject(new UiSelector().resourceId("android:id/checkbox").instance(0));
        if(myContactsCheckbox.isChecked()){
            myContactsCheckbox.click();
        }

        commonModule.scrollFindText(groupName);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        if(commonModule.isTextContains(contactsString)){
            //new group is checked, then uncheck again then verify
            openPhonebooksetting("Filter");
            testCase.click("Google");
            commonModule.scrollFindText(groupName);

            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            AcceptanceTestCase.assertTrue("the gamil contacts shouldn't show",
                    !commonModule.waitForText(contactsString, 5000));
        }else{
            //group is unchecked ,check again then verify
            openPhonebooksetting("Filter");
            testCase.click("Google");
            commonModule.scrollFindText(groupName);

            testCase.pressKey(KeyEvent.KEYCODE_BACK);

            AcceptanceTestCase.assertTrue("the gmail contacts should show",
                    commonModule.waitForText(contactsString, 5000));
        }

        //remove the filter
        startPhonebook();
        openPhonebooksetting("Filter");
        testCase.click("Google");
        myContactsCheckbox.click();
    }

    public void deleteGroup(String groupName) throws UiObjectNotFoundException{
        startPhonebook();

        commonModule.clickDescription("Groups");

        commonModule.scrollFindText(groupName);

        commonModule.pressMoreOption();

        commonModule.clickText("Delete group");

        commonModule.clickText("OK");
    }

    public void filterShowAllContacts() throws UiObjectNotFoundException{
        startPhonebook();
        openPhonebooksetting("Filter");
        UiObject contactWithNumber = new UiObject(new UiSelector().resourceId("android:id/checkbox").instance(0));
        if(contactWithNumber.isChecked()){
            contactWithNumber.click();
        }

        testCase.click("Local contact");
        UiObject allOtherContactBox = new UiObject(new UiSelector().resourceId("android:id/checkbox").instance(3));
        if(!allOtherContactBox.isChecked()){
            allOtherContactBox.click();
            commonModule.wait(1);
        }
        commonModule.wait(2);
        commonModule.scrollFindText("Google");
        commonModule.wait(1);
        UiObject myContactsCheckbox = new UiObject(new UiSelector().resourceId("android:id/checkbox").instance(0));
        if(!myContactsCheckbox.isChecked()){
            myContactsCheckbox.click();
            commonModule.wait(3);
        }

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }

    public void addContactWithBirthday(String contactName) throws UiObjectNotFoundException{
        SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
        String date = sDateFormat.format(new java.util.Date());
        String day = date.split("-")[0];
        String month = date.split("-")[1];
        String year = date.split("-")[2];

        startPhonebook();

        commonModule.clickDescription("New contact");
        if(testCase.isViewWithTextPresent("Select backup account")){
            testCase.click("Google");
        }
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }
        commonModule.wait(2);

        commonModule.clickText("First name");
        testCase.enterText(contactName);

        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(2);

        commonModule.scrollFindText("Add another field");
        commonModule.wait(2);

        commonModule.clickText("Event");

        //set birthday
        commonModule.clickIdWithInstance("android:id/numberpicker_input", 0);
        testCase.enterText(month);

        commonModule.clickIdWithInstance("android:id/numberpicker_input", 1);
        testCase.enterText(day);

        commonModule.clickIdWithInstance("android:id/numberpicker_input", 2);
        testCase.enterText(year);

        //Click Set button
        commonModule.clickText("Set");
        commonModule.wait(2);

        //click 'Done' to save contact
        commonModule.clickText("Done");
        commonModule.wait(3);

        commonModule.backOutToHomeScreen();

    }

    public void callByContact(String contact, String number) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText(contact);
        commonModule.clickText(number);
        commonModule.waitForResourceId("com.android.incallui:id/endCallButton", 5000);

        commonModule.clickResourceId("com.android.incallui:id/endCallButton");
    }

    public void shareContactBy(String contact, String shareAs, String number) throws UiObjectNotFoundException {
        startPhonebook();

        commonModule.clickText(contact);
        commonModule.pressMoreOption();
        commonModule.clickText("Send contact");
        commonModule.waitForText("Confirm send", 2000);

        commonModule.clickText("OK");
        commonModule.waitForText("Send contacts via", 2000);

        commonModule.clickText(shareAs);
        if(shareAs.equals("Gmail")) {
            commonModule.clickResourceId("com.google.android.gm:id/to");
            testCase.enterText("agingtestnew002@gmail.com\n");

            commonModule.clickResourceId("com.google.android.gm:id/subject");
            testCase.enterText("Share contact " + contact + " by Gmail.\n");

            commonModule.clickResourceId("com.google.android.gm:id/send");
            AcceptanceTestCase.assertTrue("Could not share contact by Gmail.",
                    commonModule.waitForResourceId(
                            "com.sonyericsson.android.socialphonebook:id/menu_edit", 2000));

        } else if (shareAs.equals("Email")) {
            commonModule.clickResourceId("com.android.email:id/to");
            testCase.enterText("agingtestnew002@gmail.com\n");

            commonModule.clickResourceId("com.android.email:id/subject");
            testCase.enterText("Share contact " + contact + " by Email.");

            commonModule.clickResourceId("com.android.email:id/send");
            AcceptanceTestCase.assertTrue("Could not share contact by Email.",
                    commonModule.waitForResourceId(
                            "com.sonyericsson.android.socialphonebook:id/menu_edit", 2000));

        } else if (shareAs.equals("Messaging")) {
            commonModule.clickResourceId("com.sonyericsson.conversations:id/recipients_editor");
            testCase.enterText(number);

            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
            testCase.enterText("Share contact " + contact + " by Messaging.");

            commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_send_button");
            commonModule.wait(2);

            UiObject content = new UiObject(
                    new UiSelector().resourceId("com.sonyericsson.conversations:id/mms_slide_text"));
            String contentStr = content.getText();
            AcceptanceTestCase.assertTrue("Could not share contact by message.",
                    contentStr.equals("Share contact " + contact + " by Messaging."));
        }
//        commonModule.backOutToHomeScreen();
    }

    public void linkAndUnlinkContacts(String contactA, String contactB, String linkResult) throws UiObjectNotFoundException{
        startPhonebook();

        //link contactA with contactB
        commonModule.scrollFindText(contactA);

        commonModule.pressMoreOption();
        commonModule.clickText("Link contact");
        AcceptanceTestCase.assertTrue("All contact list should display after select 'Link contact'", commonModule.waitForText("All contacts", 5000));

        commonModule.clickText(contactB);
        AcceptanceTestCase.assertTrue("Confirm link dialog popup with OK button", commonModule.waitForText("OK", 5000));

        commonModule.clickText("OK");
        AcceptanceTestCase.assertTrue("After link ,the contact name should display as "+linkResult, commonModule.waitForText(linkResult, 5000));


        //Unlink
        commonModule.clickResourceId(moduleData.get("Edit_Contact_Icon"));
        commonModule.wait(2);

        commonModule.clickResourceIdByInstance("android.widget.EditText", 0);
        commonModule.wait(1);
        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        commonModule.wait(1);

        commonModule.scrollFindText("Unlink contact");
        AcceptanceTestCase.assertTrue("Confirm unlink dialog popup with Unlink button", commonModule.waitForText("Unlink contact?", 5000));

        commonModule.clickText("Unlink");

        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
        if(commonModule.waitForText("Discard your changes?", 3000)){
            commonModule.clickText("OK");
        }

        commonModule.pressKey(KeyEvent.KEYCODE_BACK);

        AcceptanceTestCase.assertTrue("After unlink "+contactA+" should display", commonModule.waitForText(contactA, 3000));
        AcceptanceTestCase.assertTrue("After unlink "+contactB+" should display", commonModule.waitForText(contactB, 3000));
    }

    public void inputContactFirstName(String firstName) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("First name")) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if (commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
            commonModule.scrollToBegin();
            // Empty Fist name editor.
            UiObject object = new UiObject(new UiSelector().className("android.widget.EditText")
                    .instance(0));
            Rect mRect = object.getVisibleBounds();
            testCase.clickPoint(mRect.right - 10, mRect.centerY());

            for (int j = 0; j < 20; j++) {
                testCase.pressKey(KeyEvent.KEYCODE_DEL);
                if (object.getText().equals("First name")) {
                    break;
                }
            }
        }
        commonModule.clickText("First name");
        testCase.enterText(firstName);
    }

    public void inputContactLastName(String lastName) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("First name")) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if(commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
            commonModule.scrollToBegin();
        }
        commonModule.clickText("Last name");
        testCase.enterText(lastName);
    }

    protected void enterAddContactView() throws UiObjectNotFoundException {
        startPhonebook();
        commonModule.clickDescription("New contact");
        if (commonModule.waitForText("Keep local", 2000)) {
            commonModule.clickText("Keep local");
        }
        commonModule.waitForResourceId("com.sonyericsson.android.socialphonebook:id/photo", 2000);
    }

    public void inputPhoneNumber(String number) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Phone")) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if(commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
            commonModule.scrollToBegin();
        }
        UiObject phone = new UiObject(new UiSelector().text("Phone").instance(1));
        phone.click();
        testCase.enterText(number);
    }

    public void inputEmail(String mail) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Email")) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if (commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
            commonModule.wait(1);
        }
        if (!commonModule.isTextExists("Email")) {
            commonModule.scrollToBegin();
            commonModule.scrollFindText("Email");
        }
        UiObject email = new UiObject(new UiSelector().text("Email").instance(1));
        email.click();
        testCase.enterText(mail);

        commonModule.pressKey(KeyEvent.KEYCODE_BACK);
    }

    protected void editContactLocalGroup(String group) throws UiObjectNotFoundException {
        if (!commonModule.isResourceId("com.sonyericsson.android.socialphonebook:id/group_list")) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if (commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
            commonModule.scrollFindId("group_list");
        }
        if (!commonModule.isTextExists(group)) {
            commonModule.clickText("Create new group");
            commonModule.wait(1);
            testCase.enterText(group);
            commonModule.clickText("OK");
        } else {
            commonModule.clickText(group);
        }
    }

    public void createContactWithTwoGroups(String name, String number, String email, String group1,
            String group2) throws UiObjectNotFoundException {
        enterAddContactView();

        if (name != null) {
            inputContactFirstName(name);
        }

        if (number != null) {
            inputPhoneNumber(number);
        }

        if (email != null) {
            inputEmail(email);
        }

        editContactLocalGroup(group1);
        editContactLocalGroup(group2);
        if (commonModule.isTextExists("Create new group")) {
            testCase.pressBackNTimes(1);// Make group list disappeared.
        }

        commonModule.clickText("Done");
        commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000);

        commonModule.backOutToHomeScreen();
    }

    protected void enterOneOfGroup(String group) throws UiObjectNotFoundException {
        startPhonebook();
        commonModule.clickDescription("Groups");
        commonModule.clickText(group);
    }
    public void checkGroupMember(String group, String ...members) throws UiObjectNotFoundException {
        enterOneOfGroup(group);
        for (String member : members) {
            if (!commonModule.isTextExists(member)) {
                commonModule.scrollToBegin();
                commonModule.scrollFindTextNotClick(member);
            }
            AcceptanceTestCase.assertTrue("There is no " + member + " in " + group,
                    commonModule.waitForText(member, 2000));
        }
    }

    public void sendGroupMessage(String group) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Send group message") || commonModule.isTextExists(group)) {
            enterOneOfGroup(group);
        }
        commonModule.clickText("Send group message");
        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_edit_text");
        testCase.enterText("Send Group message.");
        commonModule.clickResourceId("com.sonyericsson.conversations:id/conversation_send_button");
        commonModule.backOutToHomeScreen();
    }

    public void sendGroupEmail(String group) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("Send group email") || commonModule.isTextExists(group)) {
            enterOneOfGroup(group);
        }
        commonModule.clickText("Send group email");
        commonModule.clickText("Done");

        if (commonModule.waitForText("Complete action using", 2000)) {
            commonModule.clickText("Email");
            if (commonModule.isTextExists("Just once")) {
                commonModule.clickText("Just once");
            }
        }
        AcceptanceTestCase.assertTrue("Select email failed.",
                commonModule.waitForResourceId("com.android.email:id/send", 3000));

        commonModule.clickText("Subject");
        testCase.enterText("RT test");

        commonModule.clickResourceId("com.android.email:id/message_content");
        testCase.enterText("Send group email !");

        commonModule.clickResourceId("com.android.email:id/send");
        AcceptanceTestCase.assertTrue("Send email to contact failed.",
                commonModule.waitForText("Send group email", 2000));
    }

    public void checkContactGroupInfo(String contactName, String... groups)
            throws UiObjectNotFoundException {
        startPhonebook();
        commonModule.clickDescription("Contacts");
        commonModule.scrollFindText(contactName);
        for (String group : groups) {
            AcceptanceTestCase.assertTrue(contactName + " no " + group,
                    commonModule.waitForTextContains(group, 2000));
        }
    }

    protected String getNumber(String contact) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists(contact) || !commonModule.isDescriptionExists("Edit")) {
            startPhonebook();
            commonModule.clickText(contact);
            commonModule.wait(2);
        }

        if (commonModule.isDescriptionExists("Text message")) {
            UiObject myNum = new UiObject(new UiSelector().resourceId(
                    "com.sonyericsson.android.socialphonebook:id/data").instance(0));
            String myNumStr = myNum.getText();
            int len = myNumStr.length();
            String start = myNumStr.substring(0,1);
            String end = myNumStr.substring(len-1, len);
            String realNum = myNumStr.replace(start, "").replace(end, "");

            System.err.println(realNum.length()+"......................");
            return realNum;
        }
        return null;
    }

    protected String getEmail(String contact) throws UiObjectNotFoundException{
        if (!commonModule.isTextExists(contact) || !commonModule.isDescriptionExists("Edit")) {
            startPhonebook();
            commonModule.clickText(contact);
            commonModule.wait(2);
        }
        if (commonModule.isTextExists("Email")) {
            UiObject myEmailObj = new UiObject(new UiSelector().textContains("@"));
            String myEmailStr = myEmailObj.getText();
            return myEmailStr;
        }
        return null;
    }

    protected void editPhoneNumber(String oldNum, String newNum) throws UiObjectNotFoundException {
        commonModule.wait(2);
        if (!commonModule.isTextExists(oldNum)) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if (commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
        }
        commonModule.scrollFindText(oldNum);
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/delete_button");
        testCase.assertTextNotPresent(oldNum);

        commonModule.clickText(oldNum);
        commonModule.wait(5);
        testCase.enterText(newNum);
        testCase.assertTextPresent(newNum);
    }

    protected void editEmail(String oldEmail, String newEmail) throws UiObjectNotFoundException {
        if (!commonModule.isTextExists(oldEmail)) {
            testCase.pressBackNTimes(1);// Make keyboard disappear.
            if (commonModule.waitForText("Discard your changes?", 1000)) {
                commonModule.clickText("Cancel");
            }
        }
        commonModule.wait(1);
        commonModule.scrollFindText(oldEmail);
        commonModule.wait(2);

        // Clear email text.
        if(commonModule.isTextExists("First name") || commonModule.isTextExists("Name")){
            commonModule.emptyEditorByInstance(3);
        } else if(commonModule.isTextExists("Last name")){
            commonModule.emptyEditorByInstance(2);
        } else if(commonModule.isTextExists("Phone")||commonModule.isTextContains("134")){
            commonModule.emptyEditorByInstance(1);
        } else {
            commonModule.emptyEditorByInstance(0);
        }
        testCase.assertTextNotPresent(oldEmail);

        testCase.enterText(newEmail);
        testCase.assertTextPresent(newEmail);
    }

    public void editMyself(String newName, String newNumber, String newEmail, boolean isChangePic)
            throws UiObjectNotFoundException {
        String oldNum = getNumber("Myself");
        String oldEmail = getEmail("Myself");

        startPhonebook();
        commonModule.clickText("Myself");
        commonModule.clickDescription("Edit");
        commonModule.waitForText("Done", 3000);

        if (isChangePic) {
            takePictureForContactPicture();
        }

        if (newName != null) {
            inputContactFirstName(newName);
        }

        if (newNumber != null) {
            editPhoneNumber(oldNum, newNumber);
        }

        if (newEmail != null) {
            editEmail(oldEmail, newEmail);
        }

        commonModule.clickText("Done");
        commonModule.waitForDescription("Edit", 3000);
    }


    public void handlSameContactName(boolean linkContact,String contactName)
            throws UiObjectNotFoundException {
    		commonModule.wait(2);
       if(commonModule.isTextExists("Link contacts suggestion")){
    	   if(linkContact){
    		   	commonModule.clickResourceId(moduleData.get("Link_Contact_Icon"));
    		   	commonModule.clickText("Done");
    	   }else{
		 		   commonModule.clickText("Cancel");
		 		   commonModule.wait(2);

		 		   
		 		  checkContactExist(contactName, 1);
    	   }
       }else{
    	   AcceptanceTestCase.assertTrue("There are no same contact exist", false);
       }
    }
    
    public void editContactName(String oldContactName,String newcontactName)
            throws UiObjectNotFoundException {
        commonModule.scrollFindText(oldContactName);
        commonModule.clickDescription("Edit");
        commonModule.wait(2);
        testCase.clickItemWithText(oldContactName, 0);
        commonModule.emptyEditorByInstance(0);
        testCase.enterText(newcontactName);
        commonModule.wait(2);
        commonModule.clickText("Done");
    }
    
    	public void checkContactExist(String contactName, int instance) throws UiObjectNotFoundException{
			 		   if(commonModule.isResourceId(moduleData.get("Header_Name_Container"))){
			 			   commonModule.pressKey(KeyEvent.KEYCODE_BACK);
			 		   }
		 		   commonModule.scrollFindTextInstanceNotClick(contactName,instance);
		 		   AcceptanceTestCase.assertTrue("Same contacts save in phonebook", testCase.isViewWithTextPresent(contactName, instance));
		    	}
    	
    	public void deleteOneContact(String contactName) throws UiObjectNotFoundException{
    		commonModule.scrollFindTextNotClick(contactName);
    		commonModule.longClickTextContains(contactName);
    		commonModule.wait(2);
    		commonModule.clickDescription("Delete");
    		commonModule.clickText("Delete");
    		AcceptanceTestCase.assertTrue("Delete contact failed", commonModule.waitForTextGone(contactName,3000));
 	}
    	

    public void createContactFullField(String contactName) throws UiObjectNotFoundException {
        enterAddContactView();

        commonModule.clickText("First name");
        testCase.enterText(contactName);

        commonModule.clickText("Last name");
        testCase.enterText("Bbb");
        commonModule.hidKeyBoard();

        inputPhoneNumber("15699000");
        
        String[] otherPhone = {"Work Fax","Home Fax","Pager","Custom"};
        for(int i=0;i<8;i++){
            commonModule.clickText("Add new");
            testCase.enterText("1569900000"+i);
            commonModule.hidKeyBoard();

        }
        
        commonModule.scrollToBegin();
        for(int j=0;j<otherPhone.length;j++){
        	commonModule.scrollFindTextNotClick("Other");
            if(commonModule.isTextExists("Other")){
            	commonModule.clickText("Other");
            	commonModule.clickText(otherPhone[j]);
            	if(commonModule.isTextExists("Custom field name")){
            		commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/custom_dialog_content");
            		testCase.enterText("TEST");
            		commonModule.clickText("OK");
            	}
            }
        }
        
        commonModule.hidKeyBoard();
        
        commonModule.clickText("Add new");
        commonModule.hidKeyBoard();
        
        inputEmail("reliability@gmail.com");
        
        String[] otherEmail = {"Work Fax","Home Fax","Pager","Custom"};
        for(int i=0;i<4;i++){
            commonModule.clickText("Add new");
            testCase.enterText("reliability"+i+"gmail.com");
            commonModule.hidKeyBoard();

        }
            if(commonModule.isTextExists("Mobile")){
            	commonModule.clickText("Mobile");
            	commonModule.clickText("Custom");
            	if(commonModule.isTextExists("Custom field name")){
            		commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/custom_dialog_content");
            		testCase.enterText("TEST");
            		commonModule.clickText("OK");
            	}
            }
       
        commonModule.hidKeyBoard();
        
        commonModule.wait(2);
        
        commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/expansion_view");
            
        commonModule.clickText("Street");
        testCase.enterText("Chaoyang");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        
        commonModule.clickText("PO box");
        testCase.enterText("123");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        
        commonModule.clickText("Neighborhood");
        testCase.enterText("WangJing");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("City");
        testCase.enterText("Beijing");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("State");
        testCase.enterText("China");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);

        commonModule.clickText("Zip code");
        testCase.enterText("100000");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        
        commonModule.clickText("Country");
        testCase.enterText("China");
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
        
        
        for (int k=0;k<3;k++){
	            commonModule.clickText("Add new");
	            commonModule.clickText("Street");
	            testCase.enterText("Chaoyang"+k);
	            testCase.pressKey(KeyEvent.KEYCODE_BACK);
	
	            commonModule.clickText("City");
	            testCase.enterText("Beijing");
	            testCase.pressKey(KeyEvent.KEYCODE_BACK);
	
	            commonModule.clickText("State");
	            testCase.enterText("China");
	            testCase.pressKey(KeyEvent.KEYCODE_BACK);
	
	            commonModule.clickText("Zip code");
	            testCase.enterText("10000"+k);
	            testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
    if(commonModule.isTextExists("Other")){
        	commonModule.clickText("Other");
        	commonModule.clickText("Custom");
        	if(commonModule.isTextExists("Custom field name")){
        		commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/custom_dialog_content");
        		testCase.enterText("TEST");
        		commonModule.clickText("OK");
        	}
        }
    
    commonModule.hidKeyBoard();
    
    commonModule.wait(2);
    
    commonModule.clickText("Group name");
    commonModule.clickText("Colleagues");
    commonModule.clickText("Family");
    commonModule.clickText("Friends");
    commonModule.clickText("Create new group");
    commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/group_label");
    testCase.enterText("Test");
    commonModule.clickText("OK");
    commonModule.hidKeyBoard();
    
    commonModule.scrollFindText("Add another field");
    commonModule.clickText("Organization");
    
    commonModule.clickText("Company");
       testCase.enterText("Sony");
       commonModule.clickText("Job title");
       testCase.enterText("test");
       commonModule.hidKeyBoard();
       
       for(int x=0;x<2;x++){
		        commonModule.clickText("Add new");
		        commonModule.clickText("Company");
			       testCase.enterText("Sony");
			       commonModule.clickText("Job title");
			       testCase.enterText("test"+x);
			       commonModule.hidKeyBoard();
	       
	       }
       
        if(commonModule.isTextExists("Other")){
        	commonModule.clickText("Other");
        	commonModule.clickText("Custom");
        	if(commonModule.isTextExists("Custom field name")){
        		commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/custom_dialog_content");
        		testCase.enterText("TEST");
        		commonModule.clickText("OK");
        	}
        }
        
        commonModule.hidKeyBoard();
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("IM");
        
        commonModule.clickText("AIM");
        commonModule.clickText("Windows Live");
        commonModule.clickText("IM");
        testCase.enterText("reliability@hotmail.com");
        commonModule.hidKeyBoard();
        
        commonModule.clickText("Add new");
        commonModule.clickText("AIM");
        commonModule.clickText("QQ");
        commonModule.clickText("IM");
        testCase.enterText("123456789");
        commonModule.hidKeyBoard();
        
        commonModule.clickText("Add new");
        commonModule.clickText("AIM");
        commonModule.clickText("Custom");
		    	if(commonModule.isTextExists("Custom field name")){
		    		commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/custom_dialog_content");
		    		testCase.enterText("TEST");
		    		commonModule.clickText("OK");
		    	}
        commonModule.clickText("IM");
        testCase.enterText("123456789");
        commonModule.hidKeyBoard();
        
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("Notes");
        commonModule.clickTextByIntance("Notes", 1);
        testCase.enterText("this is for automation test!");
        commonModule.hidKeyBoard();
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("Nickname");
        commonModule.clickTextByIntance("Nickname", 1);
        testCase.enterText("Reliability");
        commonModule.hidKeyBoard();
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("Website");
        commonModule.clickTextByIntance("Website", 1);
        testCase.enterText("http://www.baidu.com");
        commonModule.hidKeyBoard();
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("Internet call");
        commonModule.clickTextByIntance("Internet call", 1);
        testCase.enterText("1203");
        commonModule.hidKeyBoard();
        
        commonModule.scrollFindText("Add another field");
        commonModule.clickText("Event");
        commonModule.clickText("Set");
        commonModule.hidKeyBoard();
        
        
        
        commonModule.scrollToBegin();
        takePictureForContactPicture();
        
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.clickText("Set ringtone");
        commonModule.wait(2);
        if(commonModule.isTextExists("Sound picker")){
        	commonModule.clickText("Sound picker");
        	if(commonModule.isTextExists("Just once")){
        		commonModule.clickText("Just once");
        	}
        }
        commonModule.clickText("Atria");
        commonModule.clickText("Done");
        
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.clickText("All calls to voicemail");
        

        commonModule.clickText("Done");
        AcceptanceTestCase.assertTrue("Create contact failed",commonModule.waitForResourceId(
                "com.sonyericsson.android.socialphonebook:id/static_header_container", 2000));

    }

    
    public void cancelAutoSyncData() throws UiObjectNotFoundException{
        startPhonebook();

        openPhonebooksetting("Settings");
        commonModule.clickText("Accounts & sync");
        
		    	if(testCase.isCheckboxChecked("checkbox", 0)){
				    			commonModule.clickText("Auto-sync data");

        }
        testCase.pressBackNTimes(2);
    }
    
    public void displayContacts(String account) throws UiObjectNotFoundException{
        startPhonebook();

        openPhonebooksetting("Filter");
        commonModule.scrollFindText(account);
        AcceptanceTestCase.assertTrue("Account is not exist", commonModule.isTextExists(account));

        if(commonModule.isTextExists("All contacts")){
        	  if(!testCase.isCheckboxChecked("checkbox")){
        		   commonModule.clickText("All contacts");
        	}
        	
        }else{
        	//Select group to show all contacts, maybe there are created group, so add cycle to scroll down
        	for(int j=0;j<5;j++){
        				commonModule.wait(2);
			        	for(int i=0;i<9;i++){
			          	  if(testCase.isViewWithIdPresent("checkbox", i)&&!testCase.isCheckboxChecked("checkbox",i)){
			          		  		testCase.clickItemWithId("checkbox",i);
			          		  		
			          		  		commonModule.wait(2);
			          	  }
			        	}
			        	
			        	//The last item is "All other contacts"
			        	if(!commonModule.isTextExists("All other contacts")){
			        		testCase.scrollDown();
			        	}else{
			        		break;
			        	}
			        	
        	}
        }
        testCase.pressBackNTimes(1);
    }
    
    public int getContactNumber() throws UiObjectNotFoundException{
    				commonModule.wait(2);
    				int allCount = 0;
    				if(commonModule.isTextExists("No contacts")){
    					return allCount;
    				}else{
    				commonModule.longClickResourceId("com.sonyericsson.android.socialphonebook:id/list_item");
    				
    				commonModule.clickResourceId("com.sonyericsson.android.socialphonebook:id/selection_menu");
    				
    				commonModule.clickText("Mark all");
    				
    				String selectCount = commonModule.getTextFromResourceId("com.sonyericsson.android.socialphonebook:id/selection_menu");
    				String[] contactCount = selectCount.split(" ");
    				
    				allCount = (int)(Integer.valueOf(contactCount[0]));
    				
    				return allCount;
    				}
    				
    				
    	}
    
    public void syncContact(String account) throws UiObjectNotFoundException{
    	
    				openPhonebooksetting("Settings");
    				commonModule.clickText("Accounts & sync");
    				commonModule.clickText(account);
    				commonModule.clickText("Sync Contacts");
    				AcceptanceTestCase.assertTrue("Sync contact failed", commonModule.waitForIdGone("com.android.settings:id/sync_active",120*1000));
    				
    				testCase.pressBackNTimes(2);
    				
				}
    
    public void assertSyncContactNumberCorrectly(int count) throws UiObjectNotFoundException{
    	
						int syncNumber = getContactNumber();
						AcceptanceTestCase.assertTrue("Sync contact number is wrong, please check Phonebook", syncNumber==count+1);
		
	}
    
    public void dismissContacts(String account) throws UiObjectNotFoundException{
            startPhonebook();

            openPhonebooksetting("Filter");
            commonModule.scrollFindText(account);
            AcceptanceTestCase.assertTrue("Account is not exist", commonModule.isTextExists(account));

            if(commonModule.isTextExists("All contacts")){
            	  if(testCase.isCheckboxChecked("checkbox")){
            		   commonModule.clickText("All contacts");
            	}
            	
            }else{
            	//Select group to show all contacts, maybe there are created group, so add cycle to scroll down
            	for(int j=0;j<5;j++){
            				commonModule.wait(2);
    			        	for(int i=0;i<9;i++){
    			          	  if(testCase.isViewWithIdPresent("checkbox", i)&&testCase.isCheckboxChecked("checkbox",i)){
    			          		  		testCase.clickItemWithId("checkbox",i);
    			          		  		
    			          		  		commonModule.wait(2);
    			          	  				}
    			        	}
    			        	
    			        	//The last item is "All other contacts"
    			        	if(!commonModule.isTextExists("All other contacts")){
    			        		testCase.scrollDown();
    			        	}else{
    			        		break;
    			        	}
    			        	
            	}
            }
            testCase.pressBackNTimes(1);
        }
    
    public void deleteContactsWithoutChecked() throws UiObjectNotFoundException {
        if (!commonModule.isTextExists("No contacts")) {
            commonModule.pressMoreOption();
            commonModule.waitForText("Mark several", 2000);

            commonModule.clickText("Mark several");
            commonModule.waitForText("0 selected", 2000);

            commonModule.clickText("0 selected");
            commonModule.waitForText("Mark all", 2000);

            commonModule.clickText("Mark all");
            commonModule.waitForDescription("Delete", 2000);

            commonModule.clickDescription("Delete");
            commonModule.waitForText("Delete", 2000);

            commonModule.clickText("Delete");
            commonModule.waitForText("Cannot delete following contacts:", 2000);

            commonModule.clickText("OK");
        }
    }
	
	}