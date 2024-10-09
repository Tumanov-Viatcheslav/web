package web;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rectangles.GeometryException;
import rectangles.Rectangle;
import rectangles.RectangleTextFileWriter;

@Controller
public class RectangleController {

    @GetMapping("/rectangle")
    public String rectangle(
            Model model,
            @RequestParam(name = "width", defaultValue = "") String width,
            @RequestParam(name = "height", defaultValue = "") String height
    ) {
        Rectangle rectangle;
        if (width.isEmpty() || height.isEmpty()) {
            model.addAttribute("perimeter", "to be counted");
            model.addAttribute("area", "to be counted");
            return "rectangle";
        }

        try {
            rectangle = new Rectangle(Double.parseDouble(width), Double.parseDouble(height));
            new RectangleTextFileWriter().writeRectangleToFile(rectangle, "src/main/resources/recs.txt");
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
