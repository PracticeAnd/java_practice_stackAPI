package stack.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.stackoverflow.entity.User;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLogin(User user) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String checkInputLoginDates(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        return "redirect:/index";
    }
}
