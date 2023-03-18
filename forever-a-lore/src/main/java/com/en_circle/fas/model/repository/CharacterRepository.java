package com.en_circle.fas.model.repository;

import com.en_circle.fas.model.entities.Character;
import com.en_circle.fas.ui.utils.DBUtils;

import java.util.List;

public class CharacterRepository extends BaseRepository<Character> {

    @Override
    protected Class<Character> getEntityClass() {
        return Character.class;
    }

    @Override
    protected String getEntityName() {
        return Character.ENTITY_NAME;
    }

    public List<Long> getAll() {
        return getEntityManager().createQuery("SELECT o.id FROM Character o " +
                        "ORDER BY o.preferred DESC, o.nameAscii", Long.class)
                .getResultList();
    }

    public List<Long> getFiltered(String text) {
        return getEntityManager().createQuery("SELECT o.id FROM Character o " +
                        "WHERE o.name LIKE ?1 " +
                        "ORDER BY o.preferred DESC, o.nameAscii", Long.class)
                .setParameter(1, "%" + DBUtils.escapeLike(text) + "%")
                .getResultList();
    }
}
