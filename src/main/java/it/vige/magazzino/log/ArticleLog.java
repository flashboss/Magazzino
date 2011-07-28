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

import it.vige.magazzino.model.Article;

import java.util.List;

import org.jboss.logging.Logger.Level;
import org.jboss.seam.solder.logging.Log;
import org.jboss.seam.solder.logging.MessageLogger;
import org.jboss.seam.solder.messages.Message;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@MessageLogger
public interface ArticleLog {
    @Log(level = Level.INFO)
    @Message("%s selected with bar code %s. This is the description: %s.")
    void articleSelected(String articleCode, String articleBarCode, String articleDescription);

    @Log(level = Level.WARN)
    @Message("Aviability for article with code %s: %s.")
    void articleAvailable(String articleCode, boolean available);

    @Log(level = Level.INFO)
    @Message("New article at the %s confirmed with bar code %s.")
    void articleConfirmed(String articleCode, String articleBarCode);

    @Log(level = Level.DEBUG)
    @Message("Articles found: %s.")
    void searchExecuted(List<Article> articles);
}
