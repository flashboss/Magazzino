package it.vige.magazzino.converter;

import it.vige.magazzino.inventory.all.ReceiptAllSearch;
import it.vige.magazzino.model.Receipt;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("receiptConverter")
public class ReceiptConverter extends Converter<Receipt> {

	@Inject
	private ReceiptAllSearch receiptAllSearch;

	@Override
	public Receipt toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Receipt receipt : receiptAllSearch.getAllReceipts())
			if (receipt.getCodeReceipt().equals(arg1))
				return receipt;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Receipt arg1) {
		// TODO Auto-generated method stub
		return arg1.getCodeReceipt();
	}

}
