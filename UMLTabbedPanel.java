import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class UMLTabbedPanel extends JTabbedPane {
	
	UMLToolBar umlToolBar = null;
	
	UMLTabbedPanel(UMLToolBar toolBar)
	{
		// Save a reference to the toolbar as the umlCanvas uses it
		umlToolBar = toolBar;
	}	
	
	public int GetCurrentTabIndex()
	{
		return this.getSelectedIndex();
	}
	
	public void AddNewTab()
	{
		String title = "[Untitled " + this.getTabCount() + " ]";

		// JTabbedPane code
		
		UMLCanvas umlCanvas = new UMLCanvas();
		// Set the umlToolBar
		umlCanvas.setUMLToolBar(umlToolBar);
		
		this.addTab(title, null, umlCanvas, title + "'s tab");

		int index = this.indexOfTab(title);
		JPanel tabPanel = new JPanel(new GridBagLayout());
		tabPanel.setOpaque(false);
		JLabel lblTitle = new JLabel("[Untitled]");
		JButton btnClose = new JButton("x");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		
		tabPanel.add(lblTitle, gbc);
		
		gbc.gridx++;
		gbc.weightx = 0;
		tabPanel.add(btnClose, gbc);
		
		this.setTabComponentAt(index,  tabPanel);
		
		//btnClose.addActionListener(this);
	}
}
