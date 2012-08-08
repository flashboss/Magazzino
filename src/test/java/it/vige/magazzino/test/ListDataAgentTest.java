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
package it.vige.magazzino.test;

import static it.vige.magazzino.test.Dependencies.FACES;
import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.model.Data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ListDataAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, DataContainer.class.getPackage())
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(SOLDER)
				.addAsLibraries(RICHFACES)
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Test
	public void createListData() throws Exception {

		byte[] image = image();
		listData(image, "Logo per magazzino", 344, "logo1.gif", true);
		listData(image, "Logo per magazzino", 348, "logo2.gif", false);
		listData(image, "Logo per magazzino", 344, "logo3.gif", true);
		listData(image, "Logo per magazzino", 234, "logo4.gif", false);
		listData(image, "Logo per customer", 34, "logo5.gif", true);
		listData(image, "Logo per customer", 349, "logo6.gif", false);
		listData(image, "Logo per customer", 334, "logo7.gif", true);
		listData(image, "Logo per customer", 394, "logo8.gif", false);
		listData(image, "Logo per customer", 314, "logo9.gif", true);
		listData(image, "Logo per customer", 3411, "logo10.gif", false);
		listData(image, "Logo per customer", 334, "logo11.gif", true);

	}

	public byte[] image() throws Exception {
		byte[] bytes = new byte[10310];
		java.io.FileInputStream fis = new java.io.FileInputStream(
				"/Users/flashboss/Desktop/logo.gif");
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
		return bytes;
	}

	public String listData(byte[] image, String description, int length,
			String name, boolean isMulti) throws Exception {
		List<Data> listData = new ArrayList<Data>();
		Data data = new Data();
		data.setData(image);
		data.setDescription(description);
		data.setLength(length);
		data.setName(name);
		listData.add(data);
		if (isMulti) {
			Data data2 = new Data();
			data2.setData(image);
			data2.setDescription("nuova descrizione");
			data2.setLength(98);
			data2.setName("nuovo nome");
			listData.add(data2);
		}

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream("/Users/flashboss/prova"));
		oos.writeObject(listData);
		oos.close();
		byte[] bytes = new byte[10700];
		java.io.FileInputStream fis = new java.io.FileInputStream(
				"/Users/flashboss/prova");
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
		return result;
	}

	public String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// look up high nibble char
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return setBinaryLength(sb.toString());
	}

	private String setBinaryLength(String result) {
		while (true) {
			if (result.endsWith("0"))
				result = result.substring(0, result.lastIndexOf("0"));
			else
				break;
		}
		if (result.length() % 2 != 0)
			result = result + "0";
		return result;
	}

	// table to convert a nibble to a hex char.
	static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
}
