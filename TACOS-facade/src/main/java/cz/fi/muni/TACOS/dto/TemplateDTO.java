package cz.fi.muni.TACOS.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class TemplateDTO {

	private Long id;

	private String name;

	private BigDecimal minimalPrice;

	private Set<AttributeCategoryDTO> attributeCategories = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<AttributeCategoryDTO> getAttributeCategories() {
		return attributeCategories;
	}

	public void setAttributeCategories(Set<AttributeCategoryDTO> attributeCategories) {
		this.attributeCategories = attributeCategories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TemplateDTO that = (TemplateDTO) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "TemplateDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributeCategories=" + attributeCategories +
				'}';
	}
}
