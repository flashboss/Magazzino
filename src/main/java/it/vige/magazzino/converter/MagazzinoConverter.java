package it.vige.magazzino.converter;

import it.vige.magazzino.inventory.all.MagazzinoAllSearch;
import it.vige.magazzino.model.Magazzino;

import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.jboss.seam.faces.conversion.Converter;

@FacesConverter("magazzinoConverter")
public class MagazzinoConverter extends Converter<Magazzino> {

	@Inject
	private MagazzinoAllSearch magazzinoAllSearch;

	@Override
	public Magazzino toObject(UIComponent arg0, String arg1) {
		// TODO Auto-generated method stub
		for (Magazzino jar : magazzinoAllSearch.getAllJars())
			if (jar.getCodeJar().equals(arg1))
				return jar;
		return null;
	}

	@Override
	public String toString(UIComponent arg0, Magazzino arg1) {
		// TODO Auto-generated method stub
		return arg1.getCodeJar();
	}

}
