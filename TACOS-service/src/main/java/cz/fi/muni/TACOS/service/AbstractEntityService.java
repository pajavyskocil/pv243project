package cz.fi.muni.TACOS.service;


import cz.fi.muni.TACOS.persistence.dao.Dao;

import java.util.List;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public abstract class AbstractEntityService<T> implements EntityService<T> {

    private final Dao<T> dao;

    protected AbstractEntityService(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public void create(T entity) {
        dao.create(entity);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public T findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }
}
