package com.fasttrackit.entity;

import com.fasttrackit.entity.enumproperties.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "size")
    @Enumerated(value = EnumType.STRING)
    private Size size;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "material")
    @Enumerated(value = EnumType.STRING)
    private Material material;

    @Column(name = "color")
    @Enumerated(value = EnumType.STRING)
    private Color color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_details_id")
    private ItemDetails itemDetails;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "orders_items",
            joinColumns = {
                    @JoinColumn(name = "item_id", referencedColumnName = "id")
            },
    inverseJoinColumns = {
            @JoinColumn(name = "order_id", referencedColumnName = "id")
    })
    List<Order> orders;

    public Item(String name, double price, String description, Size size,
                Category category, Gender gender, Material material, Color color) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.size = size;
        this.category = category;
        this.gender = gender;
        this.material = material;
        this.color = color;
    }
}

