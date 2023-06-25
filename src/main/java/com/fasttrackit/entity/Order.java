package com.fasttrackit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "orders_items",
        joinColumns = {
                @JoinColumn(name = "order_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "item_id", referencedColumnName = "id")
        })
    private List<Item> items;
}
