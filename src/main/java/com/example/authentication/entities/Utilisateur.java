package com.example.authentication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenoms;
    //@JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @ManyToOne @JoinColumn(name = "idroles")
    private Roles roles;
}
