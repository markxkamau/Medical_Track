package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    //    *************************************************************************
//    GetMappings
//    =========================================================================
    //    Get All Schedules
    @GetMapping
    public List<ScheduleDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    //    View to add new Schedule
    @GetMapping("/new_schedule/{patientId}/{drugId}")
    public String addNewSchedule(@PathVariable Long patientId, @PathVariable Long drugId, @NotNull Model model) {
        if (patientId == null || drugId == null) {
            // return an error message or redirect the user to a page
            return "redirect:/patient/patient_info/"+patientId;

        }
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDrugId(drugId);
        scheduleDto.setPatientId(patientId);
        model.addAttribute("schedule_info", scheduleDto);
        model.addAttribute("patientDrug_info", scheduleService.getPatientAndDrugInfo(patientId, drugId));
        return "Schedule/schedule_input";
    }

//    *************************************************************************
//    PostMappings
//    =========================================================================

    //    Create new schedule by POST action
    @PostMapping
    public void addScheduleData(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.addNewScheduleData(scheduleDto);
    }

    //    Schedule Confirmation function
    @PostMapping("/new_schedule")
    public String addNewScheduleData(@NotNull Model model, @ModelAttribute ScheduleDto scheduleDto) {
        if (scheduleDto.getPatientId() == null || scheduleDto.getDrugId() == null || scheduleDto.getIntakes() == null || scheduleDto.getTime() == null) {
            model.addAttribute("schedule_info", scheduleDto);
            model.addAttribute("patientDrug_info", scheduleService.getPatientAndDrugInfo(scheduleDto.getPatientId(), scheduleDto.getDrugId()));
            model.addAttribute("schedule_error", "One field has not yet been filled in");
            return "Schedule/schedule_input";
        }
        if (!scheduleService.checkScheduleData(scheduleDto)) {
            model.addAttribute("schedule_info", scheduleDto);
            model.addAttribute("patientDrug_info", scheduleService.getPatientAndDrugInfo(scheduleDto.getPatientId(), scheduleDto.getDrugId()));
            model.addAttribute("schedule_error", "Schedule already exists, Check Drug, Patient and Time");
            return "Schedule/schedule_input";
        }
        if (!scheduleService.checkTime(scheduleDto.getTime())) {
            model.addAttribute("schedule_info", scheduleDto);
            model.addAttribute("patientDrug_info", scheduleService.getPatientAndDrugInfo(scheduleDto.getPatientId(), scheduleDto.getDrugId()));
            model.addAttribute("time_error", "Time input is similar, please try again");
            return "Schedule/schedule_input";
        }
        scheduleService.addNewScheduleData(scheduleDto);
        return "redirect:/patient/patient_info/"+scheduleDto.getPatientId();

    }
//    ------------------------------------------------------------------------

}
