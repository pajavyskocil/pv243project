package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.AttributeCategoryCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeCategoryDTO;
import cz.fi.muni.TACOS.dto.AttributeCreateDTO;
import cz.fi.muni.TACOS.dto.AttributeDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;
import cz.fi.muni.TACOS.facade.impl.AttributeCategoryFacadeImpl;
import cz.fi.muni.TACOS.facade.impl.AttributeFacadeImpl;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AttributeCategoryService;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
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
 * Test class for AttributeFacadeImpl
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
public class AttributeFacadeImplTest {

	@InjectMocks
	private AttributeFacade attributeFacade;

	private AttributeCategoryService attributeCategoryService = mock(AttributeCategoryService.class);

	private AttributeService attributeService = mock(AttributeService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	private Attribute attribute;
	private AttributeDTO attributeDTO;
	private Attribute secondAttribute;
	private AttributeDTO secondAttributeDTO;
	private AttributeCategory attributeCategory;
	private AttributeCategoryDTO attributeCategoryDTO;
	private AttributeCreateDTO attributeCreateDTO;

	@Before
	public void createEntities() {
		attributeCategory = EntityCreator.createTestAttributeCategory();
		secondAttribute = EntityCreator.createTestSecondAttribute();
		attributeCategoryDTO = EntityCreator.createTestAttributeCategoryDTO();
		secondAttributeDTO = EntityCreator.createTestSecondAttributeDTO();
		attribute = EntityCreator.createTestAttribute();
		attributeDTO = EntityCreator.createTestAttributeDTO();
		attributeCreateDTO = EntityCreator.createTestAttributeCreateDTO();

		attribute = EntityCreator.createTestAttribute();
	}

	@Before
	public void setFacade() {
		attributeFacade = new AttributeFacadeImpl(attributeService, attributeCategoryService, beanMappingService);
	}

	@After
	public void resetMock() {
		reset(attributeCategoryService);
		reset(attributeService);
		reset(beanMappingService);
	}

	@Test
	public void testFindById() {
		when(attributeService.findById(attribute.getId())).thenReturn(attribute);
		when(beanMappingService.mapTo(attribute, AttributeDTO.class)).thenReturn(attributeDTO);

		AttributeDTO foundAttributeDTO = attributeFacade.findById(attribute.getId());
		assertThat(foundAttributeDTO).isEqualToComparingFieldByField(attributeDTO);
	}

	@Test
	public void testGetAll() {
		when(attributeService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(attribute, secondAttribute)));
		when(beanMappingService.mapTo(any(), eq(AttributeDTO.class))).thenReturn(Arrays.asList(attributeDTO, secondAttributeDTO));

		assertThat(attributeFacade.getAll()).containsOnly(attributeDTO, secondAttributeDTO);
	}

	@Test
	public void testCreateAttributeWithAttributeCategory() throws Exception {
		when(beanMappingService.mapTo(attributeCreateDTO, Attribute.class)).thenReturn(attribute);
		when(attributeCategoryService.findById(attributeCategory.getId())).thenReturn(attributeCategory);

		doAnswer(invocation -> {
			attribute.setId(1L);
			return null;
		}).when(attributeService).create(attribute);

		doAnswer(invocation -> {
			attributeCategory.addAttribute(attribute);
			return null;
		}).when(attributeCategoryService).addAttribute(attributeCategory, attribute);

		attributeCreateDTO.setAttributeCategoryId(attributeCategory.getId());
		Long id = attributeFacade.create(attributeCreateDTO);

		verify(attributeService, times(1)).create(attribute);
		verify(attributeCategoryService, times(1)).addAttribute(attributeCategory, attribute);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testDeleteAttribute() {
		Long id = 1L;

		when(attributeService.findById(id)).thenReturn(attribute);

		attributeFacade.delete(id);

		verify(attributeService, times(1)).delete(attribute);
	}
}
