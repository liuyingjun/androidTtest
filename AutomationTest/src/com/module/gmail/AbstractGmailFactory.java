package com.module.gmail;

import com.parents.Factory;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public interface AbstractGmailFactory extends Factory{

    public IGmail create();
}
