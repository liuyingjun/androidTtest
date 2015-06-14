package com.module.fileCommander;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class FileCommanderFactory implements AbstractFileCommanderFactory{

    public AcceptanceTestCase testCase;

    public FileCommanderFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    @Override
    public IFileCommander create() {
        return new FileCommander(testCase);
    }

}
