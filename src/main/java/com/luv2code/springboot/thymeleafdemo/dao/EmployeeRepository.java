package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



    //Spring JPA will search for the pattern to create query
    //so the function name is a query: find all by order by lastname ascending
    public List<Employee> findAllByOrderByLastNameAsc();
}
