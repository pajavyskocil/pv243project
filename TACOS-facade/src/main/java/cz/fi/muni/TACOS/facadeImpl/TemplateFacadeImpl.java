package cz.fi.muni.TACOS.facadeImpl;

import cz.fi.muni.TACOS.dto.TemplateCreateDTO;
import cz.fi.muni.TACOS.dto.TemplateDTO;
import cz.fi.muni.TACOS.facade.TemplateFacade;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AttributeCategoryService;
import cz.fi.muni.TACOS.service.BeanMappingService;
import cz.fi.muni.TACOS.service.TemplateService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of TemplateFacade Interface
 *
 * @author Peter Balcirak <peter.balcirak@gmail.com>
 */
@Transactional
@ApplicationScoped
public class TemplateFacadeImpl implements TemplateFacade {

    private final TemplateService templateService;

    private final AttributeCategoryService attributeCategoryService;

    private final BeanMappingService beanMappingService;

    @Inject
    public TemplateFacadeImpl(TemplateService templateService, AttributeCategoryService attributeCategoryService, BeanMappingService beanMappingService) {
        this.templateService = templateService;
        this.attributeCategoryService = attributeCategoryService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void addAttributeCategory(Long templateId, Long categoryId) {
        templateService.addAttributeCategory(templateService.findById(templateId), attributeCategoryService.findById(categoryId));
    }

    @Override
    public void removeAttributeCategory(Long templateId, Long categoryId) {
        templateService.removeAttributeCategory(templateService.findById(templateId), attributeCategoryService.findById(categoryId));
    }

    @Override
    public Long create(TemplateCreateDTO entity) {
        Template template = beanMappingService.mapTo(entity, Template.class);
        templateService.create(template);
        return template.getId();
    }

    @Override
    public void delete(Long id) {
        templateService.delete(templateService.findById(id));
    }

    @Override
    public TemplateDTO findById(Long id) {
        Template template = templateService.findById(id);
        return beanMappingService.mapTo(template, TemplateDTO.class);
    }

    @Override
    public List<TemplateDTO> getAll() {
        return beanMappingService.mapTo(templateService.getAll(), TemplateDTO.class);
    }
}
