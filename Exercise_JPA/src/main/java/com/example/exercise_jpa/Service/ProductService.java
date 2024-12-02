package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product added successfully.";
    }

    public String updateProduct(Integer id, Product updatedProduct) {
        Product product = findProductById(id);
        if (product == null) {
            return "Product not found.";
        }

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        productRepository.save(product);
        return "Product updated successfully.";
    }

    public boolean deleteProduct(Integer id) {
        Product product = findProductById(id);
        if (product == null) {
            return false;
        }

        productRepository.delete(product);
        return true;
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        List<Product> allProducts = getProducts();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategoryID().equals(categoryId)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    private Product findProductById(Integer id) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
