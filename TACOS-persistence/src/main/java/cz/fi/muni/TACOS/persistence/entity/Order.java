package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.persistence.enums.OrderState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vyskocil Pavel <vyskocilpavel@muni.cz>
 */
@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private OrderState state;

    @NotNull
    @Column(nullable = false)
    private LocalDate submitted;

    @ManyToOne
    @JoinColumn(name = "submitter_id")
    private User submitter;

    private LocalDate finished;

    @NotNull
    @Column(nullable = false)
    @DecimalMin("0")
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "order")
    private Set<CreatedProduct> products = new HashSet<>();

    public Order() {
    }


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

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
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

    public Set<CreatedProduct> getProducts() {
        return products;
    }

    public void addProduct(CreatedProduct product) {
        this.products.add(product);
        product.setOrder(this);
    }

    public void removeProduct(CreatedProduct product) {
        this.products.remove(product);
        product.setOrder(null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getState() == order.getState() &&
                Objects.equals(getSubmitted(), order.getSubmitted()) &&
                Objects.equals(getSubmitter(), order.getSubmitter()) &&
                Objects.equals(getFinished(), order.getFinished()) &&
                Objects.equals(getPrice(), order.getPrice()) &&
                Objects.equals(getProducts(), order.getProducts());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getState(), getSubmitted(), getSubmitter(), getFinished(), getPrice(), getProducts());
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
                ", products=" + products +
                '}';
    }

}
