package com.fasttrackit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "item_details")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "eco_percent")
    private int percentOfEcoFriendlyMaterials;

    @Column(name = "iron_temperature")
    private int ironAtMaxCelsius;

    @OneToOne(mappedBy = "itemDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Item item;

    public ItemDetails(String madeIn, int percentOfEcoFriendlyMaterials, int ironAtMaxCelsius) {
        this.madeIn = madeIn;
        this.percentOfEcoFriendlyMaterials = percentOfEcoFriendlyMaterials;
        this.ironAtMaxCelsius = ironAtMaxCelsius;
    }
}
