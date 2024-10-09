package web;

import org.springframework.beans.TypeMismatchException;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
