package com.thepoptartcrpr.dawn.utils;

import com.thepoptartcrpr.dawn.Dawn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

    private static Logger console;

    public static Logger getConsole() {
        if (console == null) console = LogManager.getFormatterLogger(Dawn.Reference.MODID);
        return console;
    }

    public static void log(String str) {
        getConsole().info(str);
    }

}
