package com.whereeco.domain.address.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "address")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private float latitude;

    @Column(nullable = false, length = 30)
    private float longitude;

    @Column(nullable = false, length = 100)
    private String addressName;

    @Column(nullable = false, length = 100)
    private String placeName;

    public Address(float latitude, float longitude, String addressName, String placeName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressName = addressName;
        this.placeName = placeName;
    }

}
