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
package it.vige.magazzino.test.persistence;

import static it.vige.magazzino.test.Dependencies.FACES;
import static it.vige.magazzino.test.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.Dependencies.RICHFACES;
import static it.vige.magazzino.test.Dependencies.SOLDER;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.FileUpload;
import it.vige.magazzino.MagazzinoRegister;
import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.MagazzinoSearch;
import it.vige.magazzino.inventory.SearchCriteria;
import it.vige.magazzino.inventory.all.MagazzinoAllSearch;
import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Magazzino_;
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

import javax.ejb.EJB;
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

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@RunWith(Arquillian.class)
public class MagazzinoAgentTest implements MagazzinoMock {
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

	@EJB
	MagazzinoSelection magazzinoSelection;

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Test
	public void createMagazzino() throws Exception {
		utx.begin();
		em.joinTransaction();

		em.persist(magazzino0);
		em.persist(magazzino1);
		em.persist(magazzino2);
		em.persist(magazzino3);
		em.persist(magazzino4);
		em.persist(magazzino5);
		em.persist(magazzino6);
		em.persist(magazzino7);
		em.persist(magazzino8);
		em.persist(magazzino9);
		em.persist(magazzino10);
		utx.commit();
	}

	@Test
	public void searchMagazzino() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Magazzino b")
				.getResultList().size());
	}

	@Test
	public void testSearch() {

	}

	@Test
	public void testSearchPageSize() {

	}

	@Test
	public void testInsertDeleteNewArticle() {

	}

	@Test
	public void testMultiSearchingUpdate() {

	}

}
