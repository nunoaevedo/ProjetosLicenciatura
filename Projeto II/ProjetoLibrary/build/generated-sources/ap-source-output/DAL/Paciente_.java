package DAL;

import DAL.Codpostal;
import DAL.Consulta;
import DAL.Utilizador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Integer> telefone;
    public static volatile SingularAttribute<Paciente, String> apelido;
    public static volatile SingularAttribute<Paciente, Long> niss;
    public static volatile SingularAttribute<Paciente, String> nome;
    public static volatile SingularAttribute<Paciente, Integer> nif;
    public static volatile SingularAttribute<Paciente, Utilizador> idUser;
    public static volatile SingularAttribute<Paciente, Date> dataNasc;
    public static volatile ListAttribute<Paciente, Consulta> consultaList;
    public static volatile SingularAttribute<Paciente, Integer> id;
    public static volatile SingularAttribute<Paciente, Short> sexo;
    public static volatile SingularAttribute<Paciente, Codpostal> codPostal;
    public static volatile SingularAttribute<Paciente, String> email;
    public static volatile SingularAttribute<Paciente, String> morada;

}