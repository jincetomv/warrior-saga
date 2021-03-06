package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.codewarrior.rpg.api.config.Messages.ENTER_NUMBER_CHOICE;
import static org.codewarrior.rpg.api.config.Messages.WHAT_TO_DO;

class FightMenu extends Menu {
    private static final Logger LOGGER = Logger.getInstance(GameMenu.class);
    private static final Map<String, MenuOption> options = new LinkedHashMap<>();

    FightMenu() {
        super(WHAT_TO_DO, options, ENTER_NUMBER_CHOICE);

    }

    static {

        options.put("1", MenuOption.ATTACK);
        options.put("2", MenuOption.DEFEND);
        options.put("3", MenuOption.STEP_BACK);

    }


}
