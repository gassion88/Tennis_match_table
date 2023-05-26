package com.gassion.tennis_match_table.entities.team;

import com.gassion.tennis_match_table.entities.Player;

public class SingleTeamImpl implements Team {
    private final TeamMode teamMode;
    private final Player player;

    public SingleTeamImpl(Player player) {
        this.teamMode = TeamMode.SINGLE;
        this.player = player;
    }


    @Override
    public boolean isPlayerExist(String name) {
        return player.getName().equals(name);
    }
}
