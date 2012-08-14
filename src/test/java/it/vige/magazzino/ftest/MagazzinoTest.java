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
 * This class tests jars functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class MagazzinoTest extends AbstractTest {

	public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search/search_magazzino']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("#noJarsMsg");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='jarSelectionForm:jars:0:view']");
	public static final JQueryLocator BUTTON_PROCEED = jq("[id='magazzinoForm:proceed']");
	public static final JQueryLocator BUTTON_CONFIRM = jq("[id='confirmForm:confirm']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='confirmForm:cancel']");
	public static final JQueryLocator BUTTON_REVISE = jq("[id='confirmForm:revise']");
	public static final String MAGAZZINO_MESSAGE = "The magazzino is";
	public static final String CANCEL_MESSAGE = "has been canceled.";

	public static final JQueryLocator COUNT_MAGAZZINO = jq("[id='jarSelectionForm:jars'] tbody tr");
	public static final JQueryLocator COUNT_JARS = jq("[id='jars:jars'] tbody tr");

	public static final JQueryLocator JARS_TABLE_FIRST_ROW_NAME = jq("table[id='jars:jars'] tbody tr:first td:first");
	public static final JQueryLocator JARS_TABLE_FIRST_ROW_LINK = jq("[id='jars:jars:0:cancel']");
	public static final JQueryLocator JARS_CANCEL_MESSAGE = jq("[id='messages'] li");
	public static final String JARS_CANCEL_MESSAGE_TEXT = "Your magazzino at the .+? on .+? has been canceled\\.";

	public static final JQueryLocator DETAILS_CARD_TYPE = jq("[id='magazzinoForm:creditCardType:type']");
	public static final JQueryLocator DETAILS_CARD_NUMBER = jq("[id='magazzinoForm:creditCardNumber:input']");
	public static final JQueryLocator DETAILS_SMOKING = jq("[id='magazzinoForm:smokingPreference:input:0']");
	public static final JQueryLocator DETAILS_NONSMOKING = jq("[id='magazzinoForm:smokingPreference:input:1']");

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
	 * Tests the jars search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		enterSearchQuery("Marriott");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(2, selenium.getCount(COUNT_MAGAZZINO));

		enterSearchQuery("nonExistingMagazzino");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_MAGAZZINO));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.type(SEARCH_QUERY, "a");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			assertEquals(selenium.getCount(COUNT_MAGAZZINO), pageSize);
		}
	}

	/**
	 * Simply follows the magazzino wizard without changing anything.
	 */
	@Test
	public void testSimpleMagazzino() {
		String magazzinoName = "Grand Hyatt";
		int jarsCount = selenium.getCount(COUNT_JARS);
		searchMagazzino(magazzinoName, CreditCardType.VISA);
		assertEquals(++jarsCount, selenium.getCount(COUNT_JARS));
	}

	/**
	 * Tests "revise" and "cancel" buttons as well as that changed credit card
	 * details are propagated across the jars wizard.
	 */
	@Test
	public void testMoreSophisticatedMagazzino() {
		String magazzinoName = "Conrad Miami";
		String creditCardNumber1 = "0123456789012347";
		CreditCardType creditCardType1 = CreditCardType.DISCOVER;
		String creditCardNumber2 = "6432109876543210";
		CreditCardType creditCardType2 = CreditCardType.AMEX;
		int jarsCount = selenium.getCount(COUNT_JARS);

		enterSearchQuery(magazzinoName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// jars detail page
		populateMagazzinoFields(creditCardNumber1, creditCardType1);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber1));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(
				creditCardType1.getName()));
		selenium.click(BUTTON_REVISE);
		selenium.waitForPageToLoad();
		// back to magazzino page
		populateMagazzinoFields(creditCardNumber2, creditCardType2);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirmation page
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber2));
		assertTrue(selenium.getText(CONFIRM_TEXT).contains(
				creditCardType2.getName()));
		// cancel magazzino
		selenium.click(BUTTON_CANCEL);
		selenium.waitForPageToLoad();
		// check that the magazzino count remains unchanged
		assertEquals(jarsCount, selenium.getCount(COUNT_JARS));
	}

	@Test
	public void testMagazzinoCanceling() {
		String[] magazzinoNames = new String[] { "Hilton Diagonal Mar",
				"Parc 55", "Ritz-Carlton Montreal", "Parc 55" };
		int jarsCount = selenium.getCount(COUNT_JARS);

		// make 3 jars
		for (String magazzinoName : magazzinoNames) {
			searchMagazzino(magazzinoName, CreditCardType.VISA);
		}

		jarsCount += magazzinoNames.length;
		assertEquals(jarsCount, selenium.getCount(COUNT_JARS));

		for (int i = 0; i < magazzinoNames.length; i++) {
			String magazzinoName = selenium
					.getText(JARS_TABLE_FIRST_ROW_NAME).trim();
			selenium.click(JARS_TABLE_FIRST_ROW_LINK);
			selenium.waitForPageToLoad();
			String message = selenium.getText(JARS_CANCEL_MESSAGE);
			assertTrue("Unexpected canceling message: " + message,
					message.matches(JARS_CANCEL_MESSAGE_TEXT));
			assertTrue("Unexpected magazzino name.",
					message.contains(magazzinoName));
			assertEquals("Unexpected number of jars", --jarsCount,
					selenium.getCount(COUNT_JARS));
		}
	}

	protected void searchMagazzino(String magazzinoName,
			CreditCardType creditCardType) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(magazzinoName);
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// magazzino page
		populateMagazzinoFields(creditCardType);
		selenium.click(BUTTON_PROCEED);
		selenium.waitForPageToLoad();
		// confirm page
		selenium.click(BUTTON_CONFIRM);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Jars failed.", selenium.isTextPresent(MAGAZZINO_MESSAGE));
	}

	protected void populateMagazzinoFields(CreditCardType creditCardType) {
		selenium.select(DETAILS_CARD_TYPE, creditCardType.getLocator());
	}

	protected void populateMagazzinoFields(String creditCardNumber,
			CreditCardType creditCardType) {
		selenium.type(DETAILS_CARD_NUMBER, creditCardNumber);
		populateMagazzinoFields(creditCardType);
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
