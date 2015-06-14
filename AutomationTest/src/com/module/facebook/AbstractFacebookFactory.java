package com.module.facebook;

import com.parents.Factory;

public interface AbstractFacebookFactory extends Factory{
    public IFacebook create();
}
