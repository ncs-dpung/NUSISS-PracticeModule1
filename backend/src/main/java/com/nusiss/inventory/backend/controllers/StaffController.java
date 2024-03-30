package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffRegResDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import com.nusiss.inventory.backend.dto.UserPasswordUpdateDto;
import com.nusiss.inventory.backend.service.StaffService;
import com.nusiss.inventory.backend.service.UserService;
import com.nusiss.inventory.backend.utils.GlobalConstants;
import com.nusiss.inventory.backend.utils.PasswordGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  @PreAuthorize("hasRole('ADMIN') || hasAuthority('R_STAFF')")
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
  @PreAuthorize(
      "hasRole('ADMIN') || hasAuthority('R_STAFF') || #id =="
          + " authentication.details['user']['staffId']")
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
  @PreAuthorize("hasRole('ADMIN') || hasAuthority('C_STAFF')")
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
  @PreAuthorize(
      "hasRole('ADMIN') || hasAuthority('U_STAFF') || #id =="
          + " authentication.details['user']['staffId']")
  public ResponseEntity<StaffDto> updateStaff(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id,
      @ApiParam(value = "Staff data", required = true) @RequestBody StaffUpdateDto updateDto) {
    StaffDto updatedStaff = staffService.updateStaff(id, updateDto);
    return ResponseEntity.ok(updatedStaff);
  }

  @ApiOperation(
      value = "Update an existing staff password",
      authorizations = {@Authorization(value = "Bearer")})
  @PutMapping("/{id}/password")
  @Transactional
  @PreAuthorize("#id == authentication.details['user']['staffId']")
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
  @PreAuthorize("hasRole('ADMIN') || hasAuthority('D_STAFF')")
  public ResponseEntity<?> deleteStaff(
      @ApiParam(value = "Staff ID", required = true) @PathVariable Long id) {
    staffService.deleteStaffById(id);
    return ResponseEntity.noContent().build();
  }
}
