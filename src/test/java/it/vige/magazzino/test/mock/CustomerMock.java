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

import static it.vige.magazzino.test.mock.AddressMock.address0;
import static it.vige.magazzino.test.mock.AddressMock.address1;
import static it.vige.magazzino.test.mock.AddressMock.address2;
import static it.vige.magazzino.test.mock.AddressMock.address3;
import static it.vige.magazzino.test.mock.AddressMock.address4;
import static it.vige.magazzino.test.mock.AddressMock.address5;
import static it.vige.magazzino.test.mock.AddressMock.address6;
import static it.vige.magazzino.test.mock.AddressMock.address7;
import static it.vige.magazzino.test.mock.AddressMock.address8;
import static it.vige.magazzino.test.mock.ListDataMock.listData0;
import static it.vige.magazzino.test.mock.ListDataMock.listData1;
import static it.vige.magazzino.test.mock.ListDataMock.listData2;
import static it.vige.magazzino.test.mock.ListDataMock.listData3;
import static it.vige.magazzino.test.mock.ListDataMock.listData4;
import static it.vige.magazzino.test.mock.ListDataMock.listData5;
import static it.vige.magazzino.test.mock.ListDataMock.listData6;
import it.vige.magazzino.model.Customer;
import it.vige.magazzino.test.operation.CustomerOperation;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface CustomerMock {

	CustomerOperation customerOperation = new CustomerOperation();

	Customer customer0 = customerOperation.create("19987", "cliente 1",
			"rag soc 1", "92755353", address0, listData0);

	Customer customer1 = customerOperation.create("1177", "cliente 2",
			"rag soc 2", "74424577", address1, listData1);

	Customer customer2 = customerOperation.create("98766", "cliente 3",
			"rag soc 3", "2232322", address2, listData2);

	Customer customer3 = customerOperation.create("11121", "cliente 4",
			"rag soc 4", "76565656", address3, listData3);

	Customer customer4 = customerOperation.create("34322", "cliente 6",
			"rag soc 6", "2535345433", address4, listData4);

	Customer customer5 = customerOperation.create("22222", "cliente 7",
			"rag soc 7", "654424322", address5, listData5);

	Customer customer6 = customerOperation.create("55555", "cliente 8",
			"rag soc 8", "53546566", address6, listData6);

	Customer customer7 = customerOperation.create("325", "cliente 9",
			"rag soc 9", "323244646", address7, null);

	Customer customer8 = customerOperation.create("6433", "cliente 10",
			"rag soc 10", "324464646", address8, null);

	Customer[] customers = customerOperation.getAllCustomers();
}
