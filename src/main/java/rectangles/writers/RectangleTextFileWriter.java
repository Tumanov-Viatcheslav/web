package rectangles.writers;

import rectangles.Rectangle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RectangleTextFileWriter implements RectangleWriter {
    public void writeRectangle(Rectangle rectangle, String fileName) {
        File file = new File(fileName);
        String regex = "";
        if (file.exists() && file.length() != 0)
            regex = ";";
        try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName, true))) {
            output.write(regex + rectangle.getWidth() + " " + rectangle.getLength());
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
