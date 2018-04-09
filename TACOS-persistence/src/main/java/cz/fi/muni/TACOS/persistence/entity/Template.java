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
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<AttributeCategory> attributeCategories = new HashSet<>();

    @ManyToMany(mappedBy = "templates")
    private Set<Product> products = new HashSet<>();

    public Template() {

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

    public Set<AttributeCategory> getAttributeCategories() {
        return Collections.unmodifiableSet(attributeCategories);
    }

    public void addAttributeCategory(AttributeCategory category) {
        this.attributeCategories.add(category);
        category.addProductTemplateFromOneSide(this);
    }

    public void removeAttributeCategory(AttributeCategory category) {
        this.attributeCategories.remove(category);
        category.removeProductTemplateFromOneSide(this);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void addProductFromOneSide(Product product) {
        this.products.add(product);
    }

    public void removeProductFromOneSide(Product product) {
        this.products.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template that = (Template) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAttributeCategories(), that.getAttributeCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAttributeCategories());
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributeCategories=" + attributeCategories +
                '}';
    }
}
