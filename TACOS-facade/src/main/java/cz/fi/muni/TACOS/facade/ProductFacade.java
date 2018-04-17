package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;

/**
 * Facade Interface for Product entity.
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public interface ProductFacade extends Facade<ProductDTO, ProductCreateDTO> {

    /**
     * Adds template to product
     *
     * @param productId
     * @param templateId
     */
    void addTemplate(Long productId, Long templateId);

    /**
     * Removes template from product
     *
     * @param productId
     * @param templateId
     */
    void removeTemplate(Long productId, Long templateId);
}
