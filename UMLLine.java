///*
import java.awt.BasicStroke;
import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Draws a line between two UMLShape_Class with an arrowhead
 * facing one of the UMLShape_Class objects on the canvas
 * 
 * @author Ryan Schgier
 * 
 * @param firstSelectedShape	starting point for the line
 * @param secondSelectedShape 	ending point for the line
 * @param mycanvas				canvas for the line to be drawn on
 * 
 */

public class UMLLine extends JPanel{

	UMLShape firstSelectedShape = null;
	UMLShape secondSelectedShape = null;

	int firstX;
	int firstY;
	int secondX;
	int secondY;

	Image image;


	UMLCanvas mycanvas;


	private static final long serialVersionUID = 1L;

	public UMLLine(UMLShape firstSelectedShape, UMLShape secondSelectedShape, UMLCanvas mycanvas)
	{
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
	public void paintComponent(Graphics g)
	{			

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform tx = new AffineTransform();

		super.paintComponent(g);
		g.setColor(Color.BLACK);
		System.out.println("drawing a line-----------------");


		float angle = (float) Math.toDegrees(getAngle());

		float base = 0;

		// works for right side of a a side of the square

		System.out.println("Angle: " + angle);

		if ((315 < angle && angle < 360) || (225 < angle && angle < 270 ) || (135 < angle && angle < 180) || (45 < angle && angle < 90 ))
		{
			float rightSquareSide =  (float) (Math.tan(Math.toRadians(angle % 90) % (Math.PI / 2)));

			base = (float) -(50 / (rightSquareSide * .885));
		}




		else if ((270 < angle && angle < 315) || (180 < angle && angle < 225)  || ( 90 < angle && angle < 135 ) || (0 < angle && angle < 45 ))
		{
			float leftSquareSide = (float) (1 / Math.tan(Math.toRadians(angle % 90)));

			base = (float) (50 / (leftSquareSide * .935));
		}


		else if((angle == 90) ||(angle ==  180) ||(angle ==  270) ||(angle ==  360))
		{
			base = 0;
		}

		((Graphics2D) g).setStroke(new BasicStroke(3));

		g.drawLine((firstSelectedShape.getX() + (firstSelectedShape.getWidth() / 2)),
				(firstSelectedShape.getY() + (firstSelectedShape.getHeight() / 2)),
				(secondSelectedShape.getX() + (secondSelectedShape.getWidth() / 2)), 
				(secondSelectedShape.getY() + (secondSelectedShape.getHeight() / 2)));



		// draws the arrow and finds the angle to rotate it
		try {
			image = ImageIO.read(new File("arrow6.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		tx.rotate(((-1) * getAngle()), image.getWidth(null)/2, image.getHeight(null)/2);



		if (270 < angle && angle < 315)
		{
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (base - 81)));
		}

		else if (225 < angle && angle <= 270)
		{
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (base - 83)));
		}

		else if (45 < angle && angle < 135){

			g2d.translate(secondSelectedShape.getX() + (secondSelectedShape.getHeight() - 12),
					(secondSelectedShape.getY() - (base - 17 )));
		}

		else if (315 < angle || angle < 45)
		{

			g2d.translate(secondSelectedShape.getX() + (base + 33),
					(secondSelectedShape.getY() + (secondSelectedShape.getHeight() / 2))+22);
		}

		else if (180 <= angle && angle < 225)
		{
			g2d.translate((secondSelectedShape.getX() + 65) - (base + 33),
					(secondSelectedShape.getY() - 38));	
		}

		else if (135 < angle && angle < 180)
		{
			g2d.translate((secondSelectedShape.getX() + 68) - (base + 36),
					(secondSelectedShape.getY() - 38));	
		}
		else if (angle == 225)
		{
			g2d.translate((secondSelectedShape.getX() + 65) - (53 + 33),
					(secondSelectedShape.getY() - 38));	
		}

		else if (angle == 135)
		{
			g2d.translate(secondSelectedShape.getX() + (secondSelectedShape.getHeight() - 12),
					(secondSelectedShape.getY() - (53 - 17 )));
		}

		else if (angle == 45)
		{
			g2d.translate(secondSelectedShape.getX() + (53 + 33),
					(secondSelectedShape.getY() + (secondSelectedShape.getHeight() / 2))+22);
		}

		else if (angle == 315)
		{
			g2d.translate(secondSelectedShape.getX() - 23,
					((secondSelectedShape.getY() + 100) + (53.5 - 81)));
		}


		g2d.drawImage(image, tx, null);

		System.out.println("Base: "+ base);

	}

	/**
	 * 
	 * @return			angle in radians of the line between the two UMLShape_Classes
	 */
	private float getAngle() {
		float angle = (float) Math.toDegrees(Math.atan2(firstSelectedShape.getX() - secondSelectedShape.getX(),
				firstSelectedShape.getY() - secondSelectedShape.getY()));
		if(angle < 0){
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


}