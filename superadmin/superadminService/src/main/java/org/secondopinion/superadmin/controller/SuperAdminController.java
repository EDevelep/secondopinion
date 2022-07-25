package org.secondopinion.superadmin.controller;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.superadmin.dto.User;
import org.secondopinion.superadmin.exception.SuperAdminServerException;
import org.secondopinion.superadmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/")
@Api("/")
public class SuperAdminController {

  @Autowired
  private SuperAdminService superAdminService;

  @PostMapping(value = "/save")
  public ResponseEntity<Response<String>> saveUser(@RequestBody User user) {

    try {
      superAdminService.registerSuperAdmin(user);
      return new ResponseEntity<>(
          new Response<>("User data saved.", StatusEnum.SUCCESS, "User data saved."),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new SuperAdminServerException(e.getMessage(), e);
    }

  }
}
