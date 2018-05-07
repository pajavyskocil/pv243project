package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class AttributeCategoryDaoImpl extends AbstractDao<AttributeCategory> implements AttributeCategoryDao {

    @Override
    public void delete(AttributeCategory entity) {

		Set<Template> templatesCopy = new HashSet<>(entity.getTemplates());

        for (Template template : templatesCopy) {
            template.removeAttributeCategory(entity);
        }
		Set<Attribute> attributesCopy = new HashSet<>(entity.getAttributes());

        for (Attribute attribute: attributesCopy){
            entity.removeAttribute(attribute);
        }

        super.delete(entity);
    }
}
