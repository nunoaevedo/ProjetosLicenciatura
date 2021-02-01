/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nuno
 */
@Entity
@Table(name = "FATURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatura.findAll", query = "SELECT f FROM Fatura f")
    , @NamedQuery(name = "Fatura.findById", query = "SELECT f FROM Fatura f WHERE f.id = :id")
    , @NamedQuery(name = "Fatura.findByPrecoBase", query = "SELECT f FROM Fatura f WHERE f.precoBase = :precoBase")
    , @NamedQuery(name = "Fatura.findByIva", query = "SELECT f FROM Fatura f WHERE f.iva = :iva")
    , @NamedQuery(name = "Fatura.findByPrecoTotal", query = "SELECT f FROM Fatura f WHERE f.precoTotal = :precoTotal")})
public class Fatura implements Serializable {

    @JoinColumn(name = "ID_CONS", referencedColumnName = "ID")
    @ManyToOne
    private Consulta idCons;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECO_BASE")
    private BigDecimal precoBase;
    @Column(name = "IVA")
    private BigDecimal iva;
    @Column(name = "PRECO_TOTAL")
    private BigDecimal precoTotal;

    public Fatura() {
    }

    public Fatura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
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
        if (!(object instanceof Fatura)) {
            return false;
        }
        Fatura other = (Fatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Fatura[ id=" + id + " ]";
    }

    public Consulta getIdCons() {
        return idCons;
    }

    public void setIdCons(Consulta idCons) {
        this.idCons = idCons;
    }
    
}
