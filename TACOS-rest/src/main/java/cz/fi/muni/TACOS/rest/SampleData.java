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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Base64;
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
	private Long templateWomenTshirtId;
	private Long templateMenTshirtId;
	private Long templateTrousersId;

	private Long productTshirtId1;
	private Long productTrousers2;
	private Long productShortsId3;
	private Long productTrainersId4;

	private Long pcTshirtsId;
	private Long pcTrousersId;
	private Long pcAccessoriesId;
	private Long pcShoesId;
	private Long pcClothesId;

	private Long attributeXSSizeId;
	private Long attributeSSizeId;
	private Long attributeMSizeId;
	private Long attributeLSizeId;
	private Long attributeGreenColorId;

	private Long acTshirtSizeId;
	private Long acTshirtColorId;
	private Long acId3;
	private Long attributeBlackColorId;
	private Long attributeWhiteColorId;

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

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeXSSizeId, attributeGreenColorId)));
		createdProduct.setCount(10L);
		createdProduct.setDescription("createdProduct1desc");
		createdProduct.setProductId(productTshirtId1);
		cpId1 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeSSizeId, attributeGreenColorId)));
		createdProduct.setCount(5L);
		createdProduct.setDescription("createdProduct 2 Description");
		createdProduct.setProductId(productTshirtId1);
		cpId2 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(new HashSet<>(Arrays.asList(attributeMSizeId, attributeGreenColorId)));
		createdProduct.setCount(3L);
		createdProduct.setDescription("createdProduct3desc");
		createdProduct.setProductId(productTshirtId1);
		cpId3 = createdProductFacade.create(createdProduct, userDto.getId());

		createdProduct.setAttributeIds(Collections.singleton(attributeGreenColorId));
		createdProduct.setCount(8L);
		createdProduct.setDescription("createdProduct4desc");
		createdProduct.setProductId(productTshirtId1);
		cpId4 = createdProductFacade.create(createdProduct, userDto.getId());
	}

	public void loadTemplateSampleData() throws InvalidRelationEntityIdException {
		TemplateCreateDTO template = new TemplateCreateDTO();

		template.setName("Women t-shirt");
		template.setProductIds(Collections.singleton(productTshirtId1));
		templateWomenTshirtId = templateFacade.create(template);

		template.setProductIds(Collections.singleton(productTshirtId1));
		template.setName("Men t-shirt");
		templateMenTshirtId = templateFacade.create(template);

		template.setProductIds(Collections.singleton(productTrousers2));
		template.setName("Trousers");
		templateTrousersId = templateFacade.create(template);
	}

	public void loadProductSampleData() throws InvalidRelationEntityIdException {
		ProductCreateDTO product = new ProductCreateDTO();

		product.setDescription("Common T-shirt.");
		product.setName("T-shirt");
		product.setImage(readImage("t-shirt.png"));
		product.setProductCategoryIds(Collections.singleton(pcTshirtsId));
		productTshirtId1 = productFacade.create(product);

		product.setDescription("Trousers made with great love.");
		product.setName("Trousers");
		product.setImage(null);
		product.setProductCategoryIds(Collections.singleton(pcTrousersId));
		productTrousers2 = productFacade.create(product);

		product.setDescription("Shorts used from bla bal.");
		product.setName("Shorts");
		product.setProductCategoryIds(Collections.singleton(pcTrousersId));
		productShortsId3 = productFacade.create(product);

		product.setDescription("Common Shoes");
		product.setName("Trainers");
		product.setProductCategoryIds(Collections.singleton(pcShoesId));
		productTrainersId4 = productFacade.create(product);
	}


	public void loadProductCategorySampleData() throws InvalidRelationEntityIdException {
		ProductCategoryCreateDTO category = new ProductCategoryCreateDTO();

		category.setName("Clothes");
		pcClothesId = productCategoryFacade.create(category);

		category.setName("Accessories");
		pcAccessoriesId = productCategoryFacade.create(category);

		category.setName("Shoes");
		pcShoesId = productCategoryFacade.create(category);

		category.setName("T shirts");
		category.setParentCategoryId(pcClothesId);
		pcTshirtsId = productCategoryFacade.create(category);

		category.setName("Trousers");
		category.setParentCategoryId(pcClothesId);
		pcTrousersId = productCategoryFacade.create(category);
	}

	public void loadAttributeSampleData() throws InvalidRelationEntityIdException {
		AttributeCreateDTO attribute = new AttributeCreateDTO();

		attribute.setAttributeCategoryId(acTshirtSizeId);
		attribute.setName("XS");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeXSSizeId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtSizeId);
		attribute.setName("S");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeSSizeId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtSizeId);
		attribute.setName("M");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeMSizeId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtSizeId);
		attribute.setName("L");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeLSizeId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtColorId);
		attribute.setName("Black");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeBlackColorId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtColorId);
		attribute.setName("Green");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeGreenColorId = attributeFacade.create(attribute);

		attribute.setAttributeCategoryId(acTshirtColorId);
		attribute.setName("White");
		attribute.setDescription("Random description(to be honest, not so random)");
		attribute.setPrice(BigDecimal.TEN);
		attribute.setStatus(ProductAttributeStatus.IN_STOCK);
		attributeWhiteColorId = attributeFacade.create(attribute);
	}

	public void loadAttributeCategorySampleData() throws InvalidRelationEntityIdException {
		AttributeCategoryCreateDTO category = new AttributeCategoryCreateDTO();

		category.setTemplateIds(new HashSet<>(Arrays.asList(templateWomenTshirtId, templateMenTshirtId)));
		category.setName("Tshirt size");
		acTshirtSizeId = attributeCategoryFacade.create(category);

		category.setTemplateIds(new HashSet<>(Arrays.asList(templateWomenTshirtId, templateMenTshirtId)));
		category.setName("Tshirt color");
		acTshirtColorId = attributeCategoryFacade.create(category);
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

	private byte[] readImage(String file) {
		try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
			int nRead;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			return Base64.getEncoder().encode(buffer.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
