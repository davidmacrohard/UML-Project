import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class UMLShape extends JPanel implements MouseListener, MouseMotionListener{
	
	protected boolean isSelected = false;
	protected boolean bEditing = false;
	
	UMLShape(int x, int y, boolean selected)
	{
		this.setLocation(x, y);
		this.setSize(100, 100);

		this.setVisible(true);

		isSelected = selected;
	}
	
	public void setSelected(boolean selected)
	{
		isSelected = selected;
	}
	
	public boolean selectedState()
	{
		return isSelected;
	}
	
	
	public void setEditing(boolean isEditing)
	{
		bEditing = isEditing;
	}
	
	public boolean isEditing()
	{
		return bEditing;
	}

}
