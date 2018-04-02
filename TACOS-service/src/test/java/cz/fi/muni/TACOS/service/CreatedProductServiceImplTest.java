package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.dao.AttributeDao;
import cz.fi.muni.TACOS.persistence.dao.CreatedProductDao;
import cz.fi.muni.TACOS.persistence.entity.Attribute;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.service.Impl.CreatedProductServiceImpl;
import cz.fi.muni.TACOS.service.utils.EntityCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatedProductServiceImplTest {

	private CreatedProductDao createdProductDao = mock(CreatedProductDao.class);
	private AttributeDao attributeDao = mock(AttributeDao.class);

	private CreatedProduct product;
	private CreatedProduct secondProduct;
	private Attribute attribute;
	private Attribute secondAttribute;

	@InjectMocks
	private CreatedProductService createdProductService;

	@Before
	public void setCreatedProductService() {
		createdProductService = new CreatedProductServiceImpl(createdProductDao);
	}

	@Before
	public void setEntities() {
		product = EntityCreator.createCreatedProduct();
		secondProduct = EntityCreator.createSecondCreatedProduct();
		attribute = EntityCreator.createAttribute();
		secondAttribute = EntityCreator.createSecondAttribute();
	}

	@Test
	public void testCreate() {
		createdProductService.create(product);

		verify(createdProductDao, times(1)).create(product);
	}

	@Test
	public void testDelete() {
		createdProductService.delete(product);
		verify(createdProductDao, times(1)).delete(product);
	}

	@Test
	public void testFindById() {
		when(createdProductService.findById(product.getId())).thenReturn(product);

		CreatedProduct foundProduct = createdProductService.findById(product.getId());
		assertThat(foundProduct).isEqualTo(product);
	}

	@Test
	public void testGetAll() {
		when(createdProductService.getAll()).thenReturn(Arrays.asList(product, secondProduct));

		List<CreatedProduct> allProducts = createdProductService.getAll();
		assertThat(allProducts).containsOnly(product, secondProduct);
	}
}
