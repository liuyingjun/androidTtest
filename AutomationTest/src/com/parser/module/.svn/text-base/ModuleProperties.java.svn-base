/*********************************************************************
 *  ____                      _____      _                           *
 * / ___|  ___  _ __  _   _  | ____|_ __(_) ___ ___ ___  ___  _ __   *
 * \___ \ / _ \| '_ \| | | | |  _| | '__| |/ __/ __/ __|/ _ \| '_ \  *
 *  ___) | (_) | | | | |_| | | |___| |  | | (__\__ \__ \ (_) | | | | *
 * |____/ \___/|_| |_|\__, | |_____|_|  |_|\___|___/___/\___/|_| |_| *
 *                    |___/                                          *
 *                                                                   *
 *********************************************************************
 * Copyright 2010 Sony Ericsson Mobile Communications AB.            *
 * All rights, including trade secret rights, reserved.              *
 *********************************************************************/

/**
 * @file ModuleProperties.java
 *
 * @author Nima Davoudi-Kia (nima.davoudi-kia@sonyericsson.com)
 */

package com.parser.module;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;

public class ModuleProperties {

    private static final HashMap<String, String> DEFAULT_VALUES = new HashMap<String, String>();

    private static Properties props = null;

    public static final String PROPERTIES_FILEPATH = "/sdcard/AppConfig.xml";

    public static final String FILENAME = "AppConfig.xml";

    private static final File dataDirectory = Environment.getDataDirectory();

    static {
        setDefaultValues();
        tryToLoadPropertyFile();
    }

    public static String getAppName(String name) {
        String defaultValue = DEFAULT_VALUES.get(name);
        if (props != null) {
            return props.getProperty(name, defaultValue);
        } else {
            return defaultValue;
        }
    }

    private static void setDefaultValues() {
        int len = ModulePropertyConstants.PROP_NAMES.length;

        for (int i = 0; i < len; i++) {
            DEFAULT_VALUES.put(ModulePropertyConstants.PROP_NAMES[i],
                    ModulePropertyConstants.PROP_VALUES[i]);
        }
    }

    private static boolean tryToLoadPropertyFile() {
        if (props == null) {
            props = new Properties();
        }

        try {
            props.loadFromXML(new FileInputStream(PROPERTIES_FILEPATH));
        } catch (Exception ex) {
            props = null;
            return false;
        }

        return true;
    }

    public static void checkIfFileExistsInDataDirectory() throws FileNotFoundException {
        File fileToCheck = new File(dataDirectory, FILENAME);
        if (!fileToCheck.exists()) {
            throw new FileNotFoundException("NullPointerException is thrown since " + FILENAME
                    + " file does not exists in : /data");
        }

    }

}
