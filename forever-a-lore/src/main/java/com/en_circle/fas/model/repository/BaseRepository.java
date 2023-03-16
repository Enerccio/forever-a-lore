package com.en_circle.fas.model.repository;

import com.en_circle.fas.model.entities.JPAEntity;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class BaseRepository<T extends JPAEntity> {

    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();
    protected abstract String getEntityName();


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T create() throws Exception {
        return getEntityClass().getDeclaredConstructor().newInstance();
    }

    public T save(T t) throws Exception {
        if (t.getId() == null) {
            if (t.getCreation() == null) {
                t.setCreation(new Date());
            }
            if (t.getUuid() == null) {
                t.setUuid(UUID.randomUUID().toString());
            }
            getEntityManager().persist(t);
            return t;
        }
        return getEntityManager().merge(t);
    }

    public T find(Long id) throws Exception {
        if (id == null)
            return null;

        T entity = getEntityManager().find(getEntityClass(), id);
        entity = (T) Hibernate.unproxy(entity);
        return entity;
    }

    public T find(String uuid) throws Exception {
        TypedQuery<Long> query = getEntityManager().createQuery("SELECT o.id FROM " + getEntityName() + " o WHERE " +
                "o.uuid = ?1", Long.class)
                .setParameter(1, uuid)
                .setMaxResults(1);

        List<Long> result = query.getResultList();
        Long id = result.isEmpty() ? null : result.get(0);
        return find(id);
    }

    public void delete(T entity) throws Exception {
        if (entity == null)
            return;

        entity = getEntityManager().contains(entity) ? entity : getEntityManager().find(getEntityClass(), entity.getId());
        getEntityManager().remove(entity);
    }

    public T detach(T entity) {
        if (entity == null)
            return null;

        getEntityManager().detach(entity);
        return entity;
    }

    public boolean exists(T entity) {
        if (entity.getId() == null) {
            return false;
        }
        Query q = getEntityManager().createQuery("SELECT o.id FROM " + getEntityName() + " o WHERE o.id = ?1")
                .setParameter(1, entity.getId());
        return !q.getResultList().isEmpty();
    }

    public void flush() {
        getEntityManager().flush();
    }
}
