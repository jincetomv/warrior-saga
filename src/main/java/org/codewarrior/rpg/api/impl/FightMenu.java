package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

class FightMenu extends Menu {
    private static final Logger LOGGER = Logger.getInstance(GameMenu.class);
    private static final Map<String, MenuOption> options = new LinkedHashMap<>();

    FightMenu() {
        super("What do you want to do?", options, "Enter the number corresponding to your choice");

    }

    static {

        options.put("1", MenuOption.ATTACK);
        options.put("2", MenuOption.DEFEND);
        options.put("3", MenuOption.STEP_BACK);

    }


}
