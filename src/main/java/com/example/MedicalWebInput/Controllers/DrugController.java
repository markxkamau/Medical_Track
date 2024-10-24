package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDao;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDto;
import com.example.MedicalWebInput.Services.DrugService;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/api")
public class DrugController {
    @Autowired
    private DrugService drugService;
    @Autowired
    private PatientService patientService;

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
    @PostMapping("/drug/add_drug")
    public ResponseEntity<DrugDao> addDrugData(@RequestBody DrugDto drugDto) {
        drugService.addNewDrugData(drugDto);
        return ResponseEntity.ok(drugService.convertDtoToDao(drugDto));
    }


//    *************************************************************************
//    PutMappings
//    =========================================================================
    @PutMapping("/drug/update_drug")
    public ResponseEntity<DrugDao> updateDrugData(@RequestBody DrugDto drugDto) {
        drugService.addNewDrugData(drugDto);
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


