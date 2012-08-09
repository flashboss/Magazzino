package it.vige.magazzino.test.persistence;

import static it.vige.magazzino.test.persistence.Dependencies.FACES;
import static it.vige.magazzino.test.persistence.Dependencies.INTERNATIONAL;
import static it.vige.magazzino.test.persistence.Dependencies.RICHFACES;
import static it.vige.magazzino.test.persistence.Dependencies.SOLDER;
import static it.vige.magazzino.test.persistence.Utils.image;
import it.vige.magazzino.DataContainer;
import it.vige.magazzino.test.operation.ReceiptOperation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReceiptAgentTest {
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, DataContainer.class.getPackage())
				.addAsLibraries(INTERNATIONAL)
				.addAsLibraries(FACES)
				.addAsLibraries(SOLDER)
				.addAsLibraries(RICHFACES)
				.addAsResource("logo.gif")
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebInfResource("test-persistence.xml",
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	UserTransaction utx;

	@PersistenceContext
	EntityManager em;

	@Test
	public void createReceipt() throws Exception {
		byte[] image = image();

		utx.begin();
		em.joinTransaction();

		ReceiptOperation receiptMock = new ReceiptOperation();
		em.persist(receiptMock.create("21256", "11/05/2009", "causale 1",
				"description 1", "21344", "01/06/1999", "23432", "cause 1",
				"compensation 1", "2654654", "rag soc 1", "rag soc 2", "111",
				"01/05/1998", "4343289479", "39.000,00 Û", "70075", "4353",
				"piazza Clodio", "01122", "64746567", "7651676",
				"reqrew@vige.it", "wwewewe.com", "54", "RM", "Guidonia",
				"Rome", "5556", image, "Logo per magazzino", 344, "logo1.gif",
				true, "2561", "19987", "cliente 1", "rag soc 1", "92755353",
				"19987", "piazza Clodio", "00122", "64746567", "7654345676",
				"reqrew@vige.it", "wwewewe.com", "54", "RM", "Guidonia",
				"Rome", "5556", image, "Logo per customer", 34, "logo5.gif",
				true, "2561"));
		em.persist(receiptMock.create("23226", "02/05/2009", "causale 2",
				"description 2", "21345", "02/06/1981", "543534", "cause 2",
				"compensation 2", "2654654", "rag soc 3", "rag soc 4", "111",
				"01/01/1998", "656253253", "12.000,00 $", "32677", "222",
				"viale Mazzini", "00114", "531446", "3463165736",
				"hgdfgsfg@vige.it", "fdfd.com", "67", "LO", "Setteville",
				"London", "55156", image, "Logo per magazzino", 348,
				"logo2.gif", false, "", "1177", "cliente 2", "rag soc 2",
				"74424577", "1177", "viale Mazzini", "00134", "534537446",
				"346357465736", "hgdfgsfg@vige.it", "fdfd.com", "67", "LO",
				"Setteville", "London", "55156", image, "Logo per customer",
				349, "logo6.gif", false, ""));
		em.persist(receiptMock.create("11213", "11/10/2009", "causale 3",
				"description 3", "21346", "06/02/1999", "6546", "cause 3",
				"compensation 3", "213443", "rag soc 5", "rag soc 6", "111",
				"05/05/1977", "89898983", "45.000,00 Û", "44342", "76435",
				"piazza Bologna", "00234", "54253516457", "8871115645732",
				"afadfsd@vige.it", "wwqewr.com", "33", "BO", "Settecamini",
				"Bologna", "52656", image, "Logo per magazzino", 344,
				"logo3.gif", true, "", "98766", "cliente 3", "rag soc 3",
				"2232322", "98766", "piazza Bologna", "00234", "5425356457",
				"8875645732", "afadfsd@vige.it", "wwqewr.com", "33", "BO",
				"Settecamini", "Bologna", "52656", image, "Logo per customer",
				334, "logo7.gif", true, "2562"));
		em.persist(receiptMock.create("4343", "11/05/2007", "causale 4",
				"description 4", "21347", "06/01/1999", "2342", "cause 4",
				"compensation 4", "453534", "rag soc 7", "rag soc 8", "111",
				"05/04/1976", "455454543", "89.000,00 $", "65345", "23567",
				"viale Giulio Cesare", "00987", "896969687", "32456733",
				"hdhhjdghf@vige.it", "qasas.com", "6526", "FI", "Tivoli",
				"Florence", "55656", image, "Logo per magazzino", 234,
				"logo4.gif", false, "", "11121", "cliente 4", "rag soc 4",
				"76565656", "11121", "viale Giulio Cesare", "00987",
				"896969687", "32456733", "hdhhjdghf@vige.it", "qasas.com",
				"656", "FI", "Tivoli", "Florence", "55656", image,
				"Logo per customer", 394, "logo8.gif", false, ""));
		em.persist(receiptMock.create("5453", "11/01/2003", "causale 5",
				"description 5", "21348", "06/06/1980", "434", "cause 5",
				"compensation 5", "2787", "rag soc 9", "rag soc 10", "111",
				"04/04/1998", "8989898989", "98.000,00 Û", "98873", "876",
				"via Tibutina", "01234", "23423423", "2436233453",
				"fdsfsdfsd@vige.it", "ffkkfkf.com", "34", "PA", "Francoforte",
				"Paris", "51656", image, "Logo per magazzino", 344,
				"logo1.gif", true, "2563", "34322", "cliente 6", "rag soc 6",
				"2535345433", "34322", "via Prenestina", "00152",
				"87584734637", "84562354656", "gdhdgjfgj@vige.it", "ppopo.com",
				"24", "SH", "Zagarolo", "Shangai", "51656", image,
				"Logo per customer", 314, "logo9.gif", true, "2563"));
		em.persist(receiptMock.create("666", "02/05/2007", "causale 6",
				"description 6", "21349", "06/06/1982", "22", "cause 6",
				"compensation 6", "53453", "rag soc 11", "rag soc 12", "111",
				"04/04/1998", "32323232323", "333.000,00 $", "54664", "345",
				"via Prenestina", "00152", "87584734637", "84562354656",
				"gdhdgjfgj@vige.it", "ppopo.com", "124", "SH", "Zagarolo",
				"Shangai", "", image, "Logo per magazzino", 348, "logo2.gif",
				false, "", "22222", "cliente 7", "rag soc 7", "654424322",
				"22222", "piazza Tuscolo", "00012", "32678475323",
				"74684736433", "fsdfsdfsd@vige.it", "ewe.com", "76", "BO",
				"Nola", "Bombay", "55646", image, "Logo per customer", 3411,
				"logo10.gif", false, ""));
		em.persist(receiptMock.create("32322", "03/03/2002", "causale 7",
				"description 7", "21314", "06/01/1985", "534543", "cause 9",
				"compensation 9", "54546", "rag soc 17", "rag soc 18", "111",
				"04/02/1992", "43565757", "42.000,00 Û", "22223", "7654",
				"via Serafini", "00999", "534748622", "7568473634",
				"ewrete@vige.it", "ewewwq.com", "616", "KY", "Tropea", "Kyoto",
				"52656", image, "Logo per magazzino", 344, "logo3.gif", true,
				"2562", "55555", "cliente 8", "rag soc 8", "53546566", "55555",
				"via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome", "55436", image, "Logo per customer", 334, "logo11.gif",
				true, "2564"));
		em.persist(receiptMock.create("528", "11/03/2007", "causale 8",
				"description 8", "213334", "02/06/1986", "23432", "cause 10",
				"compensation 10", "6544", "rag soc 19", "rag soc 20", "111",
				"04/04/1998", "8787878787", "99.000,00 $", "43432", "23453",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", "55436", image, "Logo per magazzino", 234,
				"logo4.gif", false, "", "325", "cliente 9", "rag soc 9",
				"323244646", "325", "via Serafini", "00999", "534748622",
				"323244646", "ewrete@vige.it", "ewewwq.com", "66", "KY",
				"Tropea", "Kyoto", "", null, null, 0, null, false, ""));
		em.persist(receiptMock.create("95433", "01/05/2007", "causale 9",
				"description 9", "212344", "03/06/1987", "65464", "cause 11",
				"compensation 11", "34322", "rag soc 21", "rag soc 22", "111",
				"05/04/1992", "212121212", "33.000,00 Û", "86433", "65433",
				"via Tuscolana", "09833", "42675473364", "754684333",
				"tytre@vige.it", "swswd.com", "546", "RM", "Castelvolturno",
				"Rome", "556", null, null, 0, null, false, "", "6433",
				"cliente 10", "rag soc 10", "324464646", "6433",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", "", null, null, 0, null, false, ""));
		em.persist(receiptMock.create("5432", "11/12/2007", "causale 10",
				"description 10", "21234", "04/06/1988", "5646", "cause 12",
				"compensation 12", "6567", "rag soc 23", "rag soc 24", "111",
				"04/05/1998", "212121212", "33.000,00 Û", "86433", "123444",
				"via Serafini", "00999", "534748622", "7568473634",
				"ewrete@vige.it", "ewewwq.com", "626", "KY", "Tropea", "Kyoto",
				"5526", null, null, 0, null, false, "", "11121", "cliente 4",
				"rag soc 4", "76565656", "11121", "viale Giulio Cesare",
				"00987", "896969687", "32456733", "hdhhjdghf@vige.it",
				"qasas.com", "656", "FI", "Tivoli", "Florence", "55656", image,
				"Logo per customer", 394, "logo8.gif", false, ""));
		em.persist(receiptMock.create("33333", "12/05/2001", "causale 11",
				"description 11", "1344", "05/06/1989", "645645", "cause 13",
				"compensation 13", "5454", "rag soc 25", "rag soc 26", "111",
				"04/04/1995", "212121212", "33.000,00 Û", "86433", "876544",
				"via Serafina", "00666", "63564832764", "3467468733",
				"ngnghghg@vige.it", "llklk.it", "33", "TK", "Palinuro",
				"Tokyo", "55956", null, null, 0, null, false, "", "11121",
				"cliente 4", "rag soc 4", "76565656", "11121",
				"viale Giulio Cesare", "00987", "896969687", "32456733",
				"hdhhjdghf@vige.it", "qasas.com", "656", "FI", "Tivoli",
				"Florence", "55656", image, "Logo per customer", 394,
				"logo8.gif", false, ""));
		utx.commit();
	}

	@Test
	public void searchReceipt() throws Exception {
		Assert.assertEquals(11, em.createQuery("select b from Receipt b")
				.getResultList().size());
	}
}
