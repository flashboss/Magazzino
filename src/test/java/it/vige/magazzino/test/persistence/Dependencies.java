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
package it.vige.magazzino.test.persistence;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

/**
 * @author <a href="http://www.vige.it">Luca Stancapiano</a>
 */
public interface Dependencies {
	static final Archive<?>[] SOLDER = DependencyResolvers
			.use(MavenDependencyResolver.class).loadReposFromPom("pom.xml")
			.artifact("org.jboss.seam.solder:seam-solder:3.0.0.Final")
			.resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
	static final Archive<?>[] INTERNATIONAL = DependencyResolvers
			.use(MavenDependencyResolver.class).loadReposFromPom("pom.xml")
			.artifact("org.jboss.seam.international:seam-international")
			.exclusion("*").resolveAs(GenericArchive.class)
			.toArray(new Archive<?>[0]);
	static final Archive<?>[] FACES = DependencyResolvers
			.use(MavenDependencyResolver.class).loadReposFromPom("pom.xml")
			.artifact("org.jboss.seam.faces:seam-faces")
			.resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
	static final Archive<?>[] RICHFACES = DependencyResolvers
			.use(MavenDependencyResolver.class).loadReposFromPom("pom.xml")
			.artifact("org.richfaces.ui:richfaces-components-api:4.1.0.Final")
			.resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
}