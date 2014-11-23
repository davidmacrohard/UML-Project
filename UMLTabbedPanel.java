import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class UMLTabbedPanel extends JTabbedPane implements ActionListener {
	
	UMLToolBar umlToolBar = null;
	private JButton btnAddTab = null;
	private String plusNewTabTitle = "Plus New Tab Title";
	private static final Icon CLOSE_TAB_ICON_NORMAL = new ImageIcon("file_icon_normal.png");
	private static final Icon CLOSE_TAB_ICON_HOVER = new ImageIcon("file_icon_hover.png");
	private static final Icon ADD_TAB_ICON_NORMAL = new ImageIcon("add_icon_normal.png");
	private static final Icon ADD_TAB_ICON_HOVER = new ImageIcon("add_icon_hover.png");
	
	
	UMLTabbedPanel(UMLToolBar toolBar)
	{
		// Save a reference to the toolbar as the umlCanvas uses it
		umlToolBar = toolBar;
		
		CreatePlusTab();
	}	
	
	public int GetCurrentTabIndex()
	{
		return this.getSelectedIndex();
	}
	

	public void Resizing(int newWidth, int newHeight)
	{
		// Loop through the components of this class
		for (Component c : this.getComponents()) 
		{
			// We only care for the canvas class
			if(c instanceof UMLCanvas)
			{
				// loop through the components of the canvas
				for(Component c2 : ((UMLCanvas)c).getComponents())
				{
					// We only care about the UMLines ..
					if(c2 instanceof UMLLine)
					{
						// Cast the c2 component that is a line into a UMLLine object
						UMLLine line = ((UMLLine)c2);
						
						// If it's successful (just to be safe)
						if(line != null)
						{
							// Resize the line object so it fits the new dimensions
							line.setSize(newWidth, newHeight);
						}
					}
				}
				
			}
		}	
		
		
	}
	
	public void AddNewTab()
	{
		//String title = "[Untitled " + (this.getTabCount() + 1) + " ]";
		String title = "" + (this.getTabCount() + 1);

		// JTabbedPane code
		
		UMLCanvas umlCanvas = new UMLCanvas();
		// Set the umlToolBar
		umlCanvas.setUMLToolBar(umlToolBar);
		
		this.addTab(title, null, umlCanvas, title + "'s tab");

		int index = this.indexOfTab(title);
		JPanel tabPanel = new JPanel(new GridBagLayout());
		tabPanel.setName(title);
		tabPanel.setOpaque(false);
		JLabel lblTitle = new JLabel("[Untitled] ");
		
		
		
		JButton btnClose = new JButton();
		btnClose.setOpaque(false);
		

		// Configure icon and rollover icon for button
		btnClose.setRolloverIcon(CLOSE_TAB_ICON_HOVER);
		btnClose.setRolloverEnabled(true);
		btnClose.setIcon(CLOSE_TAB_ICON_NORMAL);

		// Set border null so the button doesn’t make the tab too big
		btnClose.setBorder(null);

		// Make sure the button can’t get focus, otherwise it looks funny
		btnClose.setFocusable(false);

		
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
		
		
		// MovePlusTabToEnd - move the plus tab to the end
		MovePlusTabToEnd();
		
	}
	
	private void CreatePlusTab()
	{

		UMLCanvas umlCanvas = new UMLCanvas();
		umlCanvas.setEnabled(false);
		
		this.addTab(plusNewTabTitle, null, umlCanvas, "Create new tab");
		
		int index = this.indexOfTab(plusNewTabTitle);
		JPanel tabPanel = new JPanel(new GridBagLayout());
		tabPanel.setName(plusNewTabTitle);
		tabPanel.setOpaque(false);
		JLabel lblTitle = new JLabel("");
		
		
		btnAddTab = new JButton();
		btnAddTab.setOpaque(false);

		// Configure icon and rollover icon for button
		btnAddTab.setRolloverIcon(ADD_TAB_ICON_HOVER);
		btnAddTab.setRolloverEnabled(true);
		btnAddTab.setIcon(ADD_TAB_ICON_NORMAL);

		// Set border null so the button doesn’t make the tab too big
		btnAddTab.setBorder(null);

		//Make sure the button can’t get focus, otherwise it looks funny
		btnAddTab.setFocusable(false);

		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		
		tabPanel.add(lblTitle, gbc);
		
		gbc.gridx++;
		gbc.weightx = 0;
		tabPanel.add(btnAddTab, gbc);
		
		this.setTabComponentAt(index,  tabPanel);
		
		btnAddTab.addActionListener(this);
		
	}
	
	private void MovePlusTabToEnd()
	{
		// Ugly .. could find a way to optimize to just remove and
		// set a new tab to the removed component but it'll do for now.
		this.removeTabAt(this.indexOfTab(plusNewTabTitle));
		CreatePlusTab();		
	}
	
	
	public void RenameTab()
	{
		if(this.getTabCount() > 1)
		{
			String newTabTitle = JOptionPane.showInputDialog("New Tab Title:");
			
			if(newTabTitle != "")
			{			
				JPanel panel = (JPanel) this.getTabComponentAt(getSelectedIndex());			
				JLabel label = (JLabel) panel.getComponent(0);
				label.setText(newTabTitle);
			}

		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// *** VERY IMPORTANT **
		// Very important to return after a AddNewTab event
		// else code will continue and actually remove the Plus tab 
		// Could get very ugly if we had to create a dynmaic array
		// or some other method to keep track of x-variable amount of
		// [x] buttons from n.. n+1 new tabs the user creates and keep track
		// of which [x] button was clicked to close that specific tab
		// easier just to close the tab associated with the event of a  
		// [x] button if it's not our "Add A New Tab Plus Button"
		if(e.getSource() == btnAddTab)
		{
			AddNewTab();
			return;
			
		}
		
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

