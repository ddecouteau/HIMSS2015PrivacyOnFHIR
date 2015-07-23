/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "ppsactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ppsactions.findAll", query = "SELECT p FROM Ppsactions p"),
    @NamedQuery(name = "Ppsactions.findByIdppsactions", query = "SELECT p FROM Ppsactions p WHERE p.idppsactions = :idppsactions"),
    @NamedQuery(name = "Ppsactions.findByPurposeOfUse", query = "SELECT p FROM Ppsactions p WHERE p.purposeOfUse = :purposeOfUse"),
    @NamedQuery(name = "Ppsactions.findBySensitivity", query = "SELECT p FROM Ppsactions p WHERE p.sensitivity = :sensitivity"),
    @NamedQuery(name = "Ppsactions.findByPatientAuthorization", query = "SELECT p FROM Ppsactions p WHERE p.patientAuthorization = :patientAuthorization"),
    @NamedQuery(name = "Ppsactions.findByProviderClearance", query = "SELECT p FROM Ppsactions p WHERE p.providerClearance = :providerClearance"),
    @NamedQuery(name = "Ppsactions.findByPpsAction", query = "SELECT p FROM Ppsactions p WHERE p.ppsAction = :ppsAction"),
    @NamedQuery(name = "Ppsactions.findByDocumentHandling", query = "SELECT p FROM Ppsactions p WHERE p.documentHandling = :documentHandling"),
    @NamedQuery(name = "Ppsactions.findByRefrainPolicy", query = "SELECT p FROM Ppsactions p WHERE p.refrainPolicy = :refrainPolicy")})
public class Ppsactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idppsactions")
    private Integer idppsactions;
    @Size(max = 45)
    @Column(name = "purpose_of_use")
    private String purposeOfUse;
    @Size(max = 45)
    @Column(name = "sensitivity")
    private String sensitivity;
    @Size(max = 10)
    @Column(name = "patient_authorization")
    private String patientAuthorization;
    @Size(max = 45)
    @Column(name = "provider_clearance")
    private String providerClearance;
    @Size(max = 10)
    @Column(name = "pps_action")
    private String ppsAction;
    @Size(max = 45)
    @Column(name = "document_handling")
    private String documentHandling;
    @Size(max = 45)
    @Column(name = "refrain_policy")
    private String refrainPolicy;

    public Ppsactions() {
    }

    public Ppsactions(Integer idppsactions) {
        this.idppsactions = idppsactions;
    }

    public Integer getIdppsactions() {
        return idppsactions;
    }

    public void setIdppsactions(Integer idppsactions) {
        this.idppsactions = idppsactions;
    }

    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getPatientAuthorization() {
        return patientAuthorization;
    }

    public void setPatientAuthorization(String patientAuthorization) {
        this.patientAuthorization = patientAuthorization;
    }

    public String getProviderClearance() {
        return providerClearance;
    }

    public void setProviderClearance(String providerClearance) {
        this.providerClearance = providerClearance;
    }

    public String getPpsAction() {
        return ppsAction;
    }

    public void setPpsAction(String ppsAction) {
        this.ppsAction = ppsAction;
    }

    public String getDocumentHandling() {
        return documentHandling;
    }

    public void setDocumentHandling(String documentHandling) {
        this.documentHandling = documentHandling;
    }

    public String getRefrainPolicy() {
        return refrainPolicy;
    }

    public void setRefrainPolicy(String refrainPolicy) {
        this.refrainPolicy = refrainPolicy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idppsactions != null ? idppsactions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ppsactions)) {
            return false;
        }
        Ppsactions other = (Ppsactions) object;
        if ((this.idppsactions == null && other.idppsactions != null) || (this.idppsactions != null && !this.idppsactions.equals(other.idppsactions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hl7.fhir.hcsservices.jpa.Ppsactions[ idppsactions=" + idppsactions + " ]";
    }
    
}
