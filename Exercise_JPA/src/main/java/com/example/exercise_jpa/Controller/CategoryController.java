package com.example.exercise_jpa.Controller;

import com.example.exercise_jpa.ApiResponse.ApiResponse;
import com.example.exercise_jpa.Model.Category;
import com.example.exercise_jpa.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer id, @RequestBody @Valid Category updatedCategory, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        String response = categoryService.updateCategory(id, updatedCategory);
        if ("Category not found.".equals(response)) {
            return ResponseEntity.status(400).body(new ApiResponse(response));
        }
        return ResponseEntity.status(200).body(new ApiResponse(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            return ResponseEntity.status(400).body(new ApiResponse("Category not found."));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully."));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse> countCategories() {
        int count = categoryService.countCategories();
        return ResponseEntity.status(200).body(new ApiResponse("Total categories: " + count));
    }
}
