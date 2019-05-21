package org.codewarrior.rpg.api.impl;

public enum MenuOption {
    PLAY("Play"), PLAY_AGAIN("Play again"), SAVE("Save"), RESUME("Resume"), QUIT("Quit"), CONTINUE("Continue"), UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right"), ATTACK("Attack"), DEFEND("Defend"), STEP_BACK("Step back");

    public String getDisplayName() {
        return displayName;
    }

    private final String displayName;

    MenuOption(final String displayName) {
        this.displayName = displayName;
    }
}
