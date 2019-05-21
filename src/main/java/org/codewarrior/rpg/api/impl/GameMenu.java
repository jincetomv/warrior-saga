package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

class GameMenu extends Menu {
    private static final Logger LOGGER = Logger.getInstance(GameMenu.class);
    private static final Map<String, MenuOption> options = new LinkedHashMap<>();

    GameMenu() {


        super("Game Menu", options, "Enter the option corresponding to your choice");


    }

    static {

        options.put("w", MenuOption.UP);
        options.put("s", MenuOption.DOWN);
        options.put("a", MenuOption.LEFT);
        options.put("d", MenuOption.RIGHT);
        options.put("1", MenuOption.SAVE);
        options.put("2", MenuOption.QUIT);

    }
}
