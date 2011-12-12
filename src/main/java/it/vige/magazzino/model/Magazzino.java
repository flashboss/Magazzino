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
public class Magazzino implements Serializable {
	private static final long serialVersionUID = -602933026033932730L;
	private String number;
	private String date;
	private String code;
	private String cause;
	private String compensation;
	private String codCustomer;
	private String ragSoc1;
	private String ragSoc2;
	private String numberDoc;
	private String dateDoc;

	public Magazzino() {
	}

	public Magazzino(final String number, final String date) {
		this.number = number;
		this.date = date;
	}

	public Magazzino(final String number, final String date, final String code,
			final String cause, final String compensation,
			final String codCustomer, final String ragSoc1,
			final String ragSoc2, final String numberDoc, final String dateDoc) {
		this(number, date);
		this.code = code;
		this.cause = cause;
		this.compensation = compensation;
		this.codCustomer = codCustomer;
		this.ragSoc1 = ragSoc1;
		this.ragSoc2 = ragSoc2;
		this.numberDoc = numberDoc;
		this.dateDoc = dateDoc;
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

	@NotNull
	@Size(min = 1, max = 100)
	public String getRagSoc1() {
		return ragSoc1;
	}

	public void setRagSoc1(final String ragSoc1) {
		this.ragSoc1 = ragSoc1;
	}

	@NotNull
	@Size(min = 1, max = 100)
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

	@Override
	public String toString() {
		return ragSoc1;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Magazzino))
			return super.equals(arg0);
		else
			return this.getNumber().equals(((Magazzino) arg0).getNumber());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Integer.parseInt(number);
	}
}
