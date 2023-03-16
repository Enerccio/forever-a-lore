package com.en_circle.fas.model.repository;

import com.en_circle.fas.model.entities.Game;

public class GameRepository extends BaseRepository<Game> {


    @Override
    protected Class<Game> getEntityClass() {
        return Game.class;
    }

    @Override
    protected String getEntityName() {
        return Game.ENTITY_NAME;
    }

}
