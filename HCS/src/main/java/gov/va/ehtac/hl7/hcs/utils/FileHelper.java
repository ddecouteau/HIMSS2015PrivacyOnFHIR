
package gov.va.ehtac.hl7.hcs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * The Class FileHelper.
 */
public class FileHelper {

	/**
	 * *****************************************
	 * Output string to file
	 * ******************************************.
	 *
	 * @param stringContent the string content
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	public static void writeStringToFile(String stringContent, String fileName)
			throws Exception {

		PrintWriter printOut = new PrintWriter(fileName);
		printOut.println(stringContent);
		printOut.close();
	}

	/**
	 * *****************************************
	 * Output Document to file
	 * ******************************************.
	 *
	 * @param doc the doc
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	public static void writeDocToFile(Document doc, String fileName)
			throws Exception {
		File encryptionFile = new File(fileName);
		FileOutputStream f = new FileOutputStream(encryptionFile);

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(f);
		transformer.transform(source, result);

		f.close();
	}

	/**
	 * *****************************************
	 * Output Bytes to file
	 * ******************************************.
	 *
	 * @param byteContent the byte content
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	public static void writeBytesToFile(byte[] byteContent, String fileName)
			throws Exception {

		File kekFile = new File(fileName);
		FileOutputStream f = new FileOutputStream(kekFile);
		f.write(byteContent);
		f.close();
	}	
}
