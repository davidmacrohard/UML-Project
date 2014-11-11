import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class UMLMainWindow extends JFrame {

	private UMLTabbedPanel umlTabbedPanel = null;
	
	
	UMLMainWindow()
	{
		setTitle("UML Diagram Editor");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Ability to get canvas if you only have your mainWindow
		public UMLCanvas getCanvas(){
			Container conPan = this.getContentPane();
			JPanel myPanel = (JPanel) (umlTabbedPanel.getTabComponentAt(umlTabbedPanel.GetCurrentTabIndex()));
	
			Component[] conPan2 = myPanel.getComponents();
			
			UMLCanvas canvas = null;
			
			for (int j = 0; j < conPan2.length; j++){
				if (conPan2[j] instanceof UMLCanvas){
					canvas = (UMLCanvas) conPan2[j];
					break;
				}
			}
			
			return canvas;
		}
		
		public void setUMLTabbedPanel (UMLTabbedPanel t) {
			umlTabbedPanel = t;
		}
}