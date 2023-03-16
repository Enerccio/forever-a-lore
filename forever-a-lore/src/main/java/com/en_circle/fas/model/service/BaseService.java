package com.en_circle.fas.model.service;

import com.en_circle.fas.model.entities.JPAEntity;
import com.en_circle.fas.model.repository.BaseRepository;
import com.en_circle.fas.model.tx.CommonTx;
import com.en_circle.fas.model.tx.CommonTxReadOnly;
import com.en_circle.fas.model.tx.NoTx;

public class BaseService<T extends JPAEntity, R extends BaseRepository<T>> {

    private R repository;

    @CommonTx
    public T save(T e) throws Exception {
        return getRepository().save(e);
    }

    @NoTx
    public T create() throws Exception {
        return getRepository().create();
    }

    @CommonTxReadOnly
    public T find(Long id) throws Exception {
        return getRepository().find(id);
    }

    @CommonTxReadOnly
    public T find(String uuid) throws Exception {
        return getRepository().find(uuid);
    }

    public R getRepository() {
        return repository;
    }

    public void setRepository(R repository) {
        this.repository = repository;
    }
}
