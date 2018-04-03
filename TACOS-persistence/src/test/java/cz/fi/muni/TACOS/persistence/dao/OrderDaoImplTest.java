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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Tests for OrderDao
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class OrderDaoImplTest {

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
	private AbstractDao<Product> productDao;

	@Inject
	private AbstractDao<CreatedProduct> createdProductDao;

	private Order createTestOrder() {
		Order order = new Order();
		order.setState(OrderState.NEW);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.ONE);
		orderDao.create(order);
		return order;
	}

	private Order createFinishedOrder() {
		Order order = new Order();
		order.setState(OrderState.FINISHED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.ONE);
		orderDao.create(order);
		return order;
	}

	private Product createTestProduct() {
		Product product = new Product();
		product.setName("product");
		product.setDescription("productDescription");
		product.setPrice(BigDecimal.valueOf(10.0));
		productDao.create(product);
		return product;
	}

	private CreatedProduct createCreatedProductWithOrder() {
		CreatedProduct createdProduct = new CreatedProduct();
		createdProduct.setProduct(createTestProduct());
		createdProduct.setPrice(BigDecimal.valueOf(10));
		createdProduct.setDescription("Description");
		createdProduct.setCount(10L);

		Order order = createTestOrder();
		order.addProduct(createdProduct);

		createdProductDao.create(createdProduct);
		return createdProduct;
	}

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.create(null));
	}

	@Test
	public void testCreate() {
		Order order = createTestOrder();
		Order foundOrder = orderDao.findById(order.getId());
		System.out.println(order.toString());
		System.out.println(foundOrder.toString());
		assertThat(foundOrder).isEqualToComparingFieldByField(order);
	}

	@Test
	public void testDeleteWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.delete(null));
	}

	@Test
	public void testDelete() {
		Order order = createTestOrder();
		orderDao.delete(order);
		Order foundOrder = orderDao.findById(order.getId());
		assertThat(foundOrder).isEqualTo(null);
	}

	@Test
	public void testFindByIdWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.findById(null));
	}

	@Test
	public void testFindByIdWithNegativeId() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.findById(-5L));
	}

	@Test
	public void testFindById() {
		Order order = createTestOrder();
		Order foundOrder = orderDao.findById(order.getId());
		assertThat(foundOrder).isEqualToComparingFieldByField(order);
	}

	@Test
	public void testFindByIdNothingFound() {
		Order foundOrder = orderDao.findById(1L);
		assertThat(foundOrder).isEqualTo(null);
	}

	@Test
	public void testGetAll() {
		Order order = createTestOrder();
		Order finishedOrder = createFinishedOrder();
		List<Order> foundOrders = orderDao.getAll();

		assertThat(foundOrders).containsExactly(order, finishedOrder);
	}

	@Test
	public void testGetAllNothingFound() {
		List<Order> foundOrders = orderDao.getAll();

		assertThat(foundOrders).isEqualTo(new ArrayList<Order>());
	}

	@Test
	public void testGetAllForStateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.findById(null));
	}

	@Test
	public void testGetAllForState() {
		createTestOrder();

		Order finishedOrder = createFinishedOrder();
		List<Order> foundOrders = orderDao.getAllForState(OrderState.FINISHED);

		assertThat(foundOrders).containsOnly(finishedOrder);
	}

	@Test
	public void testGetAllForStateNothingFound() {
		createTestOrder();
		List<Order> foundOrders = orderDao.getAllForState(OrderState.FINISHED);
		assertThat(foundOrders).isEqualTo(new ArrayList<Order>());

	}

	@Test
	public void testAddCreatedProduct() {
		CreatedProduct createdProduct = createCreatedProductWithOrder();
		Order foundOrder = orderDao.findById(createdProduct.getOrder().getId());
		assertThat(foundOrder.getProducts()).containsExactly(createdProduct);
	}

	@Test
	public void testAddCreatedProductWithNull() {
		Order order = createTestOrder();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> order.addProduct(null));
	}

	@Test
	public void testRemoveCreatedProduct() {
		CreatedProduct createdProduct = createCreatedProductWithOrder();
		System.out.println(createdProduct.toString());
		Long id = createdProduct.getOrder().getId();
		Order foundOrder = orderDao.findById(id);
		System.out.println(foundOrder.getProducts().toString());
		System.out.println(foundOrder.toString());
		foundOrder.removeProduct(createdProduct);
		foundOrder = orderDao.findById(id);
		System.out.println(foundOrder.getProducts().toString());
		System.out.println(foundOrder.toString());
		assertThat(foundOrder.getProducts()).doesNotContain(createdProduct);
	}

	@Test
	public void testRemoveCreatedProductWithNull() {
		Order order = createTestOrder();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> order.removeProduct(null));
	}
}
