package com.kodoma.dao;

import com.kodoma.connection.ConnectionPool;
import com.kodoma.exceptions.WrongUserNameOrPassword;
import com.kodoma.mapper.Mapper;
import com.kodoma.model.Contact;
import com.kodoma.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.kodoma.util.Procedures.*;

/**
 * Created by Кодома on 03.09.2017.
 */
public class ContactDAOImpl implements ContactDAO<Contact> {
    private static Connection connection;
    private static int userID;
    private Mapper mapper = new Mapper();
    public static ContactDAOImpl instance;

    public static synchronized ContactDAO getInstance() {
        if (instance == null) {
            instance = new ContactDAOImpl();
            ConnectionPool pool = new ConnectionPool();
            //единожды инициализируем его указав класс драйвера, URL базы данных, а также логин и пароль к базе данных
            pool.initConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/contact_book", "root", "root");
            connection = pool.getConnection();
        }
        return instance;
    }

    @Override
    public int validate(User user) throws SQLException, WrongUserNameOrPassword {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(VALIDATE);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            int id = result.getInt(1);
            if (id > 0) {
                userID = id;
            }
            else throw new WrongUserNameOrPassword();
        }
        return userID;
    }

    @Override
    public void addContact(Contact contact) throws SQLException {
        System.out.println("addContact");
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(ADD_NEW_CONTACT);
        preparedStatement.setString(1, contact.getFname());
        preparedStatement.setString(2, contact.getLname());
        preparedStatement.setString(3, contact.getAddress());
        preparedStatement.setInt(4, contact.getPhoneNumber());
        preparedStatement.setString(5, contact.getGroupName()); //group_id
        preparedStatement.setInt(6, userID); //user_id

        preparedStatement.executeQuery();
    }

    @Override
    public void editContact(Contact contact) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(EDIT_CONTACT);
        preparedStatement.setInt(1, contact.getId());
        preparedStatement.setString(2, contact.getFname());
        preparedStatement.setString(3, contact.getLname());
        preparedStatement.setString(4, contact.getAddress());
        preparedStatement.setInt(5, contact.getPhoneNumber());
        preparedStatement.setString(6, contact.getGroupName()); //group_id
        preparedStatement.setInt(7, userID); //user_id

        preparedStatement.executeQuery();
    }

    @Override
    public void deleteContract(Contact contact) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(REMOVE_CONTACT);
        preparedStatement.setInt(1, contact.getId());
        preparedStatement.setInt(2, userID);

        preparedStatement.executeQuery();
    }

    @Override
    public void showContact(int id) {

    }

    @Override
    public List<Contact> showAllContacts() throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(SHOW_ALL_CONTACTS);
        preparedStatement.setInt(1, userID);

        ResultSet result = preparedStatement.executeQuery();
        List<Contact> list = mapper.mapToContacts(result);

        return list;
    }
}
