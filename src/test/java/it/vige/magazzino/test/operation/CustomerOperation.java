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
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class CustomerOperation {

	private List<Customer> customers = new ArrayList<Customer>();

	public Customer create(String code, String name, String ragSocial,
			String iva, Address address, ArrayList<Data> listData) {
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName(name);
		customer.setRagSocial(ragSocial);
		customer.setIva(iva);
		customer.setAddress(address);
		customer.setFiles(listData);

		customers.add(customer);
		return customer;
	}

	public Customer[] getAllCustomers() {
		return customers.toArray(new Customer[0]);
	}
}
