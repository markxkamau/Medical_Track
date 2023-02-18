package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.DrugTimetableDto;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
import com.example.MedicalWebInput.Data.ScheduleDto.StockDto;
import com.example.MedicalWebInput.Models.DrugStock;
import com.example.MedicalWebInput.Services.ReminderService;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ReminderService reminderService;

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
            return "redirect:/patient/patient_info";

        }
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDrugId(drugId);
        scheduleDto.setPatientId(patientId);
        model.addAttribute("schedule_info", scheduleDto);
        model.addAttribute("patientDrug_info", scheduleService.getPatientAndDrugInfo(patientId, drugId));
        return "Schedule/schedule_input";
    }

    @GetMapping("/new_stock/{drugId}")
    public String addNewDrugStock(@PathVariable Long drugId, @NotNull Model model) {
        Long scheduleId = scheduleService.getScheduleByDrugId(drugId);
        DrugTimetableDto drugTimetableDto = new DrugTimetableDto();
        drugTimetableDto.setScheduleId(scheduleId);
        model.addAttribute("stock_info", drugTimetableDto);
        model.addAttribute("patientDrug_info", scheduleService.getDrugInfo(scheduleId));

        return "Schedule/stock_input";
    }

    @GetMapping("/edit_stock/{stockId}")
    public String editStock(@PathVariable Long stockId, @NotNull Model model, RedirectAttributes redirectAttributes) {
        // Confirm the stock id exists
        DrugStock drugStock = scheduleService.getDrugStockById(stockId);
        if (drugStock != null) {
//            when the existing stock is found
            Long drugId = drugStock.getDrug().getId();
            Long serviceId = scheduleService.getScheduleByDrugId(drugId);
            DrugTimetableDto drugTimetableDto = scheduleService.convertStockToDto(drugStock);
            model.addAttribute("stock_info", drugTimetableDto);
            model.addAttribute("patientDrug_info", scheduleService.getDrugInfo(serviceId));
            return "Schedule/stock_input";
        }
        redirectAttributes.addFlashAttribute("edit_error", "Error encountered during the edit");
        return "redirect:/patient/patient_info";
    }

    @GetMapping("/delete_stock/{stockId}")
    public String deleteStock(@PathVariable Long stockId, @NotNull Model model, RedirectAttributes redirectAttributes) {
        DrugStock drugStock = scheduleService.getDrugStockById(stockId);
        scheduleService.deleteDrugStock(drugStock);
        return "redirect:/patient/patient_info";
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
    public String addNewScheduleData(@NotNull RedirectAttributes redirectAttributes, @ModelAttribute ScheduleDto scheduleDto) {
        if (scheduleDto.getPatientId() == null || scheduleDto.getDrugId() == null || scheduleDto.getIntakes() == null || scheduleDto.getTime() == null) {
            redirectAttributes.addFlashAttribute("schedule_error", "One field has not yet been filled in");
            return "redirect:/schedule/new_schedule/" + scheduleDto.getPatientId() + "/" + scheduleDto.getDrugId();
        }
        if (!scheduleService.checkScheduleData(scheduleDto)) {
            redirectAttributes.addFlashAttribute("schedule_error", "Schedule already exists, Check Drug, Patient and Time");
            return "redirect:/schedule/new_schedule/" + scheduleDto.getPatientId() + "/" + scheduleDto.getDrugId();
        }
        if (!scheduleService.checkTime(scheduleDto.getTime())) {
            redirectAttributes.addFlashAttribute("time_error", "Time input is similar, please try again");
            return "redirect:/schedule/new_schedule/" + scheduleDto.getPatientId() + "/" + scheduleDto.getDrugId();
        }
        scheduleService.addNewScheduleData(scheduleDto);
        scheduleService.setVisibilityNone(scheduleDto.getDrugId());
        reminderService.setPatientId(scheduleDto.getPatientId());
        reminderService.sendDoseReminders();
        return "redirect:/patient/patient_info";

    }

    @PostMapping("/new_stock")
    public String addDrugStock(@NotNull Model model, @ModelAttribute DrugTimetableDto drugTimetableDto, @NotNull RedirectAttributes redirectAttributes) {
//        Ensure drug count is greater than 0
        if (drugTimetableDto.getDrugCount() < 1) {
            redirectAttributes.addFlashAttribute("stock_count_error", "Drug count is too low");
            return "redirect:/schedule/new_stock/" + scheduleService.getDrugId(drugTimetableDto);
        }
//        New stock input
        if (!scheduleService.checkIfStockExists(drugTimetableDto)) {
            //        Ensure date input is not in the past
            if (!scheduleService.checKDate(drugTimetableDto.getStartDate())) {
                redirectAttributes.addFlashAttribute("stock_data_error", "Incorrect date set");
                return "redirect:/schedule/new_stock/" + scheduleService.getDrugId(drugTimetableDto);
            }
            scheduleService.addNewDrugStock(drugTimetableDto);
            scheduleService.updateStartDate(drugTimetableDto);
            scheduleService.setStockVisibility(scheduleService.getDrugStockId(drugTimetableDto.getScheduleId()));
            return "redirect:/patient/patient_info";
        }
//        Update stock
        scheduleService.updateStockData(drugTimetableDto);
        return "redirect:/patient/patient_info";
    }

//    ------------------------------------------------------------------------

}
