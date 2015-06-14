package com.runner;

import com.android.uiautomator.testrunner.UiAutomatorInstrumentationTestRunner;
import com.parser.cases.TestDataExtract;
import com.precondition.PreconditionTest;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.test.reliability.concurrentTestCase.ReliabilityAutomaticTestII;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import android.test.suitebuilder.TestMethod;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestSuite;

public class ReliabilityConcurrentRunner extends UiAutomatorInstrumentationTestRunner {
    private static final String TAG = "Reliability_TEST";

    private List<String> loopsList = null;
    private List<String> methodNameList = null;

    @Override
    public TestSuite getAllTests() {
        TestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(PreconditionTest.class);

        // load networktestconfig.xml file
        boolean isSuccess = TestDataExtract.loadConfigFile(this, "ReliabilityConfigConcurent.xml");
        if (!isSuccess) {
            return suite;
        }
        ReliabilityAutomaticTestII.hasLoadConfigFile = true;

        // get the loops of each testcase that to be run.
        loopsList = TestDataExtract.getRunCasesLoopsList();
        int casesSize = loopsList.size();

        Log.d(TAG, "casesSize is " + casesSize);
        methodNameList = constructMethodsName(casesSize);

        try {
            for (int j = 0; j < Integer.parseInt(TestDataExtract.totalLoops); j++) {
                for (int k = 0; k < casesSize; k++) {
                    int times = Integer.parseInt((String)loopsList.get(k));
                    ReliabilityAutomaticTestII.sRunLoops = times;
                    Log.d(TAG, "The current case run " + times + " times");
                    String method = methodNameList.get(k);

                    for (int n = 0; n < times; n++) {
                        suite.addTest((new TestMethod(method, ReliabilityAutomaticTestII.class)).createTest());
                    }
                }
            }

        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return suite;

    }

    @Override
    public ClassLoader getLoader() {
        Log.d(TAG, "getLoader = ");
        return AcceptanceTestCase.class.getClassLoader();

    }

    private List<String> constructMethodsName(int count) {
        List<String> methodList = new ArrayList<String>();
        int num = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    String method = "test_" + i + j + k;
                    methodList.add(method);
                    num++;
                    if (num >= count)
                        break;
                }
                if (num >= count)
                    break;
            }
            if (num >= count)
                break;
        }
        return methodList;
    }

}
