package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.DrugTimetableDto;
import com.example.MedicalWebInput.Models.DrugStock;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/medical/api")
public class StockController {
//    @GetMapping("/new_stock/{drugId}")
//    public String addNewDrugStock(@PathVariable Long drugId, @NotNull Model model, HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        if (session == null || session.getAttribute("patient_info") == null) {
//            // If the user is not logged in, redirect them to the login page
//            return "redirect:/medical/login";
//        }
//        Long scheduleId = scheduleService.getScheduleIdByDrugId(drugId);
//        DrugTimetableDto drugTimetableDto = new DrugTimetableDto();
//        drugTimetableDto.setScheduleId(scheduleId);
//        model.addAttribute("stock_info", drugTimetableDto);
//        model.addAttribute("patientDrug_info", scheduleService.getDrugInfo(scheduleId));
//
//        return "Schedule/stock_input";
//    }
//
//    @GetMapping("/edit_stock/{stockId}")
//    public String editStock(@PathVariable Long stockId, @NotNull Model model, @NotNull RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        if (session == null || session.getAttribute("patient_info") == null) {
//            // If the user is not logged in, redirect them to the login page
//            return "redirect:/medical/login";
//        }
//        // Confirm the stock id exists
//        DrugStock drugStock = scheduleService.getDrugStockById(stockId);
//        if (drugStock != null) {
////            when the existing stock is found
//            Long drugId = drugStock.getDrug().getId();
//            Long serviceId = scheduleService.getScheduleIdByDrugId(drugId);
//            DrugTimetableDto drugTimetableDto = scheduleService.convertStockToDto(drugStock);
//            model.addAttribute("stock_info", drugTimetableDto);
//            model.addAttribute("patientDrug_info", scheduleService.getDrugInfo(serviceId));
//            return "Schedule/stock_input";
//        }
//        redirectAttributes.addFlashAttribute("edit_error", "Error encountered during the edit");
//        return "redirect:/medical/patient_info";
//    }
//
//    @GetMapping("/delete_stock/{stockId}")
//    public String deleteStock(@PathVariable Long stockId, @NotNull Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        if (session == null || session.getAttribute("patient_info") == null) {
//            // If the user is not logged in, redirect them to the login page
//            return "redirect:/medical/login";
//        }
//        DrugStock drugStock = scheduleService.getDrugStockById(stockId);
//        scheduleService.deleteDrugStock(drugStock);
//        return "redirect:/medical/patient_info";
//    }
//    @PostMapping("/new_stock")
//    public String addDrugStock(@ModelAttribute DrugTimetableDto drugTimetableDto, @NotNull RedirectAttributes redirectAttributes) {
////        Ensure drug count is greater than 0
//        if (drugTimetableDto.getDrugCount() < 1) {
//            redirectAttributes.addFlashAttribute("stock_count_error", "Drug count is too low");
//            return "redirect:/medical/new_stock/" + scheduleService.getDrugId(drugTimetableDto);
//        }
////        New stock input
//        if (!scheduleService.checkIfStockExists(drugTimetableDto)) {
//            //        Ensure date input is not in the past
//            if (!scheduleService.checKDate(drugTimetableDto.getStartDate())) {
//                redirectAttributes.addFlashAttribute("stock_data_error", "Incorrect date set");
//                return "redirect:/medical/new_stock/" + scheduleService.getDrugId(drugTimetableDto);
//            }
//            scheduleService.addNewDrugStock(drugTimetableDto);
//            scheduleService.updateStartDate(drugTimetableDto);
//            scheduleService.setStockVisibility(scheduleService.getDrugStockId(drugTimetableDto.getScheduleId()));
//            return "redirect:/medical/patient_info";
//        }
////        Update stock
//        scheduleService.updateStockData(drugTimetableDto);
//        return "redirect:/medical/patient_info";
//    }
}
