/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author олег
 */
@Entity
@Table(name = "sredn_neopr")
@NamedQueries({
    @NamedQuery(name = "SrednNeopr.findAll", query = "SELECT s FROM SrednNeopr s")})
public class SrednNeopr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mcc_code")
    private Integer mccCode;
    @Column(name = "sredn")
    private Integer sredn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "koef")
    private Float koef;

    public SrednNeopr() {
    }

    public SrednNeopr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMccCode() {
        return mccCode;
    }

    public void setMccCode(Integer mccCode) {
        this.mccCode = mccCode;
    }

    public Integer getSredn() {
        return sredn;
    }

    public void setSredn(Integer sredn) {
        this.sredn = sredn;
    }

    public Float getKoef() {
        return koef;
    }

    public void setKoef(Float koef) {
        this.koef = koef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrednNeopr)) {
            return false;
        }
        SrednNeopr other = (SrednNeopr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.home.entity.SrednNeopr[ id=" + id + " ]";
    }
    
}
