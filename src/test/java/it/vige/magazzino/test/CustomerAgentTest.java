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

import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.log.ArticleLog;
import it.vige.magazzino.log.CustomerLog;
import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.log.ReceiptLog;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Article;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CustomerAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Magazzino.class.getPackage())
				.addClasses(Receipt.class, Magazzino.class, Customer.class,
						Article.class, DefaultBundleKey.class,
						AuthenticatedUserProducer.class, ArticleLog.class,
						CustomerLog.class, MagazzinoLog.class, ReceiptLog.class)
				.addAsLibraries(SOLDER)
				.addAsLibraries(INTERNATIONAL)
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	public void prepareSeedData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Article").executeUpdate();
		em.createQuery("delete from Receipt").executeUpdate();
		em.persist(new Article("898989", "3342 Peachtree Road NE"));
		em.createQuery("delete from User").executeUpdate();
		em.persist(new Customer("45454", "ike", "ike@mailinator.com", "545454"));
		utx.commit();
	}

	@Test
	public void createCustomer() throws Exception {

		byte[] image = image();

		customer("19987", "cliente 1", "rag soc 1", "92755353", "19987",
				"piazza Clodio", "00122", "64746567", "7654345676",
				"reqrew@vige.it", "wwewewe.com", "54", "RM", "Guidonia",
				"Rome", image, "Logo per customer", 34, "logo5.gif", true);
		customer("1177", "cliente 2", "rag soc 2", "74424577", "1177",
				"viale Mazzini", "00134", "534537446", "346357465736",
				"hgdfgsfg@vige.it", "fdfd.com", "67", "LO", "Setteville",
				"London", image, "Logo per customer", 349, "logo6.gif", false);
		customer("98766", "cliente 3", "rag soc 3", "2232322", "98766",
				"piazza Bologna", "00234", "5425356457", "8875645732",
				"afadfsd@vige.it", "wwqewr.com", "33", "BO", "Settecamini",
				"Bologna", image, "Logo per customer", 334, "logo7.gif", true);
		customer("11121", "cliente 4", "rag soc 4", "76565656", "11121",
				"viale Giulio Cesare", "00987", "896969687", "32456733",
				"hdhhjdghf@vige.it", "qasas.com", "656", "FI", "Tivoli",
				"Florence", image, "Logo per customer", 394, "logo8.gif", false);
		customer("34322", "cliente 6", "rag soc 6", "2535345433", "34322",
				"via Prenestina", "00152", "87584734637", "84562354656",
				"gdhdgjfgj@vige.it", "ppopo.com", "24", "SH", "Zagarolo",
				"Shangai", image, "Logo per customer", 314, "logo9.gif", true);
		customer("22222", "cliente 7", "rag soc 7", "654424322", "22222",
				"piazza Tuscolo", "00012", "32678475323", "74684736433",
				"fsdfsdfsd@vige.it", "ewe.com", "76", "BO", "Nola", "Bombay",
				image, "Logo per customer", 3411, "logo10.gif", false);
		customer("55555", "cliente 8", "rag soc 8", "53546566", "55555",
				"via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome", image, "Logo per customer", 334, "logo11.gif", true);
		String stringa12 = customer("325", "cliente 9", "rag soc 9",
				"323244646", "325", "via Serafini", "00999", "534748622",
				"323244646", "ewrete@vige.it", "ewewwq.com", "66", "KY",
				"Tropea", "Kyoto", null, null, 0, null, false);
		customer("6433", "cliente 10", "rag soc 10", "324464646", "6433",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", null, null, 0, null, false);
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(stringa12.getBytes()));
		Customer customer = (Customer) ois.readObject();
		customer.getName();
		ois.close();

	}

	public String customer(String code, String name, String ragSocial,
			String iva, String codeAddress, String homeAddress, String cap,
			String phone, String fax, String email, String site,
			String civicNumber, String province, String town, String city,
			byte[] image, String description, int length, String nameFile,
			boolean isMulti) throws Exception {
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName(name);
		customer.setRagSocial(ragSocial);

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

		customer.setAddress(address);

		if (image != null) {
			ArrayList<Data> listData = new ArrayList<Data>();
			Data data = new Data();
			data.setData(image);
			data.setDescription(description);
			data.setLength(length);
			data.setName(nameFile);
			listData.add(data);
			if (isMulti) {
				Data data2 = new Data();
				data2.setData(image);
				data2.setDescription("nuova descrizione");
				data2.setLength(98);
				data2.setName("nuovo nome");
				listData.add(data2);
			}
			customer.setFiles(listData);
		}

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(customer);
		oos.close();
		byte[] bytes = new byte[13100];
		java.io.FileInputStream fis = new java.io.FileInputStream(
				"/Users/flashboss/prova");
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
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
