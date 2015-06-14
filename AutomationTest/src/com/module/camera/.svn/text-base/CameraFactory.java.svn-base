package com.module.camera;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;


public class CameraFactory implements AbstractCameraFactory{
    private static final String PACKAGE = "CAMERA_PACKAGE";

    public AcceptanceTestCase testCase;

    public CameraFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    public ICamera create(){
        return new CameraModule(this.testCase);
    }

}
