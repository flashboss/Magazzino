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

import it.vige.magazzino.log.ArticleLog;
import it.vige.magazzino.model.Article;
import it.vige.magazzino.model.Article_;

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
public class ArticleSearch {
    @Inject
    @TypedCategory(ArticleSearch.class)
    private ArticleLog log;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SearchCriteria criteria;

    private boolean nextPageAvailable = false;

    private List<Article> articles = new ArrayList<Article>();

    public void find() {
        criteria.firstPage();
        queryArticles(criteria);
    }

    public void nextPage() {
        criteria.nextPage();
        queryArticles(criteria);
    }

    public void previousPage() {
        criteria.previousPage();
        queryArticles(criteria);
    }

    public void currentPage() {
        queryArticles(criteria);
    }

    @Produces
    @Named
    public List<Article> getArticles() {
        return articles;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public boolean isPreviousPageAvailable() {
        return criteria.getPage() > 0;
    }

    private void queryArticles(final SearchCriteria criteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Article> cquery = builder.createQuery(Article.class);
        Root<Article> article = cquery.from(Article.class);
        // QUESTION can like create the pattern for us?
        cquery.select(article).where(
                builder.or(builder.like(builder.lower(article.get(Article_.barCode)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.ca)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.catMerc)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.cost)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.description)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.health)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.imponible)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.making)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.mis)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.notes)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.pack)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.prize)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.provider)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.qtmax)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.qtmin)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.rate)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.sc1)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.sc2)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.sc3)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.um)), criteria.getSearchPattern()),
                        builder.like(builder.lower(article.get(Article_.volume)), criteria.getSearchPattern())));

        List<Article> results = em.createQuery(cquery).setMaxResults(criteria.getFetchSize())
                .setFirstResult(criteria.getFetchOffset()).getResultList();

        nextPageAvailable = results.size() > criteria.getPageSize();
        if (nextPageAvailable) {
            // NOTE create new ArrayList since subList creates unserializable list
            articles = new ArrayList<Article>(results.subList(0, criteria.getPageSize()));
        } else {
            articles = results;
        }
        log.searchExecuted(articles);
    }
}
