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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
    , @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id")
    , @NamedQuery(name = "Paciente.findByNome", query = "SELECT p FROM Paciente p WHERE p.nome = :nome")
    , @NamedQuery(name = "Paciente.findByApelido", query = "SELECT p FROM Paciente p WHERE p.apelido = :apelido")
    , @NamedQuery(name = "Paciente.findByDataNasc", query = "SELECT p FROM Paciente p WHERE p.dataNasc = :dataNasc")
    , @NamedQuery(name = "Paciente.findByNif", query = "SELECT p FROM Paciente p WHERE p.nif = :nif")
    , @NamedQuery(name = "Paciente.findByNiss", query = "SELECT p FROM Paciente p WHERE p.niss = :niss")
    , @NamedQuery(name = "Paciente.findByMorada", query = "SELECT p FROM Paciente p WHERE p.morada = :morada")
    , @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo")
    , @NamedQuery(name = "Paciente.findByEmail", query = "SELECT p FROM Paciente p WHERE p.email = :email")
    , @NamedQuery(name = "Paciente.findByTelefone", query = "SELECT p FROM Paciente p WHERE p.telefone = :telefone")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "APELIDO")
    private String apelido;
    @Column(name = "DATA_NASC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNasc;
    @Column(name = "NIF")
    private Integer nif;
    @Column(name = "NISS")
    private Long niss;
    @Column(name = "MORADA")
    private String morada;
    @Column(name = "SEXO")
    private Short sexo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONE")
    private Integer telefone;
    @OneToMany(mappedBy = "idPac")
    private List<Consulta> consultaList;
    @JoinColumn(name = "COD_POSTAL", referencedColumnName = "COD_POSTAL")
    @ManyToOne
    private Codpostal codPostal;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    private Utilizador idUser;

    public Paciente() {
    }

    public Paciente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Long getNiss() {
        return niss;
    }

    public void setNiss(Long niss) {
        this.niss = niss;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Short getSexo() {
        return sexo;
    }

    public void setSexo(Short sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    @XmlTransient
    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    public Codpostal getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Codpostal codPostal) {
        this.codPostal = codPostal;
    }

    public Utilizador getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilizador idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Paciente[ id=" + id + " ]";
    }
    
}
