package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCategoryDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryDTO;
import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;
import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;
import cz.fi.muni.TACOS.facade.impl.ProductFacadeImpl;
import cz.fi.muni.TACOS.facade.impl.TemplateFacadeImpl;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AttributeCategoryService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.ProductCategoryService;
import cz.fi.muni.TACOS.service.ProductService;
import cz.fi.muni.TACOS.service.TemplateService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for TemplateFacadeImpl
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public class TemplateFacadeImplTest {

	@InjectMocks
	private TemplateFacade templateFacade;

	private TemplateService templateService = mock(TemplateService.class);

	private AttributeCategoryService attributeCategoryService = mock(AttributeCategoryService.class);

	private ProductService productService  = mock(ProductService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	private Product product;
	private ProductDTO productDTO;
	private AttributeCategory attributeCategory;
	private AttributeCategoryDTO attributeCategoryDTO;
	private Template template;
	private TemplateDTO templateDTO;
	private Template secondTemplate;
	private TemplateDTO secondTemplateDTO;
	private TemplateCreateDTO templateCreateDTO;

	@Before
	public void createEntities() {
		product = EntityCreator.createTestProduct();
		productDTO = EntityCreator.createTestProductDTO();
		template = EntityCreator.createTestTemplate();
		template = EntityCreator.createTestTemplate();
		templateDTO = EntityCreator.createTestTemplateDTO();
		template = EntityCreator.createTestSecondTemplate();
		templateDTO = EntityCreator.createTestSecondTemplateDTO();
		templateCreateDTO = EntityCreator.createTestTemplateCreateDTO();
		attributeCategory = EntityCreator.createTestAttributeCategory();
		attributeCategoryDTO = EntityCreator.createTestAttributeCategoryDTO();
	}

	@Before
	public void setFacade() {
		templateFacade = new TemplateFacadeImpl(templateService, attributeCategoryService, productService, beanMappingService);
	}

	@Test
	public void testFindById() {
		when(templateService.findById(template.getId())).thenReturn(template);
		when(beanMappingService.mapTo(template, TemplateDTO.class)).thenReturn(templateDTO);

		TemplateDTO foundDTO = templateFacade.findById(template.getId());
		assertThat(foundDTO).isEqualToComparingFieldByField(templateDTO);
	}

	@Test
	public void testGetAll() {
		when(templateService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(template, secondTemplate)));
		when(beanMappingService.mapTo(any(), eq(TemplateDTO.class))).thenReturn(Arrays.asList(templateDTO, secondTemplateDTO));

		assertThat(templateFacade.getAll()).containsOnly(templateDTO, secondTemplateDTO);
	}

	@Test
	public void testDeleteProduct() {
		Long id = 1L;

		when(templateService.findById(id)).thenReturn(template);

		templateFacade.delete(id);

		verify(templateService, times(1)).delete(template);
	}

	@Test
	public void testAddAttributeCategory() {
		when(templateService.findById(product.getId())).thenReturn(template);
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);

		templateFacade.addAttributeCategory(template.getId(), attributeCategory.getId());

		verify(templateService, times(1)).addAttributeCategory(template, attributeCategory);
	}

	@Test
	public void testRemoveAttributeCategory() {
		when(templateService.findById(product.getId())).thenReturn(template);
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);

		templateFacade.removeAttributeCategory(template.getId(), attributeCategory.getId());

		verify(templateService, times(1)).removeAttributeCategory(template, attributeCategory);
	}

	@Test
	public void testCreateTemplateWithProduct() throws Exception {
		when(beanMappingService.mapTo(templateCreateDTO, Template.class)).thenReturn(template);
		when(productService.findById(product.getId())).thenReturn(product);

		doAnswer(invocation -> {
			template.setId(1L);
			return null;
		}).when(templateService).create(template);

		doAnswer(invocation -> {
			product.addTemplate(template);
			return null;
		}).when(productService).addTemplate(product, template);

		Set<Long> productIds = new HashSet<>();
		productIds.add(product.getId());
		templateCreateDTO.setProductIds(productIds);
		Long id = templateFacade.create(templateCreateDTO);

		verify(templateService, times(1)).create(template);
		verify(productService, times(1)).addTemplate(product, template);

		assertThat(id).isEqualTo(1L);
	}
}
