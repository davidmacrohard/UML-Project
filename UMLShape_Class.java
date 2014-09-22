import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;





//import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class UMLShape_Class extends UMLShape{
	
	private JTextArea classText = new JTextArea("Class Name\n____________\nFunctions\n____________\nVariables");
	
	UMLShape_Class(int x, int y, boolean selected)
	{
		super(x, y, selected);	
		
		classText.setLocation(4,2);
		classText.setSize(this.getHeight() - 4, this.getWidth() - 4);
		classText.setOpaque(false);
		classText.setEditable(false);
		classText.setBackground(Color.white);
		classText.setFocusable(false);
		
		classText.addMouseListener(this);
		
		this.add(classText);
	}

		
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		
		// Only if the left mouse button was clicked, do we update the "focus" window
		// Can change to right mouse also? or middle and popup a menu popup? decision we have to make
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(e.getSource() instanceof JTextArea)
			{
				UMLShape s = (UMLShape)e.getComponent().getParent();
				UMLCanvas c = (UMLCanvas)s.getParent();
			
				c.updateSelectedShape(s);
			}
		}
				
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
		
	public void paintComponent(Graphics g)
	{
			
		/*
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN,12);
		g2D.setFont(font);
		g2D.drawString("Hello", 4+1,  12);
		*/
		
		super.paintComponent(g);
	
		// Background and border
		g.setColor(Color.WHITE);
		g.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 10, 10);
		
		Graphics2D g2D = (Graphics2D)g;
		g2D.setStroke(new BasicStroke(2F));
						
		// If this is selected, draw an outline indicating it is
		if(isSelected)
		{
			// Draw a highlighted border
			g2D.setColor(Color.YELLOW);
			g2D.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		
		}
		else
		{
			
			// Draw normal
			g2D.setColor(Color.BLACK);
			g2D.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		
		}
		
		
		

	}
	

}
