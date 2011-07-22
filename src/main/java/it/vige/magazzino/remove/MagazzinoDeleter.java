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
import it.vige.magazzino.model.Magazzino;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

/**
 * The view controller for registering a new jar
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Stateful
@ConversationScoped
@Model
public class MagazzinoDeleter {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Messages messages;

	@Inject
	private FacesContext facesContext;

	public void delete(Magazzino magazzino) {
		Magazzino oldMagazzino;
		if ((oldMagazzino = verifyNumberIsAvailable(magazzino)) != null) 
			em.remove(oldMagazzino);
		
		messages.info(new DefaultBundleKey("magazzino_deleted"))
				.defaults(
						"You have been successfully registered as the jar {0}!")
				.params(magazzino.getNumber());
	}

	/**
	 * This method just shows another approach to adding a status message.
	 * <p>
	 * Invoked by:
	 * </p>
	 * <p/>
	 * 
	 * <pre>
	 * &lt;f:event type="preRenderView" listener="#{deleter.notifyIfDeletingIsInvalid}"/>
	 * </pre>
	 */
	public void notifyIfDeletingIsInvalid() {
		if (facesContext.isValidationFailed()) {
			messages.warn(new DefaultBundleKey("magazzino_invalid")).defaults(
					"Invalid jar. Please correct the errors and try again.");
		}
	}

	private Magazzino verifyNumberIsAvailable(Magazzino magazzino) {
		Magazzino existing = em.find(Magazzino.class, magazzino.getNumber());
		return existing;
	}

}
