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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@OneToOne
	private Address address;
	private String code;
	private String name;
	private String ragSocial;
	private String iva;
	@OneToMany
	private ArrayList<Data> files;

	public Customer() {
	}

	public Customer(final String code, final String name,
			final String ragSocial, final String iva) {
		this.code = code;
		this.name = name;
		this.ragSocial = ragSocial;
		this.iva = iva;
	}

	@NotNull
	@Size(min = 1, max = 100)
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Id
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "not a valid customer")
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	@NotNull
	public String getRagSocial() {
		return ragSocial;
	}

	public void setRagSocial(final String ragSocial) {
		this.ragSocial = ragSocial;
	}

	@NotNull
	public String getIva() {
		return iva;
	}

	public void setIva(final String iva) {
		this.iva = iva;
	}

	@NotNull
	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public ArrayList<Data> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<Data> files) {
		this.files = files;
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
		else
			return this.getCode().equals(((Customer) arg0).getCode());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Integer.parseInt(code);
	}
}
