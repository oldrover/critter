package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;


    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);

    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.findAll();

    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        return scheduleRepository.findAllByCustomer_Id(customerId);
    }
}
