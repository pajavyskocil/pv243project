package cz.fi.muni.TACOS.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCreateDTO {

	private String name;

	private String description;

	private Set<Long> productCategories = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Long> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<Long> productCategories) {
		this.productCategories = productCategories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCreateDTO that = (ProductCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(description, that.description) &&
				Objects.equals(productCategories, that.productCategories);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, description, productCategories);
	}

	@Override
	public String toString() {
		return "ProductCreateDTO{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", productCategories=" + productCategories +
				'}';
	}
}
