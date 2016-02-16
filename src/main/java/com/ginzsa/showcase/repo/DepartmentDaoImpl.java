package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Department;
import com.google.inject.persist.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by santiago.ginzburg on 2/16/16.
 */
public class DepartmentDaoImpl extends AbstractDao<Department> implements DepartmentDao {

    public DepartmentDaoImpl() {
        super(Department.class);
    }

    public Department getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> departmentRoot = cq.from(Department.class);

        Predicate cond1 = cb.equal(departmentRoot.get("name"), name);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Department> getAll() {
        return super.findAll();
    }

    @Override
    public Department getById(Long id) {
        return super.find(id);
    }

    @Override
    @Transactional
    public void save(Department department) {
        super.create(department);
    }

    @Override
    @Transactional
    public void upadate(Department department) {
        super.update(department);
    }

    @Override
    @Transactional
    public void removeAll() {
        Query query = getEntityManager().createNativeQuery("DELETE FROM department");
        query.executeUpdate();
    }
}
