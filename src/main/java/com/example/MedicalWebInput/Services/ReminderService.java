package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReminderService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private EmailService emailService;
    private Long patientId;
    @Autowired
    private PatientRepository patientRepository;

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
        List<Drug> drugs = new ArrayList<>();
        if (patientId != null) {
            List<LocalTime> scheduledTime = getScheduledTime(patientId);
            LocalTime currentTime = LocalTime.now();
            System.out.println("Scheduled time: \t" + scheduledTime + "Current Time: \t" + currentTime);
            for (LocalTime s : scheduledTime) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String now = currentTime.format(formatter);
                String setTime = s.format(formatter);
                if (now.equals(setTime)) {
                    //Should any of the time be reached
                    emailService.sendSimpleMessage(patientRepository.findById(patientId).get().getEmail(),"Drug Time",identifyDrugsTaken(now).toString());
//                    System.out.println(identifyDrugsTaken(now));
                }

            }
        }
    }

    //    Send a notification to the patient alerting them of their drug time
    public void sendMessage() {
//        Try and set alarm in their phones

    }

    //    Know which drugs have set off the alarm, allowing us to keep record
    public Map<String, String> identifyDrugsTaken(String now) {
        Map<String , String> drugInfo= new HashMap<>();
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientId);
        for (Schedule item : schedules) {
            String[] times = item.getTime();
            for (String time : times) {
                if (time.equals(now)) {
                    drugInfo.put(item.getDrug().getDrugName(),now);
                }
            }

        }
        return drugInfo;
    }

    //    Update the drug record by calling the record service
    public void updateDrugRecord() {
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

}
