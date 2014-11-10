import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class UMLTabbedPanel extends JTabbedPane implements ActionListener {
	
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
		String title = "[Untitled " + (this.getTabCount() + 1) + " ]";

		// JTabbedPane code
		
		UMLCanvas umlCanvas = new UMLCanvas();
		// Set the umlToolBar
		umlCanvas.setUMLToolBar(umlToolBar);
		
		this.addTab(title, null, umlCanvas, title + "'s tab");

		int index = this.indexOfTab(title);
		JPanel tabPanel = new JPanel(new GridBagLayout());
		tabPanel.setName(title);
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
		
		btnClose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Component parent = ((Component)e.getSource()).getParent();
		
		if(parent != null)
		{
			int index = this.indexOfTab(parent.getName());
			
			if(index >= 0)
			{
				this.removeTabAt(index);
			}
		}
		
	}
}
