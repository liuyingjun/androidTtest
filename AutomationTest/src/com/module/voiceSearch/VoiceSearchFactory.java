package com.module.voiceSearch;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class VoiceSearchFactory implements AbstractVoiceSearchFactory {

    public AcceptanceTestCase testCase;

    public VoiceSearchFactory(AcceptanceTestCase testCase) {
        this.testCase = testCase;
    }

    public IVoiceSearch create() throws PropertyNotFoundException {

        return new VoiceSearchModule(this.testCase);

    }
}
