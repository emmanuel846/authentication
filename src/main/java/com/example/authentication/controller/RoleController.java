package com.example.authentication.controller;

import com.example.authentication.dao.RolesRepository;
import com.example.authentication.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RolesRepository rolesRepository;
    @GetMapping("/roles")
    public List<Roles> listRoles(){
        return  rolesRepository.findAll();
    }
    @PostMapping("/roles")
    public Roles saveRole(@RequestBody Roles role){
        return rolesRepository.save(role);
    }
}
