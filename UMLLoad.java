import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Handles the loading of diagrams previously saved as XML files.
 * 
 * @author Team MacroHard
 *
 */
public class UMLLoad {

	private String filePath = "";

	Document dom = null;

	UMLCanvas myCanvas = null;

	private Hashtable<Integer, UMLShape_Class> shapesList = null;
	private ArrayList<UMLLine> linesList = null;
	private Hashtable<Integer, UMLShape_CommentBox> commentBoxList = null;

	public UMLLoad(String file, UMLCanvas canvas) {
		filePath = file;
		myCanvas = canvas;
		shapesList = new Hashtable<Integer, UMLShape_Class>();
		linesList = new ArrayList<UMLLine>();
		commentBoxList = new Hashtable<Integer, UMLShape_CommentBox>();
		parseXmlFile();
		parseDocument();

	}

	private void parseXmlFile() {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse(filePath);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void parseDocument() {
		// get the root elememt
		Element docEle = dom.getDocumentElement();

		// get a nodelist of <employee> elements
		NodeList shapeNodeList = docEle.getElementsByTagName("Shape");
		if (shapeNodeList != null && shapeNodeList.getLength() > 0) {
			for (int i = 0; i < shapeNodeList.getLength(); i++) {

				// get the employee element
				Element el = (Element) shapeNodeList.item(i);

				// get the Employee object
				UMLShape_Class sc = getShapeClass(el);

				// add it to list
				shapesList.put(sc.getID(), sc);
			}
		}

		NodeList lineNodeList = docEle.getElementsByTagName("Line");
		if (lineNodeList != null && lineNodeList.getLength() > 0) {
			for (int i = 0; i < lineNodeList.getLength(); i++) {

				// get the employee element
				Element el = (Element) lineNodeList.item(i);

				// get the Employee object
				UMLLine l = getLine(el);

				// add it to list
				linesList.add(l);
			}
		}

		NodeList commentBoxNodeList = docEle.getElementsByTagName("CommentBox");
		if (commentBoxNodeList != null && commentBoxNodeList.getLength() > 0) {
			for (int i = 0; i < commentBoxNodeList.getLength(); i++) {

				// get the employee element
				Element el = (Element) commentBoxNodeList.item(i);

				// get the Employee object
				UMLShape_CommentBox sc = getShapeCommentBox(el);

				// add it to list
				commentBoxList.put(sc.getID(), sc);
			}
		}
	}

	private UMLShape_Class getShapeClass(Element ele) {
		int id = getIntValue(ele, "id");
		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");
		String text = getTextValue(ele, "text");
		System.out.println(text);
		UMLShape_Class shape = new UMLShape_Class(x, y, id, false);
		shape.setText(text);
		return shape;
	}

	private UMLLine getLine(Element ele) {
		int firstID = getIntValue(ele, "first");
		int secondID = getIntValue(ele, "second");
		//String type = getTextValue(ele, "type");
		String type = ele.getAttribute("type");
		System.out.println(type);
		return new UMLLine(shapesList.get(firstID), shapesList.get(secondID),
				myCanvas, type);
	}

	private UMLShape_CommentBox getShapeCommentBox(Element ele) {
		int id = getIntValue(ele, "id");
		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");
		String text = getTextValue(ele, "text");
		System.out.println(text);
		UMLShape_CommentBox commentBox = new UMLShape_CommentBox(x, y, id,
				false);
		commentBox.setText(text);
		return commentBox;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		// in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	public Hashtable<Integer, UMLShape_Class> getShapes() {
		return shapesList;
	}

	public ArrayList<UMLLine> getLines() {
		return linesList;
	}

	public Hashtable<Integer, UMLShape_CommentBox> getCommentBoxes() {
		return commentBoxList;
	}

}