package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.dao.CreatedProductDao;
import cz.fi.muni.TACOS.persistence.dao.OrderDao;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.service.Impl.OrderServiceImpl;
import cz.fi.muni.TACOS.service.utils.EntityCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class OrderServiceImplTest {

	private OrderDao orderDao = mock(OrderDao.class);
	private CreatedProductDao createdProductDao = mock(CreatedProductDao.class);

	private Order order;
	private Order secondOrder;
	private Order thirdOrder;
	private CreatedProduct product;
	private CreatedProduct secondProduct;


	@InjectMocks
	private OrderService orderService;

	@Before
	public void setOrderService() {
		orderService = new OrderServiceImpl(orderDao);
	}

	@Before
	public void setEntities(){
		order = EntityCreator.createTestOrder();
		secondOrder = EntityCreator.createSecondOrder();
		thirdOrder = EntityCreator.createThirdOrder();
		product = EntityCreator.createCreatedProduct();
		secondProduct = EntityCreator.createSecondCreatedProduct();
		order.addProduct(product);
	}

	@Test
	public void testCreate() {
		orderService.create(order);

		verify(orderDao, times(1)).create(order);
	}

	@Test
	public void testDelete() {
		orderService.delete(order);
		verify(orderDao, times(1)).delete(order);
	}

	@Test
	public void testFindById() {
		when(orderDao.findById(order.getId())).thenReturn(order);

		Order foundOrder = orderService.findById(order.getId());
		assertThat(foundOrder).isEqualTo(order);
	}

	@Test
	public void testGetAll() {
		when(orderDao.getAll()).thenReturn(Arrays.asList(order, secondOrder));

		List<Order> allOrders = orderService.getAll();
		assertThat(allOrders).containsOnly(order, secondOrder);
	}

	@Test
	public void testGetAllForState() {
		when(orderDao.getAllForState(order.getState())).thenReturn(Arrays.asList(order, secondOrder));

		List<Order> allProcessedOrders = orderService.getAllForState(order.getState());
		assertThat(allProcessedOrders).containsOnly(order, secondOrder);
	}

	@Test
	public void testAddProductToOrder() {
		when(orderDao.findById(order.getId())).thenReturn(order);
		when(createdProductDao.findById(secondProduct.getId())).thenReturn(secondProduct);

		orderService.addProduct(order, secondProduct);

		assertThat(order.getProducts()).containsOnly(product, secondProduct);
	}

	@Test
	public void testRemoveProductToOrder() {
		when(orderDao.findById(order.getId())).thenReturn(order);
		when(createdProductDao.findById(product.getId())).thenReturn(product);

		orderService.removeProduct(order, product);

		assertThat(order.getProducts()).isEmpty();
	}

	@Test
	public void testUpdatePrice() {
		when(orderDao.findById(order.getId())).thenReturn(order);
		when(createdProductDao.findById(secondProduct.getId())).thenReturn(secondProduct);

		orderService.addProduct(order, secondProduct);

		BigDecimal newValue = (product.getPrice().multiply(BigDecimal.valueOf(product.getCount())))
				.add(secondProduct.getPrice().multiply(BigDecimal.valueOf(secondProduct.getCount())));

		assertThat(order.getPrice()).isEqualTo(newValue);
	}

	@Test
	public void testSubmitOrder() {
		when(orderDao.findById(order.getId())).thenReturn(order);

		orderService.submitOrder(order);

		assertThat(order.getState()).isEqualTo(OrderState.SUBMITTED);
	}

	@Test
	public void testCancelOrder() {
		when(orderDao.findById(thirdOrder.getId())).thenReturn(thirdOrder);

		orderService.cancelOrder(order);

		assertThat(order.getState()).isEqualTo(OrderState.CANCELED);
	}

	@Test
	public void testFinishOrder() {
		when(orderDao.findById(thirdOrder.getId())).thenReturn(thirdOrder);

		orderService.finishOrder(thirdOrder);

		assertThat(thirdOrder.getState()).isEqualTo(OrderState.FINISHED);
	}

	@Test
	public void testProcessOrder() {
		when(orderDao.findById(thirdOrder.getId())).thenReturn(thirdOrder);

		orderService.processOrder(thirdOrder);

		assertThat(thirdOrder.getState()).isEqualTo(OrderState.PROCESSED);
	}
}
