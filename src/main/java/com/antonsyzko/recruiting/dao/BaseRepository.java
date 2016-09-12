package com.antonsyzko.recruiting.dao;


import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.utils.ResultUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author ihor zadyra
 */

public class BaseRepository<E> {

    @PersistenceContext(unitName = "MyPersistenceUnit")
    protected EntityManager em;

    private Class<E> clazz;

    protected BaseRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public void add(E entity) {
        em.merge(entity);
    }

    @SuppressWarnings("unchecked")
    public E get(Long id) throws SingleResultNotFoundException {
        Query query = em.createQuery("select e from " + clazz.getName() + " e where e.id=:id").setParameter("id", id);
        return (E) ResultUtil.result(query);
    }

    public Long count() {
        Query emQuery = em.createQuery("select COUNT (e) from  " + clazz.getName() + " e");
        return (Long) emQuery.getResultList().get(0);
    }

    public List<E> getAll() {
        Query query = em.createQuery("select c from " + clazz.getName() + " c");
        return ResultUtil.resultList(query);
    }
}
