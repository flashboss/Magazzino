package it.vige.magazzino.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Article.class)
public abstract class Article_ {

    public static volatile SingularAttribute<Article, String> code;
    public static volatile SingularAttribute<Article, String> barCode;
    public static volatile SingularAttribute<Article, String> description;
    public static volatile SingularAttribute<Article, String> um;
    public static volatile SingularAttribute<Article, String> mis;
    public static volatile SingularAttribute<Article, String> catMerc;
    public static volatile SingularAttribute<Article, String> imponible;
    public static volatile SingularAttribute<Article, String> prize;
    public static volatile SingularAttribute<Article, String> cost;
    public static volatile SingularAttribute<Article, String> provider;
    public static volatile SingularAttribute<Article, String> rate;
    public static volatile SingularAttribute<Article, String> ca;
    public static volatile SingularAttribute<Article, String> sc1;
    public static volatile SingularAttribute<Article, String> sc2;
    public static volatile SingularAttribute<Article, String> sc3;
    public static volatile SingularAttribute<Article, String> qtmin;
    public static volatile SingularAttribute<Article, String> qtmax;
    public static volatile SingularAttribute<Article, String> pack;
    public static volatile SingularAttribute<Article, String> health;
    public static volatile SingularAttribute<Article, String> volume;
    public static volatile SingularAttribute<Article, String> making;
    public static volatile SingularAttribute<Article, String> notes;

}
