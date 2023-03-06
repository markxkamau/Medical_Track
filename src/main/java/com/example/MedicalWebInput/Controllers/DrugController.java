package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDto.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Services.DrugService;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Services.ReminderService;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ReminderService reminderService;

    //    *************************************************************************
//    GetMappings
//    =========================================================================
    //    Get All Drugs
    @GetMapping
    public List<Drug> getAllDrugs() {
        return drugService.getAllDrugs();
    }

    //    View of Get All Drugs
    @GetMapping("/display")
    public String displayAllDrugs() {
        return "Drug/drug_list";
    }

    //    View to add new drug
    @GetMapping("/add_drug")
    public String addNewDrug(HttpServletRequest httpServletRequest, Model model) {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/patient/login";
        }
        BasicPatientDto patient = (BasicPatientDto) session.getAttribute("patient_info");
        DrugDto drugDto = new DrugDto();
        drugDto.setPatientId(patient.getId());
        model.addAttribute("drug_info", drugDto);
        return "Drug/drug_input";
    }

    //    View to confirm drug
    @GetMapping("/confirm_drug")
    public String confirmDrugData(@NotNull Model model, @ModelAttribute Drug drug) {
        model.addAttribute("drug_info", drug);
        return "Drug/drug_confirm";
    }

//    *************************************************************************
//    PostMappings
//    =========================================================================

    //    Create new drug by POST action
    @PostMapping
    public void addDrugData(@RequestBody DrugDto drug) {
        drugService.addNewDrugData(drug);
    }

    //    *Drug Confirmation function
    @PostMapping("/add_drug")
    public String addNewDrugData(@ModelAttribute DrugDto drug) {
        if (!drugService.checkDrugData(drug)) {
            drugService.updateDrugData(drug);
            return "redirect:/patient/patient_info";
        }
        drugService.addNewDrugData(drug);
        return "redirect:/patient/patient_info";
    }

    @PostMapping("/update")
    public String updateDrugData(@ModelAttribute Schedule schedule) {
        drugService.updateDrugData(schedule);
        scheduleService.updateScheduleData(schedule);
        return "redirect:/patient/patient_info";
    }
//    ------------------------------------------------------------------------

}
