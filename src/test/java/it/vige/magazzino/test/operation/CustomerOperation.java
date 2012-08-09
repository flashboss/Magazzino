package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;

import java.util.ArrayList;

public class CustomerOperation {

	public Customer create(String code, String name, String ragSocial,
			String iva, Address address, ArrayList<Data> listData)
			throws Exception {
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName(name);
		customer.setRagSocial(ragSocial);
		customer.setIva(iva);
		customer.setAddress(address);
		customer.setFiles(listData);

		return customer;
	}
}
