package com.gassion.tennis_match_table.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Players")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "Name")
    private String name;
}
