package com.nusiss.inventory.backend.controllers;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcheck")
@Api(value = "Health Check", tags = "HealthCheck Controller")
public class HealthController {
  @ApiOperation(value = "server health")
  @GetMapping
  public String getAllUsers() {
    return "HEALTHY";
  }
}
