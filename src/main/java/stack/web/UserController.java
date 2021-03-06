package stack.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stackoverflow.entity.User;
import stack.dao.UserDAOImpl;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Оля on 05.07.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDAOImpl userDAO;

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @Valid User user,
            Errors errors) {
        if (errors.hasErrors()) return "register";

        return "redirect:/index";
        //userRepository.save(user);
        //return "redirect:/user/" + user.getUsername();
    }

}
