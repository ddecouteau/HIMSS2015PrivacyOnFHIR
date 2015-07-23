/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.common.beans;

/**
 *
 * @author Socraticgrid Staff
 */

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ruleExecutionContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PPSRuleExecutionContainer {
    
    
    	@XmlElementWrapper(name="executionResponseList")
	@XmlElement(name="executionResponse")
	private List<PPSRuleExecutionResponse> executionResponseList;
        
	public PPSRuleExecutionContainer()
	{
		executionResponseList = new ArrayList<PPSRuleExecutionResponse>();
	}

	
	/**
	 * Gets the execution response list.
	 *
	 * @return the execution response list
	 */
	public List<PPSRuleExecutionResponse> getExecutionResponseList() {
		return executionResponseList;
	}

	/**
	 * Sets the execution response list.
	 *
	 * @param executionResponseList the new execution response list
	 */
	public void setExecutionResponseList(
			List<PPSRuleExecutionResponse> executionResponseList) {
		this.executionResponseList = executionResponseList;
	}

	/**
	 * Adds the execution response.
	 *
	 * @param ruleExecutionResponse the rule execution response
	 */
	public void addExecutionResponse(PPSRuleExecutionResponse ruleExecutionResponse) {
		getExecutionResponseList().add(ruleExecutionResponse);
	}
        

}
