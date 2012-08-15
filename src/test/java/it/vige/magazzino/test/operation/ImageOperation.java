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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ImageOperation {

	private List<byte[]> images = new ArrayList<byte[]>();

	public byte[] create() {
		byte[] bytes = new byte[10310];
		InputStream fis = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("logo.gif");
		try {
			fis.read(bytes);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		images.add(bytes);
		return bytes;
	}

	public byte[][] getAllImages() {
		return images.toArray(new byte[0][0]);
	}
}
