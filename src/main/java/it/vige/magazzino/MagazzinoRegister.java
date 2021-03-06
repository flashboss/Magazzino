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
package it.vige.magazzino;

import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.solder.logging.TypedCategory;

/**
 * The view controller for registering a new jar
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Stateful
@Model
@SessionScoped
public class MagazzinoRegister {

	@Inject
	@TypedCategory(MagazzinoRegister.class)
	private MagazzinoLog log;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Messages messages;

	@Inject
	private FacesContext facesContext;

	private UIInput numberInput;

	private final Magazzino newJar = new Magazzino();

	private boolean registered;

	private boolean registrationInvalid;

	public void register() {
		if (verifyNumberIsAvailable()) {
			registered = true;
			List<Data> files = newJar.getFiles();
			for (Data file : files) {
				file.setJar(newJar);
				em.persist(file);
			}
			em.persist(newJar);

			messages.info(new DefaultBundleKey("magazzino_registered"))
					.defaults(
							"You have been successfully registered as the jar {0}!")
					.params(newJar.getCodeJar());
			log.jarConfirmed(newJar.getCodeJar() + "", newJar.getCodCustomer());
		} else {
			registrationInvalid = true;
		}
	}

	public boolean isRegistrationInvalid() {
		return registrationInvalid;
	}

	/**
	 * This method just shows another approach to adding a status message.
	 * <p>
	 * Invoked by:
	 * </p>
	 * <p/>
	 * 
	 * <pre>
	 * &lt;f:event type="preRenderView" listener="#{register.notifyIfRegistrationIsInvalid}"/>
	 * </pre>
	 */
	public void notifyIfRegistrationIsInvalid() {
		if (facesContext.isValidationFailed() || registrationInvalid) {
			messages.warn(new DefaultBundleKey("magazzino_invalid")).defaults(
					"Invalid jar. Please correct the errors and try again.");
		}
	}

	@Produces
	@Named
	public Magazzino getNewJar() {
		return newJar;
	}

	public boolean isRegistered() {
		return registered;
	}

	public UIInput getNumberInput() {
		return numberInput;
	}

	public void setNumberInput(final UIInput numberInput) {
		this.numberInput = numberInput;
	}

	private boolean verifyNumberIsAvailable() {
		Magazzino existing = em.find(Magazzino.class, newJar.getCodeJar());
		if (existing != null) {
			messages.warn(new BundleKey("messages", "account_numberTaken"))
					.defaults(
							"The number '{0}' is already taken. Please choose another number.")
					.targets(numberInput.getClientId())
					.params(newJar.getCodeJar());
			log.jarAvailable(existing.getCodeJar() + "", existing != null);
			return false;
		}

		return true;
	}

}
