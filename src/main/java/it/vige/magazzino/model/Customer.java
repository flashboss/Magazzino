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

import it.vige.magazzino.FileUpload;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.seam.solder.core.Veto;

/**
 * <p>
 * <strong>Customer</strong> is the model/entity class that represents a
 * customer who may buy an article.
 * </p>
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Entity
@Table(name = "customer")
@Veto
public class Customer extends FileUpload {
	private static final long serialVersionUID = -612733026033932730L;
	@Column(length = 1000)
	private Address address = new Address();
	@Id
	@Column(name = "codeCustomer")
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "not a valid customer")
	private String codeCustomer;
	@NotNull
	@Size(min = 1, max = 100)
	private String name;
	@NotNull
	private String ragSocial;
	@NotNull
	private String iva;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Data> files = new ArrayList<Data>();
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Receipt> receipts = new ArrayList<Receipt>();

	public Customer() {
	}

	public Customer(final String codeCustomer, final String name,
			final String ragSocial, final String iva) {
		this.codeCustomer = codeCustomer;
		this.name = name;
		this.ragSocial = ragSocial;
		this.iva = iva;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCodeCustomer() {
		return codeCustomer;
	}

	public void setCodeCustomer(final String codeCustomer) {
		this.codeCustomer = codeCustomer;
	}

	public String getRagSocial() {
		return ragSocial;
	}

	public void setRagSocial(final String ragSocial) {
		this.ragSocial = ragSocial;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(final String iva) {
		this.iva = iva;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public List<Data> getFiles() {
		return files;
	}

	public void setFiles(List<Data> files) {
		this.files = files;
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	@Override
	public String toString() {
		return ragSocial;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Customer))
			return super.equals(arg0);
		else if (this.getCodeCustomer() == null
				&& ((Customer) arg0).getCodeCustomer() == null)
			return true;
		else
			return this.getCodeCustomer().equals(
					((Customer) arg0).getCodeCustomer());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return codeCustomer.hashCode();
	}
}
