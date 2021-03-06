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
package it.vige.magazzino.test.mock;

import static it.vige.magazzino.test.mock.ImageMock.image0;
import it.vige.magazzino.model.Data;
import it.vige.magazzino.test.operation.ListDataOperation;

import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface ListDataMock {

	ListDataOperation listDataOperation = new ListDataOperation();

	List<Data> listData0 = listDataOperation.create("5556", image0,
			"Logo per magazzino", 344, "logo1.gif", true, "2561");

	List<Data> listData1 = listDataOperation.create("55156", image0,
			"Logo per magazzino", 348, "logo2.gif", false, "");

	List<Data> listData2 = listDataOperation.create("52656", image0,
			"Logo per magazzino", 344, "logo3.gif", true, "2562");

	List<Data> listData3 = listDataOperation.create("55656", image0,
			"Logo per magazzino", 234, "logo4.gif", false, "");

	List<Data> listData4 = listDataOperation.create("51656", image0,
			"Logo per customer", 34, "logo5.gif", true, "2563");

	List<Data> listData5 = listDataOperation.create("55646", image0,
			"Logo per customer", 349, "logo6.gif", false, "");

	List<Data> listData6 = listDataOperation.create("55436", image0,
			"Logo per customer", 334, "logo7.gif", true, "2564");

	List<Data> listData7 = listDataOperation.create("556", image0,
			"Logo per customer", 394, "logo8.gif", false, "");

	List<Data> listData8 = listDataOperation.create("5526", image0,
			"Logo per customer", 314, "logo9.gif", true, "2565");

	List<Data> listData9 = listDataOperation.create("55956", image0,
			"Logo per customer", 3411, "logo10.gif", false, "");

	List<Data> listData10 = listDataOperation.create("256", image0,
			"Logo per customer", 334, "logo11.gif", true, "2566");

	List<Data> listData11 = listDataOperation.create("21156", image0,
			"Logo per customer", 31134, "image2.gif", true, "112566");

	List<Data> listData12 = listDataOperation.create("25116", image0,
			"Logo per customer", 33114, "image3.gif", true, "211566");

	List<Data> listData13 = listDataOperation.create("25611", image0,
			"Logo per customer", 33411, "image4.gif", true, "256116");

	List<Data> listData14 = listDataOperation.create("12256", image0,
			"Logo per customer", 12334, "image5.gif", true, "251166");

	List<Data> listData15 = listDataOperation.create("21256", image0,
			"Logo per customer", 31234, "image6.gif", true, "256611");

	List<Data> listData16 = listDataOperation.create("25126", image0,
			"Logo per customer", 33124, "image7.gif", true, "122566");

	List<Data> listData17 = listDataOperation.create("25612", image0,
			"Logo per customer", 33412, "image8.gif", true, "212566");

	List<Data> listData18 = listDataOperation.create("13256", image0,
			"Logo per customer", 13334, "image9.gif", true, "256126");

	List<Data> listData19 = listDataOperation.create("132561", image0,
			"Logo per customer", 31334, "logo11.gif", true, "256612");

	List<Data>[] listDatas = listDataOperation.getAllListDatas();

}
