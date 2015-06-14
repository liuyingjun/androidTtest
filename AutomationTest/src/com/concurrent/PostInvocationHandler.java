package com.concurrent;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.concurrent.scenario.IScenario;

public class PostInvocationHandler implements InvocationHandler{

    private Object obj;
    private IScenario steps;

    public PostInvocationHandler(Object obj, IScenario steps){
        this.obj = obj;
        this.steps = steps;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(obj, args);

        steps.doBegin();
        steps.doAfter();

        return null;
    }

}
