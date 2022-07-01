package com.charlie.addressbook.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import com.charlie.addressbook.domain.Contact;
import com.charlie.addressbook.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ContractRestControllerTest {

    @MockBean
    ContactRepository contactRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    private final int port;

    public ContractRestControllerTest(@Value("${local.server.port}") int port) {

        this.port = port;
    }

    @Test
    void testContact() {

        Contact contact = new Contact(10, "Charlie", "1234", "street", "Paris", "france", "N8");

        when(this.contactRepository.findFor(contact.getId())).thenReturn(Optional.of(contact));

        ResponseEntity<Contact> responseEntity = this.testRestTemplate.exchange(
          "http://localhost:" + this.port + "/" + contact.getId(),
          HttpMethod.GET,
          HttpEntity.EMPTY,
          Contact.class
        );
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(contact, responseEntity.getBody());
    }

    @Test
    void testContacts() {

        List<Contact> contacts = List.of(
            new Contact(1, "Charlie", "1234", "street", "Paris", "france", "N8"),
            new Contact(2, "Archie", "1234", "street", "Paris", "france", "N8"),
            new Contact(3, "Nick", "59", "Road", "London", "UK", "SE6")
        );

        when(this.contactRepository.findAll()).thenReturn(contacts);

        ResponseEntity<List<Contact>> responseEntity = this.testRestTemplate.exchange(
          "http://localhost:" + this.port,
          HttpMethod.GET,
          HttpEntity.EMPTY,
          new ContactListReferenceType()
        );
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(contacts, responseEntity.getBody());
    }

    @Test
    void testSaveContact() {

        Contact contact = new Contact("Charlie", "1234", "street", "Paris", "france", "N8");
        Contact savedContact = contact.withId(10);

        when(this.contactRepository.save(contact)).thenReturn(savedContact);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        ResponseEntity<Contact> responseEntity = this.testRestTemplate.exchange(
          "http://localhost:" + this.port,
          HttpMethod.POST,
          new HttpEntity<>(contact, httpHeaders),
          Contact.class
        );
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(savedContact, responseEntity.getBody());
    }

    private static class ContactListReferenceType extends ParameterizedTypeReference<List<Contact>> {}
}
