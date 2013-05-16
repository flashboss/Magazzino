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
package it.vige.magazzino.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.solder.core.Veto;

/**
 * <p>
 * <strong>Article</strong> is the model/entity class that represents an article
 * of the jar to insert and select.
 * </p>
 * 
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Entity
@Table(name = "article")
@Veto
public class Article implements Serializable {
	private static final long serialVersionUID = 5030595742331298971L;
	private String codeArticle;
	private String barCode;
	private String description;
	private String um;
	private String mis;
	private String catMerc;
	private String imponible;
	private String prize;
	private String cost;
	private String provider;
	private String rate;
	private String ca;
	private String sc1;
	private String sc2;
	private String sc3;
	private String qtmin;
	private String qtmax;
	private String pack;
	private String health;
	private String volume;
	private String making;
	private String notes;

	public Article() {
	}

	public Article(final String codeArticle, final String barCode) {
		this.codeArticle = codeArticle;
		this.barCode = barCode;
	}

	public Article(final String code, final String barCode,
			final String description, final String um, final String mis,
			final String catMerc, final String imponible, final String prize,
			final String cost, final String provider, final String rate,
			final String ca, final String sc1, final String sc2,
			final String sc3, final String qtmin, final String qtmax,
			final String pack, final String health, final String volume,
			final String making, final String notes) {
		this(code, barCode);
		this.description = description;
		this.um = um;
		this.mis = mis;
		this.catMerc = catMerc;
		this.imponible = imponible;
		this.prize = prize;
		this.cost = cost;
		this.provider = provider;
		this.rate = rate;
		this.ca = ca;
		this.sc1 = sc1;
		this.sc2 = sc2;
		this.sc3 = sc3;
		this.qtmin = qtmin;
		this.qtmax = qtmax;
		this.pack = qtmax;
		this.health = health;
		this.volume = volume;
		this.making = making;
		this.notes = notes;
	}

	@Id
	@NotNull
	@Size(min = 1, max = 100)
	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(final String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(final String barCode) {
		this.barCode = barCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getUm() {
		return um;
	}

	public void setUm(final String um) {
		this.um = um;
	}

	public String getMis() {
		return mis;
	}

	public void setMis(final String mis) {
		this.mis = mis;
	}

	public String getCatMerc() {
		return catMerc;
	}

	public void setCatMerc(final String catMerc) {
		this.catMerc = catMerc;
	}

	public String getImponible() {
		return imponible;
	}

	public void setImponible(final String imponible) {
		this.imponible = imponible;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(final String prize) {
		this.prize = prize;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(final String cost) {
		this.cost = cost;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(final String provider) {
		this.provider = provider;
	}

	@NotNull
	@Size(min = 5, max = 15)
	public String getRate() {
		return rate;
	}

	public void setRate(final String rate) {
		this.rate = rate;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(final String ca) {
		this.ca = ca;
	}

	public String getSc1() {
		return sc1;
	}

	public void setSc1(final String sc1) {
		this.sc1 = sc1;
	}

	public String getSc2() {
		return sc2;
	}

	public void setSc2(final String sc2) {
		this.sc2 = sc2;
	}

	public String getSc3() {
		return sc3;
	}

	public void setSc3(final String sc3) {
		this.sc3 = sc3;
	}

	public String getQtmin() {
		return qtmin;
	}

	public void setQtmin(final String qtmin) {
		this.qtmin = qtmin;
	}

	public String getQtmax() {
		return qtmax;
	}

	public void setQtmax(final String qtmax) {
		this.qtmax = qtmax;
	}

	@NotNull
	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w*$", message = "it has to be a number")
	public String getPack() {
		return pack;
	}

	public void setPack(final String pack) {
		this.pack = pack;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(final String health) {
		this.health = health;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(final String volume) {
		this.volume = volume;
	}

	public String getMaking() {
		return making;
	}

	public void setMaking(final String making) {
		this.making = making;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return description;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Article))
			return super.equals(arg0);
		else
			return this.getCodeArticle().equals(
					((Article) arg0).getCodeArticle());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return codeArticle.hashCode();
	}
}
