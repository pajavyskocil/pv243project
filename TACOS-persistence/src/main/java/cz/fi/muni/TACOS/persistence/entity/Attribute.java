package cz.fi.muni.TACOS.persistence.entity;

import cz.fi.muni.TACOS.enums.ProductAttributeStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    @NotNull
    @Column(nullable = false)
    private ProductAttributeStatus status;

    @ManyToOne
    private AttributeCategory attributeCategory;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private Byte[] image;

    @ManyToMany(mappedBy = "attributes")
    private Set<CreatedProduct> createdProducts = new HashSet<>();

    public Attribute() {

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

    public AttributeCategory getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategoryFromOneSide(AttributeCategory attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    //used for mapper
    public Long getAttributeCategoryId() {
        if (attributeCategory == null) {
            return null;
        }
        return attributeCategory.getId();
    }

    //used for mapper
    public String getAttributeCategoryName() {
        if (attributeCategory == null) {
            return null;
        }
        return attributeCategory.getName();
    }

    public void addProductFromOneSide(CreatedProduct createdProduct) {
        this.createdProducts.add(createdProduct);
    }

    public void removeProductFromOneSide(CreatedProduct createdProduct) {
        this.createdProducts.remove(createdProduct);
    }

    public Set<CreatedProduct> getCreatedProducts() {
        return Collections.unmodifiableSet(createdProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute that = (Attribute) o;
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
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
