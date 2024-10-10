package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SumPageController {
    public SumPageController() {
        System.out.println("SumPageController created");
    }

    @GetMapping("/sum")
    public String sum() {
        return "sum";
    }
}
