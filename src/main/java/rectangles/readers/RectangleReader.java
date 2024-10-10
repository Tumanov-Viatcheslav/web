package rectangles.readers;

import rectangles.Rectangle;

import java.util.List;

public interface RectangleReader {
    List<Rectangle> getRectangleList(String source);
}
