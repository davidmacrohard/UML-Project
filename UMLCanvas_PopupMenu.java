import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


@SuppressWarnings("serial")
public class UMLCanvas_PopupMenu extends JPopupMenu implements ActionListener {
		
	private JMenuItem menu_NewShape = null;
	private JMenuItem menu_NewSomething = null;
	@SuppressWarnings("unused")
	private UMLCanvas theParent = null;
	
	UMLCanvas_PopupMenu(UMLCanvas parent)
	{
		// Set the parent object of this menu
		theParent = parent;
	
		menu_NewShape = new JMenuItem("New UML Shape");
		menu_NewShape.setMnemonic(KeyEvent.VK_P);
		menu_NewShape.getAccessibleContext().setAccessibleDescription("New UML Shape");
		this.add(menu_NewShape);	
	
		
		menu_NewSomething = new JMenuItem("New ____");
		menu_NewSomething.setMnemonic(KeyEvent.VK_N);
		menu_NewSomething.getAccessibleContext().setAccessibleDescription("New _____");
		this.add(menu_NewSomething);	
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menu_NewShape)
		{
			JOptionPane.showMessageDialog(null, "New UML shape code here");
		}
		
		if(e.getSource() == menu_NewSomething)
		{
			JOptionPane.showMessageDialog(null, "New Something code here.");
		}
		
	}
}
