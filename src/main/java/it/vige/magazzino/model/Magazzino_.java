package it.vige.magazzino.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Magazzino.class)
public abstract class Magazzino_ {

    public static volatile SingularAttribute<Magazzino, String> number;
    public static volatile SingularAttribute<Magazzino, String> date;
    public static volatile SingularAttribute<Magazzino, String> code;
    public static volatile SingularAttribute<Magazzino, String> cause;
    public static volatile SingularAttribute<Magazzino, String> compensation;
    public static volatile SingularAttribute<Magazzino, String> codCustomer;
    public static volatile SingularAttribute<Magazzino, String> ragSoc1;
    public static volatile SingularAttribute<Magazzino, String> ragSoc2;
    public static volatile SingularAttribute<Magazzino, String> numberDoc;
    public static volatile SingularAttribute<Magazzino, String> dateDoc;

}
