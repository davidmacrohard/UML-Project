///*
import java.awt.Color; 
import java.awt.Graphics;
import javax.swing.JPanel;


public class UMLLine extends JPanel{
	
	UMLShape firstSelectedShape = null;
	UMLShape secondSelectedShape = null;
	
	int firstX;
	int firstY;
	int secondX;
	int secondY;
	
	
	//UMLCanvas myCanvas;

	/**
	 * 
//	 */
	private static final long serialVersionUID = 1L;

	public UMLLine(UMLShape firstSelectedShape, UMLShape secondSelectedShape, UMLCanvas mycanvas)
	{
		this.setOpaque(false);
		
		this.firstSelectedShape = firstSelectedShape;
		this.secondSelectedShape = secondSelectedShape;
		
		/*
		firstX = lastSelectedShapeX;
		firstY = lastSelectedShapeY;
		secondX = secondSelectedShapeX;
		secondY = secondSelectedShapeY;
		/
		
		System.out.println("First X:" + firstX);
		System.out.println("First Y:" + firstY);
		System.out.println("Second X:" + secondX);
		System.out.println("Second Y:" + secondY);
		*/
		
		
		this.setVisible(true);
		int sizeX = mycanvas.getWidth();
		int sizeY = mycanvas.getHeight();
		
		
		
		
		this.setSize(sizeX, sizeY);
		

	}

	public void setSize()
	{
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{			
		super.paintComponent(g);
			g.setColor(Color.BLACK);
			System.out.println("drawing a line-----------------");
			
			System.out.println("First X:" + firstSelectedShape.getX());
			System.out.println("First Y:" + firstSelectedShape.getY());
			System.out.println("Second X:" + secondSelectedShape.getX());
			System.out.println("Second Y:" + secondSelectedShape.getY());
			
			
			
			
			g.drawLine((firstSelectedShape.getX() + (firstSelectedShape.getWidth() / 2)),
					   (firstSelectedShape.getY() + (firstSelectedShape.getHeight() / 2)),
					   (secondSelectedShape.getX() + (secondSelectedShape.getWidth() / 2)), 
					   (secondSelectedShape.getY() + (secondSelectedShape.getHeight() / 2)));
	}
}