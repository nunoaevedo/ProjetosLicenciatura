package DAL;

import DAL.Consulta;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-17T16:15:37")
@StaticMetamodel(Fatura.class)
public class Fatura_ { 

    public static volatile SingularAttribute<Fatura, Consulta> idCons;
    public static volatile SingularAttribute<Fatura, BigDecimal> iva;
    public static volatile SingularAttribute<Fatura, BigDecimal> precoBase;
    public static volatile SingularAttribute<Fatura, Integer> id;
    public static volatile SingularAttribute<Fatura, BigDecimal> precoTotal;

}