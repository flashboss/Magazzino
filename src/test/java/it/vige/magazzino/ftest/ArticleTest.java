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
import it.vige.magazzino.model.Article;
import it.vige.magazzino.test.mock.ArticleMock;
import it.vige.magazzino.test.operation.ArticleOperation;

import org.jboss.test.selenium.locator.JQueryLocator;
import org.jboss.test.selenium.locator.option.OptionValueLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class tests articles functionality of the example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ArticleTest extends AbstractTest implements ArticleMock {

	public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search/search_article']");
	public static final JQueryLocator MENU_INSERT = jq("[href^='/magazzino/article']");
	public static final JQueryLocator SEARCH_NO_RESULTS = jq("[id='articleSelectionForm:noArticleMsg']");
	public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='articleSelectionForm:articles:0:view']");
	public static final JQueryLocator BUTTON_UPDATE_PROCEED = jq("[id='articleUpdater']");
	public static final JQueryLocator BUTTON_INSERT_PROCEED = jq("[id='articleRegister']");
	public static final JQueryLocator BUTTON_CANCEL = jq("[id='cancel']");

	public static final JQueryLocator COUNT_ARTICLES = jq("[id='articleSelectionForm:articles'] tbody tr");

	public static final JQueryLocator ARTICLES_TABLE_FIRST_ROW_NAME = jq("table[id='articleSelectionForm:articles'] tbody tr:first td:first");
	public static final JQueryLocator ARTICLES_TABLE_FIRST_ROW_DELETE = jq("[id='articleSelectionForm:articles:0:delete']");
	public static final JQueryLocator ARTICLES_MESSAGE = jq("[id='messages'] li");
	public static final JQueryLocator ARTICLES_MESSAGE1 = jq("[id='code:message1']");
	public static final JQueryLocator ARTICLES_MESSAGE2 = jq("[id='rate:message1']");

	public static final JQueryLocator DETAILS_BAR_CODE = jq("[id='barCode:input']");
	public static final JQueryLocator DETAILS_CODE = jq("[id='code:input']");
	public static final JQueryLocator DETAILS_DESCRIPTION = jq("[id='description:input']");
	public static final JQueryLocator DETAILS_UM = jq("[id='um:input']");
	public static final JQueryLocator DETAILS_MIS = jq("[id='mis:input']");
	public static final JQueryLocator DETAILS_CAT_MERC = jq("[id='catMerc:input']");
	public static final JQueryLocator DETAILS_IMPONIBLE = jq("[id='imponible:input']");
	public static final JQueryLocator DETAILS_PRIZE = jq("[id='prize:input']");
	public static final JQueryLocator DETAILS_COST = jq("[id='cost:input']");
	public static final JQueryLocator DETAILS_PROVIDER = jq("[id='provider:input']");
	public static final JQueryLocator DETAILS_RATE = jq("[id='rate:input']");
	public static final JQueryLocator DETAILS_CA = jq("[id='ca:input']");
	public static final JQueryLocator DETAILS_SC1 = jq("[id='sc1:input']");
	public static final JQueryLocator DETAILS_SC2 = jq("[id='sc2:input']");
	public static final JQueryLocator DETAILS_SC3 = jq("[id='sc3:input']");
	public static final JQueryLocator DETAILS_QTMIN = jq("[id='qtmin:input']");
	public static final JQueryLocator DETAILS_QTMAX = jq("[id='qtmax:input']");
	public static final JQueryLocator DETAILS_PACK = jq("[id='pack:input']");
	public static final JQueryLocator DETAILS_HEALTH = jq("[id='health:input']");
	public static final JQueryLocator DETAILS_VOLUME = jq("[id='volume:input']");
	public static final JQueryLocator DETAILS_MAKING = jq("[id='making:input']");
	public static final JQueryLocator DETAILS_NOTES = jq("[id='notes:input']");

	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

	@BeforeMethod
	public void setUp() {
		selenium.open(contextPath);
		selenium.waitForPageToLoad();

	}

	/**
	 * Tests the articles search - with both existing and non-existing queries.
	 */
	@Test
	public void testSearch() {
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("provider");
		assertFalse(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(5, selenium.getCount(COUNT_ARTICLES));

		enterSearchQuery("nonExistingArticle");
		assertTrue(selenium.isElementPresent(SEARCH_NO_RESULTS));
		assertEquals(0, selenium.getCount(COUNT_ARTICLES));
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		selenium.type(SEARCH_QUERY, "provider");

		for (int pageSize : values) {
			selenium.select(SEARCH_PAGE_SIZE,
					new OptionValueLocator(String.valueOf(pageSize)));
			waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (articles.length > pageSize)
				assertEquals(selenium.getCount(COUNT_ARTICLES), pageSize);
			else
				assertEquals(selenium.getCount(COUNT_ARTICLES), articles.length);

		}
	}

	@Test
	public void testInsertDeleteNewArticle() {
		ArticleOperation articleOperation = new ArticleOperation();
		selenium.click(MENU_INSERT);
		selenium.waitForPageToLoad();
		String catMerc = "newName";
		String rate = "012345678";
		String pack = "1111111111";
		Article article = articleOperation.create("99999999", "", "", "", "",
				catMerc, "", "", "", "", rate, "", "", "", "", "", "", pack,
				"", "", "", "");
		populateArticleFields(article);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message = selenium.getText(ARTICLES_MESSAGE);
		assertTrue(message, message.contains(article.getCode()));
		// cancel article
		selenium.click(BUTTON_CANCEL);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message1 = selenium.getText(ARTICLES_MESSAGE1);
		assertTrue(message1, message1.contains(article.getCode()));
		article.setRate("");
		article.setCode("99999991");
		populateArticleFields(article);
		selenium.click(BUTTON_INSERT_PROCEED);
		selenium.waitForPageToLoad();
		String message2 = selenium.getText(ARTICLES_MESSAGE2);
		assertFalse(message2, message2.contains(article.getCode()));
		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery(catMerc);
		selenium.click(ARTICLES_TABLE_FIRST_ROW_DELETE);
		selenium.waitForPageToLoad();
		message = selenium.getText(ARTICLES_MESSAGE);
		assertTrue(message, message.contains("99999999"));
	}

	@Test
	public void testMultiSearchingUpdate() {
		Article[] articles = new Article[] { article0, article1, article2,
				article4 };
		int articlesCount = selenium.getCount(COUNT_ARTICLES);

		// make 4 articles
		for (Article article : articles) {
			searchUpdateArticle(article, "7777777");
		}

		selenium.click(MENU_FIND);
		selenium.waitForPageToLoad();
		enterSearchQuery("7777777");
		assertEquals(articlesCount, selenium.getCount(COUNT_ARTICLES));
	}

	protected void searchUpdateArticle(Article article, String newPack) {
		if (!selenium.isElementPresent(SEARCH_QUERY)) {
			selenium.open(contextPath);
			selenium.waitForPageToLoad();
			selenium.click(MENU_FIND);
			selenium.waitForPageToLoad();
		}
		enterSearchQuery(article.getDescription());
		selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
		selenium.waitForPageToLoad();
		// article page
		populateArticleFields(newPack);
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
		// article page
		populateArticleFields(article.getPack());
		selenium.click(BUTTON_UPDATE_PROCEED);
		selenium.waitForPageToLoad();
		// main page
		assertTrue("Update success.",
				selenium.isTextPresent("You have been successfully updated"));
	}

	protected void populateArticleFields(String pack) {
		selenium.type(DETAILS_PACK, pack);
	}

	protected void populateArticleFields(String pack, String rate) {
		populateArticleFields(pack);
		selenium.type(DETAILS_RATE, rate);
	}

	protected void populateArticleFields(Article article) {
		populateArticleFields(article.getPack(), article.getRate());
		selenium.type(DETAILS_CODE, article.getCode());
		selenium.type(DETAILS_BAR_CODE, article.getBarCode());
		selenium.type(DETAILS_DESCRIPTION, article.getDescription());
		selenium.type(DETAILS_UM, article.getUm());
		selenium.type(DETAILS_MIS, article.getMis());
		selenium.type(DETAILS_CAT_MERC, article.getCatMerc());
		selenium.type(DETAILS_IMPONIBLE, article.getImponible());
		selenium.type(DETAILS_PRIZE, article.getPrize());
		selenium.type(DETAILS_COST, article.getCost());
		selenium.type(DETAILS_PROVIDER, article.getProvider());
		selenium.type(DETAILS_MAKING, article.getMaking());
		selenium.type(DETAILS_CA, article.getCa());
		selenium.type(DETAILS_SC1, article.getSc1());
		selenium.type(DETAILS_SC2, article.getSc2());
		selenium.type(DETAILS_SC3, article.getSc3());
		selenium.type(DETAILS_QTMIN, article.getQtmin());
		selenium.type(DETAILS_QTMAX, article.getQtmax());
		selenium.type(DETAILS_HEALTH, article.getHealth());
		selenium.type(DETAILS_VOLUME, article.getVolume());
		selenium.type(DETAILS_NOTES, article.getNotes());
	}
}