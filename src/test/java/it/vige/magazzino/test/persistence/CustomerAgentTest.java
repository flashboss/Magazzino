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
import it.vige.magazzino.test.operation.AddressOperation;
import it.vige.magazzino.test.operation.CustomerOperation;
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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CustomerAgentTest {
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
	public void createCustomer() throws Exception {
		utx.begin();
		em.joinTransaction();

		ImageOperation imageOperation = new ImageOperation();
		CustomerOperation customerOperation = new CustomerOperation();
		AddressOperation addressOperation = new AddressOperation();
		ListDataOperation listDataOperation = new ListDataOperation();
		byte[] image = imageOperation.create();
		em.persist(customerOperation.create("19987", "cliente 1", "rag soc 1",
				"92755353", addressOperation.create("19987", "piazza Clodio",
						"00122", "64746567", "7654345676", "reqrew@vige.it",
						"wwewewe.com", "54", "RM", "Guidonia", "Rome"),
				listDataOperation.create("5556", image, "Logo per customer",
						34, "logo5.gif", true, "2561")));
		em.persist(customerOperation.create("1177", "cliente 2", "rag soc 2",
				"74424577", addressOperation.create("1177", "viale Mazzini",
						"00134", "534537446", "346357465736",
						"hgdfgsfg@vige.it", "fdfd.com", "67", "LO",
						"Setteville", "London"), listDataOperation.create(
						"55156", image, "Logo per customer", 349, "logo6.gif",
						false, "")));
		em.persist(customerOperation.create("98766", "cliente 3", "rag soc 3",
				"2232322", addressOperation.create("98766", "piazza Bologna",
						"00234", "5425356457", "8875645732", "afadfsd@vige.it",
						"wwqewr.com", "33", "BO", "Settecamini", "Bologna"),
				listDataOperation.create("52656", image, "Logo per customer",
						334, "logo7.gif", true, "2562")));
		em.persist(customerOperation.create("11121", "cliente 4", "rag soc 4",
				"76565656", addressOperation.create("11121",
						"viale Giulio Cesare", "00987", "896969687",
						"32456733", "hdhhjdghf@vige.it", "qasas.com", "656",
						"FI", "Tivoli", "Florence"), listDataOperation.create(
						"55656", image, "Logo per customer", 394, "logo8.gif",
						false, "")));
		em.persist(customerOperation.create("34322", "cliente 6", "rag soc 6",
				"2535345433", addressOperation.create("34322",
						"via Prenestina", "00152", "87584734637",
						"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "24",
						"SH", "Zagarolo", "Shangai"), listDataOperation.create(
						"51656", image, "Logo per customer", 314, "logo9.gif",
						true, "2563")));
		em.persist(customerOperation.create("22222", "cliente 7", "rag soc 7",
				"654424322", addressOperation.create("22222", "piazza Tuscolo",
						"00012", "32678475323", "74684736433",
						"fsdfsdfsd@vige.it", "ewe.com", "76", "BO", "Nola",
						"Bombay"), listDataOperation.create("55646", image,
						"Logo per customer", 3411, "logo10.gif", false, "")));
		em.persist(customerOperation.create("55555", "cliente 8", "rag soc 8",
				"53546566", addressOperation.create("55555", "via Tuscolana",
						"09833", "42675473364", "754684333", "tytre@vige.it",
						"swswd.com", "546", "RM", "Castelvolturno", "Rome"),
				listDataOperation.create("55436", image, "Logo per customer",
						334, "logo11.gif", true, "2564")));
		em.persist(customerOperation.create("325", "cliente 9", "rag soc 9",
				"323244646", addressOperation.create("325", "via Serafini",
						"00999", "534748622", "323244646", "ewrete@vige.it",
						"ewewwq.com", "66", "KY", "Tropea", "Kyoto"),
				listDataOperation.create("", null, null, 0, null, false, "")));
		em.persist(customerOperation.create("6433", "cliente 10", "rag soc 10",
				"324464646", addressOperation.create("6433", "via Serafina",
						"00666", "63564832764", "3467468733",
						"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
						"Tokyo"), listDataOperation.create("", null, null, 0,
						null, false, "")));
		utx.commit();
	}

	@Test
	public void searchCustomer() throws Exception {
		Assert.assertEquals(9, em.createQuery("select b from Customer b")
				.getResultList().size());
	}

}
