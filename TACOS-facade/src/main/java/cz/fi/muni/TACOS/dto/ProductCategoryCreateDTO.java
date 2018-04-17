package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.ProductCategory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCategoryCreateDTO {

	private String name;

	private Set<ProductDTO> products = new HashSet<>();

	private Byte[] image;

	private ProductCategoryDTO parentCategory;

	private Set<ProductCategoryDTO> subCategories = new HashSet<>();


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

	public ProductCategoryDTO getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategoryDTO parentCategory) {
		this.parentCategory = parentCategory;
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
		if (!(o instanceof ProductCategory)) return false;
		ProductCategory that = (ProductCategory) o;
		return Objects.equals(getName(), that.getName()) &&
				Arrays.equals(getImage(), that.getImage());
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(getName());
		result = 31 * result + Arrays.hashCode(getImage());
		return result;
	}

	@Override
	public String toString() {
		return "ProductCategory{" +
				"name='" + name + '\'' +
				", products=" + products +
				", image=" + Arrays.toString(image) +
				", parentCategory=" + parentCategory +
				'}';
	}
}
