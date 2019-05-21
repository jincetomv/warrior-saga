package org.codewarrior;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.ApplicationApi;
import org.codewarrior.rpg.api.impl.AppContext;


public class Main {
    private static final Logger LOGGER = Logger.getInstance(Main.class);

    public static void main(String[] args) {
        AppContext appContext = new AppContext();
        appContext.getBean(ApplicationApi.class).run();
    }
}
