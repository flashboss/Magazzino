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
package it.vige.magazzino.log;

import it.vige.magazzino.model.Magazzino;

import java.util.List;

import org.jboss.solder.logging.Logger.Level;
import org.jboss.solder.logging.Log;
import org.jboss.solder.logging.MessageLogger;
import org.jboss.solder.messages.Message;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@MessageLogger
public interface MagazzinoLog {
    @Log(level = Level.INFO)
    @Message("%s selected with customer %s and cause %s.")
    void jarSelected(String jarNumber, String customerCode, String jarCause);

    @Log(level = Level.WARN)
    @Message("Aviability for jar with number %s: %s.")
    void jarAvailable(String jarNumber, boolean available);

    @Log(level = Level.INFO)
    @Message("New jar at the %s confirmed with customer %s.")
    void jarConfirmed(String jarNumber, String customerCode);

    @Log(level = Level.DEBUG)
    @Message("Jars found: %s.")
    void searchExecuted(List<Magazzino> jars);
}
