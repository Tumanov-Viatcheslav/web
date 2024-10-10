package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rectangles.Rectangle;
import rectangles.RectangleExelReader;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RectanglesController {

    @GetMapping("/rectangles")
    public String rectangles(
            Model model,
            @RequestParam(name = "type", defaultValue = "") String type,
            @RequestParam(name = "destination", defaultValue = "") String destination
    ) {
        List<Rectangle> rectangleList;
        if (type.isEmpty() || destination.isEmpty()) {
            model.addAttribute("rectangleList", new ArrayList<>());
            return "rectangles";
        }
        switch (type) {
            case "textFile":
                rectangleList = new ArrayList<>();
                break;
            case "exelFile":
                rectangleList = RectangleExelReader.readRectanglesFromExelFile(destination);
                break;
            case "dbTable":
                rectangleList = new ArrayList<>();
                break;
            default:
                rectangleList = new ArrayList<>();
        }
        model.addAttribute("rectangleList", rectangleList);
        return "rectangles";
    }
}
