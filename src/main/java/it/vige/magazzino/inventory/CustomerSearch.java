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

import it.vige.magazzino.log.CustomerLog;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Customer_;

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
public class CustomerSearch {

	@Inject
	@TypedCategory(CustomerSearch.class)
	private CustomerLog log;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private SearchCriteria criteria;

	private boolean nextPageAvailable = false;

	private List<Customer> customers = new ArrayList<Customer>();

	public void find() {
		criteria.firstPage();
		queryCustomers(criteria);
	}

	public void nextPage() {
		criteria.nextPage();
		queryCustomers(criteria);
	}

	public void previousPage() {
		criteria.previousPage();
		queryCustomers(criteria);
	}

	public void currentPage() {
		queryCustomers(criteria);
	}

	@Produces
	@Named
	public List<Customer> getCustomers() {
		return customers;
	}

	public boolean isNextPageAvailable() {
		return nextPageAvailable;
	}

	public boolean isPreviousPageAvailable() {
		return criteria.getPage() > 0;
	}

	private void queryCustomers(final SearchCriteria criteria) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cquery = builder.createQuery(Customer.class);
		Root<Customer> customer = cquery.from(Customer.class);
		// QUESTION can like create the pattern for us?
		cquery.select(customer).where(
				builder.or(builder.like(
						builder.lower(customer.get(Customer_.name)),
						criteria.getSearchPattern()), builder.like(
						builder.lower(customer.get(Customer_.ragSocial)),
						criteria.getSearchPattern()), builder.like(
						builder.lower(customer.get(Customer_.iva)),
						criteria.getSearchPattern())));

		List<Customer> results = em.createQuery(cquery)
				.setMaxResults(criteria.getFetchSize())
				.setFirstResult(criteria.getFetchOffset()).getResultList();

		nextPageAvailable = results.size() > criteria.getPageSize();
		if (nextPageAvailable) {
			// NOTE create new ArrayList since subList creates unserializable
			// list
			customers = new ArrayList<Customer>(results.subList(0,
					criteria.getPageSize()));
		} else {
			customers = results;
		}
		log.searchExecuted(customers);
	}
}
