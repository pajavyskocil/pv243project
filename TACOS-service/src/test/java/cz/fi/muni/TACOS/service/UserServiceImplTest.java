package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.dao.OrderDao;
import cz.fi.muni.TACOS.persistence.dao.UserDao;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.service.Impl.UserServiceImpl;
import cz.fi.muni.TACOS.service.utils.EntityCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class UserServiceImplTest {

	private UserDao userDao = mock(UserDao.class);
	private OrderDao orderDao = mock(OrderDao.class);

	private User user;
	private User secondUser;
	private Order order;
	private Order secondOrder;

	@InjectMocks
	private UserService userService;

	@Before
	public void setUserService() {
		userService = new UserServiceImpl(userDao);
	}
	@Before
	public void createEntities() {
		user = EntityCreator.createTestUser();
		secondUser = EntityCreator.createTestSecondUser();
		order = EntityCreator.createTestOrder();
		secondOrder = EntityCreator.createSecondOrder();
		user.addSubmittedOrder(secondOrder);
	}

	@Test
	public void testCreateUser() {
		userService.createUser(user);

		verify(userDao, times(1)).create(user);
	}

	@Test
	public void testDelete() {
		userService.delete(user);
		verify(userDao, times(1)).delete(user);
	}

	@Test
	public void testFindById() {
		when(userDao.findById(1L)).thenReturn(user);

		User foundUser = userService.findById(1L);
		assertThat(foundUser).isEqualTo(user);
	}

	@Test
	public void testFindByEmail() {
		when(userDao.findByEmail(user.getEmail())).thenReturn(user);

		User foundUser = userService.findByEmail(user.getEmail());
		assertThat(foundUser).isEqualTo(user);
	}

	@Test
	public void testGetAll() {
		when(userDao.getAll()).thenReturn(Arrays.asList(user, secondUser));

		List<User> allUsers = userService.getAll();
		assertThat(allUsers).containsOnly(user, secondUser);
	}

	@Test
	public void testGetAllForRole() {
		when(userDao.getAllForRole(user.getRole())).thenReturn(Collections.singletonList(user));

		List<User> allSubmitters = userService.getAllForRole(user.getRole());
		assertThat(allSubmitters).containsOnly(user);
	}

	@Test
	public void testAddOrderAsSubmittedOrder() {
		when(userDao.findById(user.getId())).thenReturn(user);
		when(orderDao.findById(order.getId())).thenReturn(order);

		userService.addOrderAsSubmittedOrder(user, order);

		assertThat(user.getOrders()).containsOnly(order, secondOrder);
	}

	@Test
	public void testRemoveOrderFromSubmittedOrders() {
		when(userDao.findById(user.getId())).thenReturn(user);
		when(orderDao.findById(order.getId())).thenReturn(order);

		userService.removeOrderFromSubmittedOrders(user, secondOrder);

		assertThat(user.getOrders()).isEmpty();

	}

}
