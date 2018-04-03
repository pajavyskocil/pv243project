package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductTemplate;
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
	private AbstractDao<Product> productDao;

	@Inject
	private AbstractDao<ProductTemplate> productTemplateDao;

	@Inject
	private OrderDao orderDao;

	@Inject
	private AbstractDao<CreatedProduct> createdProductDao;

	public Product createTestProduct() {
		Product product = new Product();
		product.setName("productName");
		product.setDescription("Description");
		product.setPrice(BigDecimal.valueOf(10.0));
		productDao.create(product);
		return product;
	}

	public Product createTestSecondProduct() {
		Product product = new Product();
		product.setName("name");
		product.setDescription("Description");
		product.setPrice(BigDecimal.valueOf(10.0));
		productDao.create(product);
		return product;
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

	public ProductTemplate createTestTemplate() {
		ProductTemplate template = new ProductTemplate();
		template.setName("template");
		productTemplateDao.create(template);
		return template;
	}

	@Test
	public void testCreateWithNull() {
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> productDao.create(null));
	}

	@Test
	public void testCreate() {
		Product product = createTestProduct();
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
		Product product = createTestProduct();
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
		Product product = createTestProduct();
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
		Product p1 = createTestProduct();
		Product p2 = createTestSecondProduct();
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
		Product product = createTestProduct();
		ProductTemplate template = createTestTemplate();
		product.addTemplate(template);

		Product foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct.getTemplates()).containsExactly(template);
	}

	@Test
	public void testAddTemplateWithNull() {
		Product product = createTestProduct();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.addTemplate(null));
	}

	@Test
	public void testRemoveTemplate() {
		Product product = createTestProduct();
		ProductTemplate template = createTestTemplate();
		product.addTemplate(template);

		Product foundProduct = productDao.findById(product.getId());
		foundProduct.removeTemplate(template);

		foundProduct = productDao.findById(product.getId());
		assertThat(foundProduct.getTemplates()).doesNotContain(template);

	}

	@Test
	public void testRemoveTemplateWithNull() {
		Product product = createTestProduct();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.removeTemplate(null));
	}

	@Test
	public void testAddCreatedProduct() {
		Product product = createTestProduct();
		CreatedProduct createdProduct = createCreatedProductWithOrder();

		product.addCreatedProduct(createdProduct);
		Product foundProduct = productDao.findById(product.getId());

		assertThat(foundProduct.getCreatedProducts()).containsExactly(createdProduct);



	}

	@Test
	public void testAddCreatedProductWithNull() {
		Product product = createTestProduct();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.addCreatedProduct(null));
	}

	@Test
	public void testRemoveCreatedProduct() {
		Product product = createTestProduct();
		CreatedProduct createdProduct = createCreatedProductWithOrder();

		product.addCreatedProduct(createdProduct);
		Product foundProduct = productDao.findById(product.getId());
		foundProduct.removeCreatedProduct(createdProduct);
		foundProduct = productDao.findById(product.getId());

		assertThat(foundProduct.getCreatedProducts()).doesNotContain(createdProduct);

	}

	@Test
	public void testRemoveCreatedProductWithNull() {
		Product product = createTestProduct();
		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> product.removeCreatedProduct(null));
	}
}
