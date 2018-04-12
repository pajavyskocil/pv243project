package cz.fi.muni.TACOS.service.Impl;

import cz.fi.muni.TACOS.persistence.dao.Dao;
import cz.fi.muni.TACOS.persistence.dao.ProductDao;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AbstractEntityService;
import cz.fi.muni.TACOS.service.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class ProductServiceImpl extends AbstractEntityService<Product> implements ProductService {

    private final ProductDao productDao;

    @Inject
    public ProductServiceImpl(ProductDao productDao) {
        super(productDao);

        this.productDao = productDao;
    }

    @Override
    public void addTemplate(Product product, Template template) {
        product.addTemplate(template);
    }

    @Override
    public void removeTemplate(Product product, Template template) {
        product.removeTemplate(template);
    }
}
