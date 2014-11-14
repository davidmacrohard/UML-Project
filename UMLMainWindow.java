import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class UMLMainWindow extends JFrame {

    private UMLTabbedPanel umlTabbedPanel = null;


    UMLMainWindow() {
        setTitle("UML Diagram Editor");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}