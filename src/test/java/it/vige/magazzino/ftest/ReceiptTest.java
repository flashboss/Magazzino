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
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.test.mock.ReceiptMock;
import it.vige.magazzino.test.operation.ReceiptOperation;

import org.jboss.test.selenium.locator.JQueryLocator;
import org.jboss.test.selenium.locator.option.OptionLocator;
import org.jboss.test.selenium.locator.option.OptionValueLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class tests receipts functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ReceiptTest extends AbstractTest implements ReceiptMock {

	public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search/search_receipt']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("[id='receiptSelectionForm:noReceiptMsg']");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='receiptSelectionForm:receipts:0:view']");
	public static final JQueryLocator BUTTON_UPDATE_PROCEED = jq("[id='receiptUpdater']");
	public static final JQueryLocator BUTTON_INSERT_PROCEED = jq("[id='receiptRegister']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='cancel']");

	public static final JQueryLocator COUNT_RECEIPTS = jq("[id='receiptSelectionForm:receipts'] tbody tr");

	public static final JQueryLocator RECEIPTS_TABLE_FIRST_ROW_NAME = jq("table[id='receiptSelectionForm:receipts'] tbody tr:first td:first");
	public static final JQueryLocator RECEIPTS_TABLE_FIRST_ROW_DELETE = jq("[id='receiptSelectionForm:receipts:0:delete']");
	public static final JQueryLocator RECEIPTS_MESSAGE = jq("[id='messages'] li");
	public static final JQueryLocator RECEIPTS_MESSAGE1 = jq("[id='number:message1']");
	public static final JQueryLocator RECEIPTS_MESSAGE2 = jq("[id='cause:message1']");

	public static final JQueryLocator DETAILS_NUMBER = jq("[id='number:input']");
	public static final JQueryLocator DETAILS_DATE = jq("[id='date:input']");
	public static final JQueryLocator DETAILS_CAUSE = jq("[id='cause:input']");
	public static final JQueryLocator DETAILS_DESCRIPTION = jq("[id='description:input']");
	public static final JQueryLocator DETAILS_JAR = jq("[id='jar:select']");
	public static final JQueryLocator DETAILS_CUSTOMER = jq("[id='customer:select']");

	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

	@BeforeMethod
	public void setUp() {
		selenium.open(contextPath);
		selenium.waitForPageToLoad();

	}

	/**
	 * Tests the receipts search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("causale");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(5, selenium.getCount(COUNT_RECEIPTS));

		enterSearchQuery("nonExistingReceipt");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_RECEIPTS));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		selenium.type(SEARCH_QUERY, "causale");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (receipts.length > pageSize)
				assertEquals(selenium.getCount(COUNT_RECEIPTS), pageSize);
			else
				assertEquals(selenium.getCount(COUNT_RECEIPTS), receipts.length);

		}
	}

	@Test
	public void testInsertDeleteNewReceipt() {
		ReceiptOperation receiptOperation = new ReceiptOperation();
		String date = "newName";
		String description = "new description";
		String cause = "new rag soc for receipt test";
		Receipt receipt = receiptOperation.create("99999999", date,
				cause, description, null, null);
		populateReceiptFields(receipt);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message = selenium.getText(RECEIPTS_MESSAGE);
		assertTrue(message, message.contains(receipt.getNumber()));
		// cancel receipt
		selenium.click(BUTTON_CANCEL);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message1 = selenium.getText(RECEIPTS_MESSAGE1);
		assertTrue(message1, message1.contains(receipt.getNumber()));
		receipt.setCause("");
		receipt.setNumber("99999991");
		populateReceiptFields(receipt);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message2 = selenium.getText(RECEIPTS_MESSAGE2);
		assertFalse(message2, message2.contains(receipt.getNumber()));
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery(description);
		selenium.click(RECEIPTS_TABLE_FIRST_ROW_DELETE);
		selenium.waitForPageToLoad();
		message = selenium.getText(RECEIPTS_MESSAGE);
		assertTrue(message, message.contains("99999999"));
	}

	@Test
	public void testMultiSearchingUpdate() {
		Receipt[] receipts = new Receipt[] { receipt0, receipt1, receipt2,
				receipt4 };
		int receiptsCount = selenium.getCount(COUNT_RECEIPTS);

		// make 4 receipts
		for (Receipt receipt : receipts) {
			searchUpdateReceipt(receipt, "test-selenium-for-ragsoc");
		}

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("test-selenium-for-ragsoc");
		assertEquals(receiptsCount, selenium.getCount(COUNT_RECEIPTS));
	}

	protected void searchUpdateReceipt(Receipt receipt, String newCause) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(receipt.getDescription());
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// receipt page
		populateReceiptFields(newCause);
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
		// receipt page
		populateReceiptFields(receipt.getCause());
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
	}

	protected void populateReceiptFields(String cause) {
		selenium.type(DETAILS_CAUSE, cause);
	}

	protected void populateReceiptFields(String cause, String description) {
		populateReceiptFields(cause);
		selenium.type(DETAILS_DESCRIPTION, description);
	}

	protected void populateReceiptFields(Receipt receipt) {
		populateReceiptFields(receipt.getCause(), receipt.getDescription());
		selenium.type(DETAILS_NUMBER, receipt.getNumber());
		selenium.select(DETAILS_JAR, Jar.JAR3.getLocator());
		selenium.select(DETAILS_CUSTOMER, Customer.CLIENTE4.getLocator());
	}

	private enum Jar {
		JAR1(magazzino1.getNumber()), JAR2(magazzino2.getNumber()), JAR3(
				magazzino3.getNumber()), JAR4(magazzino4.getNumber());

		private String name;

		private Jar(String name) {
			this.name = name;
		}

		public OptionLocator<?> getLocator() {
			return new OptionValueLocator(name);
		}
	}

	private enum Customer {
		CLIENTE1(customer1.getCode()), CLIENTE2(customer2.getCode()), CLIENTE3(
				customer3.getCode()), CLIENTE4(customer4.getCode());

		private String name;

		private Customer(String name) {
			this.name = name;
		}

		public OptionLocator<?> getLocator() {
			return new OptionValueLocator(name);
		}
	}
}
