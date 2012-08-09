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

import static it.vige.magazzino.test.persistence.Dependencies.FACES;
import static it.vige.magazzino.test.persistence.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.persistence.Dependencies.RICHFACES;
import static it.vige.magazzino.test.persistence.Dependencies.SOLDER;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.test.operation.ImageOperation;
import it.vige.magazzino.test.operation.ListDataOperation;

import java.util.List;

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

@RunWith(Arquillian.class)
public class ListDataAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, DataContainer.class.getPackage())
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(SOLDER)
				.addAsLibraries(RICHFACES)
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

	@Test
	public void createListData() throws Exception {
		utx.begin();
		em.joinTransaction();

		ImageOperation imageOperation = new ImageOperation();
		ListDataOperation listDataOperation = new ListDataOperation();
		byte[] image = imageOperation.create();
		List<Data> listData = listDataOperation.create("5556", image, "Logo per magazzino",
				344, "logo1.gif", true, "2561");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("55156", image, "Logo per magazzino", 348,
				"logo2.gif", false, "");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("52656", image, "Logo per magazzino", 344,
				"logo3.gif", true, "2562");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("55656", image, "Logo per magazzino", 234,
				"logo4.gif", false, "");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("51656", image, "Logo per customer", 34,
				"logo5.gif", true, "2563");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("55646", image, "Logo per customer", 349,
				"logo6.gif", false, "");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("55436", image, "Logo per customer", 334,
				"logo7.gif", true, "2564");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("556", image, "Logo per customer", 394,
				"logo8.gif", false, "");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("5526", image, "Logo per customer", 314,
				"logo9.gif", true, "2565");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("55956", image, "Logo per customer", 3411,
				"logo10.gif", false, "");
		for (Data data : listData)
			em.persist(data);
		listData = listDataOperation.create("256", image, "Logo per customer", 334,
				"logo11.gif", true, "2566");
		for (Data data : listData)
			em.persist(data);
		utx.commit();
	}

	@Test
	public void searchListData() throws Exception {
		Assert.assertEquals(17, em.createQuery("select b from Data b")
				.getResultList().size());
	}
}
