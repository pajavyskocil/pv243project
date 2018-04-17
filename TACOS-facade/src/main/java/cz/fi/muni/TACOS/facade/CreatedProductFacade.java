package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductDTO;

/**
 * Facade interface for CreatedProduct entity
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface CreatedProductFacade extends Facade<CreatedProductDTO, CreatedProductCreateDTO> {

	/**
	 * Add attribute to CreatedProduct's list of attributes
	 *
	 * @param createdProductId createdProduct's Id
	 * @param attributeId attribute's Id
	 */
	void addAttribute(Long createdProductId, Long attributeId);

	/**
	 * Remove attribute from CreatedProduct's list of attributes
	 *
	 * @param createdProductId createdProduct's Id
	 * @param attributeId attribute's Id
	 */
	void removeAttribute(Long createdProductId, Long attributeId);
}
