package cz.fi.muni.TACOS.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Entity
public class CreatedProduct implements Serializable {

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

    @ManyToMany
    private Set<Attribute> attributes = new HashSet<>();

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

    public void setOrderFromOneSide(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProductFromOneSide(Product product) {
        this.product = product;
    }

    public Set<Attribute> getAttributes() {
        return Collections.unmodifiableSet(attributes);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.addProductFromOneSide(this);
    }

    public void removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.removeProductFromOneSide(this);
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
