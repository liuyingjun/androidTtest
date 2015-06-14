package com.module.browser;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.module.contacts.ContactsModule;
import com.module.contacts.IContacts;
import com.parser.module.PropertyNotFoundException;

public class BrowserFactory{

    public static IBrowser create(AcceptanceTestCase testCase) throws PropertyNotFoundException{

        return new BrowserModule(testCase);

    }
}
