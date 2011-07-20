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

import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.model.Receipt_;

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

/**
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@Named
@Stateful
@SessionScoped
public class ReceiptSearch {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SearchCriteria criteria;

    private boolean nextPageAvailable = false;

    private List<Receipt> receipts = new ArrayList<Receipt>();

    public void find() {
        criteria.firstPage();
        queryReceipts(criteria);
    }

    public void nextPage() {
        criteria.nextPage();
        queryReceipts(criteria);
    }

    public void previousPage() {
        criteria.previousPage();
        queryReceipts(criteria);
    }

    @Produces
    @Named
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public boolean isPreviousPageAvailable() {
        return criteria.getPage() > 0;
    }

    private void queryReceipts(final SearchCriteria criteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Receipt> cquery = builder.createQuery(Receipt.class);
        Root<Receipt> receipt = cquery.from(Receipt.class);
        // QUESTION can like create the pattern for us?
        cquery.select(receipt).where(
                builder.or(builder.like(builder.lower(receipt.get(Receipt_.cause)), criteria.getSearchPattern()),
                        builder.like(builder.lower(receipt.get(Receipt_.date)), criteria.getSearchPattern()),
                        builder.like(builder.lower(receipt.get(Receipt_.description)), criteria.getSearchPattern())));

        List<Receipt> results = em.createQuery(cquery).setMaxResults(criteria.getFetchSize())
                .setFirstResult(criteria.getFetchOffset()).getResultList();

        nextPageAvailable = results.size() > criteria.getPageSize();
        if (nextPageAvailable) {
            // NOTE create new ArrayList since subList creates unserializable list
            receipts = new ArrayList<Receipt>(results.subList(0, criteria.getPageSize()));
        } else {
            receipts = results;
        }
    }
}
