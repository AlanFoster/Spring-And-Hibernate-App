package me.alanfoster.services.me.alanfoster.services.core.service;

import java.io.Serializable;
import java.util.List;


/**
 * All services which are able to perform basic CURD (Cread, Read, Update, Delete) operations should implement this interface.
 * If a service does not need to provide all of these CRUD operations, then it is advised for the service to implement the IService interface
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */

public interface ICrudService<T, K extends Serializable> extends IService {
    /* Create Operations */
    K create(T object);

    /* Read Operations */
    T get(K key);

    List<T> getAll();

    /* Update Operations */
    void update(T object);

    /* Delete Operations */
    void delete(T object);

    void delete(K key);
}
