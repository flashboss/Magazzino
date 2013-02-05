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
package it.vige.magazzino.selection;

import it.vige.magazzino.log.MagazzinoLog;
import it.vige.magazzino.model.Magazzino;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.solder.logging.TypedCategory;

@Stateful
@Named
@SessionScoped
public class MagazzinoSelection {

	@Inject
	@TypedCategory(MagazzinoSelection.class)
	private MagazzinoLog log;

	@Inject
	private Conversation conversation;

	private Magazzino jarSelection;

	@Begin
	public void selectJar(final Magazzino jar) {
		conversation.setTimeout(600000); // 10 * 60 * 1000 (10 minutes)
		jarSelection = jar;
		log.jarSelected(jar.getCodeJar() + "", jar.getCodCustomer(),
				jar.getCause());
	}

	@Produces
	@RequestScoped
	@Named("jar")
	public Magazzino getSelectedJar() {
		return jarSelection;
	}

}
