package it.vige.magazzino.pdf;

import it.vige.magazzino.model.Receipt;

public interface DocumentReceipt {
	void build(Receipt receipt) throws Exception;
}
