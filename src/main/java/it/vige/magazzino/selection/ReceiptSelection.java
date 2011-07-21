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

import it.vige.magazzino.model.Receipt;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.faces.context.conversation.Begin;

@Stateful
@ConversationScoped
@Named
public class ReceiptSelection {

    @Inject
    private Conversation conversation;
	
	private Receipt receiptSelection;

	@Begin
    public void selectReceipt(final Receipt receipt) {
        conversation.setTimeout(600000); //10 * 60 * 1000 (10 minutes)
        receiptSelection = receipt;
    }

    @Produces
    @RequestScoped
    @Named("receipt")
    public Receipt getSelectedReceipt() {
        return receiptSelection;
    }

}
