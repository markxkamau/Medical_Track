package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.StockDto.CreateDrugStockDTO;
import com.example.MedicalWebInput.Data.StockDto.DrugStockDTO;
import com.example.MedicalWebInput.Models.DrugStock;
import com.example.MedicalWebInput.Repository.DrugStockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired private DrugStockRepository stockRepository;
    @Autowired private ModelMapper modelMapper;

    public List<DrugStockDTO> getAllStock() {
        return stockRepository.findAll().stream().map(s -> modelMapper.map(s, DrugStockDTO.class)).collect(Collectors.toList());
    }

    public DrugStockDTO createStock(CreateDrugStockDTO dto) {
        DrugStock stock = modelMapper.map(dto, DrugStock.class);
        DrugStock saved = stockRepository.save(stock);
        return modelMapper.map(saved, DrugStockDTO.class);
    }

    public DrugStockDTO updateStockQuantity(String medicationId, int addedQuantity) {
        DrugStock stock = stockRepository.findByMedicationId(medicationId);
        if (stock != null) {
            stock.setCurrentStock(stock.getCurrentStock() + addedQuantity);
            return modelMapper.map(stockRepository.save(stock), DrugStockDTO.class);
        }
        return null;
    }

    public DrugStockDTO updateStockById(Long stockId, Map<String, Object> updates) {
        DrugStock stock = stockRepository.findById(stockId).orElse(null);
        if (stock == null) {
            return null;
        }

        if (updates.containsKey("quantity")) {
            Object quantity = updates.get("quantity");
            if (quantity instanceof Number) {
                stock.setCurrentStock(((Number) quantity).intValue());
            } else if (quantity instanceof String) {
                try {
                    stock.setCurrentStock(Integer.parseInt((String) quantity));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        if (updates.containsKey("expiration")) {
            stock.setExpiration((String) updates.get("expiration"));
        }

        DrugStock updated = stockRepository.save(stock);
        return modelMapper.map(updated, DrugStockDTO.class);
    }
}