package DAL;

import DAL.Codpostal;
import DAL.Consulta;
import DAL.Especialidade;
import DAL.Utilizador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Medico.class)
public class Medico_ { 

    public static volatile SingularAttribute<Medico, Integer> telefone;
    public static volatile SingularAttribute<Medico, String> apelido;
    public static volatile SingularAttribute<Medico, String> nome;
    public static volatile SingularAttribute<Medico, Integer> nif;
    public static volatile SingularAttribute<Medico, Utilizador> idUser;
    public static volatile SingularAttribute<Medico, Especialidade> idEsp;
    public static volatile SingularAttribute<Medico, Date> dataNasc;
    public static volatile ListAttribute<Medico, Consulta> consultaList;
    public static volatile SingularAttribute<Medico, Integer> id;
    public static volatile SingularAttribute<Medico, Short> sexo;
    public static volatile SingularAttribute<Medico, Codpostal> codPostal;
    public static volatile SingularAttribute<Medico, String> email;
    public static volatile SingularAttribute<Medico, String> morada;

}