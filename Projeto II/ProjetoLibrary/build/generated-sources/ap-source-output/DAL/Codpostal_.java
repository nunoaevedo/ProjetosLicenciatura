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
@StaticMetamodel(Codpostal.class)
public class Codpostal_ { 

    public static volatile SingularAttribute<Codpostal, String> cidade;
    public static volatile ListAttribute<Codpostal, Administrador> administradorList;
    public static volatile SingularAttribute<Codpostal, String> codPostal;
    public static volatile ListAttribute<Codpostal, Paciente> pacienteList;
    public static volatile ListAttribute<Codpostal, Rececionista> rececionistaList;
    public static volatile ListAttribute<Codpostal, Medico> medicoList;

}