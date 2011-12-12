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
		em.persist(new Article("Doubletree Atlanta-Buckhead",
				"3342 Peachtree Road NE"));
		em.createQuery("delete from User").executeUpdate();
		em.persist(new Customer("Ike", "ike", "ike@mailinator.com"));
		utx.commit();
	}

	@Test
	public void createCustomer() throws Exception {

		customer("19987", "cliente 1", "rag soc 1");
		customer("1177", "cliente 2", "rag soc 2");
		customer("98766", "cliente 3", "rag soc 3");
		customer("11121", "cliente 4", "rag soc 4");
		customer("54566", "cliente 5", "rag soc 5");
		customer("34322", "cliente 6", "rag soc 6");
		customer("22222", "cliente 7", "rag soc 7");
		customer("55555", "cliente 8", "rag soc 8");
		String stringa12 = customer("325", "cliente 9", "rag soc 9");
		customer("6433", "cliente 10", "rag soc 10");
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(stringa12.getBytes()));
		Customer customer = (Customer) ois.readObject();
		customer.getName();
		ois.close();

	}

	public String customer(String code, String name, String ragSocial)
			throws Exception {
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName(name);
		customer.setRagSocial(ragSocial);

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(customer);
		oos.close();
		byte[] bytes = new byte[331];
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
		return sb.toString();
	}

	// table to convert a nibble to a hex char.
	static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
}
