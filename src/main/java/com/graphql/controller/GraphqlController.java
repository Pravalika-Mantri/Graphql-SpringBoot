package com.graphql.controller;

import com.graphql.entity.Employee;
import com.graphql.entity.EmployeeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import com.graphql.repository.EmployeeRepo;

import java.util.List;

@RestController
@RequestMapping("employee")
public class GraphqlController {

    @Autowired
    private EmployeeRepo repo;

    @GetMapping
    public Employee findEmployees(@RequestBody int id){
        return repo.findById(id).get();
    }

    @PostMapping
    public List<Employee> saveEmployees(@RequestBody List<Employee> employees){
        return repo.saveAll(employees);
    }
    @QueryMapping
    Employee findEmployee(@Argument int id){
        return repo.findById(id).get();
    }

    @MutationMapping
    Employee saveEmployee(@Argument EmployeeRecord employee){
        Employee employee1=new Employee(employee.id(), employee.name(),employee.address(),employee.mobile());
       return repo.save(employee1);
    }

    @QueryMapping
    Employee saveEmp(@Argument EmployeeRecord employee){
        Employee employee1=new Employee(employee.id(), employee.name(),employee.address(),employee.mobile());
        return repo.save(employee1);
    }

    @MutationMapping
    String deleteEmployee(@Argument Integer id){
        repo.deleteById(id);
        return "deleted";
    }

}
