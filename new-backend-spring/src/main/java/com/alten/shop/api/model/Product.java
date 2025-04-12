package com.alten.shop.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private float price;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "INVENTORY STATUS")
    private String inventoryStatus;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "RATING")
    private int rating;
}
