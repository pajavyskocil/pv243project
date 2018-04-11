package cz.fi.muni.TACOS.service;


import java.util.List;

/**
 * Service interface for managing Entities
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface Service<T> {

    /**
     * Creates T entity
     *
     * @param entity Entity to be created
     */
    void create(T entity);

    /**
     * Deletes T entity
     * @param entity Entity to be deleted
     */
    void delete(T entity);

    /**
     * Finds T entity by given id
     *
     * @param id Id to be used for search
     * @return Entity with given Id or null
     */
    T findById(Long id);

    /**
     * Find all T entities
     *
     * @return List of all T entities
     */
    List<T> getAll();
}
