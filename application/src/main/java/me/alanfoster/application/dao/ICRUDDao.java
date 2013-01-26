package me.alanfoster.application.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The base interface which all IDAOs should extend
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ICRUDDao<T, K extends Serializable> {
    /* Create Operations */
    public K create(T object);

    /* Read Operations */
    public T get(K key);
    public List<T> getAll();

    /* Update Operations */
    public T update(T object);

    /* Delete Operations */
    public void delete(T object);
    public void delete(K key);
}


