package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.enums.ProductAttributeStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeDTO {

	private Long id;

	private String name;

	private BigDecimal price;

	private String description;

	private ProductAttributeStatus status;

	private Byte[] image;

	private Long attributeCategoryId;

	private String attributeCategoryName;

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

	public String getAttributeCategoryName() {
		return attributeCategoryName;
	}

	public void setAttributeCategoryName(String attributeCategoryName) {
		this.attributeCategoryName = attributeCategoryName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AttributeDTO)) return false;
		AttributeDTO that = (AttributeDTO) o;
		return Objects.equals(getId(), that.getId()) &&
				Objects.equals(getName(), that.getName()) &&
				Objects.equals(getPrice(), that.getPrice()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				getStatus() == that.getStatus() &&
				Arrays.equals(getImage(), that.getImage()) &&
				Objects.equals(getAttributeCategoryId(), that.getAttributeCategoryId()) &&
				Objects.equals(getAttributeCategoryName(), that.getAttributeCategoryName());
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(getId(), getName(), getPrice(), getDescription(), getStatus(), getAttributeCategoryId(), getAttributeCategoryName());
		result = 31 * result + Arrays.hashCode(getImage());
		return result;
	}

	@Override
	public String toString() {
		return "AttributeDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", status=" + status +
				", image=" + Arrays.toString(image) +
				", attributeCategoryId=" + attributeCategoryId +
				", attributeCategoryName='" + attributeCategoryName + '\'' +
				'}';
	}
}
