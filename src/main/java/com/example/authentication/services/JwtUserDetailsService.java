/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.authentication.services;

import com.example.authentication.dao.UtilisateurRepository;
import com.example.authentication.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("processing");
        //String pfl = "";
        Utilisateur user = utilisateurRepository.findByUsername(username);
        if (user != null) {
            System.out.println("ok user retrieve");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRoles().getRoleName()));
            System.out.println(authorities);
            System.out.println(user.getPrenoms());
            System.out.println(user.getNom());
            return new User(user.getUsername(), user.getPassword(), authorities);

        } else {
            System.out.println("no user");
            throw new UsernameNotFoundException("User with username: " + username + "not found !");
        }
    }
}
