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
import static it.vige.magazzino.test.Utils.image;
import static it.vige.magazzino.test.Utils.tempFile;
import static it.vige.magazzino.test.Utils.toHexString;
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
import org.junit.Assert;
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
				.addAsResource("logo.gif")
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	static final String[] hexCodes = { "", "", "", "", "", "", "", "", "", "",
			"" };

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Test
	public void createListData() throws Exception {
		byte[] image = image();

		utx.begin();
		em.joinTransaction();
		List<Data> listData = listData(image, "Logo per magazzino", 344,
				"logo1.gif", true, hexCodes[0]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per magazzino", 348, "logo2.gif",
				false, hexCodes[1]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per magazzino", 344, "logo3.gif",
				true, hexCodes[2]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per magazzino", 234, "logo4.gif",
				false, hexCodes[3]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 34, "logo5.gif", true,
				hexCodes[4]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 349, "logo6.gif",
				false, hexCodes[5]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 334, "logo7.gif", true,
				hexCodes[6]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 394, "logo8.gif",
				false, hexCodes[7]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 314, "logo9.gif", true,
				hexCodes[8]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 3411, "logo10.gif",
				false, hexCodes[9]);
		for (Data data : listData)
			em.persist(data);
		listData = listData(image, "Logo per customer", 334, "logo11.gif",
				true, hexCodes[10]);
		for (Data data : listData)
			em.persist(data);
		utx.commit();
	}

	@Test
	public void searchListData() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Data b")
				.getResultList().size());
	}

	@SuppressWarnings("unchecked")
	public List<Data> listData(byte[] image, String description, int length,
			String name, boolean isMulti, String hexCode) throws Exception {
		List<Data> listData = new ArrayList<Data>();
		String secondName = "nuovo nome";
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
			data2.setName(secondName);
			listData.add(data2);
		}

		java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream(tempFile));
		oos.writeObject(listData);
		oos.close();
		byte[] bytes = new byte[10700];
		java.io.FileInputStream fis = new java.io.FileInputStream(tempFile);
		fis.read(bytes);
		fis.close();
		String result = toHexString(bytes);
		System.out.println(result);
		java.io.ObjectInputStream ois = new java.io.ObjectInputStream(
				new java.io.ByteArrayInputStream(bytes));
		List<Data> listaDataRead = (List<Data>) ois.readObject();
		if (isMulti) {
			Assert.assertEquals(listaDataRead.size(), 2);
			Assert.assertEquals(listaDataRead.get(1).getName(), secondName);
		} else {
			Assert.assertEquals(listaDataRead.size(), 2);
		}
		Assert.assertEquals(listaDataRead.get(0).getName(), name);
		Assert.assertEquals(result, hexCode);
		ois.close();
		return listaDataRead;
	}
}
