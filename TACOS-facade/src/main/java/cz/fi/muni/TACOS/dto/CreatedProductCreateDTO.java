package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class CreatedProductCreateDTO {

	private BigDecimal price;

	private Long count;

	private String description;

	private OrderDTO order;

	private ProductDTO product;

	private Set<AttributeDTO> attributes = new HashSet<>();


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
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
		if (!(o instanceof CreatedProduct)) return false;
		CreatedProduct that = (CreatedProduct) o;
		return Objects.equals(getPrice(), that.getPrice()) &&
				Objects.equals(getCount(), that.getCount()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				Objects.equals(getProduct(), that.getProduct());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getPrice(), getCount(), getDescription(), getProduct());
	}

	@Override
	public String toString() {
		return "CreatedProduct{" +
				"price=" + price +
				", count=" + count +
				", description='" + description + '\'' +
				", product=" + product +
				'}';
	}
}
