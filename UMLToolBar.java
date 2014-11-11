import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;


@SuppressWarnings("serial")
public class UMLToolBar extends JToolBar implements ActionListener{

	private JToggleButton	btnShape_Class = null;
	private JToggleButton	btnShape_Line = null;

	
	
	UMLToolBar(String name, int type)
	{
		super(name, type);
		this.setLayout(new GridBagLayout());
		
		btnShape_Class	= new JToggleButton("Class");
		btnShape_Class.addActionListener(this);;
		
		btnShape_Line	= new JToggleButton("Line");
		btnShape_Line.addActionListener(this);

		
		// Create a grid bad constraints layout for this toolbar
		GridBagConstraints   gbc = new GridBagConstraints ();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
			
		// TODO Add actionListener for toggle buttons so if one is selected and then
		// another toggle button is selected, we ensure all toggle buttons are de-toggled
		// except the one last clicked on. Right now we can toggle all buttons.
		
		this.add(btnShape_Class, gbc);
		
		
		gbc.gridy = 1;
		gbc.weighty = 1;
		
		this.add(btnShape_Line, gbc);
		
		
		// To get rid of the dotted lines when you select the button
		btnShape_Class.setFocusable(false);
		btnShape_Line.setFocusable(false);
		
		
		// TODO Add rest of "getUMLShape_**** toggle buttons - or just add an array list and
		// iterate over it checking the state of the current indexed togglebutton
				
		
		// TODO Add ability to "lock" the toolbar and allow it to be "floatable" by changing
		// this variable true/false
		this.setFloatable(false);
		
		
	}
	
	// 
	JToggleButton getBtnShape_Class()
	{
		return btnShape_Class;
	}
	
	JToggleButton getBtnShape_Line()
	{
		return btnShape_Line;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
