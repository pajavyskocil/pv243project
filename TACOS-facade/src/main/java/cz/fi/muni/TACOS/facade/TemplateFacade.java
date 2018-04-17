package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;

/**
 * Facade Interface for Template entity.
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface TemplateFacade extends Facade<TemplateDTO, TemplateCreateDTO> {

    /**
     * Adds attribute category to template
     *
     * @param templateId template
     * @param categoryId category
     */
    void addAttributeCategory(Long templateId, Long categoryId);

    /**
     * Removes attribute category from template
     *
     * @param templateId template
     * @param categoryId category
     */
    void removeAttributeCategory(Long templateId, Long categoryId);
    /**
     * Creates template for specified product
     *
     * @param entity specification
     * @param productId id of product
     * @return id of newly created entity
     */
    Long create(TemplateCreateDTO entity, Long productId);
}
