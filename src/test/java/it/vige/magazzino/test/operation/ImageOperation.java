package it.vige.magazzino.test.operation;

import java.io.InputStream;

public class ImageOperation {

	public byte[] create() throws Exception {
		byte[] bytes = new byte[10310];
		InputStream fis = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("logo.gif");
		fis.read(bytes);
		fis.close();
		return bytes;
	}
}
