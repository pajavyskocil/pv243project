package cz.fi.muni.TACOS.facadeImpl;

import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;
import cz.fi.muni.TACOS.facade.ProductFacade;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.ProductService;
import cz.fi.muni.TACOS.service.TemplateService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of ProductFacade Interface
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Transactional
@ApplicationScoped
public class ProductFacadeImpl implements ProductFacade {

    private final TemplateService templateService;

    private final ProductService productService;

    private final BeanMappingService beanMappingService;

    @Inject
    public ProductFacadeImpl(TemplateService templateService, ProductService productService, BeanMappingService beanMappingService) {
        this.templateService = templateService;
        this.productService = productService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void addTemplate(Long productId, Long templateId) {
        productService.addTemplate(productService.findById(productId), templateService.findById(templateId));
    }

    @Override
    public void removeTemplate(Long productId, Long templateId) {
        productService.removeTemplate(productService.findById(productId), templateService.findById(templateId));
    }

    @Override
    public Long create(ProductCreateDTO entity) {
        Product product = beanMappingService.mapTo(entity, Product.class);
        productService.create(product);
        return product.getId();
    }

    @Override
    public void delete(Long id) {
        productService.delete(productService.findById(id));
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productService.findById(id);
        return beanMappingService.mapTo(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        return beanMappingService.mapTo(productService.getAll(), ProductDTO.class);
    }
}
