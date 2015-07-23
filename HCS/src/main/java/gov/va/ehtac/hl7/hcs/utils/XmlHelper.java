
package gov.va.ehtac.hl7.hcs.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * The Class XmlHelper.
 */
public class XmlHelper {
	
	/**
	 * *****************************************
	 * Load XML document from string
	 * ******************************************.
	 *
	 * @param xmlString the xml string
	 * @return the document
	 * @throws Exception the exception
	 */
	public static Document loadDocument(String xmlString) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource source = new InputSource(new StringReader(xmlString));

		Document doc = db.parse(source);

		return doc;
	}
	
	/**
	 * *****************************************
	 * Convert XML document to string
	 * ******************************************.
	 *
	 * @param xmlDocument the xml document
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerException the transformer exception
	 */
	public static String converXmlDocToString(Document xmlDocument) throws IOException, TransformerException
	{
		String xmlString = "";
		
		 TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer = tf.newTransformer();
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		    StringWriter writer = new StringWriter();
		    transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
		    xmlString = writer.getBuffer().toString().replaceAll("\n|\r", "");
		    return xmlString;
	}
}
