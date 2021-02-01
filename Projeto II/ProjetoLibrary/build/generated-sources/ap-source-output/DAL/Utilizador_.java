package DAL;

import DAL.Administrador;
import DAL.Medico;
import DAL.Paciente;
import DAL.Rececionista;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Utilizador.class)
public class Utilizador_ { 

    public static volatile SingularAttribute<Utilizador, String> password;
    public static volatile ListAttribute<Utilizador, Administrador> administradorList;
    public static volatile SingularAttribute<Utilizador, Short> tipoUser;
    public static volatile SingularAttribute<Utilizador, Integer> id;
    public static volatile ListAttribute<Utilizador, Paciente> pacienteList;
    public static volatile ListAttribute<Utilizador, Rececionista> rececionistaList;
    public static volatile SingularAttribute<Utilizador, String> username;
    public static volatile ListAttribute<Utilizador, Medico> medicoList;

}