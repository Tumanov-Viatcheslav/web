package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    public HomePageController() {
        System.out.println("HomePageController created");
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/")
    public String index2() {
        return "index";
    }

    @RequestMapping(value = "/formName", method = RequestMethod.GET)
    public String form(Model model) {
        return "second";
    }

//    @EventListener
//    public String onClick(ApplicationEvent event) {
//        return
//    }

//    @GetMapping(value = "/second")
//    public String buttonClick() {
//        return "second";
//    }
}
