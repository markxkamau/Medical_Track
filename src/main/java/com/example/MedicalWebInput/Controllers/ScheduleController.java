package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDto.DrugDto;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
import com.example.MedicalWebInput.Models.Drug;
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

    //    View to add new drug
    @GetMapping("/new_schedule/{patientId}/{drugId}")
    public String addNewSchedulr(@PathVariable Long patientId, @PathVariable Long drugId, @NotNull Model model) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDrugId(drugId);
        scheduleDto.setPatientId(patientId);
        model.addAttribute("schedule_info", scheduleDto);
        return "Schedule/schedule_input";
    }

    //    View to confirm drug
//    @GetMapping("/confirm_drug")
//    public String confirmDrugData(@NotNull Model model, @ModelAttribute Drug drug) {
//        model.addAttribute("drug_info", drug);
//        return "Drug/drug_confirm";
//    }

//    *************************************************************************
//    PostMappings
//    =========================================================================

    //    Create new drug by POST action
    @PostMapping
    public void addScheduleData(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.addNewScheduleData(scheduleDto);
    }

    //    *Drug Confirmation function
    @PostMapping("/new_schedule")
    public String addNewSCheduleData(@NotNull Model model, @ModelAttribute ScheduleDto scheduleDto) {
        if (!scheduleService.checkScheduleData(scheduleDto)) {
            model.addAttribute("schedule_info", scheduleDto);
            model.addAttribute("schedule_error", "Schedule already exists, Check Drug, Patient and Time");
            return "Schedule/schedule_input";
        }
        scheduleService.addNewScheduleData(scheduleDto);
        model.addAttribute("patient_data", scheduleService.getPatientById(scheduleDto.getPatientId()));
        model.addAttribute("drug_info", scheduleService.getDrugByPatientId(scheduleDto.getPatientId()));
        model.addAttribute("schedule_info", scheduleService.getScheduleByPatientId(scheduleDto.getPatientId()));
        return "HomePage";
    }
//    ------------------------------------------------------------------------

}
