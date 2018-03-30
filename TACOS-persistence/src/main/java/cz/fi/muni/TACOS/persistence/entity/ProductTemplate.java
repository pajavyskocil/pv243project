package cz.fi.muni.TACOS.persistence.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<ProductAttributeCategory> attributeCategories = new HashSet<>();

    @ManyToMany(mappedBy = "templates")
    private Set<Product> products = new HashSet<>();

    public ProductTemplate() {

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

    public Set<ProductAttributeCategory> getAttributeCategories() {
        return Collections.unmodifiableSet(attributeCategories);
    }

    public void addAttributeCategory(ProductAttributeCategory category) {
        this.attributeCategories.add(category);
    }

    public void removeAttributeCategory(ProductAttributeCategory category) {
        this.attributeCategories.remove(category);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTemplate)) return false;
        ProductTemplate that = (ProductTemplate) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAttributeCategories(), that.getAttributeCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAttributeCategories());
    }

    @Override
    public String toString() {
        return "ProductTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributeCategories=" + attributeCategories +
                '}';
    }
}
