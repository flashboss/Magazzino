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
package it.vige.magazzino.test.persistence.as6;

import static it.vige.magazzino.test.persistence.Dependencies.FACES;
import static it.vige.magazzino.test.persistence.Dependencies.SOLDER;
import static it.vige.magazzino.test.mock.ArticleMock.article0;
import static it.vige.magazzino.test.mock.ArticleMock.article1;
import static it.vige.magazzino.test.mock.ArticleMock.article10;
import static it.vige.magazzino.test.mock.ArticleMock.article11;
import static it.vige.magazzino.test.mock.ArticleMock.article12;
import static it.vige.magazzino.test.mock.ArticleMock.article13;
import static it.vige.magazzino.test.mock.ArticleMock.article14;
import static it.vige.magazzino.test.mock.ArticleMock.article15;
import static it.vige.magazzino.test.mock.ArticleMock.article2;
import static it.vige.magazzino.test.mock.ArticleMock.article3;
import static it.vige.magazzino.test.mock.ArticleMock.article4;
import static it.vige.magazzino.test.mock.ArticleMock.article5;
import static it.vige.magazzino.test.mock.ArticleMock.article6;
import static it.vige.magazzino.test.mock.ArticleMock.article7;
import static it.vige.magazzino.test.mock.ArticleMock.article8;
import static it.vige.magazzino.test.mock.ArticleMock.article9;
import static it.vige.magazzino.test.mock.ArticleMock.articles;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.vige.magazzino.ArticleRegister;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.ArticleSearch;
import it.vige.magazzino.inventory.SearchCriteria;
import it.vige.magazzino.inventory.all.ArticleAllSearch;
import it.vige.magazzino.log.ArticleLog;
import it.vige.magazzino.model.Article;
import it.vige.magazzino.model.Article_;
import it.vige.magazzino.remove.ArticleDeleter;
import it.vige.magazzino.selection.ArticleSelection;
import it.vige.magazzino.test.mock.ArticleMock;
import it.vige.magazzino.test.operation.ArticleOperation;
import it.vige.magazzino.update.ArticleUpdater;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.international.status.Message;
import org.jboss.seam.international.status.Messages;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.Container;
import org.jboss.weld.context.http.HttpConversationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class ArticleAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(ArticleAgentTest.class)
				.addClasses(ArticleMock.class, ArticleOperation.class,
						Article.class)
				.addClasses(ArticleRegister.class, ArticleUpdater.class,
						ArticleDeleter.class, ArticleSelection.class)
				.addClasses(ArticleLog.class, Article_.class)
				.addClasses(ArticleSearch.class, ArticleAllSearch.class,
						SearchCriteria.class)
				.addClasses(DefaultBundleKey.class)
				.addAsLibraries(SOLDER)
				.addAsLibraries(FACES)
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	ArticleRegister articleRegister;

	@EJB
	ArticleUpdater articleUpdater;

	@EJB
	ArticleDeleter articleDeleter;

	@Inject
	ArticleSelection articleSelection;

	@EJB
	ArticleSearch articleSearch;

	@Inject
	SearchCriteria criteria;

	@Inject
	Messages messages;

	@Inject
	UserTransaction utx;

	@Inject
	BeanManager beanManager;

	@PersistenceContext
	EntityManager em;

	@Before
	public void createArticle() throws Exception {
		if (em.find(Article.class, article0.getCodeArticle()) == null) {
			utx.begin();
			em.joinTransaction();

			em.persist(article0);
			em.persist(article1);
			em.persist(article2);
			em.persist(article3);
			em.persist(article4);
			em.persist(article5);
			em.persist(article6);
			em.persist(article7);
			em.persist(article8);
			em.persist(article9);
			em.persist(article10);
			em.persist(article11);
			em.persist(article12);
			em.persist(article13);
			em.persist(article14);
			em.persist(article15);
			utx.commit();
		}
	}

	@Test
	public void searchArticle() throws Exception {
		Assert.assertEquals(16, em.createQuery("select b from Article b")
				.getResultList().size());
	}

	@Test
	public void testSearch() {
		criteria.setQuery("provider");
		articleSearch.find();
		assertFalse(articleSearch.getArticles().size() == 0);
		assertEquals(5, articleSearch.getArticles().size());

		criteria.setQuery("nonExistingArticle");
		articleSearch.find();
		assertTrue(articleSearch.getArticles().size() == 0);
		assertEquals(0, articleSearch.getArticles().size());
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		criteria.setQuery("provider");

		for (int pageSize : values) {
			criteria.setPageSize(pageSize);
			articleSearch.find();
			if (articles.length > pageSize)
				assertEquals(articleSearch.getArticles().size(), pageSize);
			else
				assertEquals(articleSearch.getArticles().size(),
						articles.length);
		}
	}

	@Test
	public void testInsertDeleteNewArticle() {
		String catMerc = "newName";
		String rate = "012345678";
		String pack = "1111111111";
		Article article = articleRegister.getNewArticle();
		article.setCodeArticle("99999999");
		article.setCatMerc(catMerc);
		article.setRate(rate);
		article.setPack(pack);
		articleRegister.register();
		String message = messages.getAll().iterator().next().getText();
		assertTrue(message, message.contains(article.getCodeArticle()));
		// cancel article
		article.setCodeArticle("");
		try {
			articleRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		article.setRate("");
		article.setCodeArticle("99999991");
		try {
			articleRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		criteria.setQuery(catMerc);
		articleSearch.find();
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		articleSelection.selectArticle(articleSearch.getArticles().get(0));
		Article selectedArticle = articleSelection.getSelectedArticle();
		articleDeleter.delete(selectedArticle);
		boolean found = false;
		for (Message messageFromList : messages.getAll()) {
			if (messageFromList.getText().contains("99999999")
					&& messageFromList.getText().contains("deleted"))
				found = true;
		}

		assertTrue(message, found);
	}

	@Test
	public void testMultiSearchingUpdate() {
		Article[] articles = new Article[] { article0, article1, article2,
				article4 };

		// make 4 articles
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		for (Article article : articles) {
			searchUpdateArticle(article, "7777777");
		}
		criteria.setQuery("7777777");
		articleSearch.find();
		assertEquals(articleSearch.getArticles().size(), 0);
	}

	protected void searchUpdateArticle(Article article, String newPack) {

		criteria.setQuery(article.getDescription());
		articleSearch.find();
		articleSelection.selectArticle(articleSearch.getArticles().get(0));
		Article selectedArticle = articleSelection.getSelectedArticle();
		// article page
		selectedArticle.setPack(newPack);
		articleUpdater.update();
		// main page
		String message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
		// article page
		selectedArticle.setPack(article.getPack());
		articleUpdater.update();
		// main page
		message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
	}
}
