/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raque
 */
@Entity
@Table(name = "comanda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comanda.findAll", query = "SELECT c FROM Comanda c")
    , @NamedQuery(name = "Comanda.findByIdOrder", query = "SELECT c FROM Comanda c WHERE c.idOrder = :idOrder")
    , @NamedQuery(name = "Comanda.findByIdUser", query = "SELECT c FROM Comanda c WHERE c.idUser = :idUser")
    , @NamedQuery(name = "Comanda.findByIdProduct", query = "SELECT c FROM Comanda c WHERE c.idProduct = :idProduct")
    , @NamedQuery(name = "Comanda.findByQuantity", query = "SELECT c FROM Comanda c WHERE c.quantity = :quantity")
    , @NamedQuery(name = "Comanda.findByDatatime", query = "SELECT c FROM Comanda c WHERE c.datatime = :datatime")})
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder")
    private Integer idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduct")
    private int idProduct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "datatime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datatime;

    public Comanda() {
    }

    public Comanda(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Comanda(Integer idOrder, int idUser, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Comanda[ idOrder=" + idOrder + " ]";
    }
    
}
