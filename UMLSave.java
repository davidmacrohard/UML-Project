import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

//For jdk1.5 with built in xerces parser
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class UMLSave {

	private static String filePath = "";

	private static Hashtable<Integer, UMLShape_Class> shapesList = null;
<<<<<<< HEAD
	private static Hashtable<Integer, UMLShape_CommentBox> commentList = null;
=======
>>>>>>> origin/master
	private static ArrayList<UMLLine> linesList = null;

	private static Document dom;
	
<<<<<<< HEAD
	public static void save(String path, Hashtable<Integer, UMLShape_Class> s, ArrayList<UMLLine> l,Hashtable<Integer, UMLShape_CommentBox> c) {
=======
	public static void save(String path, Hashtable<Integer, UMLShape_Class> s, ArrayList<UMLLine> l) {
>>>>>>> origin/master
		filePath = path;
		shapesList = s;
		linesList = l;
		commentList = c;
		createDocument();
		createDOMTree();
		printToFile();
	}

	private static void createDocument() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		DocumentBuilder db = dbf.newDocumentBuilder();

		dom = db.newDocument();

		}catch(ParserConfigurationException pce) {
			System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
		}

	}
	
	private static void createDOMTree(){

		Element rootEle = dom.createElement("Objects");
		dom.appendChild(rootEle);
		
		Element shapesEle = dom.createElement("Shapes");
		rootEle.appendChild(shapesEle);
		
		Element linesEle = dom.createElement("Lines");
		rootEle.appendChild(linesEle);

		Element commentsEle = dom.createElement("CommentBoxes");
		rootEle.appendChild(commentsEle);

		Collection<UMLShape_Class> shapesCol = shapesList.values();
		Iterator<UMLShape_Class> shapeIter  = shapesCol.iterator();
		while(shapeIter.hasNext()) {
			UMLShape_Class s = shapeIter.next();
			Element shapeEle = createShapeElement(s);
			shapesEle.appendChild(shapeEle);
		}
		
		Iterator<UMLLine> lineIter  = linesList.iterator();
		while(lineIter.hasNext()) {
			UMLLine l = lineIter.next();
			Element lineEle = createLineElement(l);
			linesEle.appendChild(lineEle);
		}

		Collection<UMLShape_CommentBox> comCol = commentList.values();
		Iterator<UMLShape_CommentBox> comIter  = comCol.iterator();
		while(comIter.hasNext()) {
			UMLShape_CommentBox c = comIter.next();
			Element comEle = createCommentElement(c);
			linesEle.appendChild(comEle);
		}
	}
	
	private static Element createShapeElement(UMLShape_Class shape) {
		Element shapeEle = dom.createElement("Shape");
		shapeEle.setAttribute("type", "Class");

		Element idEle = dom.createElement("id");
		Text idText = dom.createTextNode("" + shape.getID());
		idEle.appendChild(idText);
		shapeEle.appendChild(idEle);

		Element xEle = dom.createElement("x");
		Text xText = dom.createTextNode("" + shape.getX());
		xEle.appendChild(xText);
		shapeEle.appendChild(xEle);
		
		Element yEle = dom.createElement("y");
		Text yText = dom.createTextNode("" + shape.getY());
		yEle.appendChild(yText);
		shapeEle.appendChild(yEle);
		
		Element textEle = dom.createElement("text");
		Text textText = dom.createTextNode(shape.getText());
		textEle.appendChild(textText);
		shapeEle.appendChild(textEle);

		return shapeEle;
	}
	
	private static Element createLineElement(UMLLine line) {
		Element lineEle = dom.createElement("Line");
		lineEle.setAttribute("type", "Arrow");
		
		Element firstEle = dom.createElement("first");
		Text firstText = dom.createTextNode("" + line.getFirst().getID());
		firstEle.appendChild(firstText);
		lineEle.appendChild(firstEle);
		
		Element secondEle = dom.createElement("second");
		Text secondText = dom.createTextNode("" + line.getSecond().getID());
		secondEle.appendChild(secondText);
		lineEle.appendChild(secondEle);
		
		return lineEle;
	}
	private static Element createCommentElement(UMLShape_CommentBox commentBox) {
		Element comEle = dom.createElement("CommentBox");
		comEle.setAttribute("type", "CommentBox");

		Element idEle = dom.createElement("id");
		Text idText = dom.createTextNode("" + commentBox.getID());
		idEle.appendChild(idText);
		comEle.appendChild(idEle);

		Element xEle = dom.createElement("x");
		Text xText = dom.createTextNode("" + commentBox.getX());
		xEle.appendChild(xText);
		comEle.appendChild(xEle);

		Element yEle = dom.createElement("y");
		Text yText = dom.createTextNode("" + commentBox.getY());
		yEle.appendChild(yText);
		comEle.appendChild(yEle);

		Element textEle = dom.createElement("text");
		Text textText = dom.createTextNode(commentBox.getText());
		textEle.appendChild(textText);
		comEle.appendChild(textEle);

		return comEle;
	}
	
	private static void printToFile(){

		try
		{
			//print
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);


			//to generate a file output use fileoutputstream instead of system.out
			XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File(filePath)), format);

			serializer.serialize(dom);
			System.out.println(dom.toString());

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
}