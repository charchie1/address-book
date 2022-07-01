package com.charlie.addressbook.repository;

import com.charlie.addressbook.domain.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ContractRepositoryTest {
    @Autowired
    ContactRepository contactRepository;

    @Test
    void testSave() {
        Contact contact = this.contactRepository.save(
                new Contact("charlie", "1234", "street", "Paris", "france", "N8")
        );
        assertNotNull(contact.getId());
        assertEquals(contact, this.contactRepository.findFor(contact.getId()).orElse(null));
    }

}
