/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
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

	@Inject
	Instance<Article> bookingInstance;

	public void prepareSeedData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from Booking").executeUpdate();
		em.createQuery("delete from Hotel").executeUpdate();
		em.persist(new Article("Doubletree Atlanta-Buckhead",
				"3342 Peachtree Road NE"));
		em.createQuery("delete from User").executeUpdate();
		em.persist(new Customer("Ike", "ike", "ike@mailinator.com"));
		utx.commit();
	}

	@Test
	public void createMagazzino() throws Exception {
		magazzino("21344", "01/06/1999", "23432", "cause 1", "compensation 1",
				"2654654", "rag soc 1", "rag soc 2", "111", "01/05/1998");
		magazzino("21345", "02/06/1981", "543534", "cause 2", "compensation 2",
				"2654654", "rag soc 3", "rag soc 4", "111", "01/01/1998");
		magazzino("21346", "06/02/1999", "6546", "cause 3", "compensation 3",
				"213443", "rag soc 5", "rag soc 6", "111", "05/05/1977");
		magazzino("21347", "06/01/1999", "2342", "cause 4", "compensation 4",
				"453534", "rag soc 7", "rag soc 8", "111", "05/04/1976");
		magazzino("21348", "06/06/1980", "434", "cause 5", "compensation 5",
				"2787", "rag soc 9", "rag soc 10", "111", "04/04/1998");
		magazzino("21349", "06/06/1982", "22", "cause 6", "compensation 6",
				"53453", "rag soc 11", "rag soc 12", "111", "04/04/1998");
		magazzino("21310", "06/03/1983", "55", "cause 7", "compensation 7",
				"4333", "rag soc 13", "rag soc 14", "111", "04/01/1998");
		magazzino("21311", "06/02/1984", "34224", "cause 8", "compensation 8",
				"23433", "rag soc 15", "rag soc 16", "111", "01/04/1998");
		magazzino("21314", "06/01/1985", "534543", "cause 9", "compensation 9",
				"54546", "rag soc 17", "rag soc 18", "111", "04/02/1992");
		magazzino("213334", "02/06/1986", "23432", "cause 10",
				"compensation 10", "6544", "rag soc 19", "rag soc 20", "111",
				"04/04/1998");
		magazzino("212344", "03/06/1987", "65464", "cause 11",
				"compensation 11", "34322", "rag soc 21", "rag soc 22", "111",
				"05/04/1992");
		String stringa12 = magazzino("21234", "04/06/1988", "5646", "cause 12",
				"compensation 12", "6567", "rag soc 23", "rag soc 24", "111",
				"04/05/1998");
		magazzino("1344", "05/06/1989", "645645", "cause 13",
				"compensation 13", "5454", "rag soc 25", "rag soc 26", "111",
				"04/04/1995");

		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(stringa12.getBytes()));
		Magazzino magazzino = (Magazzino) ois.readObject();
		magazzino.getCause();
		ois.close();

	}

	@Test
	public void testBookHotel() throws Exception {
		prepareSeedData();

		BoundConversationContext ctx = null;
		BoundRequest storage = new MutableBoundRequest(
				new HashMap<String, Object>(), new HashMap<String, Object>());
		try {
			ctx = Container.instance().deploymentManager().instance()
					.select(BoundConversationContext.class).get();
			ctx.associate(storage);
			ctx.activate();

			Article booking = bookingInstance.get();
			booking.setCa("1111222233334444");

			Assert.assertEquals(1, em.createQuery("select b from Booking b")
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
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc)
			throws Exception {
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

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(magazzino);
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
