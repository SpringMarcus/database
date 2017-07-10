package com.marcuschiu.service.implementation;

import com.google.common.collect.Lists;
import com.marcuschiu.data.model.entity.Employee;
import com.marcuschiu.data.repository.jpa.EmployeeRepository;
import com.marcuschiu.data.repository.v1.dao.EmployeeDao;
import com.marcuschiu.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marcus.chiu on 10/17/16.
 * @Service - indicates this class as a interfaces stereotype
 * @Transactional - starts a transaction on each method start and
 * commits it on each method exit (or rollback if method failed)
 */
@Service("employeeService")
@Transactional
public class EmployeeService implements IEmployeeService {

    // EmployeeDaoImpl bean is used
    // TODO EmployeeRepository class does not work yet
    @Autowired
    private EmployeeDao employeeRepository;

    @Override
    public Employee findById(int id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee) {
        System.out.println("EmployeeService.updateEmployee");
        Employee entity = employeeRepository.findOne(employee.getId());
        //Name embeddedName = entity.getNameTwo();

        if(entity != null) {
            entity.setName(employee.getName());
            entity.setJoiningDate(employee.getJoiningDate());
            entity.setSalary(employee.getSalary());
            entity.setSsn(employee.getSsn());
            entity.setText(employee.getText());
            entity.setBirthDate(employee.getBirthDate());
        }
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        employeeRepository.deleteEmployeeBySsn(ssn);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return  Lists.newArrayList(employeeRepository.findAll());
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return employeeRepository.findOneBySsn(ssn);
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = findEmployeeBySsn(ssn);
        return (employee == null || ((id != null) && (employee.getId() == id)));
    }
}
