package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/api")
public class DrugController {
    @Autowired
    private DrugService drugService;

//    *************************************************************************
//    GetMappings
//    =========================================================================


    //    View All Drugs
    @GetMapping("/all_drugs")
    public ResponseEntity<?> listAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    @PostMapping("/create_drug")
    public ResponseEntity<?> addNewDrug(@RequestBody CreateDrugDTO createDrugDTO) {
        return ResponseEntity.ok(drugService.addNewDrug(createDrugDTO));
    }
}