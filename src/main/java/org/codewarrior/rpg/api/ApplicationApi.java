package org.codewarrior.rpg.api;

public interface ApplicationApi {
    public void run();

    public void shutdown();

    public void createNewGameContext();

    public GameContext getCurrentGameContext();

    public void clearCurrentGameContext();


}
