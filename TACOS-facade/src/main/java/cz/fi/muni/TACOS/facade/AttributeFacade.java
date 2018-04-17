package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeDTO;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface AttributeFacade extends Facade<AttributeDTO, AttributeCreateDTO> {

    /**
     * Creates attribute in specified attribute category
     *
     * @param attributeCreateDTO attribute specification
     * @param attributeCategoryId attribute category id
     * @return id of newly created enity
     */
    Long create(AttributeCreateDTO attributeCreateDTO, Long attributeCategoryId);
}
