package com.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.concurrent.scenario.IScenario;

public class PrePostInvocationHandler implements InvocationHandler{

    private Object obj;
    private IScenario steps;

    public PrePostInvocationHandler(Object obj, IScenario steps){
        this.obj = obj;
        this.steps = steps;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	
    	steps.doBegin();

        method.invoke(obj, args);

        steps.doAfter();

        return null;
    }

}
