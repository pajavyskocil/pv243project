package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.Template;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface ProductService extends EntityService<Product> {

    /**
     * Adds template to product
     *
     * @param product product
     * @param template template
     */
    void addTemplate(Product product, Template template);

    /**
     * Removes template from product
     * @param product product
     * @param template template
     */
    void removeTemplate(Product product, Template template);
}
