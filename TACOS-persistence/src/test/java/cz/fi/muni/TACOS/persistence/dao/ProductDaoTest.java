package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.dao.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.Template;
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
 * Tests for ProductDao
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class ProductDaoTest {
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
	private ProductDao productDao;

	@Inject
	private UserDao userDao;

	@Inject
	private TemplateDao templateDao;

	@Inject
	private OrderDao orderDao;

	@Inject
	private CreatedProductDao createdProductDao;

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> productDao.create(null));
	}

	@Test
	public void testCreate() {
		Product product = EntityCreator.createTestProduct(productDao);
		Product foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct).isEqualToComparingFieldByField(product);
	}

	@Test
	public void testDeleteWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> productDao.delete(null));
	}

	@Test
	public void testDelete() {
		Product product = EntityCreator.createTestProduct(productDao);
		productDao.delete(product);
		Product foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct).isNull();
	}

	@Test
	public void testFindByIdWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> productDao.findById(null));
	}

	@Test
	public void testFindById() {
		Product product = EntityCreator.createTestProduct(productDao);
		Product foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct).isEqualToComparingFieldByField(product);
	}

	@Test
	public void testFindByIdNothingFound() {
		Product foundProduct = productDao.findById(1L);
		assertThat(foundProduct).isNull();
	}

	@Test
	public void testGetAll() {
		Product p1 = EntityCreator.createTestProduct(productDao);
		Product p2 = EntityCreator.createTestSecondProduct(productDao);
		List<Product> foundProducts = productDao.getAll();

		assertThat(foundProducts).containsExactly(p1, p2);
	}

	@Test
	public void testGetAllNothingFound() {
		List<Product> foundProducts = productDao.getAll();

		assertThat(foundProducts).isEqualTo(new ArrayList<Product>());
	}

	@Test
	public void testAddTemplate() {
		Product product = EntityCreator.createTestProduct(productDao);
		Template template = EntityCreator.createTestTemplate(templateDao);
		product.addTemplate(template);

		Product foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct.getTemplates()).containsExactly(template);
	}

	@Test
	public void testAddTemplateWithNull() {
		Product product = EntityCreator.createTestProduct(productDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.addTemplate(null));
	}

	@Test
	public void testRemoveTemplate() {
		Product product = EntityCreator.createTestProduct(productDao);
		Template template = EntityCreator.createTestTemplate(templateDao);
		product.addTemplate(template);

		Product foundProduct = productDao.findById(product.getId());
		foundProduct.removeTemplate(template);

		foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct.getTemplates()).doesNotContain(template);

	}

	@Test
	public void testRemoveTemplateWithNull() {
		Product product = EntityCreator.createTestProduct(productDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.removeTemplate(null));
	}

	@Test
	public void testAddCreatedProduct() {
		Product product = EntityCreator.createTestProduct(productDao);
		CreatedProduct createdProduct = EntityCreator
				.createCreatedProductWithOrder(productDao, orderDao, createdProductDao, userDao);

		product.addCreatedProduct(createdProduct);
		Product foundProduct = productDao.findById(product.getId());

		assertThat(foundProduct.getCreatedProducts()).containsExactly(createdProduct);



	}

	@Test
	public void testAddCreatedProductWithNull() {
		Product product = EntityCreator.createTestProduct(productDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.addCreatedProduct(null));
	}

	@Test
	public void testRemoveCreatedProduct() {
		Product product = EntityCreator.createTestProduct(productDao);
		CreatedProduct createdProduct = EntityCreator
				.createCreatedProductWithOrder(productDao, orderDao, createdProductDao, userDao);

		product.addCreatedProduct(createdProduct);
		Product foundProduct = productDao.findById(product.getId());
		foundProduct.removeCreatedProduct(createdProduct);
		foundProduct = productDao.findById(product.getId());

		assertThat(foundProduct.getCreatedProducts()).doesNotContain(createdProduct);

	}

	@Test
	public void testRemoveCreatedProductWithNull() {
		Product product = EntityCreator.createTestProduct(productDao);
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.removeCreatedProduct(null));
	}
}
