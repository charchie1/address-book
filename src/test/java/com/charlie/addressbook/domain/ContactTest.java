package com.charlie.addressbook.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void testSerialization() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Contact contact = new Contact(10, "Charlie", "1234", "street", "Paris", "france", "N8");

        assertEquals(contact, objectMapper.readValue(objectMapper.writeValueAsString(contact), Contact.class));
    }
}
