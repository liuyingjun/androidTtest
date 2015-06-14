package com.module.voiceSearch;

import com.parents.Factory;
import com.parser.module.PropertyNotFoundException;

public interface AbstractVoiceSearchFactory extends Factory{

    public IVoiceSearch create() throws PropertyNotFoundException;

}
