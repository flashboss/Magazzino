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
package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class MagazzinoOperation {

	private List<Magazzino> jars = new ArrayList<Magazzino>();

	public Magazzino create(String number, String date, String code,
			String cause, String compensation, String codCustomer,
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc,
			String iva, String capSoc, String reaPI, Address address,
			ArrayList<Data> listData) {
		Magazzino magazzino = new Magazzino();
		magazzino.setNumber(number);
		magazzino.setDate(date);
		magazzino.setCode(code);
		magazzino.setCause(cause);
		magazzino.setCompensation(compensation);
		magazzino.setCodCustomer(codCustomer);
		magazzino.setRagSoc1(ragSoc1);
		magazzino.setRagSoc2(ragSoc2);
		magazzino.setNumberDoc(numberDoc);
		magazzino.setDateDoc(dateDoc);
		magazzino.setIva(iva);
		magazzino.setCapSoc(capSoc);
		magazzino.setReaPI(reaPI);
		magazzino.setAddress(address);
		magazzino.setFiles(listData);

		jars.add(magazzino);
		return magazzino;
	}

	public Magazzino[] getAllJars() {
		return jars.toArray(new Magazzino[0]);
	}
}
