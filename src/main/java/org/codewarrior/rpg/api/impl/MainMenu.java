package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.codewarrior.rpg.api.config.Messages.ENTER_NUMBER_CHOICE;

class MainMenu extends Menu {
    private static final Logger LOGGER = Logger.getInstance(MainMenu.class);
    private static final Map<String, MenuOption> options = new LinkedHashMap<>();

    MainMenu() {
        super("Main Menu", options, ENTER_NUMBER_CHOICE);

    }

    static {

        options.put("1", MenuOption.PLAY);
        options.put("2", MenuOption.RESUME);
        options.put("3", MenuOption.QUIT);

    }

}
