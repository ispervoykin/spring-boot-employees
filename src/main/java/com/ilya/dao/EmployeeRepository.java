package com.ilya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilya.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findAllByOrderByLastNameAsc();
}
