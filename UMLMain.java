import java.awt.BorderLayout;
import javax.swing.JToolBar;



public class UMLMain {

	// Add getters and set to private
	public static UMLMainWindow mainWindow = null;
	public static UMLToolBar umlToolBar = null;
	public static UMLMainMenu mainMenu = null;
	public static UMLTabbedPanel umlTabbedPanel = null;
	
	public static void main(String[] args) {

		mainWindow 		= new UMLMainWindow();	
		umlToolBar 		= new UMLToolBar("UML_Main_ToolBar", JToolBar.VERTICAL);
		mainMenu 		= new UMLMainMenu(mainWindow);
		
		
		mainWindow.add(umlToolBar, BorderLayout.WEST);
		mainWindow.setJMenuBar(mainMenu);
				
		umlTabbedPanel = new UMLTabbedPanel(umlToolBar);
		// Default starting tab
		umlTabbedPanel.AddNewTab();
		mainWindow.add(umlTabbedPanel, BorderLayout.CENTER);
		
		mainMenu.SetUMLTabbedPanel(umlTabbedPanel);

		
		mainWindow.setVisible(true);
	}

	
	
}
