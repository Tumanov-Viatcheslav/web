package rectangles;

import org.junit.jupiter.api.*;
import rectangles.exceptions.GeometryException;
import rectangles.readers.RectangleTextFileReader;

import java.util.ArrayList;
import java.util.List;


public class TestLoader {
    @BeforeEach
    public void testStarted() {
        System.out.println("test started");
    }

    @AfterEach
    public void testFinished() {
        System.out.println("test ended\n-------------------------------------------------------------------");
    }

    @Test
    public void testLoader1() {
        Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("input.txt"));
    }

    @Test
    public void testLoader2() {
        Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputCorrect.txt"));
    }

    @Test
    public void testLoader3() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputEmpty.txt"));
        Assertions.assertEquals(0, rectangles.size());
    }

    @Test
    public void testLoader4() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect1.txt"));
        Assertions.assertEquals(0, rectangles.size());
    }

    @Test
    public void testLoader5() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect2.txt"));
        Assertions.assertEquals(2, rectangles.size());
    }

    @Test
    public void testLoader6() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect3.txt"));
        Assertions.assertEquals(2, rectangles.size());
    }

    @Test
    public void testLoader7() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect4.txt"));
        Assertions.assertEquals(1, rectangles.size());
    }

    @Test
    public void testLoader8() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect5.txt"));
        Assertions.assertEquals(2, rectangles.size());
    }

    @Test
    public void testLoader9() {
        List<Rectangle> rectangles = RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputCorrect.txt");
        List<Rectangle> rectanglesExpected = new ArrayList<>(3);
        try {
            rectanglesExpected.add(0, new Rectangle(10, 15));
            rectanglesExpected.add(1, new Rectangle(15, 12));
            rectanglesExpected.add(2, new Rectangle(3.4, 7.12));
        } catch (GeometryException _) {
        }
        Assertions.assertIterableEquals(rectanglesExpected, rectangles);
    }

    @Test
    public void testLoader10() {
        List<Rectangle> rectangles = RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputCorrect.txt");
        Assertions.assertNotNull(rectangles);
    }

    @Test
    public void testLoader11() {
        List<Rectangle> rectangles = Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputQuestionable.txt"));
        Assertions.assertEquals(1, rectangles.size());
    }

    @Test
    public void testLoader12() {
        Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect7.txt"));
    }

    @Test
    public void testLoader13() {
        Assertions.assertDoesNotThrow(() -> RectangleTextFileReader.loadListOfRectangles("src/test/resources/rectangle_text_test_input/inputIncorrect8.txt"));
    }
}
