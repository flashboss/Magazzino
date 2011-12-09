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
import org.testng.annotations.Test;

import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;
import static org.jboss.test.selenium.locator.LocatorFactory.jq;
import static org.testng.AssertJUnit.*;

/**
 * This class tests articles functionality of the example.
 *
 * @author jbalunas
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 */
public class MagazzinoTest extends AbstractMagazzinoTest {

    public static final JQueryLocator BUTTON_ARTICLE = jq("[id='actions:searchArticle']");
    public static final JQueryLocator BUTTON_PROCEED = jq("[id='articleForm:proceed']");
    public static final JQueryLocator BUTTON_CONFIRM = jq("[id='confirmForm:confirm']");
    public static final JQueryLocator BUTTON_CANCEL = jq("[id='confirmForm:cancel']");
    public static final JQueryLocator BUTTON_REVISE = jq("[id='confirmForm:revise']");
    public static final String ARTICLE_MESSAGE = "The article is";
    public static final String CANCEL_MESSAGE = "has been canceled.";

    public static final JQueryLocator COUNT_RECEIPT = jq("[id='articleSelectionForm:articles'] tbody tr");
    public static final JQueryLocator COUNT_ARTICLES = jq("[id='articles:articles'] tbody tr");

    public static final JQueryLocator ARTICLES_TABLE_FIRST_ROW_NAME = jq("table[id='articles:articles'] tbody tr:first td:first");
    public static final JQueryLocator ARTICLES_TABLE_FIRST_ROW_LINK = jq("[id='articles:articles:0:cancel']");
    public static final JQueryLocator ARTICLES_CANCEL_MESSAGE = jq("[id='messages'] li");
    public static final String ARTICLES_CANCEL_MESSAGE_TEXT = "Your article at the .+? on .+? has been canceled\\.";

    public static final JQueryLocator DETAILS_CARD_TYPE = jq("[id='articleForm:creditCardType:type']");
    public static final JQueryLocator DETAILS_CARD_NUMBER = jq("[id='articleForm:creditCardNumber:input']");
    public static final JQueryLocator DETAILS_SMOKING = jq("[id='articleForm:smokingPreference:input:0']");
    public static final JQueryLocator DETAILS_NONSMOKING = jq("[id='articleForm:smokingPreference:input:1']");

    public static final JQueryLocator CONFIRM_TEXT = jq("[id='content']");

    public static final JQueryLocator SEARCH_PAGE_SIZE = jq("[id='pageSize']");

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
        int[] values = {5, 10, 20};

        selenium.type(SEARCH_QUERY, "a");

        for (int pageSize : values) {
            selenium.select(SEARCH_PAGE_SIZE, new OptionValueLocator(String.valueOf(pageSize)));
            waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
            assertEquals(selenium.getCount(COUNT_RECEIPT), pageSize);
        }
    }

    /**
     * Simply follows the article wizard without changing anything.
     */
    @Test
    public void testSimpleArticle() {
        String receiptName = "Grand Hyatt";
        int articlesCount = selenium.getCount(COUNT_ARTICLES);
        searchArticle(receiptName, CreditCardType.VISA);
        assertEquals(++articlesCount, selenium.getCount(COUNT_ARTICLES));
    }

    /**
     * Tests "revise" and "cancel" buttons as well as that changed credit card details are propagated across the articles wizard.
     */
    @Test
    public void testMoreSophisticatedArticle() {
        String receiptName = "Conrad Miami";
        String creditCardNumber1 = "0123456789012347";
        CreditCardType creditCardType1 = CreditCardType.DISCOVER;
        String creditCardNumber2 = "6432109876543210";
        CreditCardType creditCardType2 = CreditCardType.AMEX;
        int articlesCount = selenium.getCount(COUNT_ARTICLES);

        enterSearchQuery(receiptName);
        selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
        selenium.waitForPageToLoad();
        // articles page
        selenium.click(BUTTON_ARTICLE);
        selenium.waitForPageToLoad();
        // articles detail page
        populateArticleFields(creditCardNumber1, creditCardType1);
        selenium.click(BUTTON_PROCEED);
        selenium.waitForPageToLoad();
        // confirmation page
        assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber1));
        assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardType1.getName()));
        selenium.click(BUTTON_REVISE);
        selenium.waitForPageToLoad();
        // back to article page
        populateArticleFields(creditCardNumber2, creditCardType2);
        selenium.click(BUTTON_PROCEED);
        selenium.waitForPageToLoad();
        // confirmation page
        assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardNumber2));
        assertTrue(selenium.getText(CONFIRM_TEXT).contains(creditCardType2.getName()));
        // cancel article
        selenium.click(BUTTON_CANCEL);
        selenium.waitForPageToLoad();
        // check that the article cound remains unchanged
        assertEquals(articlesCount, selenium.getCount(COUNT_ARTICLES));
    }

    @Test
    public void testArticleCanceling() {
        String[] receiptNames = new String[]{"Hilton Diagonal Mar", "Parc 55", "Ritz-Carlton Montreal", "Parc 55"};
        int articlesCount = selenium.getCount(COUNT_ARTICLES);

        // make 3 articles
        for (String receiptName : receiptNames) {
        	searchArticle(receiptName, CreditCardType.VISA);
        }

        articlesCount += receiptNames.length;
        assertEquals(articlesCount, selenium.getCount(COUNT_ARTICLES));

        for (int i = 0; i < receiptNames.length; i++) {
            String receiptName = selenium.getText(ARTICLES_TABLE_FIRST_ROW_NAME).trim();
            selenium.click(ARTICLES_TABLE_FIRST_ROW_LINK);
            selenium.waitForPageToLoad();
            String message = selenium.getText(ARTICLES_CANCEL_MESSAGE);
            assertTrue("Unexpected canceling message: " + message, message.matches(ARTICLES_CANCEL_MESSAGE_TEXT));
            assertTrue("Unexpected receipt name.", message.contains(receiptName));
            assertEquals("Unexpected number of articles", --articlesCount, selenium.getCount(COUNT_ARTICLES));
        }
    }

    protected void searchArticle(String receiptName, CreditCardType creditCardType) {
        if (!isLoggedIn()) {
            fail();
        }
        if (!selenium.isElementPresent(SEARCH_QUERY)) {
            selenium.open(contextPath);
            selenium.waitForPageToLoad();
            selenium.click(MENU_FIND);
            selenium.waitForPageToLoad();
        }
        enterSearchQuery(receiptName);
        selenium.click(SEARCH_RESULT_TABLE_FIRST_ROW_LINK);
        selenium.waitForPageToLoad();
        // articles page
        selenium.click(BUTTON_ARTICLE);
        selenium.waitForPageToLoad();
        // receipt page
        populateArticleFields(creditCardType);
        selenium.click(BUTTON_PROCEED);
        selenium.waitForPageToLoad();
        // confirm page
        selenium.click(BUTTON_CONFIRM);
        selenium.waitForPageToLoad();
        // main page
        assertTrue("Articles failed.", selenium.isTextPresent(ARTICLE_MESSAGE));
    }

    protected void populateArticleFields(CreditCardType creditCardType) {
        selenium.select(DETAILS_CARD_TYPE, creditCardType.getLocator());
    }

    protected void populateArticleFields(String creditCardNumber, CreditCardType creditCardType) {
        selenium.type(DETAILS_CARD_NUMBER, creditCardNumber);
        populateArticleFields(creditCardType);
    }

    private enum CreditCardType {
        VISA("VISA"),
        MASTERCARD("MasterCard"),
        AMEX("AMEX"),
        DISCOVER("Discover");

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
