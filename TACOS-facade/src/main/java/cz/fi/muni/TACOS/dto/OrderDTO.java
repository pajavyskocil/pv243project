package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.enums.OrderState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class OrderDTO {

	private Long id;

	private OrderState state;

	private LocalDate submitted;

	private Long submitterId;

	private LocalDate finished;

	private BigDecimal price;

	private Set<CreatedProductDTO> products = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public LocalDate getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDate submitted) {
		this.submitted = submitted;
	}

	public Long getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	public LocalDate getFinished() {
		return finished;
	}

	public void setFinished(LocalDate finished) {
		this.finished = finished;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<CreatedProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<CreatedProductDTO> products) {
		this.products = products;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDTO orderDTO = (OrderDTO) o;
		return Objects.equals(id, orderDTO.id) &&
				state == orderDTO.state &&
				Objects.equals(submitted, orderDTO.submitted) &&
				Objects.equals(submitterId, orderDTO.submitterId) &&
				Objects.equals(finished, orderDTO.finished) &&
				Objects.equals(price, orderDTO.price) &&
				Objects.equals(products, orderDTO.products);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, state, submitted, submitterId, finished, price, products);
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"id=" + id +
				", state=" + state +
				", submitted=" + submitted +
				", submitterId=" + submitterId +
				", finished=" + finished +
				", price=" + price +
				", products=" + products +
				'}';
	}
}
