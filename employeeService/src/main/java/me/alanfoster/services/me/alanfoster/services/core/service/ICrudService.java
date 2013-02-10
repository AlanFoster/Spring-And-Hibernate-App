package me.alanfoster.services.me.alanfoster.services.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * All services which are able to perform basic CURD (Cread, Read,
 * Update, Delete) operations should implement this interface.
 * <br />
 * If a service does not need to provide all of these CRUD operations,
 * then it is advised for the service to implement the IService interface
 *
 * @param <T> The entity's class type
 * @param <K> The key's class type used within the database
 *            Normally Long/Integer
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ICrudService<T, K extends Serializable> extends IService {
    /********************************************************************
     * Create Operations
     ********************************************************************/

    /**
     * Persist a new object.
     *
     * @param object The object to persist
     * @return The that was assigned to this object
     */
    K create(T object);


    /********************************************************************
     * Read Operations
     ********************************************************************/

    /**
     * Get an object based on the given key.
     *
     * @param key The key of the object
     * @return The object return the database, otherwise null
     */
    T get(K key);

    /**
     * Get all of the objects persisted.
     *
     * @return A list of type T, this will not be null
     */
    List<T> getAll();


    /********************************************************************
     * Update Operations
     ********************************************************************/

    /**
     * Update the data for this object.
     *
     * @param object The object to update
     */
    void update(T object);


    /********************************************************************
     * Delete Operations
     ********************************************************************/

    /**
     * Delete the given object.
     *
     * @param object The given object
     */
    void delete(T object);

    /**
     * Delete an entity by its given key.
     *
     * @param key The unique entity key
     */
    void delete(K key);
}
