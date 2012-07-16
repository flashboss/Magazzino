package it.vige.magazzino.converter;

import it.vige.magazzino.inventory.all.CustomerAllSearch;
import it.vige.magazzino.model.Customer;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("customerConverter")
public class CustomerConverter extends Converter<Customer> {

	@Inject
	private CustomerAllSearch customerAllSearch;

	@Override
	public Customer toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Customer jar : customerAllSearch.getAllCustomers())
			if (jar.getCode().equals(arg1))
				return jar;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Customer arg1) {
		// TODO Auto-generated method stub
		return arg1.getCode();
	}

}
