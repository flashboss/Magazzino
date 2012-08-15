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

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class AddressOperation {

	private List<Address> addresses = new ArrayList<Address>();

	public Address create(String code, String homeAddress, String cap,
			String phone, String fax, String email, String site,
			String civicNumber, String province, String town, String city) {
		Address address = new Address();
		address.setCode(code);
		address.setAddress(homeAddress);
		address.setCap(cap);
		address.setCity(city);
		address.setCivicNumber(civicNumber);
		address.setEmail(email);
		address.setFax(fax);
		address.setPhone(phone);
		address.setSite(site);
		address.setTown(town);
		address.setProvince(province);

		addresses.add(address);
		return address;
	}

	public Address[] getAllAddresses() {
		return addresses.toArray(new Address[0]);
	}
}
