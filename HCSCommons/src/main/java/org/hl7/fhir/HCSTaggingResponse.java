/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Socraticgrid Staff
 */
public class HCSTaggingResponse {
    private List<HCSCategory> processAdds = new ArrayList();
    private List<HCSCategory> processDeletes = new ArrayList();
    
    public HCSTaggingResponse() {
        
    }

    /**
     * @return the processAdds
     */
    public List<HCSCategory> getProcessAdds() {
        return processAdds;
    }

    /**
     * @param processAdds the processAdds to set
     */
    public void setProcessAdds(List<HCSCategory> processAdds) {
        this.processAdds = processAdds;
    }

    /**
     * @return the processDeletes
     */
    public List<HCSCategory> getProcessDeletes() {
        return processDeletes;
    }

    /**
     * @param processDeletes the processDeletes to set
     */
    public void setProcessDeletes(List<HCSCategory> processDeletes) {
        this.processDeletes = processDeletes;
    }
    
}
