package com.module.contacts;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class ContactsFactory {

    public static IContacts getContactsModule(AcceptanceTestCase testCase)
            throws PropertyNotFoundException {
        return new ContactsModule(testCase);
    }
}
