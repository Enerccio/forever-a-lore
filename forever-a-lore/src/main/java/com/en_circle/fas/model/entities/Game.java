package com.en_circle.fas.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.en_circle.fas.model.entities.Game.ENTITY_NAME;

@Entity(name = ENTITY_NAME)
@Table(name = "game")
public class Game extends JPAEntity {
    public static final String ENTITY_NAME = "Game";


}
