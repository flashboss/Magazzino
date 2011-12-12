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
package it.vige.magazzino.remove;

import it.vige.magazzino.i18n.DefaultBundleKey;
import it.vige.magazzino.inventory.ReceiptSearch;
import it.vige.magazzino.model.Receipt;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

/**
 * The view controller for delete a receipt
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Stateful
@Model
@SessionScoped
public class ReceiptDeleter {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Messages messages;

	@Inject
	private FacesContext facesContext;
    
    @Inject
    private ReceiptSearch receiptSearch;

	public void delete(Receipt receipt) {
		Receipt oldReceipt;
		if ((oldReceipt = verifyNumberIsAvailable(receipt)) != null) {
			em.remove(oldReceipt);
			receiptSearch.currentPage();
		}
		
		messages.info(new DefaultBundleKey("receipt_deleted"))
				.defaults(
						"You have been successfully deleted the receipt {0}!")
				.params(receipt.getNumber());
	}

	/**
	 * This method just shows another approach to adding a status message.
	 * <p>
	 * Invoked by:
	 * </p>
	 * <p/>
	 * 
	 * <pre>
	 * &lt;f:event type="preRenderView" listener="#{register.notifyIfDeletingIsInvalid}"/>
	 * </pre>
	 */
	public void notifyIfDeletingIsInvalid() {
		if (facesContext.isValidationFailed()) {
			messages.warn(new DefaultBundleKey("receipt_invalid"))
					.defaults(
							"Invalid receipt. Please correct the errors and try again.");
		}
	}

	private Receipt verifyNumberIsAvailable(Receipt receipt) {
		Receipt existing = em.find(Receipt.class, receipt.getNumber());
		return existing;
	}

}
