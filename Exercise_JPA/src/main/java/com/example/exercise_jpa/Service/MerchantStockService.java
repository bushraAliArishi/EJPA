package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Merchant;
import com.example.exercise_jpa.Model.MerchantStock;
import com.example.exercise_jpa.Model.Product;
import com.example.exercise_jpa.Repository.MerchantRepository;
import com.example.exercise_jpa.Repository.MerchantStockRepository;
import com.example.exercise_jpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public String addMerchantStock(MerchantStock merchantStock) {
        Merchant merchant = findMerchantById(merchantStock.getMerchantID());
        if (merchant == null) {
            return "Merchant not found.";
        }

        Product product = findProductById(merchantStock.getProductID());
        if (product == null) {
            return "Product not found.";
        }

        merchantStockRepository.save(merchantStock);
        return "Merchant stock added successfully.";
    }

    public String updateMerchantStock(Integer id, MerchantStock updatedStock) {
        MerchantStock merchantStock = findMerchantStockById(id);
        if (merchantStock == null) {
            return "Merchant stock not found.";
        }

        Merchant merchant = findMerchantById(updatedStock.getMerchantID());
        if (merchant == null) {
            return "Merchant not found.";
        }

        Product product = findProductById(updatedStock.getProductID());
        if (product == null) {
            return "Product not found.";
        }

        // Update fields
        merchantStock.setMerchantID(updatedStock.getMerchantID());
        merchantStock.setProductID(updatedStock.getProductID());
        merchantStock.setStock(updatedStock.getStock());
        merchantStockRepository.save(merchantStock);
        return "Merchant stock updated successfully.";
    }

    public boolean deleteMerchantStock(Integer id) {
        MerchantStock merchantStock = findMerchantStockById(id);
        if (merchantStock == null) {
            return false;
        }

        merchantStockRepository.delete(merchantStock);
        return true;
    }

    public List<MerchantStock> getStocksByProduct(Integer productId) {
        List<MerchantStock> allStocks = getAllMerchantStocks();
        List<MerchantStock> filteredStocks = new ArrayList<>();
        for (MerchantStock stock : allStocks) {
            if (stock.getProductID().equals(productId)) {
                filteredStocks.add(stock);
            }
        }
        return filteredStocks;
    }

    private MerchantStock findMerchantStockById(Integer id) {
        List<MerchantStock> stocks = getAllMerchantStocks();
        for (MerchantStock stock : stocks) {
            if (stock.getId().equals(id)) {
                return stock;
            }
        }
        return null;
    }

    private Merchant findMerchantById(Integer id) {
        List<Merchant> merchants = merchantRepository.findAll();
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(id)) {
                return merchant;
            }
        }
        return null;
    }

    private Product findProductById(Integer id) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
