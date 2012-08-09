package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;

import java.util.ArrayList;

public class CustomerOperation {

	public Customer create(String code, String name, String ragSocial,
			String iva, String codeAddress, String homeAddress, String cap,
			String phone, String fax, String email, String site,
			String civicNumber, String province, String town, String city,
			String codeImage, byte[] image, String description, int length,
			String nameFile, boolean isMulti, String codeImage2)
			throws Exception {
		Customer customer = new Customer();
		customer.setCode(code);
		customer.setName(name);
		customer.setRagSocial(ragSocial);
		customer.setIva(iva);

		Address address = new Address();
		address.setCode(codeAddress);
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

		customer.setAddress(address);

		if (image != null) {
			ArrayList<Data> listData = new ArrayList<Data>();
			Data data = new Data();
			data.setCode(codeImage);
			data.setData(image);
			data.setDescription(description);
			data.setLength(length);
			data.setName(nameFile);
			listData.add(data);
			if (isMulti) {
				Data data2 = new Data();
				data2.setCode(codeImage2);
				data2.setData(image);
				data2.setDescription("nuova descrizione");
				data2.setLength(98);
				data2.setName("nuovo nome");
				listData.add(data2);
			}
			customer.setFiles(listData);
		}

		return customer;
	}
}
