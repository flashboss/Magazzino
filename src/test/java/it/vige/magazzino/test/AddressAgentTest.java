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
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;

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
public class AddressAgentTest {
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
		em.persist(new Article("6767676",
				"3342 Peachtree Road NE"));
		em.createQuery("delete from User").executeUpdate();
		em.persist(new Customer("3246565", "ike", "ike@mailinator.com", "545454"));
		utx.commit();
	}

	@Test
	public void createAddress() throws Exception {

		address("19987", "piazza Clodio", "00122", "64746567", "7654345676",
				"reqrew@vige.it", "wwewewe.com", "54", "RM", "Guidonia", "Rome");
		address("1177", "viale Mazzini", "00134", "534537446", "346357465736",
				"hgdfgsfg@vige.it", "fdfd.com", "67", "LO", "Setteville",
				"London");
		address("98766", "piazza Bologna", "00234", "5425356457", "8875645732",
				"afadfsd@vige.it", "wwqewr.com", "33", "BO", "Settecamini",
				"Bologna");
		address("11121", "viale Giulio Cesare", "00987", "896969687",
				"32456733", "hdhhjdghf@vige.it", "qasas.com", "656", "FI",
				"Tivoli", "Florence");
		address("54566", "via Tibutina", "01234", "23423423", "2436233453",
				"fdsfsdfsd@vige.it", "ffkkfkf.com", "34", "PA", "Francoforte",
				"Paris");
		address("34322", "via Prenestina", "00152", "87584734637",
				"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "24", "SH",
				"Zagarolo", "Shangai");
		address("22222", "piazza Tuscolo", "00012", "32678475323",
				"74684736433", "fsdfsdfsd@vige.it", "ewe.com", "76", "BO",
				"Nola", "Bombay");
		address("55555", "via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome");
		String stringa12 = address("325", "via Serafini", "00999", "534748622",
				"7568473634", "ewrete@vige.it", "ewewwq.com", "66", "KY",
				"Tropea", "Kyoto");
		address("6433", "via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro", "Tokyo");

		address("4353", "piazza Clodio", "01122", "64746567", "7651676",
				"reqrew@vige.it", "wwewewe.com", "54", "RM", "Guidonia", "Rome");
		address("222", "viale Mazzini", "00114", "531446", "3463165736",
				"hgdfgsfg@vige.it", "fdfd.com", "67", "LO", "Setteville",
				"London");
		address("76435", "piazza Bologna", "00234", "54253516457", "8871115645732",
				"afadfsd@vige.it", "wwqewr.com", "33", "BO", "Settecamini",
				"Bologna");
		address("23567", "viale Giulio Cesare", "00987", "896969687",
				"32456733", "hdhhjdghf@vige.it", "qasas.com", "6526", "FI",
				"Tivoli", "Florence");
		address("876", "via Tibutina", "01234", "23423423", "2436233453",
				"fdsfsdfsd@vige.it", "ffkkfkf.com", "34", "PA", "Francoforte",
				"Paris");
		address("345", "via Prenestina", "00152", "87584734637",
				"84562354656", "gdhdgjfgj@vige.it", "ppopo.com", "124", "SH",
				"Zagarolo", "Shangai");
		address("764", "piazza Tuscolo", "00212", "32678475323",
				"74684736433", "fsdfsdfsd@vige.it", "ewe.com", "716", "BO",
				"Nola", "Bombay");
		address("3454", "via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome");
		address("7654", "via Serafini", "00999", "534748622",
				"7568473634", "ewrete@vige.it", "ewewwq.com", "616", "KY",
				"Tropea", "Kyoto");
		address("23453", "via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro", "Tokyo");
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(stringa12.getBytes()));
		Address address = (Address) ois.readObject();
		address.getEmail();
		ois.close();

	}

	public String address(String code, String homeAddress, String cap,
			String phone, String fax, String email, String site,
			String civicNumber, String province, String town, String city)
			throws Exception {
		Address address = new Address();
		address.setCode(code);
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

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(address);
		oos.close();
		byte[] bytes = new byte[351];
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

	// table to convert a nibble to a hex char.
	static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
}
