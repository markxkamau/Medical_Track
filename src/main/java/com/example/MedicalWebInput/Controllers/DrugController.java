package com.example.MedicalWebInput.Controllers;


import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Services.DrugService;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/all_drugs")
    public ResponseEntity<?> listAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    //    View to add new drug
    @GetMapping("/drug/{drugId}")
    public ResponseEntity<?> getDrugDetails(@PathVariable Long drugId) {
        return ResponseEntity.ok(drugService.getDrugById(drugId));
    }


//    *************************************************************************
//    PostMappings
//    =========================================================================

    //    Create new drug by POST action
    @PostMapping("/new_drug")
    public ResponseEntity<?> addNewDrug(@RequestBody CreateDrugDTO createDrugDTO) {

        //TODO: Confirm data is viable
        drugService.checkDataViability(createDrugDTO);
        return ResponseEntity.ok(drugService.addNewDrug(createDrugDTO));
    }

//    *************************************************************************
//    PutMappings
//    =========================================================================

    @PutMapping("/update_drug")
    public ResponseEntity<?> updateCurrentDrug(@RequestBody CreateDrugDTO createDrugDTO) {

        //TODO: Confirm data is viable : Checking if it exists and proposing a post
        drugService.checkDataViability(createDrugDTO);
        return ResponseEntity.ok(drugService.addNewDrug(createDrugDTO));
    }

//    *************************************************************************
//    DeleteMappings
//    =========================================================================

    @DeleteMapping("/delete_drug")
    public ResponseEntity<?> deleteDrugData(@RequestParam("drugId") Long id){
        return ResponseEntity.ok(drugService.deleteDrugById(id));
    }
//    @DeleteMapping("/drug/delete_drug")
//    public ResponseEntity<DrugDao> deleteDrugById(@RequestParam("drugId") Long drugId) {
//        //// TODO: NEW DESIGN WORKING WITH THE DRUG ID _ CHECK THE SCHEDULE MEANS TO DELETE DRUG
//        patientService.deleteDrugById(drugId);
//        return ResponseEntity.ok(drugService.getDrugById(drugId));
//    }
//
//    @DeleteMapping("/drug/{patientId}/delete_drug")
//    public ResponseEntity<List<DrugDao>> deletePatientDrugByPatientId(@RequestParam("patientId") Long patientId) {
//        patientService.deleteDrugsByPatientId(patientId);
//        return ResponseEntity.ok(drugService.convertToDrugListDao(drugService.getListForPatient(patientService.getPatientById(patientId).getEmail())));
//    }


}


