package com.example.exercise_jpa.Controller;

import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @RequestBody @Valid Product updatedProduct, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = productService.updateProduct(id, updatedProduct);
        if ("Product not found.".equals(response)) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
        boolean deleted = productService.deleteProduct(id);
        if (!deleted) {
            return ResponseEntity.status(400).body(new ApiResponse("Product not found."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully."));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.status(200).body(products);
    }
}
