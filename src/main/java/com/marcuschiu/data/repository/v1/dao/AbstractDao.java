package com.marcuschiu.data.repository.v1.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Generic class is the base class for all DAO implementation classes
 * Created by marcus.chiu on 10/17/16.
 */
public abstract class AbstractDao<PK extends Serializable, T> {
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * SessionFactory created during Application start from Persistence
     * will be used here
     */
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        //returns a Session object from the sessionFactory object bean
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * This method is used in child classes
     * @return Criteria
     */
    protected Criteria createEntityCriteria() {
        //getSession returns a Session object
        return getSession().createCriteria(persistentClass);
    }
}
