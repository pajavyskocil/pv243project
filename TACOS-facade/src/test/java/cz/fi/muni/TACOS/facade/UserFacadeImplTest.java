package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.dto.UserUpdateDTO;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.facadeImpl.UserFacadeImpl;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.OrderService;
import cz.fi.muni.TACOS.service.UserService;
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
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class UserFacadeImplTest {

	private UserService userService = mock(UserService.class);

	private OrderService orderService = mock(OrderService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	@InjectMocks
	private UserFacade userFacade;

	private User user;
	private User secondUser;
	private UserDTO userDTO;
	private UserDTO secondUserDTO;
	private UserCreateDTO userCreateDTO;
	private UserUpdateDTO userUpdateDTO;
	private Order order;



	@Before
	public void setFacade(){
		userFacade = new UserFacadeImpl(userService, orderService, beanMappingService);
	}

	@Before
	public void createEntities() {
		user = EntityCreator.createTestUser();
		secondUser = EntityCreator.createTestSecondUser();
		userDTO = EntityCreator.createTestUserDTO();
		secondUserDTO = EntityCreator.createTestSecondUserDTO();
		userCreateDTO = EntityCreator.createTestUserCreateDTO();
		userUpdateDTO = EntityCreator.createTestUserUpdateDTO();

		order = EntityCreator.createTestOrder();
	}

	@After
	public void resetMock() {
		reset(userService);
		reset(orderService);
		reset(beanMappingService);
	}

	@Test
	public void testCreateUser() {
		when(beanMappingService.mapTo(userCreateDTO, User.class)).thenReturn(user);

		doAnswer(invocation -> {
			user.setId(1L);
			return null;
		}).when(userService).createUser(user);
		Long id = userFacade.create(userCreateDTO);

		verify(userService, times(1)).createUser(user);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testDeleteUser() {
		Long id = 1L;

		when(userService.findById(id)).thenReturn(user);

		userFacade.delete(id);

		verify(userService, times(1)).delete(user);
	}

	@Test
	public void testUpdateUser() {
		when(userService.findById(user.getId())).thenReturn(user);

		userFacade.update(userUpdateDTO);

		assertThat(user.getName()).isEqualTo(userUpdateDTO.getName());
	}

	@Test
	public void testFindById() {
		when(userService.findById(user.getId())).thenReturn(user);
		when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);

		UserDTO foundUserDTO = userFacade.findById(user.getId());
		assertThat(foundUserDTO).isEqualToComparingFieldByField(userDTO);
	}

	@Test
	public void testFindByEmail() {
		when(userService.findByEmail(user.getEmail())).thenReturn(user);
		when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);

		UserDTO foundUserDTO = userFacade.findByEmail(user.getEmail());
		assertThat(foundUserDTO).isEqualToComparingFieldByField(userDTO);
	}

	@Test
	public void testGetAll() {
		when(userService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(user, secondUser)));
		when(beanMappingService.mapTo(any(), eq(UserDTO.class))).thenReturn(Arrays.asList(userDTO, secondUserDTO));

		assertThat(userFacade.getAll()).containsOnly(userDTO, secondUserDTO);
	}

	@Test
	public void testGetAllForRole() {
		when(userService.getAllForRole(user.getRole())).thenReturn(Collections.unmodifiableList(Arrays.asList(user, secondUser)));
		when(beanMappingService.mapTo(any(), eq(UserDTO.class))).thenReturn(Arrays.asList(userDTO, secondUserDTO));

		assertThat(userFacade.getAllForRole(user.getRole())).containsOnly(userDTO, secondUserDTO);
	}

	@Test
	public void testAddOrder() {
		when(userService.findById(user.getId())).thenReturn(user);
		when(orderService.findById(order.getId())).thenReturn(order);

		userFacade.addOrder(user.getId(), order.getId());

		verify(userService, times(1)).addOrderAsSubmittedOrder(user, order);
	}

	@Test
	public void testRemoveOrder() {
		when(userService.findById(user.getId())).thenReturn(user);
		when(orderService.findById(order.getId())).thenReturn(order);

		userFacade.removeOrder(user.getId(), order.getId());

		verify(userService, times(1)).removeOrderFromSubmittedOrders(user, order);
	}
}
