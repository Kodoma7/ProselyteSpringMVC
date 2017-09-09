package com.kodoma.controllers;

import com.kodoma.exceptions.WrongUserNameOrPassword;
import com.kodoma.model.Contact;
import com.kodoma.model.User;
import com.kodoma.service.ContactService;
import com.kodoma.service.ContactServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кодома on 04.09.2017.
 */

@Controller
public class LoginController {
    private ContactService service = ContactServiceImpl.getInstance();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        /*
        ModelAndView - передача параметров через атрибуты
        login - View, user - Model
         */
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "checkUser", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        try {
            int id = service.validate(user);
            List<Contact> contacts = service.showAllContacts();
            user.setId(id);
            modelAndView.addObject("contacts", contacts);
/*            HttpSession session = request.getSession();
            session.setAttribute("User", user);*/

        } catch (SQLException | WrongUserNameOrPassword e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("Error", e.getMessage());
        }
        return modelAndView;
    }
}
