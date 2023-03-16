package com.en_circle.fas.model.repository;

import com.en_circle.fas.model.entities.Settings;

import javax.persistence.TypedQuery;
import java.util.List;

public class SettingsRepository extends BaseRepository<Settings> {

    @Override
    protected Class<Settings> getEntityClass() {
        return Settings.class;
    }

    @Override
    protected String getEntityName() {
        return Settings.ENTITY_NAME;
    }

    public Long findGlobalSettingId() {
        TypedQuery<Long> query = getEntityManager().createQuery("SELECT o.id FROM Settings o " +
                        "WHERE o.global = ?1", Long.class)
                .setParameter(1, true)
                .setMaxResults(1);

        List<Long> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
