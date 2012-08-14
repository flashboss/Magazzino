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
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.test.mock.CustomerMock;

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
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("#noCustomersMsg");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='customerSelectionForm:customers:0:view']");
	public static final JQueryLocator BUTTON_PROCEED = jq("[id='customerUpdater']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='confirmForm:cancel']");

	public static final JQueryLocator COUNT_CUSTOMER = jq("[id='customerSelectionForm:customers'] tbody tr");
	public static final JQueryLocator COUNT_CUSTOMERS = jq("[id='customers:customers'] tbody tr");

	public static final JQueryLocator CUSTOMERS_TABLE_FIRST_ROW_NAME = jq("table[id='customers:customers'] tbody tr:first td:first");
	public static final JQueryLocator CUSTOMERS_TABLE_FIRST_ROW_LINK = jq("[id='customers:customers:0:cancel']");
	public static final JQueryLocator CUSTOMERS_CANCEL_MESSAGE = jq("[id='messages'] li");

	public static final JQueryLocator DETAILS_RAG_SOC = jq("[id='ragSocial:input']");
	public static final JQueryLocator DETAILS_CODE = jq("[id='code:input']");
	public static final JQueryLocator DETAILS_P_IVA = jq("[id='iva:input']");
	public static final JQueryLocator DETAILS_NAME = jq("[id='name:input:1']");

	public static final JQueryLocator CONFIRM_TEXT = jq("[id='content']");

	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

	@BeforeMethod
	public void setUp() {
		selenium.open(contextPath);
		selenium.waitForPageToLoad();
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();

	}

	/**
	 * Tests the customers search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		enterSearchQuery("cliente");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(5, selenium.getCount(COUNT_CUSTOMER));

		enterSearchQuery("nonExistingCustomer");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_CUSTOMER));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.type(SEARCH_QUERY, "rag soc");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			assertEquals(selenium.getCount(COUNT_CUSTOMER), pageSize);
		}
	}

	/**
	 * Simply follows the customer wizard without changing anything.
	 */
	@Test
	public void testSimpleCustomer() {
		String customerName = "cliente";
		String customerCode = "19987";
		int customersCount = selenium.getCount(COUNT_CUSTOMERS);
		searchCustomer(customerName, "rag soc", customerCode);
		assertEquals(++customersCount, selenium.getCount(COUNT_CUSTOMERS));
	}

	/**
	 * Tests "revise" and "cancel" buttons as well as that changed credit card
	 * details are propagated across the customers wizard.
	 */
	@Test
	public void testMoreSophisticatedCustomer() {
		String customerName = customer3.getName();
		String pIva1 = "0123456789012347";
		String ragSoc1 = "new rag soc for customer test";
		String pIva2 = "6432109876543210";
		String ragSoc2 = "other rag soc for customer test";
		int customersCount = selenium.getCount(COUNT_CUSTOMERS);

		enterSearchQuery(customerName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// customers detail page
		populateCustomerFields(ragSoc1, pIva1);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(ragSoc1));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(pIva1));
		// back to customer page
		populateCustomerFields(ragSoc2, pIva2);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(ragSoc2));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(pIva2));
		// cancel customer
		selenium.click(BUTTON_CANCEL);
		selenium.waitForPageToLoad();
		// check that the customer count remains unchanged
		assertEquals(customersCount, selenium.getCount(COUNT_CUSTOMERS));
	}

	@Test
	public void testCustomerCanceling() {
		Customer[] customers = new Customer[] { customer0, customer1,
				customer2, customer4 };
		int customersCount = selenium.getCount(COUNT_CUSTOMERS);

		// make 3 customers
		for (Customer customer : customers) {
			searchCustomer(customer.getName(), "rag soc 2", customer.getCode());
		}

		customersCount += customers.length;
		assertEquals(customersCount, selenium.getCount(COUNT_CUSTOMERS));

		for (int i = 0; i < customers.length; i++) {
			String customerName = selenium.getText(
					CUSTOMERS_TABLE_FIRST_ROW_NAME).trim();
			selenium.click(CUSTOMERS_TABLE_FIRST_ROW_LINK);
			selenium.waitForPageToLoad();
			String message = selenium.getText(CUSTOMERS_CANCEL_MESSAGE);
			assertTrue("Unexpected canceling message: " + message,
					message.matches("prova"));
			assertTrue("Unexpected customer name.",
					message.contains(customerName));
			assertEquals("Unexpected number of customers", --customersCount,
					selenium.getCount(COUNT_CUSTOMERS));
		}
	}

	protected void searchCustomer(String customerName, String ragSoc,
			String customerCode) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(customerName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// customer page
		populateCustomerFields(ragSoc);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.", selenium.isTextPresent(customerCode));
	}

	protected void populateCustomerFields(String ragSoc) {
		selenium.type(DETAILS_RAG_SOC, ragSoc);
	}

	protected void populateCustomerFields(String ragSoc, String pIva) {
		populateCustomerFields(ragSoc);
		selenium.type(DETAILS_P_IVA, pIva);
	}
}
