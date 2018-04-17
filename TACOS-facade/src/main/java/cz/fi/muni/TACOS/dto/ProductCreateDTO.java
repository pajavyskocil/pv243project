package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCreateDTO {

	private String name;

	private String description;

	private BigDecimal minimalPrice;

	private Set<TemplateDTO> templates = new HashSet<>();

	private Set<ProductCategoryDTO> productCategories = new HashSet<>();

	private Set<CreatedProductDTO> createdProducts = new HashSet<>();


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

	public BigDecimal getMinimalPrice() {
		return minimalPrice;
	}

	public void setMinimalPrice(BigDecimal minimalPrice) {
		this.minimalPrice = minimalPrice;
	}

	public Set<TemplateDTO> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<TemplateDTO> templates) {
		this.templates = templates;
	}

	public Set<ProductCategoryDTO> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategoryDTO> productCategories) {
		this.productCategories = productCategories;
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
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return Objects.equals(getName(), product.getName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName());
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", minimalPrice=" + minimalPrice +
				'}';
	}
}
