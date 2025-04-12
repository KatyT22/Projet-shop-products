package com.alten.shop.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "OPERATOR")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Operator{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = " NAME ")
    public String name;

    @Column(name = " ROLE ")
    public String role; // TODO create type Role

}
