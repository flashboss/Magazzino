package it.vige.magazzino.test;

import java.io.File;
import java.io.IOException;

public class Utils {

	static public String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// look up high nibble char
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return setBinaryLength(sb.toString());
	}

	static public String setBinaryLength(String result) {
		while (true) {
			if (result.endsWith("0"))
				result = result.substring(0, result.lastIndexOf("0"));
			else
				break;
		}
		if (result.length() % 2 != 0)
			result = result + "0";
		return result;
	}

	// table to convert a nibble to a hex char.
	static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String tempFile;

	static {
		try {
			tempFile = File.createTempFile("prova", "test").getAbsolutePath();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
