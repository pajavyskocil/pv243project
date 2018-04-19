package cz.fi.muni.TACOS.facade.impl;

import cz.fi.muni.TACOS.dto.ProductCreateDTO;
import cz.fi.muni.TACOS.dto.ProductDTO;
import cz.fi.muni.TACOS.facade.ProductFacade;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.ProductCategoryService;
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
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Transactional
@ApplicationScoped
public class ProductFacadeImpl implements ProductFacade {

    private final TemplateService templateService;

    private final ProductService productService;

    private final BeanMappingService beanMappingService;

    private final ProductCategoryService productCategoryService;

    @Inject
    public ProductFacadeImpl(ProductService productService, BeanMappingService beanMappingService,
                             TemplateService templateService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.beanMappingService = beanMappingService;
        this.templateService = templateService;
        this.productCategoryService = productCategoryService;
    }

    @Override
    public Long create(ProductCreateDTO entity) {
        Product product = beanMappingService.mapTo(entity, Product.class);
        productService.create(product);

        for (Long id : entity.getProductCategories()) {
            ProductCategory category = productCategoryService.findById(id);
            productCategoryService.addProductToCategory(category, product);
        }

        return product.getId();
    }

    @Override
    public void addTemplate(Long productId, Long templateId) {
        productService.addTemplate(productService.findById(productId), templateService.findById(templateId));
        Product product = productService.findById(productId);
        Template template = templateService.findById(templateId);

        productService.addTemplate(product, template);
    }

    @Override
    public void removeTemplate(Long productId, Long templateId) {
        productService.removeTemplate(productService.findById(productId), templateService.findById(templateId));
        Product product = productService.findById(productId);
        Template template = templateService.findById(templateId);

        productService.removeTemplate(product, template);
    }

    @Override
    public void delete(Long id) {
        productService.delete(productService.findById(id));
        Product product = productService.findById(id);

        productService.delete(product);
    }

    @Override
    public ProductDTO findById(Long id) {
        return beanMappingService.mapTo(productService.findById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        return beanMappingService.mapTo(productService.getAll(), ProductDTO.class);
    }
}
