package com.marcuschiu.data.repository.jpa;

import com.marcuschiu.data.model.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO save delete update does not work
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findBySsn(String ssn);

    /**
     * @Modiying - Modifying queries can only use void or int/Integer as return type
     * @param ssn
     * @return
     */
    @Modifying
    @Query("delete from Employee e where e.ssn = ?1")
    Integer deleteBySsnQuery(String ssn);
}
