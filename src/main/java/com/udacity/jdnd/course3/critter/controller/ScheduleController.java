package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.saveSchedule(convertScheduleDTOToSchedule(scheduleDTO));
        return convertScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        scheduleService.getAllSchedules().forEach(schedule -> scheduleDTOs.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        scheduleService.getScheduleForPet(petId).forEach(schedule -> scheduleDTOs.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if(scheduleDTO.getPetIds() != null) {

            List<Pet> pets = petService.getAllPets()
                    .stream()
                    .filter(pet -> scheduleDTO.getPetIds().contains(pet.getId()))
                    .collect(Collectors.toList());

            schedule.setPets(pets);

        }
        if(scheduleDTO.getEmployeeIds()!= null) {

            List<Employee> employees = employeeService.getAllEmployees()
                    .stream()
                    .filter(employee -> scheduleDTO.getEmployeeIds().contains(employee.getId()))
                    .collect(Collectors.toList());

            schedule.setEmployees(employees);
        }

        return schedule;

    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Long> petIds = schedule.getPets()
                .stream()
                .map(Pet::getId)
                .collect(Collectors.toList());

        scheduleDTO.setPetIds(petIds);

        List<Long> employeeIds = schedule.getEmployees()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        scheduleDTO.setEmployeeIds(employeeIds);



        return  scheduleDTO;

    }
}
