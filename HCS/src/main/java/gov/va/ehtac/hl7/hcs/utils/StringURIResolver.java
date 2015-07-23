
package gov.va.ehtac.hl7.hcs.utils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 * The Class StringURIResolver.
 */
public final class StringURIResolver implements URIResolver {
	
	/** The documents. */
	Map<String, String> documents = new HashMap<String, String>();

	/**
	 * Put.
	 *
	 * @param href the href
	 * @param document the document
	 * @return the string uri resolver
	 */
	public StringURIResolver put(final String href, final String document) {
		documents.put(href, document);
		return this;
	}

	/* (non-Javadoc)
	 * @see javax.xml.transform.URIResolver#resolve(java.lang.String, java.lang.String)
	 */
	public Source resolve(final String href, final String base)
			throws TransformerException {
		final String s = documents.get(href);
		if (s != null) {
			return new StreamSource(new StringReader(s));
		}
		return null;
	}
}
