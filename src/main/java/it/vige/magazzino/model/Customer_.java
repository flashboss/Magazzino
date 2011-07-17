package it.vige.magazzino.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public abstract class Customer_ {

    public static volatile SingularAttribute<Customer, String> code;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, String> ragSocial;

}
