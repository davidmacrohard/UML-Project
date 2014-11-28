import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Attributes for generic UML shapes (class diagrams as well as lines)
 * 
 * @author Team MacroHard
 *
 */
@SuppressWarnings("serial")
public abstract class UMLShape extends JPanel implements MouseListener,
		MouseMotionListener {

	protected boolean isSelected = false;
	protected boolean bEditing = false;

	private int id = 0;

	UMLShape(int x, int y, int id, boolean selected) {
		this.setLocation(x, y);
		this.setSize(100, 100);

		this.setVisible(true);

		isSelected = selected;
		this.id = id;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	public boolean selectedState() {
		return isSelected;
	}

	public void setEditing(boolean isEditing) {
		bEditing = isEditing;
	}

	public boolean isEditing() {
		return bEditing;
	}

	public int getID() {
		return id;
	}
}
