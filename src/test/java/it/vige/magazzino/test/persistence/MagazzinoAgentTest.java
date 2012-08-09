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
import static it.vige.magazzino.test.persistence.Utils.image;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.test.operation.AddressOperation;
import it.vige.magazzino.test.operation.ListDataOperation;
import it.vige.magazzino.test.operation.MagazzinoOperation;

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
public class MagazzinoAgentTest {
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
	public void createMagazzino() throws Exception {
		byte[] image = image();

		utx.begin();
		em.joinTransaction();

		MagazzinoOperation magazzinoOperation = new MagazzinoOperation();
		AddressOperation addressOperation = new AddressOperation();
		ListDataOperation listDataOperation = new ListDataOperation();
		em.persist(magazzinoOperation.create("21344", "01/06/1999", "23432",
				"cause 1", "compensation 1", "2654654", "rag soc 1",
				"rag soc 2", "111", "01/05/1998", "4343289479", "39.000,00 Û",
				"70075", addressOperation.create("4353", "piazza Clodio",
						"01122", "64746567", "7651676", "reqrew@vige.it",
						"wwewewe.com", "54", "RM", "Guidonia", "Rome"),
				listDataOperation.create("5556", image, "Logo per magazzino",
						344, "logo1.gif", true, "2561")));
		em.persist(magazzinoOperation.create("21345", "02/06/1981", "543534",
				"cause 2", "compensation 2", "2654654", "rag soc 3",
				"rag soc 4", "111", "01/01/1998", "656253253", "12.000,00 $",
				"32677", addressOperation.create("222", "viale Mazzini",
						"00114", "531446", "3463165736", "hgdfgsfg@vige.it",
						"fdfd.com", "67", "LO", "Setteville", "London"),
				listDataOperation.create("55156", image, "Logo per magazzino",
						348, "logo2.gif", false, "")));
		em.persist(magazzinoOperation.create("21346", "06/02/1999", "6546",
				"cause 3", "compensation 3", "213443", "rag soc 5",
				"rag soc 6", "111", "05/05/1977", "89898983", "45.000,00 Û",
				"44342", addressOperation.create("76435", "piazza Bologna",
						"00234", "54253516457", "8871115645732",
						"afadfsd@vige.it", "wwqewr.com", "33", "BO",
						"Settecamini", "Bologna"), listDataOperation.create(
						"52656", image, "Logo per magazzino", 344, "logo3.gif",
						true, "2562")));
		em.persist(magazzinoOperation.create("21347", "06/01/1999", "2342",
				"cause 4", "compensation 4", "453534", "rag soc 7",
				"rag soc 8", "111", "05/04/1976", "455454543", "89.000,00 $",
				"65345", addressOperation.create("23567",
						"viale Giulio Cesare", "00987", "896969687",
						"32456733", "hdhhjdghf@vige.it", "qasas.com", "6526",
						"FI", "Tivoli", "Florence"), listDataOperation.create(
						"55656", image, "Logo per magazzino", 234, "logo4.gif",
						false, "")));
		em.persist(magazzinoOperation.create("21348", "06/06/1980", "434",
				"cause 5", "compensation 5", "2787", "rag soc 9", "rag soc 10",
				"111", "04/04/1998", "8989898989", "98.000,00 Û", "98873",
				addressOperation.create("876", "via Tibutina", "01234",
						"23423423", "2436233453", "fdsfsdfsd@vige.it",
						"ffkkfkf.com", "34", "PA", "Francoforte", "Paris"),
				listDataOperation.create("51656", image, "Logo per magazzino",
						344, "logo1.gif", true, "2563")));
		em.persist(magazzinoOperation.create("21349", "06/06/1982", "22",
				"cause 6", "compensation 6", "53453", "rag soc 11",
				"rag soc 12", "111", "04/04/1998", "32323232323",
				"333.000,00 $", "54664", addressOperation.create("345",
						"via Prenestina", "00152", "87584734637",
						"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "124",
						"SH", "Zagarolo", "Shangai"), listDataOperation.create(
						"", image, "Logo per magazzino", 348, "logo2.gif",
						false, "")));
		em.persist(magazzinoOperation.create("21314", "06/01/1985", "534543",
				"cause 9", "compensation 9", "54546", "rag soc 17",
				"rag soc 18", "111", "04/02/1992", "43565757", "42.000,00 Û",
				"22223", addressOperation.create("7654", "via Serafini",
						"00999", "534748622", "7568473634", "ewrete@vige.it",
						"ewewwq.com", "616", "KY", "Tropea", "Kyoto"),
				listDataOperation.create("55646", image, "Logo per magazzino",
						344, "logo3.gif", true, "2564")));
		em.persist(magazzinoOperation.create("213334", "02/06/1986", "23432",
				"cause 10", "compensation 10", "6544", "rag soc 19",
				"rag soc 20", "111", "04/04/1998", "8787878787", "99.000,00 $",
				"43432", addressOperation.create("23453", "via Serafina",
						"00666", "63564832764", "3467468733",
						"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
						"Tokyo"), listDataOperation.create("55436", image,
						"Logo per magazzino", 234, "logo4.gif", false, "")));
		em.persist(magazzinoOperation.create("212344", "03/06/1987", "65464",
				"cause 11", "compensation 11", "34322", "rag soc 21",
				"rag soc 22", "111", "05/04/1992", "212121212", "33.000,00 Û",
				"86433", addressOperation.create("65433", "via Tuscolana",
						"09833", "42675473364", "754684333", "tytre@vige.it",
						"swswd.com", "546", "RM", "Castelvolturno", "Rome"),
				listDataOperation.create("556", null, null, 0, null, false, "")));
		em.persist(magazzinoOperation.create("21234", "04/06/1988", "5646",
				"cause 12", "compensation 12", "6567", "rag soc 23",
				"rag soc 24", "111", "04/05/1998", "212121212", "33.000,00 Û",
				"86433", addressOperation.create("123444", "via Serafini",
						"00999", "534748622", "7568473634", "ewrete@vige.it",
						"ewewwq.com", "626", "KY", "Tropea", "Kyoto"),
				listDataOperation
						.create("5526", null, null, 0, null, false, "")));
		em.persist(magazzinoOperation.create("1344", "05/06/1989", "645645",
				"cause 13", "compensation 13", "5454", "rag soc 25",
				"rag soc 26", "111", "04/04/1995", "212121212", "33.000,00 Û",
				"86433", addressOperation.create("876544", "via Serafina",
						"00666", "63564832764", "3467468733",
						"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
						"Tokyo"), listDataOperation.create("55956", null, null,
						0, null, false, "")));
		utx.commit();
	}

	@Test
	public void searchMagazzino() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Magazzino b")
				.getResultList().size());
	}

}
