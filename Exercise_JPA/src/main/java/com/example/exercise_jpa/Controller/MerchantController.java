package com.example.exercise_jpa.Controller;

import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.Merchant;
import com.example.exercise_jpa.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        List<Merchant> merchants = merchantService.getAllMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant updatedMerchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = merchantService.updateMerchant(id, updatedMerchant);
        if ("Merchant not found.".equals(response)) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMerchant(@PathVariable Integer id) {
        boolean deleted = merchantService.deleteMerchant(id);
        if (!deleted) {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted successfully."));
    }

    @PutMapping("/activate/{id}/{userId}")
    public ResponseEntity<ApiResponse> activateMerchant(@PathVariable Integer id, @PathVariable Integer userId) {
        String response = merchantService.activateMerchant(id, userId);
        if (response.equals("Merchant not found.") || response.equals("Only admin users can activate merchants.")) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @PutMapping("/deactivate/{id}/{userId}")
    public ResponseEntity<ApiResponse> deactivateMerchant(@PathVariable Integer id, @PathVariable Integer userId) {
        String response = merchantService.deactivateMerchant(id, userId);
        if (response.equals("Merchant not found.") || response.equals("Only admin users can deactivate merchants.")) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }
}
