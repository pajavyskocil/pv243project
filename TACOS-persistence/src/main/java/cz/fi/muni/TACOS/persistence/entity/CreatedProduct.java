package cz.fi.muni.TACOS.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Entity
public class CreatedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @DecimalMin("0")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Min(1L)
    @Column(nullable = false)
    private Long count;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @ManyToOne
    private Order order;

    @NotNull
    @ManyToOne
    private Product product;

    public CreatedProduct() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }
}
