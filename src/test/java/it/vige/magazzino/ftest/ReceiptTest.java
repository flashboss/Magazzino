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

import org.jboss.test.selenium.locator.JQueryLocator;
import org.jboss.test.selenium.locator.option.OptionLocator;
import org.jboss.test.selenium.locator.option.OptionValueLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;
import static org.jboss.test.selenium.locator.LocatorFactory.jq;
import static org.testng.AssertJUnit.*;

/**
 * This class tests receipts functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ReceiptTest extends AbstractTest {

	public static final JQueryLocator MENU_FIND = jq("[href^='//magazzino/search/search_receipt']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("#noReceiptsMsg");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='receiptSelectionForm:receipts:0:view']");
	public static final JQueryLocator BUTTON_PROCEED = jq("[id='receiptForm:proceed']");
	public static final JQueryLocator BUTTON_CONFIRM = jq("[id='confirmForm:confirm']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='confirmForm:cancel']");
	public static final JQueryLocator BUTTON_REVISE = jq("[id='confirmForm:revise']");
	public static final String RECEIPT_MESSAGE = "The receipt is";
	public static final String CANCEL_MESSAGE = "has been canceled.";

	public static final JQueryLocator COUNT_RECEIPT = jq("[id='receiptSelectionForm:receipts'] tbody tr");
	public static final JQueryLocator COUNT_RECEIPTS = jq("[id='receipts:receipts'] tbody tr");

	public static final JQueryLocator RECEIPTS_TABLE_FIRST_ROW_NAME = jq("table[id='receipts:receipts'] tbody tr:first td:first");
	public static final JQueryLocator RECEIPTS_TABLE_FIRST_ROW_LINK = jq("[id='receipts:receipts:0:cancel']");
	public static final JQueryLocator RECEIPTS_CANCEL_MESSAGE = jq("[id='messages'] li");
	public static final String RECEIPTS_CANCEL_MESSAGE_TEXT = "Your receipt at the .+? on .+? has been canceled\\.";

	public static final JQueryLocator DETAILS_CARD_TYPE = jq("[id='receiptForm:creditCardType:type']");
	public static final JQueryLocator DETAILS_CARD_NUMBER = jq("[id='receiptForm:creditCardNumber:input']");
	public static final JQueryLocator DETAILS_SMOKING = jq("[id='receiptForm:smokingPreference:input:0']");
	public static final JQueryLocator DETAILS_NONSMOKING = jq("[id='receiptForm:smokingPreference:input:1']");

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
	 * Tests the receipts search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		enterSearchQuery("Marriott");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(2, selenium.getCount(COUNT_RECEIPT));

		enterSearchQuery("nonExistingReceipt");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_RECEIPT));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.type(SEARCH_QUERY, "a");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			assertEquals(selenium.getCount(COUNT_RECEIPT), pageSize);
		}
	}

	/**
	 * Simply follows the receipt wizard without changing anything.
	 */
	@Test
	public void testSimpleReceipt() {
		String receiptName = "Grand Hyatt";
		int receiptsCount = selenium.getCount(COUNT_RECEIPTS);
		searchReceipt(receiptName, CreditCardType.VISA);
		assertEquals(++receiptsCount, selenium.getCount(COUNT_RECEIPTS));
	}

	/**
	 * Tests "revise" and "cancel" buttons as well as that changed credit card
	 * details are propagated across the receipts wizard.
	 */
	@Test
	public void testMoreSophisticatedReceipt() {
		String receiptName = "Conrad Miami";
		String creditCardNumber1 = "0123456789012347";
		CreditCardType creditCardType1 = CreditCardType.DISCOVER;
		String creditCardNumber2 = "6432109876543210";
		CreditCardType creditCardType2 = CreditCardType.AMEX;
		int receiptsCount = selenium.getCount(COUNT_RECEIPTS);

		enterSearchQuery(receiptName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// receipts detail page
		populateReceiptFields(creditCardNumber1, creditCardType1);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber1));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(
				creditCardType1.getName()));
		selenium.click(BUTTON_REVISE);
		selenium.waitForPageToLoad();
		// back to receipt page
		populateReceiptFields(creditCardNumber2, creditCardType2);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber2));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(
				creditCardType2.getName()));
		// cancel receipt
		selenium.click(BUTTON_CANCEL);
		selenium.waitForPageToLoad();
		// check that the receipt count remains unchanged
		assertEquals(receiptsCount, selenium.getCount(COUNT_RECEIPTS));
	}

	@Test
	public void testReceiptCanceling() {
		String[] receiptNames = new String[] { "Hilton Diagonal Mar",
				"Parc 55", "Ritz-Carlton Montreal", "Parc 55" };
		int receiptsCount = selenium.getCount(COUNT_RECEIPTS);

		// make 3 receipts
		for (String receiptName : receiptNames) {
			searchReceipt(receiptName, CreditCardType.VISA);
		}

		receiptsCount += receiptNames.length;
		assertEquals(receiptsCount, selenium.getCount(COUNT_RECEIPTS));

		for (int i = 0; i < receiptNames.length; i++) {
			String receiptName = selenium
					.getText(RECEIPTS_TABLE_FIRST_ROW_NAME).trim();
			selenium.click(RECEIPTS_TABLE_FIRST_ROW_LINK);
			selenium.waitForPageToLoad();
			String message = selenium.getText(RECEIPTS_CANCEL_MESSAGE);
			assertTrue("Unexpected canceling message: " + message,
					message.matches(RECEIPTS_CANCEL_MESSAGE_TEXT));
			assertTrue("Unexpected receipt name.",
					message.contains(receiptName));
			assertEquals("Unexpected number of receipts", --receiptsCount,
					selenium.getCount(COUNT_RECEIPTS));
		}
	}

	protected void searchReceipt(String receiptName,
			CreditCardType creditCardType) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(receiptName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// receipt page
		populateReceiptFields(creditCardType);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirm page
		selenium.click(BUTTON_CONFIRM);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Receipts failed.", selenium.isTextPresent(RECEIPT_MESSAGE));
	}

	protected void populateReceiptFields(CreditCardType creditCardType) {
		selenium.select(DETAILS_CARD_TYPE, creditCardType.getLocator());
	}

	protected void populateReceiptFields(String creditCardNumber,
			CreditCardType creditCardType) {
		selenium.type(DETAILS_CARD_NUMBER, creditCardNumber);
		populateReceiptFields(creditCardType);
	}

	private enum CreditCardType {
		VISA("VISA"), MASTERCARD("MasterCard"), AMEX("AMEX"), DISCOVER(
				"Discover");

		private String name;

		private CreditCardType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public OptionLocator<?> getLocator() {
			return new OptionValueLocator(name);
		}
	}
}
