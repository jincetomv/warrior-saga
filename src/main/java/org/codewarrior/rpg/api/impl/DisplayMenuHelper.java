package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.Menu;
import org.codewarrior.rpg.ports.Adapter;

import static org.codewarrior.rpg.api.config.Messages.INCORRECT_OPTION;

class DisplayMenuHelper {
    private final Adapter adapter;

    DisplayMenuHelper(Adapter adapter) {
        this.adapter = Assert.notNull(adapter, "Adapter cannot be null");
    }


    MenuOption show(final Menu menu) {
        String input = adapter.printMessageAndGetInput(menu.toString());
        final MenuOption menuOption = menu.validate(input);
        if (menuOption != null) {
            return menuOption;

        } else

        {
            adapter.showMessage(INCORRECT_OPTION);
            return show(menu);
        }
    }
}
