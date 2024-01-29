package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDto getUserById(Long id) {
        User user = userDao.findById(id);
        return convertToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userDao.saveUser(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userDao.findById(id);
        if (user != null) {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            // update roles or other fields
            User updatedUser = userDao.saveUser(user);
            return convertToDto(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAllUsers();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // set roles or other fields
        return user;
    }
}

