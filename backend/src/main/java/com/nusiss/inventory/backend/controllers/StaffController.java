package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffRegResDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import com.nusiss.inventory.backend.dto.UserPasswordUpdateDto;
import com.nusiss.inventory.backend.service.StaffService;
import com.nusiss.inventory.backend.service.UserService;
import com.nusiss.inventory.backend.utils.GlobalConstants;
import com.nusiss.inventory.backend.utils.PasswordGeneratorUtil;
import io.swagger.annotations.*;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@Api(value = "Staff Management", tags = "Staff Controller")
public class StaffController {

  private final StaffService staffService;
  private final UserService userService;

  @Autowired
  public StaffController(StaffService staffService, UserService userService) {
    this.staffService = staffService;
    this.userService = userService;
  }

  @ApiOperation(
      value = "Get a list of all staff",
      authorizations = {@Authorization(value = "Bearer")})
  @GetMapping
  public ResponseEntity<List<StaffDto>> getAllStaff() {
    List<StaffDto> staff = staffService.getAllStaff();
    return ResponseEntity.ok(staff);
  }

  @ApiOperation(
      value = "Get a staff by ID",
      authorizations = {@Authorization(value = "Bearer")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved staff"),
        @ApiResponse(code = 404, message = "Staff not found")
      })
  @GetMapping("/{id}")
  public ResponseEntity<StaffDto> getStaffById(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id) {
    StaffDto staffDto = staffService.getStaffById(id);
    return ResponseEntity.ok(staffDto);
  }

  @ApiOperation(
      value = "Create a new staff",
      authorizations = {@Authorization(value = "Bearer")})
  @PostMapping
  @Transactional
  public ResponseEntity<StaffRegResDto> createStaff(
      @ApiParam(value = "Staff data", required = true) @RequestBody StaffDto staffDto) {
    StaffDto createdStaff = staffService.createStaff(staffDto);
    String password = PasswordGeneratorUtil.getPassword(GlobalConstants.DEFAULT_PW_LENGTH);
    UserPasswordUpdateDto updateDto = new UserPasswordUpdateDto();
    updateDto.setNewPassword(password);
    userService.updateUserPassword(createdStaff.getUser().getId(), updateDto);

    StaffRegResDto response = new StaffRegResDto();
    response.setPassword(password);
    response.setStaff(createdStaff);
    return ResponseEntity.ok(response);
  }

  @ApiOperation(
      value = "Update an existing staff",
      authorizations = {@Authorization(value = "Bearer")})
  @PatchMapping("/{id}")
  @Transactional
  public ResponseEntity<StaffDto> updateStaff(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id,
      @ApiParam(value = "Staff data", required = true) @RequestBody StaffUpdateDto updateDto) {
    StaffDto updatedStaff = staffService.updateStaff(id, updateDto);
    return ResponseEntity.ok(updatedStaff);
  }

  @ApiOperation(
      value = "Update an existing staff password",
      authorizations = {@Authorization(value = "Bearer")})
  @PutMapping("/password/{id}")
  @Transactional
  public ResponseEntity<String> updateStaffPassword(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id,
      @ApiParam(value = "Old and New Password", required = true) @RequestBody
          UserPasswordUpdateDto updateDto) {
    StaffDto staff = staffService.getStaffById(id);
    userService.updateUserPassword(staff.getUser().getId(), updateDto);
    return ResponseEntity.ok("Password Changed");
  }

  @ApiOperation(
      value = "Delete a staff by ID",
      authorizations = {@Authorization(value = "Bearer")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Staff deleted successfully"),
        @ApiResponse(code = 404, message = "Staff not found")
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteStaff(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id) {
    staffService.deleteStaffById(id);
    return ResponseEntity.noContent().build();
  }
}
