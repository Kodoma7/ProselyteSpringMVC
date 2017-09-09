package com.kodoma.controllers;

/**
 * Created by Кодома on 02.09.2017.
 */
import com.kodoma.model.Contact;
import com.kodoma.model.Developer;
import com.kodoma.model.User;
import com.kodoma.service.ContactService;
import com.kodoma.service.ContactServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ContactController {
    private ContactService<Contact> service = ContactServiceImpl.getInstance();
    /*
    DefaultAnnotationHandlerMapping отображает запросы на методы
    контроллеров, отмеченные аннотацией @RequestMapping
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addContactForm() {
        return new ModelAndView("addcontact", "contact", new Contact());
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    public ModelAndView addContact(@ModelAttribute Contact contact) {
/*        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");*/
        List<Contact> contacts = null;

        try {
            service.addContact(contact);
            contacts = service.showAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("main", "contacts", contacts);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView editContactForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        int id = Integer.parseInt(request.getParameter("Ok"));
        String action = request.getParameter("Take");

        System.out.println("Action = " + action);

        Contact contact = new Contact();
        contact.setId(id);

        try {
            switch (action) {
                case "Edit": {
                    modelAndView.setViewName("editcontact");
                    modelAndView.addObject("contact", contact);
                    break;
                }
                case "Remove": {
                    service.deleteContract(contact);

                    List<Contact> contacts = service.showAllContacts();
                    modelAndView.setViewName("main");
                    modelAndView.addObject("contacts", contacts);
                    break;
                }
            }
        } catch (SQLException e) {

        }
        return modelAndView;
    }

    @RequestMapping(value = "editContact", method = RequestMethod.POST)
    public ModelAndView editContact(@ModelAttribute Contact contact) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            service.editContact(contact);
            List<Contact> contacts = service.showAllContacts();
            modelAndView.setViewName("main");
            modelAndView.addObject("contacts", contacts);

        } catch (SQLException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("Error", e.getMessage());
        }
        return modelAndView;
    }




    @RequestMapping(value = "developer", method = RequestMethod.GET)
    public ModelAndView developer() {
        return new ModelAndView("developer", "command", new Developer());
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public ModelAndView contact() {
        return new ModelAndView("contact", "command", new Contact());
    }

    @RequestMapping(value = "/addDeveloper", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("mvc-dispatcher") Developer developer, ModelMap model) {
        model.addAttribute("id", developer.getId());
        model.addAttribute("name", developer.getName());
        model.addAttribute("specilaty", developer.getSpecialty());
        model.addAttribute("experience", developer.getExperience());

        return "result";
    }

    /*
    Метод greeting(...) обрабатывает GET запросы для /hello,
    возвращая название View, в данном случае это "hello".
    View отвечает за рендеринг HTML содержимого.
    */
    @RequestMapping("/hello")
    //Аннотация @RequestMapping гарантирует, что HTTP запросы к /hello приведут к выполнению метода greeting().
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        /*
        @RequestParam связывает значение строкового параметра name запроса с name параметром метода greeting().
         Этот параметр не required; если он отсутствует в запросе, то будет использовано defaultValue значение "World".
         Значение параметра name добавлено в объект Model, что делает его доступным в шаблоне представления.
         */
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@ModelAttribute User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        return "/index";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show() {
        System.out.println("show()");
        return "show";
    }
}
