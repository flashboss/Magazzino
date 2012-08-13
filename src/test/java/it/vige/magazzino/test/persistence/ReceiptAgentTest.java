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

import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.FileUpload;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.test.mock.AddressMock;
import it.vige.magazzino.test.mock.CustomerMock;
import it.vige.magazzino.test.mock.ImageMock;
import it.vige.magazzino.test.mock.ListDataMock;
import it.vige.magazzino.test.mock.MagazzinoMock;
import it.vige.magazzino.test.mock.ReceiptMock;
import it.vige.magazzino.test.operation.AddressOperation;
import it.vige.magazzino.test.operation.CustomerOperation;
import it.vige.magazzino.test.operation.ImageOperation;
import it.vige.magazzino.test.operation.ListDataOperation;
import it.vige.magazzino.test.operation.MagazzinoOperation;
import it.vige.magazzino.test.operation.ReceiptOperation;

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

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class ReceiptAgentTest implements ReceiptMock {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(ReceiptAgentTest.class)
				.addClasses(CustomerMock.class, CustomerOperation.class,
						Customer.class)
				.addClasses(MagazzinoMock.class, MagazzinoOperation.class,
						Magazzino.class)
				.addClasses(ReceiptMock.class, ReceiptOperation.class,
						Receipt.class)
				.addClasses(Data.class, ListDataMock.class,
						ListDataOperation.class)
				.addClasses(ImageMock.class, ImageOperation.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(AddressMock.class, AddressOperation.class,
						Address.class)
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
	public void createReceipt() throws Exception {
		utx.begin();
		em.joinTransaction();

		em.persist(receipt0);
		em.persist(receipt1);
		em.persist(receipt2);
		em.persist(receipt3);
		em.persist(receipt4);
		em.persist(receipt5);
		em.persist(receipt6);
		em.persist(receipt7);
		em.persist(receipt8);
		em.persist(receipt9);
		em.persist(receipt10);
		utx.commit();
	}

	@Test
	public void searchReceipt() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Receipt b")
				.getResultList().size());
	}
}
