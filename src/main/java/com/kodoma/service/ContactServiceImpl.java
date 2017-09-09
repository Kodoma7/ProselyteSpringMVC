package com.kodoma.service;

import com.kodoma.dao.ContactDAO;
import com.kodoma.dao.ContactDAOImpl;
import com.kodoma.exceptions.WrongUserNameOrPassword;
import com.kodoma.model.Contact;
import com.kodoma.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кодома on 04.09.2017.
 */
public class ContactServiceImpl implements ContactService<Contact> {
    private ContactDAO dao = ContactDAOImpl.getInstance();
    public static ContactServiceImpl instance;

    public static ContactServiceImpl getInstance() {
        if (instance == null) {
            instance = new ContactServiceImpl();
        }
        return instance;
    }

    @Override
    public int validate(User user) throws SQLException, WrongUserNameOrPassword {
        return dao.validate(user);
    }

    @Override
    public void addContact(Contact obj) throws SQLException {
        dao.addContact(obj);
    }

    @Override
    public void editContact(Contact obj) throws SQLException {
        dao.editContact(obj);
    }

    @Override
    public void deleteContract(Contact obj) throws SQLException {
        dao.deleteContract(obj);
    }

    @Override
    public void showContact(int id) {

    }

    @Override
    public List<Contact> showAllContacts() throws SQLException {
        return dao.showAllContacts();
    }
}
