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

import org.jboss.test.selenium.AbstractTestCase;
import org.jboss.test.selenium.locator.JQueryLocator;

/**
 * Utility methods for the articles example.
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public abstract class AbstractTest extends AbstractTestCase {
	public static final String TITLE = "Vige Suites: Magazzino";

	public static final JQueryLocator MENU_HOME = jq("a:contains('Home')");
	public static final JQueryLocator SEARCH_QUERY = jq("#query");
	public static final JQueryLocator SEARCH_PAGE_SIZE = jq("#pageSize");

	public void enterSearchQuery(String query) {
		selenium.type(SEARCH_QUERY, query);
		waitXhr(selenium).keyUp(SEARCH_QUERY, " ");
	}
}
