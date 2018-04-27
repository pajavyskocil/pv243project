package cz.fi.muni.TACOS.persistence.entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Entity
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique=true)
    private String name;

    @NotNull
    @Column(nullable = false)
    @ManyToMany
    private Set<Product> products = new HashSet<>();

    @Lob
    @Column(columnDefinition = "mediumblob")
    private Byte[] image;

    @ManyToOne
    private ProductCategory parentCategory;

    @NotNull
    @OneToMany(mappedBy="parentCategory")
    private Set<ProductCategory> subCategories = new HashSet<>();

    public ProductCategory() {

    }

    // created for mapper
    public Long getParentCategoryId() {
        if (parentCategory == null) {
            return null;
        }
        return parentCategory.getId();
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
        product.addProductCategoryFromOneSide(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.removeProductCategoryFromOneSide(this);
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public ProductCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategoryFromOneSide(ProductCategory category) {
        this.parentCategory = category;
    }

    public Set<ProductCategory> getSubCategories() {
        return Collections.unmodifiableSet(subCategories);
    }

    public void addSubCategory(ProductCategory subCategory) {
        this.subCategories.add(subCategory);
        subCategory.setParentCategoryFromOneSide(this);
    }

    public void removeSubCategory(ProductCategory subCategory) {
        this.subCategories.remove(subCategory);
        subCategory.setParentCategoryFromOneSide(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(getName(), that.getName()) &&
                Arrays.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getName());
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
                ", parentCategory=" + parentCategory +
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
