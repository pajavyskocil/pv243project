package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.enums.ProductAttributeStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeCreateDTO {

	private String name;

	private BigDecimal price;

	private String description;

	private ProductAttributeStatus status;

	private Set<Long> attributeCategoryIds = new HashSet<>();

	private Byte[] image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductAttributeStatus getStatus() {
		return status;
	}

	public void setStatus(ProductAttributeStatus status) {
		this.status = status;
	}

	public Set<Long> getAttributeCategoryIds() {
		return attributeCategoryIds;
	}

	public void setAttributeCategoryIds(Set<Long> attributeCategories) {
		this.attributeCategoryIds = attributeCategories;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttributeCreateDTO that = (AttributeCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(price, that.price) &&
				Objects.equals(description, that.description) &&
				status == that.status &&
				Objects.equals(attributeCategoryIds, that.attributeCategoryIds) &&
				Arrays.equals(image, that.image);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(name, price, description, status, attributeCategoryIds);
		result = 31 * result + Arrays.hashCode(image);
		return result;
	}

	@Override
	public String toString() {
		return "AttributeCreateDTO{" +
				"name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", status=" + status +
				", attributeCategoryIds=" + attributeCategoryIds +
				", image=" + Arrays.toString(image) +
				'}';
	}
}
