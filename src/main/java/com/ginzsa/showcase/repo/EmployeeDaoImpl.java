package com.ginzsa.showcase.repo;

import com.ginzsa.showcase.model.Employee;
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
public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    public Employee getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);

        Predicate cond1 = cb.equal(employeeRoot.get("name"), name);
        cq.where(cond1);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Employee> getAll() {
        return super.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return super.find(id);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        super.create(employee);
    }

    @Override
    @Transactional
    public void upadate(Employee employee) {
        super.update(employee);
    }

    @Override
    @Transactional
    public void removeAll() {
        Query query = getEntityManager().createNativeQuery("DELETE FROM employee");
        query.executeUpdate();
    }
}
