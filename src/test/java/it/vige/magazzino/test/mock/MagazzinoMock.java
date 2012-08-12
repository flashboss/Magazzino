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

import it.vige.magazzino.model.Magazzino;
import it.vige.magazzino.test.operation.MagazzinoOperation;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface MagazzinoMock extends AddressMock, ListDataMock {

	MagazzinoOperation magazzinoOperation = new MagazzinoOperation();

	Magazzino magazzino0 = magazzinoOperation.create("21344", "01/06/1999",
			"23432", "cause 1", "compensation 1", "2654654", "rag soc 1",
			"rag soc 2", "111", "01/05/1998", "4343289479", "39.000,00 Û",
			"70075", address9, listData9);

	Magazzino magazzino1 = magazzinoOperation.create("21345", "02/06/1981",
			"543534", "cause 2", "compensation 2", "2654654", "rag soc 3",
			"rag soc 4", "111", "01/01/1998", "656253253", "12.000,00 $",
			"32677", address10, listData10);

	Magazzino magazzino2 = magazzinoOperation.create("21346", "06/02/1999",
			"6546", "cause 3", "compensation 3", "213443", "rag soc 5",
			"rag soc 6", "111", "05/05/1977", "89898983", "45.000,00 Û",
			"44342", address11, listData11);

	Magazzino magazzino3 = magazzinoOperation.create("21347", "06/01/1999",
			"2342", "cause 4", "compensation 4", "453534", "rag soc 7",
			"rag soc 8", "111", "05/04/1976", "455454543", "89.000,00 $",
			"65345", address12, listData12);

	Magazzino magazzino4 = magazzinoOperation.create("21348", "06/06/1980",
			"434", "cause 5", "compensation 5", "2787", "rag soc 9",
			"rag soc 10", "111", "04/04/1998", "8989898989", "98.000,00 Û",
			"98873", address13, listData13);

	Magazzino magazzino5 = magazzinoOperation.create("21349", "06/06/1982",
			"22", "cause 6", "compensation 6", "53453", "rag soc 11",
			"rag soc 12", "111", "04/04/1998", "32323232323", "333.000,00 $",
			"54664", address14, listData14);

	Magazzino magazzino6 = magazzinoOperation.create("21314", "06/01/1985",
			"534543", "cause 9", "compensation 9", "54546", "rag soc 17",
			"rag soc 18", "111", "04/02/1992", "43565757", "42.000,00 Û",
			"22223", address15, listData15);

	Magazzino magazzino7 = magazzinoOperation.create("213334", "02/06/1986",
			"23432", "cause 10", "compensation 10", "6544", "rag soc 19",
			"rag soc 20", "111", "04/04/1998", "8787878787", "99.000,00 $",
			"43432", address16, listData16);

	Magazzino magazzino8 = magazzinoOperation.create("212344", "03/06/1987",
			"65464", "cause 11", "compensation 11", "34322", "rag soc 21",
			"rag soc 22", "111", "05/04/1992", "212121212", "33.000,00 Û",
			"86433", address17, null);

	Magazzino magazzino9 = magazzinoOperation.create("21234", "04/06/1988",
			"5646", "cause 12", "compensation 12", "6567", "rag soc 23",
			"rag soc 24", "111", "04/05/1998", "212121212", "33.000,00 Û",
			"86433", address18, null);

	Magazzino magazzino10 = magazzinoOperation.create("1344", "05/06/1989",
			"645645", "cause 13", "compensation 13", "5454", "rag soc 25",
			"rag soc 26", "111", "04/04/1995", "212121212", "33.000,00 Û",
			"86433", address19, null);
}
