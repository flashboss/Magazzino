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

import static it.vige.magazzino.test.Dependencies.FACES;
import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.FileUpload;
import it.vige.magazzino.ReceiptRegister;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.ReceiptSearch;
import it.vige.magazzino.inventory.SearchCriteria;
import it.vige.magazzino.inventory.all.ReceiptAllSearch;
import it.vige.magazzino.log.ReceiptLog;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.model.Receipt_;
import it.vige.magazzino.remove.ReceiptDeleter;
import it.vige.magazzino.selection.ReceiptSelection;
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
import it.vige.magazzino.update.ReceiptUpdater;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.international.status.Message;
import org.jboss.seam.international.status.Messages;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.Container;
import org.jboss.weld.context.http.HttpConversationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class ReceiptAgentTest implements ReceiptMock, MagazzinoMock,
		CustomerMock {
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
				.addClasses(ReceiptRegister.class, ReceiptUpdater.class,
						ReceiptDeleter.class, ReceiptSelection.class)
				.addClasses(ReceiptLog.class, Receipt_.class)
				.addClasses(ReceiptSearch.class, ReceiptAllSearch.class,
						SearchCriteria.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(DefaultBundleKey.class)
				.addClasses(AddressMock.class, AddressOperation.class,
						Address.class)
				.addAsLibraries(SOLDER)
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(RICHFACES)
				.addAsLibraries(RICHFACES)
				.addAsResource("logo.gif")
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	ReceiptRegister receiptRegister;

	@EJB
	ReceiptUpdater receiptUpdater;

	@EJB
	ReceiptDeleter receiptDeleter;

	@Inject
	ReceiptSelection receiptSelection;

	@EJB
	ReceiptSearch receiptSearch;

	@Inject
	SearchCriteria criteria;

	@Inject
	Messages messages;

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Before
	public void createReceipt() throws Exception {
		if (em.find(Receipt.class, receipt0.getNumber()) == null) {
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

			em.persist(customer0);
			em.persist(magazzino0);
			utx.commit();
		}
	}

	@Test
	public void searchReceipt() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Receipt b")
				.getResultList().size());
	}

	@Test
	public void testSearch() {
		criteria.setQuery("causale");
		receiptSearch.find();
		assertFalse(receiptSearch.getReceipts().size() == 0);
		assertEquals(5, receiptSearch.getReceipts().size());

		criteria.setQuery("nonExistingReceipt");
		receiptSearch.find();
		assertTrue(receiptSearch.getReceipts().size() == 0);
		assertEquals(0, receiptSearch.getReceipts().size());
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		criteria.setQuery("causale");

		for (int pageSize : values) {
			criteria.setPageSize(pageSize);
			receiptSearch.find();
			if (receipts.length > pageSize)
				assertEquals(receiptSearch.getReceipts().size(), pageSize);
			else
				assertEquals(receiptSearch.getReceipts().size(),
						receipts.length);
		}
	}

	@Test
	public void testInsertDeleteNewReceipt() {
		String date = "newName";
		String description = "new description";
		String cause = "new rag soc for receipt test";
		Receipt receipt = receiptRegister.getNewReceipt();
		receipt.setNumber("99999999");
		receipt.setDate(date);
		receipt.setCause(cause);
		receipt.setDescription(description);
		receipt.setCustomer(customer0);
		receipt.setJar(magazzino0);
		receiptRegister.register();
		String message = messages.getAll().iterator().next().getText();
		assertTrue(message, message.contains(receipt.getNumber()));
		// cancel receipt
		receipt.setNumber("");
		try {
			receiptRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		receipt.setCause("");
		receipt.setNumber("99999991");
		try {
			receiptRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		criteria.setQuery(date);
		receiptSearch.find();
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		receiptSelection.selectReceipt(receiptSearch.getReceipts().get(0));
		Receipt selectedReceipt = receiptSelection.getSelectedReceipt();
		receiptDeleter.delete(selectedReceipt);
		boolean found = false;
		for (Message messageFromList : messages.getAll()) {
			if (messageFromList.getText().contains("99999999")
					&& messageFromList.getText().contains("deleted"))
				found = true;
		}

		assertTrue(message, found);
	}

	@Test
	public void testMultiSearchingUpdate() {
		Receipt[] receipts = new Receipt[] { receipt0, receipt1, receipt2,
				receipt4 };

		// make 4 receipts
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		for (Receipt receipt : receipts) {
			searchUpdateReceipt(receipt, "test-ejb-for-cause");
		}
		criteria.setQuery("test-ejb-for-cause");
		receiptSearch.find();
		assertEquals(receiptSearch.getReceipts().size(), 0);
	}

	protected void searchUpdateReceipt(Receipt receipt, String newCause) {
		criteria.setQuery(receipt.getDescription());
		receiptSearch.find();
		receiptSelection.selectReceipt(receiptSearch.getReceipts().get(0));
		Receipt selectedReceipt = receiptSelection.getSelectedReceipt();
		// receipt page
		selectedReceipt.setCause(newCause);
		receiptUpdater.update();
		// main page
		String message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
		// receipt page
		selectedReceipt.setCause(receipt.getCause());
		receiptUpdater.update();
		// main page
		message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
	}
}
