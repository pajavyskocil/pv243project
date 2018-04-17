package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.Template;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class TemplateCreateDTO {

	private String name;

	private BigDecimal minimalPrice;

	private Set<AttributeCategoryDTO> attributeCategories = new HashSet<>();

	private Set<ProductDTO> products = new HashSet<>();


	public BigDecimal getMinimalPrice() {
		return minimalPrice;
	}

	public void setMinimalPrice(BigDecimal minimalPrice) {
		this.minimalPrice = minimalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AttributeCategoryDTO> getAttributeCategories() {
		return attributeCategories;
	}

	public void setAttributeCategories(Set<AttributeCategoryDTO> attributeCategories) {
		this.attributeCategories = attributeCategories;
	}

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Template)) return false;
		Template template = (Template) o;
		return Objects.equals(getName(), template.getName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName());
	}

	@Override
	public String toString() {
		return "Template{" +
				"name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributeCategories=" + attributeCategories +
				'}';
	}
}
