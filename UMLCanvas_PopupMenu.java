import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


@SuppressWarnings("serial")
public class UMLCanvas_PopupMenu extends JPopupMenu implements ActionListener {
		
	private JMenuItem menu_NewShape = null;
	private JMenuItem menu_RenameTab = null;
	
	private UMLCanvas theParent = null;
	
	UMLCanvas_PopupMenu(UMLCanvas parent)
	{
		// Set the parent object of this menu
		theParent = parent;
	
		menu_NewShape = new JMenuItem("New UML Shape");
		menu_NewShape.setMnemonic(KeyEvent.VK_P);
		menu_NewShape.getAccessibleContext().setAccessibleDescription("New UML Shape");
		menu_NewShape.addActionListener(this);
		this.add(menu_NewShape);	
	
		this.addSeparator();
		
		menu_RenameTab = new JMenuItem("Rename Tab");
		menu_RenameTab.setMnemonic(KeyEvent.VK_R);
		menu_RenameTab.getAccessibleContext().setAccessibleDescription("Rename Tab");
		menu_RenameTab.addActionListener(this);
		this.add(menu_RenameTab);	
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menu_NewShape)
		{
			JOptionPane.showMessageDialog(null, "New UML shape code here");
		}
		
		if(e.getSource() == menu_RenameTab && theParent != null)
		{
			UMLTabbedPanel tabbedPanel = (UMLTabbedPanel)theParent.getParent();
			tabbedPanel.RenameTab();
		}
		
	}
}
