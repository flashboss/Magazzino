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

import it.vige.magazzino.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public class ArticleOperation {

	private List<Article> articles = new ArrayList<Article>();
	
	public Article create(String code, String barCode, String description,
			String um, String mis, String catMerc, String imponible,
			String prize, String cost, String provider, String rate, String ca,
			String sc1, String sc2, String sc3, String qtmin, String qtmax,
			String pack, String health, String volume, String making,
			String notes) {
		Article article = new Article();
		article.setBarCode(barCode);
		article.setCa(ca);
		article.setCatMerc(catMerc);
		article.setCode(code);
		article.setCost(cost);
		article.setDescription(description);
		article.setHealth(health);
		article.setImponible(imponible);
		article.setMaking(making);
		article.setMis(mis);
		article.setNotes(notes);
		article.setPack(pack);
		article.setPrize(prize);
		article.setProvider(provider);
		article.setQtmax(qtmax);
		article.setQtmin(qtmin);
		article.setRate(rate);
		article.setSc1(sc1);
		article.setSc2(sc2);
		article.setSc3(sc3);
		article.setUm(um);
		article.setVolume(volume);
		
		articles.add(article);
		return article;
	}

	public Article[] getAllArticles() {
		return articles.toArray(new Article[0]);
	}
}
