package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCategoryDTO;

/**
 * Facade Interface for AttributeCategory entity.
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public interface AttributeCategoryFacade extends Facade<AttributeCategoryDTO> {

    Long create(AttributeCategoryCreateDTO entity);
    /**
     * Adds attribute to category
     *
     * @param categoryId
     * @param attributeId
     */
    void addAttribute(Long categoryId, Long attributeId);

    /**
     * Removes attribute from category
     *
     * @param categoryId
     * @param attributeId
     */
    void removeAttribute(Long categoryId, Long attributeId);
}
