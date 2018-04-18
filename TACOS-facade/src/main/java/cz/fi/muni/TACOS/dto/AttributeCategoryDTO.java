package cz.fi.muni.TACOS.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeCategoryDTO {

	private Long id;

	private String name;

	private BigDecimal minimalPrice;

	private Set<AttributeDTO> attributes = new HashSet<>();

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

	public Set<AttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<AttributeDTO> attributes) {
		this.attributes = attributes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttributeCategoryDTO that = (AttributeCategoryDTO) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(minimalPrice, that.minimalPrice) &&
				Objects.equals(attributes, that.attributes);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, minimalPrice, attributes);
	}

	@Override
	public String toString() {
		return "AttributeCategoryDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", minimalPrice=" + minimalPrice +
				", attributes=" + attributes +
				'}';
	}
}
