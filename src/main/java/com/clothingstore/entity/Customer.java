package com.clothingstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @Size(max = 45, message = "First name cannot be longer than 45 characters!")
    @NotBlank(message = "First name cannot be blank")
    @NotNull(message = "First name cannot be null!")
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 45, message = "Last name cannot be longer than 45 characters!")
    @NotBlank(message = "Last name cannot be blank")
    @NotNull(message = "Last name cannot be null!")
    private String lastName;

    @Column(name = "address")
    @Size(max = 500, message = "Address cannot be longer than 500 characters!")
    @NotBlank(message = "Address cannot be blank")
    @NotNull(message = "Address cannot be null!")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Order>  order;
}
