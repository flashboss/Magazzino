package it.vige.magazzino.test;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.shrinkwrap.descriptor.example.User;

public class AuthenticatedUserProducer {
    @PersistenceContext
    EntityManager em;

    @Produces
    public User getRegisteredUser() {
        return em.find(User.class, "ike");
    }
}
