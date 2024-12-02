package com.example.exercise_jpa.Controller;

import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.MerchantStock;
import com.example.exercise_jpa.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchant-stocks")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity<List<MerchantStock>> getAllMerchantStocks() {
        List<MerchantStock> stocks = merchantStockService.getAllMerchantStocks();
        return ResponseEntity.status(200).body(stocks);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = merchantStockService.addMerchantStock(merchantStock);
        if ("Merchant not found.".equals(response) || "Product not found.".equals(response)) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock updatedStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = merchantStockService.updateMerchantStock(id, updatedStock);
        if ("Merchant stock not found.".equals(response) || "Merchant not found.".equals(response) || "Product not found.".equals(response)) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMerchantStock(@PathVariable Integer id) {
        boolean deleted = merchantStockService.deleteMerchantStock(id);
        if (!deleted) {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant stock not found."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant stock deleted successfully."));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<MerchantStock>> getStocksByProduct(@PathVariable Integer productId) {
        List<MerchantStock> stocks = merchantStockService.getStocksByProduct(productId);
        return ResponseEntity.status(200).body(stocks);
    }
}
