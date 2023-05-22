package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewMatchRequestDTO {
    private List<Player> players;
    private int setCount;
}
