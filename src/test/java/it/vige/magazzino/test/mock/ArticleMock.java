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

import it.vige.magazzino.model.Article;
import it.vige.magazzino.test.operation.ArticleOperation;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface ArticleMock {

	ArticleOperation articleOperation = new ArticleOperation();

	Article article0 = articleOperation.create("898989", "656565",
			"description 1", "3213", "34534", "23423", "67545", "234234",
			"323", "provider 1", "234234", "675765", "322", "5454", "3232",
			"545", "656", "5646541", "232", "5454", "making 1", "notes 1");

	Article article1 = articleOperation.create("2121", "23131",
			"description 2", "7567567", "876867", "5675", "345345", "786867",
			"22", "provider 2", "212100", "43242", "6546", "4343", "765724",
			"5443", "4324653", "878782", "656", "65433", "making 2", "notes 2");

	Article article2 = articleOperation.create("3453", "6462", "description 3",
			"65472", "645272", "5136", "53436", "6754382", "4343",
			"provider 3", "65462", "76475", "5431", "53477", "534512", "6456",
			"434", "434343", "65645", "4234423", "making 3", "notes 3");

	Article article3 = articleOperation.create("653724", "65467",
			"description 4", "756737", "53456", "745737", "655", "76574653",
			"32", "provider 4", "5435667", "654677", "765765", "53456",
			"65467", "6546", "765765", "4545454", "534534", "65464",
			"making 4", "notes 4");

	Article article4 = articleOperation.create("1234314", "5345345",
			"description 5", "654645", "4234324", "6456546", "423423423",
			"546546", "54343", "provider 5", "42434", "5466", "34536", "64563",
			"234", "6565", "656545", "343435", "434", "545", "making 5",
			"notes 5");

	Article article5 = articleOperation.create("123523", "4324235",
			"description 6", "432400", "534543", "42342", "4355", "645654",
			"54533", "provider 6", "42342", "53453", "656", "534534", "645654",
			"435", "654654", "343436", "423423", "534543", "making 6",
			"notes 6");

	Article article6 = articleOperation.create("6213", "743423",
			"description 7", "534567", "42356", "4234", "67545", "234234",
			"664", "provider 7", "234234", "675765", "322", "5454", "3232",
			"545", "656", "767677", "232", "5454", "making 7", "notes 7");

	Article article7 = articleOperation.create("567567", "23423",
			"description 8", "5454", "65464", "756765", "53453", "7657",
			"3322", "provider 8", "42342", "64564", "7657", "5435", "7567",
			"86786", "6456", "454548", "535", "756756", "making 8", "notes 8");

	Article article8 = articleOperation
			.create("87687634", "5435", "description 9", "65346", "243423",
					"645645", "42343", "645654", "744", "provider 9", "424000",
					"64564", "5345", "65465", "4234", "86785", "52524",
					"56569", "534543", "65465", "making 9", "notes 9");

	Article article9 = articleOperation.create("3222", "5435344",
			"description 10", "3232", "4343", "46576476", "423423", "645654",
			"3233", "provider 10", "42342", "8767856", "5454", "222", "555",
			"44433", "555", "434310", "42342", "7656755", "making 10",
			"notes 10");

	Article article10 = articleOperation.create("34655673", "4242",
			"description 11", "34333", "555", "222", "5345465", "54356",
			"654745", "provider 11", "534536", "633", "2423", "74675",
			"534543", "65465", "5435", "343411", "23423", "645645",
			"making 11", "notes 11");

	Article article11 = articleOperation.create("663", "65466",
			"description 12", "4242", "5433", "534543", "456456", "42342",
			"54", "provider 12", "777000", "444", "333", "555", "5674", "4242",
			"78878", "898912", "33543", "42343232", "making 12", "notes 12");

	Article article12 = articleOperation.create("73267", "4567",
			"description 13", "7654", "8765", "534674", "53467", "5346", "657",
			"provider 13", "76475", "53457", "53453", "6546", "765746", "5345",
			"65666", "343413", "5", "5435", "making 13", "notes 13");

	Article article13 = articleOperation.create("23333", "4753",
			"description 14", "645645", "34534", "42343", "67545", "234234",
			"332", "provider 14", "234234", "64564", "322", "5454", "3232",
			"2342", "42342", "7878714", "42235", "645653", "making 14",
			"notes 14");

	Article article14 = articleOperation.create("5435322", "53453",
			"description 15", "35356", "34534", "23423", "67545", "234234",
			"677456", "provider 15", "234234", "675765", "322", "5454", "3232",
			"545", "656", "4545415", "232", "5454", "making 15", "notes 15");

	Article article15 = articleOperation.create("3334", "5345",
			"description 16", "3213", "456456", "74675", "534634", "345353",
			"4224", "provider 16", "234234", "675765", "322", "5454", "3232",
			"545", "656", "44416", "232", "5454", "making 16", "notes 16");

	Article[] articles = articleOperation.getAllArticles();

}
