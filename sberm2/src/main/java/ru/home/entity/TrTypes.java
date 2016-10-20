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
@Table(name = "tr_types")
@NamedQueries({
    @NamedQuery(name = "TrTypes.findAll", query = "SELECT t FROM TrTypes t")})
public class TrTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tr_type")
    private Integer trType;

    @Column(name = "tr_description")
    private String trDescription;

    public TrTypes() {
    }

    public TrTypes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrType() {
        return trType;
    }

    public void setTrType(Integer trType) {
        this.trType = trType;
    }

    public String getTrDescription() {
        return trDescription;
    }

    public void setTrDescription(String trDescription) {
        this.trDescription = trDescription;
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
        if (!(object instanceof TrTypes)) {
            return false;
        }
        TrTypes other = (TrTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.home.entity.TrTypes[ id=" + id + " ]";
    }
    
}
