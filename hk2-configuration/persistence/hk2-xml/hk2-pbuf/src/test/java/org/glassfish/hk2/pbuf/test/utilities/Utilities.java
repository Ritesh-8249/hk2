/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.hk2.pbuf.test.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.pbuf.api.PBufUtilities;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.external.generator.ServiceLocatorGeneratorImpl;

/**
 * @author jwells
 *
 */
public class Utilities {
    private final static ServiceLocatorGenerator GENERATOR = new ServiceLocatorGeneratorImpl();
    
    public static ServiceLocator enableLocator(Class<?>... classes) {
        ServiceLocator retVal = ServiceLocatorFactory.getInstance().create(null, null, GENERATOR);
        
        ServiceLocatorUtilities.addClasses(retVal, classes);
        
        PBufUtilities.enablePBufService(retVal);
        
        // Twice tests idempotence
        PBufUtilities.enablePBufService(retVal);
        
        return retVal;
    }
    
    public static byte[] readStreamFully(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte buffer[] = new byte[1000];
            
            int readLength;
            while ((readLength = stream.read(buffer)) > 0) {
                baos.write(buffer, 0, readLength);
            }
            
            baos.flush();
            
            return baos.toByteArray();
        }
        finally {
            baos.close();
        }
        
    }
    
    public static int getNumPBufLengthBytes(byte bytes[]) {
        int lcv = 0;
        for (byte b : bytes) {
            if ((b & 0x80) == 0) {
                return lcv + 1;
            }
            
            lcv++;
        }
        
        return lcv+1;
    }

}
