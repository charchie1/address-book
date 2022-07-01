package com.charlie.addressbook.repository;

import com.charlie.addressbook.domain.Contact;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Contact> findAll() {
        return null;
    }

    @Override
    public Optional<Contact> findFor(int id) {
        return Optional.empty();
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }
}
