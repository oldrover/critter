package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        saveEmployee(employee);

    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        DayOfWeek day = employeeRequestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();

        List<Employee> allEmployees = employeeRepository.findAll();

        List<Employee> availableEmployees = allEmployees
                .stream()
                .filter(employee -> employee.getDaysAvailable().contains(day) && employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());

        return availableEmployees;

    }
}
