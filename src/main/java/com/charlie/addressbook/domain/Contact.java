package com.charlie.addressbook.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Contact {

    private final Integer id;
    private final String name;
    private final String telephone;
    private final String street;
    private final String city;
    private final String country;
    private final String postCode;

    public Contact(String name, String telephone, String street, String city, String country, String postCode) {

        this(null, name, telephone, street, city, country, postCode);
    }

    @JsonCreator
    public Contact(
        @JsonProperty("id") Integer id,
        @JsonProperty("name") String name,
        @JsonProperty("telephone") String telephone,
        @JsonProperty("street") String street,
        @JsonProperty("city") String city,
        @JsonProperty("country") String country,
        @JsonProperty("postCode") String postCode
    ) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public Contact withId(Integer id) {

        return new Contact(id, this.name, this.telephone, this.street, this.city, this.country, this.postCode);
    }
}

