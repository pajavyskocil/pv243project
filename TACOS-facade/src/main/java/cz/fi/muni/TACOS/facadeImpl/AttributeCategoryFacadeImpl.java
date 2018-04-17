package cz.fi.muni.TACOS.facadeImpl;

import cz.fi.muni.TACOS.dto.AttributeCategoryDTO;
import cz.fi.muni.TACOS.dto.ProductCategoryDTO;
import cz.fi.muni.TACOS.facade.AttributeCategoryFacade;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.service.AttributeCategoryService;
import cz.fi.muni.TACOS.service.AttributeService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.ProductCategoryService;
import cz.fi.muni.TACOS.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of AttributeCategoryFacade Interface
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Transactional
@ApplicationScoped
public class AttributeCategoryFacadeImpl implements AttributeCategoryFacade {

    private final AttributeCategoryService attributeCategoryService;

    private final AttributeService attributeService;

    private final BeanMappingService beanMappingService;

    @Inject
    public AttributeCategoryFacadeImpl(AttributeCategoryService attributeCategoryService, AttributeService attributeService, BeanMappingService beanMappingService) {
        this.attributeCategoryService = attributeCategoryService;
        this.attributeService = attributeService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void addAttribute(Long categoryId, Long attributeId) {
        attributeCategoryService.addAttribute(attributeCategoryService.findById(categoryId), attributeService.findById(attributeId));
    }

    @Override
    public void removeAttribute(Long categoryId, Long attributeId) {
        attributeCategoryService.removeAttribute(attributeCategoryService.findById(categoryId), attributeService.findById(attributeId));
    }

    @Override
    public Long create(AttributeCategoryDTO entity) {
        AttributeCategory attributeCategory = beanMappingService.mapTo(entity, AttributeCategory.class);
        attributeCategoryService.create(attributeCategory);
        return attributeCategory.getId();
    }

    @Override
    public void delete(Long id) {
        attributeCategoryService.delete(attributeCategoryService.findById(id));
    }

    @Override
    public AttributeCategoryDTO findById(Long id) {
        AttributeCategory attributeCategory = attributeCategoryService.findById(id);
        return beanMappingService.mapTo(attributeCategory, AttributeCategoryDTO.class);
    }

    @Override
    public List<AttributeCategoryDTO> getAll() {
        return beanMappingService.mapTo(attributeCategoryService.getAll(), AttributeCategoryDTO.class);
    }
}
