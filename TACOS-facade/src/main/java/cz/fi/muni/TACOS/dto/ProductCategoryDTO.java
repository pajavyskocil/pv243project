package cz.fi.muni.TACOS.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCategoryDTO {

	private Long id;

	private String name;

	private Set<ProductDTO> products = new HashSet<>();

	private Byte[] image;

	private Long parentCategoryId;

	private Set<ProductCategoryDTO> subCategories = new HashSet<>();

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

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

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
		this.products = products;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Set<ProductCategoryDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<ProductCategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCategoryDTO that = (ProductCategoryDTO) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "ProductCategoryDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", products=" + products +
				", image=" + Arrays.toString(image) +
				'}';
	}
}
