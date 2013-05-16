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

import it.vige.magazzino.log.ArticleLog;
import it.vige.magazzino.model.Article;

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
public class ArticleAllSearch {

	@Inject
	@TypedCategory(ArticleAllSearch.class)
	private ArticleLog log;

	@PersistenceContext
	private EntityManager em;

	private List<Article> allArticles = new ArrayList<Article>();

	public void find() {
		queryArticles();
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	private void queryArticles() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Article> cquery = builder.createQuery(Article.class);
		Root<Article> article = cquery.from(Article.class);
		// QUESTION can like create the pattern for us?
		cquery.select(article);

		List<Article> results = em.createQuery(cquery).getResultList();

		allArticles = results;

		log.searchExecuted(allArticles);
	}
}
