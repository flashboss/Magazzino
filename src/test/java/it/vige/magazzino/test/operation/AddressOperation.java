package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;

public class AddressOperation {

	public Address create(String code, String homeAddress, String cap,
			String phone, String fax, String email, String site,
			String civicNumber, String province, String town, String city)
			throws Exception {
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

		return address;
	}
}
