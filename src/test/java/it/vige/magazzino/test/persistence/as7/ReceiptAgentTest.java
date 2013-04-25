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
package it.vige.magazzino.test.persistence.as7;

import static it.vige.magazzino.test.Dependencies.FACES;
import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import static it.vige.magazzino.test.mock.CustomerMock.customer0;
import static it.vige.magazzino.test.mock.CustomerMock.customer1;
import static it.vige.magazzino.test.mock.CustomerMock.customer2;
import static it.vige.magazzino.test.mock.CustomerMock.customer3;
import static it.vige.magazzino.test.mock.CustomerMock.customer4;
import static it.vige.magazzino.test.mock.CustomerMock.customer5;
import static it.vige.magazzino.test.mock.CustomerMock.customer6;
import static it.vige.magazzino.test.mock.CustomerMock.customer7;
import static it.vige.magazzino.test.mock.CustomerMock.customer8;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino0;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino1;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino10;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino2;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino3;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino4;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino5;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino6;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino7;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino8;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino9;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt0;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt1;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt10;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt2;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt3;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt4;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt5;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt6;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt7;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt8;
import static it.vige.magazzino.test.mock.ReceiptMock.receipt9;
import static it.vige.magazzino.test.mock.ReceiptMock.receipts;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
public class ReceiptAgentTest {
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
		if (em.find(Receipt.class, receipt0.getCodeReceipt()) == null) {
			utx.begin();
			em.joinTransaction();
			persistList(customer0.getFiles());
			persistList(customer0.getReceipts());
			em.persist(customer0);
			persistList(customer1.getFiles());
			persistList(customer1.getReceipts());
			em.persist(customer1);
			persistList(customer2.getFiles());
			persistList(customer2.getReceipts());
			em.persist(customer2);
			persistList(customer3.getFiles());
			persistList(customer3.getReceipts());
			em.persist(customer3);
			persistList(customer4.getFiles());
			persistList(customer4.getReceipts());
			em.persist(customer4);
			persistList(customer5.getFiles());
			persistList(customer5.getReceipts());
			em.persist(customer5);
			persistList(customer6.getFiles());
			persistList(customer6.getReceipts());
			em.persist(customer6);
			persistList(customer7.getFiles());
			persistList(customer7.getReceipts());
			em.persist(customer7);
			persistList(customer8.getFiles());
			persistList(customer8.getReceipts());
			em.persist(customer8);

			persistList(magazzino0.getFiles());
			persistList(magazzino0.getReceipts());
			em.persist(magazzino0);
			persistList(magazzino1.getFiles());
			persistList(magazzino1.getReceipts());
			em.persist(magazzino1);
			persistList(magazzino2.getFiles());
			persistList(magazzino2.getReceipts());
			em.persist(magazzino2);
			persistList(magazzino3.getFiles());
			persistList(magazzino3.getReceipts());
			em.persist(magazzino3);
			persistList(magazzino4.getFiles());
			persistList(magazzino4.getReceipts());
			em.persist(magazzino4);
			persistList(magazzino5.getFiles());
			persistList(magazzino5.getReceipts());
			em.persist(magazzino5);
			persistList(magazzino6.getFiles());
			persistList(magazzino6.getReceipts());
			em.persist(magazzino6);
			persistList(magazzino7.getFiles());
			persistList(magazzino7.getReceipts());
			em.persist(magazzino7);
			persistList(magazzino8.getFiles());
			persistList(magazzino8.getReceipts());
			em.persist(magazzino8);
			persistList(magazzino9.getFiles());
			persistList(magazzino9.getReceipts());
			em.persist(magazzino9);
			persistList(magazzino10.getFiles());
			persistList(magazzino10.getReceipts());
			em.persist(magazzino10);

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
		receipt.setCodeReceipt("99999999");
		receipt.setDate(date);
		receipt.setCause(cause);
		receipt.setDescription(description);
		receipt.setCustomer(customer0);
		receipt.setJar(magazzino0);
		receiptRegister.register();
		String message = messages.getAll().iterator().next().getText();
		assertTrue(message, message.contains(receipt.getCodeReceipt()));
		// cancel receipt
		receipt.setCodeReceipt("");
		try {
			receiptRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		receipt.setCause("");
		receipt.setCodeReceipt("99999991");
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

	private <T> void persistList(List<T> elements) {
		if (elements != null)
			for (T element : elements)
				em.persist(element);
	}
}
