/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "hcslogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hcslogs.findAll", query = "SELECT h FROM Hcslogs h ORDER BY h.msgdate DESC"),
    @NamedQuery(name = "Hcslogs.findByIdhcslogs", query = "SELECT h FROM Hcslogs h WHERE h.idhcslogs = :idhcslogs"),
    @NamedQuery(name = "Hcslogs.findByMsgdate", query = "SELECT h FROM Hcslogs h WHERE h.msgdate = :msgdate"),
    @NamedQuery(name = "Hcslogs.findByMsgid", query = "SELECT h FROM Hcslogs h WHERE h.msgid = :msgid"),
    @NamedQuery(name = "Hcslogs.findByPurposeofuse", query = "SELECT h FROM Hcslogs h WHERE h.purposeofuse = :purposeofuse")})
public class Hcslogs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhcslogs")
    private Integer idhcslogs;
    @Column(name = "msgdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgdate;
    @Size(max = 250)
    @Column(name = "msgid")
    private String msgid;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "fhirbundle")
    private String fhirbundle;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "slsexecrules")
    private String slsexecrules;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "slsoutput")
    private String slsoutput;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "obligations")
    private String obligations;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ppsexecrules")
    private String ppsexecrules;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ppsoutput")
    private String ppsoutput;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "labeledresource")
    private String labeledresource;
    @Size(max = 45)
    @Column(name = "purposeofuse")
    private String purposeofuse;

    public Hcslogs() {
    }

    public Hcslogs(Integer idhcslogs) {
        this.idhcslogs = idhcslogs;
    }

    public Integer getIdhcslogs() {
        return idhcslogs;
    }

    public void setIdhcslogs(Integer idhcslogs) {
        this.idhcslogs = idhcslogs;
    }

    public Date getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(Date msgdate) {
        this.msgdate = msgdate;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getFhirbundle() {
        return fhirbundle;
    }

    public void setFhirbundle(String fhirbundle) {
        this.fhirbundle = fhirbundle;
    }

    public String getSlsexecrules() {
        return slsexecrules;
    }

    public void setSlsexecrules(String slsexecrules) {
        this.slsexecrules = slsexecrules;
    }

    public String getSlsoutput() {
        return slsoutput;
    }

    public void setSlsoutput(String slsoutput) {
        this.slsoutput = slsoutput;
    }

    public String getObligations() {
        return obligations;
    }

    public void setObligations(String obligations) {
        this.obligations = obligations;
    }

    public String getPpsexecrules() {
        return ppsexecrules;
    }

    public void setPpsexecrules(String ppsexecrules) {
        this.ppsexecrules = ppsexecrules;
    }

    public String getPpsoutput() {
        return ppsoutput;
    }

    public void setPpsoutput(String ppsoutput) {
        this.ppsoutput = ppsoutput;
    }

    public String getLabeledresource() {
        return labeledresource;
    }

    public void setLabeledresource(String labeledresource) {
        this.labeledresource = labeledresource;
    }

    public String getPurposeofuse() {
        return purposeofuse;
    }

    public void setPurposeofuse(String purposeofuse) {
        this.purposeofuse = purposeofuse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhcslogs != null ? idhcslogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hcslogs)) {
            return false;
        }
        Hcslogs other = (Hcslogs) object;
        if ((this.idhcslogs == null && other.idhcslogs != null) || (this.idhcslogs != null && !this.idhcslogs.equals(other.idhcslogs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hl7.fhir.hcsservices.jpa.Hcslogs[ idhcslogs=" + idhcslogs + " ]";
    }
    
}
