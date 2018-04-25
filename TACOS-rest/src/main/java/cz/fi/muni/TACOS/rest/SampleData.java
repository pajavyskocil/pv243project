package cz.fi.muni.TACOS.rest;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.exceptions.InvalidRelationEntityIdException;
import cz.fi.muni.TACOS.facade.AttributeCategoryFacade;
import cz.fi.muni.TACOS.facade.AttributeFacade;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.facade.OrderFacade;
import cz.fi.muni.TACOS.facade.ProductCategoryFacade;
import cz.fi.muni.TACOS.facade.ProductFacade;
import cz.fi.muni.TACOS.facade.TemplateFacade;
import cz.fi.muni.TACOS.facade.UserFacade;
import cz.fi.muni.TACOS.enums.ProductAttributeStatus;
import cz.fi.muni.TACOS.enums.UserRole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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

	private Long userId1;
	private Long userId2;
	private Long userId3;
	private Long userId4;

	private Long cpId1;
	private Long cpId2;
	private Long cpId3;
	private Long cpId4;
	private Long templateId1;
	private Long templateId2;
	private Long templateId3;

	private Long productId1;
	private Long productId2;
	private Long productId3;
	private Long productId4;

	private Long pcId1;
	private Long pcId2;
	private Long pcId3;
	private Long pcId4;

	private Long attributeId1;
	private Long attributeId2;
	private Long attributeId3;
	private Long attributeId4;
	private Long attributeId5;

	private Long acId1;
	private Long acId2;
	private Long acId3;

	public void loadUserSampleData() {
		UserCreateDTO user = new UserCreateDTO();

		user.setName("Regular");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("somerandomregularuser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		userId1 = userFacade.create(user);

		user.setName("Name");
		user.setSurname("Surname");
		user.setPassword("password");
		user.setEmail("email@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		userId2 = userFacade.create(user);

		user.setName("Admin");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("ruser@worldofjava.com");
		user.setRole(UserRole.SUPERADMIN);
		userId3 = userFacade.create(user);

		user.setName("Practitioner");
		user.setSurname("User");
		user.setPassword("password");
		user.setEmail("somerandomregularuser@email.com");
		user.setRole(UserRole.PRACTITIONER);
		userId4 = userFacade.create(user);
	}

	public void loadCreatedProductSampleData() throws InvalidRelationEntityIdException {
		UserCreateDTO user = new UserCreateDTO();

		user.setName("RegularDDDD");
		user.setSurname("UserDDD");
		user.setPassword("passwordDDD");
		user.setEmail("somedfddaruser@worldofjava.com");
		user.setRole(UserRole.SUBMITTER);
		userFacade.create(user);

		UserDTO userDto = userFacade.findByEmail(user.getEmail());
		CreatedProductCreateDTO createdProduct = new CreatedProductCreateDTO();

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeId1, attributeId5)));
		createdProduct.setCount(10L);
		createdProduct.setDescription("createdProduct1desc");
		createdProduct.setProductId(productId1);
		cpId1 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeId2, attributeId5)));
		createdProduct.setCount(5L);
		createdProduct.setDescription("createdProduct 2 Description");
		createdProduct.setProductId(productId1);
		cpId2 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeId3, attributeId5)));
		createdProduct.setCount(3L);
		createdProduct.setDescription("createdProduct3desc");
		createdProduct.setProductId(productId1);
		cpId3 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(Collections.singleton(attributeId5));
		createdProduct.setCount(8L);
		createdProduct.setDescription("createdProduct4desc");
		createdProduct.setProductId(productId1);
		cpId4 = createdProductFacade.create(createdProduct, userDto.getId());
	}

	public void loadTemplateSampleData() throws InvalidRelationEntityIdException {
		TemplateCreateDTO template = new TemplateCreateDTO();

		template.setProductIds(Collections.singleton(productId1));
		template.setName("template");
		templateId1 = templateFacade.create(template);

		template.setProductIds(Collections.singleton(productId1));
		template.setName("T-shirt Template");
		templateId2 = templateFacade.create(template);

		template.setProductIds(Collections.singleton(productId2));
		template.setName("template1");
		templateId3 = templateFacade.create(template);
	}

	public void loadProductSampleData() throws InvalidRelationEntityIdException {
		ProductCreateDTO product = new ProductCreateDTO();

		product.setDescription("desc");
		product.setName("product1");
		product.setProductCategoryIds(new HashSet<>(Arrays.asList(pcId1, pcId2)));
		productId1 = productFacade.create(product);

		product.setDescription("description");
		product.setName("product1");
		product.setProductCategoryIds(new HashSet<>(Arrays.asList(pcId2, pcId3)));
		productId2 = productFacade.create(product);

		product.setDescription("desc");
		product.setName("PRODUCT");
		product.setProductCategoryIds(new HashSet<>(Arrays.asList(pcId4, pcId1)));
		productId3 = productFacade.create(product);

		product.setDescription("desc");
		product.setName("Product3");
		product.setProductCategoryIds(Collections.singleton(pcId1));
		productId4 = productFacade.create(product);
	}


	public void loadProductCategorySampleData() throws InvalidRelationEntityIdException {
		ProductCategoryCreateDTO category = new ProductCategoryCreateDTO();

		category.setName("T shirts");
		pcId1 = productCategoryFacade.create(category);

		category.setName("Trousers");
		pcId2 = productCategoryFacade.create(category);

		category.setName("Accessories");
		pcId3 = productCategoryFacade.create(category);

		category.setName("Shoes");
		pcId4 = productCategoryFacade.create(category);
	}

	public void loadAttributeSampleData() throws InvalidRelationEntityIdException {
		AttributeCreateDTO attribute = new AttributeCreateDTO();

		attribute.setAttributeCategoryId(acId1);
		attribute.setName("XS");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeId1 = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acId1);
		attribute.setName("S");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeId2 = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acId1);
		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeId3 = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acId1);
		attribute.setName("L");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.NOT_AVAIBLE);
		attributeId4 = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acId2);
		attribute.setName("Black");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.NOT_AVAIBLE);
		attributeId5 = attributeFacade.create(attribute);
	}

	public void loadAttributeCategorySampleData() throws InvalidRelationEntityIdException {
		AttributeCategoryCreateDTO category = new AttributeCategoryCreateDTO();

		category.setTemplateIds(Collections.singleton(templateId1));
		category.setName("Tshirt size");
		acId1 = attributeCategoryFacade.create(category);

		category.setTemplateIds(new HashSet<>(Arrays.asList(templateId1, templateId2)));
		category.setName("Tshirt color");
		acId2 = attributeCategoryFacade.create(category);
	}

	public void loadSampleData() throws InvalidRelationEntityIdException {
		loadProductCategorySampleData();
		loadProductSampleData();
		loadTemplateSampleData();
		loadAttributeCategorySampleData();
		loadAttributeSampleData();
		loadUserSampleData();
		loadCreatedProductSampleData();
	}
}
