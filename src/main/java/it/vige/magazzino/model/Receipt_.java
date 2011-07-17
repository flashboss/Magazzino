package it.vige.magazzino.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Receipt.class)
public abstract class Receipt_ {

    public static volatile SingularAttribute<Receipt, String> number;
    public static volatile SingularAttribute<Receipt, String> date;
    public static volatile SingularAttribute<Receipt, String> cause;
    public static volatile SingularAttribute<Receipt, String> description;

}
