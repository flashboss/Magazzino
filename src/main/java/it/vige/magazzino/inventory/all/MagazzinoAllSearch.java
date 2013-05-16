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
package it.vige.magazzino.inventory.all;

import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Magazzino;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.solder.logging.TypedCategory;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Named
@Stateful
@SessionScoped
public class MagazzinoAllSearch {

	@Inject
	@TypedCategory(MagazzinoAllSearch.class)
	private MagazzinoLog log;

	@PersistenceContext
	private EntityManager em;

	private List<Magazzino> allJars = new ArrayList<Magazzino>();

	public void find() {
		queryJars();
	}

	public List<Magazzino> getAllJars() {
		return allJars;
	}

	private void queryJars() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Magazzino> cquery = builder.createQuery(Magazzino.class);
		Root<Magazzino> jar = cquery.from(Magazzino.class);
		// QUESTION can like create the pattern for us?
		cquery.select(jar);

		List<Magazzino> results = em.createQuery(cquery).getResultList();

		allJars = results;

		log.searchExecuted(allJars);
	}
}
