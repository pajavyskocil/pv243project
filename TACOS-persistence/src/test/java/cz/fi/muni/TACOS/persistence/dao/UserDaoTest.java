package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.persistence.enums.UserRole;
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
 * Tests for User Dao
 * @author Sassmann Vojtech <vojtech.sassmann@gmail.com>
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class UserDaoTest {

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
	private UserDao userDao;

	@Inject
	private OrderDao orderDao;

	private User createTestUser() {
		User user = new User();
		user.setName("Regular");
		user.setSurname("User");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		userDao.create(user);
		return user;
	}

	private User createTestSecondUser() {
		User user = new User();
		user.setName("Second");
		user.setSurname("Regularuser");
		user.setEmail("secondrandomuserwithemail@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		userDao.create(user);
		return user;
	}

	private Order createTestOrder() {
		Order order = new Order();
		order.setState(OrderState.NEW);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.ONE);
		orderDao.create(order);
		return order;
	}

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.create(null));
	}

	@Test
	public void testCreate() {
		User user = createTestUser();
		User foundUser = userDao.findById(user.getId());
		assertThat(foundUser).isEqualToComparingFieldByField(user);
	}

	@Test
	public void testDeleteWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.delete(null));
	}

	@Test
	public void testDelete() {
		User user = createTestUser();

		userDao.delete(user);

		User foundUser = userDao.findById(user.getId());
		assertThat(foundUser).isEqualTo(null);
	}

	@Test
	public void testFindByIdWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.delete(null));
	}

	@Test
	public void testFindById() {
		User user = createTestUser();

		User foundUser = userDao.findById(user.getId());

		assertThat(foundUser).isEqualToComparingFieldByField(user);
	}

	@Test
	public void testFindByIdNothingFound() {
		User foundUser = userDao.findById(1L);
		assertThat(foundUser).isEqualTo(null);
	}

	@Test
	public void testFindByIdNegativeId() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.findById(-1L));
	}

	@Test
	public void testFindByEmailWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.findByEmail(null));
	}

	@Test
	public void testFindByEmailWithEmptyValue() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.findByEmail(""));
	}

	@Test
	public void testFindByEmail() {
		User user = createTestUser();

		User foundUser = userDao.findByEmail(user.getEmail());

		assertThat(foundUser).isEqualToComparingFieldByField(user);
	}

	@Test
	public void testFindByEmailNothingFound() {
		User foundUser = userDao.findByEmail("randomfakeuserwithoutemail@worldwithoutjava.com");
		assertThat(foundUser).isEqualTo(null);
	}

	@Test
	public void testGetAll() {
		User user = createTestUser();
		User secondUser = createTestSecondUser();

		List<User> foundUsers = userDao.getAll();

		assertThat(foundUsers).containsExactly(user, secondUser);
	}

	@Test
	public void testGetAllNothingFound() {
		List<User> foundUsers = userDao.getAll();
		assertThat(foundUsers).isEmpty();
	}

	@Test
	public void testGetAllForRoleWithNull(){
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.getAllForRole(null));
	}

	@Test
	public void testGetAllForRole() {
		createTestUser();
		User secondUser = createTestSecondUser();
		List<User> foundUsers = userDao.getAllForRole(UserRole.SUPERADMIN);

		assertThat(foundUsers).containsOnly(secondUser);
	}

	@Test
	public void testGetAllForRoleNothingFound(){
		createTestUser();
		List<User> foundUsers = userDao.getAllForRole(UserRole.SUPERADMIN);

		assertThat(foundUsers).isEqualTo(new ArrayList<User>());
	}

	@Test
	public void testAddSubmittedOrder(){
		Order order = createTestOrder();
		User user = createTestUser();
		user.addSubmittedOrder(order);
		User foundUser = userDao.findById(user.getId());

		assertThat(foundUser.getSubmittedOrders()).containsExactly(order);
	}

	@Test
	public void testAddSubmittedOrderWithNull(){
		User user = createTestUser();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> user.addSubmittedOrder(null));
	}

	@Test
	public void testRemoveSubmittedOrder(){
		Order order = createTestOrder();
		User user = createTestUser();
		user.addSubmittedOrder(order);

		User foundUser = userDao.findById(user.getId());
		foundUser.removeSubmittedOrder(order);
		foundUser = userDao.findById(user.getId());

		assertThat(foundUser.getSubmittedOrders()).doesNotContain(order);
	}

	@Test
	public void testRemoveSubmittedOrderWithNull(){
		User user = createTestUser();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> user.removeSubmittedOrder(null));
	}
}
