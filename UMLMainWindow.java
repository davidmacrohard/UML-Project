import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class UMLMainWindow extends JFrame implements ComponentListener{

    private UMLTabbedPanel umlTabbedPanel = null;


    UMLMainWindow() {
        setTitle("UML Diagram Editor");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // To capture the resize event
        this.addComponentListener(this);
    }

    // Ability to get canvas if you only have your mainWindow
    public UMLCanvas getCanvas() {

        if (umlTabbedPanel == null) {
            System.out.println("umlTabbedPanel is invalid");
            return null;
        }


        Component curTab = umlTabbedPanel.getComponentAt(umlTabbedPanel.GetCurrentTabIndex());

        if (curTab == null) {
            System.out.println("curTab is null ... very bad.");
            return null;
        }


        return (UMLCanvas) curTab;
    }


    public void setUMLTabbedPanel(UMLTabbedPanel t) {
        umlTabbedPanel = t;
    }

	@Override
	public void componentResized(ComponentEvent e) {
		
		// IF the UMLTabbedPanel is not null
		if(umlTabbedPanel != null)
		{
			// Send it an update that we are resizing the main windows dimensions (and pass along the height/width
			umlTabbedPanel.Resizing(e.getComponent().getWidth(), e.getComponent().getHeight());
		}
		
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


}