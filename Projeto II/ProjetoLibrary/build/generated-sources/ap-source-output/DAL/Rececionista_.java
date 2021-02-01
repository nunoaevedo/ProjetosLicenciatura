package DAL;

import DAL.Codpostal;
import DAL.Utilizador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Rececionista.class)
public class Rececionista_ { 

    public static volatile SingularAttribute<Rececionista, Utilizador> idUser;
    public static volatile SingularAttribute<Rececionista, Date> dataNasc;
    public static volatile SingularAttribute<Rececionista, Integer> telefone;
    public static volatile SingularAttribute<Rececionista, String> apelido;
    public static volatile SingularAttribute<Rececionista, String> nome;
    public static volatile SingularAttribute<Rececionista, Integer> nif;
    public static volatile SingularAttribute<Rececionista, Integer> id;
    public static volatile SingularAttribute<Rececionista, Short> sexo;
    public static volatile SingularAttribute<Rececionista, Codpostal> codPostal;
    public static volatile SingularAttribute<Rececionista, String> email;
    public static volatile SingularAttribute<Rececionista, String> morada;

}