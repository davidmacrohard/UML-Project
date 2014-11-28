import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.*;

/**
 * The workhorse of the program. The white area in which the user creates his or
 * her diagram is called the canvas. This class controls the behavior of objects
 * on that canvas.
 * 
 * @author Team MacroHard
 *
 */
@SuppressWarnings("serial")
public class UMLCanvas extends JPanel implements MouseListener {

	@SuppressWarnings("unused")
	private Image img = null;
	static final int Z_TOP_CHILD = 0;
	private static UMLShape firstSelectedShape = null;
	private static UMLShape secondSelectedShape = null;
	private static UMLShape copiedShape = null;
	private static UMLShape testShape = null;
	private UMLToolBar umlToolBar = null;
	private UMLCanvas_PopupMenu popupMenu = null;
	private UMLShape umlShapeBeingEdited = null;
	public Hashtable<Integer, UMLShape_Class> shapesList = null;
	public Hashtable<Integer, UMLShape_CommentBox> commentsList = null;
	public ArrayList<UMLLine> linesList = null;
	private Random rand = null;

	UMLCanvas() {
		popupMenu = new UMLCanvas_PopupMenu(this);
		addMouseListener(this);

		this.setBackground(Color.WHITE);
		this.setLayout(null);

		// Tap into the keyboard focus manager to handle keyboard events
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyEventDispatcher() {
					public boolean dispatchKeyEvent(KeyEvent e) {
						// If the escape key is pressed
						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							// If there is an umlobject being edited
							if (umlShapeBeingEdited != null) {
								// Set it's editing to false
								umlShapeBeingEdited.setEditing(false);

								// No more shape being edited
								umlShapeBeingEdited = null;
							}
						}

						// If the key should not be dispatched to the
						// focused component, set discardEvent to true
						boolean discardEvent = false;

						return discardEvent;
					}

				});

		rand = new Random();

		shapesList = new Hashtable<Integer, UMLShape_Class>();
		linesList = new ArrayList<UMLLine>();
		commentsList = new Hashtable<Integer, UMLShape_CommentBox>();

	}

	// Overload to set the background image to draw in paint update if we wish
	// to add
	// a dotted background on the canvas
	UMLCanvas(Image imgObject) {
		img = imgObject;
	}

	public void setUMLToolBar(UMLToolBar toolbar) {
		umlToolBar = toolbar;
	}

	public void setLastSelected(UMLShape s) {
		firstSelectedShape = s;
	}

	public UMLShape getLastSelected() {
		return firstSelectedShape;
	}

	public void setSecondSelected(UMLShape s) {
		secondSelectedShape = s;
	}

	public UMLShape getSecondSelected() {
		return secondSelectedShape;
	}

	public void setUMLShapeBeingEdited(UMLShape umlShape) {
		// If there is a previous umlshape being edited, set its's
		// editing to false first
		if (umlShapeBeingEdited != null) {
			umlShapeBeingEdited.setEditing(false);
		}

		// Update the shape being edited
		umlShapeBeingEdited = umlShape;

		// CHANGE IF YOU GUYS FIND BEST
		// Unselect any selected ToolBar items
		unselectToolBarItems();
	}

	public void unselectToolBarItems() {
		// Loop over all the items on the toolbar and set their selected state
		// to false
		if (umlToolBar != null) {
			for (int current = 0; current < umlToolBar.getComponentCount(); current++) {
				((JToggleButton) umlToolBar.getComponent(current))
						.setSelected(false);

			}
		}

	}

	public UMLShape getUMLShapeBeingEdited() {
		return umlShapeBeingEdited;
	}

	public void updateSelectedShape(UMLShape newSelectedShape) {
		// This will "de-select" the previously selected UMLShape,
		// we might want to set a variable for the last selected (or
		// "currentlySelectedUMLShape")
		// and just deselect it here - might need to write some logic code in
		// case we want
		// two boxes to be highlighted for some reason or another later on?
		// Either case, this will deselect everything before updating it to the
		// 'newSelectedShape'

		if (umlToolBar.getBtnShape_Delete().isSelected()) {

			Iterator itLines = linesList.iterator();

			while (itLines.hasNext()) {
				UMLLine line = (UMLLine) itLines.next();
				if (line.getFirst() == newSelectedShape
						|| line.getSecond() == newSelectedShape) {
					remove(line);
					linesList.remove(line);
				}
			}

			remove(newSelectedShape);
			if (newSelectedShape instanceof UMLShape_Class) {
				shapesList.remove(newSelectedShape);
			} else if (newSelectedShape instanceof UMLShape_CommentBox) {
				commentsList.remove(newSelectedShape);
			}
			this.repaint();
			return;

		}

		for (int current = 0; current < this.getComponentCount(); current++) {
			if (this.getComponent(current) instanceof UMLShape) {
				((UMLShape) this.getComponent(current)).setSelected(false);
			}
		}

		if (umlToolBar.getBtnShape_Class().isSelected()) {
			if (newSelectedShape == null) {
				System.out.println("newSelected == null");
				return;
			}

			// If this is the same shape as last time bail out
			if (newSelectedShape == firstSelectedShape) {
				System.out.println("newSelectedShape == firstSelectedShape");
				return;
			}

			if (firstSelectedShape != null) {
				System.out.println("firstSelectedShape != null");

				// Update the last selected shape's selected state to false
				firstSelectedShape.setSelected(false);
			}

			if (secondSelectedShape == null) {
				System.out.println("secondSelectedShape == null");

			}

			System.out.println("before update");

			// Update the new selected shape's z order and set it's selected
			// state to true
			this.setComponentZOrder(newSelectedShape, Z_TOP_CHILD);
			newSelectedShape.setSelected(true);

			System.out.println("before last selected update");
			// Set the new lastSelectedState
			firstSelectedShape = newSelectedShape;
			testShape = newSelectedShape;

			// repaint
			this.repaint();

			System.out.println("firstSelectedShape = " + firstSelectedShape);

		} else {
			// Even if there isn't a UML toolbar button selected, set the shape
			// we just clicked on selected state
			// to true
			newSelectedShape.setSelected(true);
			testShape = newSelectedShape;
		}

		if (umlToolBar.getBtnShape_Line().isSelected()) {

			if (firstSelectedShape != null && secondSelectedShape == null) {
				// second shape is the same as the first
				if (firstSelectedShape == newSelectedShape) {
					System.out
							.println("firstSelectedShape == newSelectedShape");
					return;
				}
				// second shape is different from first,
				if (firstSelectedShape != newSelectedShape) {
					System.out.println("First is selected, second isn't");
					secondSelectedShape = newSelectedShape;
					System.out.println("Now second shape selected");
					this.setComponentZOrder(newSelectedShape, Z_TOP_CHILD);

					if (firstSelectedShape != null
							&& secondSelectedShape != null) {
						System.out.println("Reached Here");

						System.out
								.println("Coordinates for firstSelectedShape: "
										+ firstSelectedShape.getX() + " , "
										+ firstSelectedShape.getY());
						System.out
								.println("Coordinates for secondSelectedShape: "
										+ secondSelectedShape.getX()
										+ " , "
										+ secondSelectedShape.getY());

						if (!(secondSelectedShape instanceof UMLShape_CommentBox)
								&& !(firstSelectedShape instanceof UMLShape_CommentBox)) {
							UMLLine newLine = new UMLLine(firstSelectedShape,
									secondSelectedShape, this);

							this.add(newLine);
							linesList.add(newLine);

							System.out.println("Reached Here");
						}
						this.repaint();

					}
					firstSelectedShape = null;
					secondSelectedShape = null;
					return;

				}

			}
			// check to see if its the first shape
			if (firstSelectedShape == null && secondSelectedShape == null) {
				System.out.println("No Shapes Selected");
				firstSelectedShape = newSelectedShape;
				testShape = newSelectedShape;
				System.out.println("First shape is selected");
				this.setComponentZOrder(newSelectedShape, Z_TOP_CHILD);
			}

			// first shape is occupied, check to see if second shape and occupy
			// it

		}

		// Repaint
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// If the left mouse button is pressed and the umlToolbar is valid
		if (e.getButton() == MouseEvent.BUTTON1 && umlToolBar != null) {

			// Check if there is a child at this location first
			Component c = this.findComponentAt(e.getX(), e.getY());
			System.out.println("x: " + e.getX() + " y: " + e.getY()
					+ "  findComponentAt.class = " + c.getClass());

			if (c.getClass() != this.getClass()) {
				if (umlToolBar.getBtnShape_Class().isSelected()) {
					int id = rand.nextInt();
					UMLShape_Class newShape = new UMLShape_Class(e.getX(),
							e.getY(), id, false);
					shapesList.put(new Integer(id), newShape);
					this.add(newShape);
					this.setComponentZOrder(newShape, Z_TOP_CHILD);
					this.repaint();
				}

				if (umlToolBar.getBtnShape_CommentBox().isSelected()) {
					int id = rand.nextInt();
					UMLShape_CommentBox newComBox = new UMLShape_CommentBox(
							e.getX(), e.getY(), id, false);
					commentsList.put(new Integer(id), newComBox);
					this.add(newComBox);
					this.setComponentZOrder(newComBox, Z_TOP_CHILD);
					this.repaint();
				}
			}

			// If the left mouse click is inside the canvas object .. check if
			// any toggle buttons are selected
			// if so create a new object at the mouse position if the Component
			// 'c' is equal to the canvas object

			if (c.getClass() == this.getClass() || c instanceof UMLLine) {
				// TODO Do a loop and take the correct action based on the
				// toggled button
				// umlToolBar.getToggledButtons(); <-- add toggle buttons to an
				// array list

				// One for now - add more later
				// Check if the class toggle button is selected
				if (umlToolBar.getBtnShape_Class().isSelected()) {
					int id = rand.nextInt();
					UMLShape_Class newShape = new UMLShape_Class(e.getX(),
							e.getY(), id, false);
					shapesList.put(new Integer(id), newShape);
					this.add(newShape);
					this.repaint();

					// De-select the class shape? or leave toggled to create
					// more class objects?
					// Design decision we need to decide on.
				}
				if (umlToolBar.getBtnShape_CommentBox().isSelected()) {
					int id = rand.nextInt();
					UMLShape_CommentBox newComBox = new UMLShape_CommentBox(
							e.getX(), e.getY(), id, false);
					commentsList.put(new Integer(id), newComBox);
					this.add(newComBox);
					this.repaint();
				}

				// If no umlToolBar items are selected, and the user just
				// randomly clicked on the canvas itself
				// Check if there is a umlShapeBeingEdit object, and if so , set
				// it's editing to false.
				if (umlShapeBeingEdited != null) {
					umlShapeBeingEdited.setEditing(false);
					// No longer an editing object
					umlShapeBeingEdited = null;
				} else {

					// For now loop through all the children of UMLShape and set
					// it's selected state to false
					// if the user clicked on the canvas and there's no buttons
					// selected or something being edited
					for (int current = 0; current < this.getComponentCount(); current++) {
						if (this.getComponent(current) instanceof UMLShape) {
							((UMLShape) this.getComponent(current))
									.setSelected(false);
						}

						// Also set both first and second selected uml diagrams
						// to false (Change? do we like this?)
						firstSelectedShape = null;
						secondSelectedShape = null;

					}

					// Repaint
					this.repaint();

				}

			}

			// If the class at the location x,y is of a UMLShape, set it's
			// selection to true
			// also change any editing shape if one is currently being edited.
			else if (c instanceof UMLShape) {
				// Update the selected shape
				updateSelectedShape((UMLShape) c);

				// Stop editing any shape if one is being edited
				if (umlShapeBeingEdited != null) {
					umlShapeBeingEdited.setEditing(false);
					umlShapeBeingEdited = null;
				}
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		showPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		showPopup(e);

	}

	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void saveCanvas(String fileName) {
		UMLSave.save(fileName, shapesList, linesList, commentsList);
	}

	public void loadCanvas(String fileName) {
		UMLLoad loader = new UMLLoad(fileName, this);
		shapesList = loader.getShapes();
		linesList = loader.getLines();
		commentsList = loader.getCommentBoxes();
		Collection<UMLShape_Class> shapesCol = shapesList.values();
		for (UMLShape_Class newShape : shapesCol) {
			this.add(newShape);
			this.setComponentZOrder(newShape, Z_TOP_CHILD);
		}

		Collection<UMLShape_CommentBox> commentsCol = commentsList.values();
		for (UMLShape_CommentBox newShape : commentsCol) {
			this.add(newShape);
			this.setComponentZOrder(newShape, Z_TOP_CHILD);
		}

		for (UMLLine line : linesList) {
			this.add(line);
		}

		repaint();
	}

	public void clearCanvas() {

		for (Component c : this.getComponents()) {

			remove(c);
			if (c instanceof UMLShape_CommentBox) {
				commentsList.remove(c);
			} else if (c instanceof UMLShape_Class) {
				shapesList.remove(c);
			} else if (c instanceof UMLLine) {
				linesList.remove(c);
			}

		}
		repaint();
		revalidate();
	}

	public void copy() {
		unselectToolBarItems();
		copiedShape = testShape;
	}

	public void paste() {
		unselectToolBarItems();
		if (copiedShape instanceof UMLShape_Class) {
			int id = rand.nextInt();
			String text = ((UMLShape_Class) copiedShape).getText();
			UMLShape_Class newShape = new UMLShape_Class(getMousePosition().x,
					getMousePosition().y, id, false);
			newShape.setText(text);
			add(newShape);
			shapesList.put(newShape.getID(), newShape);
		}
		if (copiedShape instanceof UMLShape_CommentBox) {
			int id = rand.nextInt();
			String text = ((UMLShape_CommentBox) copiedShape).getText();
			UMLShape_CommentBox newShape = new UMLShape_CommentBox(
					getMousePosition().x, getMousePosition().y, id, false);
			newShape.setText(text);
			add(newShape);
			commentsList.put(newShape.getID(), newShape);
		}
		repaint();
	}

	public void cut() {
		unselectToolBarItems();
		copiedShape = testShape;
		copiedShape.setSelected(false);
		Iterator itLines = linesList.iterator();
		UMLLine line = null;
		while (itLines.hasNext()) {
			line = (UMLLine) itLines.next();
			if (line.getFirst() == copiedShape
					|| line.getSecond() == copiedShape) {
				linesList.remove(line);
				remove(line);
			}
		}
		this.remove(testShape);
		shapesList.remove(testShape);
		repaint();

	}
}