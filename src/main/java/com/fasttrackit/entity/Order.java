package com.fasttrackit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customers_id")
    @JsonIgnore
    private Customer customer;
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "orders_items",
        joinColumns = {
                @JoinColumn(name = "orders_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "items_id", referencedColumnName = "id")
        })
    private List<Item> items;
}
