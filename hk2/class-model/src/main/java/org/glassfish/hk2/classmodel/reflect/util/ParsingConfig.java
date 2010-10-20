/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright 2010 Sun Microsystems, Inc. All rights reserved.
 *
 *  The contents of this file are subject to the terms of either the GNU
 *  General Public License Version 2 only ("GPL") or the Common Development
 *  and Distribution License("CDDL") (collectively, the "License").  You
 *  may not use this file except in compliance with the License. You can obtain
 *  a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 *  or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 *  language governing permissions and limitations under the License.
 *
 *  When distributing the software, include this License Header Notice in each
 *  file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 *  Sun designates this particular file as subject to the "Classpath" exception
 *  as provided by Sun in the GPL Version 2 section of the License file that
 *  accompanied this code.  If applicable, add the following below the License
 *  Header, with the fields enclosed by brackets [] replaced by your own
 *  identifying information: "Portions Copyrighted [year]
 *  [name of copyright owner]"
 *
 *  Contributor(s):
 *
 *  If you wish your version of this file to be governed by only the CDDL or
 *  only the GPL Version 2, indicate your decision by adding "[Contributor]
 *  elects to include this software in this distribution under the [CDDL or GPL
 *  Version 2] license."  If you don't indicate a single choice of license, a
 *  recipient has the option to distribute your version of this file under
 *  either the CDDL, the GPL Version 2 or to extend the choice of license to
 *  its licensees as provided above.  However, if you add GPL Version 2 code
 *  and therefore, elected the GPL Version 2 license, then the option applies
 *  only if the new code is made subject to such option by the copyright
 *  holder.
 */
package org.glassfish.hk2.classmodel.reflect.util;

import java.util.Set;

/**
 * Configuration for the parser.
 *
 * @author Jerome Dochez
 */
public interface ParsingConfig {

    /**
     * Returns a list of annotations that denotes dependency injection enabled
     * classes (classes that use dependency injection). A class annotated with
     * one the returned annotation getName will potentially define one or more
     * injection point.
     *
     * @return list of annotations that denote a dependency injection enabled type.
     */
    public Set<String> getInjectionTargetAnnotations();

    /**
     * Returns a list of interfaces that denotes a dependency injection enabled
     * classes. A class implementing one of the interface returned will
     * potentially define one or more injection point.
     *
     * @return list of interfaces that a class can implement that will denote possible
     * use of dependency injection
     */
    public Set<String> getInjectionTargetInterfaces();

    /**
     * Returns a list of annotations that denote an injection point within an
     * dependency injection enabled type. This injection point (representing
     * a dependency) is either a constructor parameter, a field or a method.
     *
     * @return list of annotations denoting injection points (like @Inject).
     */
    public Set<String> getInjectionPointsAnnotations();
}
