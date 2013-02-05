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
 * <strong>Magazzino</strong> is the model/entity class that represents a jar.
 * </p>
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Entity
@Table(name = "magazzino")
@Veto
public class Magazzino extends FileUpload {
	private static final long serialVersionUID = 7693948752468272876L;
	private Address address = new Address();
	@Id
	@Column(name = "codeJar")
	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "not a valid number")
	private String codeJar;
	private String date;
	private String code;
	private String cause;
	private String compensation;
	private String codCustomer;
	@NotNull
	@Size(min = 1, max = 100)
	private String ragSoc1;
	@NotNull
	@Size(min = 1, max = 100)
	private String ragSoc2;
	private String numberDoc;
	private String dateDoc;
	private String iva;
	private String capSoc;
	private String reaPI;
	@OneToMany(mappedBy = "jar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Data> files = new ArrayList<Data>();
	@OneToMany(mappedBy = "jar", cascade = CascadeType.ALL)
	private List<Receipt> receipts = new ArrayList<Receipt>();

	public Magazzino() {
	}

	public Magazzino(final String codeJar, final String date) {
		this.codeJar = codeJar;
		this.date = date;
	}

	public Magazzino(final String codeJar, final String date,
			final String code, final String cause, final String compensation,
			final String codCustomer, final String ragSoc1,
			final String ragSoc2, final String numberDoc, final String dateDoc,
			final String iva, final String capSoc, final String reaPI) {
		this(codeJar, date);
		this.code = code;
		this.cause = cause;
		this.compensation = compensation;
		this.codCustomer = codCustomer;
		this.ragSoc1 = ragSoc1;
		this.ragSoc2 = ragSoc2;
		this.numberDoc = numberDoc;
		this.dateDoc = dateDoc;
		this.iva = iva;
		this.capSoc = capSoc;
		this.reaPI = reaPI;
	}

	public String getCodeJar() {
		return codeJar;
	}

	public void setCodeJar(final String codeJar) {
		this.codeJar = codeJar;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(final String compensation) {
		this.compensation = compensation;
	}

	public String getCodCustomer() {
		return codCustomer;
	}

	public void setCodCustomer(final String codCustomer) {
		this.codCustomer = codCustomer;
	}

	public String getRagSoc1() {
		return ragSoc1;
	}

	public void setRagSoc1(final String ragSoc1) {
		this.ragSoc1 = ragSoc1;
	}

	public String getRagSoc2() {
		return ragSoc2;
	}

	public void setRagSoc2(final String ragSoc2) {
		this.ragSoc2 = ragSoc2;
	}

	public String getNumberDoc() {
		return numberDoc;
	}

	public void setNumberDoc(final String numberDoc) {
		this.numberDoc = numberDoc;
	}

	public String getDateDoc() {
		return dateDoc;
	}

	public void setDateDoc(final String dateDoc) {
		this.dateDoc = dateDoc;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getCapSoc() {
		return capSoc;
	}

	public void setCapSoc(String capSoc) {
		this.capSoc = capSoc;
	}

	public String getReaPI() {
		return reaPI;
	}

	public void setReaPI(String reaPI) {
		this.reaPI = reaPI;
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
		return ragSoc1;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Magazzino))
			return super.equals(arg0);
		else if (this.getCodeJar() == null
				&& ((Magazzino) arg0).getCodeJar() == null)
			return true;
		else
			return this.getCodeJar().equals(((Magazzino) arg0).getCodeJar());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return codeJar.hashCode();
	}
}
