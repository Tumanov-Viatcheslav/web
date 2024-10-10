package rectangles.readers;


import rectangles.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class RectangleTextFileReader implements RectangleReader {

    @Override
    public List<Rectangle> getRectangleList(String filePath) {
        return loadListOfRectangles(filePath);
    }

    public static List<Rectangle> loadListOfRectangles(String filePath) {
        List<Rectangle> rectangles = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] splitLine;
            while ((line = input.readLine()) != null) {
                String[] rectanglesStr = line.split(";");
                for (String rectangleStr : rectanglesStr) {
                    splitLine = rectangleStr.split(" ");
                    try {
                        if (splitLine.length != 2)
                            throw new InvalidParameterException("Should be 2 parameters");
                        Double.parseDouble(splitLine[0]);
                        Double.parseDouble(splitLine[1]);
                        rectangles.add(new Rectangle(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1])));
                    } catch (Exception ex) {
                        System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
                    }
                }

            }
        }
        catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return rectangles;
    }
}
