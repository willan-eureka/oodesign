package com.design.manager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic interface for access to database
 * @param <T> Object's type
 * @param <PK> Object's primary key
 */

public interface GenericManager<T, PK extends Serializable> {
    T save(T o);
    void update(T o);
    void delete(PK id);
    T get(PK id);
    List<T> list(Long page, Long size);
    List<T> search(Map<String, String> properties, String sort);
    Long size();
 }
