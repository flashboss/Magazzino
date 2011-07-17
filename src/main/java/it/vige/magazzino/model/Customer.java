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

import org.jboss.seam.solder.core.Veto;

/**
 * <p>
 * <strong>Customer</strong> is the model/entity class that represents a customer who may buy an article.
 * </p>
 *
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
@Entity
@Table(name = "customer")
@Veto
public class Customer implements Serializable {
    private static final long serialVersionUID = -612733026033932730L;
    private String code;
    private String name;
    private String ragSocial;

    public Customer() {
    }

    public Customer(final String code, final String name, final String ragSocial) {
        this.code = code;
        this.name = name;
        this.ragSocial = ragSocial;
    }

    @NotNull
    @Size(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Id
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = "^\\w*$", message = "not a valid customer")
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @NotNull
    public String getRagSocial() {
        return ragSocial;
    }

    public void setRagSocial(final String ragSocial) {
        this.ragSocial = ragSocial;
    }

    @Override
    public String toString() {
        return "Customer(" + code + ")";
    }
}
