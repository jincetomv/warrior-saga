package org.codewarrior.rpg.api.impl;

import org.codewarrior.rpg.api.*;
import org.codewarrior.rpg.api.exception.BeanNotFoundException;
import org.codewarrior.rpg.ports.Adapter;
import org.codewarrior.rpg.ports.util.Reader;
import org.codewarrior.rpg.ports.util.Writer;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private Map<Class, Object> beans = new HashMap<>();

    public AppContext() {

        final Reader reader = new Reader(System.in);
        final Writer writer = new Writer(System.out);
        final Adapter adapter = new Adapter(reader, writer);
        beans.put(Adapter.class, adapter);

        ApplicationApi applicationApi = new ApplicationApiImpl(this, adapter);
        beans.put(ApplicationApi.class, applicationApi);


        MainMenu mainMenu = new MainMenu();
        beans.put(MainMenu.class, mainMenu);

        FightMenu fightMenu = new FightMenu();
        beans.put(FightMenu.class, fightMenu);

        GameMenu gameMenu = new GameMenu();
        beans.put(GameMenu.class, gameMenu);

        NewGameMenu newGameMenu = new NewGameMenu();
        beans.put(NewGameMenu.class, newGameMenu);

        DisplayMenuHelper displayMenuHelper = new DisplayMenuHelper(adapter);
        beans.put(DisplayMenuHelper.class, displayMenuHelper);

        NavigationApi navigationApi = new NavigationApiImpl(applicationApi, adapter);
        beans.put(NavigationApi.class, navigationApi);


        CharacterApi characterApi = new CharacterApiImpl(adapter);
        beans.put(CharacterApi.class, characterApi);


        FightApi fightApi = new FightApiImpl(displayMenuHelper, fightMenu, applicationApi);
        beans.put(FightApi.class, fightApi);

        GameApi gameApi = new GameApiImpl(adapter, applicationApi, displayMenuHelper, gameMenu, navigationApi);
        beans.put(GameApi.class, gameApi);

        MenuApi menuApi = new MenuApiImpl(applicationApi, gameApi, mainMenu, newGameMenu, displayMenuHelper);
        beans.put(MenuApi.class, menuApi);


    }

    void clear() {
        beans = null;

    }

    public <T> T getBean(final Class<T> clazz) {
        T bean = (T) beans.get(clazz);


        if (bean == null) {
            throw new BeanNotFoundException(String.format("No beans registered for class %s ", clazz.getName()));
        }

        return bean;
    }
}
