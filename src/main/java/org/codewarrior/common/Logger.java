package org.codewarrior.common;

import org.codewarrior.common.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public final class Logger {
    private static final Map<Class, Logger> loggers = new HashMap<>();
    private java.util.logging.Logger logger;

    private Logger(final Class clazz) {

        FileHandler handler = null;
        try {
            FileUtil.createDirectory("logs");
            handler = new FileHandler("logs/app.log", true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger = java.util.logging.Logger.getLogger(clazz.getName());

        if (handler != null) {
            logger.addHandler(handler);

        }
        logger.setUseParentHandlers(false);


    }

    public static Logger getInstance(final Class clazz) {
        Logger logger = loggers.get(clazz);
        if (logger == null) {
            logger = new Logger(clazz);
        }
        return logger;
    }

    public void info(final String message) {
        logger.info(message);
    }

    public void warning(final String message) {
        logger.warning(message);
    }

    public void error(final String message) {
        logger.log(Level.SEVERE, message);
    }


    public void error(final String message, Throwable t) {
        logger.log(Level.SEVERE, message, t);
    }

}
