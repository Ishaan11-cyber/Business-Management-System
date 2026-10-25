package com.bharatthymes.system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 150)
    private String companyName;

    @Size(max = 100)
    private String contactPerson;

    @Size(max = 20)
    private String phone;

    @Email(message = "Enter a valid email address")
    @Size(max = 150)
    private String email;

    @Size(max = 15)
    private String gstin;

    @Column(columnDefinition = "TEXT")
    private String address;
}
