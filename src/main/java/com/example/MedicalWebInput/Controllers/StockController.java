package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.StockDto.CreateDrugStockDTO;
import com.example.MedicalWebInput.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/medical/api")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/all_stock")
    public ResponseEntity<?> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping("/create_stock")
    public ResponseEntity<?> createStock(@RequestBody CreateDrugStockDTO dto) {
        return ResponseEntity.ok(stockService.createStock(dto));
    }

    @PutMapping("/stock/{medicationId}")
    public ResponseEntity<?> updateStock(@PathVariable String medicationId, @RequestBody Map<String, Integer> body) {
        return ResponseEntity.ok(stockService.updateStockQuantity(medicationId, body.get("addedQuantity")));
    }

    @PutMapping("/stock/item/{stockId}")
    public ResponseEntity<?> patchStockItem(@PathVariable Long stockId, @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(stockService.updateStockById(stockId, body));
    }
}