package cz.fi.muni.TACOS.dto;

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

	private Set<Long> attributeCategories = new HashSet<>();

	private Set<Long> products = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMinimalPrice() {
		return minimalPrice;
	}

	public void setMinimalPrice(BigDecimal minimalPrice) {
		this.minimalPrice = minimalPrice;
	}

	public Set<Long> getAttributeCategories() {
		return attributeCategories;
	}

	public void setAttributeCategories(Set<Long> attributeCategories) {
		this.attributeCategories = attributeCategories;
	}

	public Set<Long> getProducts() {
		return products;
	}

	public void setProducts(Set<Long> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TemplateCreateDTO that = (TemplateCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(minimalPrice, that.minimalPrice) &&
				Objects.equals(attributeCategories, that.attributeCategories) &&
				Objects.equals(products, that.products);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, minimalPrice, attributeCategories, products);
	}

	@Override
	public String toString() {
		return "TemplateCreateDTO{" +
				"name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributeCategories=" + attributeCategories +
				", products=" + products +
				'}';
	}
}
