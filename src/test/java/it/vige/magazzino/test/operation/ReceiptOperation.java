package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Customer;
import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.model.Receipt;

public class ReceiptOperation {

	public Receipt create(String number, String date, String cause,
			String description, Magazzino magazzino, Customer customer)
			throws Exception {
		Receipt receipt = new Receipt();
		receipt.setCause(cause);
		receipt.setDate(date);
		receipt.setDescription(description);
		receipt.setNumber(number);
		receipt.setJar(magazzino);
		receipt.setCustomer(customer);

		return receipt;
	}
}
