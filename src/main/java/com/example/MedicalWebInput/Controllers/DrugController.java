package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<DrugDTO>> listAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<DrugDTO> getDrugDetails(@PathVariable Long id) {
        return ResponseEntity.ok(drugService.getDrugById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DrugDTO> addNewDrug(@RequestBody CreateDrugDTO createDrugDTO) {
        return ResponseEntity.ok(drugService.addNewDrug(createDrugDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DrugDTO> updateCurrentDrug(@PathVariable Long id, @RequestBody CreateDrugDTO createDrugDTO) {
        return ResponseEntity.ok(drugService.updateDrugDetails(id, createDrugDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDrugData(@PathVariable Long id) {
        drugService.deleteDrugById(id);
        return ResponseEntity.ok().build();
    }
}
