package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeDTO;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public interface AttributeFacade extends Facade<AttributeDTO> {

    /**
     * Creates attribute in specified attribute category
     *
     * @param attributeCreateDTO attribute specification
     * @return id of newly created enity
     */
    Long create(AttributeCreateDTO attributeCreateDTO);
}
