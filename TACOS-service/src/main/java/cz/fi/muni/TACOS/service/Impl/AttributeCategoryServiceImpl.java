package cz.fi.muni.TACOS.service.Impl;

import cz.fi.muni.TACOS.persistence.dao.AttributeCategoryDao;
import cz.fi.muni.TACOS.persistence.dao.Dao;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.service.AbstractEntityService;
import cz.fi.muni.TACOS.service.AttributeCategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class AttributeCategoryServiceImpl extends AbstractEntityService<AttributeCategory> implements AttributeCategoryService {

    private final AttributeCategoryDao attributeCategoryDao;

    @Inject
    public AttributeCategoryServiceImpl(AttributeCategoryDao attributeCategoryDao) {
        super(attributeCategoryDao);

        this.attributeCategoryDao = attributeCategoryDao;
    }

    @Override
    public void addAttribute(AttributeCategory category, Attribute attribute) {
        category.addAttribute(attribute);
    }

    @Override
    public void removeAttribute(AttributeCategory category, Attribute attribute) {
        category.removeAttribute(attribute);
    }
}
