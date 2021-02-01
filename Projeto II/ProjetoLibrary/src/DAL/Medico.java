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
@Table(name = "MEDICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m")
    , @NamedQuery(name = "Medico.findById", query = "SELECT m FROM Medico m WHERE m.id = :id")
    , @NamedQuery(name = "Medico.findByNome", query = "SELECT m FROM Medico m WHERE m.nome = :nome")
    , @NamedQuery(name = "Medico.findByApelido", query = "SELECT m FROM Medico m WHERE m.apelido = :apelido")
    , @NamedQuery(name = "Medico.findByDataNasc", query = "SELECT m FROM Medico m WHERE m.dataNasc = :dataNasc")
    , @NamedQuery(name = "Medico.findByNif", query = "SELECT m FROM Medico m WHERE m.nif = :nif")
    , @NamedQuery(name = "Medico.findByMorada", query = "SELECT m FROM Medico m WHERE m.morada = :morada")
    , @NamedQuery(name = "Medico.findBySexo", query = "SELECT m FROM Medico m WHERE m.sexo = :sexo")
    , @NamedQuery(name = "Medico.findByEmail", query = "SELECT m FROM Medico m WHERE m.email = :email")
    , @NamedQuery(name = "Medico.findByTelefone", query = "SELECT m FROM Medico m WHERE m.telefone = :telefone")})
public class Medico implements Serializable {

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
    @Column(name = "MORADA")
    private String morada;
    @Column(name = "SEXO")
    private Short sexo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONE")
    private Integer telefone;
    @JoinColumn(name = "COD_POSTAL", referencedColumnName = "COD_POSTAL")
    @ManyToOne
    private Codpostal codPostal;
    @JoinColumn(name = "ID_ESP", referencedColumnName = "ID")
    @ManyToOne
    private Especialidade idEsp;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    private Utilizador idUser;
    @OneToMany(mappedBy = "idMed")
    private List<Consulta> consultaList;

    public Medico() {
    }

    public Medico(Integer id) {
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

    public Codpostal getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Codpostal codPostal) {
        this.codPostal = codPostal;
    }

    public Especialidade getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(Especialidade idEsp) {
        this.idEsp = idEsp;
    }

    public Utilizador getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilizador idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
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
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Medico[ id=" + id + " ]";
    }
    
}
