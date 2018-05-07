package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class AttributeDaoImpl extends AbstractDao<Attribute> implements AttributeDao {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    public void delete(Attribute entity) {
        AttributeCategory category = entity.getAttributeCategory();
        if (category != null) {
            category.removeAttribute(entity);
        }

        Set<CreatedProduct> createdProductsCopy = new HashSet<>(entity.getCreatedProducts());

        for (CreatedProduct createdProduct : createdProductsCopy) {
            createdProduct.removeAttribute(entity);
        }
        super.delete(entity);
    }
}
