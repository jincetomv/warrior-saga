package org.codewarrior.rpg.api;

import org.codewarrior.common.Assert;
import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.impl.MenuOption;

import java.util.Map;

public abstract class Menu {
    private static final Logger LOGGER = Logger.getInstance(Menu.class);


    private final Map<String, MenuOption> options;

    private final String name;

    private final String message;


    protected Menu(final String menuName, final Map<String, MenuOption> options, final String message) {

        name = Assert.notNull(menuName, "Menu name cannot be null");
        this.options = Assert.notNull(options, "Options cannot be null");
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append(name);
        menuBuilder.append("\n --------------");

        options.entrySet().forEach(entry -> {
            menuBuilder.append("\n");
            menuBuilder.append(entry.getKey());
            menuBuilder.append(".");
            menuBuilder.append(entry.getValue().getDisplayName());
        });

        if (message != null) {
            menuBuilder.append("\n --------------");
            menuBuilder.append("\n");
            menuBuilder.append(message);
        }
        return menuBuilder.toString();
    }

    public MenuOption validate(final String option) {


        return options.get(option);

    }


}

