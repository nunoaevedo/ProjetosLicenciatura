package DAL;

import DAL.Artigo;
import DAL.Fatura;
import DAL.Medico;
import DAL.Paciente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Consulta.class)
public class Consulta_ { 

    public static volatile SingularAttribute<Consulta, Fatura> fatura;
    public static volatile SingularAttribute<Consulta, Paciente> idPac;
    public static volatile SingularAttribute<Consulta, Date> data;
    public static volatile SingularAttribute<Consulta, Short> realizado;
    public static volatile SingularAttribute<Consulta, Medico> idMed;
    public static volatile SingularAttribute<Consulta, Integer> id;
    public static volatile ListAttribute<Consulta, Artigo> artigoList;
    public static volatile SingularAttribute<Consulta, String> descricao;

}