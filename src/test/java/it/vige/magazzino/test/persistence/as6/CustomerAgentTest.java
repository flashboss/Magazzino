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
package it.vige.magazzino.test.persistence.as6;

import static it.vige.magazzino.test.mock.CustomerMock.customer0;
import static it.vige.magazzino.test.mock.CustomerMock.customer1;
import static it.vige.magazzino.test.mock.CustomerMock.customer2;
import static it.vige.magazzino.test.mock.CustomerMock.customer3;
import static it.vige.magazzino.test.mock.CustomerMock.customer4;
import static it.vige.magazzino.test.mock.CustomerMock.customer5;
import static it.vige.magazzino.test.mock.CustomerMock.customer6;
import static it.vige.magazzino.test.mock.CustomerMock.customer7;
import static it.vige.magazzino.test.mock.CustomerMock.customer8;
import static it.vige.magazzino.test.mock.CustomerMock.customers;
import static it.vige.magazzino.test.persistence.Dependencies.FACES;
import static it.vige.magazzino.test.persistence.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.persistence.Dependencies.RICHFACES;
import static it.vige.magazzino.test.persistence.Dependencies.SOLDER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.vige.magazzino.CustomerRegister;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.FileUpload;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.CustomerSearch;
import it.vige.magazzino.inventory.SearchCriteria;
import it.vige.magazzino.inventory.all.CustomerAllSearch;
import it.vige.magazzino.log.CustomerLog;
import it.vige.magazzino.log.CustomerLog_$logger;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Customer_;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.remove.CustomerDeleter;
import it.vige.magazzino.selection.CustomerSelection;
import it.vige.magazzino.test.mock.AddressMock;
import it.vige.magazzino.test.mock.CustomerMock;
import it.vige.magazzino.test.mock.ImageMock;
import it.vige.magazzino.test.mock.ListDataMock;
import it.vige.magazzino.test.operation.AddressOperation;
import it.vige.magazzino.test.operation.CustomerOperation;
import it.vige.magazzino.test.operation.ImageOperation;
import it.vige.magazzino.test.operation.ListDataOperation;
import it.vige.magazzino.update.CustomerUpdater;

import java.util.List;

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
public class CustomerAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(CustomerAgentTest.class)
				.addClasses(Data.class, ListDataMock.class,
						ListDataOperation.class)
				.addClasses(ImageMock.class, ImageOperation.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(AddressMock.class, AddressOperation.class,
						Address.class)
				.addClasses(CustomerMock.class, CustomerOperation.class,
						Customer.class)
				.addClasses(CustomerRegister.class, CustomerUpdater.class,
						CustomerDeleter.class, CustomerSelection.class)
				.addClasses(CustomerLog.class, CustomerLog_$logger.class, Customer_.class)
				.addClasses(CustomerSearch.class, CustomerAllSearch.class,
						SearchCriteria.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(DefaultBundleKey.class)
				.addClasses(Magazzino.class)
				.addClasses(Receipt.class)
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
	CustomerRegister customerRegister;

	@EJB
	CustomerUpdater customerUpdater;

	@EJB
	CustomerDeleter customerDeleter;

	@Inject
	CustomerSelection customerSelection;

	@EJB
	CustomerSearch customerSearch;

	@Inject
	SearchCriteria criteria;

	@Inject
	Messages messages;

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Before
	public void createCustomer() throws Exception {
		if (em.find(Customer.class, customer0.getCodeCustomer()) == null) {
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
			utx.commit();
		}
	}

	@Test
	public void searchCustomer() throws Exception {
		Assert.assertEquals(9, em.createQuery("select b from Customer b")
				.getResultList().size());
	}

	@Test
	public void testSearch() {
		criteria.setQuery("cliente");
		customerSearch.find();
		assertFalse(customerSearch.getCustomers().size() == 0);
		assertEquals(5, customerSearch.getCustomers().size());

		criteria.setQuery("nonExistingCustomer");
		customerSearch.find();
		assertTrue(customerSearch.getCustomers().size() == 0);
		assertEquals(0, customerSearch.getCustomers().size());
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		criteria.setQuery("rag soc");

		for (int pageSize : values) {
			criteria.setPageSize(pageSize);
			customerSearch.find();
			if (customers.length > pageSize)
				assertEquals(customerSearch.getCustomers().size(), pageSize);
			else
				assertEquals(customerSearch.getCustomers().size(),
						customers.length);

		}
	}

	@Test
	public void testInsertDeleteNewCustomer() {
		String customerName = "newName";
		String pIva1 = "0123456789012347";
		String ragSoc1 = "new rag soc for customer test";
		Address address = new Address();
		address.setAddress("Vige street");
		Customer customer = customerRegister.getNewCustomer();
		customer.setCodeCustomer("99999999");
		customer.setName(customerName);
		customer.setIva(pIva1);
		customer.setRagSocial(ragSoc1);
		customer.setAddress(address);
		customerRegister.register();
		String message = messages.getAll().iterator().next().getText();
		assertTrue(message, message.contains(customer.getCodeCustomer()));
		// cancel customer
		customer.setCodeCustomer("");
		try {
			customerRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		customer.setName("");
		customer.setCodeCustomer("99999991");
		try {
			customerRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		criteria.setQuery(customerName);
		customerSearch.find();
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		customerSelection.selectCustomer(customerSearch.getCustomers().get(0));
		Customer selectedCustomer = customerSelection.getSelectedCustomer();
		customerDeleter.delete(selectedCustomer);
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
		Customer[] customers = new Customer[] { customer0, customer1,
				customer2, customer4 };

		// make 4 customers
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		for (Customer customer : customers) {
			searchUpdateCustomer(customer, "test-injection-for-ragsoc");
		}
		criteria.setQuery("test-injection-for-ragsoc");
		customerSearch.find();
		assertEquals(customerSearch.getCustomers().size(), 0);
	}

	protected void searchUpdateCustomer(Customer customer, String newRagSoc) {
		criteria.setQuery(customer.getName());
		customerSearch.find();
		customerSelection.selectCustomer(customerSearch.getCustomers().get(0));
		Customer selectedCustomer = customerSelection.getSelectedCustomer();
		// customer page
		selectedCustomer.setRagSocial(newRagSoc);
		customerUpdater.update();
		// main page
		String message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
		// customer page
		selectedCustomer.setRagSocial(customer.getRagSocial());
		customerUpdater.update();
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
