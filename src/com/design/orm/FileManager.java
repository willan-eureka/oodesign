package com.design.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This
 * @param <T> Entity type to manage
 * @param <PK> Entity's primary key type
 */

public interface FileManager<T, PK extends Serializable> {
    T save(T o);
    void update(T o);
    void delete(PK id);
    T get(PK id);
    List<T> list(Long page, Long id);
    List<T> search(Map<String, String> properties, String sort);
    long count();
}
