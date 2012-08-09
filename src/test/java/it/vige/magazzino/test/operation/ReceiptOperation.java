package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;

import java.util.ArrayList;

public class ReceiptOperation {

	public Receipt create(String number, String date, String cause,
			String description, String numberJar, String dateJar, String code,
			String causeJar, String compensation, String codCustomer,
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc,
			String iva, String capSoc, String reaPI, String codeAddress,
			String homeAddress, String cap, String phone, String fax,
			String email, String site, String civicNumber, String province,
			String town, String city, String codeImage, byte[] image,
			String descriptionJarFile, int length, String name,
			boolean isMulti, String codeImage2, String codeCustomer,
			String nameCustomer, String ragSocial, String ivaCustomer,
			String codeCustomerAddress, String homeCustomerAddress,
			String capCustomerAddress, String phoneCustomerAddress,
			String faxCustomerAddress, String emailCustomerAddress,
			String siteCustomerAddress, String civicNumberCustomerAddress,
			String provinceCustomerAddress, String townCustomerAddress,
			String cityCustomerAddress, String codeImageCustomer,
			byte[] imageCustomer, String descriptionCustomerImage,
			int lengthCustomerImage, String nameFileCustomerImage,
			boolean isMultiCustomerImage, String codeImageCustomer2)
			throws Exception {
		Receipt receipt = new Receipt();
		receipt.setCause(cause);
		receipt.setDate(date);
		receipt.setDescription(description);
		receipt.setNumber(number);

		Magazzino magazzino = new Magazzino();
		magazzino.setNumber(numberJar);
		magazzino.setDate(dateJar);
		magazzino.setCode(code);
		magazzino.setCause(causeJar);
		magazzino.setCompensation(compensation);
		magazzino.setCodCustomer(codCustomer);
		magazzino.setRagSoc1(ragSoc1);
		magazzino.setRagSoc2(ragSoc2);
		magazzino.setNumberDoc(numberDoc);
		magazzino.setDateDoc(dateDoc);
		magazzino.setIva(iva);
		magazzino.setCapSoc(capSoc);
		magazzino.setReaPI(reaPI);

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

		magazzino.setAddress(address);

		if (image != null) {
			ArrayList<Data> listData = new ArrayList<Data>();
			Data data = new Data();
			data.setCode(codeImage);
			data.setData(image);
			data.setDescription(descriptionJarFile);
			data.setLength(length);
			data.setName(name);
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
			magazzino.setFiles(listData);
		}
		receipt.setJar(magazzino);

		Customer customer = new Customer();
		customer.setCode(codeCustomer);
		customer.setName(nameCustomer);
		customer.setRagSocial(ragSocial);
		customer.setIva(ivaCustomer);

		Address addressCustomer = new Address();
		addressCustomer.setCode(codeCustomerAddress);
		addressCustomer.setAddress(homeCustomerAddress);
		addressCustomer.setCap(capCustomerAddress);
		addressCustomer.setCity(cityCustomerAddress);
		addressCustomer.setCivicNumber(civicNumberCustomerAddress);
		addressCustomer.setEmail(emailCustomerAddress);
		addressCustomer.setFax(faxCustomerAddress);
		addressCustomer.setPhone(phoneCustomerAddress);
		addressCustomer.setSite(siteCustomerAddress);
		addressCustomer.setTown(townCustomerAddress);
		addressCustomer.setProvince(provinceCustomerAddress);

		customer.setAddress(addressCustomer);

		if (imageCustomer != null) {
			ArrayList<Data> listData = new ArrayList<Data>();
			Data data = new Data();
			data.setCode(codeImageCustomer);
			data.setData(imageCustomer);
			data.setDescription(descriptionCustomerImage);
			data.setLength(lengthCustomerImage);
			data.setName(nameFileCustomerImage);
			listData.add(data);
			if (isMultiCustomerImage) {
				Data data2 = new Data();
				data2.setCode(codeImageCustomer2);
				data2.setData(imageCustomer);
				data2.setDescription("nuova descrizione");
				data2.setLength(98);
				data2.setName("nuovo nome");
				listData.add(data2);
			}
			customer.setFiles(listData);
		}
		receipt.setCustomer(customer);

		return receipt;
	}
}
