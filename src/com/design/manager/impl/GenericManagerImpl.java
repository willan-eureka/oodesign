package com.design.manager.impl;

import com.design.orm.FileManager;
import com.design.orm.csv.CSVManager;
import com.design.manager.GenericManager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic implementation for access to database with commons methods for all entities
 * @param <T> Object's type
 * @param <PK> Object's primary key
 */

public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

    protected FileManager<T, PK> fileManager;

    public GenericManagerImpl(final Class<T> persistenceClass) {
        this.fileManager = new CSVManager<T, PK>(persistenceClass);
    }

    @Override
    public T save(T o) {
        return fileManager.save(o);
    }

    @Override
    public void update(T o) {
        fileManager.update(o);
    }

    @Override
    public T get(PK id) {
        return fileManager.get(id);
    }

    @Override
    public void delete(PK id) {
        fileManager.delete(id);
    }

    @Override
    public List<T> list(Long page, Long size) {
        return fileManager.list(page, size);
    }

    @Override
    public List<T> search(Map<String, String> properties, String sort) {
        return fileManager.search(properties, sort);
    }

    @Override
    public Long size() {
        return fileManager.count();
    }
}
