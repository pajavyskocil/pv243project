package cz.fi.muni.TACOS.persistence.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vyskocil Pavel <vyskocilpavel@muni.cz>
 */
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String description;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Column(nullable = false)
    @ManyToMany
    private Set<ProductTemplate> templates = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    @ManyToMany(mappedBy = "products")
    private Set<ProductCategory> productCategories = new HashSet<>();

    public Product() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<ProductTemplate> getTemplates() {
        return Collections.unmodifiableSet(templates);
    }

    public void addTemplate(ProductTemplate template) {
        this.templates.add(template);
        template.addProduct(this);
    }

    public void removeTemplate(ProductTemplate template) {
        this.templates.remove(template);
        template.removeProduct(this);
    }

    public void addProductCategory(ProductCategory category) {
        this.productCategories.add(category);
    }

    public void removeProductCategory(ProductCategory category) {
        this.productCategories.remove(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;

        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getDescription(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
