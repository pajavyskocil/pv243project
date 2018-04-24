package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.ProductCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;
import cz.fi.muni.TACOS.facade.impl.ProductCategoryFacadeImpl;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.ProductCategoryService;
import cz.fi.muni.TACOS.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;

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
 * Test class for ProductCategoryImpl
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public class ProductCategoryFacadeImplTest {

	@InjectMocks
	private ProductCategoryFacade productCategoryFacade;

	private ProductCategoryService productCategoryService = mock(ProductCategoryService.class);

	private ProductService productService = mock(ProductService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	private Product product;
	private ProductDTO productDTO;
	private ProductCategory productCategory;
	private ProductCategoryDTO productCategoryDTO;
	private ProductCategory secondProductCategory;
	private ProductCategoryDTO secondProductCategoryDTO;
	private ProductCategoryCreateDTO productCategoryCreateDTO;

	@Before
	public void createEntities() {
		product = EntityCreator.createTestProduct();
		productDTO = EntityCreator.createTestProductDTO();
		productCategory = EntityCreator.createTestProductCategory();
		productCategoryDTO = EntityCreator.createTestProductCategoryDTO();
		secondProductCategory = EntityCreator.createTestSecondProductCategory();
		secondProductCategoryDTO = EntityCreator.createTestSecondProductCategoryDTO();
		productCategoryCreateDTO = EntityCreator.createTestProductCategoryCreateDTO();
	}

	@Before
	public void setFacade() {
		productCategoryFacade = new ProductCategoryFacadeImpl(productCategoryService, productService, beanMappingService);
	}

	@After
	public void resetMock() {
		reset(productCategoryService);
		reset(productService);
		reset(beanMappingService);
	}

	@Test
	public void testFindById() {
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);
		when(beanMappingService.mapTo(productCategory, ProductCategoryDTO.class)).thenReturn(productCategoryDTO);

		ProductCategoryDTO foundCategoryDTO = productCategoryFacade.findById(productCategory.getId());
		assertThat(foundCategoryDTO).isEqualToComparingFieldByField(productCategoryDTO);
	}

	@Test
	public void testGetAll() {
		when(productCategoryService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(productCategory, secondProductCategory)));
		when(beanMappingService.mapTo(any(), eq(ProductCategoryDTO.class))).thenReturn(Arrays.asList(productCategoryDTO, secondProductCategoryDTO));

		assertThat(productCategoryFacade.getAll()).containsOnly(productCategoryDTO, secondProductCategoryDTO);
	}

	@Test
	public void testCreateWithPArentCategory() throws Exception {
		when(beanMappingService.mapTo(productCategoryCreateDTO, ProductCategory.class)).thenReturn(productCategory);
		when(productCategoryService.findById(secondProductCategory.getId())).thenReturn(secondProductCategory);

		doAnswer(invocation -> {
			productCategory.setId(1L);
			return null;
		}).when(productCategoryService).create(productCategory);

		doAnswer(invocation -> {
			secondProductCategory.addSubCategory(productCategory);
			return null;
		}).when(productCategoryService).addSubCategory(secondProductCategory, productCategory);

		productCategoryCreateDTO.setParentCategoryId(secondProductCategory.getId());
		Long id = productCategoryFacade.create(productCategoryCreateDTO);

		verify(productCategoryService, times(1)).create(productCategory);
		verify(productCategoryService, times(1)).addSubCategory(secondProductCategory, productCategory);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testCreateSubCategory() {
		when(beanMappingService.mapTo(productCategoryCreateDTO, ProductCategory.class)).thenReturn(productCategory);
		when(productCategoryService.findById(secondProductCategory.getId())).thenReturn(secondProductCategory);

		doAnswer(invocation -> {
			productCategory.setId(1L);
			return null;
		}).when(productCategoryService).create(productCategory);

		doAnswer(invocation -> {
			secondProductCategory.addSubCategory(productCategory);
			return null;
		}).when(productCategoryService).addSubCategory(secondProductCategory, productCategory);

		Long id = productCategoryFacade.createSubCategory(productCategoryCreateDTO, secondProductCategory.getId());

		verify(productCategoryService, times(1)).create(productCategory);
		verify(productCategoryService, times(1)).addSubCategory(secondProductCategory, productCategory);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testDeleteProductCategory() {
		Long id = 1L;

		when(productCategoryService.findById(id)).thenReturn(productCategory);

		productCategoryFacade.delete(id);

		verify(productCategoryService, times(1)).delete(productCategory);
	}

	@Test
	public void testAddProduct() {
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);
		when(productService.findById(product.getId())).thenReturn(product);

		productCategoryFacade.addProductToCategory(productCategory.getId(), product.getId());

		verify(productCategoryService, times(1)).addProductToCategory(productCategory, product);
	}

	@Test
	public void testRemoveProduct() {
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);
		when(productService.findById(product.getId())).thenReturn(product);

		productCategoryFacade.removeProductFromCategory(productCategory.getId(), product.getId());

		verify(productCategoryService, times(1)).removeProductFromCategory(productCategory, product);
	}

	@Test
	public void testAddSubCategory() {
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);
		when(productCategoryService.findById(secondProductCategory.getId())).thenReturn(secondProductCategory);

		productCategoryFacade.addSubCategory(productCategory.getId(), secondProductCategory.getId());

		verify(productCategoryService, times(1)).addSubCategory(productCategory, secondProductCategory);
	}

	@Test
	public void testRemoveSubCategory() {
		when(productCategoryService.findById(productCategory.getId())).thenReturn(productCategory);
		when(productCategoryService.findById(secondProductCategory.getId())).thenReturn(secondProductCategory);

		productCategoryFacade.removeSubCategory(productCategory.getId(), secondProductCategory.getId());

		verify(productCategoryService, times(1)).removeSubCategory(productCategory, secondProductCategory);
	}
}
