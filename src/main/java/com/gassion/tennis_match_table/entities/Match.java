package com.gassion.tennis_match_table.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Matches")
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "Player1")
    private long player1;
    @Column(name = "Player2")
    private long player2;
    @Column(name = "Winner")
    private long winner;
}
