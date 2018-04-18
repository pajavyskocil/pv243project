package cz.fi.muni.TACOS.facade.utils;

import cz.fi.muni.TACOS.dto.AttributeDTO;
import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductDTO;
import cz.fi.muni.TACOS.dto.OrderCreateDTO;
import cz.fi.muni.TACOS.dto.OrderDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;
import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.dto.UserUpdateDTO;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.persistence.enums.ProductAttributeStatus;
import cz.fi.muni.TACOS.persistence.enums.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class EntityCreator {

	//Users
	public static User createTestUser() {
		User user = new User();
		user.setId(1L);
		user.setName("Regular");
		user.setSurname("User");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setPasswordHash("passwordHash");
		user.setRole(UserRole.SUBMITTER);
		return user;
	}

	public static User createTestSecondUser() {
		User user = new User();
		user.setId(2L);
		user.setName("Second");
		user.setSurname("Regularuser");
		user.setEmail("secondrandomuserwithemail@worldofjava.com");
		user.setPasswordHash("passwordHash");
		user.setRole(UserRole.SUPERADMIN);
		return user;
	}

	//UserDTOs
	public static UserDTO createTestUserDTO() {
		UserDTO user = new UserDTO();
		user.setId(1L);
		user.setName("Regular");
		user.setSurname("User");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		return user;
	}

	public static UserDTO createTestSecondUserDTO() {
		UserDTO user = new UserDTO();
		user.setId(2L);
		user.setName("Second");
		user.setSurname("Regularuser");
		user.setEmail("secondrandomuserwithemail@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		return user;
	}

	//UserCreateDTO
	public static UserCreateDTO createTestUserCreateDTO() {
		UserCreateDTO user = new UserCreateDTO();
		user.setPassword("123");
		user.setName("Regular");
		user.setSurname("User");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		return user;
	}

	//UserUpdateDTO
	public static UserUpdateDTO createTestUserUpdateDTO() {
		UserUpdateDTO user = new UserUpdateDTO();
		user.setId(1L);
		user.setName("NewName");
		user.setSurname("User");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		return user;
	}

	//Orders
	public static Order createTestOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setState(OrderState.NEW);
		order.setPrice(BigDecimal.ONE);
		return order;
	}

	public static Order createTestSecondOrder() {
		Order order = new Order();
		order.setId(2L);
		order.setState(OrderState.PROCESSED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.valueOf(10));
		return order;
	}

	public static Order createTestThirdOrder() {
		Order order = new Order();
		order.setId(3L);
		order.setState(OrderState.PROCESSED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.valueOf(100));
		return order;
	}

	//OrderDTOs
	public static OrderDTO createTestOrderDTO() {
		OrderDTO order = new OrderDTO();
		order.setId(1L);
		order.setState(OrderState.NEW);
		order.setPrice(BigDecimal.ONE);
		return order;
	}

	public static OrderDTO createTestSecondOrderDTO() {
		OrderDTO order = new OrderDTO();
		order.setId(2L);
		order.setState(OrderState.PROCESSED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.valueOf(10));
		return order;
	}

	public static OrderDTO createTestThirdOrderDTO() {
		OrderDTO order = new OrderDTO();
		order.setId(3L);
		order.setState(OrderState.PROCESSED);
		order.setSubmitted(LocalDate.now());
		order.setFinished(LocalDate.now());
		order.setPrice(BigDecimal.valueOf(100));
		return order;
	}

	//OrderCreateDTOs
	public static OrderCreateDTO createTestOrderCreateDTO() {
		OrderCreateDTO order = new OrderCreateDTO();
		order.setState(OrderState.NEW);
		order.setPrice(BigDecimal.ONE);
		return order;
	}

	//CreatedProduct
	public static CreatedProduct createTestCreatedProduct() {
		CreatedProduct createdProduct = new CreatedProduct();
		createdProduct.setId(1L);
		createdProduct.setCount(1L);
		createdProduct.setDescription("desc");
		createdProduct.setPrice(BigDecimal.valueOf(20));
		return createdProduct;
	}

	public static CreatedProduct createTestSecondCreatedProduct() {
		CreatedProduct createdProduct = new CreatedProduct();
		createdProduct.setId(2L);
		createdProduct.setCount(2L);
		createdProduct.setDescription("desc");
		createdProduct.setPrice(BigDecimal.valueOf(10));
		return createdProduct;
	}

	//CreatedProductDTO
	public static CreatedProductDTO createTestCreatedProductDTO() {
		CreatedProductDTO createdProduct = new CreatedProductDTO();
		createdProduct.setId(1L);
		createdProduct.setCount(1L);
		createdProduct.setDescription("desc");
		createdProduct.setPrice(BigDecimal.valueOf(20));
		return createdProduct;
	}

	public static CreatedProductDTO createTestSecondCreatedProductDTO() {
		CreatedProductDTO createdProduct = new CreatedProductDTO();
		createdProduct.setId(2L);
		createdProduct.setCount(2L);
		createdProduct.setDescription("desc");
		createdProduct.setPrice(BigDecimal.valueOf(10));
		return createdProduct;
	}

	//CreatedProductCreateDTO
	public static CreatedProductCreateDTO createTestCreatedProductCreateDTO() {
		CreatedProductCreateDTO createdProduct = new CreatedProductCreateDTO();
		createdProduct.setCount(1L);
		createdProduct.setDescription("desc");
		createdProduct.setPrice(BigDecimal.valueOf(20));
		return createdProduct;
	}


	//Templates
	public static Template createTestTemplate() {
		Template template = new Template();
		template.setId(1L);
		template.setName("template");
		return template;
	}

	//TemplateDTO
	public static TemplateDTO createTestTemplateDTO() {
		TemplateDTO template = new TemplateDTO();
		template.setId(1L);
		template.setName("template");
		return template;
	}
	//TemplateCreateDTO



	//ProductCategories
	public static ProductCategory createProductCategory() {
		ProductCategory category = new ProductCategory();
		category.setId(1L);
		category.setName("categoryOne");
		return category;
	}

	public static ProductCategory createSecondProductCategory() {
		ProductCategory category = new ProductCategory();
		category.setId(2L);
		category.setName("categoryTwo");
		return category;
	}

	//Products
	public static Product createProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setDescription("desc");
		product.setName("productOne");
		product.setMinimalPrice(BigDecimal.valueOf(10));
		return product;
	}

	public static Product createSecondProduct() {
		Product product = new Product();
		product.setId(2L);
		product.setName("productName");
		product.setDescription("Description");
		product.setMinimalPrice(BigDecimal.valueOf(10.0));
		return product;
	}

	//AttributeCategories
	public static AttributeCategory createAttributeCategory() {
		AttributeCategory category = new AttributeCategory();
		category.setId(1L);
		category.setName("Tshirt size");
		return category;
	}

	//Attributes
	public static Attribute createTestAttribute() {
		Attribute attribute = new Attribute();
		attribute.setId(1L);
		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);

		return attribute;
	}
	public static Attribute createTestSecondAttribute() {
		Attribute attribute = new Attribute();
		attribute.setId(2L);
		attribute.setName("attribute");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.ONE);
		attribute.setStatus(ProductAttributeStatus.NOT_AVAIBLE);

		return attribute;
	}

	//AttributeDTOs
	public static AttributeDTO createTestAttributeDTO() {
		AttributeDTO attribute = new AttributeDTO();
		attribute.setId(1L);
		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);

		return attribute;
	}

	public static AttributeDTO createTestSecondAttributeDTO() {
		AttributeDTO attribute = new AttributeDTO();
		attribute.setId(2L);
		attribute.setName("attribute");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.ONE);
		attribute.setStatus(ProductAttributeStatus.NOT_AVAIBLE);

		return attribute;
	}
}
