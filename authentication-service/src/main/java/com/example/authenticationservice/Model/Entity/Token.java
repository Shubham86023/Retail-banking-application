package com.example.authenticationservice.Model.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_token")
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;


}
