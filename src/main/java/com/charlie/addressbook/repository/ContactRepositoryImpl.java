package com.charlie.addressbook.repository;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.charlie.addressbook.domain.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ContactRepositoryImpl implements ContactRepository {

    private static final String SQL_SELECT = "SELECT contact_id, name, telephone, street, city, country, post_code FROM contact";

    private static final String SQL_INSERT = "INSERT INTO contact(contact_id, name, telephone, street, city, country, post_code) " +
        "VALUES (NEXTVAL('seq_contact_id'), ?, ?, ?, ?, ?, ?)";

    private static final String WHERE_ID = " WHERE contact_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Contact> findAll() {

        return this.jdbcTemplate.query(
            SQL_SELECT,
            (resultSet, row) -> contact(resultSet)
        );
    }

    @Override
    public Optional<Contact> findFor(int id) {

        return this.jdbcTemplate.query(
            SQL_SELECT + WHERE_ID,
            (PreparedStatement preparedStatement) -> preparedStatement.setInt(1, id),
            (resultSet, row) -> contact(resultSet)
        ).stream().findFirst();
    }

    @Override
    public Contact save(Contact contact) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(
          connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, contact.getName());
                preparedStatement.setString(2, contact.getTelephone());
                preparedStatement.setString(3, contact.getStreet());
                preparedStatement.setString(4, contact.getCity());
                preparedStatement.setString(5, contact.getCountry());
                preparedStatement.setString(6, contact.getPostCode());

                return preparedStatement;
            },
            keyHolder
        );

        Number id = keyHolder.getKey();
        if (id == null) throw new IllegalStateException("No id returned after inserting contact");

        return contact.withId(id.intValue());
    }

    private Contact contact(ResultSet resultSet) throws SQLException {

        return new Contact(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getString(6),
            resultSet.getString(7)
        );
    }
}
