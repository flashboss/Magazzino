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
package it.vige.magazzino.test.persistence;

import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.model.Article;
import it.vige.magazzino.test.mock.ArticleMock;
import it.vige.magazzino.test.operation.ArticleOperation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class ArticleAgentTest implements ArticleMock {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(ArticleAgentTest.class)
				.addClasses(ArticleMock.class, ArticleOperation.class,
						Article.class)
				.addAsLibraries(SOLDER)
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Test
	public void createArticle() throws Exception {
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

	@Test
	public void searchArticle() throws Exception {
		Assert.assertEquals(16, em.createQuery("select b from Article b")
				.getResultList().size());
	}
}
