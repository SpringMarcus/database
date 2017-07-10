package com.marcuschiu.data.repository.v1.dao_implementation;

import com.marcuschiu.data.model.entity.Employee;
import com.marcuschiu.data.repository.v1.dao.AbstractDao;
import com.marcuschiu.data.repository.v1.dao.EmployeeDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcus.chiu on 10/17/16.
 * @Repository - Spring 2.0 and later
 * indicates the class as a persistence stereotype (Data Access Object DAO)
 */
@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

    @Override
    public Employee findOne(int id) {
        //getByKey(int id) is defined in the AbstractDao abstract class
        return getByKey(id);
    }

    @Override
    public void save(Employee employee) {
        //persist(T employee) is defined in the AbstractDao abstract class
        persist(employee);
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        //getSession() is defined in the AbstractDao abstract class
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> findAll() {
        //Hibernate way using Criteria
        /*Criteria criteria = createEntityCriteria();
        return (List<Employee>) criteria.list();*/

        //Hibernate way using Query
        Query query = getSession().createQuery("from Employee");
        return (List<Employee>) query.list();

        //JPA way using Query
        //List<Employee> employees = entityManager.createQuery("SELECT e FROM employee e").getResultList();

        //failed JPA way using TypedQuery
        //need to configure EntityManagerFactory bean correctly
        /*TypedQuery<Employee> typedQuery = entityManager.createQuery("select t from Employee t", Employee.class);
        List<Employee> employees = typedQuery.getResultList();
        return employees;*/
    }

    @Override
    public Employee findOneBySsn(String ssn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", ssn));
        return (Employee) criteria.uniqueResult();
    }
}
