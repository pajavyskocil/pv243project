package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.dao.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class ProductCategoryTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class)
                .addPackages(true, "cz.fi.muni.TACOS.persistence", "org.assertj.core",
                        "java.lang")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProductCategoryDao productCategoryDao;


    @Inject
    private ProductDao productDao;

    @Test
    public void testGetAll() {
        ProductCategory categoryOne = EntityCreator.createProductCategory(productCategoryDao);
        ProductCategory categorySecond = EntityCreator.createSecondProductCategory(productCategoryDao);

        List<ProductCategory> foundCategories = productCategoryDao.getAll();

        assertThat(foundCategories).containsExactly(categoryOne, categorySecond);
    }

    @Test
    public void testFindById() {
        ProductCategory category = EntityCreator.createProductCategory(productCategoryDao);

        ProductCategory foundCategory = productCategoryDao.findById(category.getId());

        assertThat(foundCategory).isEqualToComparingFieldByField(category);
    }

    @Test
    public void testFindByName() {
        ProductCategory category = EntityCreator.createProductCategory(productCategoryDao);

        ProductCategory foundCategory = productCategoryDao.findByName(category.getName());

        assertThat(foundCategory).isEqualToComparingFieldByField(category);
    }

    @Test
    public void testFindByIdNothingFound() {
        ProductCategory foundCategory = productCategoryDao.findById(1L);
        assertThat(foundCategory).isEqualTo(null);
    }

    @Test
    public void testFindByIdNegativeId() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.findById(-1L));
    }

    @Test
    public void testFindByNameWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.findByName(null));
    }

    @Test
    public void testFindByNameWithEmptyValue() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.findByName(""));
    }

    @Test
    public void testCreateWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.create(null));
    }

    @Test
    public void testCreate() {
        ProductCategory category = EntityCreator.createProductCategory(productCategoryDao);
        ProductCategory foundCategory = productCategoryDao.findById(category.getId());
        assertThat(foundCategory).isEqualToComparingFieldByField(category);
    }

    @Test
    public void testDeleteWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.delete(null));
    }

    @Test
    public void testDelete() {
        ProductCategory category = EntityCreator.createProductCategory(productCategoryDao);

        productCategoryDao.delete(category);

        ProductCategory foundCategory = productCategoryDao.findById(category.getId());
        assertThat(foundCategory).isEqualTo(null);
    }

    @Test
    public void testFindByIdWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> productCategoryDao.delete(null));
    }

    @Test
    public void testGetAllNothingFound() {
        List<ProductCategory> foundCategories = productCategoryDao.getAll();
        assertThat(foundCategories).isEmpty();
    }

    @Test
    public void testAddSubCategory() {
        ProductCategory categoryOne = EntityCreator.createProductCategory(productCategoryDao);
        ProductCategory categorySecond = EntityCreator.createSecondProductCategory(productCategoryDao);

        categoryOne.addSubCategory(categorySecond);
        Set<ProductCategory> subCategories = categoryOne.getSubCategories();
        ProductCategory parentCategory = categorySecond.getParentCategory();

        assertThat(parentCategory).isEqualToComparingFieldByField(categoryOne);
        assertThat(subCategories).containsExactly(categorySecond);
    }

    @Test
    public void testRemoveSubCategory() {
        ProductCategory categoryOne = EntityCreator.createProductCategory(productCategoryDao);
        ProductCategory categorySecond = EntityCreator.createSecondProductCategory(productCategoryDao);

        categoryOne.addSubCategory(categorySecond);
        categoryOne.removeSubCategory(categorySecond);

        Set<ProductCategory> subCategories = categoryOne.getSubCategories();
        ProductCategory parentCategory = categorySecond.getParentCategory();

        assertThat(parentCategory).isEqualTo(null);
        assertThat(subCategories).isEmpty();
    }

    @Test
    public void testAddProduct() {
        ProductCategory categoryOne = EntityCreator.createProductCategory(productCategoryDao);
        Product product = EntityCreator.createProduct(productDao);

        categoryOne.addProduct(product);

        assertThat(product.getProductCategories()).containsExactly(categoryOne);
        assertThat(categoryOne.getProducts()).containsExactly(product);
    }

    @Test
    public void testRemoveProduct() {
        ProductCategory categoryOne = EntityCreator.createProductCategory(productCategoryDao);
        Product product = EntityCreator.createProduct(productDao);

        categoryOne.addProduct(product);
        categoryOne.removeProduct(product);

        assertThat(product.getProductCategories()).isEmpty();
        assertThat(categoryOne.getProducts()).isEmpty();
    }
}
