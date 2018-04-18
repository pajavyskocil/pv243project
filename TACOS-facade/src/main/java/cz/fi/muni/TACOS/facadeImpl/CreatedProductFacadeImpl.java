package cz.fi.muni.TACOS.facadeImpl;

import cz.fi.muni.TACOS.dto.CreatedProductCreateDTO;
import cz.fi.muni.TACOS.dto.CreatedProductDTO;
import cz.fi.muni.TACOS.facade.CreatedProductFacade;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.CreatedProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of CreatedProductFacade interface
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Transactional
@ApplicationScoped
public class CreatedProductFacadeImpl implements CreatedProductFacade {

	private final CreatedProductService createdProductService;

	private final AttributeService attributeService;

	private final BeanMappingService beanMappingService;

	@Inject
	public CreatedProductFacadeImpl(CreatedProductService createdProductService, AttributeService attributeService, BeanMappingService beanMappingService) {
		this.createdProductService = createdProductService;
		this.attributeService = attributeService;
		this.beanMappingService = beanMappingService;
	}

	@Override
	public Long create(CreatedProductCreateDTO entity) {
		CreatedProduct product = beanMappingService.mapTo(entity, CreatedProduct.class);
		createdProductService.create(product);
		return product.getId();
	}

	@Override
	public void delete(Long id) {
		createdProductService.delete(createdProductService.findById(id));
	}

	@Override
	public CreatedProductDTO findById(Long id) {
		return beanMappingService.mapTo(createdProductService.findById(id), CreatedProductDTO.class);
	}

	@Override
	public List<CreatedProductDTO> getAll() {
		return beanMappingService.mapTo(createdProductService.getAll(), CreatedProductDTO.class);
	}

	@Override
	public void addAttribute(Long createdProductId, Long attributeId) {
		createdProductService.addAttribute(createdProductService.findById(createdProductId), attributeService.findById(attributeId));
	}

	@Override
	public void removeAttribute(Long createdProductId, Long attributeId) {
		createdProductService.removeAttribute(createdProductService.findById(createdProductId), attributeService.findById(attributeId));
	}


}
