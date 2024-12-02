package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Merchant;
import com.example.exercise_jpa.Model.User;
import com.example.exercise_jpa.Repository.MerchantRepository;
import com.example.exercise_jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public String addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
        return "Merchant added successfully.";
    }

    public String updateMerchant(Integer id, Merchant updatedMerchant) {
        Merchant merchant = findMerchantById(id);
        if (merchant == null) {
            return "Merchant not found.";
        }

        merchant.setName(updatedMerchant.getName());
        merchantRepository.save(merchant);
        return "Merchant updated successfully.";
    }

    public boolean deleteMerchant(Integer id) {
        Merchant merchant = findMerchantById(id);
        if (merchant == null) {
            return false;
        }

        merchantRepository.delete(merchant);
        return true;
    }

    public String activateMerchant(Integer id, Integer userId) {
        User user = findUserById(userId);
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            return "Only admin users can activate merchants.";
        }

        Merchant merchant = findMerchantById(id);
        if (merchant == null) {
            return "Merchant not found.";
        }

        merchant.setActive(true);
        merchantRepository.save(merchant);
        return "Merchant activated successfully.";
    }

    public String deactivateMerchant(Integer id, Integer userId) {
        User user = findUserById(userId);
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            return "Only admin users can deactivate merchants.";
        }

        Merchant merchant = findMerchantById(id);
        if (merchant == null) {
            return "Merchant not found.";
        }

        merchant.setActive(false);
        merchantRepository.save(merchant);
        return "Merchant deactivated successfully.";
    }

    private Merchant findMerchantById(Integer id) {
        List<Merchant> merchants = getAllMerchants();
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(id)) {
                return merchant;
            }
        }
        return null;
    }

    private User findUserById(Integer id) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
