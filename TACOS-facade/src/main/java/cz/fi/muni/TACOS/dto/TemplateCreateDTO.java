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

	private Set<Long> attributeCategoryIds = new HashSet<>();

	private Set<Long> productIds = new HashSet<>();

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

	public Set<Long> getAttributeCategoryIds() {
		return attributeCategoryIds;
	}

	public void setAttributeCategoryIds(Set<Long> attributeCategories) {
		this.attributeCategoryIds = attributeCategories;
	}

	public Set<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(Set<Long> products) {
		this.productIds = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TemplateCreateDTO that = (TemplateCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(minimalPrice, that.minimalPrice) &&
				Objects.equals(attributeCategoryIds, that.attributeCategoryIds) &&
				Objects.equals(productIds, that.productIds);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, minimalPrice, attributeCategoryIds, productIds);
	}

	@Override
	public String toString() {
		return "TemplateCreateDTO{" +
				"name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributeCategoryIds=" + attributeCategoryIds +
				", productIds=" + productIds +
				'}';
	}
}
