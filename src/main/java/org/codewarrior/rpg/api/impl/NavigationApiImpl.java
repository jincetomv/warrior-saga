package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.ApplicationApi;
import org.codewarrior.rpg.api.GameContext;
import org.codewarrior.rpg.api.NavigationApi;
import org.codewarrior.rpg.ports.Adapter;

public class NavigationApiImpl implements NavigationApi {
    private final ApplicationApi applicationApi;
    private final Adapter adapter;

    NavigationApiImpl(ApplicationApi applicationApi, Adapter adapter) {
        this.applicationApi = Assert.notNull(applicationApi, "applicationApi");
        this.adapter = Assert.notNull(adapter, "adapter");
    }


    @Override
    public void showEnemyWarning() {
        adapter.showMessage("Ooh !! You have met the following enemy..");
    }

    @Override
    public void up() {
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        currentGameContext.getNavigationService().up();
    }

    @Override
    public void down() {
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        currentGameContext.getNavigationService().down();
    }

    @Override
    public void left() {
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        currentGameContext.getNavigationService().left();
    }

    @Override
    public void right() {
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        currentGameContext.getNavigationService().right();
    }
}
