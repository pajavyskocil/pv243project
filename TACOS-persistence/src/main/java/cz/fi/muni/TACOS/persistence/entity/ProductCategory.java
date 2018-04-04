package cz.fi.muni.TACOS.persistence.entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Entity
public class ProductCategory {

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
    @ManyToMany
    private Set<Product> products = new HashSet<>();

    @Lob
    @Column(columnDefinition = "mediumblob", nullable = false)
    private Byte[] image;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="category_id")
    private ProductCategory category;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy="category")
    private Set<ProductCategory> subCategories = new HashSet<>();

    public ProductCategory() {

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
        checkName(name);
        this.name = name;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.addProductCategory(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.removeProductCategory(this);
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Set<ProductCategory> getSubCategories() {
        return Collections.unmodifiableSet(subCategories);
    }

    public void addSubCategory(ProductCategory subCategory) {
        this.subCategories.add(subCategory);
        subCategory.setCategory(this);
    }

    public void removeSubCategory(ProductCategory subCategory) {
        this.subCategories.remove(subCategory);
        subCategory.setCategory(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getProducts(), that.getProducts()) &&
                Arrays.equals(getImage(), that.getImage()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getSubCategories(), that.getSubCategories());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getName(), getProducts(), getCategory(), getSubCategories());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", image=" + Arrays.toString(image) +
                ", category=" + category +
                ", subCategories=" + subCategories +
                '}';
    }

    private void checkName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }
}
