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
package it.vige.magazzino.ftest;

import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;
import static org.jboss.test.selenium.locator.LocatorFactory.jq;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.test.mock.CustomerMock;
import it.vige.magazzino.test.operation.CustomerOperation;

import org.jboss.test.selenium.locator.JQueryLocator;
import org.jboss.test.selenium.locator.option.OptionValueLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class tests customers functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class CustomerTest extends AbstractTest implements CustomerMock {

	public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search/search_customer']");
	public static final JQueryLocator MENU_INSERT = jq("[href^='/magazzino/customer']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("[id='customerSelectionForm:noCustomerMsg']");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='customerSelectionForm:customers:0:view']");
	public static final JQueryLocator BUTTON_UPDATE_PROCEED = jq("[id='customerUpdater']");
	public static final JQueryLocator BUTTON_INSERT_PROCEED = jq("[id='customerRegister']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='cancel']");

	public static final JQueryLocator COUNT_CUSTOMERS = jq("[id='customerSelectionForm:customers'] tbody tr");

	public static final JQueryLocator CUSTOMERS_TABLE_FIRST_ROW_NAME = jq("table[id='customerSelectionForm:customers'] tbody tr:first td:first");
	public static final JQueryLocator CUSTOMERS_TABLE_FIRST_ROW_DELETE = jq("[id='customerSelectionForm:customers:0:delete']");
	public static final JQueryLocator CUSTOMERS_MESSAGE = jq("[id='messages'] li");
	public static final JQueryLocator CUSTOMERS_MESSAGE1 = jq("[id='code:message1']");
	public static final JQueryLocator CUSTOMERS_MESSAGE2 = jq("[id='name:message1']");

	public static final JQueryLocator DETAILS_RAG_SOC = jq("[id='ragSocial:input']");
	public static final JQueryLocator DETAILS_CODE = jq("[id='code:input']");
	public static final JQueryLocator DETAILS_P_IVA = jq("[id='iva:input']");
	public static final JQueryLocator DETAILS_NAME = jq("[id='name:input']");
	public static final JQueryLocator DETAILS_STREET = jq("[id='street:input']");

	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

	@BeforeMethod
	public void setUp() {
		selenium.open(contextPath);
		selenium.waitForPageToLoad();

	}

	/**
	 * Tests the customers search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("cliente");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(5, selenium.getCount(COUNT_CUSTOMERS));

		enterSearchQuery("nonExistingCustomer");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_CUSTOMERS));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		selenium.type(SEARCH_QUERY, "rag soc");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			if (customers.length > pageSize)
				assertEquals(selenium.getCount(COUNT_CUSTOMERS), pageSize);
			else
				assertEquals(selenium.getCount(COUNT_CUSTOMERS),
						customers.length);

		}
	}

	/**
	 * Simply follows the customer wizard without changing anything.
	 */
	@Test
	public void testSimpleCustomer() {
		Customer customer = new Customer();
		customer.setName("cliente");
		customer.setCode("19987");
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		int customersCount = selenium.getCount(COUNT_CUSTOMERS);
		searchUpdateCustomer(customer, "rag soc");
		assertEquals(++customersCount, selenium.getCount(COUNT_CUSTOMERS));
	}

	@Test
	public void testInsertDeleteNewCustomer() {
		CustomerOperation customerOperation = new CustomerOperation();
		selenium.click(MENU_INSERT);
		selenium.waitForPageToLoad();
		String customerName = "newName";
		String pIva1 = "0123456789012347";
		String ragSoc1 = "new rag soc for customer test";
		Address address = new Address();
		address.setAddress("Vige street");
		Customer customer = customerOperation.create("99999999", customerName,
				ragSoc1, pIva1, address, null);
		populateCustomerFields(customer);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message = selenium.getText(CUSTOMERS_MESSAGE);
		assertTrue(message, message.contains(customer.getCode()));
		// cancel customer
		selenium.click(BUTTON_CANCEL);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message1 = selenium.getText(CUSTOMERS_MESSAGE1);
		assertTrue(message1, message1.contains(customer.getCode()));
		customer.setName("");
		customer.setCode("99999991");
		populateCustomerFields(customer);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message2 = selenium.getText(CUSTOMERS_MESSAGE2);
		assertFalse(message2, message2.contains(customer.getCode()));
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery(customerName);
		selenium.click(CUSTOMERS_TABLE_FIRST_ROW_DELETE);
		selenium.waitForPageToLoad();
		message = selenium.getText(CUSTOMERS_MESSAGE);
		assertTrue(message, message.contains("99999999"));
	}

	@Test
	public void testMultiSearchingUpdate() {
		Customer[] customers = new Customer[] { customer0, customer1,
				customer2, customer4 };
		int customersCount = selenium.getCount(COUNT_CUSTOMERS);

		// make 4 customers
		for (Customer customer : customers) {
			searchUpdateCustomer(customer, "test-selenium-for-ragsoc");
		}

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("test-selenium-for-ragsoc");
		assertEquals(customersCount, selenium.getCount(COUNT_CUSTOMERS));
	}

	protected void searchUpdateCustomer(Customer customer, String newRagSoc) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(customer.getName());
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// customer page
		populateCustomerFields(newRagSoc);
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
		// customer page
		populateCustomerFields(customer.getRagSocial());
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
	}

	protected void populateCustomerFields(String ragSoc) {
		selenium.type(DETAILS_RAG_SOC, ragSoc);
	}

	protected void populateCustomerFields(String ragSoc, String pIva) {
		populateCustomerFields(ragSoc);
		selenium.type(DETAILS_P_IVA, pIva);
	}

	protected void populateCustomerFields(Customer customer) {
		populateCustomerFields(customer.getRagSocial(), customer.getIva());
		selenium.type(DETAILS_CODE, customer.getCode());
		selenium.type(DETAILS_NAME, customer.getName());
		selenium.type(DETAILS_STREET, customer.getAddress().getAddress());
	}
}
