package com.fixedcode.exporttoexcelinjavademo.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable//to use as object in another class
@Data//for getter setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String state;
    private String city;
    private String address;
}
