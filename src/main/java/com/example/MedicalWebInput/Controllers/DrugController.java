package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDao;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Data.TestDto.TestDao;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Services.DrugService;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Services.ReminderService;
import com.example.MedicalWebInput.Services.ScheduleService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/medical/api")
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


    //    View All Drugs
    @GetMapping("/drugs")
    public ResponseEntity<List<DrugDao>> getAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugDao());
    }

    //    View to add new drug
    @GetMapping("/drugs/{patientId}")
    public ResponseEntity<List<DrugDao>> getDrugInfoByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(drugService.convertToDrugListDao(drugService.getDrugsForPatient(patientId)));
    }


//    *************************************************************************
//    PostMappings
//    =========================================================================

    //    Create new drug by POST action
    @PostMapping("/drug/{patientId}/add_drug")
    public ResponseEntity<DrugDao> addDrugData(@RequestBody DrugDto drugDto, @PathVariable Long patientId) {
        if (drugService.checkIfDrugExists(drugDto)){
            updateDrugData(drugDto,patientId);
        }
        drugService.addNewDrugData(drugDto);
        return ResponseEntity.ok(drugService.convertDtoToDao(drugDto));
    }


//    *************************************************************************
//    PutMappings
//    =========================================================================
    @PutMapping("/drug/{patientId}/update_drug")
    public ResponseEntity<DrugDao> updateDrugData(@RequestBody DrugDto drugDto, @PathVariable Long patientId) {
        if (!patientService.getDrugByPatientId(patientId).equals(null)){
            drugService.updateDrugData(drugDto);
            return ResponseEntity.ok(drugService.convertDtoToDao(drugDto));
        }
        return ResponseEntity.ok(drugService.convertDtoToDao(drugDto));
    }


//    *************************************************************************
//    DeleteMappings
//    =========================================================================
    @DeleteMapping("/drug/delete_drug")
    public ResponseEntity<DrugDao> deleteDrugById(@RequestParam("drugId") Long drugId) {
        patientService.deleteDrugById(drugId);
        return ResponseEntity.ok(drugService.getDrugById(drugId));
    }

    @DeleteMapping("/drug/{patientId}/delete_drug")
    public ResponseEntity<List<DrugDao>> deletePatientDrugByPatientId(@RequestParam("patientId") Long patientId) {
        patientService.deleteDrugsByPatientId(patientId);
        return ResponseEntity.ok(drugService.convertToDrugListDao(drugService.getListForPatient(patientService.getPatientById(patientId).getEmail())));
    }


}


