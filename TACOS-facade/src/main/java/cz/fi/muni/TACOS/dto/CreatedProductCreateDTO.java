package cz.fi.muni.TACOS.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class CreatedProductCreateDTO {

	private Long count;

	private String description;

	private Long orderId;

	private Long productId;

	private Set<Long> attributes = new HashSet<>();

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Set<Long> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Long> attributes) {
		this.attributes = attributes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreatedProductCreateDTO that = (CreatedProductCreateDTO) o;
		return Objects.equals(count, that.count) &&
				Objects.equals(description, that.description) &&
				Objects.equals(orderId, that.orderId) &&
				Objects.equals(productId, that.productId) &&
				Objects.equals(attributes, that.attributes);
	}

	@Override
	public int hashCode() {

		return Objects.hash(count, description, orderId, productId, attributes);
	}

	@Override
	public String toString() {
		return "CreatedProductCreateDTO{" +
				"count=" + count +
				", description='" + description + '\'' +
				", orderId=" + orderId +
				", productId=" + productId +
				", attributes=" + attributes +
				'}';
	}
}
