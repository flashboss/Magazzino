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
package it.vige.magazzino.update;

import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.selection.CustomerSelection;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

/**
 * The view controller for registering a new customer
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Stateful
@Model
@SessionScoped
public class CustomerUpdater {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Messages messages;

	@Inject
	private FacesContext facesContext;

	@Inject
	private CustomerSelection customerSelection;

	private UIInput codeInput;

	private boolean updated;

	private boolean registrationInvalid;

	public void update() {
		Customer customer = customerSelection.getSelectedCustomer();
		em.merge(customer);
		updated = true;
		messages.info(new DefaultBundleKey("customer_updated"))
				.defaults(
						"You have been successfully updated as the customer {0}!")
				.params(customer.getCodeCustomer());
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
			messages.warn(new DefaultBundleKey("customer_invalid"))
					.defaults(
							"Invalid customer. Please correct the errors and try again.");
		}
	}

	public boolean isUpdated() {
		return updated;
	}

	public UIInput getCodeInput() {
		return codeInput;
	}

	public void setCodeInput(final UIInput codeInput) {
		this.codeInput = codeInput;
	}

}
