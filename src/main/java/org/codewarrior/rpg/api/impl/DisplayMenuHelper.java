package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.Menu;
import org.codewarrior.rpg.ports.Adapter;

public class DisplayMenuHelper {
    private final Adapter adapter;

    public DisplayMenuHelper(Adapter adapter) {
        this.adapter = Assert.notNull(adapter, "Adapter cannot be null");
    }


    MenuOption show(final Menu menu) {
        String input = adapter.printMessageAndGetInput(menu.toString());
        final MenuOption menuOption = menu.validate(input);
        if (menuOption != null) {
            return menuOption;

        } else

        {
            adapter.showMessage("Incorrect/Invalid option chosen, Try again");
            return show(menu);
        }
    }
}
