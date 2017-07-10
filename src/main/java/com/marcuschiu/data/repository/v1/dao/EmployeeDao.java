package com.marcuschiu.data.repository.v1.dao;


import com.marcuschiu.data.model.entity.Employee;

import java.util.List;

/**
 * Created by marcus.chiu on 10/17/16.
 */
public interface EmployeeDao {

    Employee findOne(int id);

    void save(Employee employee);

    void deleteEmployeeBySsn(String ssn);

    List<Employee> findAll();

    Employee findOneBySsn(String ssn);

}
