/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nuno
 */
@Entity
@Table(name = "CONSULTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")
    , @NamedQuery(name = "Consulta.findById", query = "SELECT c FROM Consulta c WHERE c.id = :id")
    , @NamedQuery(name = "Consulta.findByDescricao", query = "SELECT c FROM Consulta c WHERE c.descricao = :descricao")
    , @NamedQuery(name = "Consulta.findByData", query = "SELECT c FROM Consulta c WHERE c.data = :data")
    , @NamedQuery(name = "Consulta.findByRealizado", query = "SELECT c FROM Consulta c WHERE c.realizado = :realizado")})
public class Consulta implements Serializable {

    @OneToOne(mappedBy = "idCons")
    private Fatura fatura;

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "REALIZADO")
    private Short realizado;
    @JoinTable(name = "LINHAARTIGO", joinColumns = {
        @JoinColumn(name = "ID_CONS", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ART", referencedColumnName = "ID")})
    @ManyToMany
    private List<Artigo> artigoList;
    @JoinColumn(name = "ID_MED", referencedColumnName = "ID")
    @ManyToOne
    private Medico idMed;
    @JoinColumn(name = "ID_PAC", referencedColumnName = "ID")
    @ManyToOne
    private Paciente idPac;

    public Consulta() {
    }

    public Consulta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Short getRealizado() {
        return realizado;
    }

    public void setRealizado(Short realizado) {
        this.realizado = realizado;
    }

    @XmlTransient
    public List<Artigo> getArtigoList() {
        return artigoList;
    }

    public void setArtigoList(List<Artigo> artigoList) {
        this.artigoList = artigoList;
    }

    public Medico getIdMed() {
        return idMed;
    }

    public void setIdMed(Medico idMed) {
        this.idMed = idMed;
    }

    public Paciente getIdPac() {
        return idPac;
    }

    public void setIdPac(Paciente idPac) {
        this.idPac = idPac;
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
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Consulta[ id=" + id + " ]";
    }

    @XmlTransient
    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura= fatura;
    }
    
}
