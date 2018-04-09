package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class CreatedProductTest {

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
    private CreatedProductDao createdProductDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private OrderDao orderDao;

    private CreatedProduct createCreatedProduct() {
        CreatedProduct createdProduct = new CreatedProduct();
        createdProduct.setCount(1L);
        createdProduct.setOrder(createOrder());
        createdProduct.setProduct(createProduct());
        createdProduct.setDescription("desc");
        createdProduct.setPrice(BigDecimal.valueOf(20));
        createdProductDao.create(createdProduct);
        return createdProduct;
    }

    private CreatedProduct createSecondCreatedProduct() {
        CreatedProduct createdProduct = new CreatedProduct();
        createdProduct.setCount(2L);
        createdProduct.setOrder(createSecondOrder());
        createdProduct.setProduct(createProduct());
        createdProduct.setDescription("desc");
        createdProduct.setPrice(BigDecimal.valueOf(10));
        createdProductDao.create(createdProduct);
        return createdProduct;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setDescription("desc");
        product.setName("product");
        product.setPrice(BigDecimal.valueOf(10));
        productDao.create(product);
        return product;
    }

    private Order createOrder() {
        Order order = new Order();
        order.setState(OrderState.NEW);
        order.setSubmitted(LocalDate.now());
        order.setFinished(LocalDate.now());
        order.setPrice(BigDecimal.ONE);
        orderDao.create(order);
        return order;
    }

    private Order createSecondOrder() {
        Order order = new Order();
        order.setState(OrderState.PROCESSED);
        order.setSubmitted(LocalDate.now());
        order.setFinished(LocalDate.now());
        order.setPrice(BigDecimal.valueOf(10));
        orderDao.create(order);
        return order;
    }

    @Test
    public void testGetAll() {
        CreatedProduct createdProductOne = createCreatedProduct();
        CreatedProduct createdProductSecond = createSecondCreatedProduct();

        List<CreatedProduct> foundCreatedProducts = createdProductDao.getAll();

        assertThat(foundCreatedProducts).containsExactly(createdProductOne, createdProductSecond);
    }

    @Test
    public void testFindById() {
        CreatedProduct createdProduct = createCreatedProduct();

        CreatedProduct foundCreatedProduct = createdProductDao.findById(createdProduct.getId());

        assertThat(foundCreatedProduct).isEqualToComparingFieldByField(createdProduct);
    }

    @Test
    public void testFindByIdNothingFound() {
        CreatedProduct foundCreatedProduct = createdProductDao.findById(1L);
        assertThat(foundCreatedProduct).isEqualTo(null);
    }

    @Test
    public void testFindByIdNegativeId() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> createdProductDao.findById(-1L));
    }

    @Test
    public void testCreateWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> createdProductDao.create(null));
    }

    @Test
    public void testCreate() {
        CreatedProduct createdProduct = createCreatedProduct();
        CreatedProduct foundCreatedProduct =createdProductDao.findById(createdProduct.getId());
        assertThat(foundCreatedProduct).isEqualToComparingFieldByField(createdProduct);
    }

    @Test
    public void testDeleteWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> createdProductDao.delete(null));
    }

    @Test
    public void testDelete() {
        CreatedProduct createdProduct = createCreatedProduct();

        createdProductDao.delete(createdProduct);

        CreatedProduct foundCreatedProduct = createdProductDao.findById(createdProduct.getId());
        assertThat(foundCreatedProduct).isEqualTo(null);
    }

    @Test
    public void testFindByIdWithNull() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> createdProductDao.delete(null));
    }

    @Test
    public void testGetAllNothingFound() {
        List<CreatedProduct> foundCreatedProduct = createdProductDao.getAll();
        assertThat(foundCreatedProduct).isEmpty();
    }

}
