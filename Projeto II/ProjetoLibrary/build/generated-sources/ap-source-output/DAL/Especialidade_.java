package DAL;

import DAL.Medico;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Especialidade.class)
public class Especialidade_ { 

    public static volatile SingularAttribute<Especialidade, BigDecimal> preco;
    public static volatile SingularAttribute<Especialidade, String> nome;
    public static volatile SingularAttribute<Especialidade, Integer> id;
    public static volatile ListAttribute<Especialidade, Medico> medicoList;

}