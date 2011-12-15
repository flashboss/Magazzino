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
	private static final long serialVersionUID = -602763026033932730L;
	@ManyToOne
	@JoinColumn(name = "number_bbb", nullable = false)
	private Magazzino jar;
	@ManyToOne
	@JoinColumn(name = "code_bbb", nullable = false)
	private Customer customer;
	private String number;
	private String date;
	private String cause;
	private String description;

	public Receipt() {
	}

	public Receipt(final String number, final String date, final String cause,
			Magazzino jar, Customer customer) {
		this.number = number;
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

	@NotNull
	@Size(min = 1, max = 100)
	public String getCause() {
		return cause;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

	@Size(min = 5, max = 15)
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Id
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "not a valid number")
	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@NotNull
	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	@Transient
	public String getNumberWithCause() {
		return number + " <" + cause + ">";
	}

	@Override
	public String toString() {
		return cause;
	}

	@NotNull
	public Magazzino getJar() {
		return jar;
	}

	public void setJar(final Magazzino jar) {
		this.jar = jar;
	}

	@NotNull
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
		else
			return this.getNumber().equals(((Receipt) arg0).getNumber());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Integer.parseInt(number);
	}
}