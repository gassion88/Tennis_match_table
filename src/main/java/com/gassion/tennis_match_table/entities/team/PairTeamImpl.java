package com.gassion.tennis_match_table.entities.team;

import com.gassion.tennis_match_table.entities.Player;

public class PairTeamImpl implements Team {
    private final TeamMode teamMode;
    private final Player playerOne;
    private final Player playerTwo;

    public PairTeamImpl(Player playerOne, Player playerTwo) {
        this.teamMode = TeamMode.PAIR;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public boolean isPlayerExist(String name) {
        return playerOne.getName().equals(name) || playerTwo.getName().equals(name);
    }
}
