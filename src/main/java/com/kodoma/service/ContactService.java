package com.kodoma.service;

import com.kodoma.exceptions.WrongUserNameOrPassword;
import com.kodoma.model.Contact;
import com.kodoma.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кодома on 04.09.2017.
 */
public interface ContactService<T> {
    int validate(User user) throws SQLException, WrongUserNameOrPassword;
    void addContact(T obj) throws SQLException;
    void editContact(T obj) throws SQLException;
    void deleteContract(T obj) throws SQLException;
    void showContact(int id);
    List<T> showAllContacts() throws SQLException;
}
