package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.UserDTO;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO signup(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProfilePicUrl(dto.getProfilePicUrl());
        user.setCreatedAt(LocalDateTime.now());

        return convertToDTO(userRepository.save(user));
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        return convertToDTO(user);
    }

    public UserDTO createUser(UserDTO dto) {
        return signup(dto); 
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserDTO updateUser(Integer id, UserDTO dto) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) return null;

        User user = optional.get();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProfilePicUrl(dto.getProfilePicUrl());
        user.setUpdatedAt(LocalDateTime.now());

        return convertToDTO(userRepository.save(user));
    }

    public boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getProfilePicUrl(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
}