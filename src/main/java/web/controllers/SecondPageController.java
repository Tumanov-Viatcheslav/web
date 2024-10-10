package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondPageController {

    public SecondPageController() {
        System.out.println("SecondPageController created");
    }

    @GetMapping("/second")
    public String index() {
        return "second";
    }
}
