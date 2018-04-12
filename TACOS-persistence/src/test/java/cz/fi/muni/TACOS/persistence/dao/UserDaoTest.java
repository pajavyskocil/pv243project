package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.dao.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.User;
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

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> userDao.create(null));
	}

	@Test
	public void testCreate() {
		User user = EntityCreator.createTestUser(userDao, "A");
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
		User user = EntityCreator.createTestUser(userDao, "A");

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
		User user = EntityCreator.createTestUser(userDao, "A");

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
		User user = EntityCreator.createTestUser(userDao, "A");

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
		User user = EntityCreator.createTestUser(userDao, "A");
		User secondUser = EntityCreator.createTestUser(userDao, "B");

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
		EntityCreator.createTestUser(userDao, "A");
		User secondUser = EntityCreator.createTestUser(userDao, "B", UserRole.SUPERADMIN);
		List<User> foundUsers = userDao.getAllForRole(UserRole.SUPERADMIN);

		assertThat(foundUsers).containsOnly(secondUser);
	}

	@Test
	public void testGetAllForRoleNothingFound(){
		EntityCreator.createTestUser(userDao, "A");
		List<User> foundUsers = userDao.getAllForRole(UserRole.SUPERADMIN);

		assertThat(foundUsers).isEqualTo(new ArrayList<User>());
	}

	@Test
	public void testAddSubmittedOrder(){
		Order order = EntityCreator.createTestOrder(orderDao, userDao);
		User user = EntityCreator.createTestUser(userDao, "A");
		user.addSubmittedOrder(order);
		User foundUser = userDao.findById(user.getId());

		assertThat(foundUser.getOrders()).containsExactly(order);
	}

	@Test
	public void testAddSubmittedOrderWithNull(){
		User user = EntityCreator.createTestUser(userDao, "A");
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> user.addSubmittedOrder(null));
	}

	@Test
	public void testRemoveSubmittedOrder(){
		Order order = EntityCreator.createTestOrder(orderDao, userDao);
		User user = EntityCreator.createTestUser(userDao, "A");
		user.addSubmittedOrder(order);

		User foundUser = userDao.findById(user.getId());
		foundUser.removeSubmittedOrder(order);
		foundUser = userDao.findById(user.getId());

		assertThat(foundUser.getOrders()).doesNotContain(order);
	}

	@Test
	public void testRemoveSubmittedOrderWithNull(){
		User user = EntityCreator.createTestUser(userDao, "A");
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> user.removeSubmittedOrder(null));
	}
}
