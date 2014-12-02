///*
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Draws a line between two UMLShape_Class with an arrowhead facing one of the
 * UMLShape_Class objects on the canvas
 * 
 * @author Team MacroHard
 * 
 */

public class UMLLine extends JPanel {

	UMLShape firstSelectedShape = null;
	UMLShape secondSelectedShape = null;

	BufferedImage image = null;
	UMLCanvas mycanvas;
	String arrowhead = null;
	String linetype = null;

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param firstSelectedShape
	 *            starting point for the line
	 * @param secondSelectedShape
	 *            ending point for the line
	 * @param mycanvas
	 *            canvas for the line to be drawn on
	 */
	public UMLLine(UMLShape firstSelectedShape, UMLShape secondSelectedShape,
					UMLCanvas mycanvas) {
		linetype = chooseLineTip();
		arrowhead = convertLineTip(linetype);

		this.setOpaque(false);
		this.firstSelectedShape = firstSelectedShape;
		this.secondSelectedShape = secondSelectedShape;
		this.mycanvas = mycanvas;
		this.setVisible(true);
		int sizeX = mycanvas.getWidth();
		int sizeY = mycanvas.getHeight();
		this.setSize(sizeX, sizeY);
	}

	public UMLLine(UMLShape firstSelectedShape, UMLShape secondSelectedShape,
				   UMLCanvas mycanvas, String lT) {
		linetype = lT;
		arrowhead = convertLineTip(linetype);

		this.setOpaque(false);
		this.firstSelectedShape = firstSelectedShape;
		this.secondSelectedShape = secondSelectedShape;
		this.mycanvas = mycanvas;
		this.setVisible(true);
		int sizeX = mycanvas.getWidth();
		int sizeY = mycanvas.getHeight();
		this.setSize(sizeX, sizeY);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform tx = new AffineTransform();
		super.paintComponent(g);
		g.setColor(Color.BLACK);

		float angle = (float) Math.toDegrees(getAngle());
		float base = 0;

		if ((315 < angle && angle < 360) || (225 < angle && angle < 270)
				|| (135 < angle && angle < 180) || (45 < angle && angle < 90)) {
			float rightSquareSide = (float) (Math.tan(Math
					.toRadians(angle % 90) % (Math.PI / 2)));
			base = (float) -(50 / (rightSquareSide * .885));
		}

		else if ((270 < angle && angle < 315) || (180 < angle && angle < 225)
				|| (90 < angle && angle < 135) || (0 < angle && angle < 45)) {
			float leftSquareSide = (float) (1 / Math.tan(Math
					.toRadians(angle % 90)));
			base = (float) (50 / (leftSquareSide * .935));
		}

		else if ((angle == 90) || (angle == 180) || (angle == 270)
				|| (angle == 360)) {
			base = 0;
		}

		((Graphics2D) g).setStroke(new BasicStroke(3));

		// draws the arrow and finds the angle to rotate it
		if (linetype == "Association") {
			g.drawLine((firstSelectedShape.getX() + (firstSelectedShape
					.getWidth() / 2)),
					(firstSelectedShape.getY() + (firstSelectedShape
							.getHeight() / 2)),
					(secondSelectedShape.getX() + (secondSelectedShape
							.getWidth() / 2)),
					(secondSelectedShape.getY() + (secondSelectedShape
							.getHeight() / 2)));
		}

		else if (linetype == null) {
		}

		else if (linetype == "Dependency") {
			drawDashedLine(g, (firstSelectedShape.getX() + (firstSelectedShape
					.getWidth() / 2)),
					(firstSelectedShape.getY() + (firstSelectedShape
							.getHeight() / 2)),
					(secondSelectedShape.getX() + (secondSelectedShape
							.getWidth() / 2)),
					(secondSelectedShape.getY() + (secondSelectedShape
							.getHeight() / 2)));
			try {
				image = ImageIO.read(this.getClass().getResource(
						"images/" + arrowhead));

			} catch (IOException e) {
				e.printStackTrace();
			}
			tx.rotate(((-1) * getAngle()), image.getWidth(null) / 2,
					image.getHeight(null) / 2);
		}

		else {
			g.drawLine((firstSelectedShape.getX() + (firstSelectedShape
					.getWidth() / 2)),
					(firstSelectedShape.getY() + (firstSelectedShape
							.getHeight() / 2)),
					(secondSelectedShape.getX() + (secondSelectedShape
							.getWidth() / 2)),
					(secondSelectedShape.getY() + (secondSelectedShape
							.getHeight() / 2)));
			{
				try {
					System.out.println("Arrow head is " + arrowhead);
					image = ImageIO.read(this.getClass().getResource(
							"images/" + arrowhead));

				} catch (IOException e) {
					e.printStackTrace();
				}
				tx.rotate(((-1) * getAngle()), image.getWidth(null) / 2,
						image.getHeight(null) / 2);
			}
		}

		if (270 < angle && angle < 315) {
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (base - 82)));
		}

		else if (225 < angle && angle <= 270) {
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (base - 83)));
		}

		else if (45 < angle && angle < 135) {

			g2d.translate(
					secondSelectedShape.getX()
							+ (secondSelectedShape.getHeight() - 12),
					(secondSelectedShape.getY() - (base - 17)));
		}

		else if (315 < angle || angle < 45) {

			g2d.translate(secondSelectedShape.getX() + (base + 33),
					(secondSelectedShape.getY() + (secondSelectedShape
							.getHeight() / 2)) + 22);
		}

		else if (180 <= angle && angle < 225) {
			g2d.translate((secondSelectedShape.getX() + 65) - (base + 33),
					(secondSelectedShape.getY() - 38));
		}

		else if (135 < angle && angle < 180) {
			g2d.translate((secondSelectedShape.getX() + 68) - (base + 36),
					(secondSelectedShape.getY() - 38));
		} else if (angle == 225) {
			g2d.translate((secondSelectedShape.getX() + 65) - (53 + 33),
					(secondSelectedShape.getY() - 38));
		}

		else if (angle == 135) {
			g2d.translate(
					secondSelectedShape.getX()
							+ (secondSelectedShape.getHeight() - 12),
					(secondSelectedShape.getY() - (53 - 17)));
		}

		else if (angle == 45) {
			g2d.translate(secondSelectedShape.getX() + (53 + 33),
					(secondSelectedShape.getY() + (secondSelectedShape
							.getHeight() / 2)) + 22);
		}

		else if (angle == 315) {
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (53.5 - 81)));
		}

		g2d.drawImage(image, tx, null);
	}

	/**
	 * Calculates the angle of the line to rotate the arrows so they can face
	 * the class box
	 * 
	 * @return angle in radians of the line between the two UMLShape_Classes
	 */
	private float getAngle() {
		float angle = (float) Math.toDegrees(Math.atan2(
				firstSelectedShape.getX() - secondSelectedShape.getX(),
				firstSelectedShape.getY() - secondSelectedShape.getY()));
		if (angle < 0) {
			angle += 360;
		}

		return (float) Math.toRadians(angle);
	}

	public UMLShape getFirst() {
		return firstSelectedShape;
	}

	public UMLShape getSecond() {
		return secondSelectedShape;
	}
	
	public String getType() {
		return linetype;
	}

	/**
	 * Creates a JOptionPane so the user can select which arrowhead the user
	 * would like
	 * 
	 * @return the relationship chosen by the user
	 */
	private String chooseLineTip() {
		// String arrowhead = null;

		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane("Message");
		optionPane.setSize(100, 100);
		optionPane.setVisible(true);
		String[] choices = { "Association", "Directed Association",
				"Generalization", "Dependency", "Aggregation", "Composition" };
		String input = (String) JOptionPane.showInputDialog(frame,
				"Please Choose One", "Please choose one: ",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
		return input;
	}

	/**
	 * Choose the picture used at the end of the arrowhead (if one is used)
	 * 
	 * @param arrowhead
	 *            A string depicting the line type
	 * @return the path of the png file to reference the picture
	 */
	private String convertLineTip(String arrowhead) {
		String input = null;

		if (arrowhead.equals("Dependency") || arrowhead.equals("Directed Association")) {
			input = "arrowhead.png";
		}

		if (arrowhead.equals("Composition")) {
			input = "diamond.png";
		}

		if (arrowhead.equals("Generalization")) {
			input = "arrow.png";
		}

		if (arrowhead.equals("Aggregation")) {
			input = "filleddiamond.png";
		}
		System.out.println("Input is " + arrowhead);
		return input;
	}

	/**
	 * Draws a dashed line between two points
	 * 
	 * @param g
	 *            the graphics component to draw the line
	 * @param x1
	 *            x coordinate of the first point
	 * @param y1
	 *            y coordinate of the first point
	 * @param x2
	 *            x coordinate of the second point
	 * @param y2
	 *            y coordinate of the second point
	 */

	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {
		// creates a copy of the Graphics instance
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(x1, y1, x2, y2);
		// gets rid of the copy
		g2d.dispose();
	}
}