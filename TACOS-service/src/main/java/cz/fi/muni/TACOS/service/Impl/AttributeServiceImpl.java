package cz.fi.muni.TACOS.service.Impl;

import cz.fi.muni.TACOS.persistence.dao.AttributeDao;
import cz.fi.muni.TACOS.persistence.dao.Dao;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.service.AbstractEntityService;
import cz.fi.muni.TACOS.service.AttributeService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class AttributeServiceImpl extends AbstractEntityService<Attribute> implements AttributeService {

    private final AttributeDao attributeDao;

    @Inject
    public AttributeServiceImpl(AttributeDao attributeDao) {
        super(attributeDao);

        this.attributeDao = attributeDao;
    }
}
