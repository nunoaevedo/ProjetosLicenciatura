/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nuno
 */
@Entity
@Table(name = "ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findById", query = "SELECT a FROM Administrador a WHERE a.id = :id")
    , @NamedQuery(name = "Administrador.findByNome", query = "SELECT a FROM Administrador a WHERE a.nome = :nome")
    , @NamedQuery(name = "Administrador.findByApelido", query = "SELECT a FROM Administrador a WHERE a.apelido = :apelido")
    , @NamedQuery(name = "Administrador.findByDataNasc", query = "SELECT a FROM Administrador a WHERE a.dataNasc = :dataNasc")
    , @NamedQuery(name = "Administrador.findByNif", query = "SELECT a FROM Administrador a WHERE a.nif = :nif")
    , @NamedQuery(name = "Administrador.findByMorada", query = "SELECT a FROM Administrador a WHERE a.morada = :morada")
    , @NamedQuery(name = "Administrador.findBySexo", query = "SELECT a FROM Administrador a WHERE a.sexo = :sexo")
    , @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
    , @NamedQuery(name = "Administrador.findByTelefone", query = "SELECT a FROM Administrador a WHERE a.telefone = :telefone")})
public class Administrador implements Serializable {

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
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    private Utilizador idUser;

    public Administrador() {
    }

    public Administrador(Integer id) {
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
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.Administrador[ id=" + id + " ]";
    }
    
}
