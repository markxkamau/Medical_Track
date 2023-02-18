package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.DrugStockRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReminderService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DrugStockRepository drugStockRepository;
    private Long patientid;

    public List<LocalTime> getScheduledTime(Long patientId) {
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientId);
        List<LocalTime> alarm = new ArrayList<>();
        for (Schedule s : schedules) {
            String[] times = s.getTime();

            for (String item : times) {
                LocalTime localTime = LocalTime.parse(item);
                if (!Arrays.asList(alarm).contains(item)) {
                    alarm.add(localTime);
                }
            }
        }

        return alarm;
    }

    @Scheduled(cron = "0 * * * * *") // runs every minute
    public void sendDoseReminders() {
        List<LocalTime> scheduledTime = getScheduledTime(patientid);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Scheduled time: \t"+scheduledTime+"Current Time: \t"+currentTime);
        for (LocalTime s : scheduledTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String now = currentTime.format(formatter);
            String setTime = s.format(formatter);
            if (now.equals(setTime)) {
                //Should any of the time be reached
                System.out.println("Time for Drugs");
            }
        }

    }

    public void setPatientId(long patientId){
        this.patientid = patientId;
    }

}
