package com.example.authentication.controller;

import com.example.authentication.dao.UtilisateurRepository;
import com.example.authentication.entities.Roles;
import com.example.authentication.entities.Utilisateur;
import com.example.authentication.services.JwtUserDetailsService;
import com.example.authentication.utils.JwtRequest;
import com.example.authentication.utils.JwtResponse;
import com.example.authentication.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/user")
    Utilisateur save(@RequestBody Utilisateur utilisateur){
        utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    @GetMapping("/user")
    public List<Utilisateur> getListe(){
        return utilisateurRepository.findAll();
    }
    @PostMapping("/user/addRole")
    public void addRoleToUser(@RequestBody List<Roles> roles){

    }
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        System.out.println(" username " + authenticationRequest.getUsername() + " password " + authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        System.out.println("checked");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println("userDetails" + userDetails.toString());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
