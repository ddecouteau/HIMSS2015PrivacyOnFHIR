
package gov.va.ehtac.hl7.hcs.common;


import gov.va.ehtac.hl7.hcs.common.RuleExecutionResponse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class RuleExecutionContainer.
 */
@XmlRootElement(name="ruleExecutionContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RuleExecutionContainer {
	
	/** The execution response list. */
	@XmlElementWrapper(name="executionResponseList")
	@XmlElement(name="executionResponse")
	private List<RuleExecutionResponse> executionResponseList;
	
	/**
	 * Instantiates a new rule execution container.
	 */
	public RuleExecutionContainer()
	{
		executionResponseList = new ArrayList<RuleExecutionResponse>();
	}

	
	/**
	 * Gets the execution response list.
	 *
	 * @return the execution response list
	 */
	public List<RuleExecutionResponse> getExecutionResponseList() {
		return executionResponseList;
	}

	/**
	 * Sets the execution response list.
	 *
	 * @param executionResponseList the new execution response list
	 */
	public void setExecutionResponseList(
			List<RuleExecutionResponse> executionResponseList) {
		this.executionResponseList = executionResponseList;
	}

	/**
	 * Adds the execution response.
	 *
	 * @param ruleExecutionResponse the rule execution response
	 */
	public void addExecutionResponse(RuleExecutionResponse ruleExecutionResponse) {
		getExecutionResponseList().add(ruleExecutionResponse);
	}

}
