package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.Template;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class TemplateDaoImpl extends AbstractDao<Template> implements TemplateDao {

    @Override
    public void delete(Template entity) {

        Set<Product> productsCopy = new HashSet<>(entity.getProducts());

        for (Product product : productsCopy) {
            product.removeTemplate(entity);
        }

        super.delete(entity);
    }
}
