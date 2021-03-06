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
import it.vige.magazzino.log.ReceiptLog;
import it.vige.magazzino.model.Receipt;

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
 * The view controller for registering a new receipt
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Stateful
@Model
@SessionScoped
public class ReceiptRegister {

	@Inject
	@TypedCategory(ReceiptRegister.class)
	private ReceiptLog log;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Messages messages;

	@Inject
	private FacesContext facesContext;

	private UIInput numberInput;

	private final Receipt newReceipt = new Receipt();

	private boolean registered;

	private boolean registrationInvalid;

	public void register() {
		if (verifyNumberIsAvailable()) {
			em.persist(newReceipt);
			registered = true;

			messages.info(new DefaultBundleKey("receipt_registered"))
					.defaults(
							"You have been successfully registered as the receipt {0}!")
					.params(newReceipt.getCodeReceipt());
			log.receiptConfirmed(newReceipt.getCodeReceipt() + "",
					newReceipt.getCause());
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
			messages.warn(new DefaultBundleKey("receipt_invalid"))
					.defaults(
							"Invalid receipt. Please correct the errors and try again.");
		}
	}

	@Produces
	@Named
	public Receipt getNewReceipt() {
		return newReceipt;
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
		Receipt existing = em.find(Receipt.class, newReceipt.getCodeReceipt());
		if (existing != null) {
			messages.warn(new BundleKey("messages", "account_numberTaken"))
					.defaults(
							"The number '{0}' is already taken. Please choose another number.")
					.targets(numberInput.getClientId())
					.params(newReceipt.getCodeReceipt());
			log.receiptAvailable(existing.getCodeReceipt() + "",
					existing != null);
			return false;
		}

		return true;
	}

}
