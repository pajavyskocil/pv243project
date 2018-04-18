package cz.fi.muni.TACOS.facade;

import java.util.List;

/**
 * Facade interface for T Entities
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 *
 * @param <T1> Class of the DTO entity
 */
public interface Facade<T1> {

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
