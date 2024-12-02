package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Category;
import com.example.exercise_jpa.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "Category added successfully.";
    }

    public String updateCategory(Integer id, Category updatedCategory) {
        Category category = findCategoryById(id);
        if (category == null) {
            return "Category not found.";
        }

        category.setName(updatedCategory.getName());
        categoryRepository.save(category);
        return "Category updated successfully.";
    }

    public boolean deleteCategory(Integer id) {
        Category category = findCategoryById(id);
        if (category == null) {
            return false;
        }

        categoryRepository.delete(category);
        return true;
    }

    public int countCategories() {
        return getCategories().size();
    }

    private Category findCategoryById(Integer id) {
        List<Category> categories = getCategories();
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }
}
