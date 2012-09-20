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
import it.vige.magazzino.model.Data;
import it.vige.magazzino.test.mock.ImageMock;
import it.vige.magazzino.test.mock.ListDataMock;
import it.vige.magazzino.test.operation.ImageOperation;
import it.vige.magazzino.test.operation.ListDataOperation;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class ListDataAgentTest implements ListDataMock {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(ListDataAgentTest.class)
				.addClasses(ImageMock.class, ImageOperation.class)
				.addClasses(Data.class, ListDataMock.class,
						ListDataOperation.class)
				.addAsLibraries(SOLDER)
				.addAsResource("logo.gif")
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

	@Before
	public void createListData() throws Exception {
		if (em.find(Data.class, listData0.get(0).getCode()) == null) {
			utx.begin();
			em.joinTransaction();

			for (Data data : listData0)
				em.persist(data);
			for (Data data : listData1)
				em.persist(data);
			for (Data data : listData2)
				em.persist(data);
			for (Data data : listData3)
				em.persist(data);
			for (Data data : listData4)
				em.persist(data);
			for (Data data : listData5)
				em.persist(data);
			for (Data data : listData6)
				em.persist(data);
			for (Data data : listData7)
				em.persist(data);
			for (Data data : listData8)
				em.persist(data);
			for (Data data : listData9)
				em.persist(data);
			for (Data data : listData10)
				em.persist(data);
			for (Data data : listData11)
				em.persist(data);
			for (Data data : listData12)
				em.persist(data);
			for (Data data : listData13)
				em.persist(data);
			for (Data data : listData14)
				em.persist(data);
			for (Data data : listData15)
				em.persist(data);
			for (Data data : listData16)
				em.persist(data);
			for (Data data : listData17)
				em.persist(data);
			for (Data data : listData18)
				em.persist(data);
			for (Data data : listData19)
				em.persist(data);
			utx.commit();
		}
	}

	@Test
	public void searchListData() throws Exception {
		Assert.assertEquals(35, em.createQuery("select b from Data b")
				.getResultList().size());
	}
}
