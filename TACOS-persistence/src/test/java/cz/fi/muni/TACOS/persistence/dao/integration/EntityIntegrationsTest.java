package cz.fi.muni.TACOS.persistence.dao.integration;

import cz.fi.muni.TACOS.persistence.dao.CreatedProductDao;
import cz.fi.muni.TACOS.persistence.dao.OrderDao;
import cz.fi.muni.TACOS.persistence.dao.AttributeCategoryDao;
import cz.fi.muni.TACOS.persistence.dao.AttributeDao;
import cz.fi.muni.TACOS.persistence.dao.ProductCategoryDao;
import cz.fi.muni.TACOS.persistence.dao.ProductDao;
import cz.fi.muni.TACOS.persistence.dao.TemplateDao;
import cz.fi.muni.TACOS.persistence.dao.UserDao;
import cz.fi.muni.TACOS.persistence.dao.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.persistence.entity.User;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Order relations
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class EntityIntegrationsTest {

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
    private OrderDao orderDao;

    @Inject
    private UserDao userDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private CreatedProductDao createdProductDao;

    @Inject
    private AttributeDao attributeDao;

    @Inject
    private AttributeCategoryDao attributeCategoryDao;

    @Inject
    private TemplateDao templateDao;

    @Inject
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testUserOrderRelations() {
        User user = EntityCreator.createTestUser(userDao, "OrdeRelations");
        Order order = EntityCreator.createTestOrder(orderDao, userDao);

        user.addSubmittedOrder(order);

        User foundUser = userDao.findById(user.getId());
        Order foundOrder = orderDao.findById(order.getId());

        assertThat(foundUser.getOrders()).contains(foundOrder);
        assertThat(foundOrder.getSubmitter()).isEqualTo(foundUser);
    }

    @Test
    public void testOrderCreatedProductRelations() {
        Order order = EntityCreator.createTestOrder(orderDao, userDao);
        CreatedProduct createdProduct = EntityCreator.createSecondCreatedProduct(createdProductDao, orderDao, productDao, userDao);

        order.addProduct(createdProduct);

        Order foundOrder = orderDao.findById(order.getId());
        CreatedProduct foundCreatedProduct = createdProductDao.findById(createdProduct.getId());

        assertThat(foundOrder.getProducts()).contains(foundCreatedProduct);
        assertThat(foundCreatedProduct.getOrder()).isEqualTo(foundOrder);
    }

    @Test
    public void testProductCreatedProductRelations() {
        Product product = EntityCreator.createProduct(productDao);
        CreatedProduct createdProduct = EntityCreator.createCreatedProduct(createdProductDao, orderDao, productDao, userDao);

        product.addCreatedProduct(createdProduct);

        Product foundProduct = productDao.findById(product.getId());
        CreatedProduct foundCreatedProduct = createdProductDao.findById(createdProduct.getId());

        assertThat(foundProduct.getCreatedProducts()).contains(foundCreatedProduct);
        assertThat(foundCreatedProduct.getProduct()).isEqualTo(foundProduct);
    }

    @Test
    public void testAttributeCreatedProductRelations() {
        CreatedProduct createdProduct = EntityCreator.createCreatedProduct(createdProductDao, orderDao, productDao, userDao);
        Attribute attribute = EntityCreator.createTestProductAttribute(attributeDao, attributeCategoryDao);

        createdProduct.addAttribute(attribute);

        CreatedProduct foundCreatedProduct = createdProductDao.findById(createdProduct.getId());
        Attribute foundAttribute = attributeDao.findById(attribute.getId());

        assertThat(foundCreatedProduct.getAttributes()).contains(foundAttribute);
        assertThat(foundAttribute.getCreatedProducts()).contains(foundCreatedProduct);
    }

    @Test
    public void testAttributeAttributeCategoryRelations() {
        Attribute attribute = EntityCreator.createTestProductAttribute(attributeDao, attributeCategoryDao);
        AttributeCategory attributeCategory = EntityCreator.createProductAttributeCategory(attributeCategoryDao);

        attributeCategory.addAttribute(attribute);

        Attribute foundAttribute = attributeDao.findById(attribute.getId());
        AttributeCategory foundCategory = attributeCategoryDao.findById(attributeCategory.getId());

        assertThat(foundAttribute.getAttributeCategories()).contains(foundCategory);
        assertThat(foundCategory.getAttributes()).contains(foundAttribute);
    }

    @Test
    public void testAttributeCategoryTemplateRelations() {
        AttributeCategory attributeCategory = EntityCreator.createProductAttributeCategory(attributeCategoryDao);
        Template template = EntityCreator.createTestTemplate(templateDao);

        template.addAttributeCategory(attributeCategory);

        AttributeCategory foundCategory = attributeCategoryDao.findById(attributeCategory.getId());
        Template foundTemplate = templateDao.findById(template.getId());

        assertThat(foundCategory.getTemplates()).contains(foundTemplate);
        assertThat(foundTemplate.getAttributeCategories()).contains(foundCategory);
    }

    @Test
    public void testProductTemplateProductRelations() {
        Template template = EntityCreator.createTestTemplate(templateDao);
        Product product = EntityCreator.createProduct(productDao);

        product.addTemplate(template);

        Template foundTemplate = templateDao.findById(template.getId());
        Product foundProduct = productDao.findById(product.getId());

        assertThat(foundTemplate.getProducts()).contains(foundProduct);
        assertThat(foundProduct.getTemplates()).contains(foundTemplate);
    }

    @Test
    public void testProductProductCategory() {
        Product product = EntityCreator.createProduct(productDao);
        ProductCategory category = EntityCreator.createProductCategory(productCategoryDao);

        category.addProduct(product);

        Product foundProduct = productDao.findById(product.getId());
        ProductCategory foundCategory = productCategoryDao.findById(category.getId());

        assertThat(foundProduct.getProductCategories()).contains(foundCategory);
        assertThat(foundCategory.getProducts()).contains(foundProduct);
    }

    @Test
    public void testProductCategoryProductCategory() {
        ProductCategory category1 = EntityCreator.createProductCategory(productCategoryDao);
        ProductCategory category2 = EntityCreator.createSecondProductCategory(productCategoryDao);

        category1.addSubCategory(category2);

        ProductCategory foundCategory1 = productCategoryDao.findById(category1.getId());
        ProductCategory foundCategory2 = productCategoryDao.findById(category2.getId());

        assertThat(foundCategory1.getSubCategories()).contains(foundCategory2);
        assertThat(foundCategory2.getParentCategory()).isEqualTo(foundCategory1);
    }
}
