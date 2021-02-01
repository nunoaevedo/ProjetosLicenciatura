/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nuno
 */
@Entity
@Table(name = "CODPOSTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codpostal.findAll", query = "SELECT c FROM Codpostal c")
    , @NamedQuery(name = "Codpostal.findByCodPostal", query = "SELECT c FROM Codpostal c WHERE c.codPostal = :codPostal")
    , @NamedQuery(name = "Codpostal.findByCidade", query = "SELECT c FROM Codpostal c WHERE c.cidade = :cidade")})
public class Codpostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_POSTAL")
    private String codPostal;
    @Column(name = "CIDADE")
    private String cidade;
    @OneToMany(mappedBy = "codPostal")
    private List<Administrador> administradorList;
    @OneToMany(mappedBy = "codPostal")
    private List<Rececionista> rececionistaList;
    @OneToMany(mappedBy = "codPostal")
    private List<Medico> medicoList;
    @OneToMany(mappedBy = "codPostal")
    private List<Paciente> pacienteList;

    public Codpostal() {
    }

    public Codpostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @XmlTransient
    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

    @XmlTransient
    public List<Rececionista> getRececionistaList() {
        return rececionistaList;
    }

    public void setRececionistaList(List<Rececionista> rececionistaList) {
        this.rececionistaList = rececionistaList;
    }

    @XmlTransient
    public List<Medico> getMedicoList() {
        return medicoList;
    }

    public void setMedicoList(List<Medico> medicoList) {
        this.medicoList = medicoList;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPostal != null ? codPostal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codpostal)) {
            return false;
        }
        Codpostal other = (Codpostal) object;
        if ((this.codPostal == null && other.codPostal != null) || (this.codPostal != null && !this.codPostal.equals(other.codPostal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Codpostal[ codPostal=" + codPostal + " ]";
    }
    
}
