package web.controllers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rectangles.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RectangleController {
    List<RectangleWriter> writerList = new ArrayList<>();
    Map<RectangleWriter, String> writersDestinationsMap = new HashMap<>();

    @GetMapping("/rectangle")
    public String rectangle(
            Model model,
            @RequestParam(name = "width", defaultValue = "") String width,
            @RequestParam(name = "length", defaultValue = "") String length
    ) {
        Rectangle rectangle;
        if (width.isEmpty() || length.isEmpty()) {
            model.addAttribute("perimeter", "to be counted");
            model.addAttribute("area", "to be counted");
            return "rectangle";
        }

        try {
            rectangle = new Rectangle(Double.parseDouble(width), Double.parseDouble(length));
            if (writerList.isEmpty()) {
                RectangleWriter rectangleWriter = new RectangleTextFileWriter();
                writerList.add(rectangleWriter);
                writersDestinationsMap.put(rectangleWriter, "src/main/resources/recs.txt");

                rectangleWriter = new RectangleExelFileWriter();
                writerList.add(rectangleWriter);
                writersDestinationsMap.put(rectangleWriter, "src/main/resources/recs.xlsx");

                rectangleWriter = new RectangleDBWriter();
                writerList.add(rectangleWriter);
                writersDestinationsMap.put(rectangleWriter, "jdbc:postgresql://10.10.104.136:5432/Geometry");
            }
            for (RectangleWriter writer : writerList)
                writer.writeRectangle(rectangle, writersDestinationsMap.get(writer));
            model.addAttribute("perimeter", rectangle.perimeter());
            model.addAttribute("area", rectangle.area());
        } catch (Exception e) {
            model.addAttribute("perimeter", e.getClass() + ": " + e.getMessage());
            model.addAttribute("area", e.getClass() + ": " + e.getMessage());
        }
        return "rectangle";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(Model model, TypeMismatchException e) {
        model.addAttribute("errorText",
                e.getClass().getSimpleName() + ": " +e.getMessage());
        return "error";
    }
}
