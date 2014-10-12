import java.awt.BorderLayout;
import javax.swing.JToolBar;



public class UMLMain {

	public static void main(String[] args) {

		UMLMainWindow mainWindow 	= new UMLMainWindow();	
		UMLCanvas umlCanvas 		= new UMLCanvas();
		UMLToolBar umlToolBar 		= new UMLToolBar("UML_Main_ToolBar", JToolBar.VERTICAL);
		UMLMainMenu mainMenu 		= new UMLMainMenu(mainWindow);
		
		
		mainWindow.add(umlToolBar, BorderLayout.WEST);
		mainWindow.setJMenuBar(mainMenu);
		umlCanvas.setUMLToolBar(umlToolBar);
		
		
		mainWindow.add(umlCanvas, BorderLayout.CENTER);
		mainWindow.setVisible(true);
	}

}
