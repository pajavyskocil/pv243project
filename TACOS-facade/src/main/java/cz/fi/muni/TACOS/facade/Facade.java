package cz.fi.muni.TACOS.facade;

import java.util.List;

/**
 * Facade interface for T Entities
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 *
 * @param <T1> Class of the DTO entity
 * @param <T2> Class of the CreateDTO entity
 */
public interface Facade<T1, T2> {

    /**
     * Create given T Entity
     *
     * @param entity Entity
     * @return Long Id of created entity
     */
    Long create(T2 entity);

    /**
     * Delete given T entity
     *
     * @param id Entity id
     */
    void delete(Long id);

    /**
     * Find T entity by Id
     *
     * @param id Id by which will be Entity found
     * @return T entity or null
     */
    T1 findById(Long id);

    /**
     * Get all T entities
     *
     * @return List of T entities or empty list
     */
    List<T1> getAll();
}
