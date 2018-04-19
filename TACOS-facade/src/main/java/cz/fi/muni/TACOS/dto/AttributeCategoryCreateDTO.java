package cz.fi.muni.TACOS.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeCategoryCreateDTO {

	private String name;

	private BigDecimal minimalPrice;

	private Set<Long> attributes = new HashSet<>();

	private Set<Long> templates = new HashSet<>();

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

	public Set<Long> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Long> attributes) {
		this.attributes = attributes;
	}

	public Set<Long> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<Long> templates) {
		this.templates = templates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttributeCategoryCreateDTO that = (AttributeCategoryCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(minimalPrice, that.minimalPrice) &&
				Objects.equals(attributes, that.attributes) &&
				Objects.equals(templates, that.templates);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, minimalPrice, attributes, templates);
	}

	@Override
	public String toString() {
		return "AttributeCategoryCreateDTO{" +
				"name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributes=" + attributes +
				", templates=" + templates +
				'}';
	}
}
