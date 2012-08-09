package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;

import java.util.ArrayList;

public class MagazzinoOperation {

	public Magazzino create(String number, String date, String code,
			String cause, String compensation, String codCustomer,
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc,
			String iva, String capSoc, String reaPI, String codeAddress,
			String homeAddress, String cap, String phone, String fax,
			String email, String site, String civicNumber, String province,
			String town, String city, String codeImage, byte[] image,
			String description, int length, String name, boolean isMulti,
			String codeImage2) throws Exception {
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
			data.setDescription(description);
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

		return magazzino;
	}
}
