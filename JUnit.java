import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JUnit {

    UMLCanvas Canvas = new UMLCanvas();

    @Test
    public void addOneRectangle() {

        UMLShape_Class newClassBox = new UMLShape_Class(4, 4, 0, false);
        Canvas.add(newClassBox);
        int numOfRectangles = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLShape_Class) {
                numOfRectangles++;
            }
        }
        assertEquals(1, numOfRectangles);
    }

    @Test
    public void addManyRectangles() {

        for (int i = 1; i <= 25; i++) {
            UMLShape_Class newClassBox = new UMLShape_Class(4, 4, i, false);
            Canvas.add(newClassBox);
        }
        int numOfRectangles = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLShape_Class) {
                numOfRectangles++;
            }
        }
        assertEquals(25, numOfRectangles);
    }

    @Test
    public void addOneLine() {

        UMLShape_Class newClassBox = new UMLShape_Class(4, 4, 0, false);
        UMLShape_Class newClassBox2 = new UMLShape_Class(4, 4, 0, false);
        Canvas.add(newClassBox);
        Canvas.add(newClassBox2);
        String linetype = "Association";
        UMLLine line = new UMLLine(newClassBox, newClassBox2, Canvas,linetype);
        Canvas.add(line);
        int numOfLines = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLLine) {
                numOfLines++;

            }
        }
        assertEquals(1, numOfLines);
    }

    @Test
    public void addManyLines() {

        UMLShape_Class newClassBox = new UMLShape_Class(4, 4, 0, false);
        UMLShape_Class newClassBox2 = new UMLShape_Class(4, 4, 0, false);
        Canvas.add(newClassBox);
        Canvas.add(newClassBox2);

        for (int i = 1; i <= 25; i++) {
            String linetype = "Generalization";
            UMLLine line = new UMLLine(newClassBox, newClassBox2, Canvas,linetype);
            Canvas.add(line);
        }
        int numOfLines = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLLine) {
                numOfLines++;
            }
        }
        assertEquals(25, numOfLines);
    }

    @Test
    public void clearCanvasWithOneClass() {

        UMLShape_Class newClassBox = new UMLShape_Class(4, 4, 0, false);
        Canvas.add(newClassBox);
        assertEquals(1, Canvas.getComponentCount());
        Canvas.clearCanvas();
        assertEquals(0, Canvas.getComponentCount());

    }

    @Test
    public void clearCanvasWithOneLine() {

        UMLShape_Class newClassBox = new UMLShape_Class(4, 4, 0, false);
        UMLShape_Class newClassBox2 = new UMLShape_Class(4, 4, 0, false);
        Canvas.add(newClassBox);
        Canvas.add(newClassBox2);
        UMLLine newLine = new UMLLine(newClassBox, newClassBox2, Canvas,"Association");
        Canvas.add(newLine);
        assertEquals(3, Canvas.getComponentCount());
        Canvas.clearCanvas();
        assertEquals(0, Canvas.getComponentCount());

    }

    @Test
    public void clearCanvasWithManyObjects() {
        for (int i = 0; i <= 25; i++) {
            UMLShape_Class newClassBox = new UMLShape_Class(4, 4, i, false);
            UMLShape_Class newClassBox2 = new UMLShape_Class(4, 4, i, false);
            Canvas.add(newClassBox);
            Canvas.add(newClassBox2);
            String lineType = "Aggregation";
            UMLLine line = new UMLLine(newClassBox, newClassBox2, Canvas,lineType);
            Canvas.add(line);
        }
        assertEquals(78, Canvas.getComponentCount());
        Canvas.clearCanvas();
        assertEquals(0, Canvas.getComponentCount());
    }

    @Test
    public void addOneCommentBox() {

        UMLShape_CommentBox newComBox = new UMLShape_CommentBox(4, 4, 0, false);
        Canvas.add(newComBox);
        int numOfComBoxes = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLShape_CommentBox) {
                numOfComBoxes++;
            }
        }
        assertEquals(1, numOfComBoxes);
    }

    @Test
    public void addManyCommentBoxes() {

        for (int i = 1; i <= 25; i++) {
            UMLShape_CommentBox newComBox = new UMLShape_CommentBox(4, 4, i, false);
            Canvas.add(newComBox);
        }
        int numOfBoxes = 0;
        for (int i = 0; i < Canvas.getComponentCount(); i++) {
            if (Canvas.getComponent(i) instanceof UMLShape_CommentBox) {
                numOfBoxes++;
            }
        }
        assertEquals(25, numOfBoxes);
    }

}


