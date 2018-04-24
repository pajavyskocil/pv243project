package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.enums.ProductAttributeStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeCreateDTO {

	private String name;

	private BigDecimal price;

	private String description;

	private ProductAttributeStatus status;

	private Long attributeCategoryId;

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

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Long getAttributeCategoryId() {
		return attributeCategoryId;
	}

	public void setAttributeCategoryId(Long attributeCategoryId) {
		this.attributeCategoryId = attributeCategoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AttributeCreateDTO)) return false;
		AttributeCreateDTO that = (AttributeCreateDTO) o;
		return Objects.equals(getName(), that.getName()) &&
				Objects.equals(getPrice(), that.getPrice()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				getStatus() == that.getStatus() &&
				Objects.equals(attributeCategoryId, that.attributeCategoryId) &&
				Arrays.equals(getImage(), that.getImage());
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(getName(), getPrice(), getDescription(), getStatus(), attributeCategoryId);
		result = 31 * result + Arrays.hashCode(getImage());
		return result;
	}

	@Override
	public String toString() {
		return "AttributeCreateDTO{" +
				"name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", status=" + status +
				", attributeCategoryId=" + attributeCategoryId +
				", image=" + Arrays.toString(image) +
				'}';
	}
}
