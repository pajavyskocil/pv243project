package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.enums.ProductAttributeStatus;

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

	private Set<AttributeCategoryDTO> attributeCategories = new HashSet<>();

	private Byte[] image;

	private Set<CreatedProductDTO> createdProducts = new HashSet<>();


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

	public Set<AttributeCategoryDTO> getAttributeCategories() {
		return attributeCategories;
	}

	public void setAttributeCategories(Set<AttributeCategoryDTO> attributeCategories) {
		this.attributeCategories = attributeCategories;
	}

	public Set<CreatedProductDTO> getCreatedProducts() {
		return createdProducts;
	}

	public void setCreatedProducts(Set<CreatedProductDTO> createdProducts) {
		this.createdProducts = createdProducts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Attribute)) return false;
		Attribute that = (Attribute) o;
		return Objects.equals(getName(), that.getName()) &&
				Objects.equals(getPrice(), that.getPrice()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				getStatus() == that.getStatus() &&
				Arrays.equals(getImage(), that.getImage());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getName(), getPrice(), getDescription(), getStatus());
		result = 31 * result + Arrays.hashCode(getImage());
		return result;
	}

	@Override
	public String toString() {
		return "Attribute{" +
				"name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", status=" + status +
				'}';
	}
}
