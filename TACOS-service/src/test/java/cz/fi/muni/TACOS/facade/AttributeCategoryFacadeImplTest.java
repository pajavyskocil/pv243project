package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCategoryDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.facade.impl.AttributeCategoryFacadeImpl;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AttributeCategoryService;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
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
 * Test class for AttributeCategoryFacadeImpl
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public class AttributeCategoryFacadeImplTest {


	private AttributeCategoryService attributeCategoryService = mock(AttributeCategoryService.class);

	private AttributeService attributeService = mock(AttributeService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	private TemplateService templateService = mock(TemplateService.class);

	@InjectMocks
	private AttributeCategoryFacade attributeCategoryFacade;

	private Attribute attribute;
	private AttributeCategory attributeCategory;
	private AttributeCategory secondAttributeCategory;
	private AttributeCategoryDTO attributeCategoryDTO;
	private AttributeCategoryDTO secondAttributeCategoryDTO;
	private AttributeCategoryCreateDTO attributeCategoryCreateDTO;
	private Template template;


	@Before
	public void createEntities() {
		attributeCategory = EntityCreator.createTestAttributeCategory();
		secondAttributeCategory = EntityCreator.createTestSecondAttributeCategory();
		attributeCategoryDTO = EntityCreator.createTestAttributeCategoryDTO();
		secondAttributeCategoryDTO = EntityCreator.createTestSecondAttributeCategoryDTO();
		attributeCategoryCreateDTO = EntityCreator.createTestAttributeCategoryCreateDTO();
		template = EntityCreator.createTestTemplate();
		attribute = EntityCreator.createTestAttribute();
	}

	@Before
	public void setFacade() {
		attributeCategoryFacade = new AttributeCategoryFacadeImpl(attributeCategoryService, attributeService, beanMappingService, templateService);
	}

	@After
	public void resetMock() {
		reset(templateService);
		reset(attributeCategoryService);
		reset(attributeService);
		reset(beanMappingService);
	}

	@Test
	public void testCreateAttributeCategoryWithTemplate() throws Exception {
		when(beanMappingService.mapTo(attributeCategoryCreateDTO, AttributeCategory.class)).thenReturn(attributeCategory);
		when(templateService.findById(template.getId())).thenReturn(template);

		doAnswer(invocation -> {
			attributeCategory.setId(3L);
			return null;
		}).when(attributeCategoryService).create(attributeCategory);

		doAnswer(invocation -> {
			template.addAttributeCategory(attributeCategory);
			return null;
		}).when(templateService).addAttributeCategory(template, attributeCategory);

		Set<Long> templateIds = new HashSet<>();
		templateIds.add(template.getId());
		attributeCategoryCreateDTO.setTemplateIds(templateIds);
		Long id = attributeCategoryFacade.create(attributeCategoryCreateDTO);

		verify(attributeCategoryService, times(1)).create(attributeCategory);
		verify(templateService, times(1)).addAttributeCategory(template, attributeCategory);

		assertThat(id).isEqualTo(3L);
	}

	@Test
	public void testDeleteAttributeCategory() {
		Long id = 1L;

		when(attributeCategoryService.findById(id)).thenReturn(attributeCategory);

		attributeCategoryFacade.delete(id);

		verify(attributeCategoryService, times(1)).delete(attributeCategory);
	}

	@Test
	public void testFindById() {
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);
		when(beanMappingService.mapTo(attributeCategory, AttributeCategoryDTO.class)).thenReturn(attributeCategoryDTO);

		AttributeCategoryDTO foundAttributeCategoryDTO = attributeCategoryFacade.findById(attributeCategory.getId());
		assertThat(foundAttributeCategoryDTO).isEqualToComparingFieldByField(attributeCategoryDTO);
	}

	@Test
	public void testGetAll() {
		when(attributeCategoryService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(attributeCategory, secondAttributeCategory)));
		when(beanMappingService.mapTo(any(), eq(AttributeCategoryDTO.class))).thenReturn(Arrays.asList(attributeCategoryDTO, secondAttributeCategoryDTO));

		assertThat(attributeCategoryFacade.getAll()).containsOnly(attributeCategoryDTO, secondAttributeCategoryDTO);
	}

	@Test
	public void testAddAttribute() {
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);
		when(attributeService.findById(attribute.getId())).thenReturn(attribute);

		attributeCategoryFacade.addAttribute(attributeCategory.getId(), attribute.getId());

		verify(attributeCategoryService, times(1)).addAttribute(attributeCategory, attribute);
	}

	@Test
	public void testRemoveAttribute() {
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);
		when(attributeService.findById(attribute.getId())).thenReturn(attribute);

		attributeCategoryFacade.removeAttribute(attributeCategory.getId(), attribute.getId());

		verify(attributeCategoryService, times(1)).removeAttribute(attributeCategory, attribute);
	}
}
