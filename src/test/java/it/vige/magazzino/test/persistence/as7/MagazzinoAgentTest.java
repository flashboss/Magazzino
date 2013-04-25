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
package it.vige.magazzino.test.persistence.as7;

import static it.vige.magazzino.test.persistence.Dependencies.FACES;
import static it.vige.magazzino.test.persistence.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.persistence.Dependencies.RICHFACES;
import static it.vige.magazzino.test.persistence.Dependencies.SOLDER;
import static it.vige.magazzino.test.mock.MagazzinoMock.jars;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino0;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino1;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino10;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino2;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino3;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino4;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino5;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino6;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino7;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino8;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino9;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.FileUpload;
import it.vige.magazzino.MagazzinoRegister;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.MagazzinoSearch;
import it.vige.magazzino.inventory.SearchCriteria;
import it.vige.magazzino.inventory.all.MagazzinoAllSearch;
import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.remove.MagazzinoDeleter;
import it.vige.magazzino.selection.MagazzinoSelection;
import it.vige.magazzino.test.mock.AddressMock;
import it.vige.magazzino.test.mock.ImageMock;
import it.vige.magazzino.test.mock.ListDataMock;
import it.vige.magazzino.test.mock.MagazzinoMock;
import it.vige.magazzino.test.operation.AddressOperation;
import it.vige.magazzino.test.operation.ImageOperation;
import it.vige.magazzino.test.operation.ListDataOperation;
import it.vige.magazzino.test.operation.MagazzinoOperation;
import it.vige.magazzino.update.MagazzinoUpdater;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.international.status.Message;
import org.jboss.seam.international.status.Messages;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.Container;
import org.jboss.weld.context.http.HttpConversationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class MagazzinoAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(MagazzinoAgentTest.class)
				.addClasses(Data.class, ListDataMock.class,
						ListDataOperation.class)
				.addClasses(ImageMock.class, ImageOperation.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(AddressMock.class, AddressOperation.class,
						Address.class)
				.addClasses(MagazzinoMock.class, MagazzinoOperation.class,
						Magazzino.class)
				.addClasses(MagazzinoRegister.class, MagazzinoUpdater.class,
						MagazzinoDeleter.class, MagazzinoSelection.class)
				.addClasses(MagazzinoLog.class, Magazzino_.class)
				.addClasses(MagazzinoSearch.class, MagazzinoAllSearch.class,
						SearchCriteria.class)
				.addClasses(DataContainer.class, FileUpload.class)
				.addClasses(DefaultBundleKey.class)
				.addClasses(Customer.class)
				.addClasses(Receipt.class)
				.addAsLibraries(SOLDER)
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(RICHFACES)
				.addAsResource("logo.gif")
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	MagazzinoRegister magazzinoRegister;
	
	@EJB
	MagazzinoUpdater magazzinoUpdater;

	@EJB
	MagazzinoDeleter magazzinoDeleter;

	@Inject
	MagazzinoSelection magazzinoSelection;

	@EJB
	MagazzinoSearch magazzinoSearch;

	@Inject
	SearchCriteria criteria;

	@Inject
	Messages messages;

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Before
	public void createMagazzino() throws Exception {
		if (em.find(Magazzino.class, magazzino1.getCodeJar()) == null) {
			utx.begin();
			em.joinTransaction();
			persistList(magazzino0.getFiles());
			persistList(magazzino0.getReceipts());
			em.persist(magazzino0);
			persistList(magazzino1.getFiles());
			persistList(magazzino1.getReceipts());
			em.persist(magazzino1);
			persistList(magazzino2.getFiles());
			persistList(magazzino2.getReceipts());
			em.persist(magazzino2);
			persistList(magazzino3.getFiles());
			persistList(magazzino3.getReceipts());
			em.persist(magazzino3);
			persistList(magazzino4.getFiles());
			persistList(magazzino4.getReceipts());
			em.persist(magazzino4);
			persistList(magazzino5.getFiles());
			persistList(magazzino5.getReceipts());
			em.persist(magazzino5);
			persistList(magazzino6.getFiles());
			persistList(magazzino6.getReceipts());
			em.persist(magazzino6);
			persistList(magazzino7.getFiles());
			persistList(magazzino7.getReceipts());
			em.persist(magazzino7);
			persistList(magazzino8.getFiles());
			persistList(magazzino8.getReceipts());
			em.persist(magazzino8);
			persistList(magazzino9.getFiles());
			persistList(magazzino9.getReceipts());
			em.persist(magazzino9);
			persistList(magazzino10.getFiles());
			persistList(magazzino10.getReceipts());
			em.persist(magazzino10);
			utx.commit();
		}
	}

	@Test
	public void searchMagazzino() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Magazzino b")
				.getResultList().size());
	}

	@Test
	public void testSearch() {
		criteria.setQuery("rag soc");
		magazzinoSearch.find();
		assertFalse(magazzinoSearch.getJars().size() == 0);
		assertEquals(5, magazzinoSearch.getJars().size());

		criteria.setQuery("nonExistingMagazzino");
		magazzinoSearch.find();
		assertTrue(magazzinoSearch.getJars().size() == 0);
		assertEquals(0, magazzinoSearch.getJars().size());
	}

	@Test
	public void testSearchPageSize() {
		int[] values = { 5, 10, 20 };

		criteria.setQuery("rag soc");

		for (int pageSize : values) {
			criteria.setPageSize(pageSize);
			magazzinoSearch.find();
			if (jars.length > pageSize)
				assertEquals(magazzinoSearch.getJars().size(), pageSize);
			else
				assertEquals(magazzinoSearch.getJars().size(), jars.length);
		}
	}

	@Test
	public void testInsertDeleteNewMagazzino() {
		String magazzinoCompensation = "newCompensation";
		String ragSoc1 = "new rag soc for magazzino test";
		String ragSoc2 = "0123456789012347";
		Address address = new Address();
		address.setAddress("Vige street");
		Magazzino magazzino = magazzinoRegister.getNewJar();
		magazzino.setCodeJar("99999999");
		magazzino.setCompensation(magazzinoCompensation);
		magazzino.setRagSoc1(ragSoc1);
		magazzino.setRagSoc2(ragSoc2);
		magazzino.setAddress(address);
		magazzinoRegister.register();
		String message = messages.getAll().iterator().next().getText();
		assertTrue(message, message.contains(magazzino.getCodeJar()));
		// cancel magazzino
		magazzino.setCodeJar("");
		try {
			magazzinoRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		magazzino.setRagSoc2("");
		magazzino.setCodeJar("99999991");
		try {
			magazzinoRegister.register();
			assertTrue(false);
		} catch (EJBTransactionRolledbackException ex) {
			assertTrue(true);
		}
		criteria.setQuery(magazzinoCompensation);
		magazzinoSearch.find();
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		magazzinoSelection.selectJar(magazzinoSearch.getJars().get(0));
		Magazzino selectedJar = magazzinoSelection.getSelectedJar();
		magazzinoDeleter.delete(selectedJar);
		boolean found = false;
		for (Message messageFromList : messages.getAll()) {
			if (messageFromList.getText().contains("99999999")
					&& messageFromList.getText().contains("deleted"))
				found = true;
		}

		assertTrue(message, found);
	}

	@Test
	public void testMultiSearchingUpdate() {
		Magazzino[] jars = new Magazzino[] { magazzino0, magazzino1,
				magazzino2, magazzino4 };

		// make 4 jars
		Container.instance().deploymentManager().instance()
				.select(HttpConversationContext.class).get().activate();
		for (Magazzino magazzino : jars) {
			searchUpdateMagazzino(magazzino, "test-ejb-for-ragsoc");
		}
		criteria.setQuery("test-ejb-for-ragsoc");
		magazzinoSearch.find();
		assertEquals(magazzinoSearch.getJars().size(), 0);
	}

	protected void searchUpdateMagazzino(Magazzino magazzino, String newCause) {
		criteria.setQuery(magazzino.getCompensation());
		magazzinoSearch.find();
		magazzinoSelection.selectJar(magazzinoSearch.getJars().get(0));
		Magazzino selectedJar = magazzinoSelection.getSelectedJar();
		// magazzino page
		selectedJar.setCause(newCause);
		magazzinoUpdater.update();
		// main page
		String message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
		// magazzino page
		selectedJar.setCause(magazzino.getCause());
		magazzinoUpdater.update();
		// main page
		message = messages.getAll().iterator().next().getText();
		assertTrue("Update success.",
				message.startsWith("You have been successfully updated"));
	}

	private <T> void persistList(List<T> elements) {
		if (elements != null)
			for (T element : elements)
				em.persist(element);
	}

}
