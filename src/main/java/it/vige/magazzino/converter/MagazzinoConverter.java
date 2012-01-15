package it.vige.magazzino.converter;

import java.util.List;

import it.vige.magazzino.model.Magazzino;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("magazzinoConverter")
public class MagazzinoConverter extends Converter<Magazzino> {

	@Inject
	private List<Magazzino> jars;

	@Override
	public Magazzino toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Magazzino jar : jars)
			if (jar.getNumber().equals(arg1))
				return jar;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Magazzino arg1) {
		// TODO Auto-generated method stub
		return arg1.getNumber();
	}

}