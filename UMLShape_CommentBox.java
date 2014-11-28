import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class UMLShape_CommentBox extends UMLShape {

    // The text area for this Comment Box
    private JTextArea commentText = new JTextArea("Comment text here!!");
    // Save the mouse position when the user drags the mouse over us
    private Point mousePosition;
    private boolean leftMousePressed = false;
    private boolean bEditingTextArea = false;
    private UMLShape_Class_PopupMenu popupMenu = null; // Used for the popup menu

    UMLShape_CommentBox(int x, int y, int id, boolean selected) {
        super(x, y, id, selected);
        this.setSize(100, 50);

        // Setup the JTextArea
        commentText.setLocation(1, 1);
        commentText.setSize(100, 20);
        commentText.setOpaque(false);
        commentText.setEditable(false);
        commentText.setBackground(Color.white);
        commentText.setFocusable(false);
        commentText.setLineWrap(true);
        commentText.addMouseListener(this);
        commentText.addMouseMotionListener(this);
        commentText.setWrapStyleWord(true);

        //Make sure we can move it even if its not the text box being clicked
        addMouseListener(this);
        addMouseMotionListener(this);

        // Create the popup menu for the class object
        popupMenu = new UMLShape_Class_PopupMenu(this);

        this.add(commentText);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        // Only if the left mouse button was clicked, do we update the "focus" window
        // Can change to right mouse also? or middle and popup a menu popup? decision we have to make
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePosition = e.getLocationOnScreen();
            // The user clicked on the JTextArea
            if (e.getSource() instanceof JTextArea) {
                // Get the parent of the JTextArea (us)
                UMLShape s = (UMLShape) e.getComponent().getParent();
                // Get the canvas object
                UMLCanvas canvas = (UMLCanvas) s.getParent();
                // Update the selected shape in the canvas
                canvas.updateSelectedShape(s);


                // If the user double clicked us, go into edit mode
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    // Set the canvas object to be this
                    canvas.setUMLShapeBeingEdited(this);

                    // Set our editing to true
                    setEditing(true);

                    // We handled it ..
                    e.consume();
                }

            }
        }


    }


    public void setEditing(boolean isEditing) {
        if (isEditing) {
            commentText.setFocusable(true);
            commentText.setEditable(true);
            commentText.setBackground(Color.lightGray);
            commentText.setOpaque(true);

            bEditingTextArea = true;
        } else {
            commentText.setFocusable(false);
            commentText.setEditable(false);
            commentText.setBackground(Color.WHITE);
            commentText.setOpaque(false);

            bEditingTextArea = false;
        }

    }

    public boolean isEditing() {
        return bEditingTextArea;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if (arg0.getButton() == MouseEvent.BUTTON1) {
            leftMousePressed = true;
        }

        showPopup(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (arg0.getButton() == MouseEvent.BUTTON1) {
            leftMousePressed = false;
        }

        showPopup(arg0);
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {

        // Only move with left mouse click
        if (leftMousePressed) {

            // Update this objects location based on the mouse position
            setUMLShape_Location(arg0.getX(), arg0.getY());
        }
    }


    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }


    public void setUMLShape_Location(double x, double y) {
        // Get the current x,y location for this UMLShape class object
        double curX = this.getLocation().getX();
        double curY = this.getLocation().getY();

        // Update the current location , the x,y values passed in
        // are used and called from the JTextArea that takes up
        // the UMLShape_Class's "child area" so all values are translated
        // from that object and handled by this (UMLShape_Class) with
        // the childs offset. So add the drag x/y position to the
        // UMLShape_Class current x/y and then move the UMLShape_Class
        // up and to the left based on the mouse position, this helps
        // ensure that we have a fluid motion of dragging as the cursor will always
        // stay within the bounds of the JTextArea.
        this.setLocation((int) (curX + x - mousePosition.x),
                (int) (curY + y - mousePosition.y));

        this.getParent().repaint();
    }


    @Override
    public void mouseMoved(MouseEvent arg0) {
        // Save the position of the mouse location.
        // This is needed to help ensure we have a fluid movement
        // when dragging the class object around.
        mousePosition = arg0.getPoint();

    }


    public void paintComponent(Graphics g) {

		/*
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN,12);
		g2D.setFont(font);
		g2D.drawString("Hello", 4+1,  12);
		*/

        super.paintComponent(g);
        // Background and border
        g.setColor(Color.YELLOW);
        g.fillRect(2, 2, getWidth() - 2, getHeight() - 2);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(2F));
        // If this is selected, draw an outline indicating it is
        if (isSelected) {
            // Draw a highlighted border
            g2D.setColor(Color.YELLOW);
            g2D.drawRect(0, 0, getWidth(), getHeight());

        } else {
            // Draw normal
            g2D.setColor(Color.WHITE);
            g2D.drawRect(0, 0, getWidth(), getHeight());

        }

        if (commentText.getHeight() > this.getHeight()) {

            this.setSize(commentText.getSize());
        }
    }

    public String getText() {
        return commentText.getText();
    }

    public void setText(String text) {
        commentText.setText(text);
    }


}

