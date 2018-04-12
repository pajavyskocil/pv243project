package cz.fi.muni.TACOS.service.Impl;

import cz.fi.muni.TACOS.persistence.dao.Dao;
import cz.fi.muni.TACOS.persistence.dao.TemplateDao;
import cz.fi.muni.TACOS.persistence.entity.AttributeCategory;
import cz.fi.muni.TACOS.persistence.entity.Template;
import cz.fi.muni.TACOS.service.AbstractEntityService;
import cz.fi.muni.TACOS.service.TemplateService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
@Stateless
public class TemplateServiceImpl extends AbstractEntityService<Template> implements TemplateService {

    private final TemplateDao templateDao;

    @Inject
    public TemplateServiceImpl(TemplateDao templateDao) {
        super(templateDao);

        this.templateDao = templateDao;
    }

    @Override
    public void addAttributeCategory(Template template, AttributeCategory category) {
        template.addAttributeCategory(category);
    }

    @Override
    public void removeAttributeCategory(Template template, AttributeCategory category) {
        template.removeAttributeCategory(category);
    }
}
