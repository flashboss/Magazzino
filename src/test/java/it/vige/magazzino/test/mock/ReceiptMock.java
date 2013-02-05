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

import static it.vige.magazzino.test.mock.CustomerMock.customer0;
import static it.vige.magazzino.test.mock.CustomerMock.customer1;
import static it.vige.magazzino.test.mock.CustomerMock.customer2;
import static it.vige.magazzino.test.mock.CustomerMock.customer3;
import static it.vige.magazzino.test.mock.CustomerMock.customer4;
import static it.vige.magazzino.test.mock.CustomerMock.customer5;
import static it.vige.magazzino.test.mock.CustomerMock.customer6;
import static it.vige.magazzino.test.mock.CustomerMock.customer7;
import static it.vige.magazzino.test.mock.CustomerMock.customer8;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino0;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino1;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino10;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino2;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino3;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino4;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino5;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino6;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino7;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino8;
import static it.vige.magazzino.test.mock.MagazzinoMock.magazzino9;
import it.vige.magazzino.model.Receipt;
import it.vige.magazzino.test.operation.ReceiptOperation;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface ReceiptMock {

	ReceiptOperation receiptOperation = new ReceiptOperation();

	Receipt receipt0 = receiptOperation.create("21256", "11/05/2009",
			"causale 1", "description 1", magazzino0, customer0);

	Receipt receipt1 = receiptOperation.create("23226", "02/05/2009",
			"causale 2", "description 2", magazzino1, customer1);

	Receipt receipt2 = receiptOperation.create("11213", "11/10/2009",
			"causale 3", "description 3", magazzino2, customer2);

	Receipt receipt3 = receiptOperation.create("4343", "11/05/2007",
			"causale 4", "description 4", magazzino3, customer3);

	Receipt receipt4 = receiptOperation.create("5453", "11/01/2003",
			"causale 5", "description 5", magazzino4, customer4);

	Receipt receipt5 = receiptOperation.create("666", "02/05/2007",
			"causale 6", "description 6", magazzino5, customer5);

	Receipt receipt6 = receiptOperation.create("32322", "03/03/2002",
			"causale 7", "description 7", magazzino6, customer6);

	Receipt receipt7 = receiptOperation.create("528", "11/03/2007",
			"causale 8", "description 8", magazzino7, customer7);

	Receipt receipt8 = receiptOperation.create("95433", "01/05/2007",
			"causale 9", "description 9", magazzino8, customer8);

	Receipt receipt9 = receiptOperation.create("5432", "11/12/2007",
			"causale 10", "description 10", magazzino9, customer3);

	Receipt receipt10 = receiptOperation.create("33333", "12/05/2001",
			"causale 11", "description 11", magazzino10, customer3);

	Receipt[] receipts = receiptOperation.getAllReceipts();
}
