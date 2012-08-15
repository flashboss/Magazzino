/*
 * Vige, Home of Professional Open Source
 * Copyright 2010, Vige, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.vige.magazzino.test.operation;

import it.vige.magazzino.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ListDataOperation {

	private List<ArrayList<Data>> listDatas = new ArrayList<ArrayList<Data>>();

	public ArrayList<Data> create(String code, byte[] image,
			String description, int length, String name, boolean isMulti,
			String code2) {
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

			listDatas.add(listData);
		}
		return listData;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Data>[] getAllListDatas() {
		return listDatas.toArray(new ArrayList[0]);
	}
}
