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
import javax.persistence.Lob;
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
@Table(name = "clinicallabels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clinicallabels.findAll", query = "SELECT c FROM Clinicallabels c"),
    @NamedQuery(name = "Clinicallabels.findByIdclinicallabels", query = "SELECT c FROM Clinicallabels c WHERE c.idclinicallabels = :idclinicallabels"),
    @NamedQuery(name = "Clinicallabels.findByFhirpath", query = "SELECT c FROM Clinicallabels c WHERE c.fhirpath = :fhirpath"),
    @NamedQuery(name = "Clinicallabels.findByFhircode", query = "SELECT c FROM Clinicallabels c WHERE c.fhircode = :fhircode"),
    @NamedQuery(name = "Clinicallabels.findByConfidentialitycode", query = "SELECT c FROM Clinicallabels c WHERE c.confidentialitycode = :confidentialitycode"),
    @NamedQuery(name = "Clinicallabels.findBySensitivitycode", query = "SELECT c FROM Clinicallabels c WHERE c.sensitivitycode = :sensitivitycode"),
    @NamedQuery(name = "Clinicallabels.findByConfidentialityLabel", query = "SELECT c FROM Clinicallabels c WHERE c.confidentialityLabel = :confidentialityLabel")})
public class Clinicallabels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idclinicallabels")
    private Integer idclinicallabels;
    @Size(max = 200)
    @Column(name = "fhirpath")
    private String fhirpath;
    @Size(max = 45)
    @Column(name = "fhircode")
    private String fhircode;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "fhirdisplayname")
    private String fhirdisplayname;
    @Size(max = 45)
    @Column(name = "confidentialitycode")
    private String confidentialitycode;
    @Size(max = 45)
    @Column(name = "sensitivitycode")
    private String sensitivitycode;
    @Size(max = 45)
    @Column(name = "confidentialityLabel")
    private String confidentialityLabel;

    public Clinicallabels() {
    }

    public Clinicallabels(Integer idclinicallabels) {
        this.idclinicallabels = idclinicallabels;
    }

    public Integer getIdclinicallabels() {
        return idclinicallabels;
    }

    public void setIdclinicallabels(Integer idclinicallabels) {
        this.idclinicallabels = idclinicallabels;
    }

    public String getFhirpath() {
        return fhirpath;
    }

    public void setFhirpath(String fhirpath) {
        this.fhirpath = fhirpath;
    }

    public String getFhircode() {
        return fhircode;
    }

    public void setFhircode(String fhircode) {
        this.fhircode = fhircode;
    }

    public String getFhirdisplayname() {
        return fhirdisplayname;
    }

    public void setFhirdisplayname(String fhirdisplayname) {
        this.fhirdisplayname = fhirdisplayname;
    }

    public String getConfidentialitycode() {
        return confidentialitycode;
    }

    public void setConfidentialitycode(String confidentialitycode) {
        this.confidentialitycode = confidentialitycode;
    }

    public String getSensitivitycode() {
        return sensitivitycode;
    }

    public void setSensitivitycode(String sensitivitycode) {
        this.sensitivitycode = sensitivitycode;
    }

    public String getConfidentialityLabel() {
        return confidentialityLabel;
    }

    public void setConfidentialityLabel(String confidentialityLabel) {
        this.confidentialityLabel = confidentialityLabel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclinicallabels != null ? idclinicallabels.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinicallabels)) {
            return false;
        }
        Clinicallabels other = (Clinicallabels) object;
        if ((this.idclinicallabels == null && other.idclinicallabels != null) || (this.idclinicallabels != null && !this.idclinicallabels.equals(other.idclinicallabels))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hl7.fhir.hcsservices.jpa.Clinicallabels[ idclinicallabels=" + idclinicallabels + " ]";
    }
    
}
