package com.fasttrackit.entity;

import com.fasttrackit.entity.enumproperties.Category;
import com.fasttrackit.entity.enumproperties.Color;
import com.fasttrackit.entity.enumproperties.Gender;
import com.fasttrackit.entity.enumproperties.Material;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Column(name = "name", nullable = false)
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "price", nullable = false)
    @Size(max = 5, message = "No longer than 5 digits (including 2 decimals)")
    @NotNull(message = "Price cannot be null")
    private double price;

    @Column(name = "description")
    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    @NotNull(message = "Description cannot be null")
    private String description;


    @Column(name = "category", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Category cannot be null")
    private Category category;

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @Column(name = "material", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Material cannot be null")
    private Material material;

    @Column(name = "color", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Color cannot be null")
    private Color color;


    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ItemDetails itemDetails;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "orders_items",
            joinColumns = {
                    @JoinColumn(name = "items_id", referencedColumnName = "id")
            },
    inverseJoinColumns = {
            @JoinColumn(name = "orders_id", referencedColumnName = "id")
    })
    List<Order> orders;

    public Item(String name, double price, String description, Category category,
                Gender gender, Material material, Color color) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.material = material;
        this.color = color;
    }
}

