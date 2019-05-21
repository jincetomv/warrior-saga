package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.ApplicationApi;
import org.codewarrior.rpg.api.FightApi;
import org.codewarrior.rpg.domain.services.FightService;
import org.codewarrior.rpg.domain.values.Coordinate;

public class FightApiImpl implements FightApi {
    private final DisplayMenuHelper displayMenuHelper;
    private final FightMenu fightMenu;
    private final ApplicationApi applicationApi;


    FightApiImpl(final DisplayMenuHelper displayMenuHelper, final FightMenu fightMenu, final ApplicationApi applicationApi) {
        this.displayMenuHelper = Assert.notNull(displayMenuHelper, "displayMenuHelper");
        this.fightMenu = Assert.notNull(fightMenu, "fightMenu");
        this.applicationApi = Assert.notNull(applicationApi, "applicationApi");
    }

    @Override

    public void showFightMenu(Coordinate enemyCoordinate) {
        FightService fightService = applicationApi.getCurrentGameContext().getFightService();
        switch (displayMenuHelper.show(fightMenu)) {
            case ATTACK:
                fightService.attack(enemyCoordinate);
                break;
            case DEFEND:
                fightService.defend(enemyCoordinate);
                break;
            case STEP_BACK:
                fightService.stepBack();
                break;
            default:
                //This should never be executed
                break;
        }

    }


}
