package cz.fi.muni.TACOS.rest;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.OrderCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.facade.AttributeCategoryFacade;
import cz.fi.muni.TACOS.facade.AttributeFacade;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.facade.OrderFacade;
import cz.fi.muni.TACOS.facade.ProductCategoryFacade;
import cz.fi.muni.TACOS.facade.ProductFacade;
import cz.fi.muni.TACOS.facade.TemplateFacade;
import cz.fi.muni.TACOS.facade.UserFacade;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.persistence.enums.ProductAttributeStatus;
import cz.fi.muni.TACOS.persistence.enums.UserRole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@ApplicationScoped
public class SampleData {

	@Inject
	private OrderFacade orderFacade;

	@Inject
	private UserFacade userFacade;

	@Inject
	private CreatedProductFacade createdProductFacade;

	@Inject
	private TemplateFacade templateFacade;

	@Inject
	private ProductCategoryFacade productCategoryFacade;

	@Inject
	private ProductFacade productFacade;

	@Inject
	private AttributeFacade attributeFacade;

	@Inject
	private AttributeCategoryFacade attributeCategoryFacade;


	public void loadOrderSampleData() {
		OrderCreateDTO order = new OrderCreateDTO();

		order.setState(OrderState.NEW);
		order.setPrice(BigDecimal.ZERO);
		orderFacade.create(order);

		order.setState(OrderState.SUBMITTED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.ONE);
		orderFacade.create(order);

		order.setState(OrderState.SUBMITTED);
		order.setPrice(BigDecimal.valueOf(10));
		orderFacade.create(order);

		order.setState(OrderState.FINISHED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.valueOf(10));
		orderFacade.create(order);
	}

	public void loadUserSampleData() {
		UserCreateDTO user = new UserCreateDTO();

		user.setName("Regular");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		userFacade.create(user);

		user.setName("Name");
		user.setSurname("Surname");
		user.setPassword("password");
		user.setEmail("email@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		userFacade.create(user);

		user.setName("Admin");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("ruser@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		userFacade.create(user);

		user.setName("Practitioner");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("somerandomregularuser@email.com");
		user.setRole(UserRole.PRACTITIONER);
		userFacade.create(user);

		user.setName("Submiter");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("email@email.com");
		user.setRole(UserRole.SUBMITTER);
		userFacade.create(user);
	}

	public void loadCreatedProductSampleData() {
		CreatedProductCreateDTO createdProduct = new CreatedProductCreateDTO();

		createdProduct.setCount(10L);
		createdProduct.setDescription("createdProduct1desc");
		createdProduct.setPrice(BigDecimal.valueOf(10));
		createdProductFacade.create(createdProduct);

		createdProduct.setCount(5L);
		createdProduct.setDescription("createdProduct 2 Description");
		createdProduct.setPrice(BigDecimal.valueOf(18));
		createdProductFacade.create(createdProduct);

		createdProduct.setCount(3L);
		createdProduct.setDescription("createdProduct3desc");
		createdProduct.setPrice(BigDecimal.valueOf(125));
		createdProductFacade.create(createdProduct);

		createdProduct.setCount(8L);
		createdProduct.setDescription("createdProduct4desc");
		createdProduct.setPrice(BigDecimal.valueOf(310));
		createdProductFacade.create(createdProduct);
	}

	public void loadTemplateSampleData() {
		TemplateCreateDTO template = new TemplateCreateDTO();

		template.setName("template");
		templateFacade.create(template);

		template.setName("T-shirt Template");
		templateFacade.create(template);

		template.setName("template1");
		templateFacade.create(template);

		template.setName("template2");
		templateFacade.create(template);

		template.setName("template3");
		templateFacade.create(template);
	}

	public void loadProductSampleData() {
		ProductCreateDTO product = new ProductCreateDTO();

		product.setDescription("desc");
		product.setName("product1");
		product.setMinimalPrice(BigDecimal.valueOf(10));
		productFacade.create(product);

		product.setDescription("description");
		product.setName("product1");
		product.setMinimalPrice(BigDecimal.valueOf(50));
		productFacade.create(product);

		product.setDescription("desc");
		product.setName("PRODUCT");
		product.setMinimalPrice(BigDecimal.valueOf(35));
		productFacade.create(product);

		product.setDescription("desc");
		product.setName("Product3");
		product.setMinimalPrice(BigDecimal.valueOf(5));
		productFacade.create(product);
	}


	public void loadProductCategorySampleData() {
		ProductCategoryCreateDTO category = new ProductCategoryCreateDTO();

		category.setName("category1");
		productCategoryFacade.create(category);

		category.setName("category2");
		productCategoryFacade.create(category);

		category.setName("category3");
		productCategoryFacade.create(category);

		category.setName("category4");
		productCategoryFacade.create(category);
	}

	public void loadAttributeSampleData() {
		AttributeCreateDTO attribute = new AttributeCreateDTO();

		attribute.setName("XS");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeFacade.create(attribute);

		attribute.setName("S");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeFacade.create(attribute);

		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeFacade.create(attribute);

		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.NOT_AVAIBLE);
		attributeFacade.create(attribute);
	}

	public void loadAttributeCategorySampleData() {
		AttributeCategoryCreateDTO category = new AttributeCategoryCreateDTO();

		category.setName("Tshirt size");
		attributeCategoryFacade.create(category);

		category.setName("Tshirt size");
		attributeCategoryFacade.create(category);

		category.setName("Tshirt size");
		attributeCategoryFacade.create(category);

		category.setName("Tshirt size");
		attributeCategoryFacade.create(category);
	}
}
