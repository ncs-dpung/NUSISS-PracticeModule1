package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Api(value = "User Management", tags = "User Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get a list of all users", authorizations = {@Authorization(value="Bearer")})
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Get a user by ID", authorizations = {@Authorization(value="Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or @userService.canAccessUser(principal, #id)")
    public ResponseEntity<UserDto> getUserById(
            @ApiParam(value = "User ID", required = true) @PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "Create a new user", authorizations = {@Authorization(value="Bearer")})
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(
            @ApiParam(value = "User data", required = true) @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @ApiOperation(value = "Update an existing user", authorizations = {@Authorization(value="Bearer")})
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or @userService.canAccessUser(principal, #id)")
    public ResponseEntity<UserDto> updateUser(
            @ApiParam(value = "User ID", required = true) @PathVariable Long id,
            @ApiParam(value = "User data", required = true) @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "Delete a user by ID", authorizations = {@Authorization(value="Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(
            @ApiParam(value = "User ID", required = true) @PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
