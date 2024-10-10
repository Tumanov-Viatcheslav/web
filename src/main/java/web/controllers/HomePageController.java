package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/")
    public String index2() {
        return "index";
    }
}
