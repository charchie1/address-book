package com.charlie.addressbook.api;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.Collection;

import com.charlie.addressbook.domain.Contact;
import com.charlie.addressbook.repository.ContactRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${charlie.addressbook.origins:*}", allowedHeaders = "${charlie.addressbook.allowed-headers:*}")
public class ContactRestController {

    private final ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {

        this.contactRepository = contactRepository;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Contact contact(@PathVariable("id") int id) {

        return this.contactRepository.findFor(id).orElseThrow(() -> new ContactNotFound("ID: " + id));
    }

    @GetMapping( produces = APPLICATION_JSON_VALUE)
    public Collection<Contact> contacts() {

        return this.contactRepository.findAll();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Contact saveContact(@RequestBody Contact contact) {

        return this.contactRepository.save(contact);
    }
}
