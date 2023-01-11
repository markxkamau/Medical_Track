package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

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
    public String addNewDrug(Model model) {
        model.addAttribute("drug_info", new Drug());
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
    public void addDrugData(@RequestBody Drug drug) {
        drugService.addNewDrugData(drug);
    }

    //    *Drug Confirmation function
    @PostMapping("/add_drug")
    public String addNewDrugData(@NotNull Model model, @ModelAttribute Drug drug) {
        if (!drugService.checkDrugData(drug)) {
            model.addAttribute("drug_info", drug);
            model.addAttribute("drug_error", "Drug stated already exists");
            return "Drug/drug_input";
        }
        addDrugData(drug);
        model.addAttribute("drug_info", drugService.getAllDrugs());
        return "Drug/drug_list";
    }
//    ------------------------------------------------------------------------

}
