package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.User;
import com.example.exercise_jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully.";
    }

    public String updateUser(Integer id, User updatedUser) {
        User user = findUserById(id);
        if (user == null) {
            return "User not found.";
        }

        user.setUserName(updatedUser.getUserName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
        return "User updated successfully.";
    }

    public boolean deleteUser(Integer id) {
        User user = findUserById(id);
        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

    public List<User> getUsersByRole(String role) {
        List<User> allUsers = getUsers();
        List<User> filteredUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getRole().equalsIgnoreCase(role)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    private User findUserById(Integer id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
