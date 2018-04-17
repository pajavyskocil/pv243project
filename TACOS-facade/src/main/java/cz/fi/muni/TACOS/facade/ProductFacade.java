package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;

/**
 * Facade Interface for Product entity.
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface ProductFacade extends Facade<ProductDTO, ProductCreateDTO> {

    /**
     * Creates product in category
     *
     * @param entity specification
     * @param categoryId category id
     * @return id of newly created entity
     */
    Long create(ProductCreateDTO entity, Long categoryId);

    /**
     * Adds template to specified product
     * @param productId product id
     * @param templateId template id
     */
    void addTemplate(Long productId, Long templateId);

    /**
     * Removes template from product
     *
     * @param productId product id
     * @param templateId template id
     */
    void removeTemplate(Long productId, Long templateId);
}
