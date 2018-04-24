package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.ProductCategoryDTO;
import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;
import cz.fi.muni.TACOS.facade.impl.ProductFacadeImpl;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.ProductCategoryService;
import cz.fi.muni.TACOS.service.ProductService;
import cz.fi.muni.TACOS.service.TemplateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
 * Test class for ProductFacadeImpl
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public class ProductFacadeImplTest {

	@InjectMocks
	private ProductFacade productFacade;

	private ProductCategoryService productCategoryService = mock(ProductCategoryService.class);

	private ProductService productService = mock(ProductService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	private TemplateService templateService = mock(TemplateService.class);

	private Product product;
	private ProductDTO productDTO;
	private ProductCategory productCategory;
	private ProductCategoryDTO productCategoryDTO;
	private Product secondProduct;
	private ProductDTO secondProductDTO;
	private ProductCreateDTO productCreateDTO;
	private Template template;

	@Before
	public void createEntities() {
		product = EntityCreator.createTestProduct();
		productDTO = EntityCreator.createTestProductDTO();
		productCategory = EntityCreator.createTestProductCategory();
		productCategoryDTO = EntityCreator.createTestProductCategoryDTO();
		secondProduct = EntityCreator.createTestSecondProduct();
		secondProductDTO = EntityCreator.createTestSecondProductDTO();
		productCreateDTO = EntityCreator.createTestProductCreateDTO();
		template = EntityCreator.createTestTemplate();
	}

	@Before
	public void setFacade() {
		productFacade = new ProductFacadeImpl(productService, beanMappingService, templateService, productCategoryService);
	}

	@After
	public void resetMock() {
		reset(templateService);
		reset(productCategoryService);
		reset(productService);
		reset(beanMappingService);
	}

	@Test
	public void testFindById() {
		when(productService.findById(product.getId())).thenReturn(product);
		when(beanMappingService.mapTo(product, ProductDTO.class)).thenReturn(productDTO);

		ProductDTO foundDTO = productFacade.findById(product.getId());
		assertThat(foundDTO).isEqualToComparingFieldByField(productDTO);
	}

	@Test
	public void testGetAll() {
		when(productService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(product, secondProduct)));
		when(beanMappingService.mapTo(any(), eq(ProductDTO.class))).thenReturn(Arrays.asList(productDTO, secondProductDTO));

		assertThat(productFacade.getAll()).containsOnly(productDTO, secondProductDTO);
	}

	@Test
	public void testDeleteProduct() {
		Long id = 1L;

		when(productService.findById(id)).thenReturn(product);

		productFacade.delete(id);

		verify(productService, times(1)).delete(product);
	}

	@Test
	public void testAddTemplate() {
		when(productService.findById(product.getId())).thenReturn(product);
		when(templateService.findById(template.getId())).thenReturn(template);

		productFacade.addTemplate(product.getId(), template.getId());

		verify(productService, times(1)).addTemplate(product, template);
	}

	@Test
	public void testRemoveTemplate() {
		when(productService.findById(product.getId())).thenReturn(product);
		when(templateService.findById(template.getId())).thenReturn(template);

		productFacade.removeTemplate(product.getId(), template.getId());

		verify(productService, times(1)).removeTemplate(product, template);
	}

	@Test
	public void testCreateProductWithProductCategory() throws Exception {
		when(beanMappingService.mapTo(productCreateDTO, Product.class)).thenReturn(product);
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);

		doAnswer(invocation -> {
			product.setId(1L);
			return null;
		}).when(productService).create(product);

		doAnswer(invocation -> {
			productCategory.addProduct(product);
			return null;
		}).when(productCategoryService).addProductToCategory(productCategory, product);

		Set<Long> productCategoryIds = new HashSet<>();
		productCategoryIds.add(productCategory.getId());
		productCreateDTO.setProductCategoryIds(productCategoryIds);
		Long id = productFacade.create(productCreateDTO);

		verify(productService, times(1)).create(product);
		verify(productCategoryService, times(1)).addProductToCategory(productCategory, product);

		assertThat(id).isEqualTo(1L);
	}
}
