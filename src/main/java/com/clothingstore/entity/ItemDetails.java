package com.clothingstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "item_details")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "made_in", nullable = false)
//    @Size(max = 50, message = "Name of the country cannot be longer than 50 characters")
//    @NotBlank(message = "Please specify the country that the item was made in!")
//    @NotNull(message = "'madeIn' field cannot be null")
    private String madeIn;


    @Column(name = "eco_percent")
    private int percentOfEcoFriendlyMaterials;

    @Column(name = "iron_temperature")
    private int ironAtMaxCelsius;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "items_id")
    @JsonIgnore
    private Item item;

    public ItemDetails(String madeIn, int percentOfEcoFriendlyMaterials, int ironAtMaxCelsius) {
        this.madeIn = madeIn;
        this.percentOfEcoFriendlyMaterials = percentOfEcoFriendlyMaterials;
        this.ironAtMaxCelsius = ironAtMaxCelsius;
    }
}
