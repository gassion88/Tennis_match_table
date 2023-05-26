package com.gassion.tennis_match_table.entities.MatchModel;

import com.gassion.tennis_match_table.entities.team.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
public class MatchModel {
    private UUID matchUUID;
    private Team teamOne;
    private Team teamTwo;
    private List<MatchSet> sets = new ArrayList<>();
    private int setsCountInGame;
    private boolean taiBreak;
    private MatchState state;

    public MatchModel(UUID matchUUID, Team teamOne, Team teamTwo, int setsCountInGame, boolean taiBreak) {
        this.matchUUID = matchUUID;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.setsCountInGame = setsCountInGame;
        this.taiBreak = taiBreak;
        this.state = MatchState.ONGOING;
        this.sets.add(new MatchSet());
    }

    public void addSetToMatch() {
        MatchSet newSet = new MatchSet();

        sets.add(newSet);
    }

    public MatchGame getCurrentGame() {
        MatchSet currentSet = getCurrentSet();

        for (MatchGame matchGame : currentSet.getGames()) {

            if (matchGame.isOngoing()) {
                return matchGame;
            }
        }

        return currentSet.getGames().get(currentSet.getGames().size()-1);
    }

    public MatchSet getCurrentSet() {
        for (MatchSet set : sets) {

            if (set.isOngoing()) {
                return set;
            }
        }

        return sets.get(sets.size()-1);
    }

    public int getTeamWinsSetsCountToMatch(MatchState team) {
        int playerWonSets = 0;

        for (MatchSet set : sets) {
            MatchState state = set.getState();

            if (state == team) {
                playerWonSets++;
            }
        }

        return playerWonSets;
    }

    public int getTeamWinGamesCountToSet(MatchState team, MatchSet set) {
        int playerWinGames = 0;

            for (MatchGame game :  set.getGames()){
                MatchState state = game.getState();

                if (state == team) {
                    playerWinGames++;
                }
            }

        return playerWinGames;
    }

    public int getPlayerWinScoresToGame(String playerName, MatchGame game) {
        int playerWonScores = 0;

        for (MatchScore score :  game.getScores()){
            String scorePlayerName = score.getPlayerName();

            if (Objects.equals(scorePlayerName, playerName)) {
                playerWonScores++;
            }
        }

        return playerWonScores;
    }

    public boolean isOngoing() {
        return state == MatchState.ONGOING;
    }

    public Team getWinner() {
        if (state == MatchState.TEAM_ONE_WIN){
            return teamOne;
        } else if (state == MatchState.TEAM_TWO_WIN) {
            return teamTwo;
        } else {
            return null;
        }
    }
}
