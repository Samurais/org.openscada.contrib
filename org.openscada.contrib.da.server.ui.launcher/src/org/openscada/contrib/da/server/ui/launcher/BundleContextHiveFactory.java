/*
 * This file is part of the openSCADA project
 * 
 * Copyright (C) 2013 Jens Reimann (ctron@dentrassi.de)
 *
 * openSCADA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * only, as published by the Free Software Foundation.
 *
 * openSCADA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License version 3 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with openSCADA. If not, see
 * <http://opensource.org/licenses/lgpl-3.0.html> for a copy of the LGPLv3 License.
 */

package org.openscada.contrib.da.server.ui.launcher;

import java.util.Collection;

import org.openscada.da.core.server.Hive;
import org.openscada.da.server.common.HiveCreator;
import org.openscada.da.server.exporter.ConfigurationException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class BundleContextHiveFactory
{

    private final static Logger logger = LoggerFactory.getLogger ( BundleContextHiveFactory.class );

    private final BundleContext context;

    public BundleContextHiveFactory ( final BundleContext context )
    {
        this.context = context;
    }

    public Hive createHive ( final String reference, final Node node ) throws ConfigurationException
    {
        try
        {
            final Collection<ServiceReference<HiveCreator>> refs = this.context.getServiceReferences ( HiveCreator.class, String.format ( "(%s=%s)", HiveCreator.SERVICE_REFERENCE_TYPE, reference ) );
            if ( refs.isEmpty () )
            {
                throw new ConfigurationException ( "Unable to create hive", new IllegalArgumentException ( String.format ( "Hive '%s' is unknown", reference ) ) );
            }

            final ServiceReference<HiveCreator> first = refs.iterator ().next ();
            final HiveCreator service = this.context.getService ( first );
            try
            {
                logger.debug ( "Delegating call to: {}", service );
                final Hive result = service.createHive ( reference, node );
                if ( result == null )
                {
                    throw new IllegalArgumentException ( "Creator did not create hive" );
                }
                return result;
            }
            catch ( final Exception e )
            {
                throw new ConfigurationException ( "Failed to create hive", e );
            }
            finally
            {
                this.context.ungetService ( first );
            }
        }
        catch ( final InvalidSyntaxException e )
        {
            throw new ConfigurationException ( "Failed to locate services", e );
        }
    }

}
