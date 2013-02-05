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

import static it.vige.magazzino.test.mock.MagazzinoMock.jars;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino0;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino1;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino2;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino4;
import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;
import static org.jboss.test.selenium.locator.LocatorFactory.jq;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.test.operation.MagazzinoOperation;

import org.jboss.test.selenium.locator.JQueryLocator;
import org.jboss.test.selenium.locator.option.OptionValueLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class tests jars functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class MagazzinoTest extends AbstractTest {

	public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search/search_magazzino']");
	public static final JQueryLocator MENU_INSERT = jq("[href^='/magazzino/magazzino']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("[id='jarSelectionForm:noJarMsg']");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='jarSelectionForm:jars:0:view']");
	public static final JQueryLocator BUTTON_UPDATE_PROCEED = jq("[id='magazzinoUpdater']");
	public static final JQueryLocator BUTTON_INSERT_PROCEED = jq("[id='magazzinoRegister']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='cancel']");

	public static final JQueryLocator COUNT_JARS = jq("[id='jarSelectionForm:jars'] tbody tr");

	public static final JQueryLocator JARS_TABLE_FIRST_ROW_NAME = jq("table[id='jarSelectionForm:jars'] tbody tr:first td:first");
	public static final JQueryLocator JARS_TABLE_FIRST_ROW_DELETE = jq("[id='jarSelectionForm:jars:0:delete']");
	public static final JQueryLocator JARS_MESSAGE = jq("[id='messages'] li");
	public static final JQueryLocator JARS_MESSAGE1 = jq("[id='number:message1']");
	public static final JQueryLocator JARS_MESSAGE2 = jq("[id='ragSoc2:message1']");

	public static final JQueryLocator DETAILS_RAG_SOC1 = jq("[id='ragSoc1:input']");
	public static final JQueryLocator DETAILS_RAG_SOC2 = jq("[id='ragSoc2:input']");
	public static final JQueryLocator DETAILS_NUMBER = jq("[id='number:input']");
	public static final JQueryLocator DETAILS_DATE = jq("[id='date:input']");
	public static final JQueryLocator DETAILS_CODE = jq("[id='code:input']");
	public static final JQueryLocator DETAILS_CAUSE = jq("[id='cause:input']");
	public static final JQueryLocator DETAILS_COMPENSATION = jq("[id='compensation:input']");
	public static final JQueryLocator DETAILS_COD_CUSTOMER = jq("[id='codCustomer:input']");
	public static final JQueryLocator DETAILS_NUMBER_DOC = jq("[id='numberDoc:input']");
	public static final JQueryLocator DETAILS_DATE_DOC = jq("[id='dateDoc:input']");
	public static final JQueryLocator DETAILS_IVA = jq("[id='iva:input']");
	public static final JQueryLocator DETAILS_CAP_SOC = jq("[id='capSoc:input']");
	public static final JQueryLocator DETAILS_REAPI = jq("[id='reaPI:input']");

	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

	@BeforeMethod
	public void setUp() {
		selenium.open(contextPath);
		selenium.waitForPageToLoad();

	}

	/**
	 * Tests the jars search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("rag soc");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(5, selenium.getCount(COUNT_JARS));

		enterSearchQuery("nonExistingMagazzino");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_JARS));
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (jars.length > pageSize)
				assertEquals(selenium.getCount(COUNT_JARS), pageSize);
			else
				assertEquals(selenium.getCount(COUNT_JARS), jars.length);

		}
	}

	@Test
	public void testInsertDeleteNewMagazzino() {
		MagazzinoOperation magazzinoOperation = new MagazzinoOperation();
		selenium.click(MENU_INSERT);
		selenium.waitForPageToLoad();
		String magazzinoCompensation = "newCompensation";
		String ragSoc1 = "new rag soc for magazzino test";
		String ragSoc2 = "0123456789012347";
		Magazzino magazzino = magazzinoOperation.create("99999999", "", "", "",
				magazzinoCompensation, "", ragSoc1, ragSoc2, "", "", "", "",
				"", null, null);
		populateMagazzinoFields(magazzino);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message = selenium.getText(JARS_MESSAGE);
		assertTrue(message, message.contains(magazzino.getCodeJar()));
		// cancel magazzino
		selenium.click(BUTTON_CANCEL);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message1 = selenium.getText(JARS_MESSAGE1);
		assertTrue(message1, message1.contains(magazzino.getCodeJar()));
		magazzino.setRagSoc2("");
		magazzino.setCodeJar("99999991");
		populateMagazzinoFields(magazzino);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message2 = selenium.getText(JARS_MESSAGE2);
		assertFalse(message2, message2.contains(magazzino.getCodeJar()));
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery(magazzinoCompensation);
		selenium.click(JARS_TABLE_FIRST_ROW_DELETE);
		selenium.waitForPageToLoad();
		message = selenium.getText(JARS_MESSAGE);
		assertTrue(message, message.contains("99999999"));
	}

	@Test
	public void testMultiSearchingUpdate() {
		Magazzino[] jars = new Magazzino[] { magazzino0, magazzino1,
				magazzino2, magazzino4 };
		int jarsCount = selenium.getCount(COUNT_JARS);

		// make 4 jars
		for (Magazzino magazzino : jars) {
			searchUpdateMagazzino(magazzino, "test-selenium-for-ragsoc");
		}

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("test-selenium-for-ragsoc");
		assertEquals(jarsCount, selenium.getCount(COUNT_JARS));
	}

	protected void searchUpdateMagazzino(Magazzino magazzino, String newCause) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(magazzino.getCompensation());
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// magazzino page
		populateMagazzinoFields(newCause);
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
		// magazzino page
		populateMagazzinoFields(magazzino.getCause());
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
	}

	protected void populateMagazzinoFields(String ragSoc1) {
		selenium.type(DETAILS_RAG_SOC1, ragSoc1);
	}

	protected void populateMagazzinoFields(String ragSoc1, String ragSoc2) {
		populateMagazzinoFields(ragSoc1);
		selenium.type(DETAILS_RAG_SOC2, ragSoc2);
	}

	protected void populateMagazzinoFields(Magazzino magazzino) {
		populateMagazzinoFields(magazzino.getRagSoc1(), magazzino.getRagSoc2());
		selenium.type(DETAILS_NUMBER, magazzino.getCodeJar());
		selenium.type(DETAILS_CODE, magazzino.getCodeJar());
		selenium.type(DETAILS_COMPENSATION, magazzino.getCompensation());
	}
}
