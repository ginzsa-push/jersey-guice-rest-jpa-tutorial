package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Showcase;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class ShowcaseImplDao implements ShowcaseDao {

    @PersistenceContext
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Inject
    public ShowcaseImplDao(EntityManager entityManager){
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    @Override
    public List<Showcase> getAll() {
        TypedQuery<Showcase> query = entityManager.createQuery("from com.ginzsa.showcase.model.Showcase", Showcase.class);
        entityTransaction.begin();
        List<Showcase> list = query.getResultList();
        entityTransaction.commit();
        return list;
    }

    @Override
    public Showcase getById(Long id) {

        entityTransaction.begin();
        List<Showcase> list  = entityManager
                .createQuery("from com.ginzsa.showcase.model.Showcase s where s.id = :id", Showcase.class)
                .setParameter("id", id)
                .getResultList();

        entityTransaction.commit();
        return list.isEmpty()? null: list.get(0);
    }

    @Override
    public void save(Showcase showcase) {
        entityTransaction.begin();
        entityManager.persist(showcase);
        entityTransaction.commit();
    }
}