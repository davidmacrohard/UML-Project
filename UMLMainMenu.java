import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Handles appearance and behavior of the top menu bar in the editor.
 * 
 * 
 * TODO Mac top menu support (this was working with Gnome, but need OSX support)
 * 
 * @author Team MacroHard
 *
 */
@SuppressWarnings("serial")
public class UMLMainMenu extends JMenuBar implements ActionListener {

	private JMenuItem fileMenu_New = null;
	private JMenuItem fileMenu_OpenProject = null;
	private JMenuItem fileMenu_Save = null;
	private JMenuItem fileMenu_SaveAs = null;
	private JMenuItem fileMenu_Exit = null;
	private JMenuItem fileMenu_ClearCanvas = null;
	private JMenuItem fileMenu_Copy = null;
	private JMenuItem fileMenu_RenameTab = null;
	private JMenuItem fileMenu_Paste = null;
	private JMenuItem fileMenu_Cut = null;

	private UMLMainWindow mainWindow = null;
	private UMLTabbedPanel umlTabbedPanel = null;

	private String fileName = "";

	UMLMainMenu(UMLMainWindow mainwindow) {
		// Save the main window, easy to extract canvas object and what not?
		// Add functions to UMLMainWindow to set/get UMLCanvas / toolbar / etc
		// if
		// we need to use them for whatever reason in regards to menus
		mainWindow = mainwindow;

		// TODO Streamline this so it's not hard coded - read an XML file?
		JMenu menuFile = new JMenu("File");
		setupMenuItem(menuFile, KeyEvent.VK_F, "File Management");
		this.add(menuFile);

		JMenu menuEdit = new JMenu("Edit");
		setupMenuItem(menuEdit, KeyEvent.VK_D, "Edit Management");
		this.add(menuEdit);

		JMenu menuView = new JMenu("View");
		setupMenuItem(menuView, KeyEvent.VK_V, "View");
		this.add(menuView);

		JMenu menuHelp = new JMenu("Help");
		setupMenuItem(menuHelp, KeyEvent.VK_H, "Help");
		this.add(menuHelp);

		fileMenu_New = new JMenuItem("New UML Diagram");
		setupMenuItem(fileMenu_New, KeyEvent.VK_U, "New UML Diagram");
		fileMenu_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuFile.add(fileMenu_New);

		fileMenu_OpenProject = new JMenuItem("Open UML Diagram");
		setupMenuItem(fileMenu_OpenProject, KeyEvent.VK_O, "Open UML Diagram");
		fileMenu_OpenProject.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuFile.add(fileMenu_OpenProject);

		menuFile.addSeparator();

		fileMenu_Save = new JMenuItem("Save");
		setupMenuItem(fileMenu_Save, KeyEvent.VK_S, "Save UML Diagram");
		fileMenu_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				ActionEvent.ALT_MASK));
		menuFile.add(fileMenu_Save);

		fileMenu_SaveAs = new JMenuItem("Save UML Diagram As...");
		setupMenuItem(fileMenu_SaveAs, KeyEvent.VK_A, "Save UML Diagram As...");
		fileMenu_SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
				ActionEvent.ALT_MASK));
		menuFile.add(fileMenu_SaveAs);

		menuFile.addSeparator();

		fileMenu_Exit = new JMenuItem("Exit");
		setupMenuItem(fileMenu_Exit, KeyEvent.VK_X, "Exit");
		fileMenu_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
				ActionEvent.ALT_MASK));
		menuFile.add(fileMenu_Exit);

		fileMenu_ClearCanvas = new JMenuItem("Clear Canvas");
		setupMenuItem(fileMenu_ClearCanvas, KeyEvent.VK_L, "Clear Canvas");
		fileMenu_ClearCanvas.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_6, ActionEvent.ALT_MASK));
		menuEdit.add(fileMenu_ClearCanvas);

		menuEdit.addSeparator();

		fileMenu_RenameTab = new JMenuItem("Rename Tab");
		setupMenuItem(fileMenu_RenameTab, KeyEvent.VK_R, "Rename Tab");
		fileMenu_RenameTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,
				ActionEvent.ALT_MASK));
		menuEdit.add(fileMenu_RenameTab);

		menuEdit.addSeparator();

		fileMenu_Copy = new JMenuItem("Copy");
		setupMenuItem(fileMenu_Copy, KeyEvent.VK_C, "Copy");
		fileMenu_Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		menuEdit.add(fileMenu_Copy);

		fileMenu_Paste = new JMenuItem("Paste");
		setupMenuItem(fileMenu_Paste, KeyEvent.VK_P, "Paste");
		fileMenu_Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		menuEdit.add(fileMenu_Paste);

		fileMenu_Cut = new JMenuItem("Cut");
		setupMenuItem(fileMenu_Cut, KeyEvent.VK_T, "Cut");
		fileMenu_Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		menuEdit.add(fileMenu_Cut);

		// TODO Code for edit/view/help (if we want a help menu?)

	}

	private void setupMenuItem(JMenuItem menu, int mnemonic,
			String AccessibleDescription) {
		menu.setMnemonic(mnemonic);
		menu.getAccessibleContext().setAccessibleDescription(
				AccessibleDescription);
		menu.addActionListener(this);
	}

	public void SetUMLTabbedPanel(UMLTabbedPanel TabbedPanel) {
		umlTabbedPanel = TabbedPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileMenu_Exit) {
			// TODO Boolean check for flag if user changed anything and wants to
			// save or not
			System.exit(0);

		} else if (e.getSource() == fileMenu_ClearCanvas) {
			UMLCanvas canvas = mainWindow.getCanvas();

			canvas.clearCanvas();

		} else if (e.getSource() == fileMenu_New) {
			// Not sure if we want a new tab here or just to start a whole new
			// project?
			// Will add new tab here for now
			if (umlTabbedPanel != null) {
				umlTabbedPanel.AddNewTab();
			}
		} else if (e.getSource() == fileMenu_Save) {
			if (fileName.equals("")) {
				fileName = JOptionPane.showInputDialog("Save as:");
				UMLCanvas canvas = mainWindow.getCanvas();
				canvas.saveCanvas(fileName);
				umlTabbedPanel.RenameTab(fileName);
			} else {
				UMLCanvas canvas = mainWindow.getCanvas();
				canvas.saveCanvas(fileName);
			}
		} else if (e.getSource() == fileMenu_SaveAs) {
			fileName = JOptionPane.showInputDialog("Save as:");
			UMLCanvas canvas = mainWindow.getCanvas();
			canvas.saveCanvas(fileName);
			umlTabbedPanel.RenameTab(fileName);
		} else if (e.getSource() == fileMenu_OpenProject) {
			fileName = JOptionPane.showInputDialog("Open file:");
			UMLCanvas canvas = mainWindow.getCanvas();
			canvas.loadCanvas(fileName);
			umlTabbedPanel.RenameTab(fileName);
		} else if (e.getSource() == fileMenu_RenameTab) {

			if (umlTabbedPanel != null) {
				umlTabbedPanel.RenameTab();
			}
		} else if (e.getSource() == fileMenu_Copy) {
			UMLCanvas canvas = mainWindow.getCanvas();
			canvas.copy();

		} else if (e.getSource() == fileMenu_Paste) {
			UMLCanvas canvas = mainWindow.getCanvas();
			canvas.paste();
		} else if (e.getSource() == fileMenu_Cut) {
			UMLCanvas canvas = mainWindow.getCanvas();
			canvas.cut();
		}
	}
}