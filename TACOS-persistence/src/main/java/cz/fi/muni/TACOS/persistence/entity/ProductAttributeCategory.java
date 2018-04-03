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
public class ProductAttributeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<ProductAttribute> attributes = new HashSet<>();

    @ManyToMany(mappedBy = "attributeCategories")
    private Set<ProductTemplate> templates = new HashSet<>();

    public ProductAttributeCategory() {

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

    public Set<ProductAttribute> getAttributes() {
        return Collections.unmodifiableSet(attributes);
    }

    public void addAttribute(ProductAttribute attribute) {
        this.attributes.add(attribute);
        attribute.addProductAttributeCategory(this);
    }

    public void removeAttribute(ProductAttribute attribute) {
        this.attributes.remove(attribute);
        attribute.removeProductAttributeCategory(this);
    }

    public Set<ProductTemplate> getTemplates() {
        return Collections.unmodifiableSet(templates);
    }

    public void addProductTemplate(ProductTemplate template) {
        this.templates.add(template);
    }

    public void removeProductTemplate(ProductTemplate template) {
        this.templates.remove(template);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductAttributeCategory)) return false;
        ProductAttributeCategory that = (ProductAttributeCategory) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAttributes(), that.getAttributes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAttributes());
    }

    @Override
    public String toString() {
        return "ProductAttributeCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
