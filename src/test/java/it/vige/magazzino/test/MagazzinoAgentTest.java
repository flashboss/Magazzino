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
package it.vige.magazzino.test;

import static it.vige.magazzino.test.Dependencies.FACES;
import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Article;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;

import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.Container;
import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.BoundRequest;
import org.jboss.weld.context.bound.MutableBoundRequest;
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

	@Inject
	Instance<Article> articleInstance;

	public void prepareSeedData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Article").executeUpdate();
		em.createQuery("delete from Receipt").executeUpdate();
		em.persist(new Article("7889999", "3342 Peachtree Road NE"));
		em.createQuery("delete from User").executeUpdate();
		em.persist(new Customer("34566", "ike", "ike@mailinator.com", "545454"));
		utx.commit();
	}

	@Test
	public void createMagazzino() throws Exception {

		byte[] image = image();

		magazzino("21344", "01/06/1999", "23432", "cause 1", "compensation 1",
				"2654654", "rag soc 1", "rag soc 2", "111", "01/05/1998",
				"4343289479", "39.000,00 Û", "70075", "4353", "piazza Clodio",
				"01122", "64746567", "7651676", "reqrew@vige.it",
				"wwewewe.com", "54", "RM", "Guidonia", "Rome", image,
				"Logo per magazzino", 344, "logo1.gif", true);
		magazzino("21345", "02/06/1981", "543534", "cause 2", "compensation 2",
				"2654654", "rag soc 3", "rag soc 4", "111", "01/01/1998",
				"656253253", "12.000,00 $", "32677", "222", "viale Mazzini",
				"00114", "531446", "3463165736", "hgdfgsfg@vige.it",
				"fdfd.com", "67", "LO", "Setteville", "London", image,
				"Logo per magazzino", 348, "logo2.gif", false);
		magazzino("21346", "06/02/1999", "6546", "cause 3", "compensation 3",
				"213443", "rag soc 5", "rag soc 6", "111", "05/05/1977",
				"89898983", "45.000,00 Û", "44342", "76435", "piazza Bologna",
				"00234", "54253516457", "8871115645732", "afadfsd@vige.it",
				"wwqewr.com", "33", "BO", "Settecamini", "Bologna", image,
				"Logo per magazzino", 344, "logo3.gif", true);
		magazzino("21347", "06/01/1999", "2342", "cause 4", "compensation 4",
				"453534", "rag soc 7", "rag soc 8", "111", "05/04/1976",
				"455454543", "89.000,00 $", "65345", "23567",
				"viale Giulio Cesare", "00987", "896969687", "32456733",
				"hdhhjdghf@vige.it", "qasas.com", "6526", "FI", "Tivoli",
				"Florence", image, "Logo per magazzino", 234, "logo4.gif",
				false);
		magazzino("21348", "06/06/1980", "434", "cause 5", "compensation 5",
				"2787", "rag soc 9", "rag soc 10", "111", "04/04/1998",
				"8989898989", "98.000,00 Û", "98873", "876", "via Tibutina",
				"01234", "23423423", "2436233453", "fdsfsdfsd@vige.it",
				"ffkkfkf.com", "34", "PA", "Francoforte", "Paris", image,
				"Logo per magazzino", 344, "logo1.gif", true);
		magazzino("21349", "06/06/1982", "22", "cause 6", "compensation 6",
				"53453", "rag soc 11", "rag soc 12", "111", "04/04/1998",
				"32323232323", "333.000,00 $", "54664", "345",
				"via Prenestina", "00152", "87584734637", "84562354656",
				"gdhdgjfgj@vige.it", "ppopo.com", "124", "SH", "Zagarolo",
				"Shangai", image, "Logo per magazzino", 348, "logo2.gif", false);
		magazzino("21314", "06/01/1985", "534543", "cause 9", "compensation 9",
				"54546", "rag soc 17", "rag soc 18", "111", "04/02/1992",
				"43565757", "42.000,00 Û", "22223", "7654", "via Serafini",
				"00999", "534748622", "7568473634", "ewrete@vige.it",
				"ewewwq.com", "616", "KY", "Tropea", "Kyoto", image,
				"Logo per magazzino", 344, "logo3.gif", true);
		magazzino("213334", "02/06/1986", "23432", "cause 10",
				"compensation 10", "6544", "rag soc 19", "rag soc 20", "111",
				"04/04/1998", "8787878787", "99.000,00 $", "43432", "23453",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", image, "Logo per magazzino", 234, "logo4.gif", false);
		magazzino("212344", "03/06/1987", "65464", "cause 11",
				"compensation 11", "34322", "rag soc 21", "rag soc 22", "111",
				"05/04/1992", "212121212", "33.000,00 Û", "86433", "65433",
				"via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome", null, null, 0, null, false);
		magazzino("21234", "04/06/1988", "5646", "cause 12",
				"compensation 12", "6567", "rag soc 23", "rag soc 24", "111",
				"04/05/1998", "212121212", "33.000,00 Û", "86433", "123444",
				"via Serafini", "00999", "534748622", "7568473634",
				"ewrete@vige.it", "ewewwq.com", "626", "KY", "Tropea", "Kyoto",
				null, null, 0, null, false);
		magazzino("1344", "05/06/1989", "645645", "cause 13",
				"compensation 13", "5454", "rag soc 25", "rag soc 26", "111",
				"04/04/1995", "212121212", "33.000,00 Û", "86433", "876544",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", null, null, 0, null, false);
	}

	@Test
	public void testArticles() throws Exception {
		prepareSeedData();

		BoundConversationContext ctx = null;
		BoundRequest storage = new MutableBoundRequest(
				new HashMap<String, Object>(), new HashMap<String, Object>());
		try {
			ctx = Container.instance().deploymentManager().instance()
					.select(BoundConversationContext.class).get();
			ctx.associate(storage);
			ctx.activate();

			Article article = articleInstance.get();
			article.setCa("1111222233334444");

			Assert.assertEquals(1, em.createQuery("select b from Article b")
					.getResultList().size());
		} finally {
			if (ctx != null && ctx.isActive()) {
				ctx.deactivate();
				ctx.dissociate(storage);
			}
		}
	}

	public String magazzino(String number, String date, String code,
			String cause, String compensation, String codCustomer,
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc,
			String iva, String capSoc, String reaPI, String codeAddress,
			String homeAddress, String cap, String phone, String fax,
			String email, String site, String civicNumber, String province,
			String town, String city, byte[] image, String description,
			int length, String name, boolean isMulti) throws Exception {
		Magazzino magazzino = new Magazzino();
		magazzino.setNumber(number);
		magazzino.setDate(date);
		magazzino.setCode(code);
		magazzino.setCause(cause);
		magazzino.setCompensation(compensation);
		magazzino.setCodCustomer(codCustomer);
		magazzino.setRagSoc1(ragSoc1);
		magazzino.setRagSoc2(ragSoc2);
		magazzino.setNumberDoc(numberDoc);
		magazzino.setDateDoc(dateDoc);
		magazzino.setIva(iva);
		magazzino.setCapSoc(capSoc);
		magazzino.setReaPI(reaPI);

		Address address = new Address();
		address.setCode(codeAddress);
		address.setAddress(homeAddress);
		address.setCap(cap);
		address.setCity(city);
		address.setCivicNumber(civicNumber);
		address.setEmail(email);
		address.setFax(fax);
		address.setPhone(phone);
		address.setSite(site);
		address.setTown(town);
		address.setProvince(province);

		magazzino.setAddress(address);

		if (image != null) {
			ArrayList<Data> listData = new ArrayList<Data>();
			Data data = new Data();
			data.setData(image);
			data.setDescription(description);
			data.setLength(length);
			data.setName(name);
			listData.add(data);
			if (isMulti) {
				Data data2 = new Data();
				data2.setData(image);
				data2.setDescription("nuova descrizione");
				data2.setLength(98);
				data2.setName("nuovo nome");
				listData.add(data2);
			}
			magazzino.setFiles(listData);
		}

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(magazzino);
		oos.close();
		byte[] bytes = new byte[17000];
		java.io.FileInputStream fis = new java.io.FileInputStream(
				"/Users/flashboss/prova");
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(bytes));
		Magazzino magazzinoRead = (Magazzino) ois.readObject();
		System.out.println(magazzinoRead.getRagSoc1());
		ois.close();
		return result;
	}

	public String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// look up high nibble char
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return setBinaryLength(sb.toString());
	}

	private String setBinaryLength(String result) {
		while (true) {
			if (result.endsWith("0"))
				result = result.substring(0, result.lastIndexOf("0"));
			else
				break;
		}
		if (result.length() % 2 != 0)
			result = result + "0";
		return result;
	}

	public byte[] image() throws Exception {
		byte[] bytes = new byte[10310];
		java.io.FileInputStream fis = new java.io.FileInputStream(
				"/Users/flashboss/Desktop/logo.gif");
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
		return bytes;
	}

	// table to convert a nibble to a hex char.
	static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
}
