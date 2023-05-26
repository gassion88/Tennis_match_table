package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.team.Team;
import com.gassion.tennis_match_table.entities.team.TeamMode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewMatchConfigurationDTO {
    private List<Team> teams;
    private int setCount;
    private boolean taiBreak;
    private TeamMode teamMode;
}
