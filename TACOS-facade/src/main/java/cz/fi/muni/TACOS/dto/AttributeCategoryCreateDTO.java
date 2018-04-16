package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;

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

	private Set<AttributeDTO> attributes = new HashSet<>();

	private Set<TemplateDTO> templates = new HashSet<>();


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

	public Set<AttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<AttributeDTO> attributes) {
		this.attributes = attributes;
	}

	public Set<TemplateDTO> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<TemplateDTO> templates) {
		this.templates = templates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AttributeCategory)) return false;
		AttributeCategory that = (AttributeCategory) o;
		return Objects.equals(getName(), that.getName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName());
	}

	@Override
	public String toString() {
		return "AttributeCategory{" +
				"name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributes=" + attributes +
				'}';
	}

}
