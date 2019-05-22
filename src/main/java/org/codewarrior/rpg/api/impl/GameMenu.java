package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.codewarrior.rpg.api.config.Messages.ENTER_OPTION_CHOICE;

class GameMenu extends Menu {
    private static final Logger LOGGER = Logger.getInstance(GameMenu.class);
    private static final Map<String, MenuOption> options = new LinkedHashMap<>();

    GameMenu() {


        super("Game Menu", options, ENTER_OPTION_CHOICE);


    }

    static {

        options.put("w", MenuOption.UP);
        options.put("s", MenuOption.DOWN);
        options.put("a", MenuOption.LEFT);
        options.put("d", MenuOption.RIGHT);
        options.put("4", MenuOption.SAVE);
        options.put("5", MenuOption.QUIT);

    }
}
