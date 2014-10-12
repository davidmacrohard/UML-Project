import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



@SuppressWarnings("serial")
public class UMLShape_Class_PopupMenu extends JPopupMenu implements ActionListener {
		
	private JMenuItem menu_Edit = null;
	private UMLShape thisParent = null;
	
	
	UMLShape_Class_PopupMenu(UMLShape parent)
	{
		// Set the parent object of this menu
		thisParent = parent;		
	
		menu_Edit = new JMenuItem("Edit");
		menu_Edit.setMnemonic(KeyEvent.VK_E);
		menu_Edit.getAccessibleContext().setAccessibleDescription("Edit UML Class");
		menu_Edit.addActionListener(this);
		this.add(menu_Edit);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(".......");
		if(e.getSource() == menu_Edit)
		{
			if(thisParent != null)
			{
			
				UMLCanvas canvas = (UMLCanvas)thisParent.getParent();
				canvas.setUMLShapeBeingEdited(thisParent);
				thisParent.setEditing(true);
			}
		}
	}


}
