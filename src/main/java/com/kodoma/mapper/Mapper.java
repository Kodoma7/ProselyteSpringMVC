package com.kodoma.mapper;




import com.kodoma.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private List<Contact> contacts = new ArrayList<>();
    private List<String> groups = new ArrayList<>();

    public Contact mapToContact(ResultSet resultSet) {
        Contact user = null;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                int phoneNumber = resultSet.getInt("phone_number");
                String groupName = resultSet.getString("group_name");

                user = new Contact(id, fname, lname, address, phoneNumber, groupName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Contact> mapToContacts(ResultSet resultSet) {
        contacts.clear();
        Contact user;
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                int phoneNumber = resultSet.getInt("phone_number");
                String groupName = resultSet.getString("group_name");

                user = new Contact(id, fname, lname, address, phoneNumber, groupName);
                contacts.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<String> mapToGroups(ResultSet resultSet) {
        groups.clear();
        try {
            while (resultSet.next()) {
                groups.add(resultSet.getString("group_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
