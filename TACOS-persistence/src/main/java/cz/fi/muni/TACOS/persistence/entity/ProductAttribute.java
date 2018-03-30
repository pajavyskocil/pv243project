package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.persistence.enums.ProductAttributeStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private ProductAttributeStatus status;

    @NotEmpty
    @ManyToMany(mappedBy = "attributes")
    private Set<ProductAttributeCategory> attributeCategories = new HashSet<>();

    @Lob
    @Column(columnDefinition = "mediumblob", nullable = false)
    private Byte[] image;

    public ProductAttribute() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductAttributeStatus getStatus() {
        return status;
    }

    public void setStatus(ProductAttributeStatus status) {
        this.status = status;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Set<ProductAttributeCategory> getAttributeCategories() {
        return Collections.unmodifiableSet(attributeCategories);
    }

    public void addProductAttributeCategory(ProductAttributeCategory category) {
        this.attributeCategories.add(category);
    }

    public void removeProductAttributeCategory(ProductAttributeCategory category) {
        this.attributeCategories.remove(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAttribute)) return false;
        ProductAttribute that = (ProductAttribute) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                getStatus() == that.getStatus() &&
                Arrays.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getPrice(), getDescription(), getStatus());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
