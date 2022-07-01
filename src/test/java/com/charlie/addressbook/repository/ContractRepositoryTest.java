package com.charlie.addressbook.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.charlie.addressbook.domain.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContractRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    void testFindAll() {

        Contact contact1 = this.contactRepository.save(new Contact("Charlie", "1234", "street", "Paris", "france", "N8"));
        Contact contact2 = this.contactRepository.save(new Contact("Archie", "1234", "street", "Paris", "france", "N8"));
        Contact contact3 = this.contactRepository.save(new Contact("Nick", "59", "Road", "London", "UK", "SE6"));

        assertEquals(
            List.of(contact1, contact2, contact3),
            this.contactRepository.findAll()
        );
    }

    @Test
    void testFindFor() {

        Contact contact = this.contactRepository.save(new Contact("Charlie", "1234", "street", "Paris", "france", "N8"));

        assertEquals(
          contact,
          this.contactRepository.findFor(contact.getId()).orElse(null)
        );
    }

    @Test
    void testSave() {
        Contact contact = this.contactRepository.save(
            new Contact("charlie", "1234", "street", "Paris", "france", "N8")
        );
        assertNotNull(contact.getId());

        Contact loadedContact = this.contactRepository.findFor(contact.getId()).orElse(null);
        assertEquals(contact, loadedContact);
    }
}
