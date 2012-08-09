package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Address;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.model.Magazzino;

import java.util.ArrayList;

public class MagazzinoOperation {

	public Magazzino create(String number, String date, String code,
			String cause, String compensation, String codCustomer,
			String ragSoc1, String ragSoc2, String numberDoc, String dateDoc,
			String iva, String capSoc, String reaPI, Address address,
			ArrayList<Data> listData) throws Exception {
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

		return magazzino;
	}
}
