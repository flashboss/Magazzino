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
public class AddressAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, DataContainer.class.getPackage())
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(SOLDER)
				.addAsLibraries(RICHFACES)
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
	public void createAddress() throws Exception {
		utx.begin();
		em.joinTransaction();

		AddressOperation addressMock = new AddressOperation();
		em.persist(addressMock.create("19987", "piazza Clodio", "00122", "64746567",
				"7654345676", "reqrew@vige.it", "wwewewe.com", "54", "RM",
				"Guidonia", "Rome"));
		em.persist(addressMock.create("1177", "viale Mazzini", "00134", "534537446",
				"346357465736", "hgdfgsfg@vige.it", "fdfd.com", "67", "LO",
				"Setteville", "London"));
		em.persist(addressMock.create("98766", "piazza Bologna", "00234", "5425356457",
				"8875645732", "afadfsd@vige.it", "wwqewr.com", "33", "BO",
				"Settecamini", "Bologna"));
		em.persist(addressMock.create("11121", "viale Giulio Cesare", "00987",
				"896969687", "32456733", "hdhhjdghf@vige.it", "qasas.com",
				"656", "FI", "Tivoli", "Florence"));
		em.persist(addressMock.create("54566", "via Tibutina", "01234", "23423423",
				"2436233453", "fdsfsdfsd@vige.it", "ffkkfkf.com", "34", "PA",
				"Francoforte", "Paris"));
		em.persist(addressMock.create("34322", "via Prenestina", "00152", "87584734637",
				"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "24", "SH",
				"Zagarolo", "Shangai"));
		em.persist(addressMock.create("22222", "piazza Tuscolo", "00012", "32678475323",
				"74684736433", "fsdfsdfsd@vige.it", "ewe.com", "76", "BO",
				"Nola", "Bombay"));
		em.persist(addressMock.create("55555", "via Tuscolana", "09833", "42675473364",
				"754684333", "tytre@vige.it", "swswd.com", "546", "RM",
				"Castelvolturno", "Rome"));
		em.persist(addressMock.create("325", "via Serafini", "00999", "534748622",
				"7568473634", "ewrete@vige.it", "ewewwq.com", "66", "KY",
				"Tropea", "Kyoto"));
		em.persist(addressMock.create("6433", "via Serafina", "00666", "63564832764",
				"3467468733", "ngnghghg@vige.it", "llklk.it", "33", "TK",
				"Palinuro", "Tokyo"));
		em.persist(addressMock.create("4353", "piazza Clodio", "01122", "64746567",
				"7651676", "reqrew@vige.it", "wwewewe.com", "54", "RM",
				"Guidonia", "Rome"));
		em.persist(addressMock.create("222", "viale Mazzini", "00114", "531446",
				"3463165736", "hgdfgsfg@vige.it", "fdfd.com", "67", "LO",
				"Setteville", "London"));
		em.persist(addressMock.create("76435", "piazza Bologna", "00234", "54253516457",
				"8871115645732", "afadfsd@vige.it", "wwqewr.com", "33", "BO",
				"Settecamini", "Bologna"));
		em.persist(addressMock.create("23567", "viale Giulio Cesare", "00987",
				"896969687", "32456733", "hdhhjdghf@vige.it", "qasas.com",
				"6526", "FI", "Tivoli", "Florence"));
		em.persist(addressMock.create("876", "via Tibutina", "01234", "23423423",
				"2436233453", "fdsfsdfsd@vige.it", "ffkkfkf.com", "34", "PA",
				"Francoforte", "Paris"));
		em.persist(addressMock.create("345", "via Prenestina", "00152", "87584734637",
				"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "124", "SH",
				"Zagarolo", "Shangai"));
		em.persist(addressMock.create("764", "piazza Tuscolo", "00212", "32678475323",
				"74684736433", "fsdfsdfsd@vige.it", "ewe.com", "716", "BO",
				"Nola", "Bombay"));
		em.persist(addressMock.create("3454", "via Tuscolana", "09833", "42675473364",
				"754684333", "tytre@vige.it", "swswd.com", "546", "RM",
				"Castelvolturno", "Rome"));
		em.persist(addressMock.create("7654", "via Serafini", "00999", "534748622",
				"7568473634", "ewrete@vige.it", "ewewwq.com", "616", "KY",
				"Tropea", "Kyoto"));
		em.persist(addressMock.create("23453", "via Serafina", "00666", "63564832764",
				"3467468733", "ngnghghg@vige.it", "llklk.it", "33", "TK",
				"Palinuro", "Tokyo"));
		utx.commit();
	}

	@Test
	public void searchAddress() throws Exception {
		Assert.assertEquals(20, em.createQuery("select b from Address b")
				.getResultList().size());
	}
}
