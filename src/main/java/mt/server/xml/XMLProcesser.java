package mt.server.xml;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import mt.Order;

public class XMLProcesser {
	
	public static final String XMLFILE = "orders.xml";
	private File xml;
	
	public XMLProcesser() {
		xml = new File(XMLFILE);
		
	}
	public void writeOrders(Set<Order> orderlist){
		try {
			FileWriter writer = new FileWriter(xml, true);
			writer.write("<?xml version =\"1.0\" encoding=\"UTF-8\" standalone=\"no\">");
			writer.write("<XML>\n");
			for(Order o: orderlist){
				writer.write("	<Order ID=\"" + o.getServerOrderID() + "\" Price=\"" + o.getPricePerUnit()
						+ "\" Stock=\"" + o.getStock() + "\" Units=\"" + o.getNumberOfUnits() + ">\n");
				writer.write("		<Customer>" + o.getNickname() + "</Customer>");
				writer.write("	</Order>");
			}
			writer.write("</XML>\n");
			writer.close();
		} catch (IOException e) {
		
		}
	}
	
}
