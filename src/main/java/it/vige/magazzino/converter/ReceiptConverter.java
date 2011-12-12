package it.vige.magazzino.converter;

import java.util.List;

import it.vige.magazzino.model.Receipt;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("receiptConverter")
public class ReceiptConverter extends Converter<Receipt> {

	@Inject
	private List<Receipt> receipts;

	@Override
	public Receipt toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Receipt receipt : receipts)
			if (receipt.getNumber().equals(arg1))
				return receipt;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Receipt arg1) {
		// TODO Auto-generated method stub
		return arg1.getNumber();
	}

}
