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

import it.vige.magazzino.model.Address;
import it.vige.magazzino.test.operation.AddressOperation;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface AddressMock {

	AddressOperation addressOperation = new AddressOperation();

	Address address0 = addressOperation.create("piazza Clodio", "00122",
			"64746567", "7654345676", "reqrew@vige.it", "wwewewe.com", "54",
			"RM", "Guidonia", "Rome");

	Address address1 = addressOperation.create("viale Mazzini", "00134",
			"534537446", "346357465736", "hgdfgsfg@vige.it", "fdfd.com", "67",
			"LO", "Setteville", "London");

	Address address2 = addressOperation.create("piazza Bologna", "00234",
			"5425356457", "8875645732", "afadfsd@vige.it", "wwqewr.com", "33",
			"BO", "Settecamini", "Bologna");

	Address address3 = addressOperation.create("viale Giulio Cesare", "00987",
			"896969687", "32456733", "hdhhjdghf@vige.it", "qasas.com", "656",
			"FI", "Tivoli", "Florence");

	Address address4 = addressOperation.create("via Tibutina", "01234",
			"23423423", "2436233453", "fdsfsdfsd@vige.it", "ffkkfkf.com", "34",
			"PA", "Francoforte", "Paris");

	Address address5 = addressOperation.create("via Prenestina", "00152",
			"87584734637", "84562354656", "gdhdgjfgj@vige.it", "ppopo.com",
			"24", "SH", "Zagarolo", "Shangai");

	Address address6 = addressOperation.create("piazza Tuscolo", "00012",
			"32678475323", "74684736433", "fsdfsdfsd@vige.it", "ewe.com", "76",
			"BO", "Nola", "Bombay");

	Address address7 = addressOperation.create("via Tuscolana", "09833",
			"42675473364", "754684333", "tytre@vige.it", "swswd.com", "546",
			"RM", "Castelvolturno", "Rome");

	Address address8 = addressOperation.create("via Serafini", "00999",
			"534748622", "7568473634", "ewrete@vige.it", "ewewwq.com", "66",
			"KY", "Tropea", "Kyoto");

	Address address9 = addressOperation.create("via Serafina", "00666",
			"63564832764", "3467468733", "ngnghghg@vige.it", "llklk.it", "33",
			"TK", "Palinuro", "Tokyo");

	Address address10 = addressOperation.create("piazza Clodio", "01122",
			"64746567", "7651676", "reqrew@vige.it", "wwewewe.com", "54", "RM",
			"Guidonia", "Rome");

	Address address11 = addressOperation.create("viale Mazzini", "00114",
			"531446", "3463165736", "hgdfgsfg@vige.it", "fdfd.com", "67", "LO",
			"Setteville", "London");

	Address address12 = addressOperation.create("piazza Bologna", "00234",
			"54253516457", "8871115645732", "afadfsd@vige.it", "wwqewr.com",
			"33", "BO", "Settecamini", "Bologna");

	Address address13 = addressOperation.create("viale Giulio Cesare", "00987",
			"896969687", "32456733", "hdhhjdghf@vige.it", "qasas.com", "6526",
			"FI", "Tivoli", "Florence");

	Address address14 = addressOperation.create("via Tibutina", "01234",
			"23423423", "2436233453", "fdsfsdfsd@vige.it", "ffkkfkf.com", "34",
			"PA", "Francoforte", "Paris");

	Address address15 = addressOperation.create("via Prenestina", "00152",
			"87584734637", "84562354656", "gdhdgjfgj@vige.it", "ppopo.com",
			"124", "SH", "Zagarolo", "Shangai");

	Address address16 = addressOperation.create("piazza Tuscolo", "00212",
			"32678475323", "74684736433", "fsdfsdfsd@vige.it", "ewe.com",
			"716", "BO", "Nola", "Bombay");

	Address address17 = addressOperation.create("via Tuscolana", "09833",
			"42675473364", "754684333", "tytre@vige.it", "swswd.com", "546",
			"RM", "Castelvolturno", "Rome");

	Address address18 = addressOperation.create("via Serafini", "00999",
			"534748622", "7568473634", "ewrete@vige.it", "ewewwq.com", "616",
			"KY", "Tropea", "Kyoto");

	Address address19 = addressOperation.create("via Serafina", "00666",
			"63564832764", "3467468733", "ngnghghg@vige.it", "llklk.it", "33",
			"TK", "Palinuro", "Tokyo");

	Address[] addresses = addressOperation.getAllAddresses();
}
