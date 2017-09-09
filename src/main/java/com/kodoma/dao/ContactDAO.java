package com.kodoma.dao;

import com.kodoma.exceptions.WrongUserNameOrPassword;
import com.kodoma.model.Contact;
import com.kodoma.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кодома on 03.09.2017.
 */
public interface ContactDAO<T> {
    int validate(User user) throws SQLException, WrongUserNameOrPassword;
    void addContact(T obj) throws SQLException;
    void editContact(T obj) throws SQLException;
    void deleteContract(T obj) throws SQLException;
    void showContact(int id);
    List<T> showAllContacts() throws SQLException;
}
