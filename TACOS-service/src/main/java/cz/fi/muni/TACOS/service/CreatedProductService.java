package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;

/**
 * EntityService interface for managing CreatedProduct entities
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface CreatedProductService extends EntityService<CreatedProduct> {

	/**
	 * Add attribute to CreatedProduct's list of attributes
	 * @param createdProduct CreatedProduct
	 * @param attribute Attribute
	 */
	void addAttribute(CreatedProduct createdProduct, Attribute attribute);

	/**
	 * Remove attribute from CreatedProduct's list of attributes
	 * @param createdProduct CreatedProduct
	 * @param attribute Attribute
	 */
	void removeAttribute(CreatedProduct createdProduct, Attribute attribute);
}
