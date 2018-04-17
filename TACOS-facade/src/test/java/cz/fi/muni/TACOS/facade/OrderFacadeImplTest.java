package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.OrderCreateDTO;
import cz.fi.muni.TACOS.dto.OrderDTO;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.facadeImpl.OrderFacadeImpl;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.CreatedProductService;
import cz.fi.muni.TACOS.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for OrderFacadeImpl
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class OrderFacadeImplTest {

	private OrderService orderService = mock(OrderService.class);

	private CreatedProductService createdProductService = mock(CreatedProductService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	@InjectMocks
	private OrderFacade orderFacade;

	private Order order;
	private Order secondOrder;
	private Order thirdOrder;
	private OrderDTO orderDTO;
	private OrderDTO secondOrderDTO;
	private OrderDTO thirdOrderDTO;
	private OrderCreateDTO orderCreateDTO;
	private CreatedProduct createdProduct;

	@Before
	public void setFacade(){
		orderFacade = new OrderFacadeImpl(orderService, createdProductService, beanMappingService);
	}

	@Before
	public void createEntities() {
		order = EntityCreator.createTestOrder();
		secondOrder = EntityCreator.createTestSecondOrder();
		thirdOrder = EntityCreator.createTestThirdOrder();
		orderDTO = EntityCreator.createTestOrderDTO();
		secondOrderDTO = EntityCreator.createTestSecondOrderDTO();
		thirdOrderDTO = EntityCreator.createTestThirdOrderDTO();
		orderCreateDTO = EntityCreator.createTestOrderCreateDTO();

		createdProduct = EntityCreator.createTestCreatedProduct();
	}

	@After
	public void resetMock() {
		reset(orderService);
		reset(createdProductService);
		reset(beanMappingService);
	}

	@Test
	public void testCreateOrder() {
		when(beanMappingService.mapTo(orderCreateDTO, Order.class)).thenReturn(order);

		doAnswer(invocation -> {
			order.setId(1L);
			return null;
		}).when(orderService).create(order);
		Long id = orderFacade.create(orderCreateDTO);

		verify(orderService, times(1)).create(order);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testDeleteOrder() {
		Long id = 1L;

		when(orderService.findById(id)).thenReturn(order);

		orderFacade.delete(id);

		verify(orderService, times(1)).delete(order);
	}

	@Test
	public void testFindById() {
		when(orderService.findById(order.getId())).thenReturn(order);
		when(beanMappingService.mapTo(order, OrderDTO.class)).thenReturn(orderDTO);

		OrderDTO foundOrderDTO = orderFacade.findById(order.getId());
		assertThat(foundOrderDTO).isEqualToComparingFieldByField(orderDTO);
	}

	@Test
	public void testGetAll() {
		when(orderService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(order, secondOrder, thirdOrder)));
		when(beanMappingService.mapTo(any(), eq(OrderDTO.class))).thenReturn(Arrays.asList(orderDTO, secondOrderDTO, thirdOrderDTO));

		assertThat(orderFacade.getAll()).containsOnly(orderDTO, secondOrderDTO, thirdOrderDTO);
	}

	@Test
	public void testGetAllForState() {
		when(orderService.getAllForState(secondOrder.getState())).thenReturn(Collections.unmodifiableList(Arrays.asList(secondOrder, thirdOrder)));
		when(beanMappingService.mapTo(any(), eq(OrderDTO.class))).thenReturn(Arrays.asList(secondOrderDTO, thirdOrderDTO));

		assertThat(orderFacade.getAll()).containsOnly(secondOrderDTO, thirdOrderDTO);
	}

	@Test
	public void testAddProduct() {
		when(orderService.findById(order.getId())).thenReturn(order);
		when(createdProductService.findById(createdProduct.getId())).thenReturn(createdProduct);

		orderFacade.addProduct(order.getId(), createdProduct.getId());

		verify(orderService, times(1)).addProduct(order, createdProduct);
	}

	@Test
	public void testRemoveProduct() {
		when(orderService.findById(order.getId())).thenReturn(order);
		when(createdProductService.findById(createdProduct.getId())).thenReturn(createdProduct);

		orderFacade.removeProduct(order.getId(), createdProduct.getId());

		verify(orderService, times(1)).removeProduct(order, createdProduct);
	}

	@Test
	public void testSubmitOrder() {
		when(orderService.findById(order.getId())).thenReturn(order);

		orderFacade.submitOrder(order.getId());

		verify(orderService, times(1)).submitOrder(order);
	}

	@Test
	public void testCancelOrder() {
		when(orderService.findById(order.getId())).thenReturn(order);

		orderFacade.cancelOrder(order.getId());

		verify(orderService, times(1)).cancelOrder(order);
	}

	@Test
	public void testFinishOrder() {
		when(orderService.findById(order.getId())).thenReturn(order);

		orderFacade.finishOrder(order.getId());

		verify(orderService, times(1)).finishOrder(order);
	}

	@Test
	public void testProcessOrder() {
		when(orderService.findById(order.getId())).thenReturn(order);

		orderFacade.processOrder(order.getId());

		verify(orderService, times(1)).processOrder(order);
	}


}
