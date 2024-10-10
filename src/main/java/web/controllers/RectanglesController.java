package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rectangles.Rectangle;
import rectangles.readers.RectangleExelReader;
import rectangles.readers.RectangleTextFileReader;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RectanglesController {

    @GetMapping("/rectangles")
    public String rectangles(
            Model model,
            @RequestParam(name = "type", defaultValue = "") String type,
            @RequestParam(name = "source", defaultValue = "") String source
    ) {
        List<Rectangle> rectangleList;
        if (type.isEmpty() || source.isEmpty()) {
            model.addAttribute("rectangleList", new ArrayList<>());
            return "rectangles";
        }
        //TODO make source map in Service
        rectangleList = switch (type) {
            case "textFile" -> new RectangleTextFileReader().getRectangleList(source);
            case "exelFile" -> new RectangleExelReader().getRectangleList(source);
            case "dbTable" -> new ArrayList<>();
            default -> new ArrayList<>();
        };
        model.addAttribute("rectangleList", rectangleList);
        return "rectangles";
    }
}
