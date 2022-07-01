package com.charlie.addressbook.repository;

import com.charlie.addressbook.domain.Contact;

import java.util.Collection;
import java.util.Optional;

public interface ContactRepository {
    Collection<Contact> findAll();
    Optional<Contact> findFor(int id);
    Contact save(Contact contact);

}
