package DAL;

import DAL.Codpostal;
import DAL.Utilizador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Administrador.class)
public class Administrador_ { 

    public static volatile SingularAttribute<Administrador, Utilizador> idUser;
    public static volatile SingularAttribute<Administrador, Date> dataNasc;
    public static volatile SingularAttribute<Administrador, Integer> telefone;
    public static volatile SingularAttribute<Administrador, String> apelido;
    public static volatile SingularAttribute<Administrador, String> nome;
    public static volatile SingularAttribute<Administrador, Integer> nif;
    public static volatile SingularAttribute<Administrador, Integer> id;
    public static volatile SingularAttribute<Administrador, Short> sexo;
    public static volatile SingularAttribute<Administrador, Codpostal> codPostal;
    public static volatile SingularAttribute<Administrador, String> email;
    public static volatile SingularAttribute<Administrador, String> morada;

}