package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface AttributeCategoryService extends EntityService<AttributeCategory> {

    /**
     * Adds attribute to category
     *
     * @param category category
     * @param attribute attribute
     */
    void addAttribute(AttributeCategory category, Attribute attribute);

    /**
     * Removes attribute from category
     *
     * @param category category
     * @param attribute attribute
     */
    void removeAttribute(AttributeCategory category, Attribute attribute);
}
