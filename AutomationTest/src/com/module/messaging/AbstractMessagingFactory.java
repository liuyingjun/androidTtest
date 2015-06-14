package com.module.messaging;

import com.parents.Factory;

public interface AbstractMessagingFactory extends Factory {

    public IMessaging create();
}
