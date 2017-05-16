package mt.server.xml;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mt.Order;

public class XMLProcessor {
	
	public static final String XMLFILE = "MicroTraderPersistence.xml";
	private Document doc;
	
	
	
	public XMLProcessor() {
		 try {	
	         File xml = new File(XMLFILE);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         doc = dBuilder.parse(xml);
	         doc.getDocumentElement().normalize();         	       
	      } catch (Exception e) { e.printStackTrace(); }
	   } 
	
	public void insertOrder(Order order) throws Exception {
		// Create new element Order with attributes
        Element newElementOrder = doc.createElement("Order");
       
        String id = Integer.toString(order.getServerOrderID());
        String type = (order.isSellOrder() ? "Sell" : "Buy");
        String stock = order.getStock();
        String units = Integer.toString(order.getNumberOfUnits());
        String price = Double.toString(order.getPricePerUnit());
        
        newElementOrder.setAttribute("Id", id);
        newElementOrder.setAttribute("Type", type);
        newElementOrder.setAttribute("Stock", stock);
        newElementOrder.setAttribute("Units", units);
        newElementOrder.setAttribute("Price", price);
        
    
        
        Node n = doc.getDocumentElement();
        n.appendChild(newElementOrder);
        saveXML();
	}
	
	public void saveXML() throws Exception {
		
		  Transformer transformer = TransformerFactory.newInstance().newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         StreamResult result = new StreamResult(new FileOutputStream(XMLFILE));
	         DOMSource source = new DOMSource(doc);
	         transformer.transform(source, result);
	}
		
}


