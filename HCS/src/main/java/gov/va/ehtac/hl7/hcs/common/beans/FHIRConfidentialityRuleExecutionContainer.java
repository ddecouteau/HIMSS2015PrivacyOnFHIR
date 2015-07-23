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
public class FHIRConfidentialityRuleExecutionContainer {
    
    
    	@XmlElementWrapper(name="executionResponseList")
	@XmlElement(name="executionResponse")
	private List<FHIRConfidentialityRuleExecutionResponse> executionResponseList;
        
	public FHIRConfidentialityRuleExecutionContainer()
	{
		executionResponseList = new ArrayList<FHIRConfidentialityRuleExecutionResponse>();
	}

	
	/**
	 * Gets the execution response list.
	 *
	 * @return the execution response list
	 */
	public List<FHIRConfidentialityRuleExecutionResponse> getExecutionResponseList() {
		return executionResponseList;
	}

	/**
	 * Sets the execution response list.
	 *
	 * @param executionResponseList the new execution response list
	 */
	public void setExecutionResponseList(
			List<FHIRConfidentialityRuleExecutionResponse> executionResponseList) {
		this.executionResponseList = executionResponseList;
	}

	/**
	 * Adds the execution response.
	 *
	 * @param ruleExecutionResponse the rule execution response
	 */
	public void addExecutionResponse(FHIRConfidentialityRuleExecutionResponse ruleExecutionResponse) {
		getExecutionResponseList().add(ruleExecutionResponse);
	}
        

}
