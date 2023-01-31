package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> grantAuthRoles(@PathVariable("id") String id, @RequestBody List<String> authRoles) {
        roleService.addAuthRolesToUser(id, authRoles);
        return ResponseEntity.ok().body("User " + id + " has been granted the following roles: " + authRoles);
    }

    @GetMapping("")
    public ResponseEntity<Collection<AuthRole>> getAuthRoles() {
        Collection<AuthRole> authRoles = roleService.getAllAuthRoles();
        return ResponseEntity.ok().body(authRoles);
    }
}
