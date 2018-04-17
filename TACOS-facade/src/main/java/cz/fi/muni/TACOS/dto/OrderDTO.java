package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.enums.OrderState;

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

	private UserDTO submitter;

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

	public UserDTO getSubmitter() {
		return submitter;
	}

	public void setSubmitter(UserDTO submitter) {
		this.submitter = submitter;
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
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return getState() == order.getState() &&
				Objects.equals(getSubmitted(), order.getSubmitted()) &&
				Objects.equals(getFinished(), order.getFinished()) &&
				Objects.equals(getPrice(), order.getPrice()) &&
				Objects.equals(getProducts(), order.getProducts());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getState(), getSubmitted(), getFinished(), getPrice(), getProducts());
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", state=" + state +
				", submitted=" + submitted +
				", submitter=" + submitter +
				", finished=" + finished +
				", price=" + price +
				'}';
	}

}
