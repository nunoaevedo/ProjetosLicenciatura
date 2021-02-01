package DAL;

import DAL.Consulta;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Artigo.class)
public class Artigo_ { 

    public static volatile SingularAttribute<Artigo, BigDecimal> preco;
    public static volatile ListAttribute<Artigo, Consulta> consultaList;
    public static volatile SingularAttribute<Artigo, String> nome;
    public static volatile SingularAttribute<Artigo, Integer> id;
    public static volatile SingularAttribute<Artigo, String> descricao;

}