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
package it.vige.magazzino.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.seam.solder.core.Veto;

/**
 * <p>
 * <strong>Receipt</strong> is the model/entity class that represents a customer
 * to insert or select.
 * </p>
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Entity
@Table(name = "receipt")
@Veto
public class Receipt implements Serializable {
	private static final long serialVersionUID = -6293695153492023096L;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "codeJar")
	private Magazzino jar;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "codeCustomer")
	private Customer customer;
	@Id
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "not a valid number")
	private String codeReceipt;
	@NotNull
	private String date;
	@NotNull
	@Size(min = 1, max = 100)
	private String cause;
	@Size(min = 5, max = 15)
	private String description;

	public Receipt() {
	}

	public Receipt(final String codeReceipt, final String date,
			final String cause, Magazzino jar, Customer customer) {
		this.codeReceipt = codeReceipt;
		this.date = date;
		this.cause = cause;
		this.jar = jar;
		this.customer = customer;
	}

	public Receipt(final String number, final String date, final String cause,
			final String description, Magazzino jar, Customer customer) {
		this(number, date, cause, jar, customer);
		this.description = description;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getCodeReceipt() {
		return codeReceipt;
	}

	public void setCodeReceipt(final String codeReceipt) {
		this.codeReceipt = codeReceipt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	@Transient
	public String getNumberWithCause() {
		return codeReceipt + " <" + cause + ">";
	}

	@Override
	public String toString() {
		return cause;
	}

	public Magazzino getJar() {
		return jar;
	}

	public void setJar(final Magazzino jar) {
		this.jar = jar;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Receipt))
			return super.equals(arg0);
		else if (this.getCodeReceipt() == null
				&& ((Receipt) arg0).getCodeReceipt() == null)
			return true;
		else
			return this.getCodeReceipt().equals(
					((Receipt) arg0).getCodeReceipt());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return codeReceipt.hashCode();
	}
}