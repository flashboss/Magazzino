/*
 * Vige, Home of Professional Open Source
 * Copyright 2010, Vige, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.vige.magazzino.inventory;

import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Magazzino_;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.seam.solder.logging.TypedCategory;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Named
@Stateful
@SessionScoped
public class MagazzinoSearch {
	
    @Inject
    @TypedCategory(MagazzinoSearch.class)
    private MagazzinoLog log;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SearchCriteria criteria;

    private boolean nextPageAvailable = false;

    private List<Magazzino> jars = new ArrayList<Magazzino>();

    public void find() {
        criteria.firstPage();
        queryJars(criteria);
    }

    public void nextPage() {
        criteria.nextPage();
        queryJars(criteria);
    }

    public void previousPage() {
        criteria.previousPage();
        queryJars(criteria);
    }

    public void currentPage() {
        queryJars(criteria);
    }

    @Produces
    @Named
    public List<Magazzino> getJars() {
        return jars;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public boolean isPreviousPageAvailable() {
        return criteria.getPage() > 0;
    }

    private void queryJars(final SearchCriteria criteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Magazzino> cquery = builder.createQuery(Magazzino.class);
        Root<Magazzino> jar = cquery.from(Magazzino.class);
        // QUESTION can like create the pattern for us?
        cquery.select(jar).where(
                builder.or(builder.like(builder.lower(jar.get(Magazzino_.cause)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.date)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.compensation)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.dateDoc)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.numberDoc)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.ragSoc1)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.ragSoc2)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.iva)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.capSoc)), criteria.getSearchPattern()),
                        builder.like(builder.lower(jar.get(Magazzino_.reaPI)), criteria.getSearchPattern())));

        List<Magazzino> results = em.createQuery(cquery).setMaxResults(criteria.getFetchSize())
                .setFirstResult(criteria.getFetchOffset()).getResultList();

        nextPageAvailable = results.size() > criteria.getPageSize();
        if (nextPageAvailable) {
            // NOTE create new ArrayList since subList creates unserializable list
            jars = new ArrayList<Magazzino>(results.subList(0, criteria.getPageSize()));
        } else {
            jars = results;
        }
        log.searchExecuted(jars);
    }
}
