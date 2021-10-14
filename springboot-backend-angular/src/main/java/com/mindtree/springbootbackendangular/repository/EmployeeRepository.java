package com.mindtree.springbootbackendangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springbootbackendangular.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
