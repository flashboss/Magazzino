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

import org.jboss.test.selenium.AbstractTestCase;
import org.jboss.test.selenium.locator.JQueryLocator;
import org.testng.annotations.BeforeMethod;

import static org.jboss.test.selenium.guard.request.RequestTypeGuardFactory.waitXhr;
import static org.jboss.test.selenium.locator.LocatorFactory.jq;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Utility methods for the articles example.
 *
 * @author <a href="http://community.jboss.org/people/jharting">Jozef Hartinger</a>
 */
public abstract class AbstractMagazzinoTest extends AbstractTestCase {
    public static final String TITLE = "JBoss Suites: Seam Framework Demo";
    public static final JQueryLocator LOGIN_USERNAME = jq("[id='login:username']");
    public static final JQueryLocator LOGIN_PASSWORD = jq("[id='login:password']");
    public static final JQueryLocator LOGIN_SUBMIT = jq("[id='login:login']");
    public static final JQueryLocator LOGOUT = jq("[href^='/magazzino/logout']");

    public static final JQueryLocator MENU_FIND = jq("[href^='/magazzino/search']");
    public static final JQueryLocator MENU_HOME = jq("a:contains('Home')");
    public static final JQueryLocator MENU_ACCOUNT = jq("[href^='/magazzino/account']");
    public static final JQueryLocator SEARCH_QUERY = jq("#query");
    public static final JQueryLocator SEARCH_NO_RESULTS = jq("#noArticlesMsg");
    public static final JQueryLocator SEARCH_PAGE_SIZE = jq("#pageSize");
    public static final JQueryLocator SEARCH_RESULT_TABLE_FIRST_ROW_LINK = jq("[id='articleSelectionForm:articles:0:view']");

    private final String DEFAULT_USERNAME = "jose";
    private final String DEFAULT_PASSWORD = "brazil";

    @BeforeMethod
    public void setUp() {
        selenium.open(contextPath);
        selenium.waitForPageToLoad();
        if (isLoggedIn()) {
            logout();
        }
        login();
        selenium.click(MENU_FIND);
        selenium.waitForPageToLoad();

    }

    public void login() {
        login(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public void login(String username, String password) {
        selenium.click(MENU_HOME);
        selenium.waitForPageToLoad();

        selenium.type(LOGIN_USERNAME, username);
        selenium.type(LOGIN_PASSWORD, password);
        selenium.click(LOGIN_SUBMIT);
        selenium.waitForPageToLoad();

        assertTrue("Login failed.", isLoggedIn());
    }

    public void logout() {
        if (isLoggedIn()) {
            selenium.click(LOGOUT);
            selenium.waitForPageToLoad();
        }
    }

    public boolean isLoggedIn() {
        return selenium.isElementPresent(LOGOUT);
    }

    public void enterSearchQuery(String query) {
        selenium.type(SEARCH_QUERY, query);
        waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
    }
}
