/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author олег
 */
@Entity
@Table(name = "transactions")
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "tr_day")
    private Integer trDay;
    @Column(name = "mcc_code")
    private Integer mccCode;
    @Column(name = "tr_type")
    private Integer trType;
    @Column(name = "tr_datetime")
    @Temporal(TemporalType.TIME)
    private Date trDatetime;
    
    @Column(name = "term_id")
    private String termId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Float amount;

    public Transactions() {
    }

    public Transactions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getTrDay() {
        return trDay;
    }

    public void setTrDay(Integer trDay) {
        this.trDay = trDay;
    }

    public Integer getMccCode() {
        return mccCode;
    }

    public void setMccCode(Integer mccCode) {
        this.mccCode = mccCode;
    }

    public Integer getTrType() {
        return trType;
    }

    public void setTrType(Integer trType) {
        this.trType = trType;
    }

    public Date getTrDatetime() {
        return trDatetime;
    }

    public void setTrDatetime(Date trDatetime) {
        this.trDatetime = trDatetime;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.home.entity.Transactions[ id=" + id + " ]";
    }
    
}
