package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Data;

import java.util.ArrayList;

public class ListDataOperation {

	public ArrayList<Data> create(String code, byte[] image,
			String description, int length, String name, boolean isMulti,
			String code2) throws Exception {
		ArrayList<Data> listData = null;
		if (image != null) {
			listData = new ArrayList<Data>();
			String secondName = "nuovo nome";
			Data data = new Data();
			data.setCode(code);
			data.setData(image);
			data.setDescription(description);
			data.setLength(length);
			data.setName(name);
			listData.add(data);
			if (isMulti) {
				Data data2 = new Data();
				data2.setCode(code2);
				data2.setData(image);
				data2.setDescription("nuova descrizione");
				data2.setLength(98);
				data2.setName(secondName);
				listData.add(data2);
			}
		}
		return listData;
	}
}
