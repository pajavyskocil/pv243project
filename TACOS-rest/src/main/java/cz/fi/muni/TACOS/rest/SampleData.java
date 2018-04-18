package cz.fi.muni.TACOS.rest;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.facade.AttributeCategoryFacade;
import cz.fi.muni.TACOS.facade.AttributeFacade;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.facade.OrderFacade;
import cz.fi.muni.TACOS.facade.ProductCategoryFacade;
import cz.fi.muni.TACOS.facade.ProductFacade;
import cz.fi.muni.TACOS.facade.TemplateFacade;
import cz.fi.muni.TACOS.facade.UserFacade;
import cz.fi.muni.TACOS.persistence.enums.ProductAttributeStatus;
import cz.fi.muni.TACOS.persistence.enums.UserRole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

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
		UserCreateDTO user = new UserCreateDTO();

		user.setName("RegularDDDD");
		user.setSurname("UserDDD");
		user.setPassword("passwordDDD");
		user.setEmail("somedfddaruser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		userFacade.create(user);

		CreatedProductCreateDTO createdProduct = new CreatedProductCreateDTO();

		UserDTO userDto = userFacade.findByEmail(user.getEmail());
		createdProduct.setCount(10L);
		createdProduct.setDescription("createdProduct1desc");
		createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setCount(5L);
		createdProduct.setDescription("createdProduct 2 Description");
		createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setCount(3L);
		createdProduct.setDescription("createdProduct3desc");
		createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setCount(8L);
		createdProduct.setDescription("createdProduct4desc");
		createdProductFacade.create(createdProduct, userDto.getId());
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
		productFacade.create(product);

		product.setDescription("description");
		product.setName("product1");
		productFacade.create(product);

		product.setDescription("desc");
		product.setName("PRODUCT");
		productFacade.create(product);

		product.setDescription("desc");
		product.setName("Product3");
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
