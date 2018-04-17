package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductDTO;
import cz.fi.muni.TACOS.facade.utils.EntityCreator;
import cz.fi.muni.TACOS.facadeImpl.CreatedProductFacadeImpl;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.CreatedProductService;
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
 * Test class for CreatedProductFacadeImpl
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class CreatedProductFacadeImplTest {

	private CreatedProductService createdProductService = mock(CreatedProductService.class);

	private AttributeService attributeService = mock(AttributeService.class);

	private BeanMappingService beanMappingService = mock(BeanMappingService.class);

	@InjectMocks
	private CreatedProductFacade createdProductFacade;

	private CreatedProduct createdProduct;
	private CreatedProduct secondCreatedProduct;
	private CreatedProductDTO createdProductDTO;
	private CreatedProductDTO secondCreatedProductDTO;
	private CreatedProductCreateDTO createdProductCreateDTO;
	private Attribute attribute;

	@Before
	public void setFacade(){
		createdProductFacade = new CreatedProductFacadeImpl(createdProductService, attributeService, beanMappingService);
	}

	@Before
	public void createEntities() {
		createdProduct = EntityCreator.createTestCreatedProduct();
		secondCreatedProduct = EntityCreator.createTestSecondCreatedProduct();
		createdProductDTO = EntityCreator.createTestCreatedProductDTO();
		secondCreatedProductDTO = EntityCreator.createTestSecondCreatedProductDTO();
		createdProductCreateDTO = EntityCreator.createTestCreatedProductCreateDTO();

		attribute = EntityCreator.createTestAttribute();
	}

	@After
	public void resetMock() {
		reset(createdProductService);
		reset(attributeService);
		reset(beanMappingService);
	}

	public void testCreateOrder() {
		when(beanMappingService.mapTo(createdProductCreateDTO, CreatedProduct.class)).thenReturn(createdProduct);

		doAnswer(invocation -> {
			createdProduct.setId(1L);
			return null;
		}).when(createdProductService).create(createdProduct);
		Long id = createdProductFacade.create(createdProductCreateDTO);

		verify(createdProductService, times(1)).create(createdProduct);

		assertThat(id).isEqualTo(1L);
	}

	@Test
	public void testDeleteOrder() {
		Long id = 1L;

		when(createdProductService.findById(id)).thenReturn(createdProduct);

		createdProductFacade.delete(id);

		verify(createdProductService, times(1)).delete(createdProduct);
	}

	@Test
	public void testFindById() {
		when(createdProductService.findById(createdProduct.getId())).thenReturn(createdProduct);
		when(beanMappingService.mapTo(createdProduct, CreatedProductDTO.class)).thenReturn(createdProductDTO);

		CreatedProductDTO foundCreatedProductDTO = createdProductFacade.findById(createdProduct.getId());
		assertThat(foundCreatedProductDTO).isEqualToComparingFieldByField(createdProductDTO);
	}

	@Test
	public void testGetAll() {
		when(createdProductService.getAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(createdProduct, secondCreatedProduct)));
		when(beanMappingService.mapTo(any(), eq(CreatedProductDTO.class))).thenReturn(Arrays.asList(createdProductDTO, secondCreatedProductDTO));

		assertThat(createdProductFacade.getAll()).containsOnly(createdProductDTO, secondCreatedProductDTO);
	}

	@Test
	public void testAddProduct() {
		when(createdProductService.findById(createdProduct.getId())).thenReturn(createdProduct);
		when(attributeService.findById(attribute.getId())).thenReturn(attribute);

		createdProductFacade.addAttribute(createdProduct.getId(), attribute.getId());

		verify(createdProductService, times(1)).addAttribute(createdProduct, attribute);
	}

	@Test
	public void testRemoveProduct() {
		when(createdProductService.findById(createdProduct.getId())).thenReturn(createdProduct);
		when(attributeService.findById(attribute.getId())).thenReturn(attribute);

		createdProductFacade.removeAttribute(createdProduct.getId(), attribute.getId());

		verify(createdProductService, times(1)).removeAttribute(createdProduct, attribute);
	}

}
