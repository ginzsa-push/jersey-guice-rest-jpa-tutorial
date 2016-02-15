package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Showcase;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by santiago.ginzburg on 2/9/16.
 */
public class ShowcaseImplDao extends AbstractDao<Showcase> implements ShowcaseDao {

    public ShowcaseImplDao(){
        super(Showcase.class);
    }

    public Showcase getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Showcase> cq = cb.createQuery(Showcase.class);
        Root<Showcase> showcaseRoot = cq.from(Showcase.class);

        Predicate cond1 = cb.equal(showcaseRoot.get("name"), name);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Showcase> getAll() {
        return super.findAll();
    }

    @Override
    public Showcase getById(Long id) {
        return super.find(id);
    }

    @Override
    @Transactional
    public void save(Showcase showcase) {
       super.create(showcase);
    }
}