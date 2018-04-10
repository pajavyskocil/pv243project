package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.dao.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
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
	private ProductDao productDao;

	@Inject
	private CreatedProductDao createdProductDao;

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.create(null));
	}

	@Test
	public void testCreate() {
		Order order = EntityCreator.createTestOrder(orderDao);
		Order foundOrder = orderDao.findById(order.getId());

		assertThat(foundOrder).isEqualToComparingFieldByField(order);
	}

	@Test
	public void testDeleteWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> orderDao.delete(null));
	}

	@Test
	public void testDelete() {
		Order order = EntityCreator.createTestOrder(orderDao);
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
		Order order = EntityCreator.createTestOrder(orderDao);
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
		Order order = EntityCreator.createTestOrder(orderDao);
		Order finishedOrder = EntityCreator.createFinishedOrder(orderDao);
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
		EntityCreator.createTestOrder(orderDao);

		Order finishedOrder = EntityCreator.createFinishedOrder(orderDao);
		List<Order> foundOrders = orderDao.getAllForState(OrderState.FINISHED);

		assertThat(foundOrders).containsOnly(finishedOrder);
	}

	@Test
	public void testGetAllForStateNothingFound() {
		EntityCreator.createTestOrder(orderDao);
		List<Order> foundOrders = orderDao.getAllForState(OrderState.FINISHED);
		assertThat(foundOrders).isEqualTo(new ArrayList<Order>());

	}

	@Test
	public void testAddCreatedProduct() {
		CreatedProduct createdProduct = EntityCreator
				.createCreatedProductWithOrder(productDao, orderDao, createdProductDao);
		Order foundOrder = orderDao.findById(createdProduct.getOrder().getId());
		assertThat(foundOrder.getProducts()).containsExactly(createdProduct);
	}

	@Test
	public void testAddCreatedProductWithNull() {
		Order order = EntityCreator.createTestOrder(orderDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> order.addProduct(null));
	}

	@Test
	public void testRemoveCreatedProduct() {
		CreatedProduct createdProduct = EntityCreator
				.createCreatedProductWithOrder(productDao, orderDao, createdProductDao);
		Long id = createdProduct.getOrder().getId();
		Order foundOrder = orderDao.findById(id);
		foundOrder.removeProduct(createdProduct);
		foundOrder = orderDao.findById(id);

		assertThat(foundOrder.getProducts()).doesNotContain(createdProduct);
	}

	@Test
	public void testRemoveCreatedProductWithNull() {
		Order order = EntityCreator.createTestOrder(orderDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> order.removeProduct(null));
	}
}
