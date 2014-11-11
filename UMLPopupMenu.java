import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


@SuppressWarnings("serial")
public class UMLPopupMenu extends JPopupMenu {
		
	
	UMLPopupMenu()
	{
	
		JMenuItem menuItem = new JMenuItem("New UML Shape");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.getAccessibleContext().setAccessibleDescription("New UML Shape");
		this.add(menuItem);	
		menuItem.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null, "New UML shape code here");
	            }
	        });
		
		
		JMenuItem menuItem2 = new JMenuItem("New ____");
		menuItem2.setMnemonic(KeyEvent.VK_N);
		menuItem2.getAccessibleContext().setAccessibleDescription("New _____");
		this.add(menuItem2);	
		menuItem2.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null, "New ____ code here");
	            }
	        });
		

	}
}
