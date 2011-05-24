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
package it.vige.magazzino.i18n;

import org.jboss.seam.international.status.builder.BundleKey;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class DefaultBundleKey extends BundleKey {
	
	private static final long serialVersionUID = -602733026031111730L;

    public static final String DEFAULT_BUNDLE_NAME = "messages";

    public DefaultBundleKey(String key) {
        super(DEFAULT_BUNDLE_NAME, key);
    }
}
